package br.com.bookstore.bookstore.CategoryOfBook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
}
