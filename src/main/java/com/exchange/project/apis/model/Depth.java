package com.exchange.project.apis.model;

public class Depth {
	
	private int lastUpdateId;
	
	private String[][]  bids;
	
	private String[][] asks;

	public int getLastUpdateId() {
		return lastUpdateId;
	}

	public void setLastUpdateId(int lastUpdateId) {
		this.lastUpdateId = lastUpdateId;
	}

	public String[][] getBids() {
		return bids;
	}

	public void setBids(String[][] bids) {
		this.bids = bids;
	}

	public String[][] getAsks() {
		return asks;
	}

	public void setAsks(String[][] asks) {
		this.asks = asks;
	}
	
	

}
