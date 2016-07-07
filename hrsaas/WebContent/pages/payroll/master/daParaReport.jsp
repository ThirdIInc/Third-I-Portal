<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>DA Parameter Report</b></td>
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
		<td class="bodyCell" width="10%"><b>DA Code</td>
		<td class="bodyCell" width="20%"><b>DA Rate </td>
		<td class="bodyCell" width="20%"><b>DA Effective Date</td>
		
	
	</tr>
	<s:iterator value="daArray" status="stat">
		<tr>
			<td class="bodyCell" width="10%"><s:property value="daCode" /></td>
			<td class="bodyCell" width="20%"><s:property value="daRate" /></td>
			<td class="bodyCell" width="20%"><s:property value="daEffDate" /></td>
			
			
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>
<!--<tr>
    	<td width="100%" colspan="4" align="center"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>

	
--></table>

