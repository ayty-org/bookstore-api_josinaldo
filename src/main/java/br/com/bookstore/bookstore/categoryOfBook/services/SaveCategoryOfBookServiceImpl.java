package br.com.bookstore.bookstore.categoryOfBook.services;

import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBookRepository;
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
