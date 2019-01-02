package com.attra.pms.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;



public class MessageProperties
{
   private final Properties configProp = new Properties();
   
    
   private MessageProperties()
   {
      //Private constructor to restrict new instances
      InputStream in = this.getClass().getClassLoader().getResourceAsStream("message.properties");
      try {
          configProp.load(in);
      } catch (IOException e) {
          //e.printStackTrace();
      }
   }
 
   //Bill Pugh Solution for singleton pattern
   private static class LazyHolder
   {
      private static final MessageProperties INSTANCE = new MessageProperties();
   }
 
   public static MessageProperties getInstance()
   {
      return LazyHolder.INSTANCE;
   }
    
   public String getProperty(String key){
	   String value = configProp.getProperty(key);
	   return value;
   }
    
   public Set<String> getAllPropertyNames(){
      return configProp.stringPropertyNames();
   }
    
   public boolean containsKey(String key){
      return configProp.containsKey(key);
   }
   
   
   public static String getMessageKey(String keyName) {
	   return MessageProperties.getInstance().getProperty(keyName);
	   }

   
   public static void main(String[] args)
   {
     //Get individual properties
      
     //All property names
	   //System.out.println("key"+getPropertyByKey("noRoleError"));
	   
   }
}