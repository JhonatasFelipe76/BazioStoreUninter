package com.github.jhonatasfelipe76.baozistore.controller;

import com.github.jhonatasfelipe76.baozistore.model.Pedido;
import com.github.jhonatasfelipe76.baozistore.repository.ClienteRepository;
import com.github.jhonatasfelipe76.baozistore.repository.PedidoRepository;
import com.github.jhonatasfelipe76.baozistore.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoController(
            PedidoRepository pedidoRepository,
            ClienteRepository clienteRepository,
            ProdutoRepository produtoRepository) {

        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    //Esse endpoint post verifica se os atributos clienteId, produtoId e quantidade antes de salva-los
    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        if (pedido.getClienteId() == null || pedido.getProdutoId() == null || pedido.getQuantidade() == null || pedido.getQuantidade() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        if (!clienteRepository.existsById(pedido.getClienteId()) || !produtoRepository.existsById(pedido.getProdutoId())) {
            return ResponseEntity.notFound().build();
        } else {
            Pedido pedidoSalvo = pedidoRepository.save(pedido);
            return ResponseEntity.status(201).body(pedidoSalvo);
        }
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        return pedidoRepository.findById(id)
                .map(pedido -> ResponseEntity.ok(pedido))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!pedidoRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }




}
