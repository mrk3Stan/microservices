package com.mrk3Stan.productservice

import com.fasterxml.jackson.databind.ObjectMapper
import com.mrk3Stan.productservice.dto.ProductRequest
import com.mrk3Stan.productservice.repository.ProductRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.math.BigDecimal

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	companion object {
		@Container
		val mongoDBContainer = MongoDBContainer("mongo:6.0.3")

		@DynamicPropertySource
		fun setProperties(dymDynamicPropertyRegistry: DynamicPropertyRegistry) {
			dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl)
		}
	}

	@Autowired
	private lateinit var mockMvc: MockMvc
	@Autowired
	private lateinit var objectMapper: ObjectMapper
	@Autowired
	private lateinit var productRepository: ProductRepository

	@Test
	fun shouldCreateProduct() {
		val productRequest = getProductRequest()
		val productRequestString = objectMapper.writeValueAsString(productRequest)

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
			.contentType(MediaType.APPLICATION_JSON)
			.content(productRequestString)
		).andExpect(status().isCreated)

		Assertions.assertTrue(productRepository.findAll().isNotEmpty())
	}

	@Test
	fun shouldGetProducts() {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
			.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk)
	}

	private fun getProductRequest() = ProductRequest(
		name = "test name",
		description = "testing desc",
		price = BigDecimal.valueOf(9000)
	)

}
