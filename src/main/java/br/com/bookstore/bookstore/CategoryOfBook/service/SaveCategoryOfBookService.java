package br.com.bookstore.bookstore.CategoryOfBook.service;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;

@FunctionalInterface
public interface SaveCategoryOfBookService {

    void insert(CategoryOfBook categoryOfBook);
}
