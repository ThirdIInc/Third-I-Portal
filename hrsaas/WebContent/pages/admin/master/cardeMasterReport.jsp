 <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="5" align="center"><b>Cadre Master Report</b></td>
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
		<td class="bodyCell" width="10%"><b>Cadre Code</b></td>
		<td class="bodyCell" width="20%"><b>Cadre Name</b></td>
		<td class="bodyCell" width="30%"><b>Cadre Desc</b></td>
		<td class="bodyCell" width="20%"><b>Cadre Abbr.</b></td>
		<td class="bodyCell" width="20%"><b>Cadre Parent Code</b></td>
		
	</tr>
	<s:iterator value="cardeList" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="cadreID" /></td>
			<td class="bodyCell" width="20%"><s:property value="cadreName" /></td>
			<td class="bodyCell" width="30%"><s:property value="cadreDesc" /></td>
			<td class="bodyCell" width="20%"><s:property value="cadreAbbr" /></td>
			<td class="bodyCell" width="20%"><s:property value="cadreParID" /></td>
			
			
		</tr>
	</s:iterator>
	<tr>
		<td width="100%" colspan="5">&nbsp;</td>
	</tr>
<tr>
    	<td width="100%" colspan="5" align="center"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
	
</table>

 