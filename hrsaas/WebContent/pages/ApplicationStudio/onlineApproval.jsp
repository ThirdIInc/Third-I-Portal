<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" title="default-theme"
	href="../pages/common/css/first/first.css" />
	
	<link rel="alternate stylesheet" type="text/css" media="screen" title="second-theme"
	href="../pages/common/css/second/second.css" />
	
	<link rel="alternate stylesheet" type="text/css" media="screen" title="third-theme"
	href="../pages/common/css/third/third.css" />
		<link rel="alternate stylesheet" type="text/css" media="screen" title="fourth-theme"
	href="../pages/common/css/fourth/fourth.css" />
	
		<link rel="alternate stylesheet" type="text/css" media="screen" title="fifth-theme"
	href="../pages/common/css/fifth/fifth.css" />
	<script type="text/javascript"
	src="../pages/common/include/javascript/styleswitch.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
String comanyName = (String) request.getAttribute("comanyName");
String pool_name="abc";
%>
	
	<title><%=comanyName%> Online Approval</title>

<script type="text/javascript">

</script>
</head>
<body>


<table width="100%" height="100%" border="0" cellpadding="8"
	cellspacing="8" align="center">
	<tr valign="middle">
		<td width="100%" valign="middle">
		<table width="70%" border="0" align="center" cellpadding="4"
			cellspacing="4" class="formbg">
			<tr>
				<td
					style="font-family: arial, sans-serif; font-weight: normal; font-size: 12px; text-align: center;">
				<%
				String result = (String) request.getAttribute("result");
				%> <%
 				out.println(result);
 				%><br>
				Thanks for using PeoplePower.</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>