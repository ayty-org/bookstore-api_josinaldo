package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;

@FunctionalInterface
public interface SaveBookService {
    void insert(Book book);
}
