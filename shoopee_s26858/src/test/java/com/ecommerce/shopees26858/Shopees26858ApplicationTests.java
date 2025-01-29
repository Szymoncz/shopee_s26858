package com.ecommerce.shopees26858;

import model.Client;
import model.UserBasket;
import com.ecommerce.shopees26858.repository.ClientRepository;
import com.ecommerce.shopees26858.service.ShopManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Shopees26858Tests {
	@Mock
	private ClientRepository clientRepository;

	@InjectMocks
	private ShopManager shopManager;

	private Client client;
	private UserBasket basket;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		client = new Client();
		client.setId("1001");
		client.setName("tomek");
		client.setBalance(41.0);

		basket = new UserBasket();
		basket.addItem("Milk");
		basket.addItem("Milk");
		basket.addItem("Beer");
	}

	@Test
	void testSuccessfulPurchase() {
		when(clientRepository.findById("1001")).thenReturn(java.util.Optional.of(client));
		boolean result = shopManager.processOrder("1001", basket);
		assertTrue(result);
		assertEquals(19.6, client.getBalance());
	}

	@Test
	void testInsufficientFunds() {
		client.setBalance(4.0);
		when(clientRepository.findById("1001")).thenReturn(java.util.Optional.of(client));
		boolean result = shopManager.processOrder("1001", basket);
		assertFalse(result);
		assertEquals(4.0, client.getBalance());
	}

	@Test
	void testAddingNotPresentProductToBasket() {
		basket.addItem("Coffee");
		when(clientRepository.findById("1001")).thenReturn(java.util.Optional.of(client));

		boolean result = shopManager.processOrder("1001", basket);

		assertTrue(result);
		assertEquals(19.6, client.getBalance());
	}

}