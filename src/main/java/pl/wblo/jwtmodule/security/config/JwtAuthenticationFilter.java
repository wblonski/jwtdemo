package pl.wblo.jwtmodule.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.wblo.jwtmodule.service.JwtService;
import pl.wblo.jwtmodule.util.MyLogger;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService){
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        MyLogger.debug("JwtAuthenticationFilter.doFilterInternal is running...");
        // jeśli to połączenie do rejestracji użytkownika to przepuść dalej
        if (request.getServletPath().contains("/api/v1/auth/register")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Jeśi autoryzacja bez "Bearer" to przepuść dalej
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // autoryzacja JWT
        // pobierz z tokena userEmail za pomoca jwtService
        final String jwt;
        final String userEmail;
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);
        // zweryfikuj z grubsza token, czy dotyczy tego użytkownika, więc czy zawiera jego userEmail
        // i sprawdź, że brak kontekstu uwierzytelnionej sesji
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // to pobierz pełne informacje o użytkoniku: haslo, czy nie zablokowany itd.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // jeszcze raz (dokładnie) zweryfikuj token
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // utwórz nowy token z pełnymi danymi userDetails i uprawnieniami
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                // dodaj do nowego tokena dane z requesta: IPaddress, sessionId
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
