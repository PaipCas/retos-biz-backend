package com.cesde.retosbiz.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cesde.retosbiz.Crud.CategoryCrud;
import com.cesde.retosbiz.Crud.ChallengeCrud;
import com.cesde.retosbiz.Crud.CompanyCrud;
import com.cesde.retosbiz.Crud.PriorityCrud;
import com.cesde.retosbiz.Dto.ChallengeDto;
import com.cesde.retosbiz.Dto.UpdateChallengeDto;
import com.cesde.retosbiz.Mapper.ChallengeMapper;
import com.cesde.retosbiz.Model.ChallengeModel;

@Repository
public class ChallengeModelRepository implements ChallengeRepository {
    private final ChallengeCrud challengeCrud;
    private final CompanyCrud companyCrud;
    private final CategoryCrud categoryCrud;
    private final PriorityCrud priorityCrud;
    private final ChallengeMapper challengeMapper;

    public ChallengeModelRepository(ChallengeCrud challengeCrud, CompanyCrud companyCrud,
            CategoryCrud categoryCrud, PriorityCrud priorityCrud, ChallengeMapper challengeMapper) {
        this.challengeCrud = challengeCrud;
        this.companyCrud = companyCrud;
        this.categoryCrud = categoryCrud;
        this.priorityCrud = priorityCrud;
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
        return challengeMapper.toChallengeDto(challengeCrud.findByEmpresa_Id(companyId));
    }

    @Override
    public ChallengeDto create(ChallengeDto challengeDto) {
        ChallengeModel challengeModel = challengeMapper.toChallengeModel(challengeDto);
        challengeModel.setId(null);
        assignRelations(challengeModel, challengeDto.companyId(), challengeDto.categoryId(), challengeDto.priorityId());
        return challengeMapper.toChallengeDto(challengeCrud.save(challengeModel));
    }

    @Override
    public ChallengeDto update(String id, UpdateChallengeDto updateChallengeDto) {
        ChallengeModel challengeModel = challengeCrud.findById(id).orElse(null);
        if (challengeModel == null) {
            return null;
        }

        challengeMapper.updateChallengeModelFromDto(updateChallengeDto, challengeModel);
        assignRelations(challengeModel, updateChallengeDto.companyId(), updateChallengeDto.categoryId(), updateChallengeDto.priorityId());
        return challengeMapper.toChallengeDto(challengeCrud.save(challengeModel));
    }

    @Override
    public void delete(String id) {
        challengeCrud.deleteById(id);
    }

    // Busca la empresa, la categoría y la prioridad por su id y las asigna al reto.
    // Si alguna no existe lanza IllegalArgumentException (el handler responde 400).
    private void assignRelations(ChallengeModel challengeModel, String companyId, String categoryId, String priorityId) {
        challengeModel.setEmpresa(companyCrud.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("La empresa no existe")));
        challengeModel.setCategoria(categoryCrud.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("La categoría no existe")));
        challengeModel.setPrioridad(priorityCrud.findById(priorityId)
                .orElseThrow(() -> new IllegalArgumentException("La prioridad no existe")));
    }
}