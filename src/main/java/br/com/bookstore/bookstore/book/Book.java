package br.com.bookstore.bookstore.book;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBook;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderClassName = "Builder")
@Table(name = "tb_book")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String sinopse;

    private String autor;

    private String isbn;

    private LocalDate yearOfPublication;

    private double sellPrice;

    private int quantityAvailable;

    private List<CategoryOfBook> categorys;

    public static Book to(BookDTO entity) {
        return Book
                .builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .sinopse(entity.getSinopse())
                .autor(entity.getAutor())
                .isbn(entity.getIsbn())
                .yearOfPublication(entity.getYearOfPublication())
                .sellPrice(entity.getSellPrice())
                .quantityAvailable(entity.getQuantityAvailable())
                .categorys(entity.getCategorys())
                .build();
    }

}
