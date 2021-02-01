package br.com.bookstore.bookstore.CategoryOfBook.service;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBookRepository;
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
