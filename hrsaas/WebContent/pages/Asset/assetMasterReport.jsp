 <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="4" align="right"><b>Asset Master Report</b></td>
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
		<td class="bodyCell" width="10%"><b>Asset Code </td>
		<td class="bodyCell" width="15%"><b>Inventory Code</td>
		<td class="bodyCell" width="15%"><b>Asset Type </td>
		<td class="bodyCell" width="15%"><b>Status</td>
	 	<td class="bodyCell" width="15%"><b>Purchase Date</td>
		<td class="bodyCell" width="15%"><b>Price</td>
		<td class="bodyCell" width="15%"><b>Description</td>
	</tr>
	<s:iterator value="tableList1" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="code" /></td>
			<td class="bodyCell" width="15%"><s:property value="invCode" /></td>
			 <td class="bodyCell" width="15%"><s:property value="assetname" /></td>
			 <td class="bodyCell" width="15%"><s:property value="status" /></td>
			 <td class="bodyCell" width="15%"><s:property value="purchaseDate" /></td>
			 <td class="bodyCell" width="15%"><s:property value="price" /></td>
			 <td class="bodyCell" width="15%"><s:property value="description" /></td>
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>

	
</table>

<tr>
    	<td width="100%" colspan="4" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 