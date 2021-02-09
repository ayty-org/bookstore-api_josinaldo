package br.com.bookstore.bookstore.categoryofbook.services;

import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;
import br.com.bookstore.bookstore.categoryofbook.CategoryOfBookRepository;
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
