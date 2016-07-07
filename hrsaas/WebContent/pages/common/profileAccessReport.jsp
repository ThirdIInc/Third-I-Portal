<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="7" align="center"><b><u>Access
		Profile Report</u></b></td>
	</tr>

	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;</td>
	</tr>

	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;</td>
	</tr>


	<tr>
		<td width="100%" colspan="7" align="center">&nbsp;</td>
	</tr>
</table>

<table class="bodyTable" width="100%">
	<tr>
		<td width="100%" colspan="3" align="center">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="3" align="center"><b>Division List</b></td>
	</tr>

	<tr>
		<td class="bodyCell" width="20%"><b>Sr. No.</td>
		<td class="bodyCell" width="30%"><b>Division Code</td>
		<td class="bodyCell" width="50%"><b>Division Name</td>


	</tr>
	<s:if test="nodivData">
		<tr>
			<td width="100%" colspan="3" align="center"><font
				color="red">No Data To Display</font></td>
		</tr>
	</s:if>

	<%
	int i = 1;
	%>
	<s:iterator value="profileAccess.divList" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><%=i++%></td>
			<td class="bodyCell" width="15%"><s:property value="divId" /></td>
			<td class="bodyCell" width="75%"><s:property value="divName" /></td>


		</tr>

	</s:iterator>

	<tr>
		<td width="100%" colspan="3" align="center">&nbsp;</td>
	</tr>

	<tr>
		<td width="100%" colspan="3" align="center">&nbsp;</td>
	</tr>
</table>

 



<tr>
	<td width="100%" colspan="4" align="right"><s:submit value="Print" /></td>
</tr>

