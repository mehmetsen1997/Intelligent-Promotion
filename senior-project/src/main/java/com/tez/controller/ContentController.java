package com.tez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tez.dto.AddContentDto;
import com.tez.dto.UpdateContentDto;
import com.tez.model.postgresql.Content;
import com.tez.model.postgresql.ContentType;
import com.tez.service.ContentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ContentController {
	
	@Autowired ContentService contentService;
	
	@GetMapping("content")
	public ResponseEntity<List<Content>> getContents(){
		List<Content> contents = contentService.getContents();
		return new ResponseEntity<List<Content>>(contents,HttpStatus.OK);
	}
	
	@PostMapping("content")
	public ResponseEntity<ResponseDto> addContent(@RequestBody AddContentDto content){
		contentService.addContent(content);
		ResponseDto response = new ResponseDto("OK", 201, "Content was created.");
		return new ResponseEntity<ResponseDto>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("content")
	public ResponseEntity<ResponseDto> updateContent(@RequestBody UpdateContentDto content){
		contentService.updateContent(content);
		ResponseDto response = new ResponseDto("OK", 200, "Content was updated.");
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	@GetMapping("contentType")
	public ResponseEntity<List<ContentType>> getContentTypes(){
		List<ContentType> contents = contentService.getContentTypes();
		return new ResponseEntity<List<ContentType>>(contents,HttpStatus.OK);
	}
	
}
