<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css" title="default-theme"
	href="../pages/common/css/first/first.css" />

<link rel="alternate stylesheet" type="text/css" media="screen"
	title="second-theme" href="../pages/common/css/second/second.css" />

<link rel="alternate stylesheet" type="text/css" media="screen"
	title="third-theme" href="../pages/common/css/third/third.css" />
<link rel="alternate stylesheet" type="text/css" media="screen"
	title="fourth-theme" href="../pages/common/css/fourth/fourth.css" />

<link rel="alternate stylesheet" type="text/css" media="screen"
	title="fifth-theme" href="../pages/common/css/fifth/fifth.css" />
<script type="text/javascript"
	src="../pages/common/include/javascript/styleswitch.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Online Approval Confirmation</title>
<%
	String urlApproval = (String) request.getAttribute("urlApproval");
	String approverId = (String) request.getAttribute("approverId");
	System.out.println("approverId  " + approverId);
	String notValid = (String) request.getAttribute("notValid");
%>



<script type="text/javascript">


function callConfirm(){
try{
	var approverId=document.getElementById('approverId').value;
	if(approverId!=''){
	//alert(approverId);
		var username=document.getElementById('username').value;
		var password=document.getElementById('password').value;
		if(username==''){
		alert("Please enter Username");
		return false;
		}
		if(password==''){
		alert("Please enter Password");
		return false;
		}

}

}
catch(e){

}
document.getElementById('paraFrm').target="_self";
document.getElementById('paraFrm').action="OnlineApprRejc_approveReject.action?"+"<%=urlApproval%>";
document.getElementById('paraFrm').submit();
document.getElementById('paraFrm').target="_self";	

}


</script>
</head>
<body>
<style>
TD {
	font-family: arial, sans-serif;
	font-weight: normal;
	font-size: 12px;
	text-align: center;
}
</style>
<form method="post" id="paraFrm" name="OnlineApprRejc">
<table width="100%" height="100%" border="0" cellpadding="8"
	cellspacing="8" align="center">
	<tr valign="middle">
		<td width="100%" valign="middle">
		<table width="70%" border="0" align="center" cellpadding="4"
			cellspacing="4" class="formbg">
			<%
			if (approverId != null && approverId.length() > 0) {
			%>

			<tr>
				<td align="center">
				<table width="40%" cellpadding="1" cellspacing="0" align="center">
					<%
					if (notValid != null && notValid.length() > 0) {
					%>
					<tr>
						<td colspan="2" width="100%" align="center"><font color="red"
							size="2px" face="Arial"> <%=notValid%> </font></td>
					</tr>
					<%
					}
					%>
					<tr>

						<td colspan="1">Username:</td>
						<td colspan="1" align="left"><input type="text" align="left"
							name="username" id="username"><input type="hidden"
							name="approverId" id="approverId" value="<%=approverId %>">
						</td>
					</tr>
					<tr>
						<td colspan="1">Password:</td>
						<td colspan="1" align="left"><input type="password"
							align="left" name="password" id="password"></td>
					</tr>
					<tr>
						<td>
						<td align="center"><input type="button" name=" Login"
							value=" Login " id="login" onclick="return callConfirm();"
							class="token" /></td>
					</tr>

				</table>
				</td>
			</tr>
			<script>
			document.getElementById('username').focus();
			</script>
			<%
			} else {
			%>

			<td colspan="2">Do you really want to process this request? <br>
			<br>
			<table>
				<tr>
					<td>Comments :</td>
					<td><textarea rows="5" cols="50" name="commentText"></textarea></td>
				</tr>
				<tr align="center">
					<td align="center"><input type="button" name=" Yes"
						value=" Yes " id="yes" onclick="return callConfirm();"
						class="token" />&nbsp;&nbsp;<input type="button" value="   No  "
						onclick="javascript:window.close();" class="token"></td>
				</tr>

			</table>


			<%
			}
			%>
			</td>
			</tr>
			<%
			if (approverId != null && approverId.length() > 0) {
			%>
			<script>
			document.getElementById('yes').focus();
			</script>
			<%
			}
			%>
			<tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>