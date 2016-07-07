<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="EmpPrevIncome" target="main" theme="simple"
	validate="true" id="paraFrm">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Previous Income</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="prevIncCode" />
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>

		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg">

						<tr>
							<td class="text_head" colspan="4"><strong
								class="forminnerhead">Employee Details</strong></td>
						</tr>
						<tr>
							<td width="24%"><label class="set" id="employee"
								name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><span
								class="formtext"> :</span><font color="red">*</font></td>
							<td height="22" colspan="4"><s:hidden name="empID"
								theme="simple" /> <s:textfield
								theme="simple" readonly="true" size="15" name="empToken" /> <s:textfield
								label="%{getText('empName')}" theme="simple" size="77"
								readonly="true" name="empName" /><s:if
								test="%{employeePreviousIncome.generalFlag}"></s:if> <s:else>
								<img src="../pages/common/css/default/images/search2.gif"
									width="16" height="15" id="ctrlHide"
									onclick="javascript:callsF9(500,325,'EmpPrevIncome_f9Employee.action');">
							</s:else></td>
						</tr>

						<tr>
							<td><label class="set" id="branch" name=branch
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:<font color="red">*</font></td>
							<td width="27%" height="22"><s:hidden name="centerId" theme="simple" /><s:textfield theme="simple"
								size="25" readonly="true" name="empCenter" /></td>

							<td width="15%" height="22" class="formtext"><label
								class="set" id="designation" name="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td width="34%" height="22"><s:textfield theme="simple"
								size="25" readonly="true" name="empRank" /></td>
						</tr>
						
						<tr>
							<td><label class="set" id="panNo" name=panNo
								ondblclick="callShowDiv(this);"><%=label.get("panNo")%></label>
							:<font color="red">&nbsp;</font></td>
							<td width="27%" height="22"><s:textfield theme="simple"
								size="25" readonly="true" name="panNo" /></td>

							<td width="15%" height="22" class="formtext">&nbsp;</td>
							<td width="34%" height="22">&nbsp;</td>
						</tr>

						<tr>
							<td><label class="set" id="finYrFrm" name="finYrFrm"
								ondblclick="callShowDiv(this);"><%=label.get("finYrFrm")%></label>:<font
								color="red">*</font></td>
							<td width="27%" height="22"><s:textfield theme="simple"
								size="25" name="fromYear" maxlength="4"
								onkeypress="return numbersOnly();" onblur="add()" /></td>

							<td width="15%" height="22" class="formtext"><label
								class="set" id="finYrTo" name="finYrTo"
								ondblclick="callShowDiv(this);"><%=label.get("finYrTo")%></label>:</td>
							<td width="34%" height="22"><s:textfield theme="simple"
								size="25" readonly="true" name="toYear" /></td>
						</tr>

					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="2"
				class="formbg">
				<tr>
							<td class="text_head" colspan="4"><strong
								class="forminnerhead">Income Details</strong></td>
						</tr>
				<tr>
							<td><label class="set" id="netTaxableIncome" name="netTaxableIncome"
								ondblclick="callShowDiv(this);"><%=label.get("netTaxableIncome")%></label>:<font
								color="red">&nbsp;</font></td>
							<td width="27%" height="22"><s:textfield theme="simple"
								size="25" name="netAmount" 
								onkeypress="return numbersWithDot();" cssStyle="text-align: right;" /></td>

							<td width="15%" height="22" ><label
								class="set" id="totalTaxPaid" name="totalTaxPaid"
								ondblclick="callShowDiv(this);"><%=label.get("totalTaxPaid")%></label>:</td>
							<td width="34%" height="22"><s:textfield
						theme="simple" size="10" name="taxAmount"
						onkeypress="return numbersWithDot();"
						cssStyle="text-align: right;" /></td>
						</tr>
						
						<tr>
							<td><label class="set" id="pf" name="pf"
								ondblclick="callShowDiv(this);"><%=label.get("pf")%></label>:<font
								color="red">&nbsp;</font></td>
							<td width="27%" height="22"><s:textfield
						theme="simple" size="10" name="pfAmount"
						onkeypress="return numbersWithDot();"
						cssStyle="text-align: right;" /></td>

							<td width="15%" height="22"><label
								class="set" id="ptax" name="ptax"
								ondblclick="callShowDiv(this);"><%=label.get("ptax")%></label>:</td>
							<td width="34%" height="22"><s:textfield
						theme="simple" size="10" name="ptAmount"
						onkeypress="return numbersWithDot();"
						cssStyle="text-align: right;" /></td>
						</tr>
				
				</table>
			</td>
		</tr>

		<!-- <tr>
			<td width="50%" valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="2"
				class="formbg">
				<tr>
					<td class="formth" colspan="2">Credit List</td>
				</tr>
				<tr>
					<td width="25%" class="sortableTD">BASIC:</td>
					<td width="25%" align="center" class="sortableTD"><s:textfield
						theme="simple" size="10" name="basicAmount"
						onkeypress="return numbersWithDot();"
						cssStyle="text-align: right;" onkeyup="sum();" /></td>
				</tr>
				<tr>
					<td width="25%" class="sortableTD">HRA:</td>
					<td width="25%" align="center" class="sortableTD"><s:textfield
						theme="simple" size="10" name="hraAmount"
						onkeypress="return numbersWithDot();"
						cssStyle="text-align: right;" onkeyup="sum();" /></td>
				</tr>
				<tr>
					<td width="25%" class="sortableTD">DA:</td>
					<td width="25%" align="center" class="sortableTD"><s:textfield
						theme="simple" size="10" name="daAmount"
						onkeypress="return numbersWithDot();"
						cssStyle="text-align: right;" onkeyup="sum();" /></td>
				</tr>
				<tr>
					<td width="25%" class="sortableTD">CA:</td>
					<td width="25%" align="center" class="sortableTD"><s:textfield
						theme="simple" size="10" name="caAmount"
						onkeypress="return numbersWithDot();"
						cssStyle="text-align: right;" onkeyup="sum();" /></td>
				</tr>
				<tr>
					<td width="25%" class="sortableTD">OTHER INCOME:</td>
					<td width="25%" align="center" class="sortableTD"><s:textfield
						theme="simple" size="10" name="otherAmount"
						onkeypress="return numbersWithDot();"
						cssStyle="text-align: right;" onkeyup="sum();" /></td>
				</tr>
			</table>
			</td>
			<td width="50%" valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="2"
				class="formbg">
				<tr>
					<td class="formth" colspan="2">Debit List</td>
				</tr>
				<tr>
					<td class="sortableTD">PF:</td>
					<td align="center" class="sortableTD"><s:textfield
						theme="simple" size="10" name="pfAmount"
						onkeypress="return numbersWithDot();"
						cssStyle="text-align: right;" /></td>
				</tr>
				<tr>
					<td class="sortableTD">PT:</td>
					<td align="center" class="sortableTD"><s:textfield
						theme="simple" size="10" name="ptAmount"
						onkeypress="return numbersWithDot();"
						cssStyle="text-align: right;" /></td>
				</tr>
				<tr>
					<td class="sortableTD">TAX PAID:</td>
					<td align="center" class="sortableTD"><s:textfield
						theme="simple" size="10" name="taxAmount"
						onkeypress="return numbersWithDot();"
						cssStyle="text-align: right;" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td>Net Taxable Income:</td>
					<td align="center"><s:textfield theme="simple" size="10"
						name="netAmount" onkeypress="return numbersWithDot();"
						cssStyle="background-color: #F2F2F2;text-align:right;"
						readonly="true" /></td>
				</tr>
			</table>
			</td>
		</tr>
 -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%"></td>
				</tr>
			</table>
			</td>

		</tr>
	</table>

</s:form>

<script>
function add(){
	var from = document.getElementById('paraFrm_fromYear').value;
	if(from==""){
		document.getElementById('paraFrm_toYear').value="";
	}
	else{
		var x=eval(from) +1;
		document.getElementById('paraFrm_toYear').value=x;
	}
}

function sum(){
	var basic 	= document.getElementById('paraFrm_basicAmount').value;
	var hra 	= document.getElementById('paraFrm_hraAmount').value;
	var da 		= document.getElementById('paraFrm_daAmount').value;
	var ca 		= document.getElementById('paraFrm_caAmount').value;
	var other 	= document.getElementById('paraFrm_otherAmount').value;
	
	var total 	= eval(basic)+eval(hra)+eval(da)+eval(ca)+eval(other);
	document.getElementById('paraFrm_netAmount').value=roundNumber(eval(total),2);
}

function roundNumber(num, dec) {
	var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
}

function saveFun(){
	var empCode 	= document.getElementById('paraFrm_empID').value;
	var fromYear 	= document.getElementById('paraFrm_fromYear').value;
	
	if(empCode==""){
		alert('Please select '+document.getElementById('employee').innerHTML.toLowerCase());
		document.getElementById('paraFrm_empName').focus();
		return false;
	}
	if(fromYear==""){
		alert('Please enter '+document.getElementById('finYrFrm').innerHTML.toLowerCase());
		document.getElementById('paraFrm_fromYear').focus();
		return false;
	}
	document.getElementById("paraFrm").target='_self';
	document.getElementById("paraFrm").action='EmpPrevIncome_save.action';
	document.getElementById("paraFrm").submit();
}

function backFun() {
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'EmpPrevIncome_input.action';
	document.getElementById('paraFrm').submit();
}
function resetFun() {
 	document.getElementById('paraFrm').target = "_self";
 	document.getElementById('paraFrm').action = "EmpPrevIncome_reset.action";
	document.getElementById('paraFrm').submit();
}

function editFun(){
	return true;
}

function deleteFun() {
	var conf = confirm("Do you really want to delete this record?");
 		if(conf) {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'EmpPrevIncome_delete.action';
		document.getElementById('paraFrm').submit();
	}
}
</script>
