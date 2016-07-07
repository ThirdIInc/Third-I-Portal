<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="PFChallan" validate="true" id="paraFrm" theme="simple" >
	<table width="100%" class="formbg">
		<tr>
			<td colspan="4">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">PF Challan</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
      		<td>
      			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
	         		<tr>
	            		<td nowrap="nowrap">
	            			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
	  					</td>
	            		<td width="70%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
	          		</tr>
          		</table>
            </td>
    	</tr>
    	<tr>
			<td>
			<div name="htmlReport" id='reportDiv'
				style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
			<iframe id="reportFrame" frameborder="0" onload=alertsize();
				style="vertical-align: top; float: left; border: 0px solid;"
				allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
				marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
				width="100%" height="200"></iframe> </div>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" id='reportBodyTable'>
						<tr>
							<td colspan="4">
							<table width="100%" border="0" class="formbg">
								<tr>
									<td colspan="4">
									<strong class="formhead"><label
										id=selectPeriod name="selectPeriod"
										ondblclick="callShowDiv(this);"><%=label.get("selectPeriod")%></label>
									</strong></td>
								</tr>
								<tr>
		
									<td width="20%"><label id="months" name="months" ondblclick="callShowDiv(this);"><%=label.get("months")%></label> :<font color="red">*</font></td>
									<td width="30%"><s:select theme="simple"
										name="month" cssStyle="width:152"
										list="#{'0':'-- Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
									</td>
		
									<td width="20%"><label id="years" name="years" ondblclick="callShowDiv(this);"><%=label.get("years")%></label> :<font color="red">*</font></td>
									<td colspan="1" width="30%"><s:textfield name="year"
										onkeypress="return numbersOnly();" theme="simple" maxlength="4"
										size="25" /></td>
								</tr>
								<tr>
									<td width="20%"><label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red">*</font></td>
									<td colspan="3"><s:hidden name="divId" /><s:hidden name="divAdd" /><s:hidden name="divCity" /> <s:textfield
										name="divName" theme="simple" readonly="true" maxlength="50"
										size="25" /> <img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18" id='ctrlHide' 
										onclick="javascript:callsF9(500,325,'PFChallan_f9div.action');">
									</td>
								</tr>
								<tr>
								<td><label id="slect" name="slect" ondblclick="callShowDiv(this);"><%=label.get("slect")%></label>
								:</td>
								<td><s:select name="type"
									list="#{'A':'All','S':'Salary','R':'Arrears','T':'Settlement'}"
									cssStyle="width:152" theme="simple" /></td>
								<td><label id="vpfCheck" name="vpfCheck" ondblclick="callShowDiv(this);"><%=label.get("vpfCheck")%></label> :</td>
								<td><s:hidden name="hcheckVpf" />
									<input type="checkbox" name="checkVpf" <s:property value="checkVpf"/> id="checkVpf"/>
								</td>
							</tr> 
							</table>
							</td>
						</tr>
						<tr>
						<td colspan="4">
							<table border="0" width="100%" class="formBg">
							<tr>
								<td colspan="7" align="left">
									<Strong>Deducted PF</Strong>
								</td>
							</tr>
							<tr>
								<td width="20%">&nbsp;</td>
								<td align="center"><strong><label id="pfcontribution" name="pfcontribution"
									ondblclick="callShowDiv(this);"><%=label.get("pfcontribution")%></label></strong>
								</td>
								<td align="center"><strong><label id="pfadmin" name="pfadmin"
									ondblclick="callShowDiv(this);"><%=label.get("pfadmin")%></label></strong>
								</td>
								<td align="center"><strong><label id="pensionContri" name="pensionContri"
									ondblclick="callShowDiv(this);"><%=label.get("pensionContri")%></label></strong>
								</td>
								<td align="center"><strong><label id="edliContri" name="edliContri"
									ondblclick="callShowDiv(this);"><%=label.get("edliContri")%></label></strong>
								</td>
								<td align="center"><strong><label id="edliAdmin" name="edliAdmin"
									ondblclick="callShowDiv(this);"><%=label.get("edliAdmin")%></label></strong>
								</td>
								<td align="center"><strong><label id="total" name="total"
									ondblclick="callShowDiv(this);"><%=label.get("total")%></label></strong>
								</td>
							</tr>
							<tr>
								<td>
									Employer's Share 
								</td>
								<td align="center">
									<s:textfield size="10" name="employersPfContDeductedAc1" onkeypress="return numbersOnly();" onkeyup="calculateSum();"  cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="employersPensionDeductedAc10" onkeypress="return numbersOnly();" onkeyup="calculateSum();"  cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="employersEdliDeductedAc21" onkeypress="return numbersOnly();" onkeyup="calculateSum();"  cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="employersTotalDeducted" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
							</tr>
							<tr>
								<td>
									Employee's Share 
								</td>
								<td align="center">
									<s:textfield size="10" name="empPfContDeductedAc1" onkeypress="return numbersOnly();" onkeyup="calculateSum();"  cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="empTotalDeducted" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
							</tr>
							<tr>
								<td>
									Administrative Charges
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="adminPfAdminDeductedAc2" onkeypress="return numbersOnly();" onkeyup="calculateSum();"  cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="adminEdliAdminDeductedAc22" onkeypress="return numbersOnly();" onkeyup="calculateSum();"  cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="adminTotalDeducted" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
							</tr>
							<tr>
								<td>
									Inspection Charges
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="inspectionPfAdminDeductedAc2" onkeypress="return numbersOnly();" onkeyup="calculateSum();"  cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="inspectionEdliAdminDeductedAc22" onkeypress="return numbersOnly();" onkeyup="calculateSum();"  cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="inspectionTotalDeducted" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
							</tr>
							<tr>
								<td>
									Total Deducted
								</td>
								<td align="center">
									<s:textfield size="10" name="totalPfContDeductedAc1" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;" />
								</td>
								<td align="center">
									<s:textfield size="10" name="totalPfAdminDeductedAc2" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="totalPensionDeductedAc10" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="totalEdliDeductedAc21" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="totalEdliAdminDeductedAc22" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="totalDeducted" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
							</tr>
							<tr>
								<td colspan="7"><hr color="#CBC9C0" >
								</td>
							</tr>
							<tr>
								<td>
									Total No of Subscribers:
								</td>
								<td align="center">
									<s:textfield size="10" name="subscribersPfContDeductedAc1" onkeypress="return numbersOnly();" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="subscribersPensionDeductedAc10" onkeypress="return numbersOnly();" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="subscribersEdliDeductedAc21" onkeypress="return numbersOnly();" cssStyle="text-align:right;"/>
								</td>
							</tr>
							<tr>
								<td>
									Total Wages:
								</td>
								<td align="center">
									<s:textfield size="10" name="wagesPfContDeductedAc1" onkeypress="return numbersOnly();" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="wagesPensionDeductedAc10" onkeypress="return numbersOnly();" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="wagesEdliDeductedAc21" onkeypress="return numbersOnly();" cssStyle="text-align:right;"/>
								</td>
								<td align="center" colspan="2">
									&nbsp;</td>
							</tr>
							<tr>
								<td align="center" colspan="7">
									<input type="button" value=" Copy deducted to deposited " class="token" onclick="callCopyDeducted()"/>
								</td>
							</tr>
							<tr>
								<td colspan="7"><hr color="#CBC9C0">
								</td>
							</tr>
							<tr>
								<td colspan="7" align="left">
									<Strong>Deposited PF</Strong>
								</td>
							</tr>
							<tr>
								<td>
									Employer's Share 
								</td>
								<td align="center">
									<s:textfield size="10" name="employersPfContDepositedAc1" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;" />
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="employersPensionDepositedAc10" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="employersEdliDepositedAc21" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="employersTotalDeposited" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
							</tr>
							<tr>
								<td>
									Employee's Share 
								</td>
								<td align="center">
									<s:textfield size="10" name="empPfContDepositedAc1" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="empTotalDeposited" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
							</tr>
							<tr>
								<td>
									Administrative Charges
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="adminPfAdminDepositedAc2" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="adminEdliDepositedAc22" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="adminTotalDeposited" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
							</tr>
							<tr>
								<td>
									Inspection Charges
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="inspectionPfAdminDepositedAc2" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="inspectionEdliDepositedAc22" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="inspectionTotalDeposited" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
							</tr>
							<tr>
								<td>
									Penal Damage Charges
								</td>
								<td align="center">
									<s:textfield size="10" name="penalDamagesPfContDepositedAc1" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="penalDamagesPfAdminDepositedAc2" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="penalDamagesPensionDepositedAc10" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="penalDamagesEdliDepositedAc21" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="penalDamagesEdliAdminDepositedAc22" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="penalDamagesTotalDeposited" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
							</tr>
							<tr>
								<td>
									Misc Payment
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="miscPfAdminDepositedAc2" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									&nbsp;
								</td>
								<td align="center">
									<s:textfield size="10" name="miscEdliAdminDepositedAc22" onkeypress="return numbersOnly();" onkeyup="calculateSumDeposited()" cssStyle="text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="miscTotalDeposited" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
							</tr>
							<tr>
								<td>
									Total Deposited
								</td>
								<td align="center">
									<s:textfield size="10" name="totalPfContDepositedAc1" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="totalPfAdminDepositedAc2" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="totalPensionDepositedAc10" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="totalEdliDepositedAc21" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="totalEdliAdminDepositedAc22" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
								<td align="center">
									<s:textfield size="10" name="totalDeposited" readonly="true" cssStyle="background-color: #F2F2F2;text-align:right;"/>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
						<table width="100%" border="0" class="formbg">
							<tr>
								<td colspan="4">
								<strong class="formhead"><label
									id=paymentDetails name="paymentDetails"
									ondblclick="callShowDiv(this);"><%=label.get("paymentDetails")%></label>
								</strong></td>
							</tr>
							<tr>
								<td><label id="slect" name="slect" ondblclick="callShowDiv(this);"><%=label.get("pfofficename")%></label>
								:</td>
								<td><s:textfield name="pfOfficeName" size="25"/>
								</td>
							</tr>
							<tr>
								<td width="20%"><label id="establishmentno" name="establishmentno" ondblclick="callShowDiv(this);"><%=label.get("establishmentno")%></label> :<font color="red">*</font></td>
								<td width="30%"><s:textfield name="estabNo" theme="simple" maxlength="20" size="25" maxlength="20" /></td>
								<td width="20%" colspan="1"><label id="accGrpNo" name="accGrpNo" ondblclick="callShowDiv(this);"><%=label.get("accGrpNo")%></label> :<font color="red">*</font></td>
								<td width="30%" colspan="1"><s:textfield name="accGrpNo" theme="simple" maxlength="20" size="25" maxlength="20" /></td>
							</tr>
							<tr>
								<td width="20%" colspan="1"><label id="modOfpay" name="modOfpay" ondblclick="callShowDiv(this);"><%=label.get("modOfpay")%></label> :<font color="red">*</font></td>
								<td width="30%" colspan="1"><s:select name="modePayment"
									theme="simple" cssStyle="width:152"
									list="#{'':'-- Select --','CH':'Cheque','CS':'Cash','DD':'DD','TS':'Transfer'}"
									value="%{modePayment}" onchange="callPayMode();" /></td>
									
								<td colspan="1" width="20%"><label id="datePay" name="datePay" ondblclick="callShowDiv(this);"><%=label.get("datePay")%></label>:</td>
								<td colspan="1" width="30%"><s:textfield size="25"
									theme="simple" name="dateOfPay" maxlength="10" onkeypress="return numbersWithHiphen();"/> <s:a
									href="javascript:NewCal('paraFrm_dateOfPay','DDMMYYYY');">
									<img class="iconImage"  id='ctrlHide' 
										src="../pages/images/recruitment/Date.gif" width="16"
										height="16" border="0" align="absmiddle" />
								</s:a></td>
									
							</tr>
							<tr id="che">
								<td colspan="1" width="20%"><label id="chequeno" name="chequeno" ondblclick="callShowDiv(this);"><%=label.get("chequeno")%></label> :<font color="red">*</font></td>
								<td colspan="1" width="30%"><s:textfield name="chequeNo"
									theme="simple" maxlength="20" size="25"
									onkeypress="return numbersOnly();" /></td>
								<td colspan="1" width="20%"><label id="chequeDate" name="chequeDate" ondblclick="callShowDiv(this);"><%=label.get("chequeDate")%></label> :<font color="red">*</font></td>
								<td colspan="1" width="30%"><s:textfield size="25"
									theme="simple" name="cheqDate" maxlength="10" onkeypress="return numbersWithHiphen();"/> <s:a
									href="javascript:NewCal('paraFrm_cheqDate','DDMMYYYY');">
									<img class="iconImage" id='ctrlHide' 
										src="../pages/images/recruitment/Date.gif" width="16"
										height="16" border="0" align="absmiddle" />
								</s:a></td>
							</tr>
							<tr id="bnk">
								<td width="20%" colspan="1"><label id="bank" name="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label> :<font color="red">*</font></td>
								<td width="80%" colspan="3"><s:textfield size="25"
									name="bankMicrId" value="%{bankMicrId}" readonly="true" />
								<s:textfield size="71"
									name="bankName" value="%{bankName}" readonly="true" /><s:hidden name="bankBranchName" />
								 <img
									src="../pages/images/recruitment/search2.gif" class="iconImage"
									height="18" align="absmiddle" width="18" id='ctrlHide' 
									onclick="javascript:callsF9(500,325,'PFChallan_f9bankaction.action');" /></td>
							</tr>
						</table>
						</td>
					</tr>
			</table>
			</td>
			</tr>
			<tr>
      		<td>
      			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
	         		<tr>
	            		<td nowrap="nowrap">
	            			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
	  					</td>
	            		<td width="70%">
						<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
					</td>
	          		</tr>
          		</table>
            </td>
    	</tr>
	</table>
<s:hidden name="challanCode" />
<s:hidden name="reportType" />
<s:hidden name="reportAction" value='PFChallan_report.action'/>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
getYear();
callPayMode();
calculateSum();
calculateSumDeposited();
	function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm_reportType').value=type;
			document.getElementById('paraFrm').action='PFChallan_mailReport.action';
			document.getElementById('paraFrm').submit();
		}	
	}
	function callReport(type){
		document.getElementById('paraFrm_reportType').value=type;
		if(!validateFields()){
				return false;
			} else {
				callReportCommon(type);
			}
	}
	 
	function saveFun() {
 		if(!validateFields()){
 			return false;
 		}else{
			document.getElementById('paraFrm').action='PFChallan_save.action';
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main";
		}
 	}
 	
 	function processFun() {
 	if(!validateProcess()){
 			return false;
 		}
 		document.getElementById('paraFrm').action='PFChallan_processPfChallan.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main";
 	}
 	
 	function validateProcess(){
 		if(document.getElementById("paraFrm_month").value==0){
	 		alert("Please select the "+document.getElementById('months').innerHTML.toLowerCase());
	 		return false;
 		}
 		if(document.getElementById("paraFrm_year").value==""){
 			alert("Please enter the  "+document.getElementById('years').innerHTML.toLowerCase());
 			return false;
 		}
 		if(!checkYear('paraFrm_year','years')){
	 		return false;	 
		}
 		if(document.getElementById("paraFrm_divId").value==""){
			alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
 		}
 		return true;
 	}
 	function validateFields(){
 		if(document.getElementById("paraFrm_divId").value==""){
	 		alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
	 		return false;
 		}
 		 if(document.getElementById("paraFrm_month").value==0){
	 		alert("Please select the "+document.getElementById('months').innerHTML.toLowerCase());
	 		return false;
 		}
 		if(document.getElementById("paraFrm_year").value==""){
 			alert("Please enter the  "+document.getElementById('years').innerHTML.toLowerCase());
 			return false;
 		}
 		if(!checkYear('paraFrm_year','years')){
	 		return false;	 
		}
 		if(document.getElementById("paraFrm_modePayment").value==""){
 			alert("Please select the "+document.getElementById('modOfpay').innerHTML.toLowerCase());
 			return false;
 		}
 		var check1= validateDate('paraFrm_dateOfPay','datePay');
		if(!check1){
			return false;
		}
 		if(document.getElementById("paraFrm_modePayment").value=="CH" ||
 			document.getElementById("paraFrm_modePayment").value=="DD" )
 		{
	 		if(document.getElementById("paraFrm_chequeNo").value==""){
	 			alert("Please enter the "+document.getElementById('chequeno').innerHTML.toLowerCase());
	 			return false;
	 		}
	 		if(document.getElementById("paraFrm_cheqDate").value==""){
	 			alert("Please enter the "+document.getElementById('chequeDate').innerHTML.toLowerCase());
	 			return false;
	 		}
	 		var checkCdate= validateDate('paraFrm_cheqDate','chequeDate');
			if(!checkCdate){
				return false;
			}	
 			if(document.getElementById("paraFrm_bankName").value==""){
 				alert("Please select the "+document.getElementById('bank').innerHTML.toLowerCase());
 				return false;
 			}
 		}
 		
 		if(document.getElementById("paraFrm_estabNo").value==""){
 			alert("Please enter the  "+document.getElementById('establishmentno').innerHTML.toLowerCase());
 			return false;
 		}
 		if(document.getElementById("paraFrm_accGrpNo").value==""){
 			alert("Please enter the  "+document.getElementById('accGrpNo').innerHTML.toLowerCase());
 			return false;
 		}
 		return true;
 	}
 	function resetFun() {
		
  	 	document.getElementById('paraFrm').action = 'PFChallan_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
	
      	document.getElementById('paraFrm').action = 'PFChallan_back.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	
      	document.getElementById('paraFrm').action = 'PFChallan_delete.action';
		document.getElementById('paraFrm').submit();
	}
	
 	function reportFun(){
  	
	  	document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action='PFChallan_report.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main";
	} 
 	function editFun() {
 	
      	document.getElementById('paraFrm').action = 'PFChallan_edit.action';
		document.getElementById('paraFrm').submit();
	}
 
 

function callPayMode() {
	var val = trim(document.getElementById('paraFrm_modePayment').value);

	if(val == 'CH' || val == 'DD')
	{
		document.getElementById('che').style.display = '';
		document.getElementById('bnk').style.display = '';
	}
	else if(val == "CS" || val =='TS')
	{
   					document.getElementById('paraFrm_chequeNo').value=''; 
   					document.getElementById('paraFrm_bankName').value=''; 
   					document.getElementById('paraFrm_bankMicrId').value=''; 
   					document.getElementById('paraFrm_cheqDate').value=''; 
					document.getElementById('che').style.display='none';
					document.getElementById('bnk').style.display='none';
		
	}
	else if(document.getElementById('che').value == '')
	{
			document.getElementById('che').style.display = 'none';
			document.getElementById('bnk').style.display='none';
	}
	else
	{
			document.getElementById('che').style.display = 'none';
			document.getElementById('bnk').style.display='none';
	}
	
}

 function getYear(){
	var current = new Date();
	 var year =current.getFullYear();

	 var yr =document.getElementById("paraFrm_year").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_year").value =year;
	  }
}

function callCheck(){

 	if(document.getElementById('checkVpf').checked){
 		document.getElementById('vpf1').style.display=''; 
 		document.getElementById('vpf2').style.display=''; 
 		//document.getElementById('paraFrm_hcheckVpf').value="Y";
 	}else{
 		document.getElementById('vpf1').style.display='none'; 
 		document.getElementById('vpf2').style.display='none'; 
 		document.getElementById("paraFrm_vpfCode").value="";
 		document.getElementById("paraFrm_vpfName").value="";
 		//document.getElementById('paraFrm_hcheckVpf').value="N";
 		}
 	
 }

function callCopyDeducted(){
	document.getElementById('paraFrm_employersPfContDepositedAc1').value = document.getElementById('paraFrm_employersPfContDeductedAc1').value;
	document.getElementById('paraFrm_employersPensionDepositedAc10').value = document.getElementById('paraFrm_employersPensionDeductedAc10').value;
	document.getElementById('paraFrm_employersEdliDepositedAc21').value = document.getElementById('paraFrm_employersEdliDeductedAc21').value;
	document.getElementById('paraFrm_employersTotalDeposited').value = document.getElementById('paraFrm_employersTotalDeducted').value;
	
	document.getElementById('paraFrm_empPfContDepositedAc1').value = document.getElementById('paraFrm_empPfContDeductedAc1').value;
	document.getElementById('paraFrm_empTotalDeposited').value = document.getElementById('paraFrm_empTotalDeducted').value
	document.getElementById('paraFrm_adminPfAdminDepositedAc2').value = document.getElementById('paraFrm_adminPfAdminDeductedAc2').value;
	document.getElementById('paraFrm_adminEdliDepositedAc22').value = document.getElementById('paraFrm_adminEdliAdminDeductedAc22').value;
	document.getElementById('paraFrm_adminTotalDeposited').value = document.getElementById('paraFrm_adminTotalDeducted').value;

	document.getElementById('paraFrm_inspectionPfAdminDepositedAc2').value = document.getElementById('paraFrm_inspectionPfAdminDeductedAc2').value;
	document.getElementById('paraFrm_inspectionEdliDepositedAc22').value = document.getElementById('paraFrm_inspectionEdliAdminDeductedAc22').value;
	document.getElementById('paraFrm_inspectionTotalDeposited').value = document.getElementById('paraFrm_inspectionTotalDeducted').value;
	
	document.getElementById('paraFrm_penalDamagesPfContDepositedAc1').value="0.00";
	document.getElementById('paraFrm_penalDamagesPfAdminDepositedAc2').value="0.00";
	document.getElementById('paraFrm_penalDamagesPensionDepositedAc10').value="0.00";
	document.getElementById('paraFrm_penalDamagesEdliDepositedAc21').value="0.00";
	document.getElementById('paraFrm_penalDamagesEdliAdminDepositedAc22').value="0.00";
	
	document.getElementById('paraFrm_miscPfAdminDepositedAc2').value="0.00";
	document.getElementById('paraFrm_miscEdliAdminDepositedAc22').value="0.00";
	
	document.getElementById('paraFrm_totalPfContDepositedAc1').value = document.getElementById('paraFrm_totalPfContDeductedAc1').value;
	document.getElementById('paraFrm_totalPfAdminDepositedAc2').value = document.getElementById('paraFrm_totalPfAdminDeductedAc2').value;
	document.getElementById('paraFrm_totalPensionDepositedAc10').value = document.getElementById('paraFrm_totalPensionDeductedAc10').value;
	document.getElementById('paraFrm_totalEdliDepositedAc21').value = document.getElementById('paraFrm_totalEdliDeductedAc21').value;
	document.getElementById('paraFrm_totalEdliAdminDepositedAc22').value = document.getElementById('paraFrm_totalEdliAdminDeductedAc22').value;
	document.getElementById('paraFrm_penalDamagesTotalDeposited').value="0.00";
	document.getElementById('paraFrm_miscTotalDeposited').value="0.00";
	document.getElementById('paraFrm_totalDeposited').value = document.getElementById('paraFrm_totalDeducted').value;
}

function calculateSum(){
	var initialVal = "0.00"
	var employerDeducted1 = document.getElementById('paraFrm_employersPfContDeductedAc1').value;
	var employerDeducted2 = document.getElementById('paraFrm_employersPensionDeductedAc10').value;
	var employerDeducted3 = document.getElementById('paraFrm_employersEdliDeductedAc21').value;
	var employerDeductedTotal = eval(employerDeducted1)+eval(employerDeducted2)+eval(employerDeducted3);
	document.getElementById('paraFrm_employersTotalDeducted').value = isNaN(employerDeductedTotal)?initialVal:employerDeductedTotal.toFixed(2);
	
	var employeeDeducted1 = document.getElementById('paraFrm_empPfContDeductedAc1').value;
	var employeeDeductedTotal = eval(employeeDeducted1);
	document.getElementById('paraFrm_empTotalDeducted').value=isNaN(employeeDeductedTotal)?initialVal:employeeDeductedTotal.toFixed(2);
	
	var adminDeducted1 = document.getElementById('paraFrm_adminPfAdminDeductedAc2').value;
	var adminDeducted2 = document.getElementById('paraFrm_adminEdliAdminDeductedAc22').value;
	var adminDeductedTotal = eval(adminDeducted1)+eval(adminDeducted2);
	document.getElementById('paraFrm_adminTotalDeducted').value=isNaN(adminDeductedTotal)?initialVal:adminDeductedTotal.toFixed(2);

	var inspectionDeducted1 = document.getElementById('paraFrm_inspectionPfAdminDeductedAc2').value;
	var inspectionDeducted2 = document.getElementById('paraFrm_inspectionEdliAdminDeductedAc22').value;
	var inspectionDeductedTotal =  eval(inspectionDeducted1)+eval(inspectionDeducted2); 
	document.getElementById('paraFrm_inspectionTotalDeducted').value=isNaN(inspectionDeductedTotal)?initialVal:inspectionDeductedTotal.toFixed(2);
	
	var dedTotal1 = eval(employerDeducted1)+eval(employeeDeducted1);
	var dedTotal2 = eval(adminDeducted1)+eval(inspectionDeducted1);
	var dedTotal3 = eval(employerDeducted2);
	var dedTotal4 = eval(employerDeducted3);
	var dedTotal5 = eval(adminDeducted2)+eval(inspectionDeducted2);
	
	document.getElementById('paraFrm_totalPfContDeductedAc1').value = isNaN(dedTotal1)?initialVal:dedTotal1.toFixed(2);
	document.getElementById('paraFrm_totalPfAdminDeductedAc2').value = isNaN(dedTotal2)?initialVal:dedTotal2.toFixed(2);
	document.getElementById('paraFrm_totalPensionDeductedAc10').value = isNaN(dedTotal3)?initialVal:dedTotal3.toFixed(2);
	document.getElementById('paraFrm_totalEdliDeductedAc21').value = isNaN(dedTotal4)?initialVal:dedTotal4.toFixed(2);
	document.getElementById('paraFrm_totalEdliAdminDeductedAc22').value = isNaN(dedTotal5)?initialVal:dedTotal5.toFixed(2);
	
	var totalDeducted1 = document.getElementById('paraFrm_totalPfContDeductedAc1').value;
	var totalDeducted2 = document.getElementById('paraFrm_totalPfAdminDeductedAc2').value;
	var totalDeducted3 = document.getElementById('paraFrm_totalPensionDeductedAc10').value;
	var totalDeducted4 = document.getElementById('paraFrm_totalEdliDeductedAc21').value;
	var totalDeducted5 = document.getElementById('paraFrm_totalEdliAdminDeductedAc22').value;
	
	var totalDeductedTotal = eval(totalDeducted1)+eval(totalDeducted2)+eval(totalDeducted3)+eval(totalDeducted4)+eval(totalDeducted5);
	
	document.getElementById('paraFrm_totalDeducted').value=isNaN(totalDeductedTotal)?initialVal:totalDeductedTotal.toFixed(2);
}

function calculateSumDeposited(){
	var initialVal = "0.00"
	var employerDeposited1 = document.getElementById('paraFrm_employersPfContDepositedAc1').value;
	var employerDeposited2 = document.getElementById('paraFrm_employersPensionDepositedAc10').value;
	var employerDeposited3 = document.getElementById('paraFrm_employersEdliDepositedAc21').value;
	var employerDepositedTotal = eval(employerDeposited1)+eval(employerDeposited2)+eval(employerDeposited3);
	document.getElementById('paraFrm_employersTotalDeposited').value = isNaN(employerDepositedTotal)?initialVal:employerDepositedTotal.toFixed(2);
	
	var employeeDeposited1 = document.getElementById('paraFrm_empPfContDepositedAc1').value;
	var employeeDepositedTotal = eval(employeeDeposited1);
	document.getElementById('paraFrm_empTotalDeposited').value=isNaN(employeeDepositedTotal)?initialVal:employeeDepositedTotal.toFixed(2);
	
	var adminDeposited1 = document.getElementById('paraFrm_adminPfAdminDepositedAc2').value;
	var adminDeposited2 = document.getElementById('paraFrm_adminEdliDepositedAc22').value;
	var adminDepositedTotal = eval(adminDeposited1)+eval(adminDeposited2);
	document.getElementById('paraFrm_adminTotalDeposited').value=isNaN(adminDepositedTotal)?initialVal:adminDepositedTotal.toFixed(2);
	
	var inspectionDeposited1 = document.getElementById('paraFrm_inspectionPfAdminDepositedAc2').value;
	var inspectionDeposited2 = document.getElementById('paraFrm_inspectionEdliDepositedAc22').value;
	var inspectionDepositedTotal =  eval(inspectionDeposited1)+eval(inspectionDeposited2); 
	document.getElementById('paraFrm_inspectionTotalDeposited').value=isNaN(inspectionDepositedTotal)?initialVal:inspectionDepositedTotal.toFixed(2);
	
	var penalDamagesDeposited1 = document.getElementById('paraFrm_penalDamagesPfContDepositedAc1').value;
	var penalDamagesDeposited2 = document.getElementById('paraFrm_penalDamagesPfAdminDepositedAc2').value;
	var penalDamagesDeposited3 = document.getElementById('paraFrm_penalDamagesPensionDepositedAc10').value;
	var penalDamagesDeposited4 = document.getElementById('paraFrm_penalDamagesEdliDepositedAc21').value;
	var penalDamagesDeposited5 = document.getElementById('paraFrm_penalDamagesEdliAdminDepositedAc22').value;
	var penalDamagesDepositedTotal = eval(penalDamagesDeposited1)+eval(penalDamagesDeposited2)+eval(penalDamagesDeposited3)+eval(penalDamagesDeposited4)+eval(penalDamagesDeposited5);
	document.getElementById('paraFrm_penalDamagesTotalDeposited').value=isNaN(penalDamagesDepositedTotal)?initialVal:penalDamagesDepositedTotal.toFixed(2);
	
	var miscDeposited1 = document.getElementById('paraFrm_miscPfAdminDepositedAc2').value;
	var miscDeposited2 = document.getElementById('paraFrm_miscEdliAdminDepositedAc22').value;
	var miscDepositedTotal =  eval(miscDeposited1)+eval(miscDeposited2); 
	document.getElementById('paraFrm_miscTotalDeposited').value=isNaN(miscDepositedTotal)?initialVal:miscDepositedTotal.toFixed(2);
	
	var depTotal1 = eval(employerDeposited1)+eval(employeeDeposited1)+eval(penalDamagesDeposited1);
	var depTotal2 = eval(adminDeposited1)+eval(inspectionDeposited1)+eval(penalDamagesDeposited2)+eval(miscDeposited1);
	var depTotal3 = eval(employerDeposited2)+eval(penalDamagesDeposited3);
	var depTotal4 = eval(employerDeposited3)+eval(penalDamagesDeposited4);
	var depTotal5 = eval(adminDeposited2)+eval(inspectionDeposited2)+eval(penalDamagesDeposited5)+eval(miscDeposited2);

	document.getElementById('paraFrm_totalPfContDepositedAc1').value = isNaN(depTotal1)?initialVal:depTotal1.toFixed(2);
	document.getElementById('paraFrm_totalPfAdminDepositedAc2').value = isNaN(depTotal2)?initialVal:depTotal2.toFixed(2);
	document.getElementById('paraFrm_totalPensionDepositedAc10').value = isNaN(depTotal3)?initialVal:depTotal3.toFixed(2);
	document.getElementById('paraFrm_totalEdliDepositedAc21').value = isNaN(depTotal4)?initialVal:depTotal4.toFixed(2);
	document.getElementById('paraFrm_totalEdliAdminDepositedAc22').value = isNaN(depTotal5)?initialVal:depTotal5.toFixed(2);
	
	var totalDeposited1 = document.getElementById('paraFrm_totalPfContDepositedAc1').value;
	var totalDeposited2 = document.getElementById('paraFrm_totalPfAdminDepositedAc2').value;
	var totalDeposited3 = document.getElementById('paraFrm_totalPensionDepositedAc10').value;
	var totalDeposited4 = document.getElementById('paraFrm_totalEdliDepositedAc21').value;
	var totalDeposited5 = document.getElementById('paraFrm_totalEdliAdminDepositedAc22').value;
	
	var totalDepositedTotal = eval(totalDeposited1)+eval(totalDeposited2)+eval(totalDeposited3)+eval(totalDeposited4)+eval(totalDeposited5);
	
	document.getElementById('paraFrm_totalDeposited').value=isNaN(totalDepositedTotal)?initialVal:totalDepositedTotal.toFixed(2);
}

</script>
