<%@ taglib prefix="s" uri="/struts-tags" %> 

  <table class="bodyTable" width="100%">
  	<tr>
  		<td  width="100%" colspan="3"  align="center" ><b>Category Report</td>
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="3">&nbsp;</td>      	
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="3">&nbsp;</td> 
  	</tr>
  	
  	<tr>
    	<td width="100%" colspan="2">&nbsp;</td>
  	</tr>
  	
  	<tr>  			
		<td class="bodyCell" width="10%"><b>Category Id</td>
		<td class="bodyCell" width="20%"><b>Category Name</td>		
		<td class="bodyCell" width="20%"><b>Category Description</td>
		<td class="bodyCell" width="10%"><b>ESI Zone</td>
		<td class="bodyCell" width="10%"><b>PT Zone</td>		
		<td class="bodyCell" width="10%"><b>PF Zone</td>
	</tr>
	
	<s:iterator value="catgMaster.catg">            	
	 	<tr>	 
		      <td class="bodyCell" width="10%"><s:property value="catgID"/></td>
		      <td class="bodyCell" width="20%"><s:property value="catgName"/></td>
		      <td class="bodyCell" width="20%"><s:property value="catgDesc"/></td>
		      <td class="bodyCell" width="10%"><s:property value="esiZone"/></td>
		      <td class="bodyCell" width="10%"><s:property value="ptZone"/></td>
		      <td class="bodyCell" width="10%"><s:property value="pfZone"/></td>
		</tr>
	</s:iterator>
	<tr>
    	<td width="100%" colspan="3" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
 </table>