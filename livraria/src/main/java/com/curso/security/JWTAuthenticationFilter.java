package com.curso.security;


import com.curso.services.ClienteDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;
    private final ClienteDetailsServiceImpl userDetailsService;

    // Construtor com injeção de dependência
    public JWTAuthenticationFilter(JWTUtils jwtUtils, ClienteDetailsServiceImpl userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request); // Obtém o token da requisição
        if (token != null && jwtUtils.isTokenValid(token)) { // Verifica a validade do token
            String username = jwtUtils.getUsername(token); // Obtém o username (email) do token
            var userDetails = userDetailsService.loadUserByUsername(username); // Carrega os detalhes do usuário
            var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); // Cria a autenticação
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Define os detalhes da requisição
            SecurityContextHolder.getContext().setAuthentication(auth); // Coloca a autenticação no contexto de segurança
        }
        chain.doFilter(request, response); // Continua o filtro
    }

    // Método para extrair o token da requisição
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization"); // Obtém o cabeçalho "Authorization"
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) { // Verifica se o token começa com "Bearer"
            return bearerToken.substring(7); // Retorna o token sem o prefixo "Bearer "
        }
        return null; // Retorna null caso o token não esteja presente ou seja inválido
    }
}
