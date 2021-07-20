package com.gestao.agricola.controller;

import com.gestao.agricola.model.AptAtividadeAgricola;
import com.gestao.agricola.model.form.AptAtividadeAgricolaForm;
import com.gestao.agricola.service.AptAtividadeAgricolaService;
import com.gestao.agricola.service.PropriedadeService;
import com.gestao.agricola.service.SafraService;
import com.gestao.agricola.service.TalhaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/apt-atividade")
public class AptAtividadeAgricolaController {

    @Autowired
    private AptAtividadeAgricolaService aptAtividadeAgricolaService;

    @Autowired
    private PropriedadeService propriedadeService;

    @Autowired
    private SafraService safraService;

    @Autowired
    private TalhaoService talhaoService;

    @PostMapping()
    public ResponseEntity<AptAtividadeAgricola> salvarAptAtivAgricola(@RequestBody @Valid AptAtividadeAgricolaForm apontamentoForm, UriComponentsBuilder uriBuilder) {
        AptAtividadeAgricola apontamento = apontamentoForm.converte(this.propriedadeService, this.talhaoService, this.safraService);
        URI uri = this.aptAtividadeAgricolaService.save(apontamento, uriBuilder);
        return ResponseEntity.created(uri).body(apontamento);
    }
}
