package com.example.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Base for all Endpoint integration test cases. 
 * @author Ahmed.Rabie
 *
 */
public class EndpointTest {

	/**
	 * Convert POJO to json.
	 * @param POJO
	 * @return Json representation.
	 */
	public String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}