package br.com.bookstore.bookstore.client.services;

import br.com.bookstore.bookstore.client.Client;

@FunctionalInterface
public interface SaveClientService {

    void insert(Client client);
}
