package com.mrk3Stan.productservice.controller

import com.mrk3Stan.productservice.dto.ProductRequest
import com.mrk3Stan.productservice.dto.ProductResponse
import com.mrk3Stan.productservice.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/product")
class ProductController(val productService: ProductService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody productRequest: ProductRequest) {
        productService.create(productRequest)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): List<ProductResponse> = productService.getAll()
}