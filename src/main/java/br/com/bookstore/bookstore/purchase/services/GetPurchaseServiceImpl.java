package br.com.bookstore.bookstore.purchase.services;

import br.com.bookstore.bookstore.purchase.Purchase;
import br.com.bookstore.bookstore.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetPurchaseServiceImpl implements GetPurchaseService{

    private final PurchaseRepository purchaseRepository;

    @Override
    public Purchase findById(Long id) {
        return purchaseRepository.findById(id).orElseThrow(Error::new);
    }
}
