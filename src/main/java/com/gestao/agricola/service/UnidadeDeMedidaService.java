package com.gestao.agricola.service;

import com.gestao.agricola.model.UnidadeDeMedida;
import com.gestao.agricola.repository.UnidadeDeMedidaRepository;
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

@Service
public class UnidadeDeMedidaService {

    @Autowired
    private UnidadeDeMedidaRepository unidadeDeMedidaRepository;

    public Page<UnidadeDeMedida> findAll(Pageable paginacao) {
        return this.unidadeDeMedidaRepository.findAll(paginacao);
    }

    public UnidadeDeMedida findById(UUID id) {
        return this.unidadeDeMedidaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Unidade de medida não encontrada!"));
    }

    public URI save(UnidadeDeMedida unidade, UriComponentsBuilder uriBuilder) {
        this.unidadeDeMedidaRepository.save(unidade);
        return uriBuilder.path("/unidadeDeMedida/{id}").buildAndExpand(unidade.getId()).toUri();
    }

    public void update(UUID id, UnidadeDeMedida unidadeAtualizada) {
        UnidadeDeMedida unidade = this.findById(id);
        this.unidadeDeMedidaRepository.save(this.retornaUnidadeAposAtualizacao(unidadeAtualizada, unidade));
    }

    private UnidadeDeMedida retornaUnidadeAposAtualizacao(UnidadeDeMedida unidadeAtualizada, UnidadeDeMedida unidade) {
        if(!unidadeAtualizada.getAbreviacao().isEmpty() && unidadeAtualizada.getAbreviacao() != null) {
            unidade.setAbreviacao(unidadeAtualizada.getAbreviacao());
        }

        if(!unidadeAtualizada.getDescricao().isEmpty() && unidadeAtualizada.getDescricao() != null) {
            unidade.setDescricao(unidadeAtualizada.getDescricao());
        }

        return unidade;
    }

    public void delete(UUID id) {
        try {
            this.unidadeDeMedidaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<UnidadeDeMedida> retornarTodasUnidadesSemPaginacao() {
        return this.unidadeDeMedidaRepository.findAll();
    }
}
