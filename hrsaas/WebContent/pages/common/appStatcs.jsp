<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String[][] deptAppData = null;
	Object [][] phaseData = null;
	try {
		deptAppData = (String[][]) request
		.getAttribute("deptApprisalData");
		
		phaseData = (Object[][]) request
		.getAttribute("phaseData");
	} catch (Exception e) {
		System.out.println("Error Occured in JSp While get Attribute "
		+ e);
		deptAppData = new String[0][0];
	}
%>
<table width="100%">
	
	<tr>
		<td colspan="2" width="100%" valign="top">
		
		<table width="100%">
			<tr>
				<td width="50%" class="formth"><b>Department</b></td>
				<%
					if (phaseData != null & phaseData.length > 0) {
					for (int i = 0; i < phaseData.length; i++) {
				%>
					
					<td width="25%" class="formth"><b><%=phaseData[i][1] %></b></td>
			
				<%
				}
				}
			   %>
			
			</tr>
			<%
					if (deptAppData != null & deptAppData.length > 0) {
					for (int i = 0; i < deptAppData.length; i++) {
			%>
			<tr>
				<td width="17%" class="sortabletd"><%= deptAppData[i][0]%></td>
				<%
					for (int p = 0; p < phaseData.length; p++) {
				%>
					<td width="16%" class="sortabletd"><%= deptAppData[i][1+p]%></td>
				<%
					}
			    %>
				
			</tr>

	<%
			}
		}
	%>

		</table>
	
		</td>
	</tr>

</table>
