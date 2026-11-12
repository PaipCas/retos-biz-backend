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

	public List<PriorityDto> getAllPriorities() throws Exception {
		try {
			return this.priorityRepository.getAllPriorities();
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public PriorityDto getPriorityById(String id) throws Exception {
		try {
			PriorityDto priorityDto = this.priorityRepository.getPriorityById(id);

			if (priorityDto == null) {
				throw new PriorityNotFoundException(id);
			}

			return priorityDto;
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public List<PriorityDto> getPrioritiesByLevel(Integer level) throws Exception {
		try {
			return this.priorityRepository.getPrioritiesByLevel(level);
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public PriorityDto createPriority(PriorityDto priorityDto) throws Exception {
		try {
			if (this.priorityRepository.existsByName(priorityDto.name())) {
				throw new PriorityAlreadyExistException(priorityDto.name());
			}

			return this.priorityRepository.createPriority(priorityDto);
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public PriorityDto updatePriority(String id, UpdatePriorityDto updatePriorityDto) throws Exception {
		try {
			if (!this.priorityRepository.existsById(id)) {
				throw new PriorityNotFoundException(id);
			}

			return this.priorityRepository.updatePriority(id, updatePriorityDto);
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public void deletePriority(String id) throws Exception {
		try {
			if (!this.priorityRepository.existsById(id)) {
				throw new PriorityNotFoundException(id);
			}

			this.priorityRepository.deletePriority(id);
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}
}
