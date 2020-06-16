package com.tez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tez.dto.LoginForm;
import com.tez.model.postgresql.Admin;
import com.tez.service.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
	private @Autowired LoginService loginService;
	
	
	@PostMapping("login")
	public ResponseEntity<ResponseDto> login(@RequestBody LoginForm loginForm){
		ResponseDto response = loginService.authorize(loginForm);
		return new ResponseEntity<ResponseDto>(response, HttpStatus.OK);
	}
	
	@GetMapping("profile")
	public ResponseEntity<Admin> getProfileInfo(){
		return new ResponseEntity<Admin>(LoginService.getLoggedInAdmin(), HttpStatus.OK);
	} 
}
