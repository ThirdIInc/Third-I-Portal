<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/lib/dropdown.jsp"%>
<div id="msgDiv"
	style='position: absolute; z-index: 3; background-color: red; 100 px; height: 50px; visibility: hidden; top: 70px; left: 250px;'></div>

<s:form action="OtCalculations" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="report" />
	<s:hidden name="reportAction" value='OtCalculations_getReport.action' />
	<s:hidden name="status" />
	<s:hidden name="otCalculationId" />
	<s:hidden name="doubleOTflag" />
	<s:hidden name="configOTflag" />

	<s:hidden name="paySalFlag" />
	<s:hidden name="deductTaxFlag" />

	<s:hidden name="reportAction" value='otRegister_getReport.action' />
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">OT
					Calculations </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Button Panel Start -->
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">


				<tr>
					<s:if test="status ==''">
						<td><input type="button" class="add" value=" Process "
							onclick="return processFun();" /> <input type="button"
							class="add" value=" Reset " onclick="return resetFun();" /> <input
							type="button" class="add" value=" Back "
							onclick="return backtolistFun();" /></td>
					</s:if>
					<s:elseif test='%{status =="Pending" || status =="Unlock"}'>
						<td width='70%'><input type="button" class="add"
							value="Re-Process " onclick="return processFun();" /> <input
							type="button" class="add" value=" Lock "
							onclick="return lockFun();" /> 
					<s:if test="paySalFlag=='YY'&& deductTaxFlag=='YY'">

						</s:if> <s:if test="paySalFlag=='YY'&& deductTaxFlag=='NY'">

						</s:if> <s:if test="paySalFlag=='NY'&& deductTaxFlag=='YY'">

							<input type="button" class="add" value=" Bank Statement "
								onclick="return callBankStatement();" />

							<input type="button" class="add" value=" Tax Challan "
								onclick="return taxchallanFun();" />
						</s:if> <s:if test="paySalFlag=='NY'&& deductTaxFlag=='NY'">
							<input type="button" class="add" value=" Bank Statement "
								onclick="return callBankStatement();" />
						</s:if> 
						
						<input type="button" class="add" value=" View "
							onclick="viewEmpFun();" /> <input type="button" class="add"
							value=" Back " onclick="return backtolistFun();" /></td>
						<td width='30%' align="right"><b>Export :</b>&nbsp;</td>
						<td nowrap="nowrap"><a href="#" onclick="callReport('Pdf');">
						<img src="../pages/images/buttonIcons/file-pdf.png"
							class="iconImage" align="absmiddle" " title="PDF"><span
							style="padding-left: 5px;">Pdf</span></a>&nbsp;&nbsp;</td>
						<td nowrap="nowrap"><a href="#" onclick="callReport('Xls');">
						<img src="../pages/images/buttonIcons/file-xls.png"
							class="iconImage" align="absmiddle" onclick="callReport('Xls');"
							title="Excel"><span style="padding-left: 5px;">Excel</span></a>
						&nbsp;&nbsp;</td>
					</s:elseif>
					<s:elseif test="status =='Lock'">
						<td width='70%'><input type="button" class="add"
							value="Un-Lock " onclick="return unlockFun();" /> 
							
					<s:if test="paySalFlag=='YY'&& deductTaxFlag=='YY'">

						</s:if> <s:if test="paySalFlag=='YY'&& deductTaxFlag=='NY'">

						</s:if> <s:if test="paySalFlag=='NY'&& deductTaxFlag=='YY'">

							<input type="button" class="add" value=" Bank Statement "
								onclick="return callBankStatement();" />

							<input type="button" class="add" value=" Tax Challan "
								onclick="return taxchallanFun();" />
						</s:if> <s:if test="paySalFlag=='NY'&& deductTaxFlag=='NY'">
							<input type="button" class="add" value=" Bank Statement "
								onclick="return callBankStatement();" />
						</s:if> 
						
						<input type="button" class="add" value=" View "
							onclick="viewEmpFun();" /> <input type="button" class="add"
							value=" Back " onclick="return backtolistFun();" /></td>

						<td width='30%' align="right"><b>Export :</b>&nbsp;</td>
						<td nowrap="nowrap"><a href="#" onclick="callReport('Pdf');">
						<img src="../pages/images/buttonIcons/file-pdf.png"
							class="iconImage" align="absmiddle" " title="PDF"><span
							style="padding-left: 5px;">Pdf</span></a>&nbsp;&nbsp;</td>
						<td nowrap="nowrap"><a href="#" onclick="callReport('Xls');">
						<img src="../pages/images/buttonIcons/file-xls.png"
							class="iconImage" align="absmiddle" onclick="callReport('Xls');"
							title="Excel"><span style="padding-left: 5px;">Excel</span></a>
						&nbsp;&nbsp;</td>
					</s:elseif>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Button Panel End -->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="1"
						cellspacing="2">
						<tr>
							<td colspan="6" class="formhead"><strong
								class="forminnerhead">Select Period </strong></td>
						</tr>


						<tr>
							<td colspan="1" width="15%"><label id="month1" name="month"
								ondblclick="callShowDiv(this);"><%=label.get("month")%></label>:<font
								color="red">*</font></td>
							<td colspan="1"><s:select theme="simple" name="month"
								cssStyle="width:152"
								list="#{'0':'Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
							</td>
							<td colspan="1" width="15%"><label class="set" id="year"
								name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label>:<font
								color="red">*</font></td>
							<td><s:textfield name="year"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="25" /></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- section for select filters -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="1"
						cellspacing="2">
						<tr>
							<td class="formhead"><strong class="forminnerhead">Select
							Filters </strong></td>
						</tr>

						<tr>
							<td colspan="1" width="15%"><label class="set" id="division"
								name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font
								color="red">*</font></td>
							<td colspan="1" width="20%"><s:hidden name="divisionID" />
							<s:textfield name="divisionName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callDropdown('paraFrm_divisionName',200,250,'OtCalculations_f9Division.action',event,'false','no','right')" />

							</td>

						</tr>

						<tr>


							<td width="20%"><label class="set" name="branch" id="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
							</td>

							<td colspan="2"><s:hidden name="centerId" /> <s:textarea
								cols="100" rows="1" name="centerName" theme="simple"
								readonly="true" /></td>
							<td width="30%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_centerName',350,250,'OtCalculations_f9Branch.action',event,'false','no','right')">
							</td>

						</tr>

						<tr>
							<td width="20%"><label class="set" name="department"
								id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
							</td>

							<td colspan="2"><s:hidden name="deptCode" /> <s:textarea
								cols="100" rows="1" name="deptName" theme="simple"
								readonly="true" /></td>
							<td width="30%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_deptName',350,250,'OtCalculations_f9Department.action',event,'false','no','right')">
							</td>
						</tr>
						<tr>
							<td width="20%"><label class="set" name="designation"
								id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:
							</td>

							<td colspan="2"><s:hidden name="desgCode" /> <s:textarea
								cols="100" rows="1" name="desgName" theme="simple"
								readonly="true" /></td>
							<td width="30%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_desgName',350,250,'OtCalculations_f9Desg.action',event,'false','no','right')">
							</td>
						</tr>

						<tr>
							<td width="20%"><label class="set" name="paybill"
								id="paybill" ondblclick="callShowDiv(this);"><%=label.get("paybill")%></label>:
							</td>

							<td colspan="2"><s:hidden name="paybillCode" /> <s:textarea
								cols="100" rows="1" name="paybillName" theme="simple"
								readonly="true" /></td>
							<td width="30%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_paybillName',350,250,'OtCalculations_f9Paybill.action',event,'false','no','right')">
							</td>
						</tr>
						<tr>

							<td width="20%"><label class="set" name="Costcenter"
								id="Costcenter" ondblclick="callShowDiv(this);"><%=label.get("Costcenter")%></label>:
							</td>

							<td colspan="2"><s:hidden name="costCenterCode" /> <s:textarea
								cols="100" rows="1" name="costCenterName" theme="simple"
								readonly="true" /></td>
							<td width="30%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_costCenterName',350,250,'OtCalculations_f9Costcenter.action',event,'false','no','right')">
							</td>
						</tr>

						<!--<tr>
							<td width="20%"><label class="set" name="Shift" id="Shift"
								ondblclick="callShowDiv(this);"><%=label.get("Shift")%></label>:
							</td>

							<td colspan="2"><s:hidden name="shiftCode" /> <s:textarea
								cols="100" rows="1" name="shiftName" theme="simple"
								readonly="true" /></td>
							<td width="35%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_shiftName',350,250,'OtCalculations_f9shiftaction.action',event,'false','no','right')">
							</td>
						</tr>
						
					-->
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- section for select parameters -->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="1"
						cellspacing="2">
						<tr>
							<td colspan="6" class="formhead"><strong
								class="forminnerhead">Select Parameters </strong></td>
						</tr>
						<tr>
							<td colspan="1" width="15%"><label id="paid.month"
								name="paid.month" ondblclick="callShowDiv(this);"><%=label.get("paid.month")%></label>:<font
								color="red">*</font></td>
							<td colspan="1"><s:select theme="simple" name="paidMonth"
								cssStyle="width:152"
								list="#{'0':'Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
							</td>
							<td colspan="1" width="15%"><label class="set"
								id="paid.year" name="paid.year" ondblclick="callShowDiv(this);"><%=label.get("paid.year")%></label>:<font
								color="red">*</font></td>
							<td><s:textfield name="paidYear"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="25" /></td>
							<td></td>
							<td></td>
						</tr>

						<tr>
							<td width="10%"><label class="set" id="pay.in.component"
								name="pay.in.component" ondblclick="callShowDiv(this);"><%=label.get("pay.in.component")%></label>:<font
								color="red">*</font></td>
							<td colspan="1" width="20%"><s:hidden name="creditCode" />
							<s:textfield name="creditName" theme="simple" readonly="true"
								maxlength="50" size="25" /></td>
							<td width="15%"><img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callDropdown('paraFrm_creditName',200,250,'OtCalculations_f9CreditHead.action',event,'false','no','right')" />
							</td>

						</tr>
						<tr>

							<td><label id=pay.in.salary name="pay.in.salary"
								ondblclick="callShowDiv(this);"><%=label.get("pay.in.salary")%></label>:</td>
							<td colspan="1" width=""><s:checkbox name="payInSalaryFlag"
								onclick=""></s:checkbox></td>
						</tr>
						<tr>

							<td><label id=deduct.it name="deduct.it"
								ondblclick="callShowDiv(this);"><%=label.get("deduct.it")%></label>:</td>
							<td colspan="1"><s:checkbox name="deductInconeTaxFlag"></s:checkbox></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Button Panel Start -->
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">


				<tr>
					<s:if test="status ==''">
						<td><input type="button" class="add" value=" Process "
							onclick="return processFun();" /> <input type="button"
							class="add" value=" Reset " onclick="return resetFun();" /> <input
							type="button" class="add" value=" Back "
							onclick="return backtolistFun();" /></td>
					</s:if>
					<s:elseif test='%{status =="Pending" || status =="Unlock"}'>
						<td width='70%'><input type="button" class="add"
							value="Re-Process " onclick="return processFun();" /> <input
							type="button" class="add" value=" Lock "
							onclick="return lockFun();" /> 
					<s:if test="paySalFlag=='YY'&& deductTaxFlag=='YY'">

						</s:if> <s:if test="paySalFlag=='YY'&& deductTaxFlag=='NY'">

						</s:if> <s:if test="paySalFlag=='NY'&& deductTaxFlag=='YY'">

							<input type="button" class="add" value=" Bank Statement "
								onclick="return callBankStatement();" />

							<input type="button" class="add" value=" Tax Challan "
								onclick="return taxchallanFun();" />
						</s:if> <s:if test="paySalFlag=='NY'&& deductTaxFlag=='NY'">
							<input type="button" class="add" value=" Bank Statement "
								onclick="return callBankStatement();" />
						</s:if> 
						
						<input type="button" class="add" value=" View "
							onclick="viewEmpFun();" /> <input type="button" class="add"
							value=" Back " onclick="return backtolistFun();" /></td>
						<td width='30%' align="right"><b>Export :</b>&nbsp;</td>
						<td nowrap="nowrap"><a href="#" onclick="callReport('Pdf');">
						<img src="../pages/images/buttonIcons/file-pdf.png"
							class="iconImage" align="absmiddle" " title="PDF"><span
							style="padding-left: 5px;">Pdf</span></a>&nbsp;&nbsp;</td>
						<td nowrap="nowrap"><a href="#" onclick="callReport('Xls');">
						<img src="../pages/images/buttonIcons/file-xls.png"
							class="iconImage" align="absmiddle" onclick="callReport('Xls');"
							title="Excel"><span style="padding-left: 5px;">Excel</span></a>
						&nbsp;&nbsp;</td>
					</s:elseif>
					<s:elseif test="status =='Lock'">
						<td width='70%'><input type="button" class="add"
							value="Un-Lock " onclick="return unlockFun();" /> 
							
					<s:if test="paySalFlag=='YY'&& deductTaxFlag=='YY'">

						</s:if> <s:if test="paySalFlag=='YY'&& deductTaxFlag=='NY'">

						</s:if> <s:if test="paySalFlag=='NY'&& deductTaxFlag=='YY'">

							<input type="button" class="add" value=" Bank Statement "
								onclick="return callBankStatement();" />

							<input type="button" class="add" value=" Tax Challan "
								onclick="return taxchallanFun();" />
						</s:if> <s:if test="paySalFlag=='NY'&& deductTaxFlag=='NY'">
							<input type="button" class="add" value=" Bank Statement "
								onclick="return callBankStatement();" />
						</s:if> 
						
						<input type="button" class="add" value=" View "
							onclick="viewEmpFun();" /> <input type="button" class="add"
							value=" Back " onclick="return backtolistFun();" /></td>

						<td width='30%' align="right"><b>Export :</b>&nbsp;</td>
						<td nowrap="nowrap"><a href="#" onclick="callReport('Pdf');">
						<img src="../pages/images/buttonIcons/file-pdf.png"
							class="iconImage" align="absmiddle" " title="PDF"><span
							style="padding-left: 5px;">Pdf</span></a>&nbsp;&nbsp;</td>
						<td nowrap="nowrap"><a href="#" onclick="callReport('Xls');">
						<img src="../pages/images/buttonIcons/file-xls.png"
							class="iconImage" align="absmiddle" onclick="callReport('Xls');"
							title="Excel"><span style="padding-left: 5px;">Excel</span></a>
						&nbsp;&nbsp;</td>
					</s:elseif>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Button Panel End -->

	</table>

</s:form>



<script>
getYear();
hideButton();
function getYear(){

	var current = new Date();
	 var year =current.getFullYear();

	 var yr =document.getElementById("paraFrm_year").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_year").value =year;
	  }
	  var paidYr =document.getElementById("paraFrm_paidYear").value;
	 if(paidYr==''){
	  	document.getElementById("paraFrm_paidYear").value =year;
	  }
}

	function validateFields(){
		var name = ['paraFrm_year'];
		var label = ['year'];
		var flag = ["enter"];
		
		if(!validateBlank(name, label, flag)) {
			return false;
		}
		
		var month  =document.getElementById("paraFrm_month").value;
		var mont=document.getElementById('month1').innerHTML.toLowerCase();
			if(month =='0'){
		 	alert("Select "+mont);
		 	return false;
		 	}
		var divNm   =document.getElementById("paraFrm_divisionID").value;
		if(divNm==""){
	 	alert("Please select the Division");
	 	document.getElementById('paraFrm_divisionName').focus();
	 	return false;
		 }
		 
		 var paidMonth  =document.getElementById("paraFrm_paidMonth").value;
		var paidMont=document.getElementById('paid.month').innerHTML.toLowerCase();
			if(paidMonth =='0'){
		 	alert("Select "+paidMont);
		 	return false;
		 	}
		
		var yearname = ['paraFrm_paidYear'];
		var yearlabel = ['paid.year'];
		var yearflag = ["enter"];
		
		if(!validateBlank(yearname, yearlabel, yearflag)) {
			return false;
		}
		
		
		var creditNm   =document.getElementById("paraFrm_creditCode").value;
		if(creditNm==""){
	 	alert("Please select the Pay in Component");
	 	document.getElementById('paraFrm_creditName').focus();
	 	return false;
		 }
		 
		
		return true;
	}
	
	function resetFun() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OtCalculations_reset.action';
		document.getElementById('paraFrm').submit();
	}

	

	function backtolistFun() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OtCalculations_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	function processFun() {
	try {
		
		if(!validateFields()) {
			return false;
		}   
	
	var con = confirm("Do you really want to process this record?");
	if (con) {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'OtCalculations_processOtCalculation.action';
		document.getElementById('paraFrm').submit();		
	} else {
		return false;
	}
  } catch(e) {
  	alert("Exception in processFun >>>>"+e);
  	return false;
  }
}
	function lockFun(){
		var con = confirm("Do you really want to lock this record?");
	 	if(con) {
	 		
			document.getElementById('paraFrm').action = "OtCalculations_lockRecord.action";
			document.getElementById('paraFrm').submit();
		} else {
		return false;
	}
	
	}
	
	function unlockFun() {
	var con = confirm("Do you really want to unlock this record?");
	if (con) {
		doAuthorisation('9', 'OT Calculation', 'U');
		///document.getElementById('paraFrm').target="_self";
		///document.getElementById('paraFrm').action = "OtCalculations_unLockRecord.action";
		///document.getElementById('paraFrm').submit();
	} else {
		return false;
	}
	}
	
	
	function doUnlock() {
		document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action = "OtCalculations_unLockRecord.action";
		document.getElementById('paraFrm').submit();
	}
	
	
	function callReport(type){
	
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OtCalculations_getReport.action';
		document.getElementById('paraFrm').submit();
	}
	
	function taxchallanFun(){
	var otConfigId = document.getElementById('paraFrm_otCalculationId').value;
	var type = 'O';
	var backAction = "<%=request.getContextPath()%>/ot/OtCalculations_calforedit.action?otConfigId="+otConfigId;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action='<%=request.getContextPath()%>/incometax/TaxChallan_input.action?applicationCode='+otConfigId+'&applicationType='+type+'&backAction='+backAction;
	document.getElementById('paraFrm').submit();
}

	function callBankStatement(){
		//paraFrm_linkSource
			var arrearsCode=document.getElementById('paraFrm_otCalculationId').value;	
			var linkSource11='<%=request.getContextPath()%>/ot/OtCalculations_calforedit.action?otConfigId='+arrearsCode;
			var month=document.getElementById('paraFrm_paidMonth').value;
				month = getMonthName(month);
			var year=document.getElementById('paraFrm_year').value;			
			var divName=document.getElementById('paraFrm_divisionName').value;
			var monthText=month;
			var divisionCode = document.getElementById('paraFrm_divisionID').value;
			var hiddenMonth = document.getElementById('paraFrm_paidMonth').value;
			
			document.getElementById('paraFrm').action='<%=request.getContextPath()%>/payroll/SalaryStatementBank_viewSalaryStatementLink.action?earningType=O&earningTypeDisplay=S&hiddenMonth='+hiddenMonth+'&earningYear='+year+'&earningCode='+
			arrearsCode+'&divName='+divName+'&earningMonth='+monthText+'&linkSource='+linkSource11+'&divisionCode='+divisionCode+'&month='+month;
			document.getElementById('paraFrm').submit();
			//document.getElementById('paraFrm_linkSource').value='SalaryProcess_callForEdit.action?linkSourceCode='+ledgerCodeValue;
			 
		}
		
	function getMonthName(enteredMonth) {
		if (enteredMonth=="1") {
			return "January";
		}
		if (enteredMonth=="2") {
			return "February";
		}
		if (enteredMonth=="3") {
			return "March";
		}
		if (enteredMonth=="4") {
			return "April";
		}
		if (enteredMonth=="5") {
			return "May";
		}
		if (enteredMonth=="6") {
			return "June";
		}
		if (enteredMonth=="7") {
			return "Jully";
		}
		if (enteredMonth=="8") {
			return "August";
		}
		if (enteredMonth=="9") {
			return "September";
		}
		if (enteredMonth=="10") {
			return "October";
		}
		if (enteredMonth=="11") {
			return "November";
		}
		if (enteredMonth=="12") {
			return "December";
		}
	}	
	
	
	function viewEmpFun() {
	///alert(calCode);
	var calCode = document.getElementById('paraFrm_otCalculationId').value;
		var backAction = "<%=request.getContextPath()%>/ot/OtCalculations_calforedit.action?calCode="+calCode;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action='<%=request.getContextPath()%>/ot/OtCalculations_viewEmpDetails.action?calCode='+calCode+'&backAction='+backAction;
	document.getElementById('paraFrm').submit();
	
	}
	function hideButton(){
	    try{
	    
	    var hardWareCheckboxVar = document.getElementById('paraFrm_payInSalaryFlag').checked;
		
		if((hardWareCheckboxVar == true))
		{
			document.getElementById('bankStatement').style.display ='none';
	    	document.getElementById('taxChallan').style.display ='none';
	    	document.getElementById('bankState').style.display ='none';
	    	document.getElementById('taxChall').style.display ='none';
		}else{
			document.getElementById('bankStatement').style.display ='';
	    	document.getElementById('taxChallan').style.display ='';
	    	document.getElementById('bankState').style.display ='';
	    	document.getElementById('taxChall').style.display ='';
		}
	    }catch(e){
	    	///alert(e);
	    }
	    
	}
 
</script>