package com.tez.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
	public String hash(String message) {
		byte[] bytesOfMessage = message.getBytes();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hashedMessage = md.digest(bytesOfMessage);
		return bytesToHex(hashedMessage);
	}
	
	private String bytesToHex(byte[] hash) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	    	String hex = Integer.toHexString(0xff & hash[i]);
	    	if(hex.length() == 1) hexString.append('0');
	        	hexString.append(hex);
	    }
	    return hexString.toString();
	}
}
