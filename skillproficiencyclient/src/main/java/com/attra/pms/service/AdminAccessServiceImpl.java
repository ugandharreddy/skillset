package com.attra.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attra.pms.dao.AdminAccessDAO;

@Service
	public class AdminAccessServiceImpl implements AdminAccessService {

	@Autowired
	private AdminAccessDAO dtDao;

	public List getListofEmployees(String sessionId) {
		
		
		  return dtDao.getListofEmployees(sessionId);
	}

	public List getListofAcceridations(String sessionId) {
		
		
		  return dtDao.getListofAcceridations(sessionId);
	}
	



}
