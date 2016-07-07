<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/payrollAjax.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/superTables.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/syncscroll.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="CashProcess" id="paraFrm" theme="simple">
	<s:hidden name="nonIndustrialSalary.cashCode" />
	<s:hidden name="nonIndustrialSalary.typeCode" />
	<s:hidden name="nonIndustrialSalary.payBillNo" />
	<s:hidden name="nonIndustrialSalary.branchCode" />
	<s:hidden name="nonIndustrialSalary.deptCode" />
	<s:hidden name="nonIndustrialSalary.divisionCode" />
	<s:hidden name="attCode" />
	<s:hidden name="status" />
	<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Cash
					Process</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="0" >
				<tr>
					<td><!--<s:if test="%{nonIndustrialSalary.viewFlag}">
								<s:submit cssClass="pagebutton" theme="simple"
								onclick="return check();"
								action="NonIndustrialSalary_viewNonIndustrialSalaries"
								value="View" />
							</s:if>
							--> <input type="button" class="search" value="   Search "
						onclick="javascript:callsF9(500,325,'CashProcess_f9cashProcess.action');" />

					<s:if test="%{insertFlag}">
						<s:submit cssClass="token" theme="simple"
							onclick="return check('P');" action="CashProcess_getCashProcess"
							value="Process" />


						<s:if test="creditHeader">
							<s:submit cssClass="save" theme="simple"
								onclick="return check('S');"
								action="CashProcess_saveCashProcess" value="    Save" />


							<s:submit cssClass="token" theme="simple"
								onclick="return check('F');" action="CashProcess_finalBalance"
								value="  Finalise Balance " />


						</s:if>

					</s:if> <s:submit cssClass="reset" theme="simple"
						action="CashProcess_reset" value="    Reset" /></td>
					<td align="right"><font color="red">*</font>Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
		<td>
		<table width="100%" class="formbg">
			<tr>
								<td>&nbsp;</td>
								<td width="5%"><label class="set" id="frmdate"
									name="frmdate" ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label><font
									color="red">*</font>:</td>
								<td><s:textfield name="nonIndustrialSalary.frmDate"
									maxlength="10" theme="simple"
									onblur="return validateDate('paraFrm_nonIndustrialSalary_frmDate','From Date');"
									onkeypress="return numbersWithHiphen();" /> <s:a
									href="javascript:NewCal('paraFrm_nonIndustrialSalary_frmDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="18" align="absmiddle" width="18">
								</s:a></td>
								<td><label class="set" id="todate" name="todate"
									ondblclick="callShowDiv(this);"><%=label.get("todate")%></label><font
									color="red">*</font>:</td>
								<td><s:textfield name="nonIndustrialSalary.toDate"
									maxlength="10" theme="simple"
									onblur="return validateDate('paraFrm_nonIndustrialSalary_toDate','From Date');"
									onkeypress="return numbersWithHiphen();" /> <s:a
									href="javascript:NewCal('paraFrm_nonIndustrialSalary_toDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="18" align="absmiddle" width="18">
								</s:a></td>

								<s:if test="%{emptypeFlag}">
									<tr>
										<td width="16%">&nbsp;</td>
										<td width="22%"><label class="set" id="employee"
											name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
										<font color="red">*</font>:</td>

										<td><s:textfield name="nonIndustrialSalary.typeName"
											theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
											test="%{nonIndustrialSalary.generalFlag}">
										</s:if> <s:else>
											<img src="../pages/images/search2.gif" class="iconImage"
												height="16" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'CashProcess_f9type.action');">
										</s:else></td>
									</tr>
								</s:if>

								<s:else>
									<tr style="display: none;">
										<td width="15%">&nbsp;</td>
										<td width="30%"><label class="set" id="employee"
											name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
										<font color="red">*</font>:</td>

										<td><s:textfield name="nonIndustrialSalary.typeName"
											theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
											test="%{nonIndustrialSalary.generalFlag}">
										</s:if><s:else>
											<img src="../pages/images/search2.gif" class="iconImage"
												height="18" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'CashProcess_f9type.action');">
										</s:else></td>
									</tr>
								</s:else>
								<s:if test="%{paybillFlag}">
									<tr>
										<td width="15%">&nbsp;</td>
										<td width="20%"><label class="set" id="pay.bill"
											name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
										<font color="red">*</font>:</td>

										<td><s:textfield name="nonIndustrialSalary.payBillName"
											theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
											test="%{nonIndustrialSalary.generalFlag}">
										</s:if><s:else>
											<img src="../pages/images/search2.gif" class="iconImage"
												height="18" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'CashProcess_f9payBill.action');">
										</s:else></td>
									</tr>
								</s:if>
								<s:else>
									<tr style="display: none;">
										<td width="15%">&nbsp;</td>
										<td width="20%"><label class="set" id="pay.bill"
											name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
										<font color="red">*</font>:</td>

										<td><s:textfield name="nonIndustrialSalary.payBillName"
											theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
											test="%{nonIndustrialSalary.generalFlag}">
										</s:if><s:else>
											<img src="../pages/images/search2.gif" class="iconImage"
												height="18" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'CashProcess_f9payBill.action');">
										</s:else></td>
									</tr>
								</s:else>
								<s:if test="%{departmentFlag}">
									<tr>
										<td width="15%">&nbsp;</td>
										<td width="20%"><label class="set" id="department"
											name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label><font
											color="red">*</font>:</td>
										<td><s:textfield name="nonIndustrialSalary.deptName"
											theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
											test="%{nonIndustrialSalary.generalFlag}">
										</s:if><s:else>
											<img src="../pages/images/search2.gif" class="iconImage"
												height="18" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'CashProcess_f9Dept.action');">
										</s:else></td>
									</tr>
								</s:if>
								<s:else>
									<tr style="display: none;">
										<td width="15%">&nbsp;</td>
										<td width="20%"><label class="set" id="department"
											name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label><font
											color="red">*</font>:</td>
										<td><s:textfield name="nonIndustrialSalary.deptName"
											theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
											test="%{nonIndustrialSalary.generalFlag}">
										</s:if><s:else>
											<img src="../pages/images/search2.gif" class="iconImage"
												height="18" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'CashProcess_f9Dept.action');">
										</s:else></td>
									</tr>
								</s:else>
								<s:if test="%{branchFlag}">
									<tr>
										<td width="15%">&nbsp;</td>
										<td width="22%"><label class="set" id="branch"
											name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
										<font color="red">*</font>:</td>

										<td><s:textfield name="nonIndustrialSalary.branchName"
											theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
											test="%{nonIndustrialSalary.generalFlag}">
										</s:if><s:else>
											<img src="../pages/images/search2.gif" class="iconImage"
												height="18" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'CashProcess_f9Branch.action');">
										</s:else></td>
									</tr>
								</s:if>
								<s:else>
									<tr style="display: none;">
										<td width="15%">&nbsp;</td>
										<td width="22%"><label class="set" id="branch"
											name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font
											color="red">*</font>:</td>

										<td><s:textfield name="nonIndustrialSalary.branchName"
											theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
											test="%{nonIndustrialSalary.generalFlag}">
										</s:if><s:else>
											<img src="../pages/images/search2.gif" class="iconImage"
												height="18" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'CashProcess_f9Branch.action');">
										</s:else></td>
									</tr>
								</s:else>

								<s:if test="%{divisionFlag}">
									<tr>
										<td width="15%">&nbsp;</td>
										<td width="22%"><label class="set" id="division"
											name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
											color="red">*</font>:</td>

										<td><s:textfield name="nonIndustrialSalary.divisionName"
											theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
											test="%{nonIndustrialSalary.generalFlag}">
										</s:if><s:else>
											<img src="../pages/images/search2.gif" class="iconImage"
												height="18" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'CashProcess_f9Div.action');">
										</s:else></td>

									</tr>
								</s:if>
								<s:else>
									<tr style="display: none;">
										<td width="15%">&nbsp;</td>
										<td width="22%"><label class="set" id="division"
											name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
										<font color="red">*</font>:</td>

										<td><s:textfield name="nonIndustrialSalary.divisionName"
											theme="simple" readonly="true" maxlength="50" size="25" /> <s:if
											test="%{nonIndustrialSalary.generalFlag}">
										</s:if><s:else>
											<img src="../pages/images/search2.gif" class="iconImage"
												height="18" align="absmiddle" width="18"
												onclick="javascript:callsF9(500,325,'CashProcess_f9Div.action');">
										</s:else></td>

									</tr>
								</s:else>
						</table>
						</td>
					</tr>
			<tr>
			<td>
			<s:if test="creditHeader">
				<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2" class="formbg" id="thetable">
					<tr>

						<td><input class="token" readonly="readonly"
							style="text-align: center; border: none; margin: 1px" type="text"
							size="8" value="Employee Id" /></td>
						<td><input class="token" readonly="readonly"
							style="text-align: center; border: none; margin: 1px;"
							type="text" size="24" value="Employee Name" /></td>
						<s:iterator value="creditHeader">
							<td><input class="token" type="text" size="4"
								readonly="readonly"
								style="text-align: center; border: none; margin: 1px"
								value="<s:property value="creditName" />" /></td>
						</s:iterator>
						<td><input class="token" type="text" size="8" 
							style="text-align: center; border: none; margin: 1px"
							value="Total Credit" /></td>




					</tr>
					<%
						
						Object[][] rows = null;
						Object[][] c = null;
						int colVal = 0;
						int i = 0;
						int k = 0;
						try {
							rows = (Object[][]) request.getAttribute("rows");
							c = (Object[][]) request.getAttribute("creditLength");
							colVal = rows[0].length - 3;
						}catch(Exception e)
						{
							
						}
						if(rows != null && rows.length > 0)
						for (k = 0; k < rows.length; k++) {
					%>



					<tr>

						<td><input type="hidden" name="emp_id"
							value="<%=rows[k][0] %>" id="<%=rows[k][0]%>"> <input
							type="text" style="background-color: #F2F2F2" size="8"
							readonly="readonly" name="tokenNo" value="<%=rows[k][1] %>">
						</td>

						<td><input type="text" style="background-color: #F2F2F2"
							size="24" readonly="readonly" name="empName"
							value="<%=rows[k][2] %>"></td>

						<%
						i = 3;
						%>
						<s:iterator value="creditHeader">

							<td align="center"><input type="text" size="4"
								name="<%=k%>" value="<%=String.valueOf(rows[k][i]) %>"
								id="<%=rows[k][0]+"c"+i%>"
								onkeyup="sumCredits(<%=c.length %>,<%=k%>,<%=colVal%>,<%=rows[k][0]%>)"
								style="text-align: right"></td>
							<%
							i++;
							%>
						</s:iterator>
						<td align="center"><input style="background-color: #F2F2F2" 
							type="text" size="8" readonly="readonly"
							id="<%=rows[k][0]+"c"+i%>" name="totalCredit<%=k%>"
							value="<%=rows[k][i] %>" style="text-align: right;"></td>


					</tr>

					<%
					}
					%>
					<input type="hidden" id="chkSize" value="<%=k%>">



				</table>
			</s:if>
			<s:hidden name="emptypeFlag" />
			<s:hidden name="paybillFlag" />
			<s:hidden name="branchFlag" />
			<s:hidden name="departmentFlag" />
			<s:hidden name="divisionFlag" />
		</td>
		</tr>
		</table>

		</s:form>

<script>
function checkAll(){
 var size=document.getElementById('chkSize').value;
 if(size<=0){ 
  alert('No records available.');
  return false;
 }
 
 for(i=0;i<size;i++){
     if(document.getElementById('chkParent').checked==true){
     	document.getElementById('chkChild'+i).checked=true;
     }else{
     		document.getElementById('chkChild'+i).checked=false;
     }
   
 }		
 return true;				   
						  
}

						
function sum(cLen,k,colLen,empId) {
		 
		var formElements=document.getElementsByName(k);
		var creditAmount=0;
		var debitAmount=0;
		
		
//	 alert(creditAmount+debitAmount);
 	//loop through the array  
 	
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
 //	var otAmount =document.getElementById("otAmount"+k).value
	document.getElementById(empId+"c"+eval(cLen+3)).value=creditAmount;
	document.getElementById(empId+"c"+eval(colLen-1)).value=debitAmount; 
	
	document.getElementById(empId+"c"+colLen).value=eval(creditAmount*100/100)-eval(debitAmount*100/100);; 
		 
	}

 
 function sumCredits(cLen,k,colLen,empId) {
		 
		try{
		var creditAmount=0;
		var debitAmount=0;
	var j=3;
		//alert(cLen);
	for(var i=0;i<cLen;i++){
		var values=document.getElementById(empId+"c"+j).value;
		
		if(values ==''){
 		 		values =0;
 		 }
 		 j++;
		creditAmount=eval(creditAmount)+eval((values*100)/100);
	}	
	//debitAmount = document.getElementById(empId+"c"+eval(colLen-1)).value;
	//alert('empId---'+empId);
	document.getElementById(empId+"c"+eval(cLen+3)).value=creditAmount;
	//alert('credit---'+document.getElementById(empId+"c"+eval(cLen+3)).value);
		 }catch(e){
		 	alert(e);
		 }
	}
	
function check(opr)
 {//alert();
 		try {
 		var frmdate=document.getElementById('frmdate').innerHTML.toLowerCase();
 		
 		var todate=document.getElementById('todate').innerHTML.toLowerCase();
 		var branch=document.getElementById('branch').innerHTML.toLowerCase();
 		var division=document.getElementById('division').innerHTML.toLowerCase();
 		var employee=document.getElementById('employee').innerHTML.toLowerCase();
 		var department=document.getElementById('department').innerHTML.toLowerCase();
 		if(opr=="F" && document.getElementById('paraFrm_nonIndustrialSalary_cashCode').value==""){
 		  alert('Please save the process first.');
 		  return false;
 		}if(document.getElementById('paraFrm_status').value=="Finalised"){
		
		 alert('Process already finalised.');		 
		 return false;		 
		
		}if(document.getElementById('paraFrm_nonIndustrialSalary_frmDate').value==""){
		
		 alert("Please enter "+frmdate);
		 document.getElementById('paraFrm_nonIndustrialSalary_frmDate').focus();
		 return false;
		 
		}if(document.getElementById('paraFrm_nonIndustrialSalary_toDate').value==""){
		
		 alert("Please enter "+todate);
		 document.getElementById('paraFrm_nonIndustrialSalary_toDate').focus();
		 return false;
		 
		}if(document.getElementById('paraFrm_emptypeFlag').value == 'true' && document.getElementById('paraFrm_nonIndustrialSalary_typeCode').value == "")
		{
			alert("Please select the "+employee);
			return false;
		}
		if(document.getElementById('paraFrm_paybillFlag').value == 'true' && document.getElementById('paraFrm_nonIndustrialSalary_payBillNo').value == "")
		{
			alert("Please select the PayBill!");
			return false;
		}
		if(document.getElementById('paraFrm_departmentFlag').value == 'true' && document.getElementById('paraFrm_nonIndustrialSalary_deptCode').value == "")
		{
			alert("Please select the "+department);
			return false;
		}
		if(document.getElementById('paraFrm_branchFlag').value == 'true' && document.getElementById('paraFrm_nonIndustrialSalary_branchCode').value == "")
		{
			alert("Please select the "+branch);
			return false;
		}
		
		if(document.getElementById('paraFrm_divisionFlag').value == 'true' && document.getElementById('paraFrm_nonIndustrialSalary_divisionCode').value == "")
		{
			alert("Please select the "+division);
			return false;
		}
		if(opr=="F"){
		 var conf=confirm('Do you really want to finalise');
		 if(conf){
		  return true;
		 }else
		 {
		  return false;
		 }
		}
		
		return true;
		}catch(e)
		{
			return false;
		} 
 
  }
	

function callSelect(id)
{
alert(id);
alert(document.getElementById("onHoldChk"+id).value)
document.getElementById("onHoldChkHid"+id).value=document.getElementById("onHoldChk"+id).value	

}

	function callChk(id){
   document.getElementById("pmChkId"+id).value='Y';
 }

function checkRecord()
	{
		var cnt = 0;
	
		try{
		for(var i = 0; i < eCode.length; i++)
		{
		
			if(document.getElementById(eCode[i]+"onHoldChk").checked)
			{				
				document.getElementById('change'+i).style.background = "#DDE18A";
				cnt =cnt +1;
			}
			
		}
		if(cnt==0)
		{
			alert("Please select atleast one employee");
			return false;
		}
		var ledgerCode=document.getElementById("paraFrm_attCode").value;
		retrieveURLOnHold('NonIndustrialSalary_onholdSave.action?','paraFrm',ledgerCode);
		for(var i = 0; i < eCode.length; i++)
		{	
			if(document.getElementById(eCode[i]+"onHoldChk").checked)
			{				
				document.getElementById(eCode[i]+"onHoldChk").checked =false;
			}
			
		}
		
		}
		catch(e)
		{
			alert(e.description);
		}
	}
	
	function checkRecordRecal()
	{
		var cnt = 0;
	
		try{
		for(var i = 0; i < eCode.length; i++)
		{
		
			if(document.getElementById(eCode[i]+"onHoldChk").checked)
			{				
				cnt =cnt +1;
			}
			
		}
		if(cnt==0)
		{
			alert("Please select atleast one employee");
			return false;
		}
		retrieveURLRecal('NonIndustrialSalary_recalSal.action?','paraFrm');
		
		}
		catch(e)
		{
			alert(e.description);
		}
	} 

// gridScroll();

//function gridScroll(){
//myST = superTable("thetable", { fixedCols : 2,rightCols:1,viewCols:8
//});

//}
</script>