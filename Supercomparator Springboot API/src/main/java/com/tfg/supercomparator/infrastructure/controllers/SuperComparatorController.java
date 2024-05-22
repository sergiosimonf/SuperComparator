package com.tfg.supercomparator.infrastructure.controllers;

import com.tfg.supercomparator.domain.utils.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/")
@AllArgsConstructor
public class SuperComparatorController {

    @GetMapping("")
    public ResponseEntity<Message> testConexion() {
        return ResponseEntity.ok(new Message("Conexion Exitosa"));
    }
}
