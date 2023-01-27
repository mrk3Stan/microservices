package com.mrk3Stan.orderservice.dto

import java.math.BigDecimal

data class OrderLineItemsDto(
    val id: Long,
    val skuCode: String,
    val price: BigDecimal,
    val quantity: Int
)
