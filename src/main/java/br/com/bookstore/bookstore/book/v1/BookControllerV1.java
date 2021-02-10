package br.com.bookstore.bookstore.book.v1;

import br.com.bookstore.bookstore.book.Book;
import br.com.bookstore.bookstore.book.BookDTO;
import br.com.bookstore.bookstore.book.services.DeleteBookService;
import br.com.bookstore.bookstore.book.services.GetBookService;
import br.com.bookstore.bookstore.book.services.ListBookByCategoryService;
import br.com.bookstore.bookstore.book.services.ListBookService;
import br.com.bookstore.bookstore.book.services.ListPageBookService;
import br.com.bookstore.bookstore.book.services.SaveBookService;
import br.com.bookstore.bookstore.book.services.UpdateBookService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping(value = "/{id}")
    public BookDTO find(@PathVariable Long id) {
        return BookDTO.from(getBookService.findById(id));
    }

    @GetMapping
    public List<BookDTO> findAll(){
        return BookDTO.fromAll(listBookService.findAll());
    }

    @GetMapping(path = "/{categoryname}")
    public List<BookDTO> findAllBooksByCategoryName(@PathVariable String categoryname){
        return BookDTO.fromAll(listBookByCategoryService.findAllBooksByCategoryName(categoryname));
    }

    @GetMapping(path = {"/"})
    public Page<BookDTO> findPage(@ParameterObject Pageable pageable) {
        return BookDTO.fromPage(listPageBookService.findPage(pageable));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public void insert(@Valid @RequestBody BookDTO bookDTO){
        saveBookService.insert(Book.to(bookDTO));
    }
}
