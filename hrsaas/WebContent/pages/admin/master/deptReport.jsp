<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="7" align="center"><b>Department Report</b></td>
	</tr>
	<tr>
		<td width="100%" colspan="7">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="7">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="7">&nbsp;</td>
	</tr>

	<tr>
		<td class="bodyCell" width="10%"><b>Dept Code</td>
		<td class="bodyCell" width="25%"><b>Dept Name</td>
		<td class="bodyCell" width="10%"><b>Dept Desc</td>
		<td class="bodyCell" width="10%"><b>Dept Abbr</td>
		<td class="bodyCell" width="25%"><b>Division</td>
		<td class="bodyCell" width="10%"><b>Dept Parent Code</td>
		<td class="bodyCell" width="10%"><b>Dept Level Code</td>
		
		

	</tr>
	<s:iterator value="deptList" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="deptID" /></td>
			<td class="bodyCell" width="25%"><s:property value="deptName" /></td>
		    <td class="bodyCell" width="10%"><s:property value="deptDesc" /></td>
			<td class="bodyCell" width="10%"><s:property value="deptAbbr" /></td>
			<td class="bodyCell" width="25%"><s:property value="divName" /></td>
			<td class="bodyCell" width="10%"><s:property value="deptParID" /></td>
			<td class="bodyCell" width="10%"><s:property value="deptLev" /></td>
			

		</tr>
	</s:iterator>
	<tr>
		<td width="100%" colspan="7">&nbsp;</td>
	</tr>

	
</table>

<tr>
    	<td width="100%" colspan="7" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
