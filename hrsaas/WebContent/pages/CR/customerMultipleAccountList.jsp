<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/charts/FusionCharts.jsp"%>
<STYLE type=text/css>
a:hover {
	COLOR: #FF0000;
	text-decoration: underline;
}
</STYLE>
<script LANGUAGE="Javascript" SRC="../pages/CR/FusionCharts.js"></script>		
	<script LANGUAGE="JavaScript">		
	
		//FusionCharts.debugMode._enableFirebugLite('firebug-lite.js');
	function getCookie(c_name)
	{
	var i,x,y,ARRcookies=document.cookie.split(";");
	for (i=0;i<ARRcookies.length;i++)
	{
	  x=ARRcookies[i].substr(0,ARRcookies[i].indexOf("="));
	  y=ARRcookies[i].substr(ARRcookies[i].indexOf("=")+1);
	  x=x.replace(/^\s+|\s+$/g,"");
	  if (x==c_name)
		{
		return unescape(y);
		}
	  }
	}
	
	function setCookieRenderer(type)
	{
		document.cookie = 'DEMO_RENDERER=' + type.toLowerCase();
	}
	
	if(getCookie('DEMO_RENDERER'))
	 FusionCharts.setCurrentRenderer(getCookie('DEMO_RENDERER').toLowerCase());
	else
	{
		 setCookieRenderer('javascript');
		  FusionCharts.setCurrentRenderer(getCookie('DEMO_RENDERER').toLowerCase());
	}
	
			
	//We keep flags to check whether the charts have loaded successfully.
	//By default, we assume them to false. When each chart loads, it calls
	//a JavaScript function FC_Rendered, in which we'll update the flags
	var empChartLoaded=false;
	var catChartLoaded=false;
	var prodChartLoaded=false;
	
	
	
	
	</script>
	
<s:form action="CustomerReportLogin" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="advtName" />
	<s:hidden name="checkUploadProfile" />

			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<!--<tr height="28">
				<td width="100%">
					<table width="100%" align="center" border="0" cellpadding="0"
						cellspacing="0">    
				<tr>
				<s:hidden name="accountCode"/><s:hidden name="accountID"/><s:hidden name="accountLogo"/>
					<td  nowrap="nowrap" align="left" colspan="2" valign="absmiddle">
					<s:if test="accountLogo!=''">
					<img src="../pages/CR/logo/<s:property value="accountLogo" />" 
								height="25" align="absmiddle" border="0"/>
					</s:if>					
					&nbsp;&nbsp;			
					<strong ><font size="3" color="#969696">   
							<s:property value="accountName" />
							</font></strong></td>
				
					<td colspan="3" width="85%"  nowrap="nowrap"
						align="right" id="ctrlShow">
						<s:select
						name="reportType" id="paraFrm_reportType"
						list="#{'':'--Select--','I':'Account ID','N':'Account Name'}" /> </select>
					 <s:hidden name="accountName"  /> 					
						Change Account: <img src="../pages/mypage/images/search.gif"  class="iconImage" height="18" 
											align="absmiddle" width="18" title="Select the department" 
											onclick="callsF9(500,325,'CustomerAccountInfo_f9Account.action');">
											
						
						</td>
				</tr>
			</table>
			</td>
		</tr>
			
			
				--><tr>
							<td colspan="4" style="height: 1px; background-color: #B5B5B5;  "> 
			 				</td>
						</tr>
				
				
		<s:hidden name="loginName"/> 
			
				<tr>
					<td >
					<table width="100%" border="0" cellpadding="0" cellspacing="0" >
						
						
						
					
						<tr>
							<td colspan="2" valign="top">
								<table width="100%" border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td> 
										<%
										String grapValues=(String)request.getAttribute("graphString");
										
										//grapValues="<chart caption='Deffered call statistics' palette='4' pieRadius='100' slicingDistance='5' decimals='0' animation='1' showPercentValues='1' use3DLighting='1' enableSmartLabels='1' enableRotation='1'  bgAngle='360' showBorder='0' startingAngle='70' skipOverlapLabels='1'><set label='Customer\nReschedule' value='85'  isSliced='1'/><set label='Customer\nNot Available' value='3'/><set label='Engineer\nNot Available' value='0'/><set label='Part Not Available' value='6'/></chart>";
										System.out.println("PI::"+grapValues);
										if(grapValues!=null&&grapValues.length()>0){
											//grapValues=createChartHTML("../pages/charts/fusionCharts/Pie3D.swf",    "", grapValues, "productSales", "400", 200, false);
											%>
						
									
					<!-- START Script Block for Chart TopEmployees -->
					<div id='TopEmployeesDiv' align='center'>
						Chart.
						
					</div>		
					<script type="text/javascript"><!--	
						//Instantiate the Chart	
						var chart_TopEmployees = new FusionCharts( { "swfUrl" : "../pages/CR/Pie3D.swf", "width" : "400", "height" : "225", "renderAt" : "TopEmployeesDiv", "dataFormat" : "xml", "id" : "TopEmployees", "wmode" : "opaque", "dataSource" : "<%=grapValues%>" } ).render();
						// --></script>	
					<!-- END Script Block for Chart TopEmployees -->
					
					
											<% 
										}
										//System.out.println("grapValues::"+grapValues);
										%>
																				
										</td>										
									</tr>								
								</table>
							</td>
							<td colspan="4" valign="top">
								<table width="100%" border="0" cellpadding="0" cellspacing="0" >
									<tr>
										<td> 
										
										<%
										String column3DCharts=(String)request.getAttribute("column3DCharts");
										//grapValues="<chart palette='4' pieRadius='100' slicingDistance='5' decimals='0' enableSmartLabels='1' enableRotation='0' bgColor='99CCFF,FFFFFF' bgAlpha='40,100' bgRatio='0,100' bgAngle='360' showBorder='1' startingAngle='70'><set label='BACKD_CUST_RESCH' value='500'/><set label='BACKD_CUST_NA' value='500'/><set label='BACKD_ENGG_NA' value='18'/><set label='BACKD_PART_NA' value='1450' isSliced='1'/></chart>";
										System.out.println("COLUMN3DCHARTS::"+column3DCharts);
										if(column3DCharts!=null&&column3DCharts.length()>0){
											//column3DCharts=createChartHTML("../pages/charts/fusionCharts/MSColumn3D.swf",    "", column3DCharts, "productSales", "400", 200, false);
											%>
											
											
											<div id='column3DDaily' align='center'>
												Chart.
											</div>		
									<script type="text/javascript"><!--	
										//Instantiate the Chart	
										var chart_TopEmployees = new FusionCharts( { "swfUrl" : "../pages/CR/MSColumn3D.swf", "width" : "400", "height" : "225", "renderAt" : "column3DDaily", "dataFormat" : "xml", "id" : "TopEmployees", "wmode" : "opaque", "dataSource" : "<%=column3DCharts%>" } ).render();
										// --></script>	
									<!-- END Script Block for Chart TopEmployees -->
											<% 
										}
										//System.out.println("grapValues::"+column3DCharts);
										%>
										
										<!-- START Script Block for Chart TopEmployees -->
					
										
										</td>									
									</tr>								
								</table>
							</td>
						</tr>
						<tr>
						
						<td class="txt" nowrap="nowrap"><strong class="text_head">List of Account </strong></td>
						
					</tr>
					<tr>
					<td colspan="2">
					<table width="100%" border="0" class="formbg">
						<tr>
							<td class="formtext">
							<table width="100%" border="0">
								<tr>
								<td  class="formth" align="center">Sr.No.
								</td>
									<td class="formth" align="center" width="05%"><label
										name="srno" id="srno" ondblclick="callShowDiv(this);">Account ID</label>
									</td>
									<td class="formth" align="center" width="15%"><label
										name="acc.id" id="acc.id" ondblclick="callShowDiv(this);">Account Name</label>
									</td>
									
									
									<td class="formth" align="center" width="5%">View Report</td>

								</tr>
								<s:if test="reportList">

									<%int count=0; %>
									<%int d = 0;%>
									
									<s:iterator value="reportList">
										
											<tr>
												<td width="05%" align="center" class="sortableTD">
												<%=++count%></td>
											
												<td class="sortableTD" align="left" width="10%"><s:property
													value="accountID" /></td>

												<td class="sortableTD" align="left" width="20%"><s:property
													value="accountName" />
													 <s:hidden name="customerCode"/>
													<s:hidden name="AccountCode"/>
													<s:hidden name="customerUserEmpId"/> 
													
											 		</td>
												
												
												<td class="sortableTD" width="10%" align="center"><a
													href="#?" class="link"
													onclick="javascript:callForViewAccount('<s:property value="customerCode"/>');">View
												</a></td>


											</tr>
										
									</s:iterator>

									<%
										d++;
									%>
								</s:if>

								<s:else>
									<td align="center" colspan="6" nowrap="nowrap"><font
										color="red">There is no data to display</font></td>
								</s:else>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>	
					
					</table>
					</td>
				</tr>
				
			
			
				
				
			</table>

	<!--End of Final Table -->
</s:form>
<script>
 						
 		function callAccountReports(action)
		{
		 	//alert(action) ;
		 	var reportCode=document.getElementById('paraFrm_accountCode').value;
		 	//action=action+'?accountCode='+reportCode;
		 	//alert(action);
			//document.getElementById('myframe').style.width='100%';
			document.getElementById("paraFrm").target='main';
			document.getElementById('paraFrm').action=action;
			document.getElementById("paraFrm").submit();
		}				
 						
	 function callJobNameDetailsWindow(action,e) {
	 	//alert(action);
		callPageDisplay(action,'500','300','false',e);	 
	}

	function callForViewAccount(accountId){
     	/// alert(accountId);
     ///alert((document.getElementById('paraFrm_loginName').value).replace(/,$/,''));
     var reportCode=document.getElementById('paraFrm_loginName').value;
     
     
 	   	document.getElementById("paraFrm").action='<%=request.getContextPath()%>/cr/CustomerAccountInfo_getReportList.action?accountId='+accountId+'&reportCode='+reportCode;
 	   	document.getElementById("paraFrm").target="main";
 	   //	document.getElementById('paraFrm').target = "_self";
 	    document.getElementById("paraFrm").submit();
     }




</script>