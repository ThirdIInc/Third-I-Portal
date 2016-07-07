<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript"
	src="../pages/common/include/javascript/styleswitch.js"></script>
<%
	Object passInfoObj[][] = null;
	String minLength = "6";
	String maxLength = "15";
	String msg = "";
	String comanyName = "";
	boolean flag = false;

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
<body>
<s:form action="Login" theme="simple" id="paraFrm" validate="true">


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
				src="../pages/common/css/default/images/logo.jpg"/> <%
 }
 %>
			</td>
		</tr>
	</table>
	</div>
	</div>
	

	<table class="formbg" width="100%" cellpadding="0" cellspacing="0" align="left">
		<tr>
			<td class="txt" colspan="3"><strong class="formhead"><img
				align="absmiddle"
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong>&nbsp;&nbsp;<strong class="formhead">Change
			Password </strong></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>

		<s:hidden name="emp_id" />
		<tr>
			<td width=17% colspan="1">User Name:</td>
			<td width="16%" colspan="1"><s:textfield theme="simple"
				name="userName" readonly="true"
				onkeypress="return charactersOnly();" size="25" /></td>
			<td width="67%" colspan="1"></td>
		</tr>
		<tr>
			<td width=17% colspan="1">Old Password<font color="red">*</font>:</td>
			<td width="16%" colspan="1"><s:password theme="simple"
				name="oldpssword" size="25" maxlength="15" /></td>
			<td width="67%" colspan="1"></td>
		</tr>
		<tr>
			<td width=17% colspan="1">New Password<font color="red">*</font>:</td>
			<td width="16%" colspan="1"><s:password theme="simple"
				name="newpassword" size="25" maxlength="15" /></td>
			<td width="67%" colspan="1" align="left">(Password should be
			Min. <%=minLength%> and Max. <%=maxLength%> Characters in length <%=msg%>)</td>

		</tr>
		<tr>
			<td width=17% colspan="1">Confirm New Password<font color="red">*</font>:</td>
			<td width="16%" colspan="1"><s:password theme="simple"
				name="confirmpass" size="25" maxlength="15" /></td>
			<td width="67%" colspan="1"></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>

		<tr>

			<td colspan="1" width="17%">&nbsp;</td>
			<td colspan="1" width="16%"><s:hidden name="LoginCode" /><s:hidden
				name="infoId" /><s:submit cssClass="token" theme="simple"
				value="Continue" onclick="return calladd();"
				action="Login_saveChangePassword" /></td>
			<td colspan="1" width="67%"></td>
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