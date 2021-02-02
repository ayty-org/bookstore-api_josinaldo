package br.com.bookstore.bookstore.CategoryOfBook;

import br.com.bookstore.bookstore.CategoryOfBook.services.ListPageCategoryOfBooksServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static br.com.bookstore.bookstore.CategoryOfBook.builders.CategoryOfBookBuilder.createCategoryOfBook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("Service")
@DisplayName("Validates the functionality of the services responsible for pagination of all client")
class ListPageCategoryOfBooksServiceTest {

    @Mock
    private CategoryOfBookRepository categoryOfBookRepository;

    private ListPageCategoryOfBooksServiceImpl listPageCategoryOfBooksService;

    @BeforeEach
    void setUp() {
        this.listPageCategoryOfBooksService = new ListPageCategoryOfBooksServiceImpl(categoryOfBookRepository);
    }

    @Test
    @DisplayName("listAll returns list to category of books inside page object when successful")
    void listAllReturnsListOfClientInsidePageObjectWhenSuccessful() {

        Pageable pageable = PageRequest.of(0,2);

        when(listPageCategoryOfBooksService.findPage(pageable))
                .thenReturn(new PageImpl<>(Collections.nCopies(2, createCategoryOfBook().build())));

        Page<CategoryOfBook> result = this.listPageCategoryOfBooksService.findPage(pageable);

        CategoryOfBook categoryOfBook = createCategoryOfBook().build();
        //verification
        assertAll("CategoryOfBook",
                ()-> assertThat(result.getNumber(), is(0)),
                ()-> assertThat(result.getTotalElements(), is(2L)),
                ()-> assertThat(result.getTotalPages(), is(1)),
                ()-> assertThat(result.getSize(), is(2)),
                ()-> assertThat(result.getContent().get(0).getName(), is(categoryOfBook.getName())),
                ()-> assertThat(result.getContent().get(1).getName(), is(categoryOfBook.getName()))
        );
    }
}