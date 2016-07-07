<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>Discipline   
		Report</b></td>
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
		<td class="bodyCell" width="20%"><b>Discipline Code</td>
		<td class="bodyCell" width="30%"><b>Discipline  Name</td>
		<td class="bodyCell" width="30%"><b>Discipline  Description</td>
		<td class="bodyCell" width="20%"><b>Discipline  Parent Code</td>
	

	</tr>
	<s:iterator value="dispArray" status="stat">
		<tr>
			<td class="bodyCell" width="20%"><s:property value="disciplineID" /></td>
			<td class="bodyCell" width="30%"><s:property value="disciplineName" /></td>
			<td class="bodyCell" width="30%"><s:property value="disciplineDesc" /></td>
			<td class="bodyCell" width="20%"><s:property value="disciplineParID" /></td>
			

		</tr>
	</s:iterator>
 
	<tr>
		<td>&nbsp;</td>
	</tr>
	
</table>

