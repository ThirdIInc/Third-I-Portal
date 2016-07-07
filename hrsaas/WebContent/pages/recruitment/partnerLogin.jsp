<%@ taglib prefix="s" uri="/struts-tags"%>



<style type="text/css">
.txt {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #333333;
	text-decoration: none;
}

.textfield {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #333333;
	text-align: left;
	padding-left: 3px;
	padding-top: 5px;
	border: none;
	background-image: url(../pages/images/button2.png);
	text-decoration: none;
	height: 25px;
	width: 105px;
	border: none;
}

.head_bg {
	background-image: url(../pages/images/head_bg.gif);
	border: 1px solid #abc1e4;
}

.font_size1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	color: #000000;
	text-decoration: none;
}

.font_size2 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #000000;
	text-decoration: none;
}

.font_size3 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	color: #000000;
	text-decoration: none;
}

.font_size4 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 14px;
	color: #000000;
	text-decoration: none;
}

.bookmark {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #CC0000;
	padding-left: 3px;
	text-decoration: none;
}

.blue {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #1f376b;
	text-decoration: none;
}

.style1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	font-weight: bold;
}

.bookmark1 {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	font-weight: bold;
	color: #CC0000;
	text-decoration: none;
}

.button {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	font-weight: bold;
	color: #002B55;
	background-image: url(../pages/images/button1.png);
	text-decoration: none;
	border: none;
	height: 25px;
	width: 25px;
}

.bigbutton {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11px;
	font-weight: bold;
	color: #002B55;
	background-image: url(../pages/images/button2.png);
	text-decoration: none;
	background-color: #e0edfc;
	border: none;
	height: 25px;
	width: 105px;
}
</style>
<link href="images/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="../pages/common/include/javascript/virtualkeyboard.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/RS_01_eng.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/fp_AA.js"></script>
<body onLoad="init(); return false" topmargin="">
<s:form action="PartnerLogin" method="post" validate="true" theme="simple"
	name="Login" target="_top" id="paraFrm">

	<input type="hidden" name="hidCaptchaID" value="<%= session.getId() %>" />
	<%
		String imgName, respTime = null;
		imgName = (String) request.getAttribute("imgName");
		respTime = (String) request.getAttribute("respTime");
	%>
	<table width="99%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		 <tr>
			<td valign="top">
				<img src="../pages/common/css/default/images/logo.png">
			</td>
			<td valign="top">
				<%
					String cmpName = null;
					cmpName = (String) request.getAttribute("logo");
					if (cmpName != null && !cmpName.equals("null")
							&& !cmpName.equals("")) {
				%> 
				<input type="hidden" name="compImg" value="<%=cmpName%>" />
			</td>
			<td height="50" valign="top" width="40%" align="right">
				<img src="../pages/Logo/<%=session.getAttribute("session_pool")%>/<%=cmpName%>" />
				
			<%
			} else {
			%> <input type="hidden" name="compImg" value="client_logo.jpg" /> <img
				src="../pages/images/client_logo.jpg" height="50" align="right" />
			<%
 }
 %>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="head_bg">
				<tr>
					<td><img src="../pages/images/line.gif" width="1" height="21">
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		<tr>
			
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="35%" valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td height="4"></td>
						</tr>
						<tr>
							<td align="center">
							<table id="Table_01" width="100" border="0" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="4%">
									<div align="right"><img src="../pages/images/tab_01.gif"
										width="13" height="16" alt=""></div>
									</td>
									<td width="90%"><img src="../pages/images/tab_02.gif"
										width="308" height="16" alt=""></td>
									<td width="6%"><img src="../pages/images/tab_03.gif"
										width="16" height="16" alt=""></td>
								</tr>
								<tr>
									<td background="../pages/images/tab_04.gif">
									<div align="right"><img src="../pages/images/tab_04.gif"
										width="13" height="4" alt=""></div>
									</td>
									<td valign="top" bgcolor="#EBF1FB">
									<table border="0" width="95%" id="table6">
										<tr>
											<td colspan="2"><b class="bookmark"> Login</b></td>
										</tr>
										<tr>
											<td width="26%">
											<p align="right" class="blue">User Name:
											</td>
											<td width="74%">
												<s:textfield name="loginName" cssClass="std" size="23" />
											</td>
										</tr>
										
									
											<tr>
												<td width="26%">
												<p align="right" class="blue">Password:
												</td>
												<td width="74%"><s:password name="loginPassword" cssClass="std" size="23" maxlength="15" />
												</td>
											</tr>
								
										<tr>
											<td width="26%">&nbsp;</td>
											<td width="74%">
												<s:submit action="PartnerLogin_submitUser" theme="simple"
													value="Login" onclick="return validateLogin();"  />
											</td>
										</tr>
										
										<tr>
											<td colspan="2" width="10%" align="center">
												<a href="#" class="bookmark"
													onclick="callForgotPartnerPassword();"> Forgot Password? </a></td>
										</tr>
										
										<tr>
											<td colspan="2">
											<table class="label" cellSpacing="0" cellPadding="0"
												width="100%" border="0" id="table11">
												<tr vAlign="center" align="left">
													<td width="6">&nbsp;</td>
													<td height="22" colSpan="2"> 
													 
													 	<%
				System.out.println("hrsaas.......fkjXrJo4FKer5PR1Q5FJ5Q=="
				+ "fort_________TIxS2uuT3RMp9WGxdbhufQ=="
				+ "Test__________7mar5Lf/k7Ap9WGxdbhufQ=="
				+ "dummy_________LYWDHQrytDA+18JM3NC3Sw=="

				+ "DUMMY1------------LYWDHQrytDDy4hrnLugp3Q=="
				+ request.getParameter("infoId"));
	%> 
	

		<input type="hidden" name="infoId"
		value='<%=request.getParameter("infoId") %>' />
		
	<s:hidden name="name" value="%{name}" />
	<s:hidden name="invalidFlag" value="%{invalidFlag}" />

	<s:hidden name="count" value="%{count}" />
	<s:hidden name="invalidCount" value="%{invalidCount}" id="invalidCount" />
	<s:hidden name="chatrooms" value="1" />
	<s:hidden name="actionMessage" />
	<s:hidden name="settingFlag" />
													</td>
												</tr>

												
											</table>
											</td>

										</tr>
										
										
									</table>
									</td>
									<td background="../pages/images/tab_06.gif"><img
										src="../pages/images/tab_06.gif" width="16" height="4" alt=""></td>
								</tr>
								<tr>
									<td>
									<div align="right"><img src="../pages/images/tab_07.gif"
										width="13" height="18" alt=""></div>
									</td>
									<td><img src="../pages/images/tab_08.gif" width="308"
										height="18" alt=""></td>
									<td><img src="../pages/images/tab_09.gif" width="16"
										height="18" alt=""></td>
								</tr>
							</table>
							</td>
						</tr>
					 </table>
					 </td>
		 </tr>
	  </table>
    </td>
   </tr>
   
   <tr>
		<td height="25" colspan="3" class="txt">
			<div align="center">&copy; Copyright 2000-2011 peoplepower.com,
			inc. Human Resource Management System (HRMS) All rights reserved
			Various trademarks held by their respective owners</div>
		</td>
   </tr>
  </table>
</s:form>

<script>
function init(){
	var userName = trim(document.getElementById('paraFrm_loginName').value);
	try {
		if(userName == "") {
  			document.getElementById('paraFrm_loginName').focus();
  		} else {
  			document.getElementById('paraFrm_loginPassword').focus();
  		}
  	}	
	 catch(e) {
		//alert("Error in init method : "+e);	
	}
}


 	function validateLogin() { 
 	try {
 			var name = trim(document.getElementById('paraFrm_loginName').value);
  			var pwd = document.getElementById('paraFrm_loginPassword').value; 
  	 		if(name =="")  {
  				alert("Please Enter User Name");  
  				document.getElementById('paraFrm_loginName').focus();			
  				return false;  			
  			}
  		if(pwd =="") {
  			alert("Please Enter Password");
  			document.getElementById('paraFrm_loginPassword').focus();
  			document.getElementById('paraFrm_loginPassword').value = "";
  			return false;
  		} else if(trim(pwd)==""){
  			alert("White spaces are not allowed, Please enter valid password");
  			document.getElementById('paraFrm_loginPassword').focus();
  			document.getElementById('paraFrm_loginPassword').value = "";
  			return false;
  		}
  		
  	}catch(e) {
  			alert("Error occured in validateLogin : "+e);
  		}
 }
 
 function callForgotPartnerPassword() {
	try{
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action="PartnerLogin_forgotPartnerPassword.action";
		document.getElementById('paraFrm').submit();
	}catch(e){
		alert(e);
	}
}
</script>

						