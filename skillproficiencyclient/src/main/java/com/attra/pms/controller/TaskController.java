package com.attra.pms.controller;

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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.attra.pms.model.EmployeesInTeam;
import com.attra.pms.model.ExceptionObject;
import com.attra.pms.model.ListResponse;
import com.attra.pms.model.LoginUser;
import com.attra.pms.model.Proficiency;
import com.attra.pms.model.ProficiencyCoreSkill;
import com.attra.pms.model.ProficiencyDomain;
import com.attra.pms.model.ProficiencyTechnology;
import com.attra.pms.model.ProjectList;
import com.attra.pms.model.SmeMyTaskView;
import com.attra.pms.util.ExceptionCode;
import com.attra.pms.util.MessageProperties;
import com.attra.pms.util.PropertiesCache;
import com.attra.pms.util.SPMTClientCustomExceptions;
import com.attra.pms.util.clientConstants;
import com.attra.pms.util.customHeader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
@Controller
@Scope("request")
public class TaskController  extends customHeader implements clientConstants{
	private static final Logger logger = Logger.getLogger(TaskController.class.getName());

	private static final String webServiceURL = PropertiesCache.getPropertyByKey(REST_ENDPOINT);
	private static final String loginURL = PropertiesCache.getPropertyByKey(LOGIN_ENDPOINT);


	private static final String loginJSP = MessageProperties.getMessageKey(clientConstants.LOGIN_JSP);
	private static final String smeTaskJSP = MessageProperties.getMessageKey(clientConstants.SMETASK_JSP);
	private static final String AssignTaskToSMEJSP = MessageProperties.getMessageKey(clientConstants.ASSIGNTOSME_JSP);
	private static final String AssignToPLJSP = MessageProperties.getMessageKey(clientConstants.ASSIGNTOPL_JSP);
	private static final String smeReviewJSP = MessageProperties.getMessageKey(clientConstants.SMEREVIEW_JSP);
	
	@Autowired 
	private RestTemplate restTemplate;
	


	String className =this.getClass().getSimpleName();
	
	ExceptionObject  excpObj=new ExceptionObject();
	
	// printing controller name

	private String getUserRole(HttpEntity request,String emailID){
		ResponseEntity response=null;
		excpObj.setClassname(TaskController.class);
		 excpObj.setMethodName("getUserRole");

		excpObj.setEmailId(emailID);
		
		response = restTemplate.exchange(webServiceURL+loginURL, HttpMethod.GET, request, String.class);
		String responseInfo = response.getBody().toString();

		return responseInfo;
	}
	

	@RequestMapping(value="/smeTask", method= {RequestMethod.GET, RequestMethod.POST})
	
	public ModelAndView smeTask( HttpSession session)  {
		ResponseEntity smeTask=null;
		ResponseEntity managerTask=null;
		ResponseEntity managerDecisionTask=null;
		ResponseEntity praticeLeadTask=null;
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

		RestTemplate restTemplate = new RestTemplate();
		try
		{ 
			excpObj.setClassname(TaskController.class);
			 excpObj.setMethodName("smeTask");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			List <SmeMyTaskView> smeTaskList = new ArrayList<SmeMyTaskView>();
			List <SmeMyTaskView> managerTaskList = new ArrayList<SmeMyTaskView>();
			List <SmeMyTaskView> managerDecisionTaskList = new ArrayList<SmeMyTaskView>();
			List <SmeMyTaskView> praticeLeadTaskList = new ArrayList<SmeMyTaskView>();
			ListResponse tempResponse =new ListResponse();// new

			//System.out.println(session.getAttribute("role"));
		//	model.addObject("UserObj", getUserRole(request));

			List arr=(List) session.getAttribute("role");
			
			if(arr.contains("MANAGER"))
			{
			managerTask = restTemplate.exchange(webServiceURL+"ReviewController/getManagerTask", HttpMethod.GET, request, List.class);
				managerDecisionTask = restTemplate.exchange(webServiceURL+"ReviewController/getManagerTaskForHigherProficiency", HttpMethod.GET, request, List.class);
			//	managerTask = restTemplate.exchange(webServiceURL+   "/smeAssesment/getProficiencyUpdates", HttpMethod.GET, request, List.class);
			managerTaskList = (List<SmeMyTaskView>) managerTask.getBody();//new

			//	tempResponse = (ListResponse) managerTask.getBody();//new

			//	managerTaskList = (List<SmeMyTaskView>) tempResponse.getList();//new

				

				managerDecisionTaskList =(List<SmeMyTaskView>) managerDecisionTask.getBody();
				
				/*if(arr.contains("SME LEAD"))
				{
					smeTask = restTemplate.exchange(webServiceURL+"/smeAssesment/getSMEUpdates", HttpMethod.GET, request, List.class);
					smeTaskList =(List<SmeMyTaskView>) smeTask.getBody();
				}*/
			}
			
			if(arr.contains("SME") )
			{
				smeTask = restTemplate.exchange(webServiceURL+"ReviewController/getSMETask", HttpMethod.GET, request, List.class);
				smeTaskList =(List<SmeMyTaskView>) smeTask.getBody();
			}
			if(arr.contains("PRACTICE LEAD"))
			{
				praticeLeadTask = restTemplate.exchange(webServiceURL+"ReviewController/getPLTask", HttpMethod.GET, request, List.class);
				praticeLeadTaskList =(List<SmeMyTaskView>) praticeLeadTask.getBody();
			}
			
			model.addObject("managerTaskList", managerTaskList);
			model.addObject("managerDecisionTaskList", managerDecisionTaskList);
			model.addObject("smeTaskList", smeTaskList);
			model.addObject("praticeLeadTaskList", praticeLeadTaskList);
			/*model.setViewName(smeTaskJSP);*/
			model.setViewName("smeTask");
			
			model.addObject("UserObj", arr);

		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);

			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			/*model.setViewName(loginJSP);*/
			//model.setViewName("loginpage");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			model.setViewName("redirect:/logout");
		}
		}

		return model;
	}



	@RequestMapping(value="/smeReview/{empId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView smeReview(@PathVariable String empId  , HttpSession session)  {

		
		ModelAndView model = new ModelAndView();
		 if(session == null)
	    {
			 model.addObject("msg","Invalid Session");
			 model.setViewName("loginpage");
	      }
	     else
	     {

		//ResponseEntity userDetailsResponse=null;
		

		try
		{
			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
			excpObj.setClassname(TaskController.class);
			 excpObj.setMethodName("smeReview");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			
			List<ProficiencyTechnology> coreSkills = new ArrayList<ProficiencyTechnology>();
			List <ProficiencyDomain> domain = new ArrayList<ProficiencyDomain>();


			ResponseEntity viewProficiencyResponse = null;
			ResponseEntity viewDomainProficiencyResponse = null;

List arr=(List) session.getAttribute("role");
			if(arr.contains("MANAGER"))
				
			{
				String conditionEmail ="com.au";
				if(!empId.contains(conditionEmail))
				{
					empId=empId.concat(".au");
				}
				
				byte[] plainCredsBytes = empId.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String empIdBase64Creds = new String(base64CredsBytes);
				
			viewProficiencyResponse= restTemplate.exchange(webServiceURL+ "ReviewController/getListofSkillsToReviewByManager/"+empIdBase64Creds, HttpMethod.GET, request, List.class);
			viewDomainProficiencyResponse= restTemplate.exchange(webServiceURL+ "ReviewController/getListofDomainToReviewByManager/"+empIdBase64Creds, HttpMethod.GET, request, List.class);

			
			}
			/*else if(arr.contains("SME"))
			{
				viewProficiencyResponse= restTemplate.exchange(webServiceURL+ "SME/showTaskForSMEReview/"+empId, HttpMethod.GET, request, Proficiency.class);
	
			}
			*/
			
			
			Proficiency proficiencyDetails =new Proficiency();
			coreSkills=(List<ProficiencyTechnology>) viewProficiencyResponse.getBody();
			domain=(List<ProficiencyDomain>) viewDomainProficiencyResponse.getBody();

/*
			if(proficiencyDetails.getNoDataAvailable().length()==0)
			{
				coreSkills=(List<ProficiencyTechnology>) proficiencyDetails.getListOfPTechnology();
				domain = (List<ProficiencyDomain>) proficiencyDetails.getListofPDomain();

			}
*/

			model.addObject("coreSkills",coreSkills);
			model.addObject("ProficiencyCoreSkillObject",new ProficiencyCoreSkill());



			model.addObject("domain",domain);
			model.addObject("ProficiencyDomainObject",new ProficiencyDomain());


			model.addObject("empID",empId);
			//model.setViewName(smeReviewJSP);


			model.setViewName("managerReview");
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
		//	model.addObject("loginUser",new LoginUser());
		//	model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
		}
	     }
		return model;
	}
	
	


	@RequestMapping(value="/smeandLeadDecisionReview/{empId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView smeandLeadDecisionReview(@PathVariable String empId , HttpSession session)  {

		

		//ResponseEntity userDetailsResponse=null;
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
			excpObj.setClassname(TaskController.class);
			 excpObj.setMethodName("smeandLeadDecisionReview");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			ResponseEntity viewProficiencyResponse = null;
			ResponseEntity viewDomainProficiencyResponse = null;

List arr=(List) session.getAttribute("role");
			
			if(arr.contains("SME"))
			{
				String conditionEmail ="com.au";
				if(!empId.contains(conditionEmail))
				{
					empId=empId.concat(".au");
				}
				byte[] plainCredsBytes = empId.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String empIdBase64Creds = new String(base64CredsBytes);
				
				viewProficiencyResponse= restTemplate.exchange(webServiceURL+ "ReviewController/getListOfTechToBeReviewedBySME/"+empIdBase64Creds, HttpMethod.GET, request, List.class);
				viewDomainProficiencyResponse= restTemplate.exchange(webServiceURL+ "ReviewController/getListofDomainToReviewBySME/"+empIdBase64Creds, HttpMethod.GET, request, List.class);

				model.setViewName(smeReviewJSP);
			}
			
			List<ProficiencyTechnology> coreSkills = new ArrayList<ProficiencyTechnology>();
			List <ProficiencyDomain> domain = new ArrayList<ProficiencyDomain>();
			
			Proficiency proficiencyDetails =new Proficiency();
			coreSkills=(List<ProficiencyTechnology>) viewProficiencyResponse.getBody();
			domain=(List<ProficiencyDomain>) viewDomainProficiencyResponse.getBody();

/*
			if(proficiencyDetails.getNoDataAvailable().length()==0)
			{
				coreSkills=(List<ProficiencyTechnology>) proficiencyDetails.getListOfPTechnology();
				domain = (List<ProficiencyDomain>) proficiencyDetails.getListofPDomain();

			}
*/
			

			
			model.addObject("coreSkills",coreSkills);
			model.addObject("ProficiencyCoreSkillObject",new ProficiencyCoreSkill());



			model.addObject("domain",domain);
			model.addObject("ProficiencyDomainObject",new ProficiencyDomain());


			model.addObject("empID",empId);


		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
		
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			
			}
		     }
		return model;
	}


	@RequestMapping(value="/smeDecisionReview/{empId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView smeDecisionReview(@PathVariable String empId , HttpSession session)  {

		

		ModelAndView model = new ModelAndView();
		 if(session == null)
	    {
			 model.addObject("msg","Invalid Session");
			 model.setViewName("loginpage");
	      }
	     else
	     {
		
		//ResponseEntity userDetailsResponse=null;
		

		try
		{
			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
			excpObj.setClassname(TaskController.class);
			 excpObj.setMethodName("smeDecisionReview");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			ResponseEntity viewProficiencyResponse = null;
			ResponseEntity viewDomainProficiencyResponse = null;

List arr=(List) session.getAttribute("role");
			
			if(arr.contains("MANAGER"))
			{
				String conditionEmail ="com.au";
				if(!empId.contains(conditionEmail))
				{
					empId=empId.concat(".au");
				}
				byte[] plainCredsBytes = empId.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String empIdBase64Creds = new String(base64CredsBytes);
				
				System.out.println(webServiceURL+ "/ReviewController/getListofSkillsToReviewByManagerDecision/"+empIdBase64Creds);
			viewProficiencyResponse= restTemplate.exchange(webServiceURL+ "ReviewController/getListofSkillsToReviewByManagerDecision/"+empIdBase64Creds, HttpMethod.GET, request, List.class);
			viewDomainProficiencyResponse= restTemplate.exchange(webServiceURL+ "ReviewController/getListofDomainToReviewByManagerDecision/"+empIdBase64Creds, HttpMethod.GET, request, List.class);

			model.setViewName(AssignToPLJSP);

			}
			/*else if(arr.contains("SME"))
			{
				viewProficiencyResponse= restTemplate.exchange(webServiceURL+ "SME/showTaskForSMEReview/"+empId, HttpMethod.GET, request, Proficiency.class);
	
			}
			*/
			List<ProficiencyTechnology> coreSkills = new ArrayList<ProficiencyTechnology>();
			List <ProficiencyDomain> domain = new ArrayList<ProficiencyDomain>();
			
			
			Proficiency proficiencyDetails =new Proficiency();
			coreSkills=(List<ProficiencyTechnology>) viewProficiencyResponse.getBody();
			domain=(List<ProficiencyDomain>) viewDomainProficiencyResponse.getBody();



			
		
			
			model.addObject("coreSkills",coreSkills);
			model.addObject("ProficiencyCoreSkillObject",new ProficiencyCoreSkill());



			model.addObject("domain",domain);
			model.addObject("ProficiencyDomainObject",new ProficiencyDomain());


			model.addObject("empID",empId);


		}
		catch (Exception e)
		{

			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
		//	model.addObject("loginUser",new LoginUser());
		//	model.setViewName(loginJSP);
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
		}
		}
		return model;
	}
	
	
	
	@RequestMapping(value="/getPLTask/{empId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView assignTaskToSME(@PathVariable String empId, HttpSession session)  {

		
		ModelAndView model = new ModelAndView();
		 if(session == null)
	    {
			 model.addObject("msg","Invalid Session");
			 model.setViewName("loginpage");
	      }
	     else
	     {
		

		//ResponseEntity userDetailsResponse=null;
		

		try
		{
			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
			excpObj.setClassname(TaskController.class);
			 excpObj.setMethodName("assignTaskToSME");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			//model.addObject("UserObj", getUserRole(request));
			List<ProficiencyTechnology> coreSkills = new ArrayList<ProficiencyTechnology>();
			List <ProficiencyDomain> domain = new ArrayList<ProficiencyDomain>();
			ResponseEntity viewProficiencyResponse = null;
			ResponseEntity viewDomainProficiencyResponse = null;


List arr=(List) session.getAttribute("role");
			
			if(arr.contains("PRACTICE LEAD"))
			{
				String conditionEmail ="com.au";
				if(!empId.contains(conditionEmail))
				{
					empId=empId.concat(".au");
				}
				byte[] plainCredsBytes = empId.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String empIdBase64Creds = new String(base64CredsBytes);
				
				viewProficiencyResponse= restTemplate.exchange(webServiceURL+ "ReviewController/getListofSkillsToReviewByPL/"+empIdBase64Creds, HttpMethod.GET, request, List.class);
				viewDomainProficiencyResponse= restTemplate.exchange(webServiceURL+ "ReviewController/getListofDomainToReviewByPL/"+empIdBase64Creds, HttpMethod.GET, request, List.class);
			
			}
			
			
			
			Proficiency proficiencyDetails =new Proficiency();
		//	proficiencyDetails=(Proficiency) viewProficiencyResponse.getBody();
			coreSkills=(List<ProficiencyTechnology>) viewProficiencyResponse.getBody();
			domain=(List<ProficiencyDomain>) viewDomainProficiencyResponse.getBody();

/*
			if(proficiencyDetails.getNoDataAvailable().length()==0)
			{
				coreSkills=(List<ProficiencyTechnology>) proficiencyDetails.getListOfPTechnology();
				domain = (List<ProficiencyDomain>) proficiencyDetails.getListofPDomain();

			}
*/
			

			model.addObject("coreSkills",coreSkills);
			model.addObject("ProficiencyCoreSkillObject",new ProficiencyCoreSkill());



			model.addObject("domain",domain);
			model.addObject("ProficiencyDomainObject",new ProficiencyDomain());


			model.addObject("empID",empId);


			/*model.setViewName(AssignTaskToSMEJSP);*/
			model.setViewName("AssignTaskToSME");
			
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
	
	
	
	/// not sure about below
	
	@RequestMapping(value="/assignTaskToPL/{empId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView assignTaskToPL(@PathVariable String empId, HttpSession session)  {

		
		ModelAndView model = new ModelAndView();
		 if(session == null)
	    {
			 model.addObject("msg","Invalid Session");
			 model.setViewName("loginpage");
	      }
	     else
	     {

		//ResponseEntity userDetailsResponse=null;
		

		try
		{
			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
			
			excpObj.setClassname(TaskController.class);
			 excpObj.setMethodName("assignTaskToPL");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			model.addObject("UserObj", getUserRole(request,emailID));
			List<ProficiencyTechnology> coreSkills = new ArrayList<ProficiencyTechnology>();
			List <ProficiencyDomain> domain = new ArrayList<ProficiencyDomain>();
			ResponseEntity viewProficiencyResponse = null;

			if(getUserRole(request,emailID).contains("PRACTICE LEAD"))
			{
				String conditionEmail ="com.au";
				if(!empId.contains(conditionEmail))
				{
					empId=empId.concat(".au");
				}
				byte[] plainCredsBytes = empId.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String empIdBase64Creds = new String(base64CredsBytes);
				
			viewProficiencyResponse= restTemplate.exchange(webServiceURL+"PracticeLead/PLTaskList/"+empId, HttpMethod.GET, request, Proficiency.class);
			
			}
			
			
			
			Proficiency proficiencyDetails =new Proficiency();
			proficiencyDetails=(Proficiency) viewProficiencyResponse.getBody();


			if(proficiencyDetails.getNoDataAvailable().length()==0)
			{
				coreSkills=(List<ProficiencyTechnology>) proficiencyDetails.getListOfPTechnology();
				domain = (List<ProficiencyDomain>) proficiencyDetails.getListofPDomain();

			}

			

			model.addObject("coreSkills",coreSkills);
			model.addObject("ProficiencyCoreSkillObject",new ProficiencyCoreSkill());



			model.addObject("domain",domain);
			model.addObject("ProficiencyDomainObject",new ProficiencyDomain());


			model.addObject("empID",empId);


			model.setViewName(AssignTaskToSMEJSP);
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

	//
}
