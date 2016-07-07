<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="7" align="center"><b><u>Menu
		Management Report</u></b></td>
	</tr>

	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;</td>
	</tr>

	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="7" align="center"><b>Menu List</b></td>
	</tr>
	
	<tr>
		<td class="bodyCell" width="10%"><b>Sr. No.</td>
		<td class="bodyCell" width="25%"><b>Menu Name</td>
		<td class="bodyCell" width="10%"><b>Menu Link</td>
		<td class="bodyCell" width="10%"><b>Menu Parent Code</td>
		<td class="bodyCell" width="10%"><b>Menu Parent Name</td>
		<td class="bodyCell" width="10%"><b>Total Path</td>
		<td class="bodyCell" width="10%"><b>Menu Target</td>
		<td class="bodyCell" width="10%"><b>Menu ToolTip Message</td>
		<td class="bodyCell" width="10%"><b>Menu Placement</td>
		<td class="bodyCell" width="10%"><b>Menu TabOrder</td>

	</tr>
	<%
	int i = 1;
	%>
	<s:iterator value="menuList" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><%=i++%></td>
			<td class="bodyCell" width="25%"><s:property value="menuName" /></td>
			<td class="bodyCell" width="10%"><s:property value="menuLink" /></td>
			<td class="bodyCell" width="10%"><s:property value="menuParntId" /></td>
			<td class="bodyCell" width="10%"><s:property value="menuParValue" /></td>
			<td class="bodyCell" width="10%"><s:property value="totalpath" /></td>
			
		<td class="bodyCell" width="10%"><s:property value="target" /></td>
			<td class="bodyCell" width="10%"><s:property value="message" /></td>
			<td class="bodyCell" width="10%"><s:property value="placement" /></td>
			<td class="bodyCell" width="10%"><s:property value="order" /></td>

		</tr>

	</s:iterator>
	
</table>