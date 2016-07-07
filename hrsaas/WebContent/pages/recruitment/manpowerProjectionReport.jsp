<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ManpowerProjection" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="requisitionHidCode"/>
	<table width="100%" border="0" align="right" cellpadding="1"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Manpower
					Projection</strong></td>
					<td width="3%" valign="top" class="txt"><img
						src="../pages/images/recruitment/help.gif" width="16"
						height="16" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1">
				<tr>
					<td width="78%" colspan="3"><input type="button" class="token"
						action="ManpowerProjection_reset" value=" Show Report" onclick="return calReport();"/> <s:submit
						cssClass="reset" action="ManpowerProjection_reset" theme="simple"
						value="    Reset"  /></td>
					<td width="22%" align="right"></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td width="18%" class="formtext"><label class="set"
						id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :
					</td>
					<td width="20%" nowrap="nowrap"><s:hidden name="divisonCode" /><s:textfield
						name="division" size="25" readonly="true" theme="simple"
						maxlength="50" /></td><td width="20%"> <img
						src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'ManpowerProjection_f9Divison.action');">
					</td>
					<td width="18%" class="formtext"><label class="set"
						id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :
					</td>
					<td width="20%" nowrap="nowrap"><s:hidden name="branchCode" /><s:textfield
						name="branch" size="25" theme="simple" readonly="true"
						maxlength="70" /> </td><td width="20%"><img
						src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'ManpowerProjection_f9Branch.action');">
					</td>
				</tr>

				<tr>
					<td width="18%" class="formtext"><label class="set"
						id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :
					</td>
					<td width="20%" nowrap="nowrap"><s:hidden name="deptCode" /><s:textfield
						name="department" size="25" readonly="true" theme="simple"
						maxlength="100" /></td><td width="20%"> <img
						src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'ManpowerProjection_f9Department.action');">
					</td>
					<td width="18%" class="formtext"><label class="set"
						id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :
					</td>
					<td width="20%" nowrap="nowrap"><s:hidden name="designationCode" /><s:textfield
						name="designation" size="25" readonly="true" theme="simple"
						maxlength="100" /></td><td width="20%"> <img
						src="../pages/images/recruitment/search2.gif" height="15"
						align="absmiddle" width="16"
						onclick="javascript:callsF9(500,325,'ManpowerProjection_f9Designation.action');">
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1">
				<tr>
					<td width="78%" colspan="3"><input type="button" class="token"
						value=" Show Report" onclick="return calReport();"/> <s:submit
						cssClass="reset" action="ManpowerProjection_reset" theme="simple"
						value="    Reset"  /></td>
					<td width="22%" align="right"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
function calReport(){
	document.getElementById('paraFrm').action = 'ManpowerProjection_callJspView.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
}
</script>