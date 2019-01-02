package com.attra.pms.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.attra.pms.model.AccrediationPro;
import com.attra.pms.model.CombinationalSearch;
import com.attra.pms.model.CustomMessage;
import com.attra.pms.model.DomainProficiency;
import com.attra.pms.model.EmployeeExportdata;
import com.attra.pms.model.EmployeeSearchJson;
import com.attra.pms.model.ExceptionObject;
import com.attra.pms.model.LoginUser;
import com.attra.pms.model.MethodologyPro;
import com.attra.pms.model.Proficiency;
import com.attra.pms.model.ProficiencyDomain;
import com.attra.pms.model.ProficiencyMethodology;
import com.attra.pms.model.ProficiencyTechnology;
import com.attra.pms.model.ProficiencyTool;
import com.attra.pms.model.TechProficiency;
import com.attra.pms.model.ToolProficiency;
import com.attra.pms.model.UserDetails;
import com.attra.pms.model.ViewAllEmp;
import com.attra.pms.model.accreditation;
import com.attra.pms.service.LoginService;
import com.attra.pms.service.AdminAccessService;
import com.attra.pms.util.ExceptionCode;
import com.attra.pms.util.PropertiesCache;
import com.attra.pms.util.SPMTClientCustomExceptions;
import com.attra.pms.util.SessionandClassName;
import com.attra.pms.util.clientConstants;
import com.attra.pms.util.customHeader;

@Controller
@Scope("request")
public class ExportController extends customHeader implements clientConstants{

	private static final Logger logger = Logger.getLogger(ExportController.class.getName());
	private static final String webServiceURL = PropertiesCache.getPropertyByKey(REST_ENDPOINT);
	private static final String getListofEmployeesServiceURL = PropertiesCache.getPropertyByKey(ALLEMPLOYEE_ENDPOINT);


	@Autowired 
	private RestTemplate restTemplate;

	ExceptionObject  excpObj=new ExceptionObject();
	
	String className =this.getClass().getSimpleName();
	/*function typeOf (obj) {
	  return {}.toString.call(obj).split(' ')[1].slice(0, -1).toLowerCase();
	}*/

	@RequestMapping(value="/ExportSearchEmployeeWithSkill/{skillName}/{project}/{tools}/{methodologyName}/{domainName}/{techPrimary}/{domainPrimary}/{tExp}/{dExp}/{tPro}/{dPro}/{acc}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity ExportSearchEmployeeWithSkill(@PathVariable String skillName, @PathVariable String project, @PathVariable String tools, @PathVariable String methodologyName, @PathVariable String domainName,  @PathVariable String techPrimary, @PathVariable String domainPrimary,@PathVariable String tExp,@PathVariable String dExp,@PathVariable Integer tPro,@PathVariable Integer dPro, @PathVariable String acc,HttpSession session)  {

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
		List <EmployeeSearchJson> skilledEmployees = new ArrayList<EmployeeSearchJson>();
		skilledEmployees = (List<EmployeeSearchJson>) responseList.getBody();



		Workbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Search Result");
		HSSFRow headerRow=sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Employee emailId");
		headerRow.createCell(1).setCellValue("Area Of Work");
		headerRow.createCell(2).setCellValue("Current Project");
		headerRow.createCell(3).setCellValue("Technologies");
		headerRow.createCell(4).setCellValue("Technology Experience"); 
		headerRow.createCell(5).setCellValue("Technology Proficiency"); 
		headerRow.createCell(6).setCellValue("Domains");
		headerRow.createCell(7).setCellValue("Domain Experience"); 
		headerRow.createCell(8).setCellValue("Domain Proficiency");
		headerRow.createCell(9).setCellValue("Methodlogies");
		headerRow.createCell(10).setCellValue("Tools");
		headerRow.createCell(11).setCellValue("Accreditations");
/*		headerRow.createCell(12).setCellValue("Experienece");
*/
		int rowCount=1;
		for(int i=0; i<skilledEmployees.size();i++)
		{
			Map emp  =(Map)skilledEmployees.get(i);

			HSSFRow headerRowValue=sheet.createRow(rowCount);
			headerRowValue.createCell(0).setCellValue((String) emp.get("empMailId"));
			headerRowValue.createCell(1).setCellValue((String)emp.get("areaOfWork"));
			headerRowValue.createCell(2).setCellValue((String)emp.get("projectName"));
			headerRowValue.createCell(3).setCellValue((String)emp.get("techDesc"));
			
			if((Integer)emp.get("tExpInyear")==0 && (Integer)emp.get("tExpInmonth")==0)
			{
				headerRowValue.createCell(4).setCellValue("NA");
			}
			else
			{
				headerRowValue.createCell(4).setCellValue((Integer)emp.get("tExpInyear")+"Years & "+(Integer)emp.get("tExpInmonth"));

			}
			if((Integer)emp.get("techProf")== 0)
			{
				headerRowValue.createCell(5).setCellValue("NA");//(Integer)emp.get("techProf")

			}
			else
			{
				headerRowValue.createCell(5).setCellValue((Integer)emp.get("techProf"));

			}
			headerRowValue.createCell(6).setCellValue((String)emp.get("domain"));
			if((Integer)emp.get("dExpInyear")== 0 && (Integer)emp.get("dExpInmonth")==0)
			{
				headerRowValue.createCell(7).setCellValue("NA");
			}
			else
			{
				headerRowValue.createCell(7).setCellValue((Integer)emp.get("dExpInyear")+"Years & "+(Integer)emp.get("dExpInmonth"));

			}
			if((Integer)emp.get("domainProf")== 0)
			{
				headerRowValue.createCell(8).setCellValue("NA");//(Integer)emp.get("techProf")

			}
			else
			{
				headerRowValue.createCell(8).setCellValue((Integer)emp.get("domainProf"));

			}
			
			headerRowValue.createCell(9).setCellValue((String)emp.get("methodology"));
			headerRowValue.createCell(10).setCellValue((String)emp.get("tool"));
			headerRowValue.createCell(11).setCellValue((String)emp.get("accreditation"));
/*			headerRowValue.createCell(8).setCellValue((Integer)emp.get("expInyear")+"Years & "+(Integer)emp.get("expInmonth"));
*/			rowCount++;
		}
		//String fileName ="C:/Users/charan.pathapati/Desktop/AllEmployees.xls";
		String fileName ="CombinationalSearch.xls";


		try{
			excpObj.setClassname(ExportController.class);
			 excpObj.setMethodName("ExportSearchEmployeeWithSkill");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);
			 
			String home=System.getProperty("user.home");
			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			File file = new File(fileName);

			if(file.exists()){
				InputStream inputStream = new FileInputStream(fileName); 

				byte[] out = IOUtils.toByteArray(inputStream);

				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add("content-disposition", "attachment; filename=" +"CombinationalSearch.xls");


				return new ResponseEntity(out, responseHeaders,HttpStatus.OK);


			}else{

				return new ResponseEntity ("File Not Found", HttpStatus.OK);
			}
		} catch (FileNotFoundException e) {
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			//e.printStackTrace();
		} catch (IOException e) {
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			//e.printStackTrace();
		}
		return null;

	}
	
	
	
	/*
	@RequestMapping(value="/ExportsearchByKeyword/{keyword}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity ExportsearchByKeyword(@PathVariable String keyword,  HttpSession session)  {

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		ResponseEntity responseList = null;
		responseList= restTemplate.exchange(webServiceURL+"Management/searchByKeyword/"+keyword, HttpMethod.GET, request, List.class);
		List <EmployeeSearchJson> KeywordResult = new ArrayList<EmployeeSearchJson>();
		KeywordResult = (List<EmployeeSearchJson>) responseList.getBody();
		
		


		Workbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Search Result");
		HSSFRow headerRow=sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Employee emailId");
		headerRow.createCell(1).setCellValue("Area Of Work");
		headerRow.createCell(2).setCellValue("Current Project");
		headerRow.createCell(3).setCellValue("Technologies");
		headerRow.createCell(4).setCellValue("Domains");
		headerRow.createCell(5).setCellValue("Tools");
		headerRow.createCell(6).setCellValue("Methodlogies");
		headerRow.createCell(7).setCellValue("Accreditations");
		headerRow.createCell(8).setCellValue("Experienece");

		int rowCount=1;
		for(int i=0; i<KeywordResult.size();i++)
		{
			Map emp  =(Map)KeywordResult.get(i);

			HSSFRow headerRowValue=sheet.createRow(rowCount);
			headerRowValue.createCell(0).setCellValue((String) emp.get("empMailId"));
			headerRowValue.createCell(1).setCellValue((String)emp.get("areaOfWork"));
			headerRowValue.createCell(2).setCellValue((String)emp.get("projectName"));
			headerRowValue.createCell(3).setCellValue((String)emp.get("skillDesc"));
			headerRowValue.createCell(4).setCellValue((String)emp.get("domain"));
			headerRowValue.createCell(5).setCellValue((String)emp.get("methodology"));
			headerRowValue.createCell(6).setCellValue((String)emp.get("tool"));
			headerRowValue.createCell(7).setCellValue((String)emp.get("accreditation"));
			headerRowValue.createCell(8).setCellValue((Integer)emp.get("expInyear")+"Years & "+(Integer)emp.get("expInmonth"));
			rowCount++;
		}
		//String fileName ="C:/Users/charan.pathapati/Desktop/AllEmployees.xls";
		String fileName =keyword+".xls";


		try{
			excpObj.setClassname(ExportController.class);
			 excpObj.setMethodName("ExportsearchByKeyword");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			String home=System.getProperty("user.home");
			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			File file = new File(fileName);

			if(file.exists()){
				InputStream inputStream = new FileInputStream(fileName); 

				byte[] out = IOUtils.toByteArray(inputStream);

				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add("content-disposition", "attachment; filename=" +keyword+ ".xls");


				return new ResponseEntity(out, responseHeaders,HttpStatus.OK);


			}else{

				return new ResponseEntity ("File Not Found", HttpStatus.OK);
			}
		} catch (FileNotFoundException e) {
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			//e.printStackTrace();
		} catch (IOException e) {
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			//e.printStackTrace();
		}
		return null;


	}
	*/
	/*@RequestMapping(value="/exportAllEmployees", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity searchEmployeeWithSkill(HttpSession session)  {

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		ResponseEntity	getAllEmployeesResponse = restTemplate.exchange(webServiceURL+getListofEmployeesServiceURL, HttpMethod.GET, request, List.class);

		List <ViewAllEmp>  employeeList = (List<ViewAllEmp>) getAllEmployeesResponse.getBody();



		Workbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Attra Employees");
		HSSFRow headerRow=sheet.createRow(0);
		headerRow.createCell(0).setCellValue("Employee emailId");
		headerRow.createCell(1).setCellValue("Employee Name");
		headerRow.createCell(2).setCellValue("Area Of Work");
		headerRow.createCell(3).setCellValue("Current Project");
		headerRow.createCell(4).setCellValue("Primary Technology");
		headerRow.createCell(5).setCellValue("Technologies");
		headerRow.createCell(6).setCellValue("Primary Domain");
		headerRow.createCell(7).setCellValue("Domains");
		headerRow.createCell(8).setCellValue("Tools");
		headerRow.createCell(9).setCellValue("Methodlogies");
		headerRow.createCell(10).setCellValue("Experienece");
		headerRow.createCell(11).setCellValue("Accreditations");
		int rowCount=1;
		for(int i=0; i<employeeList.size();i++)
		{
			Map emp  =(Map)employeeList.get(i);

			HSSFRow headerRowValue=sheet.createRow(rowCount);
			headerRowValue.createCell(0).setCellValue((String)emp.get("emailId"));
			headerRowValue.createCell(1).setCellValue((String)emp.get("empName"));
			headerRowValue.createCell(2).setCellValue((String)emp.get("areaOfWork"));
			headerRowValue.createCell(3).setCellValue((String)emp.get("projectName"));
			headerRowValue.createCell(4).setCellValue((String)emp.get("primaryTech"));
			headerRowValue.createCell(5).setCellValue((String)emp.get("technology"));
			headerRowValue.createCell(6).setCellValue((String)emp.get("primaryDomain"));
			headerRowValue.createCell(7).setCellValue((String)emp.get("domainName"));
			headerRowValue.createCell(8).setCellValue((String)emp.get("toolName"));
			headerRowValue.createCell(9).setCellValue((String)emp.get("methodlogy"));
			headerRowValue.createCell(10).setCellValue((Integer)emp.get("expInyear")+"Years & "+(Integer)emp.get("expInmonth")+"Months");
			headerRowValue.createCell(11).setCellValue((String)emp.get("accreditation"));
			rowCount++;
		}
		//String fileName ="C:/Users/charan.pathapati/Desktop/AllEmployees.xls";
		String fileName ="AllEmployees.xls";


		try{
			
			excpObj.setClassname(ExportController.class);
			 excpObj.setMethodName("searchEmployeeWithSkill");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			String home=System.getProperty("user.home");
			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			File file = new File(fileName);

			if(file.exists()){
				InputStream inputStream = new FileInputStream(fileName); 

				byte[] out = IOUtils.toByteArray(inputStream);

				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add("content-disposition", "attachment; filename=" + "AllEmployees.xls");


				return new ResponseEntity(out, responseHeaders,HttpStatus.OK);


			}else{

				return new ResponseEntity ("File Not Found", HttpStatus.OK);
			}
		} catch (FileNotFoundException e) {
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			//e.printStackTrace();
		} catch (IOException e) {
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			//e.printStackTrace();
		}
		return null;

	}	*/


	@SuppressWarnings("unchecked")
	@RequestMapping(value="/exportEmployeesProficiency/{emp}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity exportEmployeesProficiency(@PathVariable String emp, HttpSession session)  {

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);

		

		if(emp.toLowerCase().endsWith(".au"))
		{

		}
		else{

			emp = emp.concat(".au");
		}
		
		byte[] plainSkillBytes = emp.getBytes();
		byte[] base64SkillBytes = Base64.encodeBase64(plainSkillBytes);
		String base64Skill = new String(base64SkillBytes);
		
		ResponseEntity viewProficiencyResponse = null;
		viewProficiencyResponse= restTemplate.exchange(webServiceURL+ "Proficiency/proficiencyDetails/"+base64Skill, HttpMethod.GET, request, Proficiency.class);
		List<ProficiencyTechnology> coreSkills = new ArrayList<ProficiencyTechnology>();
		List <ProficiencyMethodology> methodologyList = new ArrayList<ProficiencyMethodology>();



		Proficiency proficiencyDetails =new Proficiency();
		proficiencyDetails=(Proficiency) viewProficiencyResponse.getBody();
		List <ProficiencyDomain> domain = new ArrayList<ProficiencyDomain>();
		List <ProficiencyTool> tool = new ArrayList<ProficiencyTool>();

		//
		coreSkills=(List<ProficiencyTechnology>) proficiencyDetails.getListOfPTechnology();
		methodologyList = (List<ProficiencyMethodology>) proficiencyDetails.getListofPMethodology();
		domain = (List<ProficiencyDomain>) proficiencyDetails.getListofPDomain();
		tool = (List<ProficiencyTool>) proficiencyDetails.getListOfPTool();

		List<String> sheetName = Arrays.asList("Technology", "Domain", "Tools", "Methodology", "Accreditation");
		List<String> techColumns = Arrays.asList("Primary", "Area Of Work", "Stream", "Skill", "Experienece","Employee's Proficiency","Employee's comment","Reviewer's comment","Final Proficiency");
		List<String> domainColumns = Arrays.asList("Primary", "Domain", "Sub-Domain", "Experienece","Employee's Proficiency","Employee's comment","Reviewer's comment","Final Proficiency");
		List<String> toolColumns = Arrays.asList("Tool Type", "Tool Name", "Experienece","Employee's Proficiency","Employee's comment");
		List<String> methodologyColumns = Arrays.asList("Methodology Name", "Experienece","Employee's Proficiency","Employee's comment");
		List<String> accColumns = Arrays.asList("Name of Certificate","Certificate Number","Issue Date");

		List <accreditation> accreditationList = new ArrayList<accreditation>();
		ResponseEntity accreditationResponse = null;
		accreditationResponse= restTemplate.exchange(webServiceURL +"Management/accreditation/"+base64Skill, HttpMethod.GET, request, List.class);
		accreditationList = (List<accreditation>) accreditationResponse.getBody();

		Workbook workbook = new HSSFWorkbook();
		for(int i=0; i<sheetName.size(); i++)

		{
			HSSFSheet sheet = (HSSFSheet) workbook.createSheet(sheetName.get(i));
			HSSFRow headerRow=sheet.createRow(0);

			if(i==0){
				for(int cellrow=0; cellrow<techColumns.size(); cellrow++)
				{
					headerRow.createCell(cellrow).setCellValue(techColumns.get(cellrow));
				}

				for(int rowCount=0; rowCount<coreSkills.size();rowCount++)
				{

					ProficiencyTechnology data  =(ProficiencyTechnology)coreSkills.get(rowCount);
					HSSFRow headerRowValue=sheet.createRow(rowCount+1);
					headerRowValue.createCell(0).setCellValue((String) data.getIsPrimary());
					headerRowValue.createCell(1).setCellValue((String) data.getAreaOfWork());
					headerRowValue.createCell(2).setCellValue((String) data.getTechnology());
					headerRowValue.createCell(3).setCellValue((String) data.getSkill());
					headerRowValue.createCell(4).setCellValue((Integer)data.getExpYears()+"Years & "+(Integer)data.getExpMonths()+"Months");
					headerRowValue.createCell(5).setCellValue((Integer) data.getEmpRating());
					headerRowValue.createCell(6).setCellValue((String) data.getEmpComments());
					String managerComment=(String) data.getMgrComment();
					int finalRating = (Integer) data.getFinalRating();
					if(finalRating==0){
						managerComment="Waiting for Review";
						String s ="Waiting for Review";
						headerRowValue.createCell(8).setCellValue("Waiting for Review");

					}
					else{
						headerRowValue.createCell(8).setCellValue(finalRating);

					}
					
					headerRowValue.createCell(7).setCellValue(managerComment);
				}

			}
			else if(i==1)
			{

				for(int cellrow=0; cellrow<domainColumns.size(); cellrow++)
				{
					headerRow.createCell(cellrow).setCellValue(domainColumns.get(cellrow));
				}


				for(int rowCount=0; rowCount<domain.size();rowCount++)
				{
					ProficiencyDomain data  =(ProficiencyDomain)domain.get(rowCount);
					HSSFRow headerRowValue=sheet.createRow(rowCount+1);
					headerRowValue.createCell(0).setCellValue((String) data.getIsPrimary());
					headerRowValue.createCell(1).setCellValue((String) data.getdName());
					headerRowValue.createCell(2).setCellValue((String) data.getsGroup());
					headerRowValue.createCell(3).setCellValue((Integer)data.getdExpYears()+"Years & "+(Integer)data.getdExpMonths()+"Months");
					headerRowValue.createCell(4).setCellValue((Integer) data.getEmpRating());
					headerRowValue.createCell(5).setCellValue((String) data.getEmpComments());
					String managerComment=(String) data.getMgrComment();
					int finalRating = (Integer) data.getFinalRating();
					if(finalRating==0){
						managerComment="Waiting for Review";
						String s ="Waiting for Review";
						headerRowValue.createCell(8).setCellValue("Waiting for Review");

					}
					else{
						headerRowValue.createCell(8).setCellValue(finalRating);

					}
					
					headerRowValue.createCell(6).setCellValue(managerComment);
//					headerRowValue.createCell(7).setCellValue((Integer) data.getFinalRating());

				}


			}
			else if(i==2)
			{
				for(int cellrow=0; cellrow<toolColumns.size(); cellrow++)
				{
					headerRow.createCell(cellrow).setCellValue(toolColumns.get(cellrow));
				}
				for(int rowCount=0; rowCount<tool.size();rowCount++)
				{


					ProficiencyTool data  =(ProficiencyTool)tool.get(rowCount);
					HSSFRow headerRowValue=sheet.createRow(rowCount+1);
					headerRowValue.createCell(0).setCellValue((String) data.getToolType());
					headerRowValue.createCell(1).setCellValue((String) data.getToolName());
					headerRowValue.createCell(2).setCellValue((Integer)data.gettExpYears()+"Years & "+(Integer)data.gettExpMonths()+"Months");
					headerRowValue.createCell(3).setCellValue((Integer) data.getEmpRating());
					headerRowValue.createCell(4).setCellValue((String) data.getEmpComments());

				}
			}
			else if(i==3)
			{
				for(int cellrow=0; cellrow<methodologyColumns.size(); cellrow++)
				{
					headerRow.createCell(cellrow).setCellValue(methodologyColumns.get(cellrow));
				}
				for(int rowCount=0; rowCount<methodologyList.size();rowCount++)
				{

					ProficiencyMethodology data  =(ProficiencyMethodology)methodologyList.get(rowCount);
					HSSFRow headerRowValue=sheet.createRow(rowCount+1);
					headerRowValue.createCell(0).setCellValue((String) data.getmName());
					headerRowValue.createCell(1).setCellValue((Integer)data.getmExpYears()+"Years & "+(Integer)data.getmExpMonths()+"Months");
					headerRowValue.createCell(2).setCellValue((Integer) data.getEmpRating());
					headerRowValue.createCell(3).setCellValue((String) data.getComments());

				}
			}
			else if(i==4)
			{

				for(int cellrow=0; cellrow<accColumns.size(); cellrow++)
				{
					headerRow.createCell(cellrow).setCellValue(accColumns.get(cellrow));
				}
				for(int rowCount=0; rowCount<accreditationList.size();rowCount++)
			{

					Map data  =(Map)accreditationList.get(rowCount);

					HSSFRow headerRowValue=sheet.createRow(rowCount+1);
					headerRowValue.createCell(0).setCellValue((String) data.get("accreditationName"));
					headerRowValue.createCell(1).setCellValue((String)data.get("cNo"));
					headerRowValue.createCell(2).setCellValue((String) data.get("issueDate"));

			}
			}



		}

		//String fileName ="C:/Users/charan.pathapati/Desktop/AllEmployees.xls";
		
		String fileName ="AllEmployees.xls";


		try{

			excpObj.setClassname(ExportController.class);
			 excpObj.setMethodName("exportEmployeesProficiency");
			 String emailID =null;
			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);

			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			File file = new File(fileName);

			if(file.exists()){
				InputStream inputStream = new FileInputStream(fileName); 

				byte[] out = IOUtils.toByteArray(inputStream);

				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add("content-disposition", "attachment; filename=" +emp+ ".xls");


				return new ResponseEntity(out, responseHeaders,HttpStatus.OK);


			}else{

				return new ResponseEntity ("File Not Found", HttpStatus.OK);
			}
		} catch (FileNotFoundException e) {
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			//e.printStackTrace();
		} catch (IOException e) {
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			//e.printStackTrace();
		}
		return null;

	}
	
	//new code for Export All Employee added by Kunal 
	
	@RequestMapping(value="/ExportReportExcel", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity ExportReportExcel(HttpSession session)  {
		
		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		ResponseEntity responseList = null;
		responseList= restTemplate.exchange(webServiceURL+"Management/exportEmployee", HttpMethod.POST, request, List.class);
		List<EmployeeExportdata> searchRecord = new ArrayList<EmployeeExportdata>();
		searchRecord = (List<EmployeeExportdata>) responseList.getBody();	
			
		String fileName ="AllEmployees.xls";
		

		File file=generateExcelReportCode(searchRecord,fileName);


		try{
			excpObj.setClassname(ExportController.class);
			 excpObj.setMethodName("ExportReportExcel");
			 String emailID =null;			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);


			if(file.exists()){
				InputStream inputStream = new FileInputStream(fileName); 

				byte[] out = IOUtils.toByteArray(inputStream);

				HttpHeaders responseHeaders = new HttpHeaders();
				responseHeaders.add("content-disposition", "attachment; filename=AllEmployees.xls");


				return new ResponseEntity(out, responseHeaders,HttpStatus.OK);


			}else{

				return new ResponseEntity ("File Not Found", HttpStatus.OK);
			}
		} catch (FileNotFoundException e) {
			excpObj.setE(e);
			SPMTClientCustomExceptions.getExceptionCode(excpObj);
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			//e.printStackTrace();
		} catch (IOException e) {
			logger.error("msg"+ExceptionCode.getExceptionCode(e));
			//e.printStackTrace();
		}
		return null;


	}
	
	
	//new code for Export free search added by Kunal 
	@RequestMapping(value="/exportKeywordSearch/{keyword}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity exportKeywordSearch(@PathVariable String keyword,  HttpSession session)  {

		HttpHeaders userCustomHeaders=customHeader.getCustomHeadersBySession((String)session.getAttribute("sess"),(String)session.getAttribute("randomUUID"));
		HttpEntity<String> request = new HttpEntity<String>(userCustomHeaders);
		ResponseEntity responseList = null;
		responseList= restTemplate.exchange(webServiceURL+"Management/exportSearchByKeyword/"+keyword, HttpMethod.GET, request, List.class);
		List<EmployeeExportdata> keywordResult = new ArrayList<EmployeeExportdata>();
		keywordResult = (List<EmployeeExportdata>) responseList.getBody();
		
		String fileName =keyword+".xls";
		
		File file=generateExcelReportCode(keywordResult,fileName);
		
		try{
			
			excpObj.setClassname(ExportController.class);
			 excpObj.setMethodName("ExportReportExcel");
			 String emailID =null;			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);
			
		if(file.exists()){
			InputStream inputStream = new FileInputStream(fileName); 

			byte[] out = IOUtils.toByteArray(inputStream);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("content-disposition", "attachment; filename="+keyword+".xls");


			return new ResponseEntity(out, responseHeaders,HttpStatus.OK);


		}else{

			return new ResponseEntity ("File Not Found", HttpStatus.OK);
		}
	} catch (FileNotFoundException e) {
		excpObj.setE(e);
		SPMTClientCustomExceptions.getExceptionCode(excpObj);
		logger.error("msg"+ExceptionCode.getExceptionCode(e));
		//e.printStackTrace();
	} catch (IOException e) {
		logger.error("msg"+ExceptionCode.getExceptionCode(e));
		//e.printStackTrace();
	}
		
		
		return null;
	}
	
	
	
	//
	
	@RequestMapping(value="/exportCombinationalSearchEmployee/{skillName}/{project}/{tools}/{methodologyName}/{domainName}/{techPrimary}/{domainPrimary}/{tExp}/{dExp}/{tPro}/{dPro}/{acc}", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity exportCombinationalSearchEmployee(@PathVariable String skillName, @PathVariable String project, @PathVariable String tools, @PathVariable String methodologyName, @PathVariable String domainName,  @PathVariable String techPrimary, @PathVariable String domainPrimary,@PathVariable String tExp,@PathVariable String dExp,@PathVariable Integer tPro,@PathVariable Integer dPro, @PathVariable String acc,HttpSession session)  {

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
		
		responseList= restTemplate.exchange(webServiceURL+ "Management/employeeDetailsCombinational", HttpMethod.POST, entity, List.class);
		List<EmployeeExportdata> keywordResult = new ArrayList<EmployeeExportdata>();
		keywordResult = (List<EmployeeExportdata>) responseList.getBody();
		
		String fileName ="CombinationalSearch.xls";
		
		File file=generateExcelReportCode(keywordResult,fileName);
		
		try{
			
			excpObj.setClassname(ExportController.class);
			 excpObj.setMethodName("ExportReportExcel");
			 String emailID =null;			 
			 
			 emailID= (String) session.getAttribute("loginUser");
			 excpObj.setEmailId(emailID);
			
		if(file.exists()){
			InputStream inputStream = new FileInputStream(fileName); 

			byte[] out = IOUtils.toByteArray(inputStream);

			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("content-disposition", "attachment; filename=CombinationalSearch.xls");


			return new ResponseEntity(out, responseHeaders,HttpStatus.OK);


		}else{

			return new ResponseEntity ("File Not Found", HttpStatus.OK);
		}
	} catch (FileNotFoundException e) {
		excpObj.setE(e);
		SPMTClientCustomExceptions.getExceptionCode(excpObj);
		logger.error("msg"+ExceptionCode.getExceptionCode(e));
		//e.printStackTrace();
	} catch (IOException e) {
		logger.error("msg"+ExceptionCode.getExceptionCode(e));
		//e.printStackTrace();
	}
		
		
		return null;
	}
	
	//Code for generate excel report.
	
	public File generateExcelReportCode(List<EmployeeExportdata> searchRecord,String fileName) {
		
		
		 Workbook workbook = new HSSFWorkbook();
			
			
			Sheet sheet = workbook.createSheet("new sheet");
			//HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Search Result");
			Row headerRow=sheet.createRow(0);
			headerRow.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));// increase row hight to 2 character
			headerRow.createCell(0).setCellValue("Employee emailId");
			headerRow.createCell(1).setCellValue("TotalExp");
			headerRow.createCell(2).setCellValue("AreaOfWork");
			headerRow.createCell(3).setCellValue("AlignedPractice");
			headerRow.createCell(4).setCellValue("Technical Proficiency");
			headerRow.createCell(13).setCellValue("Domain Proficiency");
			headerRow.createCell(21).setCellValue("Tool Proficiency");
			headerRow.createCell(26).setCellValue("Methodlogy");
			headerRow.createCell(30).setCellValue("Accrediation");
			
			Row headerRowq=sheet.createRow(1);
			headerRowq.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));// increase row hight to 2 character
			headerRowq.createCell(4).setCellValue("Area of Work");
			headerRowq.createCell(5).setCellValue("Stream");
			headerRowq.createCell(6).setCellValue("Skill");
			headerRowq.createCell(7).setCellValue("Primary");
			headerRowq.createCell(8).setCellValue("Experience");
			headerRowq.createCell(9).setCellValue("Self Rating");
			headerRowq.createCell(10).setCellValue("Final Rating");
			headerRowq.createCell(11).setCellValue("Employee Comment");
			headerRowq.createCell(12).setCellValue("Reviewer Comment");
			headerRowq.createCell(13).setCellValue("Domain");
			headerRowq.createCell(14).setCellValue("SubDomain");
			headerRowq.createCell(15).setCellValue("Primary");
			headerRowq.createCell(16).setCellValue("Experience");
			headerRowq.createCell(17).setCellValue("Self Rating");
			headerRowq.createCell(18).setCellValue("Final Rating");
			headerRowq.createCell(19).setCellValue("Employee Comment");
			headerRowq.createCell(20).setCellValue("Reviewer Comment");
			headerRowq.createCell(21).setCellValue("ToolType");
			headerRowq.createCell(22).setCellValue("Tool");
			headerRowq.createCell(23).setCellValue("Experience");
			headerRowq.createCell(24).setCellValue("Self Rating");
			headerRowq.createCell(25).setCellValue("Employee Comment");
			headerRowq.createCell(26).setCellValue("Methodology");
			headerRowq.createCell(27).setCellValue("Experience");
			headerRowq.createCell(28).setCellValue("Self Rating");
			headerRowq.createCell(29).setCellValue("Employee Comment");			
			headerRowq.createCell(30).setCellValue("Certificate");
			headerRowq.createCell(31).setCellValue("Valid From");
			
			sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
					0, // last row (0-based)
					4, // first column (0-based)
					12 // last column (0-based)
			));
			sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
					0, // last row (0-based)
					13, // first column (0-based)
					20 // last column (0-based)
			));
			sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
					0, // last row (0-based)
					21, // first column (0-based)
					25 // last column (0-based)
			));
			sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
					0, // last row (0-based)
					26, // first column (0-based)
					29 // last column (0-based)
			));
			sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
					0, // last row (0-based)
					30, // first column (0-based)
					31 // last column (0-based)
			));
			sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
					1, // last row (0-based)
					0, // first column (0-based)
					0 // last column (0-based)
			));
			
			sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
					1, // last row (0-based)
					1, // first column (0-based)
					1 // last column (0-based)
			));
			
			sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
					1, // last row (0-based)
					2, // first column (0-based)
					2 // last column (0-based)
			));
			
			sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
					1, // last row (0-based)
					3, // first column (0-based)
					3 // last column (0-based)
			));
			
			/*sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
					1, // last row (0-based)
					15, // first column (0-based)
					15// last column (0-based)
			));*/
			
			int rowCount=2;
			int count=1;
			int start=1;
			int end=1;
			
			CellStyle style = workbook.createCellStyle(); //Create new style
            style.setWrapText(true);
			
			for(int i=0; i<searchRecord.size();i++)
			{
				//EmployeeExportdata  empExport=searchRecord.get(i);
				
				Map empExport  =(Map)searchRecord.get(i);

				
				String empid=(String)empExport.get("employeeId");
				int expTotal=(Integer)empExport.get("totaExp");
				String areaWork=(String)empExport.get("areaOfWork");
				String project=(String)empExport.get("project");
	            List<TechProficiency> technology=  (List<TechProficiency>) empExport.get("techProficiency");
	            List<DomainProficiency> domain=(List<DomainProficiency>) empExport.get("domainProficiency");
	            List<ToolProficiency> tool=(List<ToolProficiency>) empExport.get("toolProficiency");
	            List<MethodologyPro> methodo=(List<MethodologyPro>) empExport.get("methodology");
	            List<AccrediationPro> accrPro=(List<AccrediationPro>) empExport.get("accrediation");
	            int a=technology.size();
	            int b= domain.size();
	            int c=tool.size();
	            int d=methodo.size();
	            int e=accrPro.size();
	            if ((a >= b) && (a >= c) && (a >= d) &&  (a >= e) ) { 
	               count=a;
	            } else if ((b >= c) && (b >= d) &&  (b >= e)) {    
	            	count=b;
	            } else if ((c >= d) &&  (c >= e)) {                
	            	count=c;			        
	            } else if((d >= e)){                                          
	            	count=d;
	            }else{
	            	count=e;
	            }
	            if(a==0 && b==0 && c==0 && d==0 && e==0){
	            	count=1;
	            }
	            start=end+1;
	            end=end+count;
	            
	        	for(int j=0;j<count;j++){
	        		
	        		Row headerRowValue=sheet.createRow(rowCount);
	        		
	        		headerRowValue.setHeightInPoints((2 * sheet.getDefaultRowHeightInPoints()));// increase row hight to 2 character
	        		if(j==0){
	    			headerRowValue.createCell(0).setCellValue(empid);
	    			headerRowValue.createCell(1).setCellValue(expTotal/12 +" years && "+expTotal%12 +" months");
	    			headerRowValue.createCell(2).setCellValue(areaWork);
	    			headerRowValue.createCell(3).setCellValue(project);
	        		}
	        		if(j<technology.size()){
	        			//TechProficiency course11=technology.get(j);
	        			Map course11  =(Map)technology.get(j);
	        			headerRowValue.createCell(4).setCellValue((String)course11.get("techAreadesc"));
	        			headerRowValue.createCell(5).setCellValue((String)course11.get("technologyDesc"));
	        			headerRowValue.createCell(6).setCellValue((String)course11.get("techSkillDesc"));
	        			headerRowValue.createCell(7).setCellValue((String)(course11.get("isTechnologyPrimary")).toString());
	        			headerRowValue.createCell(8).setCellValue(((Integer)course11.get("technologyExperience"))/12 +" years && "+((Integer)course11.get("technologyExperience"))%12 +" months");
	        			headerRowValue.createCell(9).setCellValue((Integer)course11.get("technologySelfRating"));
	        			
	        			int finalRating=(Integer)course11.get("technologyProf");
	        			if(finalRating==0){
	        				headerRowValue.createCell(10).setCellValue("Waiting For Review");
	        			}else{
	        				headerRowValue.createCell(10).setCellValue(finalRating);
	        			}
	        			Cell cell=headerRowValue.createCell(11);
	                    cell.setCellStyle(style);
	                    cell.setCellValue((String)course11.get("empComment"));
	                    Cell cell1=headerRowValue.createCell(12);
	                    cell1.setCellStyle(style);
	                    cell1.setCellValue((String)course11.get("reviewerComment"));
	        			//headerRowValue.createCell(11).setCellValue((String)course11.get("empComment"));
	        			//headerRowValue.createCell(12).setCellValue((String)course11.get("mngerComment"));
	        			
	        			
	        			
	        		}
	        		if(j<domain.size()){
	        			//DomainProficiency domain11=domain.get(j);
	        			Map domain11  =(Map)domain.get(j);
	        			headerRowValue.createCell(13).setCellValue((String)domain11.get("domainDesc"));
	        			headerRowValue.createCell(14).setCellValue((String)domain11.get("subDomainDesc"));
	        			headerRowValue.createCell(15).setCellValue((String)(domain11.get("isDPrimary").toString()));
	        			headerRowValue.createCell(16).setCellValue((Integer)(domain11.get("domainExperience"))/12 +" years && "+((Integer)domain11.get("domainExperience"))%12 +" months");
	        			headerRowValue.createCell(17).setCellValue((Integer)domain11.get("domainSelfRating"));
	        			int finalRating=(Integer)domain11.get("domainProficiency");
	        			if(finalRating==0){
	        				headerRowValue.createCell(18).setCellValue("Waiting For Review");
	        			}else{
	        				headerRowValue.createCell(18).setCellValue(finalRating);
	        			}
	        			Cell cell=headerRowValue.createCell(19);
	                    cell.setCellStyle(style);
	                    cell.setCellValue((String)domain11.get("empComment"));
	                    Cell cell1=headerRowValue.createCell(19);
	                    cell1.setCellStyle(style);
	                    cell1.setCellValue((String)domain11.get("reviewerComment"));
	        			
	        		}
	        		if(j<tool.size()){
	        			//ToolProficiency tool11=tool.get(j);
	        			Map tool11  =(Map)tool.get(j);
	        			headerRowValue.createCell(21).setCellValue((String)tool11.get("toolDesc"));
	        			headerRowValue.createCell(22).setCellValue((String)tool11.get("toolTypeDesc"));
	        			headerRowValue.createCell(23).setCellValue((Integer)(tool11.get("toolExperience"))/12 +" years && "+((Integer)tool11.get("toolExperience"))%12 +" months");
	        			headerRowValue.createCell(24).setCellValue((Integer)tool11.get("toolSelfRating"));
	        			Cell cell=headerRowValue.createCell(25);
	                    cell.setCellStyle(style);	                   
	                    cell.setCellValue((String)tool11.get("empComment"));
	        			
	        		}
	        		if(j<methodo.size()){
	        			//MethodologyPro methodology11=methodo.get(j);
	        			Map methodology11  =(Map)methodo.get(j);
	        			headerRowValue.createCell(26).setCellValue((String)methodology11.get("methodologyDesc"));
	        			headerRowValue.createCell(27).setCellValue((Integer)(methodology11.get("methodologyExperience"))/12 +" years && "+((Integer)methodology11.get("methodologyExperience"))%12 +" months");
	        			headerRowValue.createCell(28).setCellValue((Integer)methodology11.get("methodologySelfRating"));
	        			Cell cell=headerRowValue.createCell(29);
	                    cell.setCellStyle(style);
	                    cell.setCellValue((String)methodology11.get("empComment"));
	        			
	        		}
	        		if(j<accrPro.size()){
	        			
	        			Map accrediation11  =(Map)accrPro.get(j);
	        			headerRowValue.createCell(30).setCellValue((String)accrediation11.get("accrediationDesc"));
	        			headerRowValue.createCell(31).setCellValue((String)accrediation11.get("validFrom"));
	        			
	        		}
	        		rowCount++;
	        	}
	        	//System.out.println("i: "+i+" start: "+start+" end :  "+end+"count: "+count);
				sheet.addMergedRegion(new CellRangeAddress(start, // first row (0-based)
	        			end, // last row (0-based)
	    				0, // first column (0-based)
	    				0 // last column (0-based)
	    		));
				
				sheet.addMergedRegion(new CellRangeAddress(start, // first row (0-based)
	        			end, // last row (0-based)
	    				1, // first column (0-based)
	    				1 // last column (0-based)
	    		));
				
				sheet.addMergedRegion(new CellRangeAddress(start, // first row (0-based)
	        			end, // last row (0-based)
	    				2, // first column (0-based)
	    				2 // last column (0-based)
	    		));
				
				sheet.addMergedRegion(new CellRangeAddress(start, // first row (0-based)
	        			end, // last row (0-based)
	    				3, // first column (0-based)
	    				3 // last column (0-based)
	    		));
					            
	            
			
			}
			
			try{

			FileOutputStream outputStream = new FileOutputStream(fileName);
			workbook.write(outputStream);
			File file = new File(fileName);
			return file;
			}catch(Exception e){
				
			}
		return null;
		
	}
	
	


}
