<%@ taglib prefix ="s" uri="/struts-tags" %>
<table  class="bodyTable" width="100%">
<tr>
<td colspan="2" width="100%" align="center">Title Master Report </td>
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
		<td class="bodyCell" width="40%"><b>Title Code</td>
		<td class="bodyCell" width="60%"><b>Title Name </td>		 
		</tr>
	<s:iterator value="titleList" status="stat">
		<tr>
			<td class="bodyCell" width="40%"><s:property value="titleCode" /></td>
			<td class="bodyCell" width="60%"><s:property value="titleName" /></td> 
		</tr>
	</s:iterator>
	<tr>
		<td width="100%" colspan="2">&nbsp;</td>
	</tr>

	<tr>
    	<td width="100%" colspan="2" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>


</table>