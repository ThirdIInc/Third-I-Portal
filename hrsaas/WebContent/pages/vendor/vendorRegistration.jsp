<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="VendorRegistration" id="paraFrm" theme="simple"
	method="POST">
	<%
			String comanyName = "";
			try {
				comanyName = (String) request.getAttribute("comanyName");
			} catch (Exception e) {

			}
		%>

	<div style="width: 100%">
	<div style="float: left; width: 40%" align="left"><img
		src="../pages/common/css/default/images/logo.jpg" /></div>
	<div class="logotext" style="float: right; width: 60%">
	<table width="80%" height="51" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="72%">
			<div align="right" class="logotext"><strong><%=comanyName%></strong></div>
			</td>
			<td width="4%"></td>
			<td width="24%">
			<%
				String cmpName = null;
				cmpName = (String) request.getAttribute("logo");

				System.out.println("String cmpName =" + cmpName);
				if (cmpName != null && !cmpName.equals("null")
						&& !cmpName.equals("")) {
			%> <input type="hidden" name="compImg" value="<%=cmpName%>" /> <img
				src="../pages/Logo/<%=session.getAttribute("session_pool")%>/<%=cmpName%>"
				height="51" /> <%
 } else {
 %> <input type="hidden" name="compImg" value="client_logo.jpg" /> <img
				src="../pages/common/css/default/images/logo.jpg" height="51" /> <%
 }
 %>
			</td>
		</tr>
	</table>
	</div>
	</div>

	<tr>
		<td>
		<div
			style="background-color: #6979ac; float: left; width: 100%; margin: 0; padding: 0;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"></table>
		<tr>
			<td height="20px" class="mainheader"></td>
		</tr>
		</div>
		</td>
	</tr>

	<input type="hidden" name="hidCaptchaID" value="<%= session.getId() %>" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Partner
					Registration </strong></td>
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
				<tr>
					<td colspan="3">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
			</table>
			</td>
		</tr>
		<s:if test="submitFlag">
			<tr>
				<td colspan="2">
				<div id="mail">
				<table align="center">

					<tr>
						&nbsp;
					</tr>
					<tr>
						<td colspan="2" align="center">Registration process completed
						successfully. Your login details has been mailed, please check
						your mail box for login details.</td>
					</tr>
					<tr>
						<td colspan="1">&nbsp;</td>
						<td colspan="1" align="center">&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="button" class="token" theme="simple" value="Close Window"
							onclick="closeWin();" /></td>
					</tr>
				</table>
				</div>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td colspan="4">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="2"
							cellspacing="2">
							<tr>
								<td colspan="5" class="formhead"><strong
									class="forminnerhead"></strong></td>
							</tr>
							<tr>
								<td width="15%">Partner Name :<font color="red">*</font></td>
								<td width="85%"><s:textfield
									value="%{partnerName}" name="partnerName" theme="simple" size="30"
									maxlength="50" onkeypress="return charactersOnly();" /></td>
							</tr>
							<tr>
								<td width="15%">Partner Code :<font color="red">*</font></td>
								<td width="85%"><s:textfield name="partnerCode" theme="simple"
									readonly="false" size="30" maxlength="20"/></td>

							</tr>
							<tr>
								<td width="15%">Email Id :<font color="red">*</font></td>
								<td width="85%" nowrap="nowrap"><s:textfield name="emailId"
									theme="simple" size="30" maxlength="50" /></td>
							</tr>
							<tr>
								<td width="15%">Login Name :<font color="red">*</font></td>
								<td width="85%"><s:textfield name="loginName" theme="simple"
									readonly="false" size="30" maxlength="50"/></td>

							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td width="100%">
						<table width="100%">
							<tr>
								<td width="15%" class="style1" align="left"><font
									color="red">Image Authentication : Type the letters as
								shown below.</font></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td width="100%">
						<table width="100%" border="0">
							<tr>
								<td width="39%"><span class="wtxt"> <img
									src="VendorRegistration_getKeyCode.action"
									alt="Enter the characters appearing in this image" border="0"
									align="left" /></span></td>
								<td width="61%"><input name="inCaptchaChars"
									id="inCaptchaChars" type="text" size="10" /></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td width="100%">
						<table width="100%" border="0">
							<tr>
								<td width="35%" align="right"><input type="button"
									class="token" value="Submit" id="submitBtn"
									onclick="callInsert();" /> <input type="button" class="reset"
									value="Reset" onclick="resetFun();" /></td>
								<td width="65%"></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:else>				
	</table>
</s:form>
<script>

function callInsert(){

try{
  var pName= document.getElementById('paraFrm_partnerName').value;
  var pCode=document.getElementById('paraFrm_partnerCode').value;
  var email = document.getElementById('paraFrm_emailId').value;
  var userName =document.getElementById('paraFrm_loginName').value;
  var chars=trim(document.getElementById('inCaptchaChars').value);

  if(pName == null || pName== ""){
 		alert("Please enter the Partner Name");
		return false;
	}
	else if(pCode == null ||pCode == ""){
		alert("Please enter the Partner Code");
		return false;
	}
	else if(email == null ||email == ""){
	 alert("Please enter the Email ID");
	 return false;
	}
	else if(!validateEmail('paraFrm_emailId')){
		    	return false;
    }
	else if(userName == null ||userName == ""){
	 alert("Please enter the User Name");
	 return false;
	}
    else if(chars==""){
	 alert("Please enter the letters as they are displayed.");
	 document.getElementById('inCaptchaChars').focus();
	 return false;
    }
	else{
	 document.getElementById('submitBtn').disabled=true;
	 document.getElementById('paraFrm').action='VendorRegistration_save.action';
	 document.getElementById('paraFrm').submit();		
     }
	}
	catch(e){
	alert(e);
	}
 }
function closeWin(){
alert('bye');
	window.close();
}

function resetFun() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='VendorRegistration_reset.action';
		document.getElementById('paraFrm').submit();
}
</script>
