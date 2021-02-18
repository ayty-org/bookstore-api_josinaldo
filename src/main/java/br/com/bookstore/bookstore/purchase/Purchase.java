package br.com.bookstore.bookstore.purchase;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.client.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderClassName = "Builder")
@Table(name = "tb_purchase")
public class Purchase implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "purchase_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @PrimaryKeyJoinColumn
    private Client client;

    @ManyToMany(cascade = CascadeType.DETACH)
    @PrimaryKeyJoinColumn(name = "purchased_books")
    private Set<Book> purchasedBooks;

    @Column(name = "amount_to_pay")
    private double amountToPay;

    @Column(name = "purchase_status")
    private Enum<Status> status;

    public static Purchase to(PurchaseDTO dto) {
        return Purchase
                .builder()
                .id(dto.getId())
                .client(dto.getClient())
                .purchasedBooks(dto.getPurchasedBooks())
                .amountToPay(dto.getAmountToPay())
                .status(dto.getPurchaseStatus())
                .build();
    }
}
