package com.vetturno.controller;

import com.vetturno.dto.VeterinarioDTO;
import com.vetturno.dto.VeterinarioRequest;
import com.vetturno.service.VeterinarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    public VeterinarioController(VeterinarioService veterinarioService) {
        this.veterinarioService = veterinarioService;
    }

    @PostMapping
    public ResponseEntity<VeterinarioDTO> crear(
            @Valid @RequestBody VeterinarioRequest request) {

        VeterinarioDTO veterinario = veterinarioService.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(veterinario);
    }

    @GetMapping
    public ResponseEntity<List<VeterinarioDTO>> listar() {

        return ResponseEntity.ok(veterinarioService.listar());
    }
}