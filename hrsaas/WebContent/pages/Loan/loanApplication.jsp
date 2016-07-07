<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="LoanApplication" id="paraFrm" theme="simple"
	target="main" validate="true">
	<s:hidden name="hiddenCode" />
	<s:hidden name="backButtonFlag" />
	<s:hidden name="installmentPaidFlag" />
	<s:hidden name="prePaymentFlag" />
	<s:hidden name="view" />
	<s:hidden name="loanRefundableFlag" />
	<s:hidden name="level" />
	<s:hidden name="hiddenLoginfrmId" />
	<s:hidden name="installmentFlag" />

	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Loan
					Application </strong></td>
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
				cellspacing="1">
				<!--
	
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="0">
					<s:if test="isApprove"></s:if>
					<s:else>
						<tr>
							<td>
								<s:if test="%{insertFlag}">
									<s:submit cssClass="add" action="LoanApplication_saveLoanApplication" theme="simple"  
										value="Add New" onclick="return validateSave('save');"/>
								</s:if>
								<s:if test="%{updateFlag}">
									<s:submit cssClass="edit" action="LoanApplication_saveLoanApplication" theme="simple"  
										value="Update" onclick="return validateSave('update');"/>
								</s:if>
								<s:if test="%{viewFlag}">
									<input type="button" class="search" onclick="javascript:callsF9(500,325,'LoanApplication_f9Action.action'); 
										"theme="simple" value="Search"/>
								</s:if>
								<s:submit cssClass="reset" action="LoanApplication_reset" theme="simple"   value="    Reset" />
								<s:if test="%{deleteFlag}">
									<s:submit cssClass="delete" action="LoanApplication_deleteApplication" theme="simple" 
										value="Delete" onclick="return validateDelete();"/>
								</s:if>
								<s:if test="%{viewFlag}">
									<input type="button" class="token"  value="Report" 
										onclick="callReportforSelected('LoanApplication_report.action','paraFrm_loanApplCode');" />
								</s:if>
							</td>
							<td width="22%">
								<div align="right"><font color="red">*</font> Indicates Required</div>
							</td>
						</tr>
					</s:else>
				</table>
			<label></label></td>
		</tr>
		-->
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
				<!--<tr>
					<td>
					<table width="100%" class="formbg">
						<td colspan="2" id="ctrlShow"><b>Loan Application ID:</b><font
							color="red"></font><s:property value="trackingNo" /> <s:hidden
							name="trackingNo" /></td>

					</table>
					</td>
				</tr>



				-->
				<s:hidden
							name="trackingNo" />
				
				<!-- Approver Comments Section Begins -->
				<tr>
					<td>
					<table width="100%" class="formbg">
						<s:if test="approverCommentsFlag">
							<tr>
								<td colspan="2" id="ctrlShow"><b>Approver Comments</b><font
									color="red">*</font></td>
								<td colspan="3" id="ctrlShow"><s:textarea theme="simple"
									cols="70" rows="3" name="comment" id="comment"
									onkeypress="return imposeMaxLength(event, this, 500);" /></td>



							</tr>
							<s:if test="hrFlag">
								<tr>
									<td colspan="2" id="ctrlShow"><b>Approver Amounts</b><font
										color="red">*</font></td>
									<td id="ctrlShow"><s:textfield size="25" theme="simple"
										name="apprLoanAmount" /></td>

								</tr>
							</s:if>
						</s:if>
						<s:if test="approverCommentFlag">



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
									<td class="sortableTD"><s:property value="apprName" />&nbsp;</td>
									<td class="sortableTD"><s:property value="apprComments" />&nbsp;
									</td>
									<td class="sortableTD"><s:property value="apprDate" />&nbsp;</td>
									<td class="sortableTD"><s:property value="apprAmount" />&nbsp;</td>
									<td class="sortableTD"><s:property value="apprStatus" />&nbsp;</td>
								</tr>
							</s:iterator>
						</s:if>

					</table>
					</td>
				</tr>
				<!-- Approver Comments Section Ends -->
<!-- Installation Schedule start -->

<s:if test="installmentFlag">
					<tr>
						<td colspan="4">
						<table width="100%" border="0" cellpadding="2" cellspacing="0"
							class="formbg">
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
										class="sortable">
									
										<%
										int i = 0;
										%>

<%
												int j = 0;
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
										<s:iterator value="installmentList">

											
											<tr>

												<td class="border2" width="5%"><%=++i%></td>
												<td class="border2" width="20%" align="left"><s:property
													value="monthYear" /><s:hidden name="monthYear" /></td>
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
												</s:else> <input type="hidden" name="checkFlag" id="<%=i%>"
													value="<%=audFlag.equals("N")?"N":audFlag%>" /> <s:hidden
													name="balancePrincipalAmt" /></td>
											</tr>


										

										</s:iterator>
										
	<%
											i++;
											%>
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
<!-- Installation Schedule end -->


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
										class="forminnerhead"><label class="set" id="emp.details"
										name="emp.details" ondblclick="callShowDiv(this);"><%=label.get("emp.details")%></label>:</strong></td>
								</tr>

								<tr>
									<s:hidden name="approverCode" />
									<s:hidden name="year" />
									<s:hidden name="loanApplCode"></s:hidden>
									<s:hidden name='pfLoanCode' />
									<td colspan="1" width="25%"><label class="set"
										id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font
										color="red">*</font></td>
									<td colspan="3" width="25%"><s:textfield size="10"
										theme="simple" name="empToken" readonly="true" /> <s:textfield
										size="50" theme="simple" name="empName" readonly="true" /> <s:hidden
										name="empCode" /> <s:if test="generalFlag"></s:if> <s:elseif
										test="isApprove"></s:elseif> <s:else>
										<s:if test="%{hiddenCode == ''}">
											<img src="../pages/images/recruitment/search2.gif"
												height="16" align="absmiddle" width="18" theme="simple"
												id="ctrlHide" onclick="callEmployee();">
										</s:if>
									</s:else></td>
									<td colspan="1" width="25%"></td>
									<td colspan="1" width="25%"></td>
								</tr>

								<tr>
									<td colspan="1" width="25%"><label class="set" id="branch"
										name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
									<td colspan="1" width="25%"><s:hidden name="branchCode" />
									<s:textfield size="25" theme="simple" name="branchName"
										readonly="true" /></td>
									<td colspan="1" width="25%"><label class="set"
										id="department" name="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
									<td colspan="1" width="25%"><s:hidden name="deptCode" />
									<s:textfield size="25" theme="simple" name="deptName"
										readonly="true" /></td>
								</tr>

								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="designation" name="designation"
										ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
									<td colspan="1" width="25%"><s:hidden name="desgCode" />
									<s:textfield size="25" theme="simple" name="desgName"
										readonly="true" /></td>
									<td colspan="1" width="25%"><label class="set"
										id="joinDate" name="joinDate" ondblclick="callShowDiv(this);"><%=label.get("joinDate")%></label>:</td>
									<td colspan="1" width="25%"><s:textfield size="25"
										theme="simple" name="confirmationDate" readonly="true" /></td>
								</tr>

								<tr>
									<td colspan="1" width="25%"><label class="set" id="grade"
										name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
									<td colspan="1" width="25%"><s:hidden name="gradeCode" />
									<s:textfield size="25" theme="simple" name="grade"
										readonly="true" /></td>
									<!--<td colspan="1" width="23%" ><label  class = "set"  id="gross.sal" name="gross.sal" ondblclick="callShowDiv(this);"><%=label.get("gross.sal")%></label> :</td>
									<td colspan="1" width="23%">
										<s:textfield size="25" theme="simple" name="grossSalary" readonly="true"/></td>
								-->
									<td colspan="1" width="25%"><label class="set"
										id="dateApp" name="dateApp" ondblclick="callShowDiv(this);"><%=label.get("dateApp")%></label>:</td>
									<td colspan="1" width="25%"><s:textfield size="25"
										theme="simple" name="applicationdate" readonly="true"
										cssStyle="background-color: #F2F2F2;" /></td>

								</tr>

								<tr>


									<td colspan="1" width="25%"><label class="set"
										id="appStatus" name="appStatus"
										ondblclick="callShowDiv(this);"><%=label.get("appStatus")%></label>:</td>
									<td colspan="1" width="25%"><s:select
										name="applicationStatus" disabled="true" cssStyle="width:165"
										list="#{'D':'Draft', 'P':'Pending', 'A':'Approved', 'R':'Rejected', 'F':'Forwarded', 'B':'Send Back'}" /></td>

									<td><s:hidden  name="divCode" /><s:hidden name="divName" /></td>
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
							Approver(s) for this application:</strong></td>
							<td colspan="2" nowrap="nowrap"></td>

						</tr>
						<!-- APPROVER LIST  TABLE  STARTS -->
						<tr valign="top">
							<td colspan="3" rowspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id="approverListValue">
								<tr>
									<%
										int y = 1;
									%>
									<%!int z = 0;%>
									<s:iterator value="approverList">
										<tr>
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
					</table>
					</td>
				</tr>

				<!-- APPROVER LIST  TABLE  ENDS -->


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
									<td colspan="1" width="25%">
										<s:select name="loanCode" headerKey=""
											onchange="calAmtlimitValue();return callLoanType();"
											headerValue="--Select--" list="%{loanTypeHashmap}"
											cssStyle="width:160" />
											
									 <!--<s:textfield size="25" theme="simple" name="loanType" readonly="true"/> 
										<s:if test="isApprove"></s:if>
										<s:else>
											<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="18" theme="simple"
												onclick="javascript:callsF9(500,325,'LoanApplication_f9LoanTypeAction.action');">
										</s:else>--></td>
									<td colspan="1" width="25%"><label class="set" id="loanAmnt" name="loanAmnt"
										ondblclick="callShowDiv(this);"><%=label.get("loanAmnt")%></label>:</td>
									<td colspan="1" width="25%"><s:textfield size="25" theme="simple"
										name="loanAllowedLimit" onkeypress="" readonly="true"
										cssStyle="background-color: #F2F2F2;" /></td>

								</tr>
								<tr>
									<td colspan="1" width="22%"><label class="set"
										id="intType" name="intType" ondblclick="callShowDiv(this);"><%=label.get("intType")%></label>:</td>
									<td colspan="1" width="25%"><s:textfield maxlength="4"
										size="30" theme="simple" name="interestType" readonly="true"
										cssStyle="background-color: #F2F2F2;" /></td>
									<td colspan="1"  width="25%"><label class="set" id="intRate"
										name="intRate" ondblclick="callShowDiv(this);"><%=label.get("intRate")%></label>:<font color="red"></font></td>
									<td colspan="1"  width="25%"><s:textfield maxlength="4" size="25"
										theme="simple" name="interestRate" readonly="true"
										cssStyle="background-color: #F2F2F2;" /></td>

								</tr>


								<tr>
									<td colspan="1" width="25%" id='pfBalanceTD1'><label
										class="set" id="pfBalanceLable" name="pfBalanceLable"
										ondblclick="callShowDiv(this);"><%=label.get("pfBalanceLable")%></label>:</td>
									<td colspan="1" width="25%" id='pfBalanceTD2'><s:textfield
										size="25" theme="simple" name="pfBalance" readonly="true" /></td>
								</tr>
								<tr>
									<td colspan="1" width="25%"><label class="set"
										id="amtLoan" name="amtLoan" ondblclick="callShowDiv(this);"><%=label.get("amtLoan")%></label>:<font
										color="red">*</font></td>
									<td colspan="3" width="25%">
										<s:textfield size="30" theme="simple" name="loanAmount"
											onkeypress="return checkNumbersWithDot(this);" maxlength="8"
											onblur="validateAmountLimit();" />
									
									</td>
									<td colspan="1" width="25%" id="subTypeTD1"><label
										class="set" id="loanSubType" name="loanSubType"
										ondblclick="callShowDiv(this);"><%=label.get("loanSubType")%></label>:<font
										color="red">*</font></td>
									<td colspan="3" width="25%" id="subTypeTD2"><s:if
										test="isApprove">
										<s:radio list="#{'Y':'Yes','N':'No'}" disabled="true"
											theme="simple" name="loanSubType" />
									</s:if><s:else>
										<s:radio list="#{'Y':'Yes','N':'No'}" theme="simple"
											name="loanSubType" />
									</s:else><s:hidden name='hiddenLoanSubType'></s:hidden></td>
									</td>
								</tr>

								<tr>
									<td width="25%"><label class="set" id="calculate.emi"
										name="calculate.emi" ondblclick="callShowDiv(this);"><%=label.get("calculate.emi")%></label>:<font
										color="red">*</font></td>
									<td width="25%" colspan="1"><label class="set" id="expected.emi"
										name="expected.emi" ondblclick="callShowDiv(this);"><%=label.get("expected.emi")%></label>:<s:checkbox name="expectedEmi"
										onclick="showCalculatedBox();return callOptions('expectedEmi');"></s:checkbox> <label
										class="set" id="tenure" name="tenure"
										ondblclick="callShowDiv(this);"><%=label.get("tenure")%></label>:<s:checkbox name="tenure"
										onclick="showCalculatedBox();return callOptions('tenure');"></s:checkbox>
									</td>
									<td  width="25%" colspan="2" id="calculatedBox"> 
										<s:textfield size="20" theme="simple" name="expEmpValue"
											onkeypress="return checkNumbersWithDot(this);" maxlength="8"
											onblur="validateAmountLimit();" />
										<input type="button" value="EMI Calculator " class="token" onclick="callCalculateEMI();"/>
									</td>
									<s:hidden name="hiddenChechfrmId" id="hiddenChechfrmId" />
								</tr>

								<tr id='purposeTR'>

									<td colspan="1" width="25%"><label class="set"
										id="purpose" name="purpose" ondblclick="callShowDiv(this);"><%=label.get("purpose")%></label>:</td>
									<td colspan="1" width="25%"><s:textfield
										name='loanPurposeValue' size="25" readonly="true"></s:textfield><s:hidden
										name='loanPurpose'></s:hidden> <s:if test="isApprove"></s:if><s:else>
										<img src="../pages/images/recruitment/search2.gif" height="16"
											align="absmiddle" width="18" theme="simple"
											onclick="javascript:callsF9(500,325,'LoanApplication_f9Purpose.action');">
									</s:else></td>
									<td colspan="1" width="25%"><label class="set"
										id="eligibility" name="eligibility"
										ondblclick="callShowDiv(this);"><%=label.get("eligibility")%></label>:</td>
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
									<td width="25%" colspan="3"><s:textarea
										id="paraFrm_applicantComment" rows="2" cols="40"
										name="applicantComment" /></td>
								</tr>


							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<!--<tr>
			<td colspan="4">
				<table width="100%" border="0" cellpadding="2" cellspacing="1"
					class="formbg">
					<tr>
						<td width="100%" colspan="3">
							<table width="98%" border="0" align="center" cellpadding="2"
								cellspacing="1" >
								<tr>
									<td colspan="4" class="formhead" width="100%"><strong
										class="forminnerhead">Guarantor Details</strong></td>
								</tr>
								<tr>
									<td colspan="1" width="27%" ><label  class = "set"  id="recBy" name="recBy" ondblclick="callShowDiv(this);"><%=label.get("recBy")%></label> :</td>
									<td colspan="3" width="73%" nowrap="nowrap"><s:hidden name="recommendedByCode"/>
										<s:textfield size="10" theme="simple" name="recommendedByToken" readonly="true"/>
										<s:textfield size="50" theme="simple" name="recommendedByName" readonly="true"/>
										<s:if test="isApprove"></s:if>
										<s:else>
											<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="18" theme="simple"
												onclick="javascript:callsF9(500,325,'LoanApplication_f9RecommendedByAction.action');">
										</s:else></td>
										
								</tr>
								
								<tr>
									<td colspan="1" width="27%" ><label  class = "set"  id="guaran" name="guaran" ondblclick="callShowDiv(this);"><%=label.get("guaran")%></label> :</td>
									<td colspan="3" width="73%" nowrap="nowrap"><s:hidden name="firstGuarantorCode"/>
										<s:textfield size="10" theme="simple" name="firstGuarantorToken" readonly="true"/>
										<s:textfield size="50" theme="simple" name="firstGuarantorName" readonly="true"/>
										<s:if test="isApprove"></s:if>
										<s:else>
											<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="18" theme="simple"
												onclick="javascript:callsF9(500,325,'LoanApplication_f9FirstGuarantorAction.action');">&nbsp;
										</s:else>
									</td>
								</tr>
								
								<tr>
									<td colspan="1" width="27%" ><label  class = "set"  id="guaran1" name="guaran1" ondblclick="callShowDiv(this);"><%=label.get("guaran1")%></label> :</td>
									<td colspan="3" width="73%" nowrap="nowrap"><s:hidden name="secondGuarantorCode"/>
										<s:textfield size="10" theme="simple" name="secondGuarantorToken" readonly="true"/>
										<s:textfield size="50" theme="simple" name="secondGuarantorName" readonly="true"/>
										<s:if test="isApprove"></s:if>
										<s:else>
											<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="18" theme="simple"
												onclick="javascript:callsF9(500,325,'LoanApplication_f9SecondGuarantorAction.action');">&nbsp;
										</s:else>
									</td>
								</tr>
							</table>
							</td>
					</tr>
				</table>
			</td>
		</tr>
		-->
				
				
				<tr>
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">

						<tr>
							<td width="20%"><b>Completed By:</b></td>
							<td width="20%"><s:hidden name="initiatorCode" /> <s:property
								value="initiatorName" /></td>
							<td width="20%"><b>Completed On:</b></td>
							<td width="20%"><s:hidden name="initiatorDate"></s:hidden> <s:property
								value="initiatorDate" /></td>
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
			</table>
			</td>
		</tr>
	</table>

 <s:hidden name="source" id="source" />

</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>

	function validateSave(buttonType){
		document.getElementById('paraFrm_hiddenApplicationStatus').value = document.getElementById('paraFrm_applicationStatus').value;
		var pfLoanCode=document.getElementById('paraFrm_pfLoanCode').value;
		if(buttonType == 'update'){
			if(document.getElementById('paraFrm_loanApplCode').value==""){
				alert("Please select a record to update ");
	  			return false;
			}
		}
		else {
			if(!document.getElementById('paraFrm_loanApplCode').value == ""){
				alert("Please click on update button to update the record ");
	  			return false;
			}
		}
		
		if(document.getElementById('paraFrm_applicationStatus').value != 'P'){
			alert('Only pending applications can be updated !');
			return false;
		}
		var fieldNames = ["paraFrm_empName", "paraFrm_loanCode", "paraFrm_loanAmount"];
		var labelNames = ["employee", "typeLoan", "amtLoan"];
		
		var flag       = ["select", "select", "enter"];
		
		if(!validateBlank(fieldNames, labelNames, flag))return false;
		if(document.getElementById('paraFrm_loanCode').value==pfLoanCode && pfLoanCode!=""){
		if(!(document.getElementById('paraFrm_loanSubTypeY').checked ||document.getElementById('paraFrm_loanSubTypeN').checked)){
				alert("Please select "+document.getElementById('loanSubType').innerHTML);
				return false;
		}
		
		var minLimitAmt= eval((document.getElementById('paraFrm_minSanctionAmt').value)*10000000000)/10000000000;
		var maxLimitAmt= eval((document.getElementById('paraFrm_maxSanctionAmt').value)*10000000000)/10000000000;
		var loanAmt= eval((document.getElementById('paraFrm_loanAmount').value)*10000000000)/10000000000;
		if(eval(loanAmt)< eval(minLimitAmt)){
			var conf=confirm(document.getElementById('amtLoan').innerHTML+" is less than "+document.getElementById('minSanctLimit').innerHTML+",\n Are you sure to continue ?");
			if(!conf){
				document.getElementById('paraFrm_loanAmount').focus();
				return false;
			}
			
		}else 
		if(eval(loanAmt)> eval(maxLimitAmt)){
			var conf=confirm(document.getElementById('amtLoan').innerHTML+" is more than "+document.getElementById('maxSanctLimit').innerHTML+",\n Are you sure to continue ?");
			if(!conf){
				document.getElementById('paraFrm_loanAmount').focus();
				return false;
			}
			
		}
		}
	}
	
	function validateDelete(){
		if(document.getElementById('paraFrm_applicationStatus').value != 'P'){
			alert('Only pending applications can be deleted !');
			return false;
		}
		if(!callDelete('paraFrm_loanApplCode'))return false;
	}
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
		callLoanType();
		var hiddenCheckfrmId = document.getElementById('hiddenChechfrmId').value;
		if(hiddenCheckfrmId==''){
			document.getElementById('calculatedBox').style.display='none';
		}
		
		var loanSubType = document.getElementById('paraFrm_hiddenLoanSubType').value ;
		document.getElementById('paraFrm_loanSubType'+loanSubType).checked =true;
		
		
		
		
	}
	function callEmployee(){
		//if(document.getElementById('paraFrm_applicationStatus').value != 'P'){
		//	alert('Only pending applications can be updated !');
		//	return false;
		//}
		javascript:callsF9(500,325,'LoanApplication_f9EmployeeNameAction.action');
	}
	/*function setYear(){
		var year = new Date().getFullYear();
		
		document.getElementById('paraFrm_year').value = year;
	}
setYear();*/

	function backtolistFun()
		{
			try{
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
				document.getElementById('paraFrm').submit();
			}
			catch(e)
			{
				alert("Error-------"+e);
			}
	}

function backtolistFun_OLD() 
{

	var ssss=document.getElementById('paraFrm_backButtonFlag').value;
	
	if(ssss=="fromApprover"){
	document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'LoanApproval_input.action';
		document.getElementById('paraFrm').submit();
	
	} else if(ssss=="fromHr"){
	document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'LoanProcessing_input.action';
		document.getElementById('paraFrm').submit();
	
	} 
	
	else{
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'LoanApplication_back.action';
		document.getElementById('paraFrm').submit();
	}

}

	function callOptions(type){
		if(type!="expectedEmi"){
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_expectedEmi').checked =false;
				document.getElementById('hiddenChechfrmId').value = "T";
				document.getElementById('paraFrm_expEmpValue').value = "";
			}else{
			document.getElementById('hiddenChechfrmId').value = "";
			document.getElementById('paraFrm_expEmpValue').value = "";
			
			
			}
		}else {
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_tenure').checked =false;
				document.getElementById('hiddenChechfrmId').value = "E";
				document.getElementById('paraFrm_expEmpValue').value = "";
			}else{
			document.getElementById('hiddenChechfrmId').value = "";
			document.getElementById('paraFrm_expEmpValue').value = "";
			}
		}
}

function draftFun()
{	
	try
	{
		
		var empNameVar = trim(document.getElementById('paraFrm_empName').value);
			if(empNameVar == "")
			{
				alert("Please Select Employee.");
		  		document.getElementById('paraFrm_empName').focus();
		 		return false;
		 	}	
		
		var loanCodeVar = document.getElementById('paraFrm_loanCode').value;
					   		
		if(loanCodeVar=="")
		{
			alert("Please select "+document.getElementById('typeLoan').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_loanCode').focus();
		 	return false;		
		}
		
		
		var loanAmountVar = document.getElementById('paraFrm_loanAmount').value;
		
		if(loanAmountVar=="")
		{
			alert("Please enter "+document.getElementById('amtLoan').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_loanAmount').focus();
		 	return false;		
		}
		
		if(loanAmountVar!=""){
  			if(Math.abs(loanAmountVar)==0){
 		 	alert("Zero not allowed in "+ document.getElementById('amtLoan').innerHTML.toLowerCase());
 		 	
 		 	document.getElementById('paraFrm_loanAmount').value="";
 		 	document.getElementById('paraFrm_loanAmount').focus();
 		 	return false;
 		 	}
 		 	}
		
		
		var calEMIVar = document.getElementById('hiddenChechfrmId').value;
		if(calEMIVar=="")
		{
			alert("Please select Calculate EMI type.");
		  		return false;		
		}
			
	var table = document.getElementById('approverListValue');
	var rowCount = table.rows.length; 
		//alert("rowCount=="+rowCount);
	  if(eval(rowCount==1))
	 {
 			alert("Reporting structure is not defined for the employee\n"+empNameVar+"\n Please contact your HR Manager.");
 			return false;
	 }

			
	}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
		
					
		document.getElementById('paraFrm_applicationStatus').value = 'D';	
		//alert(document.getElementById('paraFrm_applicationStatus').value);
			document.getElementById('paraFrm').target = "_self";
  			document.getElementById('paraFrm').action = 'LoanApplication_draftFunction.action?status=D';
			document.getElementById('paraFrm').submit();
	}
	function calAmtlimitValue(){
	try{
	var loanCodeVar = document.getElementById('paraFrm_loanCode').value;
	//alert(loanCodeVar);
	document.getElementById('paraFrm').action = 'LoanApplication_getAmountLimit.action';
			document.getElementById('paraFrm').submit();
		}
		catch(e) {
			alert(e);
		}	
	}
	
	function validateAmountLimit(){
	try{
	var amtValue = document.getElementById('paraFrm_loanAllowedLimit').value;
	if(amtValue!='0'){
	
	if (parseFloat((document.getElementById('paraFrm_loanAmount').value)) > (parseFloat(document.getElementById('paraFrm_loanAllowedLimit').value))) {
    			 alert("Amount must be less than "+document.getElementById('paraFrm_loanAllowedLimit').value);
    			
    			document.getElementById('paraFrm_loanAmount').focus();
    			 document.getElementById('paraFrm_loanAmount').value='';
		 		return false;
			}
			}
	}catch(e){
		//alert(e);
	}
	}
	
	function sendforapprovalFun() {
		try{
		
		var con = confirm('Do you really want to send this application for approval?');
	 	
	 	if(con) {
	 		
	 		var empNameVar = trim(document.getElementById('paraFrm_empName').value);
			if(empNameVar == "")
			{
				alert("Please Select Employee.");
		  		document.getElementById('paraFrm_empName').focus();
		 		return false;
		 	}	
		
		var loanCodeVar = document.getElementById('paraFrm_loanCode').value;
		
		if(loanCodeVar=="")
		{
			alert("Please select "+document.getElementById('typeLoan').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_loanCode').focus();
		 	return false;		
		}
		
		
		var loanAmountVar = document.getElementById('paraFrm_loanAmount').value;
		
		if(loanAmountVar=="")
		{
			alert("Please enter "+document.getElementById('amtLoan').innerHTML.toLowerCase());
		  	document.getElementById('paraFrm_loanAmount').focus();
		 	return false;		
		}
		
		if(loanAmountVar!=""){
  			if(Math.abs(loanAmountVar)==0){
 		 	alert("Zero not allowed in "+ document.getElementById('amtLoan').innerHTML.toLowerCase());
 		 	
 		 	document.getElementById('paraFrm_loanAmount').value="";
 		 	document.getElementById('paraFrm_loanAmount').focus();
 		 	return false;
 		 	}
 		 	}
		
			
			var calEMIVar = document.getElementById('hiddenChechfrmId').value;
		if(calEMIVar=="")
		{
			alert("Please select Calculate EMI type.");
		  		return false;		
		}
		
	var table = document.getElementById('approverListValue');
	var rowCount = table.rows.length; 
		//alert("rowCount=="+rowCount);
	  if(eval(rowCount==1))
	 {
 			alert("Reporting structure is not defined for the employee\n"+empNameVar+"\n Please contact your HR Manager.");
 			return false;
	 }
	 	
	 		document.getElementById('paraFrm_applicationStatus').value = 'P';	
			document.getElementById('paraFrm').target = "_self";
    		document.getElementById('paraFrm').action='LoanApplication_sendForApprovalFunction.action?status=P';
			document.getElementById('paraFrm').submit();
		}
		} catch(e){
		
			alert(e);
		}
	}
	
	
	
	function deleteFun()
	{
	 	var conf=confirm("Do you really want to delete this record ?");
			 	if(conf)
				{
					document.getElementById('paraFrm').target = "_self";
	      			document.getElementById('paraFrm').action = 'LoanApplication_deleteApplication.action';
					document.getElementById('paraFrm').submit();
	
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
		function resetFun() {
	
  	 	document.getElementById('paraFrm').action = "LoanApplication_reset.action";
		document.getElementById('paraFrm').submit();
  	}
  	
  	function reportFun() {
		///document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'LoanApplication_report.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callCalculateEMI(){
	var loanAmt = document.getElementById('paraFrm_loanAmount').value;
	var hiddenCheckfrmId = document.getElementById('hiddenChechfrmId').value;
	var expEmpValue =  document.getElementById('paraFrm_expEmpValue').value;
	
	///document.getElementById('hiddenCreditCodefrmId').value = hiddenCheckfrmId; 
	
		win=window.open('','win','top=260,left=150,width=700,height=400,scrollbars=yes,status=no,resizable=no');
		document.getElementById("paraFrm").target="win";
		////document.getElementById('paraFrm').action="LoanApplication_openCalEMI.action?loanAmt="+loanAmt+"&loanAppl="+true+"+&hiddenCheckfrmId="+hiddenCheckfrmId+"&expEmpValue="+expEmpValue;
		document.getElementById('paraFrm').action="LoanApplication_openCalEMI.action?loanAmt="+loanAmt+"&loanAppl=true";
		
		document.getElementById('paraFrm').submit();
		document.getElementById("paraFrm").target="main";
	}
	function showCalculatedBox(){
	if(document.getElementById('paraFrm_expectedEmi').checked ==true || document.getElementById('paraFrm_tenure').checked ==true){
		document.getElementById('calculatedBox').style.display='';
	} else {
		document.getElementById('calculatedBox').style.display='none';
	}
	
	
		
	}
</script>

