package br.com.bookstore.bookstore.purchase;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.purchase.services.SavePurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Set;

import static br.com.bookstore.bookstore.purchase.builders.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for save purchase")
class SavePurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepositoryMock;

    private SavePurchaseServiceImpl savePurchaseService;

    @BeforeEach
    void setUp() {
        this.savePurchaseService = new SavePurchaseServiceImpl(purchaseRepositoryMock);
    }

    @Test
    @DisplayName("save returns purchase when successful")
    void saveReturnsPurchaseWhenSuccessful() {

        Purchase purchase = createPurchase().build();
        Set<Book> purchasedBooks = purchase.getPurchasedBooks();
        Client client = purchase.getClient();

        savePurchaseService.insert(purchase);
        ArgumentCaptor<Purchase> purchaseArgumentCaptor = ArgumentCaptor.forClass(Purchase.class);
        verify(purchaseRepositoryMock, times(1)).save(purchaseArgumentCaptor.capture());

        Purchase result = purchaseArgumentCaptor.getValue();

        //verification
        assertAll("Purchase",
                ()-> assertThat(result.getClient(), is(client)),
                ()-> assertThat(result.getPurchasedBooks(), is(purchasedBooks)),
                ()-> assertThat(result.getAmountToPay(), is(200.00)),
                ()-> assertThat(result.getStatus(), is(Status.PENDING))
        );
    }
}