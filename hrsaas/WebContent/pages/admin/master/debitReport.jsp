<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>Debit Report</b></td>
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
		<td class="bodyCell" width="10%"><b>Debit Code</td>
		<td class="bodyCell" width="20%"><b>Debit Name</td>
		<td class="bodyCell" width="20%"><b>Debit Abbr</td>
		<td class="bodyCell" width="20%"><b>Debit Type</td>
		<td class="bodyCell" width="20%"><b>Debit Maxcap</td>
		<td class="bodyCell" width="20%"><b>Debit Minfloor</td>
		<td class="bodyCell" width="20%"><b>Debit Roundoff</td>
		<td class="bodyCell" width="20%"><b>Debit Payflag</td>
	

	</tr>
	<s:iterator value="debitArray" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="debitCode" /></td>
			<td class="bodyCell" width="20%"><s:property value="debitName" /></td>
			<td class="bodyCell" width="20%"><s:property value="debitAbbr" /></td>
			<td class="bodyCell" width="20%"><s:property value="debitType" /></td>
			<td class="bodyCell" width="20%"><s:property value="debitmaxcap" /></td>
			<td class="bodyCell" width="20%"><s:property value="debitminimumfloor" /></td>
			<td class="bodyCell" width="20%"><s:property value="debitpolicy" /></td>
			<td class="bodyCell" width="20%"><s:property value="debitpayflag" /></td>

		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>
	
</table>

