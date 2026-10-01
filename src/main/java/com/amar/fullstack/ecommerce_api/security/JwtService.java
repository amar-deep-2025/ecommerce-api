package com.amar.fullstack.ecommerce_api.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET;

    public String generateToken(String email,String role, String tokenType) {
        return JWT.create()
                .withSubject(email)
                .withClaim("role",role)
                .withClaim("type", tokenType)//add role
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
                .sign(Algorithm.HMAC256(SECRET));
    }
}
