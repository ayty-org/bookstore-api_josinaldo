package br.com.bookstore.bookstore.book;

import br.com.bookstore.bookstore.book.services.ListBookServiceImpl;
import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.bookstore.bookstore.book.builders.BookBuilder.createBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for list all book")
class ListBookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock;

    private ListBookServiceImpl listBookService;

    @BeforeEach
    void setUp() {
        this.listBookService = new ListBookServiceImpl(bookRepositoryMock);
    }

    @Test
    @DisplayName("listAll returns list of books when successful")
    void listAllReturnsListOfBookWhenSuccessful() {
        List<Book> bookList = new ArrayList<>();

        CategoryOfBook categoryOfBookTest = new CategoryOfBook(1L,"Aventura");
        Set<CategoryOfBook> categoryOfBookSet = new HashSet<>();
        categoryOfBookSet.add(categoryOfBookTest);

        Book book = createBook().categories(categoryOfBookSet).build();
        bookList.add(book);
        when(bookRepositoryMock.findAll()).thenReturn(bookList);

        List<Book> result = this.listBookService.findAll();

        assertAll("Book",
                () -> assertThat(result.size(), is(1)),
                () -> assertThat(result.get(0).getTitle(), is("O Pequeno Príncipe")),
                ()-> assertThat(result.get(0).getSinopse(), is("O Pequeno Príncipe representa a espontaneidade.")),
                ()-> assertThat(result.get(0).getIsbn(), is("978-3-16-148410-0")),
                ()-> assertThat(result.get(0).getAutor(), is("Antoine de Saint")),
                ()-> assertThat(result.get(0).getYearOfPublication(), is(LocalDate.of(1943, 4, 6))),
                ()-> assertThat(result.get(0).getSellPrice(), is(10.00)),
                ()-> assertThat(result.get(0).getQuantityAvailable(), is(2)),
                ()-> assertThat(result.get(0).getCategories(), is(book.getCategories()))
        );

        verify(bookRepositoryMock, times(1)).findAll();
    }
}