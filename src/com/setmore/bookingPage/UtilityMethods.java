package com.setmore.bookingPage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;

public class UtilityMethods {

	private static JsonFactory factory 		 = new JsonFactory();
	private static final ObjectMapper mapper = new ObjectMapper(factory);
	
public static String getJsonString(Object object){
		
		String jsonString = "";
		
		try{
			
			Writer strWriter = 	new StringWriter();	
			mapper.writeValue(strWriter, object);
			jsonString = strWriter.toString();
			
		}catch(Exception e){				
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);			
			System.out.println(sw.toString());
		}
		return jsonString;
	}
	
	public static Object getObjectFromJsonString(String jsonString, Class classType){
		
		Object response = new Object();
		
		try{
			
			response = mapper.readValue(jsonString, classType);			
			
		}catch(Exception e){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);			
			System.out.println(sw.toString());
		}
		
		return response;
	}
}
