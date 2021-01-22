package br.com.bookstore.bookstore.client.services;

import br.com.bookstore.bookstore.client.Client;

@FunctionalInterface
public interface UpdateClientService {

    void update(Client client, Long id);
}
