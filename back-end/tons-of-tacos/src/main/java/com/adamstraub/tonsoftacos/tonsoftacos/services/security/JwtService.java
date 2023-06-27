package com.adamstraub.tonsoftacos.tonsoftacos.services.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SignatureException;
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

    @Value("${BEGIN_KEY}")
    private int BEGIN_KEY;
    @Value("${END_KEY}")
    private int END_KEY;

    @Value("${charmin}")
    private int charmin;

    @Value("${charmax}")
    private int charmax;

    @Value("${submin}")
    private int submin;

    @Value("${submax}")
    private int submax;

    @Value("${ex1}")
    private int ex1;
    @Value("${ex2}")
    private int ex2;
    @Value("${ex3}")
    private int ex3;

    @Value("${CHARSET}")
    private String CHARSET;

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private String buildToken(String username){
//        set time variable instead of creating new
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
//                16 hours, reflective of our owners work day - to be altered to facilitate mitigation of token capture
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60) * 16))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        System.out.println(token);
        System.out.println("token issued: " + new Date(System.currentTimeMillis()));
        System.out.println("token expires: " + new Date(System.currentTimeMillis() + (1000 * 60 * 60) * 16));
//        try {
//            return token;
//        }catch (io.jsonwebtoken.security.SignatureException exception){
//            System.out.println(exception.getLocalizedMessage());
//        }
        return token;
    }


    public String generateToken(String username){
        return buildToken(username);
    }

//    validate token

    private Claims extractAllClaims(String token){
        try {
            return
                    Jwts
                            .parserBuilder()
                            .setSigningKey(getSignKey())
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw new JwtException(e.getLocalizedMessage());
        }
//        return
//                Jwts
//                        .parserBuilder()
//                        .setSigningKey(getSignKey())
//                        .build()
//                        .parseClaimsJws(token)
//                        .getBody();
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
    public Date extractIssuedAt(String token){
        return extractClaim(token, Claims::getIssuedAt);
    }
    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

//    possibly condense into one
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = decrypt(extractUsername(token));
//        if (!username.equals(userDetails.getUsername()) && isTokenExpired(token)){
//            throw new SignatureException("token no good");
//        }
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

//    public void validateToken(String token, UserDetails userDetails){
//        final String username = extractUsername(token);
//        userDetails.getUsername();
//    }

    public void validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        userDetails.getUsername();
    }

//  encrypt - used during development as a means to encrypt credentials
//  before storing them and facilitating decryption means

    public String encrypt(String string){
//        rework

        System.out.println("value to be encrypted: " + string);

        byte[] codeBytes = string.getBytes(StandardCharsets.UTF_8);
        List<Integer> rolledCodeBytes = new ArrayList<>();
        int codeByteValue;
//        System.out.println("begin key: " + BEGIN_KEY);
//        System.out.println("code bytes " + Arrays.toString(codeBytes));
        for (byte codeByte : codeBytes) {
            codeByteValue = codeByte;
//            System.out.println(codeByteValue += BEGIN_KEY);
//            System.out.println("key " + BEGIN_KEY);
            codeByteValue += BEGIN_KEY;
            rolledCodeBytes.add(codeByteValue);
        }
//        System.out.println("rolled code bytes: " + rolledCodeBytes);
//      new collection with altered char values
        List<Character> chars = new ArrayList<>();
        for (int integer : rolledCodeBytes) {
            chars.add((char) integer);
        }
//      convert chars to string
//        StringBuilder rolledCharBuilder = new StringBuilder(chars.size());
//        for (Character ch : chars) {
//            rolledCharBuilder.append(ch);
//        }
//        System.out.println("chars: " + chars);
//        System.out.println("rolled charbuilder: " + rolledCharBuilder);
//      for each element insert three new random chars
        for (int i = 0; i < chars.size(); i++) {
            chars.add(i, randomChar());
            i++;
            chars.add(i, randomChar());
            i++;
            chars.add(i, randomChar());
            i++;
        }
        chars.add(randomChar());
        chars.add(randomChar());
        chars.add(randomChar());
//        -----------------------------------
        StringBuilder encryptionBuilder = new StringBuilder(chars.size());
        for (Character ch : chars) {
            encryptionBuilder.append(ch);
        }
        System.out.println("value encrypted: " + encryptionBuilder);
        return encryptionBuilder.toString();
    }

//    decrypt
    public String decrypt(String  encodedString)  {
        System.out.println("encoded: " + encodedString);
        if (encodedString.length() < 21 ){
            throw new BadCredentialsException("Invalid username or password.");
        }
        String decodedStart = String.valueOf(encodedString.charAt(BEGIN_KEY));
        String decodedEnd = String.valueOf(encodedString.charAt(encodedString.length() - END_KEY));
        String wholeDecoded = "";
        StringBuilder decoded = new StringBuilder();
        for (int i = BEGIN_KEY; i < encodedString.length(); i = i + END_KEY) {
            decoded.append(encodedString.charAt(i));
        }
        decoded = new StringBuilder(decoded.substring(submin, decoded.toString().length() - submax));
        wholeDecoded = wholeDecoded.concat(decodedStart + decoded + decodedEnd);
        System.out.println("decoded: " + wholeDecoded);
        byte[] decodedBytes = wholeDecoded.getBytes(StandardCharsets.UTF_8);
        int decodeByteValue;
        List<Character> decodedChars = new ArrayList<>();
        StringBuilder decrypt = new StringBuilder(0);
        for (byte codeByte : decodedBytes) {
            decodeByteValue = codeByte;
            decodeByteValue -= BEGIN_KEY;
            decodedChars.add((char) decodeByteValue);
        }
        for (Character ch : decodedChars) {
            decrypt.append(ch);
        }
        System.out.println("decrypted: " + decrypt);
        return decrypt.toString();
    }

    private char randomChar() {
        int min = charmin, max = charmax;
        int random = (int) (Math.random() * ((max - min)) + min);
        int[] excluded = {ex1, ex2, ex3};
        char choice = 0;
        for (int ex : excluded) {
            choice = random == ex ? randomChar() : (char) random;
        }
        return choice;
    }
}
