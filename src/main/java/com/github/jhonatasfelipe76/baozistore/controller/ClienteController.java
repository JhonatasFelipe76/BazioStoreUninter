package com.github.jhonatasfelipe76.baozistore.controller;

import com.github.jhonatasfelipe76.baozistore.model.Cliente;
import com.github.jhonatasfelipe76.baozistore.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository){
        this.repository = repository;
    }


    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {

        Cliente clienteSalvo = repository.save(cliente);
        return ResponseEntity.status(201).body(clienteSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> clientes = repository.findAll();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return repository.findById(id)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
