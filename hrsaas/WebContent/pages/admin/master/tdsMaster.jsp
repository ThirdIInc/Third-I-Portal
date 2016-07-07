<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="TDSMaster" method="post" id="paraFrm" validate="true"
	target="main" theme="simple">
	<s:hidden name="hiddenCode" />
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
					<td width="93%" class="txt"><strong class="text_head">TDS
					Parameter </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="">&nbsp;</td>
					<td width="" align="right"><span class="style2"><font
						color="red">*</font></span>Indicates Required</td>
				</tr>
				<tr>
					<td width="20%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="70%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id='topButtonTable'>
						<tr valign="middle">
							<td nowrap="nowrap"></td>
							<td width="100%"><%@ include
								file="/pages/common/reportButtonPanel.jsp"%>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
					<div name="htmlReport" id='reportDiv'
						style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
					<iframe id="reportFrame" frameborder="0" onload=alertsize();
						style="vertical-align: top; float: left; border: 0px solid;"
						allowtransparency="yes" src="../pages/common/loading.jsp"
						scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
						name="htmlReport" width="100%" height="200"></iframe></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!--<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				<s:if test="insertButtonFlag">
				<td colspan="5" valign="middle"><s:if test="%{insertFlag}">
						<s:submit cssClass="save" action="TDSMaster_save" theme="simple"
							value="   Save" onclick="return formValidate();" />
					</s:if>  <s:submit cssClass="reset" action="TDSMaster_reset" theme="simple"
						value="   Reset" /> 
					<s:submit cssClass="back" action="TDSMaster_back" theme="simple"
							value="  Back" onclick="" />
					</td>				
				</s:if>
				<s:if test="updateButtonFlag">
				<td colspan="5" valign="middle"><s:if test="%{updateFlag}">
						<s:submit cssClass="edit" action="TDSMaster_update" theme="simple"
							value="  Update" onclick="return callUpdate();" />
					</s:if> <s:submit cssClass="reset" action="TDSMaster_reset" theme="simple"
						value="   Reset" /> <s:if test="%{deleteFlag}">

						<s:submit cssClass="delete" action="TDSMaster_delete"
							onclick="return callDelete('paraFrm_tdsCode');"
							value="    Delete" />
					</s:if>
					<s:submit cssClass="report" action="TDSMaster_report" theme="simple"
							value="  Report" onclick="" />
					<s:submit cssClass="back" action="TDSMaster_back" theme="simple"
							value="  Back" onclick="" />					
					</td>
				</s:if>
					<td>
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		-->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg" id="reportBodyTable">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td colspan="4" class="formhead"><strong
								class="forminnerhead">Select Financial Year</strong></td>
						</tr>
						<tr>
							<td colspan="1" width="22%"><label class="set" id="fromyear"
								name="fromyear" ondblclick="callShowDiv(this);"><%=label.get("fromyear")%></label>:<font
								color="red">*</font></td>
							<td colspan="1" width="30%"><s:textfield name="fromYear"
								theme="simple" readonly="false" size="25" maxlength="4"
								onkeypress="return numbersOnly();" onblur="add()" /></td>
							<td colspan="1" width="20%"><label class="set" id="toyear"
								name="toyear" ondblclick="callShowDiv(this);"><%=label.get("toyear")%></label>:<font
								color="red">*</font></td>
							<td colspan="1" width="30%"><s:textfield name="toYear"
								theme="simple" readonly="true" maxlength="4" size="25" /></td>
						</tr>
						<tr>
							<td colspan="1" width="22%"><label class="set"
								id="finalizeTaxationProcess" name="finalizeTaxationProcess"
								ondblclick="callShowDiv(this);"><%=label.get("finalizeTaxationProcess")%></label>
							:</td>
							<td colspan="1" width="30%"><s:checkbox name="lockFlag"
								theme="simple" onclick="checkLock();" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" cellpadding="0" cellspacing="2">
								<tr>
									<td colspan="4" class="formhead"><strong
										class="forminnerhead">Define Parameters</strong></td>
								</tr>
								<tr>
									<s:hidden name="tdsCode" />
									<td colspan="1" width="22%"><label class="set"
										id="taxation.TdsEffDate" name="taxation.TdsEffDate"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.TdsEffDate")%></label>:<font
										color="red">*</font></td>
									<td colspan="1" width="30%"><s:textfield theme="simple"
										size="25" onkeypress="return numbersWithHiphen();"
										maxlength="10" name="tdsDate" /> <s:a
										href="javascript:NewCal('paraFrm_tdsDate','DDMMYYYY');">
										<img class="iconImage" id="ctrlHide"
											src="../pages/images/recruitment/Date.gif" width="16"
											height="16" border="0" align="absmiddle" />
									</s:a></td>
									<td colspan="1" width="20%"><label class="set"
										id="taxation.TDSSurcharge" name="taxation.TDSSurcharge"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.TDSSurcharge")%></label>:<font
										color="red">*</font></td>
									<td colspan="1" width="30%"><s:textfield theme="simple"
										name="surcharge" maxlength="6" size="25"
										onkeypress="return numbersWithDot();" /></td>
								</tr>
								<tr>
									<td colspan="1" width="22%"><label class="set"
										id="taxation.EduCess" name="taxation.EduCess"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.EduCess")%></label>:<font
										color="red">*</font></td>
									<td colspan="1" width="30%"><s:textfield theme="simple"
										name="eduCess" maxlength="6" size="25"
										onkeypress="return numbersWithDot();" /></td>
									<td width="20%" colspan="1"><label class="set"
										id="taxation.SurChargeLmt" name="taxation.SurChargeLmt"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.SurChargeLmt")%></label>:<font
										color="red">*</font></td>
									<td width="30%" colspan="1"><s:textfield theme="simple"
										name="citizenSurLimit" maxlength="15" size="25"
										onkeypress="return numbersWithDot();" /></td>
								<tr>
									<td width="22%" colspan="1"><label class="set"
										id="taxation.SrCitAge" name="taxation.SrCitAge"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.SrCitAge")%></label>:<font
										color="red">*</font></td>
									<td width="30%" colspan="1"><s:textfield theme="simple"
										size="25" name="citizenAge" maxlength="2"
										onkeypress="return numbersOnly();" /></td>
									<td colspan="1" width="20%"><label class="set"
										id="taxation.TDSDebtType" name="taxation.TDSDebtType"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.TDSDebtType")%></label>:<font
										color="red">*</font></td>
									<td colspan="1" width="30%"><s:hidden name="tdsDebitCode" /><s:textfield
										size="25" theme="simple" name="tdsDebit" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="15" align="absmiddle" width="16"
										id="ctrlHide"
										onclick="javascript:callsF9(500,325,'TDSMaster_f9debit.action');">
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="taxation.BasicCreditName" name="taxation.BasicCreditName"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.BasicCreditName")%></label>:<font
										color="red">*</font></td>
									<td colspan="1" width="30%"><s:hidden name="basicCode" /><s:textfield
										size="25" theme="simple" name="basicName" readonly="true" />
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="15" align="absmiddle" width="16"
										id="ctrlHide"
										onclick="javascript:callsF9(500,325,'TDSMaster_f9basic.action');">
									</td>

									<td width="22%" colspan="1"><label class="set"
										id="taxation.DACreditName" name="taxation.DACreditName"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.DACreditName")%></label>:</td>
									<td width="30%" colspan="1"><s:hidden name="daCode" /> <s:textfield
										name="daName" value="%{daName}" size="25" readonly="true" />
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="15" align="absmiddle" width="16"
										id="ctrlHide"
										onclick="javascript:callsF9(500,325,'TDSMaster_f9da.action');">
									</td>
								</tr>
								<tr>
									<td width="22%" colspan="1"><label class="set"
										id="InvVerifyDate" name="InvVerifyDate"
										ondblclick="callShowDiv(this);"><%=label.get("InvVerifyDate")%></label>:
									<!--  <font color="red">*</font>--></td>
									<td width="30%" colspan="1"><s:textfield
										name="invVerificationDate" size="25"
										onkeypress="return numbersWithHiphen();" maxlength="10" /> <s:a
										href="javascript:NewCal('paraFrm_invVerificationDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif" id="ctrlHide"
											class="iconImage" height="18" align="absmiddle" width="18">
									</s:a></td>
									<td width="22%" colspan="1"><label class="set"
										id="investment.declaration.cutOff.date"
										name="investment.declaration.cutOff.date"
										ondblclick="callShowDiv(this);"><%=label.get("investment.declaration.cutOff.date")%></label>:
									<!--  <font color="red">*</font>--></td>
									<td width="30%" colspan="1"><s:textfield
										name="invDeclarationCuttOffDate" size="25"
										onkeypress="return numbersWithHiphen();" maxlength="10" /> <s:a
										href="javascript:NewCal('paraFrm_invDeclarationCuttOffDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif" id="ctrlHide"
											class="iconImage" height="18" align="absmiddle" width="18">
									</s:a></td>
								</tr>
								<!-- Added by Ganesh 7/10/2011 -->
								<tr>
									<td width="22%" colspan="1"><label class="set"
										id="taxation.SignAuth" name="taxation.SignAuth"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.SignAuth")%></label>:</td>
									<td width="30%" colspan="1"><s:hidden name="signAuthId" />
									<s:hidden name="token" /> <s:textfield name="signAuthName"
										value="%{signAuthName}" size="25" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="15" align="absmiddle" width="16"
										id="ctrlHide"
										onclick="javascript:callsF9(500,325,'TDSMaster_f9signAuth.action');">
									</td>

									<td colspan="1" width="20%"><label class="set"
										id="cutoffReimburseDate" name="cutoffReimburseDate"
										ondblclick="callShowDiv(this);"><%=label.get("cutoffReimburseDate")%></label>:</td>
									<td colspan="1" width="30%"><s:textfield theme="simple"
										name="ReimbursebillDate" readonly="false" size="25"
										onkeypress="return numbersWithHiphen();" maxlength="10" /> <s:a
										href="javascript:NewCal('paraFrm_ReimbursebillDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif" id="ctrlHide"
											class="iconImage" height="18" align="absmiddle" width="18">
									</s:a></td>
								</tr>
								<tr>
									<td width="22%" colspan="1"><label class="set"
										id="monthly.investment.declaration.period"
										name="monthly.investment.declaration.period"
										ondblclick="callShowDiv(this);"><%=label.get("monthly.investment.declaration.period")%></label>:
									<!--  <font color="red">*</font>--></td>
									<td>
									<table>
										<tr>
											<td><!-- <label class="set" id="from" name="from"
												ondblclick="callShowDiv(this);"><%=label.get("from")%></label><font
												color="red"></font>: --> <s:textfield size="10"
												name="monthInvestmentDecPeriodFromDate" maxlength="2"
												onkeypress="return numbersOnly();"
												onblur="datePeriodFrom();" /></td>
											<td><label class="set" id="to" name="to"
												ondblclick="callShowDiv(this);"><%=label.get("to")%></label><font
												color="red"></font>: <s:textfield size="10"
												name="monthInvestmentDecPeriodToDate" maxlength="2"
												onkeypress="return numbersOnly();" onblur="datePeriodTo();" />
											</td>
										</tr>
									</table>
									</td>
									<!--<td width="30%" colspan="1"><s:textfield
											name="monthInvestmentDecPeriodFromDate" size="25"
											onkeypress="return numbersWithHiphen();" maxlength="10" /> <s:a
											href="javascript:NewCal('paraFrm_monthInvestmentDecPeriodFromDate','DDMMYYYY');">
											<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" height="18" align="absmiddle" width="18">
									</s:a></td>

									<td colspan="1" width="20%"><label class="set"
										id="monthly.investment.declaration.period.to" name="monthly.investment.declaration.period.to"
										ondblclick="callShowDiv(this);"><%=label.get("monthly.investment.declaration.period.to")%></label>:</td>
									<td colspan="1" width="30%"><s:textfield theme="simple"
										name="monthInvestmentDecPeriodToDate" readonly="false" size="25"
										onkeypress="return numbersWithHiphen();" maxlength="10" /> <s:a
										href="javascript:NewCal('paraFrm_monthInvestmentDecPeriodToDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="18" align="absmiddle" width="18">
									</s:a></td>-->
									<td colspan="1" width="20%"><label class="set"
										id="taxation.RebateInvLmt" name="taxation.RebateInvLmt"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.RebateInvLmt")%></label>:</td>
									<td colspan="1" width="30%"><s:textfield theme="simple"
										size="25" name="rebateLimit" readonly="false" maxlength="15"
										onkeypress="return numbersWithDot();" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="taxation.EmpProvFund" name="taxation.EmpProvFund"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.EmpProvFund")%></label>:<font
										color="red">*</font></td>
									<td colspan="3" width="30%"><s:hidden name="provFundCode" />
									<s:textfield size="25" theme="simple" name="provFundName"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="15" align="absmiddle" width="16"
										id="ctrlHide"
										onclick="javascript:callsF9(500,325,'TDSMaster_f9provident.action');">
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="6" width="100%">

					<table width="100%" border="0" cellpadding="0" cellspacing="2"
						class="formbg">
						<tr>
							<td colspan="6" class="formhead"><strong
								class="forminnerhead">HRA Exceptions</strong></td>
						</tr>
						<tr>
							<td colspan="6" valign="top">
							<table width="100%">
								<tr>
									<td width="30%"><label class="set"
										id="taxation.HRACreditName" name="taxation.HRACreditName"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.HRACreditName")%></label>
									:<font color="red">*</font></td>
									<td width="" colspan="5"><s:hidden name="hraCode" /> <s:textfield
										size="25" theme="simple" name="hraName" maxlength="20"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="15" align="absmiddle" width="16"
										id="ctrlHide"
										onclick="javascript:callsF9(500,325,'TDSMaster_f9hra.action');">
									</td>
								</tr>
							</table>
							</td>
						</tr>

						<tr>
							<td colspan="3" valign="top">
							<table>
								<tr>
									<td colspan="3" class="formhead"><strong
										class="forminnerhead">HRA Paid Conditions</strong></td>
								</tr>
								<tr>
									<td width="22%" colspan="1"><label class="set"
										id="taxation.HRAExemption" name="taxation.HRAExemption"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.HRAExemption")%></label>
									:<font color="red">*</font></td>
									<td width="30%" colspan="5"><s:hidden name="hraExCode" />
									<s:textfield name="hraExName" value="%{hraExName}" size="27"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" id="ctrlHide"
										class="iconImage" height="15" align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'TDSMaster_f9hraEx.action');">
									</td>
								</tr>
								<tr>
									<td colspan="1" width="15%" nowrap="nowrap"><label
										class="set" id="taxation.HRAPaidCond1"
										name="taxation.HRAPaidCond1" ondblclick="callShowDiv(this);"><%=label.get("taxation.HRAPaidCond1")%></label>:
									</td>
									<td colspan="1" width="15%"><s:textfield name="salAmt"
										value="%{salAmt}" size="27" readonly="true" /></td>
									<td colspan="1" width="15%"><input type="button"
										class="token" name="formCalc" value=Calculator
										id='paraFrm_formCalc'
										onclick="callFormulaBuilder('paraFrm_salAmt');" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%" nowrap="nowrap"><label
										class="set" id="taxation.HRAPaidCond2"
										name="taxation.HRAPaidCond2" ondblclick="callShowDiv(this);"><%=label.get("taxation.HRAPaidCond2")%></label>:
									</td>
									<td colspan="1" width="15%"><s:textfield name="salAmt2"
										value="%{salAmt2}" size="27" readonly="true" /></td>
									<td colspan="1" width="15%"><input type="button"
										class="token" name="formCalc2" value=Calculator
										id='paraFrm_formCalc2'
										onclick="callFormulaBuilder('paraFrm_salAmt2');" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%" nowrap="nowrap"><label
										class="set" id="taxation.HRAPaidCond3"
										name="taxation.HRAPaidCond3" ondblclick="callShowDiv(this);"><%=label.get("taxation.HRAPaidCond3")%></label>:
									</td>
									<td colspan="1" width="10%" nowrap="nowrap"><label
										class="set" id="taxation.Rent" name="taxation.Rent"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.Rent")%></label>
									<s:textfield name="salAmt3" value="%{salAmt3}" size="22"
										readonly="true" /></td>
									<td colspan="1" width="10%"><input type="button"
										class="token" name="formCalc3" value=Calculator
										id='paraFrm_formCalc3'
										onclick="callFormulaBuilder('paraFrm_salAmt3');" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%" nowrap="nowrap"><label
										class="set" id="taxation.HRAPaidCondMetro"
										name="taxation.HRAPaidCondMetro"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.HRAPaidCondMetro")%></label>:
									</td>
									<td colspan="1" width="15%"><s:textfield
										name="hraPaidMetro" value="%{hraPaidMetro}" size="27"
										readonly="true" /></td>
									<td colspan="1" width="15%"><input type="button"
										class="token" name="formCalc7" value=Calculator
										id='paraFrm_formCalc7'
										onclick="callFormulaBuilder('paraFrm_hraPaidMetro');" /></td>
								</tr>
							</table>
							</td>
							<td colspan="3" valign="top">
							<table>
								<tr>
									<td colspan="3" class="formhead"><strong
										class="forminnerhead">HRA Unpaid Conditions</strong></td>
								</tr>
								<tr>
									<td width="22%" colspan="1"><label class="set"
										id="taxation.HRAExemption" name="taxation.HRAExemption"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.HRAExemption")%></label>:<font
										color="red">*</font></td>
									<td width="30%" colspan="2"><s:hidden
										name="hraExUnpaidCode" /> <s:textfield name="hraExUnpaidName"
										value="%{hraExUnpaidName}" size="27" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" id="ctrlHide"
										class="iconImage" height="15" align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'TDSMaster_f9hraUnpaidEx.action');">
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%" nowrap="nowrap"><label
										class="set" id="taxation.HRAUnpaidCond1"
										name="taxation.HRAUnpaidCond1" ondblclick="callShowDiv(this);"><%=label.get("taxation.HRAUnpaidCond1")%></label>:
									</td>
									<td colspan="1" width="15%"><s:textfield name="salAmt4"
										value="%{salAmt4}" size="27" readonly="true" /></td>
									<td colspan="1" width="15%"><input type="button"
										class="token" name="formCalc4" value=Calculator
										id='paraFrm_formCalc4'
										onclick="callFormulaBuilder('paraFrm_salAmt4');" /></td>
								</tr>
								<tr>
									<td colspan="1" width="20%" nowrap="nowrap"><label
										class="set" id="taxation.HRAUnpaidCond2"
										name="taxation.HRAUnpaidCond2" ondblclick="callShowDiv(this);"><%=label.get("taxation.HRAUnpaidCond2")%></label>:
									</td>
									<td colspan="1" width="15%"><s:textfield name="salAmt5"
										value="%{salAmt5}" size="13" readonly="true" />% of Income</td>
									<td colspan="1" width="15%"><input type="button"
										class="token" name="formCalc5" value=Calculator
										id='paraFrm_formCalc5'
										onclick="callFormulaBuilder('paraFrm_salAmt5');" /></td>
								</tr>
								<tr>
									<td colspan="1" width="15%" nowrap="nowrap"><label
										class="set" id="taxation.HRAUnpaidCond3"
										name="taxation.HRAUnpaidCond3" ondblclick="callShowDiv(this);"><%=label.get("taxation.HRAUnpaidCond3")%></label>:
									</td>
									<td colspan="1" width="50%" nowrap="nowrap"><label
										class="set" id="taxation.Rent1" name="taxation.Rent"
										ondblclick="callShowDiv(this);"><%=label.get("taxation.Rent")%></label>

									<s:textfield name="salAmt6" value="%{salAmt6}" size="8"
										readonly="true" />% of Income</td>
									<td colspan="1" width="25%"><input type="button"
										class="token" name="formCalc6" value=Calculator
										id='paraFrm_formCalc6'
										onclick="callFormulaBuilder('paraFrm_salAmt6');" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" width="100%" class="formhead"><b>Accommodation
					Parameters:</b></td>
				</tr>
				<tr>
					<td colspan="4" width="100%">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td colspan="1" width="30%"><label class="set"
								id="mapPerquisiteHead" name="mapPerquisiteHead"
								ondblclick="callShowDiv(this);"><%=label.get("mapPerquisiteHead")%></label>:</td>
							<td colspan="1" width="30%"><s:hidden
								name="mapPerquisiteHeadCode" /> <s:textfield
								name="mapPerquisiteHeadName" size="25" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="15" align="absmiddle" width="16" id="ctrlHide"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9mapPerquisiteHeadAction.action');">
							</td>
							<td width="20%"></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td colspan="4"><label class="set"
								id="accomPerkCompanyOwnedHouse"
								name="accomPerkCompanyOwnedHouse"
								ondblclick="callShowDiv(this);"><%=label.get("accomPerkCompanyOwnedHouse")%></label>:</td>
						</tr>
						<tr>
							<td colspan="1" width="30%" nowrap="nowrap"><label
								class="set" id="ifPopulationofCityLessThan25Lakh"
								name="ifPopulationofCityLessThan25Lakh"
								ondblclick="callShowDiv(this);"><%=label.get("ifPopulationofCityLessThan25Lakh")%></label>
							</td>
							<td width="30%"><s:textfield name="accomPerqCentOwnedLess"
								onkeypress="return numbersWithDot();" size="8" /> &nbsp;<label
								class="set" id="percentageofSalaryIncome"
								name="percentageofSalaryIncome" ondblclick="callShowDiv(this);"><%=label.get("percentageofSalaryIncome")%></label>
							</td>
							<td width="20%"></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td colspan="1" width="30%" nowrap="nowrap"><label
								class="set" id="ifPopulationofCityGreaterThan25Lakh"
								name="ifPopulationofCityGreaterThan25Lakh"
								ondblclick="callShowDiv(this);"><%=label.get("ifPopulationofCityGreaterThan25Lakh")%></label>
							</td>
							<td width="30%"><s:textfield name="accomPerqCentOwnedHigher"
								onkeypress="return numbersWithDot();" size="8" /> &nbsp;<label
								class="set" id="percentageofSalaryIncome"
								name="percentageofSalaryIncome" ondblclick="callShowDiv(this);"><%=label.get("percentageofSalaryIncome")%></label>
							</td>
							<td width="20%"></td>
							<td width="20%"></td>
						</tr>

						<tr>
							<td colspan="4"></td>
						</tr>
						<tr>
							<td colspan="4"><label class="set"
								id="accommPerkCompanyPayRent" name="accommPerkCompanyPayRent"
								ondblclick="callShowDiv(this);"><%=label.get("accommPerkCompanyPayRent")%></label>:</td>
						</tr>
						<tr>
							<td colspan="1" width="30%" nowrap="nowrap"><label
								class="set" id="ifPopulationofCityLessThan25Lakh"
								name="ifPopulationofCityLessThan25Lakh"
								ondblclick="callShowDiv(this);"><%=label.get("ifPopulationofCityLessThan25Lakh")%></label>
							</td>
							<td width="30%"><s:textfield name="accomPerqCentRentedLess"
								onkeypress="return numbersWithDot();" size="8" /> &nbsp;<label
								class="set" id="percentageofSalaryIncome"
								name="percentageofSalaryIncome" ondblclick="callShowDiv(this);"><%=label.get("percentageofSalaryIncome")%></label>
							</td>
							<td width="20%"></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td colspan="1" width="30%" nowrap="nowrap"><label
								class="set" id="ifPopulationofCityGreaterThan25Lakh"
								name="ifPopulationofCityGreaterThan25Lakh"
								ondblclick="callShowDiv(this);"><%=label.get("ifPopulationofCityGreaterThan25Lakh")%></label>
							</td>
							<td width="30%"><s:textfield
								name="accomPerqCentRentedHigher"
								onkeypress="return numbersWithDot();" size="8" /> &nbsp;<label
								class="set" id="percentageofSalaryIncome"
								name="percentageofSalaryIncome" ondblclick="callShowDiv(this);"><%=label.get("percentageofSalaryIncome")%></label>
							</td>
							<td width="20%"></td>
							<td width="20%"></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" width="100%" class="formhead"><b>Leave
					Encashment Exemption:</b></td>
				</tr>
				<tr>
					<td colspan="4" width="100%">

					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td colspan="3" class="formhead"><strong
								class="forminnerhead">Least of the following is Exempt</strong></td>
						</tr>
						<tr>
							<td width="10%" colspan="1"><strong>Condition 1:</strong></td>
							<td colspan="1" width="40%"><label class="set"
								id="leaveEncash1" name="leaveEncash1"
								ondblclick="callShowDiv(this);"><%=label.get("leaveEncash1")%></label>:
							</td>
							<td colspan="1" width="40%"><s:textfield
								name="leaveEncashamt" onkeypress="return numbersOnly();"
								size="15" maxlength="10" /></td>
							<td width="10%" colspan="1">&nbsp</td>
						</tr>
						<tr>
							<td><strong>Condition 2:</strong></td>
							<td colspan="2" width="30%"><label class="set"
								id="leaveEncash2" name="leaveEncash2"
								ondblclick="callShowDiv(this);"><%=label.get("leaveEncash2")%></label>
							</td>
						</tr>
						<tr>
							<td><strong>Condition 3:</strong></td>
							<td colspan="1" width="30%"><label class="set"
								id="leaveEncash4" name="leaveEncash4"
								ondblclick="callShowDiv(this);"><%=label.get("leaveEncash4")%></label>
							<s:textfield name="leaveEncashMonths"
								onkeypress="return numbersOnly();" size="5" maxlength="2" /> <label
								class="set" id="leaveEncash3" name="leaveEncash3"
								ondblclick="callShowDiv(this);"><%=label.get("leaveEncash3")%></label>
							</td>
							<td colspan="1" width="20%"><s:textfield
								name="leaveEncashFormula" value="%{leaveEncashFormula}"
								size="12" readonly="true" /><input type="button" class="token"
								name="EncashFormula" value="Calculator"
								id='paraFrm_EncashFormula'
								onclick="callFormulaBuilder('paraFrm_leaveEncashFormula');" /></td>
						</tr>
						<tr>
							<td colspan="1" width="30%"><label class="set"
								id="leaveEncash5" name="leaveEncash5"
								ondblclick="callShowDiv(this);"><%=label.get("leaveEncash5")%></label>:</td>
							<td colspan="1" width="30%"><s:hidden name="leaveEncInvcode" />
							<s:textfield name="leaveEncInvName" size="25" readonly="true" />
							<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="15" align="absmiddle" width="16"
								id="ctrlHide"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9LeaveEx.action');">
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" width="100%" class="formhead"><b>Gratuity
					Exemption:</b></td>
				</tr>
				<tr>
					<td colspan="4" width="100%">

					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td width="30%"><label class="set" id="gratuityAmt"
								name="gratuityAmt" ondblclick="callShowDiv(this);"><%=label.get("gratuityAmt")%></label>:
							</td>
							<td colspan="1" width="30%"><s:textfield
								name="gratuityAmount" onkeypress="return numbersOnly();"
								size="15" maxlength="10" /></td>
							<td width="20%"></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="25%"><label class="set" id="exemptUnderSect"
								name="exemptUnderSect" ondblclick="callShowDiv(this);"><%=label.get("exemptUnderSect")%></label>:
							</td>
							<td colspan="1" width="25%"><s:textfield
								name="exemptSectionNo" readonly="true" size="35" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								id="ctrlHide" width="16"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9taxaction.action');">
							<s:hidden name="taxCode" /></td>
						</tr>
						<tr>
							<td width="25%"><label class="set" id="gratuityCreditHead"
								name="gratuityCreditHead" ondblclick="callShowDiv(this);"><%=label.get("gratuityCreditHead")%></label>:</td>
							<td colspan="1" width="25%"><s:hidden name="creditCode" />
							<s:textfield name="creditType" size="35" readonly="true" /> <img
								id='ctrlHide'
								src="../pages/common/css/default/images/search2.gif"
								class="iconImage" height="15" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9credits.action');" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" width="100%" class="formhead"><b>LTA
					Exemption:</b></td>
				</tr>
				<tr>
					<td colspan="4" width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td width="30%"><label class="set" id="ltaAmt" name="ltaAmt"
								ondblclick="callShowDiv(this);"><%=label.get("ltaAmt")%></label>:
							</td>
							<td colspan="1" width="30%"><s:textfield name="ltaAmount"
								onkeypress="return numbersOnly();" size="15" maxlength="10" /></td>
							<td width="20%"></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="25%"><label class="set" id="ltaexemptUnderSect"
								name="ltaexemptUnderSect" ondblclick="callShowDiv(this);"><%=label.get("ltaexemptUnderSect")%></label>:
							</td>
							<td colspan="1" width="25%"><s:textfield
								name="ltaExemptSectionNo" readonly="true" size="35" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								id="ctrlHide" width="16"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9Tdstaxaction.action');">
							<s:hidden name="ltaTaxCode" /></td>
						</tr>
						<tr>
							<td width="25%"><label class="set" id="ltaCreditHead"
								name="ltaCreditHead" ondblclick="callShowDiv(this);"><%=label.get("ltaCreditHead")%></label>:</td>
							<td width="25%"><s:hidden name="ltaCreditCode" /> <s:textfield
								name="ltaCreditType" size="35" readonly="true" /> <img
								id='ctrlHide'
								src="../pages/common/css/default/images/search2.gif"
								class="iconImage" height="15" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9Tdscredits.action');" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<!-- Added  By Ganesh 4 Oct 2011 start -->
				<!-- Transport Allowance Exemption -->
				<tr>
					<td colspan="4" width="100%" class="formhead"><b>Transport
					Allowance Exemption:</b></td>
				</tr>
				<tr>
					<td colspan="4" width="100%">

					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td width="30%"><label class="set"
								id="transport.allowance.limit" name="transport.allowance.limit"
								ondblclick="callShowDiv(this);"><%=label.get("transport.allowance.limit")%></label>:
							</td>
							<td width="30%"><s:textfield name="traAllowanceLimit"
								onkeypress="return numbersOnly();" size="15" maxlength="10" /></td>
							<td width="20%"></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="30%"><label class="set"
								id="transAllowMonthlyLimitIncasePermDisabi"
								name="transAllowMonthlyLimitIncasePermDisabi"
								ondblclick="callShowDiv(this);"><%=label.get("transAllowMonthlyLimitIncasePermDisabi")%></label>:
							</td>
							<td width="30%"><s:textfield
								name="transAllowanceLimitPermDisabi"
								onkeypress="return numbersOnly();" size="15" maxlength="10" /></td>
							<td width="20%"></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="25%"><label class="set"
								id="transportAllowanceCreditHead"
								name="transportAllowanceCreditHead"
								ondblclick="callShowDiv(this);"><%=label.get("transportAllowanceCreditHead")%></label>:
							</td>
							<td colspan="1" width="25%"><s:textfield
								name="traAllowCreditHeadName" readonly="true" size="35" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								id="ctrlHide" width="16"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9traAllowCreditHeadAction.action');">
							<s:hidden name="traAllowCreditHeadCode" /></td>
						</tr>
						<tr>
							<td width="25%"><label class="set"
								id="traAllowexemptUnderSect" name="traAllowexemptUnderSect"
								ondblclick="callShowDiv(this);"><%=label.get("traAllowexemptUnderSect")%></label>:
							</td>
							<td colspan="1" width="25%"><s:textfield
								name="traAllowExemptSectionNo" readonly="true" size="35" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								id="ctrlHide" width="16"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9traAllowTaxAction.action');">
							<s:hidden name="traAllowTaxCode" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<!-- Vehicle Maintenance Exemption -->
				<tr>
					<td colspan="4" width="100%" class="formhead"><b>Vehicle
					Maintenance Exemption:</b></td>
				</tr>
				<tr>
					<td colspan="4" width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td width="30%" colspan="1"><strong>Condition 1:</strong> <label
								class="set" id="vehicle.capacity.greater.1600"
								name="vehicle.capacity.greater.1600"
								ondblclick="callShowDiv(this);"><%=label.get("vehicle.capacity.greater.1600")%></label></td>
							<td colspan="1" width="30%"><s:textfield
								name="vehicleCapGreaterthan1600"
								onkeypress="return numbersOnly();" size="15" maxlength="10" /></td>
							<td width="20%"></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="10%" colspan="1"><strong>Condition 2:</strong> <label
								class="set" id="vehicle.capacity.less.1600"
								name="vehicle.capacity.less.1600"
								ondblclick="callShowDiv(this);"><%=label.get("vehicle.capacity.less.1600")%></label></td>
							<td colspan="1" width="40%"><s:textfield
								name="vehicleCapLessthan1600" onkeypress="return numbersOnly();"
								size="15" maxlength="10" /></td>
						</tr>
						<tr>
							<td width="25%"><label class="set"
								id="driver.used.additional.exemption"
								name="driver.used.additional.exemption"
								ondblclick="callShowDiv(this);"><%=label.get("driver.used.additional.exemption")%></label>:
							</td>
							<td width="25%"><s:textfield name="driverUsedAddExemption"
								onkeypress="return numbersOnly();" size="15" maxlength="10" /></td>
							<td width="25%"></td>
							<td width="25%"></td>
						</tr>
						<tr>
							<td width="25%"><label class="set"
								id="vehicalAllowexemptUnderSect"
								name="vehicalAllowexemptUnderSect"
								ondblclick="callShowDiv(this);"><%=label.get("vehicalAllowexemptUnderSect")%></label>:
							</td>
							<td colspan="1" width="25%"><s:textfield
								name="vehicalAllowExemptSectionNo" readonly="true" size="35" />
							<img src="../pages/images/recruitment/search2.gif" height="16"
								id="ctrlHide" width="16"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9vehicalAllowAction.action');">

							<s:hidden name="vehicalAllowTaxCode" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<!-- Donation to Charities exemption -->
				<tr>
					<td colspan="4" width="100%" class="formhead"><b>Donation
					to Charities exemption:</b></td>
				</tr>
				<tr>
					<td colspan="4" width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="0"
						class="formbg">
						<tr>
							<td width="30%"><label class="set"
								id="donationsexemptUnderSect" name="donationsexemptUnderSect"
								ondblclick="callShowDiv(this);"><%=label.get("donationsexemptUnderSect")%></label>:
							</td>
							<td colspan="3" width="70%"><s:textfield
								name="donationsExemptSectionNo" readonly="true" size="35" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								id="ctrlHide" width="16"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9donationsAction.action');">
							<s:hidden name="donationsTaxCode" /></td>
						</tr>
						<tr>
							<td width="100%" colspan="4"><label class="set"
								id="donationAmtHardcoded" name="donationAmtHardcoded"
								ondblclick="callShowDiv(this);"><%=label.get("donationAmtHardcoded")%></label>:
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="4">1) <label class="set"
								id="sumOfDonations" name="sumOfDonations"
								ondblclick="callShowDiv(this);"><%=label.get("sumOfDonations")%></label>
							</td>
						</tr>
						<tr>
							<td width="75%" colspan="2"><label class="set"
								id="formulaEmp" name="formulaEmp"
								ondblclick="callShowDiv(this);">2) Formula </label>: <s:textfield
								name="donationFormPercent" size="5" /> % of (Employee's Gross
							Total Income - (Section 80 C + sum of selected Investments))</td>

							<td width="25%"><s:textarea rows="2" cols="35"
								theme="simple" name="donationApplInv" readonly="true" /></td>
							<td width='5%'><img src="../pages/images/search2.gif"
								id="ctrlHide" class="iconImage" height="16" align="absmiddle"
								width="16"
								onclick="javascript:callDropdown('paraFrm_donationApplInv',200,250,'TDSMaster_f9DonationApplInvestment.action',event,'false','','right')">
							<s:hidden name="donationApplInvCode"></s:hidden></td>
						</tr>
					</table>
					</td>
				</tr>
				<!--<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				<s:if test="insertButtonFlag">
				<td colspan="5" valign="middle"><s:if test="%{insertFlag}">
						<s:submit cssClass="save" action="TDSMaster_save" theme="simple"
							value="   Save" onclick="return formValidate();" />
					</s:if>  <s:submit cssClass="reset" action="TDSMaster_reset" theme="simple"
						value="   Reset" /> 
					<s:submit cssClass="back" action="TDSMaster_back" theme="simple"
							value="  Back" onclick="" />					
					</td>				
				</s:if>
				<s:if test="updateButtonFlag">
				<td colspan="5" valign="middle"><s:if test="%{updateFlag}">
						<s:submit cssClass="edit" action="TDSMaster_update" theme="simple"
							value="  Update" onclick="return callUpdate();" />
					</s:if> <s:submit cssClass="reset" action="TDSMaster_reset" theme="simple"
						value="   Reset" /> <s:if test="%{deleteFlag}">

						<s:submit cssClass="delete" action="TDSMaster_delete"
							onclick="return callDelete('paraFrm_tdsCode');"
							value="    Delete" />
					</s:if>
					<s:submit cssClass="report" action="TDSMaster_report" theme="simple"
							value="  Report" onclick="" />
					<s:submit cssClass="back" action="TDSMaster_back" theme="simple"
							value="  Back" onclick="" />					
					</td>				
				</s:if>				
				</tr>
			</table>
			</td>
		</tr>-->
				<%--<tr>
			<td colspan="4" width="100%" class="formhead"><b>Permanent Disability Deduction :</b></td>
		</tr>
		<tr>
			<td colspan="4" width="100%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
		 			<tr>
						<td  width="30%"><label class="set"
								id="PermanentDisabilityDeductionUnderSec" name="PermanentDisabilityDeductionUnderSec"
								ondblclick="callShowDiv(this);"><%=label.get("PermanentDisabilityDeductionUnderSec")%></label>:
						</td>
						<td colspan="1"  width="30%">
							<s:textfield name="permanentDisabilityDedName" readonly="true" size="35" /> 
							<img src="../pages/images/recruitment/search2.gif" height="16" id ="ctrlHide"
								width="16" onclick="javascript:callsF9(500,325,'TDSMaster_f9permanentDisabilityAction.action');">
							<s:hidden name="permanentDisabilityDedCode" />
						</td>
						<td  width="20%"></td>
						<td  width="20%"></td>
					</tr>
					
				</table>
			</td>
		</tr>--%>
				<tr>
					<td colspan="4" width="100%" class="formhead"><b>Loan
					Interest Rates :</b></td>
				</tr>
				<tr>
					<td colspan="4" width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="0"
						class="formbg">
						<tr>
							<td width="30%"><label class="set" id="govsStandardLoanRate"
								name="govsStandardLoanRate" ondblclick="callShowDiv(this);"><%=label.get("govsStandardLoanRate")%></label>:
							</td>
							<td colspan="1" width="30%"><s:textfield name="govLoanRate"
								onkeypress="return numbersWithDot();" size="15" /></td>
						</tr>
						<tr>
							<td width="30%"><label class="set" id="minimumLoanAmount"
								name="minimumLoanAmount" ondblclick="callShowDiv(this);"><%=label.get("minimumLoanAmount")%></label>:
							</td>
							<td colspan="1" width="30%"><s:textfield name="minLoanAmt"
								onkeypress="return numbersWithDot();" size="15" /></td>
							<td width="20%"></td>
							<td width="20%"></td>
						</tr>
						<tr>
							<td width="30%"><label class="set"
								id="mapPerquisiteHeadforCompanyLoan"
								name="mapPerquisiteHeadforCompanyLoan"
								ondblclick="callShowDiv(this);"><%=label.get("mapPerquisiteHeadforCompanyLoan")%></label>:
							</td>
							<td colspan="1" width="30%"><s:textfield
								name="perqHeadCompanyLoan" readonly="true" size="35" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								id="ctrlHide" width="16"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9perqHeadCompanyLoanAction.action');">
							<s:hidden name="perqHeadCompanyLoanCode" /></td>
							<td width="20%"></td>
							<td width="20%"></td>
						</tr>

					</table>
					</td>
				</tr>
				<!--Tax Rebate Configuration -->
				<tr>
					<td colspan="4" width="100%" class="formhead"><b>Tax
					Rebate (Deduction U/S 87A)Configuration :</b></td>
				</tr>
				<tr>
					<td colspan="4" width="100%">
					<table width="100%" border="0" cellpadding="1" cellspacing="0"
						class="formbg">
						<tr>
							<td colspan="1" width="30%"><label class="set"
								id="taxRebateEnLbl" name="taxRebateEnLbl"
								ondblclick="callShowDiv(this);"> <%=label.get("taxRebateEnLbl")%></label>:</td>
							<td colspan="1" width="30%"><s:checkbox
								name="taxRebateEnable" theme="simple"
								onclick="checkRebateTax();" /></td>
							<td colspan="2" width="40%">
						</tr>
						<tr>
							<td colspan="1" width="30%"><label class="set"
								id="taxRebateAmountLbl" name="taxRebateAmountLbl"
								ondblclick="callShowDiv(this)"> <%=label.get("taxRebateAmountLbl")%></label>:<font
								color="red">*</font></td>
							<td colspan="1" width="30%"><s:textfield
								name="taxRebateAmount" size="35"
								onkeypress="return numbersOnly();" /></td>
							<td colspan="2" width="40%">
						</tr>
						<tr>
							<td colspan="1" width="30%"><label class="set"
								id="taxIncomeLimitLbl" name="taxIncomeLimitLbl"
								ondblclick="callShowDiv(this)"> <%=label.get("taxIncomeLimitLbl")%></label>:<font
								color="red">*</font></td>
							<td colspan="1" width="30%"><s:textfield
								name="taxIncomeLimit" size="35"
								onkeypress="return numbersOnly();" /></td>
							<td colspan="2" width="40%">
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="20%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="" align="right">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id='topButtonTable'>
						<tr valign="middle">
							<td width="100%"><%@ include
								file="/pages/common/reportButtonPanelBottom.jsp"%>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='TDSMaster_report.action' />
</s:form>
<script type="text/javascript">

function saveFun(){
	try{
		var fieldName = ["paraFrm_fromYear","paraFrm_toYear","paraFrm_tdsDate","paraFrm_surcharge","paraFrm_eduCess","paraFrm_citizenSurLimit","paraFrm_citizenAge","paraFrm_tdsDebit","paraFrm_hraName", "paraFrm_basicName","paraFrm_provFundName","paraFrm_hraExName"];  //"paraFrm_signAuthName"
		var lableName = ["fromyear","toyear","taxation.TdsEffDate","taxation.TDSSurcharge","taxation.EduCess","taxation.SurChargeLmt","taxation.SrCitAge","taxation.TDSDebtType","taxation.HRACreditName","taxation.BasicCreditName","taxation.EmpProvFund","taxation.HRAExemption"]; //,"taxation.SignAuth" ,
		var badflag = ["enter","enter","enter","enter","enter","enter","enter","select","select","select","select","select"];  //,"select"
		var surCharge=document.getElementById('taxation.TDSSurcharge').innerHTML.toLowerCase();
		var eduCess=document.getElementById('taxation.EduCess').innerHTML.toLowerCase();	 
		var dt=document.getElementById('paraFrm_tdsDate').value;
		var sc=document.getElementById('paraFrm_surcharge').value;
		var ec=document.getElementById('paraFrm_eduCess').value;
		var cessTax=document.getElementById('paraFrm_eduCess').value;
		var surchargeTax=document.getElementById('paraFrm_surcharge').value;
		var frmGrossAmt =document.getElementById('paraFrm_donationFormPercent').value;		    
		var taxRebateAmt= document.getElementById('paraFrm_taxRebateAmount').value;
		var taxRebateInLimit= document.getElementById('paraFrm_taxIncomeLimit').value;
		 
		if(!validateBlank(fieldName,lableName,badflag))
			return false;
        
		if(!validateDate('paraFrm_tdsDate',"taxation.TdsEffDate"))
			return false;	   
   
		if(!checkFinancialyearRange('paraFrm_tdsDate',"taxation.TdsEffDate"))
			return false;
   
   		if(document.getElementById('paraFrm_taxRebateEnable').checked){
   			if(taxRebateAmt==""){
   				alert("Please Enter"+ document.getElementById('taxRebateAmountLbl').innerHTML.toLowerCase());
   				return false;
   			}
   			if(taxRebateInLimit==""){
   				alert("Please Enter"+ document.getElementById('taxIncomeLimitLbl').innerHTML.toLowerCase());
   				return false;
   			}
   		}
		if(!(dt=="")) { 
			var count =0;
			var iChars =" ";
			for (var i = 0; i < dt.length; i++) {		
				if (iChars.indexOf(dt.charAt(i))!= -1) {
			  	 	count=count+1; 
				}
			}
			if(count==dt.length) {
				alert ("Blank Spaces Not Allowed");
				return false;	
			}
		}
		if(!(sc=="")) {
			var count =0;
			var iChars =" ";
			for (var i = 0; i < sc.length; i++) {		
				if (iChars.indexOf(sc.charAt(i))!= -1) {
					count=count+1; 
				}
			}
			if(count==sc.length){
				alert ("Blank Spaces Not Allowed in "+surCharge);
				return false;	
			}		
		}
		if(!(ec=="")) {
			var count =0;
			var iChars =" ";
			for (var i = 0; i < ec.length; i++) {		
				if (iChars.indexOf(ec.charAt(i))!= -1) {
					count=count+1; 
				}
			}
			if(count==ec.length) {
				alert ("Blank Spaces Not Allowed in "+eduCess);
				return false;	
			}
		}   
    	/* var amount=document.getElementById(ctcfieldname).value;
		var amountlabel=document.getElementById(ctclabelname).innerHTML.toLowerCase();			
		if(trim(cessTax)!=""){
			if(isNaN(cessTax)) { 
	 			alert("Only numbers are allowed in "+amountlabel+" field.");
	 			document.getElementById(ctcfieldname).focus();
		  		return false;		 
		 	}	
		 }*/     	 
		if(trim(cessTax)!="") {
			if(isNaN(cessTax)) { 
				alert("Invalid number value.");
				document.getElementById('paraFrm_eduCess').value="0";
				document.getElementById('paraFrm_eduCess').focus();
				return false;
			}	
     	}
		if(trim(surchargeTax)!="") {
			if(isNaN(surchargeTax)) { 
				  alert("Invalid number value.");
				 document.getElementById('paraFrm_surcharge').value="0";
				 document.getElementById('paraFrm_surcharge').focus();
				  return false;
				 
				 }	
		}
		if(trim(frmGrossAmt)!=""){
			if(isNaN(frmGrossAmt)) { 
				  alert("Invalid number value.");
				 document.getElementById('paraFrm_donationFormPercent').value="0";
				 document.getElementById('paraFrm_donationFormPercent').focus();
				  return false;
				 
				 }	
		}
		var citizensurlimit=document.getElementById('paraFrm_citizenSurLimit').value;
 		var myrebatelimit=document.getElementById('paraFrm_rebateLimit').value;
		if(trim(citizensurlimit)!=""){
			if(isNaN(citizensurlimit)) { 
				 alert("Invalid number value.");
				 document.getElementById('paraFrm_citizenSurLimit').value="0";
				 document.getElementById('paraFrm_citizenSurLimit').focus();
				 return false;				 
				 }	
     	 }
		 if(trim(myrebatelimit)!="")	{
			if(isNaN(myrebatelimit)) { 
				 alert("Invalid number value.");
				 document.getElementById('paraFrm_rebateLimit').value="0";
				 document.getElementById('paraFrm_rebateLimit').focus();
				 return false;				 
			}	
     	 }     	  
    	 if(eval(frmGrossAmt)>100){
    		alert("Percentage can't be greater than 100");
    		return false;
    	 } 	    
   		 if(eval(cessTax) > 100){
  			alert(eduCess+" can't be greater than 100.");
   			return false;
   	    } 
   		if(eval(surchargeTax) >100){
    		alert(surCharge+" can't be greater than 100.");
   			return false;
    	} 
    	//var  reImbursedate=document.getElementById('paraFrm_ReimbursebillDate').value;
    	var  invVerifydate=document.getElementById('paraFrm_invVerificationDate').value;    	
    	if(invVerifydate!="") { 
    		if(!validateDate('paraFrm_invVerificationDate',"InvVerifyDate"))
            	 return false;	                
            if(!checkFinancialyearRange('paraFrm_invVerificationDate',"InvVerifyDate"))
             	return false;             
         }   
         //if(reImbursedate!="") {
         //if(!validateDate('paraFrm_ReimbursebillDate',"cutoffReimburseDate"))
         //	return false;	   
         //  if(!checkFinancialyearRange('paraFrm_ReimbursebillDate',"cutoffReimburseDate"))
         //  return false;
         // }    
    	if(document.getElementById('paraFrm_accomPerqCentOwnedHigher').value != ""){
    		if(isNaN(document.getElementById('paraFrm_accomPerqCentOwnedHigher').value)){
				alert("Invalid number value.");
				document.getElementById('paraFrm_accomPerqCentOwnedHigher').value="0";
				document.getElementById('paraFrm_accomPerqCentOwnedHigher').focus();
				return false;
			}
    	}
    	if(document.getElementById('paraFrm_accomPerqCentOwnedLess').value != ""){
    		if(isNaN(document.getElementById('paraFrm_accomPerqCentOwnedLess').value)){
				alert("Invalid number value.");
				document.getElementById('paraFrm_accomPerqCentOwnedLess').value="0";
				document.getElementById('paraFrm_accomPerqCentOwnedLess').focus();
				return false;
			}
    	}
    	if(document.getElementById('paraFrm_accomPerqCentRentedHigher').value != ""){
    		if(isNaN(document.getElementById('paraFrm_accomPerqCentRentedHigher').value)){
				alert("Invalid number value.");
				document.getElementById('paraFrm_accomPerqCentRentedHigher').value="0";
				document.getElementById('paraFrm_accomPerqCentRentedHigher').focus();
				return false;
			}
    	}
    	if(document.getElementById('paraFrm_accomPerqCentRentedLess').value != ""){
    		if(isNaN(document.getElementById('paraFrm_accomPerqCentRentedLess').value)){
				alert("Invalid number value.");
				document.getElementById('paraFrm_accomPerqCentRentedLess').value="0";
				document.getElementById('paraFrm_accomPerqCentRentedLess').focus();
				return false;
			}
    	}    	
    	if(document.getElementById('paraFrm_govLoanRate').value != ""){
    		if(isNaN(document.getElementById('paraFrm_govLoanRate').value)){
				alert("Invalid number value.");
				document.getElementById('paraFrm_govLoanRate').value="0";
				document.getElementById('paraFrm_govLoanRate').focus();
				return false;
			}
    	}    	
   	  	document.getElementById('paraFrm').action = "TDSMaster_save.action";
		document.getElementById('paraFrm').submit();       	 
	} catch(e){
    	alert(e);
    }	
  }

function callPage(id){
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="TDSMaster_callPage.action";
	    document.getElementById('paraFrm').submit();
 }
   
   
function next(){
    var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1"){	
   		document.getElementById('myPage').value=2;
	    document.getElementById('paraFrm_show').value=2;
	 }else{
		 document.getElementById('myPage').value=eval(pageno)+1;
		 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	 }
	   document.getElementById('paraFrm').action="TDSMaster_callPage.action";
	   document.getElementById('paraFrm').submit();
  }
  	
  	
  	
  	//-----function for previous
function previous(){
   var pageno=	document.getElementById('myPage').value;   	
   document.getElementById('myPage').value=eval(pageno)-1;
   document.getElementById('paraFrm_show').value=eval(pageno)-1;
   document.getElementById('paraFrm').action="TDSMaster_callPage.action";
   document.getElementById('paraFrm').submit();
  }
   
function on(){
  		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
	 	document.getElementById('myPage').value=eval(val);
	 	document.getElementById('selectname').value=val;
	    document.getElementById('paraFrm').action="TDSMaster_callPage.action";	   
	   	document.getElementById('paraFrm').submit();
 }
   	
pgshow();

function pgshow(){
  	var pgno=document.getElementById('paraFrm_show').value;  
  	if(!(pgno==""))
  	 	document.getElementById('selectname').value=pgno;
 }



function callForEdit(id){	
	  	document.getElementById('paraFrm_hiddenCode').value=id;
	   	document.getElementById("paraFrm").action="TDSMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
  }
  
function callForDelete(id){
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="PFParameter_calfordelete.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
 }
   


function callForDelete1(id,i){
	if(document.getElementById('confChk'+i).checked == true){	  
	    document.getElementById('hdeleteCode'+i).value=id;
	}else
	   document.getElementById('hdeleteCode'+i).value="";
  }
   
function newRowColor(cell){
			cell.className='onOverCell';
 }
	
function oldRowColor(cell,val) {	
	if(val=='1'){
	 	cell.className='tableCell2';
	}else 
		cell.className='tableCell1';		
 }
	  	 
function callUpdate(){

	var fieldName = ["paraFrm_fromYear","paraFrm_toYear","paraFrm_tdsDate","paraFrm_surcharge","paraFrm_eduCess","paraFrm_citizenSurLimit","paraFrm_citizenAge","paraFrm_tdsDebit","paraFrm_hraName", "paraFrm_basicName","paraFrm_provFundName","paraFrm_hraExName"];  //"paraFrm_signAuthName"
	var lableName = ["fromyear","toyear","taxation.TdsEffDate","taxation.TDSSurcharge","taxation.EduCess","taxation.SurChargeLmt","taxation.SrCitAge","taxation.TDSDebtType","taxation.HRACreditName","taxation.BasicCreditName","taxation.EmpProvFund","taxation.HRAExemption"]; //,"taxation.SignAuth" ,
	var badflag = ["enter","enter","enter","enter","enter","enter","enter","select","select","select","select","select"];  //,"select"
  	var surCharge=document.getElementById('taxation.TDSSurcharge').innerHTML.toLowerCase();
  	var eduCess=document.getElementById('taxation.EduCess').innerHTML.toLowerCase();	 
  	var dt=document.getElementById('paraFrm_tdsDate').value;
	var sc=document.getElementById('paraFrm_surcharge').value;
	var ec=document.getElementById('paraFrm_eduCess').value;
	var cessTax=document.getElementById('paraFrm_eduCess').value;
	var surchargeTax=document.getElementById('paraFrm_surcharge').value;	
	if(document.getElementById('paraFrm_tdsCode').value==""){
 			alert("Please select the record to update ");
 			return false;
 	}else { 			 
        if(!validateBlank(fieldName,lableName,badflag))
        	return false;        
       if(!validateDate('paraFrm_tdsDate',"taxation.TdsEffDate"))
             return false;	               
       if(!checkFinancialyearRange('paraFrm_tdsDate',"taxation.TdsEffDate"))
             return false;
 	}
	if(!(dt=="")){ 					
		var count =0;
		var iChars =" ";
		for (var i = 0; i < dt.length; i++){				  			 
			 if (iChars.indexOf(dt.charAt(i))!= -1){
			  	 	count=count+1; 				  	
  			 }
  		}
  		if(count==dt.length){
  			alert ("Blank Spaces Not Allowed");
  			return false;	
  		}
	}if(!(sc=="")) {	
		var count =0;
		var iChars =" ";
		for (var i = 0; i < sc.length; i++){				  			 
			if (iChars.indexOf(sc.charAt(i))!= -1){
			  	 count=count+1; 				  	
  		    }
  		}
  		if(count==sc.length){
  			alert ("Blank Spaces Not Allowed in "+surCharge);
  			return false;	
  		}
	 }if(!(ec=="")) {	
		    var count =0;
			var iChars =" ";
		  	for (var i = 0; i < ec.length; i++){				  			 
			  if (iChars.indexOf(ec.charAt(i))!= -1){
			  	 	count=count+1; 
  		 	  }
  			}
  			if(count==ec.length){
  				alert ("Blank Spaces Not Allowed in "+eduCess);
  				return false;	
  			}		
	 }if(trim(cessTax)!=""){
		if(isNaN(cessTax)) { 
			 alert("Invalid number value.");
		 	 document.getElementById('paraFrm_eduCess').value="0";
		 	 document.getElementById('paraFrm_eduCess').focus();
		  	 return false;
		 }	
     }if(trim(surchargeTax)!=""){
			if(isNaN(surchargeTax)) { 
				  alert("Invalid number value.");
				 document.getElementById('paraFrm_surcharge').value="0";
				 document.getElementById('paraFrm_surcharge').focus();
				  return false;				 
				 }	
     }
     var citizensurlimit=document.getElementById('paraFrm_citizenSurLimit').value;
	 var myrebatelimit=document.getElementById('paraFrm_rebateLimit').value;
	 if(trim(citizensurlimit)!=""){
			if(isNaN(citizensurlimit)) { 
				  alert("Invalid number value.");
     			  document.getElementById('paraFrm_citizenSurLimit').value="0";
				  document.getElementById('paraFrm_citizenSurLimit').focus();
				  return false;
			}	
     }
	 if(trim(myrebatelimit)!=""){
			if(isNaN(myrebatelimit)) { 
				 alert("Invalid number value.");
				 document.getElementById('paraFrm_rebateLimit').value="0";
				 document.getElementById('paraFrm_rebateLimit').focus();
				 return false;				
			}	
     }	        	    
   	if(eval(cessTax) > 100){
  		alert(eduCess+" can't be greater than 100.");
   		return false;
   	} 
   	if(eval(surchargeTax) >100){
    		alert(surCharge+" can't be greater than 100.");
   			return false;
    }     
    //var  reImbursedate=document.getElementById('paraFrm_ReimbursebillDate').value;
    var  invVerifydate=document.getElementById('paraFrm_invVerificationDate').value;
    if(invVerifydate!=""){ 
    	if(!validateDate('paraFrm_invVerificationDate',"InvVerifyDate"))
             return false;	           
             if(!checkFinancialyearRange('paraFrm_invVerificationDate',"InvVerifyDate"))
             	return false;
	}   
         //if(reImbursedate!="") {
         //if(!validateDate('paraFrm_ReimbursebillDate',"cutoffReimburseDate"))
         // return false;	   
         //  if(!checkFinancialyearRange('paraFrm_ReimbursebillDate',"cutoffReimburseDate"))
         // return false;
         // }    	
   	 return true;
 }
	  
function add(){
   try{
   	 var from = document.getElementById('paraFrm_fromYear').value;    
    if(from==""){
    	 document.getElementById('paraFrm_toYear').value="";
    	 document.getElementById('paraFrm_toYear').focus();
    }
    else{
   	 	var x=eval(from) +1;
	 	 document.getElementById('paraFrm_toYear').value=x;
	}
   }catch(e){
   }
  }
	
	  
	
	
function checkFinancialyearRange(fieldName,fromLabName){	
	try{
		var enteredDate = document.getElementById(fieldName).value;
		var strDate   = enteredDate.split("-"); 
		var starttime = new Date(strDate[2],strDate[1]-1,strDate[0]); 	
		var financialyear=document.getElementById('paraFrm_fromYear').value;	
		// from date 31-03-fromyear  
		// end date 01-04-toyear
		var todayDate = new Date();
		var day       =01;  //todayDate.getDate();
		var month	  =03;    // todayDate.getMonth();
		var dupday       =31;  //todayDate.getDate();
		var dupmonth	  =02;    // todayDate.getMonth();
		var year 	  = eval(financialyear);
		var dupyear 	  = eval(financialyear)+1;
		endtime 	  = new Date(year,month,day); 
		endtime1 	  = new Date(dupyear,dupmonth,dupday); 
		//alert("endtime "+endtime);
		//alert("starttime "+starttime);	
		if((eval(starttime) < eval(endtime)) || (eval(starttime) > eval(endtime1))) { 
			//alert("first condition...!!");
			alert(""+document.getElementById(fromLabName).innerHTML.toLowerCase()+" should fall between the financial Year.");
			//document.getElementById(fieldName).focus();
			return false;
	    }	
		/*alert("after first condition...");
		if(eval(starttime) > eval(endtime1)) { 
			alert("second condition...!!");
			alert(""+document.getElementById(fromLabName).innerHTML.toLowerCase()+" should fall between the financial Year.");
			//document.getElementById(fieldName).focus();
			return false;
		}
		alert("after second condition...");*/	
	}
	catch(e){
		//alert(e);
	}
	return true;
 }
	
function format(input){	
	try{	
		var num = input.value.replace(/\,/g,'');
		if(!isNaN(num)){
			if(num.indexOf('.') > -1){
				num = num.split('.');
				num[0] = num[0].toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split('').reverse().join('').replace(/^[\,]/,'');
				if(num[1].length > 2){
					alert('You may only enter two decimals!');
					num[1] = num[1].substring(0,num[1].length-1);
				} 
				input.value = num[0]+'.'+num[1];
			 } 
			 else{ input.value = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1,').split(		
				'').reverse().join('').replace(/^[\,]/,'')		
		 	};
		 }
		 else{ alert('You may enter only numbers in this field!');
			input.value = input.value.substring(0,input.value.length-1);
		 }
	}
	catch(e){
		//alert(e);
	}
  }

function datePeriodFrom(){
   try{
  		var frmPeriod=document.getElementById('paraFrm_monthInvestmentDecPeriodFromDate').value;
  		if(frmPeriod!=''){
   			if (frmPeriod==0 || (frmPeriod) > 31 ) {
    			 alert("From Date Period Must be Between 1 to 31\n");
    			document.getElementById('paraFrm_monthInvestmentDecPeriodFromDate').focus();
    			document.getElementById('paraFrm_monthInvestmentDecPeriodFromDate').value="";
		 		return false;
			}			
		}
	} catch(e) {
		alert(e);
    }
  }	

function datePeriodTo(){
   try{
	  var fromPeriod = document.getElementById('paraFrm_monthInvestmentDecPeriodFromDate').value;
	  var toPeriod = document.getElementById('paraFrm_monthInvestmentDecPeriodToDate').value;
		if(toPeriod !=''){
   			if (toPeriod==0 || toPeriod > 31 ) {
    			 alert("To Date Period Must be Between 1 to 31\n");
    			document.getElementById('paraFrm_monthInvestmentDecPeriodToDate').focus();
    			document.getElementById('paraFrm_monthInvestmentDecPeriodToDate').value="";
		 		return false;
			 }			
			if(fromPeriod > (toPeriod)){
			  	alert("To Period Must Greater Than From Period");
			 	document.getElementById('paraFrm_monthInvestmentDecPeriodToDate').focus();
    			document.getElementById('paraFrm_monthInvestmentDecPeriodToDate').value="";
				return false;
			}
		}
	} catch(e) {
		//alert(e);
	}			
 }	
	 
function editFun() {	
		return true;
 }	
	
function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TDSMaster_delete.action';
		document.getElementById('paraFrm').submit();
	 }
 }
	
function backFun() {	
  	 	document.getElementById('paraFrm').action = "TDSMaster_back.action";
		document.getElementById('paraFrm').submit();
 }
  	
function reportFun() {	
  	 	document.getElementById('paraFrm').action = "TDSMaster_report.action";
		document.getElementById('paraFrm').submit();
 }
  	
function resetFun() {	
  	 	document.getElementById('paraFrm').action = "TDSMaster_reset.action";
		document.getElementById('paraFrm').submit();
 }

function callReport(type){
	var fieldName = ["paraFrm_fromYear","paraFrm_toYear"];
	var lableName = ["fromyear","toyear"];
	var badflag = ["enter","enter"];
	if(!validateBlank(fieldName,lableName,badflag))
		return false;
	else {
		document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type);
	}
 }

function mailReportFun(type){
	var fieldName = ["paraFrm_fromYear","paraFrm_toYear"];
	var lableName = ["fromyear","toyear"];
	var badflag = ["enter","enter"];
	if(!validateBlank(fieldName,lableName,badflag))
		return false;
	else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='TDSMaster_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
 }  
   
function alertsize(pixels){ 
	pixels+=30; 
	//alert('before'+ document.getElementById('myframe').style.height); 
	document.getElementById('reportFrame').style.height=pixels+"px"; 
	//alert('after'+ document.getElementById('myframe').style.height); 
	document.body.style.offsetHeight="0px"; 
 }

function checkLock(){
	if(document.getElementById('paraFrm_lockFlag').checked){
		document.getElementById('paraFrm_lockFlag').value = "true";
	} else {
		document.getElementById('paraFrm_lockFlag').value = "false";
	}
}
function checkRebateTax(){
	if(document.getElementById('paraFrm_taxRebateEnable').checked){
		document.getElementById('paraFrm_taxRebateEnable').value = "true";
	} else {
		document.getElementById('paraFrm_taxRebateEnable').value = "false";
	}
 }
checkRebateTax();
checkLock();

</script>