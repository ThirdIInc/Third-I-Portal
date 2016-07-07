<%@ taglib prefix="s" uri="/struts-tags"%>



<%@page import="org.paradyne.lib.Utility"%>
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
<s:form action="CustomerReportLogin" method="post" validate="true"
	theme="simple" name="Login" target="_top" id="paraFrm">

	<input type="hidden" name="hidCaptchaID" value="<%= session.getId() %>" />
	<%
		Object[][] listObj = null;
		String imgName, respTime = null;
		imgName = (String) request.getAttribute("imgName");
		respTime = (String) request.getAttribute("respTime");
		listObj = (Object[][]) request.getAttribute("listObj");
	%>

	<%
		String comanyName = (String) request.getAttribute("comanyName");
		String pool_name = "abc";
	%>
	<table width="100%" border="0" align="left" cellpadding="0"
		cellspacing="0">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="25%"><span style="float: left; width: 40%"><img
						src="../pages/portal/loginimages/logo.jpg" width="193" height="51" /></span></td>
					<td>
					<table width="80%" height="51" border="0" align="right"
						cellpadding="0" cellspacing="0">


						<tr>
							<td width="100%" align="right" nowrap="nowrap" class="emptext"><strong><%=comanyName%></strong>



							<%
								String cmpName = null;
								cmpName = (String) request.getAttribute("logo");

								System.out.println("String cmpName =" + cmpName);
								if (cmpName != null && !cmpName.equals("null")
										&& !cmpName.equals("")) {
									pool_name = (String) session.getAttribute("session_pool");
							%> <input type="hidden" name="compImg" value="<%=cmpName%>" /> <img
								align="absmiddle"
								src="../pages/Logo/<%=session.getAttribute("session_pool")%>/<%=cmpName%>"
								height="51" /> <%
 } else {
 %> <input type="hidden" name="compImg" value="client_logo.jpg" /> <img
								align="absmiddle"
								src="../pages/common/css/default/images/logo.jpg" height="51" />
							<%
							}
							%>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

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

		<tr>

			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>


					<td width="100%" valign="top">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td height="4" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td width="60%" align="center">
							<table cellpadding="0" cellspacing="0">
								
							</table>

							</td>

							<td width="40%" align="center">
							<table id="Table_01" align="center" width="200"
								style="border: 1px solid #CCC;" cellpadding="5" cellspacing="2">

								<tr>

									<td valign="top" bgcolor="#EBF1FB">
									<table border="0" width="100%" id="table6" cellpadding="0"
										cellspacing="0">
										<tr>
											<td colspan="2"><b class="bookmark">User Login</b></td>
										</tr> 
										<tr>
											<td width="26%" nowrap="nowrap">
											<p align="right" class="blue">User
											Name&nbsp;:&nbsp;&nbsp;
											</td>
											<td width="74%"><s:textfield name="loginName"
												cssClass="std" size="23" /></td>
										</tr>


										<tr>
											<td width="26%" nowrap="nowrap">
											<p align="right" class="blue">Password&nbsp;:&nbsp;&nbsp;
											
											</td>
											<td width="74%"><s:password name="loginPassword"
												cssClass="std" onkeypress="return fSubmit(event);" size="23"
												maxlength="15"
												onfocus="javascript:setKeyboardFocus('form','loginPassword');" />

											</td>
										</tr>

										<tr>
											<td width="26%">&nbsp;</td>
											<td width="74%"><s:submit
												action="CustomerReportLogin_submitnew" theme="simple"
												value="Login" onclick="return callSubmit();" /></td>
										</tr>

										<tr>
											<td colspan="2">
											<table class="label" cellSpacing="0" cellPadding="0"
												width="100%" border="0" id="table11">
												<tr vAlign="center" align="left">
													<td width="6">&nbsp;</td>
													<td height="22" colSpan="2">
													<div align="center" class="bookmark">New User<a
														href="#" class="contlink"
														onclick="javascript:callNewCandidate();" tabindex="5">&nbsp;Click
													here to register</a></div>
													</td>
												</tr>


											</table>
											</td>

										</tr>

										<tr>
											<td colspan="2" width="10%" align="center"><a href="#"
												class="contlink" onclick="callForgotCandidatePassword();">
											Forgot Password? </a></td>
										</tr>


									</table>
									</td>

								</tr>

							</table>
							</td>
						</tr>
						<tr>
							<td height="4">&nbsp;</td>
						</tr>

					</table>
					</td>

				</tr>

			</table>
			</td>
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
</s:form>

<script>
 
var entry_field		= "";
var form_name		= "";

function setKeyboardFocus(p_formname, p_fieldname) {
	form_name	= p_formname;
	entry_field = p_fieldname;
}

  init();
function init(){
	try {
		document.getElementById('loginName').focus();
	}
	catch(e) {
		
	}
}

function fSubmit(e){

	 var key;      
     if(window.event) {
		key = window.event.keyCode; //IE
	 } else {
		key = e.which;			//firefox      
	 }

	if(key != 13){
		return true;
	} else {
		return fLogon();
	}
}
 
function callSubmit() {
 	try
 	{
  		var pwd = document.getElementById('paraFrm_loginPassword').value; 
  		var name = document.getElementById('paraFrm_loginName').value;
 			if(name =="") {
  			alert("Please Enter User Name");
  			document.getElementById('paraFrm_loginName').focus();  			
  			return false;  			
  		}
  		if(pwd ==""){
  			alert("Please Enter Password");
  			document.getElementById('paraFrm_loginPassword').focus(); 
  			return false;
  		} else if(trim(pwd) == ""){
  			alert("White spaces are not allowed, Please enter valid password");
  			document.getElementById('paraFrm_loginPassword').value = ""; 
  			document.getElementById('paraFrm_loginPassword').focus(); 
  			return false;
  		}
  		var lname = document.getElementById('loginName').value; 
  		document.getElementById('Login_name').value=lname ;
  		return true;
  		}
  		catch(e)
  		{
  		}
  	}
  	
   
function callNewCandidate(){
	try{
		document.getElementById('paraFrm').action="CandidateRegistration_input.action";
		document.getElementById('paraFrm').submit();
	}catch(e){
		alert(e);
	}
}

function callForgotCandidatePassword() {
	try{
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action="CandidateRegistration_forgotPassword.action";
		document.getElementById('paraFrm').submit();
	}catch(e){
		alert(e);
	}
}
 
</script>