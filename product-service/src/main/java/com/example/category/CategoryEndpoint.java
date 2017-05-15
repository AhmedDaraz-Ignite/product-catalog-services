package com.example.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.endpoint.BaseEndpoint;
import com.example.common.types.ServiceResponse;
import com.example.product.ProductService;
import com.example.product.ProductView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * CategoryEndpoint
 * @author Ahmed.Rabie
 *
 */
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

	/**
	 * List products under for given category Id.
	 * @param category Id
	 * @return List of product resources
	 */
	@ApiOperation(value = "Retrieves products category for givin category Id", notes = "Returns products")
	@RequestMapping(value = "/{id}/products", method = RequestMethod.GET, produces = "application/json")
	public ServiceResponse<List<ProductView>> listProducts(@PathVariable("id") @ApiParam(value = "Category Id", required = true, type = "Integer") int id) {
		List<ProductView> views = productService.listProductsByCategoryId(Long.valueOf(id));
		return ServiceResponse.buildGoodResponse(views);
	}
	
	@Override
	protected String getEndpointName() {
		return "CategoryEndpoint";
	}

	@Override
	protected String getObjectName() {
		return "CategoryView";
	}
}
