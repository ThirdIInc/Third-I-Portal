<%@ taglib prefix="s" uri="/struts-tags"%>
<!--  Table started   -->
<table class="bodyTable" width="100%">
	<tr>
		<td width="100%" colspan="4" align="center"><b>Client Report</b></td>
	</tr>
	<tr>
		<td width="100%" colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td class="bodyCell" width="25%"><b>First Name</td>
		<td class="bodyCell" width="25%"><b>Last Name</td>
		<td class="bodyCell" width="25%"><b>Email Id </td>
		<td class="bodyCell" width="25%"><b>Is Active</td>

	</tr>
	<!--   Client User List Started  -->
	<s:iterator value="clientUserList" status="stat">
		<tr>
			<td class="bodyCell" width="25%"><s:property value="ittFirstName" /></td>
			<td class="bodyCell" width="25%"><s:property value="ittLastName" /></td>
			<td class="bodyCell" width="25%"><s:property value="ittEmailId" /></td>
			<td class="bodyCell" width="25%"><s:property value="ittIsActive" /></td>
		</tr>
	</s:iterator>
	<!--   Client User List Ended  -->
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>
	<!--  Table ended   -->
<tr>
	<td width="100%" colspan="4" align="right"><s:submit
		cssClass="pagebutton" value="Print" /></td>
</tr>
