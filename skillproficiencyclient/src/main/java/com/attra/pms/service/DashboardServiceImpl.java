package com.attra.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attra.pms.dao.DashboardDAO;
import com.attra.pms.model.UserDetails;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	 @Autowired
	   private DashboardDAO dtDao;

	 public UserDetails getUserInfo(String sessionId) {

			return dtDao.getUserDetails(sessionId);
		}
		
	


}
