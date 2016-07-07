<%@ taglib prefix="s" uri="/struts-tags"%>
<%
			String[][] portalAppsObj = (String[][]) request
			.getAttribute("portalD1Obj");
%>
<table width="160" border="0" cellspacing="0" cellpadding="0"
	align="center">
<tr>

<%
		if (portalAppsObj != null && portalAppsObj.length > 0) {
		%>

		<td width="15%" valign="top">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td><img src="../pages/images/icons/portal/myapps.png" /></td>
			</tr>
			<%
				if (portalAppsObj != null && portalAppsObj.length > 0) {
						for (int i = 0; i < portalAppsObj.length; i++) {
			%>
			<tr>
				<td class="portalAppButtons" width="100%"><img
					src="../pages/images/icons/portal/<%= portalAppsObj[i][2]%>";
				align="absmiddle" width="16" height="16" />&nbsp;&nbsp;<a
					href="<%= portalAppsObj[i][3]%>" class="contlink"
					title=<%=portalAppsObj[i][1]%> target="_blank" onclick=""><font
					color="black"><b><%=portalAppsObj[i][1]%></b></font></a></td>
			</tr>
			<%
				        }
				}
			%>
			
			<tr></tr>
		</table>
		</td>
		<%
			} else {

			}
		%>
</tr>
</table>
