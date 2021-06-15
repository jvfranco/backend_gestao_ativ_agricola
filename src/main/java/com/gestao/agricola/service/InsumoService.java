package com.gestao.agricola.service;

import com.gestao.agricola.model.Insumo;
import com.gestao.agricola.model.UnidadeDeMedida;
import com.gestao.agricola.repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class InsumoService {

    @Autowired
    private InsumoRepository insumoRepository;

    @Autowired
    private UnidadeDeMedidaService unidadeDeMedidaService;

    public Page<Insumo> findAll(Pageable paginacao) {
        return this.insumoRepository.findAll(paginacao);
    }

    public Insumo findById(UUID id) {
        return this.insumoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Insumo não encontrado!"));
    }

    public URI save(Insumo insumo, UriComponentsBuilder uriBuilder) {
        this.insumoRepository.save(insumo);
        return uriBuilder.path("/insumo/{id}").buildAndExpand(insumo.getId()).toUri();
    }

    public void update(UUID id, Insumo insumoAtualizado) {
        Insumo insumo = this.findById(id);
        this.insumoRepository.save(this.retornaUnidadeAposAtualizacao(insumoAtualizado, insumo));
    }

    private Insumo retornaUnidadeAposAtualizacao(Insumo insumoAtualizado, Insumo insumo) {
        if(!insumoAtualizado.getIdentificacao().isEmpty() && insumoAtualizado.getIdentificacao() != null) {
            insumo.setIdentificacao(insumoAtualizado.getIdentificacao());
        }

        if(!insumoAtualizado.getIngredientesAtivos().isEmpty() && insumoAtualizado.getIngredientesAtivos() != null) {
            insumo.setIngredientesAtivos(insumoAtualizado.getIngredientesAtivos());
        }

        if(!insumoAtualizado.getAplicacao().isEmpty() && insumoAtualizado.getAplicacao() != null) {
            insumo.setIngredientesAtivos(insumoAtualizado.getIngredientesAtivos());
        }

        if(!insumoAtualizado.getFormulacao().isEmpty() && insumoAtualizado.getFormulacao() != null) {
            insumo.setFormulacao(insumoAtualizado.getFormulacao());
        }

        if(!insumoAtualizado.getClasseAgronomica().isEmpty() && insumoAtualizado.getClasseAgronomica() != null) {
            insumo.setClasseAgronomica(insumoAtualizado.getClasseAgronomica());
        }

        if(!insumoAtualizado.getModoDeAcao().isEmpty() && insumoAtualizado.getModoDeAcao() != null) {
            insumo.setModoDeAcao(insumoAtualizado.getModoDeAcao());
        }

        if(insumoAtualizado.getQuantidade().compareTo(BigDecimal.ZERO) > 0) {
            insumo.setQuantidade(insumoAtualizado.getQuantidade());
        }

        if(!insumoAtualizado.getUnidadeDeMedida().getId().toString().isEmpty() && insumoAtualizado.getUnidadeDeMedida().getId().toString() != null) {
            UnidadeDeMedida unidade = this.unidadeDeMedidaService.findById(insumoAtualizado.getUnidadeDeMedida().getId());
            insumo.setUnidadeDeMedida(unidade);
        }

        insumo.setDataAtualizacao(LocalDate.now());

        return insumo;
    }

    public void delete(UUID id) {
        try {
            this.insumoRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
