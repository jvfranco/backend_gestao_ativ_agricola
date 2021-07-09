package com.gestao.agricola.service;

import com.gestao.agricola.model.Propriedade;
import com.gestao.agricola.model.UnidadeDeMedida;
import com.gestao.agricola.model.Usuario;
import com.gestao.agricola.model.dto.PropriedadeDTO;
import com.gestao.agricola.model.form.PropriedadeForm;
import com.gestao.agricola.repository.PropriedadeRepository;
import com.gestao.agricola.repository.UnidadeDeMedidaRepository;
import com.gestao.agricola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Service
public class PropriedadeService {

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UnidadeDeMedidaRepository unidadeDeMedidaRepository;


    public Page<PropriedadeDTO> findAll(Pageable paginacao) {
        return this.propriedadeRepository.findAll(paginacao).map(PropriedadeDTO::new);
    }

    public Optional<PropriedadeDTO> findById(UUID id) {
        return this.propriedadeRepository.findById(id).map(PropriedadeDTO::new);
    }

    public Optional<PropriedadeDTO> update(UUID id, PropriedadeForm propriedadeForm) {
        return this.propriedadeRepository.findById(id)
                .map(prop -> {
                    Propriedade propSalva = this.propriedadeRepository.save(this.retornaPropriedadeAtualizada(propriedadeForm, prop));
                    return new PropriedadeDTO(propSalva);
                });
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
        Usuario proprietario = null;

        if (!propriedadeForm.getIdProprietario().isEmpty() && propriedadeForm.getIdProprietario() != null) {
            proprietario = usuarioRepository.findById(UUID.fromString(propriedadeForm.getIdProprietario()))
                    .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

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

        if(!propriedadeForm.getIdUnidadeMedidaArea().isEmpty() && propriedadeForm.getIdUnidadeMedidaArea() != null) {
            UnidadeDeMedida unidade = this.unidadeDeMedidaRepository.findById(UUID.fromString(propriedadeForm.getIdUnidadeMedidaArea())).get();
            propriedade.setUnidadeDeMedida(unidade);
        }

        return propriedade;
    }
}
