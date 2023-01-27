package com.mrk3Stan.orderservice.repository

import com.mrk3Stan.orderservice.model.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
}