<%@page import="org.paradyne.lib.Utility"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="RehireProcess" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Rehire
					Process</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

				<tr>
					<td></td>
					<td width="22%">
					<div align="right"><font color="red">*</font>Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Select filters to rehire
							employees </strong></td>
						</tr>
						<tr>
							<td width="25%" id='TD1'><label class="set"
								name="pfparam.applDiv" id="pfparam.applDiv"
								ondblclick="callShowDiv(this);"><%=label.get("pfparam.applDiv")%></label>
							:</td>
							<td width="25%" id='TD2' nowrap="nowrap"><s:hidden
								name="applDivisionCode"></s:hidden> <s:textarea
								name="applDivisionName" cols="25" rows="2" readonly="true"></s:textarea>
							<img src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'RehireProcess_f9applDiv.action'); "></td>
							<td width="25%"><label class="set"
								name="pfparam.applBranches" id="pfparam.applBranches"
								ondblclick="callShowDiv(this);">&nbsp;<%=label.get("pfparam.applBranches")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:hidden
								name="applBranchCode"></s:hidden> <s:textarea
								name="applBranchName" cols="25" rows="2" readonly="true"></s:textarea>
							<img src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'RehireProcess_f9applBranch.action'); ">
							</td>
						</tr>
						<tr>
							
							<td width="25%"><label class="set" name="pfparam.applDept"
								id="pfparam.applDept" ondblclick="callShowDiv(this);"><%=label.get("pfparam.applDept")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:hidden
								name="applDeptCode"></s:hidden> <s:textarea name="applDeptName"
								cols="25" rows="2" readonly="true"></s:textarea> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'RehireProcess_f9applDept.action'); ">

							</td>
							<td width="25%"><label class="set" name="pfparam.applDesg"
								id="pfparam.applDesg" ondblclick="callShowDiv(this);">&nbsp;<%=label.get("pfparam.applDesg")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:hidden
								name="applDesgCode"></s:hidden> <s:textarea name="applDesgName"
								cols="25" rows="2" readonly="true"></s:textarea> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'RehireProcess_f9applDesg.action'); ">

							</td>
						</tr>
						<tr>
							
							<td width="25%"><label class="set" name="pfparam.applEmp"
								id="pfparam.applEmp" ondblclick="callShowDiv(this);"><%=label.get("pfparam.applEmp")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:hidden name="applEmpCode"></s:hidden>
							<s:textarea name="applEmpName" cols="25" rows="2" readonly="true"></s:textarea>
							<img src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'RehireProcess_f9applEmp.action'); ">
							</td>
							<td width="25%">&nbsp;<label class="set" name="applEmpType"
								id="applEmpType" ondblclick="callShowDiv(this);"><%=label.get("applEmpType")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:hidden name="applEmpTypeCode"></s:hidden>
							<s:textarea name="applEmpTypeName" cols="25" rows="2" readonly="true"></s:textarea>
							<img src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'RehireProcess_f9applEmpType.action'); ">
							</td>

						</tr>
						<tr>
							<td colspan="4" align="center"><input type="button"
								class="token" value='View Employee List'
								onclick="callViewEmp();" />
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="viewEmpList">
			<tr>
				<td align="left"><input type="button" class="token"
					value='Rehire Process' onclick="callRehireProcess();" /></td>
			</tr>
			<tr>
				<td colspan="2">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td >
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="2">
							<tr>
							<td width="10%" nowrap="nowrap" valign="top" class="formth">Sr No.</td>
								<td width="10%" nowrap="nowrap" valign="top" class="formth"><label
									class="set" id="employee.id" name="employee.id"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
								<td width="15%" nowrap="nowrap" valign="top" class="formth"><label
									class="set" id="employee" name="employee"
									ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
								<td width="15%" nowrap="nowrap" valign="top" class="formth"><label
									class="set" id="doj" name="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label></td>
								<td width="15%" nowrap="nowrap" valign="top" class="formth"><label
									class="set" id="dol" name="dol" ondblclick="callShowDiv(this);"><%=label.get("dol")%></label></td>
								<td width="15%" nowrap="nowrap" valign="top" class="formth"><label
									class="set" id="employee.type" name="employee.type"
									ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label></td>
								<td width="15%" nowrap="nowrap" valign="top" class="formth"><s:checkbox
										name="checkAll" id="checkAll" onclick="return checkedboxAll();" /></td>
							</tr>
							<s:if test="noData">
								<tr>
									<td width="100%" colspan="6" align="center"><font
										color="red">There is no data to display</font></td>
								</tr>
							</s:if>
							<%
								int i = 0;
								int s = 1;
							%>
							<%!int p = 0, t = 0;%>
							<s:iterator value="employeeList">
								<tr class="sortableTD">
									<td class="sortableTD" width="10%"><%=i+1 %></td>
									<td class="sortableTD" width="10%"><input type="hidden"
										name="listEmpId" id="listEmpId<%=i%>"
										value="<s:property value="listEmpId" />" /><s:property
										value="listEmpToken" /><input type="hidden"
										name="listEmpToken" id="listEmpToken<%=i%>"
										value="<s:property value="listEmpToken" />" /></td>
									<td class="sortableTD" width="25%"><s:property
										value="listEmpName" /><input type="hidden" name="listEmpName"
										id="listEmpName<%=i%>"
										value="<s:property value="listEmpName" />" /></td>
									<td class="sortableTD" width="15%"><s:property
										value="listDoj" /><input type="hidden" size="10"
										name="listDoj" id="listDoj<%=i%>" readonly="true"
										value="<s:property value="listDoj" />" /></td>
									<td class="sortableTD" width="15%"><s:property
										value="listDol" /><input type="hidden" size="10"
										name="listDol" id="listDol<%=i%>"
										value="<s:property value="listDol" />" /></td>
									<td class="sortableTD" width="15%"><s:property
										value="listEmpType" /><input type="hidden" size="10"
										name="listEmpType" id="listEmpType<%=i%>"
										value="<s:property value="listEmpType" />" /></td>
									<td class="sortabletd" width="10%" align="center"><s:checkbox
										name="check" id="<%="check"+i %>"
										onclick="return checkedbox('<%=i%>');" /></td>
								</tr>
								<%
									i++;
									s++;
									p++;
								%>
							</s:iterator>
							<%
								t = p;
								p = 0;
							%>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td align="left"><input type="button" class="token"
					value='Rehire Process' onclick="callRehireProcess();" /></td>
				<td align="right">
					Total No. Records : <%=i %>
				</td>
			</tr>
		</s:if>
		<s:else></s:else>
	</table>
</s:form>
<script>
function callViewEmp(){
 	document.getElementById('paraFrm').action ="RehireProcess_getEmployeeList.action";
 	document.getElementById("paraFrm").target="main";
   	document.getElementById("paraFrm").submit();
}
function callRehireProcess(){
try{
	var totalRows = <%=t%>;
	var count = 0;
	var employeeIds = "";
	for(var i=0;i<totalRows;i++){
		if(document.getElementById('check'+i).checked == true){
			employeeIds += document.getElementById('listEmpId'+i).value+",";
			count++;
		} //end of if
	} //end of loop
	if(count == 0){
		alert('Please select atleast one record');
		return false;
	} //end of if 
	else{
		employeeIds = employeeIds.substring(0,employeeIds.length-1);
	} //end of else
 	document.getElementById('paraFrm').action ='RehireProcess_rehireProcess.action?employeeIds='+employeeIds;
 	document.getElementById("paraFrm").target="main";
   	document.getElementById("paraFrm").submit();
   	}catch(e){alert(e);}
} //end of callRehireProcess function

function checkedboxAll(){
	try{
		var totalRows = <%=t%>;
		for(var i=0;i<totalRows;i++){
			if(document.getElementById('checkAll').checked == true){
				document.getElementById('check'+i).checked = true;
			} //end of if
			else{
				document.getElementById('check'+i).checked = false;
			} //end of else
		} //end of loop
	}catch(e){alert(e);}
} //end of checkedboxAll function
</script>