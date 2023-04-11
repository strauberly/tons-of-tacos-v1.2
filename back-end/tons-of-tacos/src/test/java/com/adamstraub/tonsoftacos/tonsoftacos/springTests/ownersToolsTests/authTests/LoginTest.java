package com.adamstraub.tonsoftacos.tonsoftacos.springTests.ownersToolsTests.authTests;

import com.adamstraub.tonsoftacos.tonsoftacos.entities.Orders;
import com.adamstraub.tonsoftacos.tonsoftacos.testSupport.ownersToolsSupport.OwnersToolsTestsSupport;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;

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
        @Value("${key}")
        private String SECRET;
            @Test
            void userCredentialsValidAndReturnValidWebToken200() throws NoSuchAlgorithmException, InvalidKeyException {

//                Given: a valid combination of owner username and password
                String body = validCredentials();
                System.out.println(body);
//                When: connecting to the login endpoint
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                String uri = getBaseUriForOwnersLogin();

                HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
                ResponseEntity<String> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity,
                        String.class);
                System.out.println(("Response code is " + response.getStatusCode() + "."));

//                Then: a response status code of 200 is received
                System.out.println(response.getStatusCode());
                Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//                And: a valid web token is generated and returned
                System.out.println(response.getBody());
                String[] tokenSegments = Objects.requireNonNull(response.getBody()).split("\\.");
                System.out.println(Arrays.toString(tokenSegments));
                Base64.Decoder decoder = Base64.getUrlDecoder();
                String header = new String(decoder.decode(tokenSegments[0]));
                String payload = new String(decoder.decode(tokenSegments[1]));
                System.out.println("header : " + header);
                System.out.println("payload : " + payload);

                String sub = payload.substring(8,15);
                String iat = payload.substring(23,33);
                String exp = payload.substring(40,50);

//                String token = Jwts.builder()
//                        .setSubject(sub)
//                        .setIssuedAt(new Date(iat))
//                        .setExpiration(new Date(exp))
//                        .signWith(jwtSer, SignatureAlgorithm.HS256).compact();
//                System.out.println(token);
//                return token;

                System.out.println(sub);
                System.out.println(iat);
                System.out.println(exp);
                System.out.println(new Date(Integer.parseInt(iat)));
                System.out.println(new Date(Integer.parseInt(exp)));



//                Algorithm algorithm = Algorithm.HMAC256(SECRET);
//
//                JWTVerifier verifier = JWT.require(algorithm)


////                String algorithm = SignatureAlgorithm.HS256.getJcaName();
//                String algorithm = "HmacSHA256";
//                SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), algorithm);
//                Mac mac = Mac.getInstance(algorithm);
//                mac.init(key);
//                String noSigToken = header +  "." + payload;
//                String signatureCreated =
////                        Base64.getUrlEncoder().withoutPadding().encodeToString(mac.doFinal(noSigToken.getBytes()));
//                        Base64.getUrlEncoder().encodeToString(mac.doFinal(noSigToken.getBytes()));
//
////                DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator();
//
//
//                Assertions.assertEquals(signatureCreated, tokenSegments[2]);
////                Assertions.assertEquals();
//
////
//                SignatureAlgorithm sa = SignatureAlgorithm.HS256;
//                SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET.getBytes(), sa.getJcaName());
//                String noSigToken = tokenSegments[0] + "." + tokenSegments[1];
//                String sig = tokenSegments[2];
//
////                System.out.println( Arrays.toString();
//                Jwts.parserBuilder().setSigningKey(SECRET);
////                DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(sa, secretKeySpec);
////
////                System.out.println(validator.isValid(noSigToken, sig));
//




            }
        }
}
