package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;

import java.util.List;

public interface ListBookService {

    List<Book> findAll();
}
