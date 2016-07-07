 <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="4" align="center"><b>Asset Type Report</b></td>
	</tr>
	<tr>
		<td width="100%" colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="4">&nbsp;</td>
	</tr>

	<tr>
		<td class="bodyCell" width="25%"><b>Asset Code </td>
		<td class="bodyCell" width="75%"><b>Asset Type</td>
	 
	</tr>
	<s:iterator value="assetType.tableList1" status="stat">
		<tr>
			<td class="bodyCell" width="25%"><s:property value="assetCode" /></td>
			<td class="bodyCell" width="75%"><s:property value="assetname" /></td>
			 
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>

	
</table>

<tr>
    	<td width="100%" colspan="4" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 