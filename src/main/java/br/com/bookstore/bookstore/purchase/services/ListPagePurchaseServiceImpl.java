package br.com.bookstore.bookstore.purchase.services;

import br.com.bookstore.bookstore.purchase.Purchase;
import br.com.bookstore.bookstore.purchase.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ListPagePurchaseServiceImpl implements ListPagePurchaseService{

    private PurchaseRepository purchaseRepository;

    @Override
    public Page<Purchase> findPage(Pageable pageable) {
        return purchaseRepository.findAll(pageable);
    }
}
