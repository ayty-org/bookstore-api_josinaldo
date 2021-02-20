package br.com.bookstore.bookstore.purchase;

import br.com.bookstore.bookstore.book.BookDTO;
import br.com.bookstore.bookstore.exceptions.PurchaseNotFoundException;
import br.com.bookstore.bookstore.purchase.services.DeletePurchaseService;
import br.com.bookstore.bookstore.purchase.services.GetPurchaseService;
import br.com.bookstore.bookstore.purchase.services.ListPagePurchaseService;
import br.com.bookstore.bookstore.purchase.services.ListPurchaseByStatusService;
import br.com.bookstore.bookstore.purchase.services.ListPurchaseService;
import br.com.bookstore.bookstore.purchase.services.SavePurchaseService;
import br.com.bookstore.bookstore.purchase.services.UpdatePurchaseService;
import br.com.bookstore.bookstore.purchase.v1.PurchaseControllerV1;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static br.com.bookstore.bookstore.purchase.builders.PurchaseBuilder.createPurchase;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(PurchaseControllerV1.class)
@DisplayName("Validates the functionality of the controller responsible of purchase")
class PurchaseControllerV1Test {

    private final String URL_PURCHASE = "/v1/api/purchase";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeletePurchaseService deletePurchaseService;

    @MockBean
    private GetPurchaseService getPurchaseService;

    @MockBean
    private ListPurchaseService listPurchaseService;

    @MockBean
    private ListPurchaseByStatusService listPurchaseByStatusService;

    @MockBean
    private ListPagePurchaseService listPagePurchaseService;

    @MockBean
    private SavePurchaseService savePurchaseService;

    @MockBean
    private UpdatePurchaseService updatePurchaseService;

    @Test
    @DisplayName("findById return purchase when succesful")
    void findByIdReturnPurchaseWhenSuccessful() throws Exception{

        when(getPurchaseService.findById(anyLong())).thenReturn(createPurchase().build());

        mockMvc.perform(get(URL_PURCHASE + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.client.id", is(1)))
                .andExpect(jsonPath("$.purchasedBooks.[0].id", is(1)))
                .andExpect(jsonPath("$.amountToPay", is(200.00)))
                .andExpect(jsonPath("$.status", is("PENDING")));

        verify(getPurchaseService).findById(anyLong());
    }

    @Test
    @DisplayName("findById throws PurchaseNotFoundException when purchase is not found")
    void findByIdPurchaseThrowPurchaseNotFoundExceptionWhenPurchaseNotFound() throws Exception {

        when(getPurchaseService.findById(anyLong())).thenThrow(new PurchaseNotFoundException());

        mockMvc.perform(get(URL_PURCHASE + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getPurchaseService).findById(1L);
    }

    @Test
    @DisplayName("listAll returns list of purchase when successful")
    void listAllReturnsListOfPurchaseWhenSuccessfull() throws Exception {

        when(listPurchaseService.findAll()).thenReturn(Lists.newArrayList(
                createPurchase().id(1L).build(),
                createPurchase().id(2L).build()
        ));

        mockMvc.perform(get(URL_PURCHASE).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].client.id", is(1)))
                .andExpect(jsonPath("$[0].purchasedBooks.[0].id", is(1)))
                .andExpect(jsonPath("$[0].amountToPay", is(200.00)))
                .andExpect(jsonPath("$[0].status", is("PENDING")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].client.id", is(1)))
                .andExpect(jsonPath("$[1].purchasedBooks.[0].id", is(1)))
                .andExpect(jsonPath("$[1].amountToPay", is(200.00)))
                .andExpect(jsonPath("$[1].status", is("PENDING")));

        verify(listPurchaseService).findAll();
    }

    @Test
    @DisplayName("listAll returns list of purchase inside page object when successful")
    void listAllReturnsListOfPurchaseInsidePageObject_WhenSuccessful() throws Exception{

        Page<Purchase> purchasePage = new PageImpl<>(Collections.singletonList(createPurchase().build()));

        Pageable pageable = PageRequest.of(0,2);

        when(listPagePurchaseService.findPage(pageable)).thenReturn(purchasePage);

        mockMvc.perform(get(URL_PURCHASE + "/?page=0&size=2").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].client.id", is(1)))
                .andExpect(jsonPath("$.content[0].purchasedBooks.[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].amountToPay", is(200.00)))
                .andExpect(jsonPath("$.content[0].status", is("PENDING")));

        verify(listPagePurchaseService).findPage(pageable);
    }

    @Test
    @DisplayName("listAll returns list of purchase by status when successful")
    void listAllByStatusReturnsListOfPurchaseWhenSuccessful() throws Exception {

        Purchase purchase = createPurchase().build();

        Status statusPurchase = Status.PENDING;

        when(listPurchaseByStatusService.findAllPurchaseByStatus(statusPurchase)).thenReturn(Collections.singletonList(purchase));

        mockMvc.perform(get(URL_PURCHASE + "/status/{status}", statusPurchase).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].client.id", is(1)))
                .andExpect(jsonPath("$[0].purchasedBooks.[0].id", is(1)))
                .andExpect(jsonPath("$[0].amountToPay", is(200.00)))
                .andExpect(jsonPath("$[0].status", is("PENDING")));

        verify(listPurchaseByStatusService).findAllPurchaseByStatus(statusPurchase);
    }

    @Test
    @DisplayName("save returns purchase when successful")
    void saveReturnsPurchaseWhenSuccessful() throws Exception{
        mockMvc.perform(post(URL_PURCHASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("purchaseDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(savePurchaseService).insert(any(Purchase.class));
    }

    @Test
    @DisplayName("update purchase when successful")
    void updateReturnsPurchaseUpdateWhenSuccessful() throws Exception{
        mockMvc.perform(put(URL_PURCHASE + "/done/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(updatePurchaseService).update(eq(1L));
    }

    @Test
    @DisplayName("delete remove purchase when successful")
    void deleteRemovePurchaseWhenSuccessful() throws Exception{
        mockMvc.perform(delete(URL_PURCHASE + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(deletePurchaseService).delete(anyLong());
    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/resources/dataJson/" + file).toAbsolutePath());
        return new String(bytes);
    }
}