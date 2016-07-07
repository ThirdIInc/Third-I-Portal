<%@ taglib prefix="s" uri="/struts-tags"%>

<table>
    <tr>
		<td width="100%" colspan="6" align="center"><b>Leave Report</b></td>
	</tr>
	
	<tr>
	<td>&nbsp;</td>
	</tr>
	
	
	

</table>


<table class="bodyTable" width="100%" border="1">

	

	<tr>
		<td class="bodyCell" width="10%"><b>Leave Code</td>
		<td class="bodyCell" width="20%"><b>Leave Type</td>
		<td class="bodyCell" width="20%"><b>Leave Abbreviation</td>
		
		
	

	</tr>
	<s:iterator value="leaveList" status="stat">
		<tr>
			<td class="bodyCell" width="35%"><s:property value="leaveCode" /></td>
			<td class="bodyCell" width="35%"><s:property value="leaveName" /></td>
			<td class="bodyCell" width="35%"><s:property value="leaveAbbr" /></td>
			
		</tr>
	</s:iterator>
	

	
</table>
