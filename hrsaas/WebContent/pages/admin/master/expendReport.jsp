 <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="4" align="center"><b>Expenditure Report</b></td>
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
		<td class="bodyCell" width="25%"><b>Expenditure Code </td>
		<td class="bodyCell" width="75%"><b>Expenditure Name</td>
	 
		
	</tr>
	<s:iterator value="expend.tableList1" status="stat">
		<tr>
		<%System.out.println("------------------------------"); %>
			<td class="bodyCell" width="25%"><s:property value="expCode" /></td>
			<td class="bodyCell" width="75%"><s:property value="expName" /></td>
			 
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>

	
</table>

<tr>
    	<td width="100%" colspan="4" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 