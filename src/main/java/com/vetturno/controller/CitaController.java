package com.vetturno.controller;

import com.vetturno.dto.CitaDTO;
import com.vetturno.dto.CitaRequest;
import com.vetturno.service.CitaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @PostMapping
    public ResponseEntity<CitaDTO> crear(
            @Valid @RequestBody CitaRequest request) {

        CitaDTO cita = citaService.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cita);
    }

    @GetMapping
    public ResponseEntity<List<CitaDTO>> listar() {

        return ResponseEntity.ok(citaService.listar());
    }

    @GetMapping("/veterinario/{id}")
    public ResponseEntity<List<CitaDTO>> listarPorVeterinario(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                citaService.listarPorVeterinario(id)
        );
    }
}