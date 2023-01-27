package com.mrk3Stan.orderservice.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "t_order_line_items")
data class OrderLineItems(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val skuCode: String,
    val price: BigDecimal,
    val quantity: Int
)