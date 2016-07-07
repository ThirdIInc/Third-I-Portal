<%@ taglib prefix="s" uri="/struts-tags" %>
<body">
<s:form action="EmployeeCheckList"  validate="true" id="paraFrm"  theme="simple" >
<table class="bodyTable" width="100%">
     
  	    	<tr>
	  		<td  width="45%" align="right">Select Employee :</td>
	  		<td width="55%"><img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
	  		width="18" onclick="javascript:callsF9(500,325,'EmployeeCheckList_f9empaction.action');"></td>
	  		</tr>
	  
	      <tr>
	  		<td width="45%" align="right">Token No :</td>
	  		<td width="55%"  ><s:textfield size="25" readonly="true" name="employeeCheckList.empToken" />
	  		
	  		</td>
		</tr>
		<tr>
	  		<td  align="right">Employee Name :</td>
	  		<td><s:textfield size="25" readonly="true"  name="employeeCheckList.empName" /></td>
	  		</tr>
		
	  	<tr>
	  		<td  align="right">Department :</td>
	  		<td><s:textfield size="25" readonly="true"  name="employeeCheckList.deptName" /></td>
  		</tr>
  		
	  	<tr>
	  		<td  align="right">Center :</td>
	  		<td ><s:textfield size="25" readonly="true"  name="employeeCheckList.center" /></td>  	  			
		</tr>
		
		<tr>
	  		<td  align="right">Trade :</td>
	  		<td ><s:textfield size="25" readonly="true"  name="employeeCheckList.empTrade" /></td>  	  			
		</tr>
		
		<tr>
	  		<td  align="right">Rank :</td>
	  		<td ><s:textfield size="25" readonly="true"  name="employeeCheckList.empRank" /></td>  	  			
	  		<s:hidden name="employeeCheckList.empID" /> 
		</tr>  	
    <tr>
    <td></td><td><s:submit cssClass="pagebutton" cssClass="button" value='Submit' action="EmployeeCheckList_save" /><s:submit cssClass="pagebutton" cssClass="button" value='Reset' method="input" /></td>
    </tr>
 	   
  	</table>
  	
	<table>
  	<tr> 
  		<td class="headercell" width="30%">Check Name </td><td width="40%" class="headercell">Check Discription </td><td width="30%" class="headercell">Check Submit </td>
  	 </tr>	<%int i=0; %>
  	  <s:iterator value="employeeCheckList.chkList">
  	  <tr><s:hidden name="chkId"/>
  		<td ><s:property  value="chkName" /></td><td><s:textfield value="%{chkDesc}" name="chkDesc" /> </td>
  		 <td align="middle">
  		  <input type = "checkbox" name ="chkSubmit"    value="false" /> </td>
  	</tr><%i++; %>
  	</s:iterator>
  	</table>
 
   
 </s:form>
 </body>
 <script>
 
 
 
  
 </script>
 