<%@ taglib prefix="s" uri="/struts-tags"%>
<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" class="sortable">
	<tr>
		<td  width="25%" class="formth">Suggested By</td>
		<td width="12%"  class="formth" align="left"><b>Date</td>
		<td width="63%" class="formth">Suggestion Subject</td>
	</tr>
	<s:iterator value="list" status="stat">
		<tr>
			<td width="25%" class="sortableTD"><s:property value="name" /></td>
			<td width="12%" nowrap="nowrap" class="sortableTD" ><s:property value="date" /></td>
			<td width="63%" class="sortableTD"><a href="#" onclick="viewSubject('<s:property value="suggId" />')">
			<s:property value="subject" /></a></td>
		</tr>
	</s:iterator>

</table>
