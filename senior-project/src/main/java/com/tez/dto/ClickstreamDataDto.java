package com.tez.dto;

public class ClickstreamDataDto {
	private String sessionId;
//	private Integer homepage;
	private Integer productRelated;
//	private Double homepageDuration;
	private Integer administrative;
	private String currentPage;
	private Double productRelatedDuration;
	private Double administrativeDuration;
	private String revenue;
	private Double informativeDuration;
	private Integer informative;
	
	public ClickstreamDataDto() {
		
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

//	public Integer getHomepage() {
//		return homepage;
//	}
//
//	public void setHomepage(Integer homepage) {
//		this.homepage = homepage;
//	}

	public Integer getProductRelated() {
		return productRelated;
	}

	public void setProductRelated(Integer productRelated) {
		this.productRelated = productRelated;
	}

//	public Double getHomepageDuration() {
//		return homepageDuration;
//	}
//
//	public void setHomepageDuration(Double homepageDuration) {
//		this.homepageDuration = homepageDuration;
//	}

	public Integer getAdministrative() {
		return administrative;
	}

	public void setAdministrative(Integer administrative) {
		this.administrative = administrative;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public Double getProductRelatedDuration() {
		return productRelatedDuration;
	}

	public void setProductRelatedDuration(Double productRelatedDuration) {
		this.productRelatedDuration = productRelatedDuration;
	}

	public Double getAdministrativeDuration() {
		return administrativeDuration;
	}

	public void setAdministrativeDuration(Double administrativeDuration) {
		this.administrativeDuration = administrativeDuration;
	}

	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	public Double getInformativeDuration() {
		return informativeDuration;
	}

	public void setInformativeDuration(Double informativeDuration) {
		this.informativeDuration = informativeDuration;
	}

	public Integer getInformative() {
		return informative;
	}

	public void setInformative(Integer informative) {
		this.informative = informative;
	}

	@Override
	public String toString() {
		return "ClickstreamDataDto [sessionId=" + sessionId + ", productRelated=" + productRelated + ", administrative="
				+ administrative + ", currentPage=" + currentPage + ", productRelatedDuration=" + productRelatedDuration
				+ ", administrativeDuration=" + administrativeDuration + ", revenue=" + revenue
				+ ", informativeDuration=" + informativeDuration + ", informative=" + informative + "]";
	}
	
	
	
	

	

	
}
