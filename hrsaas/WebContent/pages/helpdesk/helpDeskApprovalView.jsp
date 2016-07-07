<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="HelpDeskApproval" validate="true" id="paraFrm" theme="simple">
		<%
			String radioChk = "";
			try {
				radioChk = (String) request.getAttribute("radioStatus");

			} finally {
				if (radioChk == null) {
					radioChk = "";
				}
			}
		%>
	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
		<s:hidden name="hAppFor" />
		<s:hidden name="myPageClosed" />
		<s:hidden name="myPage" />
		<s:hidden name="reqToken" />
		<s:hidden name="requestId" />
		<s:hidden name="checkRemoveUpload" />
		<s:hidden name="dataPath"   />
		<s:hidden name="uploadFlag" />
		<s:hidden name="isManagerApproval" />
		<s:hidden name="isAssetRequest" id="paraFrm_isAssetRequest"/>
		<s:hidden name="radioCheck" value="<%=radioChk%>" id="paraFrm_radioCheck"/>
		<s:hidden name="reqManagerStatus" />
		<s:hidden name="reqEmpStatus" />
		<s:hidden name="listType" />
		<s:hidden name="source" id="source" />
		<s:hidden name="approveRejectStatus" id="paraFrm_approveRejectStatus"/>
		 
		<tr>
			<td valign="bottom" class="txt" colspan="4">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Helpdesk Request Approval</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%" align="right">
					<font color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="approverCommentFlag">
			<tr>
				<td colspan="4">
				<table class="formbg" width="100%">
					<tr>
						<td width="21%" colspan="3"><label class="set" name="apprComments"
							id="apprComments" ondblclick="callShowDiv(this);"><%=label.get("apprComments")%></label>
						<font color="red">*</font>:</td>
						<td><s:textarea theme="simple" cols="70" rows="3"
							name="approverComments" /></textarea></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>

		<!-- EMPLOYEE DETAILS TABLE STARTS-->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td width="21%"><s:hidden name="empId" />
					<s:hidden name="helpcode" />
					<label id="initiator" name="initiator" onDblClick="callShowDiv(this);"><%=label.get("initiator")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1"><s:property	value="empToken" /></td>
					<td colspan="2"><s:property	value="empName" /></td>
				</tr>
				<tr>
					<td><label class="set" id="branch"
						name="branch" onDblClick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td><s:hidden name="branchCode" />
					<s:property	value="branchName" /></td>
					<td width="20%"><label class="set" id="department"
						name="department" onDblClick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td width="30%"><s:hidden name="deptId" />
					<s:property	value="deptName" /></td>
				</tr>	
				<tr>
					<td><label class="set"
						id="designation" name="designation"
						onDblClick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td><s:hidden name="desgId" />
					<s:property	value="desgName" /></td>
					<td><label class="set" id="req.date"
						name="req.date" onDblClick="callShowDiv(this);"><%=label.get("req.date")%></label>
					<font color="red">*</font>:</td>
					<td><s:property	value="appDate" />
					<s:hidden name="newStatus" /></td>
				</tr>						
				<tr>
					<td><label class="set"
						id="contact" name="contact"
						onDblClick="callShowDiv(this);"><%=label.get("contact")%></label>
					:</td>
					<td>
					<s:textfield size="14"	name="reqEmpContactNo" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td><label class="set" id="extension"
						name="extension" onDblClick="callShowDiv(this);"><%=label.get("extension")%></label>
					<font color="red">*</font>:</td>
					<td><s:textfield size="25"	name="reqEmpExtensionNo" readonly="true"
						cssStyle="background-color: #F2F2F2;" />
					</td>
				</tr>							
				<tr>
					<td><label class="set" id="status"
						name="status" onDblClick="callShowDiv(this);"><%=label.get("status")%></label>
					:</td>
					<td><s:hidden name="hiddenStatus" /><s:textfield name="decodedReqStatus" readonly="true"
						cssStyle="background-color: #F2F2F2;"/></td>
					<td><label class="set" id="email"
						name="email" onDblClick="callShowDiv(this);"><%=label.get("email")%></label>
					<font color="red">*</font>:</td>
					<td><s:textfield size="25"	name="reqEmpEmailId" readonly="true"
						cssStyle="background-color: #F2F2F2;" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- EMPLOYEE DETAILS TABLE ENDS-->
		
		<tr>
			<td colspan="4">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td width="21%"><s:hidden name="requestForEmpId" id="paraFrm_requestForEmpId"/>
					<label id="request.for"	name="request.for" onDblClick="callShowDiv(this);"><%=label.get("request.for")%></label>
					<font color="red">*</font>:</td>
					<td colspan="3" align="left"><input type="radio" name="reqRadio" <%=radioChk.equals("S")?"checked":"" %> disabled="disabled">Self
					&nbsp;<input type="radio" name="reqRadio" <%=radioChk.equals("O")?"checked":"" %> disabled="disabled">Other
					&nbsp;<input type="radio" name="reqRadio" <%=radioChk.equals("C")?"checked":"" %> disabled="disabled">Client
					</td>
				</tr>
				<tr id="selfRow" style='display: <%= radioChk.equals("S")?"" :"none" %>;'>
					<td>Self :</td>
					<td ><s:hidden name="initiatorId" id="paraFrm_initiatorId"/>
					<s:property	value="initiatorToken" /></td>
					<td colspan"2"><s:property	value="initiatorName" /></td>
				</tr>
				<tr id="otherRow" style='display: <%= radioChk.equals("O")?"" :"none" %>;'>
					<td>Other :</td>
					<td><s:hidden name="otherEmpId" id="paraFrm_otherEmpId"/>
					<s:property	value="otherEmpToken" /></td>
					<td colspan"2">
					<s:property	value="otherEmpName" /></td>
				</tr>
				<tr id="clientRow" style='display: <%= radioChk.equals("C")?"" :"none" %>;'>
					<td>Client :</td>
					<td colspan="3">	<s:property	value="otherEmpName" /></td>
				</tr>
				<tr>
					<td><label class="set" id="subject"
						name="subject" onDblClick="callShowDiv(this);"><%=label.get("subject")%></label>
					:<font color="red"> * </font></td>
					<td colspan="3"><s:property	value="subject" /></td>
				</tr>
				<tr>
					<td><label class="set" id="req.dept"
						name="req.dept" onDblClick="callShowDiv(this);"><%=label.get("req.dept")%></label>
					:<font color="red"> * </font></td>
					<td colspan="3"><s:hidden name="reqDeptCode" /><s:property	value="reqDeptName" /></td>
				</tr>
				<tr>
					<td><label class="set" id="req.type"
						name="req.type" onDblClick="callShowDiv(this);"><%=label.get("req.type")%></label>
					:<font color="red"> * </font></td>
					<td><s:hidden name="reqTypeCode" /><s:property	value="reqType" /></td>
					<td width="20%"><label class="set"
						id="subreq.type" name="subreq.type"
						onDblClick="callShowDiv(this);"><%=label.get("subreq.type")%></label>
					:</td>
					<td width="30%"><s:property	value="subReqType" /> <s:hidden name="subReqTypeCode" /></td>
				</tr>
				<tr id="assetBlock" style='display: none'>
					<td colspan="4">
					<table width="100%" border="1" class="formbg">
						<tr>
							<td><label class="set" id="asset.type" name="asset.type"
								onDblClick="callShowDiv(this);"><%=label.get("asset.type")%></label>
							:</td>
							<td><s:property value="assetType" /> &nbsp;<s:hidden
								name="assetTypeCode" /></td>
							<td width="20%"><label class="set" id="asset.subtype"
								name="asset.subtype" onDblClick="callShowDiv(this);"><%=label.get("asset.subtype")%></label>
							:<font color="red"> * </font></td>
							<td width="30%"><s:textfield name="assetSubType"
								theme="simple" size="25" readonly="true" /> <s:hidden
								name="assetSubTypeCode" /><img id='ctrlHide' align="absmiddle"
								src="../pages/common/css/default/images/search2.gif"
								onclick="javascript:callsF9(500,325,'HelpDesk_f9SubAssetTypes.action'); "></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td><label class="set" id="comment"
						name="comment" onDblClick="callShowDiv(this);"><%=label.get("comment")%></label>
					:</td>
					<td colspan="3"><s:property	value="comments" /></td>
				</tr>
				<tr>
					<td colspan="4" >View File :</td>
				</tr>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" >
					<%	int cnt = 0;	int cnt1 = 0;	%>
					<s:iterator value="proofList" status="stat">
						<tr>
							<td><%=++cnt%>
								<s:hidden name="proofSrNo" /><s:hidden name="proofName" /> 
								<a	href="#" onclick="callDownload('<s:property value="proofName" />');">
								<s:property	value="proofName" /></a></td>
						</tr>
						<%	cnt1 = cnt;	%>
						</s:iterator>
						<%	cnt1 = 0; %>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- ACTIVITY LOG BEGINS -->
		<s:if test="logLength">
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">
				<tr>
					<td width="100%" colspan="3">
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="1">
						<tr>
							<td colspan="4" class="formhead" width="100%"><strong
								class="forminnerhead">Request History</strong></td>
						</tr>

						<tr>
						<td colspan="4">
						<table width="100%">
							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%"><b><label class="set"
											name="ser.no" id="ser.no" ondblclick="callShowDiv(this);"><%=label.get("ser.no")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="log.from" id="log.from"
											ondblclick="callShowDiv(this);"><%=label.get("log.from")%></label></b>
										</td>
										<td class="formth" width="20%"><b><label class="set"
											name="log.to" id="log.to"
											ondblclick="callShowDiv(this);"><%=label.get("log.to")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="log.status" id="log.status"
											ondblclick="callShowDiv(this);"><%=label.get("log.status")%></label></b>
										</td>
										<td class="formth" width="15%"><b><label class="set"
											name="log.date" id="log.date"
											ondblclick="callShowDiv(this);"><%=label.get("log.date")%></label></b>
										</td>
										<td class="formth" width="30%"><b><label class="set"
											name="log.comments" id="log.comments"
											ondblclick="callShowDiv(this);"><%=label.get("log.comments")%></label></b>
										</td>

									</tr>
									
										<%
										int logCn = 0;
										%>
										<s:iterator value="logList">
											<tr>
												<td class="sortableTD" width="5%" align="center"><%=++logCn%></td>
												<td class="sortableTD" width="20%" align="left"><s:property value="logFromEmp" /><s:hidden name="logFromEmp" /></td>
												<td class="sortableTD" width="20%" align="left"><s:property value="logToEmp" />&nbsp;<s:hidden name="logToEmp" /></td>
												<td class="sortableTD" width="15%" align="center"><s:property value="logStatus" />&nbsp;<s:hidden name="logStatus" /></td>
												<td class="sortableTD" width="15%" align="center"><s:property value="logDate" />&nbsp;<s:hidden name="logDate" /></td>
												<td class="sortableTD" width="30%" align="left"><s:property	value="logComments" />&nbsp;<s:hidden name="logComments" /></td>
											</tr>
										</s:iterator>
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		</s:if>
		<!-- ACTIVITY LOG ENDS -->
		<tr>
			<td colspan="6"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script>

	function approveFun(){
		if(!validateBlankField()){
				return false;
			}else{
			document.getElementById('paraFrm_approveRejectStatus').value="A";
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'HelpDeskApproval_approveRejSendBackRequestApplication.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function rejectFun(){
		if(!validateBlankField()){
				return false;
			}else{
			document.getElementById('paraFrm_approveRejectStatus').value="R";
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'HelpDeskApproval_approveRejSendBackRequestApplication.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function sendbackFun(){
		if(!validateBlankField()){
			return false;
		}else{
			document.getElementById('paraFrm_approveRejectStatus').value="B";
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'HelpDeskApproval_approveRejSendBackRequestApplication.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function validateBlankField(){
		var fields=["paraFrm_approverComments"];
	    var labels=["apprComments"];
	    var flag = ["enter"];
	    
	 	 if(!validateBlank(fields,labels,flag)){
	     	return false;
	     }
		return true;
	}

	function callDownload(templateName) {
	  	document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'HelpDeskApproval_viewUploadedFile.action?templateName=' + templateName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	function backFun(){
		document.getElementById('paraFrm').target = "_self";
		
		 
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else{
		document.getElementById('paraFrm').action = 'HelpDeskApproval_back.action';
		}
		document.getElementById('paraFrm').submit();
		 
	}
	

	function checkRadio(id){
		document.getElementById('paraFrm_radioCheck').value=id.value;

		if(document.getElementById('paraFrm_radioCheck').value=="S"){
			document.getElementById('otherRow').style.display="none";
			document.getElementById('selfRow').style.display="";
			document.getElementById('clientRow').style.display="none";
			document.getElementById('paraFrm_requestForEmpId').value = document.getElementById('paraFrm_helpdesk_empId').value;
			document.getElementById('paraFrm_initiatorId').value = document.getElementById('paraFrm_helpdesk_empId').value;
			document.getElementById('paraFrm_initiatorToken').value = document.getElementById('paraFrm_helpdesk_empToken').value;
			document.getElementById('paraFrm_initiatorName').value = document.getElementById('paraFrm_helpdesk_empName').value;
			document.getElementById('paraFrm_clientName').value="";
		}else if(document.getElementById('paraFrm_radioCheck').value=="O" ){
			document.getElementById('otherRow').style.display="";
			document.getElementById('selfRow').style.display="none";
			document.getElementById('clientRow').style.display="none";
			document.getElementById('paraFrm_requestForEmpId').value="";
			document.getElementById('paraFrm_initiatorId').value="";
			document.getElementById('paraFrm_initiatorToken').value="";
			document.getElementById('paraFrm_initiatorName').value="";
			document.getElementById('paraFrm_clientName').value="";
		}else if(document.getElementById('paraFrm_radioCheck').value=="C" ){
			document.getElementById('otherRow').style.display="none";
			document.getElementById('selfRow').style.display="none";
			document.getElementById('clientRow').style.display="";
			document.getElementById('paraFrm_requestForEmpId').value="";
			document.getElementById('paraFrm_initiatorId').value="";
			document.getElementById('paraFrm_initiatorToken').value="";
			document.getElementById('paraFrm_initiatorName').value="";
			document.getElementById('paraFrm_otherEmpId').value="";
			document.getElementById('paraFrm_otherEmpToken').value="";
			document.getElementById('paraFrm_otherEmpName').value="";
		}
		
	}
	
	
	
</script>


