package com.adamstraub.tonsoftacos.tonsoftacos.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
@Service
public class JwtService {

    //create token
    @Value("${key}")
    private String SECRET;

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        System.out.println(Arrays.toString(keyBytes));
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private String buildToken(String username){
//    private String buildToken(Map<String, Object> claims, String username){
        String token = Jwts.builder()
//                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis() * 1000))
                .setExpiration(new Date((System.currentTimeMillis() * 1000) + (1000000L * 60 * 60 * 16)))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        System.out.println(token);
        return token;
    }


    public String generateToken(String username){
//        Map<String, Object> claims = new HashMap<>();
        return buildToken(username);
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
        System.out.println(extractClaim(token, Claims::getSubject));
//        System.out.println(decrypt(extractClaim(token, Claims::getSubject)));
        return extractClaim(token, Claims::getSubject);
//    return decrypt(extractClaim(token, Claims::getSubject));
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

//    possibly condense into one
    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
        final String username = decrypt(extractUsername(token));
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
    }
//
//    //    encrypt
//    private String encrypt(String string) {
////        string = "george castillo";
////        System.out.println(string);
////        convert string to byte array
////        >> convert byte array to int array and increase the value of each element by three
//        System.out.println(string);
//
//        byte[] codeBytes = string.getBytes(StandardCharsets.UTF_8);
//        List<Integer> rolledCodeBytes = new ArrayList<>();
//        int codeByteValue;
//        System.out.println(Arrays.toString(codeBytes));
//        for (byte codeByte : codeBytes) {
//            codeByteValue = codeByte;
//            codeByteValue += 3;
//            rolledCodeBytes.add(codeByteValue);
////          new collection with altered char values
//        }
//        System.out.println("rolled bytes: " + rolledCodeBytes);
//        List<Character> chars = new ArrayList<>();
////        String encodedString = new String(rolledCodeBytes, StandardCharsets.UTF_8);
//        for (int integer : rolledCodeBytes) {
//            chars.add((char) integer);
//        }
////       convert chars to string
//        System.out.println("updated chars: " + chars);
//        StringBuilder rolledCharBuilder = new StringBuilder(chars.size());
//        for (Character ch : chars) {
//            rolledCharBuilder.append(ch);
//        }
//        System.out.println("rolled charbuilder: " + rolledCharBuilder);
////      for each element insert three new random chars
//        for (int i = 0; i < chars.size(); i++) {
//            chars.add(i, randomChar());
//            i++;
//            chars.add(i, randomChar());
//            i++;
//            chars.add(i, randomChar());
//            i++;
//        }
//        chars.add(randomChar());
//        chars.add(randomChar());
//        chars.add(randomChar());
//        System.out.println(Arrays.toString(chars.toArray()));
////        -----------------------------------
//        StringBuilder encryptionBuilder = new StringBuilder(chars.size());
//        for (Character ch : chars) {
//            encryptionBuilder.append(ch);
//        }
//        System.out.println("encrypted string: " + encryptionBuilder);
//        return encryptionBuilder.toString();
//    }
//
    //    decrypt
    public String decrypt(String  encodedString) {
//        String encodedString = encrypt(string);
//        System.out.println(string);
//    public String decrypt(String  string) {
//        String encodedString = encrypt(string);
////        System.out.println(string);
//        System.out.println(encodedString);
        String decodedStart = String.valueOf(encodedString.charAt(3));
        String decodedEnd = String.valueOf(encodedString.charAt(encodedString.length() - 4));
        String wholeDecoded = "";
        StringBuilder decoded = new StringBuilder();
        for (int i = 3; i < encodedString.length(); i = i + 4) {
            decoded.append(encodedString.charAt(i));
        }
        decoded = new StringBuilder(decoded.substring(1, decoded.toString().length() - 1));
        wholeDecoded = wholeDecoded.concat(decodedStart + decoded + decodedEnd);
//        System.out.println(decodedStart);
//        System.out.println(decodedEnd);
//        System.out.println(decoded);
//        System.out.println(wholeDecoded);
        byte[] decodedBytes = wholeDecoded.getBytes(StandardCharsets.UTF_8);
        int decodeByteValue;
        List<Character> decodedChars = new ArrayList<>();
        StringBuilder decrypt = new StringBuilder(0);
        for (byte codeByte : decodedBytes) {
            decodeByteValue = codeByte;
            decodeByteValue -= 3;
            decodedChars.add((char) decodeByteValue);

//            System.out.println(decodedChars);

//            for (Character ch : decodedChars) {
//                decrypt.append(ch);
//            }
        }
        for (Character ch : decodedChars) {
            decrypt.append(ch);
        }
//        System.out.println(decrypt);
        return decrypt.toString();
    }

    private char randomChar() {
//        int min = 33, max = 126;
//        int random = min + (int) (Math.random() * ((max - min)) + 1);
////            System.out.println(random);
//        return (char) random;
        int min = 33, max = 126;
        int random = min + (int) (Math.random() * ((max - min)) + 1);
        int[] excluded = {34, 92, 39};
//        boolean isExcludedValue = false;
        char choice = 0;
        for (int ex : excluded) {
            choice = random == ex ? randomChar() : (char) random;
//            if (random == ex) {
//                isExcludedValue = true;
//            }

//            isExcludedValue = true ? choice == randomChar() : choice == (char) random;
//        if(isExcludedValue){
//            return randomChar();
//        }
//        System.out.println(random);
//        return (char) random;
        }
        return choice;
    }
}
