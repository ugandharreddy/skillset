package com.attra.pms.dao;

import com.attra.pms.model.UserDetails;
import com.attra.pms.model.LoginUser;

public interface LoginAuthenticationDAO {
	
	public String authenticationCheck(LoginUser login);
	public UserDetails getUserDetails(String sessionId);

}
