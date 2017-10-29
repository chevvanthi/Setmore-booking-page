package com.setmore.bookingPage;


import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.setmore.bookingPage.URLFetchClass;

@Controller
public class SetmoreBookingPageServlet  {
	
	ModelAndView model;


	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView login(){
	    return new ModelAndView("index");
	}
	
	@RequestMapping(value="/service",method=RequestMethod.GET)
	@ResponseBody
	public HashMap<String,Object> getService(@RequestBody String companyKey ) throws Exception{
		
		URLFetchClass urlclassObj             =  new URLFetchClass();
		String accessToken                    =  urlclassObj.getAccessToken();
		HashMap<String,Object> services      =   urlclassObj.getServiceDetails(accessToken);
			
		return services;	
	
	}
	
	
}
