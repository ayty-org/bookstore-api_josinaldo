package br.com.bookstore.bookstore.purchase;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.purchase.services.UpdatePurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static br.com.bookstore.bookstore.purchase.builders.PurchaseBuilder.createPurchase;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for update purchase")
class UpdatePurchaseServiceTest {

    @Mock
    private PurchaseRepository purchaseRepositoryMock;

    private UpdatePurchaseServiceImpl updatePurchaseService;

    @BeforeEach
    void setUp() {
        this.updatePurchaseService = new UpdatePurchaseServiceImpl(purchaseRepositoryMock);
    }

    @Test
    @DisplayName("update purchase when successful")
    void updateReturnsPurchaseUpdateWhenSuccessful(){

        Purchase purchase = createPurchase().status(Status.DONE).build();
        Set<Book> purchasedBooks = purchase.getPurchasedBooks();
        Client client = purchase.getClient();

        Optional<Purchase> purchaseOptional = Optional.of(createPurchase().purchasedBooks(purchasedBooks).client(client).build());
        when(purchaseRepositoryMock.findById(anyLong())).thenReturn(purchaseOptional);

        updatePurchaseService.update(purchase.getId());

        ArgumentCaptor<Purchase> purchaseArgumentCaptor = ArgumentCaptor.forClass(Purchase.class);
        verify(purchaseRepositoryMock).save(purchaseArgumentCaptor.capture());

        Purchase result = purchaseArgumentCaptor.getValue();

        //verification
        assertAll("Purchase",
                ()-> assertThat(result.getClient(), is(client)),
                ()-> assertThat(result.getPurchasedBooks(), is(purchasedBooks)),
                ()-> assertThat(result.getAmountToPay(), is(200.00)),
                ()-> assertThat(result.getStatus(), is(Status.DONE))
        );
    }
}