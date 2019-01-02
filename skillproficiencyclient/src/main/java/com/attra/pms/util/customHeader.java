package com.attra.pms.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

import com.attra.pms.model.LoginUser;

public abstract class customHeader {
	
	 protected static HttpHeaders getCustomHeaders(LoginUser loginUser){
		HttpHeaders headers = new HttpHeaders();
		String plainCreds = loginUser.getUsername() + ":" + loginUser.getPassword();
		if(plainCreds.length()>0){
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		headers = new HttpHeaders();

		headers.add("Authorization", "Basic " + base64Creds);
		}
		return headers;
	}
	
	 protected static HttpHeaders getCustomHeadersBySession(String sessionId,String randomUUID){
			HttpHeaders headers = new HttpHeaders();
			
			headers = new HttpHeaders();

			headers.add("sessionId"+"_"+randomUUID , sessionId);
			headers.add("randomUUID" , randomUUID);
			
			return headers;
		}
	

}
