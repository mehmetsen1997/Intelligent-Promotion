package com.tez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tez.model.postgresql.LogInfo;
import com.tez.service.LogService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LogController {
	@Autowired LogService logService;
	
	@GetMapping("log")
	public ResponseEntity<List<LogInfo>> getLogs(){
		List<LogInfo> logs = logService.getLogs();
		return new ResponseEntity<List<LogInfo>>(logs, HttpStatus.OK);
	}
}
