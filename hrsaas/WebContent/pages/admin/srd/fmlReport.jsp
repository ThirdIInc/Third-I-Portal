<%@ taglib prefix="s" uri="/struts-tags" %>

  <table class="bodyTable" width="100%" border="0">
  	<tr>
  		<td  width="100%"   align="center" colspan="4"><b>FAMILY DETAILS 
</td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="4">&nbsp;</td>
    	
  	</tr>
  	<tr>
    	<td width="100%" colspan="4">&nbsp;</td>
    	
  	</tr>
  	<tr>
    	<td width="15%" ><b>Name :</td>
    	<td width="85%" colspan="3"><s:property value="empName"/></td>
    	
  	</tr>
  	<tr>
    	<td width="15%" ><b>Employee Id :</td>
    	<td width="85%" ><s:property value="empToken"/></td>
    	<!--<td width="25%" >Department</td>
    	<td width="25%" ><s:property value="deptName"/></td>
    	
  	--></tr>
  	<tr>
    	<td width="15%" ><b>Branch :</td>
    	<td width="85%" colspan="3" ><s:property value="centerName"/></td>
    	<!--<td width="25%" >Designation</td>
    	<td width="25%" ><s:property value="deptName"/>,<br>Naval Dockyard, Mumbai. 
  
    	-->
    	
  	</tr>
  	
  	  	<tr>
    	<td width="15%" ><b>Designation :</td>
    	<td width="85%" ><s:property value="rankName"/></td>
    	 </tr>
    	 
    	 
  	
  	
  	
  	</table>
  
  <table class="bodyTable" width="100%">
  	<tr>  			
		<td class="bodyCell" width="10%"><b>Sr.No</td>
		<td class="bodyCell" width="25%"><b>Name of family members</td>
		<td class="bodyCell" width="20%"><b>Date of birth</td>
		<td class="bodyCell" width="15%"><b>Relationship</td>
		
		
	</tr>
	<s:iterator value="famList" status="stat">            	
	 <tr>	 
	      <td class="bodyCell" width="10%"><s:property value="familyCode"/></td>
          <td class="bodyCell" width="25%"><s:property value="firstName"/></td>
	      <td class="bodyCell" width="20%"><s:property value="birthday"/></td>
	      <td class="bodyCell" width="15%"><s:property value="relName"/></td>
		  
		  
	  </tr>           
	</s:iterator>
	<tr>
    	<td width="100%" colspan="4"align="right">&nbsp;</td>
   </tr>
	<tr>
    	<td width="100%" colspan="4"align="right">Signature of the individual</td>
   </tr>
		
</table>

   
   
   
  	
  

  	
  	
  	