package com.example.endpoint;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.common.ParentIntegrationTest;
import com.example.service.CategoryService;
import com.example.view.CategoryView;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CategoryEndpointIT extends ParentIntegrationTest {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private CategoryService categoryService;
	
	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}
	
	@Test
	@WithUserDetails(value = "ahmed")
	public void testGetSingleProductSucess() throws Exception {
		this.mvc.perform(get("/category")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	@WithUserDetails(value = "ahmed")
	public void testDeleteCategorySuccess() throws Exception {
		CategoryView newCategory = categoryService.create(newCategoryView());
		this.mvc.perform(delete("/category/{id}", String.valueOf(newCategory.getId()))).andDo(print())
				.andExpect(status().isOk());
	}
	
	private CategoryView newCategoryView() {
		CategoryView category = new CategoryView();
		category.setName("New Category");
		category.setDescription("New category Description");
		return category;
	}
}
