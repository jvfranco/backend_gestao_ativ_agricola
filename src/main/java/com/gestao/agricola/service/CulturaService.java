package com.gestao.agricola.service;

import com.gestao.agricola.model.Cultura;
import com.gestao.agricola.model.dto.CulturaDTO;
import com.gestao.agricola.repository.CulturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CulturaService {

    @Autowired
    private CulturaRepository culturaRepository;

    public Page<Cultura> findAll(Pageable paginacao) {
        return this.culturaRepository.findAll(paginacao);
    }

    public Cultura findById(UUID id) {
        return this.culturaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cultura não encontrada!"));
    }

    public URI save(Cultura cultura, UriComponentsBuilder uriBuilder) {
        this.culturaRepository.save(cultura);
        return uriBuilder.path("/cultura/{id}").buildAndExpand(cultura.getId()).toUri();
    }

    public void update(UUID id, Cultura culturaAtualizada) {
        Cultura cultura = this.findById(id);
        this.culturaRepository.save(this.retornaCulturaAposAtualizacao(culturaAtualizada, cultura));
    }

    private Cultura retornaCulturaAposAtualizacao(Cultura culturaAtualizada, Cultura cultura) {
        if(!culturaAtualizada.getNome().isEmpty() && culturaAtualizada.getNome() != null) {
            cultura.setNome(culturaAtualizada.getNome());
        }

        if(!culturaAtualizada.getNomeCientifico().isEmpty() && culturaAtualizada.getNomeCientifico() != null) {
            cultura.setNomeCientifico(culturaAtualizada.getNomeCientifico());
        }

        return cultura;
    }

    public void delete(UUID id) {
        try {
            this.culturaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<CulturaDTO> retornaCulturasDTO() {
        List<Cultura> culturas = this.culturaRepository.findAll();
        return culturas.stream().map(cultura -> {
            return CulturaDTO.builder()
                    .id(cultura.getId())
                    .nome(cultura.getNome())
                    .build();
        }).collect(Collectors.toList());
    }
}
