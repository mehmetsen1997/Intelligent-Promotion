package com.tez.model.postgresql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adminId;
	private String username;
	@JsonIgnore
	private String password;
	private Integer age;
	private String role;
	@OneToMany(mappedBy="admin", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<LogInfo> logs;
	
	public Admin() {
		
	}

	

	public Admin(Integer adminId, String username, String password, Integer age, String role) {
		this.adminId = adminId;
		this.username = username;
		this.password = password;
		this.age = age;
		this.role = role;
	}



	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}

	public void addLog(LogInfo log) {
		if(this.logs == null) {
			this.logs = new ArrayList<LogInfo>();
		}
		System.out.println(this.logs == null);
		this.logs.add(log);
	}

	public List<LogInfo> getLogs() {
		return logs;
	}



	public void setLogs(List<LogInfo> logs) {
		this.logs = logs;
	}

	
	
	
	
}
