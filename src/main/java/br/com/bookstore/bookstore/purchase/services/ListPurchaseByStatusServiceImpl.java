package br.com.bookstore.bookstore.purchase.services;

import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import br.com.bookstore.bookstore.purchase.Purchase;
import br.com.bookstore.bookstore.purchase.PurchaseRepository;
import br.com.bookstore.bookstore.purchase.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListPurchaseByStatusServiceImpl implements ListPurchaseByStatusService {

    private final PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> findAllPurchaseByStatus(Status status) {
        if(purchaseRepository.findPurchaseByStatus(status).isEmpty()){
            throw new CategoryOfBookNotFoundException();
        }

        return purchaseRepository.findPurchaseByStatus(status);
    }
}