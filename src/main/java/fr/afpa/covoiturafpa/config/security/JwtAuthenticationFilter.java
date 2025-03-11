package fr.afpa.covoiturafpa.config.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.afpa.covoiturafpa.services.authentication.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Classe "Record" représentant un message d'erreur à renvoyer au client en cas de problème
     * 
     * TODO faire mieux, on mettrait pas un truc du genre partout ?
     */
    public record Error(String type, String message) {
    }

    public JwtAuthenticationFilter(
        JwtService jwtService,
        UserDetailsService userDetailsService
        // TODO voir ce truc
        // HandlerExceptionResolver handlerExceptionResolver
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        // this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (userEmail != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {

            Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
            logger.debug(exception.getMessage());

            // c'est nous sommes ici c'est qu'il eu un problème avec le JWT
            // Peut être dû un JWT mal formé ?
            // Un problème lors du chargement de l'utilisateur ?

            // Dans tous les cas on considère que'il s'agit d'un problème d'autorisation et on adapte le code de retour en fonction
            // 500 INTERNAL SERVER ERROR est adaptée puisque nous ne savons pas clairement d'où peut provenir l'ereur
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setHeader("Content-Type", "application/json");

            // création d'un Json de retour contenant l'erreur
            ObjectMapper mapper = new ObjectMapper();
            ServletOutputStream output = response.getOutputStream();
            mapper.writeValue(output, new Error("Erreur", "Erreur lors de l'accès à l'API"));
            
            // TODO réactiver ça ?
            // handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}