package com.example.product;

import java.util.List;

import com.example.category.CategoryView;
import com.example.common.service.Service;

/**
 * Product service interface
 * @author Ahmed.Rabie
 *
 */
public interface ProductService extends Service<ProductEntity, ProductView> {

	List<ProductView> listProductsByCategoryId(Long categoryId);
	CategoryView getProductCategory(Long productId);
}
