package com.example.springapi.resources;

import com.example.springapi.config.SwaggerConfig;
import com.example.springapi.entities.User;
import com.example.springapi.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.PostUpdate;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/usuarios")
@Api(value = "Usuários", tags = {SwaggerConfig.USUARIO_TAG})

public class UserResource {

    @Autowired
    private UserService service;

    @ApiOperation(value = "Lista usuários do sistema")
    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
                return ResponseEntity.ok().body(list);
    }

    @ApiOperation(value = "Lista usuários do sistema por id")
    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @ApiOperation(value = "Adiciona um novo usuário ao sistema")
    @PostMapping("/users")
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @ApiOperation(value = "Exclui o usuário de id pesquisado da base de dados")
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Altera as informações do usuário do id pesquisado cadastrado no sistema")
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,@RequestBody User obj ){
        obj = service.update(id,obj);
        return ResponseEntity.ok().body(obj);
    }
}
