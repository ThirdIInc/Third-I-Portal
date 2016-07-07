
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
   "http://www.w3.org/TR/html4/strict.dtd">

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="commonValidations.jsp" %>

<%-- Show usage; Used in Header --%>
<tiles:importAttribute name="title" scope="request" />
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<%@ include file="commonCSS.jsp"%>

<link href="../pages/portal/images/style.css" rel="stylesheet"
	type="text/css" />
</head>
<table width="100%" height="100%" border="0" cellpadding="0"
	cellspacing="0" style="border-collapse: collapse;"
	bordercolor="#111111">
	<tr>
		<td><s:hidden name="actionMessage" /> <tiles:insertAttribute
			name="header" /></td>
	</tr>
	<tr height="100%" WIDTH="100%">
		<td ALIGN="center" valign="top"><s:actionerror /> <s:actionmessage />
		<s:fielderror /> <tiles:insertAttribute name="body" /></td>

	</tr>
	<tr>
		<td><tiles:insertAttribute name="footer" /></td>
	</tr>
</table>
<script>

function callMessage() {
	var message = document.getElementById("actionMessage").value;
	if(message !="" ){
		alert (message);
		}
}


callMessage();
function callsF9(width,height,action) {
	win=window.open('','win','top=150,left=120,width='+width+',height='+height+',scrollbars=no,status=yes,resizable=yes');
	document.getElementById("paraFrm").target="win";
	document.getElementById("paraFrm").action=action;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="_top";
}
</script>