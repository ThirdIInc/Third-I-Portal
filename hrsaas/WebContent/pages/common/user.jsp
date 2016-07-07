<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<%
			Object passInfoObj[][] = (Object[][]) request
			.getAttribute("passInfoObj");
	String minLength = "6";
	String maxLength = "15";
	String msg = "";
	boolean flag = false;
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
	if (!msg.equals(""))
		msg = " and must contain" + msg.substring(0, msg.length() - 1);
%>
<s:form action="User" method="post" validate="true" theme="simple"
	name="form" id="paraFrm">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">User
					Management </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:if test="%{insertFlag}">
						<s:submit cssClass="add" action="User_submit"
							onclick="return formValidate()" value="    Save" />
					</s:if> <!-- <input type="button" class="search"
						onclick="javascript:callsF9(500,325,'User_f9Token.action');"
						value="    Search " /> --> <s:submit cssClass="reset"
						action="User_reset" value="    Reset" /> <s:if
						test="%{deleteFlag}">
						<s:submit cssClass="delete" action="User_delete"
							value="    Delete"
							onclick="return callDelete('paraFrm_loginCode');" />
					</s:if> <s:submit cssClass="back" action="User_cancel" value="    Back" />
					<s:hidden name="userBean.userProfileID" /><s:hidden name="userBean.userEmpID" />
					<s:hidden name="loginCode" /><s:hidden name="userBean.login_profile_ID"></s:hidden></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td colspan="5" class="formhead"><strong class="forminnerhead">User
					Management</strong></td>
				</tr>
				<s:if test="userFlag">
					<tr>
						<td width="17%" colspan="1"><label name="employee"
							id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font
							color="red"> *</font>:</td>
						<td width="18%" colspan="4"><s:textfield theme="simple"
							label="%{getText('userToken')}" name="userBean.userToken"
							size="8" readonly="true" /><s:textfield
							name="userBean.userEmpName" size="30" theme="simple"
							readonly="true" /> <img src="../pages/images/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callsF9(500,325,'User_f9UserToken.action');"></td>
					</tr>
				</s:if>
				<s:else>
					<td width="17%" colspan="1"><label name="employee"
						id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font
						color="red"> *</font>:</td>
					<td width="18%" colspan="4"><s:textfield theme="simple"
						label="%{getText('userToken')}" name="userBean.userToken" size="8"
						readonly="true" /><s:textfield name="userBean.userEmpName"
						size="30" theme="simple" readonly="true" /></td>
				</s:else>
				<tr>
					<td width="17%" colspan="1"><label name="profile" id="profile"
						ondblclick="callShowDiv(this);"><%=label.get("profile")%></label><font
						color="red"> *</font>:</td>
					<td width="18%" colspan="4"><s:textarea rows="1" cols="41" theme="simple"
						name="userBean.login_profile" readonly="true" /><img
						src="../pages/images/search2.gif" class="iconImage" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_userBean_login_profile',200,250,'User_f9Multiprofile.action',event,'false','','right')">
					</td>
				</tr>
				<tr>
					<td width="17%" colspan="1"><label name="accessProfile"
						id="accessProfile" ondblclick="callShowDiv(this);"><%=label.get("accessProfile")%></label>
					:</td>
					<td width="18%" colspan="4"><s:textarea rows="1" cols="41" theme="simple"
						name="userBean.login_access_profile" readonly="true" /><img
						src="../pages/images/search2.gif" class="iconImage" height="16"
						align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_userBean_login_access_profile',200,250,'User_f9MultiaccessProfile.action',event,'false','','right')">						
					    <s:hidden name="userBean.login_access_profile_ID"></s:hidden>
					    <s:hidden name="userBean.accessProfileId" /></td>
				</tr>
				<tr>
					<td width="17%" colspan="1"><label name="user.name"
						id="user.name" ondblclick="callShowDiv(this);"><%=label.get("user.name")%></label><font
						color="red"> *</font>:</td>
					<td width="18%" colspan="3"><s:textfield theme="simple"
						label="%{getText('userName')}" name="userBean.userName"
						maxlength="50" /></td>
				</tr>
				<tr>
					<td width="17%" colspan="1"><label name="password"
						id="password" ondblclick="callShowDiv(this);"><%=label.get("password")%></label>
					<font color="red"> *</font>:</td>
					<td width="18%" colspan="1"><s:password theme="simple"
						name="userBean.userPassword"
						onkeyup="testPassword(paraFrm_userBean_userPassword.value);" /></td>
					<td width="65%" colspan="2">(Password should be Min. <%=minLength%>
					and Max. <%=maxLength%> Characters in length <%=msg%>)</td>
				</tr>
				<tr>
					<td width="17%" colspan="1"><label name="confirm.password"
						id="confirm.password" ondblclick="callShowDiv(this);"><%=label.get("confirm.password")%></label>
					<font color="red"> *</font>:</td>
					<td width="18%" colspan="1"><s:password theme="simple"
						name="userBean.confirmPass" /></td>
					<td width="65%" colspan="2">
					<table>
						<tr>
							<td nowrap="nowrap"><font size="1"> <label
								name="password.strength" id="password.strength"
								ondblclick="callShowDiv(this);"><%=label.get("password.strength")%></label>:</font></td>
							<td><a id="Words"><font size="1"></font></a></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="17%" colspan="1"><label name="lblValidUpto" id="lblValidUpto"
						ondblclick="callShowDiv(this);"><%=label.get("lblValidUpto")%></label>:</td>
					<td width="18%" colspan="3"><s:textfield name="userBean.validUpto" theme="simple"/>
						<s:a href="javascript:NewCal('paraFrm_userBean_validUpto','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"></s:a></td>
				</tr>					
				<tr>
					<td width="17%" colspan="1"><label name="active" id="active"
						ondblclick="callShowDiv(this);"><%=label.get("active")%></label> <font
						color="red"> *</font>:</td>
					<td width="18%" colspan="3"><s:select theme="simple"
						name="userBean.userActive" list="#{'YES':'YES','NO':'NO'}" />&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<s:hidden name="hiddenLockStatus" />
				<s:if test="hiddenLockStatus">
					<tr>
						<td width="17%" colspan="1"><label name="lock.user"
							id="lock.user" ondblclick="callShowDiv(this);"><%=label.get("lock.user")%></label>:</td>
						<td width="18%" colspan="4"><s:checkbox theme="simple"
							name="userBean.lockFlag" /></td>
					</tr>
				</s:if>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>

function getEmployee()
{
	var loginCode =document.getElementById('paraFrm_loginCode').value;
	if(loginCode == "")
		callsF9(500,325,'User_f9UserToken.action');
	else
		alert("You can't change or select Token No for this user ! ")
}

function formValidate()
{
	var fieldName = ['paraFrm_userBean_userToken','paraFrm_userBean_login_profile','paraFrm_userBean_userName','paraFrm_userBean_userPassword','paraFrm_userBean_confirmPass'];
	var labelName = ['employee','profile','user.name','password','confirm.password'];
	var flag = ['select','select','enter','enter','enter'];
	var validDate = document.getElementById('paraFrm_userBean_validUpto').value;
	try{
	if(!validateBlank(fieldName,labelName, flag)){
		return false;
		}
		}catch(e){
		alert(e);
		}
	if(!validateDate('paraFrm_userBean_validUpto','lblValidUpto')){ 
		       return false;
	}
	if(!checkDatewithCurrentDate(validDate,'lblValidUpto')){
		       return false;
	}	
	var password = document.getElementById('paraFrm_userBean_userPassword').value;
	var confPass = document.getElementById('paraFrm_userBean_confirmPass').value;
	if(password.length < eval(<%= minLength%>) || password.length > eval(<%= maxLength%>))
	{
		alert(document.getElementById('password').innerHTML.toLowerCase()+' should be of minimum '+<%= minLength%>+' and maximum '+<%= maxLength%>+' characters long.');
		return false;
	}
	if(password != confPass)
	{
		alert(document.getElementById('password').innerHTML.toLowerCase()+" and "+document.getElementById('confirm.password').innerHTML.toLowerCase()+" should be same");
		return false;
	}
  	return true;
}


var passwd=document.getElementById("paraFrm_userBean_userPassword").value;
testPassword(passwd);
function testPassword(passwd){
	var description = new Array();
	description[0] = "<table><tr><td nowrap><table cellpadding=0 cellspacing=2><tr><td height=8 width=30 bgcolor=#ff0000></td><td height=8 width=120 bgcolor=tan></td></tr></table></td><td>   <b>Weakest</b></td></tr></table>";
	description[1] = "<table><tr><td nowrap><table cellpadding=0 cellspacing=2><tr><td height=8  width=60 bgcolor=#990000></td><td height=8  width=90 bgcolor=tan></td></tr></table></td><td>   <b>Weak</b></td></tr></table>";
	description[2] = "<table><tr><td nowrap><table cellpadding=0 cellspacing=2><tr><td height=8  width=90 bgcolor=#990099></td><td height=8  width=60 bgcolor=tan></td></tr></table></td><td>   <b>Improving</b></td></tr></table>";
	description[3] = "<table><tr><td nowrap><table cellpadding=0 cellspacing=2><tr><td height=8  width=120 bgcolor=#000099></td><td height=8  width=30 bgcolor=tan></td></tr></table></td><td>   <b>Strong</b></td></tr></table>";
	description[4] = "<table><tr><td nowrap><table  cellpadding=0 cellspacing=2><tr><td height=8  width=150 bgcolor=#0000ff></td></tr></table></td><td>   <b>Strongest</b></td></tr></table>";
	description[5] = "<table><tr><td nowrap><table  cellpadding=0 cellspacing=2><tr><td height=8  width=150 bgcolor=tan></td></tr></table></td><td>   <b></b></td></tr></table>";
	var base = 0
	var combos = 0
	if (passwd.match(/[a-z]/))
	{
		base = (base+26);
	}
	if (passwd.match(/[A-Z]/))
	{
		base = (base+26);
	}
	if (passwd.match(/\d+/))
	{
		base = (base+10);
	}
    if (passwd.match(/[>!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~]/))
	{
		base = (base+33);
	}
	combos=Math.pow(base,passwd.length);
	if(combos == 1)
	{
 		strVerdict = description[5];
	}
    else if(combos > 1 && combos < 1000000)
	{
		strVerdict = description[0];
	}
	else if (combos >= 1000000 && combos < 1000000000000)
	{
		strVerdict = description[1];
	}
	else if (combos >= 1000000000000 && combos < 1000000000000000000)
	{
		strVerdict = description[2];
	}
	else if (combos >= 1000000000000000000 && combos < 1000000000000000000000000)
	{
		strVerdict = description[3];
	}
	else
	{
		strVerdict = description[4];
	}
		document.getElementById("Words").innerHTML= (strVerdict);

      var profile= document.getElementById('paraFrm_userBean_userPassword').value
	}
	
	
	function checkDatewithCurrentDate(date, labName){
		todayDate 	= new Date();
		var year   	= todayDate.getFullYear();
		var month   = todayDate.getMonth();
		var day     = todayDate.getDate();
		
		enteredDate = date.split("-");
		startTime   = new Date (enteredDate[2], enteredDate[1]-1, enteredDate[0]);
		endTime     = new Date(year, month, day);
		
		var diff = startTime-endTime;
		if(diff <= 0){
			alert(""+document.getElementById(labName).innerHTML.toLowerCase()+" date should be greater than today's date");
			return false;
		}
		return true;
}
</script>
