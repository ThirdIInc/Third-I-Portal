  <%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="3" align="center"><b>CasteMaster Report</b></td>
	</tr>
	<tr>
		<td width="100%" colspan="3">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="3">&nbsp;</td>
	</tr>
	<tr>
		<td width="100%" colspan="3">&nbsp;</td>
	</tr>

	<tr>
		<td class="bodyCell" width="20%"><b>Caste Code</td>
		<td class="bodyCell" width="40%"><b>Caste Name </td>
		<td class="bodyCell" width="40%"><b>Category Name </td>		 
		</tr>
	<s:iterator value="casteList" status="stat">
		<tr>
			<td class="bodyCell" width="20%"><s:property value="casteCode" /></td>
			<td class="bodyCell" width="40%"><s:property value="casteName" /></td> 
			<td class="bodyCell" width="40%"><s:property value="casteCatgName" /></td> 
		</tr>
	</s:iterator>
	<tr>
		<td width="100%" colspan="3">&nbsp;</td>
	</tr>

	<tr>
    	<td width="100%" colspan="3" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
</table>

 
 