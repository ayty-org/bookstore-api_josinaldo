package br.com.bookstore.bookstore.CategoryOfBook.services;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;

@FunctionalInterface
public interface UpdateCategoryOfBookService {

    void update(CategoryOfBook categoryOfBook, Long id);
}
