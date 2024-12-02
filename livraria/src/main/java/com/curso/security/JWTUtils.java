package com.curso.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    // Método para gerar o token
    public String generateToken(String email) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes()); // Gera a chave usando o segredo
        String tokenGerado = Jwts.builder()
                .setSubject(email) // Usando o email como 'subject'
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // Definindo o tempo de expiração
                .signWith(SignatureAlgorithm.HS512, key) // Algoritmo de assinatura
                .compact();
        return "Bearer " + tokenGerado; // Prefixo Bearer, conforme padrão do OAuth2
    }

    // Método para verificar se o token é válido
    public boolean isTokenValid(String token) {
        Claims claims = getClaims(token); // Obtém os detalhes do token
        if (claims != null) {
            String email = claims.getSubject(); // Obtém o email do subject
            Date expirationDate = claims.getExpiration(); // Obtém a data de expiração
            Date now = new Date(System.currentTimeMillis()); // Data atual
            return email != null && expirationDate != null && now.before(expirationDate); // Verifica validade
        }
        return false;
    }

    // Método para obter o username (email) do token
    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject(); // Retorna o 'subject', que é o email
        }
        return null;
    }

    // Método privado para extrair os Claims do token
    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret.getBytes()) 
                    .parseClaimsJws(token) 
                    .getBody(); // Retorna os claims (informações do token)
        } catch (Exception e) {
            return null; // Retorna null caso o token seja inválido ou mal formatado
        }
    }
}

