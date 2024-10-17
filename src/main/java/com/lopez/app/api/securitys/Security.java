package com.lopez.app.api.securitys;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configuración de seguridad
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().authenticated() // Todas las solicitudes requieren autenticación
                )
                .httpBasic(withDefaults()) // Habilita Basic Authentication
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint((request, response, authException) -> handleAuthError(response)) // Manejo de error personalizado
                )
                .csrf(csrf -> csrf.disable()); // Deshabilitar CSRF para facilitar pruebas

        return http.build();
    }

    private void handleAuthError(HttpServletResponse response) throws IOException {
        // Configura la respuesta cuando hay error de autenticación
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Código de estado 401
        response.setContentType("application/json");
        response.getWriter().write("{\"mensaje\": \"Error: Autenticación requerida o credenciales incorrectas\"}");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Definir usuarios en memoria con roles
        var user1 = User.withUsername("admin")
                .password("{noop}12345") // {noop} indica que no estamos usando encriptación
                .roles("ADMIN")
                .build();

        var user2 = User.withUsername("user")
                .password("{noop}1234")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }


}
