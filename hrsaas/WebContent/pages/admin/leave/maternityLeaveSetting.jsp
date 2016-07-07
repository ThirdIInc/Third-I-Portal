<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="MaternityLeaveSetting" method="post" id="paraFrm"
	theme="simple" target="main">

	<table width="100%" border="0" class="formbg">
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="100%" class="txt"><strong class="text_head">Maternity
					Leave Setting </strong></td>
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
					<td width="78%"><s:submit cssClass="save"
						action="MaternityLeaveSetting_save" value=" Save"
						onclick="return formValidate();" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%">
					<table width="98%" border="0" cellpadding="1" cellspacing="2">
						<tr>
							<td width="12%" colspan="1"><label name="conf1" id="conf1"
								ondblclick="callShowDiv(this);"><%=label.get("conf1")%></label>
							:</td>
							<td width="25%" colspan="2"><s:textfield name="leaveType"
								readonly="true" /> <s:hidden name="LeaveName" /> <s:hidden
								name="leaveTypecode" /> <img src="../pages/images/search2.gif"
								height="16" align="absmiddle" width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'MaternityLeaveSetting_f9LeaveType.action')">
							</td>

						</tr>
						<tr>
							<td width="12%" colspan="1"><label name="conf2" id="conf2"
								ondblclick="callShowDiv(this);"><%=label.get("conf2")%></label>
							:</td>
							<td width="25%" colspan="2"><s:textfield
								name="noofLeavedays" maxlength="3" onkeypress="numbersOnly();" /></td>

						</tr>
						<tr>
							<td width="12%" colspan="1"><label name="conf3" id="conf3"
								ondblclick="callShowDiv(this);"><%=label.get("conf3")%></label>
							:</td>
							<td width="25%" colspan="2"><s:textfield
								name="minServiceperiod" maxlength="3" onkeypress="numbersOnly();"/> <label name="conf6" id="conf6"
								ondblclick="callShowDiv(this);"><%=label.get("conf6")%></label>
							</td>
						</tr>
						<tr>
							<td width="12%" colspan="1"><label name="conf4" id="conf4"
								ondblclick="callShowDiv(this);"><%=label.get("conf4")%></label>
							:</td>
							<td width="25%" colspan="2"><s:select
								list="#{'Min':'Min','Max':'Max'}" name="preMaternityType"></s:select>
							<s:textfield name="preMaternitydays" size="10" maxlength="3" onkeypress="numbersOnly();"/> Days</td>

						</tr>
						<tr>
							<td width="12%" colspan="1"><label name="conf5" id="conf5"
								ondblclick="callShowDiv(this);"><%=label.get("conf5")%></label>
							:</td>
							<td width="25%" colspan="2"><s:select
								list="#{'Min':'Min','Max':'Max'}" name="postMaternityType"></s:select>
							<s:textfield name="postMaternitydays" size="10"  maxlength="3" onkeypress="numbersOnly();"/> Days</td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>



		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%">
					<table width="98%" border="0" cellpadding="1" cellspacing="2">
						<tr>
							<td width="12%" colspan="1"><label name="conf7" id="conf7"
								ondblclick="callShowDiv(this);"><%=label.get("conf7")%></label>
							:</td>
							<td width="25%" colspan="2"><s:textfield name="subleaveType"
								readonly="true" /> <s:hidden name="subLeaveTypecode" /> <img
								src="../pages/images/search2.gif" height="16" align="absmiddle"
								width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'MaternityLeaveSetting_f9subLeaveType.action')">
							</td>

						</tr>
						<tr>
							<td width="12%" colspan="1"><label name="conf2" id="conf12"
								ondblclick="callShowDiv(this);"><%=label.get("conf2")%></label>
							:</td>
							<td width="25%" colspan="2"><s:textfield
								name="subNoofLeaves" onkeypress="numbersOnly();" maxlength="3"/></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
function formValidate()
{

 var maternitycode=document.getElementById('paraFrm_leaveTypecode').value;
 var paternitycode=document.getElementById('paraFrm_subLeaveTypecode').value;
 
 if(maternitycode=="")
 {
   alert("please select "+document.getElementById('conf1').innerHTML.toLowerCase());
   return false;
 }
 if(paternitycode=="")
 {
	 alert("please select "+document.getElementById('conf7').innerHTML.toLowerCase());
  	 return false;
 }


}
</script>
