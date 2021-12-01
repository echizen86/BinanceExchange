package com.exchange.project.controllers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.project.model.OrderBook;
import com.exchange.project.services.BinanceService;

@RestController
@RequestMapping("binance")
public class BinanceController {
	
	@Autowired
	BinanceService binanceService;
	
	@GetMapping("/ethbtc")
	public void AnalizeETHBTC() throws InvalidKeyException, NoSuchAlgorithmException {
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			binanceService.AnalizeETHBTC();
		}
		System.out.println("********************************");
//		binanceService.AnalizeETHBTC();
	}
	
	@GetMapping("/vetbtc")
	public void AnalizeVETBTC() throws InvalidKeyException, NoSuchAlgorithmException {
		for (int i = 0; i < 5000; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			boolean s = binanceService.AnalizeVETBTC();
			if (s) {
				break;
			}
		}
		System.out.println("********************************");
//		binanceService.AnalizeVETBTC();
	}
	
	@GetMapping("/analize")
	public void Analize() throws InvalidKeyException, NoSuchAlgorithmException {
		for (int i = 0; i < 500; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			binanceService.AnalizeAllMarket();
		}
		System.out.println("********************************");
//		binanceService.AnalizeVETBTC();
	}
	
	@GetMapping("/account")
	public void getAccount() throws InvalidKeyException, NoSuchAlgorithmException {
		binanceService.getAccount();
	}

}
