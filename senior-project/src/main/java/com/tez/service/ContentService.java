package com.tez.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tez.dto.AddContentDto;
import com.tez.dto.UpdateContentDto;
import com.tez.model.postgresql.Content;
import com.tez.model.postgresql.ContentType;
import com.tez.model.postgresql.LogInfo;
import com.tez.repository.postgresql.ContentRepository;
import com.tez.repository.postgresql.ContentTypeRepository;
import com.tez.repository.postgresql.LogRepository;

@Service
public class ContentService {
	
	@Autowired private ContentRepository contentRepository;
	@Autowired private ContentTypeRepository contentTypeRepository;
	@Autowired private ContentRecommender contentRecommender;
	@Autowired private LogRepository logRepository;
	
	public List<Content> getContents(){
		return contentRepository.findAll();
	}
	
	public void addContent(AddContentDto content) {
		ContentType contentType = contentTypeRepository.getOne(content.getContentTypeId());
		Content newContent = new Content();
		newContent.setType(contentType);
		newContent.setAmount(content.getContentAmount());
		contentRepository.save(newContent);
		contentRecommender.setContents();
		LogInfo log = new LogInfo(LocalDateTime.now(), "Content: " + 
				newContent.getAmount() + " " + newContent.getType().getName() + " was added by " + 
				LoginService.getLoggedInAdmin().getUsername(), LoginService.getLoggedInAdmin());
		logRepository.save(log);
	}
	
	public void deleteContent(Content content) {
		contentRepository.delete(content);
		contentRecommender.setContents();
		LogInfo log = new LogInfo(LocalDateTime.now(), "Content: " + 
				content.getAmount() + " " + content.getType().getName() + " was deleted by " + 
				LoginService.getLoggedInAdmin().getUsername(), LoginService.getLoggedInAdmin());
		logRepository.save(log);
	}
	
	public void updateContent(UpdateContentDto content) {
		ContentType contentType = contentTypeRepository.getOne(content.getContentTypeId());
		Content newContent = new Content();
		newContent.setContentId(content.getContentId());
		newContent.setType(contentType);
		newContent.setAmount(content.getContentAmount());
		contentRepository.save(newContent);
		contentRecommender.setContents();
		LogInfo log = new LogInfo(LocalDateTime.now(), "Content: " + 
				newContent.getAmount() + " " + newContent.getType().getName() + " was updated by " + 
				LoginService.getLoggedInAdmin().getUsername(), LoginService.getLoggedInAdmin());
		logRepository.save(log);
	}
	
	public List<ContentType> getContentTypes(){
		return contentTypeRepository.findAll();
	}
}
