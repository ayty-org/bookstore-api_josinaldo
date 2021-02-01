package br.com.bookstore.bookstore.CategoryOfBook.service;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;

@FunctionalInterface
public interface UpdateCategoryOfBookService {

    void update(CategoryOfBook categoryOfBook, Long id);
}
