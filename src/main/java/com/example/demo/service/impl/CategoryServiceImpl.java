package com.example.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRespository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRespository categoryRespository;

	@Override
	public Boolean saveCategory(Category category) {
		category.setIsDeleted(false);
		category.setCreatedBy(1);
		category.setCreatedOn(new Date());
		Category savedCategory = categoryRespository.save(category);
		if (ObjectUtils.isEmpty(savedCategory))
			return false;
		return true;
	}

	@Override
	public List<Category> getAllCategory() {
		List<Category> allCategory = categoryRespository.findAll();
		return allCategory;
	}

}
