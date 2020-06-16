package com.tez.model.postgresql;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LogInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer logId;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="admin_id")
	@JsonIgnore
	private Admin admin;
	private LocalDateTime timestamp;
	private String logMessage;
	
	public LogInfo() {
	}
	
	

	public LogInfo(LocalDateTime timestamp, String logMessage, Admin admin) {
		this.timestamp = timestamp;
		this.logMessage = logMessage;
		this.admin = admin;
	}



	public LogInfo(Integer logId, Admin admin, LocalDateTime timestamp, String logMessage) {
		this.logId = logId;
		this.admin = admin;
		this.timestamp = timestamp;
		this.logMessage = logMessage;
	}

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}
	
	
	
}
