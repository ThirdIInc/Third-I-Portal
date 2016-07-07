<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="SettlementLetters" validate="true" id="paraFrm"
	theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Settlement Letters </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
						
				
					<tr>
						<td width="78%"></td>
						<td width="22%">
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
						</td>
					</tr>
				
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> <font
								color="red">*</font> :<s:hidden name="empCode"/></td>
						<td width="75%" colspan="3"><s:textfield name="empToken"
								size="10" theme="simple" readonly="true" /><s:textfield
								name="empName" size="50" theme="simple"
								readonly="true" /><img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callsF9(500,325,'SettlementLetters_f9actionEmp.action'); "></td>
						
				</tr>
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="signAuth" name="signAuth" ondblclick="callShowDiv(this);"><%=label.get("signAuth")%></label> :<s:hidden name="signAuthCode"/></td>
						<td width="75%" colspan="3"><s:textfield name="signAuthToken"
								size="10" theme="simple" readonly="true" /><s:textfield
								name="signAuthName" size="50" theme="simple"
								readonly="true" /><img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="return callSignAuth();" ></td>
						
				</tr>	
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="signAuthSec" name="signAuthSec" ondblclick="callShowDiv(this);"><%=label.get("signAuthSec")%></label> :<s:hidden name="signAuthSecCode"/></td>
						<td width="75%" colspan="3"><s:textfield name="signAuthSecToken"
								size="10" theme="simple" readonly="true" /><s:textfield
								name="signAuthSecName" size="50" theme="simple"
								readonly="true" /><img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="return callSignAuthSec();" ></td>
						
				</tr>	
				
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				
				<tr>
							<td colspan="4" class="text_head"><strong
								class="forminnerhead"><label class="set"
								id="resAcceptLetter" name="resAcceptLetter"
								ondblclick="callShowDiv(this);"><%=label.get("resAcceptLetter")%></label></strong></td>
				</tr>
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="resAccTemplateName" name="templateName" ondblclick="callShowDiv(this);"><%=label.get("templateName")%></label> <font
								color="red">*</font> :<s:hidden name="resAccTempCode"/></td>
						<td width="30%" colspan="2"><s:textfield
								name="resAccTempName" size="30" theme="simple"
								readonly="true" /><img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="return callTemplateF9('resAcc')"></td>
						<td colspan="1" width="40%">
							<input type="button" value='Generate Letter' class="report" onclick="return generateLetter('resAcc');"/>
							
						</td>
				</tr>
				
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				
				
				
				<tr>
							<td colspan="4" class="text_head"><strong
								class="forminnerhead"><label class="set"
								id="expLetter" name="expLetter"
								ondblclick="callShowDiv(this);"><%=label.get("expLetter")%></label></strong></td>
				</tr>
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="expTemplateName" name="templateName" ondblclick="callShowDiv(this);"><%=label.get("templateName")%></label> <font
								color="red">*</font> :<s:hidden name="expTempCode"/></td>
						<td width="30%" colspan="2">
							<s:textfield name="expTempName" size="30" theme="simple" readonly="true" />
							<img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" onclick="return callTemplateF9('exp')">
						</td>
						<td colspan="1" width="40%">
							<input type="button" value='Generate Letter Doc' class="report" onclick="return generateLetter('exp');"/>
							<input type="button" value='Generate Letter Pdf' class="report" onclick="return generateLetter('expPdf');"/>
						</td>
				</tr>
				
							
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				
				
				
				<tr>
							<td colspan="4" class="text_head"><strong
								class="forminnerhead"><label class="set"
								id="relieveLetter" name="relieveLetter"
								ondblclick="callShowDiv(this);"><%=label.get("relieveLetter")%></label></strong></td>
				</tr>
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="relieveTemplateName" name="templateName" ondblclick="callShowDiv(this);"><%=label.get("templateName")%></label> <font
								color="red">*</font> :<s:hidden name="relieveTempCode"/></td>
						<td width="30%" colspan="2"><s:textfield
								name="relieveTempName" size="30" theme="simple"
								readonly="true" /><img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="return callTemplateF9('relieve')"></td>
						<td colspan="1" width="40%">
							<input type="button" value='Generate Letter Doc' class="report" onclick="return generateLetter('relieve');"/>
							<input type="button" value='Generate Letter Pdf' class="report" onclick="return generateLetter('relievePdf');"/>
						</td>
				</tr>
				
							
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				
				
				<tr>
							<td colspan="4" class="text_head"><strong
								class="forminnerhead"><label class="set"
								id="terminateLetter" name="terminateLetter"
								ondblclick="callShowDiv(this);"><%=label.get("terminateLetter")%></label></strong></td>
				</tr>
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="terminateTemplateName" name="templateName" ondblclick="callShowDiv(this);"><%=label.get("templateName")%></label> <font
								color="red">*</font> :<s:hidden name="terminateTempCode"/></td>
						<td width="30%" colspan="2"><s:textfield
								name="terminateTempName" size="30" theme="simple"
								readonly="true" /><img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="return callTemplateF9('terminate')"></td>
						<td colspan="1" width="40%"><input type="button" value='Generate Letter' class="report" onclick="return generateLetter('terminate');"/></td>
				</tr>	
				
							
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				
				
				
				<tr>
							<td colspan="1" width="25%" ><strong
								class="forminnerhead"><label class="set"
								id="settleLetter" name="settleLetter"
								ondblclick="callShowDiv(this);"><%=label.get("settleLetter")%></label></strong></td>
							<td width="30%" colspan="2">
							<td colspan="1" width="40%"><input type="button" value='Generate Letter' class="report" onclick="return generateLetter('settle');"/></td>
				</tr>
				
							
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				
				
				
				<tr>
							<td colspan="1" width="25%"><strong
								class="forminnerhead"><label class="set"
								id="exitInterviewLetter" name="exitInterviewLetter"
								ondblclick="callShowDiv(this);"><%=label.get("exitInterviewLetter")%></label></strong></td>
							<td width="30%" colspan="2">
							<td colspan="1" width="40%"><input type="button" value='Generate Letter' class="report" onclick="return generateLetter('exit');"/></td>
				</tr>
							
							
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				
				
				
				<tr>
							<td colspan="1" width="25%" ><strong
								class="forminnerhead"><label class="set"
								id="deptClearanceLetter" name="deptClearanceLetter"
								ondblclick="callShowDiv(this);"><%=label.get("deptClearanceLetter")%></label></strong></td>
							<td width="30%" colspan="2">
							<td colspan="1" width="40%"><input type="button" value='Generate Letter' class="report" onclick="return generateLetter('deptClear');"/></td>
				</tr>
				
							
			</table>
			</td>
		</tr>
		
		<s:hidden name='letterType'></s:hidden>
		<s:hidden name='settleCode'></s:hidden>
		<s:hidden name='exitCode'></s:hidden>
		<s:hidden name='resignCode'></s:hidden>
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">

function generateLetter(letterType){
	var settleCode=document.getElementById('paraFrm_settleCode').value;
	var exitCode=document.getElementById('paraFrm_exitCode').value;
	var resignCode = document.getElementById('paraFrm_resignCode').value;
	var actionName ='SettlementLetters_previewLetter.action';
	
	if(document.getElementById('paraFrm_empName').value==''){
		alert('Please select '+document.getElementById('employee').innerHTML);
		document.getElementById('paraFrm_empName').focus();
		return false;
	}
	if(letterType=='settle'){
		if(settleCode=='0' || settleCode==''){
			alert('Settlement not done for this '+document.getElementById('employee').innerHTML);
			return false;
		}
		actionName ='SettlmentDetails_report.action?settCode='+settleCode;
	}else if(letterType=='exit'){
		if(exitCode=='0' || exitCode==''){
			alert('Exit Interview not done for this '+document.getElementById('employee').innerHTML);
			return false;
		}
		actionName ='ExitInterview_report.action?exIntcode='+exitCode;
	}
	else if(letterType=='deptClear'){ 
		actionName ='DepartmentClearance_report.action?resignCode='+resignCode;
	}
	else {
		var letterTypeTemp=letterType;
		if(letterType=='exp'||letterType=='expPdf'){ 
		    letterTypeTemp="exp";
		}
		
		if(letterType=='relieve'||letterType=='relievePdf'){ 
		    letterTypeTemp="relieve";
		}
		if(document.getElementById('paraFrm_'+letterTypeTemp+'TempName').value==''){
			alert('Please select '+document.getElementById(letterTypeTemp+'TemplateName').innerHTML);
			document.getElementById('paraFrm_'+letterTypeTemp+'TempName').focus();
			return false;
		}
	}
	if(letterType !='terminate'){
		document.getElementById('paraFrm_letterType').value=letterType;
		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action=actionName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main"; 
	}else{
		return false;
	}

	
	
}
function callTemplateF9(letterType){
//alert(letterType);
	document.getElementById('paraFrm_letterType').value=letterType;
	//alert('bean value'+document.getElementById('paraFrm_letterType').value);
	callsF9(500,325,'SettlementLetters_f9actionTemplate.action');
}

function callSignAuth(){
	if(document.getElementById('paraFrm_empName').value==''){
		alert('Please select '+document.getElementById('employee').innerHTML);
		document.getElementById('empName').focus();
		return false;
	}
		javascript:callsF9(500,325,'SettlementLetters_f9actionSignAuth.action');
	}

function callSignAuthSec(){
	if(document.getElementById('paraFrm_empName').value==''){
		alert('Please select '+document.getElementById('employee').innerHTML);
		document.getElementById('empName').focus();
		return false;
	}
		javascript:callsF9(500,325,'SettlementLetters_f9actionSignAuthSec.action');
	}
	
</script>