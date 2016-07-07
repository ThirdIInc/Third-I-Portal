
<%@ taglib prefix="s" uri="/struts-tags"%>



<%
	String minLength = "6";
	String maxLength = "15";
	String msg = "";
	boolean flag = false;
	String comanyName = "";
	Object passInfoObj[][] = null;
	try {
		passInfoObj = (Object[][]) request.getAttribute("passInfoObj");
		comanyName = (String) request.getAttribute("comanyName");
		if (passInfoObj != null) {
			if (!String.valueOf(passInfoObj[0][0]).equals("0"))
		minLength = String.valueOf(passInfoObj[0][0]);
			if (!String.valueOf(passInfoObj[0][1]).equals("0"))
		maxLength = String.valueOf(passInfoObj[0][1]);
			if (String.valueOf(passInfoObj[0][2]).equals("Y"))
		msg = " Alphabets,";
			if (String.valueOf(passInfoObj[0][3]).equals("Y"))
		msg += " Special Characters,";
			if (String.valueOf(passInfoObj[0][4]).equals("Y"))
		msg += " Numbers,";
			if (String.valueOf(passInfoObj[0][5]).equals("Y"))
		msg += " At least one Capital Letter,";
		} else
			System.out.println("In Null >>>>>>>>>>>>>>>>>>>>>>>>>");
		if (!msg.equals("")) {
			msg = " and must contain"
			+ msg.substring(0, msg.length() - 1);
		}
	} catch (Exception e) {

	}
%>
<s:form action="GlodyneLogin" theme="simple" id="paraFrm"
	validate="true">
	
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
		<tr>
			<td height="30" colspan="2"><strong> Change Password</strong></td>
		</tr>
		<tr>
			<td colspan="3" class="blacklink">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				bordercolor="red">
				<input type="hidden" name="emp_id" value="" id="paraFrm_emp_id" />
				<tr>
					<td width="16%" colspan="1" class="headerDashlet">User Name:</td>
					<td colspan="1"><s:textfield theme="simple" name="userName"
						readonly="true" onkeypress="return charactersOnly();" size="25" /></td>
					<td colspan="1"></td>
				</tr>
				<tr>
					<td colspan="1"><span class="headerDashlet">Old
					Password</span><font color="red">*</font>:</td>
					<td colspan="1"><s:password theme="simple" name="oldpssword"
						size="25" maxlength="15" /></td>
					<td colspan="1"></td>
				</tr>
				<tr>
					<td colspan="1"><span class="headerDashlet">New
					Password</span><font color="red">*</font>:</td>
					<td width="16%" colspan="1"><s:password theme="simple"
						name="newpassword" size="25" maxlength="15" /></td>
					<td width="68%" colspan="1" align="left">(Password should be
					Min. <%=minLength%> and Max. <%=maxLength%> Characters in length <%=msg%>)</td>
				</tr>
				<tr>
					<td colspan="1"><span class="headerDashlet">Confirm New
					Password</span><font color="red">*</font>:</td>
					<td colspan="1"><s:password theme="simple" name="confirmpass"
						size="25" maxlength="15" /></td>
					<td colspan="1"></td>
				</tr>
				<tr>
					<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1"><s:hidden name="LoginCode" /><s:hidden
						name="infoId" /><s:submit theme="simple" value="Continue"
						onclick="return calladd();"
						action="GlodyneLogin_saveChangePassword" /></td>
					<td colspan="1"></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2" width="100%"><img
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
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">

function calladd()
{
	try
	{
	var oldpssd  = document.getElementById('paraFrm_oldpssword').value;
	var newpssd1 = document.getElementById('paraFrm_newpassword').value;
	var newpssd2 = document.getElementById('paraFrm_confirmpass').value;
	
	if(oldpssd =="")
	{
		alert('Please Enter Old Password');
		return false;
	}
	
	
	if(newpssd1 =="")
	{
		alert('Please Enter New Password');
		return false;
	}
	
	if(newpssd1 == oldpssd)
	{
		alert('Old password and new Password must not be same');
		return false;
	}
	if(newpssd2 =="")
	{
		alert('Please Enter Confirm  New Password');
		return false;
	}
	if(newpssd1.length < eval(<%=minLength %>) || newpssd1.length > eval(<%=maxLength %>))
	{
		alert('Invalid Password! Password should be of minimum <%=minLength %> and maximum <%=maxLength %> Characters long.');
		return false;
	}

	if(newpssd1 !=newpssd2)
	{
		alert('New Password and confirm password must be same');
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
