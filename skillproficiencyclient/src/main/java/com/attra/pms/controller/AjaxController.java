package com.attra.pms.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.CanonicalizationMethod;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.attra.pms.model.CombinationalSearch;
import com.attra.pms.model.CustomMessage;
import com.attra.pms.model.Domain;
import com.attra.pms.model.EmployeeSearchJson;
import com.attra.pms.model.ExceptionObject;
import com.attra.pms.model.LoginUser;
import com.attra.pms.model.SkillUpdate;
import com.attra.pms.model.Skills;
import com.attra.pms.model.Stream;
import com.attra.pms.model.Tools;
import com.attra.pms.model.UserDetails;
import com.attra.pms.model.ViewAllEmp;
import com.attra.pms.model.accreditation;
import com.attra.pms.model.loginJson;
import com.attra.pms.model.message;
import com.attra.pms.model.newTechUpdate;
import com.attra.pms.util.ExceptionCode;
import com.attra.pms.util.MessageProperties;
import com.attra.pms.util.PropertiesCache;
import com.attra.pms.util.SPMTClientCustomExceptions;
import com.attra.pms.util.clientConstants;
import com.attra.pms.util.customHeader;



@Controller
public class AjaxController extends customHeader implements clientConstants{
	
	private static final Logger logger = Logger.getLogger(AjaxController.class.getName());

	private static final String webServiceURL = PropertiesCache.getPropertyByKey(REST_ENDPOINT);

	private static final String getSMEListServiceURL = PropertiesCache.getPropertyByKey(SMELIST_ENDPOINT);
	private static final String getListofSkillsServiceURL = PropertiesCache.getPropertyByKey(GETSKILLS_ENDPOINT);
	private static final String getListofSubDomainServiceURL = PropertiesCache.getPropertyByKey(GETSUBDOMAIN_ENDPOINT);
	private static final String getListofToolServiceURL = PropertiesCache.getPropertyByKey(GETTOOLST_ENDPOINT);
	private static final String getListofStreamsServiceURL = PropertiesCache.getPropertyByKey(GETSTREAMS_ENDPOINT);
	private static final String getListofSuggestSkillServiceURL = PropertiesCache.getPropertyByKey(SUGGESTSKILL_ENDPOINT);
	private static final String getListofSuggestProjectServiceURL = PropertiesCache.getPropertyByKey(SUGGESTPROJECT_ENDPOINT);
	private static final String getListofSuggestToolServiceURL = PropertiesCache.getPropertyByKey(SUGGESTTOOL_ENDPOINT);
	private static final String getListofSuggestDomainServiceURL = PropertiesCache.getPropertyByKey(SUGGESTDOMAIN_ENDPOINT);
	private static final String getListofSuggestMethodologyServiceURL = PropertiesCache.getPropertyByKey(SUGGESTMETHODOLOGY_ENDPOINT);
	private static final String freeSearchServiceURL = PropertiesCache.getPropertyByKey(FREESEARCH_ENDPOINT);

	private static final String updateDoaminExpServiceURL = PropertiesCache.getPropertyByKey(UPDATEDOMAINEXP_ENDPOINT);
	private static final String updateToolExpServiceURL = PropertiesCache.getPropertyByKey(UPDATETOOLEXP_ENDPOINT);
	private static final String updateMethodologyExpServiceURL = PropertiesCache.getPropertyByKey(UPDATEMETHODOLOGYEXP_ENDPOINT);
	private static final String updateTechExpServiceURL = PropertiesCache.getPropertyByKey(UPDATETECHEXP_ENDPOINT);
	private static final String updateSkillServiceURL = PropertiesCache.getPropertyByKey(UPDATESKILL_ENDPOINT);
	
	private static final String assignToPLServiceURL = PropertiesCache.getPropertyByKey(ASSIGNTOPL_ENDPOINT);
	private static final String assignToSMEServiceURL = PropertiesCache.getPropertyByKey(ASSIGNTOSME_ENDPOINT);
	
	private static final String dashboardServiceURL = PropertiesCache.getPropertyByKey(USERDETAILS_ENDPOINT);
	private static final String getListofEmployeesServiceURL = PropertiesCache.getPropertyByKey(ALLEMPLOYEE_ENDPOINT);
	private static final String getListofACCServiceURL = PropertiesCache.getPropertyByKey(SEARCHACCERIDATION_ENDPOINT);
	private static final String homeScreenTitle = MessageProperties.getMessageKey(clientConstants.HOMESCREEN_TITLE);

	@Autowired 
	private RestTemplate restTemplate;
	
	String className =this.getClass().getSimpleName();
	// printing controller name
	
	ExceptionObject  excpObj=new ExceptionObject();
	//ajax code

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
	
	@RequestMapping(value = "getSkills/{StreamId}", method = RequestMethod.POST)
	@ResponseBody
	public List getSkills(@PathVariable String StreamId, HttpSession session)  {
		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("getSkills");
		 String emailID =null;
		 List <Skills> skillList = null;
	
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
	try{
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ResponseEntity responseList = null;
		responseList= restTemplate.exchange(webServiceURL+getListofSkillsServiceURL+StreamId, HttpMethod.GET, request, List.class);


		 skillList = new ArrayList<Skills>();
		skillList = (List<Skills>) responseList.getBody();
	}catch(Exception e){
		excpObj.setE(e);
		SPMTClientCustomExceptions.getExceptionCode(excpObj);
	}
		
		return skillList;

	}
	//ajax call for subdomain

	/*@RequestMapping(value = "getSubdomain/{domainId}", method = RequestMethod.POST)
	@ResponseBody
	public List getSubdomain(@PathVariable String domainId, HttpSession session)  {

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		byte[] plainCredsBytes = domainId.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		ListResponse tempResponse =new ListResponse();
		ResponseEntity responseList = null;
		responseList= restTemplate.exchange(webServiceURL+getListofSubDomainServiceURL+base64Creds, HttpMethod.GET, request, ListResponse.class);
		tempResponse = (ListResponse) responseList.getBody();//new

		List <Domain> subDomainList = new ArrayList<Domain>();
		subDomainList = (List<Domain>) tempResponse.getList();
		return subDomainList;

	}
	*/
		
	@RequestMapping(value = "getSubdomain/{domainId}", method = RequestMethod.POST)
	@ResponseBody
	public List getSubdomain(@PathVariable String domainId, HttpSession session)  {
		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("getSubdomain");
		 String emailID =null;
		 ResponseEntity responseList = null;
		 List <Domain> subDomainList = null;
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
try{
	HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
	HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

	byte[] plainCredsBytes = domainId.getBytes();
	byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
	String base64Creds = new String(base64CredsBytes);
		
	responseList= restTemplate.exchange(webServiceURL+getListofSubDomainServiceURL+base64Creds, HttpMethod.GET, request, List.class);
	 subDomainList = new ArrayList<Domain>();
	
}catch(Exception e){
	excpObj.setE(e);
	SPMTClientCustomExceptions.getExceptionCode(excpObj);
}
		
return 	subDomainList = (List<Domain>) responseList.getBody();


	}
	
		
	//Skill name Auto search
	@RequestMapping(value="/getSkillName/{skillNameMayBe}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List getSkillName(@PathVariable String skillNameMayBe, HttpSession session)  {
		
		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("getSkillName");
		 String emailID =null;
		 ResponseEntity responseList = null;
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		 try{
			 
			 HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
				HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

				byte[] plainCredsBytes = skillNameMayBe.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);
				
				String name= (String) session.getAttribute("loginUser");
				byte[] nameBytes = name.getBytes();
				byte[] base64CredsByt = Base64.encodeBase64(nameBytes);
				String nameString = new String(base64CredsByt);
				
				
				responseList= restTemplate.exchange(webServiceURL+"Search/getSkillSearch/"+nameString+"/"+base64Creds, HttpMethod.GET, request, List.class);

				//List <SuggestSkillName> skillNameList = new ArrayList<SuggestSkillName>();
				//skillNameList = (List<SuggestSkillName>) responseList.getBody();
		 }catch(Exception e){
			 excpObj.setE(e);
			 SPMTClientCustomExceptions.getExceptionCode(excpObj);
		 }

		return (List) responseList.getBody();


	}

	//Skill name Auto search
	@RequestMapping(value="/getProjectame/{projectNameMayBe}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List getProjectame(@PathVariable String projectNameMayBe, HttpSession session)  {
		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("getProjectame");
		 String emailID =null;
		 ResponseEntity responseList = null;
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		 try{
			 HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
				HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

				byte[] plainCredsBytes = projectNameMayBe.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);
				
				String name= (String) session.getAttribute("loginUser");
				byte[] nameBytes = name.getBytes();
				byte[] base64CredsByt = Base64.encodeBase64(nameBytes);
				String nameString = new String(base64CredsByt);
				
				
				responseList= restTemplate.exchange(webServiceURL+"Search/getProjectSearch/"+base64Creds, HttpMethod.GET, request, List.class);

				/*List <ProjectList> projectsNameList = new ArrayList<ProjectList>();
				projectsNameList = (List<ProjectList>) responseList.getBody();
				return projectsNameList;*/
		 }catch(Exception e){
			 excpObj.setE(e);
			 SPMTClientCustomExceptions.getExceptionCode(excpObj);
		 }

		
		return (List) responseList.getBody();

	}

	@RequestMapping(value="/searchEmployeeWithSkill/{skillName}/{project}/{tools}/{methodologyName}/{domainName}/{techPrimary}/{domainPrimary}/{tExp}/{dExp}/{tPro}/{dPro}/{acc}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String searchEmployeeWithSkill(@PathVariable String skillName, @PathVariable String project, @PathVariable String tools, @PathVariable String methodologyName, @PathVariable String domainName,  @PathVariable String techPrimary, @PathVariable String domainPrimary,@PathVariable String tExp,@PathVariable String dExp,@PathVariable Integer tPro,@PathVariable Integer dPro, @PathVariable String acc,HttpSession session)  {

		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("searchEmployeeWithSkill");
		 String emailID =null;
		 List <EmployeeSearchJson> skilledEmployees = null;
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		 try{
			 HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
				HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
				ResponseEntity responseList = null;
				CombinationalSearch temp =new CombinationalSearch();
				temp.setSkillName(skillName);
				temp.setProjectName(project);
				temp.setToolName(tools);
				temp.setMethodologyName(methodologyName);
				temp.setDomainName(domainName);
				temp.setTechPrimary(techPrimary);
				temp.setDomainPrimary(domainPrimary);
				temp.setTechExp(tExp);
				temp.setDomainExp(dExp);
				temp.setTechProficiency(tPro.toString());
				temp.setDomainProficiency(dPro.toString());
				temp.setAccriditation(acc);
				HttpEntity<CombinationalSearch> entity = new HttpEntity<CombinationalSearch>(temp, userCustomHeaders);
				
				responseList= restTemplate.exchange(webServiceURL+ "Employees/employeeDetails", HttpMethod.POST, entity, List.class);
				 skilledEmployees = new ArrayList<EmployeeSearchJson>();
				skilledEmployees = (List<EmployeeSearchJson>) responseList.getBody();
				
				/*System.out.println("/searchEmployeeWithSkill size==========="+skilledEmployees.size());*/
		 }catch(Exception e){
			 excpObj.setE(e);
			 SPMTClientCustomExceptions.getExceptionCode(excpObj);
		 }
		  
		
		return changeObjectToJSON(skilledEmployees);


	}
	@RequestMapping(value="/searchByKeyword/{keyword}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String searchByKeyword(@PathVariable String keyword,  HttpSession session)  {
		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("searchByKeyword");
		 String emailID =null;
		 List <EmployeeSearchJson> KeywordResult =null;
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
try{
	HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
	HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
	ResponseEntity responseList = null;
	responseList= restTemplate.exchange(webServiceURL+"Management/searchByKeyword/"+keyword, HttpMethod.GET, request, List.class);
	 KeywordResult = new ArrayList<EmployeeSearchJson>();
	KeywordResult = (List<EmployeeSearchJson>) responseList.getBody();
	
	
}catch(Exception e){
	excpObj.setE(e);
	SPMTClientCustomExceptions.getExceptionCode(excpObj);
	}
				return changeObjectToJSON(KeywordResult);
	}

	@RequestMapping(value="/gettools/{toolType}", method= {RequestMethod.GET, RequestMethod.POST},headers = "Accept=application/json", produces = "application/json")
	@ResponseBody
	public List gettools(@PathVariable String toolType, HttpSession session)  {
		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("gettools");
		 String emailID =null;
		 List <Tools> toolNameList = null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
try{
	HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
	HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

	byte[] plainCredsBytes = toolType.getBytes();
	byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
	String base64Creds = new String(base64CredsBytes);
	//ListResponse tempResponse =new ListResponse();// new

	ResponseEntity responseList = null;
	responseList= restTemplate.exchange(webServiceURL+getListofToolServiceURL+base64Creds, HttpMethod.GET, request, List.class);
	//responseList = (ListResponse) responseList.getBody();//new

	 toolNameList = new ArrayList<Tools>();
	toolNameList = (List<Tools>) responseList.getBody();
}catch(Exception e){
	excpObj.setE(e);
	SPMTClientCustomExceptions.getExceptionCode(excpObj);
}
		
		return toolNameList;
	}

	@RequestMapping(value="/getRMGtools/{toolType}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List getRMGtools(@PathVariable String toolType, HttpSession session)  {
		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("getRMGtools");
		 String emailID =null;
		 ResponseEntity responseList = null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		 try{

				HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
				HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
				byte[] plainCredsBytes = toolType.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);
				
				String name= (String) session.getAttribute("loginUser");
				byte[] nameBytes = name.getBytes();
				byte[] base64CredsByt = Base64.encodeBase64(nameBytes);
				String nameString = new String(base64CredsByt);
				
				
				responseList= restTemplate.exchange(webServiceURL+"Search/getToolSearch/"+nameString+"/"+base64Creds, HttpMethod.GET, request, List.class);

				/*List  toolNameList = new ArrayList();
				toolNameList = (List) responseList.getBody();
				return toolNameList;*/

			 
		 }catch(Exception e){
			 excpObj.setE(e);
			 SPMTClientCustomExceptions.getExceptionCode(excpObj);
		 }
		return (List) responseList.getBody();

	}

	@RequestMapping(value="/getRMGDomains/{domainString}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List getRMGDomains(@PathVariable String domainString, HttpSession session)  {
		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("getRMGDomains");
		 String emailID =null;
		 ResponseEntity responseList = null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
try{
	HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
	HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
	byte[] plainCredsBytes = domainString.getBytes();
	byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
	String base64Creds = new String(base64CredsBytes);
	String name= (String) session.getAttribute("loginUser");
	byte[] nameBytes = name.getBytes();
	byte[] base64CredsByt = Base64.encodeBase64(nameBytes);
	String nameString = new String(base64CredsByt);
		
	responseList= restTemplate.exchange(webServiceURL+"Search/getSubDomainSearch/"+nameString+"/"+base64Creds, HttpMethod.GET, request, List.class);
/*
	List  toolNameList = new ArrayList();
	toolNameList = (List) responseList.getBody();
	return toolNameList;*/
	
}catch(Exception e){
	excpObj.setE(e);
	SPMTClientCustomExceptions.getExceptionCode(excpObj);
}
				
return (List) responseList.getBody();

	}

	@RequestMapping(value="/getRMGMethodologies/{methodString}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List getRMGMethodologies(@PathVariable String methodString, HttpSession session)  {

		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("getRMGMethodologies");
		 String emailID =null;
		 ResponseEntity responseList = null;
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		 try{
			 HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
				HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
				byte[] plainCredsBytes = methodString.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);
				String name= (String) session.getAttribute("loginUser");
				byte[] nameBytes = name.getBytes();
				byte[] base64CredsByt = Base64.encodeBase64(nameBytes);
				String nameString = new String(base64CredsByt);
				
				
				responseList= restTemplate.exchange(webServiceURL+"Search/getMethodologySearch/"+nameString+"/"+base64Creds, HttpMethod.GET, request, List.class);

				/*List  toolNameList = new ArrayList();
				toolNameList = (List) responseList.getBody();
				return toolNameList;*/
			 
		 }catch(Exception e){
			 excpObj.setE(e);
			 SPMTClientCustomExceptions.getExceptionCode(excpObj);
		 }
		
		return (List) responseList.getBody();

	}


	@RequestMapping(value="/getStreamByAOW/{aowString}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List getStreamByAOW(@PathVariable String aowString, HttpSession session)  {
		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("getStreamByAOW");
		 String emailID =null;
		 List <Stream> streamsList = new ArrayList<Stream>();
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		 try{
			 HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
				HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
				ResponseEntity streamResponse = null;

				streamResponse= restTemplate.exchange(webServiceURL+getListofStreamsServiceURL+aowString, HttpMethod.GET, request, List.class);
				
				streamsList = (List<Stream>) streamResponse.getBody();

			 
		 }catch(Exception e){
			 excpObj.setE(e);
			 SPMTClientCustomExceptions.getExceptionCode(excpObj);
		 }

				return streamsList;
	}

	@RequestMapping(value="/updateDomainExp/{domainId}/{expInyear}/{expInMonth}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateDomainExp(@PathVariable String domainId, @PathVariable String expInyear, @PathVariable String expInMonth, HttpSession session)  {

		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("updateDomainExp");
		 String emailID =null;
		 String responseString =  null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		 try{

				HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
				HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
				ResponseEntity response = null;

				byte[] plainCredsBytes = domainId.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);
				
				response= restTemplate.exchange(webServiceURL+updateDoaminExpServiceURL+base64Creds+"/"+expInyear+"/"+expInMonth, HttpMethod.GET, request, message.class);
				message msgString= (message) response.getBody();
				 responseString =  msgString.getErrormsg();

			 
		 }catch(Exception e){
			 excpObj.setE(e);
			 SPMTClientCustomExceptions.getExceptionCode(excpObj);
		 }
		 		return responseString;
	}

	@RequestMapping(value="/updateToolsExp/{toolId}/{expInyear}/{expInMonth}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateToolsExp(@PathVariable String toolId, @PathVariable String expInyear, @PathVariable String expInMonth, HttpSession session)  {

		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("updateToolsExp");
		 String emailID =null;
		 String responseString =  null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		try{
			HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
			HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
			ResponseEntity response = null;

			byte[] plainCredsBytes = toolId.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);
			
			
			response= restTemplate.exchange(webServiceURL+updateToolExpServiceURL+base64Creds+"/"+expInyear+"/"+expInMonth, HttpMethod.GET, request, message.class);
			message msgString= (message) response.getBody();
			 responseString =  msgString.getErrormsg();

			
		}catch(Exception e){
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
		}
		 		return responseString;
	}

	@RequestMapping(value="/updateMethodExp/{methodlogyId}/{expInyear}/{expInMonth}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateMethodExp(@PathVariable String methodlogyId, @PathVariable String expInyear, @PathVariable String expInMonth, HttpSession session)  {

		excpObj.setClassname(AjaxController.class);
		 excpObj.setMethodName("updateMethodExp");
		 String emailID =null;
			String responseString =   null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		 try{
			 HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
				HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
				ResponseEntity response = null;

				byte[] plainCredsBytes = methodlogyId.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);
				
				response= restTemplate.exchange(webServiceURL+updateMethodologyExpServiceURL+base64Creds+"/"+expInyear+"/"+expInMonth, HttpMethod.GET, request, message.class);
				message msgString= (message) response.getBody();
				 responseString =  msgString.getErrormsg();

			 
		 }catch(Exception e){
			 excpObj.setE(e);
			 SPMTClientCustomExceptions.getExceptionCode(excpObj);
		 }
				return responseString;
	}
	
	@RequestMapping(value="/updateTechExp/{techId}/{expInyear}/{expInMonth}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String updateTechExp(@PathVariable String techId, @PathVariable String expInyear, @PathVariable String expInMonth, HttpSession session)  {

		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("updateTechExp");
		 String emailID =null;
			String responseString =  null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
		 try{
			 HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
				HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
				ResponseEntity response = null;
				
				byte[] plainCredsBytes = techId.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);

				response= restTemplate.exchange(webServiceURL+updateTechExpServiceURL+base64Creds+"/"+expInyear+"/"+expInMonth, HttpMethod.GET, request, message.class);
				message msgString= (message) response.getBody();
				 responseString =  msgString.getErrormsg();
		 }catch(Exception e){
			 excpObj.setE(e);
			 SPMTClientCustomExceptions.getExceptionCode(excpObj);
		 }
		
		return responseString;
	}
	/*
	@RequestMapping(value="/SMEList/{AOW}/{pID}/{empId}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
		public List SMEList(@PathVariable String AOW, @PathVariable String pID, @PathVariable String empId, HttpSession session)  {

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		ResponseEntity response = null;

		response= restTemplate.exchange(webServiceURL+"ReviewController/getSMEList/"+Integer.valueOf(pID), HttpMethod.GET, request, List.class);
	//	SMEAssesmentJSon tt=new SMEAssesmentJSon();
		//tt= (SMEAssesmentJSon) response.getBody();
		List<SMEList> listResponse=(List<SMEList> ) response.getBody();
		return listResponse;
		//smeEmailId
	}
	*/
	
	@RequestMapping(value="/SMEList/{AOW}/{pID}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<String> getSMEList(@PathVariable String AOW, @PathVariable String pID, HttpSession session)  {
		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("getSMEList");
		 String emailID =null;
		 List <String> streamsList = new ArrayList<String>();
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
			try{
				HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
				/*if(empId.toLowerCase().endsWith(".au"))
				{
			
				}
				else{
			
					empId = empId.concat(".au");
				}
				byte[] plainCredsBytes = empId.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);		*/
				HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
				ResponseEntity streamResponse = null;
			
				streamResponse= restTemplate.exchange(webServiceURL+"ReviewController/getSMEList/"+Integer.valueOf(pID), HttpMethod.GET, request, List.class);
				
				streamsList = (List<String>) streamResponse.getBody();
			}catch(Exception e){
				excpObj.setE(e);
				SPMTClientCustomExceptions.getExceptionCode(excpObj);
			}

		
		return streamsList;
	}

	
	
	
	@RequestMapping(value="/AssignTaskToPL/{empId}/{pid}/{Type}/{comments}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String AssignTaskToPL(@PathVariable String empId, @PathVariable String pid, @PathVariable String Type, @PathVariable String comments, HttpSession session)  {

		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("AssignTaskToPL");
		 String emailID =null;
		 String msgString=  null;
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);
try{
	HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
	
	
	byte[] plainCredsBytes = comments.getBytes();
	byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
	String base64Creds = new String(base64CredsBytes);
	
	
	ResponseEntity response = null;
	HttpEntity<String> entity = new HttpEntity<String>( userCustomHeaders);
	response= restTemplate.exchange(webServiceURL+"ReviewController/assignTaskToPL/"+pid+"/"+base64Creds+"/"+empId , HttpMethod.GET, entity, String.class);
	 msgString= (String) response.getBody();
	
}catch(Exception e){
	excpObj.setE(e);
	SPMTClientCustomExceptions.getExceptionCode(excpObj);
}
		
		
		return msgString;
	}
	
	@RequestMapping(value="/AssignTaskToSME/{empId}/{smeId}/{pID}/{plComment}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String AssignTaskToSME(@PathVariable String empId, @PathVariable String smeId, @PathVariable String pID, @PathVariable String plComment, HttpSession session)  {

		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("AssignTaskToSME");
		 String emailID =null;
		 String msgString= null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		 try{
			 HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
				ResponseEntity response = null;
				
				/*AssignToSME assign =new AssignToSME();
				assign.setEmpID(Integer.parseInt(empId));
				assign.setSmeId(Integer.parseInt(smeId));
				assign.setProficiencyType(Type);
				assign.setProficiencyName(skillName);	
				assign.setPlComment(plComment);*/
				HttpEntity<String> entity = new HttpEntity<String>( userCustomHeaders);
				
				byte[] plainCredsBytes = empId.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String empEmailId = new String(base64CredsBytes);
				
				byte[] plainCredsBytes1 = plComment.getBytes();
				byte[] base64CredsBytes1 = Base64.encodeBase64(plainCredsBytes1);
				String plComments = new String(base64CredsBytes1);
				
				byte[] plainCredsBytes2 = smeId.getBytes();
				byte[] base64CredsBytes2 = Base64.encodeBase64(plainCredsBytes2);
				String smeEmailId = new String(base64CredsBytes2);
				
				System.out.println(empEmailId);
				System.out.println(plComments);
				System.out.println(pID);
				System.out.println(smeEmailId);

				response= restTemplate.exchange(webServiceURL+"ReviewController/assignTaskToSME/"+pID+"/"+empEmailId+"/"+smeEmailId+"/"+plComments, HttpMethod.GET, entity, String.class);
				/*message msgString= (message) response.getBody();
				String stringResponse= msgString.getErrormsg();*/
				 msgString=(String) response.getBody();
			 
		 }catch(Exception e){
			 excpObj.setE(e);
			 SPMTClientCustomExceptions.getExceptionCode(excpObj);
		 }
		
				
		return msgString;

	}
	
	@RequestMapping(value="/saveTech", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String saveTech(@ModelAttribute("ProficiencySkillObject") SkillUpdate proficiencyCoreSkill, HttpSession session)  {
		
		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("saveTech");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		ResponseEntity deployResponse=null;
		String msg="";
message responseObject = new message();

HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		RestTemplate restTemplate = new RestTemplate();
		ModelAndView model = new ModelAndView();

		HttpEntity<newTechUpdate> entity;
		message responseObject1=new message();
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
		
		LoginUser loginUser= (LoginUser) session.getAttribute("loginUser");
		String name= loginUser.getUsername();

		temp.setEmpComments(proficiencyCoreSkill.getComments());
		temp.setSelfAsses(proficiencyCoreSkill.getSelfAssesment());
		temp.setSkillId(Integer.parseInt(proficiencyCoreSkill.getSkillId()));
		
		proficiencyCoreSkill.getExperienceYear();
		proficiencyCoreSkill.getExperienceMonth();
		// convert to months 
		int tempmonth=proficiencyCoreSkill.getExperienceYear()*12 +proficiencyCoreSkill.getExperienceMonth();
	
		temp.setExperience(tempmonth);
		temp.setEmpEmailId(name);
		entity = new HttpEntity<newTechUpdate>(temp, userCustomHeaders);
		try
		{ 
			deployResponse = restTemplate.exchange(webServiceURL+updateSkillServiceURL, HttpMethod.POST, entity, message.class);
			responseObject1 = (message) deployResponse.getBody();

			if(responseObject1.getErrormsg() != null)
			{
				msg="failed to update";
			}
			else
			{
				msg="successfully";

			}
		}
		catch (Exception e)
		{
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			model.addObject("msg",ExceptionCode.getExceptionCode(e));
			model.addObject("loginUser",new LoginUser());
			model.setViewName("loginpage");
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
		}
		return msg;
	}
	
	

	
	@RequestMapping(value="/EmpUploadResume", method= {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    
    public ModelAndView uploadResume(@RequestParam("file") MultipartFile file, HttpSession session)  { 
		  // public String uploadResume(@RequestParam(value="vcfFile",required = false)  MultipartHttpServletRequest request, HttpSession session)  {

/*	HttpHeaders userCustomHeaders = new HttpHeaders();
		
		userCustomHeaders = new HttpHeaders();
		String rr=(String)session.getAttribute("randomUUID");
		userCustomHeaders.add("sessionId"+"_"+rr , (String)session.getAttribute("sess"));
		userCustomHeaders.add("randomUUID" , rr);
		//userCustomHeaders.add("enctype","multipart/form-data");
		//userCustomHeaders.setContentType(new MediaType("multipart/form-data"));*/
		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("uploadResume");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		ModelAndView mview=new ModelAndView();

		if (!file.isEmpty()) {
			try {
				long size = file.getSize();
				if(size<=3048576){
				byte[] bytes = file.getBytes();
			
			
				File resume = new File(file.getOriginalFilename());
				String fileName  = resume.getAbsolutePath();
				boolean b1 = fileName.endsWith("docx");
				boolean b2 = fileName.endsWith("doc");
				if(fileName.endsWith("docx")|fileName.endsWith("doc")){
				System.out.println(b1+":"+b2);
				System.out.println(fileName);
				String cananocliPath = resume.getCanonicalPath();
				System.out.println("canonicalPath"+cananocliPath);
				
				file.transferTo(resume);
		
		
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));

		userCustomHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> value=new LinkedMultiValueMap<>();
		value.add("file", file);
   //  HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
        ResponseEntity response = null;
/*        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(value,userCustomHeaders);
*/
        HttpEntity<MultiValueMap> entity = new HttpEntity<MultiValueMap>(value,userCustomHeaders);

       // restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        
        response= restTemplate.exchange(webServiceURL+"Employees/uploadResume", HttpMethod.POST, entity, String.class);
        String msgString= (String) response.getBody();
		mview.addObject("uploadedCV",msgString);
		
		

		String roless= (String) session.getAttribute("role");

		List<String> name =new ArrayList<String>(Arrays.asList(roless.split(",")));

		
		ResponseEntity userDetailsResponse=null;
		ResponseEntity getAllSkillResponse=null;
		ResponseEntity getAllEmployeesResponse=null;
		ResponseEntity methodologyResponse=null;
		ResponseEntity domainResponse=null;
		ResponseEntity acceridationListResponse=null;
		ResponseEntity windowPeriodResponse=null;

		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		List <ViewAllEmp> empList = new ArrayList<ViewAllEmp>();

		
		if(name.contains("MANAGER") || name.contains("SME") || name.contains("EMPLOYEE") || name.contains("ADMIN") ||name.contains("PRACTICE LEAD")  )
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


mview.addObject("userDetails",userDetails);
mview.addObject("title", homeScreenTitle);
			/*modelObject.setViewName(homeJSP);*/
mview.setViewName("homeScreen");

			
			
		}
		
		if(name.contains("MANAGEMENT")){
		
		List <accreditation> avaliableAccreditationList = new ArrayList<accreditation>();
		acceridationListResponse= restTemplate.exchange(webServiceURL+getListofACCServiceURL, HttpMethod.GET, request, List.class);
		avaliableAccreditationList = (List<accreditation>) acceridationListResponse.getBody();
		mview.addObject("allAccreditationList",avaliableAccreditationList);
		
		getAllEmployeesResponse = restTemplate.exchange(webServiceURL+getListofEmployeesServiceURL, HttpMethod.GET, request, List.class);
		empList = (List<ViewAllEmp>) getAllEmployeesResponse.getBody();

		mview.addObject("employessList", empList);
		
		}
		//user dont have any role
		loginJson responseInfo = new loginJson();
		responseInfo.setUuid((String) session.getAttribute("sessionId"));
		responseInfo.setRandomUUID((String) session.getAttribute("randomUUID"));
		
		
		responseInfo.setRoleList(name);
		mview.addObject("UserObj", responseInfo);
        return mview;
    }
				}
			} catch (Exception e) {
				excpObj.setE(e);
				SPMTClientCustomExceptions.getExceptionCode(excpObj);
				mview.addObject("msg",ExceptionCode.getExceptionCode(e));
				mview.addObject("loginUser",new LoginUser());
				mview.setViewName("loginpage");
				mview.addObject("message", "failed to upload");
				logger.error("msg"+ExceptionCode.getExceptionCode(e));
		        return mview;	
			}
				}
		return mview;
	}
	/**
	 * Upload single file using Spring Controller
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody ModelAndView uploadFileHandler(@RequestParam("file") MultipartFile file,HttpSession session, HttpServletResponse res) {
		/*@RequestParam("name") String name,*/
		ModelAndView mview=new ModelAndView();
		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("updateProficiency");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);


		List<String> name= (List<String>) session.getAttribute("role");

		//List<String> name =new ArrayList<String>(Arrays.asList(roless.split(",")));

		
		ResponseEntity userDetailsResponse=null;
		ResponseEntity getAllSkillResponse=null;
		ResponseEntity getAllEmployeesResponse=null;
		ResponseEntity methodologyResponse=null;
		ResponseEntity domainResponse=null;
		ResponseEntity acceridationListResponse=null;
		ResponseEntity windowPeriodResponse=null;
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));

		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		List <ViewAllEmp> empList = new ArrayList<ViewAllEmp>();

		
		if(name.contains("MANAGER") || name.contains("SME") || name.contains("EMPLOYEE") || name.contains("ADMIN") ||name.contains("PRACTICE LEAD")  )
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
			
			
			mview.addObject("userDetails",userDetails);
			mview.addObject("title", homeScreenTitle);
						/*modelObject.setViewName(homeJSP);*/
			mview.setViewName("homeScreen");
			
			
			
		}
		
		if(name.contains("MANAGEMENT")){
		
		List <accreditation> avaliableAccreditationList = new ArrayList<accreditation>();
		acceridationListResponse= restTemplate.exchange(webServiceURL+getListofACCServiceURL, HttpMethod.GET, request, List.class);
		avaliableAccreditationList = (List<accreditation>) acceridationListResponse.getBody();
		mview.addObject("allAccreditationList",avaliableAccreditationList);
		
		getAllEmployeesResponse = restTemplate.exchange(webServiceURL+getListofEmployeesServiceURL, HttpMethod.GET, request, List.class);
		empList = (List<ViewAllEmp>) getAllEmployeesResponse.getBody();

		mview.addObject("employessList", empList);
		
		}
		//user dont have any role
		loginJson responseInfo = new loginJson();
		responseInfo.setUuid((String) session.getAttribute("sessionId"));
		responseInfo.setRandomUUID((String) session.getAttribute("randomUUID"));
		
		
		responseInfo.setRoleList(name);
		mview.addObject("UserObj", responseInfo);
		String msg1 = "Uploaded UnSuccessfully";
		HttpResponse response=null;
		 int statusCode =0;
		if (!file.isEmpty()) {
			try {
				long size = file.getSize();
				if(size<=3048576){
				byte[] bytes = file.getBytes();
			
			
				File resume = new File(file.getOriginalFilename());
				String fileName  = resume.getAbsolutePath();
				boolean b1 = fileName.endsWith("docx");
				boolean b2 = fileName.endsWith("doc");
				if(fileName.endsWith("docx")|fileName.endsWith("doc")){
				System.out.println(b1+":"+b2);
				System.out.println(fileName);
				String cananocliPath = resume.getCanonicalPath();
				System.out.println("canonicalPath"+cananocliPath);
				
				file.transferTo(resume);
				
				
				String sessionId = (String)session.getAttribute("sess");
				String randomUUID = (String)session.getAttribute("randomUUID");
				
				HttpClient httpclient = new DefaultHttpClient();
				httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
				
				HttpPost httpPost = new HttpPost(webServiceURL + "Employees/uploadResume/");
				
				MultipartEntity mpEntity = new MultipartEntity();
				ContentBody cbFile = new FileBody(resume, "multipart/form-data");
				mpEntity.addPart("file", cbFile);
				httpPost.addHeader("sessionId"+"_"+randomUUID, sessionId);
				httpPost.addHeader("randomUUID", randomUUID);
				httpPost.setEntity(mpEntity);
				
			    System.out.println("executing request " + httpPost.getRequestLine());
		
			     response = httpclient.execute(httpPost);
			    
			    System.out.println(response.getStatusLine().getStatusCode());
			    System.out.println(response.getStatusLine().getReasonPhrase());
			    
				mview.addObject("uploadedCV","Successfull");

			    if(response.getStatusLine().getStatusCode() == 200) {
			    	msg1 = "Uploaded UnSuccessfully";
					mview.addObject("uploadedCV",msg1);

			    	    }
				}else{
					System.out.println("waiting for redirecting to home screen");
					mview.addObject("uploadedCV","try again");
								}
				
				}else{
				
					System.out.println("waiting for redirecting to home screen");
					mview.addObject("uploadedCV","Size of document exceeded");

				//	res.sendRedirect("home");
				}
				
			    
			} catch (Exception e) {
				
				excpObj.setE(e);
				SPMTClientCustomExceptions.getExceptionCode(excpObj);
				mview.addObject("msg",ExceptionCode.getExceptionCode(e));
				mview.addObject("loginUser",new LoginUser());
				mview.setViewName("loginpage");
				mview.addObject("uploadedCV",e.getMessage());
				logger.error("msg"+ExceptionCode.getExceptionCode(e));

				return mview;

			}
		} else {
			mview.addObject("uploadedCV","You failed to upload because file is empty");
			return mview;


		}
		//if(response.getStatusLine().getStatusCode() == 200) {}
			
		return mview;
	}
	
	@RequestMapping(value="/downloadResume", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public void downloadResume(HttpSession session, HttpServletResponse response)  {

//		ClassLoader classLoader = getClass().getClassLoader();
//		File file = new File(classLoader.getResource("file/Attra_Curriculum_Vitae_Template.docx").getFile());
		excpObj.setClassname(ProficiencyController.class);
		 excpObj.setMethodName("updateProficiency");
		 String emailID =null;
		 
		 
		 emailID= (String) session.getAttribute("loginUser");
		 excpObj.setEmailId(emailID);

		
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		ResponseEntity<byte[]> responseFromService = null;

		HttpEntity<String> entity = new HttpEntity<String>( userCustomHeaders);

		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
		responseFromService= restTemplate.exchange(webServiceURL+"Employees/download", HttpMethod.GET, entity, byte[].class);
		FileOutputStream fos = null;
		String fname=null;
		HttpHeaders headers = responseFromService.getHeaders();
		String fn = headers.getFirst("Content-Disposition");
		String fileName1 = fn.substring(21,52);
		if(fileName1.equalsIgnoreCase("Attra_Curriculum_Vitae_Template")){
			 fname =fileName1;
		}else{
//		int i = fn.lastIndexOf('u');
		int i = fileName1.indexOf('2');
//		System.out.println("last Index"+i);
		 fname = fileName1.substring(0, i);
//		if(fileName1.equalsIgnoreCase("Attra-Curriculum-Vitae-Template.docx"))
		/*System.out.println("file name:"+fileName1);
		System.out.println("f name:"+fname);*/
		}
		try {
			
			if (responseFromService.getStatusCode().equals(HttpStatus.OK)) {
//				fos = new FileOutputStream(new File("test.docx"));
				fos = new FileOutputStream(new File(fname+".docx"));
				IOUtils.write(responseFromService.getBody(), fos);
				
				response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
				response.addHeader("Content-Disposition", "attachment; filename="+ fname+".docx");
				
				response.getOutputStream().write(responseFromService.getBody());
				response.getOutputStream().flush();

			}
			
			
			//Files.copy(output., response.getOutputStream());
			
//			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//			response.addHeader("Content-Disposition", "attachment; filename="
//					+ responseFromService.getBody().getFilename());
//			
//			InputStream inputStream = responseFromService.getBody().getInputStream();
//			byte[] buffer = new byte[10240];
//			try (
//				    OutputStream output = response.getOutputStream();
//				) {
//				    for (int length = 0; (length = inputStream.read(buffer)) > 0;) {
//				        output.write(buffer, 0, length);
//				    }
//				}
			
		} catch (Exception e) {
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			
		logger.error("msg"+ExceptionCode.getExceptionCode(e));
			
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		//String msgString= (String) response.getBody();
		
	}
	
//	public static long stream(InputStream input, OutputStream output) throws IOException {
//	    try (
//	        ReadableByteChannel inputChannel = Channels.newChannel(input);
//	        WritableByteChannel outputChannel = Channels.newChannel(output);
//	    ) {
//	        ByteBuffer buffer = ByteBuffer.allocateDirect(10240);
//	        long size = 0;
//
//	        while (inputChannel.read(buffer) != -1) {
//	            buffer.flip();
//	            size += outputChannel.write(buffer);
//	            buffer.clear();
//	        }
//
//	        return size;
//	    }
//	}


}
