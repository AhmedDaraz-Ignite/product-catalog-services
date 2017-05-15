package com.example.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.category.CategoryView;
import com.example.common.endpoint.BaseEndpoint;
import com.example.common.types.ServiceResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * ProductEndpoint
 * @author Ahmed.Rabie
 *
 */
@Api("products")
@RestController
@RequestMapping("/products")
public class ProductEndpoint extends BaseEndpoint<ProductEntity, ProductView> {

	private static final String PRODUCT_VIEW = "ProductView";
	private static final String PRODUCT_ENDPOINT = "ProductEndpoint";
	private ProductService productService;
	
	@Autowired
	public ProductEndpoint(ProductService productService) {
		super(productService);
		this.productService = productService;
	}

	/**
	 * Retrieve product category for given product Id
	 * @param product Id
	 * @return Product category
	 */
	@ApiOperation(value = "Retrieves product category for givin product Id", notes = "Returns product category")
	@RequestMapping(value = "/{id}/category", method = RequestMethod.GET, produces = "application/json")
	public ServiceResponse<CategoryView> getProductCategory(@PathVariable("id") @ApiParam(value = "Product Id", required = true, type = "Integer") int id) {
		CategoryView categoryView = productService.getProductCategory(Long.valueOf(id));
		return ServiceResponse.buildGoodResponse(categoryView);
	}

	@Override
	protected String getEndpointName() {
		return PRODUCT_ENDPOINT;
	}

	@Override
	protected String getObjectName() {
		return PRODUCT_VIEW;
	}

}
