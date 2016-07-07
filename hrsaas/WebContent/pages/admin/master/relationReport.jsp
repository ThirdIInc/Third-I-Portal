<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		
		<td width="100%" colspan="2" align="center"><b>Relation Report</b></td>
	</tr>
	<tr>
		<td width="100%" colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="2">&nbsp;</td>
	</tr>

	<tr>
		<td class="bodyCell" width="20%"><b>Relation Code</td>
		<td class="bodyCell" width="80%"><b>Relation Name</td>
		
	</tr>
	<s:iterator value="relationList" status="stat">
		<tr>
			<td class="bodyCell" width="20%"><s:property value="relationCode" /></td>
			<td class="bodyCell" width="80%"><s:property value="relationName" /></td>
			
		</tr>
	</s:iterator>
	<tr>
		<td width="100%" colspan="2">&nbsp;</td>
	</tr>
<tr>
    	<td width="100%" colspan="2" align="center"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
	
</table>

