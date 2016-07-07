 <%@ taglib prefix="s" uri="/struts-tags" %>
 <%@page import="java.util.HashMap" %>
 <s:form action="NonIndustrialSalary" id="paraFrm" theme="simple"  >

<table  class="tableBody" width="100%">
	<tr>
		<td class="pageHeader" colspan="4" ><center>Non-Industrial Salary Audit</center></td>
	</tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td width=25% >&nbsp;</td>
		<td colspan="2" >	
			<table>
					<tr>
						<td >Month <font color="red">*</font>:</td>
						<td><s:select theme="simple" name="nonIndustrialSalary.month" cssStyle="width:152" list="#{'0':'Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}"  /></td>
					</tr>
					 
					<tr>
						<td  >Year <font color="red">*</font>:</td>
						<td><s:textfield name="nonIndustrialSalary.year" onkeypress="return numbersonly(this);"  theme="simple"  maxlength="4" size="25"> </s:textfield></td>
					</tr>
					
					<tr>
							<td >Select Employee Type <font color="red">*</font>:</td>
							<s:hidden name="nonIndustrialSalary.typeCode" />
							<td><s:textfield name="nonIndustrialSalary.typeName" theme="simple" readonly="true"  maxlength="50" size="25"> </s:textfield>
							<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" 	width="18" onclick="javascript:callsF9(500,325,'NonIndustrialSalaryAudit_f9type.action');">	
						</td>
					</tr>
					<tr>
						<td  >Select Pay Bill No <font color="red">*</font>:</td>
							<td><s:textfield name="nonIndustrialSalary.payBillNo" theme="simple" readonly="true" maxlength="50" size="25"> </s:textfield>
							<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'NonIndustrialSalaryAudit_f9payBill.action');">	
						</td>
					</tr>
					 <tr>
						 <td width="45%">&nbsp;</td>
						 <td>
						 	<s:if test="%{nonIndustrialSalary.viewFlag}">
						 		<s:submit cssClass="pagebutton" theme="simple"  onclick="return check()"  action="NonIndustrialSalaryAudit_viewNonIndustrialSalaries" value=" View " />
							</s:if>
						</td>
					</tr> 
				</table>
			</td>
			<td width="25%" >&nbsp;</td>
 		</tr>
<%  Object [][] rows=(Object[][])request.getAttribute("rows");
 		if(rows !=null && rows.length >0){ %>
 		<tr><td colspan="4" >
				 <table  border="0">
				 <tr>
					<td width="90" class="headercell">Token </td>
					<td  width="160" class="headercell">Employee Name </td >
					<td class="headercell">Month</td>
					<s:iterator value="creditHeader">  
						<td align="middle" width="40" class="headercell">
						<s:property value="creditName"/><s:hidden  />
						</td>
					</s:iterator>
					<td  class="headercell">Total Credit</td>
					<s:iterator value="debitHeader">  
						<td align="middle" width="40" class="headercell">
						<s:property value="debitName"/>
						</td>
					</s:iterator>
					<td  class="headercell">Total Debit</td>
					<td  class="headercell">Net Pay</td>
				 </tr>
 		<%for(int k=0;k < rows.length;k++){ %>
   		<tr >
	   		<td><input type="text" size="8" name="tokenNo" value="<%=rows[k][1]%>" ></td>
	   		<td><input type="text" size="24" name="empName" value="<%=rows[k][2] %>" ></td>
	   		<td><input type="text" size="8" name="tokenNo" value="<%=rows[k][3]%>" ></td>
   		<%int col=4; %>
   		<s:iterator value="creditHeader">  
			<td >
				<%if(k!=0 && String.valueOf(rows[k][0]).equals(String.valueOf(rows[k-1][0])) && !(String.valueOf(rows[k][col]).equals(String.valueOf(rows[k-1][col]))) ){ %>
					<input type="text" size="4" title="<%=""+rows[k][2]+"---"+rows[k][3]%>" style="background-color:#66FF00;"  name="<%=k%>" value="<%=String.valueOf(rows[k][col]) %>" >
				<%}else{ %>
					<input type="text" size="4" title="<%=""+rows[k][2]+"---"+rows[k][3]%>" name="<%=k%>" value="<%=String.valueOf(rows[k][col]) %>" >
				<%}%>
			</td>
	 	<%col++; %>
 		</s:iterator>
 			<td>
 				<%if(k!=0 && String.valueOf(rows[k][0]).equals(String.valueOf(rows[k-1][0])) && !(String.valueOf(rows[k][col]).equals(String.valueOf(rows[k-1][col]))) ){ %>
					<input type="text" size="4" title="<%=""+rows[k][2]+"---"+rows[k][3]%>" style="background-color:#66FF00;"    name="totalCredit<%=k%>" value="<%=rows[k][col] %>" >
				<%}else{ %>
					<input type="text" size="4"  title="<%=""+rows[k][2]+"---"+rows[k][3]%>" name="totalCredit<%=k%>" value="<%=rows[k][col] %>" >
				<%}%>
 			</td>
 		<%col++; %>
 		<s:iterator value="debitHeader">  
			<td >
				<%if(k!=0 && String.valueOf(rows[k][0]).equals(String.valueOf(rows[k-1][0])) && !(String.valueOf(rows[k][col]).equals(String.valueOf(rows[k-1][col]))) ){ %>
					<input type="text" size="4" title="<%=""+rows[k][2]+"---"+rows[k][3]%>" style="background-color:#66FF00;"    name="<%=k%>" value="<%=String.valueOf(rows[k][col]) %>" >
				<%}else{ %>
					<input type="text" size="4" title="<%=""+rows[k][2]+"---"+rows[k][3]%>"  name="<%=k%>" value="<%=String.valueOf(rows[k][col]) %>" >
				<%}%>
			</td>
		<%col++; %>
		 </s:iterator>
			 <td>
			 	<%if(k!=0 && String.valueOf(rows[k][0]).equals(String.valueOf(rows[k-1][0])) && !(String.valueOf(rows[k][col]).equals(String.valueOf(rows[k-1][col]))) ){ %>
					<input type="text" size="4"  title="<%=""+rows[k][2]+"---"+rows[k][3]%>" style="background-color:#66FF00;"    name="totalDebit<%=k%>" value="<%=rows[k][col] %>" >
				<%}else{ %>
					<input type="text" size="4" title="<%=""+rows[k][2]+"---"+rows[k][3]%>"  name="totalDebit<%=k%>" value="<%=rows[k][col] %>" >
				<%}%>
			 </td> 
			 <td>
			 	<%if(k!=0 && String.valueOf(rows[k][0]).equals(String.valueOf(rows[k-1][0])) && !(String.valueOf(rows[k][col+1]).equals(String.valueOf(rows[k-1][col+1]))) ){ %>
					<input type="text" size="4" title="<%=""+rows[k][2]+"---"+rows[k][3]%>" style="background-color:#66FF00;"    name="netPay<%=k%>" value="<%=rows[k][col+1] %>" >
				<%}else{ %>
					<input type="text" size="4" title="<%=""+rows[k][2]+"---"+rows[k][3]%>"  name="netPay<%=k%>" value="<%=rows[k][col+1] %>" >
				<%}%>
			 </td>
   		</tr>
	 	<%}
	 		} %>
 </table>
 </td></tr>
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
  
	var month= document.getElementById("nonIndustrialSalary.month").value;
	var year= document.getElementById("nonIndustrialSalary.year").value;
	var type= document.getElementById("nonIndustrialSalary.typeName").value;
	var center= document.getElementById("nonIndustrialSalary.payBillNo").value;
	if(month=='')
	{
		alert('Please Select Valid Month');
		return false;
	}
	if(eval(month)<=0)
	{
		alert('Please Select Valid Month')
		return false;
	}
	if(eval(month)>12)
	{
		alert('Please Select Valid Month')
		return false;
	}
	if(year=='')
	{
		alert("Please Enter Year");
		return false;
	}
	if(eval(year)<1900)
	{
		alert('Please Enter Valid year')
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



 