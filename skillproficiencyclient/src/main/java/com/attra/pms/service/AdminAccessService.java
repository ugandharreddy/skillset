package com.attra.pms.service;

import java.util.List;

import com.attra.pms.model.UserDetails;

public interface AdminAccessService {

	public List getListofEmployees(String sessionId);
	public List getListofAcceridations(String sessionId);

	
}
