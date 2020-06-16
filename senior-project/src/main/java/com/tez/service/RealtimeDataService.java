package com.tez.service;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tez.analytics.PageInfoRetriever;
import com.tez.dto.ClickstreamDataDto;
import com.tez.dto.ContentResponseDto;
import com.tez.dto.PageInfo;
import com.tez.dto.VisitDto;
import com.tez.machinelearning.PredictionEngine;
import com.tez.model.cassandra.Visit;
import com.tez.model.postgresql.Content;
import com.tez.model.postgresql.Session;
import com.tez.repository.VisitRepository;
import com.tez.repository.postgresql.ContentRepository;
import com.tez.repository.postgresql.SessionRepository;

@Service
public class RealtimeDataService {
	
	@Autowired private VisitRepository vr;
	@Autowired private PredictionEngine pe;	
	@Autowired private PageInfoRetriever pr;
	@Autowired private ContentRecommender contentRecommender;
	@Autowired private SessionRepository sessionRepository;
	@Autowired private ContentRepository contentRepository;
	private Map<String,Visit> onlineVisits;
	
	@PostConstruct
	public void init() {
		// Concurrent HashMap used because there might be read and write operations at the same time.
		this.onlineVisits = new ConcurrentHashMap<String,Visit>();
	}
	
	public Visit createVisit(ClickstreamDataDto cd) {
		PageInfo pi = pr.getPageInfo(cd.getCurrentPage());
		Double specialDay = SpecialDay.getDistance(LocalDate.now());
		String sessionId = UUID.nameUUIDFromBytes(cd.getSessionId().getBytes()).toString();
		Visit visit = new Visit(cd, pi, specialDay);	
		visit.setSessionId(sessionId);
		return visit;
	}
	
	/**
	 * 
	 * @param cd Clickstream information that retrieved from website.
	 * @return
	 */
	public ContentResponseDto predictVisit(ClickstreamDataDto cd) {
		Visit visit = createVisit(cd);
		double[] result = pe.predict(visit);
		double probability = result[1];
		probability = Math.round(probability * 100.0) / 100.0;
		visit.setPurchaseProbability(probability);
		visit.setLastInformation(LocalDateTime.now());
		visit.setRevenue(cd.getRevenue().toUpperCase());
		this.onlineVisits.put(visit.getSessionId(), visit);
		Content content = contentRecommender.recommendContent(visit);
		visit.setThreshold(contentRecommender.getThreshold());
		if(content != null) {
			visit.setContentId(content.getContentId());
		}
		else {
			visit.setContentId(null);
		}
		return new ContentResponseDto(result[1], content);
	}
	
	/**
	 * Check if there is any terminated session and save to the database if it is terminated.
	 * Checking every 2 seconds and, if no information has been received for 5 seconds and classify that
	 * session as terminated.
	 */
	@Scheduled(fixedRate = 2000)
	public void saveVisit() {
		long seconds;
		for(String sessionId: onlineVisits.keySet()) {
			Visit visit = onlineVisits.get(sessionId);
			seconds = Duration.between(visit.getLastInformation(), LocalDateTime.now()).toSeconds();
			if(seconds > 5) {
				vr.save(visit);
				onlineVisits.remove(sessionId);
				if(visit.getContentId() != null) {
					sessionRepository.save(new Session(visit.getSessionId(), visit.getThreshold(), 
							contentRepository.getOne(visit.getContentId()), visit.getRevenue()));
				}
				else {
					sessionRepository.save(new Session(visit.getSessionId(), visit.getThreshold(), null, 
							visit.getRevenue()));
				}
			}
		}
	}
	
	// simply returns the online visitor list
	public List<VisitDto> getVisits() {
		List<VisitDto> visits = new ArrayList<>();
		for(String sessionId: onlineVisits.keySet()) {
			Visit visit = onlineVisits.get(sessionId);
			VisitDto visitDto = new VisitDto(visit.getSessionId(), visit.getPurchaseProbability()
					, visit.getCurrentPage(), visit.getTotalTime());
			visits.add(visitDto);
		}
		return visits;
	}
	
	
	
	
}
