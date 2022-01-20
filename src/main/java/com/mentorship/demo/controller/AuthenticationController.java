package com.mentorship.demo.controller;

import com.mentorship.demo.dao.AbiturientDao;
import com.mentorship.demo.dto.AuthRequestDto;
import com.mentorship.demo.model.Abiturient;
import com.mentorship.demo.security.AuthException;
import com.mentorship.demo.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/auth")
@Slf4j
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtTokenProvider provider;
    @Autowired
    private AbiturientDao abiturientDao;

    @PostMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity login(@RequestBody AuthRequestDto requestDto) {
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getFirstName(), requestDto.getPassword()));
            Abiturient abiturient = abiturientDao.findAbiturientByFirstName(requestDto.getFirstName());
            if (abiturient == null) {
                throw new UsernameNotFoundException("User with username not found");
            }
            String token = provider.createToken(requestDto.getFirstName());
//            String token = provider.createToken(requestDto.getFirstName(), Arrays.asList(abiturient.getRole()));
            log.info("token: ${token}");
            Map<Object, Object> response = new HashMap<>();
            response.put("username", requestDto.getFirstName());
            response.put("password", requestDto.getPassword());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException e) {
            throw new AuthException("Bad credentials ");
        }

    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity test() {
        Map<Object, Object> response = new HashMap<>();
        response.put("Test", "SUCCESS");
        return ResponseEntity.ok(response);
    }
}
