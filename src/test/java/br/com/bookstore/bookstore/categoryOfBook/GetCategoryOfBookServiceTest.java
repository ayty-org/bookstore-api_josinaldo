package br.com.bookstore.bookstore.categoryOfBook;

import br.com.bookstore.bookstore.categoryOfBook.services.GetCategoryOfBookServiceImpl;
import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.com.bookstore.bookstore.categoryOfBook.builders.CategoryOfBookBuilder.createCategoryOfBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for searching for a category of book by id ")
class GetCategoryOfBookServiceTest {

    @Mock
    private CategoryOfBookRepository categoryOfBookRepositoryMock;

    private GetCategoryOfBookServiceImpl getCategoryOfBookService;

    @BeforeEach
    void setUp() {
        this.getCategoryOfBookService = new GetCategoryOfBookServiceImpl(categoryOfBookRepositoryMock);
    }

    @Test
    @DisplayName("findById returns category of book when succesful")
    void findByIdReturnCategoryOfBookWhenSuccessful(){
        CategoryOfBook categoryOfBook = createCategoryOfBook().build(); //create a build to category of book

        Optional<CategoryOfBook> categoryOfBookSavedOptional = Optional.of(categoryOfBook);
        when(categoryOfBookRepositoryMock.findById(anyLong())).thenReturn(categoryOfBookSavedOptional);

        CategoryOfBook result = this.getCategoryOfBookService.findById(1L); //result of requisition

        //verification
        assertAll("categoryOfBook",
                () -> assertThat(result.getName(), is(categoryOfBook.getName()))
        );
    }

    @Test
    @DisplayName("findById throws CategoryOfBookNotFoundException when category of book is not found")
    void findByIdThrowCategoryOfBookNotFoundExceptionWhenCategoryOfBookNotFound() {
        when(categoryOfBookRepositoryMock.findById(anyLong())).thenThrow(new CategoryOfBookNotFoundException());

        assertThrows(CategoryOfBookNotFoundException.class, () -> getCategoryOfBookService.findById(1l));
    }
}