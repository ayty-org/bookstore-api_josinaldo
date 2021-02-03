package br.com.bookstore.bookstore.categoryOfBook.services;

import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;

@FunctionalInterface
public interface UpdateCategoryOfBookService {

    void update(CategoryOfBook categoryOfBook, Long id);
}
