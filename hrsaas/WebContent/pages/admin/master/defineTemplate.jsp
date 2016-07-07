<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="BankMaster" validate="true" id="paraFrm" theme="simple">
<s:hidden name="myPageInProcess" id="myPageInProcess" />
<s:hidden name="show" />
<s:hidden name="hiddencode" id="hiddencode"/>
<s:hidden name="hiddenTemplateName"/>
	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<td colspan="3">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Define Template
					</strong></td>
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
				<table width="100%" border="0" align="center">
					<tr>
						<td>
							<input type="button" class="save" value="   Save " onclick="return callAdd();" />
							<input type="button" class="back" value="   Back " onclick="return callBack();" />
							<input type="button" class="reset" value="    Reset " onclick="return callReset();" />
						</td>
					</tr>	
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						<td colspan="3" class="text_head">
						<strong class="forminnerhead">Template Title </strong></td>
					</tr>
					<tr>
						<td width="20%">
							<label id="element"	name="element" ondblclick="callShowDiv(this);"><%=label.get("element")%></label>:<font id='ctrlHide' color="red"></font>
						</td>
						<td colspan="2">
							<s:select headerKey="" headerValue="--Select--" cssStyle="width:138" name="element" list="#{'1':'Month','2':'Year','3':'Division','4':'Address'}" />
								&nbsp;
							<a href="#?" onclick="callAddString();"  title="Click for Add to Template Title">Add to Template Title</a>
						</td>
					</tr>
					<tr>
						<td class="formtext">
							<label class = "set"  id="template.title" name="template.title" ondblclick="callShowDiv(this);"><%=label.get("template.title")%></label>:<font color="red"></font>
						</td>
						<td nowrap="nowrap">
							<s:textfield size="80" name="statementTitle" maxlength="50"/>
						</td>
						<td align="center">
							<input type='button' class='reset'	value=" Clear " onclick="callClear();">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" class="formbg" id="templateTable">
				<tr>
					<td colspan="3" class="text_head">
						<strong	class="forminnerhead">Map Column Heads </strong>
					</td>
				</tr>
				 <!-- Details Table Begins -->
				<tr>
					<td align="center" class="formth">
						<b>Sr.No.</b>
					</td>
					<td align="center" class="formth">
						<b><font color="red"></font>Column Name</b>
					</td>
					<td align="center" class="formth">
						<b><font color="red"></font>Column No </b>
					</td>
				</tr>
				<tr>
					<td align="center"  class="sortableTD" >1</td>
					<td align="left"  class="sortableTD" >STATEMENT_IFSC_CODE</td>
					<td align="center"  class="sortableTD" ><s:textfield size= "25" name="statementIfscCode" id="paraFrm_statementIfscCode" maxlength="2" onkeypress="return numbersOnly();" onchange="validateStatementIfscCode();"/></td>
				</tr>
				<tr>
					<td align="center" class="sortableTD" >2</td>
					<td align="left" class="sortableTD" >STATEMENT_CR_DR              </td>
					<td align="center" class="sortableTD" ><s:textfield size= "25" name="statementCrDr" id="paraFrm_statementCrDr" maxlength="2" onkeypress="return numbersOnly();" onchange="validateStatementCrDr();"/></td>
				</tr>
				<tr>
					<td align="center" class="sortableTD" >3</td>
					<td align="left" class="sortableTD" >STATEMENT_ACCOUNT          </td>
					<td align="center" class="sortableTD" ><s:textfield size= "25" name="statementAccount" id="paraFrm_statementAccount" maxlength="2" onkeypress="return numbersOnly();" onchange="validateStatementAccount();"/></td>
				</tr>
				<tr>
					<td align="center" class="sortableTD" >4</td>
					<td align="left" class="sortableTD" >STATEMENT_AMOUNT           </td>
					<td align="center" class="sortableTD" ><s:textfield size= "25" name="statementAmount" id="paraFrm_statementAmount" maxlength="2" onkeypress="return numbersOnly();" onchange="validateStatementAmount();"/></td>
				</tr>
				<tr>
					<td align="center" class="sortableTD" >5</td>
					<td align="left" class="sortableTD" >STATEMENT_EMP_CODE        </td>
					<td align="center" class="sortableTD" ><s:textfield size= "25" name="statementEmpCode" id="paraFrm_statementEmpCode" maxlength="2" onkeypress="return numbersOnly();" onchange="validateStatementEmpCode();"/></td>
				</tr>
				<tr>
					<td align="center" class="sortableTD" >6</td>
					<td align="left" class="sortableTD" >STATEMENT_NAME                </td>
					<td align="center" class="sortableTD" ><s:textfield size= "25" name="statementName" id="paraFrm_statementName" maxlength="2" onkeypress="return numbersOnly();" onchange="validateStatementName();"/></td>
				</tr>
				<tr>
					<td align="center" class="sortableTD" >7</td>
					<td align="left" class="sortableTD" >STATEMENT_NARRATION       </td>
					<td align="center" class="sortableTD" ><s:textfield size= "25" name="statementNarration" id="paraFrm_statementNarration" maxlength="2" onkeypress="return numbersOnly();" onchange="validateStatementNarration();"/></td>
				</tr>
				<tr>
					<td align="center" class="sortableTD" >8</td>
					<td align="left" class="sortableTD" >STATEMENT_SOL_ID             </td>
					<td align="center" class="sortableTD" ><s:textfield size= "25" name="statementSolId" id="paraFrm_statementSolId" maxlength="2" onkeypress="return numbersOnly();" onchange="validateStatementSolId();"/></td>
				</tr>
				<tr>
					<td align="center" class="sortableTD" >9</td>
					<td align="left" class="sortableTD" >STATEMENT_TRANS_DESC    </td>
					<td align="center" class="sortableTD" ><s:textfield size= "25" name="statementTransDesc" id="paraFrm_statementTransDesc" maxlength="2" onkeypress="return numbersOnly();" onchange="validateStatementTransDesc();"/></td>
				</tr>
				<tr>
					<td align="center" class="sortableTD" >10</td>
					<td align="left" class="sortableTD" >STATEMENT_TRANS_PART     </td>
					<td align="center" class="sortableTD" ><s:textfield size= "25" name="statementTransPart" id="paraFrm_statementTransPart" maxlength="2" onkeypress="return numbersOnly();" onchange="validateStatementTransPart();"/></td>
				</tr>
				<tr>
					<td align="center" class="sortableTD" >11</td>
					<td align="left" class="sortableTD" >STATEMENT_CURRENCY        </td>
					<td align="center" class="sortableTD" ><s:textfield size= "25" name="statementCurrency" id="paraFrm_statementCurrency" maxlength="2" onkeypress="return numbersOnly();" onchange="validateStatementCurrency();" /></td>
				</tr>
				<tr>
					<td align="center" class="sortableTD" >12</td>
					<td align="left" class="sortableTD" >STATEMENT_DR_ACCOUNT        </td>
					<td align="center" class="sortableTD" ><s:textfield size= "25" name="statementDrAccount" id="paraFrm_statementDrAccount" maxlength="2" onkeypress="return numbersOnly();" onchange="validateStatementCurrency();" /></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" class="formbg">
				<tr>
					<td colspan="3" class="text_head">
						<strong	class="forminnerhead">
							<label class = "set"  id="coverLettter" name="coverLettter" ondblclick="callShowDiv(this);"><%=label.get("coverLettter")%></label>
						</strong>
					</td>
				</tr>
				<tr>
					<td width="30%">
						<label class = "set"  id="template" name="template" ondblclick="callShowDiv(this);"><%=label.get("template")%></label>:
					</td>
					<td colspan="2">
						<s:hidden name="templateCode" />
						<s:textfield name="templateName" theme="simple"  readonly="true" maxlength="50" size="50" />
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="15" align="absmiddle"	width="16" onclick="javascript:callsF9(500,325,'BankMaster_f9coveringLetter.action');">
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3">
			<input type="button" class="save" value="   Save " onclick="return callAdd();" />
			<input type="button" class="back" value="   Back " onclick="return callBack();" />
			<input type="button" class="reset" value="    Reset " onclick="return callReset();" />
		</td>
	</tr>	
</table>
</s:form>
<script>

function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'BankMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	
function editFun() {
	return true;
}
	
function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
	return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
}	
	
function callAdd() {
	if(!checkColumnSerialNo()){
		return false;
	}else{
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'BankMaster_updateBankStmt.action';
		document.getElementById('paraFrm').submit();
	}
}
function checkColumnSerialNo(){
	try{
		var enteredSerialNos = new Array();
		var noOfRows = document.getElementById('templateTable').getElementsByTagName('TR');
		var count=0;
		for(var i=2; i<noOfRows.length; i++){
			var tdval = noOfRows[i].getElementsByTagName('TD');
			var fieldId = tdval.item(2).getElementsByTagName('input').item(0).getAttribute('id');
			var fieldValue = document.getElementById(fieldId).value;
			if(fieldValue != ""){
				enteredSerialNos.push(fieldValue);
			}
		}
		/*THIS SORTS THE ARRAY IN ASCENDING ORDER*/
		enteredSerialNos.sort(function(a,b){return a-b});
		for(var j=1;j<=enteredSerialNos.length-1;j++){
			if((enteredSerialNos[j]-enteredSerialNos[j-1])!=1){
				alert("Assign column numbers in serial order");
				return false;
			}
		}
	}catch(e){alert(e)};
	return true;
}	
function callReset() {
	document.getElementById('paraFrm').target="main";		  
	document.getElementById("paraFrm").action="BankMaster_resetDefineTemplate.action";
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target="main";
}   
	
function callBack() {
	document.getElementById('paraFrm').target = "_self";
   	document.getElementById('paraFrm').action = 'BankMaster_back.action';
	document.getElementById('paraFrm').submit();
}
	
	
function callAddString(){
	var selectedElement = document.getElementById("paraFrm_element").value;
 	var templateName = document.getElementById("paraFrm_statementTitle").value;
 	document.getElementById("paraFrm_statementTitle").value=templateName+"<#"+getcheckedValue(selectedElement)+"#>";
}
 
function getcheckedValue(id){
 	switch(eval(id)){
 		case 1: return "MONTH";
 		case 2: return "YEAR";
 		case 3: return "DIVISION";
 		case 4: return "ADDRESS";
 		default :return "";
 	}
}
function callClear(){
	document.getElementById('paraFrm_statementTitle').value="";
	document.getElementById('paraFrm_hiddenTemplateName').value="";
	//document.getElementById('paraFrm_autoIdFlag').value="";
}
 
function validateStatementIfscCode() {
	 var statementIfscCode= document.getElementById('paraFrm_statementIfscCode').value;
	 if(statementIfscCode!=""){
	 	if(Math.abs(statementIfscCode)==0){
	 	alert("Zero not allowed in STATEMENT IFSC CODE");
	 	document.getElementById('paraFrm_statementIfscCode').focus();
	 	document.getElementById('paraFrm_statementIfscCode').value="";
	 	return false;
	 	}
	 	}
	 	
	 	if(statementIfscCode==document.getElementById('paraFrm_statementCrDr').value || 
	 	statementIfscCode==document.getElementById('paraFrm_statementAccount').value ||
	 	statementIfscCode==document.getElementById('paraFrm_statementAmount').value ||
	 	statementIfscCode==document.getElementById('paraFrm_statementEmpCode').value || 
	 	statementIfscCode==document.getElementById('paraFrm_statementName').value ||
	 	statementIfscCode==document.getElementById('paraFrm_statementNarration').value||
	 	statementIfscCode==document.getElementById('paraFrm_statementSolId').value ||
	 	statementIfscCode== document.getElementById('paraFrm_statementTransDesc').value||
	 	statementIfscCode== document.getElementById('paraFrm_statementTransPart').value||
	 	statementIfscCode== document.getElementById('paraFrm_statementCurrency').value ){
	 
	 	alert(" This number is already in used.");
	 	document.getElementById('paraFrm_statementIfscCode').focus();
	 	document.getElementById('paraFrm_statementIfscCode').value="";
	 	return false;
	 	
	 	}
	 	
	 	if ((document.getElementById('paraFrm_statementIfscCode').value) > 13) {
    			 alert("Column Number must be less than 13\n");
    			document.getElementById('paraFrm_statementIfscCode').focus();
    			document.getElementById('paraFrm_statementIfscCode').value="";
		 		return false;
			}
	 	
	 }
	 
	
function validateStatementCrDr() {
	var statementCrDr= document.getElementById('paraFrm_statementCrDr').value;
	if(statementCrDr!=""){
	 	if(Math.abs(statementCrDr)==0){
	 	alert("Zero not allowed in STATEMENT CR DR");
	 	document.getElementById('paraFrm_statementCrDr').focus();
	 	document.getElementById('paraFrm_statementCrDr').value="";
	 	return false;
	 	}
	 	}
	 	
	 	if(statementCrDr==document.getElementById('paraFrm_statementIfscCode').value || 
	 	statementCrDr==document.getElementById('paraFrm_statementAccount').value ||
	 	statementCrDr==document.getElementById('paraFrm_statementAmount').value ||
	 	statementCrDr==document.getElementById('paraFrm_statementEmpCode').value || 
	 	statementCrDr==document.getElementById('paraFrm_statementName').value ||
	 	statementCrDr==document.getElementById('paraFrm_statementNarration').value||
	 	statementCrDr==document.getElementById('paraFrm_statementSolId').value ||
	 	statementCrDr== document.getElementById('paraFrm_statementTransDesc').value||
	 	statementCrDr== document.getElementById('paraFrm_statementTransPart').value||
	 	statementCrDr== document.getElementById('paraFrm_statementCurrency').value ){
	 
	 	alert(" This number is already in used.");
	 	document.getElementById('paraFrm_statementCrDr').focus();
	 	document.getElementById('paraFrm_statementCrDr').value="";
	 	return false;
	 	
	 	}
	 	
	 	if ((document.getElementById('paraFrm_statementCrDr').value) > 13) {
    			 alert("Column Number must be less than 13\n");
    			document.getElementById('paraFrm_statementCrDr').focus();
    			document.getElementById('paraFrm_statementCrDr').value="";
		 		return false;
			}
	 	
	 }


function validateStatementAccount() {
	 var statementAccount= document.getElementById('paraFrm_statementAccount').value;
	 if(statementAccount!=""){
	 	if(Math.abs(statementAccount)==0){
	 	alert("Zero not allowed in STATEMENT ACCOUNT ");
	 	document.getElementById('paraFrm_statementAccount').focus();
	 	document.getElementById('paraFrm_statementAccount').value="";
	 	return false;
	 	}
	 	}
	 	
	 	if(statementAccount==document.getElementById('paraFrm_statementIfscCode').value || 
	 	statementAccount==document.getElementById('paraFrm_statementCrDr').value ||
	 	statementAccount==document.getElementById('paraFrm_statementAmount').value ||
	 	statementAccount==document.getElementById('paraFrm_statementEmpCode').value || 
	 	statementAccount==document.getElementById('paraFrm_statementName').value ||
	 	statementAccount==document.getElementById('paraFrm_statementNarration').value||
	 	statementAccount==document.getElementById('paraFrm_statementSolId').value ||
	 	statementAccount== document.getElementById('paraFrm_statementTransDesc').value||
	 	statementAccount== document.getElementById('paraFrm_statementTransPart').value||
	 	statementAccount== document.getElementById('paraFrm_statementCurrency').value ){
	 
	 	alert(" This number is already in used.");
	 	document.getElementById('paraFrm_statementAccount').focus();
	 	document.getElementById('paraFrm_statementAccount').value="";
	 	return false;
	 	
	 	}
	 	
	 	if ((document.getElementById('paraFrm_statementAccount').value) > 13) {
    			 alert("Column Number must be less than 13\n");
    			document.getElementById('paraFrm_statementAccount').focus();
    			document.getElementById('paraFrm_statementAccount').value="";
		 		return false;
			}
	 	
	 }


function validateStatementAmount() {
	 var statementAmount= document.getElementById('paraFrm_statementAmount').value;
	 if(statementAmount!=""){
	 	if(Math.abs(statementAmount)==0){
	 	alert("Zero not allowed in STATEMENT AMOUNT ");
	 	document.getElementById('paraFrm_statementAmount').focus();
	 	document.getElementById('paraFrm_statementAmount').value="";
	 	return false;
	 	}
	 	}
	 	
	 	if(statementAmount==document.getElementById('paraFrm_statementIfscCode').value || 
	 	statementAmount==document.getElementById('paraFrm_statementCrDr').value ||
	 	statementAmount==document.getElementById('paraFrm_statementAccount').value ||
	 	statementAmount==document.getElementById('paraFrm_statementEmpCode').value || 
	 	statementAmount==document.getElementById('paraFrm_statementName').value ||
	 	statementAmount==document.getElementById('paraFrm_statementNarration').value||
	 	statementAmount==document.getElementById('paraFrm_statementSolId').value ||
	 	statementAmount== document.getElementById('paraFrm_statementTransDesc').value||
	 	statementAmount== document.getElementById('paraFrm_statementTransPart').value||
	 	statementAmount== document.getElementById('paraFrm_statementCurrency').value ){
	 
	 	alert(" This number is already in used.");
	 	document.getElementById('paraFrm_statementAmount').focus();
	 	document.getElementById('paraFrm_statementAmount').value="";
	 	return false;
	 	
	 	}
	 	
	 		if ((document.getElementById('paraFrm_statementAmount').value) > 13) {
    			 alert("Column Number must be less than 13\n");
    			document.getElementById('paraFrm_statementAmount').focus();
    			document.getElementById('paraFrm_statementAmount').value="";
		 		return false;
			}
	 }


function validateStatementEmpCode() {
	 var statementEmpCode= document.getElementById('paraFrm_statementEmpCode').value;
	 if(statementEmpCode!=""){
	 	if(Math.abs(statementEmpCode)==0){
	 	alert("Zero not allowed in STATEMENT EMP CODE ");
	 	document.getElementById('paraFrm_statementEmpCode').focus();
	 	document.getElementById('paraFrm_statementEmpCode').value="";
	 	return false;
	 	}
	 	}
	 	
	 	if(statementEmpCode==document.getElementById('paraFrm_statementIfscCode').value || 
	 	statementEmpCode==document.getElementById('paraFrm_statementCrDr').value ||
	 	statementEmpCode==document.getElementById('paraFrm_statementAccount').value ||
	 	statementEmpCode==document.getElementById('paraFrm_statementAmount').value || 
	 	statementEmpCode==document.getElementById('paraFrm_statementName').value ||
	 	statementEmpCode==document.getElementById('paraFrm_statementNarration').value||
	 	statementEmpCode==document.getElementById('paraFrm_statementSolId').value ||
	 	statementEmpCode== document.getElementById('paraFrm_statementTransDesc').value||
	 	statementEmpCode== document.getElementById('paraFrm_statementTransPart').value||
	 	statementEmpCode== document.getElementById('paraFrm_statementCurrency').value ){
	 
	 	alert(" This number is already in used.");
	 	document.getElementById('paraFrm_statementEmpCode').focus();
	 	document.getElementById('paraFrm_statementEmpCode').value="";
	 	return false;
	 	
	 	}
	 	
	 		if ((document.getElementById('paraFrm_statementEmpCode').value) > 13) {
    			 alert("Column Number must be less than 13\n");
    			document.getElementById('paraFrm_statementEmpCode').focus();
    			document.getElementById('paraFrm_statementEmpCode').value="";
		 		return false;
			}
	 	
	 }


function validateStatementName() {
	 var statementName= document.getElementById('paraFrm_statementName').value;
	 if(statementName!=""){
	 	if(Math.abs(statementName)==0){
	 	alert("Zero not allowed in STATEMENT NAME ");
	 	document.getElementById('paraFrm_statementName').focus();
	 	document.getElementById('paraFrm_statementName').value="";
	 	return false;
	 	}
	 	}
	 	
	 	if(statementName==document.getElementById('paraFrm_statementIfscCode').value || 
	 	statementName==document.getElementById('paraFrm_statementCrDr').value ||
	 	statementName==document.getElementById('paraFrm_statementAccount').value ||
	 	statementName==document.getElementById('paraFrm_statementAmount').value || 
	 	statementName==document.getElementById('paraFrm_statementEmpCode').value ||
	 	statementName==document.getElementById('paraFrm_statementNarration').value||
	 	statementName==document.getElementById('paraFrm_statementSolId').value ||
	 	statementName== document.getElementById('paraFrm_statementTransDesc').value||
	 	statementName== document.getElementById('paraFrm_statementTransPart').value||
	 	statementName== document.getElementById('paraFrm_statementCurrency').value ){
	 
	 	alert(" This number is already in used.");
	 	document.getElementById('paraFrm_statementName').focus();
	 	document.getElementById('paraFrm_statementName').value="";
	 	return false;
	 	
	 	}
	 	
	 	if ((document.getElementById('paraFrm_statementName').value) > 13) {
    			 alert("Column Number must be less than 13\n");
    			document.getElementById('paraFrm_statementName').focus();
    			document.getElementById('paraFrm_statementName').value="";
		 		return false;
			}
	 	
	 }


function validateStatementNarration() {
	 var statementNarration= document.getElementById('paraFrm_statementNarration').value;
	 if(statementNarration!=""){
	 	if(Math.abs(statementNarration)==0){
	 	alert("Zero not allowed in STATEMENT NARRATION ");
	 	document.getElementById('paraFrm_statementNarration').focus();
	 	document.getElementById('paraFrm_statementNarration').value="";
	 	return false;
	 	}
	 	}
	 	
	 	if(statementNarration==document.getElementById('paraFrm_statementIfscCode').value || 
	 	statementNarration==document.getElementById('paraFrm_statementCrDr').value ||
	 	statementNarration==document.getElementById('paraFrm_statementAccount').value ||
	 	statementNarration==document.getElementById('paraFrm_statementAmount').value || 
	 	statementNarration==document.getElementById('paraFrm_statementEmpCode').value ||
	 	statementNarration==document.getElementById('paraFrm_statementName').value||
	 	statementNarration==document.getElementById('paraFrm_statementSolId').value ||
	 	statementNarration== document.getElementById('paraFrm_statementTransDesc').value||
	 	statementNarration== document.getElementById('paraFrm_statementTransPart').value||
	 	statementNarration== document.getElementById('paraFrm_statementCurrency').value ){
	 
	 	alert(" This number is already in used.");
	 	document.getElementById('paraFrm_statementNarration').focus();
	 	document.getElementById('paraFrm_statementNarration').value="";
	 	return false;
	 	
	 	}
	 	
	 		if ((document.getElementById('paraFrm_statementNarration').value) > 13) {
    			 alert("Column Number must be less than 13\n");
    			document.getElementById('paraFrm_statementNarration').focus();
    			document.getElementById('paraFrm_statementNarration').value="";
		 		return false;
			}
	 	
	 }



function validateStatementSolId() {
	 var statementSolId= document.getElementById('paraFrm_statementSolId').value;
	 if(statementSolId!=""){
	 	if(Math.abs(statementSolId)==0){
		 	alert("Zero not allowed in STATEMENT SOL ID  ");
		 	document.getElementById('paraFrm_statementSolId').focus();
		 	document.getElementById('paraFrm_statementSolId').value="";
		 	return false;
	 	}
	 }
	 	
	 	if(statementSolId==document.getElementById('paraFrm_statementIfscCode').value || 
		 	statementSolId==document.getElementById('paraFrm_statementCrDr').value ||
		 	statementSolId==document.getElementById('paraFrm_statementAccount').value ||
		 	statementSolId==document.getElementById('paraFrm_statementAmount').value || 
		 	statementSolId==document.getElementById('paraFrm_statementEmpCode').value ||
		 	statementSolId==document.getElementById('paraFrm_statementName').value||
		 	statementSolId==document.getElementById('paraFrm_statementNarration').value ||
		 	statementSolId== document.getElementById('paraFrm_statementTransDesc').value||
		 	statementSolId== document.getElementById('paraFrm_statementTransPart').value||
		 	statementSolId== document.getElementById('paraFrm_statementCurrency').value ){
		 
		 	alert(" This number is already in used.");
		 	document.getElementById('paraFrm_statementSolId').focus();
		 	document.getElementById('paraFrm_statementSolId').value="";
		 	return false;
	 	
	 	}
	 	
	 	if ((document.getElementById('paraFrm_statementSolId').value) > 13) {
    			 alert("Column Number must be less than 13\n");
    			document.getElementById('paraFrm_statementSolId').focus();
    			document.getElementById('paraFrm_statementSolId').value="";
		 		return false;
			}
	 	
}

function validateStatementTransDesc() {
	 var statementTransDesc= document.getElementById('paraFrm_statementTransDesc').value;
	 if(statementTransDesc!=""){
	 	if(Math.abs(statementTransDesc)==0){
	 	alert("Zero not allowed in STATEMENT TRANS DESC ");
	 	document.getElementById('paraFrm_statementTransDesc').focus();
	 	document.getElementById('paraFrm_statementTransDesc').value="";
	 	return false;
	 	}
	 	}
	 	
	 	if(statementTransDesc==document.getElementById('paraFrm_statementIfscCode').value || 
	 	statementTransDesc==document.getElementById('paraFrm_statementCrDr').value ||
	 	statementTransDesc==document.getElementById('paraFrm_statementAccount').value ||
	 	statementTransDesc==document.getElementById('paraFrm_statementAmount').value || 
	 	statementTransDesc==document.getElementById('paraFrm_statementEmpCode').value ||
	 	statementTransDesc==document.getElementById('paraFrm_statementName').value||
	 	statementTransDesc==document.getElementById('paraFrm_statementNarration').value ||
	 	statementTransDesc== document.getElementById('paraFrm_statementSolId').value||
	 	statementTransDesc== document.getElementById('paraFrm_statementTransPart').value||
	 	statementTransDesc== document.getElementById('paraFrm_statementCurrency').value ){
	 
	 	alert(" This number is already in used.");
	 	document.getElementById('paraFrm_statementTransDesc').focus();
	 	document.getElementById('paraFrm_statementTransDesc').value="";
	 	return false;
	 	
	 	}
	 	
	 	if ((document.getElementById('paraFrm_statementTransDesc').value) > 13) {
    			 alert("Column Number must be less than 13\n");
    			document.getElementById('paraFrm_statementTransDesc').focus();
    			document.getElementById('paraFrm_statementTransDesc').value="";
		 		return false;
			}
	 	
	 }


function validateStatementTransPart() {
	 var statementTransPart= document.getElementById('paraFrm_statementTransPart').value;
	 if(statementTransPart!=""){
	 	if(Math.abs(statementTransPart)==0){
	 	alert("Zero not allowed in STATEMENT TRANS PART ");
	 	document.getElementById('paraFrm_statementTransPart').focus();
	 	document.getElementById('paraFrm_statementTransPart').value="";
	 	return false;
	 	}
	 	}
	 	
	 	if(statementTransPart==document.getElementById('paraFrm_statementIfscCode').value || 
	 	statementTransPart==document.getElementById('paraFrm_statementCrDr').value ||
	 	statementTransPart==document.getElementById('paraFrm_statementAccount').value ||
	 	statementTransPart==document.getElementById('paraFrm_statementAmount').value || 
	 	statementTransPart==document.getElementById('paraFrm_statementEmpCode').value ||
	 	statementTransPart==document.getElementById('paraFrm_statementName').value||
	 	statementTransPart==document.getElementById('paraFrm_statementNarration').value ||
	 	statementTransPart== document.getElementById('paraFrm_statementSolId').value||
	 	statementTransPart== document.getElementById('paraFrm_statementTransDesc').value||
	 	statementTransPart== document.getElementById('paraFrm_statementCurrency').value ){
	 
	 	alert(" This number is already in used.");
	 	document.getElementById('paraFrm_statementTransPart').focus();
	 	document.getElementById('paraFrm_statementTransPart').value="";
	 	return false;
	 	
	 	}
	 	if ((document.getElementById('paraFrm_statementTransPart').value) > 13) {
    			 alert("Column Number must be less than 13\n");
    			document.getElementById('paraFrm_statementTransPart').focus();
    			document.getElementById('paraFrm_statementTransPart').value="";
		 		return false;
			}
	 	
	 }

 
	function validateStatementCurrency() {
	 var statementCurrency= document.getElementById('paraFrm_statementCurrency').value;
	 if(statementCurrency!=""){
	 	if(Math.abs(statementCurrency)==0){
	 	alert("Zero not allowed in STATEMENT CURRENCY");
	 	document.getElementById('paraFrm_statementCurrency').focus();
	 	document.getElementById('paraFrm_statementCurrency').value="";
	 	return false;
	 	}
	 	}
	 	
	 	if(statementCurrency==document.getElementById('paraFrm_statementIfscCode').value || 
	 	statementCurrency==document.getElementById('paraFrm_statementCrDr').value ||
	 	statementCurrency==document.getElementById('paraFrm_statementAccount').value ||
	 	statementCurrency==document.getElementById('paraFrm_statementAmount').value || 
	 	statementCurrency==document.getElementById('paraFrm_statementEmpCode').value ||
	 	statementCurrency==document.getElementById('paraFrm_statementName').value||
	 	statementCurrency==document.getElementById('paraFrm_statementNarration').value ||
	 	statementCurrency== document.getElementById('paraFrm_statementSolId').value||
	 	statementCurrency== document.getElementById('paraFrm_statementTransDesc').value||
	 	statementCurrency== document.getElementById('paraFrm_statementTransPart').value ){
	 
	 	alert(" This number is already in used.");
	 	document.getElementById('paraFrm_statementCurrency').value="";
	 	document.getElementById('paraFrm_statementCurrency').focus();
	 	
	 	return false;
	 	
	 	}
	 	
	 	if ((document.getElementById('paraFrm_statementCurrency').value) > 13) {
    			 alert("Column Number must be less than 13\n");
    			document.getElementById('paraFrm_statementCurrency').focus();
    			document.getElementById('paraFrm_statementCurrency').value="";
		 		return false;
			}
	 	
} 
</script>
