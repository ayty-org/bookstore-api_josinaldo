package br.com.bookstore.bookstore.book;

import br.com.bookstore.bookstore.book.services.DeleteBookServiceImpl;
import br.com.bookstore.bookstore.exceptions.BookNotFoundException;
import javafx.beans.binding.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for delete book")
class DeleteBookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock;

    private DeleteBookServiceImpl deleteBookService;

    @BeforeEach
    void setUp() {
        this.deleteBookService = new DeleteBookServiceImpl(bookRepositoryMock);
    }

    @Test
    @DisplayName("delete remove book when successful")
    void deleteRemoveBookWhenSuccessful() {

        when(bookRepositoryMock.existsById(anyLong())).thenReturn(true);
        deleteBookService.delete(1L);
        verify(bookRepositoryMock).existsById(anyLong());
    }

    @Test
    @DisplayName("delete throws BookFoundException when books is not found")
    void deleteThrowBookWhenBookNotFound() {
        when(bookRepositoryMock.existsById(anyLong())).thenReturn(false);
        Assertions.assertThrows(BookNotFoundException.class, ()-> deleteBookService.delete(1L));
        verify(bookRepositoryMock, times(0)).deleteById(anyLong());
    }
}