package com.inventory.inventory.controller;

import com.inventory.inventory.dto.InventoryDTO;
import com.inventory.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@CrossOrigin
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping(value = "/getallinventory")
    private List<InventoryDTO> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping(value = "/getitembyid/{itemId}")
    private InventoryDTO getItemById(@PathVariable int itemId) {
        return inventoryService.getInventoryId(itemId);
    }

    @PostMapping(value = "/addinventory")
    private String addInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.addInventory(inventoryDTO);
    }

    @PutMapping(value = "/updateinventory")
    public InventoryDTO updateInventory(@RequestBody InventoryDTO inventoryDTO) {
        return inventoryService.updateInventory(inventoryDTO);
    }

    @DeleteMapping(value = "/deleteiventory/{inventoryId}")
    private String deleteInventory(@PathVariable int inventoryId) {
        return inventoryService.deleteInventory(inventoryId);
    }

}
