<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="formbg" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>Employee Bonus Report </b></td>
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
		<td class="bodyCell" width="10%"><b>Employee Token</td>
		<td class="bodyCell" width="10%"><b>Employee Name</td>
		<td class="bodyCell" width="10%"><b>Bonus Days Declared </td>
		<td class="bodyCell" width="20%"><b>Bonus Amount</td>
		
	
	</tr>
	<s:iterator value="bonusArray" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="cEmpToken" /></td>
			<td class="bodyCell" width="10%"><s:property value="cEmpName" /></td>
			<td class="bodyCell" width="10%"><s:property value="cEmpBonusDays" /></td>
			<td class="bodyCell" width="20%"><s:property value="cAmount" /></td>
		
			
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>
<</table>