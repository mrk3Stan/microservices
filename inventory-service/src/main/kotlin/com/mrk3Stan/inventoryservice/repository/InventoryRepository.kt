package com.mrk3Stan.inventoryservice.repository

import com.mrk3Stan.inventoryservice.model.Inventory
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface InventoryRepository : JpaRepository<Inventory, Long> {

    fun findBySkuCodeIn(skuCode: List<String>): List<Inventory>
}