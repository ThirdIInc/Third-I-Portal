<!-- Bhushan Dasare --><!-- Mar 17, 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="ReportRequestChange" name="ReportRequestChange" id="paraFrm" validate="true" target="main" theme="simple">
	<s:hidden name="dataPath" /><s:hidden name="addedFile" /><s:hidden name="requestId" /><s:hidden name="status" /><s:hidden name="listType" />
	<s:hidden name="itApproversExist" /><s:hidden name="showNextApprover" /><s:hidden name="showApproverComments" />
	<s:hidden name="userAManager" /><s:hidden name="userAITApprover" />

	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">Report Request Change</strong></td>
						<td width="4%" valign="middle" align="right" class="txt">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<s:if test='showNextApprover'>
						<tr>
							<td width="20%" colspan="2">
								<strong>
									<label id="next.approver" name="next.approver" ondblclick="callShowDiv(this);"><%=label.get("next.approver")%></label> :
								</strong>
							</td>
							<td width="80%" colspan="3" nowrap="nowrap">
								<s:hidden name="nextApproverId" />
								<s:textfield name="nextApproverToken" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2; width: 140px;" />
								<s:textfield name="nextApproverName" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2; width: 300px;" />
								<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="16" id='ctrlHide' 
								onclick="javascript:callsF9(500,325,'ReportRequestChangeApproval_f9NextApprover.action');">
							</td>
						</tr>
					</s:if>
					<tr>
						<td width="20%" colspan="2">
							<strong>
								<label id="approver.comments" name="approver.comments" ondblclick="callShowDiv(this);"><%=label.get("approver.comments")%></label>
							</strong>
						</td>
						<td width="80%" colspan="3">
							<s:if test='showApproverComments'>
								<s:textarea theme="simple" cols="70" rows="3" name="approverComments" id="approverComments" />
							</s:if>
						</td>
					</tr>
					<tr>
						<td width="10%" class="formth">Sr. No.</td>
						<td width="25%" class="formth">Approver Name</td>
						<td width="40%" class="formth">Comments</td>
						<td width="15%" class="formth">Approved Date</td>
						<td width="10%" class="formth">Status</td>
					</tr>
					<%
						int count = 0;
					%>
					<s:iterator value="approverCommentsList">
						<tr>
							<td class="sortableTD"><%=++count%></td>
							<td class="sortableTD"><s:property value="apprName" /></td>
							<td class="sortableTD"><s:property value="apprComments" /></td>
							<td class="sortableTD" align="center"><s:property value="apprDate" /></td>
							<td class="sortableTD"><s:property value="apprStatus" /></td>
						</tr>
					</s:iterator>
					<%
						if(count == 0) {
					%>
					<tr>
						<td width="100%" colspan="5" align="center">
							<font color="red">No Data To Display</font>
						</td>
					</tr>
					<%
						}
					%>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%" colspan="4">
							<strong>
								<label id="requestor.information" name="requestor.information" ondblclick="callShowDiv(this);"><%=label.get("requestor.information")%></label>
							</strong>
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="requestor.name" name="requestor.name" ondblclick="callShowDiv(this);"><%=label.get("requestor.name")%></label> :
						</td>
						<td width="80%" colspan="3">
							<s:hidden name="requestorId" />
							<s:property value="requestorToken" />&nbsp;<s:property value="requestorName" />
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="requestor.designation" name="requestor.designation" ondblclick="callShowDiv(this);"><%=label.get("requestor.designation")%></label> :
						</td>
						<td width="30%">
							<s:property value="requestorDesgn" />
						</td>
						<td width="20%">
							<label id="requestor.phone" name="requestor.phone" ondblclick="callShowDiv(this);"><%=label.get("requestor.phone")%></label> :
						</td>
						<td width="30%">
							<s:property value="requestorPhone" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%" colspan="4">
							<strong>
								<label id="requested.information" name="requested.information" ondblclick="callShowDiv(this);"><%=label.get("requested.information")%></label>
							</strong>
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="requested.type" name="requested.type" ondblclick="callShowDiv(this);"><%=label.get("requested.type")%></label> :
						</td>
						<td width="30%">
							<s:property value="requestType" />
						</td>
						<td width="20%">
							<label id="requested.customer" name="requested.customer" ondblclick="callShowDiv(this);"><%=label.get("requested.customer")%></label> :
						</td>
						<td>
							<s:property value="customer" />
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="report.title" name="report.title" ondblclick="callShowDiv(this);"><%=label.get("report.title")%></label> :
						</td>
						<td width="80%" colspan="3">
							<s:property value="reportTitle" />
						</td>
					</tr>
					<tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%" colspan="4">
							<strong>
								<label id="report.change.details" name="report.change.details" ondblclick="callShowDiv(this);"><%=label.get("report.change.details")%></label>
							</strong>
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="report.details" name="report.details" ondblclick="callShowDiv(this);"><%=label.get("report.details")%></label> :
						</td>
						<td width="80%" colspan="3">
							<s:property value="details" />
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="report.attached.file" name="report.attached.file" ondblclick="callShowDiv(this);"><%=label.get("report.attached.file")%></label> :
						</td>
						<td width="80%" colspan="2">
							<a href="#" id="ctrlShow" onclick="openAttachedFile()" style="cursor: hand; color: blue;">
								<u><label id="attachedFile" /></u>
							</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="20%">
							<strong>
								<label id="completed.by" name="completed.by" ondblclick="callShowDiv(this);"><%=label.get("completed.by")%></label> :
							</strong>
						</td>
						<td width="30%">
							<s:hidden name="initiatorId" /><s:property value="initiatorName" />
						</td>
						<td width="20%">
							<strong>
								<label id="completed.on" name="completed.on" ondblclick="callShowDiv(this);"><%=label.get("completed.on")%></label> :
							</strong>
						</td>
						<td>
							<s:property value="completedOn" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	function openAttachedFile() {
		document.getElementById('paraFrm').action = 'ReportRequestChange_openAttachedFile.action';
		document.getElementById('paraFrm').submit();
	}
	
	setAddedFile();
	
	function setAddedFile() {
		var addedFile = document.getElementById('paraFrm_addedFile').value;
		document.getElementById('attachedFile').innerHTML = addedFile;
	}
	
	function authorizedsignoffFun() {
		var itApproversExist = document.getElementById('paraFrm_itApproversExist').value;
		
		if(itApproversExist == 'false') {
			alert('Application cannot be forwarded for approval as there is no IT Group approver is defined in the application.');
		} else {
			var conf = confirm('Do you really want to do authorized sign off?');
			
			if(conf) {
				document.getElementById('paraFrm').action = 'ReportRequestChangeApproval_authorizedSignOff.action';
				document.getElementById('paraFrm').submit();
			}
		}
	}
	
	function approveFun() {
		var conf = confirm('Do you really want to approve the application?');
			
		if(conf) {
			document.getElementById('paraFrm').action = 'ReportRequestChangeApproval_authorizedSignOff.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function forwardFun() {
		var nextApproverId = document.getElementById('paraFrm_nextApproverId').value;
		
		if(nextApproverId == '') {
			alert('Please select ' + document.getElementById('next.approver').innerHTML.toLowerCase());
		} else {
			var conf = confirm('Do you really want to forward application?');
			
			if(conf) {
				document.getElementById('paraFrm').action = 'ReportRequestChangeApproval_forward.action';
				document.getElementById('paraFrm').submit();
			}
		}
		
	}
	
	function rejectFun() {
		var conf = confirm('Do you really want to reject the application?');
			
		if(conf) {
			document.getElementById('paraFrm').action = 'ReportRequestChangeApproval_reject.action';
			document.getElementById('paraFrm').submit();
		}	
	}
	
	function sendbackFun() {
		var conf = confirm('Do you really want to send back the application?');
			
		if(conf) {
			document.getElementById('paraFrm').action = 'ReportRequestChangeApproval_sendBack.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function backtolistFun() {
		document.getElementById('paraFrm').action = 'ReportRequestChangeApproval_input.action';
		document.getElementById('paraFrm').submit();
	}
	function printFun() {	
	window.print();
	}
</script>