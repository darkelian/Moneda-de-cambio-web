package com.currencyChange.demo.Security;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.currencyChange.demo.Services.CustomUserDetailsService;

import java.io.IOException;
import javax.servlet.ServletException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    public JwtTokenFilter(JwtConfig jwtConfig, CustomUserDetailsService userDetailsService) {
        this.jwtConfig = jwtConfig;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Extraer el token del header 'Authorization'
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                if (jwtConfig.validateToken(token)) {
                    String username = jwtConfig.getUsernameFromToken(token);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                // En caso de que el token no sea válido, puedes manejarlo aquí.
                // Por ejemplo, puedes enviar una respuesta con un error específico.
            }
        }
        filterChain.doFilter(request, response);
    }
}
