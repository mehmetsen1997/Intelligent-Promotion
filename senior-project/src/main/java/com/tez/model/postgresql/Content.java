package com.tez.model.postgresql;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Content {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer contentId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="content_type_id")
	private ContentType type;
	private Double amount;
	
	@OneToMany(mappedBy="content", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Session> sessions;

	public Content() {
	}
	
	
	public Content(Integer contentId, ContentType type, Double amount, List<Session> sessions) {
		this.contentId = contentId;
		this.type = type;
		this.amount = amount;
		this.sessions = sessions;
	}


	public Integer getContentId() {
		return contentId;
	}


	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}


	public ContentType getType() {
		return type;
	}


	public void setType(ContentType type) {
		this.type = type;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public List<Session> getSessions() {
		return sessions;
	}


	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}


	@Override
	public String toString() {
		return "Content [contentId=" + contentId + ", type=" + type + ", amount=" + amount + ", sessions=" + sessions
				+ "]";
	}

	
	

}
