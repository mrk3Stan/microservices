package com.mrk3Stan.productservice.repository

import com.mrk3Stan.productservice.model.Product
import org.springframework.data.mongodb.repository.MongoRepository

interface ProductRepository : MongoRepository<Product, String> {
}