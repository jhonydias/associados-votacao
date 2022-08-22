package com.example.springapi.resources;

import com.example.springapi.entities.User;
import com.example.springapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.PostUpdate;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
                return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/users")
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User obj ){
        obj = service.update(id,obj);
        return ResponseEntity.ok().body(obj);
    }
}
