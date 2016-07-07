<!-- JSP file for front end for Salary Ledger -->

<%@taglib uri="/struts-tags" prefix="s"%>

<s:form action="SalaryLedger" id="paraFrm"	validate="true" theme="simple">
	<table class="bodyTable" width="100%" align="center">	

	<tr>
			<td class="pageHeader" colspan="4">
				<center>Salary Ledger</center>
			</td>
	</tr>
	
	<tr>
		<td colspan="4">&nbsp;</td>	
	</tr>	
	
			
	<tr>
	 <td width="25%" >&nbsp;</td>
		<td>Month<font color="red">*</font> :</td>
		<td>
			<s:select  name="month"  list="#{'':'------------Select------------','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}"  theme="simple" />		
		</td>
		<td width="25%" >&nbsp;</td>
    </tr>
	<tr>
   	        <td width="25%" >&nbsp;</td>
			<td >Year<font color="red">*</font>&nbsp;&nbsp;&nbsp; :</td>
			<td>
				<s:textfield label="%{getText('year')}"
				name="year" size="25" maxlength="4" onkeypress="return numbersonly(this)" />
		</td>
		<td width="25%" >&nbsp;</td>
	</tr>
	<tr>
		<td colspan="4">&nbsp;</td>	
	
	</tr>
	<tr align="center">
  		<td colspan="4">
  			<center>
		  		<s:if test="%{sl.viewFlag}">
		  				<s:submit cssClass="pagebutton" action="SalaryLedger_go" 	theme="simple" value="   Go   "  onclick="return formValidate();" />
		  		</s:if>
						<s:submit cssClass="pagebutton"  action="SalaryLedger_reset" value="Reset" theme="simple" />
			</center>
  		</td>
  		
  	</tr>
  	<tr>
       <td>&nbsp;</td>
	</tr>
	 <tr>
	     <td>&nbsp;</td>
	 </tr>
  	</table>
  	<s:if test="flag">
   	<table border="0" class="bodyTable" width="100%">
		<tr><td width="30%" class="headerCell">Employee Type</td>		
			<td width="30%" class="headercell">Pay Bill Group</td>
			<td width="30%" class="headercell">Status</td>
			<td width="10%%" class="headercell">&nbsp;</td>
		</tr>
		<s:iterator value="salList" status="stat">
		<tr><td width="30%" class="bodyCell"><s:property value="empType" /></td>
			<td width="30%" class="bodyCell"><s:property value="payGrp" /></td>
			<td width="30%" class="bodyCell"><s:property value="status" /></td>
			<s:if test="chkStatus">
			<td width="10%" class="bodyCell"><input type="button" class="pagebutton"  value="View Salary Process "  disabled="true" onclick="callSalary('<s:property value="payBillNo" />','<s:property value="typeCode" />','<s:property value="empType" />');"  />
			</s:if><s:else>
			<td width="10%" class="bodyCell"><input type="button" class="pagebutton"  value="View Salary Process" onclick="callSalary('<s:property value="payBillNo" />','<s:property value="typeCode" />','<s:property value="empType" />');"  /> </td>
			</s:else>
	    </tr>
	</s:iterator>
	</table>	
	</s:if>
	<s:hidden name="nonIndustrialSalary.payBillNo" />
	<s:hidden name="nonIndustrialSalary.typeCode" />
	<s:hidden name="nonIndustrialSalary.typeName" />
	<s:hidden name="industrialSalary.payBillNo" />
	<s:hidden name="industrialSalary.month" />
	</s:form>

	<script>
      function formValidate() {
      
      if(document.getElementById("month").value=="") {
  			alert("Please select the Month");
  			return false;
  			}
  			 if(document.getElementById("year").value==""){
  			alert("Please enter the Year");
  			return false;
  			}
      
    else {
      
      return true;
      }

}
function numbersonly(myfield)
{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789").indexOf(keychar) > -1)) {
		return true;
		}	
	else {
		myfield.focus();
		return false;
	}
}		
  function callSalary(id,typeCode,empType){
  
		var yr =document.getElementById('year').value;
		var month =document.getElementById('month').value;
		
	
			if(typeCode=="1") {
			document.getElementById('industrialSalary.month').value=month;
			document.getElementById('industrialSalary.payBillNo').value=id;
			document.getElementById('paraFrm').action="IndustrialSalary_input.action";
			
			}	else {
			document.getElementById('nonIndustrialSalary.typeName').value=empType;
			document.getElementById('nonIndustrialSalary.typeCode').value=typeCode;
			document.getElementById('nonIndustrialSalary.payBillNo').value=id;
			document.getElementById('paraFrm').action="NonIndustrialSalary_input.action";
			}
	
		
			document.getElementById('paraFrm').target='main';
			document.getElementById('paraFrm').submit();
		
  		 
  			}
  
  function getYear(){
	var current = new Date();
	 var year =current.getYear();
	 var yr =document.getElementById("year").value;
	 if(yr==''){
	  	document.getElementById("year").value =year;
	  }
}
getYear();
  

</script>
	
	
	
	
	
		
	
  			
 