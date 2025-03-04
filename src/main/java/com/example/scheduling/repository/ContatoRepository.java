package com.example.scheduling.repository;

import com.example.scheduling.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>, ContatoRepositoryCustom {

    @Query(value = " SELECT c.* " +
            " FROM desafio.contato c " +
            " WHERE c.contato_celular like :celular " +
            " limit 1;", nativeQuery = true)
    Contato findByCelular(@Param("celular") String celular);

}
