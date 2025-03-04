package com.example.scheduling.controller;

import com.example.scheduling.dto.ContatoFilterDTO;
import com.example.scheduling.model.Contato;
import com.example.scheduling.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Object> salvar(@RequestBody Contato contato) {
        try {
            Contato novoContato = contatoService.salvar(contato);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoContato);
        } catch (ResponseStatusException ex) {
            // Captura a exceção lançada no serviço e retorna a resposta adequada
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", ex.getReason());
            return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
        }
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

    @GetMapping("/filtrar")
    public List<Contato> listarContatos(ContatoFilterDTO filtro) {
        return contatoService.filtrarContatos(filtro);
    }
}

