package com.gestao.agricola.service;

import com.gestao.agricola.model.Cultivar;
import com.gestao.agricola.model.Cultura;
import com.gestao.agricola.repository.CultivarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class CultivarService {

    @Autowired
    private CultivarRepository cultivarRepository;

    @Autowired
    private CulturaService culturaService;

    public Page<Cultivar> findAll(Pageable paginacao) {
        return this.cultivarRepository.findAll(paginacao);
    }

    public Cultivar findById(UUID id) {
        return this.cultivarRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cultivar não encontrada!"));
    }

    public URI save(Cultivar cultivar, UriComponentsBuilder uriBuilder) {
        this.cultivarRepository.save(cultivar);
        return uriBuilder.path("/cultivar/{id}").buildAndExpand(cultivar.getId()).toUri();
    }

    public void update(UUID id, Cultivar cultivarAtualizada) {
        Cultivar cultivar = this.findById(id);
        this.cultivarRepository.save(this.retornaCultivarAposAtualizacao(cultivarAtualizada, cultivar));
    }

    private Cultivar retornaCultivarAposAtualizacao(Cultivar cultivarAtualizada, Cultivar cultivar) {
        if(!cultivarAtualizada.getIdentificacao().isEmpty() && cultivarAtualizada.getIdentificacao() != null) {
            cultivar.setIdentificacao(cultivarAtualizada.getIdentificacao());
        }

        if(cultivarAtualizada.getCicloEmDias() > 0 && cultivarAtualizada.getCicloEmDias() != cultivar.getCicloEmDias()) {
            cultivar.setCicloEmDias(cultivarAtualizada.getCicloEmDias());
        }

        if(cultivarAtualizada.getCultura() != null && cultivarAtualizada.getCultura().getId() != cultivar.getCultura().getId()){
            Cultura cultura = this.culturaService.findById(cultivarAtualizada.getCultura().getId());
            cultivar.setCultura(cultura);
        }

        if(!cultivarAtualizada.getObservacoes().isEmpty() && cultivarAtualizada.getObservacoes() != null) {
            cultivar.setObservacoes(cultivarAtualizada.getObservacoes());
        }

        cultivar.setDataAtualizacao(LocalDate.now());

        return cultivar;
    }

    public void delete(UUID id) {
        try {
            this.cultivarRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
