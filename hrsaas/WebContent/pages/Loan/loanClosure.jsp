<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="java.util.HashMap;"%>

<s:form action="LoanClosure" id="paraFrm" theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Loan
					Closure/Pre Payment</strong></td>
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
			<table width="100%" border="0" align="right" cellpadding="2"
				cellspacing="2">

				<tr>
					<td colspan="3"><s:hidden name="loanClosureCode"></s:hidden>
					<table width="100%" border="0" cellpadding="2" cellspacing="2">
						<tr>
							<td><s:if test="%{insertFlag}">
								<s:submit cssClass="add" action="LoanClosure_save"
									theme="simple" value="   Add New"
									onclick="return validateSave('save');" />
							</s:if> <s:if test="%{updateFlag}">
								<s:submit cssClass="edit" action="LoanClosure_save"
									theme="simple" value="   Update"
									onclick="return validateSave('update');" />
							</s:if> <s:if test="%{viewFlag}">
								<input type="button" class="search" value="    Search "
									onclick="javascript:callsF9(500,325,'LoanClosure_f9Action.action'); " />
							</s:if> <s:submit cssClass="reset" action="LoanClosure_reset"
								theme="simple" value="    Reset" /> <!--<s:if test="%{deleteFlag}">
								<s:submit cssClass="delete" action="LoanClosure_delete" theme="simple" value="    Delete"
									onclick="return callDelete('paraFrm_loanClosureCode')"/>
							</s:if>
							--><s:if test="%{viewFlag}">
								<input type="button" class="token"
									onclick="callReportforSelected('LoanClosure_report.action','paraFrm_loanClosureCode');"
									value="  Report " />
							</s:if></td>
							<td width="22%">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					<label></label></td>
				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="100%" colspan="3">
							<table width="98%" border="0" align="center" cellpadding="2"
								cellspacing="2">
								<tr>
									<td colspan="4" class="formhead" width="100%"><strong
										class="forminnerhead"><label class="set"
										id="closLoan" name="closLoan" ondblclick="callShowDiv(this);"><%=label.get("closLoan")%></label>
									</strong></td>
								</tr>

								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="selApplic" name="selApplic"
										ondblclick="callShowDiv(this);"><%=label.get("selApplic")%></label><font
										color="red">*</font> : <s:hidden name="loanAppCode" /></td>
									<td colspan="1" width="30%"><img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="18" theme="simple"
										onclick="javascript:callsF9(500,325,'LoanClosure_f9loanAppAction.action');"></td>
								</tr>

								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
									<td colspan="3" width="70%" nowrap="nowrap"><s:textfield
										size="10" theme="simple" name="empToken" readonly="true" /> <s:textfield
										size="50" theme="simple" name="empName" readonly="true" /> <s:hidden
										name="empCode" /></td>
								</tr>

								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
									<td colspan="1" width="30%"><s:textfield size="25"
										theme="simple" name="branchName" readonly="true" /></td>
									<td colspan="1" width="22%"><label class="set"
										id="department" name="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
									:</td>
									<td colspan="1" width="25%"><s:textfield size="25"
										theme="simple" name="deptName" readonly="true" /></td>
								</tr>

								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="sancAmnt" name="sancAmnt" ondblclick="callShowDiv(this);"><%=label.get("sancAmnt")%></label>
									:</td>
									<td colspan="1" width="30%"><s:textfield size="25"
										theme="simple" name="sanctionAmount" readonly="true" /></td>
									<td colspan="1" width="22%"><label class="set"
										id="sanctDate" name="sancDate" ondblclick="callShowDiv(this);"><%=label.get("sancDate")%></label>
									:</td>
									<td colspan="1" width="30%"><s:textfield size="25"
										theme="simple" name="date" readonly="true" /></td>
								</tr>



								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="amntPaid" name="amntPaid" ondblclick="callShowDiv(this);"><%=label.get("amntPaid")%></label>
									:</td>
									<td colspan="1" width="30%"><s:textfield size="25"
										theme="simple" name="amountPaid" readonly="true" /></td>
									<td colspan="1" width="22%"><label class="set"
										id="balpAmnt" name="balpAmnt" ondblclick="callShowDiv(this);"><%=label.get("balpAmnt")%></label>
									:</td>
									<td colspan="1" width="25%"><s:textfield size="25"
										theme="simple" name="balanceAmount" readonly="true" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="100%" colspan="3">
							<table width="98%" border="0" align="center" cellpadding="2"
								cellspacing="2">
								<tr>
									<td colspan="4" class="formhead" width="100%"><strong
										class="forminnerhead"><label class="set" id="perPay"
										name="perPay" ondblclick="callShowDiv(this);"><%=label.get("perPay")%></label>
									</strong></td>
								</tr>

								<tr>
									<td colspan="1" width="22%"><label class="set"
										id="intType" name="intType" ondblclick="callShowDiv(this);"><%=label.get("intType")%></label>
									:</td>
									<td colspan="1" width="25%"><s:select name="interestType"
										onchange="return disableIntRate();" cssStyle="width:150"
										list="#{'N':'No Interest','F':'Flat Interest','R':'Reducing Principal','I':'Reducing Interest'}" />
									<s:hidden name="hiddenInterestType" /> <s:hidden
										name="flatRateFlag" /></td>
									<td colspan="1" width="25%" id='intRateTD'><label
										class="set" id="intRate" name="intRate"
										ondblclick="callShowDiv(this);"><%=label.get("intRate")%></label>
									:</td>
									<td colspan="1" width="30%" id='intRateTD1'><s:textfield
										size="25" theme="simple" name="interestRate" /></td>

								</tr>
								<tr>
									<td colspan="1" width="25%" nowrap="nowrap"><label
										class="set" id="loanDatePC" name="loanDatePC"
										ondblclick="callShowDiv(this);"><%=label.get("loanDatePC")%></label><font
										color="red">*</font> :</td>
									<td colspan="1" width="30%" nowrap="nowrap"><s:textfield
										size="25" theme="simple" name="closureDate" maxlength="10"
										onkeypress="return numbersWithHiphen();"
										onkeyup="setRescheduleFlag();" /> <s:a
										href="javascript:NewCal('paraFrm_closureDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage"
											height="16" align="absmiddle" width="16">
									</s:a></td>
									<td colspan="1" width="20%"><label class="set"
										id="perpAmnt" name="perpAmnt" ondblclick="callShowDiv(this);"><%=label.get("perpAmnt")%></label>
									:</td>
									<td colspan="1" width="25%"><s:textfield size="25"
										theme="simple" name="amtPaidByEmp"
										onkeypress="return numbersWithDot();"
										onkeyup="setRescheduleFlag();" /></td>
								</tr>



								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="calcEMIBy" name="calcEMIBy"
										ondblclick="callShowDiv(this);"><%=label.get("calcEMIBy")%></label>
									:</td>
									<td colspan="2" width="25%"><s:radio name='calType'
										list="#{'I':'No. of Installment','E':'EMI Amount','P':'Principal Amount'}"
										onclick="callCalculateType(this);" /></td>
								</tr>

								<tr id='installmentTR'>
									<td colspan="1" width="25%"><label class="set"
										id="remLoanAmnt2" name="remLoanAmnt"
										ondblclick="callShowDiv(this);"><%=label.get("remLoanAmnt")%></label><font
										color="red">*</font> :</td>
									<td colspan="1" width="25%"><s:textfield size="25"
										theme="simple" name="noOfInstallmentOther" maxlength="3"
										onkeypress="return numbersOnly();" /></td>
									<td colspan="1" width="25%"></td>
									<td colspan="1" width="25%"></td>
								</tr>
								<tr id='EMITR'>
									<td colspan="1" width="25%"><label class="set"
										id="emiAmount" name="emiAmount"
										ondblclick="callShowDiv(this);"><%=label.get("emiAmount")%></label><font
										color="red">*</font> :</td>
									<td colspan="1" width="25%"><s:textfield size="25"
										theme="simple" name="emiAmount" maxlength="10"
										onkeypress="return numbersWithDot();" /></td>
									<td colspan="1" width="25%"></td>
									<td colspan="1" width="25%"></td>

								</tr>

								<tr id='principalTR'>
									<td colspan="1" width="25%"><label class="set"
										id="princAmount" name="princAmount"
										ondblclick="callShowDiv(this);"><%=label.get("princAmount")%></label><font
										color="red">*</font> :</td>
									<td colspan="1" width="25%"><s:textfield size="25"
										theme="simple" name="monthlyPrincAmount" maxlength="10"
										onkeypress="return numbersWithDot();" /></td>
									<td colspan="1" width="25%"></td>
									<td colspan="1" width="25%"></td>

								</tr>



								<tr id="otherRateDiv">
									<td colspan="1" width="25%"><label class="set"
										id="remLoanAmnt4" name="remLoanAmnt"
										ondblclick="callShowDiv(this);"><%=label.get("remLoanAmnt")%></label><font
										color="red">*</font> :</td>
									<td colspan="1" width="25%"><s:textfield size="25"
										theme="simple" name="noOfInstallmentsReduceInt" maxlength="3"
										onkeypress="return numbersOnly();" /></td>
									<td colspan="1" width="25%"></td>
									<td colspan="1" width="25%"></td>
								</tr>
								<tr>
									<td colspan="4" width="100%" align="center"><s:submit
										action="LoanClosure_rescheduleInstallments"
										value="Reschedule Loan Installments" cssClass="token"
										onclick="return validateReschedule();"></s:submit></td>

								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<%
				int c = 0;
				%>

				<s:if test="installmentFlag">
					<tr>
						<td colspan="4">
						<table width="100%" border="0" cellpadding="2" cellspacing="2"
							class="formbg">
							<tr>
								<td>
								<table width="98%" border="0" align="center" cellpadding="2"
									cellspacing="2">
									<tr>
										<td colspan="4" class="formhead"><strong
											class="forminnerhead"><label class="set"
											id="instDtl" name="instDtl" ondblclick="callShowDiv(this);"><%=label.get("instDtl")%></label>
										</strong></td>
									</tr>

									<tr height="1">
										<td colspan="4" class="formhead">&nbsp;</td>
									</tr>

									<td class="formtext">
									<table width="100%" border="0" cellpadding="2" cellspacing="2"
										class="sortable">
										<tr>
											<td width="5%" class="formth"><label class="set"
												id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
											<td width="20%" class="formth" align="left"><label
												class="set" id="instDate" name="instDate"
												ondblclick="callShowDiv(this);"><%=label.get("instDate")%></label></td>
											<td width="20%" class="formth" align="right">
											<%
												String lable = (String) request.getAttribute("lable");
												if (lable != null) {
													out.println(lable);
												}
											%>
											</td>
											<td width="20%" class="formth" align="right"><label
												class="set" id="interestAmnt" name="interestAmnt"
												ondblclick="callShowDiv(this);"><%=label.get("interestAmnt")%></label></td>
											<td width="20%" class="formth" align="right"><label
												class="set" id="emiAmnt" name="emiAmnt"
												ondblclick="callShowDiv(this);"><%=label.get("emiAmnt")%></label></td>
											<td width="25%" class="formth" align="right"><label
												class="set" id="isPaid" name="isPaid"
												ondblclick="callShowDiv(this);"><%=label.get("isPaid")%></label></td>
										</tr>
										<tr>
											<td class="formtext" colspan="6">
											<div class="scrollF9" id="scrollDiv">
											<table width="100%" border="0" cellpadding="2"
												cellspacing="2" class="sortable">

												<%
													int i = 0;
													HashMap map = (HashMap) request.getAttribute("mapData");
												%>

												<s:iterator value="installmentList">
													<%
													String data = (String) map.get("installment" + i);
													%>

													<tr>
														<td class="border2" width="5%"><%=i + 1%></td>
														<td class="border2" width="20%" align="left"><s:property
															value="monthYear" /><s:hidden name="monthYear" /></td>
														<td class="border2" width="20%" align="right"><s:property
															value="principalAmt" /><s:hidden name="principalAmt" /></td>
														<td class="border2" width="20%" align="right"><s:property
															value="interestAmt" /><s:hidden name="interestAmt" /></td>
														<td class="border2" width="20%" align="right"><s:property
															value="installmentAmt" /> <s:hidden name="installmentAmt" /><s:hidden
															name="balancePrincipalAmt" /></td>
														<td class="border2" width="25%" align="right"><STRONG><%=data%></STRONG>
														<s:hidden name="isPaid" value="<%= data %>" /></td>
													</tr>
													<%
														i++;
														c = i;
													%>

												</s:iterator>


											</table>
											</div>
											</td>
										</tr>
										<tr>
											<td class="border2" width="5%">&nbsp;</td>
											<td class="border2" width="20%" align="right"><b><label
												class="set" id="total" name="total"
												ondblclick="callShowDiv(this);"><%=label.get("total")%></label></b></td>
											<td class="border2" width="20%" align="right">&nbsp;<s:property
												value="totalPrincipalAmt" /></td>
											<td class="border2" width="20%" align="right">&nbsp;<s:property
												value="totalInterestAmt" /></td>
											<td class="border2" width="20%" align="right">&nbsp;<s:property
												value="totalInstallmenteAmt" /></td>
											<td class="border2" width="25%" align="right">&nbsp;</td>
										</tr>
									</table>
									</td>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<s:hidden name="rescheduleFlag" />
				</s:if>

				<tr>
					<td colspan="3" valign="bottom" class="txt"><input
						type="hidden" name="count" id="count" value="<%=c %>"> <s:hidden
						name="remainingPrincipalAmount" /><s:hidden name='hiddenCalType'></s:hidden>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script><!--
	function validateSave(buttonType){
		if(buttonType == 'update' && document.getElementById('paraFrm_loanClosureCode').value == ""){
			alert('Please select a record to update !');
			return false;
		}else if(buttonType == 'save' && document.getElementById('paraFrm_loanClosureCode').value != ""){
			alert('Please click on update button to update the record !');
			return false;
		}
		
		
		var fieldNames = ['paraFrm_closureDate', 'paraFrm_noOfInstallmentsReduceInt'];
		var labelNames = ['loanDatePC', 'remLoanAmnt4'];
		var flag       = ["enter", "enter"];
		
		var balanceAmt = eval(document.getElementById('paraFrm_balanceAmount').value);
		var paidAmt    = eval(document.getElementById('paraFrm_amtPaidByEmp').value);
		
		if(paidAmt == ""){
			paidAmt = 0;
		}
		var remainingPrincipalAmount = eval(balanceAmt)-eval(paidAmt);
		
		document.getElementById('paraFrm_remainingPrincipalAmount').value = remainingPrincipalAmount;
		var selApplic=document.getElementById('selApplic').innerHTML.toLowerCase();
		var loan=document.getElementById('paraFrm_loanAppCode').value
		if( loan== ""){
			alert("Please select "+selApplic);
			return false;
		}
		 var intType = document.getElementById('paraFrm_interestType').value;
		if(intType!='I'){
			var noOfInstall = document.getElementById("paraFrm_noOfInstallmentOther").value;
	 	 	var emiAmount = document.getElementById("paraFrm_emiAmount").value;
	 	 	var nolabel = document.getElementById('remLoanAmnt2').innerHTML;
	 	 	var emiLabel = document.getElementById('emiAmount').innerHTML;
	 	 	
	 	 	if(document.getElementById('paraFrm_closureDate').value==""){
	 	 		alert("Please enter "+document.getElementById('loanDatePC').innerHTML);
	 	 		return false;
	 	 	}
	 	 	
	 	 	if(balanceAmt!=paidAmt){
		 	 	if(noOfInstall=="" && emiAmount ==""){
		 	 		alert("Please enter either "+nolabel+" or "+emiLabel);
		 	 		return false;
		 	 	}
		 	 }	
		
		}else{
		if(!validateBlank(fieldNames, labelNames, flag))return false;
		}
		if(!validateDate('paraFrm_closureDate', 'loanDatePC'))return false;
		
		if(paidAmt > balanceAmt){
			alert('Your balance amount is Rs. '+balanceAmt+', so please pay only Rs. '+balanceAmt);
			return false;
		}
		//alert(eval(balanceAmt));
			//alert(eval(paidAmt));
		if(eval(balanceAmt)!=eval(paidAmt)){
			//alert(eval(balanceAmt));
			//alert(eval(paidAmt));
			if(document.getElementById("count").value == 0){
				alert('Please click on reschedule loan installments button');
				return false;
			}
			
			if(document.getElementById('paraFrm_rescheduleFlag').value == 'false'){
				alert('Please click on reschedule loan installments button');
				return false;
			}
		}
	}
	
	function clearEmi(){
		document.getElementById('paraFrm_emiAmount').value='';
		}	
	
		function clearEmiBlur(){
		if(document.getElementById('paraFrm_noOfInstallmentOther').value != "")
	document.getElementById('paraFrm_emiAmount').value='';
		}	
	
		function clearNoOfInstallment(){
	document.getElementById('paraFrm_noOfInstallmentOther').value='';
		}
		
		function clearNoOfInstallmentBlur(){
	if(document.getElementById('paraFrm_emiAmount').value != "")
	document.getElementById('paraFrm_noOfInstallmentOther').value='';
		}
	
	function validateReschedule(){
	//alert(1);
		var fieldNames = ['paraFrm_closureDate'];
		var labelNames = ['loanDatePC'];
		var flag       = ["enter", "enter"];
		
		var balanceAmt = eval(document.getElementById('paraFrm_balanceAmount').value);
		var paidAmt    = document.getElementById('paraFrm_amtPaidByEmp').value;
		
		if(paidAmt == ""){
			paidAmt = 0;
		}
		//alert(2);
		var remainingPrincipalAmount = eval(balanceAmt)-eval(paidAmt);
		var selApplic=document.getElementById('selApplic').innerHTML.toLowerCase();
		if(document.getElementById('paraFrm_loanAppCode').value == ""){
			alert("Please select "+selApplic);
			return false;
		}
		//alert(3);
	var intType = document.getElementById('paraFrm_interestType').value;
	var interestRate = document.getElementById('paraFrm_interestRate').value;
	//var balanceAmt = eval(document.getElementById('paraFrm_balanceAmount').value);
		var paidAmt    = eval(document.getElementById('paraFrm_amtPaidByEmp').value);
		
		if(paidAmt == ""){
			paidAmt = 0;
		}
		if(paidAmt > balanceAmt){
			alert('Your balance amount is Rs. '+balanceAmt+', so please pay only Rs. '+balanceAmt);
			return false;
		}
		if(intType !='N'){
			if(interestRate =="" || eval(interestRate) =="0"){
				alert("Please enter valid "+document.getElementById('intRate').innerHTML.toLowerCase());
				document.getElementById('paraFrm_interestRate').focus();
				return false;
			}
		}
		//alert(intType);
		var calType = document.getElementById('paraFrm_hiddenCalType').value;
		if(intType!='I'){
			var noOfInstall = document.getElementById("paraFrm_noOfInstallmentOther").value;
	 	 	var emiAmount = document.getElementById("paraFrm_emiAmount").value;
	 	 	var nolabel = document.getElementById('remLoanAmnt2').innerHTML;
	 	 	var emiLabel = document.getElementById('emiAmount').innerHTML;
	 	 	if(document.getElementById('paraFrm_closureDate').value==""){
	 	 		alert("Please enter "+document.getElementById('loanDatePC').innerHTML);
	 	 		document.getElementById('paraFrm_closureDate').focus();
	 	 		return false;
	 	 	}
	 	 	if(calType=='E'){
	 	 		if(balanceAmt==paidAmt){
		 	 		document.getElementById('paraFrm_emiAmount').value = "0";
		 	 	}
		 	 	if(balanceAmt!=paidAmt){
			 	 	if(emiAmount ==""){
			 	 		alert("Please enter "+emiLabel);
			 	 		document.getElementById('paraFrm_emiAmount').focus();
			 	 		return false;
			 	 	}
			 	 }
	 	 	}else if(calType=='I'){
		 	 	if(balanceAmt==paidAmt){
		 	 		document.getElementById('paraFrm_noOfInstallmentOther').value = "0";
		 	 	}
		 	 	if(balanceAmt!=paidAmt){
			 	 	if(noOfInstall ==""){
			 	 		alert("Please enter "+nolabel);
			 	 		document.getElementById('paraFrm_noOfInstallmentOther').focus();
			 	 		return false;
			 	 	}
		 	 	}
		 	 
	 	 	}
	 	 	
	 	 		
		
		}else{
			var noOfInstall = document.getElementById("paraFrm_noOfInstallmentsReduceInt").value;
	 	 	var princAmount = document.getElementById("paraFrm_monthlyPrincAmount").value;
	 	 	var nolabel = document.getElementById('remLoanAmnt4').innerHTML;
	 	 	var princLabel = document.getElementById('princAmount').innerHTML;
	 	 	if(document.getElementById('paraFrm_closureDate').value==""){
	 	 		alert("Please enter "+document.getElementById('loanDatePC').innerHTML);
	 	 		document.getElementById('paraFrm_closureDate').focus();
	 	 		return false;
	 	 	}
	 	 	//alert('1');
	 	 	if(calType=='P'){
		 	 	if(balanceAmt==paidAmt){
		 	 		//alert('2');
		 	 		document.getElementById('paraFrm_monthlyPrincAmount').value = "0";
		 	 	}
		 	 	if(balanceAmt!=paidAmt){
		 	 		//alert('3');
			 	 	if(princAmount ==""){
			 	 		//alert('4');
			 	 		alert("Please enter "+princLabel);
			 	 		document.getElementById('paraFrm_monthlyPrincAmount').focus();
			 	 		return false;
			 	 	}
			 	 }
	 	 	}else if(calType=='I'){
	 	 		//alert('5');
	 	 		if(balanceAmt==paidAmt){
	 	 			//alert('6');
		 	 		document.getElementById('paraFrm_noOfInstallmentsReduceInt').value = "0";
		 	 	}
		 	 	if(balanceAmt!=paidAmt){
		 	 		//alert('7');
			 	 	if(noOfInstall ==""){
			 	 		//alert('8');
			 	 		alert("Please enter "+nolabel);
			 	 		document.getElementById('paraFrm_noOfInstallmentsReduceInt').focus();
			 	 		return false;
			 	 	}
		 	 	}
	 	 	
	 	 	
	 	 	/*if(noOfInstall ==""){
	 	 		alert("Please enter "+nolabel);
	 	 		document.getElementById('paraFrm_noOfInstallmentsReduceInt').focus();
	 	 		return false;
	 	 	}*/
	 	 	}
	 	 	
		
		}
		if(!validateDate('paraFrm_closureDate', 'loanDatePC'))return false;

		document.getElementById('paraFrm_remainingPrincipalAmount').value = remainingPrincipalAmount;
		document.getElementById('paraFrm_rescheduleFlag').value = 'true';
		//alert(remainingPrincipalAmount);
	}
	
	function setRescheduleFlag(){
		document.getElementById('paraFrm_rescheduleFlag').value = 'false';
	}
	disableIntRate();
	function disableIntRate1(){
		var intType =document.getElementById("paraFrm_interestType").value ;
		if(intType =='N'){
		document.getElementById("paraFrm_interestRate").readOnly = 'true';
		document.getElementById("paraFrm_interestRate").value ="";
		}else 
		document.getElementById("paraFrm_interestRate").readOnly = '';
	if(intType!='I'){
	document.getElementById('otherRateDiv').style.display ='';
	document.getElementById('reduceIntDiv').style.display ='none';
	
	document.getElementById('paraFrm_noOfInstallmentsReduceInt').value='';
	}else{
		document.getElementById('otherRateDiv').style.display ='none';
		document.getElementById('reduceIntDiv').style.display ='';
		
		document.getElementById('paraFrm_noOfInstallmentOther').value='';
		document.getElementById('paraFrm_emiAmount').value='';
	}
	
	}
	
	function disableIntRate(){
	var intType =document.getElementById("paraFrm_interestType").value ;
		if(intType =='N'){
		document.getElementById("paraFrm_interestRate").readOnly = 'true';
		document.getElementById("paraFrm_interestRate").value ="";
		document.getElementById("intRateTD").style.display='none';
		document.getElementById("intRateTD1").style.display='none';
		}else {
		document.getElementById("paraFrm_interestRate").readOnly = '';
		document.getElementById("intRateTD").style.display='';
		document.getElementById("intRateTD1").style.display='';
		}
	if(intType!='I'){
	if(document.getElementById('paraFrm_hiddenCalType').value=="E"){
			document.getElementById('installmentTR').style.display='none';
			document.getElementById('EMITR').style.display='';
			document.getElementById('otherRateDiv').style.display ='none';
	}else{
		document.getElementById('paraFrm_hiddenCalType').value='I';
		document.getElementById('installmentTR').style.display='';
		document.getElementById('EMITR').style.display='none';
	}
			
			document.getElementById('principalTR').style.display='none';
			document.getElementById('paraFrm_calTypeP').disabled=true;
			document.getElementById('otherRateDiv').style.display ='none';
			document.getElementById('paraFrm_calTypeE').disabled='';
	}else{
	
		if(document.getElementById('paraFrm_hiddenCalType').value=="P"){
			document.getElementById('otherRateDiv').style.display ='none';
			document.getElementById('principalTR').style.display='';
			//document.getElementById('paraFrm_installmentNumber').value='';
		}else{
			document.getElementById('paraFrm_hiddenCalType').value='I';
			document.getElementById('otherRateDiv').style.display ='';
			document.getElementById('principalTR').style.display='none';
		}
			document.getElementById('installmentTR').style.display='none';
			document.getElementById('EMITR').style.display='none';
			document.getElementById('paraFrm_calTypeE').disabled=true;
			//document.getElementById('paraFrm_installmentNumberFlat').value='';
			//document.getElementById('paraFrm_emiAmount').value='';
			document.getElementById('paraFrm_calTypeP').disabled=false;
			
	}
	var calType=document.getElementById('paraFrm_hiddenCalType').value;
	document.getElementById('paraFrm_calType'+calType).checked=true;
	 
}
function callCalculateType(obj){
		var intType =document.getElementById("paraFrm_interestType").value ;
		if(obj.value=='I'){
			if(intType=="I"){
				document.getElementById('installmentTR').style.display='none';
				document.getElementById('otherRateDiv').style.display='';
			}else{
				document.getElementById('installmentTR').style.display='';
				document.getElementById('otherRateDiv').style.display='none';
			}
			document.getElementById('EMITR').style.display='none';
			//document.getElementById('paraFrm_emiAmount').value='';
			document.getElementById('principalTR').style.display='none';
		}else if(obj.value=='E'){
			document.getElementById('installmentTR').style.display='none';
			document.getElementById('EMITR').style.display='';
			document.getElementById('principalTR').style.display='none';
			
		}else if(obj.value=='P'){
			document.getElementById('principalTR').style.display='';
			document.getElementById('installmentTR').style.display='none';
			document.getElementById('EMITR').style.display='none';
			document.getElementById('otherRateDiv').style.display='none';
			//document.getElementById('paraFrm_installmentNumberFlat').value='';
			//document.getElementById('paraFrm_installmentNumber').value='';
			//document.getElementById('paraFrm_emiAmount').value='';
		}
			
			
			document.getElementById('paraFrm_hiddenCalType').value=obj.value;
			//alert(document.getElementById('paraFrm_hiddenCalType').value);
	}
--></script>