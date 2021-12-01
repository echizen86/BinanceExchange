package com.exchange.project.apis.model;

public class Trade {
	
	private Long id;
	
	private double price;
	
	private double qty;
	
	private double quoteQty;
	
	private Long time;
	
	private boolean isBuyerMaker;
	
	private boolean isBestMatch;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public double getQuoteQty() {
		return quoteQty;
	}

	public void setQuoteQty(double quoteQty) {
		this.quoteQty = quoteQty;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public boolean isBuyerMaker() {
		return isBuyerMaker;
	}

	public void setBuyerMaker(boolean isBuyerMaker) {
		this.isBuyerMaker = isBuyerMaker;
	}

	public boolean isBestMatch() {
		return isBestMatch;
	}

	public void setBestMatch(boolean isBestMatch) {
		this.isBestMatch = isBestMatch;
	}
	
	

}
