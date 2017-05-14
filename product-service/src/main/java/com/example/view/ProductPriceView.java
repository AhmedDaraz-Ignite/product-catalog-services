package com.example.view;

/**
 * Product price view object.
 * @author Ahmed.Rabie
 *
 */
public class ProductPriceView extends BaseView {
	
	private String currency;
	private Double amount;
	
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
