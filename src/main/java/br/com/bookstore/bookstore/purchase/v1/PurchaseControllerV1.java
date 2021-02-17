package br.com.bookstore.bookstore.purchase.v1;

import br.com.bookstore.bookstore.purchase.PurchaseDTO;
import br.com.bookstore.bookstore.purchase.services.GetPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/purchase")
public class PurchaseControllerV1 {

    private final GetPurchaseService getPurchaseService;

    @GetMapping(value = "/{id}")
    public PurchaseDTO find(@PathVariable Long id) {
        return PurchaseDTO.from(getPurchaseService.findById(id));
    }
}
