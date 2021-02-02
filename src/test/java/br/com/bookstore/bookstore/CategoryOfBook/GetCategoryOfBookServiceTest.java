package br.com.bookstore.bookstore.CategoryOfBook;

import br.com.bookstore.bookstore.CategoryOfBook.services.GetCategoryOfBookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for searching for a category of book by id ")
class GetCategoryOfBookServiceTest {

    @Mock
    private CategoryOfBookRepository categoryOfBookRepository;

    private GetCategoryOfBookServiceImpl getCategoryOfBookService;

    @BeforeEach
    void setUp() {
        this.getCategoryOfBookService = new GetCategoryOfBookServiceImpl(categoryOfBookRepository);
    }

    @Test
    @DisplayName("findById returns client when succesful")
    void findByIdReturnClientWhenSuccessful(){

    }
}