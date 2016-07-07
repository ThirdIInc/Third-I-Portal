<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page  import="java.sql.*,java.util.*" %> 
<%@page import="org.paradyne.model.common.WeekPlannerModel"%>
<%

String year=request.getParameter("year");
String month=request.getParameter("month");
String day=request.getParameter("day");

System.out.println("year-----------------"+year);
System.out.println("month-----------------"+month);
System.out.println("day-----------------"+day);
WeekPlannerModel model=new WeekPlannerModel();
model.initiate(config.getServletContext(),session);
//model.getItemsData(year,month,day);
//String date=day+"-"+month+"-"+year;

Object[][]twoDimObjArr=model.getItemsData(year,month,day);//(Object[][]) request.getAttribute("getItemData");
model.terminate();

%>
<%!int i, j;
	
%>

		
		
<%	if(twoDimObjArr !=null)
{
for(i=0;i<twoDimObjArr.length;i++)
{
	%>
	

<item>
		<id><%=twoDimObjArr[i][2]%></id>
		<description><%=twoDimObjArr[i][3]%></description>
		<eventStartDate><%=twoDimObjArr[i][0]%> </eventStartDate>
		<eventEndDate><%=twoDimObjArr[i][1]%> </eventEndDate>
		<bgColorCode><%=twoDimObjArr[i][4]%></bgColorCode>
	</item>
	<%}
}
	%>