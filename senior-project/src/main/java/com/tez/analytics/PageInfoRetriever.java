package com.tez.analytics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.api.services.analyticsreporting.v4.model.Report;
import com.tez.dto.PageInfo;

@Component
public class PageInfoRetriever {
	private AnalyticsReporter reporter;
	private PageInfoParser pageParser;
	private Map<String, List<Double>> pageInfos;
	
	public PageInfoRetriever() {
//		this.reporter = new AnalyticsReporter();
//		this.pageParser = new PageInfoParser();
//		this.pageInfos = new HashMap<>();
	}
	
	@PostConstruct
	public void init() {
		this.reporter = new AnalyticsReporter();
		this.pageParser = new PageInfoParser();
		this.pageInfos = new HashMap<>();
		pullPageInfo();
		for(String s: pageInfos.keySet()) {
			System.out.println(s);
		}
	}
	/**
	 * This method pulls the analytics data and parses the page informations periodically.
	 * TO DO: Decide for the retrieving interval. 
	 */
	//@PostConstruct
	//@Scheduled(fixedRate = 5000)
	public void pullPageInfo() {
		Report report = reporter.getReport();
		this.pageInfos = pageParser.parsePageInfos(report);
	}
	
	/**
	 * This method gets the page metrics belong to the specific page.
	 * @param pagePath 
	 * @return
	 */
	public PageInfo getPageInfo(String pagePath){
		
		List<Double> result = pageInfos.get(pagePath);
		if(result == null) {
			throw new PageInformationNotFoundException();
		}
		return new PageInfo(pagePath, result.get(0), result.get(1), result.get(2));
	}
	
	
}
