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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.attra.pms.model.EmployeesInTeam;
import com.attra.pms.model.ExceptionObject;
import com.attra.pms.model.LoginUser;
import com.attra.pms.model.Proficiency;
import com.attra.pms.model.ProficiencyDomain;
import com.attra.pms.model.ProficiencyMethodology;
import com.attra.pms.model.ProficiencyTechnology;
import com.attra.pms.model.ProficiencyTool;
import com.attra.pms.model.ProjectList;
import com.attra.pms.model.accreditation;
import com.attra.pms.model.deploy;
import com.attra.pms.util.ExceptionCode;
import com.attra.pms.util.MessageProperties;
import com.attra.pms.util.PropertiesCache;
import com.attra.pms.util.SPMTClientCustomExceptions;
import com.attra.pms.util.clientConstants;
import com.attra.pms.util.customHeader;
@Controller
@Scope("request")
public class EmployeeController extends customHeader implements clientConstants{

	private static final Logger logger = Logger.getLogger(EmployeeController.class.getName());
	private static final String webServiceURL = PropertiesCache.getPropertyByKey(REST_ENDPOINT);


	private static final String loginJSP = MessageProperties.getMessageKey(clientConstants.LOGIN_JSP);
	private static final String rmgteamJSP = MessageProperties.getMessageKey(clientConstants.RMGTEAM_JSP);
	private static final String viewProficiencyJSP = MessageProperties.getMessageKey(clientConstants.VIEWPROFICIENCY_JSP);
	private static final String rmgTeamTitle = MessageProperties.getMessageKey(clientConstants.RMGTEAM_TITLE);

	
	@Autowired 
	private RestTemplate restTemplate;
	

	ExceptionObject  excpObj=new ExceptionObject();
	
	String className =this.getClass().getSimpleName();
	// printing controller name

	@RequestMapping(value="/employeeDetailsById/{empId}/{empName}/{empemail}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getemployeeDetailsById(@PathVariable String empId, @PathVariable String empName, @PathVariable String empemail, HttpSession session)  {

		excpObj.setClassname(EmployeeController.class);
		 excpObj.setMethodName("getemployeeDetailsById");
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
		//ModelAndView model = new ModelAndView();
		ResponseEntity viewProficiencyResponse = null;

		if(empemail.toLowerCase().endsWith(".au"))
		{

		}
		else{

			empemail = empemail.concat(".au");
		}

		byte[] plainSkillBytes = empemail.getBytes();
		byte[] base64SkillBytes = Base64.encodeBase64(plainSkillBytes);
		String base64Skill = new String(base64SkillBytes);

		try{
			viewProficiencyResponse= restTemplate.exchange(webServiceURL+ "Proficiency/proficiencyDetails/"+base64Skill, HttpMethod.GET, request, Proficiency.class);

			Proficiency proficiencyDetails =new Proficiency();
			proficiencyDetails=(Proficiency) viewProficiencyResponse.getBody();
			List<ProficiencyTechnology> coreSkills = new ArrayList<ProficiencyTechnology>();
			List <ProficiencyMethodology> methodologyList = new ArrayList<ProficiencyMethodology>();
			List <ProficiencyDomain> domain = new ArrayList<ProficiencyDomain>();
			List <ProficiencyTool> tool = new ArrayList<ProficiencyTool>();

			if(proficiencyDetails !=null)
			{
				if(proficiencyDetails.getNoDataAvailable().length()==0)
				{
				model.addObject("emailId",proficiencyDetails.getEmailID());
				coreSkills=(List<ProficiencyTechnology>) proficiencyDetails.getListOfPTechnology();
				domain = (List<ProficiencyDomain>) proficiencyDetails.getListofPDomain();
				methodologyList = (List<ProficiencyMethodology>) proficiencyDetails.getListofPMethodology();
				tool = (List<ProficiencyTool>) proficiencyDetails.getListOfPTool();
				}
				model.addObject("show",proficiencyDetails.getNoDataAvailable());

			}

			model.addObject("coreSkills",coreSkills);
			model.addObject("methodologyList",methodologyList);
			model.addObject("domain",domain);
			model.addObject("tool",tool);


			List <accreditation> accreditationList = new ArrayList<accreditation>();
			ResponseEntity accreditationResponse = null;
			accreditationResponse= restTemplate.exchange(webServiceURL +"Management/accreditation/"+base64Skill, HttpMethod.GET, request, List.class);
			accreditationList = (List<accreditation>) accreditationResponse.getBody();
			if(accreditationList != null)
			{
			model.addObject("accreditationList",accreditationList);
			}
			empName = empName.concat("  ");
			model.addObject("title","Proficiency of " +empName);
			/*model.setViewName(viewProficiencyJSP);
*/
			model.setViewName("viewProficiency");
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


	@RequestMapping(value="/employeesListBySkill/{skillName}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView getemployeesListBySkill(@PathVariable String skillName, HttpSession session)  {
		excpObj.setClassname(EmployeeController.class);
		 excpObj.setMethodName("getemployeesListBySkill");
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
		ResponseEntity getListOfTeam=null;
		//ModelAndView model = new ModelAndView();
		ResponseEntity projectListResponse=null;


		byte[] plainSkillBytes = skillName.getBytes();
		byte[] base64SkillBytes = Base64.encodeBase64(plainSkillBytes);
		String base64Skill = new String(base64SkillBytes);

		try
		{
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);
			getListOfTeam = restTemplate.exchange(webServiceURL+"Skill/skillWiseEmployeeList/"+base64Skill, HttpMethod.GET, request, List.class);

			List <EmployeesInTeam> empList = new ArrayList<EmployeesInTeam>();
			empList = (List<EmployeesInTeam>) getListOfTeam.getBody();

			List <ProjectList> projectList = new ArrayList<ProjectList>();
			projectListResponse = restTemplate.exchange(webServiceURL+"/Management/projectList", HttpMethod.GET, request, List.class);
			projectList = (List<ProjectList>) projectListResponse.getBody();

			model.addObject("projectList", projectList);
			model.addObject("employessList", empList);
			model.addObject("title", rmgTeamTitle); 
			model.addObject("emp",new EmployeesInTeam());
			model.setViewName(rmgteamJSP);

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



	@RequestMapping(value="/relieveEmployee/{empId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView deployEmployee(@PathVariable String empId, HttpSession session)  {
		ResponseEntity deployResponse=null;
		excpObj.setClassname(EmployeeController.class);
		 excpObj.setMethodName("deployEmployee");
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

		RestTemplate restTemplate = new RestTemplate();
		//ModelAndView model = new ModelAndView();

		try
		{ 
			deployResponse = restTemplate.exchange(webServiceURL+"/backToBench/"+empId, HttpMethod.GET, request, String.class);

			model.setViewName("redirect:/teamMembers");

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

	@RequestMapping(value="/deployEmployee/{projectId}/{empId}", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView deployEmployee(@PathVariable String projectId,@PathVariable String empId, HttpSession session)  {
		ResponseEntity deployResponse=null;
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
		deploy bu = new deploy();
		bu.setEmpId(Integer.parseInt(empId));
		bu.setProjectId(Integer.parseInt(projectId));
		RestTemplate restTemplate = new RestTemplate();
		//ModelAndView model = new ModelAndView();

		HttpEntity<deploy> entity = new HttpEntity<deploy>(bu, userCustomHeaders);
		try
		{ 
			excpObj.setClassname(EmployeeController.class);
			 excpObj.setMethodName("deployEmployee");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);
			 
			deployResponse = restTemplate.exchange(webServiceURL+"/deploy/deployEmployee", HttpMethod.POST, entity, String.class);
			model.setViewName("redirect:/benchTeamMembers");

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

		
}
