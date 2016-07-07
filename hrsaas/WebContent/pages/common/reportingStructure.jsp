<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<%@page import="org.paradyne.bean.common.ReportingStructure"%>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="ReportingStr" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">
		<tr>
	        <td colspan="3" width="100%">
	        	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        		<tr>
				        <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt"><strong class="formhead"> Reporting
			Structure For <%
          			String structureType = (String) request
          			.getAttribute("structureType");
          	out.println(structureType);
          %> </strong></td>
          <td width="3%" valign="top" class="txt">&nbsp;</td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				    </tr>
	        	</table>
	       </td>
        </tr>
        		
		<tr>
			<td width="100%" colspan="3"><s:hidden name="ReportingStr.reportingType" />
				<input type="button" name="Back" value="   Back   " class="token" theme="simple" onclick="return validateBack();"/>
				<s:if test="%{insertFlag}">
		  			<s:submit cssClass="add"   action="ReportingStr_saveReportingStructure" theme="simple"  value="   Save"
					  		onclick="return validateSave();"/>
		  		</s:if>
		  		<s:submit cssClass="reset"   action="ReportingStr_reset" theme="simple"   value="    Reset" />
		  		<s:if test="%{deleteFlag}">	
				  	<!--<s:submit cssClass="delete"   theme="simple"   value="    Delete" action="ReportingStr_deleteRecord"
					  	onclick="return validateDelete();"/>
				--></s:if>
		  		<s:if test="%{viewFlag}">
					<input type="button" class="token" value="Report"
						onclick="return report();" />
				</s:if></td>
		</tr>

		<tr>
			<td width="100%" colspan="6">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="6" class="formhead"><strong
						class="forminnerhead">Select one or many from below
					options</strong></td>
				</tr>

				<tr>
					<td width="100%" colspan="6">
					<div id="depDiv">
					<table>
						<tr>
							<s:hidden theme="simple" name="ReportingStr.hdrCode" />
							<td colspan="1" width="22%" class="formtext"><label class="set"
								id="department" name="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td colspan="3" width="17%">&nbsp;<s:hidden theme="simple"
								name="ReportingStr.deptCode" /> <s:textfield theme="simple"
								name="ReportingStr.reqDept" size="28" maxlength="30"
								readonly="true" /></td> 
							<td colspan="2" width="69%"><img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="18" theme="simple"
								onclick="checkDepartment();"></td>
						</tr>

						<tr>
							<td colspan="1" width="22%" class="formtext"><label class="set" id="branch"
								name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td colspan="3" width="17%">&nbsp;<s:hidden theme="simple"
								name="ReportingStr.brnCode" /> <s:textfield theme="simple"
								name="ReportingStr.reqBrn" size="28" maxlength="30"
								readonly="true" /></td> 
							<td colspan="2" width="69%"><img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="18" theme="simple"
								onclick="checkBranch();"></td>
						</tr>

						<tr>
							<td colspan="1" width="22%" class="formtext"><label class="set"
								id="designation" name="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td colspan="3" width="17%">&nbsp;<s:hidden theme="simple"
								name="ReportingStr.designationCode" /> <s:textfield
								theme="simple" name="ReportingStr.designationName" size="28"
								maxlength="30" readonly="true" /></td> 
							<td colspan="2" width="69%"><img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="18" theme="simple"
								onclick="checkDesignation();"></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>

				<s:hidden name="structureType"></s:hidden>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="3" class="formhead"><strong
						class="forminnerhead"> To define hierarchy for a
					particular employee, please select employee below</strong></td>
				</tr>
				<tr>
					<td colspan="1" width="20%" class="formtext"><label class="set"
						id="reporting.employee.name" name="reporting.employee.name"
						ondblclick="callShowDiv(this);"><%=label.get("reporting.employee.name")%></label>
					:</td>
					<td colspan="1" width="11%"><s:hidden
						theme="simple" name="ReportingStr.empCode" /> <s:textfield
						theme="simple" name="ReportingStr.empTokenNo" size="10"
						readonly="true" /></td> 
					<td colspan="1" width="69%"><s:textfield theme="simple"
						name="ReportingStr.empName" size="50" maxlength="30"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="18" theme="simple"
						onclick="checkEmployee();"></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td width="100%" colspan="3" class="formhead"><strong
						class="forminnerhead">Select Reporting Structure</strong></td>
				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext"><label class="set"
						id="reporting.appr.name0" name="reporting.appr.name"
						ondblclick="callShowDiv(this);"><%=label.get("reporting.appr.name")%></label>
					:</td>
					<td colspan="1" width="11%"><s:hidden theme="simple"
						name="ReportingStr.empId" /> <s:textfield theme="simple"
						name="ReportingStr.empTokenAdd" size="10" readonly="true" /> <s:hidden
						theme="simple" name="ReportingStr.desgId" /> <s:hidden
						theme="simple" name="ReportingStr.desgName" /></td>
					<td colspan="1" width="69%"><s:textfield
						theme="simple" name="ReportingStr.empNameAdd" size="50"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="18" theme="simple"
						onclick="javascript:callsF9(500,325,'ReportingStr_f9SelectempAdd.action'); ">&nbsp;&nbsp;
					</td>
				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext"><label class="set"
						id="reporting.alter.appr.name0" name="reporting.alter.appr.name"
						ondblclick="callShowDiv(this);"><%=label.get("reporting.alter.appr.name")%></label>
					:</td>
					<td colspan="1" width="11%"><s:hidden theme="simple"
						name="alternateEmpId" /> <s:textfield theme="simple"
						name="alternateEmpToken" size="10" readonly="true" /> </td>
					<td colspan="1" width="69%"><s:textfield
						theme="simple" name="alternateEmpName" size="50" readonly="true" />
					<img src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="18" theme="simple"
						onclick="javascript:callsF9(500,325,'ReportingStr_f9SelectAlternateApprover.action'); ">&nbsp;&nbsp;
					<s:submit cssClass="token" action="ReportingStr_addApprover"
						theme="simple" value="   Add   " onclick="return checkApprover();" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
		<td width="100%" colspan="6">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
				<tr>
					<td class="formth" width="6%" height="22" valign="top"><label name="reporting.srno" class = "set"  id="reporting.srno" ondblclick="callShowDiv(this);"><%=label.get("reporting.srno")%></label></td>
					<td class="formth" width="9%" height="22" valign="top"><label name="reporting.appr.id" class = "set"  id="reporting.appr.id" ondblclick="callShowDiv(this);"><%=label.get("reporting.appr.id")%></label></td>
					<td class="formth" width="19%" height="22" valign="top"><label name="reporting.appr.name" class = "set"  id="reporting.appr.name" ondblclick="callShowDiv(this);"><%=label.get("reporting.appr.name")%></label></td>
					<td class="formth" width="15%" height="22" valign="top"><label name="reporting.alter.appr.id" class = "set"  id="reporting.alter.appr.id" ondblclick="callShowDiv(this);"><%=label.get("reporting.alter.appr.id")%></label></td>
					<td class="formth" width="19%" height="22" valign="top"><label name="reporting.alter.appr.name" class = "set"  id="reporting.alter.appr.name" ondblclick="callShowDiv(this);"><%=label.get("reporting.alter.appr.name")%></label></td></td>
					<td class="formth" width="6%" height="22" valign="top"><label name="reporting.edit" class = "set"  id="reporting.edit" ondblclick="callShowDiv(this);"><%=label.get("reporting.edit")%></label></td></td>
					<td class="formth" width="7%" height="22" valign="top"><label name="reporting.delete" class = "set"  id="reporting.delete" ondblclick="callShowDiv(this);"><%=label.get("reporting.delete")%></label></td></td>
					<td class="formth" width="10%" height="22" valign="top"><label name="reporting.priority" class = "set"  id="reporting.priority" ondblclick="callShowDiv(this);"><%=label.get("reporting.priority")%></label></td></td>
				</tr>
		
				<%
							int i = 1;
							int count = 0;
						%>
				<s:iterator value="empList" status="stat">
					<tr>
						<s:hidden name="empIdIterator" id="<%= "empIdIterator"+i %>"/>
						<s:hidden name="empTokenIterator" id="<%= "empTokenIterator"+i %>" />
						<s:hidden name="empNameIterator" id="<%= "empNameIterator"+i %>"/>
						<s:hidden name="desgIdIterator" id="<%= "desgIdIterator"+i %>"/>
						<s:hidden name="desgNameIterator" id="<%= "desgNameIterator"+i %>"/>
						<s:hidden name="srNoIterator" id="<%= "srNoIterator"+i %>"/>
						<s:hidden name="alternateEmpIdIterator" id="<%= "alternateEmpIdIterator"+i %>"/>
						<s:hidden name="alternateEmpTokenIterator" id="<%= "alternateEmpTokenIterator"+i %>" />
						<s:hidden name="alternateEmpNameIterator" id="<%= "alternateEmpNameIterator"+i %>"/>
						
						<td width="6%" class="sortableTD"><s:property value="srNoIterator"/>&nbsp;</td>
						<td width="9%" class="sortableTD"><s:property value="empTokenIterator"/>&nbsp;</td>
						<td width="19%" class="sortableTD"><s:property value="empNameIterator"/>&nbsp;</td>
						<td width="15%" class="sortableTD"><s:property value="alternateEmpTokenIterator"/>&nbsp;</td>
						<td width="19%" class="sortableTD"><s:property value="alternateEmpNameIterator"/>&nbsp;</td>
						<td width="6%" class="sortableTD">
							<input type="button" class="edit" value="   Edit" align="bottom"
								onclick="editApprover(<%=i %>, '<s:property value="empIdIterator"/>', '<s:property value="empTokenIterator"/>',
									'<s:property value="empNameIterator"/>', '<s:property value="alternateEmpIdIterator"/>', 
									'<s:property value="alternateEmpTokenIterator"/>', '<s:property value="alternateEmpNameIterator"/>')"/>&nbsp;</td>
						<td width="7%" class="sortableTD"><input
							type="button" class="delete" value="   Delete" align="bottom"
							onclick="deleteApprover(<%=i %>, '<s:property value="empIdIterator"/>')"/>&nbsp;</td>
						<td width="10%" class="sortableTD"><input type="button"  class="shuffleUp"  onclick="shuffleColumns(<%=i %>, 'upWard')"/>
							<input type="button"  class="shuffleDown"  onclick="shuffleColumns(<%=i %>, 'downWard')"/>&nbsp;</td>
						
					</tr>
					<%
						i++;
						count++;
					%>
				</s:iterator>
				<s:hidden name="ReportingStr.Structure"/>
				<s:hidden name="srNo"/>
				<input type="hidden" name="rowNum" id="rowNum" value="<%=count %>"/>
			</table>
		</td>
	</tr>
	</table>
</s:form>
<script type="text/javascript">
function report(){
	var structureFor = document.getElementById('paraFrm_ReportingStr_reportingType').value;
	callReport('ReportingStr_report.action?reportType=R&structureKey='+structureFor)
}

function validateSave(){
	if(!validateApproverDetails())return false;
	
	if(document.getElementById('rowNum').value == 0){
		alert("please add atleast one "+document.getElementById("reporting.appr.name").innerHTML.toLowerCase());
		//alert('please add atleast one approver name');
		return false;
	}
	return true;
}

function validateApproverDetails(){
	if(document.getElementById('paraFrm_ReportingStr_reqDept').value=="" && document.getElementById('paraFrm_ReportingStr_reqBrn').value==""
		&& document.getElementById('paraFrm_ReportingStr_designationName').value=="" && document.getElementById('paraFrm_ReportingStr_empName').value==""){
			alert("Please select either "+
			document.getElementById("department").innerHTML.toLowerCase()
			+" or "+
			document.getElementById("branch").innerHTML.toLowerCase()
			+" or "+
			document.getElementById("designation").innerHTML.toLowerCase()
			+" or "+
			document.getElementById("reporting.employee.name").innerHTML.toLowerCase()
			+"");
			document.getElementById('paraFrm_ReportingStr_reqDept').focus();
			return false;
	}
	return true;
}

function validateDelete(){
	if(!validateApproverDetails())return false;
}

function selectChekHead(){
	var checkDefault = document.getElementById('defaultCheck').checked;
	var checkSameas  = document.getElementById('sameAsCheck').checked;
			
	if(checkSameas){
		alert("This structure is same as "+document.getElementById('paraFrm_sameStr').value +" so can not be changed");
		return false;
	}
	return true;
}
function checkApprover(){
	var fieldName = ["paraFrm_ReportingStr_empNameAdd"];
	var labelName = ["reporting.appr.name"];
	var flag	  = ["select"];
	
	if(!validateBlank(fieldName, labelName, flag))return false;
	return true;
}

function deleteApprover(srNo, id){
	document.getElementById('paraFrm_srNo').value = srNo;
	
	 var conf=confirm("Do you really want to delete this record ?");
  		if(conf){
  			document.getElementById("paraFrm").action="ReportingStr_deleteApprover.action";
			document.getElementById('paraFrm_ReportingStr_empId').value=id;
		    document.getElementById("paraFrm").submit();
  			return true;
  		}
	  	else{
	  		 return false;
	  	}
	    return true;
}

function editApprover(srNo, approverId, approverToken, approverName, alternatAppId, alternateAppToken, alternateAppName){
	document.getElementById('paraFrm_srNo').value = srNo;
	document.getElementById('paraFrm_ReportingStr_empId').value = approverId;
	document.getElementById('paraFrm_ReportingStr_empTokenAdd').value = approverToken;
	document.getElementById('paraFrm_ReportingStr_empNameAdd').value = approverName;
	document.getElementById('paraFrm_alternateEmpId').value = alternatAppId;
	document.getElementById('paraFrm_alternateEmpToken').value = alternateAppToken;
	document.getElementById('paraFrm_alternateEmpName').value = alternateAppName;
}

function shuffleColumns(code, buttonType){
	document.getElementById('paraFrm_srNo').value = code;
	
	if(buttonType == 'upWard'){
		document.getElementById("paraFrm").action = "ReportingStr_shuffleColumnsAction.action?type=up";
		document.getElementById("paraFrm").submit();
	}
	
	if(buttonType == 'downWard'){
		document.getElementById("paraFrm").action = "ReportingStr_shuffleColumnsAction.action?type=down";
		document.getElementById("paraFrm").submit();
	}
}

function validateBack(){
	var reportingType = document.getElementById('paraFrm_ReportingStr_reportingType').value;
	
	document.getElementById("paraFrm").action = "ReportingStr_goToDefaultPage.action?structureKey="+reportingType;
	document.getElementById("paraFrm").submit();
}

function checkDepartment(){
	document.getElementById("paraFrm_ReportingStr_empCode").value = "";
	document.getElementById("paraFrm_ReportingStr_empTokenNo").value = "";
	document.getElementById("paraFrm_ReportingStr_empName").value = "";
	
	javascript:callsF9(500,325,'ReportingStr_f9Department.action');
}

function checkBranch(){
	document.getElementById("paraFrm_ReportingStr_empCode").value = "";
	document.getElementById("paraFrm_ReportingStr_empTokenNo").value = "";
	document.getElementById("paraFrm_ReportingStr_empName").value = "";
	
	javascript:callsF9(500,325,'ReportingStr_f9Branch.action');
}

function checkDesignation(){
	document.getElementById("paraFrm_ReportingStr_empCode").value = "";
	document.getElementById("paraFrm_ReportingStr_empTokenNo").value = "";
	document.getElementById("paraFrm_ReportingStr_empName").value = "";
	
	javascript:callsF9(500,325,'ReportingStr_f9Designation.action');
}

function checkEmployee(){
	document.getElementById("paraFrm_ReportingStr_deptCode").value = "";
	document.getElementById("paraFrm_ReportingStr_reqDept").value = "";
	
	document.getElementById("paraFrm_ReportingStr_brnCode").value = "";
	document.getElementById("paraFrm_ReportingStr_reqBrn").value = "";
	
	document.getElementById("paraFrm_ReportingStr_designationCode").value = "";
	document.getElementById("paraFrm_ReportingStr_designationName").value = "";
	
	javascript:callsF9(500,325,'ReportingStr_f9Selectemp.action'); 
}
</script>

