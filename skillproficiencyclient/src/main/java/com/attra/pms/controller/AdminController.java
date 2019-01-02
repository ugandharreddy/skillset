package com.attra.pms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.attra.pms.model.AccountList;
import com.attra.pms.model.AdminMessageModel;
import com.attra.pms.model.AdminRoleList;
import com.attra.pms.model.AreaOfWork;
import com.attra.pms.model.BUList;
import com.attra.pms.model.CatageoryDomain;
import com.attra.pms.model.CombinationalSearch;
import com.attra.pms.model.EmployeeList;
import com.attra.pms.model.EmployeesInTeam;
import com.attra.pms.model.ExceptionObject;
import com.attra.pms.model.ListResponse;
import com.attra.pms.model.LoginUser;
import com.attra.pms.model.Methodology;
import com.attra.pms.model.ProjectList;
import com.attra.pms.model.RoleToEmpAdmin;
import com.attra.pms.model.SMEListDomainAdmin;
import com.attra.pms.model.SMEListTechAdmin;
import com.attra.pms.model.SkillAdmin;
import com.attra.pms.model.Stream;
import com.attra.pms.model.SubDomainListAdmin;
import com.attra.pms.model.TechnologyAdmin;
import com.attra.pms.model.ToolListAdmin;
import com.attra.pms.model.ToolTypeEntity;
import com.attra.pms.model.UpdateWindowPeriod;
import com.attra.pms.model.accreditation;
import com.attra.pms.model.message;
import com.attra.pms.util.ExceptionCode;
import com.attra.pms.util.MessageProperties;
import com.attra.pms.util.PropertiesCache;
import com.attra.pms.util.SPMTClientCustomExceptions;
import com.attra.pms.util.clientConstants;
import com.attra.pms.util.customHeader;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
@Controller
@Scope("request")
public class AdminController  extends customHeader implements clientConstants{
	
	private static final Logger logger = Logger.getLogger(AdminController.class.getName());

	private static final String webServiceURL = PropertiesCache.getPropertyByKey(REST_ENDPOINT);


	private static final String getTeamMembersServiceURL = PropertiesCache.getPropertyByKey(EMPLOYEESINTEAM_ENDPOINT); //employeesInTeam
	private static final String benchListServiceURL = PropertiesCache.getPropertyByKey(BENCHLIST_ENDPOINT);
	private static final String employeesSkillServiceURL = PropertiesCache.getPropertyByKey(EMPLOYEESINSKILL_ENDPOINT);

	private static final String loginJSP = MessageProperties.getMessageKey(clientConstants.LOGIN_JSP);
	private static final String myteamJSP = MessageProperties.getMessageKey(clientConstants.MYTEAM_JSP);
	private static final String benchTeamJSP = MessageProperties.getMessageKey(clientConstants.RMGTEAM_JSP);
	private static final String benchSkillsJSP = MessageProperties.getMessageKey(clientConstants.RMGSKILL_JSP);

	private static final String myteamTitle = MessageProperties.getMessageKey(clientConstants.MYTEAM_TITLE);
	private static final String rmgTeamTitle = MessageProperties.getMessageKey(clientConstants.RMGTEAM_TITLE);
	private static final String rmgSkillsTitle = MessageProperties.getMessageKey(clientConstants.RMGSKILLS_TITLE);

	@Autowired 
	private RestTemplate restTemplate;
	
	ExceptionObject  excpObj=new ExceptionObject();
	
	public String changeObjectToJSON(Object sourceObj,String emailID) {
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("updateProficiency");
		 excpObj.setEmailId(emailID);

		ObjectMapper mapper = new ObjectMapper();
		String JSONString = "";
		try {
			JSONString = mapper.writeValueAsString(sourceObj);
		} catch (JsonGenerationException e) {
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			ExceptionCode.getExceptionCode(e);
			//e.printStackTrace();
			JSONString = "JsonGenerationException: " + e.getMessage();
		} catch (JsonMappingException e) {
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			ExceptionCode.getExceptionCode(e);
			//e.printStackTrace();
			JSONString = "JsonMappingException: " + e.getMessage();
		} catch (IOException e) {
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			ExceptionCode.getExceptionCode(e);
			//e.printStackTrace();
			JSONString = "IOException: " + e.getMessage();
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
		}
		return JSONString;
	}

	String className =this.getClass().getSimpleName();
	// printing controller name

	@RequestMapping(value="/teamMembers", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getteamMembers(HttpSession session)  {
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("updateProficiency");
		 String emailID =null;
		 		
		ModelAndView model = new ModelAndView();
		if(session == null)
        {
        	
        	model.addObject("msg","Invalid Session");
        	model.addObject("loginUser",new LoginUser());
        	model.setViewName("loginpage");
       }
       else
       {
    	   emailID= (String) session.getAttribute("loginUser");
  		 excpObj.setEmailId(emailID);
		
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ResponseEntity employeeListResponse=null;

		try
		{
			List <EmployeesInTeam> empList = new ArrayList<EmployeesInTeam>();
			List <ProjectList> projectList = new ArrayList<ProjectList>();
			ListResponse tempResponse =new ListResponse();// new
			employeeListResponse = restTemplate.exchange(webServiceURL+"Employees/employeesInTeam", HttpMethod.GET, request, ListResponse.class);//new
			
			tempResponse = (ListResponse) employeeListResponse.getBody();//new
			
			empList = (List<EmployeesInTeam>) tempResponse.getList();//new
			model.addObject("message", tempResponse.getMessage());//new
			model.addObject("employessList", empList);
			model.addObject("title", myteamTitle); 
			model.addObject("emp",new EmployeesInTeam());
			model.setViewName(myteamJSP);

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			

		}
       }
		return model;
	}
	
	@RequestMapping(value="/benchTeamMembers", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getbenchTeamMembers(HttpSession session)  {
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("getbenchTeamMembers");
		 String emailID =null;
		 ModelAndView model = new ModelAndView();
		 if(session == null)
	        {
	        	
	        	model.addObject("msg","Invalid Session");
	        	model.addObject("loginUser",new LoginUser());
	        	model.setViewName("loginpage");
	       }
				
       else
       {
		ResponseEntity employeeListResponse=null;
		ResponseEntity projectListResponse=null;

		try
		{
			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
			
			emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);
			List <EmployeesInTeam> empList = new ArrayList<EmployeesInTeam>();
			List <ProjectList> projectList = new ArrayList<ProjectList>();

			employeeListResponse = restTemplate.exchange(webServiceURL+benchListServiceURL, HttpMethod.GET, request, List.class);
			empList = (List<EmployeesInTeam>) employeeListResponse.getBody();
			/*if(role.equals("BENCH_HEAD"))
			{
				projectListResponse = restTemplate.exchange(webServiceURL+   "/projectList", HttpMethod.GET, request, List.class);
				projectList = (List<ProjectList>) projectListResponse.getBody();
				model.addObject("projectList", projectList);
			}*/
			model.addObject("employessList", empList);
			model.addObject("title", rmgTeamTitle); 
			model.addObject("emp",new EmployeesInTeam());
			model.setViewName(benchTeamJSP);

		}
		catch (Exception e)
		{

			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
       }
		return model;
	}

	@RequestMapping(value="/teamMembersSkills", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getteamMembersSkills(HttpSession session) throws JsonProcessingException  {
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("getteamMembersSkills");
		 String emailID =null;
		 String temp=null;
		 
		ModelAndView model = new ModelAndView();
		if(session == null)
        {
        	
        	model.addObject("msg","Invalid Session");
        	model.addObject("loginUser",new LoginUser());
        	model.setViewName("loginpage");
       }
       else
       {
		
		try{
			
			ResponseEntity employeeSkillListResponse=null;
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);
			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

			employeeSkillListResponse = restTemplate.exchange(webServiceURL+employeesSkillServiceURL, HttpMethod.GET, request, String.class);
			
			if(employeeSkillListResponse.getBody() != null){
				 temp =(String) employeeSkillListResponse.getBody();

				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				String json = ow.writeValueAsString(employeeSkillListResponse.getBody());
				//ModelAndView model = new ModelAndView();
				model.addObject("title", rmgSkillsTitle);
				model.addObject("skillEmpList",temp);
				model.setViewName(benchSkillsJSP);
				
			}
		}catch(Exception e){
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
		}
		
       }
		return model;
	}
	
	
//admin access page starts	
//methodology
	
	@RequestMapping(value="/addMethodology/{methodDesc}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String deployEmployee(@PathVariable String methodDesc, HttpSession session)  {
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("deployEmployee");
		 String emailID =null;
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		ResponseEntity deployResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;
		try
		{ 
			byte[] plainCredsBytes = methodDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/insertMethodology/" +base64Creds, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);

			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
		}

		return msg;
	}
	
	@RequestMapping(value="/AdminMethodology", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminMethodology( HttpSession session)  {
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("AdminMethodology");
		 String emailID =null;
		 		 
		ResponseEntity methodologyResponse=null;
		
		ModelAndView model = new ModelAndView();
		if(session == null)
        {
        	
        	model.addObject("msg","Invalid Session");
        	model.addObject("loginUser",new LoginUser());
        	model.setViewName("loginpage");
       }
       else
       {
		 emailID= (String) session.getAttribute("loginUser");
  		 excpObj.setEmailId(emailID);
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		

		try
		{ 
			methodologyResponse= restTemplate.exchange(webServiceURL+"mytask/getmethodology", HttpMethod.GET, request, List.class);
			List <Methodology> methodologyList = new ArrayList<Methodology>();
			methodologyList = (List<Methodology>) methodologyResponse.getBody();
			String list=changeObjectToJSON(methodologyList,emailID);
			model.addObject("methodologyList",list);
			
			model.setViewName("addMethodologyAdmin");

		}
		catch (Exception e)
		{

			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
       }
		return model;
	}
		
	@RequestMapping(value="/updateMethodology/{methodDesc}/{methodId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateMethodology(@PathVariable String methodDesc, @PathVariable String methodId,  HttpSession session){

		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("updateMethodology");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		ResponseEntity responseList = null;
		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;
		try{
		
			byte[] plainCredsBytes = methodDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			
			responseList= restTemplate.exchange(webServiceURL+"admin/updateMethodology/"+base64Creds+ "/" +methodId, HttpMethod.GET, request, message.class);
			msgString= (message) responseList.getBody();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
		}
		return msg;
	}
	
//  area of work	
	@RequestMapping(value="/AdminAreaOfWork", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminAreaOfWork( HttpSession session)  {
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("AdminAreaOfWork");
		 String emailID =null;
		 
		 
		 
		ResponseEntity areaofworkResponse=null;
		ModelAndView model = new ModelAndView();
		if(session == null)
        {
        	
        	model.addObject("msg","Invalid Session");
        	model.addObject("loginUser",new LoginUser());
        	model.setViewName("loginpage");
       }
       else
       {
		
    	   emailID= (String) session.getAttribute("loginUser");
  		 excpObj.setEmailId(emailID);
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		try
		{ 
			areaofworkResponse=restTemplate.exchange(webServiceURL +"mytask/getAreaOfWork", HttpMethod.GET, request, List.class);
			List <AreaOfWork> areaofworkList = new ArrayList<AreaOfWork>();
			areaofworkList = (List<AreaOfWork>) areaofworkResponse.getBody();
			String list=changeObjectToJSON(areaofworkList,emailID);
			model.addObject("areaofworkList", list);
			
			model.setViewName("AddAOWAdmin");

		}
		catch (Exception e)
		{

			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
		}
       }
		return model;
	}
	
	
	@RequestMapping(value="/addAow/{aowDesc}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String AddAreaOfWork(@PathVariable String aowDesc, HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("AddAreaOfWork");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;

		try
		{ 
			byte[] plainCredsBytes = aowDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/insertAreaOfWork/" +base64Creds, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}


	@RequestMapping(value="/updateAow/{areaOfwork}/{areaOfId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String UpdateAow(@PathVariable String areaOfwork, @PathVariable String areaOfId,  HttpSession session)  {

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		ResponseEntity responseList = null;
		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg= null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("UpdateAow");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		try{
			byte[] plainCredsBytes = areaOfwork.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			
			responseList= restTemplate.exchange(webServiceURL+"admin/updateAreaOfWork/"+base64Creds+"/" +areaOfId, HttpMethod.GET, request, message.class);
			msgString= (message) responseList.getBody();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}
			
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
		return msg;
	}
	
//STREAM 
		
	@RequestMapping(value="/AdminStream", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminStream( HttpSession session)  {
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("AdminStream");
		 String emailID =null;
		 
		 
		
		ModelAndView model = new ModelAndView();
		if(session == null)
        {
        	
        	model.addObject("msg","Invalid Session");
        	model.addObject("loginUser",new LoginUser());
        	model.setViewName("loginpage");
       }
       else
       {
    	   emailID= (String) session.getAttribute("loginUser");
  		 excpObj.setEmailId(emailID);
		
		ResponseEntity areaofworkResponse=null;
		ResponseEntity streamResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

	   try
		{ 
			areaofworkResponse=restTemplate.exchange(webServiceURL +"mytask/getAreaOfWork", HttpMethod.GET, request, List.class);
			List <AreaOfWork> areaofworkList = new ArrayList<AreaOfWork>();
			areaofworkList = (List<AreaOfWork>) areaofworkResponse.getBody();
			model.addObject("areaofworkList",areaofworkList);
			
			streamResponse=restTemplate.exchange(webServiceURL +"admin/getAdminTechnology", HttpMethod.GET, request, List.class);
			List <TechnologyAdmin> streamList = new ArrayList<TechnologyAdmin>();
			streamList = (List<TechnologyAdmin>) streamResponse.getBody();
			String list=changeObjectToJSON(streamList,emailID);
			model.addObject("streamList",list);
			
			model.setViewName("addTechnologyAdmin");

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
       }
		return model;
	}
	
	@RequestMapping(value="/addStream/{techName}/{aowId}/{plEmailId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String addStream(@PathVariable String techName,@PathVariable String aowId,@PathVariable String plEmailId,HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("addStream");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;

		try
		{ 
			byte[] plainCredsBytes = techName.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			byte[] plainCredsBytes1 = plEmailId.getBytes();
			byte[] base64CredsBytes1 = Base64.encodeBase64(plainCredsBytes1);
			String base64Creds1 = new String(base64CredsBytes1);
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/insertStream/"+base64Creds+"/"+aowId+"/"+base64Creds1, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			//String responseString =  msgString.getErrormsg();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}
			

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
	
	
	@RequestMapping(value="/updateStream/{techName}/{aowId}/{plEmailId}/{techId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateStream(@PathVariable String techName,@PathVariable String aowId,@PathVariable String plEmailId,
			@PathVariable String techId,HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("updateStream");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;

		try
		{ 
			byte[] plainCredsBytes = techName.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			byte[] plainCredsBytes1 = plEmailId.getBytes();
			byte[] base64CredsBytes1 = Base64.encodeBase64(plainCredsBytes1);
			String base64Creds1 = new String(base64CredsBytes1);
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/updateStream/"+base64Creds+"/"+aowId+"/"+base64Creds1+"/"+techId, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			//String responseString =  msgString.getErrormsg();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}
			

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
//SKill Start

	@RequestMapping(value="/AdminSkill", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminSkill( HttpSession session)  {
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("AdminSkill");
		 String emailID =null;
		 
		 
		

		ModelAndView model = new ModelAndView();
		if(session == null)
        {
        	
        	model.addObject("msg","Invalid Session");
        	model.addObject("loginUser",new LoginUser());
        	model.setViewName("loginpage");
       }
       else
       {
		
		ResponseEntity areaofworkResponse=null;
		ResponseEntity skillResponse=null;
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		

		try
		{ 
			areaofworkResponse=restTemplate.exchange(webServiceURL +"mytask/getAreaOfWork", HttpMethod.GET, request, List.class);
			List <AreaOfWork> areaofworkList = new ArrayList<AreaOfWork>();
			areaofworkList = (List<AreaOfWork>) areaofworkResponse.getBody();
			model.addObject("areaofworkList",areaofworkList);
			
			skillResponse=restTemplate.exchange(webServiceURL +"admin/getAdminSkillList", HttpMethod.GET, request, List.class);
			List <SkillAdmin> skillList = new ArrayList<SkillAdmin>();
			skillList = (List<SkillAdmin>) skillResponse.getBody();
			String list = changeObjectToJSON(skillList,emailID);
			model.addObject("skillList",list);
			
			model.setViewName("addSkillAdmin");
			
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
       }
		return model;
	}
	
	@RequestMapping(value="/AdminSkillStream/{aowId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ArrayList<Stream> AdminSkillStream(@PathVariable String aowId, HttpSession session)  {
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("AdminSkillStream");
		 String emailID =null;
		 
			ArrayList <Stream> streamsList=new ArrayList<Stream>();
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		ResponseEntity areaofworkResponse=null;
		ResponseEntity streamResponse=null;
try{
	HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
	HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);




	streamResponse= restTemplate.exchange(webServiceURL+"mytask/getstream/"+aowId, HttpMethod.GET, request, List.class);
	streamsList = (ArrayList<Stream>) streamResponse.getBody();

	// model.addObject("streamsList",streamsList);
		
}catch(Exception e){
	excpObj.setE(e);
	SPMTClientCustomExceptions.getExceptionCode(excpObj);
}
		
		return streamsList;
	}
	
	@RequestMapping(value="/addSkill/{skillName}/{techId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String addSkill(@PathVariable String skillName,@PathVariable String techId,HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("addSkill");
		 String emailID =null;
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		  
		 
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;

		try
		{ 
			byte[] plainCredsBytes = skillName.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/insertSkill/"+base64Creds+"/"+techId, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			//String responseString =  msgString.getErrormsg();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			 SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
	
	@RequestMapping(value="/updateSkill/{skillName}/{techId}/{skillId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateSkill(@PathVariable String skillName,@PathVariable String techId,
			@PathVariable String skillId,HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("updateSkill");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);


		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;

		try
		{ 
			byte[] plainCredsBytes = skillName.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/updateSkill/"+base64Creds+"/"+techId+"/"+skillId, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			//String responseString =  msgString.getErrormsg();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
	
//accredation
	
	
	@RequestMapping(value="/AdminAccrediation", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminAccrediation( HttpSession session)  {
		ResponseEntity methodologyResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("AdminAccrediation");
		 String emailID =null;
		 
		 
		

		ModelAndView model = new ModelAndView();
		if(session == null)
        {
        	
        	model.addObject("msg","Invalid Session");
        	model.addObject("loginUser",new LoginUser());
        	model.setViewName("loginpage");
       }
       else
       {
		
		

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		//ModelAndView model = new ModelAndView();
		try
		{ 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);
			methodologyResponse= restTemplate.exchange(webServiceURL+"Management/searchAccrName", HttpMethod.GET, request, List.class);
			List <accreditation> accreditationList = new ArrayList<accreditation>();
			accreditationList = (List<accreditation>) methodologyResponse.getBody();
			String List=changeObjectToJSON(accreditationList,emailID);
			model.addObject("accreditationList",List);
			
			model.setViewName("addAccrediationAdmin");

		}
		catch (Exception e)
		{

			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
       }
		return model;
	}
	
	@RequestMapping(value="/addAccredation/{accrediationDesc}/{certifiedBy}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String addAccredation(@PathVariable String accrediationDesc,@PathVariable String certifiedBy,
			HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("addAccredation");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg= null;

		try
		{ 
			byte[] plainCredsBytes = accrediationDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			byte[] plainCredsBytes1 = certifiedBy.getBytes();
			byte[] base64CredsBytes1 = Base64.encodeBase64(plainCredsBytes1);
			String base64Creds1 = new String(base64CredsBytes1);
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/insertAccrediation/" +base64Creds+"/"+base64Creds1, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
				System.out.println("messgae in side service "+msg);
			}
			else{
				msg =  msgString.getErrormsg();
			}
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}

	
	
	@RequestMapping(value="/updateAccrediation/{accrediationDesc}/{certifiedBy}/{accredId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateAccrediation(@PathVariable String accrediationDesc, @PathVariable String certifiedBy,  
			 @PathVariable String accredId,HttpSession session)  {
		
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("updateAccrediation");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);


		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		ModelAndView model = new ModelAndView();
		message msgString = new message();
		ResponseEntity responseList = null;
		String msg=null;
		try{
			byte[] plainCredsBytes = accrediationDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			byte[] plainCredsBytes1 = certifiedBy.getBytes();
			byte[] base64CredsBytes1 = Base64.encodeBase64(plainCredsBytes1);
			String base64Creds1 = new String(base64CredsBytes1);
			
			
			responseList= restTemplate.exchange(webServiceURL+"admin/updateAccrediation/"+base64Creds+ "/" +base64Creds1+"/"+accredId, HttpMethod.GET, request, message.class);
			msgString= (message) responseList.getBody();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}
			
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
		
		return msg;
	
	}
//domain
	@RequestMapping(value="/AdminDomain", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminDomain( HttpSession session)  {
		ResponseEntity methodologyResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("AdminDomain");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();

		try
		{ 
			methodologyResponse= restTemplate.exchange(webServiceURL+"mytask/getdomain", HttpMethod.GET, request, List.class);
			List <CatageoryDomain> domainList = new ArrayList<CatageoryDomain>();
			domainList = (List<CatageoryDomain>) methodologyResponse.getBody();
			String list = changeObjectToJSON(domainList,emailID);
			model.addObject("domainList",list);
			
			model.setViewName("addDomainAdmin");

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return model;
	}
	
	@RequestMapping(value="/addDomain/{domainDesc}/{plEmailId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String addDomain(@PathVariable String domainDesc,@PathVariable String plEmailId,HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("addDomain");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;

		try
		{ 
			byte[] plainCredsBytes = domainDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			byte[] plainCredsBytes1 = plEmailId.getBytes();
			byte[] base64CredsBytes1 = Base64.encodeBase64(plainCredsBytes1);
			String base64Creds1 = new String(base64CredsBytes1);
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/insertDomain/" +base64Creds+"/"+base64Creds1, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
	
	

	@RequestMapping(value="/updateDomain/{domainDesc}/{plEmailId}/{domainId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String  updateDomain(@PathVariable String domainDesc, @PathVariable String plEmailId,  
			 @PathVariable String domainId,HttpSession session)  {

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		ResponseEntity responseList = null;
		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg= null;
		try{
			byte[] plainCredsBytes = domainDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			byte[] plainCredsBytes1 = plEmailId.getBytes();
			byte[] base64CredsBytes1 = Base64.encodeBase64(plainCredsBytes1);
			String base64Creds1 = new String(base64CredsBytes1);
			
			
			responseList= restTemplate.exchange(webServiceURL+"admin/updateDomain/"+base64Creds+ "/" +base64Creds1+"/"+domainId, HttpMethod.GET, request, message.class);
			msgString= (message) responseList.getBody();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}
		}
		catch (Exception e)
		{

			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
		return msg;
	}
//plemailid search dropdown service
	@RequestMapping(value="/PLEmailList/{keyword}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String PlEmailList(@PathVariable String keyword,HttpSession session)  {
		ResponseEntity areaofworkResponse=null;
		ResponseEntity plResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("PlEmailList");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		 String list = null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		/*ArrayList <AdminMessageModel> plList=new ArrayList<AdminMessageModel>();*/
		AdminMessageModel plList=new AdminMessageModel();
		byte[] plainCredsBytes = keyword.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
try{
	plResponse= restTemplate.exchange(webServiceURL+"admin/getPLList/"+base64Creds, HttpMethod.GET, request, AdminMessageModel.class);
	plList = (AdminMessageModel) plResponse.getBody();
	if(plList.getList()!=null){
		plList.setMessage("data  found");
	}
	else{
		plList.setMessage("No data found");
	}
	 list = changeObjectToJSON(plList,emailID);
	System.out.println("pllist"+list+"pllist"+plList);
	
}catch(Exception e){
	excpObj.setE(e);
	SPMTClientCustomExceptions.getExceptionCode(excpObj);
}
	
		return list;
	}
	
//smemailid search dropdown service
	@RequestMapping(value="/SMEmailList/{keyword1}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AdminMessageModel SMEmailList(@PathVariable String keyword1,HttpSession session)  {
		ResponseEntity areaofworkResponse=null;
		ResponseEntity plResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("SMEmailList");
		 String emailID =null;
			AdminMessageModel smeList=new AdminMessageModel();
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
try{
	HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
	HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

	/*ArrayList <AdminMessageModel> plList=new ArrayList<AdminMessageModel>();*/

	byte[] plainCredsBytes = keyword1.getBytes();
	byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
	String base64Creds = new String(base64CredsBytes);

	plResponse= restTemplate.exchange(webServiceURL+"admin/getSMEList/"+base64Creds, HttpMethod.GET, request, AdminMessageModel.class);
	smeList = (AdminMessageModel) plResponse.getBody();

}catch(Exception e){
	excpObj.setE(e);
	SPMTClientCustomExceptions.getExceptionCode(excpObj);
}
		return smeList;
	}
	
//subdomain
	
	@RequestMapping(value="/AdminSubDomain", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminSubDomain( HttpSession session)  {
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("AdminSubDomain");
		 String emailID =null;
		 
		 
		 

		ResponseEntity domainResponse=null;
		ResponseEntity subdomainResponse=null;
		
		
		ModelAndView model = new ModelAndView();
		if(session == null)
        {
        	
        	model.addObject("msg","Invalid Session");
        	model.addObject("loginUser",new LoginUser());
        	model.setViewName("loginpage");
       }
       else
       {
		
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		try
		{ 
			emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);
			domainResponse= restTemplate.exchange(webServiceURL+"mytask/getdomain", HttpMethod.GET, request, List.class);
			List <CatageoryDomain> domainList = new ArrayList<CatageoryDomain>();
			domainList = (List<CatageoryDomain>) domainResponse.getBody();
			model.addObject("domainList",domainList);
			
			
			subdomainResponse= restTemplate.exchange(webServiceURL+"admin/getAdminSubDomainList", HttpMethod.GET, request, List.class);
			List <SubDomainListAdmin> subdomainList = new ArrayList<SubDomainListAdmin>();
			subdomainList = (List<SubDomainListAdmin>) subdomainResponse.getBody();
			String list=changeObjectToJSON(subdomainList,emailID);
			model.addObject("subdomainList",list);
			
			model.setViewName("addDomainCategoryAdmin");

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
       }
		return model;
	}
	
	@RequestMapping(value="/addSubdomain/{subdomainDesc}/{domainId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String addSubdomain(@PathVariable String subdomainDesc,@PathVariable String domainId,HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("addSubdomain");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;
		try
		{ 
			byte[] plainCredsBytes = subdomainDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/insertSubDomain/"+base64Creds+"/"+domainId, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			//String responseString =  msgString.getErrormsg();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
		
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
	
	@RequestMapping(value="/updateSubdomain/{subdomainDesc}/{domainId}/{subDomainId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateSubdomain(@PathVariable String subdomainDesc, @PathVariable String domainId,  
			 @PathVariable String subDomainId,HttpSession session)  {

		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("updateSubdomain");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		ResponseEntity responseList = null;
		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;
		try{
			byte[] plainCredsBytes = subdomainDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			responseList= restTemplate.exchange(webServiceURL+"admin/updateSubDomain/"+base64Creds+"/" +domainId+"/"+subDomainId, HttpMethod.GET, request, message.class);
			msgString= (message) responseList.getBody();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
		return msg;
	}
//tooltype
	
	@RequestMapping(value="/AdminToolType", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ModelAndView AdminToolType( HttpSession session)  {
		ResponseEntity toolTypeResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("AdminToolType");
		 String emailID =null;
		 
		 
	

		ModelAndView model = new ModelAndView();
		if(session == null)
        {
        	model.addObject("msg","Invalid Session");
        	model.addObject("loginUser",new LoginUser());
        	model.setViewName("loginpage");
       }
       else
       {

    		 emailID= (String) session.getAttribute("loginUser");
    		 excpObj.setEmailId(emailID);
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
    
		try
		{ 
			toolTypeResponse=restTemplate.exchange(webServiceURL +"mytask/getToolType", HttpMethod.GET, request, List.class);
			List <ToolTypeEntity> toolTypeList = new ArrayList<ToolTypeEntity>();
			toolTypeList = (List<ToolTypeEntity>) toolTypeResponse.getBody();
			String list= changeObjectToJSON(toolTypeList,emailID);
			model.addObject("toolTypeList",list);
			
			model.setViewName("addToolTypeAdmin");

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
       }
		return model;
	}
	
	
	@RequestMapping(value="/addTooltype/{toolTypeDesc}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String addTooltype(@PathVariable String toolTypeDesc, HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("addTooltype");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;
		try
		{ 
			byte[] plainCredsBytes = toolTypeDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/insertToolType/" +base64Creds, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			//String responseString =  msgString.getErrormsg();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
	
	
	@RequestMapping(value="/updateTooltype/{toolTypeDesc}/{toolTypeID}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateTooltype(@PathVariable String toolTypeDesc,@PathVariable String toolTypeID,HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("updateTooltype");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;
		try
		{ 
			byte[] plainCredsBytes = toolTypeDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/updateToolType/"+base64Creds+"/"+toolTypeID, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			//String responseString =  msgString.getErrormsg();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
	
//Tool
	@RequestMapping(value="/AdminTool", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminTool( HttpSession session)  {
		ResponseEntity toolTypeResponse=null;
		ResponseEntity toolResponse=null;
		ModelAndView model = new ModelAndView();
		 if(session == null)
	    {
			 model.addObject("msg","Invalid Session");
			 model.setViewName("loginpage");
	      }
	     else
	     {
	    	 excpObj.setClassname(AdminController.class);
			 excpObj.setMethodName("AdminTool");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

	    HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		try
		{ 
			toolTypeResponse=restTemplate.exchange(webServiceURL +"mytask/getToolType", HttpMethod.GET, request, List.class);
			List <ToolTypeEntity> toolTypeList = new ArrayList<ToolTypeEntity>();
			toolTypeList = (List<ToolTypeEntity>) toolTypeResponse.getBody();
			model.addObject("toolTypeList",toolTypeList);
			
			toolResponse=restTemplate.exchange(webServiceURL +"admin/getAdminToolList", HttpMethod.GET, request, List.class);
			List <ToolListAdmin> toolList = new ArrayList<ToolListAdmin>();
			toolList = (List<ToolListAdmin>) toolResponse.getBody();
			String list=changeObjectToJSON(toolList,emailID);
			model.addObject("toolList",list);
			
			model.setViewName("addToolAdmin");

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
	     }
		return model;
	}
	
	@RequestMapping(value="/addTool/{toolDesc}/{tooltypeID}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String addTool(@PathVariable String toolDesc,@PathVariable String tooltypeID,HttpSession session)  {
		ResponseEntity deployResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
	
		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;

		try
		{ 
			excpObj.setClassname(AdminController.class);
			 excpObj.setMethodName("addTool");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			byte[] plainCredsBytes = toolDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/insertTool/"+base64Creds+"/"+tooltypeID, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			//String responseString =  msgString.getErrormsg();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
	
	@RequestMapping(value="/updateTool/{toolDesc}/{tooltypeId}/{toolId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateTool(@PathVariable String toolDesc, @PathVariable String tooltypeId,  
			 @PathVariable String toolId,HttpSession session)  {

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		ResponseEntity responseList = null;
		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;
		try{
			excpObj.setClassname(AdminController.class);
			 excpObj.setMethodName("updateTool");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			byte[] plainCredsBytes = toolDesc.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			responseList= restTemplate.exchange(webServiceURL+"admin/updateTool/"+base64Creds+"/" +tooltypeId+"/"+toolId, HttpMethod.GET, request, message.class);
			msgString= (message) responseList.getBody();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
		return msg;
	}
//add SME for technology
	@RequestMapping(value="/AdminSMEforTech", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminSMEforTech( HttpSession session)  {
		ResponseEntity areaofworkResponse=null;
		ResponseEntity skillResponse=null;
		ResponseEntity smeResponse=null;
		ModelAndView model = new ModelAndView();
		 if(session == null)
	    {
			 model.addObject("msg","Invalid Session");
			 model.setViewName("loginpage");
	    }
	     else
	    {
		
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		

		try
		{ 
			excpObj.setClassname(AdminController.class);
			 excpObj.setMethodName("AdminSMEforTech");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			areaofworkResponse=restTemplate.exchange(webServiceURL +"mytask/getAreaOfWork", HttpMethod.GET, request, List.class);
			List <AreaOfWork> areaofworkList = new ArrayList<AreaOfWork>();
			areaofworkList = (List<AreaOfWork>) areaofworkResponse.getBody();
			model.addObject("areaofworkList",areaofworkList);
			
			smeResponse=restTemplate.exchange(webServiceURL +"admin/getAdminSMEListTech", HttpMethod.GET, request, List.class);
			System.out.println("smeResponse===>  "+smeResponse);

			List <SMEListTechAdmin> smeList = new ArrayList<SMEListTechAdmin>();
			smeList = (List<SMEListTechAdmin>) smeResponse.getBody();
			System.out.println("smeList===>  "+smeList);
			String list= changeObjectToJSON(smeList,emailID);
			model.addObject("smeList",list);
			
			model.setViewName("AssignSMEforTechnology");
			
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");

		}
		}

		return model;
	}
	
	@RequestMapping(value="/addSMEforTech/{techId}/{smeEmailId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String addSMEforTech(@PathVariable String techId,@PathVariable String smeEmailId,HttpSession session)  {
		ResponseEntity deployResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;

		try
		{ 
			excpObj.setClassname(AdminController.class);
			 excpObj.setMethodName("addSMEforTech");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			byte[] plainCredsBytes = smeEmailId.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/insertSMEforTechnology/"+techId+"/"+base64Creds, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			//String responseString =  msgString.getErrormsg();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
	
	@RequestMapping(value="/deleteSMEforTech/{smeEmailId}/{techId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String deleteSMEforTech(@PathVariable String smeEmailId,@PathVariable String techId,HttpSession session)  {
		ResponseEntity deployResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;
		try
		{ 
			excpObj.setClassname(AdminController.class);
			 excpObj.setMethodName("deleteSMEforTech");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			byte[] plainCredsBytes = smeEmailId.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/deleteSMEforTechnology/"+base64Creds+"/"+techId, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			//String responseString =  msgString.getErrormsg();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
	
//add SME for domain
	@RequestMapping(value="/AdminSMEforDomain", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminSMEforDomain( HttpSession session)  {
		ResponseEntity domainResponse=null;
		ResponseEntity smeResponse=null;
		ModelAndView model = new ModelAndView();
		 if(session == null)
	    {
			 model.addObject("msg","Invalid Session");
			 model.setViewName("loginpage");
	    }
	     else
	     {
		
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		try
		{ 
			excpObj.setClassname(AdminController.class);
			 excpObj.setMethodName("AdminSMEforDomain");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			domainResponse= restTemplate.exchange(webServiceURL+"mytask/getdomain", HttpMethod.GET, request, List.class);
			List <CatageoryDomain> domainList = new ArrayList<CatageoryDomain>();
			domainList = (List<CatageoryDomain>) domainResponse.getBody();
			model.addObject("domainList",domainList);
			
			smeResponse=restTemplate.exchange(webServiceURL +"admin/getAdminSMEListDomain", HttpMethod.GET, request, List.class);
			List <SMEListDomainAdmin> smeList = new ArrayList<SMEListDomainAdmin>();
			smeList = (List<SMEListDomainAdmin>) smeResponse.getBody();
			String list = changeObjectToJSON(smeList,emailID);
			model.addObject("smeList",list);
			
			model.setViewName("AssignSMEforDomain");
			
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
		}
		}

		return model;
	}
	
	@RequestMapping(value="/addSMEforDomain/{domainId}/{smeEmailId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String addSMEforDomain(@PathVariable String domainId,@PathVariable String smeEmailId,HttpSession session)  {
		ResponseEntity deployResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;
		try
		{ 
			excpObj.setClassname(AdminController.class);
			 excpObj.setMethodName("addSMEforDomain");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			byte[] plainCredsBytes = smeEmailId.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/insertSMEforDomain/"+domainId+"/"+base64Creds, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			//String responseString =  msgString.getErrormsg();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
	
	@RequestMapping(value="/deleteSMEforDomain/{smeEmailId}/{domainId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String deleteSMEforDomain(@PathVariable String smeEmailId,@PathVariable String domainId,HttpSession session)  {
		ResponseEntity deployResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ModelAndView model = new ModelAndView();
		message msgString = new message();
		String msg=null;
		try
		{ 
			excpObj.setClassname(AdminController.class);
			 excpObj.setMethodName("deleteSMEforDomain");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			byte[] plainCredsBytes = smeEmailId.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"admin/deleteSMEforDomain/"+base64Creds+"/"+domainId, HttpMethod.GET, request, message.class);
			msgString= (message) deployResponse.getBody();
			//String responseString =  msgString.getErrormsg();
			if(msgString.getSuccessMsg()!=null){
				msg =  msgString.getSuccessMsg();
			}
			else{
				msg =  msgString.getErrormsg();
			}

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

		return msg;
	}
	
//assign role to emp
	@RequestMapping(value="/AdminAssignEmpRole", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView AdminAssignEmpRole( HttpSession session)  {
		ResponseEntity roleResponse=null;
		ResponseEntity roleEmpResponse=null;
		excpObj.setClassname(AdminController.class);
		 excpObj.setMethodName("updateProficiency");
		 String emailID =null;
		 
		 
		
		
		ModelAndView model = new ModelAndView();
		 if(session == null)
	    {
			 model.addObject("msg","Invalid Session");
			 model.setViewName("loginpage");
	      }
	     else
	     {
	    	 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		try
		{ 
			roleResponse= restTemplate.exchange(webServiceURL+"admin/getRoles", HttpMethod.GET, request, List.class);
			List <AdminRoleList> roleList = new ArrayList<AdminRoleList>();
			roleList = (List<AdminRoleList>) roleResponse.getBody();
			model.addObject("roleList",roleList);
			
			roleEmpResponse= restTemplate.exchange(webServiceURL+"admin/getAdminRoleToEmp", HttpMethod.GET, request, List.class);
			List <RoleToEmpAdmin> roleEmpList = new ArrayList<RoleToEmpAdmin>();
			roleEmpList = (List<RoleToEmpAdmin>) roleEmpResponse.getBody();
			String list = changeObjectToJSON(roleEmpList,emailID);
			model.addObject("roleEmpList",list);
			
			model.setViewName("AdminAssignEmpRole");
			
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
	     }
		return model;
	}
	//employee search dropdown service
		@RequestMapping(value="/EmpEmailList/{keyword}", method= {RequestMethod.GET, RequestMethod.POST})
		@ResponseBody
		public AdminMessageModel EmpEmailList(@PathVariable String keyword,HttpSession session)  {
			ResponseEntity areaofworkResponse=null;
			ResponseEntity plResponse=null;
			AdminMessageModel plList=new AdminMessageModel();
try{
	excpObj.setClassname(AdminController.class);
	 excpObj.setMethodName("EmpEmailList");
	 String emailID =null;
	 
	 
	 emailID= (String) session.getAttribute("loginUser");
	 excpObj.setEmailId(emailID);

	HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
	HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

	/*ArrayList <AdminMessageModel> plList=new ArrayList<AdminMessageModel>();*/

	byte[] plainCredsBytes = keyword.getBytes();
	byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
	String base64Creds = new String(base64CredsBytes);


	plResponse= restTemplate.exchange(webServiceURL+"admin/getEmployeeList/"+base64Creds, HttpMethod.GET, request, AdminMessageModel.class);
	plList = (AdminMessageModel) plResponse.getBody();
}catch(Exception e){
	excpObj.setE(e);
	SPMTClientCustomExceptions.getExceptionCode(excpObj);
}
			
			return plList;
		}
		@RequestMapping(value="/AssignEmpRole/{empEmailId}/{roleID}", method= {RequestMethod.GET, RequestMethod.POST})
		@ResponseBody
		public String AssignEmpRole(@PathVariable String empEmailId,@PathVariable String roleID,HttpSession session)  {
			ResponseEntity deployResponse=null;
			excpObj.setClassname(AdminController.class);
			 excpObj.setMethodName("AssignEmpRole");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);


			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

			ModelAndView model = new ModelAndView();
			message msgString = new message();
			String msg=null;
			try
			{ 
				byte[] plainCredsBytes = empEmailId.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);
				
				
				deployResponse = restTemplate.exchange(webServiceURL+"admin/assignRoleToEmp/"+base64Creds+"/"+roleID, HttpMethod.GET, request, message.class);
				msgString= (message) deployResponse.getBody();
				//String responseString =  msgString.getErrormsg();
				if(msgString.getSuccessMsg()!=null){
					msg =  msgString.getSuccessMsg();
				}
				else{
					msg =  msgString.getErrormsg();
				}

			}
			catch (Exception e)
			{
				excpObj.setE(e);
				SPMTClientCustomExceptions.getExceptionCode(excpObj);
				model.addObject("msg",ExceptionCode.getExceptionCode(e));
				model.addObject("loginUser",new LoginUser());
				model.setViewName(loginJSP);
				logger.error("msg"+ExceptionCode.getExceptionCode(e));

			}

			return msg;
		}
		@RequestMapping(value="/DeleteEmpRole/{empEmailId}/{roleID}", method= {RequestMethod.GET, RequestMethod.POST})
		@ResponseBody
		public String DeleteEmpRole(@PathVariable String empEmailId,@PathVariable String roleID,HttpSession session)  {
			ResponseEntity deployResponse=null;

			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

			ModelAndView model = new ModelAndView();
			message msgString = new message();
			String msg=null;
			try
			{ 
				excpObj.setClassname(AdminController.class);
				 excpObj.setMethodName("DeleteEmpRole");
				 String emailID =null;
				 
				 
				 emailID= (String) session.getAttribute("loginUser");
				 excpObj.setEmailId(emailID);

				byte[] plainCredsBytes = empEmailId.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);
				
				
				deployResponse = restTemplate.exchange(webServiceURL+"admin/deleteRoleForEmp/"+base64Creds+"/"+roleID, HttpMethod.GET, request, message.class);
				msgString= (message) deployResponse.getBody();
				//String responseString =  msgString.getErrormsg();
				if(msgString.getSuccessMsg()!=null){
					msg =  msgString.getSuccessMsg();
				}
				else{
					msg =  msgString.getErrormsg();
				}

			}
			catch (Exception e)
			{
				excpObj.setE(e);
				SPMTClientCustomExceptions.getExceptionCode(excpObj);
				model.addObject("msg",ExceptionCode.getExceptionCode(e));
				model.addObject("loginUser",new LoginUser());
				model.setViewName(loginJSP);
				logger.error("msg"+ExceptionCode.getExceptionCode(e));
			}

			return msg;
		}
		
		
		////window period --venkat
		@RequestMapping(value="/getBuList", method= {RequestMethod.GET, RequestMethod.POST})
		public ModelAndView getBuList( HttpSession session)  {
			ResponseEntity buList=null;
			ResponseEntity projectList=null;
			ResponseEntity accList=null;
			ResponseEntity empList=null;
			ModelAndView model = new ModelAndView();
			 if(session == null)
		    {
				 model.addObject("msg","Invalid Session");
				 model.setViewName("loginpage");
		    }
		     else
		    {
			
			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

			

			try
			{ 
				excpObj.setClassname(AdminController.class);
				 excpObj.setMethodName("UpdateWindowPeriod");
				 String emailID =null;
				 
				 
				 emailID= (String) session.getAttribute("loginUser");
				 excpObj.setEmailId(emailID);

				 buList=restTemplate.exchange(webServiceURL +"admin/getBuList", HttpMethod.POST, request, List.class);
				List <BUList> buList1 = new ArrayList<BUList>();
				
				buList1 = (List<BUList>) buList.getBody();
				model.addObject("bulist",buList1);
				//================================================================
				
				/*accList=restTemplate.exchange(webServiceURL +"admin/getAccountList", HttpMethod.POST, request, List.class);
				List <AccountList> accList1 = new ArrayList<AccountList>();
				System.out.println("-------- :"+accList);
				
				accList1 = (List<AccountList>) accList.getBody();
				System.out.println("AccList1===>  "+accList1);
				model.addObject("accList",accList1);*/
				//==============================================================================================================
				
				model.setViewName("AdminWindowPeriod");
				
			}
			catch (Exception e)
			{
				excpObj.setE(e);
				SPMTClientCustomExceptions.getExceptionCode(excpObj);
				model.addObject("msg",ExceptionCode.getExceptionCode(e));
				model.addObject("loginUser",new LoginUser());
				model.setViewName(loginJSP);
				logger.error("msg"+ExceptionCode.getExceptionCode(e));

				//model.addObject("loginUser",new LoginUser());
				//model.setViewName(loginJSP);
				model.setViewName("redirect:/logout");

			}
			}

			return model;
		}
		
		

		//window period getAccountlist --venkat
		@RequestMapping(value="/getAccountList/{buID}", method= {RequestMethod.GET, RequestMethod.POST})
		@ResponseBody public String getAccountList( @PathVariable String buID,HttpSession session)  {
			ResponseEntity projectList=null;
			ResponseEntity accList=null;
			ResponseEntity empList=null;
			String list= null;
			//System.out.println("Hitting in projhect details");
			
			ModelAndView model = new ModelAndView();
			 if(session == null)
		    {
				 model.addObject("msg","Invalid Session");
				 model.setViewName("loginpage");
		    }
		     else
		    {
			
			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

			

			try
			{ 
				excpObj.setClassname(AdminController.class);
				 excpObj.setMethodName("getAccountList");
				 String emailID =null;
				 
				 
				 emailID= (String) session.getAttribute("loginUser");
				 excpObj.setEmailId(emailID);

				
				//================================================================
				
				accList=restTemplate.exchange(webServiceURL +"admin/getAccountList/"+buID, HttpMethod.GET, request, List.class);
				List <AccountList> accList1 = new ArrayList<AccountList>();
				System.out.println("-------- :"+accList);
				
				accList1 = (List<AccountList>) accList.getBody();
				
				 list= changeObjectToJSON(accList1,emailID);
				model.addObject("AccList",list);
				
				System.out.println("*********"+list);
				System.out.println("AccList1===>  "+accList1);
				//model.addObject("accList",accList1);
				//==============================================================================================================
				
				//model.setViewName("AdminWindowPeriod");
				
			}
			catch (Exception e)
			{
				excpObj.setE(e);
				SPMTClientCustomExceptions.getExceptionCode(excpObj);
				model.addObject("msg",ExceptionCode.getExceptionCode(e));
				model.addObject("loginUser",new LoginUser());
				model.setViewName(loginJSP);
				logger.error("msg"+ExceptionCode.getExceptionCode(e));

				//model.addObject("loginUser",new LoginUser());
				//model.setViewName(loginJSP);
				model.setViewName("redirect:/logout");

			}
			}

			return list;
		}
		
		
		//window period getprojectlist --venkat
				@RequestMapping(value="/getProjectListData/{accId}", method= {RequestMethod.GET, RequestMethod.POST})
				@ResponseBody public String getProjectList( @PathVariable String accId,HttpSession session)  {
					ResponseEntity projectList=null;
					ResponseEntity accList=null;
					ResponseEntity empList=null;
					String list= null;
					
					System.out.println("Hitting in projhect details");
					
					ModelAndView model = new ModelAndView();
					 if(session == null)
				    {
						 model.addObject("msg","Invalid Session");
						 model.setViewName("loginpage");
				    }
				     else
				    {
					
					HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
					HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

					

					try
					{ 
						excpObj.setClassname(AdminController.class);
						 excpObj.setMethodName("getProjectList");
						 String emailID =null;
						 
						 
						 emailID= (String) session.getAttribute("loginUser");
						 excpObj.setEmailId(emailID);

						
						//================================================================
						
						 projectList=restTemplate.exchange(webServiceURL +"admin/getProjectList/"+accId, HttpMethod.GET, request, List.class);
						 /*AdminMessageModel messageModel = new AdminMessageModel();
						 if(messageModel.getList().isEmpty()){
							 String message = messageModel.getMessage();
							 list= changeObjectToJSON(message,emailID);
								model.addObject("message",list);
							
						 }*/
						// List <ProjectList> projectList1 =  (List<ProjectList>) messageModel.getList();
						 List <ProjectList> projectList1 = new ArrayList<ProjectList>();
							System.out.println("-------- :"+projectList);
							
							projectList1 = (List<ProjectList>) projectList.getBody();
							
							System.out.println("projectList1===>  "+projectList1);
						
						 list= changeObjectToJSON(projectList1,emailID);
						model.addObject("ProjectList",list);
						
						System.out.println("*********"+list);
						System.out.println("projList1===>  "+projectList1);
						//model.addObject("accList",accList1);
						//==============================================================================================================
						
						//model.setViewName("AdminWindowPeriod");
						
					}
					catch (Exception e)
					{
						excpObj.setE(e);
						SPMTClientCustomExceptions.getExceptionCode(excpObj);
						model.addObject("msg",ExceptionCode.getExceptionCode(e));
						model.addObject("loginUser",new LoginUser());
						model.setViewName(loginJSP);
						logger.error("msg"+ExceptionCode.getExceptionCode(e));

						//model.addObject("loginUser",new LoginUser());
						//model.setViewName(loginJSP);
						model.setViewName("redirect:/logout");

					}
					}

					return list;
				}
				
				
				
				
				
				
				//window period employeeList --venkat
				@RequestMapping(value="/getEmpList/{SearchwithEmailID}", method= {RequestMethod.GET, RequestMethod.POST})
				@ResponseBody public List <EmployeeList> getEmpList(@PathVariable String SearchwithEmailID,HttpSession session)  {
					ResponseEntity projectList=null;
					ResponseEntity accList=null;
					ResponseEntity empList=null;
					String list= null;
					List <EmployeeList> empList1 = null;
					System.out.println("Hitting in projhect details");
					
					ModelAndView model = new ModelAndView();
					 if(session == null)
				    {
						 model.addObject("msg","Invalid Session");
						 model.setViewName("loginpage");
				    }
				     else
				    {
					
					HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
					HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

					

					try
					{ 
						excpObj.setClassname(AdminController.class);
						 excpObj.setMethodName("getEmpList");
						 String emailID =null;
						 
						 
						 emailID= (String) session.getAttribute("loginUser");
						 excpObj.setEmailId(emailID);

						
						//================================================================
						
						 empList=restTemplate.exchange(webServiceURL +"admin/getAdminEmployeeList/"+SearchwithEmailID, HttpMethod.GET, request, List.class);
							 empList1 = new ArrayList<EmployeeList>();
							
							
					empList1 = (List<EmployeeList>) empList.getBody();
					
					System.out.println("-------- :"+empList1);
							System.out.println("projectList1===>  "+empList1);
						
						 //list= changeObjectToJSON(empList1,emailID);
						model.addObject("empList",empList1);
						
						System.out.println("*********"+empList1);
						System.out.println("projList1===>  "+empList1);
						//model.addObject("accList",accList1);
						//==============================================================================================================
						
						//model.setViewName("AdminWindowPeriod");
						
					}
					catch (Exception e)
					{
						excpObj.setE(e);
						SPMTClientCustomExceptions.getExceptionCode(excpObj);
						model.addObject("msg",ExceptionCode.getExceptionCode(e));
						model.addObject("loginUser",new LoginUser());
						model.setViewName(loginJSP);
						logger.error("msg"+ExceptionCode.getExceptionCode(e));

						//model.addObject("loginUser",new LoginUser());
						//model.setViewName(loginJSP);
						model.setViewName("redirect:/logout");

					}
					}

					return empList1;
				}
		
		//get employee list
				//smemailid search dropdown service
				@RequestMapping(value="/getEmpEmailList/{keyword1}", method= {RequestMethod.GET, RequestMethod.POST})
				@ResponseBody
				public AdminMessageModel getEmpEmailList(@PathVariable String keyword1,HttpSession session)  {
					ResponseEntity areaofworkResponse=null;
					ResponseEntity plResponse=null;
					excpObj.setClassname(AdminController.class);
					 excpObj.setMethodName("SMEmailList");
					 String emailID =null;
						AdminMessageModel smeList=new AdminMessageModel();
					 
					 emailID= (String) session.getAttribute("loginUser");
					 excpObj.setEmailId(emailID);
			try{
				HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
				HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

				/*ArrayList <AdminMessageModel> plList=new ArrayList<AdminMessageModel>();*/

				byte[] plainCredsBytes = keyword1.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);

				plResponse= restTemplate.exchange(webServiceURL+"admin/getEmpEmailList/"+base64Creds, HttpMethod.GET, request, AdminMessageModel.class);
				smeList = (AdminMessageModel) plResponse.getBody();

			}catch(Exception e){
				excpObj.setE(e);
				SPMTClientCustomExceptions.getExceptionCode(excpObj);
			}
					return smeList;
				}
		
				
			/*	//update window period based on selection
				@RequestMapping(value="/updateWindowPeriod/{buID}/{accId}/{projectId}/{empEmailID}/{triggerPeriod}/{triggerDate}", method= {RequestMethod.GET, RequestMethod.POST})
				@ResponseBody public String updateWindowPeriod( @PathVariable String buID,@PathVariable String accId,@PathVariable String projectId,@PathVariable String empEmailID,@PathVariable String triggerPeriod,@PathVariable String triggerDate,HttpSession session)  {
					ResponseEntity projectList=null;
					ResponseEntity accList=null;
					ResponseEntity empList=null;
					String list= null;
					ResponseEntity responseList = null;
					
					System.out.println("Hitting in projhect details");
					
					ModelAndView model = new ModelAndView();
					 if(session == null)
				    {
						 model.addObject("msg","Invalid Session");
						 model.setViewName("loginpage");
				    }
				     else
				    {
					try
					{ 
						HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
						HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
						UpdateWindowPeriod windowPeriod = new UpdateWindowPeriod();
						windowPeriod.setBuID(buID);
						windowPeriod.setAccID(accId);
						windowPeriod.setProjectID(projectId);
						windowPeriod.setEmpEmailID(empEmailID);
						windowPeriod.setTriggerPeriod(triggerPeriod);
						windowPeriod.setTriggerDate(triggerDate);
						HttpEntity<UpdateWindowPeriod> entity = new HttpEntity<UpdateWindowPeriod>(windowPeriod, userCustomHeaders);
						
						excpObj.setClassname(AdminController.class);
						 excpObj.setMethodName("getProjectList");
						 String emailID =null;
						 
						 emailID= (String) session.getAttribute("loginUser");
						 excpObj.setEmailId(emailID);
						//================================================================
						 projectList=restTemplate.exchange(webServiceURL +"admin/updateEmpWindowPeriod", HttpMethod.POST, entity, List.class);
							List <ProjectList> projectList1 = new ArrayList<ProjectList>();
							System.out.println("-------- :"+projectList);
							
							projectList1 = (List<ProjectList>) projectList.getBody();
							System.out.println("projectList1===>  "+projectList1);
						
						 list= changeObjectToJSON(projectList1,emailID);
						model.addObject("ProjectList",list);
						
						System.out.println("*********"+list);
						System.out.println("projList1===>  "+projectList1);
						//model.addObject("accList",accList1);
						//==============================================================================================================
						
						//model.setViewName("AdminWindowPeriod");
						
					}
					catch (Exception e)
					{
						excpObj.setE(e);
						SPMTClientCustomExceptions.getExceptionCode(excpObj);
						model.addObject("msg",ExceptionCode.getExceptionCode(e));
						model.addObject("loginUser",new LoginUser());
						model.setViewName(loginJSP);
						logger.error("msg"+ExceptionCode.getExceptionCode(e));

						//model.addObject("loginUser",new LoginUser());
						//model.setViewName(loginJSP);
						model.setViewName("redirect:/logout");

					}
					}

					return list;
				}
				*/
				
		
				
				//update window period based on selection
				@RequestMapping(value="/updateWindowPeriod", method= {RequestMethod.GET, RequestMethod.POST})
				@ResponseBody 
				public String updateWindowPeriod( UpdateWindowPeriod windowPeriod,HttpSession session)  {
					ResponseEntity projectList=null;
					ResponseEntity accList=null;
					ResponseEntity empList=null;
					String list= null;
					ResponseEntity responseMsg = null;
					String msg = null;
					System.out.println("Hitting in projhect details");
					
					ModelAndView model = new ModelAndView();
					 if(session == null)
				    {
						 model.addObject("msg","Invalid Session");
						 model.setViewName("loginpage");
				    }
				     else
				    {
					try
					{ 
						HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
						HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
						HttpEntity<UpdateWindowPeriod> entity = new HttpEntity<UpdateWindowPeriod>(windowPeriod, userCustomHeaders);
						excpObj.setClassname(AdminController.class);
						 excpObj.setMethodName("updateWindowPeriod");
						 String emailID =null;
						 
						 emailID= (String) session.getAttribute("loginUser");
						 excpObj.setEmailId(emailID);

						 responseMsg=restTemplate.exchange(webServiceURL +"admin/updateEmpWindowPeriod", HttpMethod.POST, entity, String.class);
						 System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");
//							List <ProjectList> projectList1 = new ArrayList<ProjectList>();
							System.out.println("--------12345 :"+responseMsg);
							
							msg = (String) responseMsg.getBody();
							System.out.println("responsemessage===>  "+msg);
						
					
						System.out.println("*********"+msg);
						//==============================================================================================================
						
						//model.setViewName("AdminWindowPeriod");
						
					}
					catch (Exception e)
					{
						excpObj.setE(e);
						SPMTClientCustomExceptions.getExceptionCode(excpObj);
						model.addObject("msg",ExceptionCode.getExceptionCode(e));
						model.addObject("loginUser",new LoginUser());
						model.setViewName(loginJSP);
						logger.error("msg"+ExceptionCode.getExceptionCode(e));

						//model.addObject("loginUser",new LoginUser());
						//model.setViewName(loginJSP);
						model.setViewName("redirect:/logout");

					}
					}

					return msg;
				}

}
