package com.gestao.agricola.model.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Setter
@Getter
public class LoginForm {

    private String login;
    private String senha;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }
}
