package br.com.bookstore.bookstore.categoryOfBook.services;

import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBookRepository;
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
