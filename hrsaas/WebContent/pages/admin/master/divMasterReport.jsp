<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%" >

	<tr>
		<td width="100%" colspan="6" align="center"><b>Division Report</b></td>
	</tr>
	<tr>
	    	<td>&nbsp;</td>
	</tr>
	<tr>
	    	<td>&nbsp;</td>
	</tr>
	</table>
	<table class="bodyTable" width="100%" border="1">
	
	
	<tr>
		<td class="bodyCell" width="10%"><b>Division Code</td>
		<td class="bodyCell" width="20%"><b>Division Name</td>
		<td class="bodyCell" width="20%"><b>Division Description</td>
		<td class="bodyCell" width="20%"><b>Division Group</td>
		
   </tr>
	<s:iterator value="divList" status="stat">
		<tr>
			<td class="bodyCell" width="13%"><s:property value="divId" /></td>
			<td class="bodyCell" width="29%"><s:property value="divName" /></td>
			<td class="bodyCell" width="29%"><s:property value="divDesc" /></td>
			<td class="bodyCell" width="29%"><s:property value="divGrpName" /></td>
			
		</tr>
	</s:iterator>
	


	
</table>
