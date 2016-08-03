<%@ taglib prefix="s" uri="/struts-tags"%>
<body>
 
	<script type="text/javascript"
	src="../pages/common/include/javascript/styleswitch.js"></script>
	
	
	<%
	String comanyName = "";
	try {
		comanyName = (String) request.getAttribute("comanyName");
	} catch (Exception e) {

	}
%>
<s:form action="ForgotPwd" id="paraFrm" theme="simple" name="form">

<div style="width: 100%">
	<div style="float: left; width: 40%" align="left"><img
			src="../pages/common/css/default/images/logo.jpg"  /></div>
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
			%> <input type="hidden" name="compImg" value="<%=cmpName%>" /><%
 } else {
 %> <input type="hidden" name="compImg" value="client_logo.jpg" /> <img
				src="../pages/common/css/default/images/logo.jpg"
				height="51" /> <%
 }
 %>
			</td>
		</tr>
	</table>
	</div>
	</div>
	

	<table width="100%" cellspacing="2" cellpadding="1" align="left"
		class="formbg">
		<tr>
			<td colspan="2" width="50%" align="center">
			<table>
				<tr>
					<td width="50%" class="txt" colspan="3"><strong
						class="formhead"><img align="absmiddle"					src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;<strong class="formhead">Password Recovery</strong></td>
				</tr>



				<tr>
					<td colspan="2">&nbsp;</td>
					<s:hidden name="infoId" value="%{loginBean.infoId}"></s:hidden>
				</tr>
			
				<tr>
					<td colspan="1" width="20%">User Name</td>
					<td colspan="1" width="30%"><s:textfield name="userNamefg"
						id="userNamefg" onkeyup="return callJfun();"></s:textfield></td>
				</tr>
				<tr>
					<td colspan="2" align="center">or</td>
				</tr>

				<tr>
					<td colspan="1" width="20%">Email Id</td>
					<td colspan="1" width="30%"><s:textfield name="emailIdfg"
						id="emailIdfg" onkeyup="return callJfun1();"></s:textfield></td>
				</tr>

				<tr>
					<td colspan="2" align="center">or</td>
				</tr>
				<tr>
					<td colspan="1" width="20%">Employee Id</td>
					<td colspan="1" width="30%"><s:textfield name="empIdfg"
						id="empIdfg" onkeyup="return callJfun2();"></s:textfield></td>
				</tr>
			
				<tr>
					<!-- <td colspan="1">&nbsp;</td> -->
					<td colspan="1" width="25%" align="center"><s:submit cssClass="token" theme="simple" value="Continue"
						onclick="return submitFun();" action="ForgotPwd_submitForgot" /> 
					</td>
					<td colspan="1" width="30%" align="center"><s:submit cssClass="token" theme="simple" value="Back" onclick="return BackFun();"/> 
					</td>
				</tr>
			</table>
			</td>
		</tr>

	</table>


</s:form>
</body>
<script>
</script>


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

function BackFun()
{
document.getElementById('paraFrm').action="Login_input.action";
document.getElementById('paraFrm').submit();

}



</script>
