package br.com.bookstore.bookstore.categoryOfBook.services;

import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBookRepository;
import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UpdateCategoryOfBookServiceImpl implements UpdateCategoryOfBookService{

    private final CategoryOfBookRepository categoryOfBookRepository;

    @Override
    public void update(CategoryOfBook categoryOfBook, Long id) {
        CategoryOfBook categoryOfBookSaved = categoryOfBookRepository.findById(id).orElseThrow(CategoryOfBookNotFoundException::new);

        categoryOfBookSaved.setName(categoryOfBook.getName());

        categoryOfBookRepository.save(categoryOfBookSaved);
    }
}
