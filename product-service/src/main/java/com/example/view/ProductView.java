package com.example.view;

/**
 * Product view object
 */
import java.util.List;

public class ProductView extends BaseView {
	
	private double price;
	private List<ProductPriceView> targetPrices;
	private CategoryView category;
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public CategoryView getCategory() {
		return category;
	}
	public void setCategory(CategoryView category) {
		this.category = category;
	}
	public List<ProductPriceView> getTargetPrices() {
		return targetPrices;
	}
	public void setTargetPrices(List<ProductPriceView> targetPrices) {
		this.targetPrices = targetPrices;
	}
}