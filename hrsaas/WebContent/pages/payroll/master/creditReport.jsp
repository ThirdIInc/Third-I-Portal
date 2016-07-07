<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>Credit Report</b></td>
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
	
		<td class="bodyCell" width="10%"><b>Credit Code</td>
		<td class="bodyCell" width="40%"><b>Credit Name</td>
		<td class="bodyCell" width="40%"><b>Credit Abbr</td>
		<!--<td class="bodyCell" width="20%"><b>Credit Type</td>
		<td class="bodyCell" width="20%"><b>Credit Maxcap</td>
		<td class="bodyCell" width="20%"><b>Credit Minfloor</td>
		<td class="bodyCell" width="20%"><b>Credit Payflag</td>
	-->
	</tr>
	<%int i=1; %>
	<s:iterator value="creditArray" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="creditCode" /></td>
			<td class="bodyCell" width="40%"><s:property value="creditName" /></td>
			<td class="bodyCell" width="40%"><s:property value="creditAbbr" /></td>
			<!--<td class="bodyCell" width="20%"><s:property value="creditType" /></td>
			<td class="bodyCell" width="20%"><s:property value="creditmaxcap" /></td>
			<td class="bodyCell" width="20%"><s:property value="creditminimumfloor" /></td>
			<td class="bodyCell" width="20%"><s:property value="creditpayflag" /></td>

		--></tr>
		<%i++; %>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>	
</table>

