<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="LoanProcessing" method="post" name="LeaveForm"
	id="paraFrm" theme="simple">
	
		<s:hidden name="listType"/>
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="myPageInProcess" id="myPageInProcess" />
		<s:hidden name="myPageApproved" id="myPageApproved" />
		
			
	<s:hidden name="installmentPaidFlag" />
	<s:hidden name="noOfPaidInstallments"/><s:hidden name="prePaymentFlag" />
	<s:hidden name="view"/>	
		<s:hidden name="loanRefundableFlag"/>
		<s:hidden name="installmentFlag" />
		<s:hidden name="applicationStatus"/>
		<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">

		<!-- FORM NAME START -->
		<tr>
			<td valign="bottom" class="txt">
			
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Loan Processing </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td width="100%"> <table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0"  >
				<tr>
					<td>	<s:hidden name="hiddenCode" /><s:hidden name="level" /><s:hidden name="hiddenLoginfrmId" />	
					</td>				
				</tr>
			</table>
			</td>
		</tr>
		<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="20%">
							<div align="right"><span class="style2"></span><font
								color="red">*</font>Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
		<tr>
					<td>
					<table width="100%" class="formbg">
						<td colspan="2" id="ctrlShow"><b>Loan Application ID:</b><font
							color="red"></font><s:property value="trackingNo" /> <s:hidden
							name="trackingNo" /></td>

					</table>
					</td>
				</tr>
		<!-- Approver Comments Section Begins -->
				<tr>
					<td>
					<table width="100%" class="formbg">
						<s:if test="approverCommentsFlag">
							<tr>
								<td colspan="2" ><b><label class="set" id="appr.cmt"
										name="appr.cmt" ondblclick="callShowDiv(this);"><%=label.get("appr.cmt")%></label></b>:<font
									color="red">*</font></td>
								<td colspan="3" id="ctrlShow"><s:textarea theme="simple"
									cols="70" rows="3" name="comment" id="comment"
									onkeypress="return imposeMaxLength(event, this, 500);" /></td>



							</tr>
							<s:if test="hrFlag">
							<tr  id="ctrlShow">
								<td colspan="2" ><b><label class="set" id="appr.amt"
										name="appr.amt" ondblclick="callShowDiv(this);"><%=label.get("appr.amt")%></label></b>:<font
									color="red">*</font></td>
								<td  id="ctrlShow"><s:textfield size="25" theme="simple"
									name="apprLoanAmount" maxlength="8"/></td>

							</tr>
							</s:if>
						</s:if>
						<s:if test="listComment">



							<tr>
								<td class="formth" width="05%">Sr No</td>
								<td class="formth" width="15%">Approver Name</td>
								<td class="formth" width="40%" align="center">Comments</td>
								<td class="formth" width="15%">Application Date</td>
								<td class="formth" width="15%">Approve Amount</td>
								<td class="formth" width="15%">Status</td>
							</tr>
							<%
								int tt = 1;
							%>

							<s:iterator value="listComment">
								<tr>
									<td class="sortableTD"><%=tt++%></td>
									<td class="sortableTD"><s:property value="ittApproverName" />&nbsp;</td>
									<td class="sortableTD"><s:property value="ittComments" />&nbsp;
									</td>
									<td class="sortableTD"><s:property
										value="ittApplicationDate" />&nbsp;</td>
									<td class="sortableTD"><s:property value="ittApprAmount" />&nbsp;</td>
									<td class="sortableTD"><s:property value="ittStatus" />&nbsp;</td>
								</tr>
							</s:iterator>
						</s:if>

					</table>
					</td>
				</tr>
				<!-- Approver Comments Section Ends -->
				
				<!-- Accountant Section Start -->
				
					<tr>
						<td colspan="4">
						<table width="100%" border="0" cellpadding="2" cellspacing="1"
							class="formbg">
							<tr>
								<td width="100%" colspan="3" >
								<table width="100%" border="0" align="center" cellpadding="2"
									cellspacing="2" class="formbg"  id="ctrlShow">
									<tr>
										<td colspan="4" class="formhead" width="100%"><strong
											class="forminnerhead">Accountant </strong></td>
									</tr>
									<tr>
									
									
									<s:if test="installmentFlag">
										<td colspan="1" width="25%"><label class="set"
											id="accountNumber" name="accountNumber"
											ondblclick="callShowDiv(this);"><%=label.get("accountNumber")%></label>:<font color="red">*</font> </td>
										<td colspan="1" width="25%" >
											<s:property value="accountNumber"/>
											<s:hidden name="accountNumber" />
										</td>
										<td colspan="1" width="25%"><label class="set"
											id="bankName" name="bankName" ondblclick="callShowDiv(this);"><%=label.get("bankName")%></label>:</td>
										<td colspan="1" width="25%" >
											<s:property value="bankName"/>
											<s:hidden name="bankName" />
										</td>
									</s:if>
									<s:else>
										<td colspan="1" width="25%"><label class="set"
											id="accountNumber" name="accountNumber"
											ondblclick="callShowDiv(this);"><%=label.get("accountNumber")%></label>:<font color="red">*</font> </td>
										<td colspan="1" width="25%" ><s:textfield
											size="25" theme="simple" name="accountNumber" maxlength="18"
											onkeypress="return alphaNumeric();" /></td>
										<td colspan="1" width="25%"><label class="set"
											id="bankName" name="bankName" ondblclick="callShowDiv(this);"><%=label.get("bankName")%></label>:</td>
										<td colspan="1" width="25%" ><s:textfield
											size="25" theme="simple" name="bankName" maxlength="70"/></td>
									</s:else>
									</tr>

									<tr>
										<s:if test="installmentFlag">
											<td colspan="1" width="25%"><label class="set"
											id="sancAmnt" name="sancAmnt" ondblclick="callShowDiv(this);"><%=label.get("sancAmnt")%></label>:<font color="red">*</font></td>
											<td colspan="1" width="25%" >
												<s:property value="sanctionAmount"/>
												<s:hidden name="sanctionAmount" />
											</td>
											<td colspan="1" width="25%"><label class="set"
												id="sancDate" name="sancDate" ondblclick="callShowDiv(this);"><%=label.get("sancDate")%></label>:</td>
											<td colspan="1" width="25%" nowrap="nowrap" >
												<s:property value="sanctionDate"/>
												<s:hidden name="sanctionDate" />
											</td>
										</s:if>
										<s:else>
											<td colspan="1" width="25%"><label class="set"
											id="sancAmnt" name="sancAmnt" ondblclick="callShowDiv(this);"><%=label.get("sancAmnt")%></label>:<font color="red">*</font></td>
											<td colspan="1" width="25%" ><s:textfield
												size="25" theme="simple" name="sanctionAmount"
												onkeypress="return checkNumbersWithDot(this);" onblur="validateAmountLimit();" maxlength="8"/></td>
											<td colspan="1" width="25%"><label class="set"
												id="sancDate" name="sancDate" ondblclick="callShowDiv(this);"><%=label.get("sancDate")%></label>:</td>
											<td colspan="1" width="25%" nowrap="nowrap" ><s:textfield
												size="25" theme="simple" name="sanctionDate" maxlength="10"
												onkeypress="return numbersWithHiphen();" /> <s:a
												href="javascript:NewCal('paraFrm_sanctionDate','DDMMYYYY');">
												<img src="../pages/images/Date.gif" class="iconImage" id="ctrlHide"
													height="16" align="absmiddle" width="16">
											</s:a></td>
										</s:else>
									
									</tr>

									<tr>
										<s:if test="installmentFlag">
											<td colspan="1" width="25%"><label class="set"
												id="payDate" name="payDate" ondblclick="callShowDiv(this);"><%=label.get("payDate")%></label>:<font color="red">*</font></td>
											<td colspan="1" width="25%" nowrap="nowrap">
												<s:property value="paymentDate"/>
												<s:hidden name="paymentDate" />
											</td>
											<td colspan="1" width="25%"><label class="set"
											id="payMode" name="payMode" ondblclick="callShowDiv(this);"><%=label.get("payMode")%></label>:</td>
										<td colspan="1" width="25%" >
											<s:property value="paymentMode"/>
												<s:hidden name="paymentMode" />
										</td>
											
										</s:if>
										<s:else>
											<td colspan="1" width="25%"><label class="set"
												id="payDate" name="payDate" ondblclick="callShowDiv(this);"><%=label.get("payDate")%></label>:<font color="red">*</font></td>
											<td colspan="1" width="25%" nowrap="nowrap"><s:textfield
												size="25" theme="simple" name="paymentDate" maxlength="10"
												onkeypress="return numbersWithHiphen();" /> <s:a
												href="javascript:NewCal('paraFrm_paymentDate','DDMMYYYY');">
												<img src="../pages/images/Date.gif" class="iconImage" id="ctrlHide"
													height="16" align="absmiddle" width="16">
											</s:a></td>
											<td colspan="1" width="25%"><label class="set"
											id="payMode" name="payMode" ondblclick="callShowDiv(this);"><%=label.get("payMode")%></label>:</td>
										<td colspan="1" width="25%" ><s:select
											name="paymentMode" headerKey="" cssStyle="width:150"
											headerValue="Select"
											list="#{'CS':'Cash','TR':'Transfer','CH':'Cheque'}" onchange="return callChequeNumber();"/></td>
										</s:else>
										
									</tr>

									<tr id="showChequeField">
									<s:if test="installmentFlag">
										<td colspan="1" width="25%"><label class="set" id="cheNo"
											name="cheNo" ondblclick="callShowDiv(this);"><%=label.get("cheNo")%></label>:</td>
										<td colspan="1" width="25%" >
											<s:property value="chequeNumber"/>
												<s:hidden name="chequeNumber" />
										</td>
										
									</s:if>
									<s:else>
										<td colspan="1" width="25%"><label class="set" id="cheNo"
											name="cheNo" ondblclick="callShowDiv(this);"><%=label.get("cheNo")%></label>:</td>
										<td colspan="1" width="25%" ><s:textfield
											size="25" theme="simple" name="chequeNumber" maxlength="10"/></td>
									</s:else>
									
									</tr>
							<tr> 
								<s:if test="installmentFlag">
									<td colspan="1" width="25%"><label class="set"
										id="start.Date" name="start.Date"
										ondblclick="callShowDiv(this);"><%=label.get("start.Date")%></label>:<font color="red">*</font></td>
									<td colspan="1" width="25%" nowrap="nowrap" >
										<s:property value="startingDate"/>
												<s:hidden name="startingDate" />
									</td>
								</s:if>
								<s:else>
									<td colspan="1" width="25%"><label class="set"
										id="start.Date" name="start.Date"
										ondblclick="callShowDiv(this);"><%=label.get("start.Date")%></label>:<font color="red">*</font></td>
									<td colspan="1" width="25%" nowrap="nowrap" ><s:textfield
										size="25" theme="simple" name="startingDate" maxlength="10"
										onkeypress="return numbersWithHiphen();" /> <s:a
										href="javascript:NewCal('paraFrm_startingDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage" id="ctrlHide"
											height="16" align="absmiddle" width="16">
									</s:a></td>
								</s:else>
								</tr>
								
								<tr >
									<td colspan="1" width="22%"><label class="set" id="intType"
										name="intType" ondblclick="callShowDiv(this);"><%=label.get("intType")%></label>:</td>
									<td colspan="1" width="25%"  id="ctrlShow"><s:select name="interestType"
										onchange="return disableIntRate();" cssStyle="width:150"
										list="#{'N':'No Interest','F':'Flat Interest','R':'Reducing Principal','I':'Reducing Interest'}" /></td>
					
									<td colspan="1" id='intRateTD'><label class="set"
										id="intRate" name="intRate" ondblclick="callShowDiv(this);"><%=label.get("intRate")%></label>:<font color="red">*</font></td>
									<td colspan="1"  id='intRateTD1'>
										<s:textfield name="interestRate" onkeypress="return checkNumbersWithDot(this);"  maxlength="5" />
									</td>
								</tr>
								<tr  id="ctrlShow">
										<td colspan="1" width="25%"><label class="set"
											id="calcEMIBy" name="calcEMIBy"
											ondblclick="callShowDiv(this);"><%=label.get("calcEMIBy")%></label>:</td>
										<td  colspan="2"  id="ctrlShow"><s:checkbox
											name="noOfInstallment" id="noOfInstallment"  onclick="showfullTimeFlag();" /> No.
										of Installment<s:checkbox name="emiAmt" id="emiAmt"
											onclick="showEMIAMTFlag();" /> EMI Amount<s:checkbox
											name="prnAmt" id="prnAmt" onclick="showPRNAMTFlag();" /> Principal
										Amount</td>
										<s:hidden name="hiddenCalfrmId" id="hiddenCalfrmId" /> 
									</tr>
									<tr id='installmentTR'>
										<td colspan="1" width="25%" id="installment"><label
											class="set" id="number.Installment" name="number.Installment"
											ondblclick="callShowDiv(this);"><%=label.get("number.Installment")%></label>:<font color="red">*</font></td>
										<td colspan="1" width="25%"  id="ctrlShow"><s:textfield
											size="25" theme="simple" name="installmentNumberFlat"
											maxlength="3" onkeypress="return numbersOnly();" /></td>
										<td colspan="1" width="25%"></td>
										<td colspan="1" width="25%"></td>
									</tr>
									<tr id='EMITR'>
										<td colspan="1" width="25%" ><label
											class="set" id="emiAmount" name="emiAmount"
											ondblclick="callShowDiv(this);"><%=label.get("emiAmount")%></label>:<font color="red">*</font></td>
										<td colspan="1" width="25%"  id="ctrlShow"><s:textfield
											size="25" theme="simple" name="emiAmount" maxlength="10"
											onkeypress="return checkNumbersWithDot(this);" onblur="validateEMIAmountLimit();" /></td>
										<td colspan="1" width="25%"></td>
										<td colspan="1" width="25%"></td>

									</tr>

									<tr id='principalTR'>
										<td colspan="1" width="25%"><label class="set"
											id="princAmount" name="princAmount"
											ondblclick="callShowDiv(this);"><%=label.get("princAmount")%></label>:<font color="red">*</font></td>
										<td colspan="1" width="25%"  id="ctrlShow"><s:textfield
											size="25" theme="simple" name="monthlyPrincAmount"
											maxlength="10" onkeypress="return checkNumbersWithDot(this);" onblur="validatePRNAmountLimit();" /></td>
										<td colspan="1" width="25%"></td>
										<td colspan="1" width="25%"></td>

									</tr>
									
									
									<s:if test='%{applicationStatus=="A"}'> 
									<tr>
									<td colspan="4" width="100%" align="center"><s:submit
										action="LoanProcessing_rescheduleInstallments"
										value="Reschedule Loan Installments" cssClass="token"
										onclick="return callInstSchedule();"></s:submit></td>

								</tr>
									</s:if>
									<s:if test='%{applicationStatus=="F"}'> 
									<tr >
										<td colspan="1" width="25%"></td>
										<td width="75%" colspan="3" nowrap="nowrap"  id="ctrlShow"><!--  <input type="button" class="token" theme="simple" onclick="return callGuarantor();"  value="Guarantor Details" />-->
										<input type="button" class="token" theme="simple"
											onclick="return callInstSchedule();"
											value="Generate Installment Schedule" /></td>
									</tr>
									</s:if>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<s:if test="loanRefundableFlag">
				<s:if test="installmentFlag">
					<tr>
						<td colspan="4">
						<table width="100%" border="0" cellpadding="2" cellspacing="0"
							class="formbg" >
							<tr>
								<td>
								<table width="98%" border="0" align="center" cellpadding="2"
									cellspacing="0">
									<tr>
										<td colspan="4" class="formhead"><strong
											class="forminnerhead"> <label class="set"
											id="instaDtl" name="instaDtl" ondblclick="callShowDiv(this);"><%=label.get("instaDtl")%></label></strong></td>
									</tr>

									<tr>
											<td width="5%" class="formth"><label class="set"
												id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
											<td width="20%" class="formth" align="left"><label
												class="set" id="instaDate" name="instaDate"
												ondblclick="callShowDiv(this);"><%=label.get("instaDate")%></label></td>
											<td width="20%" class="formth" align="right">
											<%
												String lable = (String) request.getAttribute("lable");
												if (lable != null) {
													out.println(lable);
												}
											%>
											</td>
											<td width="20%" class="formth" align="right"><label
												class="set" id="instaAmnt" name="instaAmnt"
												ondblclick="callShowDiv(this);"><%=label.get("instaAmnt")%></label></td>
											<td width="20%" class="formth" align="right"><label
												class="set" id="emiAmnt" name="emiAmnt"
												ondblclick="callShowDiv(this);"><%=label.get("emiAmnt")%></label></td>
											<td width="25%" class="formth" align="center"><label
												class="set" id="isPaid" name="isPaid"
												ondblclick="callShowDiv(this);"><%=label.get("isPaid")%></label></td>

										</tr>

									<tr>
									<td class="formtext" colspan="6" >
									<div class="scrollF9" id="scrollDiv">
									<table width="100%" border="0" cellpadding="2" cellspacing="2"
										class="sortable"  id="instlDetlTable">
									
										<%
										int i = 0;
										%>


										<s:iterator value="installmentList">

											<%
												int j = 0;
											int jh = 0;
												String audFlag = "";
												try {
													HashMap afdata = (HashMap) request.getAttribute("data");
											%>

											<%
												audFlag = (String) afdata.get("" + i);
												} catch (Exception e) {
													System.out.println("exception in JSP");
													e.printStackTrace();
												}
											%>
											<tr>

												<td class="border2" width="5%"><%=i + 1%></td>
												<td class="border2" width="20%" align="left"><s:property
													value="monthYear" /><s:hidden name="monthYear" /><s:hidden name="monthNo" /></td>
												<td class="border2" width="20%" align="right"><s:property
													value="principalAmt" /><s:hidden name="principalAmt" /></td>
												<td class="border2" width="20%" align="right"><s:property
													value="interestAmt" /><s:hidden name="interestAmt" /></td>
												<td class="border2" width="20%" align="right"><s:property
													value="installmentAmt" /><s:hidden name="installmentAmt" /></td>
												<td class="border2" width="25%" align="center"><s:if
													test="%{paidFlag}">
													<input type="checkbox" class="checkbox" checked="checked" disabled="disabled"
														name="isPaid"
														value="<%=audFlag.equals("Y")?"checked":""%>"
														onclick="callChk(<%=i%>)" />
												</s:if> <s:else>
													<input type="checkbox" class="checkbox" name="isPaid"
														value="<%=audFlag.equals("Y")?"checked":""%>"
														onclick="callChk(<%=i%>)" />
												</s:else><input type="hidden" name="checkFlag" id="checkFlag<%=i%>"
													value="<%=audFlag.equals("N")?"N":audFlag%>" /> <s:hidden
													name="balancePrincipalAmt" /></td>
											</tr>


											<%
											i++;
											%>

										</s:iterator>
										

									</table></div>

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
											<td class="border2" width="25%">&nbsp;</td>
										</tr>			

</table>
								</td>
							</tr>

						</table>
						</td>
					</tr>
				</s:if>

				<s:if test="prePaymentFlag">
					<tr>
						<td colspan="3">
						<table width="100%" border="0" cellpadding="2" cellspacing="2"
							class="formbg">
							<tr>
								<td>
								<table width="98%" border="0" align="center" cellpadding="2"
									cellspacing="2">
									<tr>
										<td colspan="3" class="formhead"><strong
											class="forminnerhead"><label class="set"
											id="prepaymentdetails" name="prepaymentdetails"
											ondblclick="callShowDiv(this);"><%=label.get("prepaymentdetails")%></label>
										</strong></td>
									</tr>

									<td class="formtext" colspan="7" >
									<table width="100%" border="0" cellpadding="2" cellspacing="2"
										class="sortable">
										<tr>
											<td width="10%" class="formth" colspan="1" align="right"><label class="set"
												id="sno1" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
											<td width="30%" class="formth"  colspan="3" align="left"><label
												class="set" id="paydate1" name="payDate"
												ondblclick="callShowDiv(this);"><%=label.get("payDate")%></label></td>
											<td width="30%" class="formth" colspan="3" align="right"><label
												class="set" id="payAmnt" name="payAmnt"
												ondblclick="callShowDiv(this);"><%=label.get("payAmnt")%></label></td>
											<!--  <td width="30%" class="formth" align="right"><label
												class="set" id="penInstallment" name="penInstallment"
												ondblclick="callShowDiv(this);"><%=label.get("penInstallment")%></label></td>
											<td width="30%" class="formth" align="right"></td>-->
										</tr>

										<%
										int k = 0;
										%>

										<s:if test="noData">
											<tr>
												<td width="100%" colspan="7" align="center"><font
													color="red"><label class="set" id="noPaydone"
													name="noPaydone" ondblclick="callShowDiv(this);"><%=label.get("noPaydone")%></label></font></td>
											</tr>
										</s:if>
										<s:iterator value="prePaymentList">

											<tr>

												<td class="border2" width="10%" colspan="1" align="center"><%=k + 1%></td>
												<td class="border2" width="30%" colspan="3" align="center"><s:property
													value="prePaymentDate" /><s:hidden name="prePaymentDate" /></td>
												<td class="border2" width="30%" colspan="3" align="center"><s:property
													value="prePaymentAmt" /><s:hidden name="prePaymentAmt" /></td>
												<!--  <td class="border2" width="30%" align="right"><s:property
													value="pendingInstallments" /><s:hidden
													name="pendingInstallments" />&nbsp;</td>
												<td class="border2" width="30%" align="right">&nbsp;</td>-->
											</tr>


											<%
											k++;
											%>

										</s:iterator>

										<tr>

											<td class="border2" width="5%" colspan="1">&nbsp;</td>
											<td class="border2" width="20%" colspan="3" align="right"><b><label
												class="set" id="total" name="total"
												ondblclick="callShowDiv(this);"><%=label.get("total")%></label></b></td>
											<td class="border2" width="20%" colspan="3" align="center"><s:property
												value="totalPrePaymenAmt" /></td>
										</tr>


									</table>

									</td>

								</table>
								</td>
							</tr>

						</table>
						</td>
					</tr>
				</s:if>
				</s:if>
					
					
		

				<!-- Accountant Section End -->
		<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="1"
						class="formbg">
						<tr>
							<td width="100%" colspan="3">
							<table width="98%" border="0" align="center" cellpadding="2"
								cellspacing="1">
								<tr>
									<td colspan="4" class="formhead" width="100%"><strong
										class="forminnerhead"><label class="set" id="loanApp"
										name="loanApp" ondblclick="callShowDiv(this);"><%=label.get("loanApp")%></label></strong></td>
								</tr>

								<tr>
									
									<s:hidden name="year" />
									<s:hidden name="loanApplCode"></s:hidden>
									<s:hidden name='pfLoanCode' />
									<td colspan="1" width="25%"><label class="set"
										id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font
										color="red">*</font> </td>
									<td colspan="3" width="25%">
									<s:property value="empToken"/><s:hidden name="empToken"></s:hidden>
									<s:property value="empName"/><s:hidden
										name="empCode" /><s:hidden name="empName"></s:hidden>
									</td>
									<td colspan="1" width="25%"></td>
									<td colspan="1" width="25%"></td>
								</tr>

								<tr>
									<td colspan="1" width="25%"><label class="set" id="branch"
										name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
									<td colspan="1" width="25%"><s:hidden name="branchCode" />
									<s:property value="branchName"/><s:hidden name="branchName" />
									
									</td>
									<td colspan="1" width="25%"><label class="set"
										id="department" name="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
									<td colspan="1" width="25%"><s:hidden name="deptCode" />
									<s:property value="deptName"/><s:hidden name="deptName" />
									</td>
								</tr>

								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="designation" name="designation"
										ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
									<td colspan="1" width="25%"><s:hidden name="desgCode" />
									<s:property value="desgName"/><s:hidden name="desgName" />
									
									</td>
									<td colspan="1" width="25%"><label class="set"
										id="joinDate" name="joinDate" ondblclick="callShowDiv(this);"><%=label.get("joinDate")%></label>:</td>
									<td colspan="1" width="25%">
									<s:property value="confirmationDate"/><s:hidden name="confirmationDate" />
									</td>
								</tr>

								<tr>
									<td colspan="1" width="25%"><label class="set" id="grade"
										name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
									<td colspan="1" width="25%"><s:hidden name="gradeCode" />
									<s:property value="grade"/><s:hidden name="grade" />
									
									</td>
									<!--<td colspan="1" width="23%" ><label  class = "set"  id="gross.sal" name="gross.sal" ondblclick="callShowDiv(this);"><%=label.get("gross.sal")%></label> :</td>
									<td colspan="1" width="23%">
										<s:textfield size="25" theme="simple" name="grossSalary" readonly="true"/></td>
								-->
									<td colspan="1" width="25%"><label class="set"
										id="dateApp" name="dateApp" ondblclick="callShowDiv(this);"><%=label.get("dateApp")%></label>:</td>
									<td colspan="1" width="25%">
									<s:property value="applicationdate"/><s:hidden name="applicationdate" />
									</td>

								</tr>

								<tr>


									<!--<td colspan="1" width="25%"><label class="set"
										id="appStatus" name="appStatus"
										ondblclick="callShowDiv(this);"><%=label.get("appStatus")%></label>
									:</td>
									<td colspan="1" width="25%">
									<s:property value="applicationStatus"/><s:hidden name="applicationStatus"/>
									</td>

									--><td><s:hidden name="divCode" /><s:hidden name="divName" /></td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="50%" nowrap="nowrap"><strong>The
					Approver(s) for this application :</strong></td>
					<td colspan="2" nowrap="nowrap"></td>

				</tr>
				<!-- APPROVER LIST  TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<%
							int y = 1;
							%>
							<%!int z = 0;%>
							<s:iterator value="approverList">
								<tr><s:hidden name="approverCode" />
									<td><s:hidden name="approverName" /><STRONG><s:property
										value="srNoIterator" /></STRONG> <s:property value="approverName" /></td>

								</tr>
								<%
								y++;
								%>
							</s:iterator>
							<%
							z = y;
							%>
						</tr>
					</table>
					</td>

				</tr>
				</table></td>
				</tr>

				<!-- APPROVER LIST  TABLE  ENDS -->
				
				
				<!-- Loan Details Start -->
				
				<tr>
					<td colspan="4">
					<table width="100%" border="0" cellpadding="2" cellspacing="1"
						class="formbg">
						<tr>
							<td width="100%" colspan="3">
							<table width="100%" border="0" align="center" cellpadding="2"
								cellspacing="2">
								<tr>
									<td colspan="4" class="formhead" width="100%"><strong
										class="forminnerhead">Loan Details</strong></td>
								</tr>

								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="typeLoan" name="typeLoan" ondblclick="callShowDiv(this);"><%=label.get("typeLoan")%></label>:<font
										color="red">*</font></td>
									<td colspan="1" width="25%"><s:hidden name="loanCode"/> 
									<s:property value="loanType"/><s:hidden name="loanType"/> 
									 </td>
									<td  colspan="1" width="25%"><label class="set" id="loanAmnt" name="loanAmnt"
										ondblclick="callShowDiv(this);"><%=label.get("loanAmnt")%></label>:</td>
									<td colspan="1" width="25%">
									<s:property value="loanAllowedLimit"/><s:hidden name="loanAllowedLimit"/> 
									</td>

								</tr>
								
								
								<tr>
									<td colspan="1" width="25%" id='pfBalanceTD1'><label
										class="set" id="pfBalanceLable" name="pfBalanceLable"
										ondblclick="callShowDiv(this);"><%=label.get("pfBalanceLable")%></label>
									:</td>
									<td colspan="1" width="25%" id='pfBalanceTD2'><s:textfield
										size="25" theme="simple" name="pfBalance" readonly="true" /></td>
								</tr>
								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="amtLoan" name="amtLoan" ondblclick="callShowDiv(this);"><%=label.get("amtLoan")%></label>:<font
										color="red">*</font></td>
									<td colspan="1" width="25%"><s:if test="isApprove">
										<s:textfield size="25" theme="simple" name="loanAmount"
											readonly="true" />
									</s:if> <s:else>
										

								<s:property value="loanAmount"/><s:hidden name="loanAmount"></s:hidden>

										<!--<input type="button" value="Calculate EMI" class="token" onclick="callCalculateEMI();"/>
										-->
									</s:else></td>
									<td colspan="1" width="25%" id="subTypeTD1"><label
										class="set" id="loanSubType" name="loanSubType"
										ondblclick="callShowDiv(this);"><%=label.get("loanSubType")%></label><font
										color="red">*</font> :</td>
									<td colspan="1" width="25%" id="subTypeTD2"><s:if
										test="isApprove">
										<s:radio list="#{'Y':'Yes','N':'No'}" disabled="true"
											theme="simple" name="loanSubType" />
									</s:if><s:else>
										<s:radio list="#{'Y':'Yes','N':'No'}" theme="simple"
											name="loanSubType" />
									</s:else><s:hidden name='hiddenLoanSubType'></s:hidden></td>
									
								</tr>

								<tr>
									<td width="25%"><label class="set" id="calculate.emi"
										name="calculate.emi" ondblclick="callShowDiv(this);"><%=label.get("calculate.emi")%></label>:</td>
									<td width="25%"><label class="set" id="expected.emi"
										name="expected.emi" ondblclick="callShowDiv(this);"><%=label.get("expected.emi")%></label>:<s:checkbox name="expectedEmi"
										onclick="return callOptions('expectedEmi');" disabled="true"></s:checkbox> <label
										class="set" id="tenure" name="tenure"
										ondblclick="callShowDiv(this);"><%=label.get("tenure")%></label>:<s:checkbox name="tenure"
										onclick="return callOptions('tenure');" disabled="true"></s:checkbox></td>

									<s:hidden name="hiddenChechfrmId" id="hiddenChechfrmId" />
								</tr>

								<tr id='purposeTR'>

									<td colspan="1" width="25%"><label class="set"
										id="purpose" name="purpose" ondblclick="callShowDiv(this);"><%=label.get("purpose")%></label>
									:</td>
									<td colspan="1" width="25%"><s:textfield
										name='loanPurposeValue' size="25" readonly="true"></s:textfield><s:hidden
										name='loanPurpose'></s:hidden> <s:if test="isApprove"></s:if><s:else>
										<img src="../pages/images/recruitment/search2.gif" height="16"
											align="absmiddle" width="18" theme="simple" id="ctrlHide"
											onclick="javascript:callsF9(500,325,'LoanApplication_f9Purpose.action');">
									</s:else></td>
									<td colspan="1" width="25%"><label class="set"
										id="eligibility" name="eligibility"
										ondblclick="callShowDiv(this);"><%=label.get("eligibility")%></label>
									:</td>
									<td colspan="1" width="25%"><s:textarea cols="23" rows="2"
										theme="simple" name="loanEligibility" readonly="true" /></td>
								</tr>
								<tr id="limitTR">
									<td class="formtext" width="25%"><label class="set"
										id="minSanctLimit" name="minSanctLimit"
										ondblclick="callShowDiv(this);"><%=label.get("minSanctLimit")%></label>(<s:property
										value='minSanctionLimit' /> % of PF Balance) :</td>
									<td height="22" width="25%"><s:textfield
										name="minSanctionAmt" readonly="true" size="25" /><s:hidden
										name='minSanctionLimit' /></td>
									<td height="22" class="formtext" width="25%"><label
										class="set" id="maxSanctLimit" name="maxSanctLimit"
										ondblclick="callShowDiv(this);"><%=label.get("maxSanctLimit")%></label>(<s:property
										value='maxSanctionLimit' /> % of PF Balance) :</td>
									<td height="22" width="25%"><s:textfield
										name="maxSanctionAmt" readonly="true" size="25" /><s:hidden
										name='maxSanctionLimit' /></td>
								</tr>
								<tr>
									<td width="25%" colspan="1"><label class="set"
										name="purposeName" id="paraFrm_purposeName"
										ondblclick="callShowDiv(this);"><%=label.get("applicantComments")%></label>:</td>
									<td width="25%" colspan="3">
									<s:property value="applicantComment"/>
									</td>
								</tr>


							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				
				<!-- Loan Details End -->
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="20%">
							<div align="right"><span class="style2"></span><font
								color="red">*</font>Indicates Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
		</table>
	
	<s:hidden name="source" id="source" /> 
	<s:hidden name='hiddenCalType'/>
</s:form> 

<script>
 	function callLoanType(){
	 
	var loanCode= document.getElementById('paraFrm_loanCode').value;
	var pfLoanCode =document.getElementById('paraFrm_pfLoanCode').value;
		if(loanCode==pfLoanCode && pfLoanCode!=""){
			document.getElementById('subTypeTD1').style.display='none';
			document.getElementById('subTypeTD2').style.display='none';
			document.getElementById('pfBalanceTD1').style.display='none'; 
			document.getElementById('pfBalanceTD2').style.display='none';
			document.getElementById('limitTR').style.display='none';
			document.getElementById('purposeTR').style.display='none';
		}else{
			document.getElementById('subTypeTD1').style.display='none';
			document.getElementById('subTypeTD2').style.display='none';
			document.getElementById('pfBalanceTD1').style.display='none';
			document.getElementById('pfBalanceTD2').style.display='none';
			document.getElementById('limitTR').style.display='none';
			document.getElementById('purposeTR').style.display='none';
		}
		
	}
	
	onloadFun();
	function onloadFun(){
	disableIntRate();
		callLoanType(); 
		callChequeNumber();
	///	showfullTimeFlag();
	///	showEMIAMTFlag();
	///	showPRNAMTFlag();
		
		var loanSubType = document.getElementById('paraFrm_hiddenLoanSubType').value ;
		document.getElementById('paraFrm_loanSubType'+loanSubType).checked =true;
		
	}
	
	function backtolistFun_old()
		{
			try{
					document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
					document.getElementById('paraFrm').submit();
			}
			catch(e)
			{
				//alert("Error-------"+e);
			}
		}
		
	function backtolistFun(){

 
		document.getElementById('paraFrm').target = "_self";
		//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else{
		document.getElementById('paraFrm').action = 'LoanProcessing_callBack.action';
		}
		document.getElementById('paraFrm').submit();

	}
	function saveFun() {
		approveFun();
	}
		
		function approveFun() {
				var vv=confirm("Do you really want to approve this application?");
				if(vv){
				try{
				
				
					document.getElementById('paraFrm_applicationStatus').value = 'A';	
					var levelVal = document.getElementById('paraFrm_level').value;
					///alert(levelVal);
					
					var loanCodeVal = document.getElementById('paraFrm_hiddenCode').value;
					///alert(loanCodeVal);
					
					var comment = trimData(document.getElementById('comment').value);
			
			if(document.getElementById('comment').value==""){
				alert("Please enter "+document.getElementById('appr.cmt').innerHTML.toLowerCase());
					document.getElementById("comment").focus();
				 	return false;
			}
	if(comment==''){
			 alert ("Please enter " +document.getElementById('appr.cmt').innerHTML.toLowerCase());
			return false;
		}
					
					var apprAmount = document.getElementById('paraFrm_sanctionAmount').value;
					
					if(apprAmount==""){
						alert("Please enter "+document.getElementById('sancAmnt').innerHTML.toLowerCase());
							document.getElementById("paraFrm_sanctionAmount").focus();
						 	return false;
					}
					
					var loginVal = document.getElementById('paraFrm_hiddenLoginfrmId').value;
					///alert(loginVal);
					
					
						if(document.getElementById('paraFrm_accountNumber').value==""){
							alert("Please Enter Account Number");
							document.getElementById('paraFrm_accountNumber').focus();
				  			return false;
					
					}
					 var paymentDate = document.getElementById("paraFrm_paymentDate").value;
					
					 if( paymentDate=="")
						{
							alert("Please select or enter "+document.getElementById('payDate').innerHTML.toLowerCase());
							document.getElementById('paraFrm_paymentDate').focus();
						 	return false;	
						}
						
						if(!document.getElementById('paraFrm_paymentDate').value==''){		
							var check1= validateDate('paraFrm_paymentDate', 'payDate');
							if(!check1){
							return false;
						}
							}
							
						 var startingDate = document.getElementById("paraFrm_startingDate").value;
					
					 if( startingDate=="")
						{
							alert("Please select or enter "+document.getElementById('start.Date').innerHTML.toLowerCase());
							document.getElementById('paraFrm_startingDate').focus();
						 	return false;	
						}
						
						if(!document.getElementById('paraFrm_startingDate').value==''){		
							var check1= validateDate('paraFrm_startingDate', 'start.Date');
							if(!check1){
							return false;
						}
							}
							
					
						 var hiddenCalfrmId = document.getElementById("hiddenCalfrmId").value;
					
					 if( hiddenCalfrmId=="T" && document.getElementById("paraFrm_installmentNumberFlat").value=="")
						{
							alert("Please enter "+document.getElementById('number.Installment').innerHTML.toLowerCase());
							document.getElementById("paraFrm_installmentNumberFlat").focus();
						 	return false;	
						}
						if( hiddenCalfrmId=="E" && document.getElementById("paraFrm_emiAmount").value=="")
						{
							alert("Please enter "+document.getElementById('emiAmount').innerHTML.toLowerCase());
							document.getElementById("paraFrm_emiAmt").focus();
						 	return false;	
						}
						if( hiddenCalfrmId=="P" && document.getElementById("paraFrm_monthlyPrincAmount").value=="")
						{
							alert("Please enter "+document.getElementById('princAmount').innerHTML.toLowerCase());
							document.getElementById("paraFrm_monthlyPrincAmount").focus();
						 	return false;	
						}
						
		
		if(document.getElementById('hiddenCalfrmId').value == "")
		{
			alert("please select Calculate EMI ");
			return false;
		}
						
			if(document.getElementById('paraFrm_loanRefundableFlag').value=="true"){
					
				 if(document.getElementById("paraFrm_installmentFlag").value=="false"){
					  		alert("Please generate Installment schedule !");
					 		 return false;
					 }
			 }
				///	alert(apprAmount);
					document.getElementById('paraFrm').target = "_self";
				      	document.getElementById('paraFrm').action = 'LoanProcessing_save.action?status='+'A'+'&loanCodeVal='+loanCodeVal+'&apprAmount='+apprAmount;
						document.getElementById('paraFrm').submit();
						
				} catch(e)
				
				{
					////alert(e);
				}  
				
			}
	//alert(document.getElementById('paraFrm_applicationStatus').value);
	}
	
	function showfullTimeFlag(){
		if(document.getElementById('noOfInstallment').checked){
			document.getElementById('installmentTR').style.display = '';
			document.getElementById('EMITR').style.display = 'none';
			document.getElementById('principalTR').style.display = 'none';
			document.getElementById('noOfInstallment').checked =true;
			document.getElementById('hiddenCalfrmId').value = "T";
			document.getElementById('prnAmt').checked =false;
			document.getElementById('emiAmt').checked =false;
			document.getElementById('paraFrm_emiAmount').value='';
			////document.getElementById('paraFrm_princAmount').value='';
			document.getElementById('paraFrm_monthlyPrincAmount').value='';
		}else{
		document.getElementById('hiddenCalfrmId').value = "";
		}
	}
	function showEMIAMTFlag(){
		if(document.getElementById('emiAmt').checked){
			document.getElementById('paraFrm_installmentNumberFlat').value='';
			document.getElementById('installmentTR').style.display = 'none';
			document.getElementById('principalTR').style.display = 'none';
			document.getElementById('EMITR').style.display = '';
			document.getElementById('emiAmt').checked =true;
			document.getElementById('hiddenCalfrmId').value = "E";
			document.getElementById('prnAmt').checked =false;
			document.getElementById('noOfInstallment').checked =false;
			///document.getElementById('paraFrm_princAmount').value='';
			document.getElementById('paraFrm_monthlyPrincAmount').value='';
			
		}else{
		document.getElementById('hiddenCalfrmId').value = "";
		}
	}
	
	function showPRNAMTFlag(){
		if(document.getElementById('prnAmt').checked){
		document.getElementById('paraFrm_emiAmount').value='';
			document.getElementById('paraFrm_installmentNumberFlat').value='';
			document.getElementById('installmentTR').style.display = 'none';
			document.getElementById('EMITR').style.display = 'none';
			document.getElementById('principalTR').style.display = '';
			document.getElementById('prnAmt').checked =true;
			document.getElementById('hiddenCalfrmId').value = "P";
			document.getElementById('emiAmt').checked =false;
			document.getElementById('noOfInstallment').checked =false;
		}else{
		document.getElementById('hiddenCalfrmId').value = "";
		}
	}
	function callChequeNumber() {
	
	var actionReason= document.getElementById('paraFrm_paymentMode').value;
		
		if(actionReason == 'CH' ) {
			document.getElementById('showChequeField').style.display='';
		} else {
			document.getElementById('showChequeField').style.display='none';
			document.getElementById('paraFrm_chequeNumber').value='';
		}
	}
	
	function callInstSchedule(){
	try{
	var table = document.getElementById('instlDetlTable');
	///alert("table="+table);
	if(table!=null){
		var rowCount = table.rows.length; 
		///alert("rowCount="+rowCount);
		var count = 0;
		for(var i=0;i<(rowCount);i++){
			if(document.getElementById('checkFlag'+i).value=='Y'){
			count++;
			}
		}
		///alert("count="+count);
		if(count==rowCount){
		alert("All Installments are already paid for this schedule.");
			return false;
		
		}
	}
					
	
		var paymentDate =  document.getElementById('paraFrm_paymentDate').value;
		var startingDate =  document.getElementById('paraFrm_startingDate').value;

	if(!paymentDate == "" && !startingDate == ""){
	if(!dateDifferenceEqual(paymentDate,startingDate,'paraFrm_paymentDate','payDate','start.Date')){
						document.getElementById('paraFrm_startingDate').focus();
						document.getElementById('paraFrm_startingDate').value="";
						return false;   	
	   				}
	  }	
	  
	  var startingDate = document.getElementById("paraFrm_startingDate").value;
					
					 if( startingDate=="")
						{
							alert("Please select or enter "+document.getElementById('start.Date').innerHTML.toLowerCase());
							document.getElementById('paraFrm_startingDate').focus();
						 	return false;	
						}	
	
	
		
		 var interestRateVar = document.getElementById("paraFrm_interestRate").value;
		if(document.getElementById("paraFrm_interestType").value=='F'  &&  interestRateVar==""){
		alert("Please enter "+document.getElementById('intRate').innerHTML.toLowerCase());
							document.getElementById('paraFrm_interestRate').focus();
						 	return false;	
		}
		
		if(document.getElementById("paraFrm_interestType").value=='R'  &&  interestRateVar==""){
		alert("Please enter "+document.getElementById('intRate').innerHTML.toLowerCase());
							document.getElementById('paraFrm_interestRate').focus();
						 	return false;	
		}
		
		if(document.getElementById("paraFrm_interestType").value=='I'  &&  interestRateVar==""){
		alert("Please enter "+document.getElementById('intRate').innerHTML.toLowerCase());
							document.getElementById('paraFrm_interestRate').focus();
						 	return false;	
		}
		
		
		
		var installmentPaidFlag = document.getElementById('paraFrm_installmentPaidFlag').value;
		var prepaymentFlag = document.getElementById('paraFrm_prePaymentFlag').value;
		//var intType = document.getElementById("paraFrm_interestType").value;
		var nolabel = document.getElementById('number.Installment').innerHTML;
		if(installmentPaidFlag=="true"){
			//alert("One or more installments are already paid for this schedule");
			var conf=confirm("One or more installments are already paid for this schedule. \nDo you want to reschedule for remaining principal ?");
			if(conf){
					var paidInstallments = document.getElementById("paraFrm_noOfPaidInstallments").value;
					var noOfInstallmentField ="paraFrm_installmentNumberFlat";
					
						noOfInstallmentField = "paraFrm_installmentNumberFlat";
					
					var noOfInstallments = document.getElementById(noOfInstallmentField).value ;
					//alert("paidInstallments=="+paidInstallments+" and noOfInstallments=="+noOfInstallments);
					if(eval(paidInstallments)>= eval(noOfInstallments)){
						alert(nolabel+" must be greater than total no. of paid installments");
						document.getElementById(noOfInstallmentField).focus();
						return false;
					}
				}else {
			return false;
			}
		}else if(prepaymentFlag=="true"){
			alert("One or more prepayments are done for this schedule");
			var conf=confirm("One or more prepayments are done for this schedule. \nDo you want to reschedule for remaining principal ?");
			if(conf){
				}else {
			return false;
			}
		}
		
		if(document.getElementById('hiddenCalfrmId').value == "")
		{
			alert("please select Calculate EMI ");
			return false;
		}
				
		var fieldname  = ["paraFrm_sanctionAmount","paraFrm_paymentDate"];
		var lableName  = ["sancAmnt","payDate"];
		var flag       = ["enter","select"];
	
		var loanAmt    = document.getElementById("paraFrm_loanAmount").value;
	    var sancAmt    = document.getElementById("paraFrm_sanctionAmount").value;
	    
	    var paymentDate = document.getElementById("paraFrm_paymentDate").value;
	  
	    
		if(document.getElementById("paraFrm_hiddenCode").value==""){
			alert("Please select the record to generate Installment Schedule");
			return false;
		}else
		if(eval(loanAmt)< eval(sancAmt)){
	  		alert(document.getElementById('sancAmnt').innerHTML+" should be equal to or less than "+document.getElementById('loanAmnt').innerHTML);
	  		document.getElementById("paraFrm_sanctionAmount").focus();
	  		return false;
	 	}else
	 	if(!validateBlank(fieldname,lableName, flag))
	 	    return false;
	 	    
	 	   var noOfInstall=''; 
	 	 noOfInstall = document.getElementById("paraFrm_installmentNumberFlat").value;
	 	 	var emiAmount = document.getElementById("paraFrm_emiAmount").value;
	 	 	var principalAmt = document.getElementById("paraFrm_monthlyPrincAmount").value;
	 	 	
	 	 	var emiLabel = document.getElementById('emiAmount').innerHTML;
	 	 	var principalAmtLabel = document.getElementById('princAmount').innerHTML;
	 	 	
	 	 	if(document.getElementById('emiAmt').checked){
	 	 		if(emiAmount ==""){
	 	 		alert("Please enter "+emiLabel);
	 	 		document.getElementById("paraFrm_emiAmount").focus();
	 	 		return false;
	 	 	}	
	 	 	}else if(document.getElementById('prnAmt').checked){
	 	 		if(principalAmt ==""){
	 	 		alert("Please enter "+principalAmtLabel);
	 	 		document.getElementById("paraFrm_monthlyPrincAmount").focus();
	 	 		return false;
	 	 	}	
	 	 	}
	 	 	else{
	 	 		if(noOfInstall==""){
	 	 		alert("Please enter "+nolabel);
	 	 		 
	 	 			document.getElementById("paraFrm_installmentNumberFlat").focus();
	 	 		
	 	 		return false;
	 	 	}	
	 	 	}
	 	 	
	 	
	 	 if(!validateDate('paraFrm_paymentDate','payDate')){
				return false;
				}
		
		if(!dateDifferenceEqual(paymentDate, "payDate")){
		return false;
		} 
	 	
	 	
	
			document.getElementById("paraFrm").action="LoanProcessing_generateInstallmentSch.action"
			
			document.getElementById("paraFrm").submit();
		} catch(e)
		{
			////alert(e);
		}
	return true;
	}
	
	function rejectFun() {
	var vv=confirm("Do you really want to reject this application?");
	if(vv){
	var comment = trimData(document.getElementById('comment').value);
			
			if(document.getElementById('comment').value==""){
				alert("Please enter "+document.getElementById('appr.cmt').innerHTML.toLowerCase());
					document.getElementById("comment").focus();
				 	return false;
			}
	if(comment==''){
			 alert ("Please enter " +document.getElementById('appr.cmt').innerHTML.toLowerCase());
			return false;
		}
	
		document.getElementById('paraFrm_applicationStatus').value = 'R';	
			var levelVal = document.getElementById('paraFrm_level').value
			var loanCodeVal = document.getElementById('paraFrm_hiddenCode').value
			var loginVal = document.getElementById('paraFrm_hiddenLoginfrmId').value;
			
				var apprAmount = document.getElementById('paraFrm_sanctionAmount').value
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'LoanProcessing_sendBackApplication.action?status='+'R'+'&levelVal='+levelVal+'&loanCodeVal='+loanCodeVal+'&apprAmount='+apprAmount;
				document.getElementById('paraFrm').submit();
			
		}
	}
	function sendbackFun() {
	var vv=confirm("Do you really want to sendBack this application?");
	if(vv){
	try{
	var comment = trimData(document.getElementById('comment').value);
			
			if(document.getElementById('comment').value==""){
				alert("Please enter "+document.getElementById('appr.cmt').innerHTML.toLowerCase());
					document.getElementById("comment").focus();
				 	return false;
			}
	if(comment==''){
			 alert ("Please enter " +document.getElementById('appr.cmt').innerHTML.toLowerCase());
			return false;
		}
					
			document.getElementById('paraFrm_applicationStatus').value = 'B';	
			var levelVal = document.getElementById('paraFrm_level').value
			var loanCodeVal = document.getElementById('paraFrm_hiddenCode').value
			var loginVal = document.getElementById('paraFrm_hiddenLoginfrmId').value;
			
				var apprAmount = document.getElementById('paraFrm_sanctionAmount').value
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'LoanProcessing_sendBackApplication.action?status='+'B'+'&levelVal='+levelVal+'&loanCodeVal='+loanCodeVal+'&apprAmount='+apprAmount;
				document.getElementById('paraFrm').submit();
		
		} catch(e)
		{
			alert(e);
		}	
		}
	}
	
	function disableIntRate_old(){
			var intType =document.getElementById("paraFrm_interestType").value ;
		if(intType =='N'){
			document.getElementById("paraFrm_interestRate").readOnly = 'true';
			document.getElementById("paraFrm_interestRate").value ="";
			document.getElementById("intRateTD").style.display='none';
			document.getElementById("intRateTD1").style.display='none';
		}else {
			document.getElementById('paraFrm_interestRate').readOnly = '';
			document.getElementById("intRateTD").style.display='';
			document.getElementById("intRateTD1").style.display='';
		}
	
	 
}
function disableIntRate(){
////alert("CALTYPE:::"+document.getElementById('hiddenCalfrmId').value);
		try{
			var intType =document.getElementById("paraFrm_interestType").value ;
			var calType =document.getElementById('hiddenCalfrmId').value ;
			if(intType =='N'){
			
				if(calType=='E'){
					document.getElementById('hiddenCalfrmId').value="E";
					document.getElementById('EMITR').style.display='';
					document.getElementById('noOfInstallment').checked =false;
					document.getElementById('installmentTR').style.display='none';
					document.getElementById('prnAmt').checked =false;
					document.getElementById('principalTR').style.display='none';
					document.getElementById('prnAmt').disabled=true;
					document.getElementById("intRateTD").style.display='none';
					document.getElementById("intRateTD1").style.display='none';
				}
				if(calType=='T'){
					document.getElementById('hiddenCalfrmId').value="T";
					document.getElementById('noOfInstallment').checked =true;
					document.getElementById('EMITR').style.display='none';
					document.getElementById('prnAmt').checked =false;
					document.getElementById('principalTR').style.display='none';
					document.getElementById('prnAmt').disabled=true;
					document.getElementById("intRateTD").style.display='none';
					document.getElementById("intRateTD1").style.display='none';
					document.getElementById('emiAmt').checked =false;
						document.getElementById('emiAmt').disabled=false;
				}
				if(calType=='P'){
					document.getElementById('hiddenCalfrmId').value="T";
					document.getElementById('noOfInstallment').checked =true;
					document.getElementById('installmentTR').style.display='';
					document.getElementById('principalTR').style.display='none';
					document.getElementById('prnAmt').disabled=true;
					document.getElementById('prnAmt').checked =false;
					document.getElementById("intRateTD").style.display='none';
					document.getElementById("intRateTD1").style.display='none';
					document.getElementById('emiAmt').checked =false;
						document.getElementById('emiAmt').disabled=false;
				}
			
				document.getElementById("paraFrm_interestRate").value ="";
		
			}
			if(intType =='F'){
					//////document.getElementById('hiddenCalfrmId').value="F";
				if(calType=='E'){
					document.getElementById('hiddenCalfrmId').value="E";
					document.getElementById('EMITR').style.display='';
					document.getElementById('installmentTR').style.display='none';
					document.getElementById('prnAmt').checked =false;
					document.getElementById('principalTR').style.display='none';
					document.getElementById('prnAmt').disabled=true;
					document.getElementById("intRateTD").style.display='';
					document.getElementById("intRateTD1").style.display='';
				}else{
				document.getElementById('hiddenCalfrmId').value="T";
				document.getElementById('noOfInstallment').checked =true;
					document.getElementById('installmentTR').style.display='';
					document.getElementById('EMITR').style.display='none';
					document.getElementById('emiAmt').disabled=false;
					document.getElementById("paraFrm_interestRate").readOnly = '';
					document.getElementById("intRateTD").style.display='';
					document.getElementById("intRateTD1").style.display='';
					document.getElementById('prnAmt').checked =false;
					document.getElementById('principalTR').style.display='none';
					document.getElementById('prnAmt').disabled=true;
				}
					
				
			}
			if(intType =='R'){
			
				if(calType=='E'){
					document.getElementById('hiddenCalfrmId').value="E";
					document.getElementById('EMITR').style.display='';
					document.getElementById('installmentTR').style.display='none';
					document.getElementById('prnAmt').checked =false;
					document.getElementById('principalTR').style.display='none';
					document.getElementById('prnAmt').disabled=true;
					document.getElementById("intRateTD").style.display='';
					document.getElementById("intRateTD1").style.display='';
				}
				if(calType=='T'){
						document.getElementById('hiddenCalfrmId').value="T";
						document.getElementById('noOfInstallment').checked =true;
						document.getElementById('installmentTR').style.display='';
						document.getElementById('prnAmt').disabled=true;
						document.getElementById('principalTR').style.display='none';
						document.getElementById('EMITR').style.display='none';
						document.getElementById('emiAmt').checked =false;
						document.getElementById('emiAmt').disabled=false;
						document.getElementById("intRateTD").style.display='';
					document.getElementById("intRateTD1").style.display='';
				}
				if(calType=='P'){
				document.getElementById('hiddenCalfrmId').value="T";
						document.getElementById('noOfInstallment').checked =true;
						document.getElementById('installmentTR').style.display='';
						document.getElementById('prnAmt').checked =false;
						document.getElementById('prnAmt').disabled=true;
						document.getElementById('principalTR').style.display='none';
						document.getElementById('EMITR').style.display='none';
						document.getElementById('emiAmt').checked =false;
						document.getElementById('emiAmt').disabled=false;
				}
				
			
			}
			if(intType =='I'){
			
				if(calType=='P'){
					document.getElementById('hiddenCalfrmId').value="P";
						document.getElementById('noOfInstallment').checked =false;
						document.getElementById('installmentTR').style.display='none';
						document.getElementById('prnAmt').disabled=false;
						document.getElementById('EMITR').style.display='none';
						document.getElementById('emiAmt').checked =false;
						document.getElementById('emiAmt').disabled=true;
						document.getElementById("intRateTD").style.display='';
					document.getElementById("intRateTD1").style.display='';
					}
					
				if(calType=='T'){
					document.getElementById('hiddenCalfrmId').value="T";
						document.getElementById('noOfInstallment').checked =true;
						document.getElementById('installmentTR').style.display='';
						document.getElementById('prnAmt').disabled=false;
						document.getElementById('principalTR').style.display='none';
						document.getElementById('EMITR').style.display='none';
						document.getElementById('emiAmt').checked =false;
						document.getElementById('emiAmt').disabled=true;
						document.getElementById("intRateTD").style.display='';
					document.getElementById("intRateTD1").style.display='';
					}
					 
					if(calType=='E'){
						document.getElementById('hiddenCalfrmId').value="T";
						document.getElementById('noOfInstallment').checked =true;
						document.getElementById('installmentTR').style.display='';
						document.getElementById('prnAmt').disabled=false;
						document.getElementById('principalTR').style.display='none';
						document.getElementById('EMITR').style.display='none';
						document.getElementById('emiAmt').checked =false;
						document.getElementById('emiAmt').disabled=true;
						document.getElementById("intRateTD").style.display='';
					document.getElementById("intRateTD1").style.display='';
					}
			
				////document.getElementById('hiddenCalfrmId').value="I";
				
			}
	
	
	 }catch(e){
	   ////alert(e);
	 }
}


function checkNumbersWithDot(obj) {
	var count = 0;
	var txtNo = obj.value;
	
	for(var i = 0; i < txtNo.length; i++) {
		if(txtNo.charAt(i) == '.') {
			count = count + 1;
		}
	}
	
	if(count > 0) {
		if(!numbersOnly()) {
			return false;
		}
	} else if(!numbersWithDot()) {
		return false;
	}
	return true;
}

function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
	return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
	}
	
	function validateAmountLimit(){
	try{
	
	var amtValue = document.getElementById('paraFrm_loanAllowedLimit').value;
	if(amtValue!='0'){
	if (parseFloat((document.getElementById('paraFrm_sanctionAmount').value)) > (parseFloat(document.getElementById('paraFrm_loanAllowedLimit').value))) {
    			 alert("Amount must be less than "+document.getElementById('paraFrm_loanAllowedLimit').value);
    			
    			document.getElementById('paraFrm_sanctionAmount').focus();
    			 document.getElementById('paraFrm_sanctionAmount').value='';
		 		return false;
			}
			
			}
			
			var apprLoanAmountValue = document.getElementById('paraFrm_sanctionAmount').value;
			if(apprLoanAmountValue!=""){
			if(Math.abs(apprLoanAmountValue)==0){
 		 	alert("Zero not allowed in "+ document.getElementById('sancAmnt').innerHTML.toLowerCase());
 		 	
 		 	document.getElementById('paraFrm_sanctionAmount').value="";
 		 	document.getElementById('paraFrm_sanctionAmount').focus();
 		 	return false;
 		 	}
 		 	}
 		 	if (parseFloat((document.getElementById('paraFrm_sanctionAmount').value)) > (parseFloat(document.getElementById('paraFrm_loanAmount').value))) {
    			 alert("Amount must be less than "+document.getElementById('paraFrm_loanAmount').value);
    			
    			document.getElementById('paraFrm_sanctionAmount').focus();
    			 document.getElementById('paraFrm_sanctionAmount').value='';
		 		return false;
			}
			
	}catch(e){
		//alert(e);
	}
	}
	function validateEMIAmountLimit(){
	try{
	//alert(document.getElementById('paraFrm_emiAmount').value);
	//alert(document.getElementById('paraFrm_loanAllowedLimit').value);
	if (parseFloat((document.getElementById('paraFrm_emiAmount').value)) > (parseFloat(document.getElementById('paraFrm_sanctionAmount').value))) {
    			 alert("EMI Amount must be less than "+document.getElementById('paraFrm_sanctionAmount').value);
    			
    			document.getElementById('paraFrm_emiAmount').focus();
    			 document.getElementById('paraFrm_emiAmount').value='';
		 		return false;
			}
				
	}catch(e){
		//alert(e);
	}
	}
	
	function validatePRNAmountLimit(){
	try{
	if (parseFloat((document.getElementById('paraFrm_monthlyPrincAmount').value)) > (parseFloat(document.getElementById('paraFrm_sanctionAmount').value))) {
    			 alert("Principal Amount must be less than "+document.getElementById('paraFrm_sanctionAmount').value);
    			
    			document.getElementById('paraFrm_monthlyPrincAmount').focus();
    			 document.getElementById('paraFrm_monthlyPrincAmount').value='';
		 		return false;
			}
	}catch(e){
		//alert(e);
	}
	}
	
</script>