<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="5" align="center"><b>Rank Report</b></td>
	</tr>
	<tr>
		<td width="100%" colspan="5">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="5">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="5">&nbsp;</td>
	</tr>

	<tr>
		<td class="bodyCell" width="10%"><b>Rank Code</b></td>
		<td class="bodyCell" width="20%"><b>Rank Name</b></td>
		<td class="bodyCell" width="30%"><b>Rank Abbr</b></td>
		<td class="bodyCell" width="20%"><b>Rank Parent Code</b></td>
		<td class="bodyCell" width="20%"><b>Rank Higher Code</b></td>
		
	</tr>
	<s:iterator value="rankList" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="rankId" /></td>
			<td class="bodyCell" width="20%"><s:property value="rankName" /></td>
			<td class="bodyCell" width="30%"><s:property value="rankAbbr" /></td>
			<td class="bodyCell" width="20%"><s:property value="rankParCode" /></td>
			<td class="bodyCell" width="20%"><s:property value="rankHighCode" /></td>
			
			
		</tr>
	</s:iterator>
	<tr>
		<td width="100%" colspan="5">&nbsp;</td>
	</tr>
<tr>
    	<td width="100%" colspan="5" align="center"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
	
</table>

