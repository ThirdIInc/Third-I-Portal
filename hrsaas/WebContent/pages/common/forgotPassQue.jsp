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
			%> <input type="hidden" name="compImg" value="<%=cmpName%>" /> <img
				src="../pages/Logo/<%=session.getAttribute("session_pool")%>/<%=cmpName%>"
				height="51" /> <%
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
					<td width="100%" class="txt" colspan="3" align="left"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">
					Secure Authentication Question </strong></td>
				</tr>



				<tr>
					<td colspan="2">&nbsp;</td>
					<s:hidden name="infoId" value="%{loginBean.infoId}"></s:hidden>
				</tr>
				<tr>
					<td colspan="1" width="10%">Question<font color="red">*</font>
					</td>
					<td colspan="1" width="30%"><s:property value="forgotPassQue" /><s:hidden
						name="forgotPassQue" /><s:hidden name="forgotPassQueCode" /></td>
					<td><s:textfield name="forgotPassAns" /><s:hidden name="LoginCode" /><s:hidden name="answercnt" />  </td>
				</tr>
				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1" width="30%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:submit cssClass="token" theme="simple" value="Submit"
						onclick="return submitFun();" action="ForgotPwd_submitAnswer" />
					</td>
				</tr>

			</table>
			</td>
		</tr>

	</table>


</s:form>
</body>

<script>

function submitFun()
{

var answer=document.getElementById('paraFrm_forgotPassAns').value;

if(answer=="")
{
		alert('Please give the answer of question');
		
		return false;
}
	return true;
}
</script>
