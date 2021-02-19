package br.com.bookstore.bookstore.purchase;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.purchase.services.ListPurchaseByStatusServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.bookstore.bookstore.book.builders.BookBuilder.createBook;
import static br.com.bookstore.bookstore.client.builders.ClientBuilder.createClient;
import static br.com.bookstore.bookstore.purchase.builders.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for list all purchase by status")
class ListPurchaseByStatusServiceTest {

    @Mock
    private PurchaseRepository purchaseRepositoryMock;

    private ListPurchaseByStatusServiceImpl listPurchaseByStatusService;

    @BeforeEach
    void setUp() {
        this.listPurchaseByStatusService = new ListPurchaseByStatusServiceImpl(purchaseRepositoryMock);
    }

    @Test
    @DisplayName("listAll returns list of purchase by status when successful")
    void listAllReturnsListOfPurchaseWhenSuccessful() {

        Set<Book> books = new HashSet<>();
        Book book = createBook().build();
        books.add(book);

        Client client = createClient().build();

        when(purchaseRepositoryMock.findPurchaseByStatus(any())).thenReturn(
                Stream.of(createPurchase().purchasedBooks(books).client(client).build()).collect(Collectors.toList())
        );

        List<Purchase> result = this.listPurchaseByStatusService.findAllPurchaseByStatus(Status.PENDING);

        assertAll("Book",
                () -> assertThat(result.size(), is(1)),
                () -> assertThat(result.get(0).getClient(), is(client)),
                ()-> assertThat(result.get(0).getPurchasedBooks(), is(books)),
                ()-> assertThat(result.get(0).getAmountToPay(), is(200.00))
        );

        verify(purchaseRepositoryMock, times(1)).findPurchaseByStatus(any());
    }
}