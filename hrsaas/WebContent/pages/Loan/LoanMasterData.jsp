<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:hidden name="loanTypeName" />
<s:hidden name="Debitname" />
<s:hidden name="Debitcode" />
<s:form action="LoanMaster" validate="true" id="paraFrm" name="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Loan
					Type </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Loan Type </strong></td>
					<s:hidden name="loanTypeCode" />
				</tr>
				
				<tr>
				<td width="15%"><label class="set" id="division1"
							name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font></td>
				
				<td colspan="6"><s:hidden name="divCode"/>
							<s:textarea cols="94" rows="1" theme="simple"	readonly="true" name="divName"  />
						<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16" id="ctrlHide"
							onclick="javascript:callDropdown('paraFrm_divName',350,250,'LoanMaster_f9divaction.action',event,'false','no','right')"></td>
			</tr>
				
				
				<tr>
					<td height="22" class="formtext" width="25%"><label
						class="set" name="loan.type" id="loan.type"
						ondblclick="callShowDiv(this);"><%=label.get("loan.type")%></label>:<font color="red">*</font></td>
					<td width="25%" height="22" ><s:textfield name="loanTypeName"
						size="30" maxlength="70" onkeypress="return allCharacters();"></s:textfield></td>

					<td width="25%" class="formtext">&nbsp;</td>
					<td width="25%">&nbsp;</td>
				</tr>
				<tr>
					<td height="22" class="formtext" width="25%"><label
						class="set" name="loan.underdebithead" id="loan.underdebithead"
						ondblclick="callShowDiv(this);"><%=label.get("loan.underdebithead")%></label>:<font color="red">*</font></td>
					<td width="25%" height="22" colspan="1"><s:textfield
						name="Debitname" readonly="true" size="30"></s:textfield><s:hidden
						name="Debitcode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						id="ctrlHide" width="16"
						onclick="javascript:callsF9(500,325,'LoanMaster_f9debitaction.action');"></td>
					<td width="25%" height="22"><label class="set"
						name="loan.loanlimitamount" id="loan.loanlimitamount"
						ondblclick="callShowDiv(this);"><%=label.get("loan.loanlimitamount")%></label>:
					</td>
					<td width="25%" height="22"><s:textfield maxlength="10"
						name="loanLimitAmount" size="35"
						onkeypress="return checkNumbersWithDot(this);" ></s:textfield></td>
				</tr>
				<tr>
					<td colspan="1" width="22%"><label class="set" id="intType"
						name="intType" ondblclick="callShowDiv(this);"><%=label.get("intType")%></label>:</td>
					<td colspan="1" width="25%"><s:select name="interestType"
						onchange="return disableIntRate();" cssStyle="width:175" headerKey="" headerValue="-------Select--------"
						list="#{'N':'No Interest','F':'Flat Interest','R':'Reducing Principal','I':'Reducing Interest'}" />
					</td>
					<td colspan="1" width="25%" id='intRateTD'><label class="set"
						id="intRate" name="intRate" ondblclick="callShowDiv(this);"><%=label.get("intRate")%></label>:<font color="red">*</font></td>
					<td colspan="1" width="30%" id='intRateTD1'><s:textfield
						maxlength="4" size="35" theme="simple" name="interestRate"
						onkeypress="return checkNumbersWithDot(this);" /></td>

				</tr>
				<tr>
					<td colspan="1" width="25%"><label class="set" id="taxable"
						name="taxable" ondblclick="callShowDiv(this);"><%=label.get("taxable")%></label>:</td>

					<td colspan="1"><s:select cssStyle="width:175" headerKey=""
						headerValue="   --Select--      " list=" #{'Y':'Yes','N':'No'}"
						name="taxable" /></td>


					<td colspan="1" width="22%"></td>
					<td colspan="2" width="25%"></td>
				</tr>
				<tr>
				<td colspan="1" width="22%"><label class="set"
						id="other.loan.terms" name="other.loan.terms"
						ondblclick="callShowDiv(this);"><%=label.get("other.loan.terms")%></label>:</td>
					<td colspan="3" width="25%"><s:textarea cols="93" rows="1" theme="simple"	name="otherLoanTerms"  /></td>
				
				</tr>
				<tr>
					<td width="20%" colspan="1"><label id="admin.approval"
						name="admin.approval" ondblclick="callShowDiv(this);"><%=label.get("admin.approval")%></label>:<font color="red">*</font></td>
					<!--<td width="25%"><s:textfield name="reqReplacing" size="25" maxlength="50"/></td>

					-->

					<td width="60%" colspan="3"><s:textfield name="adminToken"
						size="30" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /><s:textfield
						name="adminName" size="60" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="adminCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'LoanMaster_f9admin.action');">
					</td>


				</tr>
				<tr>

					<td width="20%" colspan="1"><label id="account.approval"
						name="account.approval" ondblclick="callShowDiv(this);"><%=label.get("account.approval")%></label>:<font color="red">*</font></td>
					<!--<td width="25%"><s:textfield name="reqReplacing" size="25" maxlength="50"/></td>

					-->

					<td width="60%" colspan="3"><s:textfield name="accountToken"
						size="30" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /><s:textfield
						name="accountName" size="60" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="accountCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'LoanMaster_f9account.action');">


					</td>


				</tr>
				<tr>
					<td colspan="1" width="25%"><label class="set"
						id="std.int.rate.SBI" name="std.int.rate.SBI"
						ondblclick="callShowDiv(this);"><%=label.get("std.int.rate.SBI")%></label>:<font color="red">*</font></td>
					<td colspan="1" width="30%"><s:textfield size="30"
						maxlength="4" theme="simple" name="stdIntRateSBI"
						onkeypress="return numbersWithDot();" /></td>

				</tr>
			</table>
			</td>
		</tr>

		<!-- Loan Type Configuration-->
		<!--
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Loan Type Configuration</strong></td>

				</tr>
				<tr>
					<td width="25%"><label class="set"
						name="admin.approval.required" id="admin.approval.required"
						ondblclick="callShowDiv(this);"><%=label.get("admin.approval.required")%></label><font
						color="red"></font> :</td>

					<td width="25%"><s:select id="admApprovalReq"
						cssStyle="width:135" name="admApprovalReq"
						list="#{'N':'No','Y':'Yes'}"
						onchange="return callAdmApprovalReq();" /></td>

					<td width="25%">&nbsp;</td>
					<td width="25%">&nbsp;</td>
				</tr>

				<tr id="admApprovalReqSection">
					<td width="20%" colspan="1"><label id="admin.approval"
						name="admin.approval" ondblclick="callShowDiv(this);"><%=label.get("admin.approval")%></label>
					:<font color="red">*</font></td>
					<td width="25%"><s:textfield name="reqReplacing" size="25" maxlength="50"/></td>

					

					<td width="60%" colspan="3"><s:textfield name="adminToken"
						size="25" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /><s:textfield
						name="adminName" size="60" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="adminCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'LoanMaster_f9admin.action');">
					</td>


				</tr>

				<tr>
					<td width="25%"><label class="set"
						name="accountant.approval.required"
						id="accountant.approval.required" ondblclick="callShowDiv(this);"><%=label.get("accountant.approval.required")%></label><font
						color="red"></font> :</td>

					<td width="25%"><s:select id="accApprovalReq"
						cssStyle="width:135" name="accApprovalReq"
						list="#{'N':'No','Y':'Yes'}"
						onchange="return callAccApprovalReq();" /></td>

					<td width="25%">&nbsp;</td>
					<td width="25%">&nbsp;</td>
				</tr>
				<tr id="accApprovalReqSection">

					<td width="20%" colspan="1"><label id="account.approval"
						name="account.approval" ondblclick="callShowDiv(this);"><%=label.get("account.approval")%></label>
					:<font color="red">*</font></td>
					<td width="25%"><s:textfield name="reqReplacing" size="25" maxlength="50"/></td>

					

					<td width="60%" colspan="3"><s:textfield name="accountToken"
						size="25" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /><s:textfield
						name="accountName" size="60" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="accountCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'LoanMaster_f9account.action');">


					</td>


				</tr>

			</table>
			</td>
		</tr>
		-->
		<tr>
			<td colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>
<script>

disableIntRate();
callAdmApprovalReq();
	callAccApprovalReq();
	
	function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
	return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
	}
	
	function saveFun()
	{	
	if(document.getElementById('paraFrm_divCode').value==""){
			alert("Please select Division");
			document.getElementById('paraFrm_divName').focus();
  			return false;
		}
		
		
     	var fieldName = ['paraFrm_loanTypeName', 'paraFrm_Debitname'];
     	var lableName = ['loan.type', 'loan.underdebithead'];
     	var badFlag = ['enter', 'select'];
		var fieldName1 = ['paraFrm_loanTypeName'];
       if(!validateBlank(fieldName,lableName,badFlag))
          return false;
       if(!f9specialchars(fieldName1))
          return false;
       
       var intType = document.getElementById('paraFrm_interestType').value;
	var interestRate = document.getElementById('paraFrm_interestRate').value;
	
	if(intType !='N'){
			if(interestRate ==""){
				alert("Please enter valid "+document.getElementById('intRate').innerHTML.toLowerCase());
				document.getElementById('paraFrm_interestRate').focus();
				return false;
			}
		}
		//alert(intType);   
        
       
		if(document.getElementById('paraFrm_adminCode').value=="")
		{
			alert("Please select "+document.getElementById('admin.approval').innerHTML.toLowerCase());
			document.getElementById('paraFrm_adminToken').focus();
		 	return false;	
		 	
		}
	  
          
		if(document.getElementById('paraFrm_accountCode').value=="")
		{
			alert("Please select "+document.getElementById('account.approval').innerHTML.toLowerCase());
			document.getElementById('paraFrm_accountToken').focus();
		 	return false;	
		 	
		}
	
	
	var sbiRate = document.getElementById('paraFrm_stdIntRateSBI').value;
        if(sbiRate =="" ){
				alert("Please enter valid "+document.getElementById('std.int.rate.SBI').innerHTML.toLowerCase());
				document.getElementById('paraFrm_stdIntRateSBI').focus();
				return false;
			}
			
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='LoanMaster_save.action';
		document.getElementById("paraFrm").submit();
		return true;
	}
	function editFun()
	{
		return true;
	}
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'LoanMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	function resetFun() {
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'LoanMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
  	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'LoanMaster_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function callAdmApprovalReq() {
	
	var actionReason= document.getElementById('admApprovalReq').value;
		
		if(actionReason == 'Y' ) {
		
			document.getElementById('admApprovalReqSection').style.display='';
			
		} else {
			document.getElementById('admApprovalReqSection').style.display='none';
			
			document.getElementById('paraFrm_adminCode').value='';
			document.getElementById('paraFrm_adminToken').value='';
			document.getElementById('paraFrm_adminName').value='';
		}
		
	}
	
	function callAccApprovalReq() {
	
	var actionReason= document.getElementById('accApprovalReq').value;
		
		if(actionReason == 'Y' ) {
		
			document.getElementById('accApprovalReqSection').style.display='';
			
		} else {
			document.getElementById('accApprovalReqSection').style.display='none';
			
			document.getElementById('paraFrm_accountCode').value='';
			document.getElementById('paraFrm_accountToken').value='';
			document.getElementById('paraFrm_accountName').value='';
			
		}
		
	}
		function disableIntRate(){
			var intType =document.getElementById("paraFrm_interestType").value ;
		if(intType =='N'){
			document.getElementById("paraFrm_interestRate").readOnly = 'true';
			document.getElementById("paraFrm_interestRate").value ="";
			document.getElementById("intRateTD").style.display='none';
			document.getElementById("intRateTD1").style.display='none';
		}else {
			document.getElementById("paraFrm_interestRate").readOnly = '';
			document.getElementById("intRateTD").style.display='';
			document.getElementById("intRateTD1").style.display='';
		}
	
	 
}

function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'LoanMaster_report.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	function checkNumbersWithDot(obj) {
	var count = 0;
	var txtNo = obj.value;
	
	for(var i = 0; i < txtNo.length; i++) {
		if(txtNo.charAt(i) == '.') {
			count = count + 1;
		}
	}
	
	if(count > 0) {
		if(!numbersOnly()) {
			return false;
		}
	} else if(!numbersWithDot()) {
		return false;
	}
	return true;
}
	
</script>