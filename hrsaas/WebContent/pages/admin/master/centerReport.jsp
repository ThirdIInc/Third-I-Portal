<%@ taglib prefix="s" uri="/struts-tags"%>
<table class="bodyTable" width="100%">
	<tr>
		<td width="100%" colspan="4" align="center"><b>Branch Report</b></td>
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
		<td class="bodyCell" width="25%"><b>Branch Code </td>
		<td class="bodyCell" width="25%"><b>Branch Name</td>
		<td class="bodyCell" width="25%"><b>Branch Desc</td>
		<td class="bodyCell" width="25%"><b>Branch Department </td>

	</tr>
	<s:iterator value="centerList" status="stat">
		<tr>
			<td class="bodyCell" width="25%"><s:property value="centerID" /></td>
			<td class="bodyCell" width="25%"><s:property value="centerName" /></td>
			<td class="bodyCell" width="25%"><s:property value="centerDesc" /></td>
			<td class="bodyCell" width="25%"><s:property value="deptName" /></td>
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>
</table>

<tr>
	<td width="100%" colspan="4" align="right"><s:submit
		cssClass="pagebutton" value="Print" /></td>
</tr>
