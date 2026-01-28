package br.com.icarolins.finance.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuth extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserDetailsService detailsService;

    public JwtAuth(JwtUtil jwtUtil, UserDetailsService detailsService) {
        this.jwtUtil = jwtUtil;
        this.detailsService = detailsService;
    }

    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        // 1. Verifica se existe o Header
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);

            try {
                username = jwtUtil.extractUsername(jwtToken);
            } catch (Exception e) {
                System.out.println("Token inválido: " + e.getMessage());
            }
        }

        // 2. Se encontrou username e não existe autenticação ainda
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = detailsService.loadUserByUsername(username);

            System.out.println("Authorities do usuário: " + userDetails.getAuthorities());


            if (jwtUtil.isTokenValid(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 3. Registra a autenticação no contexto
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 4. Segue adiante
        filterChain.doFilter(request, response);
    }
}
