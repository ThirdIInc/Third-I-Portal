<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ReimbConfiguration_" validate="true" id="paraFrm"	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reimbursement
					Configuration</strong></td>

					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td width="27%"><label class="set" name="reimb.Head.sel"
						id="reimb.Head.sel" ondblclick="callShowDiv(this);"><%=label.get("reimb.Head.sel")%></label>
					<font color="red">*</font>:</td>
					<td width="26%" colspan="3"><s:hidden name="IdHead"></s:hidden><s:hidden
						name="TypeHead"></s:hidden><s:textfield size="25" theme="simple"
						maxlength="60" name="reimbHead"
						onkeypress="return allCharacters();" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						id='ctrlHide'
						onclick="javascript:callsF9(500,325,'ReimbConfiguration_f9ReimbursementHead.action')"
						width="16" height="15"></td>
					</td>
				</tr>
				<tr>
					<td width="27%"><label class="set"
						name="reimb.Manager.Approval.required"
						id="reimb.Manager.Approval.required"
						ondblclick="callShowDiv(this);"><%=label.get("reimb.Manager.Approval.required")%></label><font
						color="red">*</font> :</td>
					<td colspan="3" width="26%"><s:select
						name="reimbManagerApproval" disabled="false" headerKey=" "
						headerValue="--Select--" list="#{'Y':'Yes','N':'No'}" /></td>
					<!--<s:select
									name="Status" disabled="false" headerKey=" "
									headerValue="--Select--" list="#{'A':'Active','D':'Deactive'}"
									cssStyle="width:180;z-index:5;" /></td>-->
				</tr>
				<tr>
					<td width="27%"><label class="set"
						name="reimb.Admin.Approval.required"
						id="reimb.Admin.Approval.required" ondblclick="callShowDiv(this);"><%=label.get("reimb.Admin.Approval.required")%></label><font
						color="red">*</font> :</td>
					<td colspan="3" width="26%"><s:select
						name="reimbAdminApproval" theme="simple" onchange="callAdmin()"
						headerKey="" headerValue="--Select--" list="#{'Y':'Yes','N':'No'}" /></td>
				</tr>

				<tr id="reimbAdmin">
					<td width="27%"><label class="set" name="reimb.Admin.sel"
						id="reimb.Admin.sel" ondblclick="callShowDiv(this);"><%=label.get("reimb.Admin.sel")%></label><font
						color="red">*</font> :</td>
					<td width="26%" colspan="3"><s:hidden name="EmpIdAdmin"></s:hidden><s:hidden
						name="EmpTokenAdmin"></s:hidden><s:textfield size="25"
						theme="simple" maxlength="60" name="reimbAdmin"
						onkeypress="return allCharacters();" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						id='ctrlHide'
						onclick="javascript:callsF9(500,325,'ReimbConfiguration_searchAdmin.action')"
						width="16" height="15"></td>
					</td>
				</tr>
				<tr>
					<td width="27%"><label class="set"
						name="isAccountantRed" id="isAccountantRed" ondblclick="callShowDiv(this);"><%=label.get("isAccountantRed")%></label><font
						color="red">*</font> :</td>
					<td colspan="3" width="26%"><s:select
						name="reimbAccountantReqd" theme="simple" onchange="callAccountant()"
						headerKey="" headerValue="--Select--" list="#{'Y':'Yes','N':'No'}" /></td>
				</tr>
				<tr id="accountantBlock">
					<td width="27%"><label class="set" name="reimb.Accountant.sel"
						id="reimb.Accountant.sel" ondblclick="callShowDiv(this);"><%=label.get("reimb.Accountant.sel")%></label><font
						color="red">*</font> :</td>
					<td width="26%" colspan="3"><s:hidden name="EmpIdAccountant"></s:hidden><s:hidden
						name="EmpTokenAccountant"></s:hidden><s:textfield size="25"
						theme="simple" maxlength="60" name="reimbAccountant"
						onkeypress="return allCharacters();" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						id='ctrlHide'
						onclick="javascript:callsF9(500,325,'ReimbConfiguration_searchAccountant.action')"
						width="16" height="15"></td>
					</td>
				</tr>

				<tr>
					<td width="27%"><label class="set" name="payment.Mode"
						id="payment.Mode" ondblclick="callShowDiv(this);"><%=label.get("payment.Mode.sel")%></label><font
						color="red">*</font> :</td>
					<!--<td colspan="2" width="55%"><s:select name="paymentMode"
						theme="simple" headerKey=" " headerValue="--Select--" onchange="callpaymentMode()"
						list="#{'CS':'Cash','CQ':'Cheque','T':'Transfer','S':'Salary'}"  /></td>
				-->
					<td width="26%" colspan="3"><s:checkbox name="paymentModeCash"
						theme="simple"></s:checkbox><label>Cash</label> <s:checkbox
						name="paymentModeCheque" theme="simple"></s:checkbox><label>Cheque</label>
					<s:checkbox name="paymentMode" theme="simple"
						onclick="callpaymentMode()"></s:checkbox><label>Transfer</label> <s:checkbox
						name="paymentModeSalary" theme="simple"></s:checkbox><label>Salary</label>
					</td>

				</tr>

				<tr id="trnsferacc">
					<td width="27%"><label class="set" name="transfer.Account"
						id="transfer.Account" ondblclick="callShowDiv(this);"><%=label.get("transfer.Account")%>:</label>
					</td>
					<td colspan="3" width="26%"><s:select name="transferAccount"
						theme="simple" headerKey="" headerValue="--Select--"
						list="#{'S':'Salary Account','R':'Reimbursement Account'}" /></td>
				</tr>
				<tr>
					<td width="27%"><label class="set" name="reimbursement.Period"
						id="reimbursement.Period" ondblclick="callShowDiv(this);"><%=label.get("reimbursement.Period")%></label><font
						color="red">*</font> :</td>
					<td colspan="3" width="28%"><s:select
						name="reimbursementPeriod" theme="simple" headerKey=" "
						headerValue="--Select--"
						list="#{'M':'Monthly','Q':'Quarterly','A':'Annually','H':'Half yearly','F':'Frequent'}" /></td>
				</tr>

				<tr>
					<td width="27%"><label class="set" name="carrier.Forward"
						id="carrier.Forward" ondblclick="callShowDiv(this);"><%=label.get("carrier.Forward")%></label><font
						color="red">*</font>:
					</td>
					<td colspan="3" width="28%"><s:select name="carrierForward"
						theme="simple" headerKey=" " headerValue="--Select--"
						onchange="callcarrierForward()" list="#{'Y':'Yes','N':'No'}" /></td>
				</tr>

				<tr id="carrierPercentg">
					<td width="27%"><label class="set" name="carrier.Percentage"
						id="carrier.Percentage" ondblclick="callShowDiv(this);"><%=label.get("carrier.Percentage")%><font
						color="red">*</font>:</label>
					</td>
					<td  width="20%"><s:textfield
						name="carrierPercentage" onkeypress="return numbersOnly();"
						onkeyup="return numbersOnly();"></s:textfield></td>
					<td width="20%"><label class="set" name="validPeriod"
						id="validPeriod" ondblclick="callShowDiv(this);"><%=label.get("validPeriod")%>:</label>
					</td>
					<td width="20%"><s:textfield name="carryFwdPeriod" size="8" onkeypress="return numbersOnly();"
						onkeyup="return numbersOnly();" />
						<input type="text" name="carriedFwdReimbPeriod" id="paraFrm_carriedFwdReimbPeriod" style='border:none' size="6" />
					</td>
				</tr>
				<tr>
					<td width="27%"><label class="set" name="advance.Allowed"
						id="advance.Allowed" ondblclick="callShowDiv(this);"><%=label.get("advance.Allowed")%></label>
						<font
						color="red">*</font>:
					</td>
					<td colspan="3" width="28%"><s:select name="advanceAllowed"
						theme="simple" headerKey=" " headerValue="--Select--"
						onchange="callAdvAllowed()" list="#{'Y':'Yes','N':'No'}" /></td>
				</tr>
				<tr id="advAllowedBlock">
					<td width="20%"><label class="set" name="maxAdvAllowed"
						id="maxAdvAllowed" ondblclick="callShowDiv(this);"><%=label.get("maxAdvAllowed")%></label><font
						color="red">*</font>:
					</td>
					<td width="30%" colspan="3">
					<label class="set" name="eligibleAmt"
						id="eligibleAmt" ondblclick="callShowDiv(this);"><%=label.get("eligibleAmt")%></label>
					<s:textfield name="advAllowedPeriod" size="8" onkeypress="return numbersOnly();"
						onkeyup="return numbersOnly();" />
						<input type="text" name="advanceAllowedReimbPeriod" id="paraFrm_advanceAllowedReimbPeriod" style='border:none' size="6" />
					</td>
				</tr>
				<tr>
					<td nowrap="nowrap" width="27%"><label class="set"
						name="year.from" id="year.from" ondblclick="callShowDiv(this);"><%=label.get("year.from")%>:</label>
					</td>
					<td width="20%"><s:hidden name="yearFrom"/>
					<s:select name="startmonth"
						list="#{'':'---Select---','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
					<td nowrap="nowrap" width="20%"><label class="set"
						name="year.to" id="year.to" ondblclick="callShowDiv(this);"><%=label.get("year.to")%>:</label>
					</td>
					<td width="20%"><s:hidden name="yearTo" />
					<s:select name="endmonth"
						list="#{'':'---Select---','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>
<script>
  	onload();
  	function saveFun() {
		
			var reimbHead = trim(document.getElementById('paraFrm_reimbHead').value);
		

		if(reimbHead==""){

			alert("Please select "+document.getElementById('reimb.Head.sel').innerHTML.toLowerCase());
			return false;
			
		}
		
		var reimbManagerApproval = trim(document.getElementById('paraFrm_reimbManagerApproval').value);
	
		
		if(reimbManagerApproval==""){
			alert("Please select "+document.getElementById('reimb.Manager.Approval.required').innerHTML.toLowerCase());
			return false;
		}
		var reimbAdminApproval = trim(document.getElementById('paraFrm_reimbAdminApproval').value);
	
		
		if(reimbAdminApproval==""){
			alert("Please select "+document.getElementById('reimb.Admin.Approval.required').innerHTML.toLowerCase());
			return false;
		}
		var reimbAdminApproval=document.getElementById('paraFrm_reimbAdminApproval').value;
	
	   if(reimbAdminApproval=="Y")
	   {
		var reimbAdmin = trim(document.getElementById('paraFrm_reimbAdmin').value);
	
		if(reimbAdmin==""){
			alert("Please select Admin");
			return false;
		}
		}
		
		
		var reimbAccountant=document.getElementById('paraFrm_reimbAccountantReqd').value;
		
		if(reimbAccountant==""){
			alert("Please select "+document.getElementById('isAccountantRed').innerHTML.toLowerCase());
			return false;
		}
		if(reimbAccountant=="Y") {
			var reimbAccountant = trim(document.getElementById('paraFrm_reimbAccountant').value);
			if(reimbAccountant==""){
				alert("Please select Accountant");
				return false;
			}
		}
		
		var paymentMode =document.getElementById('paraFrm_paymentMode').checked;
		var paymentModeCash = document.getElementById('paraFrm_paymentModeCash').checked;
			var paymentModeCheque = document.getElementById('paraFrm_paymentModeCheque').checked;
				var paymentModeSalary = document.getElementById('paraFrm_paymentModeSalary').checked;
		
		if(!paymentMode&&!paymentModeCash&&!paymentModeCheque&&!paymentModeSalary){
			
			alert("Please select "+document.getElementById('payment.Mode').innerHTML.toLowerCase());
			return false;
		}
		if(paymentMode){
		
			if(document.getElementById('paraFrm_transferAccount').value==''){
			alert("Please select "+document.getElementById('transfer.Account').innerHTML.toLowerCase());
			return false;
			}
			
		}
	
		var reimbursementPeriod = trim(document.getElementById('paraFrm_reimbursementPeriod').value);
		
		if(reimbursementPeriod==""){
			alert("Please select "+document.getElementById('reimbursement.Period').innerHTML.toLowerCase());
			return false;
		}
		
		var carryForward = trim(document.getElementById('paraFrm_carrierForward').value);
		if(carryForward==""){
			alert("Please select "+document.getElementById('carrier.Forward').innerHTML.toLowerCase());
			return false;
		}
		
		if(carryForward=="Y") {
			var carryFwd = trim(document.getElementById('paraFrm_carrierPercentage').value);
			if(carryFwd==""){
				alert("Please enter Percentage carried forward");
				return false;
			}
		}
		var advAllowed = trim(document.getElementById('paraFrm_advanceAllowed').value);
		if(advAllowed==""){
			alert("Please select "+document.getElementById('advance.Allowed').innerHTML.toLowerCase());
			return false;
		}
		if(advAllowed=="Y") {
			var advance = trim(document.getElementById('paraFrm_advAllowedPeriod').value);
			if(advance==""){
				alert("Please enter max advance allowed");
				return false;
			}
		}
		
  		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'ReimbConfiguration_save.action';
		document.getElementById('paraFrm').submit();
      	
  			}
  			
  			function searchFun(){
  			
  			
  			if(navigator.appName == 'Netscape') {
				var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
			} else {
				var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
			}
			
			document.getElementById("paraFrm").target = 'myWin';
			document.getElementById("paraFrm").action ='ReimbConfiguration_searchConfiguration.action';
			document.getElementById("paraFrm").submit();
  	}
		
  	
  		function resetFun() {
		
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'ReimbConfiguration_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun(){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="ReimbConfiguration_back.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	function deleteFun() 
{
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ReimbConfiguration_delete.action';
		document.getElementById('paraFrm').submit();
	}
	
	
}
	
	
	function editFun() {
		return true;
	}
	function callAdmin(){
	
	var reimbAdminApproval=document.getElementById('paraFrm_reimbAdminApproval').value;
	//alert('......................................'+ reimbAdminApproval);
	if(reimbAdminApproval=="Y")
	{
	
	document.getElementById("reimbAdmin").style.display="";
	}
	else{
		document.getElementById("reimbAdmin").style.display="none";
			document.getElementById("paraFrm_reimbAdmin").value="";
	}
	}
	
	function callpaymentMode(){
	var paymentMode=document.getElementById('paraFrm_paymentMode').checked;
//alert('......................................'+ paymentMode);
	if(paymentMode==true)
	{
	document.getElementById("trnsferacc").style.display="";
	}
	else{
		document.getElementById("trnsferacc").style.display="none";
		document.getElementById("paraFrm_transferAccount").value="";
	}
	}
	
	function callcarrierForward(){
		var carrierForward=document.getElementById('paraFrm_carrierForward').value;
		var reimbPeriod = document.getElementById('paraFrm_reimbursementPeriod').value;
		if(reimbPeriod != 'A'){
			if(reimbPeriod=='M' ){
				document.getElementById('paraFrm_carriedFwdReimbPeriod').value='Months';
			}else if(reimbPeriod=='Q'){
				document.getElementById('paraFrm_carriedFwdReimbPeriod').value='Quarter';
			}else if(reimbPeriod=='H'){
				document.getElementById('paraFrm_carriedFwdReimbPeriod').value='Half Year';
			}else if(reimbPeriod=='F'){
				document.getElementById('paraFrm_carriedFwdReimbPeriod').value='Days';
			}else{
					document.getElementById('paraFrm_advanceAllowedReimbPeriod').value='';
			}
	}

	if(carrierForward=="Y")
	{
	document.getElementById("carrierPercentg").style.display="";
	}
	else{
		document.getElementById("carrierPercentg").style.display="none";
		document.getElementById("paraFrm_carrierPercentage").value="";
	}
	}
	function onload(){
		callAdmin();
		callcarrierForward();
		callpaymentMode();
		callAccountant();
		callAdvAllowed();
	}
	
	function callAccountant(){
		
		var reimbAccountant=document.getElementById('paraFrm_reimbAccountantReqd').value;
		if(reimbAccountant=="Y"){
			document.getElementById("accountantBlock").style.display="";
		}else{
			document.getElementById("accountantBlock").style.display="none";
			document.getElementById("paraFrm_reimbAccountant").value="";
		}
	}
	function callAdvAllowed(){
		var reimbPeriod = document.getElementById('paraFrm_reimbursementPeriod').value;
		var reimbAdvAllowed=document.getElementById('paraFrm_advanceAllowed').value;
		if(reimbAdvAllowed=="Y"){
			document.getElementById("advAllowedBlock").style.display="";
			if(reimbPeriod != 'A'){
				if(reimbPeriod=='M' ){
					document.getElementById('paraFrm_advanceAllowedReimbPeriod').value='Months';
				}else if(reimbPeriod=='Q'){
					document.getElementById('paraFrm_advanceAllowedReimbPeriod').value='Quarter';
				}else if(reimbPeriod=='H'){
					document.getElementById('paraFrm_advanceAllowedReimbPeriod').value='Half Year';
				}else if(reimbPeriod=='F'){
					document.getElementById('paraFrm_advanceAllowedReimbPeriod').value='Days';
				}else{
					document.getElementById('paraFrm_advanceAllowedReimbPeriod').value='';
				}
			}
		}else{
			document.getElementById("advAllowedBlock").style.display="none";
			document.getElementById("paraFrm_advAllowedPeriod").value="";
		}
	}
	
</script>


