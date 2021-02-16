package br.com.bookstore.bookstore.book;

import br.com.bookstore.bookstore.book.services.UpdateBookService;
import br.com.bookstore.bookstore.book.services.UpdateBookServiceImpl;
import br.com.bookstore.bookstore.exceptions.BookNotFoundException;
import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static br.com.bookstore.bookstore.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for update book")
class UpdateBookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock;

    private UpdateBookService updateClientService;

    @BeforeEach
    void setUp() {
        this.updateClientService = new UpdateBookServiceImpl(bookRepositoryMock);
    }

    @Test
    @DisplayName("update book when successful")
    void updateReturnsBookUpdateWhenSuccessful(){

        Book putBookRequest = createBook()
                .title("New Title")
                .quantityAvailable(10)
                .build();

        Optional<Book> bookOptional = Optional.of(createBook().build());
        when(bookRepositoryMock.findById(anyLong())).thenReturn(bookOptional);

        updateClientService.update(BookDTO.from(putBookRequest), 1L);

        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        verify(bookRepositoryMock).save(bookArgumentCaptor.capture());

        Book result = bookArgumentCaptor.getValue();

        //verification
        assertAll("Book",
                ()-> assertThat(result.getTitle(), is("New Title")),
                ()-> assertThat(result.getSinopse(), is("O Pequeno Príncipe representa a espontaneidade.")),
                ()-> assertThat(result.getIsbn(), is("978-3-16-148410-0")),
                ()-> assertThat(result.getAutor(), is("Antoine de Saint")),
                ()-> assertThat(result.getYearOfPublication(), is(LocalDate.of(1943, 4, 6))),
                ()-> assertThat(result.getSellPrice(), is(10.00)),
                ()-> assertThat(result.getQuantityAvailable(), is(10))
        );
    }

    @Test
    @DisplayName("update throws BookNotFoundException when book is not found")
    void updateThrowBookNotFoundExceptionWhenBookNotFound() {
        when(bookRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, ()-> this.updateClientService.update(BookDTO.builder().build(), 1L));

        verify(bookRepositoryMock, times(0)).save(any());
    }
}