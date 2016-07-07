<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>


<s:form action="InterviewAssessmentReport" id="paraFrm" theme="simple">
	<table class="formbg" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Interview
					Assessment Report </strong></td>
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
					<td width="70%" colspan="3"><input type="button" class="token"
						onclick="return callReport();" value="    Generate Report" /> <s:submit
						cssClass="reset" action="InterviewAssessmentReport_reset"
						theme="simple" value="    Reset" /></td>

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
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!--
				<tr>
					<td width="25%" colspan="1" class="formtext"><label
						class="set" id="reqs.code" name="reqs.code"
						ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
					<font color="red">*</font>:</td>
					<td width="75%" colspan="3">
					<img
						src="../pages/images/search2.gif" height="16" align="absmiddle"
						width="16" theme="simple"
						onclick="javascript:callsF9(500,325,'InterviewAssessmentReport_f9requisitionCode.action')"></td>

				</tr>

				-->
				<tr>
					<td width="25%" colspan="1" class="formtext"><label
						class="set" id="cand.name" name="cand.name"
						ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
					<font color="red">*</font>:</td>
					<td width="75%" colspan="3"><s:hidden name="candCode"></s:hidden>
					<s:hidden name="reqCode" /> <s:textfield name="reqName" size="30" readonly="true"/> <s:textfield
						name="candName" size="50" theme="simple" readonly="true" /> <img
						src="../pages/images/search2.gif" height="16" align="absmiddle"
						width="16" theme="simple" onclick="validateCandidate();"></td>

				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="70%" colspan="3"><input type="button" class="token"
						onclick="return callReport();" value="    Generate Report" /> <s:submit
						cssClass="reset" action="InterviewAssessmentReport_reset"
						theme="simple" value="    Reset" /></td>

					<td width="22%"></td>
				</tr>
			</table>
			<label></label></td>

		</tr>
	</table>


</s:form>


<script>


function validateCandidate()
{
	 
	 callsF9(500,325,'InterviewAssessmentReport_f9candidate.action');
}


function callReport(){
  	 
  		if(document.getElementById('paraFrm_candCode').value == ''){
  			alert("Please select the "+document.getElementById("cand.name").innerHTML.toLowerCase());
  			return false;
  		}
  		
	  document.getElementById('paraFrm').target="_blank";
      document.getElementById('paraFrm').action="InterviewAssessmentReport_report.action";
	  document.getElementById('paraFrm').submit();  
      document.getElementById('paraFrm').target="main";
  
   	
	
}

</script>