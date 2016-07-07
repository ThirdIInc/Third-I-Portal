<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table align="center">
<tr style="height: 154px">
<td>
<center>
<strong><s:property value="mailSendMsg"/></strong> 
</center>
<td>
<tr style="height: 100px">
<td>
<center>
<input type="button" value="Closed Window" onclick="closedWindow()">
</center>
</td>
</tr>
<tr style="height: 175px">
</tr>
</table>


</body>
<script type="text/javascript">
function closedWindow(){
window.close();
}

</script>

</html>
