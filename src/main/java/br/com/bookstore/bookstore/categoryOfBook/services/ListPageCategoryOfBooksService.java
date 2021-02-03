package br.com.bookstore.bookstore.categoryOfBook.services;

import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPageCategoryOfBooksService {
    Page<CategoryOfBook> findPage(Pageable pageable);
}
