package com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security;
import com.adamstraub.tonsoftacos.tonsoftacos.dao.OwnerRepository;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security.AuthenticationRequest;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security.AuthenticationResponse;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security.OwnerAuthDto;
import com.adamstraub.tonsoftacos.tonsoftacos.services.ownersServices.security.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



//possibly need to annotate as component
@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {
    @Autowired
    private final OwnerRepository ownerRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse ownerLogin(AuthenticationRequest request) {
        System.out.println("service");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPsswrd()

                )
        );
        var user = ownerRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
//    @Value("${key}")
//    private String SECRET;
//    @Override
//    public String ownerLogin(AuthenticationRequest request) {
//        System.out.println("service");
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),
//                        request.getPsswrd()));
//        if (authentication.isAuthenticated()) {
//        return jwtService.generateToken(request.getUsername());
//    }else {
//            throw new UsernameNotFoundException("invalid user request");
//        }
    }
//
//    //create token
//    private Key getSignKey(){
//        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    private String createToken(Map<String, Object> claims, String username){
//        String token = Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis()+ (((1000 * 60) * 60) * 16)))
//                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
//        System.out.println(token);
//        return token;
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
////        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
//        return (username.equals(userDetails.getUsername()));
//    }
//}
