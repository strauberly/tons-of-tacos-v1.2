package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.authTests;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.services.security.JwtService;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport.OwnersToolsTestsSupport;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import io.jsonwebtoken.impl.crypto.JwtSignatureValidator;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.springframework.util.Base64Utils.encode;

public class LoginTest implements JwtSignatureValidator {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean isValid(String s, String s1) {
        return false;
    }

    @Nested
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @TestPropertySource("classpath:test-application.properties")
    @Sql(scripts = {
            "classpath:/test-schema.sql",
            "classpath:/test-data.sql",
    },
            config = @SqlConfig(encoding = "utf-8"))
    class testThatDoesNotPolluteTheApplicationContextUris extends OwnersToolsTestsSupport {

        @Autowired
        JwtService jwtService;

        @Autowired
        UserDetailsService userDetailsService;

//        @Value("${key}")
//        private String SECRET;

//        code altered for encryption 18 Apr 2023 and test no longer valid
//        @Test
//        void userCredentialsValidAndReturnValidWebToken200() throws Exception {
//
////                Given: a valid combination of owner username and password
//            String body = validCredentials();
//            System.out.println(body);
//
////                When: connecting to the login endpoint
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            String uri = getBaseUriForOwnersLogin();
//            HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
//            ResponseEntity<String> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
//                    String.class);
//
////                Then: a response status code of 200 is received
//            System.out.println(("Response code is " + response.getStatusCode() + "."));
//            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//
////                And: a valid web token is generated and returned
//
////            split the token, get the subject from payload
//            String[] tokenSegments = Objects.requireNonNull(response.getBody()).split("\\.");
//            Base64.Decoder decoder = Base64.getUrlDecoder();
//            String payload = new String(decoder.decode(tokenSegments[1]));
//            String sub = payload.substring(8, 15);
////            String sub = payload.substring(8, 15);
////            validate username and check if token is past expiration
//            UserDetails userDetails = userDetailsService.loadUserByUsername(sub);
//            Assertions.assertTrue(jwtService.isTokenValid(response.getBody(), userDetails));
//            System.out.println("token valid: " + jwtService.isTokenValid(response.getBody(), userDetails));
//
//        }

        @Test
        void encryptedUserCredentialsValidAndReturnValidWebToken200() throws Exception {

//                Given: a valid combination of owner username and password
            String body = encryptedCredentials();
            System.out.println(body);

//                When: connecting to the login endpoint
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String uri = getBaseUriForOwnersLogin();
            HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
                    String.class);

//                Then: a response status code of 200 is received
            System.out.println(("Response code is " + response.getStatusCode() + "."));
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

//                And: a valid web token is generated and returned

//            split the token, get the subject from payload
            String[] tokenSegments = Objects.requireNonNull(response.getBody()).split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(tokenSegments[1]));
            System.out.println("sub value: " + payload);
            System.out.println("extract user: " + jwtService.extractUsername(response.getBody()));
            System.out.println(jwtService.decrypt(jwtService.extractUsername(response.getBody())));
//            byte[] subBytes = payload.getBytes(StandardCharsets.UTF_8);
//            int decodedSubByte;
//            List<Character> decodedSubBytes = new ArrayList<>();
//            System.out.println(Arrays.toString(subBytes));
//            for (Byte subByte: subBytes){
//                decodedSubByte = subByte;
//                decodedSubByte -= 3;
//                decodedSubBytes.add((char) decodedSubByte);
//            }
//            System.out.println(decodedSubBytes);
//            String sub = payload.substring(8, 15);
//            validate username and check if token is past expiration
            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.decrypt(jwtService.extractUsername(response.getBody())));
            Assertions.assertTrue(jwtService.isTokenValid(response.getBody(), userDetails));
            System.out.println("token valid: " + jwtService.isTokenValid(response.getBody(), userDetails));

        }
    }
}



