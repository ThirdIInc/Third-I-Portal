<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:hidden name="partnerChgCode"></s:hidden>
<s:form action="PartnerChgPassword" id="paraFrm" theme="simple"
	validate="true">
	<table width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<td valign="middle">
			<fieldset><legend class="legend"><img
				src="../pages/mypage/images/icons/earnings16.png" height="16"
				width="16"> &nbsp; Partner Change Password</legend>
			<table width="100%" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td></td>
				</tr>
				<tr>
					<td width="30%">
					<div align="right"><font color="red">*</font>Indicates
					Required</div>
					</td>
				</tr>
				<tr>					
					<td>
					<fieldset><legend class="legend1">Change
					Password </legend>
					<table border="0" align="left" cellpadding="2" cellspacing="2">
						<tr>
							<td width="20%"><label name="partnerNm" id="partnerNm"
								ondblclick="callShowDiv(this);"> <%=label.get("partnerNm")%>
							</label></td>
							<td width="1%">&nbsp;</td>
							<td width="1%">:</td>
							<td class="text1"><s:property value="partnerChgNm"></s:property></td>
						</tr>
						<tr>
							<td width="20%"><label name="partnerCode" id="partnerCode" 
								ondblclick="callShowDiv(this);">
							<%=label.get("partnerCode")%> </label></td>
							<td width="1%">&nbsp;</td>
							<td width="1%">:</td>
							<td class="text1"><s:property value="partnerChgCode" /></td>
						</tr>
						<tr>
							<td width="20%"><label name="oldPass" id="oldPass"
								ondblclick="callShowDiv(this);"> <%=label.get("oldPass")%>
							</label></td>
							<td width="1%" class="star">*</td>
							<td width="1%">:</td>							
							<td class="text1"><s:password name="partnerOldPass" size="20" /></td>
						</tr>
						<tr>
							<td width="20%"><label name="newPass" id="newPass"
								ondblclick="callShowDiv(this);"> <%=label.get("newPass")%>
							</label></td>
							<td width="1%" class="star">*</td>
							<td width="1%">:</td>							
							<td class="text1"><s:password name="partnerNewPass" /></td>
						</tr>
						<tr>
							<td width="20%"><label name="confPass" id="confPass"
								ondblclick="callShowDiv(this);"> <%=label.get("confPass")%>
							</label></td>
							<td width="1%" class="star">*</td>
							<td width="1%">:</td>							
							<td class="text1"><s:password name="partnerConfPass" /></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td>
					<fieldset>
					<table width="100%" border="0">
						<tr>
							<td>
							<table border="0" cellpadding="2" cellspacing="2" align="left">
								<tr>									
									<td width="1%"><img
										src="../pages/mypage/images/icons/draft.png"
										height="10" /></td>
									<td width="13%"><s:a href="#" onclick="return changeFun();">Change Password</s:a></td>
									<td width="1%">|</td>
									<td width="1%"><img
										src="../pages/mypage/images/icons/sendforapproval.png"
										height="10" border="0" /></td>
									<td width="5%"><s:a href="#" onclick=" return cancelFun();">Cancel</s:a></td>
									<td width="1%">|</td>
									<td width="75%"></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			</fieldset>
			</td>
		</tr>
	</table>
</s:form>
<script>

function changeFun(){
	var oldPass = document.getElementById('paraFrm_partnerOldPass').value;
	var newPass = document.getElementById('paraFrm_partnerNewPass').value;
	var confPass = document.getElementById('paraFrm_partnerConfPass').value;
	
	if(oldPass==""){
	    alert("Please enter the "+document.getElementById('oldPass').innerHTML.toLowerCase());
		document.getElementById('paraFrm_partnerOldPass').focus();
		return false; 	
	}
	if(newPass==""){
	    alert("Please enter the "+document.getElementById('newPass').innerHTML.toLowerCase());
		document.getElementById('paraFrm_partnerNewPass').focus();
		return false; 	
	}
	if(confPass==""){
	    alert("Please enter the "+document.getElementById('confPass').innerHTML.toLowerCase());
		document.getElementById('paraFrm_partnerConfPass').focus();
		return false; 	
	}	
	if(newPass != confPass){
		alert("New Password and Confirm Password Must be Same");
		document.getElementById('paraFrm_partnerConfPass').focus();
		return false;
	}
	document.getElementById('paraFrm').action="PartnerChgPassword_save.action"; 
	document.getElementById('paraFrm').submit();
}

function cancelFun(){
    document.getElementById('paraFrm').target = 'main';
    document.getElementById('paraFrm').action="PartnerChgPassword_reset.action"; 
	document.getElementById('paraFrm').submit();

}
</script>
