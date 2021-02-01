package br.com.bookstore.bookstore.CategoryOfBook.service;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBookRepository;

public class SaveCategoryOfBookServiceImpl implements SaveCategoryOfBookService {

    private CategoryOfBookRepository categoryOfBookRepository;

    @Override
    public void insert(CategoryOfBook categoryOfBook) {
        categoryOfBookRepository.save(categoryOfBook);
    }
}
