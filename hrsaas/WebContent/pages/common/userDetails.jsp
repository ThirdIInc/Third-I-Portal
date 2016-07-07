
<%@ taglib prefix="s" uri="/struts-tags"%>

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
		src="../pages/common/css/default/images/logo.jpg" /></div>
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
				src="../pages/common/css/default/images/logo.jpg" height="51" /> <%
 }
 %>
			</td>
		</tr>
	</table>
	</div>
	</div>







	<table width="100%" border="0" align="left" cellpadding="0"
		cellspacing="0">
		<tr>
			<td>

			<table width="100%" border="0" align="left" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3" width="100%"><img
						src="../pages/portal/images/line.gif" width="100%" height="7" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>

			<table width="45%" border="0" align="center" cellpadding="0"
				cellspacing="0">


				<tr>
					<td colspan="2" class="blacklink">
					<table width="100%">
						<tr>
							<td width="50%" class="formbg" colspan="3">&nbsp;<strong
								class="formhead">User Details</strong></td>
						</tr>
						<tr>
							<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<tr>
									<td colspan="3" class="text_head"><strong> These
									are the users associated with this employee Id/email id</strong></td>
								</tr>
								<tr>
									<td colspan="3" class="text_head"><strong
										class="blacklink">Please select one user to retrieve
									login credential</strong></td>
								</tr>
								<!--<tr>
							<td valign="top" colspan="1" class="formth">&nbsp;</td>
							<td valign="top" colspan="2" class="formth">&nbsp;</td>
						</tr>
						<tr>
							<td valign="top" colspan="1" class="formth">&nbsp;</td>
							<td valign="top" colspan="2" class="formth">&nbsp;</td>
						</tr>
						-->
								<tr>
									<td width="20%" valign="top" colspan="1" class="formth">Sr
									No</td>
									<td width="80%" valign="top" colspan="2" class="formth">User
									Name</td>
								</tr>

								<%
								int y = 1;
								%>
								<%!int z = 0;%>
								<s:iterator value="list">
									<tr class="sortableTD">
										<td class="sortableTD" colspan="1" width="20%" align="center"><%=y%></td>
										<td class="sortableTD" colspan="1" width="80%"><s:property
											value="userNames" /></td>
										<td class="sortableTD" colspan="1" width="100%"><input
											class="POLLINPUT" type="radio" name="forgotUserName"
											id="forgotUserName" value="<s:property value="userNames" />"
											onclick="setVal(id);" /></td>

									</tr>
									<%
									y++;
									%>
								</s:iterator>
								<%
								z = y;
								%>


							</table>
							</td>
						</tr>
						<tr>
							<td colspan="1">&nbsp;</td>
							<td colspan="1" align="center"><s:hidden name="infoId" /><s:submit
								cssClass="token" theme="simple" value="Continue"
								onclick="return calladd();"
								action="ForgotPwd_getQuestionForEmailId" /></td>
							<td colspan="1">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3" width="100%"><img
						src="../pages/portal/images/line.gif" width="100%" height="7" /></td>
				</tr>
				<tr>
					<td width="8%">&nbsp;</td>
					<td width="92%">
					<div align="right" class="blacklink">2010 - 2012 Glodyne
					Technoserve Limited. All Rights Reserved.<br />
					Best Viewed in resolution above 1024 x 768. Please disable popup
					blockers.<br />
					</div>
					</td>
				</tr>
				<tr>
					<td width="8%">&nbsp;</td>
					<td width="92%">&nbsp;</td>
				</tr>
			</table>

			</td>
		</tr>
	</table>
	<s:hidden name="hiddenRadio" />
</s:form>

<script>


function calladd()
{
if(document.getElementById('paraFrm_hiddenRadio').value=="")
 {
 alert("Please select at least one user name");
 	return false;
 }
  return true;
}


function setVal(id)
{
document.getElementById('paraFrm_hiddenRadio').value="Y";
 
}


</script>



