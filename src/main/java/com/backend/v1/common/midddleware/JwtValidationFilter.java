package com.backend.v1.common.midddleware;

import com.backend.v1.common.constant.JwtConstants;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class JwtValidationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(JwtConstants.JWT_SECRET_KEY);

        if(Objects.isNull(jwt)) {
            filterChain.doFilter(request, response);
        }

            try {
                Environment environment = getEnvironment();
                if(environment != null ) {
                    String secretKey = environment.getProperty(JwtConstants.JWT_SECRET_KEY, JwtConstants.JWT_SECRET_KEY_DEFAULT_VALUE);
                    SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
                    var claims = Jwts.parserBuilder()
                                            .setSigningKey(key)
                                            .build()
                                            .parseClaimsJwt(jwt)
                                            .getBody();
                    String email = claims.get("email", String.class);
                    String authorities = claims.get("authorities", String.class);


                    Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                    SecurityContextHolder.getContext().setAuthentication(authentication);


                }
            }
            catch (ExpiredJwtException e){
                throw new BadCredentialsException(e.getMessage());
            }
            catch (Exception e) {
                throw new BadCredentialsException("Invalid token received");
            }
        }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return super.shouldNotFilter(request);
    }
}
