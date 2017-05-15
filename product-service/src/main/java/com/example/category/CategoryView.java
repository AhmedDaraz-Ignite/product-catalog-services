package com.example.category;

import java.util.List;

import com.example.common.types.BaseView;
import com.example.product.ProductView;

/**
 * Category view object.
 * @author Ahmed.Rabie
 *
 */
public class CategoryView extends BaseView {
	
	private List<ProductView> products;

	public List<ProductView> getProducts() {
		return products;
	}

	public void setProducts(List<ProductView> products) {
		this.products = products;
	}
}
