package br.com.bookstore.bookstore.purchase.builders;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.client.Client;
import br.com.bookstore.bookstore.purchase.Purchase;
import br.com.bookstore.bookstore.purchase.Status;

import java.util.HashSet;
import java.util.Set;

import static br.com.bookstore.bookstore.book.builders.BookBuilder.createBook;

public class PurchaseBuilder {

    public static Purchase.Builder createPurchase(){
        Set<Book> books = new HashSet<>();
        books.add(createBook().title("Star Wars: Dark Edition").build());
        return Purchase.builder()
                .id(1L)
                .client(Client.builder().build())
                .purchasedBooks(books)
                .amountToPay(200.00)
                .status(Status.PENDING);
    }
}
