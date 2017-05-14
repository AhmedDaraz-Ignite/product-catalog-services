package com.example.service;

import java.util.List;
import java.util.Map;

import javax.naming.ServiceUnavailableException;

/**
 * Currency exchange service, it wrapes the actual service that provides currency rates.
 * @author Ahmed.Rabie
 *
 */
public interface CurrencyExchangeService {
	Map<String, Double> getCurrencyAmountIn(List<String> targetCurrency, double amount) throws ServiceUnavailableException;
}
