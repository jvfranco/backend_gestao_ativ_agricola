package com.gestao.agricola.controller;

import com.gestao.agricola.model.Talhao;
import com.gestao.agricola.model.dto.TalhaoDTO;
import com.gestao.agricola.model.form.TalhaoForm;
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

    @GetMapping("/todos")
    public ResponseEntity<Page<TalhaoDTO>> retornarTodosTalhoes(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<TalhaoDTO> pageTalhoes = this.talhaoService.findAll(paginacao);

        return ResponseEntity.ok().body(pageTalhoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TalhaoDTO> retornarTalhaoDetalhado(@PathVariable UUID id) {
        TalhaoDTO talhaoDTO = this.talhaoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Talhão não encontrado"));

        return ResponseEntity.ok().body(talhaoDTO);
    }

    //TODO criptografar senha
    @PostMapping()
    public ResponseEntity<TalhaoDTO> salvarNovoTalhao(@RequestBody @Valid TalhaoForm TalhaoForm, UriComponentsBuilder uriBuilder) {
        Talhao Talhao = TalhaoForm.converter(TalhaoForm);
        URI uri = this.talhaoService.save(Talhao, uriBuilder);

        return ResponseEntity.created(uri).body(new TalhaoDTO(Talhao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTalhao(@PathVariable UUID id, @RequestBody @Valid TalhaoForm talhaoForm) {
        this.talhaoService.update(id, talhaoForm)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Talhão não encontrado"));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTalhao(@PathVariable UUID id) {
        return this.talhaoService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
