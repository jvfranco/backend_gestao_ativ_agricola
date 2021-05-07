package com.gestao.agricola.repository;

import com.gestao.agricola.entity.Talhao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TalhaoRepository extends JpaRepository<Talhao, UUID> {
}
