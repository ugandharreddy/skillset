package com.test;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	 public static void main(String[] args) {
		 
		// Save the System.out instance
	        PrintStream oldPrintStream = System.out;
	   /*  Result result = JUnitCore.runClasses(EmployeeSearch.class);
			
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
			
	      System.out.println(result.wasSuccessful());
	      */
	        
	      /*writing junit result's to external file */
	      try (OutputStream outFile = Files.newOutputStream(Paths.get("JunitTestresult.txt"), 
	    	       StandardOpenOption.WRITE, StandardOpenOption.CREATE);
	    	     PrintStream newSysOut = new PrintStream(outFile)) {
	    	     System.setOut(newSysOut);
	    	     Result result = JUnitCore.runClasses(EmployeeSearchTest.class);
	    	     for (Failure failure : result.getFailures()) {
	    	         System.out.println(failure.toString());
	    	      }
	    			
	    	      System.out.println(result.wasSuccessful());
	    	} catch (IOException ex) {
	    	     // your code here
	    	} finally {
	    	     System.setOut(oldPrintStream);
	    	}
	   }
	
}
