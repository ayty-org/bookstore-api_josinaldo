package br.com.bookstore.bookstore.CategoryOfBook.service;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ListPageCategoryOfBooksServiceImpl implements ListPageCategoryOfBooksService{

    private CategoryOfBookRepository categoryOfBookRepository;

    @Override
    public Page<CategoryOfBook> findPage(Pageable pageable) {
        return null;
    }
}
