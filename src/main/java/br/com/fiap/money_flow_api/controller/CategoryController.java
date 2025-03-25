package br.com.fiap.money_flow_api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.money_flow_api.model.Category;
import br.com.fiap.money_flow_api.repository.CategoryRepository;


@RequestMapping("/categories")
@RestController //component
public class CategoryController {
    
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private CategoryRepository repository;

    //Listar todas as categorias
    //GET :8080/categories -> 200 ok -> json  
    @GetMapping
    public List<Category> index(){
        return repository.findAll();
    }

    //Cadastrar categories
    //POST :8080/categories
    @PostMapping
    //@ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Category> create(@RequestBody Category category){
        log.info("Cadastrando.." + category.getNome());
        repository.save(category);
        return ResponseEntity.status(201).body(category);
    }

    //Detalhes da catgories
    //POST :8080/categories/id
    @GetMapping("/{id}")
    public Category get(@PathVariable Long id){
        log.info("Buscando categoria by id " + id);

        return (getCategory(id));
    }

    //Apagar categoria
    //DELETE :8080/categories/id
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT) //204
    public void detroy(@PathVariable Long id){
        log.info("Apagando categoria" + id);

        repository.delete(getCategory(id));
    }

    //Editar categoria
    //PUT :8080/categories/id + bbody
    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category){
        log.info("Atualizando categoria" + id + category);

        getCategory(id);
        category.setId(id);
        repository.save(category);

        return category;
    }

    private Category getCategory(Long id) {
        return repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
            );
    }

}
