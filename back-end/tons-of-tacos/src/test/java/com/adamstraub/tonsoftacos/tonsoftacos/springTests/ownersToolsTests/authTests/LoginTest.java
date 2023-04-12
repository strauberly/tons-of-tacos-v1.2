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

        @Value("${key}")
        private String SECRET;

        @Test
        void userCredentialsValidAndReturnValidWebToken200() throws Exception {

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

            String sub = payload.substring(8, 15);
            String iat = payload.substring(23, 36);
            String exp = payload.substring(43, 56);

            Long longiat = Long.parseLong(iat);
            Long longexp = Long.parseLong(exp);


            UserDetails userDetails = userDetailsService.loadUserByUsername(sub);
            System.out.println(jwtService.isTokenValid(response.getBody(), userDetails));

            long yourmilliseconds = Long.parseLong(iat);
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
            Date resultdate = new Date(yourmilliseconds);
            System.out.println(sdf.format(resultdate));

            String token = Jwts.builder()
//                        .setClaims(Map<String, Object> claims)
                    .setSubject(sub)
                    .setIssuedAt(new Date(longiat))
                    .setExpiration(new Date(longexp))
//                    .setIssuedAt(new Date(Long.parseLong(iat)))
//                    .setExpiration(new Date(Long.parseLong(exp)))
                    .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
            System.out.println("new token: " + token);
            System.out.println("old token: " + response.getBody());

            System.out.println(sub);
            System.out.println(iat);
            System.out.println(exp);
            System.out.println(Long.parseLong(exp) - Long.parseLong(iat));
            System.out.println(new Date(Long.parseLong(iat)));
            System.out.println(new Date(Long.parseLong(exp)));
            String noSigToken = header + "." + payload;

            byte[] hash = SECRET.getBytes(StandardCharsets.UTF_8);
            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(hash, "HmacSHA256");
            sha256Hmac.init(secretKeySpec);

            byte[] signedBytes = sha256Hmac.doFinal(noSigToken.getBytes(StandardCharsets.UTF_8));
//            byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//            Keys.hmacShaKeyFor(keyBytes);


            System.out.println("new: " + tokenSegments[2].equals(hmacSha256(tokenSegments[0] + "." + tokenSegments[1], getSignKey())));


//                Algorithm algorithm = Algorithm.HMAC256(SECRET);
//
//                JWTVerifier verifier = JWT.require(algorithm)


//                String algorithm = SignatureAlgorithm.HS256.getJcaName();
            String algorithm = "HmacSHA256";
            SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(key);
//                String noSigToken = header +  "." + payload;
            String signatureCreated =
                    Base64.getEncoder().withoutPadding().encodeToString(mac.doFinal(noSigToken.getBytes()));

            Algorithm alg = Algorithm.HMAC256(SECRET.getBytes());
            DecodedJWT decodedJWT = JWT.decode(response.getBody());
            System.out.println(decodedJWT.toString());

//
//                SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//                SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET.getBytes(), signatureAlgorithm.getJcaName());
//
//                DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(signatureAlgorithm, secretKeySpec);
//                if (!validator.isValid(noSigToken, tokenSegments[2])){
//                    throw new Exception("token buggered");
//                }
//                                        Arrays.toString(mac.doFinal(noSigToken.getBytes()));

            byte[] byteArray = signatureCreated.getBytes();
            Charset charset = StandardCharsets.UTF_8;
            String string = new String(byteArray, charset);
            System.out.println(string);

            System.out.println(Arrays.toString(byteArray));
            System.out.println(signatureCreated);
            System.out.println(tokenSegments[2]);
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

        private String hmacSha256(String data) throws NoSuchAlgorithmException, InvalidKeyException {

            byte[] hash = Decoders.

//                byte[] hash = secret.getBytes(StandardCharsets.UTF_8);
                Mac sha256Hmac = Mac.getInstance("HmacSHA256");
                SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
                sha256Hmac.init(secretKey);

                byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
                return encode(signedBytes);
        }
        private static String encode(byte[] bytes) {
            return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
        }

        private Key getSignKey(){
            byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        System.out.println(Arrays.toString(keyBytes));
//        System.out.println(Keys.hmacShaKeyFor(keyBytes));
            return Keys.hmacShaKeyFor(keyBytes);
        }
        private String buildToken(java.util.Map<String, Object> claims, String username){
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(new Date(System.currentTimeMillis() * 1000))
                    .setExpiration(new Date((System.currentTimeMillis() * 1000) + (1000000L * 60 * 60 * 16)))
                    .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
            System.out.println(token);
            return token;
        }


        public String generateToken(String username){
            Map<String, Object> claims = new HashMap<>();
            return buildToken(claims, username);
        }
    }
}
