package com.ecommerce.shopees26858.repository;

import model.Client;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ClientRepository {
    private final Map<String, Client> clients = new HashMap<>();

    public void save(Client client) {
        clients.put(client.getId(), client);
    }

    public Optional<Client> findById(String id) {
        return Optional.ofNullable(clients.get(id));
    }
}
