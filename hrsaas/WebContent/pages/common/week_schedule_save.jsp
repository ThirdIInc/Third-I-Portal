<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String description=request.getParameter("description");
String bgColorCode=request.getParameter("bgColorCode");
String eventStartDate=request.getParameter("eventStartDate");
String eventEndDate=request.getParameter("eventEndDate");
String id=request.getParameter("id");
 System.out.println("under week_schwdule_save jsp file------description----"+description);
 System.out.println("under week_schwdule_save jsp file----bgColorCode------"+bgColorCode);
 System.out.println("under week_schwdule_save jsp file----veventStartDate------"+eventStartDate);
 System.out.println("under week_schwdule_save jsp file------eventEndDate----"+eventEndDate);
 System.out.println("under week_schwdule_save jsp file-----id-----"+id);
 
 %>
</body>
</html>