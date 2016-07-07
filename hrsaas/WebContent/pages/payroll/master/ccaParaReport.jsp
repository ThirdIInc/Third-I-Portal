<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>CCA Parameter Report</b></td>
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
		<td class="bodyCell" width="10%"><b>CCA Code</td>
		<td class="bodyCell" width="20%"><b>Equi Salary From </td>
		<td class="bodyCell" width="20%"><b>Equi Salary To</td>
		<td class="bodyCell" width="20%"><b>Cca Amount</td>
	
	</tr>
	<s:iterator value="ccaArray" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="ccaCode" /></td>
			<td class="bodyCell" width="20%"><s:property value="equiSalFrom" /></td>
			<td class="bodyCell" width="20%"><s:property value="equiSalTo" /></td>
			<td class="bodyCell" width="20%"><s:property value="ccaAmt" /></td>
			
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>
<!--<tr>
    	<td width="100%" colspan="4" align="center"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>

	
--></table>

