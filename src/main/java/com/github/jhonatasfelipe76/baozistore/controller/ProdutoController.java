package com.github.jhonatasfelipe76.baozistore.controller;

import com.github.jhonatasfelipe76.baozistore.model.Produto;
import com.github.jhonatasfelipe76.baozistore.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository){
        this.repository = repository;
    }
    //Endopoint de cadastro
    @PostMapping
    public ResponseEntity<Produto> create(@RequestBody Produto produto){
        Produto produtoSalvo = repository.save(produto);
        return ResponseEntity.status(201).body(produtoSalvo);

    }
    //Endpoint lista
    @GetMapping
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> produtos = repository.findAll();
        return ResponseEntity.ok(produtos);
    }
    //Endpoint lista pelo id
    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        return repository.findById(id)
                .map(produto -> ResponseEntity.ok(produto))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
    //Endpoint deleta pelo id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
