package com.attra.pms.dao;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.attra.pms.controller.AdminController;
import com.attra.pms.controller.LoginController;
import com.attra.pms.model.UserDetails;
import com.attra.pms.util.ExceptionCode;
import com.attra.pms.util.PropertiesCache;
import com.attra.pms.util.clientConstants;
import com.attra.pms.util.customHeader;

@Repository
public class DashboardDAOImpl extends customHeader implements DashboardDAO,clientConstants{
	
	@Autowired 
	private RestTemplate restTemplate;
	@Autowired
	LoginController homeSession;
	
	private static final String webServiceURL = PropertiesCache.getPropertyByKey(REST_ENDPOINT);
	private static final String dashboardServiceURL = PropertiesCache.getPropertyByKey(USERDETAILS_ENDPOINT);
	
	private static final Logger logger = Logger.getLogger(DashboardDAOImpl.class.getName());
	
	@SuppressWarnings("rawtypes")
	public UserDetails getUserDetails(String sessionId) {


		HttpHeaders headers=customHeader.getCustomHeadersBySession(sessionId,"");
		ResponseEntity response=null;

		UserDetails userInfo= new UserDetails();

		HttpEntity<String> request = new HttpEntity<String>(headers);
		try
		{
			System.out.println("  web-serviceName ="+webServiceURL+ dashboardServiceURL);

			response = restTemplate.exchange(webServiceURL+dashboardServiceURL, HttpMethod.GET, request, UserDetails.class);
			userInfo =(UserDetails)response.getBody();

		}catch(Exception e){
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			System.out.println(ExceptionCode.getExceptionCode(e));
		}
		return userInfo;

	}
	

}
