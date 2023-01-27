package com.mrk3Stan.inventoryservice.controller

import com.mrk3Stan.inventoryservice.dto.InventoryResponse
import com.mrk3Stan.inventoryservice.service.InventoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/inventory")
class InventoryController {

    @Autowired
    private lateinit var inventoryService: InventoryService

//    @GetMapping("/{sku-code}")
//    @ResponseStatus(HttpStatus.OK)
//    fun isInStock(@PathVariable("sku-code") skuCode: String): Boolean {
//        return inventoryService.isInStock(skuCode)
//    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun isInStock(@RequestParam skuCode: List<String>): List<InventoryResponse> {
        return inventoryService.isInStock(skuCode)
    }
}