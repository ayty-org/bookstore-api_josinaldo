package br.com.bookstore.bookstore.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookAlreadyExistException extends RuntimeException{

    public BookAlreadyExistException() {
        super("Book already exists");
    }
}
