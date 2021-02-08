package br.com.bookstore.bookstore.categoryofbook.services;

import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;

import java.util.List;

@FunctionalInterface
public interface ListCategoryOfBookService {

    List<CategoryOfBook> findAll();
}
