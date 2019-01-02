package com.attra.pms.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.attra.pms.model.AccUpdate;
import com.attra.pms.model.AreaOfWork;
import com.attra.pms.model.CatageoryDomain;
import com.attra.pms.model.CustomMessage;
import com.attra.pms.model.EmployeesInTeam;
import com.attra.pms.model.ExceptionObject;
import com.attra.pms.model.ListResponse;
import com.attra.pms.model.LoginUser;
import com.attra.pms.model.Methodology;
import com.attra.pms.model.Proficiency;
import com.attra.pms.model.ProficiencyDomain;
import com.attra.pms.model.ProficiencyMethodology;
import com.attra.pms.model.ProficiencyTechnology;
import com.attra.pms.model.ProficiencyTool;
import com.attra.pms.model.SkillUpdate;
import com.attra.pms.model.Skills;
import com.attra.pms.model.Stream;
import com.attra.pms.model.ToolTypeEntity;
import com.attra.pms.model.accreditation;
import com.attra.pms.model.message;
import com.attra.pms.model.newSubDomainUpdate;
import com.attra.pms.util.ExceptionCode;
import com.attra.pms.util.MessageProperties;
import com.attra.pms.util.PropertiesCache;
import com.attra.pms.util.SPMTClientCustomExceptions;
import com.attra.pms.util.clientConstants;
import com.attra.pms.util.customHeader;
import com.attra.pms.model.newTechUpdate;
import com.attra.pms.model.newToolandMethodologyUpdate;
@Controller
@Scope("request")
public class ProficiencyController  extends customHeader implements clientConstants{

	private static final Logger logger = Logger.getLogger(ProficiencyController.class.getName());
	private static final String webServiceURL = PropertiesCache.getPropertyByKey(REST_ENDPOINT);


	private static final String getTeamMembersURL = PropertiesCache.getPropertyByKey(LOGIN_ENDPOINT); //employeesInTeam
	private static final String dashboardServiceURL = PropertiesCache.getPropertyByKey(USERDETAILS_ENDPOINT);
	private static final String getListofEmployeesServiceURL = PropertiesCache.getPropertyByKey(ALLEMPLOYEE_ENDPOINT);
	private static final String getListofACCServiceURL = PropertiesCache.getPropertyByKey(SEARCHACCERIDATION_ENDPOINT);

	private static final String loginJSP = MessageProperties.getMessageKey(clientConstants.LOGIN_JSP);
	private static final String viewProficiencyJSP = MessageProperties.getMessageKey(clientConstants.VIEWPROFICIENCY_JSP);
	private static final String updateProficiencyJSP = MessageProperties.getMessageKey(clientConstants.UPDATEPROFICIENCY_JSP);


	private static final String viewProficiencyScreenTitle = MessageProperties.getMessageKey(clientConstants.VIEWPROFICIENCY_TITLE);
	private static final String updateProficiencyScreenTitle = MessageProperties.getMessageKey(clientConstants.UPDATEPROFICIENCY_TITLE);




	@Autowired 
	private RestTemplate restTemplate;


	ExceptionObject  excpObj=new ExceptionObject();
	
	String className =this.getClass().getSimpleName();
	// printing controller name

	@RequestMapping(value="/viewProficiency", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView viewProficiency( HttpSession session)  {
		
		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("viewProficiency");
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

    	  // System.out.println("username asjkhdjasdjkj"+session.getAttribute("loginUser"));
    	   
    	   emailID = (String) session.getAttribute("loginUser");
    	   excpObj.setEmailId(emailID);
    	   
    	   System.out.println("-------------hbhxb-------"+emailID+"-----------jkjxassax---------");
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);



		ResponseEntity viewProficiencyResponse = null;
		try
		{
			viewProficiencyResponse= restTemplate.exchange(webServiceURL+"Proficiency/proficiencyDetails/MA==", HttpMethod.GET, request, Proficiency.class);
			System.out.println("web service url:----"+webServiceURL+"Proficiency/proficiencyDetails/MA==");
			List<ProficiencyTechnology> coreSkills = new ArrayList<ProficiencyTechnology>();
			List <ProficiencyMethodology> methodologyList = new ArrayList<ProficiencyMethodology>();


			
			Proficiency proficiencyDetails =new Proficiency();
			proficiencyDetails=(Proficiency) viewProficiencyResponse.getBody();
			List <ProficiencyDomain> domain = new ArrayList<ProficiencyDomain>();
			List <ProficiencyTool> tool = new ArrayList<ProficiencyTool>();

			if(viewProficiencyResponse.getBody() != null){

				coreSkills=(List<ProficiencyTechnology>) proficiencyDetails.getListOfPTechnology();
				methodologyList = (List<ProficiencyMethodology>) proficiencyDetails.getListofPMethodology();
				domain = (List<ProficiencyDomain>) proficiencyDetails.getListofPDomain();
				
				
				tool = (List<ProficiencyTool>) proficiencyDetails.getListOfPTool();

			}



			model.addObject("coreSkills",coreSkills);
			model.addObject("methodologyList",methodologyList);
			model.addObject("domain",domain);
			model.addObject("tool",tool);
			model.addObject("title", "View Employee Proficiency");


			List <accreditation> accreditationList = new ArrayList<accreditation>();
			ResponseEntity accreditationResponse = null;
			accreditationResponse= restTemplate.exchange(webServiceURL +"Management/accreditation/MA==", HttpMethod.GET, request, List.class);
			accreditationList = (List<accreditation>) accreditationResponse.getBody();
			model.addObject("accreditationList",accreditationList);

			model.addObject("emailId",proficiencyDetails.getEmailID());

			if(proficiencyDetails.getNoDataAvailable() !=null){
				model.addObject("show","");
			}
			else
			{
				model.addObject("show",proficiencyDetails.getNoDataAvailable());

			}
			model.setViewName("viewProficiency");

		}

		catch(Exception e)
		{
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName("loginpage");
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
		}
       }
		return model;

	}

	//updateProficiency
	@RequestMapping(value="/updateProficiency/{msg}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateProficiency( @PathVariable String msg, HttpSession session)  {

		excpObj.setClassname(ProficiencyController.class);
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

		ResponseEntity viewProficiencyResponse = null;
		ResponseEntity streamResponse=null;
		ResponseEntity toolsResponse=null;
		ResponseEntity methodologyResponse=null;
		ResponseEntity domainResponse=null;
		ResponseEntity acceridationListResponse=null;
		ResponseEntity areaofworkResponse=null;
		ResponseEntity domainAreaofworkResponse=null;

		try
		{
			viewProficiencyResponse= restTemplate.exchange(webServiceURL+"Proficiency/proficiencyDetails/MA==", HttpMethod.GET, request, Proficiency.class);

			Proficiency proficiencyDetails =new Proficiency();
			List<ProficiencyTechnology> coreSkills = new ArrayList<ProficiencyTechnology>();

			List <ProficiencyDomain> domain = new ArrayList<ProficiencyDomain>();
			List <ProficiencyTool> tool = new ArrayList<ProficiencyTool>();
			List <ProficiencyMethodology> methodology = new ArrayList<ProficiencyMethodology>();

			if(viewProficiencyResponse.getBody() != null){
				proficiencyDetails=(Proficiency) viewProficiencyResponse.getBody();
				coreSkills=(List<ProficiencyTechnology>) proficiencyDetails.getListOfPTechnology();


				methodology = (List<ProficiencyMethodology>) proficiencyDetails.getListofPMethodology();

				domain = (List<ProficiencyDomain>) proficiencyDetails.getListofPDomain();
				tool = (List<ProficiencyTool>) proficiencyDetails.getListOfPTool();

			}
			model.addObject("methodology",methodology);
			model.addObject("domain",domain);
			model.addObject("coreSkills",coreSkills);
			model.addObject("tool",tool);


			List <accreditation> accreditationList = new ArrayList<accreditation>();
			ResponseEntity accreditationResponse = null;
			accreditationResponse= restTemplate.exchange(webServiceURL+"Management/accreditation/MA==", HttpMethod.GET, request, List.class);
			accreditationList = (List<accreditation>) accreditationResponse.getBody();
			model.addObject("accreditationList",accreditationList);

			List <accreditation> avaliableAccreditationList = new ArrayList<accreditation>();
			acceridationListResponse= restTemplate.exchange(webServiceURL +"Management/searchAccrName", HttpMethod.GET, request, List.class);
			avaliableAccreditationList = (List<accreditation>) acceridationListResponse.getBody();
			model.addObject("allAccreditationList",avaliableAccreditationList);
			List <AreaOfWork> areaofworkList = new ArrayList<AreaOfWork>();

			areaofworkResponse= restTemplate.exchange(webServiceURL +"mytask/getAreaOfWork", HttpMethod.GET, request, List.class);
			/*	List temp = (List) areaofworkResponse.getBody();
			for(int i=0; i<temp.size();i++)
			{
				System.out.println(temp.get(i));
				AreaOfWork tempAOW =new AreaOfWork();
				tempAOW.setAowList((List) temp.get(i));
				tempAOW.setAreaId((int)temp.get(0));
				tempAOW.setAreaWork((String)temp.get(1));
				areaofworkList.add(tempAOW);
			}*/
			areaofworkList = (List<AreaOfWork>) areaofworkResponse.getBody();
			model.addObject("areaofworkList",areaofworkList);


			//domainAreaofworkResponse= restTemplate.exchange(webServiceURL +"mytask/getdomainAreaOfWork", HttpMethod.GET, request, List.class);
			List <AreaOfWork> domainAreaofwork = new ArrayList<AreaOfWork>();
			//	domainAreaofwork = (List<AreaOfWork>) domainAreaofworkResponse.getBody();
			model.addObject("domainAreaofworkResponse",domainAreaofwork);


			/*streamResponse= restTemplate.exchange(webServiceURL+"mytask/getstream/", HttpMethod.GET, request, List.class);
					List <Stream> streamsList = new ArrayList<Stream>();
					streamsList = (List<Stream>) streamResponse.getBody();
					model.addObject("streamsList",streamsList);*/


			toolsResponse= restTemplate.exchange(webServiceURL+"mytask/getToolType", HttpMethod.GET, request, List.class);
			List <ToolTypeEntity> toolsList = new ArrayList<ToolTypeEntity>();
			toolsList = (List<ToolTypeEntity>) toolsResponse.getBody();
			model.addObject("toolsTypeList",toolsList);

			methodologyResponse= restTemplate.exchange(webServiceURL+"mytask/getmethodology", HttpMethod.GET, request, List.class);
			List <Methodology> methodologyList = new ArrayList<Methodology>();
			methodologyList = (List<Methodology>) methodologyResponse.getBody();
			model.addObject("methodologyList",methodologyList);

			domainResponse= restTemplate.exchange(webServiceURL+"mytask/getdomain", HttpMethod.GET, request, List.class);
			List <CatageoryDomain> domainList = new ArrayList<CatageoryDomain>();
			domainList = (List<CatageoryDomain>) domainResponse.getBody();
			model.addObject("domainList",domainList);

			model.addObject("title",updateProficiencyScreenTitle);
			model.setViewName("updateProficiency");
			model.addObject("msg", msg);

			model.addObject("ProficiencyMethodologyObject",new ProficiencyMethodology());
			model.addObject("ProficiencyDomainObject",new ProficiencyDomain());
			model.addObject("ProficiencyToolObject",new ProficiencyTool());
			model.addObject("ProficiencySkillObject",new SkillUpdate());
			model.addObject("ProficiencyAccObject",new accreditation());
		}
		catch (Exception e)
		{
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			//model.addObject("loginUser",new LoginUser());
			//model.setViewName("loginpage");
			model.setViewName("redirect:/logout");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);

		}
       }
		return model;
	}

	@RequestMapping(value = "/getstream/{areaId}", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getStream(@PathVariable String areaId, HttpSession session)  {
		ModelAndView model = new ModelAndView();
		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("getStream");
		 String emailID =null;
		
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

		ResponseEntity streamResponse = null;
		try{
		streamResponse= restTemplate.exchange(webServiceURL+"mytask/getstream/"+areaId, HttpMethod.GET, request, List.class);


		List <Stream> streamsList = new ArrayList<Stream>();
		streamsList = (List<Stream>) streamResponse.getBody();
		model.addObject("streamsList",streamsList);
		}catch(Exception e){
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			
		}
       }
		return model;


	
	}
/*
	@RequestMapping(value="/updateAcc", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateAcc(@ModelAttribute("ProficiencyAccObject") accreditation accreditationObject, HttpSession session)  {
		ResponseEntity deployResponse=null;
		message responseObject =new message();
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		RestTemplate restTemplate = new RestTemplate();
		ModelAndView model = new ModelAndView();
		AccUpdate tempObject=new AccUpdate();
		String name= (String) session.getAttribute("loginUser");
		tempObject.setAccID(Integer.parseInt(accreditationObject.getAccreditationName()));
		String cNo =accreditationObject.getCertificateNo();
		System.out.println("certificate no:"+cNo);
		tempObject.setCertificateNo(cNo);
		tempObject.setEmailID(name);
		tempObject.setIssueDt(accreditationObject.getIssueDt());
		//tempObject.setCertificateNo(accreditationObject.getCertifiedBy());

		HttpEntity<AccUpdate> entity = new HttpEntity<AccUpdate>(tempObject, userCustomHeaders);
		try
		{ 



			//deployResponse = restTemplate.exchange(webServiceURL+"/Employees/updateAccreditation", HttpMethod.POST, entity, message.class);
			deployResponse = restTemplate.exchange(webServiceURL+"Employees/updateAccreditation", HttpMethod.POST, entity, message.class);
			String msg= "";
			responseObject=(message) deployResponse.getBody();

			if(responseObject.getErrormsg()!= null)			{
				msg="failed to update";
			}
			else
			{
				msg="successfully updated";

			}
			msg=responseObject.getErrormsg();
			String temp="redirect:/viewProficiency";
			//temp = temp.concat(msg);
			model.setViewName(temp);


		}
		catch (Exception e)
		{

			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);

		}

		return model;
	}	
*/

	@RequestMapping(value="/updateCoreSkill", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateCoreSkill(@ModelAttribute("ProficiencySkillObject") SkillUpdate proficiencyCoreSkill, HttpSession session)  {
		ResponseEntity deployResponse=null;

		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("updateCoreSkill");
		 String emailID =null;
		 String msg=null;
		
		
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		RestTemplate restTemplate = new RestTemplate();
		ModelAndView model = new ModelAndView();

		HttpEntity<newTechUpdate> entity;// = new HttpEntity<TechUpdate>(proficiencyCoreSkill, userCustomHeaders);
		message responseObject=new message();
		String isPrimary =proficiencyCoreSkill.getIsPrimary();

		newTechUpdate temp =new newTechUpdate();

		if(isPrimary.equals("0"))
		{


			temp.setIsPrimery("N");

		}
		else
		{
			temp.setIsPrimery("Y");
		}

		emailID= (String) session.getAttribute("loginUser");
		excpObj.setEmailId(emailID);

		temp.setEmpComments(proficiencyCoreSkill.getComments());
		temp.setSelfAsses(proficiencyCoreSkill.getSelfAssesment());
		temp.setSkillId(Integer.parseInt(proficiencyCoreSkill.getSkillId()));

		// convert to months 
		int tempmonth=proficiencyCoreSkill.getExperienceYear()*12 +proficiencyCoreSkill.getExperienceMonth();

		temp.setExperience(tempmonth);
		temp.setEmpEmailId(emailID);
		entity = new HttpEntity<newTechUpdate>(temp, userCustomHeaders);

		try{
		//deployResponse = restTemplate.exchange(webServiceURL +"/Skill/updateSkill", HttpMethod.POST, entity, message.class);
		deployResponse = restTemplate.exchange(webServiceURL +"UpdateProficiencyController/updateTechnology", HttpMethod.POST, entity, String.class);
		 msg=(String) deployResponse.getBody();
		System.out.println("charan:"+msg);
		}catch (Exception e) {
			// TODO: handle exception
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
		}

		return msg;
	}	

	/*
	@RequestMapping(value="/updateCoreSkill", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateCoreSkill(@ModelAttribute("ProficiencySkillObject") SkillUpdate proficiencyCoreSkill, HttpSession session)  {
		ResponseEntity deployResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		RestTemplate restTemplate = new RestTemplate();
		ModelAndView model = new ModelAndView();

		HttpEntity<newTechUpdate> entity;// = new HttpEntity<TechUpdate>(proficiencyCoreSkill, userCustomHeaders);
		message responseObject=new message();
		String isPrimary =proficiencyCoreSkill.getIsPrimary();

		newTechUpdate temp =new newTechUpdate();

		if(isPrimary==null)
		{


			temp.setIsPrimery("N");

		}
		else
		{
			temp.setIsPrimery("Y");
		}

		String name= (String) session.getAttribute("loginUser");

		temp.setEmpComments(proficiencyCoreSkill.getComments());
		temp.setSelfAsses(proficiencyCoreSkill.getSelfAssesment());
		temp.setSkillId(Integer.parseInt(proficiencyCoreSkill.getSkillId()));

		// convert to months 
		int tempmonth=proficiencyCoreSkill.getExperienceYear()*12 +proficiencyCoreSkill.getExperienceMonth();

		temp.setExperience(tempmonth);
		temp.setEmpEmailId(name);
		entity = new HttpEntity<newTechUpdate>(temp, userCustomHeaders);

		try
		{ 
			//deployResponse = restTemplate.exchange(webServiceURL +"/Skill/updateSkill", HttpMethod.POST, entity, message.class);
			deployResponse = restTemplate.exchange(webServiceURL +"UpdateProficiencyController/updateTechnology", HttpMethod.POST, entity, String.class);
			String msg=(String) deployResponse.getBody();

			if(responseObject.getErrormsg()!= null)					{
				msg="failed to update";
			}
			else
			{
				msg="successfully";
			}
			String temp1="redirect:/updateProficiency/";
			temp1 = temp1.concat(msg);
			model.setViewName("redirect:/viewProficiency");


		}
		catch (Exception e)
		{

			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);

		}

		return model;
	}	



	@RequestMapping(value="/updateMethodology", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateMethodology(@ModelAttribute("ProficiencyMethodologyObject") ProficiencyMethodology proficiencyMethodology, HttpSession session)  {
		ResponseEntity deployResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
message responseObject =new message();
		RestTemplate restTemplate = new RestTemplate();
		ModelAndView model = new ModelAndView();
	//	HttpEntity<ProficiencyMethodology> entity = new HttpEntity<ProficiencyMethodology>(proficiencyMethodology, userCustomHeaders);
		HttpEntity<newToolandMethodologyUpdate> entity;
		try
		{ 
			String name= (String) session.getAttribute("loginUser");


			newToolandMethodologyUpdate tempObject =new newToolandMethodologyUpdate();
			tempObject.setMethodologyId(Integer.parseInt(proficiencyMethodology.getmName()));
			tempObject.setEmpEmailId(name);
			tempObject.setSelfAsses(proficiencyMethodology.getEmpRating());
			tempObject.setEmpComments(proficiencyMethodology.getComments());
			int tempmonth=proficiencyMethodology.getmExpYears()*12 +proficiencyMethodology.getmExpMonths();
			tempObject.setExperience(tempmonth);
			entity = new HttpEntity<newToolandMethodologyUpdate>(tempObject, userCustomHeaders);

			//deployResponse = restTemplate.exchange(webServiceURL+"/Employees/methodlogyUpdate", HttpMethod.POST, entity, message.class);
			deployResponse = restTemplate.exchange(webServiceURL+"UpdateProficiencyController/updateMethodology", HttpMethod.POST, entity, String.class);

				model.setViewName("redirect:/smeReview");
			String msg=(String) deployResponse.getBody();
			model.setViewName("redirect:/viewProficiency");


		}
		catch (Exception e)
		{

			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);

		}

		return model;
	}

	@RequestMapping(value="/updateTool", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateTool(@ModelAttribute("ProficiencyToolObject") ProficiencyTool proficiencyTool, HttpSession session)  {
		ResponseEntity deployResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		RestTemplate restTemplate = new RestTemplate();
		ModelAndView model = new ModelAndView();
message responseObject =new message();
	//	HttpEntity<ProficiencyTool> entity = new HttpEntity<ProficiencyTool>(proficiencyTool, userCustomHeaders);
HttpEntity<newToolandMethodologyUpdate> entity;

		try
		{ 

			String name= (String) session.getAttribute("loginUser");

			newToolandMethodologyUpdate tempObject =new newToolandMethodologyUpdate();
			tempObject.setToolId(Integer.parseInt(proficiencyTool.getToolName()));
			tempObject.setEmpEmailId(name);
			tempObject.setSelfAsses(proficiencyTool.getEmpRating());
			tempObject.setEmpComments(proficiencyTool.getEmpComments());
			int tempmonth=proficiencyTool.gettExpYears()*12 +proficiencyTool.gettExpMonths();
			tempObject.setExperience(tempmonth);
			entity = new HttpEntity<newToolandMethodologyUpdate>(tempObject, userCustomHeaders);

			//deployResponse = restTemplate.exchange(webServiceURL+"/update/tools", HttpMethod.POST, entity, message.class);
			//deployResponse = restTemplate.exchange(webServiceURL+"/Employees/toolUpdate", HttpMethod.POST, entity, message.class);
			deployResponse = restTemplate.exchange(webServiceURL+"UpdateProficiencyController/updateTools", HttpMethod.POST, entity, String.class);

			//	model.setViewName("redirect:/smeReview");
			String msg= (String) deployResponse.getBody();
			model.setViewName("redirect:/viewProficiency");


		}
		catch (Exception e)
		{

			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
		}

		return model;
	}*/

	@RequestMapping(value="/updateMethodology", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateMethodology(@ModelAttribute("ProficiencyMethodologyObject") ProficiencyMethodology proficiencyMethodology, HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("updateMethodology");
		 String emailID =null;
		 String msg = null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		message responseObject =new message();
		RestTemplate restTemplate = new RestTemplate();
		//	HttpEntity<ProficiencyMethodology> entity = new HttpEntity<ProficiencyMethodology>(proficiencyMethodology, userCustomHeaders);
		HttpEntity<newToolandMethodologyUpdate> entity;
		try
		{ 
//			String name= (String) session.getAttribute("loginUser");
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);


			newToolandMethodologyUpdate tempObject =new newToolandMethodologyUpdate();
			tempObject.setMethodologyId(Integer.parseInt(proficiencyMethodology.getmName()));
			tempObject.setEmpEmailId(emailID);
			tempObject.setSelfAsses(proficiencyMethodology.getEmpRating());
			tempObject.setEmpComments(proficiencyMethodology.getComments());
			int tempmonth=proficiencyMethodology.getmExpYears()*12 +proficiencyMethodology.getmExpMonths();
			tempObject.setExperience(tempmonth);
			entity = new HttpEntity<newToolandMethodologyUpdate>(tempObject, userCustomHeaders);

			//deployResponse = restTemplate.exchange(webServiceURL+"/Employees/methodlogyUpdate", HttpMethod.POST, entity, message.class);
			deployResponse = restTemplate.exchange(webServiceURL+"UpdateProficiencyController/updateMethodology", HttpMethod.POST, entity, String.class);

			 msg=(String) deployResponse.getBody();

			


		}
		catch (Exception e)
		{
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			return "Not Saved";
			

		}
		return msg;
	}
	@RequestMapping(value="/updateTool", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateTool(@ModelAttribute("ProficiencyToolObject") ProficiencyTool proficiencyTool, HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("updateTool");
		 String emailID =null;
		 String msg = null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		RestTemplate restTemplate = new RestTemplate();
		message responseObject =new message();
		//	HttpEntity<ProficiencyTool> entity = new HttpEntity<ProficiencyTool>(proficiencyTool, userCustomHeaders);
		HttpEntity<newToolandMethodologyUpdate> entity;

		try
		{ 

			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);
			newToolandMethodologyUpdate tempObject =new newToolandMethodologyUpdate();
			tempObject.setToolId(Integer.parseInt(proficiencyTool.getToolName()));
			tempObject.setEmpEmailId(emailID);
			tempObject.setSelfAsses(proficiencyTool.getEmpRating());
			tempObject.setEmpComments(proficiencyTool.getEmpComments());
			int tempmonth=proficiencyTool.gettExpYears()*12 +proficiencyTool.gettExpMonths();
			tempObject.setExperience(tempmonth);
			entity = new HttpEntity<newToolandMethodologyUpdate>(tempObject, userCustomHeaders);

			//deployResponse = restTemplate.exchange(webServiceURL+"/update/tools", HttpMethod.POST, entity, message.class);
			//deployResponse = restTemplate.exchange(webServiceURL+"/Employees/toolUpdate", HttpMethod.POST, entity, message.class);
			deployResponse = restTemplate.exchange(webServiceURL+"UpdateProficiencyController/updateTools", HttpMethod.POST, entity, String.class);

			//	model.setViewName("redirect:/smeReview");
			 msg= (String) deployResponse.getBody();
			


		}
		catch (Exception e)
		
		{
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			return "Not Saved";

		}
		return msg;
	}

	@RequestMapping(value="/updateDomain", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateDomain(@ModelAttribute("ProficiencyDomainObject") ProficiencyDomain proficiencyDomain, HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("updateDomain");
		 String emailID =null;
		 String msg= null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);


		newSubDomainUpdate tempObject =new newSubDomainUpdate();


		HttpEntity<newSubDomainUpdate> entity;
		// = new HttpEntity<ProficiencyDomain>(proficiencyDomain, userCustomHeaders);
		message responseObject =new message();
		String isPrimary =proficiencyDomain.getIsPrimary();
		if(isPrimary.equals("0"))
		{
			tempObject.setIsPrimery("N");

		}
		else
		{
			tempObject.setIsPrimery("Y");
		}

		
		tempObject.setSelfAsses(proficiencyDomain.getEmpRating());
		tempObject.setSubDomainId(Integer.parseInt(proficiencyDomain.getdName()));
		tempObject.setEmpComments(proficiencyDomain.getEmpComments());
		//String name= (String) session.getAttribute("loginUser");
		
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		tempObject.setEmpEmailId(emailID);
		int tempmonth=proficiencyDomain.getdExpYears()*12 +proficiencyDomain.getdExpMonths();

		tempObject.setExperiency(tempmonth);

		entity = new HttpEntity<newSubDomainUpdate>(tempObject, userCustomHeaders);

		RestTemplate restTemplate = new RestTemplate();

		try
		{ 

			//deployResponse = restTemplate.exchange(webServiceURL+"/update/domain", HttpMethod.POST, entity, message.class);
			//deployResponse = restTemplate.exchange(webServiceURL+"/Employees/domainUpdate", HttpMethod.POST, entity, message.class);
			deployResponse = restTemplate.exchange(webServiceURL+"UpdateProficiencyController/updateDomain", HttpMethod.POST, entity, String.class);
			//model.setViewName("redirect:/smeReview");
			 msg=(String) deployResponse.getBody();
			
			


		}
		catch (Exception e)
		{
			
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			return "Not Saved";

		}
		return msg;
	}	
	
	@RequestMapping(value="/updateAcc", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateAcc(@ModelAttribute("ProficiencyAccObject") accreditation accreditationObject, HttpSession session)  {
		ResponseEntity deployResponse=null;
		message responseObject =new message();
		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("updateProficiency");
		 String emailID =null;
		 String msg= "";
	
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		RestTemplate restTemplate = new RestTemplate();
		AccUpdate tempObject=new AccUpdate();
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		tempObject.setAccID(Integer.parseInt(accreditationObject.getAccreditationName()));
		String cNo =accreditationObject.getCertificateNo();
		System.out.println("certificate no:"+cNo);
		tempObject.setCertificateNo(cNo);
		tempObject.setEmailID(emailID);
		tempObject.setIssueDt(accreditationObject.getIssueDt());
		//tempObject.setCertificateNo(accreditationObject.getCertifiedBy());

		HttpEntity<AccUpdate> entity = new HttpEntity<AccUpdate>(tempObject, userCustomHeaders);
		try
		{ 



			//deployResponse = restTemplate.exchange(webServiceURL+"/Employees/updateAccreditation", HttpMethod.POST, entity, message.class);
			deployResponse = restTemplate.exchange(webServiceURL+"Employees/updateAccreditation", HttpMethod.POST, entity, message.class);
			
			responseObject=(message) deployResponse.getBody();

			if(responseObject.getErrormsg()!= null)			{
				//msg="failed to update";
				msg=responseObject.getErrormsg();
			}
			else
			{
				//msg="successfully updated";
				msg="New record inserted successfully";

			}
			


		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			return "failed to update";
			}
		return msg;
	}	

	/*
	@RequestMapping(value="/updateDomain", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView updateDomain(@ModelAttribute("ProficiencyDomainObject") ProficiencyDomain proficiencyDomain, HttpSession session)  {
		ResponseEntity deployResponse=null;

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);


		newSubDomainUpdate tempObject =new newSubDomainUpdate();


		HttpEntity<newSubDomainUpdate> entity;
		// = new HttpEntity<ProficiencyDomain>(proficiencyDomain, userCustomHeaders);
message responseObject =new message();
		String isPrimary =proficiencyDomain.getIsPrimary();
		if(isPrimary==null)
		{
			tempObject.setIsPrimery("N");

		}
		else
		{
			tempObject.setIsPrimery("Y");
		}

		tempObject.setEmpComments(proficiencyDomain.getEmpComments());
		tempObject.setSelfAsses(proficiencyDomain.getEmpRating());
		tempObject.setSubDomainId(Integer.parseInt(proficiencyDomain.getdName()));
		String name= (String) session.getAttribute("loginUser");

		tempObject.setEmpEmailId(name);
		int tempmonth=proficiencyDomain.getdExpYears()*12 +proficiencyDomain.getdExpMonths();

		tempObject.setExperiency(tempmonth);

		entity = new HttpEntity<newSubDomainUpdate>(tempObject, userCustomHeaders);

		RestTemplate restTemplate = new RestTemplate();
		ModelAndView model = new ModelAndView();

		try
		{ 

			//deployResponse = restTemplate.exchange(webServiceURL+"/update/domain", HttpMethod.POST, entity, message.class);
			//deployResponse = restTemplate.exchange(webServiceURL+"/Employees/domainUpdate", HttpMethod.POST, entity, message.class);
			deployResponse = restTemplate.exchange(webServiceURL+"UpdateProficiencyController/updateDomain", HttpMethod.POST, entity, String.class);
			//model.setViewName("redirect:/smeReview");
			String msg=(String) deployResponse.getBody();
			model.setViewName("redirect:/viewProficiency");



		}
		catch (Exception e)
		{
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName(loginJSP);
		}

		return model;
	}	
	 */


}
