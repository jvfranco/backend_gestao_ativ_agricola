package com.gestao.agricola.service;

import com.gestao.agricola.entity.Propriedade;
import com.gestao.agricola.entity.Talhao;
import com.gestao.agricola.entity.Usuario;
import com.gestao.agricola.entity.dto.PropriedadeDTO;
import com.gestao.agricola.entity.form.PropriedadeForm;
import com.gestao.agricola.repository.PropriedadeRepository;
import com.gestao.agricola.repository.TalhaoRepository;
import com.gestao.agricola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public Optional<Propriedade> update(UUID id, PropriedadeForm propriedadeForm, UsuarioRepository usuarioRepository, TalhaoRepository talhaoRepository) {
        Usuario usuario = usuarioRepository.findById(UUID.fromString(propriedadeForm.getIdProprietario()))
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        List<Talhao> talhoes = propriedadeForm.getIdTalhoes()
                .stream()
                .map(idTal -> {
                    return talhaoRepository.findById(UUID.fromString(idTal)).get();
                }).collect(Collectors.toList());

        return this.propriedadeRepository.findById(id)
                .map(prop -> {
                   prop.setNome(propriedadeForm.getNome());
                   prop.setProprietario(usuario);
                   prop.setArea(propriedadeForm.getArea());
                   prop.setTalhoes(talhoes);
                   prop.setDataAtualizacao(LocalDate.now());
                   return this.propriedadeRepository.save(prop);
                });
    }

    public boolean delete(UUID id) {
        this.propriedadeRepository.findById(id)
                .map(propriedade -> {
                    this.propriedadeRepository.delete(propriedade);
                    return true;
                });
        return false;
    }

    public URI save(Propriedade propriedade, UriComponentsBuilder uriBuilder) {
        this.propriedadeRepository.save(propriedade);
        return uriBuilder.path("/usuario/{id}").buildAndExpand(propriedade.getId()).toUri();
    }
}
