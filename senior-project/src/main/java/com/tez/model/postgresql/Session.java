package com.tez.model.postgresql;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Session {
	
	@Id
	private String sessionId;
	
	private Double threshold;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="content_id")
	@JsonIgnore
	private Content content;
	private String result;
	
	
	public Session() {
		
	}
	
	public Session(String sessionId, Double threshold, Content content, String result) {
		this.sessionId = sessionId;
		this.threshold = threshold;
		this.content = content;
		this.result = result;
	}

	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}

	public Double getThreshold() {
		return threshold;
	}

	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}


	
	
	
}
