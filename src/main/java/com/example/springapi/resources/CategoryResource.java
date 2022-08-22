package com.example.springapi.resources;

import com.example.springapi.dtos.CategoryDTO;
import com.example.springapi.entities.Category;
import com.example.springapi.entities.User;
import com.example.springapi.services.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
public class CategoryResource {

    @Autowired
    private CategoryService service;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/categories/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        Category obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/categories")
    public ResponseEntity<Object> insert(@RequestBody @Valid CategoryDTO categoryDTO){
        if (service.existsByName(categoryDTO.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Name of Category is already in use!");
        }

        var category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(category));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid CategoryDTO categoryDTO){
        Optional<Category> categoryOptional = Optional.ofNullable(service.findById(id));
        if (!categoryOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND: The Category is not found!");
        }
        if (service.existsByName(categoryDTO.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: The Name of Category is already in use!");
        }

        var category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setId(categoryOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(service.save(category));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable(value = "id") Long id){
        Optional<Category> categoryOptional = Optional.ofNullable(service.findById(id));
        if (!categoryOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The Category is not found");
        }

        service.delete(categoryOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Category ID:" + id + " - Category deleted successfully");
    }

}
