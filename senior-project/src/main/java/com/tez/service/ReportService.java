package com.tez.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tez.dto.Report;
import com.tez.dto.ThresholdInfo;
import com.tez.model.postgresql.Session;
import com.tez.repository.postgresql.SessionRepository;

@Service
public class ReportService {
	@Autowired SessionRepository sessionRepository;
	
	public Report getReport() {
		List<Session> sessions = sessionRepository.findAll();
		int totalPurchase = 0, totalPurchaseWithPromotion = 0;
		for(Session session: sessions) {
			if(session.getResult().equals("TRUE")) {
				System.out.println(session.getResult());
				totalPurchase++;
			}
			if(session.getContent() != null && session.getResult().equals("TRUE")) {
				totalPurchaseWithPromotion++;
			}
		}
		return new Report(totalPurchase, totalPurchaseWithPromotion, sessions.size());
	}
	
	public List<ThresholdInfo> getThresholdReport(){
		List<ThresholdInfo> thresholdReport = new ArrayList<ThresholdInfo>();
		Map<Double,Report> thresholdInfos = getThresholdMap();
		for(Double threshold: thresholdInfos.keySet()) {
			ThresholdInfo info = new ThresholdInfo(threshold, thresholdInfos.get(threshold));
			thresholdReport.add(info);
		}
		return thresholdReport;
	}
	
	public Map<Double,Report> getThresholdMap(){
		Map<Double, Report> thresholdInfos = new HashMap<Double, Report>();
		for(Double key: thresholdInfos.keySet()) {
			System.out.println("gdgdh: " + thresholdInfos.get(key));
		}
		List<Session> sessions = sessionRepository.findAll();
		Double threshold;
		for(Session session: sessions) {
			threshold = session.getThreshold();
			if(thresholdInfos.containsKey(threshold)) {
				Report report = thresholdInfos.get(threshold);
				updateReport(session, report);
			}
			else {
				thresholdInfos.put(threshold, createReport(session));
			}
		}
		return thresholdInfos;
	}
	
	public Report createReport(Session session) {
		Report report = new Report();
		System.out.println("session re: " + session.getResult());
		if(session.getResult().equals("TRUE")) {
			report.setTotalPurchase(1);
			if(session.getContent() != null) { 
				report.setTotalPurchaseWithPromotion(1);
			}
			else {
				report.setTotalPurchaseWithPromotion(0);
			}
		}
		else {
			report.setTotalPurchase(0);
			report.setTotalPurchaseWithPromotion(0);
		}
		report.setTotalSession(1);	
		System.out.println("report created for: " + session.getThreshold() + " " + report.getTotalPurchase() + report.getTotalPurchaseWithPromotion());
		return report;
	}
	
	public void updateReport(Session session, Report report) {
		if(session.getResult().equals("TRUE")) {
			System.out.println("update report: " + session.getResult());
			report.setTotalPurchase(report.getTotalPurchase() + 1);
			if(session.getContent() != null) {
				System.out.println("update report promotion: " + session.getResult());
				report.setTotalPurchaseWithPromotion(report.getTotalPurchaseWithPromotion()+1);
			}
		}
		report.setTotalSession(report.getTotalSession()+1);	
	}
}
