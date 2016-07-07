<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="include/javascript/sorttable.js"></script>

<s:form action="HelpDeskApproval" validate="true" id="paraFrm"
	theme="simple">

	<s:hidden name="myPageAssigned" id="myPageAssigned" />
	<s:hidden name="myPageResolved" id="myPageResolved" />
	<s:hidden name="myPagePending" />
	<s:hidden name="myPageClosed" id="myPageResolved" />
	<s:hidden name="myPage" id="myPage" />

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Help 
					Desk Request Processing </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<s:hidden name="listType"/>
		
		<!-- COMMENTS FIELD FOR REOPEN AND CLOSE BEGINS -->
		<s:if test="resolvedFlag">
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td width="141" colspan="1"><label class="set" id="desc1"
						name="desc" onDblClick="callShowDiv(this);"><%=label.get("desc")%></label>
					<font color="red">*</font>:</td>
					<td colspan="3"><s:textarea theme="simple" name="reopenCloseDesc"
						value="%{reopenCloseDesc}" cols="60" rows="3" wrap="wrap"
						onkeyup="return callLength('isappcount2');" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_reopenCloseDesc','desc1','','500','500');">
					</td>
					<td width="175" colspan="1" nowrap="nowrap">Remaining Chars <s:textfield
						name="isappcount2" readonly="true" size="5" />(Max:500)</td>
					<td colspan="1"></td>
				</tr>


			</table>
			</td>
		</tr>
		</s:if>
		<!-- COMMENTS FIELD FOR REOPEN AND CLOSE ENDS -->
		
		
		<!-- EMPLOYEE DETAILS TABLE STARTS-->
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<s:if test="%{appType == 'self'}">
						<td width="141" colspan="1"><label id="employee"
							name="employee" onDblClick="callShowDiv(this);"><%=label.get("employee")%></label>
						:</td>
						<td width="22" colspan="1"><s:property
							value="helpdesk.empToken" /><s:hidden name="helpdesk.helpcode" /></td>
						<td colspan="1"></td>
						<td colspan="3" nowrap="nowrap"><s:property
							value="helpdesk.empName" /><s:hidden name="checkEdit" /></td>
						<td width="30" nowrap="nowrap" colspan="1"><s:hidden
							name="helpdesk.empId" /></td>
					</s:if>
					<s:if test="%{appType == 'others'}">
						<td width="141" colspan="1"><label id="employee"
							name="employee" onDblClick="callShowDiv(this);"><%=label.get("employee")%></label>
						:</td>
						<td width="22" colspan="1"><s:property
							value="helpdesk.empToken" /><s:hidden name="helpdesk.helpcode" /></td>
						<td colspan="1"></td>
						<td colspan="3" nowrap="nowrap"><s:property
							value="helpdesk.empName" /><s:hidden name="checkEdit" /></td>
						<td width="30" nowrap="nowrap" colspan="1"><s:hidden
							name="helpdesk.empId" /></td>
					</s:if>
					<s:if test="%{appType == 'client'}">
						<td width="141" colspan="1"><label id="client" name="client1"
							onDblClick="callShowDiv(this);"><%=label.get("client")%></label>
						:</td>
						<td width="22" colspan="4"><s:property
							value="helpdesk.clientName" /><s:hidden name="checkEdit" /></td>
						<td colspan="1" nowrap="nowrap"></td>
						<td width="30" nowrap="nowrap" colspan="1"></td>
					</s:if>
				</tr>

				<s:if test="%{appType == 'client'}"></s:if>
				<s:else>
					<tr>
						<td width="141" colspan="1"><label class="set" id="branch"
							name="branch" onDblClick="callShowDiv(this);"><%=label.get("branch")%></label>
						:</td>
						<td colspan="2"><s:property value="helpdesk.branchName" /></td>
						<td width="141" colspan="1"><label class="set"
							id="department" name="department" onDblClick="callShowDiv(this);"><%=label.get("department")%></label>
						:</td>
						<td colspan="2"><s:property value="helpdesk.deptName" /></td>
					</tr>
					<tr>
						<td width="141" colspan="1"><label class="set"
							id="designation" name="designation"
							onDblClick="callShowDiv(this);"><%=label.get("designation")%></label>
						:</td>
						<td colspan="2"><s:property value="helpdesk.desigName" /></td>
						<td width="102" colspan="1"></td>
						<td colspan="2"></td>
					</tr>
				</s:else>

				<s:if test="isReqApp">
					<tr>
						<td width="141" colspan="1"><label class="set" id="status"
							name="status" onDblClick="callShowDiv(this);"><%=label.get("status")%></label>
						:</td>
						<td colspan="2"><s:hidden name="hiddenStatus" /><s:property
							value="status" /></td>
						<td width="141" colspan="1"><label class="set" id="req.date"
							name="req.date" onDblClick="callShowDiv(this);"><%=label.get("req.date")%></label>
						:</td>
						<td colspan="2"><s:property value="helpdesk.appDate" /></td>
						<s:hidden name="newStatus" />
					</tr>
				</s:if>
				<s:else>


					<tr>
						<td width="141" colspan="1"><label class="set" id="req.date"
							name="req.date" onDblClick="callShowDiv(this);"><%=label.get("req.date")%></label>
						:</td>
						<td colspan="5"><s:property value="helpdesk.appDate" /></td>
					</tr>
				</s:else>

			</table>
			</td>
		</tr>
		<!-- EMPLOYEE DETAILS TABLE ENDS-->

		<!--  DETAILS BEGIN -->
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">

				<tr>
					<td width="141" colspan="1"><label class="set" id="subject"
						name="subject" onDblClick="callShowDiv(this);"><%=label.get("subject")%></label>
					:</td>
					<td colspan="5"><s:property value="subject" /></td>
				</tr>

				<tr>
					<td width="141" colspan="1" nowrap="nowrap"><label class="set" id="req.dept"
						name="req.dept" onDblClick="callShowDiv(this);"><%=label.get("req.dept")%></label>
					:</td>
					<td colspan="1"><s:hidden name="reqDeptCode" /> <s:property
						value="reqDeptName" /></td>
					<td width="102" colspan="1"></td>
					<td colspan="2"></td>
				</tr>



				<tr>
					<td width="141" colspan="1"><label class="set" id="req.type"
						name="req.type" onDblClick="callShowDiv(this);"><%=label.get("req.type")%></label>
					:</td>
					<td colspan="1"><s:property value="reqType" /> <s:hidden
						name="reqTypeCode" /></td>
					<td></td>
					<td width="141" colspan="1"><label class="set"
						id="subreq.type" name="subreq.type"
						onDblClick="callShowDiv(this);"><%=label.get("subreq.type")%></label>
					:</td>
					<td colspan="1"><s:property value="subReqType" /> <s:hidden
						name="subReqTypeCode" /></td>
					<td></td>
				</tr>


				<tr>
					<td width="141" colspan="1"><label class="set" id="comment"
						name="comment" onDblClick="callShowDiv(this);"><%=label.get("comment")%></label>
					:</td>
					<td colspan="3"><s:property value="comments" /></td>
					<td width="175" colspan="1" nowrap="nowrap"></td>
					<td colspan="1"></td>
				</tr>

				<tr>
					<td width="141" colspan="1">View File
					:</td>
					<td colspan="1"></td>
					<td colspan="1"><s:hidden name="uploadDoc" />
										<s:hidden name="uploadDocPath" /></td>
				</tr>
				
				<tr>
					<td colspan="7">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<%
							int cnt = 0;
							int cnt1 = 0;
						%>

						<s:iterator value="proofList" status="stat">

							<tr>

								<td width="80%"><%=++cnt%><s:hidden name="proofSrNo" /><s:hidden
									name="proofName" /> <a
									href="#"
									onclick="callDownload('<s:property value="proofName" />');"><s:property
									value="proofName" /></a></td>
								<td width="20%" ><a href="#" id="ctrlHide"
									onclick="callForRemoveUpload(<%=cnt%>);">Remove</a></td>
							</tr>
							<%
							cnt1 = cnt;
							%>
						</s:iterator>
						<%
						cnt1 = 0;
						%>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!--  DETAILS END -->

		<!-- SLA DETAILS TABLE BEGINS-->
		<s:if test="slaListFlag">
			<tr>
				<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="100%" colspan="7">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="sortable">
							<tr>
								<td width="100%" nowrap="nowrap" colspan="7"><strong>
								SLA Details : <s:property value="slaName" /></strong></td>

							</tr>
							<tr>
								<td class="formth" width="10%" height="22" valign="top">Sr.No.</td>
								<td class="formth" width="45%" height="22" valign="top">Status
								Category</td>
								<td class="formth" width="45%" height="22" valign="top">
								Duration</td>

							</tr>

							<%
							int i = 0;
							%>
							<%
							int k = 1;
							%>
							<s:iterator value="slaList" status="stat">
								<tr>
									<td width="10%" class="sortableTD"><%=k%><s:hidden
										name="srNo" value="%{<%=k%>}" /></td>
									<td width="45%" class="sortableTD"><s:property
										value="statusCategory" /><s:hidden name="statusCategory" /></td>
									<td width="45%" class="sortableTD"><s:property
										value="statusDuration" /><s:hidden name="statusDuration" /></td>
									
								</tr>
								<%
								k++;
								%>
							</s:iterator>
							<%
								i = k;
								k = 0;
							%>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>


		</s:if>
		<!-- SLA DETAILS TABLE ENDS -->
		
		<!-- REQUEST DETAILS TABLE BEGINS-->
		<s:if test="reqDtlListFlag">
			<tr>
				<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="100%" colspan="7">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="sortable">
							<tr>
								<td width="100%" nowrap="nowrap" colspan="7"><strong>
								Activity Log : </strong></td>

							</tr>
							<tr>
								<td class="formth" width="25%" height="22" valign="top">Previous
								Owner</td>
								<td class="formth" width="25%" height="22" valign="top">New
								Owner</td>
								<td class="formth" width="10%" height="22" valign="top">Status
								</td>
								<td class="formth" width="15%" height="22" valign="top">Date
								</td>
								<td class="formth" width="25%" height="22" valign="top">Comments
								</td>

							</tr>

							<%
							int ii = 0;
							%>
							<%
							int kk = 1;
							%>
							<s:iterator value="reqDtlList" status="stat">
								<tr>
									<s:hidden
										name="srNo" value="%{<%=kk%>}" />
									<td width="25%" class="sortableTD"><s:property
										value="prevOwner" /><s:hidden name="prevOwner" /></td>
									<td width="25%" class="sortableTD"><s:property
										value="newOwner" /><s:hidden name="newOwner" /></td>
									<td width="10%" class="sortableTD"><s:property
										value="chkStatus" /><s:hidden name="chkStatus" /></td>
									<td width="10%" class="sortableTD"><s:property
										value="chkDate" /><s:hidden name="chkDate" /></td>
									<td width="25%" class="sortableTD">&nbsp;<s:property
										value="chkComments" /><s:hidden name="chkComments" /></td>
									
								</tr>
								<%
								kk++;
								%>
							</s:iterator>
							<%
								ii = kk;
								kk = 0;
							%>

						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>


		</s:if>
		<!-- REQUEST DETAILS TABLE ENDS -->

		<!-- REQUIRED FOR APPROVAL -->
		<s:if test="resolvedFlag"></s:if><s:else>
		<s:if test="%{listType == 'assigned'}"></s:if><s:else>
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">

				<tr>
					<td width="141" colspan="1"><label class="set" id="status2"
						name="status" onDblClick="callShowDiv(this);"><%=label.get("status")%></label>
					:<font color="red"> * </font></td>
					<td colspan="5"><s:select name="newStatus"
						cssStyle="width:175" headerKey="" headerValue="--Select--"
						list="tmap" onchange="statusType();" /> <s:hidden
						name="statusOrder" /> <s:hidden name="hiddenStatus" /></td>
				</tr>

				<tr>
					<td width="141" colspan="1"><label class="set" id="desc"
						name="desc" onDblClick="callShowDiv(this);"><%=label.get("desc")%></label>
					<font color="red">*</font>:</td>
					<td colspan="3"><s:textarea theme="simple" name="description"
						value="%{description}" cols="60" rows="3" wrap="wrap"
						onkeyup="return callLength('isappcount1');" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_description','desc','','500','500');">
					</td>
					<td width="175" colspan="1" nowrap="nowrap">Remaining Chars <s:textfield
						name="isappcount1" readonly="true" size="5" />(Max:500)</td>
					<td colspan="1"></td>
				</tr>


			</table>
			</td>
		</tr>

		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td width="141" colspan="1"><label id="assign" name="assign"
						onDblClick="callShowDiv(this);"><%=label.get("assign")%></label> :</td>
					<td width="22" colspan="1"><s:textfield name="assToEmpToken"
						size="10" readonly="true" /></td>

					<td colspan="2" nowrap="nowrap"><s:textfield size="50"
						name="assToEmpName" readonly="true" /></td>
					<td nowrap="nowrap" colspan="1" width="30%"><s:hidden
						name="assToEmpCode" /><img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="14" width="14"
						onClick="javascript:callsF9(500,325,'HelpDesk_f9AssignEmployee.action');">
					</td>
					<td></td>
				</tr>
			</table>
			</td>
		</tr>
		</s:else></s:else>

<s:hidden name="dataPath"   />

	</table>
</s:form>

<script>
	function processFun(){
		var statField = document.getElementById('status2').innerHTML.toLowerCase();
		var newStatus = trim(document.getElementById('paraFrm_newStatus').value);
		if(newStatus==""){
			alert("Please select "+statField);
			document.getElementById('paraFrm_newStatus').focus();
			return false;
		}
		var descr = document.getElementById('paraFrm_description').value;
		if(descr==""){
			alert("Please enter "+document.getElementById('desc').innerHTML.toLowerCase());
			document.getElementById('paraFrm_description').focus();
			return false;
		}
		if(descr.length > 500){
			alert("Maximum length of "+document.getElementById('desc').innerHTML.toLowerCase()+
			" is 500 characters.");
			return false;
		}
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ="HelpDesk_process.action"; 
		document.getElementById('paraFrm').submit();
	}
	
	function backtolistFun(){
		document.getElementById("paraFrm").target="main";
		document.getElementById("paraFrm").action="HelpDesk_backToApprovalList.action";
		document.getElementById("paraFrm").submit();
	}
	
	function reopenrequestFun(){
		var descr = document.getElementById('paraFrm_reopenCloseDesc').value;
		if(descr==""){
			alert("Please enter "+document.getElementById('desc1').innerHTML.toLowerCase());
			document.getElementById('paraFrm_reopenCloseDesc').focus();
			return false;
		}
		if(descr.length > 500){
			alert("Maximum length of "+document.getElementById('desc1').innerHTML.toLowerCase()+
			" is 500 characters.");
			return false;
		}
		for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'reopenrequest') {
				document.all[i].disabled=true;
			}
		}
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ='HelpDesk_reopenClose.action?checkStatus=O'; 
		document.getElementById('paraFrm').submit();
	}
	
	function closerequestFun(){
		var descr = document.getElementById('paraFrm_reopenCloseDesc').value;
		if(descr==""){
			alert("Please enter "+document.getElementById('desc1').innerHTML.toLowerCase());
			document.getElementById('paraFrm_reopenCloseDesc').focus();
			return false;
		}
		if(descr.length > 500){
			alert("Maximum length of "+document.getElementById('desc1').innerHTML.toLowerCase()+
			" is 500 characters.");
			return false;
		}
		for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'closerequest') {
				document.all[i].disabled=true;
			}
		}
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ='HelpDesk_reopenClose.action?checkStatus=C'; 
		document.getElementById('paraFrm').submit();
	}
	
	function callDownload(templateName) {
	  	document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'HelpDesk_viewUploadedFile.action?templateName=' + templateName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	


</script>