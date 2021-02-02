package br.com.bookstore.bookstore.CategoryOfBook.builders;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;

public class CategoryOfBookBuilder {

    public static CategoryOfBook.Builder createCategoryOfBook (){
        return CategoryOfBook
                .builder()
                .id(1L)
                .name("Romance");
    };
}
