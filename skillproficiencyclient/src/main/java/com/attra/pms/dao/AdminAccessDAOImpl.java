package com.attra.pms.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.attra.pms.controller.AdminController;
import com.attra.pms.controller.LoginController;
import com.attra.pms.model.ExceptionObject;
import com.attra.pms.model.ViewAllEmp;
import com.attra.pms.model.accreditation;
import com.attra.pms.util.ExceptionCode;
import com.attra.pms.util.PropertiesCache;
import com.attra.pms.util.clientConstants;
import com.attra.pms.util.customHeader;

@Repository
public class AdminAccessDAOImpl extends customHeader implements clientConstants, AdminAccessDAO {
	
	@Autowired 
	private RestTemplate restTemplate;

	@Autowired
	LoginController homeSession;

	private static final Logger logger = Logger.getLogger(AdminController.class.getName());

	private static final String webServiceURL = PropertiesCache.getPropertyByKey(REST_ENDPOINT);
	private static final String getListofEmployeesServiceURL = PropertiesCache.getPropertyByKey(ALLEMPLOYEE_ENDPOINT);
	private static final String getListofACCServiceURL = PropertiesCache.getPropertyByKey(SEARCHACCERIDATION_ENDPOINT);

	ExceptionObject  excpObj=new ExceptionObject();
	
	@SuppressWarnings("rawtypes")
	public List getListofEmployees(String sessionId) {

		HttpHeaders headers=customHeader.getCustomHeadersBySession(sessionId,"");
		ResponseEntity response=null;	

		List <ViewAllEmp> empList = new ArrayList<ViewAllEmp>();

		HttpEntity<String> request = new HttpEntity<String>(headers);
		try
		{
			
			System.out.println("web-serviceName ="+webServiceURL+ getListofEmployeesServiceURL);

			response = restTemplate.exchange(webServiceURL+ getListofEmployeesServiceURL, HttpMethod.GET, request, List.class);
			empList =(List<ViewAllEmp>) response.getBody();

		}catch(Exception e){
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			System.out.println(ExceptionCode.getExceptionCode(e));
		}
		return empList;
	}
	
	@SuppressWarnings("rawtypes")
	public List getListofAcceridations(String sessionId) {

		HttpHeaders headers=customHeader.getCustomHeadersBySession(sessionId,"");
		ResponseEntity response=null;

		List <accreditation> empList = new ArrayList<accreditation>();

		HttpEntity<String> request = new HttpEntity<String>(headers);
		try
		{
			System.out.println("web-serviceName ="+webServiceURL+ getListofACCServiceURL);

			response = restTemplate.exchange(webServiceURL+ getListofACCServiceURL, HttpMethod.GET, request, List.class);
			empList =(List<accreditation>) response.getBody();

		}catch(Exception e){
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			System.out.println(ExceptionCode.getExceptionCode(e));
		}
		return empList;
	}


}
