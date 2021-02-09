package br.com.bookstore.bookstore.book.v1;

import br.com.bookstore.bookstore.book.BookDTO;
import br.com.bookstore.bookstore.book.services.DeleteBookService;
import br.com.bookstore.bookstore.book.services.GetBookService;
import br.com.bookstore.bookstore.book.services.ListBookByCategoryService;
import br.com.bookstore.bookstore.book.services.ListBookService;
import br.com.bookstore.bookstore.book.services.ListPageBookService;
import br.com.bookstore.bookstore.book.services.SaveBookService;
import br.com.bookstore.bookstore.book.services.UpdateBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/book")
public class BookControllerV1 {

    private final GetBookService getBookService;
    private final ListBookService listBookService;
    private final ListBookByCategoryService listBookByCategoryService;
    private final ListPageBookService listPageBookService;
    private final SaveBookService saveBookService;
    private final DeleteBookService deleteBookService;
    private final UpdateBookService updateBookService;

    public BookDTO find(@PathVariable Long id) {
        return BookDTO.from(getBookService.findById(id));
    }
}
