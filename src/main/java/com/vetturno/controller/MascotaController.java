package com.vetturno.controller;

import com.vetturno.dto.MascotaDTO;
import com.vetturno.dto.MascotaRequest;
import com.vetturno.service.MascotaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @PostMapping
    public ResponseEntity<MascotaDTO> crear(
            @Valid @RequestBody MascotaRequest request) {

        MascotaDTO mascota = mascotaService.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mascota);
    }

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> listar() {

        return ResponseEntity.ok(mascotaService.listar());
    }
}