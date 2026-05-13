package com.example.login_auth_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adm")
public class AdmController {
    @GetMapping
    public ResponseEntity<String> getAdm(){
        return ResponseEntity.ok("Sucesso! ");
    }
}
