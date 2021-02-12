package br.com.bookstore.bookstore.categoryofbook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Builder(builderClassName = "Builder")
@Entity
@Table(name = "tb_categoryofbook")public class CategoryOfBook implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public static CategoryOfBook to(CategoryOfBookDTO dto) {
        return CategoryOfBook
                .builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

}
