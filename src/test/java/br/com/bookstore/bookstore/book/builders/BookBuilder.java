package br.com.bookstore.bookstore.book.builders;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class BookBuilder {

    public static Book.Builder createBook(){
        CategoryOfBook categoryOfBookTest = new CategoryOfBook(1L,"Aventura");
        Set<CategoryOfBook> categoryOfBookSet = new HashSet<>();
        categoryOfBookSet.add(categoryOfBookTest);
        return Book.builder()
                .id(1L)
                .title("O Pequeno Princípe")
                .sinopse("O Pequeno Príncipe representa a espontaneidade.")
                .isbn("978-3-16-148410-0")
                .autor("Antoine de Saint")
                .yearOfPublication(LocalDate.of(1943, 4, 6))
                .sellPrice(10.00)
                .quantityAvailable(2)
                .categories(categoryOfBookSet);
    }
}
