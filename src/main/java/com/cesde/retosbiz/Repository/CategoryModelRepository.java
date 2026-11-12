package com.cesde.retosbiz.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cesde.retosbiz.Crud.CategoryCrud;
import com.cesde.retosbiz.Dto.CategoryDto;
import com.cesde.retosbiz.Dto.UpdateCategoryDto;
import com.cesde.retosbiz.Mapper.CategoryMapper;
import com.cesde.retosbiz.Model.CategoryModel;

@Repository
public class CategoryModelRepository implements CategoryRepository {
	private final CategoryCrud categoryCrud;
	private final CategoryMapper categoryMapper;

	public CategoryModelRepository(CategoryCrud categoryCrud, CategoryMapper categoryMapper) {
		this.categoryCrud = categoryCrud;
		this.categoryMapper = categoryMapper;
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		return categoryMapper.toCategoryDto(this.categoryCrud.findAll());
	}

	@Override
	public CategoryDto getCategoryById(String id) {
		return this.categoryCrud.findById(id).map(this.categoryMapper::toCategoryDto).orElse(null);
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		CategoryModel categoryModel = this.categoryMapper.toCategoryModel(categoryDto);
		return this.categoryMapper.toCategoryDto(this.categoryCrud.save(categoryModel));
	}

	@Override
	public CategoryDto updateCategory(String id, UpdateCategoryDto updateCategoryDto) {
		CategoryModel categoryModel = this.categoryCrud.findById(id).orElseThrow();
		this.categoryMapper.updateCategoryModelFromDto(updateCategoryDto, categoryModel);
		
		return this.categoryMapper.toCategoryDto(this.categoryCrud.save(categoryModel));
	}

	@Override
	public boolean existsByName(String name) {
		return this.categoryCrud.findFirstByNombre(name) != null;
	}

	@Override
	public boolean existsById(String id) {
		return this.categoryCrud.existsById(id);
	}

	@Override
	public void deleteCategory(String id) {
		this.categoryCrud.deleteById(id);
	}

}
