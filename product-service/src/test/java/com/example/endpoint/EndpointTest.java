package com.example.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EndpointTest {

	public String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}