package com.attra.pms.dao;

import com.attra.pms.model.UserDetails;
public interface DashboardDAO {
	
	public UserDetails getUserDetails(String sessionId);


}
