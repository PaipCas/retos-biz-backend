package com.cesde.retosbiz.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cesde.retosbiz.Crud.ChallengeCrud;
import com.cesde.retosbiz.Dto.ChallengeDto;
import com.cesde.retosbiz.Dto.UpdateChallengeDto;
import com.cesde.retosbiz.Mapper.ChallengeMapper;
import com.cesde.retosbiz.Model.ChallengeModel;

@Repository
public class ChallengeModelRepository implements ChallengeRepository {
    private final ChallengeCrud challengeCrud;
    private final ChallengeMapper challengeMapper;

    public ChallengeModelRepository(ChallengeCrud challengeCrud, ChallengeMapper challengeMapper) {
        this.challengeCrud = challengeCrud;
        this.challengeMapper = challengeMapper;
    }

    @Override
    public List<ChallengeDto> getAll() {
        return challengeMapper.toChallengeDto(challengeCrud.findAll());
    }

    @Override
    public ChallengeDto getById(String id) {
        ChallengeModel challengeModel = challengeCrud.findById(id).orElse(null);
        return challengeMapper.toChallengeDto(challengeModel);
    }

    @Override
    public List<ChallengeDto> getByEstado(String estado) {
        return challengeMapper.toChallengeDto(challengeCrud.findByEstado(estado));
    }

    @Override
    public List<ChallengeDto> getByCompanyId(String companyId) {
        return challengeMapper.toChallengeDto(challengeCrud.findByIdEmpresa(companyId));
    }

    @Override
    public ChallengeDto create(ChallengeDto challengeDto) {
        ChallengeModel challengeModel = challengeMapper.toChallengeModel(challengeDto);
        challengeModel.setId(null);
        return challengeMapper.toChallengeDto(challengeCrud.save(challengeModel));
    }

    @Override
    public ChallengeDto update(String id, UpdateChallengeDto updateChallengeDto) {
        ChallengeModel challengeModel = challengeCrud.findById(id).orElse(null);
        if (challengeModel == null) {
            return null;
        }

        challengeMapper.updateChallengeModelFromDto(updateChallengeDto, challengeModel);
        return challengeMapper.toChallengeDto(challengeCrud.save(challengeModel));
    }

    @Override
    public void delete(String id) {
        challengeCrud.deleteById(id);
    }
}
