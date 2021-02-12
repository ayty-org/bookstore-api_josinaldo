package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.book.BookRepository;
import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;
import br.com.bookstore.bookstore.categoryofbook.CategoryOfBookRepository;
import br.com.bookstore.bookstore.exceptions.BookAlreadyExistException;
import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class SaveBookServiceImpl implements SaveBookService {

    private final BookRepository bookRepository;

    private final CategoryOfBookRepository categoryOfBookRepository;

    @Override
    public void insert(Book book) {
        if(bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistException();
        }

        Set<CategoryOfBook> categoryOfBookSaved = Collections.singleton(categoryOfBookRepository.findById(new CategoryOfBook().getId()).orElseThrow(CategoryOfBookNotFoundException::new));

        book.setCategories(categoryOfBookSaved);

        bookRepository.save(book);
    }
}
