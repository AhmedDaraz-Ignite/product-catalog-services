package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

	private RestTemplate restTemplate;
	
	@Autowired
	public CurrencyExchangeServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Double> getCurrencyAmountIn(List<String> targetCurrency, double amount) throws ServiceUnavailableException {
		Map<String, String> params = new HashMap<>();
	    
	    StringBuilder prices = new StringBuilder();
	    targetCurrency.forEach(price -> prices.append(price));
	    params.put("symbols", prices.toString());
		
		Map<String, Object> response = restTemplate.getForObject("http://api.fixer.io/latest", Map.class, params);
		
		Map<String, Double> exchangeRates = (Map<String, Double>) response.get("rates");
		Map<String, Double> result = new HashMap<>();
		
		targetCurrency.forEach(p -> result.put(p, exchangeRates.get(p) * amount));
		
		return result;
	}

	@Override
	public double getCurrencyAmountIn(String sourceCurrency, String targetCurrency, double amount)
			throws ServiceUnavailableException {
		// TODO Auto-generated method stub
		return 0;
	}


}
