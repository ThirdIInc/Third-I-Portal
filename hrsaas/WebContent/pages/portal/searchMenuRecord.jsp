<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="org.paradyne.lib.Utility"%>

<html>
    <head>
<meta http-equiv="refresh"  content="search page" />
<link rel="stylesheet" type="text/css" title="default-theme"
	href="<%=request.getContextPath()%>/pages/common/css/commonCSS.jsp" />
</head>	
 

<%@ include file="/pages/common/commonValidations.jsp"%>

<%
String actionName ="";
try{
actionName=(String)request.getAttribute("actionName");
}catch(Exception e)
{
	e.printStackTrace();
}
 
%>

<table  height="100%" border="0" cellpadding="0" 
	cellspacing="0" style="border-collapse: collapse;"
	bordercolor="#111111" width="100%">
 
	<tr>
		<td valign="top" width="235"><%@include
			file="../common/leftEmployeePortalHome.jsp"%>
		</td>
		<td valign="top" width="100%">
		<table width="99%" border="0" cellspacing="0" cellpadding="0">
			<s:form action="EventData" id="paraFrm" theme="simple"
				name="paraFrmName">
				<!-- 
				<tr>
					<td colspan="2"><img src="../pages/portal/images/banner2.gif"  width="675"
						height="97" /></td>
				</tr>
			 -->
				<tr>
					<td height="5" colspan="2"></td>
				</tr>
				<tr>
					<td colspan="2"><iframe id="myframe" frameborder="0"
						style="vertical-align: top; float: left; border: 0px solid;"
						allowtransparency="yes"
						src="<%=request.getContextPath()%>/<%=actionName%>"
						scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
						name="main" width="100%" height="1000"> </iframe></td>
				</tr>
			</s:form>
		</table>
		</td>
	</tr>
</table>

</html>





