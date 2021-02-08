package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.book.BookDTO;

@FunctionalInterface
public interface UpdateBookService {

    void update(BookDTO bookDTO, Long id);
}
