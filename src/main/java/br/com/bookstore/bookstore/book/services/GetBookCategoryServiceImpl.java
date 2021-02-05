package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.book.BookRepository;
import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetBookCategoryServiceImpl implements GetBookCategoryService{

    private final BookRepository bookRepository;
    @Override
    public Book findByCategory(CategoryOfBook category) {
        return bookRepository.findByCategory(category.getName()).orElseThrow(CategoryOfBookNotFoundException::new);
    }
}
