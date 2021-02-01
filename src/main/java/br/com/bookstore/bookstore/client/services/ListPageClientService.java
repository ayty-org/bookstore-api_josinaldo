package br.com.bookstore.bookstore.client.services;

import br.com.bookstore.bookstore.client.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPageClientService {
    Page<Client> findPage(Pageable pageable);
}
