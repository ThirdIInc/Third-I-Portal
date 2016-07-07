	<!--
	@author Vijay.Gaikwad
	DashBorad jsp is used for show dashBoard components
	Date : 14-jan-2013
	-->
	<%@ taglib prefix="s" uri="/struts-tags"%>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<script LANGUAGE="Javascript" SRC="../charts/jsClass/FusionCharts.js"></script>
	
	<%@ include file="../charts/FusionCharts.jsp"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
	<%@page import="java.util.HashMap"%>
	<%@page import="java.util.Iterator"%>
	<%@page import="java.util.Map"%>
	<%@page import="java.util.Set"%>
	<%@page import="java.util.Vector"%>
	<%@page import="java.awt.Toolkit"%>
	<%@page import="java.awt.Dimension"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="java.util.LinkedHashMap"%>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<script type="text/javascript">
	function getMap(){
	alert("Done");
	}
		

	//popup function is used for generate the excel file by reportid
	//var VScreenHight=$(window).height();
	//var VScreenWidth=$(window).width();

	//alert("VScreenHight - "+VScreenHight);
	//alert("VScreenWidth - "+VScreenWidth);
//popup function is used for populating the component reports
	
	
	function popup(reportId,reportName){
	try{
		var accountID=document.getElementById("accountID").value;
		var accountName= document.getElementById("accountName").value;
    		window.open('','new','top=205px,left=141px,width=500,height=400,scrollbars=yes,status=no,resizable=yes');
	 		document.getElementById("paraFrm").target="new";
	 		document.getElementById("paraFrm").action='DashBoard_getMenuList.action?reportId='+reportId+'&reportName='+reportName;//+'&accountID='+accountID+'&accountName='+accountName; 
	  		document.getElementById("paraFrm").submit();
	  		document.getElementById("paraFrm").target="main";
	  		}
	 		 catch(e){
	  			alert(e)
	  			}
}
				//This function is used for populating only data table
	function popupTable(tableHeading,componentNo){
	try{	
    		window.open('','new','top=150,left=150,width=750,height=450,scrollbars=yes,status=no,resizable=yes');
	 		document.getElementById("paraFrm").target="new";
	 		document.getElementById("paraFrm").action='DashBoard_getPopUpTable.action?tableHeading='+tableHeading+'&componentNo='+componentNo; 
	  		document.getElementById("paraFrm").submit();
	  		document.getElementById("paraFrm").target="main";
	  		}
	catch(e){
	  	//	alert(e)
	  		}
		}
		
	function callDashBoard(){
	document.getElementById("paraFrm").target="_self"; 
	document.getElementById("paraFrm").action='DashBoard_input' ;
	document.getElementById("paraFrm").submit();
	}
		
		
				//this function is used for displaying component in data table and graph in detail format
	function showComponent(componentNo,AutoID){
	
		var dashBoardID=document.getElementById("dashBoardID").value;
		var accountID1=document.getElementById("accountID").value;
		var dashBoardName=document.getElementById("dashBoardName").value;
		var dashBoardAccountName=document.getElementById("dashBoardAccountName").value;
	
 	try{
 			window.open('','new','top=181,left=65,width=1025,height=547,scrollbars=yes,status=no,resizable=yes');
	 		document.getElementById("paraFrm").target="new";
	 		document.getElementById("paraFrm").action='DashBoard_getComponent.action?componentId='+componentNo+'&accountID1='+accountID1+'&autoID='+AutoID+'&dashBoardName='+dashBoardName+'&dashBoardAccountName='+dashBoardAccountName; 
	  		document.getElementById("paraFrm").submit();
	  		document.getElementById("paraFrm").target="main";
	  		}
	  		catch(e){
	  	//	alert(e)
	  		}
}
				//for sending mail perpose
	function sendMail(componentNo,componentName,isDataInternal){
	try{
		
		var usertype=document.getElementById("usertype").value;
		
			componentName=componentName.replace('&','and');
			window.open('','new','top=181,left=131,width=948,height=512,scrollbars=yes,status=no,resizable=yes');
	 		document.getElementById("paraFrm").target="new";
			document.getElementById("paraFrm").action='DashBoard_getEmailPage.action?CompoNo='+componentNo+'&componentName='+componentName+'&isDataInternal='+isDataInternal+'&isDashBoard=YY'; 
	  		document.getElementById("paraFrm").submit();
	  		document.getElementById("paraFrm").target="main";
		}
	catch(e){
		//alert(e)
			}

	}
				//mail scheduler
	function showScheduleReport(reportID,reportName){
			document.getElementById("paraFrm").action='DashBoard_getReportScheduler.action?reportID='+reportID+'&reportName='+reportName; 
	  		document.getElementById("paraFrm").submit();
	  		document.getElementById("paraFrm").target="main";
			}
		document.addEventListener('DOMContentLoaded', function() {
		   // your code here
		 //  setScreenWidth();
		}, false);
		
	function addrowDashBoard(){

	//alert("After Loading function is called")
	
	var table = document.getElementById("dashTab");
 
            var rowCount = table.rows.length;
           
            var row = table.insertRow(rowCount);
 		
            var colCount = table.rows[0].cells.length;
 			alert("colCount"+colCount);
            for(var i=0;i<colCount;i++){
            var newcell = row.insertCell(i);
 
         	newcell.innerHTML = table.rows[0].cells[i].innerHTML;
                //alert(newcell.childNodes);
              } 
            
	}		
	function getMyDoc(FileLoc,fileName){
	//alert(FileLoc);
	//alert(fileName);
	document.getElementById("paraFrm").target="_self"; 
	document.getElementById("paraFrm").action='DashBoard_getUserDocument.action?FileLoc='+FileLoc+'&fileName='+fileName ;
	document.getElementById("paraFrm").submit();
	}
		
	function getGraph(preComponentId,nextcomponentId,graphParameter,nextParam,autoID) {
	//alert("preComponentId - "+preComponentId);//current component ID
	//alert("nextcomponentId - "+nextcomponentId);//next
	//alert("graphParameter - "+graphParameter);//""
	//alert("nextParam - "+nextParam);//"current Parameter"
	//alert("autoID  - "+autoID);//"0"
	
	if(nextcomponentId=="null"||nextcomponentId=="") {
			return;
		}
		window.open('','new','top=181,left=65,width=1025,height=547,scrollbars=yes,status=no,resizable=yes');
	 	document.getElementById("paraFrm").target="new";
		document.getElementById("paraFrm").action='DashBoard_getComponent.action?autoID='+autoID+'&preComponentId='+preComponentId+'&componentId='+nextcomponentId+'&nextParam='+nextParam+'&graphParameter='+graphParameter;
		document.getElementById("paraFrm").submit();
	}
	
	function callMIS(){
	window.open('','new','top=181,left=65,width=1025,height=547,scrollbars=yes,status=no,resizable=yes');
	 	document.getElementById("paraFrm").target="new";
		document.getElementById("paraFrm").action='DashBoard_callMIS.action';
		document.getElementById("paraFrm").submit();
	}		
	</script>
	</head>
	<body>
	<s:form action="DashBoardAction" validate="true" id="paraFrm"name="paraFrm" validate="true" theme="simple">
	<s:hidden id="dashBoardID" name="dashBoardID"/>
	<s:hidden id="accountID" name="accountID" />
	<s:hidden id="accountName" name="accountName" />
	<s:hidden id="usertype" name="userType"/>
	<s:hidden id="userID" name="userID"/>
	<s:hidden id="scrWidth" name="screenWidth"/>
	<s:hidden id="dashBoardName" name="dashBoardName"/>
	<s:hidden id="dashBoardAccountName" name="dashBoardAccountName"></s:hidden>
	<%
	Object[][] objectReportMenuList=(Object[][])null;
	Boolean curcolSpanFlag=false;
	Boolean nextcolSpanFlag=false;
	
	
	try {
		objectReportMenuList = (Object[][]) request.getAttribute("objectReportMenuList");//report Menu List
		
		}//End of try 
		catch (Exception e) {
				e.printStackTrace();
		}//End of Catch
	%>
	<!--		Table containing one row and two column 2nd column contain many tables		-->
		<table width="100%" border="0"  align="top" cellpadding="0" cellspacing="0" >
		

		
		<tr>
		<td>
		<!-- Main table	-->
	<table width="100%" border="0"  align="top"  cellpadding="0" id="dashTab"
		name="dashTab" cellspacing="0" style="overflow: auto">
		
		
		<tr>
			<%
					//check for menu list Empty or not
				if (objectReportMenuList != null && objectReportMenuList.length > 0) {	
			%> <!-- For Menu List-->	
									<!--row start	-->
			
			
			
			<td width="100%" valign="top">
			<table width="100%" height="100%" border="0" bordercolor="black" 
				id="dashTab" name="dashTab" >
				<tr>
		<td colspan="2">
		<table width="100%">
		<tr>
		<td >
		<div style="text-align: center;">
		<STRONG><s:property value="dashBoardName"/> 	
		</STRONG></div>
		</td >
		</tr>
		</table>
		</td>
		</tr>
				<%
						try {
						//check HashMap Object(colspan,rowspan) is empty or not
						Object[][] dashObject=(Object[][])request.getAttribute("dashObject");
						/* System.out.println("dashObject Containt length - "+dashObject.length);
						for(int i=0;dashObject.length>i;i++){
							for(int j=0;dashObject[0].length>j;j++) {
								System.out.println("dashObject Containt - "+dashObject[i][j]);
							}
						}
						*/
						String USGraph=(String)request.getAttribute("USString");
						System.out.println("US Grpah String- - "+USGraph);
						LinkedHashMap comMap=(LinkedHashMap)request.getAttribute("comMap");
						String NRow = String.valueOf(dashObject[0][10]);
						String NCol = String.valueOf(dashObject[0][11]);
						System.out.println("NCol - "+NCol+" NRow - "+NRow);
						if (dashObject != null) {
						int count = 0;
							//loop: Dashboard rows
					for (int row = 0; row < Integer.parseInt(NRow); row++) {
				%>

				<tr valign="top" style="display:table-row">
					<%
					int addScol1=1;
							//dashboard cols 
							for (int col = 0; col < Integer.parseInt(NCol);) {
								
								String Srow = row + "";
								String SCol = col + "";
								String addSrow=row+"";
								String addScol="";
								if(addScol1<=col){
								 addScol=col+1+"";}
								else{
									addScol=SCol;	
								}
								
								
								int colSpanValue=1;
								int row1=Integer.parseInt(String.valueOf(dashObject[count][6]));
								int col1=Integer.parseInt(String.valueOf(dashObject[count][7]));
								int row11=0;
								int col11=0;
								if((count+1)!=dashObject.length){
								if(dashObject[count+1][6]!=null){
								row11=Integer.parseInt(String.valueOf(dashObject[count+1][6]));}
								
								if(dashObject[count+1][7]!=null){
								col11=Integer.parseInt(String.valueOf(dashObject[count+1][7]));}
								}
								
								if(col1==col && row1==row){
									System.out.println("no colspan");
									curcolSpanFlag=false;
								}
								else{
									System.out.println("ColSpan is required");
									curcolSpanFlag=true;
									colSpanValue=colSpanValue+1;
									
								}
								
								if(row11==row && col11==(col+1)){
									System.out.println("no colspan ");
									nextcolSpanFlag=false;
								}
								//if point to last row and coloum of current row
								else if((col+1)==Integer.parseInt(NCol)){
									System.out.println("no colspan ");
									nextcolSpanFlag=false;
								}
								else
								{	
									System.out.println("ColSpan is required");
									if(curcolSpanFlag==false){
									colSpanValue=colSpanValue+1;}
									nextcolSpanFlag=true;
								}
								if(Integer.parseInt(String.valueOf(dashObject[count][8]))>1){
									colSpanValue=Integer.parseInt(String.valueOf(dashObject[count][8]));
								}
								
								
								System.out.println("Colspan value - "+colSpanValue);
								String screenWidth=	(String)request.getAttribute("screenWidth");
								double iscreenWidth=Double.parseDouble(screenWidth);
								double calculatedWidht=(iscreenWidth*0.8 - (10*Integer.parseInt(NCol)))*(colSpanValue)/Integer.parseInt(NCol);
								System.out.println("DashBoard Width -"+calculatedWidht );
								
					%>
					<!-- Colspan Configuration -->
					
					<%
					System.out.println("Colspan - "+dashObject[count][8]);
					if(curcolSpanFlag==false &&nextcolSpanFlag==false ){
					%>
					<td colspan=<%=colSpanValue%> style="padding: 4px; "  ><!--  Start TD-->
					<%}
					else if(curcolSpanFlag==true &&nextcolSpanFlag==false) {
						%>
					<td colspan=<%=colSpanValue%> style="padding: 4px;" id="table<%=count%>">
						<%}
					else if(curcolSpanFlag==false &&nextcolSpanFlag==true) {
					 %>	
					<td colspan=<%=colSpanValue%> style="padding: 4px; "  id="table<%=count%>">
						<%}
					else if(curcolSpanFlag==true &&nextcolSpanFlag==true) {
						 %>	
						<td colspan=<%=colSpanValue%> style="padding: 4px;" id="table<%=count%>">
							<%}
					%>
					
					<!--	this table is used for giving header of each componet -->
					<table width="100%" border="1" align="center" cellpadding="0"id="dataTable" name="dataTable" cellspacing="0" style="border-collapse: collapse; border-color: #ccc">
						<tr
							style="background-color: #919DC1; color: #010101; height: 20px;">
							<!--  Component Header-->
							<td class="portalAppButtons" width="100%">
							<img src="../pages/CR/icons/analytics.png" ;	align="absmiddle" width="16" height="16"><strong> <%=String.valueOf(dashObject[count][3])%></strong>
							<!--<a href="#"onclick="sendMail('<%=String.valueOf(dashObject[count][0]) %>','<%=String.valueOf(dashObject[count][3])%>','<%=String.valueOf(dashObject[count][12])%>')"><img src="../pages/cssv2/icons/message24.png"; class="iconImage"  align="right" width="16" height="16" ></a>
											--><!--	String.valueOf(ObjDetails[count][4]) Table Headings						-->
							<a href="#" onclick="showComponent('<%=String.valueOf(dashObject[count][0]) %>','<%=String.valueOf(dashObject[count][5]) %>')" ><img src="../pages/images/icons/portal/insightone.png";	class="iconImage" align="right" width="14" height="14"  style="padding: 2px";></a></td>
						</tr>
						<tr>
							<!--  Start TR-->
						<td valign="top" style="padding: 4px;" height="100%"><!-- inner cell table	 -->
							<%
										//check whether object contain Data or Graph
										System.out.println("Data Object Containt for Graph ---"+dashObject[count][1]);
							if (String.valueOf(dashObject[count][1]).equals("D")) {
											//Object[] colinfo = (Object[])objMap.get(row + "-" + col);
								try{		
							%> 
				<%
 				System.out.println("Colinfo(colspan)==="+ dashObject[count][8]);
 				//int Crow = objCell.length;
 				//int Ccol = objCell[1].length;
 				Object[][] tabObj=(Object[][])comMap.get(dashObject[count][0]+"-"+dashObject[count][1]+"-"+dashObject[count][4]);
 				int Crow = tabObj.length;//for getting number of row from object
 				System.out.println("Number of rows in inner table"+Crow);
 				int Ccol = tabObj[1].length;//for getting number of coloums from object
 				System.out.println("Number of Cols in inner table"+Ccol);
 				System.out.println("Cell Rows - " + Crow+ " cell Cols" + Ccol);%> 
 				<!--                create innter cell table						-->
							<table width="100%" height="100%" border="1" cellpadding="0"
								id="dashTab" name="innerCellTab" cellspacing="0"
								style="border-collapse: collapse; border-color: #ccc">
								<%
									int dataLenght = tabObj.length;
									if (dataLenght > 15) {
										dataLenght = 15;//displaying only first 15 records
														}
									//rendering the table a=row b=cols
								   for (int a = 0; a < dataLenght; a++) {
									//for alternative color of dashBoard Table color
									   if (a % 2 == 0) {
								%>
								<tr valign="top" style="background-color: #E6EAE2">
									<%
									} else {
									%>
								
								<tr style="background-color: #FFFFFF">
									<%
												}
												for (int b = 0; b < tabObj[0].length; b++) {
													if (a == 0) {
									%>
									<th ALIGN="left" valign="top"
										style="padding-left: 4px; padding-right: 2px; background-color: ">
										<%=tabObj[a][b]%>
									</th>
									<%
									} else {
									%>

									<td ALIGN="Left"
										style="border: 1px solid #ccc; padding-left: 4px; padding-right: 2px;">
										<a href="#" onclick="getGraph('<%=String.valueOf(dashObject[count][0])%>','<%=String.valueOf(dashObject[count][14])%>','','<%=String.valueOf(tabObj[a][0]).trim()%>','0')"><%=tabObj[a][b]%>
										</a></td>
									<%
												}
												}//end icol
									%>
								</tr>
								<%
												}//end irow
												if (tabObj.length > 15) {
								%>
								<tr>
									<%request.setAttribute("tableObject",tabObj); %>
									
									<td align="right" colspan="<%= tabObj[0].length%>" >Total
									
									records: <%=tabObj.length%> &nbsp;&nbsp; <a href="#" onclick="showComponent('<%=String.valueOf(dashObject[count][0]) %>','<%=String.valueOf(dashObject[count][5]) %>')">View
									All >> </a></td>											  	
									
								</tr>

								<%
								}
								%>
							</table>
							<!-- End inner cell table--> <%
							
							
							}
					catch(Exception e){
								e.printStackTrace();
							}
 			}//end if
 			else if(String.valueOf(dashObject[count][4]).trim().equals("Pie2D")){
 				
 				String chartStr="";
 				chartStr=(String)comMap.get(dashObject[count][0]+"-"+dashObject[count][1]+"-"+dashObject[count][4]);
 				

 				 %> <!--             For Graph    		--> <%
 				 																			//w:400	//h:240
 				 		String chartCode = createChartHTML(	"../pages/charts/fusionCharts/Pie2D.swf","",String.valueOf(chartStr),"productSales",  String.valueOf(calculatedWidht), 240, false);
 				 		System.out.println("in createChart result"+ chartCode);
 				 %>
 											<div style="height: 100%; width:100% " align="center"><%=chartCode%></div>
 											<!--            End Of Graph         --> <%
 				 
 			}
 			else if(String.valueOf(dashObject[count][4]).trim().equals("Line")){
 				String chartStr="";
 				chartStr=(String)comMap.get(dashObject[count][0]+"-"+dashObject[count][1]+"-"+dashObject[count][4]);
 				String chartCode = createChartHTML(	"../pages/charts/fusionCharts/Line.swf","",String.valueOf(chartStr),"productSales",  String.valueOf(calculatedWidht), 240, false);
 				System.out.println("Chart Code Object  - "+chartCode);
 			%>
 			<div style="height: 100%; width:100% " align="center"><%=chartCode%></div>
 			<%
 			
 			}
 			else if(String.valueOf(dashObject[count][4]).trim().equals("MLine")){
 				String chartStr="";
 				chartStr=(String)comMap.get(dashObject[count][0]+"-"+dashObject[count][1]+"-"+dashObject[count][4]);
 				String chartCode = createChartHTML(	"../pages/charts/fusionCharts/MSLine.swf","",String.valueOf(chartStr),"productSales",  String.valueOf(calculatedWidht), 240, false);
 				System.out.println("Chart Code Object  - "+chartCode);
 			%>
 			<div style="height: 100%; width:100% " align="center"><%=chartCode%></div>
 			<%
 			
 			}
 			else if(String.valueOf(dashObject[count][4]).trim().equals("MCLine")){
 				String chartStr="";
 				chartStr=(String)comMap.get(dashObject[count][0]+"-"+dashObject[count][1]+"-"+dashObject[count][4]);
 				String chartCode = createChartHTML(	"../pages/charts/fusionCharts/MSColumnLine3D.swf","",String.valueOf(chartStr),"productSales",  String.valueOf(calculatedWidht), 240, false);
 				System.out.println("Chart Code Object  - "+chartCode);
 			%>
 			<div style="height: 100%; width:100% " align="center"><%=chartCode%></div>
 			<%
 			
 			}else if(String.valueOf(dashObject[count][4]).trim().equals("MLineScroll")){
 				
 				String chartStr="";
 				chartStr=(String)comMap.get(dashObject[count][0]+"-"+dashObject[count][1]+"-"+dashObject[count][4]);
 				

 				 %> <!--             For Graph    		--> <%
 				 																			//w:400	//h:240
 				 		String chartCode = createChartHTML(	"../pages/charts/fusionCharts/ScrollLine2D.swf","",String.valueOf(chartStr),"productSales",  String.valueOf(calculatedWidht), 240, false);
 				 		System.out.println("in createChart result"+ chartCode);
 				 %>
 											<div style="height: 100%; width:100% " align="center"><%=chartCode%></div>
 											<!--            End Of Graph         --> <%
 				 
 			}
 			
		else if(String.valueOf(dashObject[count][4]).trim().equals("barScroll")){
 				
 				String chartStr="";
 				chartStr=(String)comMap.get(dashObject[count][0]+"-"+dashObject[count][1]+"-"+dashObject[count][4]);
 				

 				 %> <!--             For Graph    		--> <%
 				 																			//w:400	//h:240
 				 		String chartCode = createChartHTML(	"../pages/charts/fusionCharts/ScrollColumn2D.swf","",String.valueOf(chartStr),"productSales",  String.valueOf(calculatedWidht), 240, false);
 				 		System.out.println("in createChart result"+ chartCode);
 				 %>
 											<div style="height: 100%; width:100% " align="center"><%=chartCode%></div>
 											<!--            End Of Graph         --> <%
 				 
 			}
		else if(String.valueOf(dashObject[count][4]).trim().equals("USGraph")){
				
				String chartStr="";
				chartStr=(String)comMap.get(dashObject[count][0]+"-"+dashObject[count][1]+"-"+dashObject[count][4]);
				System.out.println("US Graph String--- -  "+chartStr);				

				 %> <!--             For Graph    		--> <%
				 																			//w:400	//h:240
				 		String chartCode = createChartHTML(	"../pages/charts/fusionCharts/FCMap_USA.swf","",String.valueOf(chartStr),"productSales",  String.valueOf(calculatedWidht), 400, false);
				 		System.out.println("in USMapGraph result"+ chartCode);
				 %>
											<div style="height: 100%; width:100% " align="center"><%=chartCode%></div>
											<!--            End Of Graph         --> <%
				 
			}
 			
 			else {
 				String chartStr="";
 				chartStr=(String)comMap.get(dashObject[count][0]+"-"+dashObject[count][1]+"-"+dashObject[count][4]);
 					%> <!--             For Graph    		-->
 				 <%
	 				System.out.println("in createChart strXML======="	+ chartStr);
	 				Toolkit toolkit =  Toolkit.getDefaultToolkit ();
					Dimension dim = toolkit.getScreenSize();
					System.out.println("Width of Screen Size is "+dim.width+" pixels");																			
					System.out.println("Height of Screen Size is "+dim.height+" pixels");																	//w:400	//h:240
					String Str="<script>document.writeln(VScreenHight)</script>";
					
					//screenWidth = from bean;
					
					//graphWidth = (screenWidth*0.8 - (5*colSpanValue))*colSpanValue/Integer.parseInt(NCol);
					
	 				String chartCode = createChartHTML(	"../pages/charts/fusionCharts/MSColumn3D.swf","",String.valueOf(chartStr),"productSales", String.valueOf(calculatedWidht) ,240 , false);
	 				System.out.println("in createChart result"+ chartCode);
 				%>
				<div  id="fcexpDiv" style="height: 100%; width:100% " align="center"><%=chartCode%>	</div>

							
							
							
							<!--            End Of Graph         --> 
			<%
 				}//end else
 					
 					//String ChatCode1= createChartHTML(	"../pages/charts/fusionCharts/FCMap_USA.swf","",String.valueOf(USGraph),"productSales", String.valueOf(calculatedWidht) ,240 , false);
 			%> 
 			
 			<%
 			
 			//col=col+Integer.parseInt( String.valueof(colinfo[0]));
 			int Scolinfo = 0;
 			Scolinfo=Integer.parseInt(String.valueOf(dashObject[count][8]));
 			System.out.println("Colspan info------" + Integer.parseInt(String.valueOf(dashObject[count][8])));
 			//check colspan is greater than 1
 			if (Scolinfo > 1) {
 				col = col + Scolinfo;
 			}//end if 
 			else {
 				col++;
 			}//end else
 			
 			if(curcolSpanFlag || nextcolSpanFlag){
 				col++;
 			}
 			
 			count++;//increment counter
 			//GlobCount++;
 			%>
		</td>
		</tr>
		</table>
		</td>
			<%
				}
							//end of cols for loop
				addScol1=addScol1-1;
			%>

		</tr>
			<%
						}//end of rows for loop
						%>
						
						<% 
						}//end of if
						
			 			
			%>
		
 		
			</table>
			</td>
		</tr>
		<!--	row1 end -->
			<%
				}
				 catch (Exception e) {
				e.printStackTrace();
				}
		%>
	</table>
	</td>
	</tr>
	</table>
	<!--End of main table-->
	</s:form>
	</body>
	</html>