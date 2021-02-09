package br.com.bookstore.bookstore.book.services;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.book.BookRepository;
import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;
import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListBookByCategoryServiceImpl implements ListBookByCategoryService {

    private final BookRepository bookRepository;

    @Override
    public List<Book> findAllBooksByIsbn(String category) {
        List<CategoryOfBook> categorys = Book.builder().build().getCategorys();

        if(category.equals(categorys.getClass().getName())) {
            return bookRepository.findAll();
        }

        throw new CategoryOfBookNotFoundException();
    }
}
