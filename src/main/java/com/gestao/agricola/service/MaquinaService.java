package com.gestao.agricola.service;

import com.gestao.agricola.model.Combustivel;
import com.gestao.agricola.model.Maquina;
import com.gestao.agricola.model.Marca;
import com.gestao.agricola.repository.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Service
public class MaquinaService {

    @Autowired
    private MaquinaRepository maquinaRepository;

    @Autowired
    private MarcaService marcaService;

    public Page<Maquina> findAll(Pageable paginacao) {
        return this.maquinaRepository.findAll(paginacao);
    }

    public Maquina findById(UUID id) {
        return this.maquinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Maquina não encontrada!"));
    }

    public URI save(Maquina maquina, UriComponentsBuilder uriBuilder) {
        this.maquinaRepository.save(maquina);
        return uriBuilder.path("/maquina/{id}").buildAndExpand(maquina.getId()).toUri();
    }

    public void update(UUID id, Maquina maquinaAtualizada) {
        Maquina maquina = this.findById(id);
        this.maquinaRepository.save(this.retornaUnidadeAposAtualizacao(maquinaAtualizada, maquina));
    }

    private Maquina retornaUnidadeAposAtualizacao(Maquina maquinaAtualizada, Maquina maquina) {
        if(!maquinaAtualizada.getModelo().isEmpty() && maquinaAtualizada.getModelo() != null) {
            maquina.setModelo(maquinaAtualizada.getModelo());
        }

        if(!maquinaAtualizada.getMarca().getId().toString().isEmpty() && maquinaAtualizada.getMarca().getId().toString() != null &&
                maquinaAtualizada.getMarca().getId().compareTo(maquina.getId()) != 0) {
            Marca marca = this.marcaService.findById(maquinaAtualizada.getMarca().getId());
            maquina.setMarca(marca);
        }

        if(!maquinaAtualizada.getHorimetro().isEmpty() && maquinaAtualizada.getHorimetro() != null) {
            maquina.setHorimetro(maquinaAtualizada.getHorimetro());
        }

        if(!maquinaAtualizada.getCombustivel().toString().isEmpty() && maquinaAtualizada.getCombustivel().toString() != null &&
                maquinaAtualizada.getCombustivel().toString().compareTo(maquina.getCombustivel().toString()) != 0) {
            switch (maquinaAtualizada.getCombustivel())  {
                case ETANOL:
                    maquina.setCombustivel(Combustivel.ETANOL);
                    break;
                case ELETRICO:
                    maquina.setCombustivel(Combustivel.ELETRICO);
                    break;
                case GASOLINA:
                    maquina.setCombustivel(Combustivel.GASOLINA);
                    break;
                case OLEO_DIESEL:
                    maquina.setCombustivel(Combustivel.OLEO_DIESEL);
                    break;
            }
        }

        if(maquinaAtualizada.getPotencia() > 0) {
            maquina.setPotencia(maquinaAtualizada.getPotencia());
        }

        return maquina;
    }

    public void delete(UUID id) {
        try {
            this.maquinaRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
