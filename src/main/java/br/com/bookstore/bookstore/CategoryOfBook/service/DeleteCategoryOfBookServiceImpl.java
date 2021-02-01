package br.com.bookstore.bookstore.CategoryOfBook.service;

import br.com.bookstore.bookstore.CategoryOfBook.CategoryOfBookRepository;
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
