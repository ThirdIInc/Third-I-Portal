<%@ taglib prefix="s" uri="/struts-tags" %>

  <table class="bodyTable" width="100%">
  	<tr>
  		<td  width="100%" colspan="3"  align="center" ><b>Handicap Report</td>
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="3">&nbsp;</td>  
    	
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="3">&nbsp;</td> 
  	</tr>
  	<tr>  			
		<td class="bodyCell" width="50%"><b>Catagory ID</td>
		<td class="bodyCell" width="50%"><b>Catagory Name</td>
		
	</tr>
	
	<s:iterator value="handicapMaster.att">            	
	 	<tr>	 
		      <td class="bodyCell" width="50%"><s:property value="catagoryID"/></td>
		      <td class="bodyCell" width="50%"><s:property value="catagoryName"/></td>
		      
		</tr>
	</s:iterator>
	<tr>
    	<td width="100%" colspan="3" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 </table>
  	
  	