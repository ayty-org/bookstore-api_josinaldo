package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPageBookService {
    Page<Book> findPage(Pageable pageable);
}
