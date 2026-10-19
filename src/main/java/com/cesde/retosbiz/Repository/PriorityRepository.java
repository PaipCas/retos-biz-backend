package com.cesde.retosbiz.Repository;

import java.util.List;

import com.cesde.retosbiz.Dto.PriorityDto;
import com.cesde.retosbiz.Dto.UpdatePriorityDto;

public interface PriorityRepository {
	List<PriorityDto> getAllPriorities();

	PriorityDto getPriorityById(String id);

	List<PriorityDto> getPrioritiesByLevel(Integer level);

	PriorityDto createPriority(PriorityDto categoryDto);

	PriorityDto updatePriority(String id, UpdatePriorityDto updatePriorityDto);

	void deletePriority(String id);

	boolean existsByName(String name);

	boolean existsById(String id);
}