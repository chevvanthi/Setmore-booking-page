package com.setmore.bookingPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fullauth.api.enums.OauthExpiryType;
import com.fullauth.api.model.oauth.OauthAccessToken;
import com.fullauth.api.service.FullAuthOauthService;


public class URLFetchClass {

    
           
        public static void main(String[] args) throws Exception{
        	URLFetchClass urlObj = new URLFetchClass();
        	String token = urlObj.getAccessToken();
        	urlObj.getServiceDetails(token);
        	
        }
        
        /**
         * 
         * @param refreshToken
         * @return access token
         */
        private OauthAccessToken getAccessTokenByRefreshToken(String refreshToken){
    		OauthAccessToken token = null;
    		try{
    			FullAuthOauthService authService = FullAuthOauthService.builder()
    					.authDomain(WorkConstants.fullAuthURL)
    					.clientId(WorkConstants.FULLAUTH_CLIENT_ID)
    					.clientSecret(WorkConstants.FULLAUTH_CLIENT_SECRET)
    					.build();

    			token = authService.refreshAccessToken(refreshToken, OauthExpiryType.LONG);
    			token.setRefreshToken(refreshToken);
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    		return token;
    	}
	
        //TODO : add try-catch
     public String getAccessToken() throws Exception{
        	
        	   /*URL obj               = new URL("https://developer.setmore.com/api/v1/o/oauth2/token?refreshToken=7af7d05d50SB3I6gYN6AosocWO_N3Tquz1s0w4_vNvnw8");
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

        	    HashMap<String,Object> hashmap = objectmapper.readValue(response, typeRef); */
    	 
    	 		String url = WorkConstants.setmoreURL+"/o/oauth2/token?refreshToken=7af7d05d50SB3I6gYN6AosocWO_N3Tquz1s0w4_vNvnw8";
    	 		
    	 		String response = URLFetchUtil.makeHttpRequest(url, null, "GET", "application/json");
    	 		
    	 		HashMap<String,Object> responseMap = (HashMap) UtilityMethods.getObjectFromJsonString(response, HashMap.class);
        	 
        	    String accessToken = "";
        	   
        	    for(Map.Entry<String, Object> entry : responseMap.entrySet()){
        	    	
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

     //TODO : add try-catch
	public Map<String,Object> getServiceDetails(String token) throws Exception {
		
		String url = WorkConstants.setmoreURL+"/bookingapi/services";
		
		System.out.println("the value of token inseide the get service " + token);
		/*URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");		
        con.setRequestProperty("Content-Type", "application/json");	 
        con.setRequestProperty("Authorization","BEARER " + Token);
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			response += inputLine;	
			
		}*/
		
		String response = URLFetchUtil.authenticationHttpRequest(url, null, "GET", "application/json", token);
		
		/*ObjectMapper mapper = new ObjectMapper();
		
		   TypeReference<HashMap<String,Object>> typeRef 
           = new TypeReference<HashMap<String,Object>>() {};

             HashMap<String,Object> hashmap = mapper.readValue(response, typeRef); 
		
			System.out.println("the service value is " + hashmap);*/
		
		Map<String,Object> responseMap = (Map) UtilityMethods.getObjectFromJsonString(response, HashMap.class);
			
				
		  return responseMap;

	}
	
}
