package com.mrk3Stan.inventoryservice.service

import com.mrk3Stan.inventoryservice.dto.InventoryResponse
import com.mrk3Stan.inventoryservice.repository.InventoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InventoryService {

    @Autowired
    private lateinit var inventoryRepository: InventoryRepository

    @Transactional(readOnly = true)
    fun isInStock(skuCode: List<String>): List<InventoryResponse> {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
            .map { i -> InventoryResponse(i.skuCode, i.quantity > 0) }
            .toList()
    }
}