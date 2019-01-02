package com.attra.pms.service;

import com.attra.pms.model.UserDetails;


public interface DashboardService {
	
	public UserDetails getUserInfo(String sessionId);
	

}
