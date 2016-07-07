<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link href="../pages/common/css/default/default.css"  rel="stylesheet" type="text/css" />
</head>

<body topmargin="40%">
<%String infoId=(String)request.getAttribute("poolName"); %>
<table width="100%" height="100%" border="0"  cellpadding="8" cellspacing="8"  >
<tr>
<td width="100%" valign="middle">
<table width="36%" border="0" align="center" cellpadding="4" cellspacing="4"  class="formbg">
  <tr valign="middle">
    <td width="31%" valign="middle"><div align="center"><img src="../pages/images/Logout.gif" width="48" height="48" /></div></td>
    <td width="69%" valign="middle"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formhead">
     
      <tr>
        <td height="25"><div align="center" class="bluetxt">
          <div align="left"><strong>Thank you for using PeoplePower </strong></div>
        </div></td>
      </tr>
    </table></td>
  </tr>
   <tr>
    <td colspan="2" valign="middle" class="cellbg"><div align="right"><a href="<%=request.getContextPath() %>/recruit/PartnerLogin_input.action?infoId=<%=infoId%>">Login</a>&nbsp;&nbsp; <a href="javascript:closeWindow();">Close Window</a></div></td>
  </tr>
</table>
</td>
</tr>
</table>
<script language="javascript" type="text/javascript">

function closeWindow() {
	if(window.confirm('Do you want to close this window ?')) {
		window.open('','_parent','');
		window.close();
	}
}
</script> 
</body>
</html>
