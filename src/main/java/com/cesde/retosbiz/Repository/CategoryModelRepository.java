package com.cesde.retosbiz.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cesde.retosbiz.Crud.CategoryCrud;
import com.cesde.retosbiz.Dto.CategoryDto;
import com.cesde.retosbiz.Dto.UpdateCategoryDto;
import com.cesde.retosbiz.Exception.CategoryAlreadyExistException;
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
		CategoryModel categoryModel = this.categoryCrud.findById(id).orElse(null);
		return this.categoryMapper.toCategoryDto(categoryModel);
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		if (this.categoryCrud.findFirstByNombre(categoryDto.name()) != null){
			throw new CategoryAlreadyExistException(categoryDto.name());
		}

		CategoryModel categoryModel = this.categoryMapper.toCategoryModel(categoryDto);

		return this.categoryMapper.toCategoryDto(this.categoryCrud.save(categoryModel));
	}

	@Override
	public CategoryDto updateCategory(String id, UpdateCategoryDto updateCategoryDto) {
		CategoryModel categoryModel = this.categoryCrud.findById(id).orElse(null);
		
		if (categoryModel == null)
			return null;

		this.categoryMapper.updateCategoryModelFromDto(updateCategoryDto, categoryModel);
		return this.categoryMapper.toCategoryDto(this.categoryCrud.save(categoryModel));
	}

	@Override
	public void deleteCategory(String id) {
		this.categoryCrud.deleteById(id);
	}

}
