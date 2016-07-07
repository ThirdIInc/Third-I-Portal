<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<%@page import="org.paradyne.bean.common.ReportingStructure"%>
<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="GoalReportingStructure" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">
		<tr>
	        <td colspan="3" width="100%">
	        	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        		<tr>
				        <td width="4%" valign="bottom" class="txt">
				        <strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt"><strong class="formhead"> Reporting
			Structure For Goal Setting
           </strong></td>
          <td width="3%" valign="top" class="txt">&nbsp;</td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				    </tr>
	        	</table>
	       </td>
        </tr>
        		
		<tr>
			<td width="100%" colspan="3"><s:hidden name="reportingType" />
				
				<s:if test="%{insertFlag}">
		  			<s:submit cssClass="add"   action="GoalReportingStructure_saveReportingStructure" theme="simple"  value="Save"
					  		onclick="return validateSave();"/>
		  		</s:if>
		  		<s:submit cssClass="reset"   action="GoalReportingStructure_reset" theme="simple"   value="Reset" />
		  		<s:if test="%{deleteFlag}">	
				  	<!--<s:submit cssClass="delete"   theme="simple"   value="    Delete" action="ReportingStr_deleteRecord"
					  	onclick="return validateDelete();"/>
				     -->
			    </s:if>
		  	<!-- Updated by Anantha Lakshmi -->
		  		<s:if test="%{viewFlag}">
					  <s:submit cssClass="report"   action="GoalReportingStructure_viewApproverListReportStructure" theme="simple"  value="Exception Report"/>
				</s:if>
				
			 </td>
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
							<s:hidden theme="simple" name="hdrCode" />
							<td colspan="1" width="22%" class="formtext"><label class="set"
								id="department" name="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td colspan="3" width="17%">&nbsp;<s:hidden theme="simple"
								name="deptCode" /> <s:textfield theme="simple"
								name="reqDept" size="28" maxlength="30"
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
								name="brnCode" /> <s:textfield theme="simple"
								name="reqBrn" size="28" maxlength="30"
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
								name="designationCode" /> <s:textfield
								theme="simple" name="designationName" size="28"
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
						theme="simple" name="empCode" /> <s:textfield
						theme="simple" name="empTokenNo" size="10"
						readonly="true" /></td> 
					<td colspan="1" width="69%"><s:textfield theme="simple"
						name="empName" size="50" maxlength="30"
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
						class="forminnerhead">Select Reporting Structure </strong></td>
				</tr>

				<tr>
					<td colspan="1" width="20%" class="formtext"><label class="set"
						id="reporting.appr.name0" name="reporting.appr.name"
						ondblclick="callShowDiv(this);"><%=label.get("reporting.appr.name")%></label>
					:</td>
					<td colspan="1" width="11%"><s:hidden theme="simple"
						name="empId" /> <s:textfield theme="simple"
						name="empTokenAdd" size="10" readonly="true" /> <s:hidden
						theme="simple" name="desgId" /> <s:hidden
						theme="simple" name="desgName" /></td>
					<td colspan="1" width="69%"><s:textfield
						theme="simple" name="empNameAdd" size="50"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="18" theme="simple"
						onclick="javascript:callsF9(500,325,'GoalReportingStructure_f9SelectempAdd.action'); ">&nbsp;&nbsp;
					</td>
				</tr>

				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
							<td colspan="1" width="20%">&nbsp;</td>
							<td colspan="1" width="30%" align="left">
								<input type="radio" id='approver'  value='A' onclick="call('Approver');"
										name="apprReviewRadio" checked="checked" align="right" /> Approver&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" id='reviewer' value='R' onclick="call('Reviewer');"
										name="apprReviewRadio" /> Reviewer		
										
							</td>	
							
							<td colspan="1" width="40%">
								<s:submit cssClass="token" action="GoalReportingStructure_addApprover"
								theme="simple" value="Add" onclick="return checkApprover();" /></td>
							</td>
					</tr></table></td>
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
					<td class="formth" width="5%" height="22" valign="top"><label name="reporting.approver" class = "set"  id="reporting.approver" ondblclick="callShowDiv(this);"><%=label.get("reporting.approver")%></label></td>
					<td class="formth" width="5%" height="22" valign="top"><label name="reporting.view" class = "set"  id="reporting.view" ondblclick="callShowDiv(this);"><%=label.get("reporting.view")%></label></td>
					<td class="formth" width="5%" height="22" valign="top"><label name="reporting.rating" class = "set"  id="reporting.rating" ondblclick="callShowDiv(this);"><%=label.get("reporting.rating")%></label></td></td>
					<td class="formth" width="5%" height="22" valign="top"><label name="reporting.comments" class = "set"  id="reporting.comments" ondblclick="callShowDiv(this);"><%=label.get("reporting.comments")%></label></td></td>
					
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
						<s:hidden name="empIdIterator" id='<%= "empIdIterator"+i %>'/>
						<s:hidden name="empTokenIterator" id='<%= "empTokenIterator"+i %>' />
						<s:hidden name="empNameIterator" id='<%= "empNameIterator"+i %>'/>
						<s:hidden name="desgIdIterator" id='<%= "desgIdIterator"+i %>'/>
						<s:hidden name="desgNameIterator" id='<%= "desgNameIterator"+i %>'/>
						<s:hidden name="srNoIterator" id='<%= "srNoIterator"+i %>'/>
						<s:hidden name="apprreviewIterator" id='<%= "apprreviewIterator"+i %>'/>
						
						<td width="6%" class="sortableTD"><s:property value="srNoIterator"/>&nbsp;</td>
						<td width="9%" class="sortableTD"><s:property value="empTokenIterator"/>&nbsp;</td>
						<td width="19%" class="sortableTD"><s:property value="empNameIterator"/>&nbsp;</td>
						<td width="19%" class="sortableTD"><s:property value="apprreviewIterator"/>&nbsp;</td>
						<td width="5%" align="center" class="sortableTD">
							<s:hidden name="viewIterator"  id='<%= "viewIterator"+i %>' />
							<input type="checkbox" name="viewIteratorChk"   id='<%= "viewIteratorChk"+i %>' onclick="setCheckbox(this,'view',<%=i%>);"/>&nbsp;</td>
						<td width="5%" align="center" class="sortableTD">
							<s:hidden name="ratingIterator"  id='<%= "ratingIterator"+i %>' />
							<input type="checkbox" name="ratingIteratorChk"  id='<%= "ratingIteratorChk"+i %>' onclick="setCheckbox(this,'rating',<%=i%>);"/>&nbsp;</td>
						<td width="5%" align="center" class="sortableTD">
							<s:hidden name="commentsIterator"  id='<%= "commentsIterator"+i %>' />
							<input type="checkbox" name="commentsIteratorChk" id='<%= "commentsIteratorChk"+i %>' onclick="setCheckbox(this,'comments',<%=i%>);"/>&nbsp;</td>
						<td width="7%" class="sortableTD">	
						<input type="button" class="edit" value="   Edit" align="bottom"
								onclick="editApprover(<%=i %>, '<s:property value="empIdIterator"/>', '<s:property value="empTokenIterator"/>',
									'<s:property value="empNameIterator"/>')"/>&nbsp;</td>
						<td width="7%" class="sortableTD"><input
							type="button" class="delete" value="   Delete" align="bottom"
							onclick="deleteApprover(<%=i %>, '<s:property value="empIdIterator"/>')"/>&nbsp;</td>
					
						<td width="10%" align="center" class="sortableTD"><input type="button"  class="shuffleUp"  onclick="shuffleColumns(<%=i %>, 'upWard')"/>
							<input type="button"  class="shuffleDown"  onclick="shuffleColumns(<%=i %>, 'downWard')"/>&nbsp;</td>
					</tr>
					<%
						i++;
						count++;
					%>
				</s:iterator><script></script>
				<tr><td colspan="10"> <b>Note : For Esclation Work Flow Please add Reviewer</b></td></tr>
				<s:hidden name="Structure"/>
				<s:hidden name="srNo"/>
				<input type="hidden" name="rowNum" id="rowNum" value="<%=count %>"/>
			</table>
		</td>
	</tr>
	</table>
	
	<s:hidden name="hiddencheck" id="hiddencheck"></s:hidden>
	
</s:form>
<script type="text/javascript">

function call(id)
{
		document.getElementById('hiddencheck').value=id;
}

document.getElementById('hiddencheck').value="Approver";
setCheckboxOnload();
function report(){
	var structureFor = document.getElementById('paraFrm_ReportingStr_reportingType').value;
	callReport('ReportingStr_report.action?reportType=R&structureKey='+structureFor)
}

function setCheckbox(obj,type,rowId){
	if(!document.getElementById('viewIteratorChk'+rowId).checked){
	document.getElementById('ratingIteratorChk'+rowId).checked=false;
	document.getElementById('commentsIteratorChk'+rowId).checked=false;
	document.getElementById('ratingIterator'+rowId).value="false";
	document.getElementById('commentsIterator'+rowId).value="false";
	}
	
		if(obj.checked){
			document.getElementById(type+'Iterator'+rowId).value = 'true';
		}else document.getElementById(type+'Iterator'+rowId).value = 'false';
	
}
function setCheckboxOnload(){
var count = document.getElementById('rowNum').value;
	for(var total=1; total<=count; total++ )
	{
			var viewBoolean=getBooleanValue(document.getElementById('viewIterator'+total).value);
			var ratingBoolean=getBooleanValue(document.getElementById('ratingIterator'+total).value);
			var commentsBoolean=getBooleanValue(document.getElementById('commentsIterator'+total).value);
			
			document.getElementById('viewIteratorChk'+total).checked = viewBoolean;
			document.getElementById('ratingIteratorChk'+total).checked = ratingBoolean;
			document.getElementById('commentsIteratorChk'+total).checked = commentsBoolean;
			
	}	
	
}
function getBooleanValue(value){
	if(value==""){
		return false;
	}else if(value=="true"){
		return true;
	}else
	return false;
}
function validateSave(){

	if(!validateApproverDetails()){
		return false;
	}	
	if(document.getElementById('rowNum').value == 0){
		alert("please add atleast one "+document.getElementById("reporting.appr.name").innerHTML.toLowerCase());
		return false;
	}
	return true;
}


function validateApproverDetails(){
	if(document.getElementById('paraFrm_reqDept').value=="" && document.getElementById('paraFrm_reqBrn').value==""
		&& document.getElementById('paraFrm_designationName').value=="" && document.getElementById('paraFrm_empName').value==""){
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

	var fieldName = ["paraFrm_empNameAdd"];
	var labelName = ["reporting.appr.name"];
	var flag	  = ["select"];
	
	var apprreviewName=document.getElementById('hiddencheck').value;
	var rowID = document.getElementById('rowNum').value;
	var empID = document.getElementById('paraFrm_empId').value;
	
	for(var total=1; total<=rowID; total++)
	{
		var empIteratorID = document.getElementById('empIdIterator'+total).value;
		var apprReviewIt = document.getElementById('apprreviewIterator'+total).value;
		if(empIteratorID == empID)
		{
			if( apprReviewIt==apprreviewName){
				
				alert("Approver already added, Please select another approver.");
				return false;
			}
		}
	}	
	if(!validateBlank(fieldName, labelName, flag)){
		return false;
	}	
	return true;
}

function deleteApprover(srNo, id){
	document.getElementById('paraFrm_srNo').value = srNo;
	
	 var conf=confirm("Do you really want to delete this record ?");
  		if(conf){
  			document.getElementById("paraFrm").action="GoalReportingStructure_deleteApprover.action";
			document.getElementById('paraFrm_empId').value=id;
		    document.getElementById("paraFrm").submit();
  			return true;
  		}
	  	else{
	  		 return false;
	  	}
	    return true;
}

function editApprover(srNo, approverId, approverToken, approverName){
	document.getElementById('paraFrm_srNo').value = srNo;
	document.getElementById('paraFrm_empId').value = approverId;
	document.getElementById('paraFrm_empTokenAdd').value = approverToken;
	document.getElementById('paraFrm_empNameAdd').value = approverName;
}

function shuffleColumns(code, buttonType){
	document.getElementById('paraFrm_srNo').value = code;
	
	if(buttonType == 'upWard'){
		document.getElementById("paraFrm").action = "GoalReportingStructure_shuffleColumnsAction.action?type=up";
		document.getElementById("paraFrm").submit();
	}
	
	if(buttonType == 'downWard'){
		document.getElementById("paraFrm").action = "GoalReportingStructure_shuffleColumnsAction.action?type=down";
		document.getElementById("paraFrm").submit();
	}
}

function checkDepartment(){
	document.getElementById("paraFrm_empCode").value = "";
	document.getElementById("paraFrm_empTokenNo").value = "";
	document.getElementById("paraFrm_empName").value = "";
	
	javascript:callsF9(500,325,'GoalReportingStructure_f9Department.action');
}

function checkBranch(){
	document.getElementById("paraFrm_empCode").value = "";
	document.getElementById("paraFrm_empTokenNo").value = "";
	document.getElementById("paraFrm_empName").value = "";
	
	javascript:callsF9(500,325,'GoalReportingStructure_f9Branch.action');
}

function checkDesignation(){
	document.getElementById("paraFrm_empCode").value = "";
	document.getElementById("paraFrm_empTokenNo").value = "";
	document.getElementById("paraFrm_empName").value = "";
	
	javascript:callsF9(500,325,'GoalReportingStructure_f9Designation.action');
}

function checkEmployee(){
	document.getElementById("paraFrm_deptCode").value = "";
	document.getElementById("paraFrm_reqDept").value = "";
	
	document.getElementById("paraFrm_brnCode").value = "";
	document.getElementById("paraFrm_reqBrn").value = "";
	
	document.getElementById("paraFrm_designationCode").value = "";
	document.getElementById("paraFrm_designationName").value = "";
	
	javascript:callsF9(500,325,'GoalReportingStructure_f9Selectemp.action'); 
}
function validateReport(){
	//alert("validateReport");
	//document.getElementById("paraFrm").action="GoalReportingStructure_viewApprover.action";
	//document.getElementById("paraFrm").submit();
	return true;
}

</script>

