package com.example.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.category.CategoryView;
import com.example.common.exception.ProductServiceException;
import com.example.common.service.BaseServiceImpl;
import com.example.common.types.StatucCode;
import com.example.common.util.BeanMapperUtil;
import com.example.user.CurrencyExchangeService;

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
		
		ProductEntity product = productRepository.findOne(productId);
		if (Objects.isNull(product)) {
			throw new ProductServiceException("Resource with Id {0} not found", StatucCode.RESOURCE_NOT_FOUND, productId);
		}
		BeanMapperUtil.getMapper().map(product.getCategory(), categoryView);
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

	@Override
	protected void fixEntityAssociations(ProductEntity entity) {
		entity.getTargetPrices().forEach(price -> price.setProduct(entity));
	}


}
