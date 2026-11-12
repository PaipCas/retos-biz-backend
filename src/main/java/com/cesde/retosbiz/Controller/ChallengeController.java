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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {
	private final ChallengeService challengeService;

	public ChallengeController(ChallengeService challengeService) {
		this.challengeService = challengeService;
	}

	// Consulta general
	@GetMapping
	public ResponseEntity<List<ChallengeDto>> getAllChallenges() {
		return ResponseEntity.ok(this.challengeService.getAllChallenges());
	}

	// Consulta por llave primaria (si no existe, el servicio lanza 404)
	@GetMapping("/{id}")
	public ResponseEntity<ChallengeDto> getChallengeById(@PathVariable String id) {
		return ResponseEntity.ok(this.challengeService.getChallengeById(id));
	}

	// Consultas propias
	@GetMapping("/status/{status}")
	public ResponseEntity<List<ChallengeDto>> getChallengesByStatus(@PathVariable String status) {
		return ResponseEntity.ok(this.challengeService.getChallengesByStatus(status));
	}

	@GetMapping("/company/{companyId}")
	public ResponseEntity<List<ChallengeDto>> getChallengesByCompany(@PathVariable String companyId) {
		return ResponseEntity.ok(this.challengeService.getChallengesByCompanyId(companyId));
	}

	// Adicionar
	@PostMapping
	public ResponseEntity<ChallengeDto> createChallenge(@RequestBody @Valid ChallengeDto challengeDto) {
		ChallengeDto createdChallenge = this.challengeService.createChallenge(challengeDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdChallenge);
	}

	// Modificar
	@PutMapping("/{id}")
	public ResponseEntity<ChallengeDto> updateChallenge(@PathVariable String id, @RequestBody @Valid UpdateChallengeDto updateChallengeDto) {
		return ResponseEntity.ok(this.challengeService.updateChallenge(id, updateChallengeDto));
	}

	// Eliminar
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteChallenge(@PathVariable String id) {
		this.challengeService.deleteChallenge(id);
		return ResponseEntity.noContent().build();
	}
}