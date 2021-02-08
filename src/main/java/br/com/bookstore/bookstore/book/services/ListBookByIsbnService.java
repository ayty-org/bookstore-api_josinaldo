package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;

import java.util.List;

@FunctionalInterface
public interface ListBookByIsbnService {
    List<Book> findAllBooksByIsbn(String isbn);
}
