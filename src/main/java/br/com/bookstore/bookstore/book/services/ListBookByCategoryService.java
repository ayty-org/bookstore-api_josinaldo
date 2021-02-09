package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;

import java.util.List;

@FunctionalInterface
public interface ListBookByCategoryService {
    List<Book> findAllBooksByCategoryName(String name);
}
