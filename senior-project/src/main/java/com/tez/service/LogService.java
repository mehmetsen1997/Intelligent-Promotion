package com.tez.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tez.model.postgresql.LogInfo;
import com.tez.repository.postgresql.LogRepository;

@Service
public class LogService {
	@Autowired LogRepository logRepository;
	
	public List<LogInfo> getLogs(){
		return logRepository.findAll();
	}
	
}
