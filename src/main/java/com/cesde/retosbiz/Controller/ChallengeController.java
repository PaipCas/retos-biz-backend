package com.cesde.retosbiz.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesde.retosbiz.Dto.ChallengeDto;
import com.cesde.retosbiz.Dto.UpdateChallengeDto;
import com.cesde.retosbiz.Service.ChallengeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/challenges")
@Tag(name = "Retos", description = "Operaciones para consultar y administrar retos")
public class ChallengeController {
	private final ChallengeService challengeService;

	public ChallengeController(ChallengeService challengeService) {
		this.challengeService = challengeService;
	}

	// Consulta general
	@GetMapping
	@Operation(summary = "Obtener todos los retos", description = "Retorna una lista con todos los retos registrados.")
	public ResponseEntity<List<ChallengeDto>> getAllChallenges() {
		return ResponseEntity.ok(this.challengeService.getAllChallenges());
	}

	// Consulta por llave primaria (si no existe, el servicio lanza 404)
	@GetMapping("/{id}")
	@Operation(summary = "Obtener un reto por ID", description = "Busca y retorna un reto según su identificador único.")
	public ResponseEntity<ChallengeDto> getChallengeById(@PathVariable String id) {
		return ResponseEntity.ok(this.challengeService.getChallengeById(id));
	}

	// Consultas propias
	@GetMapping("/status/{status}")
	@Operation(summary = "Obtener retos por estado", description = "Retorna los retos que coinciden con el estado indicado.")
	public ResponseEntity<List<ChallengeDto>> getChallengesByStatus(@PathVariable String status) {
		return ResponseEntity.ok(this.challengeService.getChallengesByStatus(status));
	}

	@GetMapping("/company/{companyId}")
	@Operation(summary = "Obtener retos por empresa", description = "Retorna los retos asociados al identificador de empresa indicado.")
	public ResponseEntity<List<ChallengeDto>> getChallengesByCompany(@PathVariable String companyId) {
		return ResponseEntity.ok(this.challengeService.getChallengesByCompanyId(companyId));
	}

	// Adicionar
	@PostMapping
	@Operation(summary = "Crear un reto", description = "Registra un reto nuevo con los datos proporcionados.")
	public ResponseEntity<ChallengeDto> createChallenge(@RequestBody @Valid ChallengeDto challengeDto) {
		ChallengeDto createdChallenge = this.challengeService.createChallenge(challengeDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdChallenge);
	}

	// Modificar
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar un reto", description = "Actualiza los datos de un reto existente según su identificador.")
	public ResponseEntity<ChallengeDto> updateChallenge(@PathVariable String id, @RequestBody @Valid UpdateChallengeDto updateChallengeDto) {
		return ResponseEntity.ok(this.challengeService.updateChallenge(id, updateChallengeDto));
	}

	// Eliminar
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un reto", description = "Elimina el reto que coincide con el identificador indicado.")
	public ResponseEntity<Void> deleteChallenge(@PathVariable String id) {
		this.challengeService.deleteChallenge(id);
		return ResponseEntity.noContent().build();
	}
}