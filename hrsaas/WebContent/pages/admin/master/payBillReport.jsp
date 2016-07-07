<%@ taglib prefix="s" uri="/struts-tags" %> 

  <table class="bodyTable" width="100%">
  	<tr>
  		<td  width="100%" colspan="2"  align="center" ><b>Pay Bill Report</td>
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
		<td class="bodyCell" width="30%"><b>Pay Bill Code</td>
		<td class="bodyCell" width="70%"><b>Pay Bill Group</td>		
	</tr>
	
	<s:iterator value="pay">            	
	 	<tr>	 
		      <td class="bodyCell" width="30%"><s:property value="payID"/></td>
		      <td class="bodyCell" width="70%"><s:property value="payGrp"/></td>
		</tr>
	</s:iterator>
	<tr>
    	<td width="100%" colspan="2" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 </table>