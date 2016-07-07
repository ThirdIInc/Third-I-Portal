<%@ taglib prefix="s" uri="/struts-tags"%>
<body>
<link rel="stylesheet" type="text/css" title="default-theme"
	href="../pages/common/css/first/first.css" />
	
	<link rel="alternate stylesheet" type="text/css" media="screen" title="second-theme"
	href="../pages/common/css/second/second.css" />
	
	<link rel="alternate stylesheet" type="text/css" media="screen" title="third-theme"
	href="../pages/common/css/third/third.css" />
		<link rel="alternate stylesheet" type="text/css" media="screen" title="fourth-theme"
	href="../pages/common/css/fourth/fourth.css" />
	
		<link rel="alternate stylesheet" type="text/css" media="screen" title="fifth-theme"
	href="../pages/common/css/fifth/fifth.css" />
	<script type="text/javascript"
	src="../pages/common/include/javascript/styleswitch.js"></script>
<s:form action="PartnerLogin" id="paraFrm" theme="simple" name="form">

	<table width="100%" cellspacing="2" cellpadding="1" align="center"
		class="formbg">
		<tr>
			<td colspan="2" width="50%" align="center">
			<s:if test="forgotPasswordSubmitFlag">
				<table>
					<tr>
						<td  colspan="3" align="left"><strong
							class="formhead"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Password Recovery</strong>
						</td>
					</tr>

					<tr>
						<td colspan="3" align="center">
							<br><b>Your password has been mailed to you, please check your mail for login details.</b><br><br>
						</td>
					</tr>
			
					<tr>
						<td colspan="3" align="center">
							<input type="button" class="token" theme="simple" value="Close Window" onclick="closeWin();" />
						</td>
					</tr>
				 
				</table>
			</s:if>
			
			<s:else>
					<table>
					<tr>
						<td  colspan="3" align="left"><strong
							class="formhead"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Password Recovery</strong>
						</td>
					</tr>

					<tr>
						<td colspan="2">&nbsp;</td>
						<s:hidden name="infoId" value="%{loginBean.infoId}"></s:hidden>
					</tr>
			
					<tr>
						<td colspan="1" width="10%" align="center">User Name : </td>
						<td colspan="1" width="30%">
							<s:textfield name="partnerUserNameForgot" id="partnerUserNameForgot" maxlength="50"/>
						</td>
					</tr>
				 
					<tr>
						<td colspan="1" width="8%">&nbsp;</td>
						<td colspan="1" width="30%" align="left"><s:submit cssClass="token" theme="simple" value="Continue"
							onclick="return submitData();" action="PartnerLogin_submitForgotPartnerPassword" /> 
						</td>
					</tr>
				</table>
		   </s:else>
			
			
			
			</td>
		</tr>
	</table>
</s:form>
</body>

<script>
function submitData() {
try {
	var uName=document.getElementById('partnerUserNameForgot').value;
   	if(uName=="" ) {
   		alert("Please enter user name");
   		document.getElementById('partnerUserNameForgot').focus();
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

