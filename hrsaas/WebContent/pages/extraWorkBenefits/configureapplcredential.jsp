
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>


<s:form action="ConfigureApplCredential" id="paraFrm" validate="true"
	target="main" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Configure  
					Application Credential </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">



				<tr>
					<td colspan="4"><s:submit cssClass="add"
						action="ConfigureApplCredential_save" value="    Save    "
						onclick="return callsave();" /> 
						
						<!--<s:submit cssClass="add"
						action="EmployeePortalAction_showEmployeePortal" value="    Back To Home    "
						  /> 
						
						--><!-- 	<s:submit cssClass="reset"
						action="ConfigureApplCredential_reset" value="    Reset    " />   -->
					
					</td>

					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>


				</tr>

			</table>

			<label></label></td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>

					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">


						<tr>
							<td width="20%" colspan="1" height="27"><label class="set"
								name="employee" id="employee" " ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font
								color="red">*</font> :</td>
							<td height="27"><s:hidden name="empCode" /> <s:hidden
								name="empToken" /> <s:textfield theme="simple" name="empName"
								size="40" maxlength="80" /> 
								</td>
						</tr>

						<tr>
							<td width="20%" colspan="1" height="27"><label class="set"
								name="application.name" id="application.name"
								ondblclick="callShowDiv(this);"><%=label.get("application.name")%></label><font
								color="red">*</font> :</td>
							<td height="27"><s:select theme="simple"
								name="applicationCode" value="%{applicationCode}" cssStyle="width:225"
								list="#{'-1':'--Select--','1':'Check My Mails','2':'Sales MAnagement System','3':'Servicedesk System','4':'Service Desk','5':'Help Desk - Perl','6':'Help Desk - Sapphire','7':'Support Sales System','8':'Supply Chain Management','9':'Education','10':'People Power','11':'Project Managemnt System','12':'Learning Center','13':'Astea Applications','14':'Webone','15':'Dispatch Extender','16':'Native Astea','17':'NTS'}" />

							</td>
						</tr>

						<tr>
							<td width="20%" colspan="1" height="27"><label class="set"
								name="user.name" id="user.name"
								" ondblclick="callShowDiv(this);"><%=label.get("user.name")%></label><font
								color="red">*</font> :</td>
							<td height="27"><s:textfield theme="simple" name="userName"
								size="40" maxlength="80" /></td>
						</tr>

						<tr>
							<td width="20%" colspan="1" height="27"><label class="set"
								name="user.password" id="user.password"
								" ondblclick="callShowDiv(this);"><%=label.get("user.password")%></label><font
								color="red">*</font> :</td>
							<td height="27"><s:password theme="simple"
								name="userPassword" size="40" maxlength="80" /></td>
						</tr>


					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">



				<tr>
					<td colspan="4"><s:submit cssClass="add"
						action="ConfigureApplCredential_save" value="    Save    "
						onclick="return callsave();" /> 
						
							<!-- 	<s:submit cssClass="reset"
						action="ConfigureApplCredential_reset" value="    Reset    " />   -->
						
					</td>

					<td width="22%">
					 
					</td>
				</tr>


				</tr>

			</table>

			<label></label></td>
		</tr>


	</table>

<s:hidden name="hiddenAutoCode" /> 

</s:form>


<script type="text/javascript">
  
 function callsave()
 {
  
   var empCode = document.getElementById('paraFrm_empCode').value;
   var applicationCode = document.getElementById('paraFrm_applicationCode').value;
   
    var userName = document.getElementById('paraFrm_userName').value;
    
     var userPassword = document.getElementById('paraFrm_userPassword').value;
   
   
   if(empCode=="")
   {
   			alert("Please select employee");
   			return false;
   }
   if(applicationCode=="")
   {
   			alert("Please select application name");
   			return false;
   }
   
    if(userName=="")
   {
   			alert("Please enter user name");
   			return false;
   }
   
    if(userPassword=="")
   {
   			alert("Please enter password");
   			return false;
   }
   
   
   
   return true;
  
	}

 

</script>






