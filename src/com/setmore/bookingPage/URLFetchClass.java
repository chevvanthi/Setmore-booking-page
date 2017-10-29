package com.setmore.bookingPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.fasterxml.jackson.core.type.TypeReference;


public class URLFetchClass {

    
           
        public static void main(String[] args) throws Exception{
        	URLFetchClass urlObj = new URLFetchClass();
        	String token = urlObj.getAccessToken();
        	urlObj.getServiceDetails(token);
        	
        }
	
     public String getAccessToken() throws IOException, JSONException{
        	
        	   URL obj               = new URL("https://developer.setmore.com/api/v1/o/oauth2/token?refreshToken=7af7d05d50SB3I6gYN6AosocWO_N3Tquz1s0w4_vNvnw8");
        	   HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        	   con.setRequestMethod("GET");
        	   con.setRequestProperty("Content-Type", "application/json");
        	 
        	             
               ObjectMapper objectmapper      =  new ObjectMapper();     	 
        	   BufferedReader in              =  new BufferedReader(new InputStreamReader(con.getInputStream()));
        	   String inputLine;
        	   String response = "";

        	   while ((inputLine = in.readLine()) != null) {
        	   response +=inputLine;
        	   }
        	   in.close();
        	 
        	    TypeReference<HashMap<String,Object>> typeRef 
        	            = new TypeReference<HashMap<String,Object>>() {};

        	    HashMap<String,Object> hashmap = objectmapper.readValue(response, typeRef); 
        	 
        	    String accessToken = "";
        	   
        	    for(Map.Entry<String, Object> entry : hashmap.entrySet()){
        	    	
        	    	String key = entry.getKey();
        	    	
        	    	if(key.equals("data")){
        	    	     	    		
        	    		Map<String,Object> token = (Map<String, Object>) entry.getValue();
        	    	    		
        	    		   for(Map.Entry<String, Object> entryVal : token.entrySet()){
        	    			
        	    			   if( entryVal.getKey().equals("token")){
        	    			
        	    				      Map<String,Object> accessTokenValue = (Map<String, Object>) entryVal.getValue();
        	    				          
        	    				      for(Map.Entry<String, Object> accessTokenEntry : accessTokenValue.entrySet()){
	        	    					      if(accessTokenEntry.getKey().equals("access_token"))
	        	    						   accessToken = (String) accessTokenEntry.getValue();    	    						
        	    				           }
        	    			         }	
        	    		      }	    		
        	    	      }        	    	
        	    	}
        	  
        	   return accessToken;      	  
        	   
           }

	public HashMap<String,Object> getServiceDetails(String Token) throws Exception {
		
		String url = "https://developer.setmore.com/api/v1/bookingapi/services";
		String response="";
		
		System.out.println("the value of token inseide the get service " + Token);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");		
        con.setRequestProperty("Content-Type", "application/json");	 
        con.setRequestProperty("Authorization","BEARER " + Token);
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			response += inputLine;	
			
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		   TypeReference<HashMap<String,Object>> typeRef 
           = new TypeReference<HashMap<String,Object>>() {};

             HashMap<String,Object> hashmap = mapper.readValue(response, typeRef); 
		
			System.out.println("the service value is " + hashmap);
			
				
		  return hashmap;

	}
	
}
