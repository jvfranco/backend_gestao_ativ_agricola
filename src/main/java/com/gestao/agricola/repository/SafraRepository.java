package com.gestao.agricola.repository;

import com.gestao.agricola.model.Safra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SafraRepository extends JpaRepository<Safra, UUID> {
}
