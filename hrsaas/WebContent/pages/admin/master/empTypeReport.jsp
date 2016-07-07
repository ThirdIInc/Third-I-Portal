<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="3" align="center"><b>Employee Type Report</b></td>
	</tr>
	<tr>
		<td width="100%" colspan="3">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="3">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="3">&nbsp;</td>
	</tr>

	<tr>
		<td class="bodyCell" width="10%"><b>Type Code</td>
		<td class="bodyCell" width="30%"><b>Type Name</td>
		<td class="bodyCell" width="30%"><b>Type Abbr</td>
		<td class="bodyCell" width="10%"><b>ESI Zone</td>
		<td class="bodyCell" width="10%"><b>PF Zone</td>
		<td class="bodyCell" width="10%"><b>PT Zone</td>
		</tr>
	<s:iterator value="typeList" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="typeID" /></td>
			<td class="bodyCell" width="20%"><s:property value="typeName" /></td>
			<td class="bodyCell" width="20%"><s:property value="typeAbbr" /></td>
			<td class="bodyCell" width="10%"><s:property value="esiZone" /></td>
			<td class="bodyCell" width="10%"><s:property value="ptZone" /></td>
			<td class="bodyCell" width="10%"><s:property value="pfZone" /></td>
		</tr>
	</s:iterator>
	<tr>
		<td width="100%" colspan="3">&nbsp;</td>
	</tr>

	
</table>

<tr>
    	<td width="100%" colspan="3" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
