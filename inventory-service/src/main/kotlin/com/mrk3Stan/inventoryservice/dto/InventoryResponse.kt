package com.mrk3Stan.inventoryservice.dto

data class InventoryResponse(
    val skuCode: String,
    val isInStock: Boolean
)