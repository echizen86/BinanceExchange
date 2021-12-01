package com.exchange.project.services.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.exchange.project.apis.BinanceAPI;
import com.exchange.project.apis.model.Account;
import com.exchange.project.apis.model.Depth;
import com.exchange.project.apis.model.Trade;
import com.exchange.project.services.BinanceService;
import com.exchange.project.utils.IConstants;

@Service
public class BinanceServiceImpl implements BinanceService {
	
	@Autowired
	BinanceAPI api = new BinanceAPI(IConstants.BaseURLv3US);

	@Override
	public String ping() {
		return api.ping();
	}

	@Override
	public String time() {
		return api.time();
	}

	@Override
	public String exchangeInfo() {
		RestTemplate rest = new RestTemplate();
		String result = rest.getForObject(IConstants.BaseURLv3 + "/exchangeInfo", String.class);
		return result;
	}

	@Override
	public Object[] getTrades() {
		RestTemplate restTemplate = new RestTemplate();

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(IConstants.BaseURLv3 + "/trades")
				.queryParam("symbol", "ETHBTC");

		ResponseEntity<Trade[]> responseEntity = restTemplate.getForEntity(builder.toUriString(), Trade[].class);
		Trade[] objects = responseEntity.getBody();

		Trade trade = (Trade) objects[objects.length - 1];

		System.out.println(trade.getPrice());

		return objects;
	}
	
	@Override
	public void AnalizeAllMarket() throws InvalidKeyException, NoSuchAlgorithmException {
		
		Depth depthBookAsset1;
		Depth depthBookExchangeAssets;
		Depth depthBookAsset2;
		double capitalInitial = 100;
		
		String asset1Symbol = "";
		String exchangeAssets = "";
		String asset2Symbol = "";
		
		// ETH
		asset1Symbol = "BTCUSD";
		exchangeAssets = "ETHBTC";
		asset2Symbol = "ETHUSD";
		
		depthBookAsset1 = api.getDepth(asset1Symbol, 10);
		depthBookExchangeAssets = api.getDepth(exchangeAssets, 10);
		depthBookAsset2 = api.getDepth(asset2Symbol, 10);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.exchangeAssets(capitalInitial, depthBookAsset1, depthBookAsset2, depthBookExchangeAssets, asset1Symbol,
				exchangeAssets, asset2Symbol);
		
		
		// BNB
		asset1Symbol = "BTCUSD";
		exchangeAssets = "BNBBTC";
		asset2Symbol = "BNBUSD";
		
		depthBookAsset1 = api.getDepth(asset1Symbol, 10);
		depthBookExchangeAssets = api.getDepth(exchangeAssets, 10);
		depthBookAsset2 = api.getDepth(asset2Symbol, 10);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.exchangeAssets(capitalInitial, depthBookAsset1, depthBookAsset2, depthBookExchangeAssets, asset1Symbol,
				exchangeAssets, asset2Symbol);
		
		// ADA
		asset1Symbol = "BTCUSD";
		exchangeAssets = "ADABTC";
		asset2Symbol = "ADAUSD";
		
		depthBookAsset1 = api.getDepth(asset1Symbol, 10);
		depthBookExchangeAssets = api.getDepth(exchangeAssets, 10);
		depthBookAsset2 = api.getDepth(asset2Symbol, 10);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.exchangeAssets(capitalInitial, depthBookAsset1, depthBookAsset2, depthBookExchangeAssets, asset1Symbol,
				exchangeAssets, asset2Symbol);
		
		// LTC
		asset1Symbol = "BTCUSD";
		exchangeAssets = "LTCBTC";
		asset2Symbol = "LTCUSD";
		
		depthBookAsset1 = api.getDepth(asset1Symbol, 10);
		depthBookExchangeAssets = api.getDepth(exchangeAssets, 10);
		depthBookAsset2 = api.getDepth(asset2Symbol, 10);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.exchangeAssets(capitalInitial, depthBookAsset1, depthBookAsset2, depthBookExchangeAssets, asset1Symbol,
				exchangeAssets, asset2Symbol);
		
		// VET
		asset1Symbol = "BTCUSD";
		exchangeAssets = "VETBTC";
		asset2Symbol = "VETUSD";
		
		depthBookAsset1 = api.getDepth(asset1Symbol, 10);
		depthBookExchangeAssets = api.getDepth(exchangeAssets, 10);
		depthBookAsset2 = api.getDepth(asset2Symbol, 10);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.exchangeAssets(capitalInitial, depthBookAsset1, depthBookAsset2, depthBookExchangeAssets, asset1Symbol,
				exchangeAssets, asset2Symbol);
		
	}

	@Override
	public void AnalizeETHBTC() throws InvalidKeyException, NoSuchAlgorithmException {

		// symbols for assets
		String asset1Symbol = "BTCUSD";
		String exchangeAssets = "ETHBTC";
		String asset2Symbol = "ETHUSD";

		// GET BTC-USD depth book
		Depth depthBookAsset1 = api.getDepth(asset1Symbol, 10);

		// GET BTC-VET depth book
		Depth depthBookExchangeAssets = api.getDepth(exchangeAssets, 10);

		// GET VET-USD depth book
		Depth depthBookAsset2 = api.getDepth(asset2Symbol, 10);

		// get time to get response from APIs
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// convert USD-BTC-VET-USD
		double capitalInitial = 1000;

		boolean success = this.exchangeAssets(capitalInitial, depthBookAsset1, depthBookAsset2, depthBookExchangeAssets, asset1Symbol,
				exchangeAssets, asset2Symbol);
		
//		return success;
	}

	@Override
	public boolean AnalizeVETBTC() throws InvalidKeyException, NoSuchAlgorithmException {

		// symbols for assets
		String asset1Symbol = "BTCUSD";
		String exchangeAssets = "VETBTC";
		String asset2Symbol = "VETUSD";

		// GET BTC-USD depth book
		Depth depthBookAsset1 = api.getDepth(asset1Symbol, 10);

		// GET BTC-VET depth book
		Depth depthBookExchangeAssets = api.getDepth(exchangeAssets, 10);

		// GET VET-USD depth book
		Depth depthBookAsset2 = api.getDepth(asset2Symbol, 10);

		// get time to get response from APIs
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// convert USD-BTC-VET-USD
		double capitalInitial = 100;

		boolean success = this.exchangeAssets(capitalInitial, depthBookAsset1, depthBookAsset2, depthBookExchangeAssets, asset1Symbol,
				exchangeAssets, asset2Symbol);
		
		return success;

//		System.out.println(result);
	}

	private boolean exchangeAssets(double capital, Depth asset1, Depth asset2, Depth depthBookExchangeAssets,
			String asset1Symbol, String exchangeAssets, String asset2Symbol)
			throws InvalidKeyException, NoSuchAlgorithmException {
		
		boolean success = false;

		// working with BigDecimals
		BigDecimal cap = BigDecimal.valueOf(capital);
		BigDecimal asset2GetAsk = BigDecimal.valueOf(Double.parseDouble(asset2.getAsks()[1][0]));
		BigDecimal dBEAgetBids = BigDecimal.valueOf(Double.parseDouble(depthBookExchangeAssets.getBids()[1][0]));
		BigDecimal asset1getAsk = BigDecimal.valueOf(Double.parseDouble(asset1.getAsks()[1][0]));
		BigDecimal dBEAgetAsk = BigDecimal.valueOf(Double.parseDouble(depthBookExchangeAssets.getAsks()[1][0]));
		BigDecimal asset1getBids = BigDecimal.valueOf(Double.parseDouble(asset1.getBids()[1][0]));

		BigDecimal bA2 = cap.divide(asset2GetAsk, MathContext.DECIMAL32);
		BigDecimal eA2T1 = bA2.multiply(dBEAgetBids, MathContext.DECIMAL32);
		BigDecimal sellA1 = eA2T1.multiply(asset1getAsk);
		
		double a = 100.1;
		if (sellA1.compareTo(BigDecimal.valueOf(a)) > 0) {
			System.out.println("Buying 2" + exchangeAssets + " -- " + sellA1);
		}


		// working with BigDecimals
//		BigDecimal bA1 = cap.divide(asset1getAsk, MathContext.DECIMAL32);
//		BigDecimal eA1TA2 = bA1.divide(dBEAgetAsk, MathContext.DECIMAL32);
//		BigDecimal sellA2 = eA1TA2.multiply(asset2GetAsk, MathContext.DECIMAL32);
//
//		System.out.println("Buying Asset 2: " + sellA2);

		/** 
		if (sellA1.compareTo(BigDecimal.valueOf(1001.10)) > 0) {
			System.out.println("Place order 1");
			Order orderBuyAsset2 = api.placeMarketOrder(asset2Symbol, "BUY", "", String.valueOf(capital), "5000");
			// place time
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (orderBuyAsset2.getStatus().equalsIgnoreCase("FILLED")) {
				System.out.println("Place order 2");
				System.out.println(orderBuyAsset2.getOrigQty());
				System.out.println(orderBuyAsset2.getExecutedQty());
				System.out.println(orderBuyAsset2.getCummulativeQuoteQty());
				double value1 = Double.parseDouble(
						new DecimalFormat("#.######").format(orderBuyAsset2.getOrigQty()));
				Order orderExchangeAsset1ToAsset2 = api.placeMarketOrder(exchangeAssets, "SELL", String.valueOf(value1), "", "5000");
				// place time
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (orderExchangeAsset1ToAsset2.getStatus().equalsIgnoreCase("FILLED")) {
					System.out.println("Place order 3");
					System.out.println(orderExchangeAsset1ToAsset2.getOrigQty());
					System.out.println(orderExchangeAsset1ToAsset2.getExecutedQty());
					System.out.println(orderExchangeAsset1ToAsset2.getCummulativeQuoteQty());
					// take only 6 digits decimal
					double value = Double.parseDouble(
							new DecimalFormat("#.######").format(orderExchangeAsset1ToAsset2.getCummulativeQuoteQty()));
					Order orderSellAsset2 = api.placeMarketOrder(asset1Symbol, "SELL", String.valueOf(value), "", "5000");
					// place time
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Finished");
					success = true;
				}
			}
		}
		
		*/

		// working with BigDecimals
		BigDecimal bA1 = cap.divide(asset1getAsk, MathContext.DECIMAL32);
		BigDecimal eA1TA2 = bA1.divide(dBEAgetAsk, MathContext.DECIMAL32);
		BigDecimal sellA2 = eA1TA2.multiply(asset2GetAsk, MathContext.DECIMAL32);
		
		if (sellA2.compareTo(BigDecimal.valueOf(a)) > 0) {
			System.out.println("Buying 1" + exchangeAssets + " -- " + sellA2);
		}

		/**
		if (sellA2.compareTo(BigDecimal.valueOf(1001.10)) > 0) {

			System.out.println("Place order 1 differ1");
			Order orderBuyAsset1 = api.placeMarketOrder(asset1Symbol, "BUY", "", String.valueOf(capital), "5000");
			// place time
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (orderBuyAsset1.getStatus().equalsIgnoreCase("FILLED")) {
				System.out.println("Place order 2");
				System.out.println(orderBuyAsset1.getOrigQty());
				System.out.println(orderBuyAsset1.getExecutedQty());
				System.out.println(orderBuyAsset1.getCummulativeQuoteQty());
				double value1 = Double.parseDouble(
						new DecimalFormat("#.######").format(orderBuyAsset1.getCummulativeQuoteQty()));
				Order orderExchangeAsset1ToAsset2 = api.placeMarketOrder(exchangeAssets, "SELL", "",
						String.valueOf(value1), "5000");
				// place time
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (orderExchangeAsset1ToAsset2.getStatus().equalsIgnoreCase("FILLED")) {
					System.out.println("Place order 3");
					System.out.println(orderExchangeAsset1ToAsset2.getOrigQty());
					System.out.println(orderExchangeAsset1ToAsset2.getExecutedQty());
					System.out.println(orderExchangeAsset1ToAsset2.getCummulativeQuoteQty());
					// take only 6 digits decimal
					double value = Double.parseDouble(
							new DecimalFormat("#.######").format(orderExchangeAsset1ToAsset2.getOrigQty()));
					Order orderSellAsset2 = api.placeMarketOrder(asset2Symbol, "SELL", String.valueOf(value), "",
							"5000");
					// place time
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Finished");
					success = true;
				}
			}
		}
		
		*/
		
		return success;

	}

	private double exchangeAssets123(double capital, Depth asset1, Depth asset2, Depth depthBookExchangeAssets,
			String asset1Symbol, String exchangeAssets, String asset2Symbol)
			throws InvalidKeyException, NoSuchAlgorithmException {

		boolean side = false;

		// buy asset1
		double buyAsset1 = capital / Double.parseDouble(asset1.getAsks()[2][0]);
		// buy asset2 with asset1
		double exchangeAsset1ToAsset2 = buyAsset1 / Double.parseDouble(depthBookExchangeAssets.getAsks()[2][0]);
		// sell asset2
		double sellAsset2 = exchangeAsset1ToAsset2 * Double.parseDouble(asset2.getBids()[2][0]);

		double resultBuyingAsset1 = sellAsset2 - capital;

		System.out.println("DIFFER1: " + resultBuyingAsset1);

		// place orders
		if (resultBuyingAsset1 > 10) {
			System.out.println("Place order 1 differ1");
//			Order orderBuyAsset1 = api.placeMarketOrder(asset1Symbol, "BUY", "", String.valueOf(capital), "5000");
//			// place time
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (orderBuyAsset1.getStatus().equalsIgnoreCase("FILLED")) {
//				System.out.println("Place order 2");
//				Order orderExchangeAsset1ToAsset2 = api.placeMarketOrder(exchangeAssets, "SELL", "",
//						String.valueOf(orderBuyAsset1.getOrigQty()), "5000");
//				// place time
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				if (orderExchangeAsset1ToAsset2.getStatus().equalsIgnoreCase("FILLED")) {
//					System.out.println("Place order 3");
//					Order orderSellAsset2 = api.placeMarketOrder(asset2Symbol, "SELL",
//							String.valueOf(orderExchangeAsset1ToAsset2.getOrigQty()), "", "5000");
//					// place time
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println("Finished");
//				}
//			}
		}

		// working with BigDecimals
		BigDecimal cap = BigDecimal.valueOf(capital);
		BigDecimal asset2GetBid = BigDecimal.valueOf(Double.parseDouble(asset2.getBids()[2][0]));
		BigDecimal bA2 = cap.divide(asset2GetBid, MathContext.DECIMAL32);
		BigDecimal dBEAgetAsk = BigDecimal.valueOf(Double.parseDouble(depthBookExchangeAssets.getAsks()[2][0]));
		BigDecimal eA2T1 = bA2.divide(dBEAgetAsk, MathContext.DECIMAL32);
		BigDecimal asset1getAsk = BigDecimal.valueOf(Double.parseDouble(asset1.getAsks()[2][0]));
		BigDecimal sellA1 = eA2T1.multiply(asset1getAsk);

		// buy asset2
		double buyAsset2 = capital / Double.parseDouble(asset2.getBids()[2][0]);
		buyAsset2 = Double.parseDouble(new DecimalFormat("#.######").format(buyAsset2));
		System.out.println("Compramos " + buyAsset2 + " VET con " + capital + " dolares");
		// buy asset1 with asset2
		double exchangeAsset2ToAsset1 = buyAsset2 / Double.parseDouble(depthBookExchangeAssets.getAsks()[2][0]);
		exchangeAsset2ToAsset1 = Double.parseDouble(new DecimalFormat("#.######").format(exchangeAsset2ToAsset1));
		System.out.println("Cambiamos " + buyAsset2 + " VET a " + exchangeAsset2ToAsset1 + " Bitcoins");
		// sell asset1
		double sellAsset1 = exchangeAsset2ToAsset1 * Double.parseDouble(asset1.getAsks()[2][0]);
		System.out.println("Vendemos " + exchangeAsset2ToAsset1 + " BTC, y obtenemos " + sellAsset1 + " Dolares");

		double resultBuyingAsset2 = sellAsset1 - capital;

		System.out.println("DIFFER2: " + resultBuyingAsset2);

		if (resultBuyingAsset2 > 10) {
			System.out.println("Place order 1");
//			Order orderBuyAsset1 = api.placeMarketOrder(asset2Symbol, "BUY", "", String.valueOf(capital), "5000");
//			// place time
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if (orderBuyAsset1.getStatus().equalsIgnoreCase("FILLED")) {
//				System.out.println("Place order 2");
//				Order orderExchangeAsset1ToAsset2 = api.placeMarketOrder(exchangeAssets, "SELL",
//						String.valueOf(orderBuyAsset1.getOrigQty()), "", "5000");
//				// place time
//				try {
//					Thread.sleep(500);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				if (orderExchangeAsset1ToAsset2.getStatus().equalsIgnoreCase("FILLED")) {
//					System.out.println("Place order 3");
//					// take only 6 digits decimal
//					double value = Double.parseDouble(
//							new DecimalFormat("#.######").format(orderExchangeAsset1ToAsset2.getCummulativeQuoteQty()));
//					System.out.println("getOrigQty: " + orderExchangeAsset1ToAsset2.getOrigQty());
//					System.out.println("getExecutedQty: " + orderExchangeAsset1ToAsset2.getExecutedQty());
//					System.out
//							.println("getCummulativeQuoteQty: " + orderExchangeAsset1ToAsset2.getCummulativeQuoteQty());
//					System.out.println("value: " + value);
//					Order orderSellAsset2 = api.placeMarketOrder(asset1Symbol, "SELL", String.valueOf(value), "",
//							"5000");
//					// place time
//					try {
//						Thread.sleep(500);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println("Finished");
//				}
//			}
		}

		return resultBuyingAsset1;
	}

	@Override
	public void getAccount() throws InvalidKeyException, NoSuchAlgorithmException {
		Account account = api.getAccount();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < account.getBalances().size(); i++) {
			System.out.println(account.getBalances().get(i).getAsset());
			System.out.println(account.getBalances().get(i).getFree());
		}

	}

}
