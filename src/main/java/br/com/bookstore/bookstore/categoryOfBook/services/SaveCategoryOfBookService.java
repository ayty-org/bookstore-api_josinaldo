package br.com.bookstore.bookstore.categoryOfBook.services;

import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;

@FunctionalInterface
public interface SaveCategoryOfBookService {

    void insert(CategoryOfBook categoryOfBook);
}
