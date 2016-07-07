<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="MgmntReviewPanel" theme="simple" method="post"
	name="paraFrm" id="paraFrm">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee List</strong></td>
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

			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
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
					<td width="20%" colspan="1" height="20" class="formtext"><label
						name="group.head" class="set" id="group.head"
						ondblclick="callShowDiv(this);"><%=label.get("group.head")%></label>
					 :</td>
					<td width="70%"  height="20" colspan="3"><s:property value="groupHeadName"/></td>
				</tr>
				<tr>
					<td width="20%" colspan="1" height="20" class="formtext"><label
						name="manager" class="set" id="manager"
						ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>
					 :</td>
					<td width="30%"  height="20" ><s:property value="managerName"/></td>
					<td width="15%" colspan="1" height="20" class="formtext"><label
						name="designation" class="set" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					 :</td>
					<td width="35%"  height="20" ><s:property value="managerDesg"/></td>
				</tr>
				<tr>
					<td width="20%" colspan="1" height="20" class="formtext"><label
						name="division" class="set" id="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
					 :</td>
					<td width="30%"  height="20" ><s:property value="managerDiv"/></td>
					<td width="15%" colspan="1" height="20" class="formtext"><label
						name="department" class="set" id="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					 :</td>
					<td width="35%"  height="20" ><s:property value="managerDept"/></td>
				</tr>
				<tr>
					<td width="20%" colspan="1" height="20" class="formtext"><label
						name="branch" class="set" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					 :</td>
					<td width="30%"  height="20" ><s:property value="managerCenter"/></td>
					<td width="15%" colspan="1" height="20" class="formtext"><label
						name="appraisal.code" class="set" id="appraisal.code"
						ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
					 :</td>
					<td width="35%" colspan="4" height="20" ><s:property value="apprCode" /><s:hidden
						name="apprId" /><s:hidden name="apprCode" /></td>
					
				</tr>
			</table>
			</td>
		</tr>
		
		<!-- MANAGER LIST SECTION BEGINS -->
		
		<tr><td width="100%"><b><label	name="employee.list" class="set" id="employee.list"
									ondblclick="callShowDiv(this);"><%=label.get("employee.list")%></label></b></td></tr>
			<tr>
				<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
					border="0">
					<tr>
						<td width="100%" class="formbg" colspan="3">
						<table width="100%" class="sortableTD">
							<tr>
								<td class="formth" align="center" width="25%"><label
									name="employee.panel" class="set" id="employee.panel"
									ondblclick="callShowDiv(this);"><%=label.get("employee.panel")%></label></td>
								<td class="formth" align="center" width="10%"><label
									name="kra.score" class="set" id="kra.score"
									ondblclick="callShowDiv(this);"><%=label.get("kra.score")%></label></td>
								<td class="formth" align="center" width="15%"><label
									name="comp.score" class="set" id="comp.score"
									ondblclick="callShowDiv(this);"><%=label.get("comp.score")%></label></td>
								<td class="formth" align="center" width="10%"><label
									name="total.score" class="set" id="total.score"
									ondblclick="callShowDiv(this);"><%=label.get("total.score")%></label></td>
								<td class="formth" align="center" width="10%"><label
									name="mod.score" class="set" id="mod.score"
									ondblclick="callShowDiv(this);"><%=label.get("mod.score")%></label></td>
								<td class="formth" align="center" width="30%"><label
									name="hr.action" class="set" id="hr.action"
									ondblclick="callShowDiv(this);"><%=label.get("hr.action")%></label></td>
							</tr>
							<s:if test="empRecordsAvailable">
								<%!int a = 0;%>
								<%
									int j = 0;
												
								%>
								<s:iterator value="employeeList">
									<tr title="Click to view cometency analysis">
										<td width="25%" align="left" class="sortableTD"><s:hidden
											name="empId" />&nbsp;<a href="#" id="menuid" class="on" onclick="showDetails('<s:property value="empId" />', '<s:property
											value="empKRAScore" />', '<s:property value="empCompetencyScore" />')">
											<s:property value="empName" /></a>
</td>
										<td width="10%" align="left" class="sortableTD">&nbsp;<s:property
											value="empKRAScore" /><s:hidden	name="empKRAScore" /></td>
										<td width="15%" align="left" class="sortableTD">&nbsp;<s:property
											value="empCompetencyScore" /><s:hidden	name="empCompetencyScore" /></td>
										<td width="10%" align="left" class="sortableTD">&nbsp;<s:property
											value="empTotalScore" /><s:hidden
											name="empTotalScore" /></td>
										<td width="10%" align="left" class="sortableTD">&nbsp;<s:textfield name="empModScore" size="5" onkeypress="return numbersWithDot();" /></td>
										<td width="30%" align="left" class="sortableTD">&nbsp;<s:textarea cols="50" rows="3" name="empHRAction" /></td>
									</tr>
								</s:iterator>
								
								<%
									a = j;
								%>
								</s:if>
						</table>
						<s:if test="empRecordsAvailable"></s:if> <s:else>
							<table width="100%">
								<tr>
									<td align="center"><font color="red">No Data To
									Display</font></td>
								</tr>
							</table>
						</s:else></td>
					</tr>
				</table>
				</td>
			</tr>
		<tr>
			<td colspan="3">

			<table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
					<td width="100%" ><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="hidGroupId" />
		<s:hidden name="grpHeadFlag" />
		<s:hidden name="managerFlag" />
		<s:hidden name="managerId" />
		<s:hidden name="managerDesg" />
		<s:hidden name="managerDiv" />
		<s:hidden name="managerDept" />
		<s:hidden name="managerCenter" />
		<s:hidden name="managerName" />
		<s:hidden name="hidGroupHeadId" />
		<s:hidden name="phaseCode" />
		<s:hidden name="frmDate" />
		<s:hidden name="toDate" />
		<s:hidden name="hiddenEmpId" />
		<s:hidden name="hiddenKraScore" />
		<s:hidden name="hiddenCompetencyScore" />
	</table>
</s:form>

<script>
function backFun(){
	var grpHeadId=document.getElementById('paraFrm_hidGroupHeadId').value;
	document.getElementById('paraFrm').action = "MgmntReviewPanel_getManagersUnderGroupHeads.action?groupHeadId="+grpHeadId;
	document.getElementById('paraFrm').submit();
}
function saveFun(){
var conf=confirm('Do you really want to save the record?');
			 	if(!conf){
			 	return false;
			 	}
	document.getElementById('paraFrm').action = "MgmntReviewPanel_saveMgmntScore.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
}
function callEmpList(managerId,groupId,managerDesg,managerDept,managerBranch,managerDiv){
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm_managerDesg').value = managerDesg;
	document.getElementById('paraFrm_managerDept').value = managerDept;
	document.getElementById('paraFrm_managerDiv').value = managerDiv;
	document.getElementById('paraFrm_managerCenter').value = managerBranch;
	document.getElementById('paraFrm').action = "MgmntReviewPanel_getEmployeeList.action?groupId="+groupId+"&managerId="+managerId;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}

function showDetails(empId, kraScore, competencyScore){

		document.getElementById('paraFrm_hiddenEmpId').value=empId;
		document.getElementById('paraFrm_hiddenKraScore').value=kraScore;
		document.getElementById('paraFrm_hiddenCompetencyScore').value=competencyScore;
		window.open('','new','top=50,left=100,width=800,height=700,scrollbars=yes,status=no,resizable=yes');
		document.getElementById("paraFrm").target="new";
	 	document.getElementById("paraFrm").action="MgmntReviewPanel_getEmpAppraisalDetails.action"; 
		document.getElementById("paraFrm").submit();
		document.getElementById('paraFrm').target="main";
}
</script>