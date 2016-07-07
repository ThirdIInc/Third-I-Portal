<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>
<meta content="text/html; charset=iso-8859-1" http-equiv="Content-Type">
<title>PF Balance Page</title>
<link type="text/css" rel="stylesheet"
	href="../pages/kiosk/images/skin.css">
</head>
<body>

<s:form action="KioskLogin" method="post" validate="true" theme="simple"
	id="paraFrm" name="form" target="_top">

	<%
	
		Object pfBalanceData[][] = null;
		try {
			pfBalanceData = (Object[][]) request
			.getAttribute("pfBalanceData");
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
							<td width="699"
								background="../pages/kiosk/images/Untitled-1_02.gif"><img
								height="22" width="362" alt=""
								src="../pages/kiosk/images/Untitled-1_02.gif"></td>
							<td width="40"><img height="22" width="40" alt=""
								src="../pages/kiosk/images/Untitled-1_03.gif"></td>
						</tr>
						<tr>
							<td><img height="187" width="23" alt=""
								src="../pages/kiosk/images/Untitled-1_04.gif"></td>
							<td valign="top"
								background="../pages/kiosk/images/Untitled-1_05.gif">
							<table width="100%" border="0">
								<tbody>
									<!-- data to display -->
									<tr>
										<td>
										<table border="0" width="50%" height="100" align="center">
											<tr>
												<% if (pfBalanceData != null && pfBalanceData.length > 0) {%>
												<td style="font: bold 15px Arial">Employee Name:</td>
												
												<td><%=String.valueOf(pfBalanceData[0][0])%></td>

											</tr>
											<tr>
												<td style="font: bold 15px Arial">PF Balance :</td>
												<td><%=Math.round(Double.parseDouble(String
									.valueOf(pfBalanceData[0][1])))%></td>

											</tr>
											<%
											} else {
											%>

											<tr>
												<td align="center" colspan="2" style="font: bold 15px Arial" >No Data to display</td>
											</tr>

											<%
											}
											%>
											<%
												} catch (Exception e) {

												}
											%>


											</tr>
											</td>
											</tr>
										</table>
										<table align="center" border="0">
											<tr>
												<th height="40" scope="col"><input type="button" value="Back" style="width: 80px; height: 23px; background-color: #003366; color: white; font: bold 15px Arial"
														;name="Submit" 
													onclick="return callBack();"></th>
											</tr>
										</table>
								</tbody>
							</table>
							</td>
							<td><img height="187" width="40" alt=""
								src="../pages/kiosk/images/Untitled-1_06.gif"></td>
						</tr>
						<tr>
							<td><img height="31" width="23" alt=""
								src="../pages/kiosk/images/Untitled-1_07.gif"></td>
							<td background="../pages/kiosk/images/Untitled-1_08.gif"><img
								height="31" width="362" alt=""
								src="../pages/kiosk/images/Untitled-1_08.gif"></td>
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

		</tbody>
	</table>
	 
		<s:hidden name="loginName" />
		<s:hidden name="infoId" />
	
</s:form>
</body>
</html>

<script>
function callBack() {
 	var empName=document.getElementById('paraFrm_loginName').value;
 	var infoId=document.getElementById('paraFrm_infoId').value;
	document.getElementById('paraFrm').action ="KioskLogin_returnHome.action?empName="+empName+'&infoIds='+infoId;
  	document.getElementById('paraFrm').submit();
}

function callLogout(){
			var loginName=document.getElementById('paraFrm_loginName').value;
			document.getElementById('paraFrm').action = "KioskLogin_logout.action?loginName="+loginName;
  			document.getElementById('paraFrm').submit();

}
</script>