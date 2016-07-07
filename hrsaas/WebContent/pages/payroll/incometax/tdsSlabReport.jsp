<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>Tds Slab Report</b></td>
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
		<td class="bodyCell" width="10%"><b>Sr No</td>
		<td class="bodyCell" width="10%"><b>From Amount</td>
		<td class="bodyCell" width="20%"><b>To Amount</td>
		<td class="bodyCell" width="20%"><b>Tax Percentage</td>
		
	
	</tr>
	<s:iterator value="taxSlab.tdsSlabArray" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="invCode" /></td>
			<td class="bodyCell" width="10%"><s:property value="taxSlab.frmAmount" /></td>
			<td class="bodyCell" width="20%"><s:property value="taxSlab.toAmount" /></td>
			<td class="bodyCell" width="20%"><s:property value="taxSlab.taxPercentage" /></td>
			
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>
<tr>
    	<td width="100%" colspan="4" align="center"><s:submit cssClass="pagebutton" value="Print"/></td>
</tr>	
</table>