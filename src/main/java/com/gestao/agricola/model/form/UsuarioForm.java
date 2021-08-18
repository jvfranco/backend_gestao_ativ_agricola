package com.gestao.agricola.model.form;

import com.gestao.agricola.model.Perfil;
import com.gestao.agricola.model.Pessoa;
import com.gestao.agricola.model.Usuario;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class UsuarioForm {

    @NotNull @NotEmpty
    private List<Perfil> perfis;

    @NotNull @NotEmpty
    private String usuario;

    @NotNull @NotEmpty
    private String senha;

    @NotNull @NotEmpty
    private Pessoa pessoa;

    public Usuario converter() {
        Usuario usuario = Usuario.builder()
                .perfis(this.perfis)
                .usuario(this.usuario)
                .senha(new BCryptPasswordEncoder().encode(this.senha))
                .pessoa(this.pessoa)
                .build();
        return usuario;
    }
}
