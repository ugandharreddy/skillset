package com.attra.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attra.pms.dao.LoginAuthenticationDAO;
import com.attra.pms.model.UserDetails;
import com.attra.pms.model.LoginUser;

@Service
public class LoginServiceImpl implements LoginService {
	
	 @Autowired
	   private LoginAuthenticationDAO loginDao;
	

	public String authentactionCheck(LoginUser login) {
		
		
		  return loginDao.authenticationCheck(login);
	}


	public UserDetails getUserInfo(String sessionId) {

		return loginDao.getUserDetails(sessionId);
	}
	
	
}
