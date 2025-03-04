package com.example.scheduling.service;

import com.example.scheduling.model.Contato;
import com.example.scheduling.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public List<Contato> listarTodos() {
        return contatoRepository.findAll();
    }

    public Optional<Contato> buscarPorId(Long id) {
        return contatoRepository.findById(id);
    }

    public Contato salvar(Contato contato) {
        contato.setDateCadastro(LocalDateTime.now());
        return contatoRepository.save(contato);
    }

    public Contato atualizar(Long id, Contato contatoAtualizado) {
        return contatoRepository.findById(id)
                .map(contato -> {
                    contato.setNome(contatoAtualizado.getNome());
                    contato.setEmail(contatoAtualizado.getEmail());
                    contato.setCelular(contatoAtualizado.getCelular());
                    contato.setTelefone(contatoAtualizado.getTelefone());
                    contato.setFavorito(contatoAtualizado.getFavorito());
                    contato.setAtivo(contatoAtualizado.getAtivo());
                    return contatoRepository.save(contato);
                })
                .orElseThrow(() -> new RuntimeException("Contato não encontrado"));
    }

    public void deletar(Long id) {
        contatoRepository.deleteById(id);
    }
}
