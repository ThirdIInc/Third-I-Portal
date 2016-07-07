<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="EmployeePortal" id="paraFrm" theme="simple"
	name="employeePortalForm">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<%
			Object[][] eventDataObj = null;
				try {													
					eventDataObj = (Object[][]) request
						.getAttribute("eventDataObj");
		%>
		<%
			if (eventDataObj != null && eventDataObj.length > 0) {
					for (int i = 0; ((i < eventDataObj.length) && i < 5); i++) {
		%>
		<tr>
			<td height="20" width="3%"><img align="absmiddle" src="../pages/common/css/default/images/dot.gif" /></td>
			<td height="20" width="97%"><a href="javascript:void(0);"
				class="contlink"
				onClick="callPage('<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=null&eventCode=<%=eventDataObj[i][0]%>&yearValue=<%=eventDataObj[i][3]%>');">
			<%=eventDataObj[i][1]%> </a></td>
		</tr>
		<%
				}
			}
			if (eventDataObj != null && eventDataObj.length > 0) {
				for (int i = 0; i < 5 - eventDataObj.length; i++) {
		%>
		<tr>
			<td height="20" width="3%"></td>
			<td height="20" width="97%"></td>
		</tr>
		<%
			}
		  }
		 } catch (Exception e) {}
		%>
		<% if (eventDataObj != null && eventDataObj.length > 5) { %>
		<tr>
			<td>&nbsp;</td>
			<td height="28" valign="bottom"><label>
			 <div align="right"><img
				onclick="callMore('<%=request.getContextPath()%>/portal/EmployeePortal_showMoreInfo.action?dashletCode=2');"
				src="../pages/common/css/default/images/more.gif" width="42"
				height="13" border="1"
				style="border-color: #CCCCCC; cursor: pointer;" /></div>
			</label></td>
		</tr>
		<%} %>
	</table>
</s:form>
<script>	
function callMore(id){
  try{
		document.getElementById('paraFrm').action=id;
		document.getElementById('paraFrm').submit();
	 }
	catch(e){

	}	
}

function callPage(actionName){ 
	try{
		document.getElementById('paraFrm').action = actionName;	 
	    document.getElementById('paraFrm').submit();
	}
	catch(e){
	}
}
</script>
