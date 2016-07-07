
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="ReportingStrNew" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">
	
	<s:hidden name="defineSelectSource"/> 

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reporting
					  Structure </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	
	<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="2">
					
					<s:submit name="saveButton" value="Save" cssClass="add"
					onclick="return callSave();"
					 action="ReportingStrNew_saveManager" /> 
					 
					 	<s:submit name="backBtn" value="Back" cssClass="back" action="ReportingStrNew_back"/> 
					 	
					</td>
					<td width="22%">
					</td>
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
							name="mdoule.name" id="mdoule.name2"
							ondblclick="callShowDiv(this);"><%=label.get("mdoule.name")%></label><font
							color="red">*</font> :</td>
						<td height="27"><s:property value="moduleNameApprover"/> 
						<s:hidden name="divCodeApprover"/>
						<s:hidden name="moduleAbbrApprover"/>
						<s:hidden name="moduleNameApprover"/>
							</td>
					</tr>
					
					
					<tr>
						<td width="20%" colspan="1" height="27"><label class="set"
							name="group.name" id="group.name1"
							ondblclick="callShowDiv(this);"><%=label.get("group.name")%></label><font
							color="red">*</font> :</td>
						<td height="27"><s:property value="groupNameApprover"/> 
						
						<s:hidden name="groupCodeApprover"/>
							</td>
					</tr>

					<tr>
						<td width="20%" colspan="1" height="27"><label class="set"
							name="select.manager1" id="select.manager1"
							ondblclick="callShowDiv(this);"><%=label.get("select.manager1")%></label><font
							color="red">*</font> :</td>
						<td height="27"><s:textfield name="manager1EmpToken" readonly="true"/> <s:textfield readonly="true"
							name="manager1EmpName" /> <s:hidden name="manager1EmpCode" />
							
							<img src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:f9selectEmp('manager1EmpToken','manager1EmpName','manager1EmpCode' );" />
								
							</td>
					</tr>

					<tr>
						<td width="20%" colspan="1" height="27"><label class="set"
							name="alter.approver" id="alter.approver"
							ondblclick="callShowDiv(this);"><%=label.get("alter.approver")%></label> :</td>
						<td height="27"><s:textfield name="manager1EmpTokenAlter"  readonly="true"/>
						<s:textfield name="manager1EmpNameAlter"  readonly="true"/> <s:hidden
							name="manager1EmpCodeAlter" />
							
								<img src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'ReportingStrNew_f9selectEmpAlterApprover1.action');"  />
								
							
							</td>
					</tr>

					<tr>
						<td width="20%" colspan="1" height="27"><label class="set"
							name="select.manager2" id="select.manager2"
							ondblclick="callShowDiv(this);"><%=label.get("select.manager2")%></label> :</td>
						<td height="27"><s:textfield name="manager2EmpToken" readonly="true"/> <s:textfield readonly="true"
							name="manager2EmpName" /> <s:hidden name="manager2EmpCode" />
							
							<img src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'ReportingStrNew_f9selectEmpManager2.action');" />
								
							
							</td>
					</tr>

					<tr>
						<td width="20%" colspan="1" height="27"><label class="set"
							name="alter.approver" id="alter.approver2"
							ondblclick="callShowDiv(this);"><%=label.get("alter.approver")%></label> :</td>
						<td height="27"><s:textfield name="manager2EmpTokenAlter"  readonly="true"/>
						<s:textfield name="manager2EmpNameAlter" readonly="true"/> <s:hidden
							name="manager2EmpCodeAlter" />
							
							<img src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'ReportingStrNew_f9selectEmpAlterApprover2.action');"  />
								
							</td>
					</tr>

					<tr>
						<td width="20%" colspan="1" height="27"><label class="set"
							name="select.manager2" id="select.manager2"
							ondblclick="callShowDiv(this);"><%=label.get("select.manager3")%></label> :</td>
						<td height="27"><s:textfield name="manager3EmpToken" readonly="true"/> <s:textfield
							name="manager3EmpName" readonly="true"/> <s:hidden name="manager3EmpCode" />
							
							
							<img src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'ReportingStrNew_f9selectEmpManager3.action');" />
								
							</td>
					</tr>

					<tr>
						<td width="20%" colspan="1" height="27"><label class="set"
							name="alter.approver" id="alter.approver3"
							ondblclick="callShowDiv(this);"><%=label.get("alter.approver")%></label> :</td>
						<td height="27"><s:textfield name="manager3EmpTokenAlter" readonly="true"/>
						<s:textfield name="manager3EmpNameAlter" readonly="true"/> <s:hidden
							name="manager3EmpCodeAlter" />
							
							<img src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage"
								onclick="javascript:callsF9(500,325,'ReportingStrNew_f9selectEmpAlter3.action');"/>
							
							</td>
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
					<td width="78%" colspan="2">
					
				<s:submit name="saveButton" value="Save" cssClass="add"
					onclick="return callSave();"
					 action="ReportingStrNew_saveManager" /> 
					 
					 	<s:submit name="backBtn" value="Back" cssClass="back" action="ReportingStrNew_back"/> 
					</td>
					<td width="22%">
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
	
</s:form>


<script>

function f9selectEmp()
{
callsF9(500,325,'ReportingStrNew_f9selectEmp.action');
}

function callSave()
{
var manager1EmpCode =document.getElementById('paraFrm_manager1EmpCode').value;

if(manager1EmpCode=="")
{
	alert("Please select manager1");
	return false;
}

return true;
}

</script>