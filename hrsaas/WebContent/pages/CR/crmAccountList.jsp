<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/charts/FusionCharts.jsp"%>

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



<s:form action="CRMAccountInfo" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="advtName" />
	<s:hidden name="checkUploadProfile" />
	<s:hidden name="myPage" id="myPage" />
	<table  width="100%">
		<!--Final Table -->
		
		<!--<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Account Information</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		--><tr>
			<td width="100%">  
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr height="28">
				<td >
					<table width="99%" align="center" border="0" cellpadding="0"
						cellspacing="0">
				<tr>
				<s:hidden name="accountCode"/><s:hidden name="accountID"/><s:hidden name="accountLogo"/>
				<s:hidden name="custCode"/>
					<td  nowrap="nowrap" align="left" colspan="2">
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
						<!--<s:select
						name="reportType" id="paraFrm_reportType"
						list="#{'':'--Select--','I':'Account ID','N':'Account Name'}" /> </select>
					 --><s:hidden name="accountName"  /> 
					  
											<a  title="Back to list" onclick="callBacktolist('<%=request.getContextPath()%>/cr/PerformanceMetrics_input.action');" 
						href="#" class="mainheader" >Back to list</a> |
						<a href="#?" class="link"  onclick="callsF9(500,325,'CRMAccountInfo_f9Account.action');">Change Account : </a>
											
						 <img src="../pages/mypage/images/search.gif"  class="iconImage" height="18" 
											align="absmiddle" width="18" title="Select Account" 
											onclick="callsF9(500,325,'CRMAccountInfo_f9Account.action');"> 
											
											
						
						</td>
				</tr>
			</table>
			</td>
		</tr>
			
			
				<tr>
							<td colspan="4" style="height: 1px; background-color: #B5B5B5;  "> 
			 				</td>
						</tr>
				
		
			
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
										//System.out.println("PI::"+grapValues);
										if(grapValues!=null&&grapValues.length()>0){
											//grapValues=createChartHTML("../pages/charts/fusionCharts/Pie3D.swf",    "", grapValues, "productSales", "400", 200, false);
											%>
						
									
					<!-- START Script Block for Chart TopEmployees -->
					<div id='TopEmployeesDiv' align='center' >
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
										var chart_TopEmployees = new FusionCharts( { "swfUrl" : "../pages/CR/MSColumn3D.swf", "width" : "400", "height" : "225", "renderAt" : "column3DDaily", "dataFormat" : "xml", "id" : "TopEmployees12", "wmode" : "opaque", "dataSource" : "<%=column3DCharts%>" } ).render();
										// --></script>	
									<!-- END Script Block for Chart TopEmployees -->
											<% 
										}
										//System.out.println("grapValues::"+column3DCharts);
										%>
										
										</td>									
									</tr>								
								</table>
							</td>
						</tr>
						
						
					<tr>
							<td colspan="4" style="height: 1px; background-color: #B5B5B5;  "> 
			 				</td>
						</tr>
						
						
						<tr>
							<td style="padding: 3px;"><strong class="text_head"></strong></td>
						<td width="93%" class="txt" nowrap="nowrap"><strong class="text_head">Generate Reports </strong></td>
						<td width="3%" valign="middle" nowrap="nowrap"><strong class="text_head">Last Run On</strong> </td>						
						<td nowrap="nowrap" align="center"> <strong class="text_head" >Preview</strong></td>
					</tr>
					
					<tr>
						<td colspan="4" style="height: 1px; background-color: #EBEBEB;  "> 
			 			</td>
					</tr>
					<tr>
						<td colspan="4" style="height: 1px; background-color: #EBEBEB;  "> 
			 			</td>
					</tr>
					<tr>
						<td colspan="4" style="height: 1px; background-color: #EBEBEB;  "> 
			 			</td>
					</tr>
					<tr>
						<td colspan="4" style="height: 1px; background-color: #EBEBEB;  "> 
			 			</td>
					</tr>
						<% int d = 1;%>
						<s:if test="reportList">
						<s:iterator value="reportList">
							<tr >
								<s:hidden value="listIcon" />
								<s:hidden value="listReportDesc" />
								<s:hidden name="listReportCode" />
								<s:hidden name="listAccountCode" />
								<s:hidden name="listGroupName" />
								<s:hidden name="listLastRun" />
								<s:hidden name="listAccountID" />
								<s:hidden name="hCustomerCode" />
							<td width="5%" valign="top">
								<img src="../pages/CR/icons/<s:property value="listIcon" />" width="30"
								height="30" />
							</td>
							<td  valign="top">
								
								<a  title="Home" onClick="callJobNameDetailsWindow('CRMAccountInfo_generateReport.action?reportCode=<s:property value='listReportCode'/>&accountvalue=<s:property value='listAccountCode'/>&listGroupNameValue=<s:property value='listGroupName'/>&listAccountIDValue=<s:property value='listAccountID'/>&listhCustomerCode=<s:property value='hCustomerCode'/>&&app_random=<%=java.lang.Math.random()%>',event)"
						href="#" class="servicelink"  ><s:property value="listReportName" /></a>
								<br>
								<s:property value="listReportDesc" />
							</td>
							
							
							<td width="10%"  valign="middle" class="sortableTD" nowrap="nowrap">
							<s:property value="listLastRun" />
							</td>
								
									
								<td width="20%"  class="sortableTD" align="center">
								<a
								href="javascript:void(0);"
								onClick="callJobNameDetailsWindow('CRMAccountInfo_generateReport.action?reportCode=<s:property value='listReportCode'/>&accountvalue=<s:property value='listAccountCode'/>&listGroupNameValue=<s:property value='listGroupName'/>&listAccountIDValue=<s:property value='listAccountID'/>&listhCustomerCode=<s:property value='hCustomerCode'/>&&app_random=<%=java.lang.Math.random()%>',event)"
								class="contlink"
								title="Generate"
								target="main">
								<img src="../pages/CR/images/preview.png"  />
								</a>
								
								</td>
								
							</tr>
						<tr>
						<td bgcolor="#ffffff" colspan="4">&nbsp;</td>
						</tr>
						
						<tr>
							<td colspan="4" style="height: 1px; background-color: #EBEBEB;  "> 
			 				</td>
						</tr>
													
						</s:iterator>
						</s:if>
						<s:else>
							<tr>
								<td width="100%" colspan="4" align="center"><font
								color="red"><b>No data to display</b></font></td>
							</tr>
						</s:else>
					
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
	 	////alert(action);
		callPageDisplay(action,'500','300','false',e,140);	 
	}
	 function callBacktolist(actionlink){
	     	
	 	   document.getElementById("paraFrm").action=actionlink;
	 	   	document.getElementById('paraFrm').target = "_self";
	 	    document.getElementById("paraFrm").submit();
	     }

	
</script>