package com.tez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tez.dto.ClickstreamDataDto;
import com.tez.dto.ContentResponseDto;
import com.tez.dto.VisitDto;
import com.tez.service.RealtimeDataService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RealTimeDataController {
	@Autowired private RealtimeDataService rs;
	
	@PostMapping("/realTimeData")
	public ResponseEntity<ContentResponseDto> postRealTimeData(@RequestBody ClickstreamDataDto cd){
		cd.setCurrentPage("/tez" + cd.getCurrentPage());
		return new ResponseEntity<>(rs.predictVisit(cd), HttpStatus.OK);
	}
	
	@GetMapping("/visit")
	public ResponseEntity<List<VisitDto>> getOnlineVisits(){
		List<VisitDto> visits = rs.getVisits();
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}
}
