<%@ taglib uri="/struts-tags" prefix="s"%>

<s:form action="ButtonMaster" method="post" name="EmailTemplate"
	validate="true" id="paraFrm" theme="simple">

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
					<td width="93%" class="txt"><strong class="text_head">Button
					Master </strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="78%"><s:submit cssClass="save"
						action="ButtonMaster_save" value="    Save"
						onclick="return callSave();" /> <s:submit cssClass="reset"
						action="ButtonMaster_clear" value="    Reset" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="1" width="20%">Button Name<font color="red">*</font>
							:</td>
							<td colspan="1" width="80%"><s:textfield name="btnName"
								value="%{btnName}" maxlength="40" /><s:hidden name="btnCode" /><s:hidden
								name="hiddenEdit" /></td>
						</tr>
						<tr>
							<td colspan="1" width="20%">Button Order<font color="red">*</font>:
							</td>
							<td colspan="1" width="80%"><s:textfield name="btnOrder"
								onkeypress="return numbersOnly();" maxlength="2" /></td>
						</tr>
						<tr>
							<td colspan="1" width="20%">Button Flag<font color="red">*</font>:
							</td>
							<td colspan="1" width="80%"><s:select name="btnFlag"
								list="#{'I':'Insert','U':'Update','D':'Delete','V':'View'}" /></td>
						</tr>
						<tr>
							<td colspan="1" width="20%">Enable Field<font color="red">*</font>
							:</td>
							<td colspan="1" width="80%"><s:select name="enableField"
								list="#{'Y':'Yes','N':'No'}" /></td>
						</tr>
						<tr>
							<td><s:submit name="add" cssClass="add" value="Add"
								action="ButtonMaster_addList" onclick="return validate();" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<%
		int cnt = 0;
		%>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td class="formth"><b>Button Name</b></td>
					<td class="formth"><b>Button Order</b></td>
					<td class="formth"><b>Button Flag</b></td>
					<td class="formth"><b>Enable Field</b></td>
					<td class="formth"><b>Edit | Delete</b></td>
				</tr>
				<%!int d = 0;%>
				<%
					int i = 1;
					int c = 0;
				%>
				<s:iterator value="buttonList">
					<tr>

						<td class="sortableTD"><s:hidden name="buttonCode"
							value="%{buttonCode}" /> <s:property value="buttonName" /><s:hidden
							name="buttonName" value="%{buttonName}" /></td>
						<td class="sortableTD"><s:property value="buttonOrder" /><s:hidden
							name="buttonOrder" value="%{buttonOrder}" /></td>
						<td class="sortableTD"><s:property value="buttonFlag" /><s:hidden
							name="buttonFlag" value="%{buttonFlag}" /></td>
						<td class="sortableTD"><s:property value="enabledisable" /><s:hidden
							name="enabledisable" value="%{enabledisable}" /></td>

						<td class="sortableTD" align="center"><input type="button"
							class="rowEdit" onclick="callForEdit('<%=i%>')" /> |
						<input type="button" class="rowDelete"
							onclick="callForDelete('<%=i%>')"</td>
					</tr>
					<%
						i++;
						c++;
					%>

				</s:iterator>
				<%
				d = i;
				%>
			</table>
			</td>
		</tr>
	</table>
	<input type="hidden" name="count" id="count" value="<%=c%>" />
</s:form>


<script>


function callForEdit(id){
   		document.getElementById('paraFrm_hiddenEdit').value=id;
   		document.getElementById("paraFrm").action="ButtonMaster_editData.action";
		document.getElementById("paraFrm").submit();
	   	return false;
  	
  		}
  		
  		 function callForDelete(id)
	   {
	  	document.getElementById('paraFrm_hiddenEdit').value=id;
   		document.getElementById("paraFrm").action="ButtonMaster_deleteData.action";
		document.getElementById("paraFrm").submit();
	   
   		}
  
  		function callSave()
  		{
  			if(document.getElementById("count").value==0)
  				{
					alert("There is no record to save");
						return false;
				}
			return true;
   		}
   
function validate()
{
 
	var btnName =document.getElementById("paraFrm_btnName").value;
	var btnOrder =document.getElementById("paraFrm_btnOrder").value;
	
	if(btnName=="")
	{
		alert("Please enter button name");
		document.getElementById("paraFrm_btnName").focus();
		return false;
	}
	if(btnOrder=="")
	{
		alert("Please enter button order");
		document.getElementById("paraFrm_btnOrder").focus();
		return false;
	}
	
	return true;
}


</script>