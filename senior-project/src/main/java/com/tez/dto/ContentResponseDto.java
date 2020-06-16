package com.tez.dto;

import com.tez.model.postgresql.Content;

public class ContentResponseDto {
	private Double purchaseProbability;
	private Content content;
	
	public ContentResponseDto() {
		
	}

	public ContentResponseDto(Double purchaseProbability, Content content) {
		this.purchaseProbability = purchaseProbability;
		this.content = content;
	}
	
	public Double getPurchaseProbability() {
		return purchaseProbability;
	}
	public void setPurchaseProbability(Double purchaseProbability) {
		this.purchaseProbability = purchaseProbability;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}

	
}
