<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="InformationSystemChangeRequest"
	name="InformationSystemChangeRequest" id="paraFrm" theme="simple"
	validate="true">
	<s:hidden name="status" />
	<s:hidden name="hiddenCode" />
	<s:hidden name="trackingNo" />
	<s:hidden name="dataPath" />
	<s:hidden name="listType" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="myPageInProcess" id="myPageInProcess" />
	<s:hidden name="myPageSentBack" id="myPageSentBack" />
	<s:hidden name="myPageApproved" id="myPageApproved" />
	<s:hidden name="myPageApprovedCancel" id="myPageApprovedCancel" />
	<s:hidden name="myPageCancel" id="myPageCancel" />
	<s:hidden name="myPageRejected" id="myPageRejected" />
	<s:hidden name="myPageCancelRejected" id="myPageCancelRejected" />

	<s:hidden name="path" value="%{getText('data_path')}" id="pathFld" />


	<table width="100%" class="formbg" align="right">

		<tr>
			<td colspan="5">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="92%" class="txt"><strong class="text_head">Information
					System Change Request Form</strong></td>
					<td width="4%" valign="middle" align="right" class="txt"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
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

		<!-- Added by Nilesh for Tracking Number 2nd Dec 2011-->

		<s:if test="trackingFlag">
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
		</s:if>

		<!-- Ended by Nilesh for Tracking Number 2nd Dec 2011-->


		<!-- Result of change -->
		<s:if test="feedbackFlag">
			<tr>
				<td width="100%" height="22" id="ctrlShow">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="1" class="formbg">
					<tr>
						<td colspan="4"><b><label class="set"
							name="result.change" id="result.change"
							ondblclick="callShowDiv(this);"><%=label.get("result.change")%></label></b>
						</td>
					</tr>
					<tr>
						<td width="25%" id="ctrlShow"><label id="describe.change"
							name="describe.change" ondblclick="callShowDiv(this);"><%=label.get("describe.change")%></label>
						:</td>
						<td colspan="2" id="ctrlShow"><s:textarea
							name="describeChange" cols="100" rows="2"
							onkeypress="return imposeMaxLength(event, this, 400);" /><img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							id="ctrlShow" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_describeChange','describe.change','','400','400');"></td>

					</tr>
					<tr>
						<td width="25%" id="ctrlShow"><label
							id="identify.improvement" name="identify.improvement"
							ondblclick="callShowDiv(this);"><%=label.get("identify.improvement")%></label>
						:</td>
						<td colspan="2" id="ctrlShow"><s:textarea
							name="identifyImprovement" cols="100" rows="2"
							onkeypress="return imposeMaxLength(event, this, 400);" /><img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							id="ctrlShow" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_identifyImprovement','identify.improvement','','400','400');"></td>

					</tr>

					<tr>
						<td width="25%" id="ctrlShow"><label id="comments"
							name="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
						<font color="red">*</font>:</td>

						<td colspan="2" id="ctrlShow"><s:textarea name="comments"
							cols="100" rows="2"
							onkeypress="return imposeMaxLength(event, this, 400);" /><img
							src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							id="ctrlShow" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_comments','comments','','400','400');"></td>

					</tr>
				</table>
				</td>
			</tr>
		</s:if>
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
		
		<!-- BEGINS -- Re-Open Comments -->
		<s:if test="reOpenCommentsFlag">
		<tr>
			<td width="100%" height="22" id="ctrlShow">
				<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
					<tr>
						<td width="25%" id="ctrlShow">
							<label id="reOpenCommentsLabel" name="reOpenCommentsLabel" ondblclick="callShowDiv(this);"><%=label.get("reOpenCommentsLabel")%></label> 
							<font color="red">*</font> :
						</td>
					 
						<td colspan="2" id="ctrlShow">
							<s:textarea name="reOpenComments" cols="100" rows="2"
							onkeypress="return imposeMaxLength(event, this, 400);" />
							<img src="../pages/images/zoomin.gif" height="12" align="absmiddle"
							id="ctrlShow" width="12" theme="simple" onclick="javascript:callWindow('paraFrm_reOpenComments','reOpenCommentsLabel','','400','400');">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		</s:if>
		<!-- ENDS -- Re-open Comments -->
		
		<!-- Approver Comments Section Begins -->
		<s:if test="approverCommentFlag">
			<tr>
				<td>
				<table width="100%" class="formbg">
					<tr>
						<td colspan="5"><b>Approver Comments</b></td>
					</tr>
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
							<td class="sortableTD"><%=++count%></td>
							<td class="sortableTD"><s:property value="apprName" /></td>
							<td class="sortableTD"><s:property value="approverRole" /></td>
							<td class="sortableTD"><s:property value="apprComments" /></td>
							<td class="sortableTD">
									<a href="#" title="Click here to view uploaded document" 
									 	onclick="viewUploadedDocument('<s:property value='uploadFileNameApproverItr'/>');">
									 	<font color="blue"><s:property value='uploadFileNameApproverItr'/></font>
									 </a>
							</td>
							<td class="sortableTD" align="center"><s:property value="apprDate" /></td>
							<td class="sortableTD"><s:property value="apprStatus" /></td>
						</tr>
					</s:iterator>
					<%
					if (count == 0) {
					%>
					<tr>
						<td width="100%" colspan="5" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
					<%
					}
					%>
				</table>
				</td>
			</tr>
		</s:if>



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
					<td width="25%"><s:textfield name="changeTitle" size="30"
						maxlength="400" /></td>


					<td width="25%" align="left">
						<label id="change.schedular.occur" name="change.schedular.occur"
						ondblclick="callShowDiv(this);"><%=label.get("change.schedular.occur")%>
						</label><br>(DD-MM-YYYY)
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
					<td width="25%"><s:textfield name="changeCategory" size="30"
						maxlength="400" /></td>


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
					<td width="25%"><s:textfield name="impactChange" size="30"
						maxlength="400" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="risk.associated.change"
						name="risk.associated.change" ondblclick="callShowDiv(this);"><%=label.get("risk.associated.change")%></label>
					:</td>
					<td width="25%"><s:textfield name="riskAssociatedChange"
						size="30" maxlength="400" /></td>


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
							<label class="set" name="current.status.change"
							id="current.status.change" ondblclick="callShowDiv(this);"><%=label.get("current.status.change")%></label><font
							color="red"></font> :
					 	-->	
					</td>

					<td>
						<!-- 
							<s:select headerKey="" headerValue="--Select--" cssStyle="width:135" name="currentStatusChange" list="#{'O':'Open','C':'Closed'}" />						
						 -->
					</td>

					<td width="25%"><label id="status" name="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
					<td width="25%"><s:select disabled="true" cssStyle="width:145"
						name="status"
						list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected',
						'N':'Cancelled','F':'Forwarded','C':'Applied For Cancellation','X':'Feedback','Z':'Cancellation Rejected'}" />

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
					<td width="25%"><label id="optional.project.plan"
						name="optional.project.plan" ondblclick="callShowDiv(this);"><%=label.get("optional.project.plan")%></label>
					:</td>
					<td colspan="2"><s:textarea name="optionalProjectPlan"
						cols="70" rows="2"
						onkeypress="return imposeMaxLength(event, this, 400);" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_optionalProjectPlan','optional.project.plan','','400','400');"></td>



				</tr>

				<tr>
					<td width="25%"><label id="backout.plan.estimate"
						name="backout.plan.estimate" ondblclick="callShowDiv(this);"><%=label.get("backout.plan.estimate")%></label>
					:</td>
					<td width="25%"><s:textfield name="backoutPlanEstimate"
						size="30" maxlength="400" /></td>


					<td width="25%"><label id="who.perform.change"
						name="who.perform.change" ondblclick="callShowDiv(this);"><%=label.get("who.perform.change")%></label>
					:</td>
					<td width="25%"><s:textfield name="whoPerformChangeTesting"
						size="30" maxlength="400" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="how.change.tested"
						name="how.change.tested" ondblclick="callShowDiv(this);"><%=label.get("how.change.tested")%></label>
					:</td>
					<td width="25%"><s:textfield name="howChangeTested" size="30"
						maxlength="400" /></td>


					<td width="25%"><label id="update.optional"
						name="update.optional" ondblclick="callShowDiv(this);"><%=label.get("update.optional")%></label>
					:</td>
					<td width="25%"><s:textfield name="updateOptional" size="30"
						maxlength="400" /></td>

				</tr>

			</table>
			</td>
		</tr>


		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">


				<tr>
					<td width="100%">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">

						<tr>
							<td colspan="4"><b><label id="form.approval"
								name="form.approval" ondblclick="callShowDiv(this);"><%=label.get("form.approval")%></label>
							:</b></td>
						</tr>
						<tr>
							<td><label id="default.manager" name="default.manager"
								ondblclick="callShowDiv(this);"><%=label.get("default.manager")%></label>
							:</td>
							<s:hidden name="firstApproverCode" />
							<s:hidden name="firstApproverToken" />
							<s:iterator value="approverList">

								<td><s:hidden name="approverName" /><STRONG><s:property
									value="srNoIterator" /></STRONG> <s:property value="approverName" /></td>

							</s:iterator>

						</tr>
						<tr>
							<td width="20%"><label id="change.approval"
								name="change.approval" ondblclick="callShowDiv(this);"><%=label.get("change.approval")%></label>
							:<font color="red" id='ctrlHide'>*</font></td>
							<td width="60%" colspan="3"><s:textfield
								name="approverToken" size="25" theme="simple" readonly="true"
								cssStyle="background-color: #F2F2F2;" /> <s:textfield
								name="selectapproverName" size="71" theme="simple"
								readonly="true" cssStyle="background-color: #F2F2F2;" /> <s:hidden
								name="approverCode" /> <img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" id='ctrlHide'
								onclick="javascript:callsF9(500,325,'InformationSystemChangeRequest_f9Approver.action');">
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


		<input type="hidden" name="locationOption" id="locationOption"
			value='<s:property value="locationOption"/>' />
		<s:hidden name="checkRemoveUpload" />
		<s:hidden name="checkRemoveUploadOptional" />
	</table>
</s:form>


<script>
	callOnLoadFunctions();
	
	function uploadFile(fieldName)
{

	var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
	
	
	
	function sendforapprovalFun() {
	
		try
	{
		var changeTitleVar = document.getElementById('paraFrm_changeTitle').value;
		if(changeTitleVar=="")
		{
				alert("Please Enter Change Title.");
		  		document.getElementById('paraFrm_changeTitle').focus();
		 		return false;
		}
		var changeSchedularOccurVar = document.getElementById('paraFrm_changeSchedularOccur').value;
		if(changeSchedularOccurVar=="")
		{
				alert("Please Enter or Select when change scheduled to occur.");
		  		document.getElementById('paraFrm_changeSchedularOccur').focus();
		 		return false;
		}
		if(!document.getElementById('paraFrm_changeSchedularOccur').value==''){		
			var check1= validateDate('paraFrm_changeSchedularOccur', 'change.schedular.occur');
			if(!check1){
			return false;
		}
			}
		
		var uploadFileNameVar = document.getElementById('paraFrm_uploadFileName').value;
		if(uploadFileNameVar=="")
		{
				alert("Please upload Detailed plan of action with time estimates.");
		  		document.getElementById('paraFrm_uploadFileName').focus();
		 		return false;
		}
		
		try {	
		var firstApproverCodeVar = document.getElementById('paraFrm_firstApproverCode').value;
		
		if(firstApproverCodeVar=="")
		{
			var selectapproverNameVar = trim(document.getElementById('paraFrm_selectapproverName').value);
			if(selectapproverNameVar == "")
			{
				alert("Please Select Approver.");
		  		document.getElementById('paraFrm_selectapproverName').focus();
		 		return false;
		 	}	
		 	
		}
		} catch(e) 
		{
			alert("Exception occured in draft function : "+e);
		}
		
			
	}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
		
			

		var con = confirm('Do you really want to send this application for approval?');
	 	
	 	if(con) {
	 	
	 		document.getElementById('paraFrm_status').value = 'P';	
			document.getElementById('paraFrm').target = "_self";
    		document.getElementById('paraFrm').action='InformationSystemChangeRequest_sendForApprovalFunction.action';
			document.getElementById('paraFrm').submit();
		}
	}

	function draftFun()
{	
	try
	{
		var changeTitleVar = document.getElementById('paraFrm_changeTitle').value;
		if(changeTitleVar=="") {
			alert("Please Enter Change Title.");
		  	document.getElementById('paraFrm_changeTitle').focus();
		 	return false;
		}
		
		var changeSchedularOccurVar = document.getElementById('paraFrm_changeSchedularOccur').value;
		if(changeSchedularOccurVar=="") {
			alert("Please Enter or Select when change scheduled to occur.");
		  	document.getElementById('paraFrm_changeSchedularOccur').focus();
		 	return false;
		} else {
			var check1= validateDate('paraFrm_changeSchedularOccur', 'change.schedular.occur');
			if(!check1){
				return false;
			}
		}
		 
		 
		var uploadFileNameVar = document.getElementById('paraFrm_uploadFileName').value;
		if(uploadFileNameVar=="")
		{
				alert("Please upload Detailed plan of action with time estimates.");
		  		document.getElementById('paraFrm_uploadFileName').focus();
		 		return false;
		}
		
		
		
		try {	
		var firstApproverCodeVar = document.getElementById('paraFrm_firstApproverCode').value;
		
		if(firstApproverCodeVar=="")
		{
			var selectapproverNameVar = trim(document.getElementById('paraFrm_selectapproverName').value);
			if(selectapproverNameVar == "")
			{
				alert("Please Select Approver.");
		  		document.getElementById('paraFrm_selectapproverName').focus();
		 		return false;
		 	}	
		 	
		}
		} catch(e) 
		{
			alert("Exception occured in draft function : "+e);
		}
		
		
		
			
	}catch(e)
	{
		alert("Exception occured in draft function : "+e);
	}
		
				
		
			
		document.getElementById('paraFrm_status').value = 'D';	
			document.getElementById('paraFrm').target = "_self";
  			document.getElementById('paraFrm').action = 'InformationSystemChangeRequest_draftFunction.action';
			document.getElementById('paraFrm').submit();
					
		  
	}
	
	

function backtolistFun()  {
	document.getElementById('paraFrm').target = "_self";
    document.getElementById('paraFrm').action = 'InformationSystemChangeRequest_back.action';
	document.getElementById('paraFrm').submit();
}
	
function printFun() {	
	window.print();
}

function deleteFun() {
	var con = confirm('Do you want to delete the record(s) ?');
 	
 	if(con) {
		document.getElementById('paraFrm').target = "_self";
     		document.getElementById('paraFrm').action = 'InformationSystemChangeRequest_delete.action';
		document.getElementById('paraFrm').submit();
	}
}
	
function reportFun()  {
	alert("No Record To Display Report ");
}

function cancelApprovedFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="InformationSystemChangeRequest_cancel.action";
	  	document.getElementById('paraFrm').submit();  
}

function resetFun() {
		document.getElementById('paraFrm').target = "_self";     	
  		document.getElementById('paraFrm').action = 'InformationSystemChangeRequest_reset.action';
     	document.getElementById('paraFrm').submit();
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
		document.getElementById('paraFrm').action = 'InformationSystemChangeRequest_viewUploadedFile.action?uploadFileName='+uploadFileName;
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
		document.getElementById('paraFrm').action = 'InformationSystemChangeRequest_viewUploadedFile.action?uploadFileName='+uploadFileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
function feedbackFun() {
		var comments = trim(document.getElementById('paraFrm_comments').value);
		if (comments =="") {
			alert("Please enter your " + document.getElementById('comments').innerHTML.toLowerCase());
			return false;
		}
		var con = confirm('Do you really want to send Feedback?');
	 	if(con) {
				document.getElementById('paraFrm').target = "_self";
		  		document.getElementById('paraFrm').action = 'InformationSystemChangeRequest_feedbackFunction.action';
				document.getElementById('paraFrm').submit();
		}
}

function reopenFun() {
	var reOpenComments = trim(document.getElementById('paraFrm_reOpenComments').value);
	if (reOpenComments =="") {
		alert("Please enter your " + document.getElementById('reOpenCommentsLabel').innerHTML.toLowerCase());
		return false;
	}
	var con = confirm('Do you really want to reopen this application?');
	if(con) {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'InformationSystemChangeRequest_reopenFunction.action';
		document.getElementById('paraFrm').submit();
	}
}
	
</script>