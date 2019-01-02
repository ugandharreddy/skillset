package com.attra.pms.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jca.endpoint.GenericMessageEndpointManager;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.attra.pms.model.ExceptionObject;
import com.attra.pms.model.UserDetails;
import com.attra.pms.model.ViewAllEmp;
import com.attra.pms.model.accreditation;
import com.attra.pms.model.message;
import com.attra.pms.model.newTechUpdate;
import com.attra.pms.model.reviewObject;
import com.attra.pms.model.CustomMessage;
import com.attra.pms.model.LoginUser;
import com.attra.pms.model.ProficiencyCoreSkill;
import com.attra.pms.model.ProficiencyDomain;
import com.attra.pms.model.ProficiencyMethodology;
import com.attra.pms.model.ProficiencyTool;
import com.attra.pms.model.SMEReview;
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
public class ReviewController extends customHeader implements clientConstants{
	
	private static final Logger logger = Logger.getLogger(ReviewController.class.getName());
	private static final String webServiceURL = PropertiesCache.getPropertyByKey(REST_ENDPOINT);


	private static final String loginURL = PropertiesCache.getPropertyByKey(LOGIN_ENDPOINT);
	private static final String dashboardServiceURL = PropertiesCache.getPropertyByKey(USERDETAILS_ENDPOINT);
	private static final String getListofEmployeesServiceURL = PropertiesCache.getPropertyByKey(ALLEMPLOYEE_ENDPOINT);
	private static final String getListofACCServiceURL = PropertiesCache.getPropertyByKey(SEARCHACCERIDATION_ENDPOINT);

	
	private static final String loginJSP = MessageProperties.getMessageKey(clientConstants.LOGIN_JSP);
	
	
	
	@Autowired 
	private RestTemplate restTemplate;
	
	ExceptionObject  excpObj=new ExceptionObject();

	String className =this.getClass().getSimpleName();
	// printing controller name
	@RequestMapping(value="/reviewSkill/{empId}/{proId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView reviewSkill(@ModelAttribute("ProficiencyCoreSkillObject") ProficiencyCoreSkill proficiencyCoreSkill,@PathVariable String empId,@PathVariable String proId, HttpSession session)  
	{
		ResponseEntity deployResponse=null;
		
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
		
/*
		SMEReview review =new SMEReview();
		review.setMgrComment(proficiencyCoreSkill.getMgrComment());
		review.setMgrRating(proficiencyCoreSkill.getMgrRating());
		review.setEmpId(empId);
		review.seteExpYears(proficiencyCoreSkill.getExpYears());
		review.seteExpMonths(proficiencyCoreSkill.getExpMonths());
		int rating =proficiencyCoreSkill.getEmpRating();
		review.setEmpRating(Integer.toString(rating));		
		review.setProficiencyName(proficiencyCoreSkill.getTechnology());
		*/
		reviewObject obj =new reviewObject();

		HttpEntity<reviewObject> entity;

		
		try
		{ 
		
			excpObj.setClassname(ReviewController.class);
			 excpObj.setMethodName("reviewSkill");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			
			obj.setComment(proficiencyCoreSkill.getMgrComment());
			obj.setEmpEmailId(empId);
			obj.setRating(proficiencyCoreSkill.getMgrRating());
			obj.setProficiencyId(Integer.parseInt(proId));
			entity = new HttpEntity<reviewObject>(obj, userCustomHeaders);

			
			/*deployResponse = restTemplate.exchange(webServiceURL+"/SME/updatecoreSkill", HttpMethod.POST, entity, message.class);*/
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"ReviewController/proficiencyReviewByManager", HttpMethod.POST, entity, String.class);
			System.out.println(webServiceURL+"ReviewController/proficiencyReviewByManager"+"aaaaaaaaaaaaaaaaaaaaaaaaaa");
		
			/*if(proficiencyCoreSkill.getMgrComment()!=null)
			{
				deployResponse = restTemplate.exchange(webServiceURL+"/ReviewController/proficiencyReviewByPL", HttpMethod.POST, entity, message.class);
			}
				else
			{
					if((proficiencyCoreSkill.getMgrComment()!=null))
				deployResponse = restTemplate.exchange(webServiceURL+"/ReviewController/proficiencyReviewBySME", HttpMethod.POST, entity, message.class);
			}
			*/
		/*	
		message temp = new message();
		temp=(message)deployResponse.getBody();
			if(temp.getErrormsg()== null)
			{
				model.addObject("title","successfully updated");
			}
			else
			{
				model.addObject("title","failed to update");

			}
			*/
			
			
			
			//String temp="redirect:/smeReview/";
		//	temp = temp.concat(empId);
			model.setViewName("redirect:/smeTask");
			//model.setViewName("smeTask");


		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			/*model.setViewName(loginJSP);*/
			//model.setViewName("loginpage");
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
	     }
		return model;
	}

	@RequestMapping(value="/reviewSkillByPL/{empId}/{proId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView reviewSkillByPL(@ModelAttribute("ProficiencyCoreSkillObject") ProficiencyCoreSkill proficiencyCoreSkill,@PathVariable String empId,@PathVariable String proId, HttpSession session)  
	{
		ResponseEntity deployResponse=null;
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
		reviewObject obj =new reviewObject();

		HttpEntity<reviewObject> entity;
		
		try
		{ 
			
			excpObj.setClassname(ReviewController.class);
			 excpObj.setMethodName("reviewSkillByPL");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			obj.setComment(proficiencyCoreSkill.getMgrComment());
			obj.setEmpEmailId(empId);
			obj.setRating(proficiencyCoreSkill.getMgrRating());
			obj.setProficiencyId(Integer.parseInt(proId));
			entity = new HttpEntity<reviewObject>(obj, userCustomHeaders);

			
			/*deployResponse = restTemplate.exchange(webServiceURL+"/SME/updatecoreSkill", HttpMethod.POST, entity, message.class);*/
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"ReviewController/proficiencyReviewByPL", HttpMethod.POST, entity, String.class);
			
		
		
		/*	
		message temp = new message();
		temp=(message)deployResponse.getBody();
			if(temp.getErrormsg()== null)
			{
				model.addObject("title","successfully updated");
			}
			else
			{
				model.addObject("title","failed to update");

			}
			*/
			
			
			
			//String temp="redirect:/smeReview/";
		//	temp = temp.concat(empId);
			model.setViewName("redirect:/smeTask");
			//model.setViewName("smeTask");


		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			/*model.setViewName(loginJSP);*/
			//model.setViewName("loginpage");
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));

		}
	     }

		return model;
	}
	@RequestMapping(value="/reviewSkillBySME/{empId}/{proId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView reviewSkillBySME(@ModelAttribute("ProficiencyCoreSkillObject") ProficiencyCoreSkill proficiencyCoreSkill,@PathVariable String empId,@PathVariable String proId, HttpSession session)  
	{
		ResponseEntity deployResponse=null;
		
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
		reviewObject obj =new reviewObject();
		HttpEntity<reviewObject> entity;

		try
		{ 
			
			excpObj.setClassname(ReviewController.class);
			 excpObj.setMethodName("reviewSkillBySME");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			/*byte[] plainCredsBytes = empId.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			*/
			obj.setComment(proficiencyCoreSkill.getMgrComment());
			obj.setEmpEmailId(empId);
			obj.setRating(proficiencyCoreSkill.getMgrRating());
			obj.setProficiencyId(Integer.parseInt(proId));
			entity = new HttpEntity<reviewObject>(obj, userCustomHeaders);

			
			
			
			deployResponse = restTemplate.exchange(webServiceURL+"ReviewController/proficiencyReviewBySME", HttpMethod.POST, entity, String.class);
			
		/*	
		message temp = new message();
		temp=(message)deployResponse.getBody();
			if(temp.getErrormsg()== null)
			{
				model.addObject("title","successfully updated");
			}
			else
			{
				model.addObject("title","failed to update");

			}
			*/
			
			
			
			//String temp="redirect:/smeReview/";
		//	temp = temp.concat(empId);
			model.setViewName("redirect:/smeTask");
			//model.setViewName("smeTask");


		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			/*model.setViewName(loginJSP);*/
			model.setViewName("loginpage");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			//model.setViewName("loginpage");
			model.setViewName("redirect:/logout");
		}
		}

		return model;
	}
	
	
	/*
	@RequestMapping(value="/reviewMethodology/{empId}/{profiName}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView reviewMethodology(@ModelAttribute("ProficiencyMethodologyObject") ProficiencyMethodology proficiencyMethodology,@PathVariable String profiName, @PathVariable String empId, HttpSession session)  {
		ResponseEntity deployResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		RestTemplate restTemplate = new RestTemplate();
		ModelAndView model = new ModelAndView();

		SMEReview review =new SMEReview();

		review.setMgrComment(proficiencyMethodology.getMgrComment());
		review.setMgrRating(proficiencyMethodology.getMgrRating());
		review.setEmpId(empId);
		review.setProficiencyName(profiName);



		HttpEntity<SMEReview> entity = new HttpEntity<SMEReview>(review, userCustomHeaders);
		try
		{ 
			deployResponse = restTemplate.exchange(webServiceURL+"/SME/methodology", HttpMethod.POST, entity, String.class);
			//	model.setViewName("redirect:/smeReview");
			if(deployResponse.getBody().equals(""))
			{
				model.addObject("msg","failed to update");
			}
			else
			{
				model.addObject("msg","successfully updated");

			}
			String temp="redirect:/smeReview/";
			temp = temp.concat((String) empId);
			model.setViewName(temp);

		}
		catch (Exception e)
		{
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName("loginpage");

		}

		return model;
	}

	@RequestMapping(value="/reviewTool/{empId}/{profiName}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView reviewTool(@ModelAttribute("ProficiencyToolObject") ProficiencyTool proficiencyTool,@PathVariable String profiName, @PathVariable String empId, HttpSession session)  {
		ResponseEntity deployResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		RestTemplate restTemplate = new RestTemplate();
		ModelAndView model = new ModelAndView();

		SMEReview review =new SMEReview();
		review.setProficiencyName(profiName);
		review.setMgrComment(proficiencyTool.getMgrComment());
		review.setMgrRating(proficiencyTool.getMgrRating());
		review.setEmpId(empId);
		HttpEntity<SMEReview> entity = new HttpEntity<SMEReview>(review, userCustomHeaders);
		try
		{ 
			deployResponse = restTemplate.exchange(webServiceURL+"/SME/tool", HttpMethod.POST, entity, String.class);
			//	model.setViewName("redirect:/smeReview");
			if(deployResponse.getBody().equals(""))
			{
				model.addObject("msg","failed to update");
			}
			else
			{
				model.addObject("msg","successfully updated");

			}
			String temp="redirect:/smeReview/";
			temp = temp.concat((String) empId);
			model.setViewName(temp);

		}
		catch (Exception e)
		{

			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());

		}

		return model;
	}
*/
	@RequestMapping(value="/reviewDomain/{empId}/{profiName}/{proId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView reviewDomain(@ModelAttribute("ProficiencyDomainObject") ProficiencyDomain proficiencyDomain,@PathVariable String profiName,@PathVariable String proId, @PathVariable String empId, HttpSession session)  {
		ResponseEntity deployResponse=null;
		
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
		System.out.println(proficiencyDomain.getdName());
		SMEReview review =new SMEReview();
		review.setProficiencyName(profiName);
		review.setMgrComment(proficiencyDomain.getMgrComment());
		review.setMgrRating(proficiencyDomain.getMgrRating());
		review.setEmpId(empId);
		review.seteExpYears(proficiencyDomain.getdExpYears());
		review.seteExpMonths(proficiencyDomain.getdExpMonths());
		int rating =proficiencyDomain.getEmpRating();
		review.setEmpRating(Integer.toString(rating));
		
		reviewObject obj =new reviewObject();

		HttpEntity<reviewObject> entity;
		
		try
		{ 
			excpObj.setClassname(ReviewController.class);
			 excpObj.setMethodName("reviewDomain");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			obj.setComment(proficiencyDomain.getMgrComment());
			obj.setEmpEmailId(empId);
			obj.setRating(proficiencyDomain.getMgrRating());
			obj.setProficiencyId(Integer.parseInt(proId));
			entity = new HttpEntity<reviewObject>(obj, userCustomHeaders);
			
			/*deployResponse = restTemplate.exchange(webServiceURL+"/SME/domain", HttpMethod.POST, entity, message.class);*/
			//model.setViewName("redirect:/smeReview");
			deployResponse = restTemplate.exchange(webServiceURL+"ReviewController/proficiencyReviewByManager", HttpMethod.POST, entity, String.class);

			
			/*
			
			message msg =new message();
			msg=(message) deployResponse.getBody();
			if(msg.getErrormsg() !=null)
			{
				model.addObject("msg","failed to update");
			}
			else
			{
				model.addObject("msg","successfully updated");

			}
			String temp="redirect:/smeReview/";
			temp = temp.concat((String) empId);
			*/
			
			
			//model.setViewName(temp);
			model.setViewName("redirect:/smeTask");
			//model.setViewName("smeTask");
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
/*			model.setViewName(loginJSP);
*/			//model.setViewName("loginpage");
			
			model.setViewName("redirect:/logout");
            logger.error("msg"+ExceptionCode.getExceptionCode(e));
		}
	     }

		return model;
	}
	@RequestMapping(value="/reviewDomainbyPL/{empId}/{profiName}/{proId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView reviewDomainbyPL(@ModelAttribute("ProficiencyDomainObject") ProficiencyDomain proficiencyDomain,@PathVariable String profiName,@PathVariable String proId, @PathVariable String empId, HttpSession session)  {
		ResponseEntity deployResponse=null;
		
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
		System.out.println(proficiencyDomain.getdName());
		SMEReview review =new SMEReview();
		review.setProficiencyName(profiName);
		review.setMgrComment(proficiencyDomain.getMgrComment());
		review.setMgrRating(proficiencyDomain.getMgrRating());
		review.setEmpId(empId);
		review.seteExpYears(proficiencyDomain.getdExpYears());
		review.seteExpMonths(proficiencyDomain.getdExpMonths());
		int rating =proficiencyDomain.getEmpRating();
		review.setEmpRating(Integer.toString(rating));
		
		reviewObject obj =new reviewObject();

		HttpEntity<reviewObject> entity;
		
		try
		{ 
			excpObj.setClassname(ReviewController.class);
			 excpObj.setMethodName("reviewDomainbyPL");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			obj.setComment(proficiencyDomain.getMgrComment());
			obj.setEmpEmailId(empId);
			obj.setRating(proficiencyDomain.getMgrRating());
			obj.setProficiencyId(Integer.parseInt(proId));
			entity = new HttpEntity<reviewObject>(obj, userCustomHeaders);
			
			/*deployResponse = restTemplate.exchange(webServiceURL+"/SME/domain", HttpMethod.POST, entity, message.class);*/
			//model.setViewName("redirect:/smeReview");
			deployResponse = restTemplate.exchange(webServiceURL+"ReviewController/proficiencyReviewByPL", HttpMethod.POST, entity, String.class);
System.out.println(webServiceURL+"aaaaaaaaaaaaaaaaaaaaaaaaaa");
			/*
			
			message msg =new message();
			msg=(message) deployResponse.getBody();
			if(msg.getErrormsg() !=null)
			{
				model.addObject("msg","failed to update");
			}
			else
			{
				model.addObject("msg","successfully updated");

			}
			String temp="redirect:/smeReview/";
			temp = temp.concat((String) empId);
			*/
			
			
			//model.setViewName(temp);
			model.setViewName("redirect:/smeTask");
			//model.setViewName("smeTask");
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
/*			model.setViewName(loginJSP);
*/			//model.setViewName("loginpage");
			model.setViewName("redirect:/logout");
			
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
		}
	  }

		return model;
	}
	@RequestMapping(value="/reviewDomainBySME/{empId}/{profiName}/{proId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView reviewDomainBySME(@ModelAttribute("ProficiencyDomainObject") ProficiencyDomain proficiencyDomain,@PathVariable String profiName,@PathVariable String proId, @PathVariable String empId, HttpSession session)  {
		ResponseEntity deployResponse=null;
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
		System.out.println(proficiencyDomain.getdName());
		SMEReview review =new SMEReview();
		review.setProficiencyName(profiName);
		review.setMgrComment(proficiencyDomain.getMgrComment());
		review.setMgrRating(proficiencyDomain.getMgrRating());
		review.setEmpId(empId);
		review.seteExpYears(proficiencyDomain.getdExpYears());
		review.seteExpMonths(proficiencyDomain.getdExpMonths());
		int rating =proficiencyDomain.getEmpRating();
		review.setEmpRating(Integer.toString(rating));
		
		reviewObject obj =new reviewObject();

		HttpEntity<reviewObject> entity;
		
		try
		{ 
			excpObj.setClassname(ReviewController.class);
			 excpObj.setMethodName("reviewDomainBySME");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			obj.setComment(proficiencyDomain.getMgrComment());
			obj.setEmpEmailId(empId);
			obj.setRating(proficiencyDomain.getMgrRating());
			obj.setProficiencyId(Integer.parseInt(proId));
			entity = new HttpEntity<reviewObject>(obj, userCustomHeaders);
			
			/*deployResponse = restTemplate.exchange(webServiceURL+"/SME/domain", HttpMethod.POST, entity, message.class);*/
			//model.setViewName("redirect:/smeReview");
			deployResponse = restTemplate.exchange(webServiceURL+"ReviewController/proficiencyReviewBySME", HttpMethod.POST, entity, String.class);

		
			
			/*
			
			message msg =new message();
			msg=(message) deployResponse.getBody();
			if(msg.getErrormsg() !=null)
			{
				model.addObject("msg","failed to update");
			}
			else
			{
				model.addObject("msg","successfully updated");

			}
			String temp="redirect:/smeReview/";
			temp = temp.concat((String) empId);
			*/
			
			
			//model.setViewName(temp);
			model.setViewName("redirect:/smeTask");
			//model.setViewName("smeTask");
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
/*			model.setViewName(loginJSP);
*/			//model.setViewName("loginpage");
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
		}
	     }

		return model;
	}
}
