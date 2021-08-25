package com.gestao.agricola.model.form;

import com.gestao.agricola.model.Perfil;
import com.gestao.agricola.model.Pessoa;
import com.gestao.agricola.model.Usuario;
import com.gestao.agricola.repository.PerfilRepository;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Getter
public class UsuarioForm {

    private String perfil;

    @NotNull @NotEmpty
    private String usuario;

    @NotNull @NotEmpty
    private String senha;

    @NotNull @NotEmpty
    private Pessoa pessoa;

    public Usuario converter(PerfilRepository perfilRepository) {
        Perfil perfil = perfilRepository.findByNome(this.perfil)
                .orElseThrow(() -> new IllegalArgumentException("Perfil nao encontrado"));

        Usuario usuario = Usuario.builder()
                .perfis(Arrays.asList(perfil))
                .usuario(this.usuario)
                .senha(new BCryptPasswordEncoder().encode(this.senha))
                .pessoa(this.pessoa)
                .build();
        return usuario;
    }
}
