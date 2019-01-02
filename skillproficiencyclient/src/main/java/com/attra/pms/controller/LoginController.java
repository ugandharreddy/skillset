package com.attra.pms.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;

import com.attra.pms.model.ExceptionObject;
import com.attra.pms.model.UserDetails;
import com.attra.pms.model.ViewAllEmp;
import com.attra.pms.model.accreditation;
import com.attra.pms.model.loginJson;
import com.attra.pms.model.CustomMessage;
import com.attra.pms.model.LoginUser;
import com.attra.pms.service.AdminAccessService;
import com.attra.pms.service.DashboardService;
import com.attra.pms.service.LoginService;
import com.attra.pms.util.ExceptionCode;
import com.attra.pms.util.MessageProperties;
import com.attra.pms.util.PropertiesCache;
import com.attra.pms.util.SPMTClientCustomExceptions;
import com.attra.pms.util.clientConstants;
import com.attra.pms.util.customHeader;



@Controller
public class LoginController extends customHeader implements clientConstants{

	private static final Logger logger = Logger.getLogger(LoginController.class.getName());
	private static final String webServiceURL = PropertiesCache.getPropertyByKey(REST_ENDPOINT);


	private static final String loginURL = PropertiesCache.getPropertyByKey(LOGIN_ENDPOINT);
	private static final String dashboardServiceURL = PropertiesCache.getPropertyByKey(USERDETAILS_ENDPOINT);
	private static final String getListofEmployeesServiceURL = PropertiesCache.getPropertyByKey(ALLEMPLOYEE_ENDPOINT);
	private static final String getListofACCServiceURL = PropertiesCache.getPropertyByKey(SEARCHACCERIDATION_ENDPOINT);

	private static final String loginJSP = MessageProperties.getMessageKey(clientConstants.LOGIN_JSP);
	private static final String homeJSP = MessageProperties.getMessageKey(clientConstants.HOME_JSP);
	private static final String rmgHomeJSP = MessageProperties.getMessageKey(clientConstants.RMGHOME_JSP);

	private static final String homeScreenTitle = MessageProperties.getMessageKey(clientConstants.HOMESCREEN_TITLE);

	private static final String noRoloeErrorMsg = MessageProperties.getMessageKey(clientConstants.NOROLEERROR);

	
	public String changeObjectToJSON(Object sourceObj) {
		ObjectMapper mapper = new ObjectMapper();
		String JSONString = "";
		try {
			JSONString = mapper.writeValueAsString(sourceObj);
		} catch (JsonGenerationException e) {
			JSONString = "JsonGenerationException: " + e.getMessage();
		} /*catch (JsonMappingException e) {
			JSONString = "JsonMappingException: " + e.getMessage();
		}*/ catch (IOException e) {
			JSONString = "IOException: " + e.getMessage();
		}
		return JSONString;
	}
	
	
	@Autowired 
	private RestTemplate restTemplate;
	
	ExceptionObject  excpObj=new ExceptionObject();


	String className =this.getClass().getSimpleName();
	// printing controller name

  // loading login page
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session) {

		session.invalidate();
		ModelAndView mview=new ModelAndView();
        mview.setViewName("redirect:/");
		return mview;
	}
	@RequestMapping("/")
	public ModelAndView helloWorld() {

		ModelAndView mview=new ModelAndView();
	//	session.setAttribute("sess",loginUserDetail.getSessionId());

		mview.addObject("loginUser",new LoginUser());
		mview.setViewName("loginpage");
		return mview;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/login", method= {RequestMethod.GET})
	public ModelAndView authentactionValidation1(@ModelAttribute("loginUser") LoginUser loginUser,HttpSession session,HttpServletResponse res,HttpServletRequest req)  {
	
		ModelAndView modelObject = new ModelAndView();
		if(session.getAttribute("sessionId") == null){
			try {
				res.sendRedirect(req.getContextPath());
			} catch (IOException e) {
				e.printStackTrace();
				
			}  
		
		
	}
		 if(session == null)
	    {
		   modelObject.addObject("msg","Invalid Session");
		   modelObject.setViewName("loginpage");
	      }
	     else
	     {
	    	 
	    	 modelObject=getModelandViewObjectfromSession(session);
	     }
		
		return modelObject;
		  
	}
	
	//Login WebService 
		@SuppressWarnings("rawtypes")
		@RequestMapping(value="/login", method= {RequestMethod.POST})
		public ModelAndView getEntity(@ModelAttribute("loginUser") LoginUser loginUser,HttpSession session)  {
			ModelAndView modelObject = new ModelAndView();
			// if((String)session.getAttribute("sess") == null)
			 if(session == null)
			{
		    	   
		  modelObject.addObject("msg","Invalid Session");
		  modelObject.addObject("loginUser",new LoginUser());
		  modelObject.setViewName("loginpage");
		   }
		else
			 {
			ResponseEntity response=null;
			ResponseEntity userDetailsResponse=null;
			ResponseEntity getAllSkillResponse=null;
			ResponseEntity getAllEmployeesResponse=null;
			ResponseEntity methodologyResponse=null;
			ResponseEntity domainResponse=null;
			ResponseEntity acceridationListResponse=null;
			ResponseEntity windowPeriodResponse=null;

			//HttpHeaders userCustomHeaders = ReportController.customHeaders((LoginUser)session.getAttribute("loginUser"));

     try{
    	 excpObj.setClassname(LoginController.class);
		 excpObj.setMethodName("getEntity");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

			String uname = loginUser.getUsername();
			/*if(uname==null){
				uname=(String)session.getAttribute(loginUser.getUsername());
			}*/
			modelObject.addObject("uploadedCV","NO");

			String conditionEmail ="attra.com.au";
			String str[]=uname.split("@");
			String st =str[1];
			String uname1=str[0].toLowerCase();
		
			if(conditionEmail.equalsIgnoreCase(st))
			{
				uname=uname1+"@"+conditionEmail;
			} 
			if(uname.contains(conditionEmail))
			{
				String pwd = loginUser.getPassword();

				loginUser = new LoginUser();
				loginUser.setUsername(uname);
				loginUser.setPassword(pwd);

				
				byte[] plainCredsBytes = uname.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String usrname = new String(base64CredsBytes);
				
				byte[] plainCredsBytes1 = pwd.getBytes();
				byte[] base64CredsBytes1 = Base64.encodeBase64(plainCredsBytes1);
				String usrpwd = new String(base64CredsBytes1);
				
				
				HttpHeaders headers = new HttpHeaders();
				
				headers = new HttpHeaders();

				headers.add("userid" , usrname);
				headers.add("userpwd" , usrpwd);

				
				
				
				//	String tempresponse  = commonService.getAccessLevel(webServiceURL+   "/login",userCustomHeaders);

				HttpEntity<String> loginRequest = new HttpEntity<String>(headers);
				try
				{
					
					//response = restTemplate.exchange(webServiceURL+loginURL, HttpMethod.POST, loginRequest, loginJson.class);
					response = restTemplate.exchange(webServiceURL+"/user/login", HttpMethod.POST, loginRequest, loginJson.class);

					System.out.println("web service url:--"+webServiceURL+loginURL);
					
					
					loginJson responseInfo = (loginJson) response.getBody();
					
					CustomMessage loginUserDetail =new CustomMessage();
					loginUserDetail.setSessionId(responseInfo.getUuid());
					loginUserDetail.setRoleList(responseInfo.getRoleList());
					
					session.setAttribute("sessionId", loginUserDetail.getSessionId());
					session.setAttribute("role",loginUserDetail.getRoleList());

					session.setAttribute("loginUser",loginUser.getUsername());
					session.setAttribute("sess",loginUserDetail.getSessionId());
					session.setAttribute("randomUUID",responseInfo.getRandomUUID());

					HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession(loginUserDetail.getSessionId(),responseInfo.getRandomUUID());
					HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
					List <ViewAllEmp> empList = new ArrayList<ViewAllEmp>();
						
					
					
					if(loginUserDetail.getRoleList().contains("MANAGER") || loginUserDetail.getRoleList().contains("SME") || loginUserDetail.getRoleList().contains("EMPLOYEE") || loginUserDetail.getRoleList().contains("ADMIN") ||loginUserDetail.getRoleList().contains("PRACTICE LEAD")  )
					{
						userDetailsResponse= restTemplate.exchange(webServiceURL+dashboardServiceURL, HttpMethod.GET, request, UserDetails.class);
						UserDetails userDetails =new UserDetails();
						
						if(userDetailsResponse.getBody() != null){
							userDetails=(UserDetails) userDetailsResponse.getBody();
							session.setAttribute("expY", userDetails.getTotalExpYears());
								session.setAttribute("expM", userDetails.getTotalExpMonths());
						}
						else
						{
							session.setAttribute("expY", 0);
							session.setAttribute("expM", 0);
						}
						
					   windowPeriodResponse= restTemplate.exchange(webServiceURL+"mytask/getTriggerWindow", HttpMethod.GET, request, Integer.class);
                       Integer windowValue= (Integer) windowPeriodResponse.getBody();
                       session.setAttribute("windowValue",windowValue.toString());
                       modelObject.addObject("userDetails",userDetails);
					   modelObject.addObject("title", homeScreenTitle);
					   /*modelObject.setViewName(homeJSP);*/
					   modelObject.setViewName("homeScreen");

						
						
					}
					
					if(loginUserDetail.getRoleList().contains("MANAGEMENT")){
					
					List <accreditation> avaliableAccreditationList = new ArrayList<accreditation>();
					acceridationListResponse= restTemplate.exchange(webServiceURL+getListofACCServiceURL, HttpMethod.GET, request, List.class);
					avaliableAccreditationList = (List<accreditation>) acceridationListResponse.getBody();
					modelObject.addObject("allAccreditationList",avaliableAccreditationList);
					
					getAllEmployeesResponse = restTemplate.exchange(webServiceURL+getListofEmployeesServiceURL, HttpMethod.GET, request, List.class);
					empList = (List<ViewAllEmp>) getAllEmployeesResponse.getBody();

					modelObject.addObject("employessList", empList);
					modelObject.addObject("jsonObject",changeObjectToJSON(empList));
					
					}
					//user dont have any role
					if(loginUserDetail.getRoleList().isEmpty()){
						modelObject.addObject("msg","You are UnAuthorised to access this application. Please contact to Tool Admin");
						modelObject.addObject("loginUser",new LoginUser());
						modelObject.setViewName("loginpage");
					}
					
					modelObject.addObject("UserObj", responseInfo);
				}
				catch (Exception e)
				{
					excpObj.setE(e);
					excpObj.setEmailId(emailID);
					SPMTClientCustomExceptions.getExceptionCode(excpObj);
					//modelObject.addObject("loginUser",new LoginUser());
					modelObject.addObject("msg",ExceptionCode.getExceptionCode(e));
				/*	modelObject.setViewName(loginJSP);*/
					//modelObject.setViewName("loginpage");
					modelObject.setViewName("redirect:/logout");
					logger.error("msg"+ExceptionCode.getExceptionCode(e));

				}

			}
			else
			{
				modelObject.addObject("msg","Please provide emailId in Attra format");
				modelObject.addObject("loginUser",new LoginUser());
				modelObject.setViewName("loginpage");
			}
     }
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			modelObject.addObject("loginUser",new LoginUser());
			modelObject.addObject("msg",ExceptionCode.getExceptionCode(e));
		/*	modelObject.setViewName(loginJSP);*/
			modelObject.setViewName("loginpage");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}

	}
			return modelObject;

		}
		
		
	private String getUserRole(HttpEntity request,String emailID){
			ResponseEntity response=null;
			excpObj.setClassname(LoginController.class);
			 excpObj.setMethodName("getUserRole");
			 
			 String responseInfo = null;
			 excpObj.setEmailId(emailID);
try{

	response = restTemplate.exchange(webServiceURL+loginURL, HttpMethod.GET, request, String.class);
	 responseInfo = response.getBody().toString();
	 
	 System.out.println("responseInfo===: "+responseInfo);
	
}catch(Exception e){
	excpObj.setE(e);
	SPMTClientCustomExceptions.getExceptionCode(excpObj);
}
			return responseInfo;
		}
		
		@RequestMapping(value="/rmgHome", method= {RequestMethod.GET, RequestMethod.POST})
		public ModelAndView rmgHome(HttpSession session)  {
			ModelAndView modelObject = new ModelAndView();
	
			if(session == null)
        {
    	   
    	 modelObject.addObject("msg","Invalid Session");
	     modelObject.addObject("loginUser",new LoginUser());
	     modelObject.setViewName("loginpage");
       }
       else
       {
	
    	   excpObj.setClassname(LoginController.class);
  		 excpObj.setMethodName("rmgHome");
  		 String emailID =null;
  		 
  		 
  		 emailID= (String) session.getAttribute("loginUser");
  		 excpObj.setEmailId(emailID);

			//HttpHeaders userCustomHeaders=customHeader.getCustomHeaders((LoginUser)session.getAttribute("loginUser"));
			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));

			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

			ResponseEntity getAllEmployeesResponse=null;
			ResponseEntity userDetailsResponse=null;
			ResponseEntity acceridationListResponse=null;
			try
			{
				String role =getUserRole(request,emailID);
				UserDetails userDetails =new UserDetails();

				List <ViewAllEmp> empList = new ArrayList<ViewAllEmp>();
				if(role.equals("MANAGEMENT"))
				{
				userDetailsResponse= restTemplate.exchange(webServiceURL+dashboardServiceURL, HttpMethod.GET, request, UserDetails.class);

				userDetails=(UserDetails) userDetailsResponse.getBody();
			}
				modelObject.addObject("userDetails",userDetails);


				
				List <accreditation> avaliableAccreditationList = new ArrayList<accreditation>();
				acceridationListResponse= restTemplate.exchange(webServiceURL+getListofACCServiceURL, HttpMethod.GET, request, List.class);
				avaliableAccreditationList = (List<accreditation>) acceridationListResponse.getBody();
				modelObject.addObject("allAccreditationList",avaliableAccreditationList);
				
				getAllEmployeesResponse = restTemplate.exchange(webServiceURL+getListofEmployeesServiceURL, HttpMethod.GET, request, List.class);
				empList = (List<ViewAllEmp>) getAllEmployeesResponse.getBody();

				
				modelObject.addObject("employessList", empList);
				
				modelObject.addObject("jsonObject",changeObjectToJSON(empList));
				
				modelObject.setViewName("rmgHomeScreen");
				modelObject.addObject("UserObj", getUserRole(request,emailID));
			}
			catch (Exception e)
			{
				excpObj.setE(e);
				SPMTClientCustomExceptions.getExceptionCode(excpObj);
				modelObject.addObject("msg",ExceptionCode.getExceptionCode(e));
				modelObject.addObject("loginUser",new LoginUser());
				modelObject.setViewName("loginpage");
				logger.error("msg"+ExceptionCode.getExceptionCode(e));
			}
}
			return modelObject;
		}
		@RequestMapping(value="/home", method= {RequestMethod.GET, RequestMethod.POST})
		public ModelAndView homeScreen(HttpSession session)  {
			
			ModelAndView modelObject = new ModelAndView();
			 if(session == null)
		    {
			   modelObject.addObject("msg","Invalid Session");
			   modelObject.setViewName("loginpage");
		      }
		     else
		     {
		    	 
		    	 modelObject=getModelandViewObjectfromSession(session);
			
			/*HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

			ResponseEntity userDetailsResponse=null;
			
			ResponseEntity getAllEmployeesResponse=null;
			ResponseEntity acceridationListResponse=null;
			modelObject.addObject("uploadedCV","NO");

			List <ViewAllEmp> empList = new ArrayList<ViewAllEmp>();
			try
			{
				excpObj.setClassname(LoginController.class);
				 excpObj.setMethodName("homeScreen");
				 String emailID =null;
				 
				 
				 emailID= (String) session.getAttribute("loginUser");
				 excpObj.setEmailId(emailID);

				userDetailsResponse= restTemplate.exchange(webServiceURL+dashboardServiceURL, HttpMethod.GET, request, UserDetails.class);
				UserDetails userDetails =new UserDetails();
				userDetails=(UserDetails) userDetailsResponse.getBody();
				modelObject.addObject("title", homeScreenTitle);
				modelObject.addObject("userDetails",userDetails);
				modelObject.addObject("UserObj", getUserRole(request));
				modelObject.setViewName("homeScreen");
List te=(List)session.getAttribute("role");
if(te.contains("MANAGEMENT")){
	
	         List <accreditation> avaliableAccreditationList = new ArrayList<accreditation>();
	         acceridationListResponse= restTemplate.exchange(webServiceURL+getListofACCServiceURL, HttpMethod.GET, request, List.class);
	         avaliableAccreditationList = (List<accreditation>) acceridationListResponse.getBody();
	         modelObject.addObject("allAccreditationList",avaliableAccreditationList);
	
	         getAllEmployeesResponse = restTemplate.exchange(webServiceURL+getListofEmployeesServiceURL, HttpMethod.GET, request, List.class);
	         empList = (List<ViewAllEmp>) getAllEmployeesResponse.getBody();

	         modelObject.addObject("employessList", empList);
	         modelObject.addObject("jsonObject",changeObjectToJSON(empList));
	}

	 }
	catch (Exception e)
	{
		
		excpObj.setE(e);
		SPMTClientCustomExceptions.getExceptionCode(excpObj);
		modelObject.addObject("msg",ExceptionCode.getExceptionCode(e));
				//modelObject.addObject("loginUser",new LoginUser());
				//modelObject.setViewName("loginpage");
				modelObject.setViewName("redirect:/logout");
				
				
				logger.error("msg"+ExceptionCode.getExceptionCode(e));
			}*/
		       }
			return modelObject;
		}
		
		
		public ModelAndView getModelandViewObjectfromSession(HttpSession session){
			
			ModelAndView modelObject = new ModelAndView();
			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

			ResponseEntity userDetailsResponse=null;
			
			ResponseEntity getAllEmployeesResponse=null;
			ResponseEntity acceridationListResponse=null;
			modelObject.addObject("uploadedCV","NO");

			List <ViewAllEmp> empList = new ArrayList<ViewAllEmp>();
			try
			{
				excpObj.setClassname(LoginController.class);
				 excpObj.setMethodName("homeScreen");
				 String emailID =null;
				 
				 
				 emailID= (String) session.getAttribute("loginUser");
				 excpObj.setEmailId(emailID);
	           if(emailID!=null){
				userDetailsResponse= restTemplate.exchange(webServiceURL+dashboardServiceURL, HttpMethod.GET, request, UserDetails.class);
				UserDetails userDetails =new UserDetails();
				userDetails=(UserDetails) userDetailsResponse.getBody();
				modelObject.addObject("title", homeScreenTitle);
				modelObject.addObject("userDetails",userDetails);
	/*				modelObject.addObject("UserObj", getUserRole(request));
	*/				modelObject.setViewName("homeScreen");
	List te=(List)session.getAttribute("role");
	if(te.contains("MANAGEMENT")){

	        List <accreditation> avaliableAccreditationList = new ArrayList<accreditation>();
	        acceridationListResponse= restTemplate.exchange(webServiceURL+getListofACCServiceURL, HttpMethod.GET, request, List.class);
	        avaliableAccreditationList = (List<accreditation>) acceridationListResponse.getBody();
	        modelObject.addObject("allAccreditationList",avaliableAccreditationList);

	        getAllEmployeesResponse = restTemplate.exchange(webServiceURL+getListofEmployeesServiceURL, HttpMethod.GET, request, List.class);
	        empList = (List<ViewAllEmp>) getAllEmployeesResponse.getBody();

	        modelObject.addObject("employessList", empList);
	        modelObject.addObject("jsonObject",changeObjectToJSON(empList));

	}
	           }else{
	        		 modelObject.addObject("loginUser",new LoginUser());
	        		  modelObject.setViewName("loginpage");
	        	}

	}
	catch (Exception e)
	{
		
		excpObj.setE(e);
		SPMTClientCustomExceptions.getExceptionCode(excpObj);
		modelObject.addObject("msg",ExceptionCode.getExceptionCode(e));
				//modelObject.addObject("loginUser",new LoginUser());
				//modelObject.setViewName("loginpage");
				modelObject.setViewName("redirect:/logout");
				
				
				logger.error("msg"+ExceptionCode.getExceptionCode(e));
			}
		       
			return modelObject;
			
		}
		
}

