package br.com.bookstore.bookstore.purchase.services;

import br.com.bookstore.bookstore.purchase.Purchase;
import br.com.bookstore.bookstore.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SavePurchaseServiceImpl implements SavePurchaseService{

    private final PurchaseRepository purchaseRepository;

    @Override
    public void insert(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}
