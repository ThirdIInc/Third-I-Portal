	<%@ taglib prefix="s" uri="/struts-tags"%>
	<script LANGUAGE="Javascript" SRC="../charts/jsClass/FusionCharts.js"></script>
	<%@ include file="../charts/FusionCharts.jsp"%>
	<%@page import="java.util.HashMap"%>
	<%@page import="java.util.List"%>
	<%@page import="java.util.ArrayList"%>
	<%@page import="java.util.Map"%>
	<html>
	<head></head>
	
	<body>
	
	<s:form action="DashBoardAction" validate="true" id="paraFrm" name="paraFrm" validate="true" theme="simple">
	
	<s:hidden id="dashBoardID" name="dashBoardID"/>
	<s:hidden id="accountID" name="accountID" />
	<s:hidden id="accountName" name="accountName" />
	<s:hidden id="usertype" name="userType"/>
	<s:hidden id="userID" name="userID"/>
	<s:hidden id="screenWidth" name="screenWidth"/>
	<s:hidden id="dashBoardName" name="dashBoardName"/>
	<s:hidden id="dashBoardAccountName" name="dashBoardAccountName"/>
	<s:hidden name="dashMonth"/>
	<s:hidden name="dashYear"/>
	
	<%
	String GraphPara=(String)request.getAttribute("GraphPara");
	System.out.println("GraphPara - - - "+GraphPara);
	%>
	
	<table border="0" width="100%">
		<tr>
		<%if(!GraphPara.equals("")){ %>
			<td align="right" width="2%" id="back">
			<a href="#" onclick="backhistory();"><img
						src="../pages/images/buttonIcons/back.png" class="iconImage"
						align="absmiddle" title="Back">&nbsp;</a>
			<!-- 
			<INPUT type=button value=" Back " onClick="backhistory();">
			 -->
			</td>
			<%} %>	
				<td colspan="2" valign="top" align="left">
				<div style="text-align: center;">
				<STRONG><s:property value="dashBoardName"/> 
				<s:if test='%{accountName!=""}'>
				 - <s:property value="dashBoardAccountName"/> ( <s:property value="accountName"/> )
				</s:if>
				</STRONG></div></td>
				
				
				</tr>
		</table>
	
		
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
				height="16"><strong>&nbsp;<%=header%>
			<% 
			// for display parameter of graph
			if(!graphParamSimplyfied.equals("")){
				headerComponentName=headerComponentName.replaceFirst(",","  ");
			%>	
				(<%=headerComponentName%>)</strong> 
				<%
			}%>
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
	<table border="0" width="100%">
		<tr>
		<%if(!GraphPara.equals("")){ %>
			<td align="right" width="2%" id="back">
			<a href="#" onclick="backhistory();"><img
						src="../pages/images/buttonIcons/back.png" class="iconImage"
						align="absmiddle" " title="Back">&nbsp;</a>
			
			
			<!-- 
			<INPUT type=button value=" Back " onClick="backhistory();">
			 -->
			</td>
			<%} %>	
				<td colspan="2" valign="top" align="left">
				<div style="text-align: center;">
				<STRONG>
				</STRONG></div></td>
				
				
				</tr>
		</table>
	</s:form>
	
	</body>
	
	
	<script type="text/javascript"><!--
	var count=1;
	//navigating to dashboard home page
	function backFun() {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'DashBoard_input.action';
			document.getElementById('paraFrm').submit();
	}
	// future used
	function backDrillDown(preComponentId,graphParameter) {	
		document.getElementById("paraFrm").action='DashBoard_getComponent.action?componentId='+preComponentId+'&graphParameter='+graphParameter;
		document.getElementById("paraFrm").submit();
	}
	//this function is used for drill down perpose by giving perivious component value, next component value, 
	// autoId is used for if same component is used for differnt graph for example same component is used for pia and bar graph for this autoid is used 
	function getGraph(preComponentId,nextcomponentId,graphParameter,nextParam,autoID) {
		//alert('preComponentId '+preComponentId);
		//alert('nextcomponentId '+nextcomponentId);
		//alert('graphParameter '+graphParameter);
		//alert('nextParam '+nextParam);
		//alert('autoID' +autoID);
		
		
		if(nextcomponentId=="null"||nextcomponentId=="") {
			return;
		}
		document.getElementById("paraFrm").action='DashBoard_getComponent.action?autoID='+autoID+'&preComponentId='+preComponentId+'&componentId='+nextcomponentId+'&nextParam='+nextParam+'&graphParameter='+graphParameter;
		document.getElementById("paraFrm").submit();
	}
		//sending mail by component id
	function sendMail(componentNo,componentName,graphPrameter,isDataInternal,nextcomponentId,nextParam,autoID){
	try{
		
		componentName=componentName.replace('&','and');
		document.getElementById("paraFrm").action='DashBoard_getEmailPage.action?CompoNo='+componentNo+'&componentName='+componentName+'&graphPrameter='+graphPrameter+'&isDataInternal='+isDataInternal+'&isDashBoard=NN'; 
		document.getElementById("paraFrm").submit();
		//document.getElementById("paraFrm").target="main";
		}
	catch(e){
		alert(e)
		}
		}
	Onload();
	function Onload(){
	if(history.length==1){

	document.getElementById('back').style.display ='none';
	
	}
	else{

	document.getElementById("back").display="block";
	}
	}
	
	
	function backhistory(){
	//history.back();
	if(navigator.appName!='Microsoft Internet Explorer'){
	//alert("in other")
	
	
	history.back();
	}	
	else{
	//alert("in IE Browser");
	history.go(-2);
	}
}

</script>
	</html>