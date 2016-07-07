<%@ taglib prefix="s" uri="/struts-tags"%>

<table class="bodyTable" width="100%">

	<tr>
		<td width="100%" colspan="6" align="center"><b>Leave Balance Report</b></td>
	</tr>
	
	 <tr>
    	<td >&nbsp;</td>
  	</tr>
  	
  	 <tr>
    	<td>&nbsp;</td>
  	</tr>
  	
  	<tr>
    	<td >&nbsp;</td>
  	</tr>
	
	<tr>
	<td width="100%" colspan="3">Employee  Name :
    	<s:property   value="empName" />
    </td>
    </tr>
    <tr>
    <td width="100%" colspan="3">Rank Name :
    	<s:property  value="rankName" />
    </td>
    </tr>
    <tr>
    <td width="100%" colspan="3">Center Name :
    	<s:property   value="centerName" />
    </td>
      
    
    </tr>
    
      	<tr>
    	<td width="100%" colspan="3">&nbsp;</td>
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="3">&nbsp;</td>
  	</tr>
	
	<tr>
		<td class="bodyCell" width="10%"><b>Leave Type</b></td>
		<td class="bodyCell" width="20%"><b>Opening Balance</b></td>
		<td class="bodyCell" width="40%"><b>Closing Balance</b></td>
		
		
	

	</tr>
	<s:iterator value="leveList" status="stat">
		<tr>
			<td class="bodyCell" width="50%"><s:property value="leaveName" /></td>
			<td class="bodyCell" width="25%"><s:property value="opBal" /></td>
			<td class="bodyCell" width="25%"><s:property value="clBal" /></td>
			
		</tr>
	</s:iterator>
	


	
</table>
