package com.curso.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JWTUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    // Construtor que recebe AuthenticationManager, JWTUtils e UserDetailsService
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtils jwtUtils,
                                  UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        // Verifica se o cabeçalho Authorization está presente e se começa com "Bearer "
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            // Extrai o token e autentica o usuário
            UsernamePasswordAuthenticationToken authToken = getAuthentication(header.substring(7));
            if (authToken != null) {
                SecurityContextHolder.getContext().setAuthentication(authToken); // Define o token de autenticação
            }
        }
        chain.doFilter(request, response); // Continua a execução do filtro
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jwtUtils.isTokenValid(token)) {
            String userName = jwtUtils.getUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName); // Carrega os detalhes do usuário
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); // Retorna o token de autenticação
        }
        return null; // Retorna null se o token for inválido
    }
}

