<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="InterviewEvaluationReport" method="post" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Interview
					Evaluation Analysis</strong></td>
					<td width="3%" valign="top" class="otxt">
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
					<td width="100%"> 
						<input type="button" class="token" onclick="return generateReport();" value=" Generate Report" />
					 	<s:submit cssClass="reset" action="InterviewEvaluationReport_reset" theme="simple" value="Reset"/>
					 </td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td width="20%">
						<label name="requisitionTitle" id="requisitionTitle" ondblclick="callShowDiv(this);"><%=label.get("requisitionTitle")%></label></b> : 
					</td>
					<td width="20%">
						<s:textfield name="reqname" size="40" theme="simple" readonly="true" /> 
						<s:hidden name="reqCode" />
						<s:hidden name="reqDate" />
						<s:hidden name="reqPosition" />
					</td>
					<td width="60%">
						<img src="../pages/images/recruitment/search2.gif" height="15"
							align="absmiddle" width="16" onclick="
							javascript:callsF9(500,325,'InterviewEvaluationReport_f9actionReqName.action');" />
					</td>
				</tr>

				<tr>
					<td width="20%">
						<label name="inteviewerName" id="inteviewerName" ondblclick="callShowDiv(this);"><%=label.get("inteviewerName")%></label></b> :
					</td>
					
					<td width="20%">
						<s:textfield name="interviewerName" size="40" theme="simple" readonly="true" /> 
						<s:hidden name="interviewerToken" />
						<s:hidden name="interviewerId" />
					</td>

					<td width="60%">
						<img src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16" onclick="callsF9(500,325,'InterviewEvaluationReport_f9actionInterviewer.action');">
					</td>
				</tr>

				<tr>
					<td width="20%">
						<label name="candidateName" id="candidateName" ondblclick="callShowDiv(this);"><%=label.get("candidateName")%></label></b> :
					</td>
					
					<td width="20%">
						<s:textfield name="candidateName" size="40" theme="simple" readonly="true" />
						<s:hidden name="candidateCode" />	
					</td>
					
					<td width="60%">
						<img src="../pages/images/recruitment/search2.gif" height="15" align="absmiddle" width="16" onclick="callsF9(500,325,'InterviewEvaluationReport_f9Candidate.action');">
					</td>
				</tr>

				<tr>
					<td width="20%">
						<label name="position" id="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b> :
					</td>
					
					<td width="20%">
						<s:textfield name="position" size="40" theme="simple" readonly="true" />
						<s:hidden name="positionId" />	
					</td>
					
					<td width="60%">
						<img src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16" onclick="callsF9(500,325,'InterviewEvaluationReport_f9Position.action');">
					</td>
				</tr>

				<tr>
					<td width="20%" colspan="1" class="formtext">
						<label class="set" id="reqs.date" name="reqs.date"
							ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label> :
					</td>
					
					<td width="80%" colspan="2">
						<table width="100%">
							<tr>
								<td width="10%">
									<label class="set" id="fromDateLabel" name="fromDateLabel"
										ondblclick="callShowDiv(this);"><%=label.get("fromDateLabel")%></label> :
								</td>
								
								<td width="10%">
									<s:textfield name="frmDate" size="9" maxlength="10" onkeypress="return numbersWithHiphen();"/>
								</td>
								
								<td width="15%">
									<img
									src="../pages/images/recruitment/Date.gif" id="fromDateIcon"
									class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
								</td>
								
								<td width="10%" colspan="1">
									<label class="set" id="toDateLabel" name="toDateLabel"
										ondblclick="callShowDiv(this);"><%=label.get("toDateLabel")%></label> :
								</td>
								
								<td width="10%">
									<s:textfield name="toDate" size="10" maxlength="10" onkeypress="return numbersWithHiphen();" />  
								</td>
								
								<td width="15%"> 
									<img src="../pages/images/recruitment/Date.gif" class="iconImage"
									height="18" align="absmiddle" width="18" onclick="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="20%" colspan="1" class="formtext">
						<label class="set" id="interviewDateLabel" name="interviewDateLabel"
							ondblclick="callShowDiv(this);"><%=label.get("interviewDateLabel")%></label> :
					</td>
					<td width="80%" colspan="2">
						<table width="100%">
							<tr>
								<td width="">
									<s:textfield name="interviewDate" size="9" maxlength="10" onkeypress="return numbersWithHiphen();"/>
									<img
									src="../pages/images/recruitment/Date.gif" id="fromDateIcon"
									class="iconImage" height="18" align="absmiddle" width="18"
									onclick="javascript:NewCal('paraFrm_interviewDate','DDMMYYYY');">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="20%">Report type : <font color="red">*</font> </td>
					<td colspan="2">
						<s:select headerKey="1" headerValue="--Select--" name="reportType" list="#{'Pdf':'Pdf','Xls':'Xls' ,'Doc':'Doc'}" />
					</td>
				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="100%" colspan="3">
								<input type="button" class="token" onclick="return generateReport();" value=" Generate Report" />
								<s:submit cssClass="reset" action="InterviewEvaluationReport_reset" theme="simple" value="Reset"  />
							</td>
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
function generateReport() {
	if (!validateFields()) {
		return false;
	} else {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='InterviewEvaluationReport_report.action';
		document.getElementById('paraFrm').submit();
	}
}

function validateFields(){
	var reportType = document.getElementById('paraFrm_reportType').value;
	if (reportType == 1) {
		alert("Please select report type");
		return false;
	}
	
	var fromDate = document.getElementById('paraFrm_frmDate').value;
	var toDate = document.getElementById('paraFrm_toDate').value;
	if(fromDate != "") {
		if(!validateDate('paraFrm_frmDate','fromDateLabel')){
			document.getElementById('paraFrm_frmDate').focus();
			return false;   	
	   	}
	   	
	   	if (toDate == ""){
	   		alert("Please select/enter "+document.getElementById('toDateLabel').innerHTML.toLowerCase());
	   		document.getElementById('paraFrm_toDate').focus();
	   		return false;
	   	}
   	}
   	
	
	if(toDate != "") {
		if(!validateDate('paraFrm_toDate','toDateLabel')){
			document.getElementById('paraFrm_toDate').focus();
			return false;   	
	   	}
	   	
		if (fromDate == ""){
	   		alert("Please select/enter "+document.getElementById('fromDateLabel').innerHTML.toLowerCase());
	   		document.getElementById('paraFrm_frmDate').focus();
	   		return false;
	   	} 
   	}
   	
   	if(fromDate != "" && toDate != "") {
   		if(!dateDifferenceEqual(fromDate,toDate,"paraFrm_toDate",'fromDateLabel','toDateLabel')) {
   			return false;
   		}
   	}
	return true;   	
}
</script>
 