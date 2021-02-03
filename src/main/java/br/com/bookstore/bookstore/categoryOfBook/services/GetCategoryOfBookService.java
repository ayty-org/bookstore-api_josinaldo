package br.com.bookstore.bookstore.categoryOfBook.services;


import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;

@FunctionalInterface
public interface GetCategoryOfBookService {
    CategoryOfBook findById(Long id);
}
