<%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="25%" class="formth">Employee Name</td>
	    <td width="25%" class="formth">From Date</td>
		<td width="25%" class="formth">To Date</td>
		<td width="25%" class="formth">Reason</td>

	</tr>
	<s:iterator value="empLeaveList">
		<tr>

			<td class="sortabletd" width="25%"><s:property value="eName" /></td>
			<td class="sortabletd" width="25%"><s:property value="LeaveFromDate" /></td>
			<td class="sortabletd" width="25%"><s:property value="LevToDate" /></td>
			<td class="sortabletd" width="25%"><s:property value="reasoninfo" /></td>

			<td width="25%" class="sortabletd"><s:property value="eName" /></td>
			<td width="25%" class="sortabletd"><s:property value="LeaveFromDate" /></td>
			<td width="25%" class="sortabletd"><s:property value="LevToDate" /></td>
			<td width="25%" class="sortabletd"><s:property value="reasoninfo" /></td>




		</tr>

	</s:iterator>

</table>

<script>


</script>
