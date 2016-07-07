<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="EmployeePortal" validate="true" id="paraFrm" validate="true"
	theme="simple">


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
					<td width="93%" class="txt"><strong class="text_head">Connect
					with CEO </strong></td>
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
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2">



						<tr>
							<td width="20%" height="22" class="formtext" colspan="3">
							Subject:<font color="red">*</font></td>
							<td width="80%"><s:textfield name="subject"
								theme="simple" size="70" maxlength="40"
								onkeypress="return allCharacters();" /></td>

						</tr>

						<tr>
							<td width="20%" height="22" class="formtext" colspan="3">
							Description:<font color="red">*</font></td>
							<td width="80%"><s:textarea name="description"
								theme="simple" cols="70" rows="5" /></td>

						</tr>

						<tr>
							<td width="20%" height="22" class="formtext" colspan="3">Hide
							my identity:</td>
							<td width="80%"><s:checkbox name="hideIdentity" value="%{hideIdentity}"></s:checkbox></td>

						</tr>

						<tr>
							<td align="center">
							<table width="100%">
								<tr>
									<td width="100%" align="center" nowrap="nowrap"><input type="button"
										id="ctrlShow" value=" Submit " class="token"
										onclick="callSubmit();" /> 
									 <input id="ctrlShow" type="button" value="Close"
										class="token" onclick="callClose();"></td>
								</tr>
							</table>
							</td>
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

function callClose()
{
	window.close();
}


function callSubmit()
{
		var subject  = document.getElementById('paraFrm_subject').value;
		
		var description  = document.getElementById('paraFrm_description').value;
		
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
		
	 	document.getElementById('paraFrm').action = 'EmployeePortal_submitCeoDesc.action';
		document.getElementById('paraFrm').submit();	
		window.close();
		
	return true ;	

}
</script>
