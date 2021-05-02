package com.gestao.agricola.controller;

import com.gestao.agricola.entity.dto.PropriedadeDTO;
import com.gestao.agricola.service.PropriedadeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/propriedade")
public class PropriedadesController {

    private PropriedadeService propriedadeService;

    @GetMapping("/todos")
    public ResponseEntity<Page<PropriedadeDTO>> retornarTodosUsuarios(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<PropriedadeDTO> pagePropriedades = this.propriedadeService.findAll(paginacao);

        return ResponseEntity.ok().body(pagePropriedades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropriedadeDTO> retornarPropriedadeDetalhada(@PathVariable UUID id) {
        PropriedadeDTO propriedadeDTO = this.propriedadeService.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Propriedade não encontrada"));

        return ResponseEntity.ok().body(propriedadeDTO);
    }

    //TODO terminar métodos CRUD
}
