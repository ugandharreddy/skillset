package com.attra.pms.util;

import javax.servlet.http.HttpSession;

import com.attra.pms.model.CustomMessage;

public abstract class SessionandClassName {
	
	
	
	// to get sessionId from session
	public static String getSessionId(HttpSession session, String className)
	{
		System.out.println(className);

		CustomMessage sessionObject= (CustomMessage) session.getAttribute("sessionId");
		return sessionObject.getSessionId();
	}
	

}
