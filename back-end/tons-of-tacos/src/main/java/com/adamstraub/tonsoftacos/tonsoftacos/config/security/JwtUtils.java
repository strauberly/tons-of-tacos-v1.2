//package com.adamstraub.tonsoftacos.tonsoftacos.config.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
////koushik kothagal - published to github 26 0ct 2019
////https://github.com/koushikkothagal/spring-security-jwt/blob/master/src/main/java/io/javabrains/springsecurityjwt/util/JwtUtil.java
//
//// basahota - published to github 20 Jan 2023
////https://github.com/Java-Techie-jt/spring-boot3-jwt/blob/main/src/main/java/com/javatechie/service/JwtService.java
//@Component
//public class JwtUtils {
////    store secret in separated file
//    @Value("${key}")
//    private String SECRET;
////create token
//    private Key getSignKey(){
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    private String createToken(Map<String, Object> claims, String username){
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 16 ))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//    }
//
//    public String generateToken(String username){
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, username);
//    }
//
////    validate token
//
//    private Claims extractAllClaims(String token){
//        return Jwts
//                .parserBuilder()
//                .setSigningKey(getSignKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
//        final Claims claims = extractAllClaims(token);
//        return claimsResolver.apply(claims);
//    }
//    public String extractUsername(String token){
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Date extractExpiration(String token){
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    private Boolean isTokenExpired(String token){
//        return extractExpiration(token).before(new Date());
//    }
//
//    public Boolean validateToken(String token, UserDetails userDetails){
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
//    }
//}
