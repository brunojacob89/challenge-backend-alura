package br.com.alura.challenge.config.security;

import java.util.Date;

import br.com.alura.challenge.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${videos.jwt.expiration}")
    private String expiration;

    @Value("${videos.jwt.secret}")
    private String secret;


    public String gerarToken(Authentication authenticate) {

        Usuario logado = (Usuario) authenticate.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API de Videos")
                .setSubject(logado.getId().toString()) //dono do token
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao) //Duracao 1 dia como colocado na application.properties
                .signWith(SignatureAlgorithm.HS256, secret) //algoritmo de segurança e senha
                .compact();
    }


    public boolean isTokenValido(String token) {
        try {

            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;

        } catch (Exception e) {
            return false;
        }

    }


    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();

        return Long.parseLong(claims.getSubject());
    }
}