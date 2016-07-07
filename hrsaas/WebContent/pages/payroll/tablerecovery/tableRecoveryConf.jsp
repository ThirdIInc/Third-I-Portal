<%@ taglib prefix="s" uri="/struts-tags"%>


<s:form action="TabRecConf" id="paraFrm" validate="true"
	 theme="simple">

	<table  width="100%" border="0" >
      <tr>
  			<td width="100%" colspan="6" class="pageHeader" align="center" >Table Recovery Configuration</td>
  	  </tr>
  	   <tr>
	    <td >&nbsp;</td>
	  </tr>
	<tr>
	  		<td >Select Employee : </td>
	  		
	  		
	  		<td  colspan="3">
	  		<s:hidden name="empID" />
	  		<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
	  		width="18" onclick="javascript:callsF9(500,325,'TabRecConf_f9empaction.action');">
	  		<s:textfield name="tabRecConf.empTokenNo" size="15" readonly="true"/>
	  		<s:textfield name="tabRecConf.empName" size="74" readonly="true"/>
	  		</td>
	</tr>
	
	
	<tr>
	  		<td    >Rank :</td>
	  		<td  ><s:textfield size="25" name="tabRecConf.department" readonly="true" /></td>
   
	  		<td    >Center :</td>
	  		<td  ><s:textfield size="25" name="tabRecConf.center" readonly="true" /></td>  
	 </tr> 
	 <tr>
	    <td >&nbsp;</td>
	  </tr>
	  </table>
	  
	 <table width="100%" align="center">
	  <s:if test="%{tabRecConf.display}">
	 <tr>  			
  		<td class="headercell" width="5%">SNo</td>
		<td class="headercell" width="30%">Debit Name</td>
		
		<td class="headercell" width="10%">Check</td>
		
	</tr>	
	
	<% try{
		
	String[][] centerChk=(String[][])request.getAttribute("centerChk");
	
	for(int k =0; k < centerChk.length; k++){ %>
	 <tr>	 
	      <td class="bodyCell" width="5%"><%=k+1%></td>
	      
	       <td class="bodyCell" width="10%"><%= String.valueOf(centerChk[k][1]) %></td>
	     
	      <td class="bodyCell" width="10%" align="center"><s:checkbox name ="debitCode" value="<%= String.valueOf(centerChk[k][2])%>" fieldValue="<%= String.valueOf(centerChk[k][0])%>" />
	     
	       </td>
	     
	  </tr>    
	  
	  <%
	  }
	}catch(Exception e)
	{e.printStackTrace();}
	  %>   
	  
	   <tr>
	    <td >&nbsp;</td>
	  </tr>
	  <tr>
  		<td width="100%" colspan="6" align="center" ><s:submit cssClass="pagebutton" action="TabRecConf_save" theme="simple"	value="Save" />
  	
  		<s:submit cssClass="pagebutton" action="TabRecConf_reset" theme="simple"	value="Reset" /></td>
  	</tr>
	  
	  </s:if>
	 
	  <s:else></s:else>
	 </table> 
		
	</s:form>
	
	<script>
	function callChk(id){

 if(document.getElementById(id).value=='Y'){
  document.getElementById(id).value='N';
 }else  if(document.getElementById(id).value=='N'){
  document.getElementById(id).value='Y';
 } 
}
	</script>