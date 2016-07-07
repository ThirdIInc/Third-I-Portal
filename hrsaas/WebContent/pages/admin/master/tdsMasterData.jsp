<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="TDSMaster" method="post" id="paraFrm" validate="true"
	target="main" theme="simple">
	<table width="100%" class="formbg">
		<tbody>
			<tr>
				<td width="100%" colspan="3">
				<table width="100%" cellspacing="0" cellpadding="0" border="0"
					align="center" class="formbg">
					<tbody>
						<tr>
							<td width="4%" valign="bottom" class="txt"><strong
								class="formhead"> <img height="25" width="25"
								src="../pages/images/recruitment/review_shared.gif"></strong></td>
							<td width="93%" class="txt"><strong class="text_head">TDS
							Parameter</strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"><img height="16" width="16"
								src="../pages/images/recruitment/help.gif"></div>
							</td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>

			<tr>
				<td width="100%" colspan="3">


				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<td width="70%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td>
							<td>
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%" cellspacing="0" cellpadding="0" border="0"
					class="formbg">
					<tbody>
						<tr>
							<td>
							<table width="98%" cellspacing="2" cellpadding="0" border="0">


								<tbody>
									<tr>

										<td width="22%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="fromyear" id="fromyear"
											class="set">Financial Year From</label>:<font color="red">*</font></td>
										<td width="30%" colspan="1"><input type="text"
											onkeypress="return numbersOnly();" onblur="add()"
											id="paraFrm_fromYear" value="" maxlength="4" size="25"
											name="fromYear"></td>

										<td width="20%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="toyear" id="toyear"
											class="set">Financial Year To</label> :<font color="red">*</font></td>
										<td width="30%" colspan="1"><input type="text"
											id="paraFrm_toYear" readonly="readonly" value=""
											maxlength="4" size="25" name="toYear"></td>


									</tr>

									<tr>
										<input type="hidden" id="paraFrm_tdsCode" value=""
											name="tdsCode">

										<td width="22%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="taxation.TdsEffDate"
											id="taxation.TdsEffDate" class="set">TDS Effective
										Date</label>:<font color="red">*</font></td>
										<td width="30%" colspan="1"><input type="text"
											onkeypress="return numbersWithHiphen();" id="paraFrm_tdsDate"
											value="" maxlength="10" size="25" name="tdsDate"> <a
											href="javascript:NewCal('paraFrm_tdsDate','DDMMYYYY');"
											id="paraFrm_"> <img height="16" width="16" border="0"
											align="absmiddle" src="../pages/images/recruitment/Date.gif"
											class="iconImage"> </a></td>
										<td width="20%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="taxation.TDSSurcharge"
											id="taxation.TDSSurcharge" class="set">TDS Surcharge
										%</label>:<font color="red">*</font></td>
										<td width="30%" colspan="1"><input type="text"
											onkeypress="return numbersWithDot();" id="paraFrm_surcharge"
											value="" maxlength="6" size="25" name="surcharge"></td>
									</tr>

									<tr>
										<td width="22%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="taxation.EduCess"
											id="taxation.EduCess" class="set">Education Cess</label>:<font
											color="red">*</font></td>
										<td width="30%" colspan="1"><input type="text"
											onkeypress="return numbersWithDot();" id="paraFrm_eduCess"
											value="" maxlength="6" size="25" name="eduCess"></td>


										<td width="20%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="taxation.SurChargeLmt"
											id="taxation.SurChargeLmt" class="set">Surcharge
										Limit</label><font color="red">*</font>:</td>
										<td width="30%" colspan="1"><input type="text"
											onkeypress="return numbersWithDot();"
											id="paraFrm_citizenSurLimit" value="" maxlength="15"
											size="25" name="citizenSurLimit"></td>

									</tr>
									<tr>
										<td width="22%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="taxation.SrCitAge"
											id="taxation.SrCitAge" class="set">Sr. Citizen Age</label>:<font
											color="red">*</font></td>
										<td width="30%" colspan="1"><input type="text"
											onkeypress="return numbersOnly();" id="paraFrm_citizenAge"
											value="" maxlength="2" size="25" name="citizenAge"></td>

										<td width="20%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="taxation.TDSDebtType"
											id="taxation.TDSDebtType" class="set">TDS Debit Type</label>:<font
											color="red">*</font></td>
										<td width="30%" colspan="1"><input type="hidden"
											id="paraFrm_tdsDebitCode" value="" name="tdsDebitCode">
										<input type="text" id="paraFrm_tdsDebit" readonly="readonly"
											value="" size="25" name="tdsDebit"> <img height="15"
											width="16" align="absmiddle"
											onclick="javascript:callsF9(500,325,'TDSMaster_f9debit.action');"
											class="iconImage"
											src="../pages/images/recruitment/search2.gif"></td>

									</tr>

									<tr>
										<td width="22%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="taxation.HRACreditName"
											id="taxation.HRACreditName" class="set">HRA Credit
										Name</label>:<font color="red">*</font></td>
										<td width="30%" colspan="1"><input type="hidden"
											id="paraFrm_hraCode" value="" name="hraCode"> <input
											type="text" id="paraFrm_hraName" readonly="readonly" value=""
											maxlength="20" size="25" name="hraName"> <img
											height="15" width="16" align="absmiddle"
											onclick="javascript:callsF9(500,325,'TDSMaster_f9hra.action');"
											class="iconImage"
											src="../pages/images/recruitment/search2.gif"></td>
										<td width="20%" colspan="1"><label
											ondblclick="callShowDiv(this);"
											name="taxation.BasicCreditName" id="taxation.BasicCreditName"
											class="set">Basic Credit Name</label>:<font color="red">*</font></td>
										<td width="30%" colspan="1"><input type="hidden"
											id="paraFrm_basicCode" value="" name="basicCode"> <input
											type="text" id="paraFrm_basicName" readonly="readonly"
											value="" size="25" name="basicName"> <img height="15"
											width="16" align="absmiddle"
											onclick="javascript:callsF9(500,325,'TDSMaster_f9basic.action');"
											class="iconImage"
											src="../pages/images/recruitment/search2.gif"></td>
									</tr>
									<tr>
										<td width="22%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="taxation.DACreditName"
											id="taxation.DACreditName" class="set">DA Credit Name</label>:</td>
										<td width="30%" colspan="1"><input type="hidden"
											id="paraFrm_daCode" value="" name="daCode"> <input
											type="text" id="paraFrm_daName" readonly="readonly" value=""
											size="25" name="daName"> <img height="15" width="16"
											align="absmiddle"
											onclick="javascript:callsF9(500,325,'TDSMaster_f9da.action');"
											class="iconImage"
											src="../pages/images/recruitment/search2.gif"></td>

										<td width="20%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="taxation.EmpProvFund"
											id="taxation.EmpProvFund" class="set">Emp Provident
										Fund</label>:<font color="red">*</font></td>
										<td width="30%" colspan="1"><input type="hidden"
											id="paraFrm_provFundCode" value="" name="provFundCode">

										<input type="text" id="paraFrm_provFundName"
											readonly="readonly" value="" size="25" name="provFundName">
										<img height="15" width="16" align="absmiddle"
											onclick="javascript:callsF9(500,325,'TDSMaster_f9provident.action');"
											class="iconImage"
											src="../pages/images/recruitment/search2.gif"></td>


									</tr>

									<tr>
										<td width="22%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="taxation.HRAExemption"
											id="taxation.HRAExemption" class="set">HRA Exemption</label>:<font
											color="red">*</font></td>
										<td width="30%" colspan="1"><input type="hidden"
											id="paraFrm_hraExCode" value="" name="hraExCode"> <input
											type="text" id="paraFrm_hraExName" readonly="readonly"
											value="" size="25" name="hraExName"> <img height="15"
											width="16" align="absmiddle"
											onclick="javascript:callsF9(500,325,'TDSMaster_f9hraEx.action');"
											class="iconImage"
											src="../pages/images/recruitment/search2.gif"></td>

										<td width="20%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="taxation.RebateInvLmt"
											id="taxation.RebateInvLmt" class="set">Rebate Inv
										Limit </label>:</td>
										<td width="30%" colspan="1"><input type="text"
											onkeypress="return numbersWithDot();"
											id="paraFrm_rebateLimit" value="" maxlength="15" size="25"
											name="rebateLimit"></td>

									</tr>



									<tr>
										<td width="22%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="InvVerifyDate"
											id="InvVerifyDate" class="set">Investment
										Verification Date</label> <!--  <font color="red">*</font>--> :</td>
										<td width="30%" colspan="1"><input type="text"
											onkeypress="return numbersWithHiphen();"
											id="paraFrm_invVerificationDate" value="" maxlength="10"
											size="25" name="invVerificationDate"> <a
											href="javascript:NewCal('paraFrm_invVerificationDate','DDMMYYYY');"
											id="paraFrm_"> <img height="18" width="18"
											align="absmiddle" class="iconImage"
											src="../pages/images/recruitment/Date.gif"> </a></td>

										<td width="20%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="cutoffReimburseDate"
											id="cutoffReimburseDate" class="set">Cutoff Date for
										Reimbursement-Bills</label>:</td>
										<td width="30%" colspan="1"><input type="text"
											onkeypress="return numbersWithHiphen();"
											id="paraFrm_ReimbursebillDate" value="" maxlength="10"
											size="25" name="ReimbursebillDate"> <a
											href="javascript:NewCal('paraFrm_ReimbursebillDate','DDMMYYYY');"
											id="paraFrm_"> <img height="18" width="18"
											align="absmiddle" class="iconImage"
											src="../pages/images/recruitment/Date.gif"> </a></td>

									</tr>
									<tr>
										<td width="22%" colspan="1"><label
											ondblclick="callShowDiv(this);" name="taxation.SignAuth"
											id="taxation.SignAuth" class="set">Signing Authority</label>
										:</td>
										<!-- <font color="red">*</font> -->
										<td width="30%" colspan="3"><input type="hidden"
											id="paraFrm_signAuthId" value="" name="signAuthId"> <input
											type="hidden" id="paraFrm_token" value="" name="token">
										<input type="text" id="paraFrm_signAuthName"
											readonly="readonly" value="" size="25" name="signAuthName">
										<img height="15" width="16" align="absmiddle"
											onclick="javascript:callsF9(500,325,'TDSMaster_f9signAuth.action');"
											class="iconImage"
											src="../pages/images/recruitment/search2.gif"></td>
									</tr>
									<tr>
										<td></td>
									</tr>
								</tbody>
							</table>
							</td>
						</tr>

					</tbody>
				</table>

				</td>
			</tr>


			<tr>
				<td width="100%" colspan="6">

				<table width="100%" cellspacing="0" cellpadding="0" border="0"
					class="formbg">
					<tbody>
						<tr>
							<td class="formhead" colspan="6"><strong
								class="forminnerhead">HRA Exceptions</strong></td>
						</tr>

						<tr>
							<td nowrap="nowrap" width="15%" colspan="1"><label
								ondblclick="callShowDiv(this);" name="taxation.HRAPaidCond1"
								id="taxation.HRAPaidCond1" class="set">HRA Paid
							Condition 1(Non Metro)</label>:</td>
							<td width="15%" colspan="1"><input type="text"
								id="paraFrm_salAmt" readonly="readonly" value="" size="27"
								name="salAmt"></td>
							<td width="15%" colspan="1"><input type="button"
								onclick="callFormulaBuilder('paraFrm_salAmt');"
								id="paraFrm_formCalc" value="Calculator" name="formCalc"
								class="token"></td>

							<td nowrap="nowrap" width="20%" colspan="1"><label
								ondblclick="callShowDiv(this);" name="taxation.HRAUnpaidCond1"
								id="taxation.HRAUnpaidCond1" class="set">HRA Unpaid
							Condition 1</label>:</td>
							<td width="15%" colspan="1"><input type="text"
								id="paraFrm_salAmt4" readonly="readonly" value="" size="27"
								name="salAmt4"></td>
							<td width="15%" colspan="1"><input type="button"
								onclick="callFormulaBuilder('paraFrm_salAmt4');"
								id="paraFrm_formCalc4" value="Calculator" name="formCalc4"
								class="token"></td>

						</tr>

						<tr>
							<td nowrap="nowrap" width="20%" colspan="1"><label
								ondblclick="callShowDiv(this);" name="taxation.HRAPaidCond2"
								id="taxation.HRAPaidCond2" class="set">HRA Paid
							Condition 2</label>:</td>
							<td width="15%" colspan="1"><input type="text"
								id="paraFrm_salAmt2" readonly="readonly" value="" size="27"
								name="salAmt2"></td>
							<td width="15%" colspan="1"><input type="button"
								onclick="callFormulaBuilder('paraFrm_salAmt2');"
								id="paraFrm_formCalc2" value="Calculator" name="formCalc2"
								class="token"></td>
							<td nowrap="nowrap" width="20%" colspan="1"><label
								ondblclick="callShowDiv(this);" name="taxation.HRAUnpaidCond2"
								id="taxation.HRAUnpaidCond2" class="set">HRA Unpaid
							Condition 2</label>:</td>
							<td width="15%" colspan="1"><input type="text"
								id="paraFrm_salAmt5" readonly="readonly" value="" size="13"
								name="salAmt5"> % of Income</td>
							<td width="15%" colspan="1"><input type="button"
								onclick="callFormulaBuilder('paraFrm_salAmt5');"
								id="paraFrm_formCalc5" value="Calculator" name="formCalc5"
								class="token"></td>
						</tr>
						<tr>
							<td nowrap="nowrap" width="20%" colspan="1"><label
								ondblclick="callShowDiv(this);" name="taxation.HRAPaidCond3"
								id="taxation.HRAPaidCond3" class="set">HRA Paid
							Condition 3</label>:</td>
							<td nowrap="nowrap" width="10%" colspan="1"><label
								ondblclick="callShowDiv(this);" name="taxation.Rent"
								id="taxation.Rent" class="set">Rent</label> <input type="text"
								id="paraFrm_salAmt3" readonly="readonly" value="" size="21"
								name="salAmt3"></td>
							<td width="10%" colspan="1"><input type="button"
								onclick="callFormulaBuilder('paraFrm_salAmt3');"
								id="paraFrm_formCalc3" value="Calculator" name="formCalc3"
								class="token"></td>
							<td nowrap="nowrap" width="15%" colspan="1"><label
								ondblclick="callShowDiv(this);" name="taxation.HRAUnpaidCond3"
								id="taxation.HRAUnpaidCond3" class="set">HRA Unpaid
							Condition 3</label>:</td>
							<td nowrap="nowrap" width="50%" colspan="1"><label
								ondblclick="callShowDiv(this);" name="taxation.Rent"
								id="taxation.Rent1" class="set">Rent</label> <input type="text"
								id="paraFrm_salAmt6" readonly="readonly" value="" size="8"
								name="salAmt6"> % of Income</td>
							<td width="25%" colspan="1"><input type="button"
								onclick="callFormulaBuilder('paraFrm_salAmt6');"
								id="paraFrm_formCalc6" value="Calculator" name="formCalc6"
								class="token"></td>
						</tr>
						<tr>
							<td nowrap="nowrap" width="20%" colspan="1"><label
								ondblclick="callShowDiv(this);" name="taxation.HRAPaidCondMetro"
								id="taxation.HRAPaidCondMetro" class="set">HRA Paid
							Condition 1(Metro)</label>:</td>
							<td width="15%" colspan="1"><input type="text"
								id="paraFrm_hraPaidMetro" readonly="readonly" value="" size="27"
								name="hraPaidMetro"></td>
							<td width="15%" colspan="1"><input type="button"
								onclick="callFormulaBuilder('paraFrm_hraPaidMetro');"
								id="paraFrm_formCalc7" value="Calculator" name="formCalc7"
								class="token"></td>

						</tr>
					</tbody>
				</table>
				</td>
			</tr>


			<tr>
				<td width="100%" class="formhead" colspan="4"><b>Leave
				Encashment Exemption:</b></td>
			</tr>
			<tr>
				<td width="100%" colspan="4">

				<table width="100%" cellspacing="2" cellpadding="2" border="0"
					class="formbg">
					<tbody>
						<tr>
							<td class="formhead" colspan="3"><strong
								class="forminnerhead">Least of the following is Exempt</strong></td>
						</tr>

						<tr>
							<td width="10%" colspan="1"><strong>Condition 1:</strong></td>
							<td width="40%" colspan="1"><label
								ondblclick="callShowDiv(this);" name="leaveEncash1"
								id="leaveEncash1" class="set">Encashment Amount Limit</label>:</td>
							<td width="40%" colspan="1"><input type="text"
								onkeypress="return numbersOnly();" id="paraFrm_leaveEncashamt"
								value="" maxlength="10" size="15" name="leaveEncashamt">
							</td>
							<td width="10%" colspan="1">&nbsp;</td>

						</tr>

						<tr>
							<td><strong>Condition 2:</strong></td>
							<td width="30%" colspan="2"><label
								ondblclick="callShowDiv(this);" name="leaveEncash2"
								id="leaveEncash2" class="set">Amount Of Leave Encashment
							Actually Received</label></td>

						</tr>
						<tr>
							<td><strong>Condition 3:</strong></td>
							<td width="30%" colspan="1"><label
								ondblclick="callShowDiv(this);" name="leaveEncash4"
								id="leaveEncash4" class="set">No.of Leaves X</label> <input
								type="text" onkeypress="return numbersOnly();"
								id="paraFrm_leaveEncashMonths" value="" maxlength="2" size="5"
								name="leaveEncashMonths"> <label
								ondblclick="callShowDiv(this);" name="leaveEncash3"
								id="leaveEncash3" class="set">Month Average Salary With
							Components formula </label></td>
							<td width="20%" colspan="1"><input type="text"
								id="paraFrm_leaveEncashFormula" readonly="readonly" value=""
								size="12" name="leaveEncashFormula"> <input
								type="button"
								onclick="callFormulaBuilder('paraFrm_leaveEncashFormula');"
								id="paraFrm_EncashFormula" value="Calculator"
								name="EncashFormula" class="token"></td>

						</tr>
						<tr>
							<td width="30%" colspan="1"><label
								ondblclick="callShowDiv(this);" name="leaveEncash5"
								id="leaveEncash5" class="set">Map Taxable Leave
							Encashment Amount To</label> :</td>
							<td width="30%" colspan="1"><input type="hidden"
								id="paraFrm_leaveEncInvcode" value="" name="leaveEncInvcode">

							<input type="text" id="paraFrm_leaveEncInvName"
								readonly="readonly" value="" size="25" name="leaveEncInvName">
							<img height="15" width="16" align="absmiddle"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9LeaveEx.action');"
								class="iconImage" src="../pages/images/recruitment/search2.gif">
							</td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>

			<tr>
				<td width="100%" class="formhead" colspan="3"><b>Gratuity
				Exemption:</b></td>
			</tr>
			<tr>
				<td width="100%" colspan="3">
				<table width="100%" cellspacing="0" cellpadding="0" border="0"
					class="formbg">
					<tbody>
						<tr>
							<td width="30%" colspan="2"><label
								ondblclick="callShowDiv(this);" name="gratuityAmt"
								id="gratuityAmt" class="set">Gratuity Amount Limit</label>:</td>
							<td width="20%" colspan="1"><input type="text"
								onkeypress="return numbersOnly();" id="paraFrm_gratuityAmount"
								value="" maxlength="10" size="15" name="gratuityAmount">
							</td>
						</tr>
						<tr>
							<td width="30%" colspan="2"><label
								ondblclick="callShowDiv(this);" name="exemptUnderSect"
								id="exemptUnderSect" class="set">Exempted Under Section</label>:
							</td>
							<td width="30%" colspan="1"><input type="text"
								id="paraFrm_exemptSectionNo" readonly="readonly" value=""
								size="35" name="exemptSectionNo"> <img height="16"
								width="16"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9taxaction.action');"
								id="ctrlHide" src="../pages/images/recruitment/search2.gif">

							<input type="hidden" id="paraFrm_taxCode" value="" name="taxCode">
							</td>
						</tr>
						<tr>
							<td width="30%" colspan="2"><label
								ondblclick="callShowDiv(this);" name="gratuityCreditHead"
								id="gratuityCreditHead" class="set">Gratuity Credit Head</label>
							:</td>
							<td width="30%" colspan="1"><input type="hidden"
								id="paraFrm_creditCode" value="" name="creditCode"> <input
								type="text" id="paraFrm_creditType" readonly="readonly" value=""
								size="35" name="creditType"> <img height="15" width="16"
								align="absmiddle"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9credits.action');"
								class="iconImage"
								src="../pages/common/css/default/images/search2.gif"
								id="ctrlHide"></td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>

			<tr>
				<td width="100%" class="formhead" colspan="3"><b>LTA
				Exemption:</b></td>
			</tr>
			<tr>
				<td width="100%" colspan="3">

				<table width="100%" cellspacing="0" cellpadding="0" border="0"
					class="formbg">


					<tbody>
						<tr>
							<td width="30%" colspan="2"><label
								ondblclick="callShowDiv(this);" name="ltaAmt" id="ltaAmt"
								class="set">LTA Amount Limit</label>:</td>
							<td width="20%" colspan="1"><input type="text"
								onkeypress="return numbersOnly();" id="paraFrm_ltaAmount"
								value="" maxlength="10" size="15" name="ltaAmount"></td>

						</tr>
						<tr>
							<td width="30%" colspan="2"><label
								ondblclick="callShowDiv(this);" name="ltaexemptUnderSect"
								id="ltaexemptUnderSect" class="set">LTA Exempted Under
							Section</label>:</td>
							<td width="30%" colspan="1"><input type="text"
								id="paraFrm_ltaExemptSectionNo" readonly="readonly" value=""
								size="35" name="ltaExemptSectionNo"> <img height="16"
								width="16"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9Tdstaxaction.action');"
								id="ctrlHide" src="../pages/images/recruitment/search2.gif">

							<input type="hidden" id="paraFrm_ltaTaxCode" value=""
								name="ltaTaxCode"></td>
						</tr>
						<tr>
							<td width="30%" colspan="2"><label
								ondblclick="callShowDiv(this);" name="ltaCreditHead"
								id="ltaCreditHead" class="set">LTA Credit Head</label> :</td>
							<td width="30%" colspan="1"><input type="hidden"
								id="paraFrm_ltaCreditCode" value="" name="ltaCreditCode">

							<input type="text" id="paraFrm_ltaCreditType" readonly="readonly"
								value="" size="35" name="ltaCreditType"> <img
								height="15" width="16" align="absmiddle"
								onclick="javascript:callsF9(500,325,'TDSMaster_f9Tdscredits.action');"
								class="iconImage"
								src="../pages/common/css/default/images/search2.gif"
								id="ctrlHide"></td>
						</tr>
					</tbody>
				</table>
				</td>
			</tr>
			<tr>
				<td width="70%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td>
			</tr>
		</tbody>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript">

	function backFun() {
	
  	 	document.getElementById('paraFrm').action = "TDSMaster_back.action";
		document.getElementById('paraFrm').submit();
  	}
  	
  	function resetFun() {
	
  	 	document.getElementById('paraFrm').action = "TDSMaster_reset.action";
		document.getElementById('paraFrm').submit();
  	}

</script>