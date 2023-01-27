package com.mrk3Stan.orderservice.dto

data class OrderRequest(
    val orderLineItemsDto: List<OrderLineItemsDto>
)