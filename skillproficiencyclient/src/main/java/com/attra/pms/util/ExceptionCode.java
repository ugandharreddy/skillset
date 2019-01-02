package com.attra.pms.util;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

public abstract class ExceptionCode implements clientConstants {
	private static final Logger logger = Logger.getLogger(ExceptionCode.class.getName());
	
	public static String getExceptionCode(Exception e){
		logger.error("PMS -  Error Message is "+e.getMessage());
		if(e instanceof HttpClientErrorException)
		{
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
		}*/
		
		
		if(e instanceof IOException)
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
			return ServerConstants.IO_EXCEPTION;
		}
		if(e instanceof NullPointerException)
		{logger.error("PMS -  Error Message is "+e.getMessage());
			return e.getCause().toString();
		}
		if(e instanceof ResourceAccessException)
		{logger.error("PMS -  Error Message is "+e.getMessage());
			return MessageProperties.getMessageKey(ServerConstants.SERVER_DOWN);
		}
		if(e instanceof ArithmeticException )
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
			return ServerConstants.ARITHMETIC_EXCEPTION;
		}
		if(e instanceof HibernateException )
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
			return ServerConstants.HIBERNATE_EXCEPTION;
		}
		else if(e instanceof ArrayStoreException )//k
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
			return ServerConstants.ARRAYSTORE_EXCEPTION;
		}
		else if(e instanceof ArrayIndexOutOfBoundsException ) //k
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
			return ServerConstants.ARRAYINDEX_OUT_OF_BOUND_EXCEPTION;
		}
		else if(e instanceof StringIndexOutOfBoundsException  )//k
		{		logger.error("PMS -  Error Message is "+e.getMessage());	
			return ServerConstants.STRING_INDEX_OUT_OF_BOUND_EXCEPTION;
		}
		else if(e instanceof IndexOutOfBoundsException  )//k
		{
			logger.error("PMS -  Error Message is "+e.getMessage());		
			return ServerConstants.INDEX_OUT_OF_BOUND_EXCEPTION;
		}
		
		else if(e instanceof ClassCastException )//k
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
			return ServerConstants.CLASSCAST_EXCEPTION;
		}
		else if(e instanceof NumberFormatException )
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
		   	return ServerConstants.NUMBERFORMAT_EXCEPTION;
		}
		
		else if(e instanceof IllegalArgumentException )
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
			return ServerConstants.ILLEGAL_ARGUMENT_EXCEPTION;
		}
		else if(e instanceof IllegalMonitorStateException )
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
			return ServerConstants.ILLEGAL_MONITORSTATE_EXCEPTION;
		}
		else if(e instanceof  NegativeArraySizeException)
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
		return ServerConstants.NEGATIVE_ARRAY_SIZE_EXCEPTION;
		}
				
		else if(e instanceof InstantiationException  )
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
		  return ServerConstants.INSTANTIATION_EXCEPTION;
		}
		else if(e instanceof Exception  )
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
		  return ServerConstants.EXCEPTION;
		}else
		{
			logger.error("PMS -  Error Message is "+e.getMessage());
		/*	return MessageProperties.getMessageKey(clientConstants.OTHER_ERROR);*/
			return MessageProperties.getMessageKey(e.getMessage());
		}
	}

}
