package br.com.bookstore.bookstore.CategoryOfBook.services;


import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;

@FunctionalInterface
public interface GetCategoryOfBookService {
    CategoryOfBook findById(Long id);
}
