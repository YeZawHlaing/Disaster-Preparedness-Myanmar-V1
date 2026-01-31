package com.backend.v1.common.middleware;

import com.backend.v1.common.utils.JwtUtils;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        var authHeader = request.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.split(" ")[1].trim();
            try {
                var claims = JwtUtils.validateJwtToken(token);
                var email = claims.get("email", String.class);
                var role = claims.get("role", String.class);

                var authenticationToken = new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.commaSeparatedStringToAuthorityList(role));


                SecurityContextHolder.getContext().setAuthentication(authenticationToken);



            } catch (JwtException e) {
                System.out.println(e.getMessage());
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(e.getMessage());
                return;
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            }
            finally {
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(request.getRequestURI().endsWith("/hello")){
            System.out.println(request.getRequestURI());
            return false;
        }
        return true;
    }


}
