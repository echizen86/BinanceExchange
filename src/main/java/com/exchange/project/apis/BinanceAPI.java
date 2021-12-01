package com.exchange.project.apis;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.exchange.project.apis.model.Account;
import com.exchange.project.apis.model.AggTrades;
import com.exchange.project.apis.model.Depth;
import com.exchange.project.apis.model.Order;
import com.exchange.project.apis.model.TickerPrice;
import com.exchange.project.apis.model.Trades;
import com.exchange.project.utils.IConstants;

public class BinanceAPI {

	private String baseURL;
	RestTemplate restTemplate = new RestTemplate();

	public BinanceAPI(String baseURL) {
		this.setBaseURL(baseURL);
	}

	/**
	 * Test connectivity to the Rest API. Response: {}
	 */
	public String ping() {
		String result = restTemplate.getForObject(baseURL + "/ping", String.class);

		return result;
	}

	/**
	 * Test connectivity to the Rest API and get the current server time.
	 */
	public String time() {
		String result = restTemplate.getForObject(baseURL + "/time", String.class);

		return result;
	}

	/**
	 * Get Depth book
	 * 
	 * @param symbol The symbol. Ex: BTCUSD
	 * @param limit  A number, Valid limits:[5, 10, 20, 50, 100, 500, 1000, 5000]
	 */
	public Depth getDepth(String symbol, int limit) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL + "/depth").queryParam("symbol", symbol)
				.queryParam("limit", limit);

		ResponseEntity<Depth> responseEntity = restTemplate.getForEntity(builder.toUriString(), Depth.class);
		Depth depthBook = responseEntity.getBody();

		return depthBook;
	}

	/**
	 * Get recent trades
	 * 
	 * @param symbol
	 * @param limit  Default 500; max 1000
	 * @return
	 */
	public Trades getRecentTrades(String symbol, int limit) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL + "/trades")
				.queryParam("symbol", symbol).queryParam("limit", limit);

		ResponseEntity<Trades> responseEntity = restTemplate.getForEntity(builder.toUriString(), Trades.class);
		Trades trades = responseEntity.getBody();

		return trades;
	}

	/**
	 * Get compressed, aggregate trades. Trades that fill at the time, from the same
	 * order, with the same price will have the quantity aggregated.
	 * 
	 * @param symbol The symbol. Ex: ETHBTC
	 * @param limit  Default 500; max 1000.
	 */
	public AggTrades getAggTrades(String symbol, int limit) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL + "/aggTrades")
				.queryParam("symbol", symbol).queryParam("limit", limit);

		ResponseEntity<AggTrades[]> responseEntity = restTemplate.getForEntity(builder.toUriString(),
				AggTrades[].class);
		AggTrades[] objects = responseEntity.getBody();

		AggTrades trade = (AggTrades) objects[objects.length - 1];

		return trade;
	}

	/**
	 * Latest price for a symbol
	 * 
	 * @param symbol
	 * @return
	 */
	public TickerPrice getPrice(String symbol) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL + "/ticker/price").queryParam("symbol",
				symbol);

		ResponseEntity<TickerPrice> responseEntity = restTemplate.getForEntity(builder.toUriString(),
				TickerPrice.class);
		TickerPrice price = responseEntity.getBody();

		return price;
	}

	// not complete yet
	public Account getAccount() throws InvalidKeyException, NoSuchAlgorithmException {
		Long timestamp = new Date().getTime();
		String params = "timestamp=" + timestamp.toString();

		String signature = this.generateHmac256(params, IConstants.secretKey.getBytes());
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL + "/account ").queryParam("timestamp",
				timestamp.toString());

		HttpHeaders headers = new HttpHeaders();
		headers.add("X-MBX-APIKEY", IConstants.apiKey);

		HttpEntity request = new HttpEntity(headers);

		ResponseEntity<Account> responseEntity = restTemplate.getForEntity(builder.toUriString(), Account.class);
		Account object = responseEntity.getBody();

		return object;
	}

	/**
	 * Send in a new order.
	 * 
	 * @param symbol        The symbol
	 * @param side          BUY or SELL
	 * @param quantity      MARKET orders using the quantity field specifies the
	 *                      amount of the base asset the user wants to buy or sell
	 *                      at the market price.
	 * @param quoteOrderQty MARKET orders using quoteOrderQty specifies the amount
	 *                      the user wants to spend (when buying) or receive (when
	 *                      selling) the quote asset; the correct quantity will be
	 *                      determined based on the market liquidity and
	 *                      quoteOrderQty.
	 * @param recvWindow    The value cannot be greater than 60000
	 * @param apiKey        ApiKey
	 * @param secretKey     SecretKey
	 */
	public Order placeMarketOrder(String symbol, String side, String quantity, String quoteOrderQty, String recvWindow,
			String apiKey, String secretKey) throws InvalidKeyException, NoSuchAlgorithmException {

		Long timestamp = new Date().getTime();

		UriComponentsBuilder builder;

		if (quoteOrderQty.isBlank()) {
			String params = "symbol=" + symbol + "&side=" + side + "&type=MARKET" + "&quantity=" + quantity
					+ "&recvWindow=" + recvWindow + "&timestamp=" + timestamp.toString();

			String signature = this.generateHmac256(params, secretKey.getBytes());

			builder = UriComponentsBuilder.fromHttpUrl(baseURL + "/order").queryParam("symbol", symbol)
					.queryParam("side", side).queryParam("type", "MARKET").queryParam("quantity", quantity)
					.queryParam("recvWindow", recvWindow).queryParam("timestamp", timestamp.toString())
					.queryParam("signature", signature);
		} else {
			String params = "symbol=" + symbol + "&side=" + side + "&type=MARKET" + "&quoteOrderQty=" + quoteOrderQty
					+ "&recvWindow=" + recvWindow + "&timestamp=" + timestamp.toString();

			String signature = this.generateHmac256(params, secretKey.getBytes());

			builder = UriComponentsBuilder.fromHttpUrl(baseURL + "/order").queryParam("symbol", symbol)
					.queryParam("side", side).queryParam("type", "MARKET").queryParam("quoteOrderQty", quoteOrderQty)
					.queryParam("recvWindow", recvWindow).queryParam("timestamp", timestamp.toString())
					.queryParam("signature", signature);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("X-MBX-APIKEY", apiKey);

		HttpEntity request = new HttpEntity(headers);

		ResponseEntity<Order> responseEntity = restTemplate.postForEntity(builder.toUriString(), request, Order.class);

		Order order = responseEntity.getBody();

		return order;
	}

	private String generateHmac256(String message, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
		byte[] bytes = hmac("HmacSHA256", key, message.getBytes());
		return bytesToHex(bytes);
	}

	private byte[] hmac(String algorithm, byte[] key, byte[] message)
			throws NoSuchAlgorithmException, InvalidKeyException {
		Mac mac = Mac.getInstance(algorithm);
		mac.init(new SecretKeySpec(key, algorithm));
		return mac.doFinal(message);
	}

	private String bytesToHex(byte[] bytes) {
		final char[] hexArray = "0123456789abcdef".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0, v; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

}
