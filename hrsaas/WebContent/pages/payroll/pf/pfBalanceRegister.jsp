<%@include file="/pages/common/labelManagement.jsp"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="PFBalanceRegister" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">PF Balance
					Register </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<td><s:if test="%{viewFlag}">
						<input type="button" class="token" onclick="return callForReport();"
							value=" Report " />
					</s:if> <input type="button" class="token" onclick="return callReset()"
						value=" Reset " /></td>
					<td>
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						

						<tr>

							<td colspan="1" width="20%"><label id="division"
								name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
								color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:hidden name="divCode" />
							<s:textfield name="divName" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'PFBalanceRegister_f9Div.action');">

							</td>

							<td colspan="1" width="20%"><label id="branch" name="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden
								name="branchCode" /> <s:textfield
								name="branchName" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'PFBalanceRegister_f9BranchAction.action');">

							</td>
						</tr>



						<tr>

							<td colspan="1" width="20%"><label id="department"
								name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td><s:hidden name="deptCode" /> <s:textfield
								name="deptName" theme="simple" readonly="true"
								maxlength="50" size="30" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'PFBalanceRegister_f9Deptaction.action');">
							</td>
							<td colspan="1" width="20%"><label id="report.type"
								name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> :</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="reportType" cssStyle="width:152"
								list="#{'XLS':'Xls','PDF':'Pdf'}" /></td><s:hidden name="sysDate"></s:hidden>
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
autoDate();
 function autoDate () {
 try{
	var tDay = new Date();
	var tMonth = tDay.getMonth()+1;
	var tDate = tDay.getDate();
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10) tDate = "0"+tDate;
			document.getElementById("paraFrm_sysDate").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
				}catch(e){alert(e);}
}

function callForReport(){
	var fields=["paraFrm_divName","paraFrm_branchName"];
	var labels=["division","branch"];
	var flag=["select","select"];
	
	if(!validateBlank(fields,labels,flag)){
		return false;
	}
	callReport("PFBalanceRegister_getPFBalanceReport.action");
	//document.getElementById('paraFrm').action="PFBalanceRegister_getPFBalanceReport.action"
}

function callReset(){
	document.getElementById('paraFrm_divName').value="";
	document.getElementById('paraFrm_divCode').value="";
	document.getElementById('paraFrm_branchName').value="";
	document.getElementById('paraFrm_branchCode').value="";
	document.getElementById('paraFrm_deptCode').value="";
	document.getElementById('paraFrm_deptName').value="";
	document.getElementById('paraFrm_reportType').value="PDF";
}
</script>

