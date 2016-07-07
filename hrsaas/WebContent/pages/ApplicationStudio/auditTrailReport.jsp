<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="AuditTrailReport" method="post" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Audit
					Trail Report</strong></td>
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
					<td width="78%" colspan="1"><input type="button" class="token"
						onclick="return callReport();" value=" Report"  />


					<s:submit cssClass="reset" action="AuditTrailReport_clear"
						theme="simple" value="    Reset" /></td>
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
					<td colspan="1" width="20%" class="formtext" height="22"><label name="fDate" id="fDate"
								ondblclick="callShowDiv(this);"><%=label.get("fDate")%></label>:</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						onkeypress="return numbersWithHiphen();" name="fromDate"
						maxlength="10" size="25" /> <s:a
						href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
					<td colspan="1" width="20%" class="formtext"><label name="tDate" id="tDate"
								ondblclick="callShowDiv(this);"><%=label.get("tDate")%></label> :</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						onkeypress="return numbersWithHiphen();" name="toDate"
						maxlength="10" size="25" /> <s:a
						href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label name="formname" id="formname"
								ondblclick="callShowDiv(this);"><%=label.get("formname")%></label> :</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						readonly="true" name="moduleName" size="25" /><img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'AuditTrailReport_f9moduleName.action');"></td>
					<td colspan="1" width="20%"><label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> <font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:select theme="simple" name="reportType" cssStyle="width:155"
							list="#{'0':'--Select--','Pdf':'Pdf','Xls':'Xls'}" /></td>
				</tr>

				 
			</table>
			<tr>
				<td>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label name="type" id="type"
								ondblclick="callShowDiv(this);"><%=label.get("type")%></label>
					:</td>
					<td colspan="3" width="80%"><s:select theme="simple"
						name="status" cssStyle="width:150"
						list="#{'E':'Employee Affected','C':'Changed By Employee'}" /></td>



				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label name="division" id="division"
								ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						readonly="true" name="divName" size="25" /><s:hidden
						name="divCode" /><img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'AuditTrailReport_f9divaction.action');"></td>
					<td colspan="1" width="20%" class="formtext" height="22"><label name="department" id="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						readonly="true" name="deptName" size="25" /><s:hidden
						name="deptCode" /><img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'AuditTrailReport_f9deptaction.action');"></td>

				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext" height="22"><label name="branch" id="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						readonly="true" name="branchName" size="25" /><s:hidden
						name="branchCode" /><img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'AuditTrailReport_f9branchaction.action');"></td>
					<td colspan="1" width="20%" class="formtext" height="22"><label name="designation" id="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td colspan="1" width="30%"><s:textfield theme="simple"
						readonly="true" name="desgName" size="25" /><s:hidden
						name="desgCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'AuditTrailReport_f9desigantionaction.action');"></td>

				</tr>
				<tr>
					<td height="15" class="formhead" colspan="4" align="center"><b>(OR)</b>
					&nbsp;</td>
				</tr>


				<tr>
					<td colspan="1" width="20%" class="formtext"><label name="empaffect" id="empaffect"
								ondblclick="callShowDiv(this);"><%=label.get("empaffect")%></label>
					:</td>
					<td colspan="3" width="80%"><s:textfield name="tokenNo"
						size="25" readonly="true" /><s:textfield size="60"
						name="employeeName" readonly="true" /> <s:hidden
						name="employeeCode" /> <img class="iconImage"
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" border="0"
						onclick="javascript:callsF9(500,325,'AuditTrailReport_f9employee.action');" /></td>


				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext"><label name="changedbyemp" id="changedbyemp"
								ondblclick="callShowDiv(this);"><%=label.get("changedbyemp")%></label> :</td>
					<td colspan="3" width="80%"><s:textfield name="emptokenNo"
						size="25" readonly="true" /><s:textfield size="60" name="empName"
						readonly="true" /> <s:hidden name="empCode" /> <img
						class="iconImage" src="../pages/images/recruitment/search2.gif"
						width="16" height="15" border="0"
						onclick="javascript:callsF9(500,325,'AuditTrailReport_f9employeeaction.action');" /></td>


				</tr>
				</table>
			</td>
		</tr>

	</table>

</s:form>
<script type="text/javascript">

function callReport(){
	try
	
	{
	var frmDate = document.getElementById("paraFrm_fromDate").value;
  	var toDate  = document.getElementById("paraFrm_toDate").value;
   	
		if(!validateDate('paraFrm_fromDate', 'fDate'))
		{
  			return false;
  		}	
  	
		if(!validateDate('paraFrm_toDate', 'tDate'))
		{
  			return false;
  		}
	 if ((frmDate!="") && (toDate!="")) {
  			if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_frmDate', 'fDate', 'tDate'))
			return false;
	}
	
	if(document.getElementById('paraFrm_reportType').value=='0'){
		alert('Please select '+document.getElementById('report.type').innerHTML);
		document.getElementById('paraFrm_reportType').focus();
		return false;
	}
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='AuditTrailReport_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	}
	catch(e)
	{
		//alert(e);
	}
	}



</script>


