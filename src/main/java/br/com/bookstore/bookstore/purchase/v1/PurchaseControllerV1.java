package br.com.bookstore.bookstore.purchase.v1;

import br.com.bookstore.bookstore.purchase.Purchase;
import br.com.bookstore.bookstore.purchase.PurchaseDTO;
import br.com.bookstore.bookstore.purchase.services.GetPurchaseService;
import br.com.bookstore.bookstore.purchase.services.ListPagePurchaseService;
import br.com.bookstore.bookstore.purchase.services.ListPurchaseService;
import br.com.bookstore.bookstore.purchase.services.SavePurchaseService;
import br.com.bookstore.bookstore.purchase.services.UpdatePurchaseService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final ListPagePurchaseService listPagePurchaseService;
    private final SavePurchaseService savePurchaseService;
    private final UpdatePurchaseService updatePurchaseService;

    @GetMapping(value = "/{id}")//list purchase by id
    public PurchaseDTO find(@PathVariable Long id) {
        return PurchaseDTO.from(getPurchaseService.findById(id));
    }

    @GetMapping//list all purchase
    public List<PurchaseDTO> findAll() {
        return PurchaseDTO.fromAll(listPurchaseService.findAll());
    }

    @GetMapping(path = {"/"}) //list all purchase inside object page
    public Page<PurchaseDTO> findPage(@ParameterObject Pageable pageable){
        return PurchaseDTO.fromPage(listPagePurchaseService.findPage(pageable));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping//create purchase
    public void insert(@Valid @RequestBody PurchaseDTO purchaseDTO){
        savePurchaseService.insert(Purchase.to(purchaseDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}") //replace purchase by id
    public void update(@Valid @RequestBody Long id) {
        updatePurchaseService.update(id);
    }
}
