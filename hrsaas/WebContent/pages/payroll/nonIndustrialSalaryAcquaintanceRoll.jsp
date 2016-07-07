 <%@ taglib prefix="s" uri="/struts-tags" %>
 
 <s:form action="NonIndustrialSalaryAcquaintanceRoll" id="paraFrm" theme="simple"  >

<table width="100%">
	<tr>
		<td class="pageHeader" colspan="4" ><center>Non Industrial Salary Acquaintance Roll</center></td>
	</tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	
<tr><td width=25% >&nbsp;</td>
<td>Month <font color="red">*</font>:</td> 
<td><s:select theme="simple" name="nonIndustrialSalary.month" cssStyle="width:152" list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}"  /></td>
<td width=25% >&nbsp;</td>
</tr>
<tr><td width=25% >&nbsp;</td>
<td >Year <font color="red">*</font>:</td><td><s:textfield name="nonIndustrialSalary.year" onkeypress="return numbersonly(this);"  id="yr" theme="simple"  maxlength="4" size="25"> </s:textfield></td>
<td width=25% >&nbsp;</td>
</tr>



<tr><td width=25% >&nbsp;</td>
<td >Select Employee Type <font color="red">*</font>:</td>
	<s:hidden name="nonIndustrialSalary.typeCode" />
	<td><s:textfield name="nonIndustrialSalary.typeName" theme="simple" readonly="true" id="et" maxlength="50" size="25"> </s:textfield>
	<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" 	width="18" onclick="javascript:callsF9(500,325,'NonIndustrialSalaryAcquaintanceRoll_f9type.action');">	
</td>
<td width=25% >&nbsp;</td>
</tr>
<tr><td width=25% >&nbsp;</td>
<td >Select Pay Bill No<font color="red">*</font> :</td>

	<td><s:textfield name="nonIndustrialSalary.payBillNo" theme="simple" id="cn" readonly="true" maxlength="30" size="25"> </s:textfield>
	<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'NonIndustrialSalaryAcquaintanceRoll_f9payBill.action');">	
</td>
<td width=25% >&nbsp;</td>
</tr>
<tr>

</tr>

 <tr>
 <td colspan="2" >&nbsp;</td>
<td>
	<s:if test="%{nonIndustrialSalary.viewFlag}">
		<input type="button" class="pagebutton" onclick="return check()"  value="Report" />
	</s:if>
		<s:submit cssClass="pagebutton"  theme="simple"    action="NonIndustrialSalaryAcquaintanceRoll_reset" value="Reset" />	 </td>
<td width=25% >&nbsp;</td>
</tr>
</table>
</s:form>
<script>
function sum(cLen,k) {
		 
		var formElements=document.getElementsByName(k);
		var creditAmount=0;
		var debitAmount=0;

//	 alert(creditAmount+debitAmount);
 	//loop through the array  
 	 
 	 
 	
 	for (var i=formElements.length-1; i>=0; --i ){
 		 if(i<cLen)
 		 {
			creditAmount=creditAmount+eval(formElements[i].value);

		}
		else
		{
				debitAmount=debitAmount+eval(formElements[i].value);
		} 		
 	}
  
 	  
 	var totalCredit=totalCredit+k;
 	
	document.getElementById("totalCredit"+k).value=creditAmount;
	document.getElementById("totalDebit"+k).value=debitAmount; 
	document.getElementById("netPay"+k).value=creditAmount-debitAmount; 
		 
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
	if ((("0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}
 
 
 function check()
 {
  
	var month= document.getElementById("nonIndustrialSalary.month").value;
	var year= document.getElementById("paraFrm").yr.value;
	var type= document.getElementById("paraFrm").et.value;
	var center= document.getElementById("paraFrm").cn.value;
	if(month=='0')
	{
		alert('Please Enter Month');
		return false;
	}
	if(year=='')
	{
		alert("Please Enter Year");
		return false;
	}
	
	if(year.length!=4)
	{
		alert("Please Enter Valid Year");
		return false;
	}
	if(type=="")
	{
	alert("Please Select Employee Type");
	return false;
	}
	if(center=="")
	{
	alert("Please Select Pay Bill No");
	return false;
	}
	else
	{
	document.getElementById('paraFrm').target="_blank";	
		document.getElementById('paraFrm').action="NonIndustrialSalaryAcquaintanceRoll_viewNonIndustrialSalaries.action";	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";
	}
 }
function getYear(){
	var current = new Date();
	 var year =current.getYear();
	 var yr =document.getElementById("nonIndustrialSalary.year").value;
	 if(yr==''){
	  	document.getElementById("nonIndustrialSalary.year").value =year;
	  }
}
getYear();
	
</script>



 