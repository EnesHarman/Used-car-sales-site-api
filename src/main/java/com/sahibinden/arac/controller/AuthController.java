package com.sahibinden.arac.controller;

import com.sahibinden.arac.core.result.Result;
import com.sahibinden.arac.dto.requests.CustomerRegisterRequest;
import com.sahibinden.arac.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CustomerRegisterRequest customerRegisterRequest){
        Result result = authService.register(customerRegisterRequest);
        if(result.getSuccess()){
            return ResponseEntity.ok(result.getMessage());
        }
        return ResponseEntity.badRequest().body(result.getMessage());
    }
}
