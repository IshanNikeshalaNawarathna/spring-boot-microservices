package com.inventory.inventory.service;

import com.inventory.inventory.dto.InventoryDTO;
import com.inventory.inventory.model.Inventory;
import com.inventory.inventory.repo.InventoryRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<InventoryDTO> getAllInventory() {
        List<Inventory> inventoryList = inventoryRepo.findAll();
        return modelMapper.map(inventoryList, new TypeToken<List<InventoryDTO>>() {
        }.getType());
    }

    public String addInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = modelMapper.map(inventoryDTO, Inventory.class);
        inventoryRepo.save(inventory);
        return "Inventory added successfully";
    }

    public InventoryDTO updateInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = modelMapper.map(inventoryDTO, Inventory.class);
        inventoryRepo.save(inventory);
        return inventoryDTO;
    }

    public String deleteInventory(int  inventoryId) {
        inventoryRepo.deleteById(inventoryId);
        return "Inventory deleted successfully";
    }

}
