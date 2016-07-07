  <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="2" align="center"><b>Holiday Master Report</b></td>
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
		<td class="bodyCell" width="40%"><b>Holiday Date</td>
		<td class="bodyCell" width="60%"><b>Holiday Desc </td>		 
		</tr>
	<s:iterator value="holiList" status="stat">
		<tr>
			<td class="bodyCell" width="40%"><s:property value="holiDate" /></td>
			<td class="bodyCell" width="60%"><s:property value="desc" /></td> 
		</tr>
	</s:iterator>
	<tr>
		<td width="100%" colspan="2">&nbsp;</td>
	</tr>

	<tr>
    	<td width="100%" colspan="2" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
</table>

 
 