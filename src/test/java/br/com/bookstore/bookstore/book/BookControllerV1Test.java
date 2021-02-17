package br.com.bookstore.bookstore.book;

import br.com.bookstore.bookstore.book.services.DeleteBookService;
import br.com.bookstore.bookstore.book.services.GetBookService;
import br.com.bookstore.bookstore.book.services.ListBookByCategoryService;
import br.com.bookstore.bookstore.book.services.ListBookService;
import br.com.bookstore.bookstore.book.services.ListPageBookService;
import br.com.bookstore.bookstore.book.services.SaveBookService;
import br.com.bookstore.bookstore.book.services.UpdateBookService;
import br.com.bookstore.bookstore.book.v1.BookControllerV1;
import br.com.bookstore.bookstore.exceptions.BookNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.bookstore.bookstore.book.builders.BookBuilder.createBook;
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
@WebMvcTest(BookControllerV1.class)
@DisplayName("Validates the functionality of the controller responsible of book")
class BookControllerV1Test {

    private final String URL_BOOK = "/v1/api/book";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeleteBookService deleteBookService;

    @MockBean
    private GetBookService getBookService;

    @MockBean
    private ListBookByCategoryService listBookByCategoryService;

    @MockBean
    private ListBookService listBookService;

    @MockBean
    private ListPageBookService listPageBookService;

    @MockBean
    private SaveBookService saveBookService;

    @MockBean
    private UpdateBookService updateBookService;

    @Test
    @DisplayName("findById return book when succesful")
    void findByIdReturnBookWhenSuccessful() throws Exception{

        when(getBookService.findById(anyLong())).thenReturn(createBook().build());

        mockMvc.perform(get(URL_BOOK + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("O Pequeno Príncipe")))
                .andExpect(jsonPath("$.sinopse", is("O Pequeno Príncipe representa a espontaneidade.")))
                .andExpect(jsonPath("$.isbn", is("978-3-16-148410-0")))
                .andExpect(jsonPath("$.autor", is("Antoine de Saint")))
                .andExpect(jsonPath("$.yearOfPublication", is("1943-04-06")))
                .andExpect(jsonPath("$.sellPrice", is(10.00)))
                .andExpect(jsonPath("$.quantityAvailable", is(2)))
                .andExpect(jsonPath("$.categories.[0].id", is(1)))
                .andExpect(jsonPath("$.categories.[0].name", is("Aventura")));

        verify(getBookService).findById(anyLong());
    }

    @Test
    @DisplayName("findById throws BookNotFoundException when book is not found")
    void findByIdBookThrowBookNotFoundExceptionWhenBookNotFound() throws Exception {

        when(getBookService.findById(anyLong())).thenThrow(new BookNotFoundException());

        mockMvc.perform(get(URL_BOOK + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(getBookService).findById(1L);
    }
}