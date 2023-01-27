package com.mrk3Stan.inventoryservice

import com.mrk3Stan.inventoryservice.model.Inventory
import com.mrk3Stan.inventoryservice.repository.InventoryRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class StartupRunner(
    private val inventoryRepository: InventoryRepository
) : CommandLineRunner {

    val log = LoggerFactory.getLogger(StartupRunner::class.java)

    override fun run(vararg args: String?) {
        inventoryRepository.save(Inventory(
            skuCode = "iphone_14",
            quantity = 10
        ))
        inventoryRepository.save(Inventory(
            skuCode = "iphone_14_pro",
            quantity = 0
        ))
        log.info("Inventory initialised.")
    }
}