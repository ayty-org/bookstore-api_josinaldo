package br.com.bookstore.bookstore.purchase;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.purchase.services.ListPagePurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static br.com.bookstore.bookstore.book.builders.BookBuilder.createBook;
import static br.com.bookstore.bookstore.client.builders.ClientBuilder.createClient;
import static br.com.bookstore.bookstore.purchase.builders.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for pagination of all purchase")
class ListPagePurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepositoryMock;

    private ListPagePurchaseServiceImpl listPagePurchaseService;

    @BeforeEach
    void setUp() {
        this.listPagePurchaseService = new ListPagePurchaseServiceImpl(purchaseRepositoryMock);
    }

    @Test
    @DisplayName("listAll returns list of purchase inside page object when successful")
    void listAllReturnsListOfPurchaseInsidePageObjectWhenSuccessful() {

        Pageable pageable = PageRequest.of(0,2);

        //Object book
        Set<Book> books = new HashSet<>();
        Book book = createBook().build();
        books.add(book);

        Client client = createClient().build();

        when(listPagePurchaseService
                .findPage(pageable))
                .thenReturn(new PageImpl<>(Collections.nCopies(1, createPurchase().purchasedBooks(books).client(client).build())));

        Page<Purchase> result = listPagePurchaseService.findPage(pageable);

        //verification
        assertAll("Purchase",
                ()-> assertThat(result.getNumber(), is(0)),
                ()-> assertThat(result.getTotalElements(), is(1L)),
                ()-> assertThat(result.getTotalPages(), is(1)),
                ()-> assertThat(result.getSize(), is(1)),
                ()-> assertThat(result.getContent().get(0).getClient(), is(client)),
                ()-> assertThat(result.getContent().get(0).getPurchasedBooks(), is(books)),
                ()-> assertThat(result.getContent().get(0).getAmountToPay(), is(200.00)),
                ()-> assertThat(result.getContent().get(0).getStatus(), is(Status.PENDING))
        );

        verify(purchaseRepositoryMock).findAll(pageable);
    }

}