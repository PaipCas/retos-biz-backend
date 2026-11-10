package com.cesde.retosbiz.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.cesde.retosbiz.Service.PriorityService;

import jakarta.validation.Valid;

import com.cesde.retosbiz.Dto.PriorityDto;
import com.cesde.retosbiz.Dto.UpdatePriorityDto;
import com.cesde.retosbiz.Exception.PriorityAlreadyExistException;
import com.cesde.retosbiz.Exception.PriorityNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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

@RestController
@RequestMapping("/priorities")
@CrossOrigin(origins = "*")
@Tag(name = "Prioridades", description = "Operaciones relacionadas con la gestión de prioridades")
public class PriorityController {
	private final PriorityService priorityService;

	public PriorityController(PriorityService priorityService) {
		this.priorityService = priorityService;
	}

	@GetMapping
	@Operation(summary = "Obtener todas las prioridades", description = "Retorna una lista con todas las prioridades registradas en el sistema.")
	public ResponseEntity<List<PriorityDto>> getAllPriorities() {
		try {
			return ResponseEntity.ok(this.priorityService.getAllPriorities());
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar las prioridades: ", e);
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Obtener una prioridad por ID", description = "Busca y retorna una prioridad específica según el identificador único proporcionado.")
	public ResponseEntity<PriorityDto> getPriorityById(@PathVariable String id) {
		try {
			return ResponseEntity.ok(this.priorityService.getPriorityById(id));
		} catch (PriorityNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar la prioridad " + id, e);
		}
	}

	@GetMapping("/level/{level}")
	@Operation(summary = "Obtener prioridades por nivel", description = "Retorna una lista de prioridades filtradas de acuerdo a su nivel numérico.")
	public ResponseEntity<List<PriorityDto>> getPrioritiesByLevel(@PathVariable Integer level) {
		try {
			return ResponseEntity.ok(this.priorityService.getPrioritiesByLevel(level));
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar las prioridades de nivel " + level, e);
		}
	}

	@PostMapping
	@Operation(summary = "Crear una nueva prioridad", description = "Registra una nueva prioridad en el sistema utilizando los datos proporcionados.")
	public ResponseEntity<PriorityDto> createPriority(@RequestBody PriorityDto priorityDto) {
		try {
			PriorityDto createdPriority = this.priorityService.createPriority(priorityDto);

			return ResponseEntity.status(HttpStatus.CREATED).body(createdPriority);
		} catch (PriorityAlreadyExistException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al crear la prioridad", e);
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Actualizar una prioridad", description = "Actualiza la información de una prioridad existente basada en su identificador.")
	public ResponseEntity<PriorityDto> updatePriority(@PathVariable String id, @RequestBody @Valid UpdatePriorityDto updatePriorityDto) {
		try {
			return ResponseEntity.ok(this.priorityService.updatePriority(id, updatePriorityDto));
		} catch (PriorityNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar la prioridad " + id, e);
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar una prioridad", description = "Elimina una prioridad del sistema utilizando su identificador único.")
	public ResponseEntity<Void> deletePriority(@PathVariable String id) {
		try {
			this.priorityService.deletePriority(id);
			return ResponseEntity.noContent().build();
		} catch (PriorityNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al eliminar la prioridad " + id, e);
		}
	}
}