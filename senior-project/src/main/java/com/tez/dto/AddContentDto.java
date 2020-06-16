package com.tez.dto;

public class AddContentDto {
	private Integer contentTypeId;
	private Double contentAmount;
	
	public AddContentDto() {
		
	}

	public AddContentDto(Integer contentTypeId, Double contentAmount) {
		this.contentTypeId = contentTypeId;
		this.contentAmount = contentAmount;
	}

	public Integer getContentTypeId() {
		return contentTypeId;
	}

	public void setContentTypeId(Integer contentTypeId) {
		this.contentTypeId = contentTypeId;
	}

	public Double getContentAmount() {
		return contentAmount;
	}

	public void setContentAmount(Double contentAmount) {
		this.contentAmount = contentAmount;
	}
	
	
	
}
