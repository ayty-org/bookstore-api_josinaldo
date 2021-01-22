package br.com.bookstore.bookstore.client.services;

import br.com.bookstore.bookstore.client.Client;

@FunctionalInterface
public interface GetClientAppService {
    Client findById(Long id);
}
