<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="ReturnAct" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table width="99%" cellspacing="2" class="formbg" align="center">
		<s:hidden name="gratuityBean.gratuityID" value="1" />
		<s:hidden name="gratuityBean.gratuityReturnID" value="1" />
		<s:hidden name="pageToShow" value="maternitybenefits" />
		<s:hidden name="previousPage" value="rentallowance" />
		<s:hidden name="orgId" /><s:hidden name="frequency" />
		<s:hidden name="fromPeriod" /><s:hidden name="toPeriod" />
<s:hidden name="orgName" />
		<tr>
			<td valign="bottom" class="txt" colspan="3">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Payment
					of Gratuity Act </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td class="formtext" colspan="3">
			<table width="100%">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="30%">
					<div align="left"> <s:select
						name="actList" list="actMap" size="1" headerKey=""
						headerValue="--Select Act--" cssStyle="width:150" theme="simple" />
						<input type="submit" value="Go To"
						class="token" onclick="return goTo();">
						</div>
					</td>

				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td class="formtext" colspan="3">
			<table width="100%" style="border: 1px solid #565656;">
				<!-- Heading Section (Start) -->
				<!-- English heading section (Begin) -->
				<tr>
					<td align="center"><label class="set"
						name="gratuity.english_Heading1" id="gratuity.english_Heading1"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.english_Heading1")%></label></td>
				</tr>
				<tr>
					<td align="center"><label class="set"
						name="gratuity.english_HeadingLine2"
						id="gratuity.english_HeadingLine2" ondblclick="callShowDiv(this);"><%=label.get("gratuity.english_HeadingLine2")%></label></td>
					</td>
				</tr>

				<tr>
					<td colspan="2" align="center"><label class="set"
						name="gratuity.english_fromDate" id="gratuity.english_fromDate"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.english_fromDate")%>
					</label><s:property value="fromPeriod" /> <!--<s:textfield maxLength="10" cssStyle="width:130"
							size="10" theme="simple" name="fromDate" value="%{fromDate}" />
						<a	href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');"> <img
						src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"> </a>
						&nbsp; 
						--> &nbsp; <label class="set" name="gratuity.english_toDate"
						id="gratuity.english_toDate" ondblclick="callShowDiv(this);"><%=label.get("gratuity.english_toDate")%>
					</label> &nbsp;<s:property value="toPeriod" /> <!--
						 	&nbsp;
							 <s:textfield maxLength="10" cssStyle="width:130"
							size="10" theme="simple" name="toDate" value="%{toDate}" /><a	href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');"> <img
							src="../pages/images/Date.gif" class="iconImage" height="16"
							align="absmiddle" width="16"> </a>
						--></td>
				</tr>
				<td>&nbsp;<br>
				</td>
				<!-- English heading section (End) -->

				<!-- Marathi heading section (Begin) -->
				<tr>
					<td align="center"><label class="set"
						name="gratuity.marathi_Heading1" id="gratuity.marathi_Heading1"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.marathi_Heading1")%></label>
					</td>
				</tr>
				<tr>
					<td align="center"><label class="set"
						name="gratuity.marathi_HeadingLine2"
						id="gratuity.marathi_HeadingLine2" ondblclick="callShowDiv(this);"><%=label.get("gratuity.marathi_HeadingLine2")%></label></b>
					</td>
				</tr>

				<tr>
					<td colspan="2" align="center"><!--
						<s:textfield maxLength="10" cssStyle="width:130"
						size="10" theme="simple" name="fromMarathiDate" value="%{fromMarathiDate}" />
						<a	href="javascript:NewCal('paraFrm_fromMarathiDate','DDMMYYYY');"> <img
						src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"> </a>&nbsp;
						--> &nbsp; <s:property value="fromPeriod" /> <label class="set"
						name="gratuity.marathi_fromDate" id="gratuity.marathi_fromDate"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.marathi_fromDate")%>
					</label> <!--
						&nbsp; <s:textfield maxLength="10" cssStyle="width:130"
						size="10" theme="simple" name="toMarathiDate" value="%{toMarathiDate}" />
						<a	href="javascript:NewCal('paraFrm_toMarathiDate','DDMMYYYY');"> <img
						src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"> </a>&nbsp;
						--> &nbsp; <s:property value="toPeriod" /> <label class="set"
						name="gratuity.marathi_toDate" id="gratuity.marathi_toDate"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.marathi_toDate")%>
					</label></td>
				</tr>
				<td>&nbsp;<br>
				</td>
				<!-- Marathi heading section (End) -->
			</table>
			<!-- Heading Section (End) --></td>
		</tr>

		<tr>
			<td class="formtext" colspan="3"><!-- Start of Nominations, applications Section -->
			<table width="100%" cellspacing="2"
				style="border: 1px solid #565656;" align="center">
				<tr>
					<td colspan="3"><label class="set"
						name="gratuity.nomination_head" id="gratuity.nomination_head"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.nomination_head")%>
					</label></td>
				</tr>
				<tr>
					<td width="3%">1</td>
					<td width="66%"><label class="set"
						name="gratuity.no_of_nominies" id="gratuity.no_of_nominies"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.no_of_nominies")%>
					</label></td>
					<td width="30%" align="left"><s:textfield
						name="gratuityBean.Nominations_Received" size="5" /></td>
				</tr>
				<tr>
					<td>2</td>
					<td><label class="set"
						name="gratuity.no_of_application_accepted"
						id="gratuity.no_of_application_accepted"
						ondblclick="callShowDiv(this);"><%=label
										.get("gratuity.no_of_application_accepted")%>
					</label></td>
					<td align="left"><s:textfield
						name="gratuityBean.Nominations_Accepted" size="5" /></td>
				</tr>
				<tr>
					<td>3</td>
					<td><label class="set"
						name="gratuity.no_of_applications_received"
						id="gratuity.no_of_applications_received"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.no_of_applications_received")%>
					</label></td>
					<td align="left"><s:textfield
						name="gratuityBean.Applications_Received" size="5" /></td>
				</tr>
				<tr>
					<td>4</td>
					<td><label class="set" name="gratuity.application_approved"
						id="gratuity.application_approved" ondblclick="callShowDiv(this);"><%=label.get("gratuity.application_approved")%>
					</label></td>
					<td align="left"><s:textfield
						name="gratuityBean.Applications_Approved" size="5" /></td>
				</tr>
				<tr>
					<td>5</td>
					<td><label class="set" name="gratuity.application_rejected"
						id="gratuity.application_rejected" ondblclick="callShowDiv(this);"><%=label.get("gratuity.application_rejected")%>
					</label></td>
					<td align="left"><s:textfield
						name="gratuityBean.Applications_Rejected" size="5" /></td>
				</tr>
				<tr>
					<td>6</td>
					<td colspan="2"><label class="set"
						name="gratuity.application_rejected_reasons"
						id="gratuity.application_rejected_reasons"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.application_rejected_reasons")%>
					</label></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set" name="gratuity.workers_disorder"
						id="gratuity.workers_disorder" ondblclick="callShowDiv(this);"><%=label.get("gratuity.workers_disorder")%>
					</label></td>
					<td align="left"><s:textfield
						name="gratuityBean.workerDisorderlyConduct" size="5" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set"
						name="gratuity.workers_not_in_continuous_service"
						id="gratuity.workers_not_in_continuous_service"
						ondblclick="callShowDiv(this);"><%=label
										.get("gratuity.workers_not_in_continuous_service")%>
					</label></td>
					<td align="left"><s:textfield
						name="gratuityBean.workersContinuedService" size="5" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set" name="gratuity.other_reasons"
						id="gratuity.other_reasons" ondblclick="callShowDiv(this);"><%=label.get("gratuity.other_reasons")%>
					</label></td>
					<td align="left"><s:textfield name="gratuityBean.otherReasons"
						size="5" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set" name="gratuity.other_reasons_specify"
						id="gratuity.other_reasons_specify"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.other_reasons_specify")%>
					</label></td>
					<td align="left"><s:textarea
						name="gratuityBean.otherReasonsSpecify" cols="38" rows="2" /></td>
				</tr>
			</table>

			<!-- End of Nominations, applications Section --></td>
		</tr>

		<tr>
			<td class="formtext" colspan="3"><!-- Start of Payment Section -->
			<table width="100%" cellspacing="2"
				style="border: 1px solid #565656;" align="center">
				<tr>
					<td colspan="3"><label class="set"
						name="gratuity.payment_section_head"
						id="gratuity.payment_section_head" ondblclick="callShowDiv(this);"><%=label.get("gratuity.payment_section_head")%>
					</label></td>
				</tr>
				<tr>
					<td width="3%">7</td>
					<td width="66%"><label class="set"
						name="gratuity.total_amount" id="gratuity.total_amount"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.total_amount")%>
					</label></td>
					<td width="30%" align="left"><s:textfield
						name="gratuityBean.AmountOfGratuityPaid" size="23" /></td>
				</tr>
				<tr>
					<td>8</td>
					<td><label class="set" name="gratuity.total_maximum_amount"
						id="gratuity.total_maximum_amount" ondblclick="callShowDiv(this);"><%=label.get("gratuity.total_maximum_amount")%>
					</label></td>
					<td align="left"><s:textfield
						name="gratuityBean.MaxGratuityAmount" size="23" /></td>
				</tr>
				<tr>
					<td>9</td>
					<td><label class="set" name="gratuity.total_minimum_amount"
						id="gratuity.total_minimum_amount" ondblclick="callShowDiv(this);"><%=label.get("gratuity.total_minimum_amount")%>
					</label></td>
					<td align="left"><s:textfield
						name="gratuityBean.MinGratuityAmount" size="23" /></td>
				</tr>
				<tr>
					<td>10</td>
					<td colspan="2"><label class="set"
						name="gratuity.payments_paid" id="gratuity.payments_paid"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.payments_paid")%>
					</label></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set" name="gratuity.cash" id="gratuity.cash"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.cash")%>
					</label></td>
					<td align="left"><s:textfield name="gratuityBean.GratuityPaid_Cash"
						size="23" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set" name="gratuity.bank_cheque"
						id="gratuity.bank_cheque" ondblclick="callShowDiv(this);"><%=label.get("gratuity.bank_cheque")%>
					</label></td>
					<td align="left"><s:textfield name="gratuityBean.GratuityPaid_BankCheque"
						size="23" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set" name="gratuity.normal_cheque"
						id="gratuity.normal_cheque" ondblclick="callShowDiv(this);"><%=label.get("gratuity.normal_cheque")%>
					</label></td>
					<td align="left"><s:textfield name="gratuityBean.GratuityPaid_NormalCheque"
						size="23" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set" name="gratuity.credit_to_bank_account"
						id="gratuity.credit_to_bank_account"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.credit_to_bank_account")%>
					</label></td>
					<td align="left"><s:textfield
						name="gratuityBean.GratuityPaid_CreditToAccount" size="23" /></td>
				</tr>
			</table>
			<!-- End of Payment Section --></td>
		</tr>

		<tr>
			<td class="formtext" colspan="3"><!-- Start of Cases pending Section -->
			<table width="100%" cellspacing="2"
				style="border: 1px solid #565656;" align="center">
				<tr class="clsTRPageCaption">
					<td colspan="3"><label class="set"
						name="gratuity.cases_pending_head"
						id="gratuity.cases_pending_head" ondblclick="callShowDiv(this);"><%=label.get("gratuity.cases_pending_head")%>
					</label></td>
				</tr>
				<tr>
					<td width="3%">11</td>
					<td width="66%"><label class="set"
						name="gratuity.gratuity_case" id="gratuity.gratuity_case"
						ondblclick="callShowDiv(this);"><%=label.get("gratuity.gratuity_case")%>
					</label></td>
					<td width="30%" align="left"><s:checkbox
						name="gratuityBean.AnyCasesPending"
						id="gratuityBean.AnyCasesPending" /> Yes /
					&#2361;&#2379;&#2351;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><label class="set" name="gratuity.gratuity_case_yes_no"
						id="gratuity.gratuity_case_yes_no" ondblclick="callShowDiv(this);"><%=label.get("gratuity.gratuity_case_yes_no")%>
					</label></td>
					<td align="left"><s:textarea
						name="gratuityBean.PendingCasesDetails" cols="38" rows="2" /></textarea></td>
				</tr>
			</table>
			<!-- End of Cases pending Section --></td>
		</tr>

		<tr>
			<td class="formtext" colspan="3">
			<table width="100%" cellspacing="2" align="center">
				<tr>
					<td class="formtext" colspan="3">
					<table width="100%">
						<tr>
							<td width="78%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	function goTo() {
		var combo = trim(document.getElementById('paraFrm_actList').value);
		var pageValue='';
		if(combo==''){
			alert('Please select act');
			return false;
		}
		if(combo=='POG'){
			pageValue='gratuityrules';
		}
		else if(combo=='MHRA'){
			pageValue='rentallowance';
		}
		else if(combo=='POB'){
			pageValue='bonusrules';
		}
		else if(combo=='CL'){
			pageValue='childlabour';
		}
		else if(combo=='ER'){
			pageValue='equalrenumeration';
		}else if(combo=='MB'){
			pageValue='maternitybenefits';
		}else if(combo=='DISH'){
			pageValue='annualfactory';
		}
		document.getElementById('paraFrm_pageToShow').value = pageValue;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_next.action';
		document.getElementById('paraFrm').submit();
	}
	
function nextFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_next.action';
		document.getElementById('paraFrm').submit();
	}
function previousFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	function returntolistFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_callReturnList.action';
		document.getElementById('paraFrm').submit();
	}
function saveandnextFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_gratuitySaveAndNextRecords.action';
		document.getElementById('paraFrm').submit();
	}
	
</script>


