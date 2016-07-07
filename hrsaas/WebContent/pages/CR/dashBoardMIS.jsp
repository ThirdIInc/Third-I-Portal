<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>   
<script LANGUAGE="Javascript" SRC="../charts/jsClass/FusionCharts.js"></script>
<%@ include file="../charts/FusionCharts.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.Map"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<SCRIPT type="text/javascript">
function showScenario(){
var scenerioId=document.getElementById('paraFrm_scenarioId').value;
alert("scenerioId - "+scenerioId);
if(scenerioId=='-1'){
alert('Please Select Scenerio');
return false;
}
document.getElementById('paraFrm').target = "_self";
document.getElementById("paraFrm").action='DashBoard_getObjMIS.action?scenerioId='+scenerioId;
document.getElementById("paraFrm").submit();
document.getElementById("paraFrm").target="main";
}
function generateReport(){
 var scenerioId=document.getElementById('paraFrm_scenarioId').value;
if(scenerioId=='-1'){
alert('Please Select Scenerio');
return false;
}
 document.getElementById("paraFrm").action='DashBoard_getComponetReport.action?scenerioId='+scenerioId;
	 document.getElementById("paraFrm").target="main";
	 document.getElementById("paraFrm").submit();
}
function getGraph(preComponentId,nextcomponentId,graphParameter,nextParam,autoID) {
		alert('preComponentId '+preComponentId);
		alert('nextcomponentId '+nextcomponentId);
		alert('graphParameter '+graphParameter);
		alert('nextParam '+nextParam);
		alert('autoID' +autoID);
		
		
		if(nextcomponentId=="null"||nextcomponentId=="") {
			return;
		}
		document.getElementById("paraFrm").action='DashBoard_getComponent.action?autoID='+autoID+'&preComponentId='+preComponentId+'&componentId='+nextcomponentId+'&nextParam='+nextParam+'&graphParameter='+graphParameter;
		document.getElementById("paraFrm").submit();
	}
</SCRIPT>
<body>
<s:form action="DashBoardAction" validate="true" id="paraFrm"name="paraFrm" validate="true" theme="simple">
<s:hidden name="compoNo"/>
<s:hidden name="scenario_name"/>
<s:hidden name="isDataInternal"/>
<s:hidden name="componentName"/>
<s:hidden name="accountID"/>
<s:hidden name="accountName"/> 
<s:hidden id="scrWidth" name="screenWidth"/>
<s:hidden id="dashBoardID" name="dashBoardID"/>
<s:hidden id="userID" name="userID"/>
<s:hidden id="usertype" name="userType"/>
<s:hidden name="dashMonth"/>
<s:hidden name="dashYear"/>
<s:hidden name="dashscFlag"> </s:hidden>
<table width="100%" border="0"   cellpadding="0" cellspacing="0">
<tr align="center">
<td>
<table width="100%">
<tr>
<td nowrap="nowrap" align="right">
<input type="button" value="Generate Report" onclick="generateReport();"> 
</td>
<td nowrap="nowrap" align="left">
<strong>Select Scenario:</strong>
<s:select list="SenerioMap" headerKey="-1" headerValue="" name="scenarioId" onchange="showScenario()" /> 
<!--<img src="../pages/images/recruitment/search2.gif" width="16" height="15" class="iconImage" id="ctrlShow" onclick="javascript:callf9function();" />
--></td>
</tr>
</table>
</td>
</tr>
</table>
<!-- Drill Down Component -->
<!-- For Drill Down Requirement Data-->
<%
	try{
	Map mainMap=(Map)request.getAttribute("mainMap");
	Map componentMap=null;
	String previousDataString=(String)request.getAttribute("previousDataString");
	
	int mapSize=mainMap.size();
	System.out.println("mapSize-- "+mapSize); 
	for(int i=0;mapSize>i;i++){
		// Map object getting form DashBoardModel and from function getResultbyComponentId
		componentMap=(Map)mainMap.get(String.valueOf(i));
		Object tableData[][]=(Object[][])componentMap.get("tableData");
		String chartData=String.valueOf(componentMap.get("chartData"));
		String header=String.valueOf(componentMap.get("graphName"));
		System.out.println("header Parameter - "+header);
		String isChartEnable=String.valueOf(componentMap.get("isChart"));
		String isTable=String.valueOf(componentMap.get("isTable"));
		String isDataInternal=String.valueOf(componentMap.get("isDataInternal"));
		String graphParam=String.valueOf(componentMap.get("graphParam"));
		System.out.println("graph Parameter - "+graphParam);
		String graphParamSimplyfied=graphParam;
		graphParamSimplyfied=graphParamSimplyfied.replace("1111","3333");
		graphParamSimplyfied=graphParamSimplyfied.replace("2222","=");
		String nextComponentId=String.valueOf(componentMap.get("nextComponentId"));
		System.out.println("nextComponentId Parameter - "+nextComponentId);
		String currentComponentId=String.valueOf(componentMap.get("currentComponentId"));
		System.out.println("currentComponentId Parameter - "+currentComponentId);
		String componentId=String.valueOf(componentMap.get("componentId"));
		System.out.println("componentId Parameter - "+componentId);
		System.out.println("ComponentId - "+componentId+"Current Component Id"+currentComponentId);
		String graphType=String.valueOf(componentMap.get("graphType"));
		String headerComponentName="";
		if(graphParamSimplyfied!=null){
		headerComponentName=graphParamSimplyfied.replace("3333",", ");
		headerComponentName=headerComponentName.replace("=",": ");
		String screenWidth=	(String)request.getAttribute("screenWidth");
		double iscreenWidth=Double.parseDouble(screenWidth);
		double calculatedWidht=iscreenWidth - 20.0 ;
		System.out.println("DashBoard Width -"+calculatedWidht );
		}
	
%>
<table width="100%" height="100%" border="0" cellpadding="0" id="dashTab" 
	name="OuterTable" cellspacing="0" style="border-collapse: collapse; border-color: #ccc">
		<tr>
			<td class="portalAppButtons" align="left" width="100%">
			<img src="../pages/CR/icons/analytics.png" ;	align="absmiddle" width="16"
				height="16">
				<!--  
				<a href="#"	onclick="return backFun();" align="right"">
				<img src="../pages/CR/icons/HomeDashBoard.png" class="iconImage" align="right""></a> -->
				<a href="#">
				<img src="../pages/cssv2/icons/message24.png" onclick="sendMail('<%=currentComponentId%>','<%=header%>','<%=graphParamSimplyfied%>','<%=isDataInternal%>','<%=nextComponentId %>')" class="iconImage" align="right" width="16" height="16" style="padding: 2px";></a>
				
		</tr>
		<!--  chart Start here -->
		<tr>
		<td>
		<%
		String chartCode = "";
		try{
			// for calculating client desktop screen width for dashboard components
			String screenWidth=	(String)request.getAttribute("screenWidth");
			double iscreenWidth=Double.parseDouble(screenWidth);
			double calculatedWidht=iscreenWidth * 0.80 ;
			System.out.println("DashBoard Width -"+calculatedWidht );
			//check graph type
		if(isChartEnable.equalsIgnoreCase("Y")){	
			System.out.println("Graph Type is"+graphType);
		   if(graphType!=null && graphType.trim().equals("Pie2D")){
		    System.out.println("in createChart strXML=======" + chartData);
			chartCode = createChartHTML("../pages/charts/fusionCharts/Pie2D.swf", "",	chartData, "productSales", String.valueOf(calculatedWidht), 400, false);
		     }
		   else if(graphType!=null && graphType.trim().equals("MLine")){
			    System.out.println("in createChart strXML=======" + chartData);
				chartCode = createChartHTML("../pages/charts/fusionCharts/MSLine.swf", "",	chartData, "productSales", String.valueOf(calculatedWidht), 400, false);
			     }
		   else if(graphType!=null && graphType.trim().equals("MCLine")){
			    System.out.println("in createChart strXML=======" + chartData);
				chartCode = createChartHTML("../pages/charts/fusionCharts/MSColumnLine3D.swf", "",	chartData, "productSales", String.valueOf(calculatedWidht), 400, false);
			     }
		   else if(graphType!=null && graphType.trim().equals("MLineScroll")){
			    System.out.println("in createChart strXML=======" + chartData);
				chartCode = createChartHTML("../pages/charts/fusionCharts/ScrollLine2D.swf", "",	chartData, "productSales", String.valueOf(calculatedWidht), 400, false);
			     }
		   else if(graphType!=null && graphType.trim().equals("barScroll")){
			    System.out.println("in createChart strXML=======" + chartData);
				chartCode = createChartHTML("../pages/charts/fusionCharts/ScrollColumn2D.swf", "",	chartData, "productSales", String.valueOf(calculatedWidht), 400, false);
			     }
		   else if(graphType!=null && graphType.trim().equals("USGraph")){
			    System.out.println("in createChart strXML=======" + chartData);
				chartCode = createChartHTML("../pages/charts/fusionCharts/FCMap_USA.swf", "",	chartData, "productSales", String.valueOf(calculatedWidht), 400, false);
			     } 
		   else{
				System.out.println("in createChart strXML======="+ chartData);
				chartCode = createChartHTML("../pages/charts/fusionCharts/MSColumn3D.swf","", chartData,"productSales", String.valueOf(calculatedWidht), 400, false);
		     }
			 
		}}
		catch (Exception e) {
						e.printStackTrace();
					}
			//System.out.println("Chart Data On Div - "+chartCode);
			%>
			<div style="height: 100%" align="center"><%=chartCode%></div>
			
			</td>
		</tr>
		<!-- chart End Here -->	
		<!--  check whethere expanded component containt data table -->
		<%if(isTable.equalsIgnoreCase("Y")) {%>
		
		<tr>
			<!--Table Start Hare-->

			<table width="100%" height="100%" border="0" cellpadding="0"
				id="dashTab" name="innerCellTab" cellspacing="0"
				style="border-collapse: collapse; border-color: #ccc">
				<%
					//rendering the table a=row b=cols
					if (tableData != null) {
						for (int a = 0; a < tableData.length; a++) {
							//for giving alternative color of table rows
							if (a % 2 == 0) {
				%>
				<tr valign="top" style="background-color: #E6EAE2">
					<%
					} else {
					%>
				
				<tr style="background-color: #FFFFFF">
					<%
								}
								for (int b = 0; b < tableData[0].length; b++) {
							if (a == 0) {
					%>
					<th ALIGN="left" valign="top"
						style="padding-left: 4px; padding-right: 2px; background-color: ">
						
						<%=tableData[a][b]%>
						
						</th>
					<%
					} else {
					%>

					<td ALIGN="Left"
						style="border: 1px solid #ccc; padding-left: 4px; padding-right: 2px;"><a href="#" onclick="javascript:getGraph('<%=currentComponentId%>','<%=nextComponentId%>','<%=String.valueOf(graphParam).trim()%>','<%=String.valueOf(tableData[a][0]).trim()%>','0');"><%=tableData[a][b]%></a></td>
					<%
								}
								}//end icol
					%>
				</tr>
				<%
					}//end irow
					}// end if
				%>
			<!--  End of Table Here -->
			</table>
			<br />
			<% }//table if end %>
		</tr>
	</table>
		<%
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	
	%>

</s:form>
</body>
</html>