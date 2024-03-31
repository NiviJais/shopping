package com.java.dev.shopping.app.Controllers;

import com.java.dev.shopping.app.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public ResponseEntity<Map<String, Integer>> getInventory(){
        Map<String, Integer> inventoryStatus = inventoryService.getInventoryStatus();
        return ResponseEntity.ok(inventoryStatus);
    }
}

