package br.com.bookstore.bookstore.purchase.services;

@FunctionalInterface
public interface DeletePurchaseService {
    void delete(Long id);
}
