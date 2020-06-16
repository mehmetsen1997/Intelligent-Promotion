package com.tez.dto;

public class VisitDto {
	private String sessionId;
	private Double purchaseProbability;
	private String currentPath;
	private Double totalTime;
	
	public VisitDto() {
		
	}
	
	
	
	
	public VisitDto(String sessionId, Double purchaseProbability, String currentPath, Double totalTime) {
		this.sessionId = sessionId;
		this.purchaseProbability = purchaseProbability;
		this.currentPath = currentPath;
		this.totalTime = totalTime;
	}


	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Double getPurchaseProbability() {
		return purchaseProbability;
	}
	public void setPurchaseProbability(Double purchaseProbability) {
		this.purchaseProbability = purchaseProbability;
	}
	public String getCurrentPath() {
		return currentPath;
	}
	public void setCurrentPath(String currentPath) {
		this.currentPath = currentPath;
	}
	public Double getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(Double totalTime) {
		this.totalTime = totalTime;
	}

	

}
