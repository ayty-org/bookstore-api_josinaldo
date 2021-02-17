package br.com.bookstore.bookstore.purchase.services;

import br.com.bookstore.bookstore.purchase.Purchase;
import br.com.bookstore.bookstore.purchase.PurchaseRepository;
import br.com.bookstore.bookstore.purchase.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SavePurchaseServiceImpl implements SavePurchaseService{

    private final PurchaseRepository purchaseRepository;

    @Override
    public void insert(Purchase purchase) {
        Purchase purchaseSaved = new Purchase(
                null,
                purchase.getClient(),
                purchase.getPurchasedBooks(),
                purchase.getAmountToPlay(),
                Status.PENDING
                );

        purchaseRepository.save(purchaseSaved);
    }
}
