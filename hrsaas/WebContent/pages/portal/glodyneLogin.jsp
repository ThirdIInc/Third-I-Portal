 
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<%
String comanyName = (String) request.getAttribute("comanyName");
String pool_name="abc";
%>
<title><%=comanyName%></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="../pages/portal/logincss/login.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript"
	src="../pages/portal/loginjs/jquery_002.js"></script>
<script type="text/javascript" src="../pages/portal/loginjs/jquery.js"></script>

<%
	String imgName, respTime = null;
	imgName = (String) request.getAttribute("imgName");
	respTime = (String) request.getAttribute("respTime");
%>


<script language="javascript">
$(document).ready(function() {

	$.featureList(
		$("#tabs li a"),
		$("#output li"), {
			start_item	:	0
		}
	);

	/*
	
	// Alternative

	
	$('#tabs li a').featureList({
		output			:	'#output li',
		start_item		:	1
	});

	*/

});
</script>

<%
String comanyName = (String) request.getAttribute("comanyName");
String pool_name="abc";
%>
</head>
<body>
<s:form action="GlodyneLogin" method="post" validate="true" theme="simple"
	id="paraFrm" name="form" target="_top">
	<table width="100%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td bgcolor="#FF6600" height="4px"></td>
		</tr>
		<tr>
			<td>
			<table width="980" border="0" align="center" cellpadding="0"
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
											pool_name=(String)session.getAttribute("session_pool");
							%> <input type="hidden" name="compImg" value="<%=cmpName%>" /> <img 
						align="absmiddle"
								src="../pages/Logo/<%=session.getAttribute("session_pool")%>/<%=cmpName%>"
								height="51" /> <%
 } else {
 %> <input type="hidden" name="compImg" value="client_logo.jpg" /> <img align="absmiddle"
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
			<td></td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td ><div  
			style="background-color: #6979ac; float: left; width: 100%; margin: 0;padding: 0;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="20px" class="mainheader"></td>
			</tr>
		</table>
		</div></td>
						</tr>

						<tr>
							<td>
							<table width="980" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td>
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="5px" bgcolor="#FFFFFF"></td>
										</tr>

										<tr>
											<td valign="top" bgcolor="#F6F7F1">
											<div id="content">
											<div id="feature_list">
											<ul id="tabs">
												<li><a class="" href="javascript:;"> <strong>HRMS
												Services</strong><br />
												Leading T.I.M.E </a></li>
												<li><a class="" href="javascript:;"> <strong>Transforming
												Business</strong><br />
												Best in class HRIS & Payroll Services</a></li>
												<li><a class="" href="javascript:;"> <strong>Innovating
												Technology</strong><br />
												HRMS System brings concept to reality</a></li>
												<li><a class="" href="javascript:;"> <strong>Maximizing
												Value</strong><br />
												We do more with less</a></li>
												<li><a class="" href="javascript:;"> <strong>Empowering
												People</strong><br />
												People, Processes & Technology Focused</a></li>
											</ul>

											<ul id="output">
												<li style="display: none;"><img
													src="../pages/portal/loginimages/1.gif" />Focused on
												People, Processes & technology</li>
												<li style="display: none;"><img
													src="../pages/portal/loginimages/2.gif" /></li>
												<li style="display: none;"><img
													src="../pages/portal/loginimages/3.gif" /></li>
												<li style="display: none;"><img
													src="../pages/portal/loginimages/4.gif" /></li>
												<li style="display: none;"><img
													src="../pages/portal/loginimages/5.gif" /></li>
											</ul>
											</div>
											</div>
											</td>
										</tr>

										<tr>
											<td height="5px" bgcolor="#FFFFFF"></td>
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
					<td class="border">
					<table width="980" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>

							<!-- Condition 1 Default Login begin-->

							<td width="44%"><!--  --> <s:if test="imgTxtFlag">
								<table width="99%" border="0" cellpadding="2" cellspacing="1">
									<tr>
										<td colspan="4" class="link"><span class="emptext">Secure
										Access Image and Message</span></td>
									</tr>
									<tr>
										<td width="21%" rowspan="3" class="link"><img
											src="<%=imgName%>" alt="" width="76" height="75" /></td>
										<td width="1%">&nbsp;</td>
										<td colspan="2"><s:textfield cssClass="textfield"
											name="userMsg" size="30" readonly="true" /></td>
									</tr>
									<tr>
										<td width="1%">&nbsp;</td>
										<td width="6%" class="text1"><s:checkbox
											id="checkbox_confirm" name="checkbox_confirm" /></td>
										<td width="72%" class="link">Confirm your Secure Access
										Image and Message to login</td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td colspan="2" valign="top" class="link">Please report
										to support@peoplepower.com if, this is not image and messageas
										specified by you.</td>
									</tr>
								</table>
							</s:if> <s:else>
								<img src="../pages/portal/loginimages/iv.gif" alt="" width="433"
									height="106" />
							</s:else></td>
							<td width="1%" align="center"><img
								src="../pages/portal/loginimages/vline.jpg" alt="" width="1"
								height="100" /></td>
							<td width="30%"><s:if test="%{invalidFlag}">

								<table width="95%" align="center" cellpadding="2"
									cellspacing="1">
									<tr>
										<td colspan="4" class="emptext">Secure Authentication</td>

									</tr>
									<tr>
										<td width="38%" class="link">Secure Access</td>
										<td width="3%" class="star">&nbsp;</td>
										<td width="3%">:</td>
										<td width="56%"><img src="GlodyneLogin_getKeyCode.action"
											style="border: 1px;"
											alt="Enter the characters appearing in this image"
											align="middle" height="40px" width="100px" /></td>
									</tr>
									<tr>
										<td rowspan="2" valign="top" class="link" nowrap>Enter
										Secure Access Code</td>
										<td width="3%" class="star">*</td>
										<td width="3%">:</td>
										<td class="link"><span class="text1"> <input
											name="inCaptchaChars" id="inCaptchaChars" type="text"
											size="10" /> </span></td>
									</tr>
									<tr>
										<td class="star">&nbsp;</td>
										<td>&nbsp;</td>
										<td valign="top" class="text1">&nbsp;</td>
									</tr>
								</table>


							</s:if> <s:else>
								<img src="../pages/portal/loginimages/kop.gif" alt=""
									width="294" height="107" />
							</s:else></td>
							<td width="1%" align="center"><img
								src="../pages/portal/loginimages/vline.jpg" alt="" width="1"
								height="100" /></td>
							<td width="24%" valign="top">
							<table width="99%" border="0" cellpadding="2" cellspacing="1">
								<tr>
									<td colspan="4" class="style1">Sign In</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td width="28%" class="link" nowrap>Login ID</td>
									<td width="1%" class="star" nowrap>*</td>
									<td width="1%">:</td>
									<td><s:if test="settingFlag">
										<s:textfield name="loginName" cssClass="textfield" size="13" />
									</s:if> <s:else>
										<s:textfield name="loginName" cssClass="textfield" size="13"
											readonly="true" />
									</s:else></td>
									<td><s:if test="loginFlag">
									</s:if> <s:else>

										<s:submit action="GlodyneLogin_continueUser" value="Continue"
											onclick="return callContinue();" />

									</s:else></td>

								</tr>
								<s:if test="loginFlag">
								</s:if>
								<s:else>
									<td colspan="5" class="link">Click Continue to key-in your
									password!</td>
								</s:else>
								<tr>
									<s:if test="passFlag">
										<td class="link" nowrap>Password</td>
										<td width="1%" class="star">*</td>
										<td width="1%">:</td>
										<td class="text1"><s:password name="loginPassword"
											onkeypress="return fSubmit(event);" size="13" maxlength="13"
											cssClass="textfield"
											onfocus="javascript:setKeyboardFocus('form','loginPassword');" /></td>
									</s:if>
									<td><s:if test="loginFlag">

										<s:submit name="loginBtn" value="Login"
											onclick="return callSubmit();" />
									</s:if> <s:else></s:else></td>
								</tr>

								<tr>
									<td>&nbsp;</td>
									<td class="star">&nbsp;</td>
									<td>&nbsp;</td>
									<td colspan="2" valign="top"><a href="#" class="text1"
										onclick="javascript:callForgot();">Forgot Login ID /
									Password?</a></td>
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
		 	
			</script><a href="javascript:void(0)" onClick="window.open('../pages/policies/Terms & Conditions.html','','width=800,height=600,scrollbars=yes,resizable=yes,menubar=no,top=0,left=0')" class="link">
							Terms &amp; Conditions </a><strong class="link"> | </strong><a
								href="javascript:void(0)" onClick="callWindowPage()"
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
			</td>
		</tr>
	</table>
	
	
	<% 
String infoStr="";
	try{
	infoStr=(String)request.getAttribute("infoId"); 

if(infoStr==null ||infoStr.equals("null")||infoStr.equals(""))
{
	infoStr=(String)request.getParameter("infoId"); 
 }
	}catch(Exception e)
	{
		
	}
 
%> 

  
<input type="hidden" name="infoId"
		value='<%=infoStr%>' />
	 
	<s:hidden name="clientName" value="%{clientName}" />
	<s:hidden name="name" value="%{name}" />
	<s:hidden name="invalidFlag" value="%{invalidFlag}" />

	<s:hidden name="count" value="%{count}" />
	<s:hidden name="invalidCount" value="%{invalidCount}" id="invalidCount" />
	<s:hidden name="chatrooms" value="1" />
	<s:hidden name="actionMessage" />
	<s:hidden name="settingFlag" />
	<input type="hidden" name="hidCaptchaID" value="<%= session.getId() %>" />

	<s:hidden name="lockAttempt" id="lockAttempt"></s:hidden>
	<s:hidden name="imgTxtFlag" id="imgTxtFlag"></s:hidden>
	<input type="text" name="login" style="visibility: hidden;" />

</s:form>

</body>
<script>



  function callContinue()
{
	try
	{
		var name = document.getElementById('paraFrm_loginName').value;
  		
  		
  			if(name =="") {
  			alert("Please Enter Login ID"); 
  			document.getElementById('paraFrm_loginName').focus(); 			
  			return false;  			
  			}
  		
  		return true;
  	}
  	catch(e)
  	{
  	  alert(e);
  	}	
}


 var entry_field		= "";
var form_name		= "";


function setKeyboardFocus(p_formname, p_fieldname)
{
	 try{
		form_name	= p_formname;
		entry_field = p_fieldname;
		 }
	catch(e)
	 {
	 //alert("Error "+e);
	 }
	 
}
 


function callSubmit() {
 	
 	try
 	{
 		var name = document.getElementById('paraFrm_loginName').value;
  		var pwd = document.getElementById('paraFrm_loginPassword').value; 
  		var imgTxtFlag = document.getElementById('imgTxtFlag').value; 
  	 		if(name =="") 
  	 		{
  			alert("Please Enter Login ID");  
  			document.getElementById('paraFrm_loginName').focus();			
  			return false;  			
  			}
  		if(pwd =="")
  		{
  			alert("Please Enter Password");
  			document.getElementById('paraFrm_loginPassword').focus();
  			return false;
  		}
  		//alert(imgTxtFlag);
  		if(imgTxtFlag=="true")
  		{
  	
  		try{
  			var checkFlag=document.getElementById('checkbox_confirm').checked;
  			//alert(checkFlag);
  		if(checkFlag==false){
  			alert("Please confirm your Secure Access Image");
  			document.getElementById('checkbox_confirm').focus();
  			return false;
  		}
  		}
  		catch(e){
  		alert(e);
  		}

  		}
  		var lname = document.getElementById('paraFrm_loginName').value; 
  		document.getElementById('paraFrm_name').value=lname ;
  		//document.getElementById('invalidCount').value=	
  		//alert(document.getElementById('invalidCount').value);
  		if(document.getElementById('invalidCount').value!=0) 
  		{
  			try{
  			var inCaptchaChars = document.getElementById('inCaptchaChars').value;  
 		  		//alert("inCaptchaChars"+inCaptchaChars);
  			if(inCaptchaChars=="")
  			{
  				alert("Enter the letters as they are shown in the image"); 
  					return false;
  			}
  			}catch(e){
  			}
  			
  			
  			if(document.getElementById('invalidCount').value==4) {
  			alert('You have attempted 3 unsuccessful logins. The browser will be closed.');  		  		
  			window.open('','_par','');
			window.close();
			return false;
  		}
  		
  		}
  		
   document.getElementById('paraFrm').action = 'GlodyneLogin_submitUser.action';
 		 document.getElementById('paraFrm').submit();
  
  		return true;
  		
  	}
  		catch(e)
  		{
  			 //alert(e);
  		}
  }

function callForgot(){
try
{
document.getElementById('paraFrm').action="ForgotPassword_forgotPage.action";
document.getElementById('paraFrm').submit();
}
catch(e)
{
	//alert(e);
}
}


 init();
function init(){

	<%if(pool_name.equals("pool_sprt")){%>
document.getElementById('paraFrm_loginName').value="demo";
	document.getElementById('paraFrm_loginPassword').value="demo";
		<%}%>
	try
	{
	//setKeyboardFocus("form","loginPassword");
		if(document.getElementById('paraFrm_loginName').value=="")
  			{
  			document.getElementById('paraFrm_loginName').focus();
  			}
  			else
  			{
  			//document.getElementById('paraFrm_loginPassword').value="demo";
  			}
  			
  				if(document.getElementById('lockAttempt').value==1) {
  					window.close();
											 		}
	
	//document.form.loginPassword.focus();
	}
	catch(e)
	{
		//alert(e);
	}
}


</script>
<script type="text/javascript">
<!--
var BrowserDetect = {
	init: function () {
		this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
		this.version = this.searchVersion(navigator.userAgent)
			|| this.searchVersion(navigator.appVersion)
			|| "an unknown version";
		this.OS = this.searchString(this.dataOS) || "an unknown OS";
	},
	searchString: function (data) {
		for (var i=0;i<data.length;i++)	{
			var dataString = data[i].string;
			var dataProp = data[i].prop;
			this.versionSearchString = data[i].versionSearch || data[i].identity;
			if (dataString) {
				if (dataString.indexOf(data[i].subString) != -1)
					return data[i].identity;
			}
			else if (dataProp)
				return data[i].identity;
		}
	},
	searchVersion: function (dataString) {
		var index = dataString.indexOf(this.versionSearchString);
		if (index == -1) return;
		return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
	},
	dataBrowser: [
		{
			string: navigator.userAgent,
			subString: "Chrome",
			identity: "Chrome"
		},
		{ 	string: navigator.userAgent,
			subString: "OmniWeb",
			versionSearch: "OmniWeb/",
			identity: "OmniWeb"
		},
		{
			string: navigator.vendor,
			subString: "Apple",
			identity: "Safari",
			versionSearch: "Version"
		},
		{
			prop: window.opera,
			identity: "Opera",
			versionSearch: "Version"
		},
		{
			string: navigator.vendor,
			subString: "iCab",
			identity: "iCab"
		},
		{
			string: navigator.vendor,
			subString: "KDE",
			identity: "Konqueror"
		},
		{
			string: navigator.userAgent,
			subString: "Firefox",
			identity: "Firefox"
		},
		{
			string: navigator.vendor,
			subString: "Camino",
			identity: "Camino"
		},
		{		// for newer Netscapes (6+)
			string: navigator.userAgent,
			subString: "Netscape",
			identity: "Netscape"
		},
		{
			string: navigator.userAgent,
			subString: "MSIE",
			identity: "Explorer",
			versionSearch: "MSIE"
		},
		{
			string: navigator.userAgent,
			subString: "Gecko",
			identity: "Mozilla",
			versionSearch: "rv"
		},
		{ 		// for older Netscapes (4-)
			string: navigator.userAgent,
			subString: "Mozilla",
			identity: "Netscape",
			versionSearch: "Mozilla"
		}
	],
	dataOS : [
		{
			string: navigator.platform,
			subString: "Win",
			identity: "Windows"
		},
		{
			string: navigator.platform,
			subString: "Mac",
			identity: "Mac"
		},
		{
			   string: navigator.userAgent,
			   subString: "iPhone",
			   identity: "iPhone/iPod"
	    },
		{
			string: navigator.platform,
			subString: "Linux",
			identity: "Linux"
		}
	]

};
BrowserDetect.init();
// -->
</script>
<body leftmargin="0" topmargin="0" >
<script type="text/javascript">
var browserDetect='true';
if(BrowserDetect.browser=='Explorer' && Number(BrowserDetect.version) < 7){
browserDetect='false';
}
if(BrowserDetect.browser=='Firefox' && Number(BrowserDetect.version) < 4) {
browserDetect='false';
}
if(BrowserDetect.browser=='Chrome' && Number(BrowserDetect.version) < 16) {
browserDetect='false';
}
if(BrowserDetect.browser=='Safari' && Number(BrowserDetect.version) < 5) {
browserDetect='false';	
}
if(browserDetect=='false'){
document.write("<table><tr><td>&nbsp;&nbsp;&nbsp; <img src='../pages/mypage/images/icons/systemalert.png'/>	<b>System Alert</b>:<font size='2'> Your current version "+BrowserDetect.browser+"-"+ BrowserDetect.version+" is not compatible or not supported !</font>");
document.write('<br>&nbsp;&nbsp;&nbsp;<font size="2">For best performance, it is recommended to upgrade latest version.</font>');
if(BrowserDetect.browser=='Explorer')
{
document.write("&nbsp;&nbsp;&nbsp;<a  class='contlink' href='http://windows.microsoft.com/en-IN/internet-explorer/products/ie/home'>Internet Explorer</a>");
}
if(BrowserDetect.browser=='Firefox')
{
document.write("&nbsp;&nbsp;&nbsp;<a href='http://www.mozilla.org/en-US/products/download.html'>FireFox Mozilla</a>");
}
if(BrowserDetect.browser=='Chrome')
{
document.write("&nbsp;&nbsp;&nbsp;<a href='http://www.google.com/chrome/'>Google Chrome</a>");
}
if(BrowserDetect.browser=='Safari')
{
document.write("&nbsp;&nbsp;&nbsp;<a href='http://www.apple.com/safari/download/'>Apple Safari</a>");
}
document.write('&nbsp;&nbsp;&nbsp;<font size="2">to upgrade.</font>');
 
document.write('</td></tr></table>');
}
</script> 
<table id="loginLayout" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111">
<tr>
<td >
<s:hidden name="actionMessage"/>
<tiles:insertAttribute name="header"/>
</td></tr>
<tr height="100%" WIDTH="100%">
<td valign="top">
<tiles:insertAttribute name="body"/>
</td>
</tr>
<tr><td >
<tiles:insertAttribute name="footer"/>
</td></tr>
</table>
<script>
function callMessage() {
	var message = document.getElementById("actionMessage").value;
	if(message !="" ){
		alert (message);
		}
}
callMessage();
</script>

</html>
 