package com.setmore.bookingPage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLFetchUtil {

	
	
	public static String authenticationHttpRequest(String urlString, String params, String methodName, String contentType, String access_Token) {
		StringBuffer responseJson = new StringBuffer();
		String responseData  = "";
		try{
			
			URL url = new URL(urlString);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod(methodName);
			connection.setReadTimeout(60000);
			connection.setConnectTimeout(60000);
			connection.setInstanceFollowRedirects(false);
			
			if(contentType != null && !contentType.isEmpty()){
				connection.setRequestProperty("Content-Type",contentType);
				connection.setRequestProperty("Authorization","Bearer "+access_Token);

			}
			else{
				connection.setRequestProperty("Authorization","Bearer "+access_Token);

			}
			if(params != null && !params.isEmpty()){
				OutputStreamWriter writers = new OutputStreamWriter(connection.getOutputStream());
				writers.write(params);
				writers.flush();
			}

			System.out.println(";-----; response code " + connection.getResponseCode());

			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

				String responseString = "";
				while ((responseString = reader.readLine()) != null) {

					responseJson.append(responseString);
				}
			}else{
				responseJson.append("{\"error\" : \"Error from server " + connection.getResponseCode() + ".\"}");
			}

			responseData = responseJson.toString();
	
		}catch(Exception e){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			System.out.println(sw.toString());
		}
		return responseData;
	}
	
public static String makeHttpRequest(String urlString, String params, String methodName, String contentType) throws Exception{
		
		StringBuffer responseJson = new StringBuffer();
		String responseData  = "";
		try{
			
			URL url = new URL(urlString);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod(methodName);
			connection.setReadTimeout(60000);	
			connection.setConnectTimeout(60000);
			connection.setInstanceFollowRedirects(false);
			
			if(contentType != null && !contentType.isEmpty()){
				connection.setRequestProperty("Content-Type",contentType);
				connection.setRequestProperty("ACCEPT",contentType);
			}
			if(params != null && !params.isEmpty()){
				OutputStream os = connection.getOutputStream();
                os.write(params.getBytes());
                os.flush();
			}

			System.out.println(";-----; response code " + connection.getResponseCode());

			if(connection.getResponseCode() >= HttpURLConnection.HTTP_OK && connection.getResponseCode() < HttpURLConnection.HTTP_MULT_CHOICE){
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String responseString = "";
				while ((responseString = reader.readLine()) != null) {

					responseJson.append(responseString);
				}
			}else{
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String responseString = "";
				while ((responseString = reader.readLine()) != null) {

					responseJson.append(responseString);
				}
				
				if(responseJson.toString().isEmpty()){
					responseJson.append("{\"error\" : \"Error from server " + connection.getResponseCode() + ".\"}");
				}
				
				
			}

			responseData = responseJson.toString();
		}catch(Exception e){			
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			throw e;
		}
		return responseData;
	}
}
