package com.example.view;

import java.util.List;

public class CategoryView extends BaseView {
	
	private List<ProductView> products;

	public List<ProductView> getProducts() {
		return products;
	}

	public void setProducts(List<ProductView> products) {
		this.products = products;
	}
}
