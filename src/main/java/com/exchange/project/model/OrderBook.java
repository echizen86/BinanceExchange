package com.exchange.project.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBook {
	
	private String lastUpdateId;
	
	private Object[][] bids;
	
	private Object asks;

	public String getLastUpdateId() {
		return lastUpdateId;
	}

	public void setLastUpdateId(String lastUpdateId) {
		this.lastUpdateId = lastUpdateId;
	}

	public Object[][] getBids() {
		return bids;
	}

	public void setBids(Object[][] bids) {
		this.bids = bids;
	}

	public Object getAsks() {
		return asks;
	}

	public void setAsks(Object asks) {
		this.asks = asks;
	}
	
}
