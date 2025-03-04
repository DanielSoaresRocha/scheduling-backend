package com.example.scheduling.repository;

import com.example.scheduling.dto.ContatoFilterDTO;
import com.example.scheduling.model.Contato;
import java.util.List;

public interface ContatoRepositoryCustom {

    List<Contato> filtrarContatos(ContatoFilterDTO filtro);
}