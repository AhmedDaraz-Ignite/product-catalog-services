package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.model.CategoryEntity;
import com.example.repository.CategoryRepository;
import com.example.view.CategoryView;

/**
 * Category service class.
 * @author Ahmed.Rabie
 *
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryEntity, CategoryView> implements CategoryService {

	private static final String CATEGORY = "Category";
	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	@Override
	protected JpaRepository<CategoryEntity, Long> getRepository() {
		return categoryRepository;
	}

	@Override
	protected String getObjectName() {
		return CATEGORY;
	}

	@Override
	protected CategoryView getViewInstance() {
		return new CategoryView();
	}

	@Override
	protected CategoryEntity getEntityInstance() {
		return new CategoryEntity();
	}


}
