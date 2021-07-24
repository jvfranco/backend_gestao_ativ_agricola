package com.gestao.agricola.controller;

import com.gestao.agricola.model.Ocorrencia;
import com.gestao.agricola.model.form.OcorrenciaForm;
import com.gestao.agricola.service.OcorrenciaService;
import com.gestao.agricola.service.PropriedadeService;
import com.gestao.agricola.service.SafraService;
import com.gestao.agricola.service.TalhaoService;
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
@RequestMapping("/ocorrencia")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaService ocorrenciaService;

    @Autowired
    private PropriedadeService propriedadeService;

    @Autowired
    private TalhaoService talhaoService;

    @Autowired
    private SafraService safraService;

    @GetMapping("/todos")
    public ResponseEntity<Page<Ocorrencia>> retornarTodasOcorrencias(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<Ocorrencia> pageOcorrencias = this.ocorrenciaService.findAll(paginacao);
        return ResponseEntity.ok(pageOcorrencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ocorrencia> retornarOcorrenciaDetalhada(@PathVariable String id) {
        Ocorrencia ocorrencia = this.ocorrenciaService.findById(UUID.fromString(id));
        return ResponseEntity.ok(ocorrencia);
    }

    @PostMapping()
    public ResponseEntity<Ocorrencia> salvarNovaOcorrencia(@RequestBody @Valid OcorrenciaForm ocorrenciaForm, UriComponentsBuilder uriBuilder) {
        Ocorrencia ocorrencia = ocorrenciaForm.converte(this.propriedadeService, this.talhaoService, this.safraService);
        URI uri = this.ocorrenciaService.save(ocorrencia, uriBuilder);
        return ResponseEntity.created(uri).body(ocorrencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarMOcorrencia(@PathVariable String id, @RequestBody Ocorrencia ocorrencia) {
        this.ocorrenciaService.update(UUID.fromString(id), ocorrencia);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirMarca(@PathVariable String id) {
        this.ocorrenciaService.delete(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
