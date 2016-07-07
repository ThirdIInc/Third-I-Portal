<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="CustomerAccountInfo" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
	  		<td colspan="3" width="100%">
			   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Weekly Performance Summary
							</strong>
						</td>
						<td width="3%" valign="top" class="otxt"></td>
					</tr>
				</table>
			</td>
	  	</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
							
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td nowrap="nowrap">From Week :<font color="red">*</font></td>
									<td nowrap="nowrap"><s:textfield size="18" name="fromWeek"
										maxlength="4" readonly="true"/>
										
										
							<s:a
								href="javascript:NewCal('paraFrm_fromWeek','DDMMYYYY');">
								<img class="iconImage" src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" /></s:a>
										</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								
								<tr>
									<td nowrap="nowrap">To Week :<font color="red">*</font></td>
									<td nowrap="nowrap"><s:textfield size="18" name="toWeek" maxlength="4" readonly="true"/>
										<s:a
								href="javascript:NewCal('paraFrm_toWeek','DDMMYYYY');">
								<img class="iconImage" src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" /></s:a>
										</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								
								<tr>
									<td colspan="4" align="center">
									<s:hidden name="hCustomerCode" />
									<s:hidden name="accountCode"/>
									<s:hidden name="reportCode" />	
									<s:hidden name="accountID"/>
									<s:hidden name="hPublishDate"/>
																
										<input type="button" class="save" value="Generate Report" 
										onclick="generateCrystalReport('paraFrm_reportCode','paraFrm_accountCode','paraFrm_fromWeek','paraFrm_toWeek','paraFrm_accountID','CustomerAccountInfo_weeklyPerformanceSummaryReport.action','paraFrm_hPublishDate','client');">
								</td>
								</tr>
								
							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>

function callGenerateReport123(reportcode,accCode,inFromWeek,inToWeek){
	var reportcodeValue=document.getElementById(reportcode).value;
	var accCodeValue=document.getElementById(accCode).value;
	var fromWeekValue=document.getElementById(inFromWeek).value;
	var toWeekValue=document.getElementById(inToWeek).value;
	reportcodeValue=reportcodeValue+'_'+accCodeValue+'_'+fromWeekValue+'_'+toWeekValue;

	var action_method= '<%=request.getContextPath()%>/cr/CustomerAccountInfo_weeklyPerformanceSummaryReport.action?reportcodeValue12='+reportcodeValue;
	generateCrystalReport(action_method);
	//document.getElementById('paraFrm').target = "_blank";
	//document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/cr/CustomerAccountInfo_weeklyPerformanceSummaryReport.action?reportcodeValue12='+reportcodeValue;
	//document.getElementById('paraFrm').submit();

	}

function generateCrystalReport(reportcode,accCode,inFromWeek,inToWeek,inlistAccountID,method_action){
	var reportcodeValue=document.getElementById(reportcode).value;
	var accCodeValue=document.getElementById(accCode).value;
	var fromWeekValue=document.getElementById(inFromWeek).value;
	var toWeekValue=document.getElementById(inToWeek).value;
	if(fromWeekValue==''){
		alert('Please select from date');
		return false;
	}
	if(toWeekValue==''){
		alert('Please select to date');
		return false;
	}
	var inlistAccountIDValue=document.getElementById(inlistAccountID).value;
	reportcodeValue=reportcodeValue+'_'+accCodeValue+'_'+fromWeekValue+'_'+toWeekValue+'_'+inlistAccountIDValue;
	//alert('<%=request.getContextPath()%>/cr/CustomerAccountInfo_'+method_action+'?reportcodeValue12='+reportcodeValue);
	document.getElementById('paraFrm').target = "_blank";
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/cr/'+method_action+'?reportcodeValue12='+reportcodeValue;
	document.getElementById('paraFrm').submit();
	
	}
	
</script>