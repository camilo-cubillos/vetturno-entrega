package com.vetturno.service;

import com.vetturno.dto.CitaDTO;
import com.vetturno.dto.CitaRequest;
import com.vetturno.model.Cita;
import com.vetturno.model.Mascota;
import com.vetturno.model.Veterinario;
import com.vetturno.repository.CitaRepository;
import com.vetturno.repository.MascotaRepository;
import com.vetturno.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final MascotaRepository mascotaRepository;
    private final VeterinarioRepository veterinarioRepository;

    public CitaService(
            CitaRepository citaRepository,
            MascotaRepository mascotaRepository,
            VeterinarioRepository veterinarioRepository) {

        this.citaRepository = citaRepository;
        this.mascotaRepository = mascotaRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    public CitaDTO crear(CitaRequest request) {

        Mascota mascota = mascotaRepository
                .findById(request.getMascotaId())
                .orElseThrow(() ->
                        new RuntimeException("Mascota no encontrada"));

        Veterinario veterinario = veterinarioRepository
                .findById(request.getVeterinarioId())
                .orElseThrow(() ->
                        new RuntimeException("Veterinario no encontrado"));

        if (request.getFechaHora().isBefore(LocalDateTime.now())) {
            throw new RuntimeException(
                    "La fecha de la cita debe ser futura"
            );
        }

        boolean horarioOcupado =
                citaRepository.existsByVeterinarioIdAndFechaHora(
                        veterinario.getId(),
                        request.getFechaHora()
                );

        if (horarioOcupado) {
            throw new RuntimeException(
                    "El veterinario ya tiene una cita en ese horario"
            );
        }

        Cita cita = new Cita();

        cita.setFechaHora(request.getFechaHora());
        cita.setMotivo(request.getMotivo());
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);

        Cita guardada = citaRepository.save(cita);

        return convertirADTO(guardada);
    }

    public List<CitaDTO> listar() {

        return citaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public List<CitaDTO> listarPorVeterinario(Long veterinarioId) {

        return citaRepository.findByVeterinarioId(veterinarioId)
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    private CitaDTO convertirADTO(Cita cita) {

        CitaDTO dto = new CitaDTO();

        dto.setId(cita.getId());
        dto.setFechaHora(cita.getFechaHora());
        dto.setMotivo(cita.getMotivo());
        dto.setMascota(cita.getMascota().getNombre());
        dto.setPropietario(
                cita.getMascota().getPropietario().getNombre()
        );
        dto.setVeterinario(
                cita.getVeterinario().getNombre()
        );

        return dto;
    }
}