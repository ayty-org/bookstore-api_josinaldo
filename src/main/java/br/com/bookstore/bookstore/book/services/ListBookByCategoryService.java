package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;

import java.util.List;

@FunctionalInterface
public interface ListBookByCategoryService {
    List<Book> findAllBookByCategory(CategoryOfBook category);
}
