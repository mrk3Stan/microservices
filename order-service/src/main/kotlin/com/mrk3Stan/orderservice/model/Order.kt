package com.mrk3Stan.orderservice.model

import javax.persistence.*


@Entity
@Table(name = "t_orders")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val orderNumber: String,
    @OneToMany(cascade = [CascadeType.ALL])
    val orderLineItems: List<OrderLineItems>
)