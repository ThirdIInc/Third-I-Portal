<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ApplCodeTemplate" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%" cellpadding="2" cellspacing="1">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					<s:if test="mainPageFlag">
				          Application Code Template 
				          </s:if> <s:else>
				          Application Code Template For
				           <%
 			String structureType = (String) request
 			.getAttribute("applicationType");
 	out.println(structureType);
 %>
					</s:else> </strong></td>
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
			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>

					<td><s:if test="mainPageFlag"></s:if><s:else>
						<s:if test="insertFlag">
							<input type="button" class="save"
								onclick="return callValidate();" value="  Save " />
						</s:if>
						<s:if test="deleteFlag">
							<s:if test='deleteTempl'>
								<input type="button" class="delete"
									onclick="return callDeleteTempl();" value="  Delete" />
							</s:if>
						</s:if>
						<!--  <input type="button" class="token"  onclick="resetField()" value="Reset"/>-->
						<input type="button" class="back" onclick="goBack()"
							value="  Back" />
					</s:else></td>

					<td>
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="mainPageFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">

					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="2"
							cellspacing="2">
							



							<tr>
								<td colspan="1" width="20%"><label class="set"
									id="applType" name="applType" ondblclick="callShowDiv(this);"><%=label.get("applType")%></label>
								<font color="red">*</font>:</td>
								<td colspan="1" width="30%"><s:select
									name="applicationType"
									list="#{'':'Select','Default':'Default','Appra': 'Appraisal','Asset':'Asset','Cash':'Cash Voucher','Confere':'Conference',
							'EmpId':'Employee Id','Help':'Help Desk','Leave':'Leave', 'Loan':'Loan','Purchase':'Purchase','Recruitment':'Recruitment','REIMB':'Reimbursement',
							'Sugg':'Suggestion','Train':'Training','Tran':'Transfer', 'TYD':'Travel', 'D1-EXP':'D1-EXP', 'D1-NONINV':'D1-NONINV', 'D1-AUTHTRVL':'D1-AUTHTRVL', 
							'D1-REPCHG':'D1-REPCHG', 'D1-PERSONAL':'D1-PERSONAL', 'D1-DATACHG':'D1-DATACHG','D1-ENROLL':'D1-ENROLL','D1-HIRE':'D1-HIRE','D1-REQBACKOUT':'D1-REQBACKOUT',
							'D1-CLASS':'D1-CLASS','D1-DEPT':'D1-DEPT','D1-INJURY':'D1-INJURY','D1-SHORTDIS':'D1-SHORTDIS','D1-TERMNT':'D1-TERMNT','D1-ASIPO':'D1-ASIPO',
							'D1-HWSW':'D1-HWSW','D1-CCR':'D1-CCR','D1-CDROM':'D1-CDROM','D1-CBT':'D1-CBT','D1-CASHADV':'D1-CASHADV', 'D1-APPLN_SEC_REQ':'D1-APPLN_SEC_REQ', 
							'D1-CEAR':'D1-CEAR','D1-QUICK PROJECT':'D1-QUICK PROJECT','D1-ISCR':'D1-ISCR','RMS-OfferLetter':'RMS-OfferLetter','RMS-AppointmentLetter':'RMS-AppointmentLetter','D1-BRD':'D1-BRD','D1-CCServiceReq':'D1-CCServiceReq'}"
									cssStyle="width :160">
								</s:select></td>

								<td colspan="1" width="20%"></td>
								<td></td>
							</tr>

							<tr>
								<td width="20%" colspan="1">&nbsp;</td>
								<td width="15%" colspan="1" align="center"><input
									type="button" class="next" theme="simple" value="  Next"
									onclick="return checkNext();" /></td>
								<td width="40%">&nbsp;</td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>

		<s:else>
			<s:hidden name="applCodeType" />
			<s:hidden name="hiddenTemplateName" />
			<tr>
				<td>
				<table width="98%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td colspan="1" width="20%"><label class="set"
							id="applCodeTypeLabel" name="applCodeTypeLabel"
							ondblclick="callShowDiv(this);"><%=label.get("applCodeTypeLabel")%></label>
						<font color="red">*</font>:</td>
						<td colspan="3" width="80%"><input type="checkbox"
							id='applCodeT' name='templateType'
							onclick="return callCheckBox(this);" /><label class="set"
							id="templateBasedLabel" name="templateBasedLabel"
							ondblclick="callShowDiv(this);"><%=label.get("templateBasedLabel")%></label>
						<input type="checkbox" id='applCodeQ' name='queryType'
							onclick="return callCheckBox(this);" /> <label class="set"
							id="queryBasedLabel" name="queryBasedLabel"
							ondblclick="callShowDiv(this);"><%=label.get("queryBasedLabel")%></label>
						</td>
					</tr>
				</table>
				</td>
			</tr>

			<tr id='templateTR'>
				<td>
				<table width="98%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<s:hidden name="applicationType"></s:hidden>
						<td colspan="1" width="20%"><label class="set"
							id="selectElement" name="selectElement"
							ondblclick="callShowDiv(this);"><%=label.get("selectElement")%></label>
						<font color="red">*</font>:</td>
						<td colspan="1" width="30%"><s:select name="element"
							cssStyle="width :160"
							list="#{'1':'Month','2':'Year','3':'Division','4':'Branch','5':'Department','6':'Text','7':'Autogenerated ID','8':'First Name Initial','9':'Last Name Initial'}"
							onchange="return callElementChange(this);" /></td>
						<td colspan="1" width="20%"><label class="set" id="separator"
							name="separator" ondblclick="callShowDiv(this);"><%=label.get("separator")%></label>
						:</td>
						<td colspan="1" width="30%" nowrap="nowrap"><s:select
							name="seperatorValue" cssStyle="width :160" headerKey=""
							headerValue="--Select--"
							list="#{',':',','.':'.','_':'_','-':'-','/':'/',':':':'}"></s:select>
						</td>
					</tr>

					<tr>
						<td colspan="1" width="20%"></td>
						<td colspan="1" width="30%" nowrap="nowrap"></td>
					</tr>
					<tr id="textTr">

						<td colspan="1" width="20%"><label class="set" id="text"
							name="text" ondblclick="callShowDiv(this);"><%=label.get("text")%></label>
						<font color="red">*</font> :</td>
						<td colspan="1" width="30%" nowrap="nowrap"><s:textfield
							name='textToAppend' size="25" maxlength="30"></s:textfield></td>

						<td colspan="1" width="20%"></td>
						<td colspan="1" width="30%"></td>
					</tr>
					<tr id="autoGenTr">

						<td colspan="1" width="20%" nowrap="nowrap"><label
							class="set" id="idType" name="idType"
							ondblclick="callShowDiv(this);"><%=label.get("idType")%></label>
						:</td>
						<td colspan="1" width="30%" nowrap="nowrap"><s:select
							theme="simple" headerKey="Common" headerValue="Common"
							name="autoGenIdType" cssStyle="width:160"
							list="#{'Division':'Division-wise','Branch':'Branch-wise','Department':'Department-wise','First Name':'First Name-wise','Last Name':'Last Name-wise'}" />
						</td>

						<td colspan="1" width="20%" nowrap="nowrap"><label
							class="set" id="resetAutoId" name="resetAutoId"
							ondblclick="callShowDiv(this);"><%=label.get("resetAutoId")%></label>:</td>
						<td colspan="1" width="30%" nowrap="nowrap"><s:select
							theme="simple" headerKey="N" headerValue="No Reset"
							name="resetType" cssStyle="width:160"
							list="#{'M':'Monthly','Y':'Yearly (Calendar Year)','F':'Yearly (Financial Year)'}" /></td>
					</tr>
					<tr id="autoGenTr1">

						<td colspan="1" width="20%" nowrap="nowrap"><label
							class="set" id="digits" name="digits"
							ondblclick="callShowDiv(this);"><%=label.get("digits")%></label>
						:</td>
						<td colspan="1" width="30%" nowrap="nowrap"><s:textfield
							name='autoIdDigits' maxlength="3" size='25'
							onkeypress="return numbersOnly()"></s:textfield></td>

						<td colspan="1" width="30%" nowrap="nowrap"></td>
					</tr>

					<tr>
						<td colspan="4" align="center"><input type="button"
							value=" Add to Template " class="add"
							onclick="return callAddString();" /></td>
					</tr>

					<tr>
						<td colspan="1"><label class="set" id="templateName"
							name="templateName" ondblclick="callShowDiv(this);"><%=label.get("templateName")%></label>
						Preview <font color="red">*</font> :</td>
						<td colspan="3"><s:textfield name="templateName" size="90"
							readonly="true" /> <s:hidden name="templateId" /><s:hidden
							name="autoIdFlag"></s:hidden> <input type='button' class='reset'
							value=' Clear' onclick="callClear();"></td>
					</tr>
				</table>
				</td>
			</tr>


			<tr id='queryTR'>
				<td>
				<table width="98%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td colspan="1" width="20%"><label class="set" id="query"
							name="query" ondblclick="callShowDiv(this);"><%=label.get("query")%></label>
						<font color="red">*</font>:</td>
						<td colspan="3" width="80%"><s:textarea cols="100" rows="3"
							name='templateQuery'>
						</s:textarea></td>
					</tr>

				</table>
				</td>
			</tr>
		</s:else>


	</table>
	</td>
	</tr>

	</table>
	</td>
	</tr>


	</table>

</s:form>
<script>
 function checkNext(){
 	if(document.getElementById('paraFrm_applicationType').value==""){
 		alert("Please select "+document.getElementById('applType').innerHTML);
 		return false;
 		}else{
 			document.getElementById('paraFrm').action="ApplCodeTemplate_next.action";
 			document.getElementById('paraFrm').submit();
  		}
 }
 function callOnloadForTemplatePage(){
 		var applCodeType = document.getElementById('paraFrm_applCodeType').value;
 		document.getElementById('textTr').style.display='none';
 		document.getElementById('autoGenTr').style.display='none'; 	
 		document.getElementById('autoGenTr1').style.display='none';
 		
 		if(applCodeType=='Q'){
 			document.getElementById('templateTR').style.display='none';
 			document.getElementById('queryTR').style.display='';
 			document.getElementById('applCodeQ').checked=true; 
 		}else {
 			document.getElementById('templateTR').style.display='';
 			document.getElementById('queryTR').style.display='none';
 			document.getElementById('applCodeT').checked=true; 
 		}
 		
 			
 	
 }
 callOnloadForTemplatePage();
 function callElementChange(obj){
 	try{
 	if(obj.value=="6"){
 		document.getElementById('textTr').style.display='';
 		document.getElementById('autoGenTr').style.display='none';
 		document.getElementById('autoGenTr1').style.display='none';
 	}else
 	if(obj.value=="7"){
 		document.getElementById('autoGenTr').style.display='';
 		document.getElementById('textTr').style.display='none';
 		document.getElementById('autoGenTr1').style.display='';
 	}else{
 		document.getElementById('autoGenTr').style.display='none';
 		document.getElementById('textTr').style.display='none';
 		document.getElementById('autoGenTr1').style.display='none';
 	}
 	}catch(e){
 		alert(e);
 	}
 }
 function callValidate(){
 if(document.getElementById('paraFrm_applCodeType').value=='Q'){
 	if(document.getElementById('paraFrm_templateQuery').value==""){
 		alert('Please enter '+document.getElementById('query').innerHTML);
 		document.getElementById('paraFrm_templateQuery').focus();
 		return false;
 		}
 	}else{
 		if(document.getElementById('paraFrm_templateName').value==""){
 		alert('Please enter '+document.getElementById('templateName').innerHTML);
 		document.getElementById('paraFrm_templateName').focus();
 		return false;
 	}
 	if(document.getElementById('paraFrm_autoIdFlag').value!="true"){
 		alert("Please add 'Autogenerated Id' in the "+document.getElementById('templateName').innerHTML);
 		document.getElementById("paraFrm_element").value = 7;
 		document.getElementById("paraFrm_element").focus();
 		document.getElementById('autoGenTr').style.display='';
 		document.getElementById('autoGenTr1').style.display='';
 		document.getElementById('textTr').style.display='none';
 		return false;
 	}
 	}
 		document.getElementById('paraFrm').action = 'ApplCodeTemplate_save.action';
 		document.getElementById('paraFrm').submit();
 	
 }
 
 function callAddString(){
 var selectedElement = document.getElementById("paraFrm_element").value;
 
 if(selectedElement==6){
 	if (trim(document.getElementById('paraFrm_textToAppend').value)==""){
 		alert("Please enter the "+document.getElementById('text').innerHTML);
 		document.getElementById('paraFrm_textToAppend').value=trim(document.getElementById('paraFrm_textToAppend').value);
 		document.getElementById('paraFrm_textToAppend').focus();
 		return false;
 	}
 	
 }else if(selectedElement==7){
 	if(document.getElementById('paraFrm_autoIdFlag').value=="true"){
 		alert("Autogenerated ID is already added in the "+document.getElementById('templateName').innerHTML);
 		return false;
 	}
 	document.getElementById('paraFrm_autoIdFlag').value="true";
 }
 if(document.getElementById('paraFrm_templateName').value==""){
 	document.getElementById('paraFrm_templateName').value =getcheckedValue(selectedElement);
 	document.getElementById('paraFrm_hiddenTemplateName').value = "<#"+getcheckedValue(selectedElement)+"#>";
 }else{
 	document.getElementById('paraFrm_templateName').value +=document.getElementById('paraFrm_seperatorValue').value+getcheckedValue(selectedElement);
 	document.getElementById('paraFrm_hiddenTemplateName').value +="<#"+document.getElementById('paraFrm_seperatorValue').value+"#><#"+getcheckedValue(selectedElement)+"#>";
 }
 document.getElementById('paraFrm_textToAppend').value = '';
 /*try{
 var checkedFlag='false';
 	for (var i =1; i<=7; i++){
 		if(document.getElementById("paraFrm_element"+i).checked){
 		if(i==7){
 			document.getElementById('paraFrm_autoIdFlag').value="true";
 		}
 		//alert("checked for "+document.getElementById("paraFrm_element"+i).value);
 		document.getElementById('paraFrm_templateName').value +=document.getElementById('paraFrm_seperatorValue').value+getcheckedValue(i);
 		document.getElementById('paraFrm_hiddenTemplateName').value +="<#"+document.getElementById('paraFrm_seperatorValue').value+"#><#"+getcheckedValue(i)+"#>";
 		checkedFlag = 'true';
 		break;
 		}
 	}
 	if(checkedFlag=='false'){
 		alert("Please check atleast one option");
 		return false;
 	}
 	//alert("hidden templateName=="+document.getElementById('paraFrm_hiddenTemplateName').value);
 	}catch(e){
 		alert("exception=="+e);
 	}*/
 	
 }
 function callDeleteTempl(){
 	var conf=confirm("Do you really want to delete this record ?");
  			if(conf) 
  			{
  			document.getElementById('paraFrm').action="ApplCodeTemplate_delete.action";
 			document.getElementById('paraFrm').submit();
  			}
	  		else
	  		{
	  			 return false;
	  		}
 			
 }
 function getcheckedValue(id){
 	switch(eval(id)){
 		case 1: return "MONTH";
 		case 2: return "YEAR";
 		case 3: return "DIVISION";
 		case 4: return "BRANCH";
 		case 5: return "DEPARTMENT";
 		case 6: return document.getElementById('paraFrm_textToAppend').value;
 		case 7: return "AUTOGENERATED ID";
 		case 8: return "FNAME INITIAL";
 		case 9: return "LNAME INITIAL";
 		default :return "";
 	}
 }
 function callClear(){
 		document.getElementById('paraFrm_templateName').value="";
 		document.getElementById('paraFrm_hiddenTemplateName').value="";
 		document.getElementById('paraFrm_autoIdFlag').value="";
 }
 function goBack(){
 			document.getElementById('paraFrm').action="ApplCodeTemplate_back.action";
 			document.getElementById('paraFrm').submit();
 }
 
 function callCheckBox(obj){
 if(obj.id=='applCodeT'){
 	if(document.getElementById(obj.id).checked){
 		document.getElementById('applCodeQ').checked=false;
 		document.getElementById('templateTR').style.display='';
 		document.getElementById('queryTR').style.display='none';	
 	}else{
 		document.getElementById('applCodeQ').checked=true;
 		document.getElementById('templateTR').style.display='none';
 		document.getElementById('queryTR').style.display='';	
 	}
 	document.getElementById('paraFrm_applCodeType').value='T';
 }else{
 	if(document.getElementById(obj.id).checked){
 		document.getElementById('applCodeT').checked=false;
 		document.getElementById('templateTR').style.display='none';
 		document.getElementById('queryTR').style.display='';	
 	}else{
 		document.getElementById('applCodeT').checked=true;
 		document.getElementById('templateTR').style.display='';
 		document.getElementById('queryTR').style.display='none';	
 	}
 	document.getElementById('paraFrm_applCodeType').value='Q';
 }
 }
</script>