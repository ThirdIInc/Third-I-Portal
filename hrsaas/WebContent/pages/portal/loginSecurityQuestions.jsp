
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String comanyName = "";
	try {
		comanyName = (String) request.getAttribute("comanyName");
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
			<td colspan="3" width="100%" ><img
				src="../pages/portal/images/line.gif" width="100%" height="7" /></td>
		</tr>

		<tr>
			<td colspan="3" class="blacklink">
			<table class="formbg" width="100%" border="0" align="center" cellpadding="0" bordercolor="red"
		cellspacing="0">
				<tr>
					<td  colspan="3"><strong>Secure
					Authentication Question </strong></td>
				</tr>
				<tr>
					<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
				</tr>
				<input type="hidden" name="emp_id" value="" id="paraFrm_emp_id" />
				<tr>
					<td width="10%" nowrap="nowrap">Question1<font color="red">
					*</font> :</td>
					<td width="40%" nowrap="nowrap"><s:select name="secureQue1"
						cssStyle="width:1000" size="1" list="tmap" theme="simple" /></td>
						<td width="50%" nowrap="nowrap">
						<s:textfield
						name="secureAns1" value="%{secureAns1}" maxlength="30" /></td>
				</tr>
				<tr>
					<td width="10%" nowrap="nowrap">Question2<font color="red">
					*</font> :</td>
					<td width="40%" nowrap="nowrap"><s:select name="secureQue2"
						cssStyle="width:1000" size="1" list="qmap" theme="simple" />
						</td>
						<td width="50%" nowrap="nowrap">
						<s:textfield
						name="secureAns2" maxlength="30" value="%{secureQue2}" /></td>
				</tr>
				<tr>
					<td width="10%" nowrap="nowrap">Question3<font color="red">
					*</font> :</td>
					<td width="40%" nowrap="nowrap"><s:select name="secureQue3"
						cssStyle="width:1000" size="1" list="quesmap" theme="simple" />
						</td>
						<td width="50%" nowrap="nowrap">
						<s:textfield
						name="secureAns3" value="%{secureQue3}" maxlength="30" /></td>
				</tr>
				<tr>
					<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="1">&nbsp;</td>
					<td colspan="1"><s:submit theme="simple" value="Continue"
						onclick="return calladd();" action="GlodyneLogin_saveQuestion" /><s:hidden
						name="LoginCode" /><s:hidden name="infoId" /></td>
						<td colspan="1"></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%" ><img
				src="../pages/portal/images/line.gif" width="100%" height="7" /></td>
		</tr>
		<tr>
			<td colspan="1">&nbsp;</td>
			<td colspan="1">&nbsp;</td>
			<td colspan="1">&nbsp;</td>
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
		 alert(e);
	}
	return true;
	
}


</script>