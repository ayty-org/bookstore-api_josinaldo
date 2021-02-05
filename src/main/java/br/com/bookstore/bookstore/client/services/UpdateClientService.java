package br.com.bookstore.bookstore.client.services;

import br.com.bookstore.bookstore.client.ClientDTO;

@FunctionalInterface
public interface UpdateClientService {

    void update(ClientDTO client, Long id);
}
