<%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#FFFFFF">
	<tr>
		<td width="25%" class="formth">Employee Name</td>
		<td width="25%" class="formth">Application Date</td>
		<td width="25%" class="formth">From Date</td>
		<td width="25%" class="formth">To Date</td>

	</tr>
	<s:iterator value="leaveList">
		<tr>

			<td class="sortabletd" width="25%"><a href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/leaves/LeaveApproval_callstatus.action"><s:property value="employeeName" /></a></td>
			<td class="sortabletd" width="25%"><s:property value="applDate" /></td>
			<td class="sortabletd" width="25%"><s:property value="LeaveFrmDate" /></td>
			<td class="sortabletd" width="25%"><s:property value="LeaveToDate" /></td>

			<td width="25%" class="sortabletd"><a href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/leaves/LeaveApproval_callstatus.action"><s:property value="employeeName" /></a></td>
			<td width="25%" class="sortabletd"><s:property value="applDate" /></td>
			<td width="25%" class="sortabletd"><s:property value="LeaveFrmDate" /></td>
			<td width="25%" class="sortabletd"><s:property value="LeaveToDate" /></td>




		</tr>

	</s:iterator>

</table>

<script>


</script>
