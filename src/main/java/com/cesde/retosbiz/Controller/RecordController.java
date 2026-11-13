package com.cesde.retosbiz.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/records")
@CrossOrigin(origins = "*")
@Tag(name = "Registros", description = "Operaciones relacionadas con la gestión de registros de retos")
public class RecordController {
	private final RecordService recordService;

	public RecordController(RecordService recordService) {
		this.recordService = recordService;
	}

	// Consulta general
	@GetMapping
	@Operation(summary = "Obtener todos los registros", description = "Retorna una lista con todos los registros almacenados en el sistema.")
	public ResponseEntity<List<RecordDto>> getAllRecords() {
		return ResponseEntity.ok(this.recordService.getAllRecords());
	}

	// Consulta por llave primaria
	@GetMapping("/{id}")
	@Operation(summary = "Obtener un registro por ID", description = "Busca y retorna un registro específico según el identificador proporcionado.")
	public ResponseEntity<RecordDto> getRecordById(@PathVariable String id) {
		RecordDto recordDto = this.recordService.getRecordById(id);

		if (recordDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(recordDto);
	}

	// Consultas propias
	@GetMapping("/status/{status}")
	@Operation(summary = "Obtener registros por estado", description = "Retorna una lista de registros filtrados según su estado actual.")
	public ResponseEntity<List<RecordDto>> getRecordsByStatus(@PathVariable String status) {
		return ResponseEntity.ok(this.recordService.getRecordsByStatus(status));
	}

	@GetMapping("/user/{userId}")
	@Operation(summary = "Obtener registros por usuario", description = "Retorna una lista de registros asociados a un usuario específico.")
	public ResponseEntity<List<RecordDto>> getRecordsByUser(@PathVariable String userId) {
		return ResponseEntity.ok(this.recordService.getRecordsByUser(userId));
	}

	@GetMapping("/challenge/{challengeId}")
	@Operation(summary = "Obtener registros por reto", description = "Retorna una lista de registros asociados a un reto específico.")
	public ResponseEntity<List<RecordDto>> getRecordsByChallenge(@PathVariable String challengeId) {
		return ResponseEntity.ok(this.recordService.getRecordsByChallenge(challengeId));
	}

	// Adicionar
	@PostMapping
	@Operation(summary = "Crear un nuevo registro", description = "Registra una nueva entrada o inscripción en el sistema.")
	public ResponseEntity<RecordDto> createRecord(@RequestBody RecordDto recordDto) {
		RecordDto createdRecord = this.recordService.createRecord(recordDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRecord);
	}

	// Modificar
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar un registro", description = "Actualiza la información de un registro existente basado en su ID.")
	public ResponseEntity<RecordDto> updateRecord(@PathVariable String id, @RequestBody RecordDto recordDto) {
		RecordDto updatedRecord = this.recordService.updateRecord(id, recordDto);

		if (updatedRecord == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(updatedRecord);
	}

	// Eliminar
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar un registro", description = "Elimina un registro del sistema utilizando su identificador único.")
	public ResponseEntity<Void> deleteRecord(@PathVariable String id) {
		if (!this.recordService.deleteRecord(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}