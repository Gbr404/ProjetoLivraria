package com.curso.config;


import com.curso.services.ClienteDetailsServiceImpl;
import com.curso.security.JWTAuthenticationFilter;
import com.curso.security.JWTAuthorizationFilter;
import com.curso.security.JWTUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private static final String[] PUBLIC_URLS = {
        "/h2-console/**",
        "/auth/**",
        "/login",
        "/swagger-ui/**",
        "/v3/api-docs/**"
    };    
    
    private final Environment env;
    private final JWTUtils jwtUtils;
    private final ClienteDetailsServiceImpl clienteDetailsService;

    // Construtor com injeção de dependência
    public SecurityConfig(Environment env, JWTUtils jwtUtils, ClienteDetailsServiceImpl clienteDetailsService) {
        this.env = env;
        this.jwtUtils = jwtUtils;
        this.clienteDetailsService = clienteDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        AuthenticationManager authManager = authenticationManager(http);

        // Desabilita a configuração de cabeçalhos para o perfil "test"
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        }

        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configura CORS
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Define que a aplicação não usará sessões
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(PUBLIC_URLS).permitAll() // Permite acesso sem autenticação às URLs públicas
                        .anyRequest().authenticated()) // Exige autenticação para todas as outras URLs
                .csrf(csrf -> csrf.disable()); // Desabilita CSRF (não necessário para APIs REST com JWT)

        // Adiciona o filtro de autenticação do JWT
        http.addFilterBefore(new JWTAuthenticationFilter(jwtUtils, clienteDetailsService),
                UsernamePasswordAuthenticationFilter.class);

        // Adiciona o filtro de autorização do JWT
        http.addFilterBefore(new JWTAuthorizationFilter(authManager, jwtUtils, clienteDetailsService),
                UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // Permite todas as origens
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Permite os métodos especificados
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Permite os cabeçalhos especificados

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica a configuração para todas as rotas
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(clienteDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Usa o algoritmo BCrypt para criptografar senhas
    }
}