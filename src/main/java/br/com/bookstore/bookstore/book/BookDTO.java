package br.com.bookstore.bookstore.book;

import br.com.bookstore.bookstore.categoryofbook.CategoryOfBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String title;

    @Size(max = 500)
    private String sinopse;

    @Size(min = 1)
    @NotNull
    private String autor;

    @Size(min = 17, max = 17, message = "ISBN must contain 17 characters" + "\n Ex.: 978-3-16-148410-0")
    @NotNull(message = "ISBN cannot be null")
    private String isbn;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate yearOfPublication;

    @NotNull
    @DecimalMin(value = "0.00", message = "The min value to sell price is {value} .")
    private double sellPrice;

    @NotNull
    @Min(0)
    private int quantityAvailable;

    private Set<CategoryOfBook> categories;

    public static BookDTO from(Book entity) {
        return BookDTO
                .builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .sinopse(entity.getSinopse())
                .autor(entity.getAutor())
                .isbn(entity.getIsbn())
                .yearOfPublication(entity.getYearOfPublication())
                .sellPrice(entity.getSellPrice())
                .quantityAvailable(entity.getQuantityAvailable())
                .categories(entity.getCategories())
                .build();
    }

    public static List<BookDTO> fromAll(List<Book> books) {
        return books.stream().map(BookDTO::from).collect(Collectors.toList());
    }

    public static Page<BookDTO> fromPage(Page<Book> pages){
        return pages.map(BookDTO::from);
    }
}
