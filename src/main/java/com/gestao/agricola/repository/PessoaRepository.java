package com.gestao.agricola.repository;

import com.gestao.agricola.model.Ocupacao;
import com.gestao.agricola.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    public List<Pessoa> findByOcupacao(Ocupacao ocupacao);
}
