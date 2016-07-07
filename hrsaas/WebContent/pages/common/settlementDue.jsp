<%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" class="sortable">
	<tr>
		<td>
		<table width="100%" border="0" align="center" cellpadding="2"
			cellspacing="1" class="sortable">
			<tr>
				<td width="100%"  colspan="4"><strong class="text_head">Settlement Due</strong></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td width="25%" class="formth" colspan="1">Name</td>
		<td width="25%" class="formth" align="left" colspan="1">Branch</td>
		<td width="25%" class="formth" colspan="1">Due Date</td>
		<td width="25%" class="formth" colspan="1">Pending Since Days</td>
	</tr>
	<s:iterator value="list" status="stat">
		<tr>
			<td  class="sortableTD"><s:property value="dueEmpName" /></td>
			<td  nowrap="nowrap" class="sortableTD" ><s:property value="dueEmpbranch" /></td>
			<td  nowrap="nowrap" class="sortableTD" ><s:property value="dueEmpDate" /></td>
			<td  class="sortableTD"><a href="#" onclick="viewSettlementDue()">
			<s:property value="pendingSince" /></a></td>
		</tr>
	</s:iterator>

</table>


