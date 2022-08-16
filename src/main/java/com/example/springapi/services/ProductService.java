package com.example.springapi.services;

import com.example.springapi.entities.Product;
import com.example.springapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.get();
    }

    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    public boolean existsByName(String name) {
        return repository.existsByName(name);
    }
}
