<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="AlertEmailSetting" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Alert
					Email Setting</strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="1"><s:submit
						cssClass="reset" action="AlertEmailSetting_save" theme="simple"
						value="    Save" /> <s:submit
						cssClass="reset" action="AlertEmailSetting_clear" theme="simple"
						value="    Reset" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
						<td colspan="1" width="20%" class="formtext" height="22">Module Name
						:</td>
						<td colspan="3" width="80%"><s:textfield theme="simple" readonly="true"
							name="modName"  size="25" /><s:hidden name="modCode" /><img
							src="../pages/images/recruitment/search2.gif" height="18"
							class="iconImage" align="absmiddle" width="18"
							onclick="javascript:callsF9(500,325,'AlertEmailSetting_f9Modules.action');"></td>
							
					</tr>
					<tr>
						<td colspan="1" width="20%" class="formtext" height="22">Type
						:</td>
						<td colspan="3" width="80%"><s:select theme="simple"
						name="alertType" cssStyle="width:60"
						list="#{'A':'Alert','E':'Email','B':'Both'}" /></td>
							
					</tr>
					<tr>
						<td colspan="1" width="20%" class="formtext" height="22">No Of Days To Show Record
						:</td>
						<td colspan="3" width="80%"><s:textfield name="noOfDays" maxlength="2" size="25" value="%{noOfDays}" onkeypress="return numbersOnly();"/></td>
							
					</tr>
					<tr>
						<td colspan="1" width="20%" class="formtext" height="22">Send Alert
						:</td>
						<td colspan="1" width="20%">Applicant To Approver <s:checkbox name="applicantToapprover" /></td>
						<td colspan="1" width="20%">Approver To Approver <s:checkbox name="approverToapprover" /></td>
						<td colspan="1" width="40%">Approver To Applicant <s:checkbox name="approverToapplicant" /></td>
							
					</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>