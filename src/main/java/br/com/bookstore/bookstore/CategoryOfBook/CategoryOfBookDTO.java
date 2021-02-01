package br.com.bookstore.bookstore.CategoryOfBook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class CategoryOfBookDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private String name;

    public static CategoryOfBookDTO from(CategoryOfBook entity) {
        return CategoryOfBookDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static List<CategoryOfBookDTO> fromAll(List<CategoryOfBook> categorys) {
        return categorys.stream().map(CategoryOfBookDTO::from).collect(Collectors.toList());
    }

    public static Page<CategoryOfBookDTO> fromPage(Page<CategoryOfBook> pages){
        return pages.map(CategoryOfBookDTO::from);
    }
}
