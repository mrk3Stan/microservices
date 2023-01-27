package com.mrk3Stan.inventoryservice.model

import javax.persistence.*

@Entity
@Table(name = "t_inventory")
data class Inventory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val skuCode: String,
    val quantity: Int
)