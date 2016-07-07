<%@ taglib prefix="s" uri="/struts-tags"%>

 
<s:form action="ForgotPassword" id="paraFrm" theme="simple" name="form">

<%
	String comanyName = "";
	try {
		comanyName = (String) request.getAttribute("comanyName");
	} catch (Exception e) {

	}
%>

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
			<td colspan="2">
			<table width="547">
				<tr>
					<td class="txt" colspan="3" align="left"><strong
						 >Secure Authentication Question </strong></td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
					<s:hidden name="infoId" value="%{loginBean.infoId}"></s:hidden>
				</tr>
				<tr>
					<td colspan="1" width="13%">Question<font color="red">*</font>
					</td>
					<td colspan="1" width="46%"><s:property value="forgotPassQue" /><s:hidden
						name="forgotPassQue" /><s:hidden name="forgotPassQueCode" /></td>
					<td width="41%"><s:textfield name="forgotPassAns" /><s:hidden
						name="LoginCode" /><s:hidden name="answercnt" /></td>
				</tr>
				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1" width="46%" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

					<s:submit theme="simple" value="Submit"
						onclick="return submitFun();" action="ForgotPassword_submitAnswer" />


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
			<td width="92%">&nbsp;</td>
		</tr>
	</table>
</s:form>

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

