package br.com.bookstore.bookstore.categoryofbook.services;


import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;

@FunctionalInterface
public interface GetCategoryOfBookService {
    CategoryOfBook findById(Long id);
}
