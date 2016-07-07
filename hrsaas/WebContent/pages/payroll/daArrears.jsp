
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="DaArrear" id="paraFrm" theme="simple">
	<table width="100%">
		<tr>
			<td class="pageHeader" colspan="4">
			<center>DA Arrear</center>
			</td>
		</tr>
		<tr>
			<td colspan="4" width="100%">&nbsp;</td>
		</tr>

		<tr>
			<td colspan="1" width="25%">&nbsp;</td>
			<td colspan="1" width="25%">DA Date<font color="red">*</font> :</td>
			<td colspan="2" width="50%"><s:hidden name="daArrear.daCode" />
			<s:hidden name="daArrear.daRate" /> <s:hidden name="daArrear.daFlag" />
			<s:textfield name="daArrear.daDate" theme="simple" id="fld" size="30"
				readonly="true">
			</s:textfield> <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'DaArrear_f9action.action');">
			</td>
		</tr>
		<tr>
			<td colspan="1" width="25%">&nbsp;</td>
			<td colspan="1" width="25%">Select Employee Type <font
				color="red">*</font>:</td>
			<s:hidden name="daArrear.typeCode" />
			<td colspan="2" width="50%"><s:textfield
				name="daArrear.typeName" readonly="true" theme="simple" id="et"
				maxlength="40" size="30">
			</s:textfield> <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'DaArrear_f9type.action');">
			</td>
		</tr>
		<tr>
			<td colspan="1" width="25%">&nbsp;</td>
			<td colspan="1" width="25%"><font color="red"></font>Select
			Pay Bill No :<font color="red">*</font></td>

			<td colspan="2" width="50%"><s:hidden name="daArrear.payBillNo"
				value="%{daArrear.payBillNo}"></s:hidden> <s:textfield
				name="daArrear.payBillName" theme="simple" readonly="true" id="pb"
				maxlength="40" size="30">
			</s:textfield> <img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"
				width="18"
				onclick="javascript:callsF9(500,325,'DaArrear_f9payBill.action');">
			</td>
		</tr>
		<tr>
			<td colspan="4" width="100%">&nbsp;</td>
		</tr>
		<tr align="center" >
			
			<td align="center" colspan="4" ><s:submit cssClass="pagebutton" theme="simple" onclick="return check()"
				action="DaArrear_view" value="  View  " />&nbsp;<s:submit cssClass="pagebutton"
				theme="simple" action="DaArrear_calculate" value=" Calculate " onclick="return check()"/>&nbsp;<s:if
				test="daArrear.saveFlag">
				<s:submit cssClass="pagebutton" theme="simple" action="DaArrear_save" value="  Save  " />
			</s:if> &nbsp; <s:if test="daArrear.lockFlag">

				<s:submit cssClass="pagebutton" theme="simple" action="DaArrear_lock" value="  Lock  " />
			</s:if></td>

		</tr>
	</table>

	<s:if test="daList">
		<table width="100%">
			<tr>
				<td class="headercell" width="5%" nowrap="nowrap">Sr. No.</td>
				<td class="headercell" width="10%">Employee Token</td>
				<td class="headercell" width="25%">Employee Name</td>
				<td class="headercell" width="10%">Month</td>
				<td class="headercell" width="10%">Year</td>
				<td class="headercell" width="10%">Paid DA</td>
				<td class="headercell" width="10%">Due DA</td>
				<td class="headercell" width="20%">Difference</td>
			</tr>
			<%!
			int i = 1,j=0;
			%>
			
			<%!int p = 0, s = 0;%>
			<s:iterator value="daList">


				<tr>
					<td align="center" class="bodyCell" nowrap="nowrap" width="5%"><%=i%>
					<%
					i++;
					%>
					</td>
					<td class="bodyCell" width="10%"><s:property value="empToken" /></td>
					<td class="bodyCell" width="25%"><s:hidden name="empId" /><s:property
						value="empName" /></td>

					<td class="bodyCell" width="10%"><s:hidden name="daMonth" />
					<s:property value="daMonthName" /></td>
					<td class="bodyCell" width="10%"><s:textfield name="daYear"
						size="5" /></td>
					<td align="center" class="bodyCell" width="10%"><input type="text"
						name="paidDa" value='<s:property value="paidDa"/>'
						id="paidDa<%=j%>" size="12" onkeyup="sumPreAmt(<%=j%>)"
						style="text-align: right" onkeypress="return numbersonly(this);"  readonly/></td>
					<td align="center" class="bodyCell" width="10%">
					<input type="text"
						name="dueDa" value='<s:property value="dueDa"/>'
						id="dueDa<%=j%>" size="12" onkeyup="sumPreAmt(<%=j%>)"
						style="text-align: right" onkeypress="return numbersonly(this);" />
					
					</td>
					<td align="center" class="bodyCell" width="20%"><input type="text"
						name="diff" value='<s:property value="diff"/>'
						id="diff<%=j%>" size="12" onkeyup="sumPreAmt(<%=j%>)"
						style="text-align: right" onkeypress="return numbersonly(this);" readonly/>
					</td>

				</tr>
				<%
					p++;
					j++;
				%>
			</s:iterator>
				<%
				s = p;
				p = 0;
			%>
		</table>
	</s:if>
	<s:hidden name="sysDate" />
</s:form>
<script>

   
    	 function check() {
    	  var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
  		 
    	  	var fld= document.getElementById("paraFrm").fld.value;
    	  
    	 	var type= document.getElementById('daArrear.typeCode').value;
			var center= document.getElementById('daArrear.payBillNo').value;
	    if ((fld.match(RegExPattern)) && (fld!='')) {
    
   			 } else {
       				 alert('Please Select DA Date !');
       				  document.getElementById("paraFrm").fld.value=""; 
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

function numbersonly(myfield)
{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789.").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}


function sumPreAmt(row)
{
//alert('row number'+row);
var totalrow =<%=s%> ;

	
	var dueDa=document.getElementById('dueDa'+row).value;
	var paidDa=document.getElementById('paidDa'+row).value;
	
	var newtotalot=eval(dueDa)-eval(paidDa)
	//alert('total'+newtotalot)
	
	//alert('total amount'+totalot);
	//alert('total amount'+newtotalot);
	document.getElementById('diff'+row).value=newtotalot;
	//var dueRound=Math.round()
	//document.getElementById('duePay'+row).value=Math.round(newtotalot-totalot);
	
	
	
//var debit=document.getElementById('preCommAmt'+s).value;

}
 </script>
