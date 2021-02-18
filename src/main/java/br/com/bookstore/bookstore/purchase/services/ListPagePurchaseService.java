package br.com.bookstore.bookstore.purchase.services;


import br.com.bookstore.bookstore.purchase.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPagePurchaseService {
    Page<Purchase> findPage(Pageable pageable);
}
