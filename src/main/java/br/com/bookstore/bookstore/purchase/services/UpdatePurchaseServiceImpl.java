package br.com.bookstore.bookstore.purchase.services;

import br.com.bookstore.bookstore.purchase.Purchase;
import br.com.bookstore.bookstore.purchase.PurchaseRepository;
import br.com.bookstore.bookstore.purchase.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdatePurchaseServiceImpl implements UpdatePurchaseService{

    private PurchaseRepository purchaseRepository;

    @Override
    public void update(Long id) {
        Purchase purchaseSaved = purchaseRepository.getOne(id);

        purchaseSaved.setPurchaseStatus(Status.DONE);

        purchaseRepository.save(purchaseSaved);
    }
}
