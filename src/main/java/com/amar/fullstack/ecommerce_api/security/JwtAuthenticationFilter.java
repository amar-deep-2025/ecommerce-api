package com.amar.fullstack.ecommerce_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String SECRET="mysecretkey123";

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader=req.getHeader("Authorization");
        System.out.println("Header "+authHeader);
        System.out.println("Request "+req.getMethod());
        if (authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(req,res);
            return;
        }
        String token=authHeader.substring(7);
        String email= JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getSubject();

        String role=JWT.require(Algorithm.HMAC256(SECRET))
                .build()
                .verify(token)
                .getClaim("role").asString();

        if (email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
           List<GrantedAuthority> authorityList= List.of(new SimpleGrantedAuthority("ROLE_"+role));
            UsernamePasswordAuthenticationToken authToken=
                    new UsernamePasswordAuthenticationToken(email,null,authorityList);

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(req,res);

    }
}
