package com.gestao.agricola.service;

import com.gestao.agricola.entity.dto.PropriedadeDTO;
import com.gestao.agricola.repository.PropriedadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PropriedadeService {

    @Autowired
    private PropriedadeRepository propriedadeRepository;


    public Page<PropriedadeDTO> findAll(Pageable paginacao) {
        return this.propriedadeRepository.findAll(paginacao).map(PropriedadeDTO::new);
    }

    public Optional<PropriedadeDTO> findById(UUID id) {
        return this.propriedadeRepository.findById(id).map(PropriedadeDTO::new);
    }
}
