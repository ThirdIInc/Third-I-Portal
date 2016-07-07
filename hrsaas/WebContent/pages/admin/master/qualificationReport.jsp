<%@ taglib prefix="s" uri="/struts-tags" %>

  <table class="bodyTable" width="100%">    
  	<tr>
  		<td  width="100%" colspan="3"  align="center" ><b>Qualification Report</td>
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
		<td class="bodyCell" width="30%"><b>Qualification Code</td>
		<td class="bodyCell" width="40%"><b>Qualification Name</td>
		<td class="bodyCell" width="30%"><b>Qualification Grade</td>
	</tr>
	
	<s:iterator value="quaMaster.att">            	
	 	<tr>	 
		      <td class="bodyCell" width="30%"><s:property value="quaID"/></td>
		      <td class="bodyCell" width="40%"><s:property value="quaName"/></td>
		      <td class="bodyCell" width="30%"><s:property value="quaGrade"/></td>
		</tr>
	</s:iterator>
	<tr>
    	<td width="100%" colspan="3" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 </table>