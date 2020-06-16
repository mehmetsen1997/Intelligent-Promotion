package com.tez.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tez.dto.ChangeThresholdDto;
import com.tez.model.postgresql.LogInfo;
import com.tez.repository.postgresql.LogRepository;
import com.tez.service.ContentRecommender;
import com.tez.service.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ThresholdController {
	@Autowired ContentRecommender contentRecommender;
	@Autowired LogRepository logRepository;
	
	@PutMapping("threshold")
	public void changeThreshold(@RequestBody ChangeThresholdDto threshold){
		contentRecommender.setThreshold(threshold.getThreshold());
		LogInfo log = new LogInfo(LocalDateTime.now(), 
				"Threshold value updated as " + contentRecommender.getThreshold() + " by"
						+ LoginService.getLoggedInAdmin().getUsername(), LoginService.getLoggedInAdmin());
		logRepository.save(log);
	}
}
