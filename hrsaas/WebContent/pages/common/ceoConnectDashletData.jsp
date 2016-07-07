<%@ include file="/pages/common/labelManagement.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="EmployeePortal" id="paraFrm" theme="simple"
	name="employeePortalForm">

	<%
	String loginEmpCode = request.getParameter("loginEmpCode");
	%>

	<input type="hidden" name="loginEmpCode" value="<%=loginEmpCode %>"
		id="loginEmpCode" />


	<table width="100%" id="div_Id"
		style="display: inline; vertical-align: top; width: 621px; height: 244px;"
		class="formbg">

		<tr>
			<td width="100%" valign="top">



			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt">
					<table width="100%" border="0" align="right" cellpadding="0"
						cellspacing="0" class="formbg">
						<tr>
							<td><strong class="text_head"><img
								src="../pages/mypage/images/icons/46.png" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">CEO
							Connect </strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td nowrap="nowrap">Subject: <font color="red">*</font></td>
							<td><s:textfield name="subject" theme="simple" size="70"
								id="subject" maxlength="40" onkeypress="return allCharacters();" /></td>
						</tr>

						<tr>
							<td nowrap="nowrap">Description: <font color="red">*</font></td>
							<td><s:textarea name="description"
								cssStyle="width: 621px; height: 244px;" theme="simple" cols="72"
								id="paraFrm_description" rows="5"
								onkeyup="calculateRemainingChars('descCnt');"></s:textarea></td>
						</tr>
						<tr>
							<td></td>
							<td align="right">Remaining Characters:<s:textfield
								name="descCnt" size="5" readonly="true"></s:textfield></td>
						</tr>

						<tr>
							<td nowrap="nowrap">Hide my identity:</td>
							<td><input type="checkbox" name="hideIdentity"
								id="hideIdentity" /></td>
						</tr>


					</table>
					</td>
				</tr>
				<tr>
					<td width="100%">
					<table width="100%">
						<tr>
							<td width="50%" align="right"><input type="button"
								id="ctrlShow" value="Submit" class="token"
								onclick="callSubmit();" /> <input type="button" id="ctrlShow"
								value="Clear" onclick="callClear();" /></td>
							<td></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>

<script>

function callClear()
{
	document.getElementById('subject').value="";
	document.getElementById('description').value="";
	document.getElementById('hideIdentity').checked=false;
}


function calculateRemainingChars(type)
{
   if(type=='descCnt')
  {
   var desc=document.getElementById('paraFrm_description').value;
   var remainChars=1000-eval(desc.length);
   document.getElementById('paraFrm_descCnt').value=remainChars;
   if((eval(remainChars)<0))
    {
      document.getElementById('paraFrm_description').style.background="#FFFF99";
      return false;
    }
   else
    {
      document.getElementById('paraFrm_description').style.background="#FFFFFF";
    }
  }
}

	function callSubmit()
{
	try{
		var subject  = trim(document.getElementById('subject').value);
		
		var description  = trim(document.getElementById('paraFrm_description').value);
		
		var hideIdentity  = "";
	 
		if(document.getElementById('hideIdentity').checked)
		{
		hideIdentity='Y';
		}
		else{
		hideIdentity='N';
		}
		
		var loginEmpId =  document.getElementById('loginEmpCode').value;
		 
		
		if(subject == "")
		{
			alert("Please enter subject");
			return false ;
		}
		
		if(description == "")
		{
			alert("Please enter description");
			return false ;
		}
 		
		retrieveCeoConnect('<%=request.getContextPath()%>/portal/EmployeePortal_submitCeoDesc.action?random='+Math.random()+'&subject='+subject+'&description=' + description+'&hideIdentity='+hideIdentity+'&loginEmpId='+loginEmpId, 'employeePortalForm')
	return true ;	
	}catch(e){ alert(e);}
	

}

</script>