package com.gestao.agricola.controller;

import com.gestao.agricola.model.Hibrido;
import com.gestao.agricola.model.dto.CulturaDTO;
import com.gestao.agricola.model.form.HibridoForm;
import com.gestao.agricola.service.HibridoService;
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
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hibrido")
public class HibridoController {

    @Autowired
    private HibridoService hibridoService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Hibrido>> retornarTodasHibridos(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Hibrido> pageCultivares = this.hibridoService.findAll(paginacao);
        return ResponseEntity.ok(pageCultivares);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hibrido> retornarHibridoDetalhado(@PathVariable String id) {
        Hibrido hibrido = this.hibridoService.findById(UUID.fromString(id));
        return ResponseEntity.ok(hibrido);
    }

    @GetMapping("/culturas")
    public ResponseEntity<List<CulturaDTO>> retornarCulturasDTO() {
        List<CulturaDTO> culturas = this.hibridoService.retornarCulturasDTO();
        return ResponseEntity.ok(culturas);
    }

    @PostMapping()
    public ResponseEntity<HibridoForm> salvarNovoHibrido(@RequestBody @Valid HibridoForm hibridoForm, UriComponentsBuilder uriBuilder) {
        URI uri = this.hibridoService.save(hibridoForm, uriBuilder);
        return ResponseEntity.created(uri).body(hibridoForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarHibrido(@PathVariable String id, @RequestBody HibridoForm hibridoForm) {
        Hibrido hibrido = this.hibridoService.converteFormEmEntity(hibridoForm);
        this.hibridoService.update(UUID.fromString(id), hibrido);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirHibrido(@PathVariable String id) {
        this.hibridoService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
