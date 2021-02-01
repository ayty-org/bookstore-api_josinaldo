package br.com.bookstore.bookstore.CategoryOfBook.service;


import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBookRepository;
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
