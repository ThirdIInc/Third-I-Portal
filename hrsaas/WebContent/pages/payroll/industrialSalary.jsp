 <%@ taglib prefix="s" uri="/struts-tags" %>
 
 <s:form action="IndustrialSalary" id="paraFrm" theme="simple"  >

<table  class="tableBody" width="100%">
	<tr>
		<td class="pageHeader" colspan="4" ><center>Industrial Salary</center></td>
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
				<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle" 	width="18" onclick="javascript:callsF9(500,325,'IndustrialSalary_f9type.action');">	
			</td>
			</tr>
			<tr>
				<td  >Select Pay Bill No <font color="red">*</font>:</td>
				<td><s:textfield name="industrialSalary.payBillNo" theme="simple" id="cn" readonly="true" maxlength="50" size="25"> </s:textfield>
				<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'IndustrialSalary_f9payBill.action');">	
			</td>
			</tr>
			<tr>
			
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			 <tr>
				 <td colspan="2" >
				 	<s:if test="%{industrialSalary.viewFlag}">
				 		<s:submit cssClass="pagebutton"  theme="simple"  onclick="return check();"  action="IndustrialSalary_viewNonIndustrialSalaries" value="View" />
				 	</s:if>
				 	<s:if test="%{industrialSalary.insertFlag}">
						<s:submit cssClass="pagebutton"  theme="simple" onclick="return check();"   action="IndustrialSalary_getNonIndustrialSalaries" value="Salary Process" />
						<s:submit cssClass="pagebutton"  theme="simple" onclick="return check();"   action="IndustrialSalary_processRecovery" value="Process Recovery" />
						<s:if test="debitHeader" ><s:submit cssClass="pagebutton" theme="simple" onclick="return check();"   action="IndustrialSalary_saveNonIndustrialSalaries" value="Save" /></s:if>
						<s:submit cssClass="pagebutton" theme="simple" onclick="return check();"   action="IndustrialSalary_lockNonIndustrialSalaries" value="Lock" />
					</s:if>
				 </td>
			</tr> 
		</table>
		</td>
	<td width="25%" >&nbsp;</td>
 </tr>
 <tr><td colspan="4" >
	<s:if test="debitHeader" >
		 <table  border="0">
			 <tr>
				<td width="90" class="headercell">Token No
				</td>
				<td  width="160" class="headercell">Employee Name
				</td>
				<s:iterator value="creditHeader">  
				<%int g=0; %>
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
				<td  class="headercell">OT Amount </td>
				<td  class="headercell">Net Pay</td>

			 </tr>   
		
	  <% 
	  Object [][] rows=(Object[][])request.getAttribute("rows");
	  Object [][] c=(Object[][])request.getAttribute("creditLength");
	  %><%! int k=0; %>
	  <%for ( k=0;k<rows.length;k++) { %>
		 <tr><input type="hidden"  name="emp_id" value="<%=rows[k][0] %>" >
			 <td width="90"   align="middle" ><input type="text" size="8" name="tokenNo" value="<%=rows[k][1] %>" >
			 </td>
			  <td  width="160"  ><input type="text" size="24" name="empName" value="<%=rows[k][2] %>" >
			 </td><%int i=3; %>
			
			<s:iterator value="creditHeader">  
			
			<td align="middle" width="40"  >
			<input type="text" size="4" name="<%=k%>" value="<%=String.valueOf(rows[k][i]) %>"   onkeyup="sum(<%=c.length %>,<%=k%>)" style="text-align:right"  >
			</td>
			 <%i++; %>
			 </s:iterator>
			<td> <input type="text" size="6" name="totalCredit<%=k%>" value="<%=rows[k][i] %>" style="text-align:right" ></td>
			<s:iterator value="debitHeader">  
			<%i++; %> 
			<td align="middle" width="40"  >
			<input type="text" size="4" name="<%=k%>" value="<%=rows[k][i] %>"   onkeyup="sum(<%=c.length %>,<%=k%>)" style="text-align:right" >
			</td>
			
			 </s:iterator>
			<td> <input type="text" size="6"  name="totalDebit<%=k%>" value="<%=rows[k][i+1] %>"  style="text-align:right"  readonly="true"></td> 
			<td> <input type="text" size="6" name="otAmount<%=k%>" value="<%=rows[k][i+2] %>"  style="text-align:right"  readonly="true"></td> 
			<td> <input type="text" size="6" name="netPay<%=k%>" value="<%=rows[k][i+3] %>"   style="text-align:right" readonly="true"></td> 
			
		 </tr>
	<% }%>
		 </table>
	 </s:if>
  </td></tr>
 </table>
</s:form>
<script>
function sum(cLen,k) {
		 
		var formElements=document.getElementsByName(k);
		var creditAmount=0;
		var debitAmount=0;
		
 	for (var i=formElements.length-1; i>=0; --i ){
 		 if(i<cLen)
 		 {
 		 	var values=formElements[i].value;
 		 	if(values ==''){
 		 		values =0;
 		 	}
			creditAmount=creditAmount+eval((values*100)/100);

		}
		else
		{
			var values=formElements[i].value;
 		 	if(values ==''){
 		 		values =0;
 		 	}
				debitAmount=debitAmount+eval((values*100)/100);
		} 		
 	}
  
 	var totalCredit=totalCredit+k;
 	var otAmount =document.getElementById("otAmount"+k).value
	document.getElementById("totalCredit"+k).value=creditAmount;
	document.getElementById("totalDebit"+k).value=debitAmount; 
	
	document.getElementById("netPay"+k).value=(eval(creditAmount*100/100)+eval(otAmount*100/100))-eval(debitAmount*100/100); 
		 
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



 