package com.example.scheduling.repository;

import com.example.scheduling.dto.ContatoFilterDTO;
import com.example.scheduling.model.Contato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ContatoRepositoryCustomImpl implements ContatoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Contato> filtrarContatos(ContatoFilterDTO filtro) {
        String sql = "SELECT * FROM desafio.contato WHERE 1=1 ";
        List<Object> params = new ArrayList<>();

        if (StringUtils.hasText(filtro.getNome())) {
            sql += " AND LOWER(contato_nome) LIKE ? ";
            params.add("%" + filtro.getNome().toLowerCase() + "%");
        }

        if (StringUtils.hasText(filtro.getEmail())) {
            sql += " AND LOWER(contato_email) LIKE ? ";
            params.add("%" + filtro.getEmail().toLowerCase() + "%");
        }

        if (StringUtils.hasText(filtro.getCelular())) {
            sql += " AND contato_celular LIKE ? ";
            params.add("%" + filtro.getCelular() + "%");
        }

        if (StringUtils.hasText(filtro.getTelefone())) {
            sql += " AND contato_telefone LIKE ? ";
            params.add("%" + filtro.getTelefone() + "%");
        }

        if (StringUtils.hasText(filtro.getFavorito())) {
            sql += " AND contato_sn_favorito = ? ";
            params.add(filtro.getFavorito());
        }

        if (StringUtils.hasText(filtro.getAtivo())) {
            sql += " AND contato_sn_ativo = ? ";
            params.add(filtro.getAtivo());
        }

        Query query = entityManager.createNativeQuery(sql, Contato.class);

        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i));
        }

        return query.getResultList();
    }
}

