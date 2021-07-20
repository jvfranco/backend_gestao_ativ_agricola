package com.gestao.agricola.repository;

import com.gestao.agricola.model.Talhao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TalhaoRepository extends JpaRepository<Talhao, UUID> {

    @Query(value = "SELECT * FROM TALHAO WHERE ID_PROPRIEDADE = ?1", nativeQuery = true)
    List<Talhao> findAllByPropriedade(UUID id);
}
