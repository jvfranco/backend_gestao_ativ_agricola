package com.gestao.agricola.repository;

import com.gestao.agricola.model.Hibrido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HibridoRepository extends JpaRepository<Hibrido, UUID> {
}
