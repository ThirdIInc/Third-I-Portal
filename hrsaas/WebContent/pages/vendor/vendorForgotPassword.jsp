
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String comanyName = "";
	try {
		comanyName = (String) request.getAttribute("comanyName");
	} catch (Exception e) {

	}
%>

<s:form action="VendorLogin" id="paraFrm" theme="simple" name="form">
	<s:hidden name="infoId" value="%{loginBean.infoId}" />

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


	<table width="98%" border="0" align="left" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="2" width="100%"><img
				src="../pages/portal/images/line.gif" width="100%" height="7" /></td>
		</tr>

		<s:if test="showForgot">
		<tr align="left">
				<td height="30" colspan="2" align="left">
				<h1 class="headerDashlet">Forgot your password?</h1>
				</td>
			</tr>
			<tr align="left">
				<td colspan="2" class="contlink"><strong> To recover
				your password, type the Username and you will receive an automated
				password recovery email to your registered email id. </strong></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr align="left">
				<td class="contlink"><strong class="headerDashlet">User
				Name</strong></td>
				<td><s:textfield name="vendorUserNameForgot" maxlength="50" /></td>
			</tr>


			<tr align="left">
				<td>&nbsp;</td>
				<td><label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>
				<s:submit cssClass="token" theme="simple" value="Continue"
					onclick="return submitData();"
					action="VendorLogin_submitForgotVendorPassword" /></td>
			</tr>
			<tr align="left">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" width="100%"><img
					src="../pages/portal/images/line.gif" width="100%" height="7" /></td>
			</tr>
			<tr>
				<td width="8%">&nbsp;</td>
				<td width="92%">
				<div align="right" class="contlink">2010 - 2012 Glodyne
				Technoserve Limited. All Rights Reserved.<br />
				Best Viewed in resolution above 1024 x 768.Please disable popup
				blockers.</div>
				</td>
			</tr>
			<tr>
				<td width="8%">&nbsp;</td>
				<td width="92%"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td>
				<table>
				 

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
				</td>
			</tr>


		</s:else>
	</table>
</s:form>

<script>
 function submitData() {
try {
	var uName=document.getElementById('paraFrm_vendorUserNameForgot').value;
   	if(uName=="" ) {
   		alert("Please enter user name");
   		document.getElementById('paraFrm_vendorUserNameForgot').focus();
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