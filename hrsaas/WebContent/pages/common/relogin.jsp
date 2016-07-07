<%@ taglib prefix="s" uri="/struts-tags" %>
<HTML>
<HEAD>
<TITLE>Untitled-1</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">

<link rel="stylesheet" type="text/css" title="default-theme"
	href="../pages/common/css/default/default.css" />
</HEAD>
<BODY BGCOLOR=#FFFFFF LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0>
<p>
  <!-- ImageReady Slices (Untitled-1) -->
</p>
<s:form action="ReLogin" method="post" validate="true" theme="simple"
	name="loginFrm" target="_top">
<TABLE WIDTH=276 BORDER=0 align="center" CELLPADDING=0 CELLSPACING=0>
	<TR>
		<TD width="10" class="relogin"></TD>
		<TD width="262" class="relogin1"></TD>
		<TD width="10" class="relogin2"></TD>
	</TR>
	<% String pool =(String)request.getParameter("infoId");
		String user =(String)request.getParameter("loginName");%>
	<TR>
		<TD class="relogin3">&nbsp;</TD>
		<TD class="reloginbg"><table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
          <tr>
            <td height="25" colspan="2"><div align="center"><span class="reloginstyle">Your session is about to expire .. Please Relogin </span></div></td>
          </tr>
          <tr>
            <td height="25"><span class="style1">Username</span></td>
            <td><input name="loginName" type="text" class="box" readonly value="<%=user%>"  size="20" /></td>
          </tr>
          <tr>
            <td height="22" class="style1">Password</td>
            <td height="22" class="style1"><input name="loginPassword" type="password" class="box" value="" size="20" />
            </td>
          </tr>
          <tr>
            <td height="25" colspan="2"><div align="center">
            <s:submit cssClass="box"
								action="ReLogin_submit" value="Login" onclick="return callSubmit();"
								 targets="_top" />
            </div></td>
          </tr>
        </table></TD>
		<TD class="relogin4">
	  </TD>
	</TR>
	<TR>
		<TD class="relogin5">&nbsp;</TD>
		<TD  class="relogin6">&nbsp;</TD>
		<TD  class="relogin7">&nbsp;</TD>
	</TR>
</TABLE>
<input type="hidden" name="infoId" value ="<%=pool%>" />
<s:hidden name="name" value="%{name}" />
</s:form>
<!-- End ImageReady Slices -->
</BODY>
</HTML>
<script>
  	function callSubmit(){
  	//alert('in call');
  		var pwd = document.getElementById('loginPassword').value; 
  			var name = document.getElementById('loginName').value; 
  			if(name ==""){
  			alert("Enter UsenName");  			
  			return false;  			
  		}
  		if(pwd ==""){
  			alert("Enter Password");
  			//document.getElementById('div1').style.display='none';
  			return false;
  			
  		}
  		var lname = document.getElementById('loginName').value; 
  		document.getElementById('name').value=lname ;
  		return true;
  	}
  </script>