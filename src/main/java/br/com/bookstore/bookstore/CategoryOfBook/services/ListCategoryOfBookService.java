package br.com.bookstore.bookstore.CategoryOfBook.services;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;

import java.util.List;

@FunctionalInterface
public interface ListCategoryOfBookService {

    List<CategoryOfBook> findAll();
}