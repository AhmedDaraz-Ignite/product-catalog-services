package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.beanmapping.BeanMapperUtil;
import com.example.model.ProductEntity;
import com.example.repository.ProductRepository;
import com.example.view.CategoryView;
import com.example.view.ProductView;

@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductEntity, ProductView> implements ProductService {

	private ProductRepository productRepository;
	CategoryService s;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<ProductView> listProductsByCategoryId(Long categoryId) {
		List<ProductEntity> products = productRepository.findByCategoryIdEquals(categoryId);
		List<ProductView> productViews = new ArrayList<>();
		super.convertToViews(productViews, products);
		return productViews;
	}
	
	@Override
	public CategoryView getProductCategory(Long productId) {
		CategoryView categoryView = new CategoryView();
		BeanMapperUtil.getMapper().map(productRepository.getOne(productId).getCategory(), categoryView);
		return categoryView;
	}
	
	@Override
	protected JpaRepository<ProductEntity, Long> getRepository() {
		return productRepository;
	}

	@Override
	protected String getObjectName() {
		return "Product";
	}

	@Override
	protected ProductView getViewInstance() {
		return new ProductView();
	}

	@Override
	protected ProductEntity getEntityInstance() {
		return new ProductEntity();
	}

}
