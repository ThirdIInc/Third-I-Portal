
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.businessobjects.crystalreports.viewer.core.l"%>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<%
				String[][] links = null;
				if (request.getAttribute("links") != null)
					links = (String[][]) request.getAttribute("links");
				if (links != null && links.length > 0) {
					for (int i = 0; i < links.length; i++) {
			%>
			<tr>
				<td class="td_bottom_border"><a
					href="javascript:reportViewer('http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=links[i][2] %>');"><img
					src="../pages/images/report.gif" class="iconImage" height="15"
					width="15">&nbsp;<%=links[i][1]%></a></td>
			</tr>
			<%
				}
				}
			%>
</table>


