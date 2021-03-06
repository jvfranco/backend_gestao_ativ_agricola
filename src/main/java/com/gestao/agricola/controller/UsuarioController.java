package com.gestao.agricola.controller;

import com.gestao.agricola.model.Usuario;
import com.gestao.agricola.model.dto.UsuarioDTO;
import com.gestao.agricola.model.form.UsuarioForm;
import com.gestao.agricola.repository.PerfilRepository;
import com.gestao.agricola.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping("/todos")
    public ResponseEntity<Page<UsuarioDTO>> retornarTodosUsuarios(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        Page<UsuarioDTO> pageUsuarios = this.usuarioService.findAll(paginacao);

        return ResponseEntity.ok(pageUsuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> retornarUsuarioDetalhado(@PathVariable UUID id) {
        UsuarioDTO usuarioDTO = this.usuarioService.findById(id);

        return ResponseEntity.ok(usuarioDTO);
    }

    @PostMapping()
    public ResponseEntity<UsuarioDTO> salvarNovoUsuario(@RequestBody UsuarioForm usuarioForm, UriComponentsBuilder uriBuilder) {
        Usuario usuario = usuarioForm.converter(this.perfilRepository);
        URI uri = this.usuarioService.save(usuario, uriBuilder);

        return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable UUID id, @RequestBody UsuarioForm usuarioForm) {
        this.usuarioService.update(id, usuarioForm);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable UUID id) {
        this.usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
