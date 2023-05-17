package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.authTests;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.services.security.JwtService;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport.OwnersToolsTestsSupport;

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
import org.springframework.core.ParameterizedTypeReference;
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


        @Test
        void encryptedUserCredentialsValidAndReturnValidWebToken200(){

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
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            System.out.println(("Response code is " + response.getStatusCode() + "."));

//                And: a valid web token is generated and returned

//            split the token, get the subject from payload
            String[] tokenSegments = Objects.requireNonNull(response.getBody()).split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(tokenSegments[1]));

            System.out.println("sub value: " + payload);
            System.out.println("extract user: " + jwtService.extractUsername(response.getBody()));
            System.out.println(jwtService.decrypt(jwtService.extractUsername(response.getBody())));

            System.out.println("payload value: " + payload);
            System.out.println("extract sub: " + jwtService.extractUsername(response.getBody()));
            System.out.println("decrypted user: " + jwtService.decrypt(jwtService.extractUsername(response.getBody())));

            UserDetails userDetails = userDetailsService.loadUserByUsername(jwtService.decrypt(jwtService.extractUsername(response.getBody())));
            Assertions.assertTrue(jwtService.isTokenValid(response.getBody(), userDetails));
            System.out.println(userDetails);
            System.out.println(response.getBody());
            System.out.println("token valid: " + jwtService.isTokenValid(response.getBody(), userDetails));
            System.out.println("Test for successful use case complete.");
        }



        @Test
        void invalidUsernameCredentialsReturns401(){
//            Given: a bad username
            String badUserNameBody = badUsername();
            System.out.println("bad username body: "+ badUserNameBody);

//            When: connection to login endpoint is made
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            String uri = getBaseUriForOwnersLogin();

            HttpEntity<String> httpEntity = new HttpEntity<>(badUserNameBody, header);
            ResponseEntity<Map<String, Object>> response = getRestTemplate().exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>() {
            });

//            Then: status code of 401 is returned
            Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
            System.out.println(("Response code is " + response.getStatusCode() + "."));

//            And: the error message contains
            Map<String, Object> error = response.getBody();
            System.out.println(error);
            assert error != null;
            Assertions.assertEquals(error.get("status code").toString().substring(0,3), HttpStatus.UNAUTHORIZED.toString().substring(0,3));
            Assertions.assertTrue(error.containsValue("/api/owners-tools/login"));
            Assertions.assertTrue(error.containsKey("message"));
            Assertions.assertTrue(error.containsKey("timestamp"));
            System.out.println("Test for unsuccessful use case complete.");

        }
    }
}



