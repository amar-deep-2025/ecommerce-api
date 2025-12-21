package com.amar.fullstack.ecommerce_api.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET="mysecretkey123";

    public String generateToken(String email){
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
                .sign(Algorithm.HMAC256(SECRET));
    }
}
