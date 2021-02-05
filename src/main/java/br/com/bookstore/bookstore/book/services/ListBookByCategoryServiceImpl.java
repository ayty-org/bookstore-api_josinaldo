package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.book.BookRepository;
import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.exceptions.BookNotFoundException;
import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListBookByCategoryServiceImpl implements ListBookByCategoryService {

    private final BookRepository bookRepository;
    @Override
    public List<Book> findAllBookByCategory(CategoryOfBook category) {
        if(bookRepository.existsByCategory(category.getName())){
            return bookRepository.findAll();
        }

        if(!bookRepository.existsByCategory(category.getName())){
            throw  new CategoryOfBookNotFoundException();
        }

        throw new BookNotFoundException();
    }
}
