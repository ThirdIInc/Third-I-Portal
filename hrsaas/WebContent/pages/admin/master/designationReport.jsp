<%@ taglib prefix="s" uri="/struts-tags" %>

  <table class="bodyTable" width="100%">  
  	<tr>
  		<td  width="100%" colspan="7"  align="center" ><b>Designation Report</td>
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="7">&nbsp;</td>  
    	
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="7">&nbsp;</td> 
  	</tr>
  	<tr>  			
		<td class="bodyCell" width="10%"><b>Desg Code</td>
		<td class="bodyCell" width="20%"><b>Desg Name</td>
		<td class="bodyCell" width="15%"><b>Desg Par Code</td>
		<td class="bodyCell" width="15%"><b>Desg High Code</td>
		<td class="bodyCell" width="10%"><b>Desg Abbr</td>
		<td class="bodyCell" width="15%"><b>Desg Desc</td>
		<td class="bodyCell" width="15%"><b>Desg Level</td>
		<td class="bodyCell" width="15%"><b>Recommending Authority</td>
		<td class="bodyCell" width="15%"><b>Approving Authority</td>		
	</tr>
	
  	<s:iterator value="desgMaster.att">            	
	 	<tr>	 
		      <td class="bodyCell" width="10%"><s:property value="desgID"/></td>
		      <td class="bodyCell" width="20%"><s:property value="desgName"/></td>
		      <td class="bodyCell" width="15%"><s:property value="desgParCode"/></td>
   		      <td class="bodyCell" width="15%"><s:property value="desgHighCode"/></td>
   		      <td class="bodyCell" width="10%"><s:property value="desgAbbr"/></td>
		      <td class="bodyCell" width="15%"><s:property value="desgDesc"/></td>
		      <td class="bodyCell" width="15%"><s:property value="desgLevel"/></td>		         		      
		      <td class="bodyCell" width="15%"><s:property value="rcmndAuth"/></td>		         		      
		      <td class="bodyCell" width="15%"><s:property value="apprAuth"/></td>		         		      
		</tr>
	</s:iterator>
	<tr>
    	<td width="100%" colspan="3" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 </table>
  