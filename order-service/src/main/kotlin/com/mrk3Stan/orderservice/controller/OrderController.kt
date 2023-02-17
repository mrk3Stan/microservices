package com.mrk3Stan.orderservice.controller

import com.mrk3Stan.orderservice.dto.OrderRequest
import com.mrk3Stan.orderservice.service.OrderService
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.retry.annotation.Retry
import io.github.resilience4j.timelimiter.annotation.TimeLimiter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/api/order")
class OrderController {

    @Autowired
    private lateinit var orderService: OrderService

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    fun placeOrder(@RequestBody orderRequest: OrderRequest): CompletableFuture<String> {
        return CompletableFuture.supplyAsync { orderService.placeOrder(orderRequest) }
    }

    fun fallbackMethod(orderRequest: OrderRequest, runtimeException: RuntimeException): CompletableFuture<String> {
        return CompletableFuture.supplyAsync { "Something went wrong, please try again after some time!" }
    }
}