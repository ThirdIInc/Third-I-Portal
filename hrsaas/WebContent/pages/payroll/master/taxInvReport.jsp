<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>Tax Investment Report</b></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>

	<tr>
		<td class="bodyCell" width="10%"><b>Investment Code</td>
		<td class="bodyCell" width="10%"><b>Investment Name</td>
		<td class="bodyCell" width="20%"><b>Investment Limit1</td>
		<td class="bodyCell" width="20%"><b>Investment Limit2</td>
		<td class="bodyCell" width="20%"><b>Investment Order</td>
		<td class="bodyCell" width="20%"><b>Investment Chapter</td>
		<td class="bodyCell" width="20%"><b>Investment Section</td>
		<td class="bodyCell" width="20%"><b>Investment Type</td>
	
	</tr>
	<s:iterator value="taxInv.taxInvArray" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="invCode" /></td>
			<td class="bodyCell" width="10%"><s:property value="invName" /></td>
			<td class="bodyCell" width="20%"><s:property value="invLimit1" /></td>
			<td class="bodyCell" width="20%"><s:property value="invLimit2" /></td>
			<td class="bodyCell" width="20%"><s:property value="invOrder" /></td>
			<td class="bodyCell" width="20%"><s:property value="invChapter" /></td>
			<td class="bodyCell" width="20%"><s:property value="invSection" /></td>
			<td class="bodyCell" width="20%"><s:property value="invType" /></td>
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>
<tr>
    	<td width="100%" colspan="4" align="center"><s:submit cssClass="pagebutton" value="Print"/></td>
</tr>	
</table>