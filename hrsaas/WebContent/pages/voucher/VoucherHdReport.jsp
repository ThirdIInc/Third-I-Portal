 <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="4" align="center"><b>Voucher Head Report</b></td>
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
		<td class="bodyCell" width="25%"><b>Voucher Code </td>
		<td class="bodyCell" width="75%"><b>Voucher Name</td>
	 
		
	</tr>
	<s:iterator value="voucherList" status="stat">
		<tr>
			<td class="bodyCell" width="25%"><s:property value="voucherCode" /></td>
			<td class="bodyCell" width="75%"><s:property value="voucherHead" /></td>
			 
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>

	
</table>


 