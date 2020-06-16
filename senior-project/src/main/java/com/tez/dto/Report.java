package com.tez.dto;

public class Report {
	private Integer totalPurchase;
	private Integer totalPurchaseWithPromotion;
	private Integer totalSession;
	
	public Report() {
		
	}

	public Report(Integer totalPurchase, Integer totalPurchaseWithPromotion, Integer totalSession) {
		this.totalPurchase = totalPurchase;
		this.totalPurchaseWithPromotion = totalPurchaseWithPromotion;
		this.totalSession = totalSession;
	}

	public Integer getTotalPurchase() {
		return totalPurchase;
	}

	public void setTotalPurchase(Integer totalPurchase) {
		this.totalPurchase = totalPurchase;
	}

	public Integer getTotalPurchaseWithPromotion() {
		return totalPurchaseWithPromotion;
	}

	public void setTotalPurchaseWithPromotion(Integer totalPurchaseWithPromotion) {
		this.totalPurchaseWithPromotion = totalPurchaseWithPromotion;
	}

	public Integer getTotalSession() {
		return totalSession;
	}

	public void setTotalSession(Integer totalSession) {
		this.totalSession = totalSession;
	}


	
	
}
