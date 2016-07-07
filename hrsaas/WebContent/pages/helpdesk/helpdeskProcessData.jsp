<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="HelpDeskProcess" id="paraFrm" theme="simple"
	target="main" validate="true">
	<table class="formbg" width="100%">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Helpdesk
					Request Processing</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="myPageClosed" id="myPageClosed" />
		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="appliedFor" />
		<s:hidden name="listType" />
		<s:hidden name="fileFlag" />
		<s:hidden name="assetFlag" />
		<s:hidden name="escalatedFlag" />
		<s:hidden name="escalatedFromName" />
		<s:hidden name="escalatedTime" />
		<s:hidden name="src" id="src"/>

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
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
		<s:if test="escalatedFlag">
			<tr>
				<td width="100%">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="100%" colspan="4"><font color="red">Request
						has been escalated from '<s:property value='escalatedFromName' />'
						by <s:property value='escalatedTime' />.</font></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">
				<tr>
					<td width="100%" colspan="3">
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="1">

						<tr>
							<td colspan="4" class="formhead" width="100%"><strong
								class="forminnerhead">Initiator Details</strong></td>
						</tr>

						<tr>
							<s:hidden name="requestId" />
							<td colspan="1" width="26%"><label class="set" id="employee"
								name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font
								color="red">*</font> :</td>
							<td colspan="3" width="74%"><s:property value="empId" />- <s:property
								value="empName" /> <s:hidden name="empName" /><s:hidden
								name="empId" /><s:hidden name="empCode" /></td>
						</tr>

						<tr>
							<td colspan="1" width="27%"><label class="set" id="branch"
								name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td colspan="1" width="27%"><s:property value="branchName" />

							</td>
							<td colspan="1" width="23%"><label class="set"
								id="department" name="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td colspan="1" width="23%"><s:property value="deptName" />
							<s:hidden name="deptCode" /></td>
						</tr>

						<tr>
							<td colspan="1" width="27%"><label class="set"
								id="designation" name="designation"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
							<td colspan="1" width="27%"><s:property value="desgName" /></td>
							<td colspan="1" width="23%"><label class="set"
								id="request.date" name="request.date"
								ondblclick="callShowDiv(this);"><%=label.get("request.date")%></label>
							:</td>
							<td colspan="1" width="23%"><s:property value="requestDate" /><s:hidden
								name="requestDate" /><s:hidden name="requestDateTime" /></td>
						</tr>
						<tr>
							<td><label class="set" id="contact" name="contact"
								onDblClick="callShowDiv(this);"><%=label.get("contact")%></label>
							:</td>
							<td><s:property value="reqEmpContactNo" /></td>
							<td><label class="set" id="extension" name="extension"
								onDblClick="callShowDiv(this);"><%=label.get("extension")%></label>
							<font color="red">*</font>:</td>
							<td><s:property value="reqEmpExtensionNo" /></td>
						</tr>
						<tr>
							<td colspan="1" width="27%"><label class="set" id="status"
								name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
							:</td>
							<td colspan="1" width="27%"><s:property value='status' /> <s:hidden
								name="status" /></td>
							<td><label class="set" id="email" name="email"
								onDblClick="callShowDiv(this);"><%=label.get("email")%></label>
							<font color="red">*</font>:</td>
							<td><s:property value="reqEmpEmailId" /></td>
						</tr>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">
				<tr>
					<td width="100%" colspan="3">
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="1">
						<tr>
							<td colspan="4" class="formhead" width="100%"><strong
								class="forminnerhead">Request Details</strong></td>
						</tr>

						<tr>
							<td colspan="1" width="26%"><label class="set"
								id="request.for" name="request.for"
								ondblclick="callShowDiv(this);"><%=label.get("request.for")%></label>
							<s:property value="appliedFor" /> :</td>
							<s:if test="appliedFor=='Client'">
								<td colspan="3" width="74%"><s:property value="clientName" /></td>
							</s:if>
							<s:else>
								<td colspan="3" width="74%"><s:property value="empIdFor" />
								<s:property value="empNameFor" /> <s:hidden name="empCodeFor" /></td>
							</s:else>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label class="set" id="req.dept"
								name="req.dept" ondblclick="callShowDiv(this);"><%=label.get("req.dept")%></label>
							:</td>
							<td width="30%"><s:property value="applDeptName" /> <s:hidden
								name="applDeptCode" /></td>
							<td colspan="1" width="20%"><label class="set" id="req.type"
								name="req.type" ondblclick="callShowDiv(this);"><%=label.get("req.type")%></label>
							:</td>
							<td width="30%"><s:property value="reqType" /> <s:hidden
								name="reqTypeCode" /><s:hidden name="reqSubTypeCode" /></td>
						</tr>

						<tr>
							<td colspan="1" width="20%"><label class="set"
								id="req.subject" name="req.subject"
								ondblclick="callShowDiv(this);"><%=label.get("req.subject")%></label>
							:</td>
							<td colspan="1" width="30%"><s:property value="subject" />
							</td>
							<td colspan="1" width="20%"><label class="set"
								id="request.token" name="request.token"
								ondblclick="callShowDiv(this);"><%=label.get("request.token")%></label>
							:</td>
							<td colspan="1" width="30%"><s:property value="requestToken" />
							<s:hidden name="requestToken" /></td>
						</tr>
						<!-- 
						<s:if test="assetFlag">
						<tr>
							<td colspan="1" width="20%"><label class="set"
								id="asset.type" name="asset.type" ondblclick="callShowDiv(this);"><%=label.get("asset.type")%></label> :</td>
							<td width="30%"><s:property value="assetType" /> <s:hidden
								name="assetTypeCode" /></td>
							<td colspan="1" width="20%"><label class="set"
								id="asset.subType" name="asset.subType" ondblclick="callShowDiv(this);"><%=label.get("asset.subType")%></label> :</td>
							<td width="30%"><s:property value="assetSubType" /> <s:hidden
								name="assetSubTypeCode" /></td>
						</tr>
						
						<tr>
							<td colspan="1" width="20%"><label class="set"
								id="req.quantity" name="req.quantity" ondblclick="callShowDiv(this);"><%=label.get("req.quantity")%></label> :</td>
							<td width="30%"><s:property value="reqQuantity" /> </td>
							<td colspan="1" width="20%"></td>
							<td width="30%"></td>
						</tr>
						</s:if>
						 -->
						<tr>
							<td colspan="1" width="26%"><label class="set" id="req.desc"
								name="req.desc" ondblclick="callShowDiv(this);"><%=label.get("req.desc")%></label>
							:</td>
							<td colspan="3" width="74%"><s:property value="reqDesc" />
							</td>
						</tr>
						<tr>
							<td colspan="1" width="26%"><label class="set"
								id="view.files" name="view.files"
								ondblclick="callShowDiv(this);"><%=label.get("view.files")%></label>
							:</td>
							<td colspan="3" width="74%">
							<%
							int fCn = 0;
							%> <s:if test="fileFlag">
								<s:iterator value="fileList">
									<table cellpadding="0">
										<tr>
											<td><%=++fCn%>)<a href="#"
												onclick="return viewFile('<s:property value="fileName" />');">
											<font color="blue"><s:property value="fileName" /></font> </a></td>
										</tr>
									</table>
								</s:iterator>
							</s:if></td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1"
				class="formbg">
				<tr>
					<td width="100%" colspan="3">
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="1">
						<tr>
							<td colspan="4" class="formhead" width="100%"><strong
								class="forminnerhead">SLA Details</strong></td>
						</tr>

						<tr>
							<td colspan="4">
							<table width="100%">
								<tr>
									<td>
									<table width="100%" class="sorttable">
										<tr>
											<td class="formth" width="5%"><b><label class="set"
												name="sla.sno" id="sla.sno" ondblclick="callShowDiv(this);"><%=label.get("sla.sno")%></label></b>
											</td>
											<td class="formth" width="15%"><b><label class="set"
												name="sla.status" id="sla.status"
												ondblclick="callShowDiv(this);"><%=label.get("sla.status")%></label></b>
											</td>
											<td class="formth" width="30%"><b><label class="set"
												name="sla.duration" id="sla.duration"
												ondblclick="callShowDiv(this);"><%=label.get("sla.duration")%></label></b>
											</td>
											<td class="formth" width="15%"><b><label class="set"
												name="sla.actualDuration" id="sla.actualDuration"
												ondblclick="callShowDiv(this);"><%=label.get("sla.actualDuration")%></label></b>
											</td>

										</tr>

										<%
										int cn = 0;
										%>
										<s:iterator value="slaList">
											<tr>
												<td class="sortableTD" width="10%" align="center"><%=++cn%></td>
												<td class="sortableTD" width="10%" align="center"><s:property
													value="slaStatus" /><s:hidden name="slaStatus" /></td>
												<td class="sortableTD" width="25%"><s:property
													value="slaDuration" />&nbsp;<s:hidden name="slaDuration" /></td>
												<td class="sortableTD" width="30%"><s:property
													value="slaActualDuration" />&nbsp;<s:hidden
													name="slaActualDuration" /></td>
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
		<s:if test="logLength">
			<tr>
				<td colspan="3">
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
													name="log.sno" id="log.sno" ondblclick="callShowDiv(this);"><%=label.get("log.sno")%></label></b>
												</td>
												<td class="formth" width="20%"><b><label
													class="set" name="log.from" id="log.from"
													ondblclick="callShowDiv(this);"><%=label.get("log.from")%></label></b>
												</td>
												<td class="formth" width="20%"><b><label
													class="set" name="log.to" id="log.to"
													ondblclick="callShowDiv(this);"><%=label.get("log.to")%></label></b>
												</td>
												<td class="formth" width="15%"><b><label
													class="set" name="log.status" id="log.status"
													ondblclick="callShowDiv(this);"><%=label.get("log.status")%></label></b>
												</td>
												<td class="formth" width="15%"><b><label
													class="set" name="log.date" id="log.date"
													ondblclick="callShowDiv(this);"><%=label.get("log.date")%></label></b>
												</td>
												<td class="formth" width="30%"><b><label
													class="set" name="log.comments" id="log.comments"
													ondblclick="callShowDiv(this);"><%=label.get("log.comments")%></label></b>
												</td>

											</tr>

											<%
											int logCn = 0;
											%>
											<s:iterator value="logList">
												<tr>
													<td class="sortableTD" width="5%" align="center"><%=++logCn%></td>
													<td class="sortableTD" width="20%" align="left"><s:property
														value="logFromEmp" /><s:hidden name="logFromEmp" /></td>
													<td class="sortableTD" width="20%" align="left"><s:property
														value="logToEmp" />&nbsp;<s:hidden name="logToEmp" /></td>
													<td class="sortableTD" width="15%" align="center"><s:property
														value="logStatus" />&nbsp;<s:hidden name="logStatus" /></td>
													<td class="sortableTD" width="15%" align="center"><s:property
														value="logDate" />&nbsp;<s:hidden name="logDate" /></td>
													<td class="sortableTD" width="30%" align="left"><s:property
														value="logComments" />&nbsp;<s:hidden name="logComments" /></td>
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
		<s:if test="listType=='pending'">
			<s:if test="escalatedFlag"></s:if>
			<s:else>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="1"
						class="formbg">
						<tr>
							<td width="100%" colspan="3">
							<table width="98%" border="0" align="center" cellpadding="2"
								cellspacing="1">
								<tr>
									<td colspan="4" class="formhead" width="100%"><strong
										class="forminnerhead">Request Process Details</strong></td>
								</tr>

								<s:if test="status=='Open'">
									<tr>
										<td colspan="1" width="20%"><label class="set"
											id="process.status" name="process.status"
											ondblclick="callShowDiv(this);"><%=label.get("process.status")%></label>
										: <font color="red">*</font></td>
										<td width="30%"><s:select name="processStatus"
											onchange="callAssetTr(this);"
											list="statusMap" /></td>
										<td colspan="1" width="20%"></td>
										<td width="30%"></td>
									</tr>
								</s:if>
								
								<s:elseif test="status=='InProcess'">
									<tr>
										<td colspan="1" width="20%"><label class="set"
											id="process.status" name="process.status"
											ondblclick="callShowDiv(this);"><%=label.get("process.status")%></label>
										: <font color="red">*</font></td>
										<td width="30%"><s:select name="processStatus"
											onchange="callAssetTr(this);"
											list="statusMap" /></td>
										<td colspan="1" width="20%"></td>
										<td width="30%"></td>
									</tr>
								</s:elseif>
								<s:elseif test="status=='Waiting For Parts'">
									<tr>
										<td colspan="1" width="20%"><label class="set"
											id="process.status" name="process.status"
											ondblclick="callShowDiv(this);"><%=label.get("process.status")%></label>
										: <font color="red">*</font></td>
										<td width="30%"><s:select name="processStatus"
											onchange="callAssetTr(this);"
											list="statusMap" /></td>
										<td colspan="1" width="20%"></td>
										<td width="30%"></td>
									</tr>
								</s:elseif>
								
								<s:elseif test="status=='Waiting For Approval'">
									<tr>
										<td colspan="1" width="20%"><label class="set"
											id="process.status" name="process.status"
											ondblclick="callShowDiv(this);"><%=label.get("process.status")%></label>
										: <font color="red">*</font></td>
										<td width="30%"><s:select name="processStatus"
											onchange="callAssetTr(this);"
											list="statusMap" /></td>
										<td colspan="1" width="20%"></td>
										<td width="30%"></td>
									</tr>
								</s:elseif>
								<!--<s:if test="assetFlag">
						<tr id='Asset_TR'>
							<td colspan="1" width="20%">
							<td colspan="3" width="80%"><input type="button" class="token" onclick="assignAsset();" value='Assign Asset' /></td>
						</tr>
						</s:if>
						-->

								<tr id='Assign_TR'>
									<td colspan="1" width="20%"><label class="set"
										id="assign.to" name="assign.to"
										ondblclick="callShowDiv(this);"><%=label.get("assign.to")%></label>
									: <font color="red">*</font></td>
									<td width="30%" colspan="3"><s:hidden name="assignToCode" /><s:textfield
										name="assignToId" readonly="true" size="10" /><s:textfield
										name="assignToName" readonly="true" size="30" /><img
										src="../pages/images/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="callsF9(500,325,'HelpDeskProcess_f9AssignTo.action');"></td>
								</tr>

								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="process.comments" name="process.comments"
										ondblclick="callShowDiv(this);"><%=label.get("process.comments")%></label>
									: <font color="red">*</font></td>
									<td width=70% " colspan="3"><s:textarea
										name="processComments" rows="3" cols="75" /></td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</s:else>
		</s:if>
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
function viewFile(fileName)
	{
		if(fileName==" "){
			alert("proof document not uploaded.");
			return false;
		}
		else{
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "HelpDeskProcess_viewFile.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		}
	}
callAssetTr();
function callAssetTr(){
var obj=document.getElementById('paraFrm_processStatus').value;

		if(obj=="I"){
		
			document.getElementById('Assign_TR').style.display='';
			//document.getElementById('Asset_TR').style.display='none';
		}else{ if(obj=="R"){
		
			document.getElementById('Assign_TR').style.display='none';
			
			//document.getElementById('Asset_TR').style.display='';
		}else {
		
			document.getElementById('Assign_TR').style.display='none';
			//document.getElementById('Asset_TR').style.display='none';
			}
			document.getElementById('paraFrm_assignToId').value="";
			document.getElementById('paraFrm_assignToName').value="";
			document.getElementById('paraFrm_assignToCode').value="";
		}
}
  function backtolistFun(){
  	try {
  	
  		if(document.getElementById('src').value=='mymessages') {
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		} else if(document.getElementById('src').value=='myservices') {
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else if(document.getElementById('paraFrm_listType').value=="closed"){
  	    document.getElementById('paraFrm').action="HelpDeskProcess_getResolvedList.action";
  	     }else{
  	    document.getElementById('paraFrm').action="HelpDeskProcess_input.action";
  	    }
			document.getElementById('paraFrm').submit();
  	}catch(e) {
  		alert("Back >>"+e);
  	}
  }
  ///Added by Nilesh D 5th Jan 2012
  function reportFun()
  {
  callReport('HelpDeskProcess_genReport.action');
  
  }
  
  
	function resetFun(){
		document.getElementById('paraFrm_processStatus').value="";
		document.getElementById('paraFrm_processComments').value="";
		document.getElementById('paraFrm_assignToId').value="";
		document.getElementById('paraFrm_assignToName').value="";
		document.getElementById('paraFrm_assignToCode').value="";
		document.getElementById('Assign_TR').style.display='none';
	}
	function processFun(){
	
	var fields=["paraFrm_processStatus","paraFrm_assignToId","paraFrm_processComments"];
	var labels=["process.status","assign.to","process.comments"];
	
	var flags=["select","select","enter"];
		if(document.getElementById('paraFrm_processStatus').value!="I"){
			fields=["paraFrm_processStatus","paraFrm_processComments"];
			labels=["process.status","process.comments"];
			flags=["select","enter"];
		}
		if(!validateBlank(fields,labels,flags)){
			return false;
		}
		var conf=confirm('Do you really want to process the request?');
			 	if(!conf){
			 	return false;
			 	}
		document.getElementById('paraFrm').action='HelpDeskProcess_processRequest.action';
   		document.getElementById('paraFrm').submit();
		
	}
	function assignAsset(){
   		document.getElementById('paraFrm').action='HelpDeskProcess_showForAssignment.action';
   		document.getElementById('paraFrm').submit();
   		document.getElementById('paraFrm').target = "main";
	}
</script>

