package br.com.bookstore.bookstore.categoryOfBook;

import br.com.bookstore.bookstore.categoryOfBook.services.SaveCategoryOfBookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.bookstore.bookstore.categoryOfBook.builders.CategoryOfBookBuilder.createCategoryOfBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for save category of book")
class SaveCategoryOfBookServiceTest {

    @Mock
    private CategoryOfBookRepository categoryOfBookRepositoryMock;

    private SaveCategoryOfBookServiceImpl saveCategoryOfBookService;

    @BeforeEach
    void setUp() {
        this.saveCategoryOfBookService = new SaveCategoryOfBookServiceImpl(categoryOfBookRepositoryMock);
    }

    @Test
    @DisplayName("save created category of books when successful")
    void saveReturnsClientWhenSuccessful() {

        CategoryOfBook categoryOfBook = createCategoryOfBook().build();

        saveCategoryOfBookService.insert(categoryOfBook);
        ArgumentCaptor<CategoryOfBook> captorCategoryOfBook = ArgumentCaptor.forClass(CategoryOfBook.class);

        verify(categoryOfBookRepositoryMock, times(1)).save(captorCategoryOfBook.capture());

        CategoryOfBook result = captorCategoryOfBook.getValue();

        assertAll("categoryOfBook",
                () -> assertThat(result.getName(), is(categoryOfBook.getName()))
        );
    }
}