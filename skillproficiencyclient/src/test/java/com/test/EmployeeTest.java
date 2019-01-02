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

import com.attra.pms.util.Domain;
import com.attra.pms.util.ICalculator;

public class EmployeeTest {
	private static ICalculator calculator;
	    @BeforeClass
	    public static void initCalculator() {
	        calculator = new Domain();
	    }
	    @Before
	    public void beforeEachTest() {
	    	MockitoAnnotations.initMocks(this);
	        System.out.println("This is executed before each Test");
	    }
	 
	    @After
	    public void afterEachTest() {
	        System.out.println("This is exceuted after each Test");
	    }
	 
	    @Test
	    public void testDomain() {
	        int result = calculator.domainProficiency(3, 4);
	        
	        assertEquals(7, result);
	 
	    }
	 
	    @Test
	    public void testTools() {
	        try {
	            int result = calculator.searchresults(10, 2);
	 
	            assertEquals(5, result);
	        } catch (Exception e) {
	            e.printStackTrace(System.err);
	        }
	    }
	    
	    
	 
	    @Test(expected = Exception.class)
	    public void testSkillProficiencyException() throws Exception {
	        calculator.searchresults(10, 0);
	    }
	 
	    @Ignore
	    @Test
	    public void testCompareSkill() {
	        boolean result = calculator.equalIntegers(20, 20);
	 
	        assertFalse(result);
	    }
	 
	    @Ignore
	    @Test
	    public void testSkillUpdate() {
	        int result = 10 - 3;
	 
	        assertTrue(result == 9);
	    }
	    
	    @Test
	    public void testProjCompleteness() {
	        try {
	            int result = calculator.searchresults(10, 2);
	 
	            assertEquals(5, result);
	        } catch (Exception e) {
	            e.printStackTrace(System.err);
	        }
	    }
	    
	    @Test
	    public void testCrossSkillSet() {
	        try {
	            int result = calculator.searchresults(10, 2);
	 
	            assertEquals(5, result);
	        } catch (Exception e) {
	            e.printStackTrace(System.err);
	        }
	    }
	    
	    @Test
	    public void testSearchDomainResults() {
	        int result = calculator.domainProficiency(3, 4);
	        
	        assertEquals(7, result);
	 
	    }
	    
	    @Test
	    public void testSearchEmployeeResults() {
	        int result = calculator.domainProficiency(3, 4);
	        
	        assertEquals(7, result);
	 
	    }
	    
	    @Test
	    public void testDomainExperienceCandidates() {
	        int result = calculator.domainProficiency(3, 4);
	        
	        assertEquals(7, result);
	 
	    }
	    
	    @Test
	    public void testSkillSetCandidates() {
	        int result = calculator.domainProficiency(3, 4);
	        
	        assertEquals(7, result);
	 
	    }
	    
	    
	    @Test
	    public void testCoreTools() {
	        try {
	            int result = calculator.searchresults(10, 2);
	 
	            assertEquals(5, result);
	        } catch (Exception e) {
	            e.printStackTrace(System.err);
	        }
	    }
	    
	    
	    @Test
	    public void testCoreSkillProficiency() {
	        try {
	            int result = calculator.searchresults(10, 2);
	 
	            assertEquals(5, result);
	        } catch (Exception e) {
	            e.printStackTrace(System.err);
	        }
	    }

}
