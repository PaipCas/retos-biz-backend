package com.cesde.retosbiz.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

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

    public List<ChallengeDto> getAllChallenges() {
        return this.challengeRepository.getAll();
    }

    // Lanza NoSuchElementException (404) si el reto no existe
    public ChallengeDto getChallengeById(String id) {
        ChallengeDto challenge = this.challengeRepository.getById(id);
        if (challenge == null) {
            throw new NoSuchElementException("Reto no encontrado");
        }
        return challenge;
    }

    public List<ChallengeDto> getChallengesByStatus(String status) {
        return this.challengeRepository.getByEstado(status);
    }

    public List<ChallengeDto> getChallengesByCompanyId(String companyId) {
        return this.challengeRepository.getByCompanyId(companyId);
    }

    public ChallengeDto createChallenge(ChallengeDto challengeDto) {
        validate(challengeDto);
        return this.challengeRepository.create(challengeDto);
    }

    public ChallengeDto updateChallenge(String id, UpdateChallengeDto updateChallengeDto) {
        getChallengeById(id);
        validate(updateChallengeDto);
        return this.challengeRepository.update(id, updateChallengeDto);
    }

    public void deleteChallenge(String id) {
        getChallengeById(id);
        this.challengeRepository.delete(id);
    }

    private void validate(ChallengeDto challenge) {
        if (challenge == null) {
            throw new IllegalArgumentException("El reto es obligatorio");
        }
        validate(challenge.name(), challenge.description(), challenge.startDate(),
                challenge.endDate(), challenge.status(), challenge.companyId());
    }

    private void validate(UpdateChallengeDto challenge) {
        if (challenge == null) {
            throw new IllegalArgumentException("El reto es obligatorio");
        }
        validate(challenge.name(), challenge.description(), challenge.startDate(),
                challenge.endDate(), challenge.status(), challenge.companyId());
    }

    // Lanza IllegalArgumentException (400) si algún dato no es válido
    private void validate(String name, String description, LocalDate startDate, LocalDate endDate,
                          String status, String companyId, String categoryId, String priorityId) {
                          String status, String companyId)
            throws Exception {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (name.length() > 150) {
            throw new IllegalArgumentException("El nombre no puede exceder los 150 caracteres");
        }
        if (description != null && description.length() > 500) {
            throw new IllegalArgumentException("La descripción no puede exceder los 500 caracteres");
        }
        if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("La fecha fin no puede ser anterior a la fecha inicio");
        }
        if (status == null || !ESTADOS.contains(status.trim().toLowerCase())) {
            throw new IllegalArgumentException(
                    "Estado no válido. Use: En progreso, Completado, Pendiente o Cancelado");
        }
        if (status.length() > 50) {
            throw new IllegalArgumentException("El estado no puede exceder los 50 caracteres");
        }
        validateRequiredId(companyId, "empresa");
    }

    private void validateRequiredId(String id, String field) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El ID de " + field + " es obligatorio");
        }
        if (id.length() > 50) {
            throw new IllegalArgumentException("El ID de " + field + " no puede exceder los 50 caracteres");
        }
    }
}