package br.com.bookstore.bookstore.purchase;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.purchase.services.GetPurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static br.com.bookstore.bookstore.purchase.builders.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for searching for purchase by id ")
class GetPurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepositoryMock;

    private GetPurchaseServiceImpl getPurchaseService;

    @BeforeEach
    void setUp() {
        this.getPurchaseService = new GetPurchaseServiceImpl(purchaseRepositoryMock);
    }

    @Test
    @DisplayName("findById returns purchase when successful")
    void findByIdReturnBookWhenSuccessful(){
        Purchase purchase = createPurchase().build();//create build to book;
        Optional<Purchase> purchaseOptional = Optional.of(purchase);
        when(purchaseRepositoryMock.findById(1L)).thenReturn(purchaseOptional);

        Purchase result = this.getPurchaseService.findById(1L);

        Client client = getPurchaseService.findById(1L).getClient();
        Set<Book> book = getPurchaseService.findById(1L).getPurchasedBooks();
        //verification
        assertAll("Purchase",
                ()-> assertThat(result.getClient(), is(client)),
                ()-> assertThat(result.getPurchasedBooks(), is(book)),
                ()-> assertThat(result.getAmountToPay(), is(200.00)),
                ()-> assertThat(result.getStatus(), is(Status.PENDING))
        );

        verify(purchaseRepositoryMock, times(3)).findById(1L);
    }
}