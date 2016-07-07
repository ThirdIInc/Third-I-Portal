<%@ taglib prefix="s" uri="/struts-tags"%>
<table class="bodyTable" width="100%">
	<tr>
		<td width="100%" colspan="6" align="center"><b>Bank Report</b></td>
	</tr>
	<tr>
		<td width="100%" colspan="6" >&nbsp;</td>
	<tr>
	<tr>
		<td width="100%" colspan="6" >&nbsp;</td>
	<tr>
	<tr>
		<td width="100%" colspan="6" >&nbsp;</td>
	<tr>
	<tr>
		<td class="bodyCell" width="15%"><b>Bank MICR Code</b></td>
		<td class="bodyCell" width="15%"><b>Bank Name</b></td>
		<td class="bodyCell" width="10%"><b>Branch Code</b></td>
		<td class="bodyCell" width="15%"><b>Branch Name</b></td>
		<td class="bodyCell" width="25%"><b>Branch Address</b></td>
		<td class="bodyCell" width="20%"><b>Branch City</b></td>
		
	</tr>

	<s:iterator value="bankList" status="stat">
		<tr>
			<td class="bodyCell" width="15%"><s:property value="bankMicrCode" /></td>
			<td class="bodyCell" width="15%"><s:property value="bankName" /></td>
			<td class="bodyCell" width="10%"><s:property value="branchCode" /></td>
			<td class="bodyCell" width="15%"><s:property value="branchName" /></td>
			<td class="bodyCell" width="25%"><s:property
				value="branchAddress" /></td>
			<td class="bodyCell" width="20%"><s:property value="branchCity" /></td>
			
		</tr>

	</s:iterator>
	<tr>
		<td width="100%" colspan="6">&nbsp;</td>
	<tr>
</table>
<tr>
	<td width="100%" colspan="6" align="right"><s:submit cssClass="pagebutton"
		value="print" /></td>
</tr>
