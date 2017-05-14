package com.example.service;

import java.util.List;
import java.util.Map;

import javax.naming.ServiceUnavailableException;

public interface CurrencyExchangeService {
	Map<String, Double> getCurrencyAmountIn(List<String> targetCurrency, double amount) throws ServiceUnavailableException;

	double getCurrencyAmountIn(String sourceCurrency, String targetCurrency, double amount) throws ServiceUnavailableException;
}
