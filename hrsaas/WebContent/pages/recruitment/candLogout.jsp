<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Untitled Document</title>
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
</head>

<body topmargin="40%">
<%
String infoId = (String) request.getAttribute("poolName");
%>
<%
String forceLogout = (String) request.getAttribute("forceLogout");
%>
<table width="100%" height="550" border="0" cellpadding="8"
	cellspacing="8">
	<tr>
		<td width="100%" valign="middle">
		<table width="36%" border="0" align="center" cellpadding="4"
			cellspacing="4" class="formbg">
			<tr valign="middle">
				<td width="31%" valign="middle">
				<div align="center"><img src="../pages/images/Logout.gif"
					width="48" height="48" /></div>
				</td>
				<td width="69%" valign="middle">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="formhead">
					<tr>
						<td height="25">
						<div align="center" class="bluetxt">
						<div align="left"><strong> <%
 if (forceLogout != null && forceLogout.length() > 0) {
 %>
						Your session has been Expired <%
 } else {
 %> Thank you for using PeoplePower.
 <% }
 %>
						</strong></div>
						</div>
						</td>
					</tr>
					<tr>
						<td height="25" nowrap="nowrap">
						<div align="center" class="bluetxt">
						<div align="left"><strong>For security reasons please close this window.</strong></div>
						</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<!--<tr>
				<td colspan="2" valign="middle">
				<div align="right"><a
					href="http://<%=request.getServerName()%>">Login</a>&nbsp;&nbsp; <a
					href="javascript:closeWindow();">Close Window</a></div>
				</td>
			</tr>
		--></table>
		</td>
	</tr>
</table>
<script language="javascript" type="text/javascript">


window.close();


function closeWindow() {
if(
window.confirm('Do you want to close this window ?')){
window.open('','_parent','');
window.close();
}




}

</script>
</body>
</html>
