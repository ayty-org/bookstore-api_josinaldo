package br.com.bookstore.bookstore.categoryOfBook.services;


import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBookRepository;
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
