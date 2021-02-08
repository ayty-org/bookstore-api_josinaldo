package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.book.BookRepository;
import br.com.bookstore.bookstore.exceptions.BookNotFoundException;
import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ListBookByIsbnServiceImpl implements ListBookByIsbnService {

    private final BookRepository bookRepository;
    @Override
    public List<Book> findAllBooksByIsbn(String isbn) {
        Optional<Book> isbnInDB = bookRepository.findByIsbn(isbn);

        if(isbnInDB.isPresent()) {
            return bookRepository.findAll();
        }

        throw new CategoryOfBookNotFoundException();
    }
}
