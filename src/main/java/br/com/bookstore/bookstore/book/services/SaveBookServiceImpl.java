package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.book.BookRepository;
import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;
import br.com.bookstore.bookstore.exceptions.BookAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class SaveBookServiceImpl implements SaveBookService {

    private final BookRepository bookRepository;

    @Override
    public void insert(Book book) {
        if(bookRepository.existsByIsbn(book.getIsbn())) {
            throw new BookAlreadyExistException();
        }

        if(!book.getCategories().isEmpty()){
            Set<CategoryOfBook> categoryOfBookSet = new HashSet<>();
            for(CategoryOfBook categoryOfBook: book.getCategories()){
                categoryOfBook.getId();
                categoryOfBookSet.add(categoryOfBook);
            }

            book.setCategories(categoryOfBookSet);
        }

        bookRepository.save(book);
    }
}
