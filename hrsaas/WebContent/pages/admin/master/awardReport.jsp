 <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="2" align="center"><b>Award Report</b></td>
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
		<td class="bodyCell" width="40%"><b>Award Code</td>
		<td class="bodyCell" width="60%"><b>Award Type </td>		 
		</tr>
	<s:iterator value="awardList" status="stat">
		<tr>
			<td class="bodyCell" width="40%"><s:property value="awardCode" /></td>
			<td class="bodyCell" width="60%"><s:property value="awardType" /></td> 
		</tr>
	</s:iterator>
	<tr>
		<td width="100%" colspan="2">&nbsp;</td>
	</tr>

	<tr>
    	<td width="100%" colspan="2" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
</table>

 
 