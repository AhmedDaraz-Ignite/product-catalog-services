package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Currency exchange service implementation.
 * @author Ahmed.Rabie
 *
 */
@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

	/**
	 * [http://api.fixer.io/latest] is used as currency exchange provider.
	 */
	@Value("${api.currency-exchange.url}")
	private String currencyExchangeUrl;
	
	private RestTemplate restTemplate;
	
	@Autowired
	public CurrencyExchangeServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	
	/**
	 *By providing required currency symbols [USD, GBP, EGP, etc..] method will return map with different proce values based on the given currency symbols.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Double> getCurrencyAmountIn(List<String> targetCurrency, double amount) throws ServiceUnavailableException {
		Map<String, String> params = new HashMap<>();
	    
	    StringBuilder prices = new StringBuilder();
	    targetCurrency.forEach(price -> prices.append(price));
	    params.put("symbols", prices.toString());
		
		Map<String, Object> response = restTemplate.getForObject(currencyExchangeUrl, Map.class, params);
		
		Map<String, Double> exchangeRates = (Map<String, Double>) response.get("rates");
		Map<String, Double> result = new HashMap<>();
		
		targetCurrency.forEach(p -> result.put(p, exchangeRates.get(p) * amount));
		
		return result;
	}

}
