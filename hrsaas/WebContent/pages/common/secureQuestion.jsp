<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="commonValidations.jsp" %>

<body>
 
	<script type="text/javascript"
	src="../pages/common/include/javascript/styleswitch.js"></script>
<s:form action="Login" theme="simple" id="paraFrm" validate="true">

<%
	String comanyName = "";
	try {
		comanyName = (String) request.getAttribute("comanyName");
	} catch (Exception e) {

	}
%>

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
			<td class="txt" colspan="3"><strong class="formhead"><img align="absmiddle"
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong>&nbsp;&nbsp;&nbsp;<strong class="formhead">Secure Authentication
			Question </strong></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>

		<s:hidden name="emp_id" />
		<tr>

			<td width="9%" nowrap="nowrap">Question1<font color="red">
			*</font> :</td>
		  <td width="28%" nowrap="nowrap"><s:select name="secureQue1"
				cssStyle="width:400"  
				size="1" list="tmap" theme="simple" /></td>
		  <td width="63%" nowrap="nowrap" align="left"><s:textfield name="secureAns1"
				value="%{secureAns1}" maxlength="30" />	</td>

		</tr>
		<tr>
			<td width="9%" nowrap="nowrap">Question2<font color="red">
			*</font> :</td>
		  <td width="28%" nowrap="nowrap"><s:select name="secureQue2"
				cssStyle="width:400"  
				size="1" list="qmap" theme="simple" /></td>
		  <td width="63%" nowrap="nowrap" align="left"><s:textfield name="secureAns2"  maxlength="30"
				value="%{secureQue2}" /></td>
				
		</tr>
		<tr>
			<td width="9%" nowrap="nowrap">Question3<font color="red">
			*</font> :</td>
		  <td width="28%" nowrap="nowrap"><s:select name="secureQue3"
				cssStyle="width:400"  
				size="1" list="quesmap" theme="simple" /></td>
		  <td width="63%" nowrap="nowrap" align="left"><s:textfield 
				name="secureAns3" value="%{secureQue3}" maxlength="30" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
		</tr>

		<tr>
			<td colspan="1" width="9%">&nbsp;</td>
			<td colspan="1" width="28%"><s:submit cssClass="token"
				theme="simple" value="Continue" onclick="return calladd();"
				action="Login_saveQuestion" /><s:hidden name="LoginCode" /><s:hidden name="infoId" /></td>
				<td colspan="1" width="63%"></td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">

function calladd()
{
	try
	{
	
	var question1=document.getElementById('paraFrm_secureQue1').value;
	var question2=document.getElementById('paraFrm_secureQue2').value;
	var question3=document.getElementById('paraFrm_secureQue3').value;
	var answer_1=document.getElementById('paraFrm_secureAns1').value;
	var answer_2=document.getElementById('paraFrm_secureAns2').value;
	var answer_3=document.getElementById('paraFrm_secureAns3').value;
	
	var fields=["paraFrm_secureAns1","paraFrm_secureAns2","paraFrm_secureAns3"];
    var labels=["answer of question1","answer of question2","answer of question3"];
    var flag = ["enter","enter","enter"];
 	 if(!checkMandatory(fields,labels,flag))
     return false;
		if(question1=="")
	{
		alert('Please select question1');
		return false;
	}
	if(answer_1=="")
	{
			alert('Please enter answer of question1');
		return false;
	}
		if(question2=="")
	{
			alert('Please select question2');
		return false;
	}
	if(answer_2=="")
	{
			alert('Please enter answer of question2');
		return false;
	}
		if(question3=="")
	{
			alert('Please select question3');
		return false;
	}
	if(answer_3=="")
	{
			alert('Please enter answer of question3');
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