package br.com.bookstore.bookstore.CategoryOfBook.service;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ListPageCategoryOfBooksServiceImpl implements ListPageCategoryOfBooksService{

    private final CategoryOfBookRepository categoryOfBookRepository;

    @Override
    public Page<CategoryOfBook> findPage(Pageable pageable) {
        return categoryOfBookRepository.findAll(pageable);
    }
}
