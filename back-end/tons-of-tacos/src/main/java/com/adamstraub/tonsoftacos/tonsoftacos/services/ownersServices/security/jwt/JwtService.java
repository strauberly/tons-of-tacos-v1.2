package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Service
public class JwtService {
    @Value("${key}")
    private String SECRET;
    //create token
    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
// here we use user details where before we used username and assumed we passed the authdto
//    private String createToken(Map<String, Object> extraClaims, UserDetails userDetails){
//        return Jwts.builder()
//                .setClaims(extraClaims)
//                .setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+ (((1000 * 60) * 60) * 16)))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//                .compact();
////        System.out.println(token);
////        return token;
//    }

    private String createToken(Map<String, Object> claims, String username){
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ (((1000 * 60) * 60) * 16)))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        System.out.println(token);
        return token;
    }

//    public String generateToken(UserDetails userDetails){
//        return createToken(new HashMap<>(), userDetails);
//    }


    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

//    validate token

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
        return (username.equals(userDetails.getUsername()));
    }
}
