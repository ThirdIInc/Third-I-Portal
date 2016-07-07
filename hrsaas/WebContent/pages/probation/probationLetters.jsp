<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="ProbationLetters" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
	<s:hidden name="dataPath" />	
	<s:hidden name="probationCode" /> 
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Probation Letters </strong></td>
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
							height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callsF9(500,325,'ProbationLetters_f9actionEmp.action'); "></td>
						
				</tr>
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="signAuthName" name="signAuthName" ondblclick="callShowDiv(this);"><%=label.get("signAuthName")%></label>  
								  :<s:hidden name="signAuthCode"/></td>
						<td width="75%" colspan="3"><s:textfield name="signAuthToken"
								size="10" theme="simple" readonly="true" /><s:textfield
								name="signAuthName" size="50" theme="simple"
								readonly="true" /><img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callsF9(500,325,'ProbationLetters_f9signAutho.action'); " ></td>
						
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
								id="confLetter" name="confLetter"
								ondblclick="callShowDiv(this);"><%=label.get("confLetter")%></label></strong></td>
				</tr>
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="confTemplateName" name="templateName3" ondblclick="callShowDiv(this);"><%=label.get("templateName")%></label> <font
								color="red">*</font> :<s:hidden name="confTempCode"/></td>
						<td width="30%" colspan="2"><s:textfield
								name="confTempName" size="30" theme="simple"
								readonly="true" /><img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callsF9(500,325,'ProbationLetters_f9actionConfirmation.action'); " ></td>
						
				</tr>	
				<tr>
					<td colspan="2" width="50%" align="right">
						<s:submit
							value=" Generate Letter Doc "
							action="ProbationLetters_generateConfirmationLetter"
							onclick="return callConfirmation();" cssClass="token"></s:submit>
						<s:submit
							value=" Generate Letter Pdf "
							action="ProbationLetters_generateConfirmationLetterPdf"
							onclick="return callConfirmation();" cssClass="token"></s:submit>
					</td>
					<td colspan="1" width="30%" align="left">
						<s:submit
							value=" Send Mail "
							action="ProbationLetters_sendmailConfTemp"
							onclick="return callConfirmation();" cssClass="token"></s:submit>
						</td>
					
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
								id="extensionLetter" name="extensionLetter"
								ondblclick="callShowDiv(this);"><%=label.get("extensionLetter")%></label></strong></td>
				</tr>
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="extensionTemplateName" name="templateName2" ondblclick="callShowDiv(this);"><%=label.get("templateName")%></label> <font
								color="red">*</font> :<s:hidden name="extTempCode"/></td>
						<td width="30%" colspan="2"><s:textfield
								name="extTempName" size="30" theme="simple"
								readonly="true" /><img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callsF9(500,325,'ProbationLetters_f9actionExtension.action'); " ></td>
						<td colspan="1" width="40%"><input type="button" value='Generate Letter' class="report" onclick="return callExtension();"/></td>
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
								id="terminationLetter" name="terminationLetter"
								ondblclick="callShowDiv(this);"><%=label.get("terminationLetter")%></label></strong></td>
				</tr>
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="terminationTemplateName" name="templateName1" ondblclick="callShowDiv(this);"><%=label.get("templateName")%></label> <font
								color="red">*</font> :<s:hidden name="termTempCode"/></td>
						<td width="30%" colspan="2"><s:textfield
								name="termTempName" size="30" theme="simple"
								readonly="true" /><img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callsF9(500,325,'ProbationLetters_f9actionTermination.action'); " ></td>
						<td colspan="1" width="40%"><input type="button" value='Generate Letter' class="report" onclick="return callTermination();"/></td>
				</tr>		
				
			</table>
			</td>
		</tr>
 	
		
</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">

		function callTermination(){
		if(document.getElementById('paraFrm_empCode').value==''){
		alert("Select "+document.getElementById('employee').innerHTML.toLowerCase());
		return false;
		}
		if(document.getElementById('paraFrm_termTempName').value==''){
		alert("Select "+document.getElementById('terminationTemplateName').innerHTML.toLowerCase());
		return false;
		}
		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action='ProbationLetters_generateTerminationLetter.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main"; 
		}
		
		
		function callExtension(){
		if(document.getElementById('paraFrm_empCode').value==''){
		alert("Select "+document.getElementById('employee').innerHTML.toLowerCase());
		return false;
		}
		if(document.getElementById('paraFrm_extTempName').value==''){
		alert("Select "+document.getElementById('extensionTemplateName').innerHTML.toLowerCase());
		return false;
		}	
		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action='ProbationLetters_generateExtensionLetter.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main"; 
		}
		
		
		function callConfirmation(){
		if(document.getElementById('paraFrm_empCode').value==''){
		alert("Select "+document.getElementById('employee').innerHTML.toLowerCase());
		return false;
		}
		if(document.getElementById('paraFrm_confTempName').value==''){
		alert("Select "+document.getElementById('confTemplateName').innerHTML.toLowerCase());
		return false;
		}
		/*document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action='ProbationLetters_generateConfirmationLetter.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main"; */
		}
				
		
		
	</script>