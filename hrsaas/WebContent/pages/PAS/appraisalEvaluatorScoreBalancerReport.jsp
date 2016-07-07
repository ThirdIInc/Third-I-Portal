<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="AppraisalBellCurve" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="sAppId"></s:hidden>
	<s:hidden name="sAppStartDate"></s:hidden>
	<s:hidden name="sAppEndDate"></s:hidden>

	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Evaluator Score Balancer Report </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="token"
						theme="simple" value=" Report" onclick="return validateReport();" />
					<input type="button" class="reset" theme="simple" value=" Reset"
						onclick="return resetScreen();" /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<s:hidden name="startDate" />
					<s:hidden name="endDate" />
					<td colspan="1" width="15%"><label name="appr.code"
						id="appr.code" ondblclick="callShowDiv(this);"><%=label.get("appr.code")%></label><font
						color="red">*</font> :</td>
					<td colspan="1" width="30%"><s:textfield size="30" readonly="true"
						name="sAppCode" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						class="iconImage" height="15"
						onclick="javascript:callsF9(500,325,'AppraisalEvaluatorScoreBalancerRpt_f9AppraisalCode.action');" />
					</td>

					<td colspan="1" width="15%">&nbsp;</td>
					<td colspan="1" width="40%">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="token"
						theme="simple" value=" Report" onclick="return validateReport();" />
					<input type="button" class="reset" theme="simple" value=" Reset"
						onclick="return resetScreen();" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<script type="text/javascript">
		function validateReport()
		{
			if(document.getElementById('paraFrm_sAppCode').value=="")
			{
				alert("Please select '" + document.getElementById('appr.code').innerHTML + "'");
				return false;
			}
			
			callReport("AppraisalEvaluatorScoreBalancerRpt_generateReport.action");
			resetScreen();
			return true;
		}
	
		function resetScreen()
		{
			document.getElementById('paraFrm_sAppId').value="";
			document.getElementById('paraFrm_sAppCode').value="";
			document.getElementById('paraFrm_sAppStartDate').value="";
			document.getElementById('paraFrm_sAppEndDate').value="";
		}
	</script>

</s:form>