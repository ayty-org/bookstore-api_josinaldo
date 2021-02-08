package br.com.bookstore.bookstore.book.services;

@FunctionalInterface
public interface DeleteBookService {
    void delete(Long id);
}
