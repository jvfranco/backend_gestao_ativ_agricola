package com.gestao.agricola.service;

import com.gestao.agricola.model.Cultura;
import com.gestao.agricola.model.Hibrido;
import com.gestao.agricola.model.dto.CulturaDTO;
import com.gestao.agricola.model.form.HibridoForm;
import com.gestao.agricola.repository.HibridoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class HibridoService {

    @Autowired
    private HibridoRepository hibridoRepository;

    @Autowired
    private CulturaService culturaService;

    public Page<Hibrido> findAll(Pageable paginacao) {
        return this.hibridoRepository.findAll(paginacao);
    }

    public Hibrido findById(UUID id) {
        return this.hibridoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cultivar não encontrada!"));
    }

    public URI save(HibridoForm hibridoForm, UriComponentsBuilder uriBuilder) {
        Hibrido hibrido = this.converteFormEmEntity(hibridoForm);
        this.hibridoRepository.save(hibrido);
        return uriBuilder.path("/cultivar/{id}").buildAndExpand(hibrido.getId()).toUri();
    }

    public Hibrido converteFormEmEntity(HibridoForm hibridoForm) {
        Cultura cultura = new Cultura();

        if(Objects.nonNull(hibridoForm.getIdCultura())){
            cultura = this.culturaService.findById(UUID.fromString(hibridoForm.getIdCultura()));
        }

        return Hibrido.builder()
                .identificacao(hibridoForm.getIdentificacao())
                .cultura(cultura)
                .ciclo(hibridoForm.getCiclo())
                .observacoes(hibridoForm.getObservacoes())
                .build();
    }

    public void update(UUID id, Hibrido hibridoAtualizada) {
        Hibrido hibrido = this.findById(id);
        this.hibridoRepository.save(this.retornaCultivarAposAtualizacao(hibridoAtualizada, hibrido));
    }

    private Hibrido retornaCultivarAposAtualizacao(Hibrido hibridoAtualizada, Hibrido hibrido) {
        if(!hibridoAtualizada.getIdentificacao().isEmpty() && hibridoAtualizada.getIdentificacao() != null) {
            hibrido.setIdentificacao(hibridoAtualizada.getIdentificacao());
        }

        if(hibridoAtualizada.getCiclo() != null && hibridoAtualizada.getCiclo() != hibrido.getCiclo()) {
            hibrido.setCiclo(hibridoAtualizada.getCiclo());
        }

        if(hibridoAtualizada.getCultura() != null && hibridoAtualizada.getCultura().getId() != hibrido.getCultura().getId()){
            Cultura cultura = this.culturaService.findById(hibridoAtualizada.getCultura().getId());
            hibrido.setCultura(cultura);
        }

        if(!hibridoAtualizada.getObservacoes().isEmpty() && hibridoAtualizada.getObservacoes() != null) {
            hibrido.setObservacoes(hibridoAtualizada.getObservacoes());
        }

        hibrido.setDataAtualizacao(LocalDate.now());

        return hibrido;
    }

    public void delete(UUID id) {
        try {
            this.hibridoRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<CulturaDTO> retornarCulturasDTO() {
        return this.culturaService.retornaCulturasDTO();
    }
}
