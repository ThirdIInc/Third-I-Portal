<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- Show usage; Used in Header --%>
<tiles:importAttribute name="title" scope="request"/>
<html>
    <head>
    <title><tiles:getAsString name="title"/></title>    
    </head>
<body>
<link rel="stylesheet" href="../pages/common/hrms.css" type="text/css">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111">
<tr>
<td>
<tiles:insertAttribute name="body"/>
</td>
</tr>
</table>
</body>
</html>