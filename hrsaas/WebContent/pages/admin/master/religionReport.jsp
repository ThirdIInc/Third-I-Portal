<%@ taglib prefix="s" uri="/struts-tags" %>

  <table class="bodyTable" width="100%">
  	<tr>
  		<td  width="100%" colspan="2"  align="center" ><b>Religion Report</td>
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="2">&nbsp;</td>  
    	
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="2">&nbsp;</td> 
  	</tr>
  	
  	<tr>  			
		<td class="bodyCell" width="50%" align="center"><b>Religion Code</td>
		<td class="bodyCell" width="50%" align="center"><b>Religion Name</td>
		
	</tr>
  	<s:iterator value="regMaster.att">            	
	 	<tr>	 
		      <td class="bodyCell" width="50%"><s:property value="religionID"/></td>
		      <td class="bodyCell" width="50%"><s:property value="religionName"/></td>
		      
		</tr>
	</s:iterator>
	
 </table>
 
 <tr>
    	<td width="100%" colspan="3" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>