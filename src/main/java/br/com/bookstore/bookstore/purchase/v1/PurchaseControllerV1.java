package br.com.bookstore.bookstore.purchase.v1;

import br.com.bookstore.bookstore.purchase.Purchase;
import br.com.bookstore.bookstore.purchase.PurchaseDTO;
import br.com.bookstore.bookstore.purchase.Status;
import br.com.bookstore.bookstore.purchase.services.DeletePurchaseService;
import br.com.bookstore.bookstore.purchase.services.GetPurchaseService;
import br.com.bookstore.bookstore.purchase.services.ListPagePurchaseService;
import br.com.bookstore.bookstore.purchase.services.ListPurchaseByStatusService;
import br.com.bookstore.bookstore.purchase.services.ListPurchaseService;
import br.com.bookstore.bookstore.purchase.services.SavePurchaseService;
import br.com.bookstore.bookstore.purchase.services.UpdatePurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(value = "v1/api/purchase")
public class PurchaseControllerV1 {

    private final GetPurchaseService getPurchaseService;
    private final ListPurchaseService listPurchaseService;
    private final ListPagePurchaseService listPagePurchaseService;
    private final ListPurchaseByStatusService listPurchaseByStatusService;
    private final SavePurchaseService savePurchaseService;
    private final UpdatePurchaseService updatePurchaseService;
    private final DeletePurchaseService deletePurchaseService;

    @GetMapping(value = "/{id}")//list purchase by id
    public PurchaseDTO find(@PathVariable Long id) {
        return PurchaseDTO.from(getPurchaseService.findById(id));
    }

    @GetMapping//list all purchase
    public List<PurchaseDTO> findAll() {
        return PurchaseDTO.fromAll(listPurchaseService.findAll());
    }

    @GetMapping(path = "/status/{status}") //list purchase by status
    public List<PurchaseDTO> findAllPurchaseByStatus(@PathVariable Status status){
        return PurchaseDTO.fromAll(listPurchaseByStatusService.findAllPurchaseByStatus(status));
    }

    @GetMapping(path = {"/"})//list all purchase inside object page
    public Page<PurchaseDTO> findPage(Pageable pageable){
        return PurchaseDTO.fromPage(listPagePurchaseService.findPage(pageable));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping//create purchase
    public void insert(@Valid @RequestBody PurchaseDTO purchaseDTO){
        savePurchaseService.insert(Purchase.to(purchaseDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping( "/done/{id}") //replace purchase by id
    public void update(@PathVariable Long id) {
        updatePurchaseService.update(id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}") //delete purchase
    public void delete(@Valid @PathVariable Long id) {
        deletePurchaseService.delete(id);
    }
}
