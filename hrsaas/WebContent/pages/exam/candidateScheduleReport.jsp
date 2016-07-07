<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="CandidateScheduleReport" method="post" id="paraFrm">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" background="../images/lines.gif"
				class="txt"><img src="../pages/images/recruitment/lines.gif"
				width="16" height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>
		<tr>
			<td valign="bottom" class="txt"><strong class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Candidates Schedule
			Report </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">



				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%" colspan="3"><input type="button"
								class="token" 
								onclick="return callReportSchedule();" value="    Report"
								 /> <s:submit cssClass="reset"
								action="CandidateScheduleReport_reset" theme="simple"
								value="    Reset"  /></td>
							<td width="22%">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					<label></label></td>
				</tr>





				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">

								<td width="20%" class="formtext">From Date<font color="red">*</font>:</td>
								<td width="20%"><s:textfield name="fromDate" size="15"
									onkeypress="return numbersWithHiphen();" theme="simple"
									maxlength="10" /> <s:a
									href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
								<td width="20%" class="formtext">To Date<font color="red">*</font>:</td>
								<td width="20%"><s:textfield name="toDate" size="15"
									onkeypress="return numbersWithHiphen();" theme="simple"
									maxlength="10" /> <s:a
									href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"
										class="iconImage" height="16" align="absmiddle" width="16">
								</s:a></td>
								</tr>


								<tr>
									<td width="20%" colspan="1" class="formtext" nowrap="nowrap">Paper
									:<s:hidden name="candrpt.paperCode" value="%{paperCode}" /></td>
									<td width="20%" colspan="1"><s:textfield name="candrpt.paperName" size="30" value="%{paperName}"
										theme="simple" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'CandidateScheduleReport_f9Paper.action');">
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
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>

				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
			</table>
			<br />


			<label></label></td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">

function callReportSchedule(){
  	var frmDate = document.getElementById("paraFrm_fromDate").value;
  	var toDate  = document.getElementById("paraFrm_toDate").value;
    if(frmDate!=""){
  			if(!validateDate("paraFrm_fromDate","From Date"))
				return false;
	}else{
	alert("Enter From Date");
	document.getElementById("paraFrm_fromDate").focus();
	return false;
	}
	 if(toDate!=""){
  			if(!validateDate("paraFrm_toDate","To Date"))
			return false;
	}else{
	alert("Enter To Date");
	document.getElementById("paraFrm_toDate").focus();
	return false;
	}
	  	 if ((frmDate!="") && (toDate!="")) {
  			if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_fromDate', 'From Date', 'To Date'))
			return false;
	}
			callReport('CandidateScheduleReport_report.action');
	}

</script>
