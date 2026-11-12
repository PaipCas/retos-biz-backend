package com.cesde.retosbiz.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cesde.retosbiz.Dto.ChallengeDto;
import com.cesde.retosbiz.Dto.UpdateChallengeDto;
import com.cesde.retosbiz.Repository.ChallengeRepository;

@Service
public class ChallengeService {

    private static final List<String> ESTADOS =
            List.of("en progreso", "completado", "pendiente", "cancelado");

    private final ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public List<ChallengeDto> getAll() {
        return challengeRepository.getAll();
    }

    public ChallengeDto getById(String id) throws Exception {
        ChallengeDto challenge = challengeRepository.getById(id);
        if (challenge == null) {
            throw new Exception("Reto no encontrado");
        }
        return challenge;
    }

    public List<ChallengeDto> getByEstado(String estado) {
        return challengeRepository.getByEstado(estado);
    }

    public List<ChallengeDto> getByCompanyId(String companyId) {
        return challengeRepository.getByCompanyId(companyId);
    }

    public ChallengeDto create(ChallengeDto challenge) throws Exception {
        validate(challenge);
        return challengeRepository.create(challenge);
    }

    public ChallengeDto update(String id, UpdateChallengeDto challenge) throws Exception {
        getById(id);
        validate(challenge);
        return challengeRepository.update(id, challenge);
    }

    public void delete(String id) throws Exception {
        getById(id);
        challengeRepository.delete(id);
    }

    private void validate(ChallengeDto challenge) throws Exception {
        if (challenge == null) {
            throw new Exception("El reto es obligatorio");
        }
        validate(challenge.name(), challenge.description(), challenge.startDate(),
                challenge.endDate(), challenge.status(), challenge.companyId());
    }

    private void validate(UpdateChallengeDto challenge) throws Exception {
        if (challenge == null) {
            throw new Exception("El reto es obligatorio");
        }
        validate(challenge.name(), challenge.description(), challenge.startDate(),
                challenge.endDate(), challenge.status(), challenge.companyId());
    }

    private void validate(String name, String description, LocalDate startDate, LocalDate endDate,
                          String status, String companyId)
            throws Exception {
        if (name == null || name.isBlank()) {
            throw new Exception("El nombre es obligatorio");
        }
        if (name.length() > 150) {
            throw new Exception("El nombre no puede exceder los 150 caracteres");
        }
        if (description != null && description.length() > 500) {
            throw new Exception("La descripción no puede exceder los 500 caracteres");
        }
        if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
            throw new Exception("La fecha fin no puede ser anterior a la fecha inicio");
        }
        if (status == null || !ESTADOS.contains(status.trim().toLowerCase())) {
            throw new Exception(
                    "Estado no válido. Use: En progreso, Completado, Pendiente o Cancelado");
        }
        if (status.length() > 50) {
            throw new Exception("El estado no puede exceder los 50 caracteres");
        }
        validateRequiredId(companyId, "empresa");
    }

    private void validateRequiredId(String id, String field) throws Exception {
        if (id == null || id.isBlank()) {
            throw new Exception("El ID de " + field + " es obligatorio");
        }
        if (id.length() > 50) {
            throw new Exception("El ID de " + field + " no puede exceder los 50 caracteres");
        }
    }
}
