package com.tez.machinelearning;

public class Metric {
	private double accuracy;
	private double f1;
	private double weightedPrecision;
	private double weightedRecall;
	
	
	
	public Metric() {
		
	}



	public Metric(double accuracy, double f1, double weightedPrecision, double weightedRecall) {
		super();
		this.accuracy = accuracy;
		this.f1 = f1;
		this.weightedPrecision = weightedPrecision;
		this.weightedRecall = weightedRecall;
	}



	public double getAccuracy() {
		return accuracy;
	}



	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}



	public double getF1() {
		return f1;
	}



	public void setF1(double f1) {
		this.f1 = f1;
	}



	public double getWeightedPrecision() {
		return weightedPrecision;
	}



	public void setWeightedPrecision(double weightedPrecision) {
		this.weightedPrecision = weightedPrecision;
	}



	public double getWeightedRecall() {
		return weightedRecall;
	}



	public void setWeightedRecall(double weightedRecall) {
		this.weightedRecall = weightedRecall;
	}



	@Override
	public String toString() {
		return "Metric [accuracy=" + accuracy + ", f1=" + f1 + ", weightedPrecision=" + weightedPrecision
				+ ", weightedRecall=" + weightedRecall + "]";
	}
	
	

	
	
}
