package br.com.bookstore.bookstore.categoryofbook.services;

import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;
import br.com.bookstore.bookstore.categoryofbook.CategoryOfBookRepository;
import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCategoryOfBookServiceImpl implements GetCategoryOfBookService{

    private final CategoryOfBookRepository categoryOfBookRepository;

    @Override
    public CategoryOfBook findById(Long id) {
        return categoryOfBookRepository.findById(id).orElseThrow(CategoryOfBookNotFoundException::new);
    }
}
