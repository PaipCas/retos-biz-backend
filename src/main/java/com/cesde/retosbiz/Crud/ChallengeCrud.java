package com.cesde.retosbiz.Crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cesde.retosbiz.Model.ChallengeModel;

public interface ChallengeCrud extends CrudRepository<ChallengeModel, String> {
    List<ChallengeModel> findByEstado(String estado);
    List<ChallengeModel> findByIdEmpresa(String idEmpresa);
}
