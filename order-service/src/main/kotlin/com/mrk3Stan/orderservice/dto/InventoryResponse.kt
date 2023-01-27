package com.mrk3Stan.orderservice.dto

data class InventoryResponse(
    val skuCode: String,
    val isInStock: Boolean
)