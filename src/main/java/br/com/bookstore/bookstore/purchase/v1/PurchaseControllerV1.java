package br.com.bookstore.bookstore.purchase.v1;

import br.com.bookstore.bookstore.book.BookDTO;
import br.com.bookstore.bookstore.purchase.Purchase;
import br.com.bookstore.bookstore.purchase.PurchaseDTO;
import br.com.bookstore.bookstore.purchase.services.GetPurchaseService;
import br.com.bookstore.bookstore.purchase.services.ListPurchaseService;
import br.com.bookstore.bookstore.purchase.services.SavePurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/purchase")
public class PurchaseControllerV1 {

    private final GetPurchaseService getPurchaseService;
    private final ListPurchaseService listPurchaseService;
    private final SavePurchaseService savePurchaseService;

    @GetMapping(value = "/{id}")
    public PurchaseDTO find(@PathVariable Long id) {
        return PurchaseDTO.from(getPurchaseService.findById(id));
    }

    public List<PurchaseDTO> findAll() {
        return PurchaseDTO.fromAll(listPurchaseService.findAll());
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public void insert(@Valid @RequestBody PurchaseDTO purchaseDTO){
        savePurchaseService.insert(Purchase.to(purchaseDTO));
    }
}
