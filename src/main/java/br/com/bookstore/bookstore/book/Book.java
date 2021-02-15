package br.com.bookstore.bookstore.book;

import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "tb_book")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String sinopse;

    private String autor;

    private String isbn;

    private LocalDate yearOfPublication;

    private double sellPrice;

    private int quantityAvailable;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "book_category",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<CategoryOfBook> categories = new HashSet<>();

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
                .categories(dto.getCategories())
                .build();
    }

}
