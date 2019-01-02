package com.attra.pms.service;

import com.attra.pms.model.UserDetails;
import com.attra.pms.model.LoginUser;

public interface LoginService {
	
	public String authentactionCheck(LoginUser login);
	
	public UserDetails getUserInfo(String sessionId);

}
