package com.exchange.project.apis.model;

import java.util.List;

public class Account {
	
	private double makerCommission;
	
	private double takerCommission;
	
	private double buyerCommission;
	
	private double sellerCommission;
	
	private boolean canTrade;
	
	private boolean canWithdraw;
	
	private boolean canDeposit;
	
	private Long updateTime;
	
	private String accountType;
	
	private List<AccountBalance> balances;
	
	private String[] permissions;

	public double getMakerCommission() {
		return makerCommission;
	}

	public void setMakerCommission(double makerCommission) {
		this.makerCommission = makerCommission;
	}

	public double getTakerCommission() {
		return takerCommission;
	}

	public void setTakerCommission(double takerCommission) {
		this.takerCommission = takerCommission;
	}

	public double getBuyerCommission() {
		return buyerCommission;
	}

	public void setBuyerCommission(double buyerCommission) {
		this.buyerCommission = buyerCommission;
	}

	public double getSellerCommission() {
		return sellerCommission;
	}

	public void setSellerCommission(double sellerCommission) {
		this.sellerCommission = sellerCommission;
	}

	public boolean isCanTrade() {
		return canTrade;
	}

	public void setCanTrade(boolean canTrade) {
		this.canTrade = canTrade;
	}

	public boolean isCanWithdraw() {
		return canWithdraw;
	}

	public void setCanWithdraw(boolean canWithdraw) {
		this.canWithdraw = canWithdraw;
	}

	public boolean isCanDeposit() {
		return canDeposit;
	}

	public void setCanDeposit(boolean canDeposit) {
		this.canDeposit = canDeposit;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public List<AccountBalance> getBalances() {
		return balances;
	}

	public void setBalances(List<AccountBalance> balances) {
		this.balances = balances;
	}

	public String[] getPermissions() {
		return permissions;
	}

	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}
	
	
}
