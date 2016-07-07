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
	    <td class="bodyCell" width="10%"><b>Employee Id</td>
	    <td class="bodyCell" width="10%"><b>Employee Code</td>
	    <td class="bodyCell" width="10%"><b>Employee Name</td>
		<td class="bodyCell" width="10%"><b>Employee Department</td>
		<td class="bodyCell" width="10%"><b>Employee Center</td>
		<td class="bodyCell" width="10%"><b>Employee Trade</td>
		<td class="bodyCell" width="10%"><b>Employee Rank</td>
		
		
	</tr>
	<s:iterator value="creditArray" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="empId" /></td>
			<td class="bodyCell" width="20%"><s:property value="empCredit" /></td>
			<td class="bodyCell" width="20%"><s:property value="empName" /></td>
			<td class="bodyCell" width="20%"><s:property value="empCenter" /></td>
			<td class="bodyCell" width="20%"><s:property value="empTrade" /></td>
			<td class="bodyCell" width="20%"><s:property value="empRank" /></td>
			<td class="bodyCell" width="20%"><s:property value="empAmount" /></td>
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>
<tr>
    	<td width="100%" colspan="3" align="center"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>

	
</table>

