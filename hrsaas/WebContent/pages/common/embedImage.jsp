<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="javax.servlet.*,java.io.*"
    pageEncoding="ISO-8859-1"%>
 
 
 <%
  		 response.setContentType("image/jpeg");
		 ServletOutputStream out1 = response.getOutputStream();
		String path=request.getParameter("path");
	//	 String path="C:/hrwork/dataFiles/EPFO_Logo.jpg";
		 BufferedInputStream in = new BufferedInputStream(new FileInputStream (path));
		 int data=0;
		 while ((data = in.read()) != -1) {
		 out1.write(data);
		 }
		 in.close();
		 out1.close();
		  
	 
 %>