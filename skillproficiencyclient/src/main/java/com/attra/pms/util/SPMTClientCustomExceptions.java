package com.attra.pms.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.attra.pms.model.ExceptionObject;
import org.apache.log4j.Logger;

//import com.attra.pms.daoImpl.MyTaskDaoImpl;
import com.attra.pms.model.ExceptionObject;



public abstract class SPMTClientCustomExceptions implements clientConstants {
		
	private static final Logger logger = Logger.getLogger(SPMTClientCustomExceptions.class.getName());
	

	
	/*public static void getExceptionCode(ExceptionObject obj){
		
		StringWriter stack = new StringWriter();
		obj.getE().printStackTrace(new PrintWriter(stack));
		
			logger.error(obj.getClassname()+" method: "+obj.getMethodName()+" EmailID: "+ obj.getEmailId()+" Exception Occured: "+stack);
			//obj.getE().printStackTrace();
		
	}
	*/

	
public static void getExceptionCode(ExceptionObject obj){
		
		StringWriter stack = new StringWriter();
		obj.getE().printStackTrace(new PrintWriter(stack));
		StringBuilder builder = new StringBuilder();
		Class<?> classNamne = obj.getClassname();
		builder.append(classNamne);
		
			logger.error(obj.getClassname()+" method: "+obj.getMethodName()+" EmailID: "+ obj.getEmailId()+" Exception Occured: "+stack);
			//obj.getE().printStackTrace();
		
	}
	
	
	

	public static String getExceptionCode(Exception e){
		
		
		
		logger.error("PMS -  Error Message is "+e.getMessage());
		if(e instanceof HttpClientErrorException)
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
			
			if(e.getMessage().equals(clientConstants.UNAUTHORIZED401))
			{
				return MessageProperties.getMessageKey(clientConstants.ERROR401);
			}
			if(e.getMessage().equals(clientConstants.FORBIDDEN403))
			{
				return MessageProperties.getMessageKey(clientConstants.ERROR403);
			}
			else
			{
				 return MessageProperties.getMessageKey(clientConstants.OTHER_ERROR);
			}
			
		}
		/*if(e instanceof IOException)
		{
			System.out.println("IOException");
			
			return clientConstants.IO_EXCEPTION;
		}
		if(e instanceof NullPointerException)
		{
			return e.getCause().toString();
		}
		if(e instanceof ResourceAccessException)
		{
			return MessageProperties.getMessageKey(clientConstants.SERVER_DOWN);
		}
		else
		{
			return MessageProperties.getMessageKey(clientConstants.OTHER_ERROR);
			return MessageProperties.getMessageKey(e.getMessage());
		}*/
	
	
		if(e instanceof IOException)
		{
			return ServerConstants.IO_EXCEPTION;
		}
		if(e instanceof NullPointerException)
		{
			return e.getCause().toString();
		}
		if(e instanceof ResourceAccessException)
		{
			return MessageProperties.getMessageKey(ServerConstants.SERVER_DOWN);
		}
		if(e instanceof ArithmeticException )
		{
			return ServerConstants.ARITHMETIC_EXCEPTION;
		}
		if(e instanceof HibernateException )
		{
			return ServerConstants.HIBERNATE_EXCEPTION;
		}
		else if(e instanceof ArrayStoreException )//k
		{
			return ServerConstants.ARRAYSTORE_EXCEPTION;
		}
		else if(e instanceof ArrayIndexOutOfBoundsException ) //k
		{
			return ServerConstants.ARRAYINDEX_OUT_OF_BOUND_EXCEPTION;
		}
		else if(e instanceof StringIndexOutOfBoundsException  )//k
		{		
			return ServerConstants.STRING_INDEX_OUT_OF_BOUND_EXCEPTION;
		}
		else if(e instanceof IndexOutOfBoundsException  )//k
		{
			return ServerConstants.INDEX_OUT_OF_BOUND_EXCEPTION;
		}
		
		else if(e instanceof ClassCastException )//k
		{
			return ServerConstants.CLASSCAST_EXCEPTION;
		}
		else if(e instanceof NumberFormatException )
		{
		   	return ServerConstants.NUMBERFORMAT_EXCEPTION;
		}
		
		else if(e instanceof IllegalArgumentException )
		{
			return ServerConstants.ILLEGAL_ARGUMENT_EXCEPTION;
		}
		else if(e instanceof IllegalMonitorStateException )
		{
			return ServerConstants.ILLEGAL_MONITORSTATE_EXCEPTION;
		}
		else if(e instanceof  NegativeArraySizeException)
		{
		return ServerConstants.NEGATIVE_ARRAY_SIZE_EXCEPTION;
		}
				
		else if(e instanceof InstantiationException  )
		{
		  return ServerConstants.INSTANTIATION_EXCEPTION;
		}
		else if(e instanceof Exception  )
		{
		  return ServerConstants.EXCEPTION;
		}else
		{
		/*	return MessageProperties.getMessageKey(clientConstants.OTHER_ERROR);*/
			return MessageProperties.getMessageKey(e.getMessage());
		}
	}

}
