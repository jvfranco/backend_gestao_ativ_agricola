package com.gestao.agricola.service;

import com.gestao.agricola.model.Ocorrencia;
import com.gestao.agricola.model.Safra;
import com.gestao.agricola.model.Talhao;
import com.gestao.agricola.repository.OcorrenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Service
public class OcorrenciaService {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private SafraService safraService;

    @Autowired
    private HibridoService hibridoService;

    @Autowired
    private TalhaoService talhaoService;

    public Page<Ocorrencia> findAll(Pageable paginacao) {
        return this.ocorrenciaRepository.findAll(paginacao);
    }

    public Ocorrencia findById(UUID id) {
        return this.ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ocorrência não encontrada!"));
    }

    public URI save(Ocorrencia ocorrencia, UriComponentsBuilder uriBuilder) {
        this.ocorrenciaRepository.save(ocorrencia);
        return uriBuilder.path("/ocorrencia/{id}").buildAndExpand(ocorrencia.getId()).toUri();
    }

    public void update(UUID id, Ocorrencia ocorrenciaAtualizada) {
        Ocorrencia ocorrencia = this.findById(id);
        this.ocorrenciaRepository.save(this.retornaOcorrenciaAposAtualizacao(ocorrenciaAtualizada, ocorrencia));
    }

    private Ocorrencia retornaOcorrenciaAposAtualizacao(Ocorrencia ocorrenciaAtualizada, Ocorrencia ocorrencia) {
        if(!ocorrenciaAtualizada.getTitulo().isEmpty() && ocorrenciaAtualizada.getTitulo() != null) {
            ocorrencia.setTitulo(ocorrenciaAtualizada.getTitulo());
        }

        if(!ocorrenciaAtualizada.getObservacoes().isEmpty() && ocorrenciaAtualizada.getObservacoes() != null) {
            ocorrencia.setObservacoes(ocorrenciaAtualizada.getObservacoes());
        }

        if(ocorrenciaAtualizada.getDataOcorrencia() != null && ocorrenciaAtualizada.getDataOcorrencia().compareTo(ocorrencia.getDataOcorrencia()) != 0) {
            ocorrencia.setDataOcorrencia(ocorrenciaAtualizada.getDataOcorrencia());
        }

        if(!ocorrenciaAtualizada.getSafra().getId().toString().isEmpty() && ocorrenciaAtualizada.getSafra().getId().toString() != null &&
                ocorrenciaAtualizada.getSafra().getId().compareTo(ocorrencia.getSafra().getId()) != 0) {
            Safra safra = this.safraService.findById(ocorrenciaAtualizada.getSafra().getId());
            ocorrencia.setSafra(safra);
        }

        if(!ocorrenciaAtualizada.getTalhao().getId().toString().isEmpty() && ocorrenciaAtualizada.getTalhao().getId().toString() != null &&
                ocorrenciaAtualizada.getTalhao().getId().compareTo(ocorrencia.getTalhao().getId()) != 0) {
            Talhao talhao = this.talhaoService.findById(ocorrenciaAtualizada.getTalhao().getId());
            ocorrencia.setTalhao(talhao);
        }

        return ocorrencia;
    }

    public void delete(UUID id) {
        try {
            this.ocorrenciaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
