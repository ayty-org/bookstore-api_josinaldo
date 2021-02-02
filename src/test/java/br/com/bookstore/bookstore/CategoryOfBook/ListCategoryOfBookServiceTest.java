package br.com.bookstore.bookstore.CategoryOfBook;

import br.com.bookstore.bookstore.CategoryOfBook.services.ListCategoryOfBookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.bookstore.bookstore.CategoryOfBook.builders.CategoryOfBookBuilder.createCategoryOfBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for list all category of books")
class ListCategoryOfBookServiceTest {

    @Mock
    private CategoryOfBookRepository categoryOfBookRepositoryMock;

    private ListCategoryOfBookServiceImpl listCategoryOfBookService;

    @BeforeEach
    void setUp() {
        this.listCategoryOfBookService = new ListCategoryOfBookServiceImpl(categoryOfBookRepositoryMock);
    }

    @Test
    @DisplayName("listAll returns to list category of books when successful")
    void listAllReturnsListOfCategoryOfBooksWhenSuccessfull() {

        when(categoryOfBookRepositoryMock.findAll()).thenReturn(
                Stream.of(createCategoryOfBook().name("Ação").build()).collect(Collectors.toList())
        );

        List<CategoryOfBook> result = this.listCategoryOfBookService.findAll();

        //verification
        assertAll("CategoryOfBook",
                () -> assertThat(result.size(), is(1)),
                () -> assertThat(result.get(0).getName(), is("Ação"))
        );
    }
}