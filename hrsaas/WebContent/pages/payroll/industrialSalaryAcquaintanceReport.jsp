 <%@ taglib prefix="s" uri="/struts-tags" %>
 
 <s:form action="IndustrialSalaryReport" id="paraFrm"  theme="simple" >

<table  class="tableBody" width="100%">
	<tr>
		<td class="pageHeader" colspan="4" ><center>Industrial Salary Report</center></td>
	</tr>
	<tr><td colspan="4">&nbsp;</td></tr>
 <tr><td width=25% >&nbsp;</td>
	<td colspan="2" >
		<table>
			<tr>
				<td >Month <font color="red">*</font>:</td>  
				<td><s:select theme="simple" name="industrialSalary.month" cssStyle="width:152" theme="simple" list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}"  /></td>
			</tr>
			<tr>
				<td  >Year <font color="red">*</font>:</td>
				<td><s:textfield name="industrialSalary.year" onkeypress="return numbersonly(this);"  id="yr" theme="simple"  maxlength="4" size="25"> </s:textfield></td>
			</tr>
			
			
			
			<tr>
				<td >Select Employee Type <font color="red">*</font>:</td>
				<s:hidden name="industrialSalary.typeCode" />
				<td><s:textfield name="industrialSalary.typeName" theme="simple" readonly="true" id="et" maxlength="50" size="25"> </s:textfield>
				<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" 	width="18" onclick="javascript:callsF9(500,325,'IndustrialSalaryReport_f9type.action');">	
			</td>
			</tr>
			<tr>
				<td  >Select Pay Bill No <font color="red">*</font>:</td>
				<td><s:textfield name="industrialSalary.payBillNo" theme="simple" id="cn" readonly="true" maxlength="50" size="25"> </s:textfield>
				<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'IndustrialSalaryReport_f9payBill.action');">	
			</td>
			</tr>
			<tr>
			
			</tr>
			
			 <tr>
			 <td width="45%">&nbsp;</td>
			<td>
				<s:if test="%{industrialSalary.viewFlag}">
					<s:submit cssClass="pagebutton"  theme="simple"  onclick="return check()"  action="IndustrialSalaryReport_report"  value=" Report " />
				</s:if>
					<s:submit cssClass="pagebutton"  theme="simple"  action="IndustrialSalaryReport_clear" value="Reset" />
			</td>
			</tr>
		</table>
	</td>
	<td width="25%" >&nbsp;</td>
 </tr>
 </table>
</s:form>
<script>
 
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
  
	 var month =document.getElementById("industrialSalary.month").value;
	 var year =document.getElementById("industrialSalary.year").value;
	 var type =document.getElementById("industrialSalary.typeName").value;
	 var paybill =document.getElementById("industrialSalary.payBillNo").value;
	 
	 if(month =='0'){
	 	alert('Select month !');
	 	return false;
	 }
	 if(year ==''){
	 	alert('Enter year !');
	 	return false;
	 }
	 if(type ==''){
	 	alert('Select employee type!');
	 	return false;
	 }
	 if(paybill ==''){
	 	alert('Select paybill group!');
	 	return false;
	 }
	
	return true;
 }
 function getYear(){
	var current = new Date();
	 var year =current.getYear();
	 var yr =document.getElementById("industrialSalary.year").value;
	 if(yr==''){
	  	document.getElementById("industrialSalary.year").value =year;
	  }
}
getYear();
</script>



 