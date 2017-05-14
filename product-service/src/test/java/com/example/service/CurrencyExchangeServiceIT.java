package com.example.service;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Map;

import javax.naming.ServiceUnavailableException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.common.ParentIntegrationTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyExchangeServiceIT extends ParentIntegrationTest {
	
	@Autowired
	private CurrencyExchangeService currencyExchangeService;
	
	@Test
	public void testGetCurrencyAmountInSuccess() throws ServiceUnavailableException {
		Map<String, Double> rates = currencyExchangeService.getCurrencyAmountIn(Arrays.asList("USD"), 1);
		assertNotNull(rates);
	}
}
