package com.exchange.project.services;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface BinanceService {
	
	String ping();
	
	String time();
	
	String exchangeInfo();
	
	Object[] getTrades();

	void AnalizeETHBTC() throws InvalidKeyException, NoSuchAlgorithmException;
	
	void getAccount() throws InvalidKeyException, NoSuchAlgorithmException;

	boolean AnalizeVETBTC() throws InvalidKeyException, NoSuchAlgorithmException;
	
	void AnalizeAllMarket() throws InvalidKeyException, NoSuchAlgorithmException;

}
