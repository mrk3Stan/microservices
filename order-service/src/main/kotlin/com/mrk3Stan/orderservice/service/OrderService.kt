package com.mrk3Stan.orderservice.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.mrk3Stan.orderservice.dto.InventoryResponse
import com.mrk3Stan.orderservice.dto.OrderLineItemsDto
import com.mrk3Stan.orderservice.dto.OrderRequest
import com.mrk3Stan.orderservice.model.Order
import com.mrk3Stan.orderservice.model.OrderLineItems
import com.mrk3Stan.orderservice.repository.OrderRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.reactive.function.client.WebClient
import java.util.UUID

@Service
@Transactional
class OrderService {

    private val log = LoggerFactory.getLogger(OrderService::class.java)

    @Autowired
    private lateinit var orderRepository: OrderRepository
    @Autowired
    private lateinit var webClientBuilder: WebClient.Builder
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    fun placeOrder(orderRequest: OrderRequest): String {
        val order = Order(
            orderNumber = UUID.randomUUID().toString(),
            orderLineItems = orderRequest.orderLineItemsDto
                .stream()
                .map { orderLineItemsDto -> mapToDto(orderLineItemsDto) }
                .toList()
        )
        val skuCodes = order.orderLineItems.stream()
            .map(OrderLineItems::skuCode)
            .toList()

        val inventoryResponses = callInventoryService(skuCodes)
        val allProductsInStock = inventoryResponses.stream().allMatch(InventoryResponse::isInStock)

        if (allProductsInStock) {
            orderRepository.save(order)
            return "Order places successfully."
        } else {
            throw IllegalArgumentException("Product not in stock!")
        }
    }

    private fun mapToDto(orderLineItemsDto: OrderLineItemsDto) = OrderLineItems(
            id = orderLineItemsDto.id,
            skuCode = orderLineItemsDto.skuCode,
            price = orderLineItemsDto.price,
            quantity = orderLineItemsDto.quantity
        )

    private fun callInventoryService(skuCodes: List<String>): List<InventoryResponse> {
        // call inventory service, and place order if product is in stock
        val inventoryResponseList: List<*>? = webClientBuilder.build().get()
            .uri("http://inventory-service/api/inventory") {
                    uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build()
            }
            .retrieve()
            .bodyToMono(List::class.java)
            .block()    // so that  webclient will have a  synchronous request

        log.info("inventoryResponseList - $inventoryResponseList")

        return objectMapper.convertValue(inventoryResponseList, object: TypeReference<List<InventoryResponse>>(){})
    }
}