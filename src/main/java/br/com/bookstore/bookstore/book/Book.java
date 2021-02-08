package br.com.bookstore.bookstore.book;

import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
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

    @OneToMany
    @PrimaryKeyJoinColumn
    private List<CategoryOfBook> categorys;

    public static Book to(BookDTO dto) {
        return Book
                .builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .sinopse(dto.getSinopse())
                .autor(dto.getAutor())
                .isbn(dto.getIsbn())
                .yearOfPublication(dto.getYearOfPublication())
                .sellPrice(dto.getSellPrice())
                .quantityAvailable(dto.getQuantityAvailable())
                .categorys(dto.getCategorys())
                .build();
    }

}
