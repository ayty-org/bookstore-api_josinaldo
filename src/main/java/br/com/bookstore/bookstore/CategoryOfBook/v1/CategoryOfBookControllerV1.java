package br.com.bookstore.bookstore.CategoryOfBook.v1;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;
import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBookDTO;
import br.com.bookstore.bookstore.CategoryOfBook.services.DeleteCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.services.GetCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.services.ListCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.services.ListPageCategoryOfBooksService;
import br.com.bookstore.bookstore.CategoryOfBook.services.SaveCategoryOfBookService;
import br.com.bookstore.bookstore.CategoryOfBook.services.UpdateCategoryOfBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

    @GetMapping(value = "/{id}") //list category of book by id
    public CategoryOfBookDTO find(@PathVariable Long id){
        return CategoryOfBookDTO.from(getCategoryOfBookService.findById(id));
    }

    @GetMapping
    public List<CategoryOfBookDTO> findAll(){ //list all category of book
        return CategoryOfBookDTO.fromAll(listCategoryOfBookService.findAll());
    }

    @GetMapping(path = {"/page"}) //list all category of book inside object page
    public Page<CategoryOfBookDTO> findPage(Pageable pageable) {
        return CategoryOfBookDTO.fromPage(listPageCategoryOfBooksService.findPage(pageable));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping //create category of book
    public void insert(@Valid @RequestBody CategoryOfBookDTO categoryOfBookDTO){
        saveCategoryOfBookService.insert(CategoryOfBook.to(categoryOfBookDTO));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}") //replace category of book by id
    public void update(@Valid @RequestBody CategoryOfBookDTO categoryOfBookDTO, @PathVariable Long id) {
        updateCategoryOfBookService.update(CategoryOfBook.to(categoryOfBookDTO), id);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}") //delete category of book
    public void delete(@PathVariable Long id) {
        deleteCategoryOfBookService.delete(id);
    }
}
