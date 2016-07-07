<%@ taglib prefix="s" uri="/struts-tags"%>
 <%@include file="/pages/common/labelManagement.jsp" %>
<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="ReportingHandover" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">
		<s:hidden name="reportingHdrCode"/>
		<s:hidden name="reportingDtlCode"/>
		
		 <tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reporting
					Hand Over </strong></td>
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
					<td>
					<!--<s:submit name="view" value="Show Structure" cssClass="token" action="ReportingHandover_showStructure" 
							theme="simple" onclick="return validateShowStructure();"/>-->
						<s:submit name="process" value="Process" cssClass="token" action="ReportingHandover_handoverStructure" 
							theme="simple" onclick="return validateProcess();"/>
						<s:submit name="reset" value="Reset" cssClass="token" action="ReportingHandover_reset" theme="simple"/>
					</td>
			        <td width="22%" class="txt"><div align="right"><span class="style2"><font color="red">*</font></span> Indicates Required </div></td>
				</table>
			</td>
		</tr>
        
        <tr>
			<td width="100%" colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="100%" colspan="3" class="formhead"><strong class="forminnerhead">Reporting Hand Over</strong></td>
					</tr>
					
					<tr>
						<td colspan="1" width="32%"><label  class = "set" name="transfer.frm.emp" id="transfer.frm.emp" ondblclick="callShowDiv(this);"><%=label.get("transfer.frm.emp")%></label><font color="red">*</font> :</td>
						<td colspan="3" width="60%" nowrap="nowrap"><s:hidden theme="simple" name="fromEmpCode" /> 
							<s:textfield theme="simple" name="fromEmpTokenNo" size="10" readonly="true" />
							<s:textfield theme="simple" name="fromEmpName" size="50" maxlength="30" readonly="true" /> 
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="18" theme="simple"
								onclick="javascript:callsF9(500,325,'ReportingHandover_f9SelectFromEmployee.action'); "></td>
					</tr>
					
					<tr>
						<td colspan="1" width="32%"><label  class = "set" name="transfer.to.emp" id="transfer.to.emp" ondblclick="callShowDiv(this);"><%=label.get("transfer.to.emp")%></label><font color="red">*</font> :</td>
						<td colspan="3" width="60%" nowrap="nowrap"><s:hidden theme="simple" name="toEmpCode" /> 
							<s:textfield theme="simple" name="toEmpTokenNo" size="10" readonly="true" />
							<s:textfield theme="simple" name="toEmpName" size="50" maxlength="30" readonly="true" /> 
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="18" theme="simple"
								onclick="javascript:callsF9(500,325,'ReportingHandover_f9SelectToEmployee.action'); "></td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td td width="100%" colspan="4">
				<table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
					<tr>
						<td class="formth" width="7%" height="22" valign="top"><label  class = "set" name="srno" id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
						<td class="formth" width="47%" height="22" valign="top"><label  class = "set" name="struct.defined" id="struct.defined" ondblclick="callShowDiv(this);"><%=label.get("struct.defined")%></label>
						</td>
						<td class="formth" width="15%" height="22" valign="top"><label  class = "set" name="app.type" id="app.type" ondblclick="callShowDiv(this);"><%=label.get("app.type")%></label>
						</td>
						<td class="formth" width="16%" height="22" valign="top"><label  class = "set" name="appr.type" id="appr.type" ondblclick="callShowDiv(this);"><%=label.get("appr.type")%></label>
						</td>
						<td class="formth" width="13%" height="22" valign="top"><label  class = "set" name="appr.level" id="appr.level" ondblclick="callShowDiv(this);"><%=label.get("appr.level")%></label>
						</td>
					</tr>
					
					<%int i=0; %>
					<s:iterator value="approverDataList" status="stat">
						<s:hidden name="structureDefinedFor"/>
						<s:hidden name="reportingType"/>
						<s:hidden name="approverType"/>
						<s:hidden name="level"/>
						<tr>
							<td class="border2" width="7%"><%=i+1 %>&nbsp;</td>
							<td class="border2" width="47%"><s:property value="structureDefinedFor"/>&nbsp;</td>
							<td class="border2" width="15%"><s:property value="reportingType"/>&nbsp;</td>
							<td class="border2" width="16%"><s:property value="approverType"/>&nbsp;</td>
							<td class="border2" width="13%"><s:property value="level"/>&nbsp;</td>
						</tr>
						<%i++; %>
					</s:iterator>
				</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">

function validateShowStructure(){
	if(document.getElementById('paraFrm_fromEmpName').value == ""){
		alert('Please select '+document.getElementById('transfer.frm.emp').innerHTML.toLowerCase());
		document.getElementById('paraFrm_fromEmpName').focus();
		return false;
	}
	return true;	
}

function validateProcess(){
	var fromEmpName = document.getElementById("paraFrm_fromEmpName").value;
	var toEmpName   = document.getElementById('paraFrm_toEmpName').value;
	
	if(!validateShowStructure())return false;
	
	if(toEmpName == ""){
		alert('Please select '+document.getElementById('transfer.to.emp').innerHTML.toLowerCase());
		document.getElementById('paraFrm_toEmpName').focus();
		return false;
	}
	var conf = confirm("The reporting hierarchy of below listed  will be changed from "+fromEmpName+" to "+toEmpName+". Do you really want to continue ?");
	
	if(!conf)return false;
}
</script>

