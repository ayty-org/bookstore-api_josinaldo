package br.com.bookstore.bookstore.categoryofbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryOfBookRepository extends JpaRepository<CategoryOfBook, Long> {
}
