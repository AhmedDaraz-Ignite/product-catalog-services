package com.example.service;

import java.util.List;

import com.example.model.ProductEntity;
import com.example.view.CategoryView;
import com.example.view.ProductView;

public interface ProductService extends Service<ProductEntity, ProductView> {

	List<ProductView> listProductsByCategoryId(Long categoryId);
	CategoryView getProductCategory(Long productId);
}
