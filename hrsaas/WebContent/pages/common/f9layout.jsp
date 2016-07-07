 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
   "http://www.w3.org/TR/html4/strict.dtd">

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%-- Show usage; Used in Header --%>
<tiles:importAttribute name="title" scope="request"/>

<html>
	<head>
    	<title><tiles:getAsString name="title"/></title>    
    	 <%@ include file="commonCSS.jsp" %>
    </head>
	<body class="main">
		 
		<table width="100%" height="100%" style="border-collapse: collapse" bordercolor="#111111">
			<tr>
				<td><tiles:insertAttribute name="header"/></td>
			</tr>
			<tr height="100%" WIDTH="100%">
				<td><tiles:insertAttribute name="body"/></td>
			</tr>
			<tr>
				<td><tiles:insertAttribute name="footer"/></td></tr>
		</table>
	</body>
</html>