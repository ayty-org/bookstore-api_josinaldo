package br.com.bookstore.bookstore.purchase.services;

import br.com.bookstore.bookstore.purchase.Purchase;

@FunctionalInterface
public interface GetPurchaseService {
    Purchase findById(Long id);
}
