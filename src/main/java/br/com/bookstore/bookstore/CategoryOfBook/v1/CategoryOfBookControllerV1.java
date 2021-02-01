package br.com.bookstore.bookstore.CategoryOfBook.v1;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBookDTO;
import br.com.bookstore.bookstore.CategoryOfBook.service.DeleteCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.service.GetCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.service.ListCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.service.ListPageCategoryOfBooksService;
import br.com.bookstore.bookstore.CategoryOfBook.service.SaveCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.service.UpdateCategoryOfBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/book/category")
public class CategoryOfBookControllerV1 {

    private final GetCategoryOfBookService getCategoryOfBookService;
    private final UpdateCategoryOfBookService updateCategoryOfBookService;
    private final ListCategoryOfBookService listCategoryOfBookService;
    private final ListPageCategoryOfBooksService listPageCategoryOfBooksService;
    private final DeleteCategoryOfBookService deleteCategoryOfBookService;
    private final SaveCategoryOfBookService saveCategoryOfBookService;

    @GetMapping(value = "/{id}") //list categoryofbook by id
    public CategoryOfBookDTO find(@PathVariable Long id){
        return CategoryOfBookDTO.from(getCategoryOfBookService.findById(id));
    }

    public List<CategoryOfBookDTO> findAll(){
        return CategoryOfBookDTO.fromAll(listCategoryOfBookService.findAll());
    }

    @GetMapping(path = {"/page"})
    public Page<CategoryOfBookDTO> findPage(Pageable pageable) {
        return CategoryOfBookDTO.fromPage(listPageCategoryOfBooksService.findPage(pageable));
    }
}
