<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="RecruitmentSettings" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="branchName"/>
	<s:hidden name="departmentName"/>
	<s:hidden name="rankName"/>
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		height="300px" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="5%"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="95%" class="txt"><strong class="text_head">Recruitment
					Settings </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="5%"><s:submit cssClass="save" theme="simple"
						action="RecruitmentSettings_saveRecruitmentSetting" value=" Save" /> <s:submit cssClass="reset" theme="simple"
						action="RecruitmentSettings_reset" value=" Reset" /></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table class="formbg" border="0" width="100%">
				<tr>
					<td width="82%" class="text_head" colspan="3"><strong><label
						name="applyRule" id="applyRule" ondblclick="callShowDiv(this);"><%=label.get("applyRule")%></label></strong></td>
				</tr>
				<tr>
					<td colspan="1" height="22" width="30%" nowrap="nowrap"><label
						name="durAfterReaaply" id="durAfterReaaply"
						ondblclick="callShowDiv(this);"><%=label.get("durAfterReaaply")%></label>:</td>
					<td colspan="3" width="22%"><s:textfield name="durCandReapply"
						size="3" maxlength="3" onkeypress="return numbersOnly();" /> <label
						name="afterText" id="afterText" ondblclick="callShowDiv(this);"><%=label.get("afterText")%></label></td>
				</tr>
				<tr>
					<td width="8%"><label class="set" id="checkForDuplicate"
						name="checkForDuplicate" ondblclick="callShowDiv(this);"><%=label.get("checkForDuplicate")%></label>:
					</td>
					<td width="15%"><s:checkbox name="duplicateDivFlag"
						onclick="callDuplicateDiv();" /> <s:hidden name="recSettingsCode" /></td>
				</tr>
				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>
				<tr id="flagsTable">
					<td colspan="3">
					<table width="85%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr id="nameRow">
							<td width="158" nowrap="nowrap"><label class="set" id="name"
								name="name" ondblclick="callShowDiv(this);"><%=label.get("name")%></label>
							:</td>
							<td colspan="2" width="100" nowrap="nowrap"><s:checkbox name="nameFlag"
								id="name" /></td>
						</tr>

						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>

						<tr>
							<td width="25%" align="center"><strong class="forminnerhead">AND</strong></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
						<tr id="dobRow">
							<td width="158" nowrap="nowrap"><label class="set" id="dob"
								name="dob" ondblclick="callShowDiv(this);"><%=label.get("dob")%></label>
							:</td>
							<td width="40%" nowrap="nowrap"><s:checkbox name="dobFlag"
								id="dob" /></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
						<tr>
							<td width="25%" align="center"><strong class="forminnerhead">AND</strong></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
						

						<tr id="emailIdRow">
							<td width="158" nowrap="nowrap"><label class="set"
								id="emailIdLab" name="emailIdLab"
								ondblclick="callShowDiv(this);"><%=label.get("emailIdLab")%></label>
							:</td>
							<td width="40%" nowrap="nowrap"><s:checkbox
								name="emailIdFlag" id="emailId" /></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
						<tr>
							<td width="25%" align="center"><strong class="forminnerhead">AND</strong></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
						<tr id="mobileRow">
							<td width="158" nowrap="nowrap"><label class="set"
								id="mobileLab" name="mobileLab" ondblclick="callShowDiv(this);"><%=label.get("mobileLab")%></label>
							:</td>
							<td width="40%" nowrap="nowrap"><s:checkbox
								name="mobileNumFlag" id="mobileNum" /></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
						<tr>
							<td width="25%" align="center"><strong class="forminnerhead">OR</strong></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
						<tr id="panNumRow">
							<td width="158" nowrap="nowrap"><label class="set"
								id="panNumLab" name="panNumLab" ondblclick="callShowDiv(this);"><%=label.get("panNumLab")%></label>
							:</td>
							<td width="40%" nowrap="nowrap"><s:checkbox
								name="panNumberFlag" id="panNumber" /></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
						<tr>
							<td width="25%" align="center"><strong class="forminnerhead">OR</strong></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
						<tr id="passportNumRow">
							<td width="158" nowrap="nowrap"><label class="set"
								id="passportNumLab" name="passportNumLab"
								ondblclick="callShowDiv(this);"><%=label.get("passportNumLab")%></label>
							:</td>
							<td width="40%" nowrap="nowrap"><s:checkbox
								name="passportNumFlag" id="passportNum" /></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
						<tr>
							<td width="25%" align="center"><strong class="forminnerhead">OR</strong></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
					<!-- 	<tr id="cityRow">
							<td width="40%" nowrap="nowrap"><label class="set" id="city"
								name="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label>
							:</td>
							<td width="40%" nowrap="nowrap"><s:checkbox name="cityFlag"
								id="city" /></td>  
						</tr>  -->
						<tr>
							<td colspan="3"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="4" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="8%"><label class="set" id="allowMultipleLab"
						name="allowMultipleLab" ondblclick="callShowDiv(this);"><%=label.get("allowMultipleLab")%></label>
					:</td>
					<td width="15%"><s:checkbox name="allowMultipleFlag"
						id="allowMultiple" /></td>
				</tr>
				<tr>
					<td width="8%"><label class="set" id="testMustStart"
						name="testMustStart" ondblclick="callShowDiv(this);"><%=label.get("testMustStart")%></label>
					</td>
					<td width="15%" nowrap="nowrap"><s:textfield
						name="hoursFormate" size="3" maxlength="3"
						onkeypress="return numbersOnly();" /> <label class="set"
						id="fromScheduleTime" name="fromScheduleTime"
						ondblclick="callShowDiv(this);"><%=label.get("fromScheduleTime")%></label>
					</td>
				</tr>
				<tr>
					<td width="8%"><label class="set" id="selectRecHead"
						name="selectRecHead" ondblclick="callShowDiv(this);"><%=label.get("selectRecHead")%></label>
					</td>
					<td width="15%" nowrap="nowrap"><s:hidden name="empCode" />
					<s:textfield
						name="selectRecruitHead" readonly="true" size="25" maxlength="50" /> <span
						id="empName"><img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="15"
						height="16"
						onclick="javascript:callsF9(500,325,'RecruitmentSettings_f9Employee.action');" />
					</span></td>

				</tr>
				<tr>
					<td width="37%"><label class="set" id="selectSignAuthority"
						name="selectSignAuthority" ondblclick="callShowDiv(this);"><%=label.get("selectSignAuthority")%></label>
					</td>
					<td width="15%" nowrap="nowrap"><s:hidden name="signAuthorityCode" />
					<s:textfield
						name="signAuthority" readonly="true" size="25" maxlength="50" /> <span
						id="signAuthorName"><img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="15"
						height="16"
						onclick="javascript:callsF9(500,325,'RecruitmentSettings_f9SignAuthority.action');" />
					</span></td>

				</tr>
				<tr>
					<td colspan="1" height="22" width="30%" nowrap="nowrap"><label
						name="leadtimeduration" id="leadtimeduration"
						ondblclick="callShowDiv(this);"><%=label.get("leadtimeduration")%></label>:</td>
					<td colspan="3" width="22%"><s:textfield name="leadTime"
						size="3" maxlength="3" onkeypress="return numbersOnly();" /> <label
						name="afterText" id="afterText" ondblclick="callShowDiv(this);"><%=label.get("afterText")%></label></td>
				</tr>
				
				
				<tr>
					<td width="8%"><label class="set" id="resume.ApprvlReqrd"
						name="resume.ApprvlReqrd" ondblclick="callShowDiv(this);"><%=label.get("resume.ApprvlReqrd")%></label>:
					</td>
					<td width="15%"><s:checkbox name="resumeApprvlReqrdFlag"
						onclick="callResumeApprvlReqrdFlag();" /> </td>
				</tr>
				<tr>
					<td width="8%"><label class="set" id="preOffr.ChckList"
						name="preOffr.ChckList" ondblclick="callShowDiv(this);"><%=label.get("preOffr.ChckList")%></label>:
					</td>
					<td width="15%"><s:checkbox name="preOffrChckListFlag"
						onclick="callPreOffrChckListFlag();" /></td>
				</tr>
				<tr>
					<td width="8%"><label class="set" id="preApmnt.ChckList"
						name="preApmnt.ChckList" ondblclick="callShowDiv(this);"><%=label.get("preApmnt.ChckList")%></label>:
					</td>
					<td width="15%"><s:checkbox name="preApmntChckListFlag"
						onclick="callpreApmntChckListFlag();" /> </td>
				</tr>
								
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="5%"><s:submit cssClass="save" theme="simple"
						action="RecruitmentSettings_saveRecruitmentSetting" value=" Save" />
						<s:submit cssClass="reset" theme="simple"
						action="RecruitmentSettings_reset" value=" Reset" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
callDuplicateDiv();
function callDuplicateDiv(){
	if(document.getElementById('paraFrm_duplicateDivFlag').checked == true){
			document.getElementById('flagsTable').style.display = '';
		}else{
			document.getElementById('flagsTable').style.display = 'none';
			document.getElementById('emailIdFlag').checked=false;
			document.getElementById('panNumberFlag').checked=false;
			document.getElementById('passportNumFlag').checked=false;
			document.getElementById('mobileNumFlag').checked=false;
		}
}
</script>
