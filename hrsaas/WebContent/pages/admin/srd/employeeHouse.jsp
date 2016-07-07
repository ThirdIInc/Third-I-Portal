 <%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="EmployeeHouse" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="bodyTable" width="100%">
		<tr>
			<td width="100%" colspan="4" class="pageHeader" align="center">Employee
			House</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td width="25%" colspan="1">Select Employee<font color="red">*</font> :</td>
			<td width="75%" colspan="3"><s:if
				test="%{empHouse.generalFlag}">
				</s:if> <s:else>
				<s:if test="empHouse.viewFlag"><img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
					width="18"
					onclick="javascript:callsF9(500,325,'EmployeeHouse_f9empaction.action');" /> </s:if>
			</s:else> <s:hidden name="empHouse.empID" /> <s:textfield
				readonly="true" name="empHouse.employeeToken" theme="simple"
				id="employeeToken" size="10" /><s:textfield readonly="true"
				name="empHouse.empName" size="81" /></td>
		</tr>
		 
		<tr>
			<td width="25%" colspan="1">Center:</td>
			<td width="25%" colspan="1"><s:textfield size="30"
				readonly="true" name="empHouse.center" /></td> <s:hidden name="empHouse.houseID" />
			<td width="25%" colspan="1">Rank:</td>
			<td width="25%" colspan="1"><s:textfield size="30"
				readonly="true" name="empHouse.rank" /></td>

		</tr>

		 <tr>
			<td width="100%" colspan="4">&nbsp;</td>
		
		</tr>
		 <tr>
			<td colspan="4" class="sectionhead">House Particulars</td>
		
		</tr>
		 <tr>
			<td width="100%" colspan="4">&nbsp;</td>
		
		</tr>
		 
		 
		<tr>
			<td width="25%" colspan="1" >House Number<font color="red">*</font>  :
			<td width="25%" colspan="1" ><s:textfield name="empHouse.houseNO"
				size="30"   /></td>
			<td width="25%" colspan="1" >House Colony Name<font color="red">*</font> :</td>
			<td width="25%" colspan="1" ><s:hidden name="empHouse.houseColonyID"
				  />  
				<s:textfield name="empHouse.houseColonyName"
				size="25" />  

				<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
					width="18"
					onclick="javascript:callsF9(500,325,'EmployeeHouse_f9colonyidaction.action');" />
		 </td>
		</tr>

		<tr>
			<td width="25%" colspan="1">Occupancy Status <font color="red">*</font>  :</td>
			<td width="75%"colspan="3"><s:select name="empHouse.status"
				id="sta" headerKey="1" headerValue="-------------Select--------------" cssStyle="width:157"
				list="#{'Y':'Yes','N':'No'}" /></td>
			 
		</tr>

		<tr>
			<td colspan="4">&nbsp</td>

		</tr>




		<tr>
			<td width="100%" align="center" colspan="4">
			
			<s:if test="empHouse.insertFlag">
			
			<s:submit cssClass="pagebutton"
				action="EmployeeHouse_save" value=" Insert " onclick="return validate()"/>&nbsp;  </s:if>
				<s:if test="empHouse.updateFlag">
			   <s:submit cssClass="pagebutton"
				action="EmployeeHouse_update" value=" Update " onclick="return updateValidate()" />
				</s:if>
				&nbsp;
				<s:submit cssClass="pagebutton"
				action="EmployeeHouse_reset" value=" Reset " /></td>
 
		</tr>

		<tr>
			<td colspan="4">&nbsp;<s:hidden name="empHouse.paraId"
				  /> <s:hidden name="empHouse.flag" />  </td>

		</tr>

       <tr>
			<td class="headercell" width="25%">House No</td>
			<td class="headercell" width="25%">Colony Name</td>
			<td class="headercell" width="25%">Occupancy Status </td>
			<td class="headercell" width="25%">&nbsp;</td>
		</tr>
 
<s:iterator value="tableList" status="stat">
			<tr>
				 
				<td class="bodyCell" width="25%"><s:property value="houseNO" /></td>
				<td class="bodyCell" width="25%"><s:property value="houseColonyName" /></td>
				<td class="bodyCell" width=25%"><s:property value="status" /></td>
				<td class="bodyCell" width="25%"> <input type="button" class="pagebutton" onclick="callForEdit('<s:property value="houseID"/>')"  value="Edit"/>
				<input type="button" class="pagebutton" onclick="callDelete('<s:property value="houseID"/>')"  value="Delete" />
				
				 </td>

			</tr>
		</s:iterator>



	</table>
</s:form>
<script>

 function callDelete(id){
        
          var conf=confirm("Are you sure to delete this record?");
  			if(conf) {
  			          
  			       
  			          
  			}
  			else
  			{
  				return false;
  			}
   
   
	   	document.getElementById("paraFrm").action="EmployeeHouse_delete.action";
	  	document.getElementById('empHouse.paraId').value=id;
	    document.getElementById("paraFrm").submit();
   }

  function callForEdit(id){
	   	document.getElementById("paraFrm").action="EmployeeHouse_edit.action";
	  	document.getElementById('empHouse.paraId').value=id;
	    document.getElementById("paraFrm").submit();
   }

 
 function validate(){
	   
	  var id =	document.getElementById('empHouse.flag').value ;
	  var empName =document.getElementById('empHouse.empName').value;
	  var houseNO =document.getElementById('empHouse.houseNO').value;
	  var houseColonyID =document.getElementById('empHouse.houseColonyID').value;
	  var status =document.getElementById('empHouse.status').value;
	  
	  
	  if(empName == ""){
  			alert("Please Select Employee Name");
  			return false;
  		}	
  		 if(houseNO == ""){
  			alert("Please Enter House Number");
  			return false;
  		}	
  		 if(houseColonyID == ""){
  			alert("Please Select Colony Name");
  			return false;
  		}	
  		 if(status == "1"){
  			alert("Please Select Status");
  			return false;
  		}		
  		  
	   if(id == "false")
	  {
	  
	  }  else
	  {
	   
	    alert("You can't Insert.Please Update");
	   return false;
	  }
	  	   
	  	   
	  	   if(houseNO == ""){
  			alert("Please Select Employee Name");
  			return false;
  		}	
	  	   
	  	   
	  	   
	  	   
	  	   
	  
   }
   
   function updateValidate(){
	   
	  var id =	document.getElementById('empHouse.flag').value ;
	 
	  if(id == "true")
	  {
	   
	  }  else
	  {
	  alert("You can't Update.Please Insert");
	  return false;
	  }
	  
	   
	  
	  
   }
   


</script>