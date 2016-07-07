<%@ taglib prefix="s" uri="/struts-tags" %>

  <table class="bodyTable" width="100%">    
  	<tr>
  		<td  width="100%" colspan="3"  align="center" ><b>Check List Report</td>
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
		<td class="bodyCell" width="30%"><b>Check List Code</td>
		<td class="bodyCell" width="40%"><b>Check List Name</td>
		<td class="bodyCell" width="30%"><b>Check List Type</td>
	</tr>
	
	<s:iterator value="checkListMaster.att">            	
	 	<tr>	 
		      <td class="bodyCell" width="30%"><s:property value="checkCode"/></td>
		      <td class="bodyCell" width="40%"><s:property value="checkName"/></td>
		      <td class="bodyCell" width="30%"><s:property value="checkType"/></td>
		</tr>
	</s:iterator>
 </table>