package br.com.bookstore.bookstore.exceptions;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryOfBookNotFoundException extends RuntimeException{

    public CategoryOfBookNotFoundException(){
        super("Category of book not found");
    }
}
