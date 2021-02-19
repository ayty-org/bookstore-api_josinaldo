package br.com.bookstore.bookstore.purchase;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.purchase.services.ListPurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static br.com.bookstore.bookstore.book.builders.BookBuilder.createBook;
import static br.com.bookstore.bookstore.client.builders.ClientBuilder.createClient;
import static br.com.bookstore.bookstore.purchase.builders.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for list all purcahse")
class ListPurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepositoryMock;

    private ListPurchaseServiceImpl listPurchaseService;

    @BeforeEach
    void setUp() {
        this.listPurchaseService = new ListPurchaseServiceImpl(purchaseRepositoryMock);
    }

    @Test
    @DisplayName("listAll returns list of purchase when successful")
    void listAllReturnsListOfPurchaseWhenSuccessful() {
        Set<Book> books = new HashSet<>();
        Book book = createBook().build();
        books.add(book);

        Client client = createClient().build();

        List<Purchase> purchaseList = Arrays.asList(createPurchase().client(client).purchasedBooks(books).build());

        when(purchaseRepositoryMock.findAll()).thenReturn(purchaseList);

        List<Purchase> result = this.listPurchaseService.findAll();

        assertAll("Purchase",
                () -> assertThat(result.size(), is(1)),
                () -> assertThat(result.get(0).getClient(), is(client)),
                ()-> assertThat(result.get(0).getPurchasedBooks(), is(books)),
                ()-> assertThat(result.get(0).getAmountToPay(), is(200.00)),
                ()-> assertThat(result.get(0).getStatus(), is(Status.PENDING))
        );

        verify(purchaseRepositoryMock, times(1)).findAll();
    }
}