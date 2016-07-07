<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="InformationSystemChangeRequestAppr" validate="true"
	id="paraFrm" validate="true" theme="simple">

	<s:hidden name="level" />
	<s:hidden name="infoSysReqApprId" />
	<s:hidden name="itApprover" />
	<s:hidden name="dataPath" />
	<s:hidden name="trackingNo" />
	
	<s:hidden name="listType" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPagePendingCancel" id="myPagePendingCancel" />
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageRejected" id="myPageRejected" />
	<s:hidden name="myPageClosed" id="myPageClosed" />
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Information
					System Change Request Approver</strong></td>
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
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- Assignment comments & Forwarded employee Section -- BEGINS -->
		<s:if test="activityAssignmentForwardEmpFlag">
			<tr>
				<td width="100%" height="22">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="1" class="formbg">
					<tr>
						<td width="25%"><label id="assignmentCommentsLabel"
							name="assignmentCommentsLabel" ondblclick="callShowDiv(this);"><%=label.get("assignmentCommentsLabel")%></label>
						<font color="red">*</font> :</td>
						<td colspan="3" id="ctrlShow"><s:textarea
							name="assignmentComments" cols="72" rows="3" /></td>
					</tr>
					<tr>
						<td width="25%"><label id="selectForwardToRoleLabel"
							name="selectForwardToRoleLabel" ondblclick="callShowDiv(this);"><%=label.get("selectForwardToRoleLabel")%></label>
						<font color="red">*</font> :</td>
						<td colspan="3" id="ctrlShow">
							<s:select headerKey="-1" headerValue="------------Select--------------" name="selectForwardToRole"
							cssStyle="width:160" list="forwardToRoleMap" />
						</td>
					</tr>
					<tr>
						<td width="25%"><label id="forwardToEmployee"
							name="forwardToEmployee" ondblclick="callShowDiv(this);"><%=label.get("forwardToEmployee")%></label>
						<font color="red">*</font> :</td>

						<td width="10%" id="ctrlShow">
							<s:textfield name="forwardToEmployeeToken" size="10" readonly="true" cssStyle="background-color: #F2F2F2;" />
						</td>

						<td width="40%" id="ctrlShow">
							<s:hidden name="forwardToEmployeeId" /> 
							<s:textfield readonly="true" cssStyle="background-color: #F2F2F2;" size="56" name="forwardToEmployeeName" />
						</td>
						<td width="25%"><img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16"
							onclick="javascript:callsF9(500,325,'InformationSystemChangeRequestAppr_f9ForwardToEmployee.action');">
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- Assignment comments & Forwarded employee Section -- ENDS -->

		<s:if test="feedbackSubmitFlag">
			<tr>
				<td width="100%" height="22">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="1" class="formbg">
					<tr>
						<td colspan="4"><b><label class="set"
							name="result.change" id="result.change"
							ondblclick="callShowDiv(this);"><%=label.get("result.change")%></label></b>
						</td>
					</tr>
					<tr>
						<td width="25%"><label id="describe.change"
							name="describe.change" ondblclick="callShowDiv(this);"><%=label.get("describe.change")%></label>
						:</td>
						<td colspan="2"><s:textarea name="describeChange" cols="100"
							rows="2" onkeypress="return imposeMaxLength(event, this, 400);" /><img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							id="ctrlHide" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_describeChange','describe.change','','400','400');"></td>

					</tr>
					<tr>
						<td width="25%"><label id="identify.improvement"
							name="identify.improvement" ondblclick="callShowDiv(this);"><%=label.get("identify.improvement")%></label>
						:</td>
						<td colspan="2"><s:textarea name="identifyImprovement"
							cols="100" rows="2"
							onkeypress="return imposeMaxLength(event, this, 400);" /><img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							id="ctrlHide" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_identifyImprovement','identify.improvement','','400','400');"></td>

					</tr>

					<tr>
						<td width="25%"><label id="comments" name="comments"
							ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
						:</td>

						<td colspan="2"><s:textarea name="comments" cols="100"
							rows="2" onkeypress="return imposeMaxLength(event, this, 400);" /><img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							id="ctrlHide" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_comments','comments','','400','400');"></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>

		<!-- Approver Comments Section Begins -->
		<s:if test="approverCommentsFlag">
			<tr>
				<td width="100%" height="22">
				<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
					 <tr>
						<s:if test="itInfoSystemChangeGroupFlag">
							<td id="ctrlShow" width="25%"><label
								id="closeTicketCommentsLabel" name="closeTicketCommentsLabel"
								ondblclick="callShowDiv(this);"><%=label.get("closeTicketCommentsLabel")%></label>
							<font id='ctrlShow' color="red">*</font> :</td>
						</s:if>
						<s:elseif test="forwardedEmployeeFlag">
							<td id="ctrlShow" width="25%"><label
								id="closeActivityCommentsLabel" name="closeActivityCommentsLabel"
								ondblclick="callShowDiv(this);"><%=label.get("closeActivityCommentsLabel")%></label>
								<font id='ctrlShow' color="red">*</font> :
							</td>
						</s:elseif>
						<s:else>
							<td id="ctrlShow" width="25%"><label
								id="approverCommentsLabel" name="approverCommentsLabel"
								ondblclick="callShowDiv(this);"><%=label.get("approverCommentsLabel")%></label>
							:</td>
						</s:else>

						<td colspan="4" id="ctrlShow"><s:textarea theme="simple"
							cols="72" rows="3" name="approverComments" id="approverComments"
							onkeypress="return imposeMaxLength(event, this, 500);" />
						</td>
					</tr>
					
					<tr>
						<td id="ctrlShow" width="25%"><label
							id="selectFiletoUploadLabel" name="selectFiletoUploadLabel"
							ondblclick="callShowDiv(this);"><%=label.get("selectFiletoUploadLabel")%></label>
						:</td>

						<td id="ctrlShow" width="30%">
							<s:textfield name="uploadFileNameApprover" size="45" readonly="true" cssStyle="background-color: #F2F2F2;" />
						</td>
						
						<td id="ctrlShow" width="10%" align="center">
							<input type="button" value="Select File" class="token"
									onclick="uploadFile('uploadFileNameApprover')" />
						</td>
						
						<td id="ctrlShow" width="35%">
							<input type="button" name="show" value="Show" class="token"
									onclick="viewApproverSideUploadedFile('paraFrm_uploadFileNameApprover', 'upload');" />
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- Approver Comments Section Ends -->
		
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
				<tr>
					<td width="5%" class="formth">Sr. No.</td>
					<td width="15%" class="formth">Approver Name</td>
					<td width="15%" class="formth">Role</td>
					<td width="30%" class="formth">Comments</td>
					<td width="15%" class="formth">Uploaded File</td>
					<td width="10%" class="formth">Date</td>
					<td width="10%" class="formth">Status</td>
				</tr>
					<%
						int count = 0;
					%>
			<s:iterator value="approverCommentList">
				<tr>
					<td class="sortableTD" align="center"><%=++count%></td>
					<td class="sortableTD"><s:property value="apprName" /></td>
					<td class="sortableTD"><s:property value="approverRole" /></td>
					<td class="sortableTD"><s:property value="apprComments" /></td>
					<td class="sortableTD">
						<a href="#" title="Click here to view uploaded document" 
						 	onclick="viewApproverSideUploadedFile('<s:property value='uploadFileNameApproverItr'/>', 'comments');">
						 	<font color="blue"><s:property value='uploadFileNameApproverItr'/></font>
						 </a>
					</td>
					<td class="sortableTD" align="center"><s:property value="apprDate" /></td>
					<td class="sortableTD"><s:property value="apprStatus" /></td>
				</tr>
			</s:iterator>
				<%
					if(count == 0) {
				%>
				<tr>
					<td width="100%" colspan="5" align="center"><font
						color="red">No Data To Display</font></td>
				</tr>
				<%
					}
				%>
			</table>
		  </td>
		 </tr>
		

		<!-- Added by Nilesh for Tracking Number7th Dec 2011-->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td colspan="1" width="18%">Tracking Number :</td>
					<td width="80%"><s:property value="trackingNo" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Ended by Nilesh for Tracking Number 7th  Dec 2011-->
		<!-- Change Information -->
		<tr>
			<td width="100%" height="22" colspan="6">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="6"><b><label class="set"
						name="change.infomation" id="change.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("change.infomation")%></label></b>
					</td>
				</tr>
				<tr>
					<td><label class="set" name="applicationDate"
						id="applicationDate" ondblclick="callShowDiv(this);"><%=label.get("applicationDate")%>
					</label> :</td>
					<td><s:textfield name="applDate" theme="simple" size="10"
						maxlength="10" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td></td>
					<td></td>
				</tr>

				<tr>

					<td width="25%"><label id="change.title" name="change.title"
						ondblclick="callShowDiv(this);"><%=label.get("change.title")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="25%"><s:textfield name="changeTitle" size="30" /></td>


					<td width="25%" align="left"><label
						id="change.schedular.occur" name="change.schedular.occur"
						ondblclick="callShowDiv(this);"><%=label.get("change.schedular.occur")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>

					<td colspan="3" width="25%"><s:textfield
						name="changeSchedularOccur" size="25"
						onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_changeSchedularOccur','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							id="ctrlHide" height="16" align="absmiddle" width="16">
					</s:a></td>

				</tr>
				<tr>
					<td width="25%"><label id="change.category"
						name="change.category" ondblclick="callShowDiv(this);"><%=label.get("change.category")%></label>
					:</td>
					<td width="25%"><s:textfield name="changeCategory" size="30" /></td>


					<td width="25%"><label id="reason" name="reason"
						ondblclick="callShowDiv(this);"><%=label.get("reason")%></label> :</td>
					<td colspan="2"><s:textarea name="reason" cols="30" rows="2"
						onkeypress="return imposeMaxLength(event, this, 400);" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_reason','reason','','400','400');"></td>

				</tr>
				<tr>
					<td width="25%"><label id="what.change" name="what.change"
						ondblclick="callShowDiv(this);"><%=label.get("what.change")%></label>
					:</td>
					<td colspan="1"><s:textarea name="whatChange" cols="30"
						rows="2" onkeypress="return imposeMaxLength(event, this, 400);" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_whatChange','what.change','','400','400');"></td>


					<td width="25%"><label id="impact.change" name="impact.change"
						ondblclick="callShowDiv(this);"><%=label.get("impact.change")%></label>
					:</td>
					<td width="25%"><s:textfield name="impactChange" size="30" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="risk.associated.change"
						name="risk.associated.change" ondblclick="callShowDiv(this);"><%=label.get("risk.associated.change")%></label>
					:</td>
					<td width="25%"><s:textfield name="riskAssociatedChange"
						size="30" /></td>


					<td width="25%"><label id="expect.result" name="expect.result"
						ondblclick="callShowDiv(this);"><%=label.get("expect.result")%></label>
					:</td>
					<td colspan="2"><s:textarea name="expectResult" cols="30"
						rows="2" onkeypress="return imposeMaxLength(event, this, 400);" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_expectResult','expect.result','','400','400');"></td>

				</tr>
				<tr>
					<td>
					<!-- 
						<label class="set" name="current.status.change" id="current.status.change" ondblclick="callShowDiv(this);"><%=label.get("current.status.change")%></label>
						<font color="red"></font> :					
					 -->	
					</td>

					<td>
					<!-- 
						<s:select headerKey="" headerValue="--Select--"
						cssStyle="width:135" name="currentStatusChange"
						list="#{'O':'Open','C':'Closed'}" />					
					 -->	
					</td>

				</tr>




			</table>
			</td>
		</tr>

		<!-- Plan of Action/Testing  -->
		<tr>
			<td width="100%" height="22" colspan="5">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5"><b><label class="set" name="plan.action"
						id="plan.action" ondblclick="callShowDiv(this);"><%=label.get("plan.action")%></label></b>
					</td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label id="detail.plan.action"
						name="detail.plan.action" ondblclick="callShowDiv(this);"><%=label.get("detail.plan.action")%>:</label><font
						color="red" id='ctrlHide'>*</font></td>

					<td width="25%" colspan="2"><s:textfield name="uploadFileName"
						onclick="uploadFile('uploadFileName')" readonly="true" size="40"
						cssStyle="background-color: #F2F2F2;" /> <input type="button"
						value="Upload File" class="token"
						onclick="uploadFile('uploadFileName')" /></td>
					<td width="25%"></td>
					<td width="25%"></td>

				</tr>

				<tr>
					<td></td>
					<td><a href="#" onclick="viewUploadedFile();"><font
						color="blue"><u>click here to view Detail Plan of
					Action </u></font></a></td>

				</tr>

				<tr>
					<td width="25%" colspan="1"><label
						id="optional.project.plan.attachment"
						name="optional.project.plan.attachment"
						ondblclick="callShowDiv(this);"><%=label.get("optional.project.plan.attachment")%>:</label><font
						color="red" id='ctrlHide'></font></td>

					<td width="25%" colspan="2"><s:textfield
						name="uploadOptionalFileName"
						onclick="uploadFile('uploadOptionalFileName')" readonly="true"
						size="40" cssStyle="background-color: #F2F2F2;" /> <input
						type="button" value="Upload File" class="token"
						onclick="uploadFile('uploadOptionalFileName')" /></td>
					<td width="25%"></td>
					<td width="25%"></td>
				</tr>

				<tr>
					<td></td>
					<td><a href="#" onclick="viewUploadedFileOptional();"><font
						color="blue"><u>click here to view optional project
					plan</u></font></a></td>

				</tr>



				<tr>
					<td width="25%"><label id="backout.plan.estimate"
						name="backout.plan.estimate" ondblclick="callShowDiv(this);"><%=label.get("backout.plan.estimate")%></label>
					:</td>
					<td width="25%"><s:textfield name="backoutPlanEstimate"
						size="30" /></td>


					<td width="25%"><label id="who.perform.change"
						name="who.perform.change" ondblclick="callShowDiv(this);"><%=label.get("who.perform.change")%></label>
					:</td>
					<td width="25%"><s:textfield name="whoPerformChangeTesting"
						size="30" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="how.change.tested"
						name="how.change.tested" ondblclick="callShowDiv(this);"><%=label.get("how.change.tested")%></label>
					:</td>
					<td width="25%"><s:textfield name="howChangeTested" size="30" /></td>


					<td width="25%"><label id="update.optional"
						name="update.optional" ondblclick="callShowDiv(this);"><%=label.get("update.optional")%></label>
					:</td>
					<td width="25%"><s:textfield name="updateOptional" size="30" /></td>

				</tr>

			</table>
			</td>
		</tr>


		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
				<tr>
					<td width="100%">
					<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
						<tr>
							<td colspan="4"><b><label id="form.approval"
								name="form.approval" ondblclick="callShowDiv(this);"><%=label.get("form.approval")%></label>
							:</b></td>
						</tr>
						<tr>
							<td>
								<label id="default.manager" name="default.manager"
								ondblclick="callShowDiv(this);"><%=label.get("default.manager")%></label> :</td>
								<td>
									<s:hidden name="firstApproverCode" />
									<s:hidden name="approverName" />
									<s:property value="firstApproverToken" />
									<s:property value="approverName" />
								</td>
						</tr>
						<tr>
							<td width="20%"><label id="change.approval"
								name="change.approval" ondblclick="callShowDiv(this);"><%=label.get("change.approval")%></label>
								:<font color="red" id='ctrlHide'>*</font>
							</td>
							<td width="60%" colspan="3">
								<s:textfield name="approverToken" size="25" theme="simple" readonly="true"
								cssStyle="background-color: #F2F2F2;" /> 
								<s:textfield name="selectapproverName" size="71" theme="simple"
								readonly="true" cssStyle="background-color: #F2F2F2;" /> 
								<s:hidden name="approverCode" /> 
								<img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" id='ctrlHide'
								onclick="javascript:callsF9(500,325,'PersonalDataChange_f9Approver.action');">
							</td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td width="20%"><b>Completed By:</b></td>
					<td width="20%"><s:hidden name="initiatorCode" /> <s:property
						value="initiatorName" /></td>
					<td width="20%"><b>Completed On:</b></td>
					<td width="20%"><s:hidden name="initiatorDate"></s:hidden> <s:property
						value="initiatorDate" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%"></td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="status" />

		<input type="hidden" name="locationOption" id="locationOption"
			value='<s:property value="locationOption"/>' />
	</table>
</s:form>

<script>

function approveFun()
{
	var con = confirm("Do you really want to approve this application?");
	if(con)
	{
		document.getElementById('paraFrm').target = "_self";
    	document.getElementById('paraFrm').action = 'InformationSystemChangeRequestAppr_approveApplication.action';
		document.getElementById('paraFrm').submit();
	}
}

function rejectFun()
{
	var con = confirm("Do you really want to reject this application?");
	if(con)
	{
		document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').action = 'InformationSystemChangeRequestAppr_rejectApplication.action';
		document.getElementById('paraFrm').submit();
	}
}

function sendbackFun() {
	var con = confirm("Do you really want to sendback this application?");
	if(con) {
		document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').action = 'InformationSystemChangeRequestAppr_sendBackApplication.action';
		document.getElementById('paraFrm').submit();
	}
}


function backtolistFun() {
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'InformationSystemChangeRequestAppr_backToList.action';
	document.getElementById('paraFrm').submit();
}

function printFun() {	
	window.print();
}

function deleteFun() {
	 var con=confirm('Do you want to delete this application ?');
	 if(con) {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'InformationSystemChangeRequestAppr_delete.action';
		document.getElementById('paraFrm').submit();
	}
}

function cancelapplicationFun() {
	 var con=confirm('Do you want to cancel this application ?');
	 if(con) {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'InformationSystemChangeRequestAppr_cancel.action';
		document.getElementById('paraFrm').submit();
	}
}

function imposeMaxLength(Event, Object, MaxLen) {
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
}

function viewUploadedFile() {
		var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
		//alert('Please first upload the file...'+uploadFileName);
		if(uploadFileName == '') {
			alert('Please first upload the file...');
			return false;
		}
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'InformationSystemChangeRequestAppr_viewUploadedFile.action?uploadFileName='+uploadFileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
}
	
function viewUploadedFileOptional() {
		var uploadFileName = document.getElementById('paraFrm_uploadOptionalFileName').value;
		//alert('Please first upload the file...'+uploadFileName);
		if(uploadFileName == '') {
			alert('Please first upload the file...');
			return false;
		}
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'InformationSystemChangeRequestAppr_viewUploadedFile.action?uploadFileName='+uploadFileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
}

function closeFun() {
	window.close();
}

function approveandforwardFun() {
	forwardApplicationFunction();
}

function forwardFun() {
	forwardApplicationFunction();
} 

function forwardApplicationFunction() {
	try {
		var forwardToEmployeeName = trim(document.getElementById('paraFrm_forwardToEmployeeName').value);
		var assignmentComments = trim(document.getElementById('paraFrm_assignmentComments').value);
		if(assignmentComments == "") {
			alert("Please enter "+document.getElementById('assignmentCommentsLabel').innerHTML.toLowerCase());
			document.getElementById('paraFrm_assignmentComments').focus();
			return false;
		}  else if (eval(assignmentComments.length)>500) {
			alert("Only 500 characters are allowed for "+document.getElementById('assignmentCommentsLabel').innerHTML.toLowerCase());
			document.getElementById('paraFrm_assignmentComments').focus();
			return false;
		}
		
	/*	
		var selectForwardToRole = document.getElementById('paraFrm_selectForwardToRole').value;
		if(selectForwardToRole == '-1') {
			alert("Please "+document.getElementById('selectForwardToRoleLabel').innerHTML.toLowerCase());
			document.getElementById('paraFrm_selectForwardToRole').focus();
			return false;
		}
	*/	
		if(forwardToEmployeeName == '') {
			alert("Please select "+document.getElementById('forwardToEmployee').innerHTML.toLowerCase());
			return false;
		}
	
	 	var con=confirm('Do you want to forward this application ?');
	 	if(con) {
			document.getElementById('paraFrm').target = "_self";
      		document.getElementById('paraFrm').action = 'InformationSystemChangeRequestAppr_forwardApplication.action';
			document.getElementById('paraFrm').submit();
		}
	} catch(e) {
		alert("Forward Function >>"+e);
	}	
}


function approveandcloseFun() {
	closeApplicationFunction();
}

function closedticketFun() {
	closeApplicationFunction();
}

function closeApplicationFunction() {
	try {
		var approverComments = trim(document.getElementById('approverComments').value);
		if(approverComments == '') {
			alert("Please enter your "+document.getElementById('closeTicketCommentsLabel').innerHTML.toLowerCase());
			document.getElementById('approverComments').focus();
			return false;
		} else if (eval(approverComments.length)>500) {
			alert("Only 500 characters are allowed for "+document.getElementById('closeTicketCommentsLabel').innerHTML.toLowerCase());
			document.getElementById('approverComments').focus();
			return false;
		}
	
	 	var con=confirm('Do you really want to close ticket?');
	 	if(con) {
			document.getElementById('paraFrm').target = "_self";
      		document.getElementById('paraFrm').action = 'InformationSystemChangeRequestAppr_closedApplication.action';
			document.getElementById('paraFrm').submit();
		}
	} catch(e) {
		alert("Forward Function >>"+e);
	}
}

function closeactivityFun() {
	try {
		var approverComments = trim(document.getElementById('approverComments').value);
		if(approverComments == '') {
			alert("Please enter your "+document.getElementById('closeActivityCommentsLabel').innerHTML.toLowerCase());
			document.getElementById('approverComments').focus();
			return false;
		} else if (eval(approverComments.length)>500) {
			alert("Only 500 characters are allowed for "+document.getElementById('closeActivityCommentsLabel').innerHTML.toLowerCase());
			document.getElementById('approverComments').focus();
			return false;
		}
		
		var con=confirm('Do you really want to close activity?');
	 	if(con) {
			document.getElementById('paraFrm').target = "_self";
      		document.getElementById('paraFrm').action = 'InformationSystemChangeRequestAppr_closeActivity.action';
			document.getElementById('paraFrm').submit();
		}
	} catch(e) {
		alert("Error while aclosing activity");
	}
}

function uploadFile(fieldName) {
	var path = document.getElementById("paraFrm_dataPath").value;
	window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}

function viewApproverSideUploadedFile(uploadFileName, section) {
	var fileName = "";
	if (section == "comments") {
		fileName = uploadFileName;	
	} else {
		fileName = document.getElementById(uploadFileName).value;	
	}
	
	if(fileName == '') {
		alert('Please first upload the file.');
		return false;
	}
	document.getElementById('paraFrm').target = '_blank';
	document.getElementById('paraFrm').action = 'InformationSystemChangeRequestAppr_viewUploadedFile.action?uploadFileName='+fileName;
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}
</script>