<%@ taglib prefix="s" uri="/struts-tags"%>

<table width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>Award Details Report</b></td>
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
		<td  width="15%"><b>Employee ID :</td>
		<td  width="15%"><s:property value="empId" /></td>
		</tr>
		
		<tr>	
		<td  width="15%"><b>Employee Token :</td>
		<td  width="15%"><s:property value="employeeToken" /></td>
		</tr>
		
		
		<tr>
		<td  width="15%"><b>Employee Name :</td>
		<td  width="15%"><s:property value="empName" /></td>
		</tr>
		<tr>
		<td  width="15%"><b>Center :</td>
		<td  width="15%"><s:property value="empCent" /></td>
		</tr>
		<tr>
		
		<td  width="15%"><b>Rank :</td>
		<td  width="15%"><s:property value="empRank" /></td>
		<td>&nbsp;</td>
		</tr>
		
	<tr>
		<td>&nbsp;</td>
	</tr>
	
	<tr>
		<td>&nbsp;</td>
	</tr>
		
		
	
	<tr  align="center">	
		<td class="bodyCell" width="15%"><b>Award Type</td>
		<td class="bodyCell" width="20%"><b>Award Description</td>
		<td class="bodyCell" width="20%"><b>Award Date</td>
		<td class="bodyCell" width="20%"><b>CE Order No.</td>
		<td class="bodyCell" width="20%"><b>CE Order Date</td>
		
		
	</tr>
	<s:iterator value="awardList" status="stat">
		<tr>
			<td class="bodyCell" width="15%"><s:property value="awardType" /></td>
			<td class="bodyCell" width="15%"><s:property value="awdDesc" /></td>
			<td class="bodyCell" width="15%"><s:property value="awdDt" /></td>
			<td class="bodyCell" width="15%"><s:property value="auth" /></td>
			<td class="bodyCell" width="15%"><s:property value="authDt" /></td>
		</tr>
	</s:iterator>
	<tr>
		<td>&nbsp;</td>
	</tr>
<tr>
    	<td width="100%" colspan="4" align="center"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>

	
</table>
