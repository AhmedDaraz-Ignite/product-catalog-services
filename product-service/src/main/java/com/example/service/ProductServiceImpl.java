package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.beanmapping.BeanMapperUtil;
import com.example.endpoint.StatucCode;
import com.example.exception.ProductServiceException;
import com.example.model.ProductEntity;
import com.example.repository.ProductRepository;
import com.example.view.CategoryView;
import com.example.view.ProductPriceView;
import com.example.view.ProductView;

/**
 * Product service implementation
 * @author Ahmed.Rabie
 *
 */
@Service
public class ProductServiceImpl extends BaseServiceImpl<ProductEntity, ProductView> implements ProductService {

	private static final String PRODUCT = "Product";
	private ProductRepository productRepository;
	private CurrencyExchangeService currencyExchangeService;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, CurrencyExchangeService currencyExchangeService) {
		this.productRepository = productRepository;
		this.currencyExchangeService = currencyExchangeService;
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
	
	/**
	 * Create product object, only users with admin Role can call this method.
	 */
	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public ProductView create(ProductView view) throws ProductServiceException {
		List<ProductPriceView> targetPrices = view.getTargetPrices();
		
		if(!targetPrices.isEmpty()) {
			List<String> prices = new ArrayList<>(); 
			targetPrices.forEach(p -> prices.add(p.getCurrency()));
			try {
				Map<String, Double> targetCurrencies = currencyExchangeService.getCurrencyAmountIn(prices, view.getPrice());
				targetPrices.forEach(p -> p.setAmount(targetCurrencies.get(p.getCurrency())));
			} catch (ServiceUnavailableException e) {
				throw new ProductServiceException("Currency exchange service unavailable", StatucCode.INTERNAL_SERVICE_ERROR, e);
			}
		}
		
		return super.create(view);
	}
	
	@Override
	protected JpaRepository<ProductEntity, Long> getRepository() {
		return productRepository;
	}

	@Override
	protected String getObjectName() {
		return PRODUCT;
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
