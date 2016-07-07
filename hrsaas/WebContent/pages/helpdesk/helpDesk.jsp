<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="HelpDesk" validate="true" id="paraFrm" theme="simple">
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
		<s:hidden name="level" />
		<s:hidden name="listType" />
	<table width="100%" border="0" cellpadding="1" cellspacing="1"
		class="formbg" height="100%">
		<tr>
			<td valign="bottom" class="txt" colspan="4">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">My Helpdesk Request</strong></td>
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
		<s:if test="reopenFlag">
			<tr>
				<td colspan="4">
				<table class="formbg" width="100%">
					<tr>
						<td width="21%" colspan="3"><label class="set" name="openCloseComments"
							id="openCloseComments" ondblclick="callShowDiv(this);"><%=label.get("openCloseComments")%></label>
						<font color="red">*</font>:</td>
						<td><s:textarea theme="simple" cols="70" rows="3"
							name="reopenComments"  id="ctrlShow"/></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td width="21%"><s:hidden name="helpdesk.empId" /> <s:hidden
						name="helpdesk.helpcode" /> <label id="initiator"
						name="initiator" onDblClick="callShowDiv(this);"><%=label.get("initiator")%></label>
					<font color="red">*</font>:</td>
					<td colspan="3"><s:textfield name="helpdesk.empToken"
						size="14" readonly="true" cssStyle="background-color: #F2F2F2;" /><s:textfield
						size="81" name="helpdesk.empName" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
				</tr>
				<tr>
					<td><label class="set" id="branch"
						name="branch" onDblClick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td><s:hidden name="helpdesk.branchCode" />
					<s:textfield size="14"	name="helpdesk.branchName" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td width="20%"><label class="set" id="department"
						name="department" onDblClick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td width="30%"><s:hidden name="helpdesk.deptId" />
					<s:textfield size="25"
						maxlength="40" name="helpdesk.deptName" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
				</tr>	
				<tr>
					<td><label class="set"
						id="designation" name="designation"
						onDblClick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td><s:hidden name="helpdesk.desgId" />
					<s:textfield size="14"	name="helpdesk.desgName" readonly="true"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td><label class="set" id="req.date"
						name="req.date" onDblClick="callShowDiv(this);"><%=label.get("req.date")%></label>
					:</td>
					<td><s:textfield size="25" name="helpdesk.appDate"
						readonly="true" cssStyle="background-color: #F2F2F2;"
						onblur="return validateDate('paraFrm_helpdesk_appDate','Date');"
						onkeypress="return numbersWithHiphen();" maxlength="10" /> <s:hidden name="newStatus" /></td>
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
					:</td>
					<td><s:textfield size="25"	name="reqEmpExtensionNo" readonly="true"
						cssStyle="background-color: #F2F2F2;" />
					</td>
				</tr>							
				<tr>
					<td><label class="set" id="status"
						name="status" onDblClick="callShowDiv(this);"><%=label.get("status")%></label>
					:</td>
					<td><s:hidden name="hiddenStatus" /><s:textfield name="decodedReqStatus" readonly="true"
						cssStyle="background-color: #F2F2F2;" size="14"/></td>
					<td><label class="set" id="email"
						name="email" onDblClick="callShowDiv(this);"><%=label.get("email")%></label>
					:</td>
					<td><s:textfield size="25"	name="reqEmpEmailId" readonly="true"
						cssStyle="background-color: #F2F2F2;" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td width="21%"><s:hidden name="requestForEmpId" id="paraFrm_requestForEmpId"/>
					<label id="request.for"	name="request.for" onDblClick="callShowDiv(this);"><%=label.get("request.for")%></label>
					<font color="red">*</font>:</td>
					<td colspan="3" align="left"><input type="radio" name="reqRadio" <%=radioChk.equals("S")?"checked":"" %> onclick="checkRadio(this);" value="S">Self
					&nbsp;<input type="radio" name="reqRadio" <%=radioChk.equals("O")?"checked":"" %> onclick="checkRadio(this);" value="O">Other
					&nbsp;<input type="radio" name="reqRadio" <%=radioChk.equals("C")?"checked":"" %> onclick="checkRadio(this);" value="C">Client
					</td>
				</tr>
				<tr id="selfRow" style='display: <%= radioChk.equals("S")?"" :"none" %>;'>
					<td>Self <font color="red">*</font>:</td>
					<td colspan="3"><s:hidden name="initiatorId" id="paraFrm_initiatorId"/>
					<s:textfield name="initiatorToken" id="paraFrm_initiatorToken" size="14" readonly="true" />
					<s:textfield size="51" name="initiatorName" id="paraFrm_initiatorName" readonly="true"/></td>
				</tr>
				<tr id="otherRow" style='display: <%= radioChk.equals("O")?"" :"none" %>;'>
					<td>Other <font color="red">*</font>:</td>
					<td colspan="3"><s:hidden name="otherEmpId" id="paraFrm_otherEmpId"/>
					<s:textfield name="otherEmpToken" id="paraFrm_otherEmpToken" size="14" readonly="true" />
					<s:textfield size="51" name="otherEmpName" id="paraFrm_otherEmpName" readonly="true"/>
					<img src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="14" width="14" onClick="javascript:callsF9(500,325,'HelpDesk_f9otherEmployee.action');" id='ctrlHide'></td>
				</tr>
				<tr id="clientRow" style='display: <%= radioChk.equals("C")?"" :"none" %>;'>
					<td>Client <font color="red">*</font>:</td>
					<td colspan="3"><s:textfield size="71"	name="clientName" id="paraFrm_clientName"/></td>
				</tr>
				<tr>
					<td><label class="set" id="req.dept"
						name="req.dept" onDblClick="callShowDiv(this);"><%=label.get("req.dept")%></label>
					<font color="red"> * </font>:</td>
					<td colspan="3"><s:hidden name="reqDeptCode" /> <s:textfield
						size="21" name="reqDeptName" readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" theme="simple" class="iconImage"
						onclick="callSearch('f9deptaction');" id='ctrlHide'></td>
				</tr>
				<tr>
					<td><label class="set" id="req.type"
						name="req.type" onDblClick="callShowDiv(this);"><%=label.get("req.type")%></label>
					<font color="red"> * </font>:</td>
					<td>
					<s:hidden name="reqTypeCode" />
					<s:textfield name="reqType" theme="simple"
						size="21" readonly="true" />
						<img id='ctrlHide' align="absmiddle"
						src="../pages/common/css/default/images/search2.gif"
						onclick="callSearch('f9reqType');"></td>
				</tr>
				<tr>
					<td><label class="set"
						id="subreq.type" name="subreq.type"
						onDblClick="callShowDiv(this);"><%=label.get("subreq.type")%></label>
					<font color="red"> * </font>:</td>
					<td>
					<s:hidden name="subReqTypeCode" />
					<s:textfield name="subReqType" size="21" readonly="true"/>
					<img id='ctrlHide' align="absmiddle"
						src="../pages/common/css/default/images/search2.gif"
						onclick="callSearch('f9subReqType');"></td>
				</tr>
				<tr>
					<td><label class="set" id="subject"
						name="subject" onDblClick="callShowDiv(this);"><%=label.get("subject")%></label>
					<font color="red"> * </font>:</td>
					<td colspan="3"><s:textfield size="70" name="subject"
						maxlength="240" /></td>
				</tr>
				<tr id="assetBlock" style='display: none'>
					<td colspan="4">
					<table width="100%" border="0" class="formbg">
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
					<td><label class="set" id="comment" name="comment"
						onDblClick="callShowDiv(this);"><%=label.get("comment")%></label>
					:</td>
					<td colspan="3"><s:textarea theme="simple" name="comments"
						value="%{comments}" cols="85" rows="5" wrap="wrap"
						onkeyup="return callLength('isappcount');" /><s:if
						test="isReqApp">
						<img src="../pages/images/zoomin.gif" height="12"
							align="absmiddle" width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_comments','comment','','500','500');"
							id='ctrlHide'>
					</s:if></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="3"><s:if test="isReqApp">Remaining Chars <s:textfield
							name="isappcount" readonly="true" size="5" />(Max:500)</s:if></td>
				</tr>
				<s:if test="isReqApp">
				<tr>
					<td>Upload File :</td>
					<td colspan="3"><s:textfield name="uploadFileName"  readonly="true" size="30"/>
						<s:hidden name="userUploadFileName" />
						<input name="Upload" type="button" class="token" value="Browse"
						onclick="uploadFile('uploadFileName');" />&nbsp; 
					<input type="button" name="" value=" Upload File"
						class=" add" onclick="return callUpload();" /></td>
				</tr>
				</s:if><s:else>
				<tr>
					<td>View File :</td>
				</tr>
				</s:else>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" >
					<%
						int cnt = 0;
						int cnt1 = 0;
					%>
					<s:iterator value="proofList" status="stat">
						<tr>
							<td><%=++cnt%>
								<s:hidden name="proofSrNo" /><s:hidden name="proofName" /> 
								<a	href="#" onclick="callDownload('<s:property value="proofName" />');">
								<s:property	value="proofName" /></a></td>
							<td colspan="3" width="70%" align="left"><a href="#" id="ctrlHide"
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
	<s:hidden name="source" id="source" />
	
</s:form>

<script>
	onload();
 	function onload(){
 	
 		if(document.getElementById('paraFrm_radioCheck').value=="S"){
			document.getElementById('selfRow').style.display="";
			document.getElementById('paraFrm_requestForEmpId').value = document.getElementById('paraFrm_helpdesk_empId').value;
			document.getElementById('paraFrm_initiatorId').value = document.getElementById('paraFrm_helpdesk_empId').value;
			document.getElementById('paraFrm_initiatorToken').value = document.getElementById('paraFrm_helpdesk_empToken').value;
			document.getElementById('paraFrm_initiatorName').value = document.getElementById('paraFrm_helpdesk_empName').value;
		}
 	}
 	//statusType();
 	function statusType(){
		var val=document.getElementById('paraFrm_newStatus').value;
		//VALUE = 2 CODE FOR ASSIGN
		//CHANGE VALUE AS PER THE CODE IN DATABASE FOR "ASSIGN" STATUS
		//alert(val);
		if(val=="2"){
 			document.getElementById('divFwd').style.display='';
    	}else{ 
     		document.getElementById('divFwd').style.display ='none';
  			document.getElementById('paraFrm_assignToEmpName').value ='';
   			document.getElementById('paraFrm_assignToEmpCode').value ='';
     	}
	}

	function formValidate(){
	try{
		var appFor = document.getElementById('paraFrm_radioCheck').value;
		
		if(appFor == ""){
			alert("Please select request for.");
			return false;
		}
		if(appFor=="O"){
			var emp = document.getElementById('paraFrm_otherEmpName').value;
		if(emp==""){
			alert("Please select other Employee.");
			document.getElementById('paraFrm_otherEmpName').focus();
			return false;
		}
	}
	if(appFor=="C"){
		var client = document.getElementById('paraFrm_clientName').value;
		if(client==""){
			alert("Please enter client name.");
			document.getElementById('paraFrm_clientName').focus();
			return false;
		}
	}
	
	var statField = "";
	try{
		statField = document.getElementById('status').innerHTML.toLowerCase();
	}catch(e){
		statField = document.getElementById('status1').innerHTML.toLowerCase();
	}
	var subField = document.getElementById('subject').innerHTML.toLowerCase();
	var dptField = document.getElementById('req.dept').innerHTML.toLowerCase();
	var reqField = document.getElementById('req.type').innerHTML.toLowerCase();
	var subreqField = document.getElementById('subreq.type').innerHTML.toLowerCase();
	
	if(trim(document.getElementById('paraFrm_reqDeptCode').value)==""){
		alert("Please select "+dptField);
		document.getElementById('paraFrm_reqDeptName').focus();
		return false;
	}
	
	if(trim(document.getElementById('paraFrm_reqTypeCode').value)==""){
		alert("Please select "+reqField);
		document.getElementById('paraFrm_reqType').focus();
		return false;
	}
	
	if(trim(document.getElementById('paraFrm_subReqTypeCode').value)==""){
		alert("Please select "+subreqField);
		document.getElementById('paraFrm_subReqType').focus();
		return false;
	}
	
	if(trim(document.getElementById('paraFrm_subject').value)==""){
		alert("Please enter "+subField);
		document.getElementById('paraFrm_subject').focus();
		return false;
	}
	
	var comments = document.getElementById('paraFrm_comments').value;
	if(comments.length > 500){
		alert("Maximum length of "+document.getElementById('comment').innerHTML.toLowerCase()+
		" is 500 characters.");
		return false;
	}
	return true;
	}catch(e){
		alert(e);
		return false;
	}
}


	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'HelpDesk_reset.action';
		document.getElementById('paraFrm').submit();
	}
	
	function backFun(){
	 if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		} else {
		
		document.getElementById('paraFrm').action = 'HelpDesk_back.action';
		}
	
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').submit();
	}
	function reopenrequestFun(){
		if( trim(document.getElementById('ctrlShow').value)==""){
			alert("Please enter reopen request comments");
			return false;
		}else{
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'HelpDesk_reopenRequest.action';
			document.getElementById('paraFrm').submit();
		}
	}
	function closerequestFun(){

	if( trim(document.getElementById('ctrlShow').value)==""){
			alert("Please enter close request comments");
			return false;
		}else{
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = 'HelpDesk_closeRequest.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	/*function submitrequestFun(){ 
		if(!formValidate()){
			return false;
		}
	 		
		for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'sendtoprocess') {
				//alert(document.all[i]);
				//document.all[i].value="Saving...";
				document.all[i].disabled=true;
			}
		}
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ='HelpDesk_save.action?checkStatus=O'; 
		document.getElementById('paraFrm').submit();
	}*/
	function submitrequestFun(){
	try{ 
		if(!formValidate()){
			return false;
		}
		//document.getElementById('sendtoprocess').disabled=true;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ='HelpDesk_save.action?checkStatus=O'; 
		document.getElementById('paraFrm').submit();
		}
		catch(e){
		alert(e);
		}
	}	
		
		
		
	function draftFun(){ 
		if(!formValidate()){
			return false;
			}
		 
		document.getElementById('draft').disabled=true;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action ='HelpDesk_save.action?checkStatus=D'; 
		document.getElementById('paraFrm').submit();
	}
	
	
	
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

function saveValidate(buttonType){
   var hiddenStatus  = document.getElementById('paraFrm_helpdesk_hiddenStatus').value;
   var status        = document.getElementById('paraFrm_helpdesk_status').value;
   var chkDesc       = document.getElementById('paraFrm_helpdesk_description').value;
      
   var fieldName     = ["paraFrm_helpdesk_empName","paraFrm_helpdesk_applText","paraFrm_helpdesk_appDate",
   						"paraFrm_helpdesk_subject","paraFrm_helpdesk_description"];
   var lableName     = ["employee name","apply to department","helpdesk date","subject","description"];
   
   var flag      	 = ["select", "select", "enter", "enter", "enter"];
	
  if(!document.getElementById('paraFrm_helpdesk_helpcode').value=="" && buttonType == "save"){
  		alert("Please click on update button to update the record");
  		return false;
  	}else{
  		if(document.getElementById('paraFrm_helpdesk_helpcode').value == "" && buttonType == "update"){
	  		alert("Please select a record to update");
	  		return false;
  		}
  	}
  	
  	if(status != "O"){
  		alert("You can not update the record !");
  		return false;
  	}
   
   if(!checkMandatory(fieldName,lableName, flag))return false;
  		
   if(!validateDate('paraFrm_helpdesk_appDate','Application Date'))return false;
  	
   if(chkDesc.length >200){
   		alert("Description too large, maximum 200 characters allowed");
   		return false;
   }
   return true;
}



function saveView(date, time, stat){
	var levelFlag = document.getElementById('paraFrm_helpdesk_levelFlag').value;
	if(document.getElementById('paraFrm_helpdesk_hiddenStatus').value!='O'){
   		alert("You can not update the application! \n As the application is already been closed.");
   		return false;
   }if(levelFlag == "true"){
   		alert("You can not update the application !\n As the application is already been assigned.");
   		return false;
   }
	var fieldName1 = ["paraFrm_helpdesk_assToEmpName", "paraFrm_helpdesk_callAttndDatet", "paraFrm_helpdesk_time"];
	var labelName1 = ["Assign TO Employee", date, time];
	if(!checkMandatory(fieldName1,labelName1))return false;
	if(!validateDate("paraFrm_helpdesk_callAttndDatet", date)){
		return false;
	}
	var status = document.getElementById('paraFrm_helpdesk_status').value;
	document.getElementById("paraFrm_helpdesk_hideStatus").value = status;
	if(stat=='C'){
		if(status=='O'){
			alert('Please change the status from open to close');
			document.getElementById('paraFrm_helpdesk_status').focus();
			return false;
		}
	}
	//if(!checkTimeWithCurrentTime("helpBean.time_View"))return false
}
function callUpdate(){
 var status        = document.getElementById('paraFrm_helpdesk_status').value;
 var level         = document.getElementById('paraFrm_helpdesk_level').value;
 
  	if(document.getElementById('paraFrm_helpdesk_helpcode').value==""){
  	alert("Please select a record to update  !");
  	return false;
  	}
  	if(status!="" && status!= 'O'){
   		alert("You can not modify the application");
   		return false;
   } 
  	
  	if(level != 1){
   		alert("You can not modify the application");
   		return false;
   } 
  	return true;
}

function checkDelete(){
	var status        = document.getElementById('paraFrm_helpdesk_status').value;
	if(status != 'O'){
		alert('You can delete the record !');
		return false;
	}
	if(!callDelete('paraFrm_helpdesk_helpcode'))return false;
}


	function callLength(type){ 
 		if(type=='isappcount'){
			var cmt =document.getElementById('paraFrm_comments').value;
			var remain = 500- eval(cmt.length);
			document.getElementById('paraFrm_isappcount').value = remain;
			if(eval(remain)< 0){
				document.getElementById('paraFrm_comments').style.background = '#FFFF99';
			}else 
				document.getElementById('paraFrm_comments').style.background = '#FFFFFF';
		}
		if(type=='isappcount1'){
			var cmt =document.getElementById('paraFrm_description').value;
			var remain = 500- eval(cmt.length);
			document.getElementById('paraFrm_isappcount1').value = remain;
			if(eval(remain)< 0){
				document.getElementById('paraFrm_description').style.background = '#FFFF99';
			}else 
				document.getElementById('paraFrm_description').style.background = '#FFFFFF';
		}
 	}
 	
 	
 	function uploadFile(fieldName) {
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/helpdesk/fileUpload.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
	
	function callUpload()
	{
		var uploadFile = document.getElementById('paraFrm_uploadFileName').value;
		
		if(uploadFile=="")
		{
			alert("Please Upload file");
			return false;
		}
		document.getElementById('paraFrm').action = 'HelpDesk_addMultipleFiles.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
		return true;
	}
	
	
	function callDownload(templateName) {
	  	document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'HelpDesk_viewUploadedFile.action?templateName=' + templateName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	function callForRemoveUpload(id)
    {
    	var conf=confirm("Are you sure !\n You want to Remove this record ?");
		if(conf){
	  		document.getElementById('paraFrm_checkRemoveUpload').value=id;
	  		document.getElementById('paraFrm').target="_self";
	  		document.getElementById("paraFrm").action="HelpDesk_removeUploadFile.action";
			document.getElementById("paraFrm").submit();
		}	
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
			document.getElementById('paraFrm_otherEmpId').value="";
			document.getElementById('paraFrm_otherEmpToken').value="";
			document.getElementById('paraFrm_otherEmpName').value="";
			
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
	
	function callSearch(action) {
		
		if(action == "f9deptaction"){
			document.getElementById("paraFrm_reqTypeCode").value = "";
			document.getElementById("paraFrm_reqType").value = "";
			document.getElementById("paraFrm_subReqType").value = "";
			document.getElementById("paraFrm_subReqTypeCode").value = "";
		}
		if(action == "f9reqType"){
			if(document.getElementById("paraFrm_reqDeptName").value == ""){
				alert("Please select department.");
				return false;
			}
		}
		if(action == "f9subReqType"){
			if(document.getElementById("paraFrm_reqType").value == ""){
				alert("Please select request category.");
				return false;
			}
		}
		
	
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'HelpDesk_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
</script>