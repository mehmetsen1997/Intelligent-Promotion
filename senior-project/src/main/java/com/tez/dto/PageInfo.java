package com.tez.dto;

public class PageInfo {
	private String page;
	private Double bounceRate;
	private Double exitRate;
	private Double pageValue;
	
	public PageInfo(String page, Double bounceRate, Double exitRate, Double pageValue) {
		this.page = page;
		this.bounceRate = bounceRate;
		this.exitRate = exitRate;
		this.pageValue = pageValue;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Double getBounceRate() {
		return bounceRate;
	}

	public void setBounceRate(Double bounceRate) {
		this.bounceRate = bounceRate;
	}

	public Double getExitRate() {
		return exitRate;
	}

	public void setExitRate(Double exitRate) {
		this.exitRate = exitRate;
	}

	public Double getPageValue() {
		return pageValue;
	}

	public void setPageValue(Double pageValue) {
		this.pageValue = pageValue;
	}

	@Override
	public String toString() {
		return "PageInfo [page=" + page + ", bounceRate=" + bounceRate + ", exitRate=" + exitRate + ", pageValue="
				+ pageValue + "]";
	}
	
	
	
}
