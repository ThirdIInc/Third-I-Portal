<!-- Bhushan Dasare --><!-- Mar 17, 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="ReportRequestChange" name="ReportRequestChange" id="paraFrm" validate="true" target="main" theme="simple">
	<s:hidden name="dataPath" /><s:hidden name="addedFile" /><s:hidden name="requestId" /><s:hidden name="status" /><s:hidden name="listType" />
	<s:hidden name="newRecord" /><s:hidden name="trackingNo" />

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
				<table width="100%">
					<tr>
						<td width="70%">
							<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
						</td>
						<td id="ctrlHide" align="right"><font color="red">*</font>Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<s:if test='status != "D"'>
			<tr>
				<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="100%" colspan="5">
								<strong>
									<label id="approver.comments" name="approver.comments" ondblclick="callShowDiv(this);"><%=label.get("approver.comments")%></label>
								</strong>
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
		</s:if>
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
							<s:if test="!generalFlag">
								<font id="ctrlHide" color="red">*</font>
							</s:if>
						</td>
						<td width="80%" colspan="3" nowrap="nowrap">
							<s:hidden name="requestorId" />
							<s:textfield name="requestorToken" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2; width: 175px;" />
							<s:textfield name="requestorName" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2; width: 450px;" />
							<s:if test="!generalFlag">
								<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="16" id='ctrlHide' 
								onclick="javascript:callsF9(500,325,'ReportRequestChange_f9Requestor.action');">
							</s:if>
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="requestor.designation" name="requestor.designation" ondblclick="callShowDiv(this);"><%=label.get("requestor.designation")%></label> :
							<font id="ctrlHide" color="red">*</font>
						</td>
						<td width="30%">
							<s:textfield name="requestorDesgn" theme="simple" cssStyle="width: 175px;" />
						</td>
						<td width="20%">
							<label id="requestor.phone" name="requestor.phone" ondblclick="callShowDiv(this);"><%=label.get("requestor.phone")%></label> :
							<font id="ctrlHide" color="red">*</font>
						</td>
						<td width="30%">
							<s:textfield name="requestorPhone" theme="simple" cssStyle="width: 175px;" />
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
							<font id="ctrlHide" color="red">*</font>
						</td>
						<td width="30%">
							<s:select name="requestType" headerKey="" headerValue="--Select--" list="#{'N':'New', 'C':'Change', 'D':'Delete'}" 
							cssStyle="width: 175px;" />
						</td>
						<td width="20%">
							<label id="requested.customer" name="requested.customer" ondblclick="callShowDiv(this);"><%=label.get("requested.customer")%></label> :
						</td>
						<td>
							<s:textfield name="customer" theme="simple" cssStyle="width: 175px;" />
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="report.title" name="report.title" ondblclick="callShowDiv(this);"><%=label.get("report.title")%></label> :
						</td>
						<td width="80%" colspan="3">
							<s:textfield name="reportTitle" theme="simple" cssStyle="width: 175px;" />
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
							<s:textarea name="details" rows="4" cols="70" onkeypress="return imposeMaxLength(event, this, 800);" />
							<img id='ctrlHide' src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple" 
							onclick="callWindow('paraFrm_details', 'report.details', '', '', '800', '800');">
						</td>
					</tr>
					<s:if test='status == "D" || status == "B"'>
						<tr>
							<td width="20%">
								<label id="report.attach.file" name="report.attach.file" ondblclick="callShowDiv(this);"><%=label.get("report.attach.file")%></label> :
							</td>
							<td width="30%">
								<s:textfield name="reportFile" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2; width: 275px; cursor: hand;" 
								onclick="uploadFile('reportFile');" />
							</td>
							<td width="50%" colspan="3">
								<input type="button" class="token" theme="simple" value="Select File" title="Select file" 
								onclick="uploadFile('reportFile');" />
								<input type="button" class="token" theme="simple" value="Attach File" title="Attach File" 
								onclick="attachFile();" />
							</td>
						</tr>
					</s:if>
					<tr>
						<td width="20%">
							<label id="report.attached.file" name="report.attached.file" ondblclick="callShowDiv(this);"><%=label.get("report.attached.file")%></label>
						</td>
						<td width="30%">
							<a href="#" id="ctrlShow" onclick="openAttachedFile()" style="cursor: hand; color: blue;">
								<u><label id="attachedFile" /></u>
							</a>
						</td>
						<s:if test='status == "D" || status == "B"'>
							<td width="50%" colspan="2">
								<a href="#" id="ctrlShow" onclick="removeFile()" style="cursor: hand; color: blue;">
									<u><label id="removeFile" /></u>
								</a>
							</td>
						</s:if>
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
								<label id="form.approval" name="form.approval" ondblclick="callShowDiv(this);"><%=label.get("form.approval")%></label>
							</strong>
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="my.approver" name="my.approver" ondblclick="callShowDiv(this);"><%=label.get("my.approver")%></label> :
						</td>
						<td width="80%" colspan="3">
							<s:hidden name="defApproverId" /><s:property value="defApproverToken" />&nbsp;<s:property value="defApproverName" />
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="change.approver" name="change.approver" ondblclick="callShowDiv(this);"><%=label.get("change.approver")%></label> :
						</td>
						<td width="80%" colspan="3" nowrap="nowrap">
							<s:hidden name="newApproverId" />
							<s:textfield name="newApproverToken" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2; width: 175px;" />
							<s:textfield name="newApproverName" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2; width: 450px;" />
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="16" id='ctrlHide' 
							onclick="javascript:callsF9(500,325,'ReportRequestChange_f9Approver.action');">
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
	function uploadFile(fieldName) {
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/helpdesk/fileUpload.jsp?path=' + dataPath + '&field=' + fieldName, '', 
		'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}

	function attachFile() {
		var reportFile = document.getElementById('paraFrm_reportFile').value;
		
		if(reportFile == '') {
			alert('Please select file.');
		} else {
			document.getElementById('attachedFile').innerHTML = reportFile;
			document.getElementById('removeFile').innerHTML = 'Remove File';
			document.getElementById('paraFrm_addedFile').value = reportFile;
			document.getElementById('paraFrm_reportFile').value = '';
		}
	}
	
	function openAttachedFile() {
		document.getElementById('paraFrm').action = 'ReportRequestChange_openAttachedFile.action';
		document.getElementById('paraFrm').submit();
	}
	
	function removeFile() {
		var conf = confirm('Do you really want to rmeove the file?');
			
		if(conf) {
			document.getElementById('paraFrm_addedFile').value = '';
			document.getElementById('attachedFile').innerHTML = '';
			document.getElementById('removeFile').innerHTML = '';
		}
	}
	
	function doValidation() {
		if(document.getElementById('paraFrm_requestorId').value == '') {
			alert('Please select ' + document.getElementById('requestor.name').innerHTML.toLowerCase());
			return false;
		}
		
		if(document.getElementById('paraFrm_requestType').value == '') {
			alert('Please select ' + document.getElementById('requested.type').innerHTML.toLowerCase());
			document.getElementById('paraFrm_requestType').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_requestorDesgn').value == '') {
			alert('Please enter ' + document.getElementById('requestor.designation').innerHTML.toLowerCase());
			document.getElementById('paraFrm_requestorDesgn').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_requestorPhone').value == '') {
			alert('Please enter ' + document.getElementById('requestor.phone').innerHTML.toLowerCase());
			document.getElementById('paraFrm_requestorPhone').focus();
			return false;
		}
		
		return true;
	}
	
	function draftFun() {
		if(doValidation()) {
			document.getElementById('paraFrm').action = 'ReportRequestChange_draft.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function sendforapprovalFun() {
		if(doValidation()) {
			var conf = confirm('Do you really want to send the application for approval?');
			
			if(conf) {
				document.getElementById('paraFrm').action = 'ReportRequestChange_sendForApproval.action';
				document.getElementById('paraFrm').submit();
			}
		}
	}
	
	setAddedFile();
	
	function setAddedFile() {
		var addedFile = document.getElementById('paraFrm_addedFile').value;
		document.getElementById('attachedFile').innerHTML = addedFile;
		
		var status = document.getElementById('paraFrm_status').value;
		
		if(addedFile != '' && (status == 'D' || status == 'B')) {
			document.getElementById('removeFile').innerHTML = 'Remove File';
		}
	}
	
	function resetFun() {
		document.getElementById('attachedFile').innerHTML = '';
		document.getElementById('removeFile').innerHTML = '';
		
		document.getElementById('paraFrm').action = 'ReportRequestChange_reset.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		var conf = confirm('Do you really want to delete the application?');
			
		if(conf) {
			document.getElementById('paraFrm').action = 'ReportRequestChange_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function backtolistFun() {
		document.getElementById('paraFrm').action = 'ReportRequestChange_input.action';
		document.getElementById('paraFrm').submit();
	}
	function printFun() {	
	window.print();
	}
	function imposeMaxLength(Event, Object, MaxLen) {
		return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
	}
</script>