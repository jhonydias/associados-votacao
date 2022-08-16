package com.example.springapi.services;

import com.example.springapi.entities.Category;
import com.example.springapi.repositories.CategoryRepository;
import com.example.springapi.resources.CategoryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll(){
        return repository.findAll();
    }

    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
    @Transactional
    public Category save(Category category) {
        return repository.save(category);
    }

    @Transactional
    public void delete(Category category) {
        repository.delete(category);
    }
}
