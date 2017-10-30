<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@page
   import = "java.util.UUID"
%>
<%
  String uuid = UUID.randomUUID().toString();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Setmore Booking page</title>
 
 		<!-- <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script> -->
	  <script type="text/javascript" src = "/JSFiles/lib/jquery-3.2.1.min.js"></script>
	  <link rel = "stylesheet" type="text/css" href = "DesignFiles/design.css" /> 
	  <script src="/JSFiles/BookingPage.js?id=<%=uuid%>"></script>
	  <script src="/JSFiles/modal.js?id=<%=uuid%>"></script>  
	  
</head>

<body>

<div id ='serviceDiv'> 
  
	  <div id='serviceHeader'><span>Book My Service!</span></div>
	 
	  <div id='serviceList' class ='serviceListClass'></div>
	  
  
  </div>

</body>
</html>