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
		<td class="bodyCell" width="20%"><b>Debit Priority</td>
		<td class="bodyCell" width="20%"><b>Is Loan Flag</td>
		<td class="bodyCell" width="20%"><b>Debit Balance Carry Forward Flag</td>
		<td class="bodyCell" width="20%"><b>Debit Exempted in Tax</td>
		<td class="bodyCell" width="20%"><b>Debit Exempted Under Section</td>
		<td class="bodyCell" width="20%"><b>Is Table Recovery </td>
	

	</tr>
	<s:iterator value="debitArray" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="debitCode" /></td>
			<td class="bodyCell" width="20%"><s:property value="debitName" /></td>
			<td class="bodyCell" width="20%"><s:property value="debitAbbr" /></td>
			<td class="bodyCell" width="20%"><s:property value="debitPriority" /></td>
			<td class="bodyCell" width="20%"><s:property value="DebitforLoan" /></td>
			<td class="bodyCell" width="20%"><s:property value="DebitBalFlag" /></td>
			<td class="bodyCell" width="20%"><s:property value="Debitexempt" /></td>
			<td class="bodyCell" width="20%"><s:property value="exemptSectionNo" /></td>
			<td class="bodyCell" width="20%"><s:property value="tableRecover" /></td>

		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>

	
</table>

