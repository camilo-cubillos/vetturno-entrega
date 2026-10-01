package com.vetturno.service;

import com.vetturno.dto.MascotaDTO;
import com.vetturno.dto.MascotaRequest;
import com.vetturno.model.Mascota;
import com.vetturno.model.Propietario;
import com.vetturno.repository.MascotaRepository;
import com.vetturno.repository.PropietarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final PropietarioRepository propietarioRepository;

    public MascotaService(
            MascotaRepository mascotaRepository,
            PropietarioRepository propietarioRepository) {

        this.mascotaRepository = mascotaRepository;
        this.propietarioRepository = propietarioRepository;
    }

    public MascotaDTO crear(MascotaRequest request) {

        Propietario propietario = propietarioRepository
                .findById(request.getPropietarioId())
                .orElseThrow(() ->
                        new RuntimeException("Propietario no encontrado"));

        Mascota mascota = new Mascota();

        mascota.setNombre(request.getNombre());
        mascota.setEspecie(request.getEspecie());
        mascota.setRaza(request.getRaza());
        mascota.setPropietario(propietario);

        Mascota guardada = mascotaRepository.save(mascota);

        return convertirADTO(guardada);
    }

    public List<MascotaDTO> listar() {

        return mascotaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    private MascotaDTO convertirADTO(Mascota mascota) {

        MascotaDTO dto = new MascotaDTO();

        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setEspecie(mascota.getEspecie());
        dto.setRaza(mascota.getRaza());

        dto.setPropietarioId(
                mascota.getPropietario().getId());

        dto.setPropietarioNombre(
                mascota.getPropietario().getNombre());

        return dto;
    }
}