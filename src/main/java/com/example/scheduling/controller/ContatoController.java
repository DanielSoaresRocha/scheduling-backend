package com.example.scheduling.controller;

import com.example.scheduling.model.Contato;
import com.example.scheduling.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public List<Contato> listarTodos() {
        return contatoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarPorId(@PathVariable Long id) {
        Optional<Contato> contato = contatoService.buscarPorId(id);
        return contato.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Contato salvar(@RequestBody Contato contato) {
        return contatoService.salvar(contato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizar(@PathVariable Long id, @RequestBody Contato contatoAtualizado) {
        try {
            Contato contato = contatoService.atualizar(id, contatoAtualizado);
            return ResponseEntity.ok(contato);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        contatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

