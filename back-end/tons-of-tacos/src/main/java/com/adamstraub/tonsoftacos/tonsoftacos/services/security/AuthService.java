package com.adamstraub.tonsoftacos.tonsoftacos.services.security;
import com.adamstraub.tonsoftacos.tonsoftacos.dto.ownersDto.security.OwnerAuthDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


//service pertains to authentication functions(login, logout, session timeout etc.)
@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final AuthenticationManager authenticationManager;
        @Override
    public String ownerLogin(OwnerAuthDto ownerAuthDto) {
        System.out.println("auth service");
//        move auth out to controller, get that gate up
//            try decrypt here
            System.out.println(ownerAuthDto.getUsername());
            System.out.println(decrypt(ownerAuthDto.getUsername()));
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(decrypt(ownerAuthDto.getUsername()),

//                .authenticate(new UsernamePasswordAuthenticationToken(ownerAuthDto.getUsername(),
                        ownerAuthDto.getPsswrd()));
        if (authentication.isAuthenticated()) {
        return jwtService.generateToken(ownerAuthDto.getUsername());
    }else {
            throw new UsernameNotFoundException("invalid user request");
        }
    }

    private String decrypt(String  encodedString) {
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
