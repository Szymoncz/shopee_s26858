package com.ecommerce.shopees26858.service;

import model.Client;
import model.UserBasket;
import com.ecommerce.shopees26858.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShopManager {
    @Autowired
    private ClientRepository clientRepository;

    private final Map<String, Double> storeInventory = new HashMap<>();

    public ShopManager() {
        storeInventory.put("Milk", 5.5);
        storeInventory.put("Beer", 10.4);
        storeInventory.put("Bread", 4.2);
    }

    public boolean processOrder(String clientId, UserBasket basket) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client == null) return false;

        double totalCost = basket.getItems().stream()
                .filter(storeInventory::containsKey)
                .mapToDouble(storeInventory::get)
                .sum();

        if (client.canAfford(totalCost)) {
            client.setBalance(client.getBalance() - totalCost);
            return true;
        }
        return false;
    }
}

