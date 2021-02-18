package br.com.bookstore.bookstore.purchase.services;

import br.com.bookstore.bookstore.purchase.Purchase;

import java.util.List;

@FunctionalInterface
public interface ListPurchaseService {
    List<Purchase> findAll();
}
