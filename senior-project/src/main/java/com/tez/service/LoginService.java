package com.tez.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tez.controller.ResponseDto;
import com.tez.dto.LoginForm;
import com.tez.hash.HashGenerator;
import com.tez.model.postgresql.Admin;
import com.tez.repository.postgresql.AdminRepository;

@Service
public class LoginService {
	@Autowired private AdminRepository adminRepository;
	private static Admin loggedInAdmin;
	
	public ResponseDto authorize(LoginForm loginForm) {
		Admin admin = adminRepository.findByUsername(loginForm.getUsername());
		if(admin==null) {
			return new ResponseDto("FAILED", 404, "User with username " + loginForm.getUsername() +
								" not found!");
		}
		else {
			HashGenerator hashGenerator = new HashGenerator();
			String hashedPassword = hashGenerator.hash(loginForm.getPassword());
			if(hashedPassword.equalsIgnoreCase(admin.getPassword())) {
				setLoggedInAdmin(admin);
				return new ResponseDto("OK", 200, "Login was succesfull!");
			}
			return new ResponseDto("FAILED", 404, "Wrong password");	
		}
		
	}

	public static Admin getLoggedInAdmin() {
		return loggedInAdmin;
	}

	public static void setLoggedInAdmin(Admin loggedInAdmin) {
		LoginService.loggedInAdmin = loggedInAdmin;
	}
	
	
}
