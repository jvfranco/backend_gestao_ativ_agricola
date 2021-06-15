package com.gestao.agricola.controller;

import com.gestao.agricola.model.AtividadeAgricola;
import com.gestao.agricola.service.AtivAgricolaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/ativ_agricola")
public class AtivAgricolaController {

    @Autowired
    private AtivAgricolaService ativAgricolaService;

    @GetMapping("/todos")
    public ResponseEntity<Page<AtividadeAgricola>> retornarTodasAtividadesAgricolas(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<AtividadeAgricola> pageAtividadesAgricolas = this.ativAgricolaService.findAll(paginacao);
        return ResponseEntity.ok(pageAtividadesAgricolas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtividadeAgricola> retornarAtividadeAgricolaDetalhada(@PathVariable String id) {
        AtividadeAgricola atividadeAgricola = this.ativAgricolaService.findById(UUID.fromString(id));
        return ResponseEntity.ok(atividadeAgricola);
    }

    @PostMapping()
    public ResponseEntity<AtividadeAgricola> salvarNovaAtividadeAgricola(@RequestBody @Valid AtividadeAgricola atividadeAgricola, UriComponentsBuilder uriBuilder) {
        URI uri = this.ativAgricolaService.save(atividadeAgricola, uriBuilder);
        return ResponseEntity.created(uri).body(atividadeAgricola);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAtividadeAgricola(@PathVariable String id, @RequestBody AtividadeAgricola atividadeAgricola) {
        this.ativAgricolaService.update(UUID.fromString(id), atividadeAgricola);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirAtividadeAgricola(@PathVariable String id) {
        this.ativAgricolaService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
