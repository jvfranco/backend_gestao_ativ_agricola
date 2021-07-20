package com.gestao.agricola.repository;

import com.gestao.agricola.model.AptAtividadeAgricolaDetalhe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AptAtividadeAgricolaDetalheRepository extends JpaRepository<AptAtividadeAgricolaDetalhe, UUID> {

    @Query(value = "SELECT * FROM ATIVIDADE_AGRICOLA_DETALHE WHERE ID_APONTAMENTO_CABECALHO = ?1", nativeQuery = true)
    List<AptAtividadeAgricolaDetalhe> findAllByApontamento(UUID id);
}
