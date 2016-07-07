<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>Shift Report</b></td>
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
		<td class="bodyCell" width="10%"><b>Shift ID</td>
		<td class="bodyCell" width="20%"><b>Shift Name</td>
		<td class="bodyCell" width="10%"><b>Shift Start time1</td>
		<td class="bodyCell" width="10%"><b>Shift End time1</td>
		<td class="bodyCell" width="10%"><b>Shift Start time2</td>
		<td class="bodyCell" width="10%"><b>Shift End time2</td>
		
		<td class="bodyCell" width="10%"><b>Shift OT Start Time</td>
		<td class="bodyCell" width="10%"><b>Shift Work Hours</td>
		<td class="bodyCell" width="10%"><b>Shift Night Flag</td>
		<td class="bodyCell" width="10%"><b>Shift Break Flag</td>
		<td class="bodyCell" width="10%"><b>Shift Start Time</td>
		<td class="bodyCell" width="10%"><b>Shift End Time</td>
		<td class="bodyCell" width="10%"><b>Shift Other Time</td>

	</tr>
	<s:iterator value="shiftList" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="shiftID" /></td>
			<td class="bodyCell" width="20%"><s:property value="shiftName" /></td>
			<td class="bodyCell" width="10%"><s:property value="shiftStrTime1" /></td>
			<td class="bodyCell" width="10%"><s:property value="shiftEndTime1" /></td>
			<td class="bodyCell" width="10%"><s:property value="shiftStrTime2" /></td>
			<td class="bodyCell" width="10%"><s:property value="shiftEndTime2" /></td>
			<td class="bodyCell" width="10%"><s:property value="shiftOTStrTime" /></td>
			<td class="bodyCell" width="10%"><s:property value="shiftWrHours" /></td>
			<td class="bodyCell" width="10%"><s:property value="shiftNtFlag" /></td>
			<td class="bodyCell" width="10%"><s:property value="shiftBrFlag" /></td>
			<td class="bodyCell" width="10%"><s:property value="shiftStrTime" /></td>
			<td class="bodyCell" width="10%"><s:property value="shiftEndTime" /></td>
			<td class="bodyCell" width="10%"><s:property value="shiftOthTime" /></td>
			
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>

	
</table>

<tr>
    	<td width="100%" colspan="4" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
