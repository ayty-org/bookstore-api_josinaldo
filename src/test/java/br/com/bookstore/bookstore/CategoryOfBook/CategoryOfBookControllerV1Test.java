package br.com.bookstore.bookstore.CategoryOfBook;

import br.com.bookstore.bookstore.CategoryOfBook.services.DeleteCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.services.GetCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.services.ListCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.services.ListPageCategoryOfBooksService;
import br.com.bookstore.bookstore.CategoryOfBook.services.SaveCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.services.UpdateCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.v1.CategoryOfBookControllerV1;
import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;

import static br.com.bookstore.bookstore.CategoryOfBook.builders.CategoryOfBookBuilder.createCategoryOfBook;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("Controller")
@ExtendWith(SpringExtension.class)
@WebMvcTest(CategoryOfBookControllerV1.class)
@DisplayName("Validates the functionality of the controller responsible of category of book")
class CategoryOfBookControllerV1Test {

    private final String URL_CATEGORYOFBOOK = "/v1/api/book/category";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetCategoryOfBookService getCategoryOfBookService;

    @MockBean
    private ListCategoryOfBookService listCategoryOfBookService;

    @MockBean
    private ListPageCategoryOfBooksService listPageCategoryOfBooksService;

    @MockBean
    private SaveCategoryOfBookService saveCategoryOfBookService;

    @MockBean
    private DeleteCategoryOfBookService deleteCategoryOfBookService;

    @MockBean
    private UpdateCategoryOfBookService updateCategoryOfBookService;

    @Test
    @DisplayName("findById returns category of book when successful")
    void findByIdReturnCategoryOfBookWhenSuccessful() throws Exception {

        when(getCategoryOfBookService.findById(anyLong())).thenReturn(createCategoryOfBook().build());

        CategoryOfBook categoryOfBookBuilder = createCategoryOfBook().build();

        mockMvc.perform(get(URL_CATEGORYOFBOOK + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is(categoryOfBookBuilder.getName())));

        verify(getCategoryOfBookService).findById(anyLong());
    }

    @Test
    @DisplayName("findById throws CategoryOfBookNotFoundException when category of book is not found")
    void findByIdCategoryOfBookThrowCategoryOfBookNotFoundExceptionWhenCategoryOfBookNotFound() throws Exception {

        when(getCategoryOfBookService.findById(anyLong())).thenThrow( new CategoryOfBookNotFoundException());

        mockMvc.perform(get(URL_CATEGORYOFBOOK + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getCategoryOfBookService).findById(anyLong());
    }

    @Test
    @DisplayName("listAll returns list of category of book when successful")
    void listAllReturnsListOfCategoryOfBookWhenSuccessfull() throws Exception {

        when(listCategoryOfBookService.findAll()).thenReturn(Lists.newArrayList(
           createCategoryOfBook().id(1L).build(),
           createCategoryOfBook().id(2L).build(),
           createCategoryOfBook().id(3L).build(),
           createCategoryOfBook().id(4L).build()
        ));

        CategoryOfBook categoryOfBook = createCategoryOfBook().build();

        mockMvc.perform(get(URL_CATEGORYOFBOOK).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(4)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is(categoryOfBook.getName())))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is(categoryOfBook.getName())))
                .andExpect(jsonPath("$[2].id", is(3)))
                .andExpect(jsonPath("$[2].name", is(categoryOfBook.getName())))
                .andExpect(jsonPath("$[3].id", is(4)))
                .andExpect(jsonPath("$[3].name", is(categoryOfBook.getName()))
                );

        verify(listCategoryOfBookService).findAll();
    }

    @Test
    @DisplayName("listAll returns list of category of books inside page object when successful")
    void listAllReturnsListOfCategoryOfBookInsidePageObject_WhenSuccessful() throws Exception{

        Page<CategoryOfBook> categoryOfBooksPage = new PageImpl<>(Collections.singletonList(createCategoryOfBook().build()));

        Pageable pageable = PageRequest.of(0,2);

        when(listPageCategoryOfBooksService.findPage(pageable)).thenReturn(categoryOfBooksPage);

        CategoryOfBook categoryOfBookBuild = createCategoryOfBook().build();

        mockMvc.perform(get(URL_CATEGORYOFBOOK + "/page/?page=0&size=2", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].name", is(categoryOfBookBuild.getName())))
        ;

        verify(listPageCategoryOfBooksService).findPage(pageable);
    }
}