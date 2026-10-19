package com.cesde.retosbiz.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesde.retosbiz.Dto.PriorityDto;
import com.cesde.retosbiz.Dto.UpdatePriorityDto;
import com.cesde.retosbiz.Exception.PriorityAlreadyExistException;
import com.cesde.retosbiz.Exception.PriorityNotFoundException;
import com.cesde.retosbiz.Repository.PriorityRepository;

@Service
public class PriorityService {
	private final PriorityRepository priorityRepository;

	public PriorityService(PriorityRepository priorityRepository) {
		this.priorityRepository = priorityRepository;
	}

	public List<PriorityDto> getAllPriorities() {
		try {
			return this.priorityRepository.getAllPriorities();
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar las prioridades: ", e);
		}
	}

	public PriorityDto getPriorityById(String id) {
		try {
			PriorityDto priorityDto = this.priorityRepository.getPriorityById(id);

			if (priorityDto == null) {
				throw new PriorityNotFoundException(id);
			}

			return priorityDto;
		} catch (PriorityNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar la prioridad " + id, e);
		}
	}

	public List<PriorityDto> getPrioritiesByLevel(Integer level) {
		try {
			return this.priorityRepository.getPrioritiesByLevel(level);
		} catch (Exception e) {
			throw new RuntimeException("Error al consultar las prioridades de nivel " + level, e);
		}
	}

	public PriorityDto createPriority(PriorityDto priorityDto) {
		try {
			if (this.priorityRepository.existsByName(priorityDto.name())) {
				throw new PriorityAlreadyExistException(priorityDto.name());
			}

			return this.priorityRepository.createPriority(priorityDto);
		} catch (PriorityAlreadyExistException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al crear la prioridad", e);
		}
	}

	public PriorityDto updatePriority(String id, UpdatePriorityDto updatePriorityDto) {
		try {
			if (!this.priorityRepository.existsById(id)) {
				throw new PriorityNotFoundException(id);
			}

			return this.priorityRepository.updatePriority(id, updatePriorityDto);
		} catch (PriorityNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al actualizar la prioridad " + id, e);
		}
	}

	public void deletePriority(String id) {
		try {
			if (!this.priorityRepository.existsById(id)) {
				throw new PriorityNotFoundException(id);
			}

			this.priorityRepository.deletePriority(id);
		} catch (PriorityNotFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException("Error al eliminar la prioridad " + id, e);
		}
	}
}
