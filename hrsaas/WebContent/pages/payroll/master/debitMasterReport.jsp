<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>Debit Head Master
		Report</b></td>
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
		<td class="bodyCell" width="10%"><b>Debit Abbr</td>
	</tr>
	<s:iterator value="debitList" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="debitCode" /></td>
			<td class="bodyCell" width="20%"><s:property value="debitName" /></td>
			<td class="bodyCell" width="10%"><s:property value="debitAbbr" /></td>
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>

	
</table>

<tr>
    	<td width="100%" colspan="4" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
