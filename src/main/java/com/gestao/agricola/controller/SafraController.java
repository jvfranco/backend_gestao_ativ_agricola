package com.gestao.agricola.controller;

import com.gestao.agricola.model.Safra;
import com.gestao.agricola.service.SafraService;
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
@RequestMapping("/safra")
public class SafraController {

    @Autowired
    private SafraService safraService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Safra>> retornarTodasSafra(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Safra> pageSafras = this.safraService.findAll(paginacao);
        return ResponseEntity.ok(pageSafras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Safra> retornarSafraDetalhada(@PathVariable String id) {
        Safra safra = this.safraService.findById(UUID.fromString(id));
        return ResponseEntity.ok(safra);
    }

    @PostMapping()
    public ResponseEntity<Safra> salvarNovaSafra(@RequestBody @Valid Safra safra, UriComponentsBuilder uriBuilder) {
        URI uri = this.safraService.save(safra, uriBuilder);
        return ResponseEntity.created(uri).body(safra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarSafra(@PathVariable String id, @RequestBody Safra safra) {
        this.safraService.update(UUID.fromString(id), safra);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirSafra(@PathVariable String id) {
        this.safraService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
