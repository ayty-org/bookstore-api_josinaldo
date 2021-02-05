package br.com.bookstore.bookstore.book;

import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByCategory(CategoryOfBook category);
}
