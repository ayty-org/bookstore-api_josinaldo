package br.com.bookstore.bookstore.categoryofbook.builders;

import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;

public class CategoryOfBookBuilder {

    public static CategoryOfBook.Builder createCategoryOfBook (){
        return CategoryOfBook
                .builder()
                .id(1L)
                .name("Romance");
    };
}
