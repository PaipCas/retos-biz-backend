package com.cesde.retosbiz.Repository;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.cesde.retosbiz.Model.ChallengeModel;

public interface ChallengeRepository extends CrudRepository<ChallengeModel, String> {
    List<ChallengeModel> findByEstado(String estado);
    List<ChallengeModel> findByIdEmpresa(String idEmpresa);
}