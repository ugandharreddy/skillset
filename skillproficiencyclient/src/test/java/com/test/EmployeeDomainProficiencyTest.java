package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.attra.pms.model.EmployeeDetails;
import com.attra.pms.util.EmpBusinessLogic;
import com.attra.pms.util.EmployeeSalary;
import com.attra.pms.util.EmployeeSearchDetails;

public class EmployeeDomainProficiencyTest {
	 private static EmployeeSearchDetails emp;
	    @BeforeClass
	    public static void initemp() {
	        emp = new EmployeeSalary();
	    }
	    @Before
	    public void beforeEachTest() {
	    	MockitoAnnotations.initMocks(this);
	    }
	 
	    @After
	    public void afterEachTest() {
	    }
	 
	    @Test
	    public void testLoginTime() {
	        int result = emp.employeeMonthlysalary(3, 4);
	        
	        assertEquals(7, result);
	 
	    }
	 
	    @Test
	    public void testDashboard() {
	        try {
	            int result = emp.empcategory(10, 2);
	 
	            assertEquals(5, result);
	        } catch (Exception e) {
	            e.printStackTrace(System.err);
	        }
	    }
	 
	    @Test(expected = Exception.class)
	    public void testKraAllocation() throws Exception {
	        emp.empcategory(10, 0);
	    }
	 
	    @Test
	    public void testReview() {
	        boolean result = emp.equalIntegers(10, 20);
	 
	        assertFalse(result);
	    }
	 
	    @Test
	    public void testSelfRating() {
	        int result = 10 - 1;
	 
	        assertTrue(result == 9);
	    }
	   
	   
	EmpBusinessLogic empBusinessLogic = new EmpBusinessLogic();
	   EmployeeDetails employee = new EmployeeDetails();

	   //test to check appraisal
	   @Test
	   public void testFeedback() {
	      employee.setName("sarath");
	      employee.setAge(35);
	      employee.setMonthlySalary(7000);
			
	      double appraisal = empBusinessLogic.calculateAppraisal(employee);
	      assertEquals(500, appraisal, 0.0);
	   }

	  
	   

	  
	   
}
