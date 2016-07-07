  <%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="EmpChangeHistory" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="bodyTable" width="100%">
		<tr>
			<td width="100%" colspan="4" class="pageHeader" align="center">Employee
			Change History</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td width="25%">Select Employee<font color="red">*</font> :</td>
			<td width="75%" colspan="3"><s:if
				test="%{emphistory.generalFlag}">
				</s:if> <s:else>
				<s:if test="emphistory.viewFlag"><img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
					width="18"
					onclick="javascript:callsF9(500,325,'EmpChangeHistory_f9empaction.action');" /></s:if>
			</s:else> <s:hidden name="emphistory.empID" /> <s:textfield
				readonly="true" name="emphistory.employeeToken" theme="simple"
				id="employeeToken" size="10" /><s:textfield  readonly="true" 
				name="emphistory.empName" size="81" /></td>
		</tr>
		
		<tr>
			<td width="25%" colspan="1">Center:</td>
			<td width="25%" colspan="1"><s:textfield size="30"
				readonly="true" name="emphistory.center" /></td>
			<td width="25%" colspan="1">Rank:</td>
			<td width="25%" colspan="1"><s:textfield size="30"
				readonly="true" name="emphistory.rank" /></td>

		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
			
		</tr>
		<tr>
			<td colspan="4" class="sectionhead">Change History Particulars</td>
			
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
			
		</tr>
		<tr>
			<td width="25%" colspan="1">Change Type <font color="red">*</font>:</td>

			<td width="25%" colspan="1"><s:select name="emphistory.type"
				id="sta" headerKey="1" headerValue="--Select--" cssStyle="width:157"
				list="#{'F':'First Name','M':'Middle Name','L':'Last Name','D':'Date of Birth','T':'Token No','H':'Home Town'}" /></td>
			<td colspan="2" width="25%"><s:submit cssClass="pagebutton"
				action="EmpChangeHistory_dropDown" value=" Go "
				onclick="return call()" /> <s:hidden name="emphistory.cityID" /> <s:hidden
				name="emphistory.cityName" /></td>
		<tr>
			<td width="25%">Old Value :
			<td width="25%"><s:textfield name="emphistory.oldValue"
				size="30" readonly="true" /></td>
				
				
				 <s:if test="flagGen">
              <td width="25%">New Value <font color="red">*</font> :</td>
			  <td width="25%"><s:textfield name="emphistory.newValue"
				size="30" />				 
			 </td> </s:if>  
			 
			  <s:if test="flagType">
                                         <td width="25%">New Value <font color="red">*</font>: </td>
			  <td width="25%"><s:textfield name="emphistory.newValue"
				size="25" />
				 
				 <s:a
				href="javascript:NewCal('emphistory.newValue','DDMMYYYY');">

				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16">
			</s:a>
				 
			 </td> </s:if> 
				
				
				
				
				
			  <s:if test="flagList">
             <td width="25%">New Value <font color="red">*</font>: </td>
			<td width="25%"><s:textfield name="emphistory.newValue"
				size="25" />
				<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
					width="18"
					onclick="javascript:callsF9(500,325,'EmpChangeHistory_f9cityaction.action');" />
			</td></s:if> 
		</tr>

		<tr>
			<td width="25%">Authority Number<font color="red">*</font> :</td>
			<td width="25%"><s:textfield name="emphistory.authNO" size="30" /></td>
			<td width="25%">Authority Date<font color="red">*</font> :</td>
			<td width="25%"><s:textfield name="emphistory.authDate"
				size="25" /> <s:a
				href="javascript:NewCal('emphistory.authDate','DDMMYYYY');">

				<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
					width="16">
			</s:a></td>
		</tr>

		<tr>
			<td colspan="4">&nbsp</td>

		</tr>




		<tr>
		
			<td width="100%" align="center" colspan="4">
			  <s:if test="emphistory.insertFlag">
  		      <s:if test="emphistory.updateFlag"> 
			   <s:submit cssClass="pagebutton"
				action="EmpChangeHistory_save" value=" Save " onclick="return callSave(); " /> 
			</s:if>
			</s:if>	
				&nbsp; <s:submit cssClass="pagebutton"
				action="EmpChangeHistory_reset" value=" Reset " />
				</td>

		</tr>

		<tr>
			<td colspan="4">&nbsp</td>

		</tr>



		<tr>
			<td class="headercell" width="25%">Change Type</td>
			<td class="headercell" width="25%">Old Value</td>
			<td class="headercell" width="25%">New Value</td>
			<td class="headercell" width="25%">Change Date</td>
		</tr>

		<s:iterator value="emphistory.tableList" status="stat">
			<tr>
				<%
				System.out.println("------------------------------");
				%>
				<td class="bodyCell" width="25%"><s:property value="type" /></td>
				<td class="bodyCell" width="25%"><s:property value="oldValue" /></td>
				<td class="bodyCell" width=25%"><s:property value="newValue" /></td>
				<td class="bodyCell" width="25%"><s:property value="authDate" /></td>

			</tr>
		</s:iterator>






	</table>
</s:form>
 <script>
 
   function callSave(){ 
	 	 
  		var empName =document.getElementById('emphistory.empName').value;
  		var type =document.getElementById('emphistory.type').value;
  		var newValue =document.getElementById('emphistory.newValue').value;
  		var authorNo =document.getElementById('emphistory.authNO').value;
  		var authorDate =document.getElementById('emphistory.authDate').value;
  		
  		if(empName == ""){
  			alert("Please Select Employee Name");
  			return false;
  		}	
  		
  		
  		if(type == "1"){
  			alert("Please Select Change Type ");
  			return false;
  		}	
  		
  		if(newValue == ""){
  			alert("Please Enter New Value ");
  			return false; 
  		}	
  			
  			 if(newValue != ""){
  			 
  			var iChars = "`~!@#$%^&*()+=[]\\\';,/{}|\"<>?";
		  			for (var i = 0; i < newValue.length; i++)
		  			 {		  	
			  		if (iChars.indexOf(newValue.charAt(i)) != -1)
			  	 	{
				  	alert ("Please Enter Charter OR Number Only !");
				  	return false;
				  	
  					  }
  					}
  			 
  		}	
  			
  			 
  		
  		if(authorNo == ""){
  			alert("Please Enter Author Number");
  			return false;
  		}	
  		
  		
  		if(authorDate == ""){
  			alert("Please Enter Author Date ");
  			return false;
  		}		
  		    
		}
		
		
		function call(){ 
 
  		var empName =document.getElementById('emphistory.empName').value;
  		var type =document.getElementById('emphistory.type').value;
  		 	 
  		
  		if(empName == ""){
  			alert("Please Select Employee Name");
  			return false;
  		}	
  		
  		
  		if(type == "1"){
  			alert("Please Select Change Type ");
  			return false;
  		}	
  		
  	 
  		  
  		  
  		 
  			
		}
		
		 
 
 </script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>


 




 