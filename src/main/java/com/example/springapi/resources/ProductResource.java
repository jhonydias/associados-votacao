package com.example.springapi.resources;

import com.example.springapi.dtos.CategoryDTO;
import com.example.springapi.dtos.ProductDTO;
import com.example.springapi.entities.Category;
import com.example.springapi.entities.Product;
import com.example.springapi.services.CategoryService;
import com.example.springapi.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Rest Products")
@CrossOrigin(origins = "*")
public class ProductResource {

    @Autowired
    private ProductService service;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "Retorna uma lista de produtos")
    @GetMapping( "/products")
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "products/{id}")
    @ApiOperation(value = "Retorna um produto pelo ID")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/products")
    @ApiOperation(value = "Cadastra um produto")
    public ResponseEntity<Object> insert(@RequestBody @Valid ProductDTO productDTO){
        var product = new Product();
        var categorySet = new HashSet<Category>();


        for (Long id : productDTO.getCategoriesId()){
            Category category = categoryService.findById(id);
            if (category == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NOT FOUND: The Category of this product is not found!");
            }

            categorySet.add(category);
        }

        BeanUtils.copyProperties(productDTO, product);
        product.setCategories(categorySet);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("/products/{id}")
    @ApiOperation(value = "Edita um produto")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id, @RequestBody @Valid ProductDTO productDTO){
        Optional<Product> productOptional = Optional.ofNullable(service.findById(id));
        if (productOptional.isEmpty() || !productOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND: The Product is not found!");
        }

        var product = new Product();
        var categorySet = new HashSet<Category>();

        for (Long idCategory : productDTO.getCategoriesId()){
            Category category = categoryService.findById(idCategory);
            if (category == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("NOT FOUND: The Category of this product is not found!");
            }

            categorySet.add(category);
        }

        BeanUtils.copyProperties(productDTO, product);
        product.setCategories(categorySet);
        product.setId(productOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(service.save(product));
    }
}
