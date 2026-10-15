package com.cesde.retosbiz.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.cesde.retosbiz.Model.ChallengeModel;
import com.cesde.retosbiz.Repository.ChallengeRepository;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;
    
    private static final List<String> ESTADOS =
        List.of("en progreso", "completado", "pendiente", "cancelado");

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public List<ChallengeModel> getAll() {
        return (List<ChallengeModel>) challengeRepository.findAll();
    }

    public ChallengeModel getById(String id) {
        return challengeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Reto no encontrado"));
    }

    public List<ChallengeModel> getByEstado(String estado) {
        return challengeRepository.findByEstado(estado);
    }

    public ChallengeModel create(ChallengeModel reto) {
        validar(reto);
        reto.setId(null);
        return challengeRepository.save(reto);
    }

    public ChallengeModel update(String id, ChallengeModel reto) {
        getById(id);
        validar(reto);
        reto.setId(id);
        return challengeRepository.save(reto);
    }

    public void delete(String id) {
        getById(id);
        challengeRepository.deleteById(id);
    }

    private void validar(ChallengeModel reto) {
        if (reto.getNombre() == null || reto.getNombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio");
        }
        if (reto.getEstado() == null
        || !ESTADOS.contains(reto.getEstado().trim().toLowerCase())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Estado no válido. Use: En progreso, Completado, Pendiente o Cancelado");
}
        if (reto.getFechaInicio() != null && reto.getFechaFin() != null
                && reto.getFechaFin().isBefore(reto.getFechaInicio())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha fin no puede ser anterior a la fecha inicio");
        }
    }
}