<!-- @author: Reeba Joseph @date: 22 OCT 2009 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ExtraWorkingBenefits" method="post" id="paraFrm" validate="true"
	target="main" theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Apply
					Benefits To </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="benefitsID" />
		<s:hidden name="benefitSettingID" />
		<s:hidden name="myPage" />
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
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
					<td colspan="1" width="20%" class="formtext"><label
						class="set" name="division" id="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<td colspan="3" width="80%"><s:textfield size="25"
						name="divisionName" readonly="true" /> <s:hidden
						name="divisionCode" /> <img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callDivision();" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext"><label
						class="set" name="department" id="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="25"
						name="deptName" readonly="true" /> <s:hidden name="deptCode" />
					<img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callDept();" /></td>
					<td colspan="1" width="20%" class="formtext"><label
						class="set" name="branch" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="25"
						name="branchName" readonly="true" /> <s:hidden name="branchCode" />
					<img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callBranch();" /></td>
				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext"><label
						name="employee.type" id="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="25"
						name="empTypeName" readonly="true" /> <s:hidden
						name="empTypeCode" /> <img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callEmpType();" /></td>
					<td colspan="1" width="20%" class="formtext"><label
						class="set" name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield size="25"
						name="desgName" readonly="true" /> <s:hidden name="desgCode" />
					<img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0" onclick="javascript:callDesignation();" /></td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
function callDivision(){
	callsF9(500,325,'ExtraWorkingBenefits_f9division.action');
}

function callEmpType(){
	callsF9(500,325,'ExtraWorkingBenefits_f9empType.action');
}

function callDept(){
	callsF9(500,325,'ExtraWorkingBenefits_f9dept.action');
}

function callBranch(){
	callsF9(500,325,'ExtraWorkingBenefits_f9branch.action');
}

function callDesignation(){
	callsF9(500,325,'ExtraWorkingBenefits_f9designation.action');
}

function saveFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'ExtraWorkingBenefits_saveSettings.action';
	document.getElementById('paraFrm').submit();
}

function resetFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'ExtraWorkingBenefits_resetSettings.action';
	document.getElementById('paraFrm').submit();
}

function backFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = 'ExtraWorkingBenefits_resetSettings.action';
	document.getElementById('paraFrm').submit();
}
</script>