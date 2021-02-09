package br.com.bookstore.bookstore.categoryofbook.services;

import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@FunctionalInterface
public interface ListPageCategoryOfBooksService {
    Page<CategoryOfBook> findPage(Pageable pageable);
}
