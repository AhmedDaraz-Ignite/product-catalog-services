package com.example.endpoint;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.common.ParentIntegrationTest;
import com.example.view.ProductPriceView;
import com.example.view.ProductView;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ProductEndpointIT extends ParentIntegrationTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	@Test
	@WithUserDetails(value = "ahmed")
	public void testGetSingleProductSucess() throws Exception {
		this.mvc.perform(get("/products/{id}", String.valueOf(1l))).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@WithUserDetails(value = "ahmed")
	public void testGetSingleProductFailure() throws Exception {
		this.mvc.perform(get("/products/{id}", String.valueOf(9l))).andDo(print()).andExpect(status().isNotFound());
	}
	
	@Test
	@WithUserDetails(value = "ahmed")
	public void testCreateProductSuccess() throws Exception {
		this.mvc.perform(
                post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(newProductView())))
                .andExpect(status().isCreated());
	}
	
	private ProductView newProductView() {
		ProductView product = new ProductView();
		product.setName("New Product");
		product.setPrice(500d);
		product.setTargetPrices(new ArrayList<ProductPriceView>());
		return product;
	}
}
