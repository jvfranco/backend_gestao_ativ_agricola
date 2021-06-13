package com.gestao.agricola.controller;

import com.gestao.agricola.model.Cultivar;
import com.gestao.agricola.service.CultivarService;
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
@RequestMapping("/cultivar")
public class CultivarController {

    @Autowired
    private CultivarService cultivarService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Cultivar>> retornarTodasCultivares(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Cultivar> pageCultivares = this.cultivarService.findAll(paginacao);
        return ResponseEntity.ok(pageCultivares);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cultivar> retornarCultivarDetalhada(@PathVariable String id) {
        Cultivar cultivar = this.cultivarService.findById(UUID.fromString(id));
        return ResponseEntity.ok(cultivar);
    }

    @PostMapping()
    public ResponseEntity<Cultivar> salvarNovaCultivar(@RequestBody @Valid Cultivar cultivar, UriComponentsBuilder uriBuilder) {
        URI uri = this.cultivarService.save(cultivar, uriBuilder);
        return ResponseEntity.created(uri).body(cultivar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCultivar(@PathVariable String id, @RequestBody Cultivar cultivar) {
        this.cultivarService.update(UUID.fromString(id), cultivar);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirCultura(@PathVariable String id) {
        this.cultivarService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
