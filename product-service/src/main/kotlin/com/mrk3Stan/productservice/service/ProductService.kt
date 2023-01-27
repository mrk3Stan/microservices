package com.mrk3Stan.productservice.service

import com.mrk3Stan.productservice.dto.ProductRequest
import com.mrk3Stan.productservice.dto.ProductResponse
import com.mrk3Stan.productservice.model.Product
import com.mrk3Stan.productservice.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductService(val productRepository: ProductRepository) {

    private val log = LoggerFactory.getLogger(ProductService::class.java)

    fun create(productRequest: ProductRequest) {
        val product = Product(name = productRequest.name, description = productRequest.description, price = productRequest.price)
        productRepository.save(product)
        log.info("Product ${product.name} is saved")
    }

    fun getAll(): List<ProductResponse> {
        val products = productRepository.findAll()
        return products.stream()
            .map { product -> mapToProductResponse(product) }
            .toList()
    }

    private fun mapToProductResponse(product: Product): ProductResponse {
        return ProductResponse(product.id.toString(), product.name, product.description, product.price)
    }
}