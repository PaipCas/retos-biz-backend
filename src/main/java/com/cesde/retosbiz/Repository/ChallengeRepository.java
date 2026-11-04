package com.cesde.retosbiz.Repository;

import java.util.List;

import com.cesde.retosbiz.Dto.ChallengeDto;
import com.cesde.retosbiz.Dto.UpdateChallengeDto;

public interface ChallengeRepository {
    List<ChallengeDto> getAll();
    ChallengeDto getById(String id);
    List<ChallengeDto> getByEstado(String estado);
    List<ChallengeDto> getByCompanyId(String companyId);
    ChallengeDto create(ChallengeDto challengeDto);
    ChallengeDto update(String id, UpdateChallengeDto updateChallengeDto);
    void delete(String id);
}