<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="D1AnalyticHome" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="accountNo"></s:hidden>
	<s:hidden name="dashBoardID"></s:hidden>
	<s:hidden name="accountID"></s:hidden>
	<s:hidden name="accountName"></s:hidden>
	
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
	  		<td colspan="3" width="100%">
			   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Change password </strong></td>
						<td width="3%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>
			</td>
	  	</tr>

		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
								<td>
									<s:submit cssClass="save" 
										theme="simple" value="    Save  " action="D1AnalyticHome_changePassWordClient"
										onclick="return validateSave();" /> 
										
								
								<s:submit cssClass= "save" 
								value="    Back  " onclick="return backFun();" theme="simple"/></td>
							<td >
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					<label></label></td>

				</tr>


				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								

								<tr>
									<td width="25%">Old Password :<font color="red">*</font></td>
									<td colspan="2" width="40%"><s:password size="25" name="customerOldPassword" maxlength="50" />
									</td>
								</tr>
								
								<tr>
									<td width="25%">New Password :<font color="red">*</font></td>
									<td colspan="2" width="40%"><s:password size="25" name="customerNewPassword" maxlength="16" />
									</td>
								</tr>
								
								<tr>
									<td width="25%">Confirm New Password :<font color="red">*</font></td>
									<td colspan="2" width="40%"><s:password size="25" name="customerConfirmPass" maxlength="16" />
									</td>
								</tr>
							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>

function validateSave(){

var newPass = document.getElementById('paraFrm_customerNewPassword').value;
var confPass = document.getElementById('paraFrm_customerConfirmPass').value;
var oldPass = document.getElementById('paraFrm_customerOldPassword').value;
if(oldPass == ""){
	alert('Please enter old password');
	return false;
}
if(newPass == ""){
	alert('Please enter new password.');
	return false;
}
if(confPass == ""){
	alert('Please enter confirm new password.');
	return false;
}

if(newPass != confPass){
	alert('New Password and Confirm New Password must be same.');
	return false;
}
return true;
}

function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'D1AnalyticHome_input.action';
		document.getElementById('paraFrm').submit();
	}
</script>