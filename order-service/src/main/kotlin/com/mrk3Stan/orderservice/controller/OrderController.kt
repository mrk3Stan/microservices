package com.mrk3Stan.orderservice.controller

import com.mrk3Stan.orderservice.dto.OrderRequest
import com.mrk3Stan.orderservice.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/order")
class OrderController {

    @Autowired
    private lateinit var orderService: OrderService

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun placeOrder(@RequestBody orderRequest: OrderRequest): String {
        orderService.placeOrder(orderRequest)
        return "Order places successfully."
    }
}