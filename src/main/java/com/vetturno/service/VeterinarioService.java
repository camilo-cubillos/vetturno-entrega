package com.vetturno.service;

import com.vetturno.dto.VeterinarioDTO;
import com.vetturno.dto.VeterinarioRequest;
import com.vetturno.model.Veterinario;
import com.vetturno.repository.VeterinarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeterinarioService {

    private final VeterinarioRepository veterinarioRepository;

    public VeterinarioService(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    public VeterinarioDTO crear(VeterinarioRequest request) {

        Veterinario veterinario = new Veterinario();

        veterinario.setNombre(request.getNombre());
        veterinario.setEspecialidad(request.getEspecialidad());

        Veterinario guardado = veterinarioRepository.save(veterinario);

        return convertirADTO(guardado);
    }

    public List<VeterinarioDTO> listar() {

        return veterinarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    private VeterinarioDTO convertirADTO(Veterinario veterinario) {

        VeterinarioDTO dto = new VeterinarioDTO();

        dto.setId(veterinario.getId());
        dto.setNombre(veterinario.getNombre());
        dto.setEspecialidad(veterinario.getEspecialidad());

        return dto;
    }
}