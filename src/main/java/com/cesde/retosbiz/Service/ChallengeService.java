package com.cesde.retosbiz.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cesde.retosbiz.Model.ChallengeModel;
import com.cesde.retosbiz.Repository.ChallengeRepository;

@Service
public class ChallengeService {

    private static final List<String> ESTADOS =
            List.of("en progreso", "completado", "pendiente", "cancelado");

    private final ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    // Consulta general
    public List<ChallengeModel> getAll() throws Exception {
        try {
            return (List<ChallengeModel>) this.challengeRepository.findAll();
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta individual por llave primaria
    public ChallengeModel getById(String id) throws Exception {
        try {
            Optional<ChallengeModel> retoEncontrado = this.challengeRepository.findById(id);
            if (retoEncontrado.isPresent()) {
                return retoEncontrado.get();
            } else {
                throw new Exception("Reto no encontrado");
            }
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Consulta por estado
    public List<ChallengeModel> getByEstado(String estado) throws Exception {
        try {
            return this.challengeRepository.findByEstado(estado);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Crear un reto
    public ChallengeModel create(ChallengeModel reto) throws Exception {
        try {
            validar(reto);
            reto.setId(null);
            return this.challengeRepository.save(reto);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Actualizar un reto
    public ChallengeModel update(String id, ChallengeModel reto) throws Exception {
        try {
            getById(id);
            validar(reto);
            reto.setId(id);
            return this.challengeRepository.save(reto);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Eliminar un reto
    public void delete(String id) throws Exception {
        try {
            getById(id);
            this.challengeRepository.deleteById(id);
        } catch (Exception error) {
            throw new Exception(error.getMessage());
        }
    }

    // Reglas de negocio
    private void validar(ChallengeModel reto) throws Exception {
        if (reto.getNombre() == null || reto.getNombre().isBlank()) {
            throw new Exception("El nombre es obligatorio");
        }
        if (reto.getFechaInicio() != null && reto.getFechaFin() != null
                && reto.getFechaFin().isBefore(reto.getFechaInicio())) {
            throw new Exception("La fecha fin no puede ser anterior a la fecha inicio");
        }
        if (reto.getEstado() == null
                || !ESTADOS.contains(reto.getEstado().trim().toLowerCase())) {
            throw new Exception("Estado no válido. Use: En progreso, Completado, Pendiente o Cancelado");
        }
    }
}