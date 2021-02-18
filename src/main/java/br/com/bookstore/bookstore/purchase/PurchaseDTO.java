package br.com.bookstore.bookstore.purchase;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.client.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private Client client;

    @NotNull
    private Set<Book> purchasedBooks;

    @NotNull
    private double amountToPay;

    private Enum<Status> status;

    public static PurchaseDTO from(Purchase entity) {
        return PurchaseDTO
                .builder()
                .id(entity.getId())
                .client(entity.getClient())
                .purchasedBooks(entity.getPurchasedBooks())
                .amountToPay(entity.getAmountToPay())
                .status(entity.getStatus())
                .build();
    }

    public static List<PurchaseDTO> fromAll(List<Purchase> purchases) {
        return purchases.stream().map(PurchaseDTO::from).collect(Collectors.toList());
    }

    public static Page<PurchaseDTO> fromPage(Page<Purchase> pages){
        return pages.map(PurchaseDTO::from);
    }
}
