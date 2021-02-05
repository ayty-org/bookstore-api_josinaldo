package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;

@FunctionalInterface
public interface GetBookService {
    Book findById(Long id);
}
