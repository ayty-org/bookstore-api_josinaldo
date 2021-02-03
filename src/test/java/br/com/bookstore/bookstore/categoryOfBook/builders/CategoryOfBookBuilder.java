package br.com.bookstore.bookstore.categoryOfBook.builders;

import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;

public class CategoryOfBookBuilder {

    public static CategoryOfBook.Builder createCategoryOfBook (){
        return CategoryOfBook
                .builder()
                .id(1L)
                .name("Romance");
    };
}
