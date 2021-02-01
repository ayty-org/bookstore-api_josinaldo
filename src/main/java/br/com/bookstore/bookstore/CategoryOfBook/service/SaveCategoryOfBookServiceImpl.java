package br.com.bookstore.bookstore.CategoryOfBook.service;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SaveCategoryOfBookServiceImpl implements SaveCategoryOfBookService {

    private final CategoryOfBookRepository categoryOfBookRepository;

    @Override
    public void insert(CategoryOfBook categoryOfBook) {
        categoryOfBookRepository.save(categoryOfBook);
    }
}
