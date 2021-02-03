package br.com.bookstore.bookstore.categoryOfBook;

import br.com.bookstore.bookstore.categoryOfBook.services.DeleteCategoryOfBookServiceImpl;
import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for delete category of book")
class DeleteCategoryOfBookServiceTest {

    @Mock
    private CategoryOfBookRepository categoryOfBookRepositoryMock;

    private DeleteCategoryOfBookServiceImpl deleteCategoryOfBookService;

    @BeforeEach
    void setUp() {
        this.deleteCategoryOfBookService = new DeleteCategoryOfBookServiceImpl(categoryOfBookRepositoryMock);
    }

    @Test
    @DisplayName("delete remove category of books when successful")
    void deleteRemoveCategoryOfBookWhenSuccessful() {
        when(categoryOfBookRepositoryMock.existsById(anyLong())).thenReturn(true);
        deleteCategoryOfBookService.delete(1L);
        verify(categoryOfBookRepositoryMock).existsById(anyLong());
    }

    @Test
    @DisplayName("delete throws CategoryOfBookNotFoundException when category of books is not found")
    void deleteThrowCategoryOfBookNotFoundExceptionWhenCategoryOfBookNotFound() {
        when(categoryOfBookRepositoryMock.existsById(anyLong())).thenReturn(false);

        Assertions.assertThrows(CategoryOfBookNotFoundException.class, ()-> deleteCategoryOfBookService.delete(1L));

        verify(categoryOfBookRepositoryMock, times(0)).deleteById(anyLong());
    }
}