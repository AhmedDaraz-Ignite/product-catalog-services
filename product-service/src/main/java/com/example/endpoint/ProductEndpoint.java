package com.example.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ProductEntity;
import com.example.service.ProductService;
import com.example.view.CategoryView;
import com.example.view.ProductView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("products")
@RestController
@RequestMapping("/products")
public class ProductEndpoint extends BaseEndpoint<ProductEntity, ProductView> {

	private ProductService productService;
	
	@Autowired
	public ProductEndpoint(ProductService productService) {
		super(productService);
		this.productService = productService;
	}

	@ApiOperation(value = "Retrieves product category for givin product Id", notes = "Returns product category")
	@RequestMapping(value = "/{id}/category", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<CategoryView> getProductCategory(@PathVariable("id") @ApiParam(value = "Product Id", required = true, type = "Integer") int id) {
		CategoryView categoryView = productService.getProductCategory(Long.valueOf(id));
		return ResponseEntity.status(HttpStatus.OK).body(categoryView);
	}

	@Override
	protected String getEndpointName() {
		return ProductEndpoint.class.getName();
	}

}
