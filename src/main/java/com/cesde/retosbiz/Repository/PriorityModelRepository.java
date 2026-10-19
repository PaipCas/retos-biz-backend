package com.cesde.retosbiz.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cesde.retosbiz.Crud.PriorityCrud;
import com.cesde.retosbiz.Dto.PriorityDto;
import com.cesde.retosbiz.Dto.UpdatePriorityDto;
import com.cesde.retosbiz.Mapper.PriorityMapper;
import com.cesde.retosbiz.Model.PriorityModel;

@Repository
public class PriorityModelRepository implements PriorityRepository {
	private final PriorityCrud priorityCrud;
	private final PriorityMapper priorityMapper;

	public PriorityModelRepository(PriorityCrud priorityCrud, PriorityMapper priorityMapper) {
		this.priorityCrud = priorityCrud;
		this.priorityMapper = priorityMapper;
	}

	@Override
	public List<PriorityDto> getAllPriorities() {
		return priorityMapper.toPriorityDto(this.priorityCrud.findAll());
	}

	@Override
	public PriorityDto getPriorityById(String id) {
		return this.priorityCrud.findById(id).map(this.priorityMapper::toPriorityDto).orElse(null);
	}

	@Override
	public List<PriorityDto> getPrioritiesByLevel(Integer level) {
		return this.priorityMapper.toPriorityDto(this.priorityCrud.findByNivel(level));
	}

	@Override
	public PriorityDto createPriority(PriorityDto priorityDto) {
		PriorityModel priorityModel = this.priorityMapper.toPriorityModel(priorityDto);
		return this.priorityMapper.toPriorityDto(this.priorityCrud.save(priorityModel));
	}

	@Override
	public PriorityDto updatePriority(String id, UpdatePriorityDto updatePriorityDto) {
		PriorityModel priorityModel = this.priorityCrud.findById(id).orElseThrow();
		this.priorityMapper.updatePriorityModelFromDto(updatePriorityDto, priorityModel);

		return this.priorityMapper.toPriorityDto(this.priorityCrud.save(priorityModel));
	}

	@Override
	public boolean existsByName(String name) {
		return this.priorityCrud.findFirstByNombre(name) != null;
	}

	@Override
	public boolean existsById(String id) {
		return this.priorityCrud.existsById(id);
	}

	@Override
	public void deletePriority(String id) {
		this.priorityCrud.deleteById(id);
	}

}
