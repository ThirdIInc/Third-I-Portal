<html>
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
<s:form action="GlodyneLogin" method="post" validate="true" theme="simple" id="paraFrm"
	name="form" target="_top">
<br>
<br>
<br>
<br>

<table align="center" cellpadding="0" cellspacing="0" border="0" class="formbg" >
<tr>
<td height="100" style="font-family:arial,sans-serif;font-weight: normal;font-size: 12px;text-align: center;" width="60%">
Your previous session was terminated incorrectly or is currently active<br>
<br>
<br>
<a href="javascript:logout();">Please Click here to Relogin</a>
</td>
</tr>
</table>
</s:form>
<script type="text/javascript">
function logout(){

var poolName='<%=(String)session.getAttribute("poolId")%>';
<%session.invalidate();%>
window.location.href='http://'+'<%=request.getServerName()%>'+':<%=request.getServerPort()%><%=request.getContextPath()%>'+'/portal/GlodyneLogin_input.action?infoId='+poolName;
}
</script>

</body>
</html>


