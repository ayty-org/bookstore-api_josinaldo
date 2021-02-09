package br.com.bookstore.bookstore.categoryofbook.services;

import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;

@FunctionalInterface
public interface UpdateCategoryOfBookService {

    void update(CategoryOfBook categoryOfBook, Long id);
}
