<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>


<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type">
<title>Home Page</title>
<link type="text/css" rel="stylesheet" href="../pages/kiosk/images/skin.css">
</head>
<body>
<s:form action="KioskHome" method="post" validate="true" theme="simple"
	id="paraFrm" name="form" target="_top">
	
	<%
		Object loginData[][] = null;
		try {
			loginData = (Object[][]) request.getAttribute("loginData");
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
	
	<%
		String pool = "";
		String user = "";
		pool = (String) request.getParameter("session_pool");
		user = (String) request.getParameter("loginName");
		
	%>

	<table width="99%" border="0">
		<tbody>
		<tr>
		<td align="right"><input type="button" value="Logout" class="button" name="Submit" style="width:80px;height:30px;background-color:white;color:red;font: bold 16px Arial" onclick="return callLogout();"></td>
		</tr>
			<tr>
				<th scope="col"><img height="128" width="116"
					src="../pages/kiosk/images/umc.gif"></th>
			</tr>
			<tr>
				<th scope="col">
				<table height="240" width="69%" cellspacing="0" cellpadding="0"
					border="0" id="Table_01" align="center">
					<tbody>
						<tr>
							<td width="23"><img height="22" width="23" alt=""
								src="../pages/kiosk/images/Untitled-1_01.gif"></td>
							<td width="699" background="../pages/kiosk/images/Untitled-1_02.gif"><img
								height="22" width="362" alt="" src="../pages/kiosk/images/Untitled-1_02.gif"></td>
							<td width="40"><img height="22" width="40" alt=""
								src="../pages/kiosk/images/Untitled-1_03.gif"></td>
						</tr>
						<tr>
							<td><img height="187" width="23" alt=""
								src="../pages/kiosk/images/Untitled-1_04.gif"></td>
							<td valign="top" background="../pages/kiosk/images/Untitled-1_05.gif">
							<table width="100%" border="0">
								<tbody>
									<tr>
										<th width="27%" scope="col">
										<table height="99" width="100%" border="0" class="bg">
											<tbody>
												<tr>
													<th height="125" scope="col">
													<table height="125" width="100%" border="0"
														bgcolor="#cccccc">
														<tbody>
															<tr>
																<td align=center>
			<%
			String str = (String) request.getAttribute("photo");
		
			%> <%if(str.equals("NoImage.jpg"))   { %> <img
				src="../pages/images/employee/NoImage.jpg" width="120px" height="130px"
				align="middle" /> <% } else  { %> <img
				src="../pages/images/<%=session.getAttribute("session_pool") %>/employee/<%=str %>"
				height="110" /> <%} %>
			</td>
															</tr>
														</tbody>
													</table>
													</th>
												</tr>
												<tr>
													<th scope="col">Welcome <br>
													<%=String.valueOf(loginData[0][8])%></th>
												</tr>
											</tbody>
										</table>
										</th>
										<th width="73%" scope="col">
										<table width="100%" cellspacing="0" cellpadding="0" border="0">
											<tbody>
												<tr>
													<th width="33%" scope="col">
													<table width="100%" cellspacing="2" cellpadding="0"
														border="0">
														<tbody>
															<tr>
																<th scope="col"><img height="51" width="56"
																	src="../pages/kiosk/images/b1.gif"  onclick=" return callLeaveReportAction();"></th>
															</tr>
															<tr>
																<th scope="row" class="txt">Leave Monthly Report</th>
															</tr>
														</tbody>
													</table>
													</th>
													<th width="33%" scope="col">
													<table width="100%" cellspacing="0" cellpadding="0"
														border="0">
														<tbody>
															<tr>
																<th scope="col"><img height="52" width="56"
																	src="../pages/kiosk/images/b2.gif" onclick=" return callPFLoanEMIReportAction();"></th>
															</tr>
															<tr>
																<th scope="row" class="txt">PF Loan EMI Report</th>
															</tr>
														</tbody>
													</table>
													</th>
													<th width="33%" scope="col">
													<table width="100%" cellspacing="0" cellpadding="0"
														border="0">
														<tbody>
															<tr>
																<th scope="col"><img height="52" width="56"
																	src="../pages/kiosk/images/b3.gif" onclick=" return callPFBalanceReportAction();"></th>
															</tr>
															<tr>
																<th scope="row" class="txt">PF Balance Report</th>
															</tr>
														</tbody>
													</table>
													</th>
												</tr>
												<tr>
													<th width="33%" scope="row"></th>
													<td width="33%"></td>
													<td width="33%"></td>
												</tr>
												<tr>
													<th width="33%" scope="row">
													<table width="100%" cellspacing="0" cellpadding="0"
														border="0">
														<tbody>
															<tr>
																<th scope="col"><img height="51" width="56"
																	src="../pages/kiosk/images/b4.gif" onclick=" return callPFSlipAction();"></th>
															</tr>
															<tr>
																<th scope="row" class="txt">PF Slip</th>
															</tr>
														</tbody>
													</table>
													</th>
													<td width="33%">
													<table width="100%" cellspacing="0" cellpadding="0"
														border="0">
														<tbody>
															<tr>
																<th scope="col"><img height="52" width="56"
																	src="../pages/kiosk/images/b5.gif" onclick=" return callSalarySlipAction();"></th>
															</tr>
															<tr>
																<th scope="row" class="txt">Salary Slip</th>
															</tr>
														</tbody>
													</table>
													</td>
													<td width="33%">
													<table width="100%" cellspacing="0" cellpadding="0"
														border="0">
														<tbody>
															<tr>
																<th scope="col"><img height="52" width="56"
																	src="../pages/kiosk/images/b6.gif" onclick=" return callLICPolicyReportAction();"></th>
															</tr>
															<tr>
																<th scope="row" class="txt">LIC Policy</th>
															</tr>
														</tbody>
													</table>
													</td>
												</tr>
											</tbody>
										</table>
										</th>
									</tr>
								</tbody>
							</table>
							</td>
							<td><img height="187" width="40" alt=""
								src="../pages/kiosk/images/Untitled-1_06.gif"></td>
						</tr>
						<tr>
							<td><img height="31" width="23" alt=""
								src="../pages/kiosk/images/Untitled-1_07.gif"></td>
							<td background="../pages/kiosk/images/Untitled-1_08.gif"><img height="31"
								width="362" alt="" src="../pages/kiosk/images/Untitled-1_08.gif"></td>
							<td><img height="31" width="40" alt=""
								src="../pages/kiosk/images/Untitled-1_09.gif"></td>
						</tr>
					</tbody>
				</table>
				</th>
			</tr>
			<tr>
				<th scope="col"><img height="60" width="231"
					src="../pages/kiosk/images/pp.gif"></th>
			</tr>
	<s:hidden name="loginName" value="<%=user%>" />
	<s:hidden name="infoId" value="<%=pool%>" />
		</tbody>
	</table>
</s:form>
</body>
</html>


<script>

 

function callLeaveReportAction()
{
			document.getElementById('paraFrm').action='http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/kiosk/KioskLeaveMonthlyReport_report.action';
			document.getElementById('paraFrm').target = '_blank';
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = '';
}

function callPFLoanEMIReportAction()
{
			document.getElementById('paraFrm').action='http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/kiosk/KioskPFLoanEMIReport_pfLoanEMIReport.action';
			document.getElementById('paraFrm').target = '_blank';
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = '';
}

function callPFBalanceReportAction()
{
			document.getElementById('paraFrm').action='http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/kiosk/KioskPFBalanceReport_pfBalanceReport.action';
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
}

function callSalarySlipAction()
{
			document.getElementById('paraFrm').action = "KioskHome_salarySlip.action";
  			document.getElementById('paraFrm').submit();
}
function callLICPolicyReportAction(){
			
			document.getElementById('paraFrm').action='http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/kiosk/KioskLICPolicyReport_report.action';
			document.getElementById('paraFrm').target = '_blank';
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = '';

}

function callPFSlipAction()
{			
			document.getElementById('paraFrm').action = "KioskHome_pfSlip.action";
  			document.getElementById('paraFrm').submit();
}

function callLogout(){
			var loginName=document.getElementById('paraFrm_loginName').value;
			var infoId=document.getElementById('paraFrm_infoId').value;
			
			document.getElementById('paraFrm').action = "KioskLogin_logout.action?loginName="+loginName+'&infoIds='+infoId;
			
  			document.getElementById('paraFrm').submit();

}
</script>