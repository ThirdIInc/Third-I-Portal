<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>Menu </title>
	<script type="text/javascript" src="../pages/common/dtree/dtree.js"></script>
</head>
<body topmargin="0" leftmargin="0">
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111">
<tr>
<td>
<tiles:insertAttribute name="header"/>
</td></tr>
<tr height="100%" WIDTH="100%">
<td ALIGN="left" valign="top">
<tiles:insertAttribute name="body"/>
</td>
</tr>
<tr><td>
<tiles:insertAttribute name="footer"/>
</td></tr>
</table>
</body>
</html>