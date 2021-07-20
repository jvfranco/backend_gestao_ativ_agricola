package com.gestao.agricola.service;

import com.gestao.agricola.model.Pessoa;
import com.gestao.agricola.model.Propriedade;
import com.gestao.agricola.model.UnidadeDeMedida;
import com.gestao.agricola.model.form.PropriedadeForm;
import com.gestao.agricola.repository.PessoaRepository;
import com.gestao.agricola.repository.PropriedadeRepository;
import com.gestao.agricola.repository.UnidadeDeMedidaRepository;
import com.gestao.agricola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Service
public class PropriedadeService {

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UnidadeDeMedidaRepository unidadeDeMedidaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;


    public Page<Propriedade> findAll(Pageable paginacao) {
        return this.propriedadeRepository.findAll(paginacao);
    }

    public Propriedade findById(UUID id) {
        return this.propriedadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Propriedade não encontrada"));
    }

    public Propriedade update(UUID id, PropriedadeForm propriedadeForm) {
        return this.propriedadeRepository.findById(id)
                .map(prop -> {
                    Propriedade propSalva = this.propriedadeRepository.save(this.retornaPropriedadeAtualizada(propriedadeForm, prop));
                    return propSalva;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Propriedade não encontrada"));
    }

    public void delete(UUID id) {
        Propriedade propriedade = this.propriedadeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Propriedade não encontrada!"));
        this.propriedadeRepository.delete(propriedade);
    }

    public URI save(Propriedade propriedade, UriComponentsBuilder uriBuilder) {
        this.propriedadeRepository.save(propriedade);
        return uriBuilder.path("/usuario/{id}").buildAndExpand(propriedade.getId()).toUri();
    }

    private Propriedade retornaPropriedadeAtualizada(PropriedadeForm propriedadeForm, Propriedade propriedade) {

        if (!propriedadeForm.getIdProprietario().isEmpty() && propriedadeForm.getIdProprietario() != null) {
            Pessoa proprietario = this.pessoaRepository.findById(UUID.fromString(propriedadeForm.getIdProprietario()))
                    .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));

            propriedade.setProprietario(proprietario);
        }

        if (!propriedadeForm.getNome().isEmpty() && propriedadeForm.getNome() != null) {
            propriedade.setNome(propriedadeForm.getNome());
        }

        if (!propriedadeForm.getCoordenadas().isEmpty() && propriedadeForm.getCoordenadas() != null) {
            propriedade.setCoordenadas(propriedadeForm.getCoordenadas());
        }

        if (propriedadeForm.getArea().compareTo(BigDecimal.ZERO) > 0) {
            propriedade.setArea(propriedadeForm.getArea());
        }

        if(!propriedadeForm.getIdUnidade().isEmpty() && propriedadeForm.getIdUnidade() != null) {
            UnidadeDeMedida unidade = this.unidadeDeMedidaRepository.findById(UUID.fromString(propriedadeForm.getIdUnidade())).get();
            propriedade.setUnidadeDeMedida(unidade);
        }

        return propriedade;
    }

    public List<Propriedade> findAll() {
        return this.propriedadeRepository.findAll();
    }
}
