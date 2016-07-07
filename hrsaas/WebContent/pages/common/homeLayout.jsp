 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
   "http://www.w3.org/TR/html4/strict.dtd">

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="commonValidations.jsp" %>


<tiles:importAttribute name="title" scope="request" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7; IE=8" />

<%@ include file="commonCSS.jsp"%>

<link href="../pages/portal/images/style.css" rel="stylesheet"
	type="text/css" />

<title><tiles:getAsString name="title" /></title>
</head>
<body topmargin="0" leftmargin="0" class="main" onload="parent.alertsize(document.body.scrollHeight);">



<table width="100%" border="0" cellpadding="0" cellspacing="0"
	align="center">
	<tr>
		<td width="100%" style="padding-left: 3px;padding-right:3px;"><tiles:insertAttribute name="body" /></td>
	</tr>


</table>
</body>

<script>

callMessage();

function callMessage() {
	
		var message = document.getElementById("actionMessage").value;
		
	if(trim(message)!="" ) {
		alert(message);
	}
}

</script>