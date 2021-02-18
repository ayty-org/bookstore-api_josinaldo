package br.com.bookstore.bookstore.purchase.services;

import br.com.bookstore.bookstore.exceptions.PurchaseNotFoundException;
import br.com.bookstore.bookstore.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeletePurchaseServiceImpl implements DeletePurchaseService{

    private final PurchaseRepository purchaseRepository;
    @Override
    public void delete(Long id) {
        if(!purchaseRepository.existsById(id)) {
            throw new PurchaseNotFoundException();
        }

        purchaseRepository.deleteById(id);
    }
}
