 <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<%
	String[][] knowList =null;
			try {
				knowList= (String[][]) request
			.getAttribute("knowList");
	%>
	<%
				if (knowList != null && knowList.length > 0) {
				for (int i = 0; ((i < knowList.length) && i < 5); i++) {
	%>
	<tr>
		<td height="20" width="3%"><img align="absmiddle"
			src="../pages/common/css/default/images/dot.gif" /> 
			</td>
			<td height="20" width="97%">
			<a
			href="javascript:void(0);" class="contlink"
			onclick="callMore('<%=request.getContextPath()%>/portal/EmployeePortal_showKnowledge.action?knowCode=<%=knowList[i][0]%>');"> <%
 			try {
 			out.print(knowList[i][0].substring(0, 40) + "..");
 		} catch (Exception ex) {
 			out.println(knowList[i][0]);
 		}
 %> </a></td>
	</tr>
	<%
			}
			}
			if (knowList != null && knowList.length > 0) {
				for (int i = 0; i < 5 - knowList.length; i++) {
	%>
	<tr>
		<td height="20" width="3%"></td>
		<td height="20" width="97%"></td>
	</tr>
	<%
			}
			}

		} catch (Exception e) {

		}
	%>
	<%if (knowList != null && knowList.length > 5){ %>
	<tr>
		<td>&nbsp;</td>
		<td height="30" valign="bottom"><label>
		<div align="right"><img
			onclick="callMore('<%=request.getContextPath()%>/portal/EmployeePortal_showMoreInfo.action?dashletCode=3');"
			src="../pages/common/css/default/images/more.gif" width="42"
			height="13" border="1" style="border-color: #CCCCCC; cursor: pointer;" /></div>
		</label></td>
	</tr>

<%} %>

</table>
 