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

public class EmployeeSearchTest {
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
	    public void testEmployeeMonthlysalary() {
	        int result = emp.employeeMonthlysalary(3, 4);
	        
	        assertEquals(7, result);
	 
	    }
	 
	    @Test
	    public void testDivison() {
	        try {
	            int result = emp.empcategory(10, 2);
	 
	            assertEquals(5, result);
	        } catch (Exception e) {
	            e.printStackTrace(System.err);
	        }
	    }
	 
	    @Test(expected = Exception.class)
	    public void testDomainException() throws Exception {
	        emp.empcategory(10, 0);
	    }
	 
	    @Ignore
	    @Test
	    public void testEmpPerformance() {
	        boolean result = emp.equalIntegers(20, 20);
	 
	        assertFalse(result);
	    }
	 
	    @Test
	    public void testLastYearApprisal() {
	        int result = 20 - 11;
	 
	        assertTrue(result == 9);
	    }
	   
	   
	EmpBusinessLogic empBusinessLogic = new EmpBusinessLogic();
	   EmployeeDetails employee = new EmployeeDetails();

	   //test to check appraisal
	   @Test
	   public void testCalculateAppriasal() {
	      employee.setName("Rajeev");
	      employee.setAge(25);
	      employee.setMonthlySalary(8000);
			
	      double appraisal = empBusinessLogic.calculateAppraisal(employee);
	      assertEquals(500, appraisal, 0.0);
	   }

	   // test to check yearly salary
	   @Test
	   public void testCalculateYearlySalary() {
	      employee.setName("Rajeev");
	      employee.setAge(25);
	      employee.setMonthlySalary(8000);
			
	      double salary = empBusinessLogic.calculateYearlySalary(employee);
	      assertEquals(96000, salary, 0.0);
	   }
	   

	  
	   
}
