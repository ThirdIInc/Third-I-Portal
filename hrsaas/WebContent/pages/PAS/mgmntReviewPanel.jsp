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
					<td width="93%" class="txt"><strong class="text_head">Management
					Review Panel</strong></td>
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
					<td width="78%"><s:if test="managerFlag" ><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></s:if></td>
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
				<s:if test="managerFlag" >
					<td width="20%" height="20" class="formtext"><label
						name="group.head" class="set" id="group.head"
						ondblclick="callShowDiv(this);"><%=label.get("group.head")%></label>
					 :</td>
					<td width="30%"  height="20" ><s:property value="groupHeadName"/></td>
					</s:if>
					<td width="20%"  height="20" class="formtext"><label
						name="appraisal.code" class="set" id="appraisal.code"
						ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label>
					<font color="red">*</font> :</td>
					<td width="30%"  height="20" ><s:textfield
						name="apprCode" size="20" maxlength="25" readonly="true" /><s:hidden
						name="apprId" />
						<s:if test="managerFlag" ></s:if><s:else>
						<img
						src="../pages/images/recruitment/search2.gif" height="16" id="ctrlHide"
						align="absmiddle" width="17" theme="simple"
						onclick="javascript:callsF9(500,325,'MgmntReviewPanel_f9AppCal.action'); "></s:else></td>
				</tr>
				<tr>
					<td width="20%" height="20" class="formtext" ><label
						name="appraisal.from" class="set" id="appraisal.from"
						ondblclick="callShowDiv(this);"><%=label.get("appraisal.from")%></label>: </td>
					<td width="30%" ><s:textfield name="frmDate" readonly="true"/> </td>
					<td width="20%" ><label name="appraisal.to" class="set" id="appraisal.to"
						ondblclick="callShowDiv(this);"><%=label.get("appraisal.to")%></label>
					: </td>
					<td width="30%" ><s:textfield name="toDate" readonly="true"/></td>
				</tr>
				<s:if test="managerFlag" ><s:hidden name="phaseCode"/></s:if><s:else>
				<tr>
					<td width="30%" colspan="1" height="20" class="formtext"><label
						name="phase" class="set" id="phase"
						ondblclick="callShowDiv(this);"><%=label.get("phase")%></label>
					<font color="red">*</font> :</td>
					<td width="70%" colspan="3" height="20" ><s:select
						name="phaseCode" list="phaseMap" size="1" cssStyle="width:120" theme="simple" /></td>
				</tr></s:else>
			</table>
			</td>
		</tr>
		<!-- GEOUP HEADS LIST SECTION BEGINS -->
		<s:if test="grpHeadFlag" >
			<tr><td width="25%"><b>Group Heads</b></td>
				<td width="75%">
				<table width="100%">
					<tr>
						<td width="70%"></td>
						<%
							int totalPage = 0;
									int pageNo = 0;
						%>
						<s:if test="recordsAvailable">
							<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
							<%
								totalPage = (Integer) request.getAttribute("totalPage");
											pageNo = (Integer) request.getAttribute("pageNo");
							%> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPage%>', 'MgmntReviewPanel_setPhaseDetails.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPage%>', 'MgmntReviewPanel_setPhaseDetails.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
								maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage%>', 'MgmntReviewPanel_setPhaseDetails.action');return numbersOnly();" />
							of <%=totalPage%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPage%>', 'MgmntReviewPanel_setPhaseDetails.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'MgmntReviewPanel_setPhaseDetails.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</s:if>
					</tr>
				</table>
				</td>
			</tr>

			<tr>
				<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
					border="0">
					<tr>
						<td width="100%" class="formbg" colspan="3">
						<table width="100%" class="sortableTD">
							<tr>
								<s:hidden name="myPage" id="myPage" />
								<td class="formth" align="center" width="5%"><label
									name="ser.no" class="set" id="ser.no"
									ondblclick="callShowDiv(this);"><%=label.get("ser.no")%></label></td>
								<td class="formth" align="center" width="25%"><label
									name="group.head" class="set" id="group.head"
									ondblclick="callShowDiv(this);"><%=label.get("group.head")%></label></td>
								<td class="formth" align="center" width="15%"><label
									name="department" class="set" id="department"
									ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
								<td class="formth" align="center" width="15%"><label
									name="designation" class="set" id="designation"
									ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
								<td class="formth" align="center" width="10%"><label
									name="branch" class="set" id="branch"
									ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>

								<s:if test="recordsAvailable">
									<td width="5%" align="center" class="formth" id="ctrlShow">
									Process</td>
								</s:if>
							</tr>
							<s:if test="recordsAvailable">
								<%!int d = 0;%>
								<%
									int i = 0;
												int cn = pageNo * 20 - 20;
								%>
								<s:iterator value="grpHeadList">
									<tr onmouseover="javascript: newRowColor(this);"
										onmouseout="javascript: oldRowColor(this);"
										ondblclick="javascript: callForEdit('<s:property value="typeID"/>');"
										style="cursor: hand;">
										<td width="5%" align="center" class="sortableTD"><%=++cn%>
										<%
											++i;
										%><s:hidden name="srNo" /></td>
										<td width="25%" align="left" class="sortableTD"><s:hidden
											name="grpHeadId" /><s:property value="grpHeadName" /></td>
										<td width="15%" align="left" class="sortableTD">&nbsp;<s:property
											value="grpHeadDept" /></td>
										<td width="15%" align="left" class="sortableTD">&nbsp;<s:property
											value="grpHeadDesg" /></td>
										<td width="10%" align="left" class="sortableTD">&nbsp;<s:property
											value="grpHeadCenter" /><s:hidden
											name="groupId" /></td>
										<td width="10%" id="ctrlShow" align="center"
											class="sortableTD"><input type="button"
											class="token" id="confChk<%=i%>" name="process" value = "View Managers"
											onclick=" callProcess('<s:property value="grpHeadId"/>', '<%=i%>')" />
										</td>
									</tr>
								</s:iterator>
								<%
									d = i;
								%>
							</s:if>
						</table>
						<s:if test="recordsAvailable"></s:if> <s:else>
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
		</s:if>
		<!-- GROUP HEAD LIST SECTION ENDS -->
		
		<!-- MANAGER LIST SECTION BEGINS -->
		<s:if test="managerFlag" >
		<tr><td width="100%"><b>Manager List</b></td></tr>
			<tr>
				<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
					border="0">
					<tr>
						<td width="100%" class="formbg" colspan="3">
						<table width="100%" class="sortableTD">
							<tr>
								<td class="formth" align="center" width="5%"><label
									name="ser.no" class="set" id="ser.no1"
									ondblclick="callShowDiv(this);"><%=label.get("ser.no")%></label></td>
								<td class="formth" align="center" width="25%"><label
									name="manager" class="set" id="manager"
									ondblclick="callShowDiv(this);"><%=label.get("manager")%></label></td>
								<td class="formth" align="center" width="15%"><label
									name="department" class="set" id="department1"
									ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
								<td class="formth" align="center" width="15%"><label
									name="designation" class="set" id="designation1"
									ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
								<td class="formth" align="center" width="10%"><label
									name="branch" class="set" id="branch1"
									ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
								<td class="formth" align="center" width="10%"><label
									name="group.name" class="set" id="group.name"
									ondblclick="callShowDiv(this);"><%=label.get("group.name")%></label></td>
								<s:if test="mgrRecordsAvailable">
									<td width="5%" align="center" class="formth" id="ctrlShow">
									Process</td>
								</s:if>
							</tr>
							<s:if test="mgrRecordsAvailable">
								<%!int a = 0;%>
								<%
									int j = 0;
												
								%>
								<s:iterator value="managerList">
									<tr>
										<td width="5%" align="center" class="sortableTD"><%=++j%>
										</td>
										<td width="25%" align="left" class="sortableTD"><s:hidden
											name="managerIdList" />&nbsp;<s:property value="managerName" /></td>
										<td width="15%" align="left" class="sortableTD">&nbsp;<s:property
											value="managerDept" /></td>
										<td width="15%" align="left" class="sortableTD">&nbsp;<s:property
											value="managerDesg" /></td>
										<td width="10%" align="left" class="sortableTD">&nbsp;<s:property
											value="managerCenter" /><s:hidden
											name="groupId" /></td>
										<td width="10%" align="left" class="sortableTD">&nbsp;<s:property
											value="groupName" /><s:hidden
											name="groupName" /></td>
										<td width="10%" id="ctrlShow" align="center"
											class="sortableTD"><input type="button"
											class="token" id="confChk<%=j%>" name="process" value = "View Team"
											onclick=" callEmpList('<s:property value="managerIdList"/>','<s:property value="groupId"/>','<s:property value="managerDesg"/>',
											'<s:property value="managerDept"/>','<s:property value="managerCenter"/>','<s:property value="managerDiv"/>','<s:property value="managerName"/>')" />
										</td>
									</tr>
								</s:iterator>
								<%
									a = j;
								%>
							</s:if>
						</table>
						<s:if test="mgrRecordsAvailable"></s:if> <s:else>
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
		</s:if>
		<!-- MANAGER LIST SECTION ENDS -->
		<s:hidden name="hidGroupId" />
		<s:hidden name="hidGroupHeadId" />
		<s:hidden name="grpHeadFlag" />
		<s:hidden name="managerFlag" />
		<s:hidden name="managerId" />
		<s:hidden name="managerDesg" />
		<s:hidden name="managerDiv" />
		<s:hidden name="managerDept" />
		<s:hidden name="managerCenter" />
		<s:hidden name="managerName" />
		<s:hidden name="groupHeadName" />
	</table>
</s:form>

<script>
document.getElementById('paraFrm_enableAll').value='Y';
function callProcess(grpHeadId, id){
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm_hidGroupHeadId').value = grpHeadId;
	document.getElementById('paraFrm').action = "MgmntReviewPanel_getManagersUnderGroupHeads.action?groupHeadId="+grpHeadId;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}
function backFun(){
	if(document.getElementById('paraFrm_managerFlag').value=="true"){
		document.getElementById('paraFrm').action = "MgmntReviewPanel_setPhaseDetails.action";
	}else {
		
	}
	document.getElementById('paraFrm').submit();
}
function callEmpList(managerId,groupId,managerDesg,managerDept,managerBranch,managerDiv,managerName){
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm_managerId').value = managerId;
	document.getElementById('paraFrm_hidGroupId').value = groupId;
	document.getElementById('paraFrm_managerDesg').value = managerDesg;
	document.getElementById('paraFrm_managerDept').value = managerDept;
	document.getElementById('paraFrm_managerDiv').value = managerDiv;
	document.getElementById('paraFrm_managerCenter').value = managerBranch;
	document.getElementById('paraFrm_managerName').value = managerName;
	document.getElementById('paraFrm').action = "MgmntReviewPanel_getEmployeeList.action?groupId="+groupId;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}

function reportFun(){
	document.getElementById('paraFrm').action = "MgmntReviewPanel_generateReport.action";
	document.getElementById('paraFrm').submit();
	return true;
}
</script>