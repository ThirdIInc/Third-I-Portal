
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="../common/commonValidations.jsp"%>
<link rel="stylesheet" type="text/css" title="default-theme"
	href="<%=request.getContextPath()%>/pages/common/css/commonCSS.jsp" />

 

<%
	String comanyName = "";
	String res ="";
	try {
		comanyName = (String) request.getAttribute("comanyName");
		res = (String) request.getAttribute("result");
	} catch (Exception e) {

	}
%>


<s:form id="paraFrm" theme="simple" action="" name="form" method="post">
	<s:hidden name="result" id="result" value='<%=res%>' />
	<s:hidden name="loginBean.infoId"></s:hidden>
	
	<div style="width: 100%">
	<div style="float: left; width: 40%" align="left"><img
		src="../pages/common/css/default/images/myglodyne.jpg" width="193"
		height="51" /></div>
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
				src="../pages/common/css/default/images/myglodyne.jpg"
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
			<td colspan="2" width="100%" ><img
				src="../pages/portal/images/line.gif" width="100%" height="7" /></td>
		</tr>
		
		<tr>
			<td colspan="2" class="blacklink">
			<table width="80%" border="0" cellpadding="4" cellspacing="4">
				<tr>
					<td colspan="2">
					<div id="mail">
					<table align="center">
						<tr>
							&nbsp;
						</tr>
						<tr>
							<td colspan="2" align="center">Your password has been sent on your registered email id
							.Please check your mail Box.</td>
						</tr>
						<tr>
							<td colspan="1">&nbsp;</td>
							<td colspan="1" align="center">
							<%
								String url = "";

								try {

									url = "http://" + request.getServerName() + ":"
									+ request.getServerPort() + request.getContextPath()
									+ "/portal/GlodyneLogin_input.action";

								} catch (Exception e) {
									response.sendError(
									HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
									"There was a problem");
								}
							%> <input type="submit" value="Login" theme="simple"
								  onclick="javascript:login('<%=url %>');">
							&nbsp;&nbsp;&nbsp;&nbsp; <!--<input type="submit" id="paraFrm_0" value="   close" class="token" onclick="closeBrowser();"/>
</td>--></td>
						</tr>
					</table>
					</div>
					</td>
					<td colspan="2">
					<div id="inactive">
					<table align="center">
						<tr>
							<td colspan="2">You are not eligible to access the service.</td>
						</tr>
						<tr>
							<td colspan="1">&nbsp;</td>
							<td colspan="1" align="right"><!--<input type="submit" id="paraFrm_1" value="   close" class="token" onclick="closeBrowser();"/>
</td>--></td>
						</tr>
					</table>
					</div>
					</td>
					<td colspan="2">
					<div id="notPermanentAdd">
					<table align="center">
						<tr>
							<td colspan="2" align="center">System can not send your
							account information as your email is not configured.Please
							contact System administrator.</td>
						</tr>
						<tr>
							<td colspan="1">&nbsp;</td>
							<td align="right"><!--<input type="submit" id="paraFrm_2" value="   close" class="token" onclick="closeBrowser();"/>
</td>--></td>
						</tr>
					</table>
					</div>
					</td>
					<td colspan="2">
					<div id="noMailBox">
					<table align="center">
						<tr>
							<td colspan="2" align="center">Mail Box has not been
							configured.</td>
						</tr>
						<tr>
							<td colspan="1">&nbsp;</td>
							<td align="right"><!--<input type="submit" id="paraFrm_3" value="   close" class="token" onclick="closeBrowser();"/>
</td>--></td>
						</tr>
					</table>
					</div>
					</td>
					<td colspan="2">
					<div id="wrongans">
					<table align="center">
						<tr>
							<td colspan="2" align="center">Please Contact Your System
							Administrator for Password Recovery</td>
						</tr>
						<tr>
							<td colspan="1">&nbsp;</td>
							<td align="right"><!--<input type="submit" id="paraFrm_4" value="   close" class="token" onclick="closeBrowser();"/>
</td>--></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="2" width="100%" ><img
				src="../pages/portal/images/line.gif" width="100%" height="7" /></td>
		</tr>
		<tr>
			<td width="8%">&nbsp;</td>
			<td width="92%">
			<div align="right" class="contlink">2010 - 2012 Glodyne
			Technoserve Limited. All Rights Reserved.<br />
			Best Viewed in resolution above 1024 x 768.Please disable popup blockers.
			</div>
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
	</table>
</s:form>

<script type="text/javascript">
<!--

//-->
 checkResult();
function closeBrowser()
{
window.open('','_parent','');

window.close();

}


function login(url)
{

var poolName=document.getElementById('paraFrm_loginBean_infoId').value;

document.getElementById('paraFrm').action=url+'?infoId='+poolName;
document.getElementById('paraFrm').target="_self";
document.getElementById('paraFrm').submit();
}

function checkResult()
 
{
   var res= document.getElementById("result").value;

   if(res=="mail")
   {
   document.getElementById("inactive").style.display = 'none';
   document.getElementById("notPermanentAdd").style.display = 'none';
   document.getElementById("noMailBox").style.display = 'none';
   document.getElementById("mail").style.display = '';
  document.getElementById("wrongans").style.display = 'none';
  
   }
   if(res=="inactive" ||res=="notInSevvice")
   {
    document.getElementById("mail").style.display = 'none';
    document.getElementById("notPermanentAdd").style.display = 'none';
    document.getElementById("inactive").style.display = '';
    document.getElementById("noMailBox").style.display = 'none';
  document.getElementById("wrongans").style.display = 'none';
   }
   
   
    if(res=="notPermanentAdd")
    {
    document.getElementById("notPermanentAdd").style.display = '';
    document.getElementById("inactive").style.display = 'none';
    document.getElementById("mail").style.display = 'none';
    document.getElementById("noMailBox").style.display = 'none';
document.getElementById("wrongans").style.display = 'none';
    }
    if(res=="noMailBox")
    {
    document.getElementById("noMailBox").style.display = '';
    document.getElementById("notPermanentAdd").style.display = 'none';
    document.getElementById("inactive").style.display = 'none';
    document.getElementById("mail").style.display = 'none';
document.getElementById("wrongans").style.display = 'none';
    }
    
     if(res=="wrongans")
    {
    document.getElementById("noMailBox").style.display = 'none';
    document.getElementById("notPermanentAdd").style.display = 'none';
    document.getElementById("inactive").style.display = 'none';
    document.getElementById("mail").style.display = 'none';
document.getElementById("wrongans").style.display = '';
    }
    
    }

</script>