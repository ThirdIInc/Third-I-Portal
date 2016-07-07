<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- Show usage; Used in Header --%>
<tiles:importAttribute name="title" scope="request"/>
<html>
    <head>
    <title><tiles:getAsString name="title"/></title>
    <link rel="shortcut icon" href="../pages/images/favicon.ico">    
    </head>
<body leftmargin="0" topmargin="0" >
<link rel="stylesheet" href="../pages/common/css/default/default.css" type="text/css">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111">
<tr>
<td >
<s:hidden name="actionMessage"/>
<tiles:insertAttribute name="header"/>
</td></tr>
<tr height="100%" WIDTH="100%">
<td valign="top">
<tiles:insertAttribute name="body"/>
</td>
</tr>
<tr><td >
<tiles:insertAttribute name="footer"/>
</td></tr>
</table>
<script>


function callMessage() {
	var message = document.getElementById("actionMessage").value;
	if(message !="" ){
		alert (message);
		}
}


callMessage();
</script>
</body>       
</html>