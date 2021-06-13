package com.gestao.agricola.controller;

import com.gestao.agricola.model.Cultura;
import com.gestao.agricola.service.CulturaService;
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
@RequestMapping("/cultura")
public class CulturaController {

    @Autowired
    private CulturaService culturaService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Cultura>> retornarTodasCultura(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Cultura> pageCulturas = this.culturaService.findAll(paginacao);
        return ResponseEntity.ok(pageCulturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cultura> retornarCulturaDetalhada(@PathVariable String id) {
        Cultura cultura = this.culturaService.findById(UUID.fromString(id));
        return ResponseEntity.ok(cultura);
    }

    @PostMapping()
    public ResponseEntity<Cultura> salvarNovaCultura(@RequestBody @Valid Cultura cultura, UriComponentsBuilder uriBuilder) {
        URI uri = this.culturaService.save(cultura, uriBuilder);
        return ResponseEntity.created(uri).body(cultura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCultura(@PathVariable String id, @RequestBody Cultura cultura) {
        this.culturaService.update(UUID.fromString(id), cultura);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirCultura(@PathVariable String id) {
        this.culturaService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
