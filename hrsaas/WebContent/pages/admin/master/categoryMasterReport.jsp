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
		<td class="bodyCell" width="30%"><b>Category Code</td>
		<td class="bodyCell" width="40%"><b>Category Name</td>		
		<td class="bodyCell" width="40%"><b>Category Description</td>
		<td class="bodyCell" width="40%"><b>Category Age</td>
	</tr>
	
	<s:iterator value="catgMaster.catg">            	
	 	<tr>	 
		      <td class="bodyCell" width="30%"><s:property value="catgID"/></td>
		      <td class="bodyCell" width="40%"><s:property value="catgName"/></td>
		      <td class="bodyCell" width="40%"><s:property value="catgDesc"/></td>
		      <td class="bodyCell" width="40%"><s:property value="catgAge"/></td>
		</tr>
	</s:iterator>
	<tr>
    	<td width="100%" colspan="3" align="right"><s:submit cssClass="pagebutton"   value="Print"/></td>
  	</tr>
 </table>