package com.exchange.project.apis.model;

import java.util.List;

public class Order {
	private String symbol;
	
	private int orderId;
	
	private int orderListId;
	
	private Long transactTime;
	
	private double price;
	
	private double origQty;
	
	private double executedQty;
	
	private double cummulativeQuoteQty;
	
	private String status;
	
	private String timeInForce;
	
	private String type;
	
	private String side;
	
	private List<Fill> fills;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderListId() {
		return orderListId;
	}

	public void setOrderListId(int orderListId) {
		this.orderListId = orderListId;
	}

	public Long getTransactTime() {
		return transactTime;
	}

	public void setTransactTime(Long transactTime) {
		this.transactTime = transactTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getOrigQty() {
		return origQty;
	}

	public void setOrigQty(double origQty) {
		this.origQty = origQty;
	}

	public double getExecutedQty() {
		return executedQty;
	}

	public void setExecutedQty(double executedQty) {
		this.executedQty = executedQty;
	}

	public double getCummulativeQuoteQty() {
		return cummulativeQuoteQty;
	}

	public void setCummulativeQuoteQty(double cummulativeQuoteQty) {
		this.cummulativeQuoteQty = cummulativeQuoteQty;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTimeInForce() {
		return timeInForce;
	}

	public void setTimeInForce(String timeInForce) {
		this.timeInForce = timeInForce;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public List<Fill> getFills() {
		return fills;
	}

	public void setFills(List<Fill> fills) {
		this.fills = fills;
	}
	
	
		
	
}
