package br.com.bookstore.bookstore.book;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String title;

    private String sinopse;

    private String autor;

    private String isbn;

    private double sellPrice;

    private int quantityAvailable;
}
