package br.com.bookstore.bookstore.CategoryOfBook;

import br.com.bookstore.bookstore.CategoryOfBook.services.UpdateCategoryOfBookServiceImpl;
import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.bookstore.bookstore.CategoryOfBook.builders.CategoryOfBookBuilder.createCategoryOfBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for update category of book")
class UpdateCategoryOfBookServiceTest {

    @Mock
    private CategoryOfBookRepository categoryOfBookRepositoryMock;

    private UpdateCategoryOfBookServiceImpl updateCategoryOfBookService;

    @BeforeEach
    void setUp() {
        this.updateCategoryOfBookService = new UpdateCategoryOfBookServiceImpl(categoryOfBookRepositoryMock);
    }

    @Test
    @DisplayName("update category of book when successful")
    void updateReturnsCategoryOfBookUpdateWhenSuccessful(){
        CategoryOfBook categoryOfBookUpdated = createCategoryOfBook().name("Terror").build();

        CategoryOfBook categoryOfBook = createCategoryOfBook().build();
        Optional<CategoryOfBook> categoryOfBookOptional  = Optional.of(categoryOfBook);

        when(categoryOfBookRepositoryMock.findById(anyLong())).thenReturn(categoryOfBookOptional);

        updateCategoryOfBookService.update(categoryOfBookUpdated, 1L);

        ArgumentCaptor<CategoryOfBook> categoryOfBookArgumentCaptor = ArgumentCaptor.forClass(CategoryOfBook.class);
        verify(categoryOfBookRepositoryMock).save(categoryOfBookArgumentCaptor.capture());

        CategoryOfBook result = categoryOfBookArgumentCaptor.getValue();

        assertAll("Client",
                () -> assertThat(result.getName(), is(categoryOfBookUpdated.getName()))
        );
    }

    @Test
    @DisplayName("update throws CategoryOfBookNotFoundException when category of book is not found")
    void updateThrowCategoryOfBookNotFoundExceptionWhenCategoryOfBooktNotFound() {
        when(categoryOfBookRepositoryMock.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(CategoryOfBookNotFoundException.class,
                ()-> this.updateCategoryOfBookService.update(CategoryOfBook.builder().build(), 1L));
    }
}