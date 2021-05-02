package com.gestao.agricola.repository;

import com.gestao.agricola.entity.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, UUID> {
}
