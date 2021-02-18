package br.com.bookstore.bookstore.purchase.services;

import br.com.bookstore.bookstore.purchase.Purchase;
import br.com.bookstore.bookstore.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListPurchaseServiceImpl implements ListPurchaseService{

    private final PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findAll() {
        return purchaseRepository.findAll();
    }
}
