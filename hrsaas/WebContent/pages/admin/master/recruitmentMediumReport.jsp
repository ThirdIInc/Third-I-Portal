<%@ taglib prefix="s" uri="/struts-tags"%>
<table>
<tr>
<td width="100%" colspan="6" align="center"><b>Recruitment Medium Report</b></td>
</tr>

<tr>
<td>&nbsp;</td>
</tr>

</table>




<table class="bodyTable" width="100%" border="1">

	
	
	

	<tr>
		<td class="bodyCell" width="10%"><b>Recruitment ID</b></td>
		<td class="bodyCell" width="20%"><b>Recruitment Name</b></td>
		
	

	</tr>
	<s:iterator value="recrtList" status="stat">
		<tr>
			<td class="bodyCell" width="25%"><s:property value="mediumCode" /></td>
			<td class="bodyCell" width="50%"><s:property value="mediumName" /></td>
		</tr>
	</s:iterator>
	

	
</table>
