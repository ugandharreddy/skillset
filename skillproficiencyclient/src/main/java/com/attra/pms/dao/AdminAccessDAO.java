package com.attra.pms.dao;

import java.util.List;

public interface AdminAccessDAO {

	public List getListofEmployees(String sessionId);
	public List getListofAcceridations(String sessionId);

	
}
