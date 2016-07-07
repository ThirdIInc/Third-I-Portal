<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="OpenCloseOffice" validate="true" id="paraFrm" target="main"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="1"
		cellspacing="2" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="93%" class="txt"><strong
							class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong><strong class="text_head">Configuration</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1">
				<tr>
					<td width="78%"></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				
				<tr>
					<td width="10%" colspan="1" class="formtext"><label  class = "set"  id="branch" 
					name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label><font color="red">*</font> :</td>
						<s:hidden name="branchId"></s:hidden>
					<td width="90%" colspan="3"><s:textfield
						name="branchName" size="35" value="%{branchName}" theme="simple"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17"
						onclick="javascript:callsF9(500,325,'OpenCloseOfficeConfig_f9branch.action');">
					</td>
				</tr>
				
				<tr>
					<td width="10%" colspan="1" class="formtext"><label  class = "set"  id="employee" name="employee"
					 ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font color="red">*</font> :
					<s:hidden name="empCode"/></td>
					<td width="90%" colspan="3"><s:textfield name="empToken"
						size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
						name="ename" size="50" value="%{ename}" theme="simple"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17"
						onclick="javascript:callsF9(500,325,'OpenCloseOfficeConfig_f9Employee.action');">
					</td>
				</tr>
				
				<tr>
					<td colspan="2"><s:submit cssClass="add" action="OpenCloseOfficeConfig_add"
						theme="simple" value=" Add"
						onclick="return callAdd();" />
					&nbsp;<s:submit cssClass="reset" action="OpenCloseOfficeConfig_resetButton"
						theme="simple" value=" Reset"/> 
					</td>
				</tr>
			</table>
			<table width="100%" class="formbg">
			<tr>
				<td class="formth" width="25%" colspan="1"><label name="employee.id" id="employee.id" 
				ondblclick="callShowDiv(this);"><%=label.get("employee.id") %></label> </td>
				<td class="formth" width="55%" colspan="1"><label name="employee" id="employee" 
				ondblclick="callShowDiv(this);"><%=label.get("employee") %></label> </td>
				<td class="formth" width="20%" colspan="1">&nbsp;</td>
			</tr>
				<s:if test="listNotnull">
					<s:iterator value="empList">
						<tr>
							<td class="sortableTD" width="25%" colspan="1">
							<s:hidden  value="empIdList" /><s:property
								value="empTokenList" /></td>
							<td class="sortableTD" width="55%" colspan="1"><s:property
								value="enameList" /></td>
							<td class="sortableTD" width="20%" colspan="1" align="center">
							<input type="button" title="Delete Record" class="rowDelete"
								onclick="callForDelete('<s:property value="empIdList"/>','OpenCloseOfficeConfig_delete.action')" />
							</td>
						</tr>
					</s:iterator>			
				</s:if>
				<s:else>
					<tr align="center">
						<td colspan="6" class="sortableTD" width="100%"><font
							color="red">No Data to display</font></td>
					</tr>
				</s:else>
			</table>
			</td>
		</tr>	
		<s:hidden name="delEmpCode"/>	
	</table>	
</s:form>

<script>
	function callAdd(){
		var bCode = document.getElementById('paraFrm_branchId').value;
		var eCode = document.getElementById('paraFrm_empCode').value;
		if(bCode == ""){
			alert("Please select branch");
			return false;
		}
		if(eCode == ""){
			alert("Please select employee");
			return false;
		}
		return true;
	}
	
	function callForDelete(empCode,action){
		if(confirm('<%=label.get("confirm.delete")%>'))
		{
			document.getElementById("paraFrm_delEmpCode").value = empCode;
			document.getElementById('paraFrm').action = action;
			document.getElementById('paraFrm').submit();
		} //end of if
	} //end of func
	
</script>