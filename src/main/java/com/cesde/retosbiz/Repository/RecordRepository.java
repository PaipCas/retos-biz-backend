package com.cesde.retosbiz.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cesde.retosbiz.Model.RecordModel;

public interface RecordRepository extends JpaRepository<RecordModel, String> {

	// ---------- Operaciones de escritura (guardan en la base de datos) ----------

	// Adicionar (id nuevo) y modificar (id existente)
	@Override
	<S extends RecordModel> S save(S recordModel);

	// Eliminar
	@Override
	void deleteById(String id);

	// ---------- Operaciones de consulta ----------

	// Consulta general
	@Override
	List<RecordModel> findAll();

	// Consulta por llave primaria
	@Override
	Optional<RecordModel> findById(String id);

	// Consultas propias de registro
	List<RecordModel> findByEstado(String estado);

	@Query("SELECT r FROM RecordModel r WHERE r.id_usuario = :userId")
	List<RecordModel> findByUserId(@Param("userId") String userId);

	@Query("SELECT r FROM RecordModel r WHERE r.id_reto = :challengeId")
	List<RecordModel> findByChallengeId(@Param("challengeId") String challengeId);
}