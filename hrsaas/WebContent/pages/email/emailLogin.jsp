<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<s:form action="http://203.94.215.195:2400/webmail/imp/redirect.php"
	validate="true" id="paraFrm" validate="true" theme="simple">
	<!--  <form action="http://203.94.215.195:2400/webmail/imp/redirect.php" >-->




	<table class="formbg" cellpadding="0" cellspacing="0" border="0"
		width="100%">
		<tr align="center">
			<td colspan="1" align="right">Server :</td>
			<td colspan="3" align="left"><s:textfield 
				name="server" value="%{server}" /><s:textfield name="port"
				 size="5" onkeypress="return numbersonly(this);"/> <s:select name="protocol" onchange="updatePort();"
				 list="#{' ':'Select','pop3':'POP3','pop3/novalidate-cert':'POP3 (self-signed certificate)','pop3/notls':'POP3, no TLS','pop3/ssl':'POP3 over SSL','pop3/ssl/novalidate-cert':'POP3 over SSL (self-signed certificate)','imap':'IMAP','imap/novalidate-cert':'IMAP (self-signed certificate)','imap/notls':'IMAP, no TLS','imap/ssl':'IMAP over SSL','imap/ssl/novalidate-cert':'IMAP over SSL (self-signed certificate)'}" /></td>
		</tr>
		<tr align="center">
			<td colspan="1" align="right">Username :</td>
			<td colspan="3" align="left"><s:textfield name="imapuser"
				 /></td>
		</tr>
		<tr align="center">
			<td colspan="1" align="right">Password :</td>
			<td colspan="3" align="left"><s:password name="pass" /></td>
		</tr>
		<tr align="center">
			<td colspan="1" align="right">Outbound Server:</td>
			<td colspan="3" align="left"><s:textfield name="smtphost"
				 /><s:textfield name="smtpport" onkeypress="return numbersonly(this);" size="5"/>SMTP</td>
		</tr>
	 <s:hidden name="new_lang" value="en_US" />
		 <s:hidden name="select_view" value="dimp" />
		<tr>
			<td align="right">&nbsp;</td>
			<td align="left">
				<input type="button" value="Login" class="token" onclick="callLogin()"/>
				
				</td>
		</tr>
	</table>
<table height="600">
	<tr><td></td></tr></table>
</s:form>
<script>
		function callLogin(){			
				//192.168.25.12
				alert('--');
				document.getElementById('paraFrm').target="main";
				document.getElementById('paraFrm').action="http://203.94.215.195:2400/webmail/imp/redirect.php";
				document.getElementById('paraFrm').submit();	
		
		}
		
		function updatePort(){
		var serv=document.getElementById('paraFrm_protocol').value;
		if(serv=='pop3' ||serv=='pop3/novalidate-cert' ||serv=='pop3/notls'){
		document.getElementById('paraFrm_port').value='110';		
		}
		
		else if(serv=='pop3/ssl' ||serv=='pop3/ssl/novalidate-cert' ){
		document.getElementById('paraFrm_port').value='995';	
		}
		
		else if(serv=='imap' ||serv=='imap/novalidate-cert' ||serv=='imap/notls'){
		document.getElementById('paraFrm_port').value='143';	
		}
		else if(serv=='imap/ssl' ||serv=='imap/ssl/novalidate-cert' ){
		document.getElementById('paraFrm_port').value='993';	
		}
		
		}
		
		function numbersonly(myfield)
{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}
		
		
</script>
</body>
</html>