package br.com.bookstore.bookstore.categoryOfBook.services;

import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListCategoryOfBookServiceImpl implements ListCategoryOfBookService{

    private final CategoryOfBookRepository categoryOfBookRepository;

    @Override
    public List<CategoryOfBook> findAll() {
        return categoryOfBookRepository.findAll();
    }
}
