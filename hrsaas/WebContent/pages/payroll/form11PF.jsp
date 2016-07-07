<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="Form11PF" validate="true" id="paraFrm" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Form11PF</strong></td>
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
					<td width="78%">&nbsp;</td>
					<td width="22%">
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
					</td>
				</tr>
				<tr>
					<td width="78%">
						<s:hidden name="reportType" />
						<s:submit cssClass="reset" action="Form3a_reset" theme="simple" value="    Reset" />
						<input type="button" class="token"  name = "mailReportBtn" onclick="mailReportFun();" value=" Mail Report "/> 
					</td>
					<td width="22%" align="right">
						Exported as : &nbsp;
						<img src="../pages/images/icons/file-pdf.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="return callReport('Form11Report_report.action','Pdf');" title="PDF">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
						<tr>
							<td height="22" width="20%" class="formtext">
								<label class="set" id="selectemployee" name="selectemployee"
								ondblclick="callShowDiv(this);"><%=label.get("selectemployee")%></label>
								<font color="red">*</font> :
							</td>
							<td>
								<s:hidden name="empId" /> 
								<s:hidden name="division" />
								<s:hidden name="empDesignation" /> 
								<s:textfield name="empToken" size="10" theme="simple" maxlength="70" readonly="true" />
								<s:textfield name="empName" size="30" theme="simple" maxlength="70" readonly="true" />
								<img src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18" onclick="javascript:callsF9(500,325,'Form11Report_f9Employee.action');">
							</td>
						</tr>
						<tr>
							<td height="22" class="formtext" colspan="2">
								Previous Companies Details
							</td>
						</tr>
						<tr>
							<td class="formtext">
								<label class="set" id="employer1" name="employer1" ondblclick="callShowDiv(this);"><%=label.get("employer1")%></label>
							</td>
							<td><s:textfield size="27" name="preEmployer1" readonly="true" /></td>
							<s:hidden name="preJoiningDt1" />
							<s:hidden name="preReleavingDt1" />
						</tr>

						<tr>
							<td class="formtext">
								<label class="set" id="pfacc1" 
								name="pfacc1" ondblclick="callShowDiv(this);"><%=label.get("pfacc1")%></label>
							<td><s:textfield size="27" name="pfAccNo1" readonly="true" /></td>
						</tr>
						<tr>
							<td class="formtext">
								<label class="set" id="employer2" 
								name="employer2" ondblclick="callShowDiv(this);"><%=label.get("employer2")%></label>
							</td>
							<td><s:textfield size="27" name="preEmployer2" readonly="true" /></td>
							<s:hidden name="preJoiningDt2" />
							<s:hidden name="preReleavingDt2" />
						</tr>

						<tr>
							<td class="formtext">
								<label class="set" id="pfacc2" 
								name="pfacc2" ondblclick="callShowDiv(this);"><%=label.get("pfacc2")%></label>
							</td>
							<td><s:textfield size="27" name="pfAccNo2" readonly="true" /></td>
						</tr>
						
						<tr>
							<td height="22" width="20%" class="formtext">
								<label class="set" id="selectsigningauth" name="selectsigningauth"
								ondblclick="callShowDiv(this);"><%=label.get("selectsigningauth")%>								
								</label>
								<font color="red">*</font> :
							</td>
							<td>
								<s:hidden name="signAuthId" /> 
								<s:textfield name="signAuthToken" size="10" theme="simple" maxlength="70" readonly="true" /> 
								<s:textfield name="signAuthName" size="30" theme="simple" maxlength="70" readonly="true" />
								<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" 
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'Form11Report_f9SigningAuthority.action');">
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%">
						<s:hidden name="reportType" />
						<s:submit cssClass="reset" action="Form3a_reset" theme="simple" value="    Reset" />
						<input type="button" class="token"  name = "mailReportBtn" onclick="mailReportFun();" value=" Mail Report "/> 
					</td>
					<td width="22%" align="right">
						Exported as : &nbsp;
						<img src="../pages/images/icons/file-pdf.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="return callReport('Form11Report_report.action','Pdf');" title="PDF">
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	function callReport(action,type) {
		var employee=document.getElementById('paraFrm_empName').value;
		var signingauth=document.getElementById('paraFrm_signAuthName').value;
		if(employee==""){
		  alert("Please select "+document.getElementById('selectemployee').innerHTML.toLowerCase());	  
		  return false;
		}
		if(signingauth==""){
		  alert("Please select "+document.getElementById('selectsigningauth').innerHTML.toLowerCase());	  
		  return false;
		}
		else {
			//callDropdown('mailReportBtn',200,250,'Form3a_f9reportType.action','false');
			var frm = document.getElementById("paraFrm");
			frm.action = action;
			frm.reportType.value = type;
			frm.submit();
			return true;
		}	
	}
   
	function mailReportFun(){
		var employee=document.getElementById('paraFrm_empName').value;
		var signingauth=document.getElementById('paraFrm_signAuthName').value;
		if(employee==""){
		  alert("Please select "+document.getElementById('selectemployee').innerHTML.toLowerCase());	  
		  return false;
		}
		if(signingauth==""){
		  alert("Please select "+document.getElementById('selectsigningauth').innerHTML.toLowerCase());	  
		  return false;
		}
		else {
			//callDropdown('mailReportBtn',200,250,'Form3a_f9reportType.action','false');
			var frm = document.getElementById("paraFrm");
			frm.action = "Form11Report_mailReport.action";
			frm.submit();
			return true;
		}	
	}
</script>

