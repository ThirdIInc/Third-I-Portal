<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- Show usage; Used in Header --%>
<tiles:importAttribute name="title" scope="request"/>
<html>
    <head>
    <title><tiles:getAsString name="title"/></title>    
    </head>
<body topmargin="0" leftmargin="0">
<link rel="stylesheet" href="/msc/pages/common/hrms.css" type="text/css">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111">

</table>
</body>       
</html>