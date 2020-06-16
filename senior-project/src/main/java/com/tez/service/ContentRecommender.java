package com.tez.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.tez.model.cassandra.Visit;
import com.tez.model.postgresql.Content;
import com.tez.repository.postgresql.ContentRepository;

@Service
public class ContentRecommender {
	@Autowired private ContentRepository contentRepository;
	private Double threshold;
	private Map<String,Content> contents;
	
	@PostConstruct
	public void init() {
		this.threshold = 0.75;
		setContents();
	}
	
	public void setContents() {
		List<Content> contents = contentRepository.findAll();
		this.contents = new ConcurrentHashMap<>();
		for(Content content: contents) {
			System.out.println(content);
			this.contents.put(content.getType().getName(), content);
		}
	}
	
	public Content recommendContent(Visit visit) {
		double rateProductRelated = visit.getTotalTime() / visit.getProductRelatedDuration();
		if(visit.getPurchaseProbability() > threshold && visit.getTotalTime() > 20 
				&& visit.getProductRelatedPageview() >= 1) {
			Content content = selectContent(rateProductRelated);
			System.out.println("Content offered: " + content);
			return content;
		}
		else {
			return null;
		}
	}
	
	public Content selectContent(double rateProductRelated) {
		if(rateProductRelated>=0.8) {
			return this.contents.get("Promotion");
		}
		else if(rateProductRelated<0.8 && rateProductRelated>0.6) {
			return this.contents.get("FreeShipping");
		}
		else {
			return this.contents.get("Other");
		}
	}

	public Double getThreshold() {
		return threshold;
	}

	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	} 
	
	
}
