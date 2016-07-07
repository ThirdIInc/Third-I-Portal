<%@ taglib prefix="s" uri="/struts-tags" %>

  <table class="bodyTable" width="100%">  
  	<tr>
  		<td  width="100%" colspan="3"  align="center" ><b>Credit Report</td>
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="3">&nbsp;</td>  	
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="3">&nbsp;</td> 
  	</tr>
  	
  <tr>  			
		<td class="bodyCell" width="30%"><b>Credit Code</td>
		<td class="bodyCell" width="40%"><b>Credit Name</td>
		<td class="bodyCell" width="30%"><b>Credit Abbr</td>
	</tr>
	
	<s:iterator value="creditMaster.att">            	
	 	<tr>	 
		      <td class="bodyCell" width="30%"><s:property value="creditCode"/></td>
		      <td class="bodyCell" width="40%"><s:property value="creditName"/></td>
		      <td class="bodyCell" width="30%"><s:property value="creditAbbr"/></td>
		</tr>
	</s:iterator>
	<tr>
    	<td width="100%" colspan="3" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 </table>