package com.java.dev.shopping.app.Service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {
    private int availableQuantity;
    private int orderedQuantity;

    @PostConstruct
    public void initializeInventory() {
        // Initialize inventory on startup
        availableQuantity = 100;
        orderedQuantity = 0;
    }

    public Map<String, Integer> getInventoryStatus() {
        Map<String, Integer> inventoryStatus = new HashMap<>();
        inventoryStatus.put("ordered", orderedQuantity);
        inventoryStatus.put("price", 100); // Assuming fixed price
        inventoryStatus.put("available", availableQuantity);
        return inventoryStatus;
    }

    public void updateOrderedQuantity(int quantity) {
        // Update ordered quantity
        orderedQuantity += quantity;
        availableQuantity -= quantity;
    }
}
