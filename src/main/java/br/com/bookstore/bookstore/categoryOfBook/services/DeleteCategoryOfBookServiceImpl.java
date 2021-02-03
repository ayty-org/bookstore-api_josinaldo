package br.com.bookstore.bookstore.categoryOfBook.services;

import br.com.bookstore.bookstore.categoryOfBook.CategoryOfBookRepository;
import br.com.bookstore.bookstore.exceptions.CategoryOfBookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteCategoryOfBookServiceImpl implements DeleteCategoryOfBookService{

    private final CategoryOfBookRepository categoryOfBookRepository;
    @Override
    public void delete(Long id) {
        if(!categoryOfBookRepository.existsById(id)){
            throw new CategoryOfBookNotFoundException();
        }

        categoryOfBookRepository.deleteById(id);
    }
}
