package com.attra.pms.controller;



import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.attra.pms.model.CustomMessage;
import com.attra.pms.model.ExceptionObject;
import com.attra.pms.model.UserDetails;
import com.attra.pms.model.ViewAllEmp;
import com.attra.pms.model.accreditation;
import com.attra.pms.service.LoginService;
import com.attra.pms.service.AdminAccessService;
import com.attra.pms.util.SessionandClassName;

@Controller
@Scope("request")
public class DashboardController {
	
	ExceptionObject  excpObj=new ExceptionObject();
	private static final Logger logger = Logger.getLogger(DashboardController.class.getName());
	
	@Autowired
	LoginService dashboardService;

	@Autowired
	AdminAccessService adminService;
	
	String className =this.getClass().getSimpleName();

/*	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView dashBoard( HttpSession session, CustomMessage loginUser) {

		ModelAndView mview=new ModelAndView();
		UserDetails userInfo=dashboardService.getUserInfo(loginUser.getSessionId());
		mview.addObject("userDetails",userInfo);
		mview.addObject("title", "homescreenTitle");
		mview.setViewName("homeScreen");

		return mview;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/rmgHome", method = RequestMethod.GET)
	public ModelAndView rmgHome( HttpSession session, CustomMessage loginUser) {

		ModelAndView mview=new ModelAndView();
		UserDetails userInfo=null;
		//user rolee need to be checked
			if(loginUser.getSuccessMsg().equals("RMG HEAD"))
			{
		 userInfo=dashboardService.getUserInfo(loginUser.getSessionId());
			}
		
		
		List <ViewAllEmp> empList = new ArrayList<ViewAllEmp>();
		empList=adminService.getListofEmployees(loginUser.getSessionId());
		mview.addObject("employessList", empList);


		List <accreditation> avaliableAccreditationList = new ArrayList<accreditation>();
		avaliableAccreditationList=adminService.getListofAcceridations(loginUser.getSessionId());
		mview.addObject("allAccreditationList",avaliableAccreditationList);

		mview.addObject("userDetails",userInfo);
		mview.addObject("title", "homescreenTitle");
		mview.setViewName("homeScreen");
		
		return mview;
	}*/
	
}
