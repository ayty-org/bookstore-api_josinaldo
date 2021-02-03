package br.com.bookstore.bookstore.categoryOfBook.services;

import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;

import java.util.List;

@FunctionalInterface
public interface ListCategoryOfBookService {

    List<CategoryOfBook> findAll();
}
