<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="LeavePolicySetting" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2" n
				cellspacing="2" class="formbg">
				<tr>
					<s:hidden name="policyCancel" />
					<s:hidden name="excepCancel" />
					<s:hidden name="editPolicies" />
					<s:hidden name="editExceptions" />
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="100%" class="txt"><strong class="text_head">Leave
					Policy Setting </strong></td>
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
					<s:hidden name="hiddenCode" />
					<s:hidden name="hiddenDivCode" />
					<s:hidden name="newPolicyFlag" />
					<s:hidden name="newExceptionFlag" />
					<s:hidden name="nextFlag" />
					<td width="78%"></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<s:if test="%{onLoadFlag}">
					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="division" id="division"
							ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						:<font color="red">*</font></td>
						<td colspan="3" width="80%"><s:textfield size="25"
							name="divisionName" readonly="true" /> <s:hidden
							name="divisionCode" /> <img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callDivision();" /></td>
					</tr>
				</s:if>
				<s:elseif test="%{newPolicyFlag}">
					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="division" id="division"
							ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						:<font color="red">*</font></td>
						<td colspan="3" width="80%"><s:textfield size="25"
							name="divisionName" readonly="true" /> <s:hidden
							name="divisionCode" /> <img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callDivision();" /></td>
					</tr>
				</s:elseif>
				<s:elseif test="%{newExceptionFlag}">
					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="division" id="division"
							ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						:<font color="red">*</font></td>
						<td colspan="3" width="80%"><s:textfield size="25"
							name="divisionName" readonly="true" /> <s:hidden
							name="divisionCode" /> <img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callDivision();" /></td>
					</tr>
				</s:elseif>
				<s:if test="%{newPolicyFlag}"></s:if>
				<s:else>
					<s:if test="%{newExceptionFlag}"></s:if>
					<s:else>
						<tr>
							<td width="100%" align="center" colspan="4"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						</tr>
					</s:else>
				</s:else>
			</table>
			</td>
		</tr>

		<s:if test="%{newPolicyFlag}">
			<tr>
				<td>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td height="15" class="formhead" nowrap="nowrap"><strong
							class="forminnerhead">Add New Policies</strong></td>
					</tr>
					<tr>
						<td height="15" class="formhead" nowrap="nowrap"><strong
							class="forminnerhead">Select a filter:-</strong></td>
					</tr>

					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="department" id="department"
							ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
						:</td>
						<td colspan="1" width="30%"><s:textfield size="25"
							name="deptName" readonly="true" /> <s:hidden name="deptCode" />
						<img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callDept();" /></td>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="branch" id="branch"
							ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
						:</td>
						<td colspan="1" width="30%"><s:textfield size="25"
							name="branchName" readonly="true" /> <s:hidden name="branchCode" />
						<img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callBranch();" /></td>
					</tr>

					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							name="employee.type" id="employee.type"
							ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
						:</td>
						<td colspan="1" width="30%"><s:textfield size="25"
							name="empTypeName" readonly="true" /> <s:hidden
							name="empTypeCode" /> <img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callEmpType();" /></td>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="designation" id="designation"
							ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
						:</td>
						<td colspan="1" width="30%"><s:textfield size="25"
							name="desgName" readonly="true" /> <s:hidden name="desgCode" />
						<img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callDesignation();" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>

		<s:if test="%{newExceptionFlag}">
			<tr>
				<td>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td colspan="3" class="text_head"><strong
							class="forminnerhead">Add Employee Exceptions:-</strong></td>
					</tr>
					<tr>
						<td colspan="1" width="20%" class="formtext"><label
							class="set" name="employee" id="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						:<font color="red">*</font></td>
						<td colspan="1" width="14%"><s:hidden name="employeeCode"
							value="%{employeeCode}" /><s:textfield readonly="true" size="15"
							name="tokenNo" /></td>
						<td colspan="1" width="66%"><s:textfield size="80"
							name="employeeName" label="%{getText('employeeName')}"
							readonly="true" /><img class="iconImage"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callEmployee();" /></td>
					</tr>

				</table>
				</td>
			</tr>
		</s:if>

		<s:if test="%{nextFlag}"></s:if>
		<s:else>
			<s:if test="%{onLoadFlag}"></s:if>
			<s:else>
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2" class="formbg">
						<tr>
							<td colspan="1" width="20%" class="formtext"><label
								class="set" name="policy" id="policy"
								ondblclick="callShowDiv(this);"><%=label.get("policy")%></label>
							:<font color="red">*</font></td>
							<td colspan="3" width="80%"><s:textfield size="25"
								name="policyName" readonly="true" /> <s:hidden
								name="policyCode" /> <img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" border="0" onclick="javascript:callPolicy();" /></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="2">
						<tr>
							<td width="100%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</s:else>
		</s:else>

		<s:if test="%{nextFlag}">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="3" class="text_head"><strong
							class="forminnerhead">Policy List</strong></td>
					</tr>
					<tr>
						<td class="formth" width="5%" height="22" valign="top"><label
							class="set" id="srNo" name="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
						<td class="formth" width="20%" height="22" valign="top"><label
							class="set" id="division1" name="division"
							ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
						<td class="formth" width="15%" height="22" valign="top"><label
							class="set" id="department1" name="department"
							ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
						<td class="formth" width="20%" height="22" valign="top"><label
							class="set" id="branch1" name="branch"
							ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
						<td class="formth" width="15%" height="22" valign="top"><label
							class="set" id="employee.type1" name="employee.type"
							ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label></td>
						<td class="formth" width="20%" height="22" valign="top"><label
							class="set" id="designation1" name="designation"
							ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
						</td>
						<td class="formth" width="15%" height="22" valign="top"><label
							class="set" id="policy" name="policy"
							ondblclick="callShowDiv(this);"><%=label.get("policy")%></label></td>
						<td class="formth" width="5%" height="22" valign="top">Edit</td>
						<td class="formth" width="5%" height="22" valign="top">Delete</td>
					</tr>
					<s:if test="noData">
						<tr>
							<td width="100%" colspan="7" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
					</s:if>
					<%
					int k = 1;
					%>
					<s:iterator value="tableList" status="stat">
						<tr>
							<td width="5%" class="sortableTD"><%=k%></td>
							<td width="15%" class="sortableTD"><s:property
								value="divisionNameItr" /><s:hidden name="divisionNameItr" /><s:hidden
								name="divisionCodeItr" /></td>
							<td width="15%" class="sortableTD"><s:property
								value="deptNameItr" /><s:hidden name="deptNameItr" /><s:hidden
								name="deptCodeItr" /></td>
							<td width="20%" class="sortableTD"><s:property
								value="branchNameItr" /><s:hidden name="branchNameItr" /> <s:hidden
								name="branchCodeItr" /></td>
							<td width="15%" class="sortableTD"><s:property
								value="empTypeNameItr" /><s:hidden name="empTypeNameItr" /><s:hidden
								name="empTypeCodeItr" /></td>
							<td width="20%" class="sortableTD"><s:property
								value="desgNameItr" /><s:hidden name="desgCodeItr" /></td>
							<s:hidden name="desgNameItr" />
							<td width="15%" class="sortableTD"><s:property
								value="policyNameItr" /><s:hidden name="policyCodeItr" /></td>
							<s:hidden name="policyNameItr" />
							<td width="5%" class="sortableTD" align="center"><s:hidden
								name="settingCode" /> <input type="button" class="rowEdit"
								class="edit" title="Edit Record"
								onclick="editList('<s:property value="settingCode" />');" /></td>
							<td width="5%" class="sortableTD" align="center"><input
								type="button" class="rowDelete" class="delete"
								title="Delete Record"
								onclick="deleteList('<s:property value="settingCode" />');" /></td>
						</tr>
						<%
						k++;
						%>
					</s:iterator>
				</table>
				</td>
			</tr>
		</s:if>
		<s:elseif test="%{newPolicyFlag}">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="3" class="text_head"><strong
							class="forminnerhead">Policy List</strong></td>
					</tr>
					<tr>
						<td class="formth" width="5%" height="22" valign="top"><label
							class="set" id="srNo1" name="srNo" ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
						<td class="formth" width="20%" height="22" valign="top"><label
							class="set" id="division2" name="division"
							ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
						<td class="formth" width="15%" height="22" valign="top"><label
							class="set" id="department2" name="department"
							ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
						<td class="formth" width="20%" height="22" valign="top"><label
							class="set" id="branch2" name="branch"
							ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
						<td class="formth" width="15%" height="22" valign="top"><label
							class="set" id="employee.type2" name="employee.type"
							ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label></td>
						<td class="formth" width="20%" height="22" valign="top"><label
							class="set" id="designation2" name="designation"
							ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
						</td>
						<td class="formth" width="15%" height="22" valign="top"><label
							class="set" id="policy1" name="policy"
							ondblclick="callShowDiv(this);"><%=label.get("policy")%></label></td>
						<td class="formth" width="5%" height="22" valign="top">Edit</td>
						<td class="formth" width="5%" height="22" valign="top">Delete</td>
					</tr>
					<s:if test="noData">
						<tr>
							<td width="100%" colspan="7" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
					</s:if>
					<%
					int l = 1;
					%>
					<s:iterator value="tableList" status="stat">
						<tr>
							<td width="5%" class="sortableTD"><%=l%></td>
							<td width="15%" class="sortableTD"><s:property
								value="divisionNameItr" /><s:hidden name="divisionNameItr" /><s:hidden
								name="divisionCodeItr" /></td>
							<td width="15%" class="sortableTD"><s:property
								value="deptNameItr" /><s:hidden name="deptNameItr" /><s:hidden
								name="deptCodeItr" /></td>
							<td width="20%" class="sortableTD"><s:property
								value="branchNameItr" /><s:hidden name="branchNameItr" /> <s:hidden
								name="branchCodeItr" /></td>
							<td width="15%" class="sortableTD"><s:property
								value="empTypeNameItr" /><s:hidden name="empTypeNameItr" /><s:hidden
								name="empTypeCodeItr" /></td>
							<td width="20%" class="sortableTD"><s:property
								value="desgNameItr" /><s:hidden name="desgCodeItr" /></td>
							<s:hidden name="desgNameItr" />
							<td width="15%" class="sortableTD"><s:property
								value="policyNameItr" /><s:hidden name="policyCodeItr" /></td>
							<s:hidden name="policyNameItr" />

							<td width="5%" class="sortableTD" align="center"><s:hidden
								name="settingCode" /> <input type="button" class="rowEdit"
								class="edit" title="Edit Record"
								onclick="editList('<s:property value="settingCode" />');" /></td>
							<td width="5%" class="sortableTD" align="center"><input
								type="button" class="rowDelete" class="delete"
								title="Delete Record"
								onclick="deleteList('<s:property value="settingCode" />');" /></td>
						</tr>
						<%
						l++;
						%>
					</s:iterator>
				</table>
				</td>
			</tr>
		</s:elseif>

		<s:if test="%{nextFlag}">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="3" class="text_head"><strong
							class="forminnerhead">Employee Exception List</strong></td>
					</tr>
					<tr>
						<td class="formth" width="15%" height="22" valign="top"><label
							class="set" id="srNo3" name="srNo"
							ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
						<td class="formth" width="20%" height="22" valign="top"><label
							class="set" id="division2" name="division"
							ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
						<td class="formth" width="30%" height="22" valign="top"><label
							class="set" id="employee1" name="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
						<td class="formth" width="25%" height="22" valign="top"><label
							class="set" id="policy1" name="policy"
							ondblclick="callShowDiv(this);"><%=label.get("policy")%></label></td>
						<td class="formth" width="15%" height="22" valign="top">Edit</td>
						<td class="formth" width="15%" height="22" valign="top">Delete</td>
					</tr>
					<s:if test="noEmpData">
						<tr>
							<td width="100%" colspan="5" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
					</s:if>
					<%
					int i = 1;
					%>
					<s:iterator value="employeeList" status="stat">
						<tr>
							<td width="5%" class="sortableTD"><%=i%></td>
							<td width="20%" class="sortableTD"><s:property
								value="divisionNameItt" /><s:hidden name="divisionNameItt" /><s:hidden
								name="divisionCodeItt" /></td>
							<td width="30%" class="sortableTD"><s:property
								value="employeeNameItr" /><s:hidden name="employeeNameItr" /><s:hidden
								name="employeeCodeItr" /></td>

							<td width="25%" class="sortableTD"><s:property
								value="policyNameItt" /><s:hidden name="policyCodeItt" /></td>
							<s:hidden name="policyNameItt" />

							<td width="10%" class="sortableTD" align="center"><input
								type="button" class="rowEdit" class="edit" title="Edit Record"
								onclick="editEmployeeList('<s:property value="settingCodeItt" />');" /><s:hidden
								name="settingCodeItt" /></td>
							<td width="10%" class="sortableTD" align="center"><input
								type="button" class="rowDelete" class="delete"
								title="Delete Record"
								onclick="deleteList('<s:property value="settingCodeItt" />');" /></td>
						</tr>
						<%
						i++;
						%>
					</s:iterator>
				</table>
				</td>
			</tr>
		</s:if>
		<s:elseif test="%{newExceptionFlag}">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="3" class="text_head"><strong
							class="forminnerhead">Employee Exception List</strong></td>
					</tr>
					<tr>
						<td class="formth" width="15%" height="22" valign="top"><label
							class="set" id="srNo2" name="srNo"
							ondblclick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
						<td class="formth" width="20%" height="22" valign="top"><label
							class="set" id="division3" name="division"
							ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
						<td class="formth" width="30%" height="22" valign="top"><label
							class="set" id="employee2" name="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
						<td class="formth" width="25%" height="22" valign="top"><label
							class="set" id="policy2" name="policy"
							ondblclick="callShowDiv(this);"><%=label.get("policy")%></label></td>
						<td class="formth" width="15%" height="22" valign="top">Edit</td>
						<td class="formth" width="15%" height="22" valign="top">Delete</td>
					</tr>
					<s:if test="noEmpData">
						<tr>
							<td width="100%" colspan="5" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
					</s:if>
					<%
					int m = 1;
					%>
					<s:iterator value="employeeList" status="stat">
						<tr>
							<td width="5%" class="sortableTD"><%=m%></td>
							<td width="20%" class="sortableTD"><s:property
								value="divisionNameItt" /><s:hidden name="divisionNameItt" /><s:hidden
								name="divisionCodeItt" /></td>
							<td width="30%" class="sortableTD"><s:property
								value="employeeNameItr" /><s:hidden name="employeeNameItr" /><s:hidden
								name="employeeCodeItr" /></td>

							<td width="25%" class="sortableTD"><s:property
								value="policyNameItt" /><s:hidden name="policyCodeItt" /></td>
							<s:hidden name="policyNameItt" />

							<td width="10%" class="sortableTD" align="center"><input
								type="button" class="rowEdit" class="edit" title="Edit Record"
								onclick="editEmployeeList('<s:property value="settingCodeItt" />');" /><s:hidden
								name="settingCodeItt" /></td>
							<td width="10%" class="sortableTD" align="center"><input
								type="button" class="rowDelete" class="delete"
								title="Delete Record"
								onclick="deleteList('<s:property value="settingCodeItt" />');" /></td>
						</tr>
						<%
						m++;
						%>
					</s:iterator>
				</table>
				</td>
			</tr>
		</s:elseif>

	</table>
</s:form>

<script>
	function nextFun(){
		var divCode = document.getElementById('paraFrm_divisionCode').value;
		document.getElementById('paraFrm_hiddenDivCode').value = divCode;
		if(document.getElementById('paraFrm_divisionName').value==""){
			alert('Please select '+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		document.getElementById("paraFrm").action="LeavePolicySetting_next.action";
		document.getElementById("paraFrm").submit();
	}
	
	function addnewpolicyFun(){
		var divCode = document.getElementById('paraFrm_divisionCode').value;
		if(document.getElementById('paraFrm_hiddenDivCode').value=="")
			document.getElementById('paraFrm_hiddenDivCode').value = divCode;
		if(document.getElementById('paraFrm_divisionName').value==""){
			alert('Please select '+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		document.getElementById("paraFrm").action="LeavePolicySetting_addNewPolicy.action";
		document.getElementById("paraFrm").submit();
	}
	
	function addexceptionsFun(){
		var divCode = document.getElementById('paraFrm_divisionCode').value;
		if(document.getElementById('paraFrm_hiddenDivCode').value=="")
			document.getElementById('paraFrm_hiddenDivCode').value = divCode;
		if(document.getElementById('paraFrm_divisionName').value==""){
			alert('Please select '+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		document.getElementById("paraFrm").action="LeavePolicySetting_addExceptions.action";
		document.getElementById("paraFrm").submit();
	}
	
	function saveFun(){
		if(document.getElementById('paraFrm_newExceptionFlag').value=="true"){
			if(document.getElementById('paraFrm_employeeName').value==""){
				alert('Please select '+document.getElementById('employee').innerHTML.toLowerCase());
				return false;
			}
		}
		if(document.getElementById('paraFrm_policyName').value==""){
			alert('Please select '+document.getElementById('policy').innerHTML.toLowerCase());
			return false;
		}
		document.getElementById("paraFrm").action="LeavePolicySetting_assignPolicy.action";
		document.getElementById("paraFrm").submit();
	}
	
	function cancelFun(){
		if(document.getElementById('paraFrm_policyCancel').value=="true"){
			document.getElementById('paraFrm').action="LeavePolicySetting_cancelFirst.action";
			document.getElementById('paraFrm').submit();
		}else if(document.getElementById('paraFrm_excepCancel').value=="true"){	
			document.getElementById('paraFrm').action="LeavePolicySetting_cancelSec.action";	
			document.getElementById('paraFrm').submit();
		}
	}
	
	function resetFun(){
		if(document.getElementById('paraFrm_policyCancel').value=="true"){
			document.getElementById('paraFrm').action="LeavePolicySetting_resetFilters.action";
			document.getElementById('paraFrm').submit();
		}else if(document.getElementById('paraFrm_excepCancel').value=="true"){	
			document.getElementById('paraFrm').action="LeavePolicySetting_resetExceptions.action";	
			document.getElementById('paraFrm').submit();
		}
	}

	function callDivision(){
		callsF9(500,325,'LeavePolicySetting_f9division.action');
	}
	
	function callEmpType(){
		callsF9(500,325,'LeavePolicySetting_f9empType.action');
	}
	
	function callDept(){
		callsF9(500,325,'LeavePolicySetting_f9dept.action');
	}
	
	function callBranch(){
		callsF9(500,325,'LeavePolicySetting_f9branch.action');
	}
	
	function callDesignation(){
		callsF9(500,325,'LeavePolicySetting_f9designation.action');
	}
	
	function callEmployee(){
		callsF9(500,325,'LeavePolicySetting_f9employee.action');
	}
	
	function callPolicy()
	{
		callsF9(500,325,'LeavePolicySetting_f9policy.action');
	}
	
	function assignPolicy()
	{
		if(document.getElementById('paraFrm_divisionName').value==""){
			alert('Please select '+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		if(document.getElementById('paraFrm_policyName').value==""){
			alert('Please select '+document.getElementById('policy').innerHTML.toLowerCase());
			return false;
		}
	}
	
	function editList(code) {
		document.getElementById('paraFrm_hiddenCode').value = code;
		document.getElementById("paraFrm").action="LeavePolicySetting_editList.action";
		document.getElementById("paraFrm").submit();
	}
	
	function deleteList(code) {
		var conf=confirm("Do you really want to delete this record ?");
  		if(conf){
			document.getElementById('paraFrm_hiddenCode').value = code;
			document.getElementById("paraFrm").action="LeavePolicySetting_deleteList.action";
			document.getElementById("paraFrm").submit();
			return true;
 			}
  		else{
  			 return false;
  		}
	}
	
	function editEmployeeList(code) {
		document.getElementById('paraFrm_hiddenCode').value = code;
		document.getElementById("paraFrm").action="LeavePolicySetting_editEmployeeList.action";
		document.getElementById("paraFrm").submit();
	}
</script>