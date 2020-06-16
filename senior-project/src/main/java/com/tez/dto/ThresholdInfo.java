package com.tez.dto;

public class ThresholdInfo {
	private Double threshold;
	private Report report;
	
	public ThresholdInfo() {
		
	}

	public ThresholdInfo(Double threshold, Report report) {
		this.threshold = threshold;
		this.report = report;
	}

	public Double getThreshold() {
		return threshold;
	}

	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	
	
	
	
}
