package br.com.bookstore.bookstore.purchase.services;

import br.com.bookstore.bookstore.purchase.Purchase;
import br.com.bookstore.bookstore.purchase.Status;

import java.util.List;

@FunctionalInterface
public interface ListPurchaseByStatusService {
    List<Purchase> findAllPurchaseByStatus(Status status);
}
