package com.exchange.project.apis.model;

public class AccountBalance {
	
	private String asset;
	
	private double free;
	
	private double locked;

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public double getFree() {
		return free;
	}

	public void setFree(double free) {
		this.free = free;
	}

	public double getLocked() {
		return locked;
	}

	public void setLocked(double locked) {
		this.locked = locked;
	}
	
	

}
