 
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>PeoplePower</title>
				
				<jsp:include page="/pages/CommonCssJS.jsp" ></jsp:include>
				<link type="text/css" href="../pages/portal/logincss/login.css" rel="stylesheet"  />
				<script type="text/javascript" src="../pages/portal/loginjs/jquery_002.js"></script>
				<script type="text/javascript" src="../pages/portal/loginjs/jquery.js"></script>
				<script type="text/javascript">
				
				
				</script>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
				
				

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
<div class="wrapper">
	<form class="login" action="../common/Login_submitUser.action" method="post" validate="true" theme="simple" id="paraFrm" name="form" target="_top">
	<s:hidden id="screenWidth" name="screenWidth"></s:hidden>

		<div class="title"><%=comanyName%></div>
		<div class="form-title" >Log In</div>
		<div class = "col" style = "margin-bottom: 10px;"><s:if test="settingFlag">
		<!--<div class = "login-label" ><fieldset><section>Username <span class = "un"><i class="fa fa-user"></i></span> :</section></fieldset></div>-->
		<input type="text" name="loginName"   size="20" placeholder="Username" autofocus/>
		</s:if> <s:else>
		<input type="text" name="loginName" cssClass="textfield" size="13"  readonly="true"  />
		</s:else></div>
				
		<div class = "col"><s:if test="loginFlag">
		</s:if> <s:else>
		<s:submit action="Login_continueUser" value="Continue" onclick="return callContinue();" />
		</s:else></div>
						
		<s:if test="passFlag">
		<!--  <div class = "login-label"><fieldset><section>Password <span class = "un"><i class="fa fa-lock"></i></span> :</section></fieldset></div>-->
		<input type="password" name="loginPassword" onkeypress="return fSubmit(event);" size="20" onfocus="javascript:setKeyboardFocus('form','loginPassword');" 
			placeholder="Password" autofocus/>
		</s:if>
				
		<a href="#" class="forget-link" onclick="javascript:callForgot();">Forgot Username/Password?</a>

		<s:if test="loginFlag">
		<input type="submit" name="loginBtn" value="Login" class="btn" class="login-btn"
						onclick="return callSubmit();" id="loginBtn"/>
		</s:if> 
		<s:else></s:else>
	
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

	<input type="hidden" name="infoId"	value='<%=infoStr%>' />
	 
	<s:hidden name="clientName" value="%{clientName}" />
	<s:hidden name="name" value="%{name}" />
	<s:hidden name="invalidFlag" value="%{invalidFlag}" />

	<s:hidden name="count" value="%{count}" />
	<s:hidden name="invalidCount" value="%{invalidCount}" id="invalidCount" />
	<s:hidden name="chatrooms" value="1" />
	<s:hidden name="actionMessage" id="actionMessage"/>
	<s:hidden name="settingFlag" />
	<input type="hidden" name="hidCaptchaID" value="<%= session.getId() %>" />

	<s:hidden name="lockAttempt" id="lockAttempt"></s:hidden>
	<s:hidden name="imgTxtFlag" id="imgTxtFlag"></s:hidden>
	<input type="text" name="login" style="visibility: hidden;" />

	</form>
</div>
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
  			alert("Please confirm your Secure Access Image and Message");
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
  		
  		document.getElementById('loginBtn').disabled=true;
   		document.getElementById('paraFrm').action = 'Login_submitUser.action';
 		document.getElementById('paraFrm').submit();
  
  		return true;
  		
  	}
  		catch(e)
  		{
  			 //alert(e);
  		}
}
function callForgot(){
	try{
		document.getElementById('paraFrm').action="ForgotPwd_forgotPage.action";
		document.getElementById('paraFrm').submit();
	}
	catch(e){
	//alert(e);
	}
}
init();
function init(){
	try{
	//setKeyboardFocus("form","loginPassword");
		if(document.getElementById('paraFrm_loginName').value=="")	{
  			document.getElementById('paraFrm_loginName').focus();
  		}else {
  			//document.getElementById('paraFrm_loginPassword').value="demo";
  		}
  		if(document.getElementById('lockAttempt').value==1) {
  			window.close();
 		}
			//document.form.loginPassword.focus();
	}
	catch(e){
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

setScreenWidth();
function setScreenWidth(){
try{
var VScreenWidth=$(window).width();


document.getElementById('screenWidth').value=VScreenWidth;

}
catch(e){
alert(e);
}

}
</script>

</html>
 