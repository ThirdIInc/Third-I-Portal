<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="CandidateRegistration" id="paraFrm" theme="simple"
	name="form">
	
		<%
	String comanyName = "";
	try {
		comanyName = (String) request.getAttribute("comanyName");
	} catch (Exception e) {

	}
%>
	
	<div style="width: 100%">
	<div style="float: left; width: 40%" align="left"><img
			src="../pages/common/css/default/images/logo.jpg"  /></div>
	<div class="logotext" style="float: right; width: 60%">
	<table width="80%" height="51" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td width="72%">
			<div align="right" class="logotext"><strong><%=comanyName%></strong></div>
			</td>
			<td width="4%">&nbsp;</td>
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
				src="../pages/common/css/default/images/logo.jpg"
				height="51" /> <%
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
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="20px" class="mainheader"></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
	

	<table width="100%" cellspacing="2" cellpadding="1" align="left"
		class="formbg">
		
		<tr>
			<td colspan="2" width="50%" align="center"><s:if
				test="forgotCandidatePasswordSubmitFlag">
				<table>
					<tr>
						<td colspan="3"><strong class="formhead"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Password
						Recovery</strong></td>
					</tr>

					<tr>
						<td colspan="3" align="center"><br>
						<b>Your password has been mailed to you, please check your
						mail for login details.</b><br>
						<br>
						</td>
					</tr>

					<tr>
						<td colspan="3" align="center"><input type="button"
							class="token" theme="simple" value="Close Window"
							onclick="closeWin();" /></td>
					</tr>
				</table>
			</s:if> <s:else>
				<table>
					<tr>
						<td colspan="3"><strong class="formhead"><img align="absmiddle"
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Password
						Recovery</strong></td>
					</tr>



					<tr>
						<td colspan="2">&nbsp;</td>
						<s:hidden name="infoId" value="%{loginBean.infoId}"></s:hidden>
					</tr>

					<tr>
						<td colspan="1" width="10%">User Name :</td>
						<td colspan="1" width="30%"><s:textfield
							name="userNameForgot" id="userNamefg" maxlength="50" /></td>
					</tr>

					<tr>
						<td colspan="1">&nbsp;</td>
						<td colspan="1" width="30%" align="left"><s:submit
							cssClass="token" theme="simple" value="Continue"
							onclick="return submitFun();"
							action="CandidateRegistration_submitForgotCandidatePassword" />
						</td>
					</tr>
				</table>
			</s:else></td>
		</tr>
		
			<tr>
			<td height="28" bgcolor="#f2f2f2" class="border1">
			<table width="980" border="0" align="center">
				<tr>
					<td class="link">&reg;All Rights Reserved &copy;Copyright</td>
					<td class="txt">
					<div align="center"><span class="link">I / We
					acknowledge and accept the Terms and Conditions applicable and
					available on the site.</span></div>
					</td>
					<td class="txt">
					<div align="right"><script>
			function callWindowPage()
			{
			 window.open('../pages/policies/privacy.html','wind','width=550,height=275,scrollbars=no,resizable=yes,menubar=no,top=200,left=100');	 
			}
		 	
			</script><a href="javascript:void(0)"
						onClick="window.open('../pages/policies/Terms & Conditions.html','','width=800,height=600,scrollbars=yes,resizable=yes,menubar=no,top=0,left=0')"
						class="link"> Terms &amp; Conditions </a><strong class="link">
					| </strong><a href="javascript:void(0)" onClick="callWindowPage()"
						class="link">Privacy Policy</a></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td height="28">
			<table width="980" border="0" align="center">
				<tr>
					<td width="133" valign="top" class="txt">
					<div align="left"><img
						src="../pages/portal/loginimages/siteseal_sf_3_h_l_m.gif" alt=""
						width="132" height="31"
						onclick="window.open('https://seal.starfieldtech.com/verifySeal?sealID=jK9ZWVummW3yGgtypDRq8Jph5lCiEkC0DJ1OYelJbk74WEArSN');"
						src="<%=request.getContextPath()%>/pages/portal/images/siteseal_sf_3_h_l_m.gif" /></div>
					</td>
					<td width="837" valign="top" class="link"><a
						href="http://www.starfieldtech.com/" class="link">SSL</a></td>
				</tr>
			</table>
		</tr>
	</table>
</s:form>


<script>
function submitFun() {
try {
	var uName=document.getElementById('userNamefg').value;
   	if(uName=="" ) {
   		alert("Please enter user name");
   		document.getElementById('userNamefg').focus();
   		return false;
   	}
  } catch(e) {
  	alert(e);
  }
   return true;
}

function closeWin() {
	window.close();
}
</script>
