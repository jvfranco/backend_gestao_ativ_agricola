package com.gestao.agricola.controller;

import com.gestao.agricola.model.Talhao;
import com.gestao.agricola.model.dto.TalhaoDTO;
import com.gestao.agricola.model.form.TalhaoForm;
import com.gestao.agricola.repository.PropriedadeRepository;
import com.gestao.agricola.repository.UnidadeDeMedidaRepository;
import com.gestao.agricola.service.TalhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/talhao")
public class TalhaoController {

    @Autowired
    private TalhaoService talhaoService;

    @Autowired
    private PropriedadeRepository propriedadeRepository;

    @Autowired
    private UnidadeDeMedidaRepository unidadeDeMedidaRepository;

    @GetMapping("/todos")
    public ResponseEntity<Page<TalhaoDTO>> retornarTodosTalhoes(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<TalhaoDTO> pageTalhoes = this.talhaoService.findAll(paginacao);

        return ResponseEntity.ok(pageTalhoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TalhaoDTO> retornarTalhaoDetalhado(@PathVariable String id) {
        TalhaoDTO talhaoDTO = this.talhaoService.findById(UUID.fromString(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Talhão não encontrado"));

        return ResponseEntity.ok(talhaoDTO);
    }

    //TODO criptografar senha
    @PostMapping()
    public ResponseEntity<TalhaoDTO> salvarNovoTalhao(@RequestBody @Valid TalhaoForm talhaoForm, UriComponentsBuilder uriBuilder) {
        Talhao talhao = talhaoForm.converter(this.propriedadeRepository, this.unidadeDeMedidaRepository);
        URI uri = this.talhaoService.save(talhao, uriBuilder);

        return ResponseEntity.created(uri).body(new TalhaoDTO(talhao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTalhao(@PathVariable String id, @RequestBody @Valid TalhaoForm talhaoForm) {
        this.talhaoService.update(UUID.fromString(id), talhaoForm)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Talhão não encontrado"));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTalhao(@PathVariable String id) {
        this.talhaoService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
