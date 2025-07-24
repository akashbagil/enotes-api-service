package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.demo.dto.CategoryDto;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRespository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRespository categoryRespository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Boolean saveCategory(CategoryDto categoryDto) {
		
		Category category = mapper.map(categoryDto, Category.class);
		
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		Category savedCategory = categoryRespository.save(category);
		if (ObjectUtils.isEmpty(savedCategory))
			return false;
		return true;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> allCategory = categoryRespository.findAll();
		
		List<CategoryDto> allCategoryDto = allCategory.stream().map(cat -> mapper.map(cat, CategoryDto.class)).toList();
		return allCategoryDto;
	}

	@Override
	public List<CategoryResponse> getAllActiveCategory() {
		
		List<Category> allCategory = categoryRespository.findByIsActiveTrue();
		
		List<CategoryResponse> categoryResponse = allCategory.stream().map(cat -> mapper.map(cat, CategoryResponse.class)).toList();
		return categoryResponse;
	}

}
