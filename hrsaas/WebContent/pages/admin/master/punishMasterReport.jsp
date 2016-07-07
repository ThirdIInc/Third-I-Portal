 <%@ taglib prefix="s" uri="/struts-tags"%>

<table>	
	       <tr>
		<td width="100%" colspan="6" align="center"><b>Punishment Report</b></td>
	        </tr>
	        
	        <tr>
	        <td>&nbsp;</td>
	        </tr>
	
	</table>

<table class="bodyTable" width="100%" border="1">

<tr>
		<td class="bodyCell" width="10%"><b>Punishment Code</b></td>
		<td class="bodyCell" width="30%"><b>Punishment Name</b></td>
		<td class="bodyCell" width="30%"><b>Financial Implication</b></td>
		<td class="bodyCell" width="30%"><b>Is Major</b></td>
	
		
</tr>

<s:iterator value="punishmentList" status="stat">
		<tr>
			<td class="bodyCell" width="10%" nowrap="nowrap"><s:property value="punishId" /></td>
			<td class="bodyCell" width="30%"><s:property value="punishName" /></td>
			<td class="bodyCell" width="30%"><s:property value="fImplication" /></td>
			<td class="bodyCell" width="30%"><s:property value="isMajor" /></td>
			
			
		</tr>
	</s:iterator>
	
</table>


 