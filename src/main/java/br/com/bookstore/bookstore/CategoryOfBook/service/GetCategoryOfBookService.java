package br.com.bookstore.bookstore.CategoryOfBook.service;


import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;

@FunctionalInterface
public interface GetCategoryOfBookService {
    CategoryOfBook findById(Long id);
}
