  <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="4" align="center"><b>Cash Requisition Head Report</b></td>
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
		<td class="bodyCell" width="25%"><b>Requisition Code </td>
		<td class="bodyCell" width="75%"><b>Requisition Name</td>
	 
		
	</tr>
	<s:iterator value="reqTableList" status="stat">
		<tr>
		<%System.out.println("------------------------------"); %>
			<td class="bodyCell" width="25%"><s:property value="requisitionCode" /></td>
			<td class="bodyCell" width="75%"><s:property value="reuisitionName" /></td>
			 
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>

	
</table>

<tr>
    	<td width="100%" colspan="4" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 