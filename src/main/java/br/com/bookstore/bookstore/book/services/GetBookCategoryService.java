package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;

@FunctionalInterface
public interface GetBookCategoryService {
    Book findByCategory(CategoryOfBook category);
}
