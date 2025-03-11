package br.com.fiap.money_flow_api.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.money_flow_api.model.Category;



@RestController //component
public class CategoryController {
    
    private List<Category> repository = new ArrayList<>();

    //Listar todas as categorias
    //GET :8080/categories -> 200 ok -> json  
    @GetMapping(path = "/categories")
    public List<Category> index(){
        return repository;
    }

    //Cadastrar categories
    //POST :8080/categories
    @PostMapping("/categories")
    //@ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Category> create(@RequestBody Category category){
        System.out.println("Cadastrando.." + category.getNome());
        repository.add(category);
        return ResponseEntity.status(201).body(category);
    }

    //Detalhes da catgories
    //POST :8080/categories/id
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> get(@PathVariable Long id){
        System.out.println("Buscando categoria by id " + id);
        var category = repository
            .stream()
            .filter(c -> c.getId().equals(id))
            .findFirst();

        if (category.isEmpty()) {
           return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(category.get());

    }

    //Apagar categoria
    //DELETE :8080/categories/id
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Object> detroy(@PathVariable Long id){
        System.out.println("Apagando categoria" + id);

        var category = repository
            .stream()
            .filter(c -> c.getId().equals(id))
            .findFirst();

        if (category.isEmpty()) {
           return ResponseEntity.notFound().build();
        }
        
        repository.remove(category.get());
        return ResponseEntity.noContent().build(); //204
    }

    //Editar categoria
    //PUT :8080/categories/id + bbody
    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category){
        System.out.println("Atualizando categoria" + id + category);

        var oldCategory = repository
            .stream()
            .filter(c -> c.getId().equals(id))
            .findFirst();

        if (oldCategory.isEmpty()) {
           return ResponseEntity.notFound().build();
        }

        repository.remove(oldCategory.get());
        repository.add(category);

        return ResponseEntity.ok(category);
    }

}
