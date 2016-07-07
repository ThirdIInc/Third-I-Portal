
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String comanyName = "";
	try {
		comanyName = (String) request.getAttribute("comanyName");
	} catch (Exception e) {

	}
%>

<s:form action="ForgotPassword" id="paraFrm" theme="simple" name="form">
	<s:hidden name="infoId" value="%{loginBean.infoId}" />

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
			<td colspan="2" width="100%"><img
				src="../pages/portal/images/line.gif" width="100%" height="7" /></td>
		</tr>
		<tr align="left">
			<td height="30" colspan="2" align="left">
			<h1 class="headerDashlet">Forgot your password?</h1>
			</td>
		</tr>
		<tr align="left">
			<td colspan="2" class="contlink"><strong> To recover
			your password, type the full email address or Username or Employee ID
			and you will receive an automated password recovery email to your
			registered email id. </strong></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr align="left">
			<td class="contlink"><strong class="headerDashlet">User
			Name</strong></td>
			<td><s:textfield name="userNamefg" id="userNamefg"
				onkeyup="return callJfun();"></s:textfield></td>
		</tr>
		<tr align="left">
			<td>&nbsp;</td>
			<td class="orange">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;or</td>
		</tr>
		<tr align="left">
			<td class="contlink"><strong class="headerDashlet">Email
			Id</strong></td>
			<td><s:textfield name="emailIdfg" id="emailIdfg"
				onkeyup="return callJfun1();"></s:textfield></td>
		</tr>
		<tr align="left">
			<td>&nbsp;</td>
			<td class="orange">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;or</td>
		</tr>
		<tr align="left">
			<td class="contlink"><strong class="headerDashlet">Employee
			Id</strong></td>
			<td><s:textfield name="empIdfg" id="empIdfg"
				onkeyup="return callJfun2();"></s:textfield></td>
		</tr>
		<tr align="left">
			<td>&nbsp;</td>
			<td><label>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br>
			<s:submit theme="simple" value="Continue"
				onclick="return submitFun();" action="ForgotPassword_submitForgot" /></td>
		</tr>
		<tr align="left">
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" width="100%"><img
				src="../pages/portal/images/line.gif" width="100%" height="7" /></td>
		</tr>
		<tr>
			<td width="8%">&nbsp;</td>
			<td width="92%">
			<div align="right" class="contlink">2010 - 2012 Glodyne
			Technoserve Limited. All Rights Reserved.<br />
			Best Viewed in resolution above 1024 x 768.Please disable popup
			blockers.</div>
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

<script>



function callJfun()

{

document.getElementById('emailIdfg').value="";
document.getElementById('empIdfg').value="";
return true;

}

function callJfun1()
{

document.getElementById('userNamefg').value="";
document.getElementById('empIdfg').value="";
return true;
}

function callJfun2()
{

document.getElementById('emailIdfg').value="";
document.getElementById('userNamefg').value="";
return true;
}

function locate()
{
document.getElementById('paraFrm').action="Login_input.action";
document.getElementById('paraFrm').submit();

}
function submitFun()
{
try
{
var uName=document.getElementById('userNamefg').value;
var eMail=document.getElementById('emailIdfg').value;
var empId=document.getElementById('empIdfg').value;

 
   if(uName=="" && eMail=="" && empId=="")
   {
   alert("Please enter any of above field");
   return false;
   }
  
  }
  catch(e)
  {
  	//alert(e);
  }
   return true;
  
}



</script>