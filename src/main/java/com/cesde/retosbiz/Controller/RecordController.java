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

import com.cesde.retosbiz.Dto.RecordDto;
import com.cesde.retosbiz.Service.RecordService;

@RestController
@RequestMapping("/records")
public class RecordController {
	private final RecordService recordService;

	public RecordController(RecordService recordService) {
		this.recordService = recordService;
	}

	// Consulta general
	@GetMapping
	public ResponseEntity<List<RecordDto>> getAllRecords() {
		return ResponseEntity.ok(this.recordService.getAllRecords());
	}

	// Consulta por llave primaria
	@GetMapping("/{id}")
	public ResponseEntity<RecordDto> getRecordById(@PathVariable String id) {
		RecordDto recordDto = this.recordService.getRecordById(id);

		if (recordDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(recordDto);
	}

	// Consultas propias
	@GetMapping("/status/{status}")
	public ResponseEntity<List<RecordDto>> getRecordsByStatus(@PathVariable String status) {
		return ResponseEntity.ok(this.recordService.getRecordsByStatus(status));
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<RecordDto>> getRecordsByUser(@PathVariable String userId) {
		return ResponseEntity.ok(this.recordService.getRecordsByUser(userId));
	}

	@GetMapping("/challenge/{challengeId}")
	public ResponseEntity<List<RecordDto>> getRecordsByChallenge(@PathVariable String challengeId) {
		return ResponseEntity.ok(this.recordService.getRecordsByChallenge(challengeId));
	}

	// Adicionar
	@PostMapping
	public ResponseEntity<RecordDto> createRecord(@RequestBody RecordDto recordDto) {
		RecordDto createdRecord = this.recordService.createRecord(recordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
	}

	// Modificar
	@PutMapping("/{id}")
	public ResponseEntity<RecordDto> updateRecord(@PathVariable String id, @RequestBody RecordDto recordDto) {
		RecordDto updatedRecord = this.recordService.updateRecord(id, recordDto);

		if (updatedRecord == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedRecord);
	}

	// Eliminar
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRecord(@PathVariable String id) {
		if (!this.recordService.deleteRecord(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}