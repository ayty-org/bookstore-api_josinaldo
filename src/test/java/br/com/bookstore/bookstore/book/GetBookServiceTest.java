package br.com.bookstore.bookstore.book;

import br.com.bookstore.bookstore.book.BookRepository;
import br.com.bookstore.bookstore.book.services.GetBookServiceImpl;
import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;
import br.com.bookstore.bookstore.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static br.com.bookstore.bookstore.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for searching for book by id ")
class GetBookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock;

    private GetBookServiceImpl getBookService;

    @BeforeEach
    void setUp() {
        this.getBookService = new GetBookServiceImpl(bookRepositoryMock);
    }

    @Test
    @DisplayName("findById returns book when succesful")
    void findByIdReturnBookWhenSuccessful(){

        Book book = createBook().build(); //create a build to book
        Optional<Book> bookSavedOptional = Optional.of(book);
        when(bookRepositoryMock.findById(anyLong())).thenReturn(bookSavedOptional);

        Book result = this.getBookService.findById(1L); //result of requisition

        Set<CategoryOfBook> categoryOfBook = getBookService.findById(1L).getCategories();

        //verification
        assertAll("Book",
                ()-> assertThat(result.getTitle(), is("O Pequeno Princípe")),
                ()-> assertThat(result.getSinopse(), is("O Pequeno Príncipe representa a espontaneidade.")),
                ()-> assertThat(result.getIsbn(), is("978-3-16-148410-0")),
                ()-> assertThat(result.getAutor(), is("Antoine de Saint")),
                ()-> assertThat(result.getYearOfPublication(), is(LocalDate.of(1943, 4, 6))),
                ()-> assertThat(result.getSellPrice(), is(10.00)),
                ()-> assertThat(result.getQuantityAvailable(), is(2)),
                ()-> assertThat(result.getCategories(), is(categoryOfBook))
        );

        verify(bookRepositoryMock, times(2)).findById(1L);
    }

    @Test
    @DisplayName("findById throws BookNotFoundException when book is not found")
    void findByIdBookThrowBookNotFoundExceptionWhenBookNotFound() {

        when(bookRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, ()-> getBookService.findById(1L));
        verify(bookRepositoryMock, times(1)).findById(anyLong());
    }
}