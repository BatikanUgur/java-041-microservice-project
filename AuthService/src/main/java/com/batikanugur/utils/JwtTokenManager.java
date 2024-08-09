package com.batikanugur.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {
/*
    String secretKey = "Ankara06";
    String issuer = "abc";
    Long expireDate = 1000L * 60 * 2; // 2 dakika

 */
    @Value("${authservice.secret.key}")
    String secretKey ;
    @Value("${authservice.issuer}")
    String issuer;
    @Value("${authservice.expire.date}")
    Long expireDate;

    public Optional<String> createToken (Long id)
    {
        String token = "";
    try {
        token = JWT.create().withAudience()
                .withClaim("id", id)
                .withClaim("serviceName", "AuthService")
                .withIssuer(issuer) //oluşturan yapı kurum
                .withIssuedAt(new Date())// tokenın oluşturulma zamanı
                .withExpiresAt(new Date(System.currentTimeMillis() * expireDate))
                .sign(Algorithm.HMAC512(secretKey));

        return Optional.of(token);
        }catch (Exception e){
        e.printStackTrace();
        }
        return Optional.empty();
    }

    public Boolean verifyToken (String token) {
       try {

           Algorithm algorithm = Algorithm.HMAC512(secretKey);
           JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();

           DecodedJWT decodedJWT = verifier.verify(token);

           if(decodedJWT == null){
               return false;
           }

       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
       return true;
    }

    public Optional<Long> getIdInfoFromToken(String token){
        try {

            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();

            DecodedJWT decodedJWT = verifier.verify(token);

            if(decodedJWT == null){
                return Optional.empty();
            }

            Long id = decodedJWT.getClaim("id").asLong();
            System.out.println("Tokendaki id değeri: " + id);

            String serviceName = decodedJWT.getClaim("serviceName").asString();
            System.out.println("Tokendaki serviceName değeri: " + id);

            return Optional.of(id);

        }catch (Exception e){
            e.printStackTrace();
            return Optional.empty();
        }

    }

}


