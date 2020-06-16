package com.tez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tez.dto.Report;
import com.tez.dto.ThresholdInfo;
import com.tez.service.ReportService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReportController {
	
	@Autowired private ReportService reportService;
	
	@GetMapping("report")
	public ResponseEntity<Report> getOnlineVisits(){
		Report report = reportService.getReport();
		return new ResponseEntity<>(report, HttpStatus.OK);
	}
	
	@GetMapping("threshold")
	public ResponseEntity<List<ThresholdInfo>> getThresholdReport(){
		List<ThresholdInfo> report = reportService.getThresholdReport();
		return new ResponseEntity<>(report, HttpStatus.OK);
	}
}
