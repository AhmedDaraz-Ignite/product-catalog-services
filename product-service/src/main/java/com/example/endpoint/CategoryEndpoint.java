package com.example.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.CategoryEntity;
import com.example.service.CategoryService;
import com.example.service.ProductService;
import com.example.view.CategoryView;
import com.example.view.ProductView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("category")
@RestController
@RequestMapping("/category")
public class CategoryEndpoint extends BaseEndpoint<CategoryEntity, CategoryView> {

	private ProductService productService;
	
	@Autowired
	public CategoryEndpoint(CategoryService baseService, ProductService productService) {
		super(baseService);
		this.productService = productService;
	}

	@ApiOperation(value = "Retrieves products category for givin category Id", notes = "Returns products")
	@RequestMapping(value = "/{id}/products", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<ProductView>> listProducts(@PathVariable("id") @ApiParam(value = "Category Id", required = true, type = "Integer") int id) {
		List<ProductView> views = productService.listProductsByCategoryId(Long.valueOf(id));
		return ResponseEntity.status(HttpStatus.OK).body(views);
	}
	
	@Override
	protected String getEndpointName() {
		return CategoryEndpoint.class.getName();
	}

}
