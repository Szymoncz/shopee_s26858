package com.ecommerce.shopees26858;

import model.Client;
import com.ecommerce.shopees26858.repository.ClientRepository;
import com.ecommerce.shopees26858.service.ShopManager;
import model.UserBasket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Shopees26858IntegrationTests {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ShopManager shopManager;

    private Client client;
    private UserBasket basket;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId("1002");
        client.setName("Karolina");
        client.setBalance(233);
        clientRepository.save(client);

        basket = new UserBasket();
        basket.addItem("Milk");
        basket.addItem("Milk");
        basket.addItem("Milk");
        basket.addItem("Milk");
        basket.addItem("Bread");
        basket.addItem("Beer");
        basket.addItem("Beer");
        basket.addItem("Beer");
        basket.addItem("Beer");
        basket.addItem("Beer");
        basket.addItem("Beer");
        basket.addItem("Beer");
        basket.addItem("Beer");
    }

    @Test
    void testPurchaseFullRoad() {
        boolean result = shopManager.processOrder("1002", basket);
        Client updatedClient = clientRepository.findById("1002").orElseThrow();

        assertTrue(result);
        assertEquals(123.6, updatedClient.getBalance());
    }

    @Test
    void testPurchaseWithNonExistentClient() {
        boolean result = shopManager.processOrder("1003", basket);
        assertFalse(result);
    }
}
