<!-- Bhushan Dasare -->
<!-- Feb 28, 2011 -->
<!-- Changes in Display Fields of Employee -Nilesh D on 24th May -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ApplicationSecurityRequest"
	name="ApplicationSecurityRequest" id="paraFrm" validate="true"
	target="main" theme="simple">
	<s:hidden name="applicationId" />
	<s:hidden name="dataPath" />
	<s:hidden name="addedFile" />
	<s:hidden name="listType" />
	<s:hidden name="status" />
	<s:hidden name="pageForDraft" id="pageForDraft" />
	<s:hidden name="pageForPending" id="pageForPending" />
	<s:hidden name="pageForSendBack" id="pageForSendBack" />
	<s:hidden name="pageForApprove" id="pageForApprove" />
	<s:hidden name="pageForApproveCancel" id="pageForApproveCancel" />
	<s:hidden name="pageForPendingCancel" id="pageForPendingCancel" />
	<s:hidden name="pageForRejected" id="pageForRejected" />
	<s:hidden name="pageForRejectedCancel" id="pageForRejectedCancel" />

	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="92%" class="txt"><strong class="text_head">Application
					Security Request</strong></td>
					<td width="4%" valign="middle" align="right" class="txt"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td id="ctrlHide" align="right"><font color="red">*</font>Indicates
					Required</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test='!(status == "P" || status == "D")'>
			<tr>
				<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%" colspan="5"><strong> <label
							id="approver.comments" name="approver.comments"
							ondblclick="callShowDiv(this);"><%=label.get("approver.comments")%></label>
						</strong></td>
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
							<td class="sortableTD" align="center"><s:property
								value="apprDate" /></td>
							<td class="sortableTD"><s:property value="apprStatus" /></td>
						</tr>
					</s:iterator>
					<%
							if(count == 0) {
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
	<!-- Added by Nilesh for Tracking Number 2nd Dec 2011-->
	<s:if test ="trackingFlag">
	<tr>
					<td width="100%" height="22">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1" class="formbg">
						
						<tr>
							<td colspan="1" width="18%">Tracking Number : 
							</td>
							<td width="80%" >
							<s:property value="trackingNo"/><s:hidden name="trackingNo"/>
							</td>
						</tr>
					</table>
					</td>
					</tr></s:if>
	
	<!-- Ended by Nilesh for Tracking Number 2nd Dec 2011-->
	
	
	
	
	
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="20%"><label id="requested.date"
						name="requested.date" ondblclick="callShowDiv(this);"><%=label.get("requested.date")%></label>
					:</td>
					<td colspan="3"><s:hidden name="requestDate" /><s:property
						value="requestDate" /></td>
				</tr>
				<tr>
					<td width="20%"><label id="requested.type"
						name="requested.type" ondblclick="callShowDiv(this);"><%=label.get("requested.type")%></label>
					: <font id="ctrlHide" color="red">*</font></td>
					<td width="30%"><s:select name="requestType" headerKey=""
						headerValue="--Select--"
						list="#{'A':'Add', 'C':'Change', 'D':'Delete'}"
						cssStyle="width: 175px;" /></td>
					<td width="20%"><label id="requested.status"
						name="requested.status" ondblclick="callShowDiv(this);"><%=label.get("requested.status")%></label>
					:</td>
					<td><s:select name="status" cssStyle="width: 175px;"
						disabled="true"
						list="#{'D':'Draft', 'P':'Pending', 'B':'Sent Back', 
							'A':'Approved', 'R':'Rejected', 'F':'Forwarded', 'C':'Applied For Cancellation', 'X':'Cancellation Approved', 
							'Z':'Cancellation Rejected'}" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong class="text_head">
					<label id="manager.info" name="manager.info"
						ondblclick="callShowDiv(this);"><%=label.get("manager.info")%></label>
					</strong></td>
				</tr>
				<tr>
					<td width="20%"><label id="manager.name" name="manager.name"
						ondblclick="callShowDiv(this);"><%=label.get("manager.name")%></label>
					: <s:if test="!generalFlag">
						<font id="ctrlHide" color="red">*</font>
					</s:if></td>
					<td nowrap="nowrap" colspan="3"><s:hidden name="mgrId" /> <s:textfield
						name="mgrToken" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2; width: 175px;" /> <s:textfield
						name="mgrName" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2; width: 450px;" /> <s:if
						test="!generalFlag">
						<img src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlHide'
							onclick="javascript:callsF9(500,325,'ApplicationSecurityRequest_f9Manager.action');">
					</s:if></td>
				</tr>
				<tr>
					<td width="20%"><label id="manager.division"
						name="manager.division" ondblclick="callShowDiv(this);"><%=label.get("manager.division")%></label>
					: <font id="ctrlHide" color="red">*</font></td>
					<td width="30%"><s:textfield name="mgrDivision" theme="simple"
						cssStyle="width: 175px;" maxlength="90" /></td>
					<td width="20%"><label id="manager.designation"
						name="manager.designation" ondblclick="callShowDiv(this);"><%=label.get("manager.designation")%></label>
					: <font id="ctrlHide" color="red">*</font></td>
					<td><s:textfield name="mgrDesignation" theme="simple"
						cssStyle="width: 175px;" maxlength="90" /></td>
				</tr>
				<tr>
					<td width="20%"><label id="manager.department"
						name="manager.department" ondblclick="callShowDiv(this);"><%=label.get("manager.department")%></label>
					: <font id="ctrlHide" color="red">*</font></td>
					<!--<td width="30%">
							<s:textfield name="mgrDepartment" theme="simple" cssStyle="width: 175px;" maxlength="90" />
						</td>
						-->
					<td width="25%" colspan="1"><s:hidden name="deptCode" /> <s:textfield
						name="mgrDepartment" cssStyle="width: 175px;" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'ApplicationSecurityRequest_f9deptNumber.action');"></td>


					<td width="20%"><label id="manager.city" name="manager.city"
						ondblclick="callShowDiv(this);"><%=label.get("manager.city")%></label>
					: <font id="ctrlHide" color="red">*</font></td>
					<td><s:textfield name="mgrCity" theme="simple"
						cssStyle="width: 175px;" maxlength="90" /></td>
				</tr>
				<tr>
					<td width="20%"><label id="manager.state" name="manager.state"
						ondblclick="callShowDiv(this);"><%=label.get("manager.state")%></label>
					: <font id="ctrlHide" color="red">*</font></td>
					<td width="30%"><s:textfield name="mgrState" theme="simple"
						cssStyle="width: 175px;" maxlength="90" /></td>
					<td width="20%"><label id="manager.pincode"
						name="manager.pincode" ondblclick="callShowDiv(this);"><%=label.get("manager.pincode")%></label>
					: <font id="ctrlHide" color="red">*</font></td>
					<td><s:textfield name="mgrPincode" theme="simple"
						cssStyle="width: 175px;" maxlength="6" /></td>
				</tr>
				<tr>
					<td width="20%"><label id="manager.workphone"
						name="manager.workphone" ondblclick="callShowDiv(this);"><%=label.get("manager.workphone")%></label>
					: <font id="ctrlHide" color="red">*</font></td>
					<td width="30%"><s:textfield name="mgrWorkPhone"
						theme="simple" cssStyle="width: 175px;" maxlength="18" /></td>
					<td width="20%"><label id="manager.ext" name="manager.ext"
						ondblclick="callShowDiv(this);"><%=label.get("manager.ext")%></label>
					:</td>
					<td><s:textfield name="mgrExt" theme="simple"
						cssStyle="width: 175px;" maxlength="8" /></td>
				</tr>
				<tr>
					<td width="20%"><label id="manager.fax" name="manager.fax"
						ondblclick="callShowDiv(this);"><%=label.get("manager.fax")%></label>
					:</td>
					<td width="30%"><s:textfield name="mgrFax" theme="simple"
						cssStyle="width: 175px;" maxlength="18" /></td>
					<td width="20%"><label id="manager.email" name="manager.email"
						ondblclick="callShowDiv(this);"><%=label.get("manager.email")%></label>
					: <font id="ctrlHide" color="red">*</font></td>
					<td><s:textfield name="mgrEmail" theme="simple"
						cssStyle="width: 175px;" maxlength="48"
						onblur="return validateEmail('mgrEmail');" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong class="text_head">
					<label id="employee.info" name="employee.info"
						ondblclick="callShowDiv(this);"><%=label.get("employee.info")%></label>
					</strong></td>
				</tr>
				<!--<tr>
						<td width="100%" colspan="4" nowrap="nowrap">
							<label id="employee.file.request" name="employee.file.request" ondblclick="callShowDiv(this);"><%=label.get("employee.file.request")%></label>
							<font id="ctrlHide" color="red">*</font>
							<s:select name="empFileRequest" list="#{'N':'No', 'Y':'Yes'}"  />
						</td>
					</tr>
					-->
				<s:hidden name="empFileRequest" value="N" />

				<tr id="fillEmpInfo">
					<td width="100%" colspan="4">
					<table width="100%" class="formbg" border="0">
						<tr>
							<td width="100%" colspan="4" id="ctrlHide"><strong
								class="text_head"> <label id="employee.provide.info"
								name="employee.provide.info" ondblclick="callShowDiv(this);"><%=label.get("employee.provide.info")%></label>
							</strong></td>
						</tr>

						<tr>
							<td width="20%"><label id="employee.type"
								name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
							: <font id="ctrlHide" color="red">*</font></td>
							<td width="30%"><s:hidden name="hdnEmpType" id="hdnEmpType" />
							<s:radio name="empType" list="#{'R':'Regular'}"
								onclick="enableExpiration();" />&nbsp;&nbsp; <s:radio
								name="empType" list="#{'T':'Temp/Contractor'}"
								onclick="enableExpiration();display()" />&nbsp;&nbsp; <s:radio
								name="empType" list="#{'V':'VWE'}" onclick="enableExpiration();" />
							</td>
							<td width="20%"><label id="employee.exp.date"
								name="employee.exp.date" ondblclick="callShowDiv(this);"><%=label.get("employee.exp.date")%></label>
							: <font color="red">*</font></td>
							<td><s:textfield name="empExpDate" theme="simple"
								cssStyle="width: 175px;" maxlength="10"
								onkeypress="return numbersWithHiphen();" /> <s:a
								href="javascript:NewCal('paraFrm_empExpDate','DDMMYYYY');">
								<span id="imgDate"> <img
									src="../pages/common/css/default/images/Date.gif" width="16"
									height="16" border="0" id='ctrlHide' /> </span>
							</s:a></td>
						</tr>

							<tr >
							<td colspan="1"  width="20%"><label id="employee.id"
								name="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
							: </td>


							<td nowrap="nowrap" colspan="1" >
							<s:hidden name="empId" /> <s:textfield name="empToken"
								theme="simple" size="15" maxlength="20" /> </div>
							</td>
							<td colspan="1">
							<label id="employee.name"
								name="employee.name" ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label>:<font id="ctrlHide" color="red">*</font>
							</td>
						<td width="25%" colspan="1">
						<s:textfield
								name="empName" theme="simple" cssStyle="width: 175px;" maxlength="100"/>
						
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="16" id='ctrlHide' 
										onclick="javascript:callsF9(500,325,'ApplicationSecurityRequest_f9Employee.action');"><div id="displayfield">
						</td>

						</tr>


						<!--<tr id="displayicon">
							<td width="20%"> 
						
									<label id="employee.name"
								name="employee.name" ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label>						
								
							: <font id="ctrlHide" color="red">*</font></td>


							<td nowrap="nowrap" colspan="3" style="vertical-align: middle;">
							<s:hidden name="empId" /> <s:textfield name="empToken"
								theme="simple" cssStyle="width: 175px;" /> <s:textfield
								name="empName" theme="simple" cssStyle=" width: 450px;" /></td>
						</tr>
						--><tr>
							<td width="20%"><label id="employee.designation"
								name="employee.designation" ondblclick="callShowDiv(this);"><%=label.get("employee.designation")%></label>
							: <font id="ctrlHide" color="red">*</font></td>
							<td width="20%"><s:textfield name="empDesignation"
								theme="simple" cssStyle="width: 175px;" maxlength="90" /></td>
							<td width="20%"><label id="copy.manager.email"
								name="copy.manager.email" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.email")%></label>
							:</td>
							<td><s:textfield name="copyMgrEmail" theme="simple"
								cssStyle="width: 175px;" maxlength="48"
								onblur="return validateEmail('copyMgrEmail');" /></td>
						</tr>
						<tr>
							<td width="20%"><label id="copy.manager.workphone"
								name="copy.manager.workphone" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.workphone")%></label>
							:</td>
							<td width="30%"><s:textfield name="copyMgrWorkPhone"
								theme="simple" cssStyle="width: 175px;" maxlength="18" /></td>
							<td width="20%"><label id="copy.manager.ext"
								name="copy.manager.ext" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.ext")%></label>
							:</td>
							<td><s:textfield name="copyMgrExt" theme="simple"
								cssStyle="width: 175px;" maxlength="8" /></td>
						</tr>
						<tr>
							<td width="20%"><label id="copy.manager.fax"
								name="copy.manager.fax" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.fax")%></label>
							:</td>
							<td width="80%" colspan="3"><s:textfield name="copyMgrFax"
								theme="simple" cssStyle="width: 175px;" maxlength="18" /></td>
						</tr>

						<tr>
							<td width="100%" colspan="4" id="ctrlHide"><strong
								class="text_head"> <label id="copy.manager.info"
								name="copy.manager.info" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.info")%></label>
							</strong></td>
						</tr>
						<tr id="ctrlHide">
							<td width="20%" align="center"><input type="button"
								class="token" value="Copy" onclick="copyManagerInfo();" /></td>
							<td colspan="3"><label id="copy.manager.note"
								name="copy.manager.note" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.note")%></label>
							</td>
						</tr>
						<tr>
							<td width="20%"><label id="copy.manager.division"
								name="copy.manager.division" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.division")%></label>
							: <font id="ctrlHide" color="red">*</font></td>
							<td width="30%" nowrap="nowrap"><s:textfield
								name="copyMgrDiv" theme="simple" cssStyle="width: 175px;"
								maxlength="90" /></td>
							<td width="20%"><label id="copy.manager.department"
								name="copy.manager.department" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.department")%></label>
							: <font id="ctrlHide" color="red">*</font></td>
							<!--<td nowrap="nowrap">
										<s:textfield name="copyMgrDept" theme="simple" cssStyle="width: 175px;" maxlength="90" />
									</td>
								-->
							<td width="25%" colspan="1"><s:hidden name="deptCode" /> <s:textfield
								name="copyMgrDept" cssStyle="width: 175px;" readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" width="16"
								height="15" class="iconImage" id='ctrlHide'
								onclick="javascript:callsF9(500,325,'ApplicationSecurityRequest_f9copyDeptNumber.action');"></td>


						</tr>
						<tr>
							<td width="20%"><label id="copy.manager.city"
								name="copy.manager.city" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.city")%></label>
							: <font id="ctrlHide" color="red">*</font></td>
							<td width="30%" nowrap="nowrap"><s:textfield
								name="copyMgrCity" theme="simple" cssStyle="width: 175px;"
								maxlength="90" /></td>
							<td width="20%"><label id="copy.manager.state"
								name="copy.manager.state" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.state")%></label>
							: <font id="ctrlHide" color="red">*</font></td>
							<td><s:textfield name="copyMgrState" theme="simple"
								cssStyle="width: 175px;" maxlength="90" /></td>
						</tr>
						<tr>
							<td width="20%"><label id="copy.manager.pincode"
								name="copy.manager.pincode" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.pincode")%></label>
							: <font id="ctrlHide" color="red">*</font></td>
							<td width="30%"><s:textfield name="copyMgrPincode"
								theme="simple" cssStyle="width: 175px;" maxlength="8" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr id="addAttachment">
					<td width="100%">
					<table width="100%" class="formbg">
						<tr>
							<td width="100%" colspan="4"><strong> <label
								id="employee.file.attach" name="employee.file.attach"
								ondblclick="callShowDiv(this);"><%=label.get("employee.file.attach")%></label>
							</strong></td>
						</tr>
						<tr id="ctrlHide">
							<td width="20%"><label id="employee.attach.here"
								name="employee.attach.here" ondblclick="callShowDiv(this);"><%=label.get("employee.attach.here")%></label>
							<font id="ctrlHide" color="red"></font></td>
							<td width="30%"><s:textfield name="empFile" theme="simple"
								readonly="true"
								cssStyle="background-color: #F2F2F2; width: 275px;" /></td>
							<td width="50%" colspan="3"><input type="button"
								class="token" theme="simple" value="Select File"
								title="Select file" onclick="uploadFile('empFile');" /> <input
								type="button" class="token" theme="simple" value="Attach File"
								title="Attach File" onclick="attachFile();" /></td>
						</tr>
						<tr>
							<td width="20%"><label id="employee.file.attached"
								name="employee.file.attached" ondblclick="callShowDiv(this);"><%=label.get("employee.file.attached")%></label>
							</td>
							<td width="80%" colspan="3"><a href="#" id="ctrlShow"
								onclick="openAttachedFile()" style="cursor: hand; color: blue;">
							<u><label id="attachedFile" /></u> </a></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="100%" colspan="4"><strong class="text_head">
					<label id="applications.required" name="applications.required"
						ondblclick="callShowDiv(this);"><%=label.get("applications.required")%></label>
					</strong></td>
				</tr>
				<tr>
					<td width="100%" colspan="4"><label id="applications.note"
						name="applications.note" ondblclick="callShowDiv(this);"><%=label.get("applications.note")%></label>
					</td>
				</tr>
				<tr>
					<td width="20%"><label id="applications.list"
						name="applications.list" ondblclick="callShowDiv(this);"><%=label.get("applications.list")%></label>
					: <font id="ctrlHide" color="red">*</font></td>
					<td width="80%" colspan="3" id="ctrlShow"><s:hidden
						name="hdnApplications" id="hdnApplications" /><s:hidden
						name="hdnSections" id="hdnSections" />
					<table width="100%">
						<%	try {
										if(request.getAttribute("applicationList") != null) {
											Object[][] applicationList = (Object[][])request.getAttribute("applicationList");
											int cnt = 1;
											for(int i = 0; i < applicationList.length; i++) {
												if(cnt == 1) {
								%>
						<tr>
							<%				}
								%>
							<td width="20%">
							<%					if(String.valueOf(applicationList[i][3]).equals("Y")) {
								%> <input type="checkbox"
								ext" name="<%=String.valueOf(applicationList[i][0])%>"
								checked="checked"
								onclick="addApplications(this, '<%=String.valueOf(applicationList[i][0])%>', 
														'<%=String.valueOf(applicationList[i][2]) %>');" />
							<%					} else {
								%> <input type="checkbox"
								ext" name="<%=String.valueOf(applicationList[i][0])%>"
								onclick="addApplications(this, '<%=String.valueOf(applicationList[i][0])%>', 
														'<%=String.valueOf(applicationList[i][2]) %>');" />
							<%					}
								%> <%=String.valueOf(applicationList[i][1])%></td>
							<%				if(cnt == 3) {
													cnt = 0;
								%>
						</tr>
						<%				}
												cnt++;
											}
											if(cnt != 1) {
								%>
						</tr>
						<%			}
										} else {
								%>
						<tr>
							<td>Please configure the application for security.</td>
						</tr>
						<%		}
									} catch(Exception e){}
								%>
					</table>
					</td>
				</tr>
				<tr id="AST">
					<td width="100%" colspan="4">
					<table width="100%" class="formbg">
						<tr>
							<td width="100%" colspan="4"><strong class="text_head">
							<label id="astea.required" name="astea.required"
								ondblclick="callShowDiv(this);"><%=label.get("astea.required")%></label>
							<font color="RED"> <label id="astea.required.note"
								name="astea.required.note" ondblclick="callShowDiv(this);"><%=label.get("astea.required.note")%></label>
							</font> </strong></td>
						</tr>
						<tr>
							<td width="20%"><label id="astea.workgroup"
								name="astea.workgroup" ondblclick="callShowDiv(this);"><%=label.get("astea.workgroup")%></label>
							: <font id="ctrlHide" color="RED">*</font></td>
							<td colspan="3"><s:select name="asteaWorkgroup" headerKey=""
								headerValue="--Select--" cssStyle="width: 175px;"
								onchange="displayWorkgroup()"
								list="#{'CCD':'Call Center Dispatch', 'CCHD':'Call Center Help Desk', 
										'FLD':'Field', 'FIN':'Finance', 'IT':'IT', 'LGS':'Logistics', 'NTSMGR':'NTS Manager', 'NTST':'NTS Tech'}" />
							</td>
						</tr>
						<tr id="FLD">
							<td width="100%" colspan="4">
							<table width="100%">
								<tr>
									<td width="100%" colspan="4"><br>
									</td>
								</tr>
								<tr>
									<td width="100%" colspan="4"><strong class="text_head">
									<label id="astea.field.workgroup.required"
										name="astea.field.workgroup.required"
										ondblclick="callShowDiv(this);"><%=label.get("astea.field.workgroup.required")%></label>
									</strong></td>
								</tr>
								<tr>
									<td width="20%"><label id="astea.field.role"
										name="astea.field.role" ondblclick="callShowDiv(this);"><%=label.get("astea.field.role")%></label>
									: <font id="ctrlHide" color="RED">*</font></td>
									<td width="30%"><s:select name="asteaFieldRole"
										headerKey="" headerValue="--Select--" cssStyle="width: 175px;"
										list="#{'CSE':'CSE', 'CSM':'CSM', 'CPM':'CPM'}" /></td>
									<td width="20%"><label id="astea.field.default.warehouse"
										name="astea.field.default.warehouse"
										ondblclick="callShowDiv(this);"><%=label.get("astea.field.default.warehouse")%></label>
									<font id="ctrlHide" color="RED">*</font></td>
									<td><s:textfield name="asteaFieldDefaultWarehouse"
										theme="simple" cssStyle="width: 175px;" maxlength="48" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr id="FIN">
							<td width="100%" colspan="4">
							<table width="100%">
								<tr>
									<td width="100%" colspan="4"><br>
									</td>
								</tr>
								<tr>
									<td width="100%" colspan="4"><strong class="text_head">
									<label id="astea.finance.workgroup.required"
										name="astea.finance.workgroup.required"
										ondblclick="callShowDiv(this);"><%=label.get("astea.finance.workgroup.required")%></label>
									</strong></td>
								</tr>
								<tr>
									<td width="20%"><label id="astea.finance.role"
										name="astea.finance.role" ondblclick="callShowDiv(this);"><%=label.get("astea.finance.role")%></label>
									: <font id="ctrlHide" color="RED">*</font></td>
									<td colspan="3"><s:select name="asteaFinanceRole"
										headerKey="" headerValue="--Select--" cssStyle="width: 175px;"
										list="#{'AP':'Accounts Payable', 'GA':'General Accounting', 'PR':'Pricing', 'RA1':'Rev Admin 1', 'RA2':'Rev Admin 2', 
													'RA3':'Rev Admin 3', 'SP':'Service Planning', 'OTH':'Other'}" />
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr id="LGS">
							<td width="100%" colspan="4">
							<table width="100%">
								<tr>
									<td width="100%" colspan="4"><br>
									</td>
								</tr>
								<tr>
									<td width="100%" colspan="4"><strong class="text_head">
									<label id="astea.logistics.workgroup.required"
										name="astea.logistics.workgroup.required"
										ondblclick="callShowDiv(this);"><%=label.get("astea.logistics.workgroup.required")%></label>
									</strong></td>
								</tr>
								<tr>
									<td width="20%"><label id="astea.logistics.role"
										name="astea.logistics.role" ondblclick="callShowDiv(this);"><%=label.get("astea.logistics.role")%></label>
									: <font id="ctrlHide" color="RED">*</font></td>
									<td colspan="3"><s:select name="asteaLogisticsRole"
										headerKey="" headerValue="--Select--" cssStyle="width: 175px;"
										list="#{'DRV':'Direct Return Vendor', 'FISC':'FISC', 'ICQA':'Inventory Control & QA', 'IP':'Inventory Planning', 
													'MH':'Material Handler', 'PFSS':'Purchasing & FSS', 'WRNTY':'Warranty'}" />
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr id="UXACC">
					<td width="100%" colspan="4">
					<table width="100%" class="formbg">
						<tr>
							<td width="100%" colspan="4"><strong class="text_head">
							<label id="unix.required" name="unix.required"
								ondblclick="callShowDiv(this);"><%=label.get("unix.required")%></label>
							</strong></td>
						</tr>
						<tr>
							<td width="20%"><label id="unix.host.names"
								name="unix.host.names" ondblclick="callShowDiv(this);"><%=label.get("unix.host.names")%></label>
							: <font id="ctrlHide" color="RED">*</font></td>
							<td colspan="3" nowrap="nowrap">
							<table width="32%">
								<tr>
									<td width="8%"><s:textfield name="unixHost1"
										cssStyle="width: 120px;" maxlength="48" /></td>
									<td width="8%"><s:textfield name="unixHost2"
										cssStyle="width: 120px;" maxlength="48" /></td>
									<td width="8%"><s:textfield name="unixHost3"
										cssStyle="width: 120px;" maxlength="48" /></td>
									<td width="8%"><s:textfield name="unixHost4"
										cssStyle="width: 120px;" maxlength="48" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="20%"><label id="unix.host.access"
								name="unix.host.access" ondblclick="callShowDiv(this);"><%=label.get("unix.host.access")%></label>
							: <font id="ctrlHide" color="RED">*</font></td>
							<td width="30%"><s:textfield name="unixHostAccess"
								theme="simple" cssStyle="width: 175px;" maxlength="48" /></td>
							<td width="20%"><label id="unix.group" name="unix.group"
								ondblclick="callShowDiv(this);"><%=label.get("unix.group")%></label>
							:</td>
							<td><s:textfield name="unixGroup" theme="simple"
								cssStyle="width: 175px;" maxlength="48" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr id="NTACC">
					<td width="100%" colspan="4">
					<table width="100%" class="formbg">
						<tr>
							<td width="100%" colspan="4"><strong class="text_head">
							<label id="nt.required" name="nt.required"
								ondblclick="callShowDiv(this);"><%=label.get("nt.required")%></label>
							</strong></td>
						</tr>
						<tr>
							<td width="20%"><label id="nt.host.names"
								name="nt.host.names" ondblclick="callShowDiv(this);"><%=label.get("nt.host.names")%></label>
							: <font id="ctrlHide" color="RED">*</font></td>
							<td colspan="3" nowrap="nowrap">
							<table width="32%">
								<tr>
									<td width="8%"><s:textfield name="ntHost1"
										cssStyle="width: 120px;" maxlength="48" /></td>
									<td width="8%"><s:textfield name="ntHost2"
										cssStyle="width: 120px;" maxlength="48" /></td>
									<td width="8%"><s:textfield name="ntHost3"
										cssStyle="width: 120px;" maxlength="48" /></td>
									<td width="8%"><s:textfield name="ntHost4"
										cssStyle="width: 120px;" maxlength="48" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="20%"><label id="nt.host.access"
								name="nt.host.access" ondblclick="callShowDiv(this);"><%=label.get("nt.host.access")%></label>
							: <font id="ctrlHide" color="RED">*</font></td>
							<td width="30%"><s:textfield name="ntHostAccess"
								theme="simple" cssStyle="width: 175px;" maxlength="48" /></td>
							<td width="20%"><label id="nt.group" name="nt.group"
								ondblclick="callShowDiv(this);"><%=label.get("nt.group")%></label>
							:</td>
							<td><s:textfield name="ntGroup" theme="simple"
								cssStyle="width: 175px;" maxlength="48" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr id="DBA">
					<td width="100%" colspan="4">
					<table width="100%" class="formbg">
						<tr>
							<td width="100%" colspan="4"><strong class="text_head">
							<label id="devon.building.access.required"
								name="devon.building.access.required"
								ondblclick="callShowDiv(this);"><%=label.get("devon.building.access.required")%></label>
							</strong></td>
						</tr>
						<tr>
							<td width="20%"><label id="devon.building.required"
								name="devon.building.required" ondblclick="callShowDiv(this);"><%=label.get("devon.building.required")%></label>
							: <font id="ctrlHide" color="RED">*</font></td>
							<td width="80%" colspan="3"><s:checkbox
								name="frontDoorAccess" />Front Door 7/24
							&nbsp;&nbsp;&nbsp;&nbsp; <s:checkbox name="dataRoomAccess" />Data
							Room 7/24 &nbsp;&nbsp;&nbsp;&nbsp; <s:checkbox
								name="pictureIdAccess" />Picture Id</td>
						</tr>
						<tr>
							<td width="20%"><label id="devon.building.agency"
								name="devon.building.agency" ondblclick="callShowDiv(this);"><%=label.get("devon.building.agency")%></label>
							: <font id="ctrlHide" color="RED">*</font></td>
							<td width="30%"><s:textfield name="agency" theme="simple"
								cssStyle="width: 175px;" maxlength="48" /></td>
							<td colspan="2" id="ctrlHide"><label
								id="devon.building.agency.required"
								name="devon.building.agency.required"
								ondblclick="callShowDiv(this);"><%=label.get("devon.building.agency.required")%></label>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
					<table width="100%" class="formbg">
						<tr>
							<td width="100%" colspan="4"><strong class="text_head">
							<label id="security.clearance" name="security.clearance"
								ondblclick="callShowDiv(this);"><%=label.get("security.clearance")%></label>
							</strong></td>
						</tr>
						<tr>
							<td width="20%"><label id="security.clearance.comments"
								name="security.clearance.comments"
								ondblclick="callShowDiv(this);"><%=label.get("security.clearance.comments")%></label>
							: <span id="showMandatory"><font id="ctrlHide" color="RED">*</font></span>
							</td>
							<td width="60%" colspan="2" nowrap="nowrap"><s:textarea
								name="comments" rows="4" cols="80"
								onkeypress="return imposeMaxLength(event, this, 800);" /> <img
								id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
								align="bottom" width="12" theme="simple"
								onclick="callWindow('paraFrm_comments', 'security.clearance.comments', '', '', '800', '800');">
							</td>
							<td width="20%"><span id="ctrlHide"> <label
								id="security.clearance.comments.new.line"
								name="security.clearance.comments.new.line"
								ondblclick="callShowDiv(this);"><%=label.get("security.clearance.comments.new.line")%></label>
							</span></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="20%"><strong class="text_head"> <label
						id="completed.by" name="completed.by"
						ondblclick="callShowDiv(this);"><%=label.get("completed.by")%></label>
					: </strong></td>
					<td width="30%"><s:hidden name="initiatorId" /><s:property
						value="initiatorName" /></td>
					<td width="20%"><strong class="text_head"> <label
						id="completed.on" name="completed.on"
						ondblclick="callShowDiv(this);"><%=label.get("completed.on")%></label>
					: </strong></td>
					<td><s:property value="completedOn" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr height="400">
			<td width="100%" valign="top"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script type="text/javascript"><!--
	callOnLoadFunctions();


	function callOnLoadFunctions() {
		//callEmpInfo();
		setAddedFile();
		displayWorkgroup();
		showSections();
		enableEmpType();
		enableExpiration();
	
	}

	function removeEmployee(remEmpId) {
		var conf = confirm('Do you want to remove an employee?');
		if(conf) {
			document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_removeEmployee.action?remEmpId=' + remEmpId;
			document.getElementById('paraFrm').submit();
		}
	}
	
	function draftFun() {
		if(doValidation()) {
			document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_draft.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function editFun() {
		return true;
	}
	
	function sendforapprovalFun() {
		if(doValidation()) {
			var conf = confirm('Do you really want to send application for approval?');			
			if(conf) {
				document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_sendForApproval.action';
				document.getElementById('paraFrm').submit();
			}
		}
	}
	
	function resetFun() {
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_reset.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		var applicationId = document.getElementById('paraFrm_applicationId').value;
		
		if(applicationId == '') {
			alert('Please select the application to delete');
			document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_input.action';
			document.getElementById('paraFrm').submit();
		} else {
			var conf = confirm('Do you want to delete the application?');

			if(conf) {
				document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_delete.action';
				document.getElementById('paraFrm').submit();
			}
		}
	}
	
	function backtolistFun() {
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	function printFun() {	
	window.print();
	}
	
	function callEmpInfo() {
		var empFileRequest = document.getElementById('paraFrm_empFileRequest').value;
		
		if(empFileRequest == 'Y') {
			document.getElementById('addAttachment').style.display = '';
			document.getElementById('fillEmpInfo').style.display = 'none';
			document.getElementById('paraFrm_empId').value = '';
			document.getElementById('paraFrm_empToken').value = '';
			document.getElementById('paraFrm_empName').value = '';
			document.getElementById('paraFrm_empTypeR').checked = false;
			document.getElementById('paraFrm_empTypeT').checked = false;
			document.getElementById('paraFrm_empTypeV').checked = false;
			enableExpiration();
			document.getElementById('paraFrm_empDesignation').value = '';
			document.getElementById('paraFrm_copyMgrDiv').value = '';
			document.getElementById('paraFrm_copyMgrDept').value = '';
			document.getElementById('paraFrm_copyMgrCity').value = '';
			document.getElementById('paraFrm_copyMgrState').value = '';
			document.getElementById('paraFrm_copyMgrPincode').value = '';
			document.getElementById('paraFrm_copyMgrWorkPhone').value = '';
			document.getElementById('paraFrm_copyMgrExt').value = '';
			document.getElementById('paraFrm_copyMgrFax').value = '';
			document.getElementById('paraFrm_copyMgrEmail').value = '';
		} else {
			document.getElementById('addAttachment').style.display = 'none';
			document.getElementById('fillEmpInfo').style.display = '';
			document.getElementById('paraFrm_empFile').value = '';
			document.getElementById('paraFrm_addedFile').value = '';
			document.getElementById('attachedFile').innerHTML = '';
		}
	}
	
	function uploadFile(fieldName) {
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/helpdesk/fileUpload.jsp?path=' + dataPath + '&field=' + fieldName, '', 
		'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
	}
	
	function copyManagerInfo() {
		var mgrId = document.getElementById('paraFrm_mgrId').value;
		
		if(mgrId == '') {
			alert('Please select the manager');
		} else {
			document.getElementById('paraFrm_copyMgrDiv').value = document.getElementById('paraFrm_mgrDivision').value;
			document.getElementById('paraFrm_copyMgrDept').value = document.getElementById('paraFrm_mgrDepartment').value;
			document.getElementById('paraFrm_copyMgrCity').value = document.getElementById('paraFrm_mgrCity').value;
			document.getElementById('paraFrm_copyMgrState').value = document.getElementById('paraFrm_mgrState').value;
			document.getElementById('paraFrm_copyMgrPincode').value = document.getElementById('paraFrm_mgrPincode').value;
		}
	}
	
	function setAddedFile() {
		document.getElementById('attachedFile').innerHTML = document.getElementById('paraFrm_addedFile').value;
	}
	
	function openAttachedFile() {
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_openAttachedFile.action';
		document.getElementById('paraFrm').submit();
	}
	
	function attachFile() {
		var empFile = document.getElementById('paraFrm_empFile').value;
		if(empFile == '') {
			alert('Please select file.');
		} else {
			document.getElementById('attachedFile').innerHTML = empFile;
			document.getElementById('paraFrm_addedFile').value = empFile;
			document.getElementById('paraFrm_empFile').value = '';
		}
	}
	
	function cancelapplicationFun() {
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function doValidation() {
		if(document.getElementById('paraFrm_requestType').value == '') {
			alert('Please select ' + document.getElementById('requested.type').innerHTML.toLowerCase());
			document.getElementById('paraFrm_requestType').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_mgrId').value == '') {
			alert('Please select ' + document.getElementById('manager.name').innerHTML.toLowerCase());
			return false;
		}
		
		if(document.getElementById('paraFrm_mgrDivision').value == '') {
			alert('Please enter ' + document.getElementById('manager.division').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mgrDivision').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_mgrDesignation').value == '') {
			alert('Please enter ' + document.getElementById('manager.designation').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mgrDesignation').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_mgrDepartment').value == '') {
			alert('Please enter ' + document.getElementById('manager.department').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mgrDepartment').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_mgrCity').value == '') {
			alert('Please enter ' + document.getElementById('manager.city').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mgrCity').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_mgrState').value == '') {
			alert('Please enter ' + document.getElementById('manager.state').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mgrState').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_mgrPincode').value == '') {
			alert('Please enter ' + document.getElementById('manager.pincode').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mgrPincode').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_mgrWorkPhone').value == '') {
			alert('Please enter ' + document.getElementById('manager.workphone').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mgrWorkPhone').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_mgrEmail').value == '') {
			alert('Please enter ' + document.getElementById('manager.email').innerHTML.toLowerCase());
			document.getElementById('paraFrm_mgrEmail').focus();
			return false;
		}
		
		
			
			if(document.getElementById('paraFrm_empName').value == '') {
				alert('Please select or enter ' + document.getElementById('employee.name').innerHTML.toLowerCase());
				document.getElementById('paraFrm_empName').focus();
				return false;
			}
			
			if(!(document.getElementById('paraFrm_empTypeR').checked || document.getElementById('paraFrm_empTypeT').checked || 
			document.getElementById('paraFrm_empTypeV').checked)) {
				alert('Please select at least one ' + document.getElementById('employee.type').innerHTML.toLowerCase());
				return false;
			}
			
			if(document.getElementById('paraFrm_empTypeT').checked && document.getElementById('paraFrm_empExpDate').value == '') {
				alert('Please enter ' + document.getElementById('employee.exp.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_empExpDate').focus();
				return false;
			} else if (document.getElementById('paraFrm_empTypeT').checked && document.getElementById('paraFrm_empExpDate').value != ''){
				if (!validateDate('paraFrm_empExpDate', 'employee.exp.date')){
					return false;
				}
			}
			
			if(document.getElementById('paraFrm_empDesignation').value == '') {
				alert('Please enter ' + document.getElementById('employee.designation').innerHTML.toLowerCase());
				document.getElementById('paraFrm_empDesignation').focus();
				return false;
			}
			
			
			
			if(document.getElementById('paraFrm_copyMgrDiv').value == '') {
				alert('Please enter ' + document.getElementById('copy.manager.division').innerHTML.toLowerCase() + ' for manager location information');
				document.getElementById('paraFrm_copyMgrDiv').focus();
				return false;
			}
			
			if(document.getElementById('paraFrm_copyMgrDept').value == '') {
				alert('Please enter ' + document.getElementById('copy.manager.department').innerHTML.toLowerCase() + ' for manager location information');
				document.getElementById('paraFrm_copyMgrDept').focus();
				return false;
			}
			
			if(document.getElementById('paraFrm_copyMgrCity').value == '') {
				alert('Please enter ' + document.getElementById('copy.manager.city').innerHTML.toLowerCase() + ' for manager location information');
				document.getElementById('paraFrm_copyMgrCity').focus();
				return false;
			}
			
			if(document.getElementById('paraFrm_copyMgrState').value == '') {
				alert('Please enter ' + document.getElementById('copy.manager.state').innerHTML.toLowerCase() + ' for manager location information');
				document.getElementById('paraFrm_copyMgrState').focus();
				return false;
			}
			
			if(document.getElementById('paraFrm_copyMgrPincode').value == '') {
				alert('Please enter ' + document.getElementById('copy.manager.pincode').innerHTML.toLowerCase() + ' for manager location information');
				document.getElementById('paraFrm_copyMgrPincode').focus();
				return false;
			}
			var empFile = document.getElementById('paraFrm_empFile').value;
			if(empFile == '') {
				//alert('Please select file.');
			} else {
				alert('You had selected file for attachment , Please click on Attach File');
				document.getElementById('paraFrm_empFile').focus();
				return false;
			}
			
		var hdnApplications = document.getElementById('hdnApplications').value;

		if(hdnApplications == '') {
			alert('Please select ' + document.getElementById('applications.list').innerHTML.toLowerCase());
			return false;
		} else {
			var hdnSections = document.getElementById('hdnSections').value;
			
			if(hdnSections != '') {
				var applications = hdnSections.split(',');
			
				for(var i = 0; i < applications.length; i++) {
					var applnOption = applications[i];
					
					if(applnOption == 'AST' && !doValidationForAstea()) {
						return false;
					}
					
					if(applnOption == 'UXACC' && !doValidationForUnixAccount()) {
						return false;
					}
					
					if(applnOption == 'NTACC' && !doValidationForNTAccount()) {
						return false;
					}
					
					if(applnOption == 'DBA' && !doValidationForDevonBuildingAccess()) {
						return false;
					}
					
					if(applnOption == 'NFCMT' && document.getElementById('paraFrm_comments').value == '') {
						alert('Please enter ' + document.getElementById('security.clearance.comments').innerHTML.toLowerCase());
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	function doValidationForAstea() {
		var asteaWorkgroup = document.getElementById('paraFrm_asteaWorkgroup').value;

		if(asteaWorkgroup == '') {
			alert('Please select ' + document.getElementById('astea.workgroup').innerHTML.toLowerCase());
			document.getElementById('paraFrm_asteaWorkgroup').focus();
			return false;
		} else if(asteaWorkgroup == 'FLD') {
			if(document.getElementById('paraFrm_asteaFieldRole').value == '') {
				alert('Please select ' + document.getElementById('astea.field.role').innerHTML.toLowerCase() + ' for field');
				document.getElementById('paraFrm_asteaFieldRole').focus();
				return false;
			}
			
			if(document.getElementById('paraFrm_asteaFieldDefaultWarehouse').value == '') {
				alert('Please select ' + document.getElementById('astea.field.default.warehouse').innerHTML.toLowerCase());
				document.getElementById('paraFrm_asteaFieldDefaultWarehouse').focus();
				return false;
			}
		} else if(asteaWorkgroup == 'FIN') {
			if(document.getElementById('paraFrm_asteaFinanceRole').value == '') {
				alert('Please select ' + document.getElementById('astea.finance.role').innerHTML.toLowerCase() + ' for finance');
				document.getElementById('paraFrm_asteaFinanceRole').focus();
				return false;
			}
		} else if(asteaWorkgroup == 'LGS') {
			if(document.getElementById('paraFrm_asteaLogisticsRole').value == '') {
				alert('Please select ' + document.getElementById('astea.logistics.role').innerHTML.toLowerCase() + ' for logistics');
				document.getElementById('paraFrm_asteaLogisticsRole').focus();
				return false;
			}
		}
		
		return true;
	}
	
	function doValidationForUnixAccount() {
		var unixHost1 = document.getElementById('paraFrm_unixHost1').value;
		var unixHost2 = document.getElementById('paraFrm_unixHost2').value;
		var unixHost3 = document.getElementById('paraFrm_unixHost3').value;
		var unixHost4 = document.getElementById('paraFrm_unixHost4').value;
		
		if(unixHost1 == '' && unixHost2 == '' && unixHost3 == '' && unixHost4 == '') {
			alert('Please enter at least one ' + document.getElementById('unix.host.names').innerHTML.toLowerCase() + ' ' + 
			document.getElementById('unix.required').innerHTML.toLowerCase());
			document.getElementById('paraFrm_unixHost1').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_unixHostAccess').value == '') {
			alert('Please enter ' + document.getElementById('unix.host.access').innerHTML.toLowerCase() + ' ' + 
			document.getElementById('unix.required').innerHTML.toLowerCase());
			document.getElementById('paraFrm_unixHostAccess').focus();
			return false;
		}
		
		return true;
	}
	
	function doValidationForNTAccount() {
		var ntHost1 = document.getElementById('paraFrm_ntHost1').value;
		var ntHost2 = document.getElementById('paraFrm_ntHost2').value;
		var ntHost3 = document.getElementById('paraFrm_ntHost3').value;
		var ntHost4 = document.getElementById('paraFrm_ntHost4').value;
		
		if(ntHost1 == '' && ntHost2 == '' && ntHost3 == '' && ntHost4 == '') {
			alert('Please enter at least one ' + document.getElementById('nt.host.names').innerHTML.toLowerCase() + ' ' + 
			document.getElementById('nt.required').innerHTML.toLowerCase());
			document.getElementById('paraFrm_ntHost1').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_ntHostAccess').value == '') {
			alert('Please enter ' + document.getElementById('nt.host.access').innerHTML.toLowerCase() + ' ' + 
			document.getElementById('nt.required').innerHTML.toLowerCase());
			document.getElementById('paraFrm_ntHostAccess').focus();
			return false;
		}
		
		return true;
	}
	
	function doValidationForDevonBuildingAccess() {
		var frontDoorAccess = document.getElementById('paraFrm_frontDoorAccess').checked;
		var dataRoomAccess = document.getElementById('paraFrm_dataRoomAccess').checked;
		var pictureIdAccess = document.getElementById('paraFrm_pictureIdAccess').checked;
		
		if(!frontDoorAccess && !dataRoomAccess && !pictureIdAccess) {
			alert('Please select at least one ' + document.getElementById('devon.building.required').innerHTML.toLowerCase());
			document.getElementById('paraFrm_frontDoorAccess').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_empTypeT').checked && document.getElementById('paraFrm_agency').value == '') {
			alert('Please enter ' + document.getElementById('devon.building.agency').innerHTML.toLowerCase());
			document.getElementById('paraFrm_agency').focus();
			return false;
		}
		
		return true;
	}
	
	function displayWorkgroup() {
		document.getElementById('FLD').style.display = 'none';
		document.getElementById('FIN').style.display = 'none';
		document.getElementById('LGS').style.display = 'none';
		
		var asteaWorkgroup = document.getElementById('paraFrm_asteaWorkgroup').value;
		
		if(asteaWorkgroup == 'FLD') {
			document.getElementById('paraFrm_asteaFinanceRole').value = '';
			document.getElementById('paraFrm_asteaLogisticsRole').value = '';
			document.getElementById('FLD').style.display = '';
		} else if(asteaWorkgroup == 'FIN') {
			document.getElementById('paraFrm_asteaFieldRole').value = '';
			document.getElementById('paraFrm_asteaFieldDefaultWarehouse').value = '';
			document.getElementById('paraFrm_asteaLogisticsRole').value = '';
			document.getElementById('FIN').style.display = '';
		} else if(asteaWorkgroup == 'LGS') {
			document.getElementById('paraFrm_asteaFieldRole').value = '';
			document.getElementById('paraFrm_asteaFieldDefaultWarehouse').value = '';
			document.getElementById('paraFrm_asteaFinanceRole').value = '';
			document.getElementById('paraFrm_asteaLogisticsRole').value = '';
			document.getElementById('LGS').style.display = '';
		}
	}
	
	function addApplications(obj, applnId, section) {
		var hdnApplications = document.getElementById('hdnApplications').value;
		var hdnSections = document.getElementById('hdnSections').value;

		if(obj.checked) {
			if(!(section == 'null' || section == '')) {
				if(section == 'AST') {
					document.getElementById(section).style.display = '';
					
					// for Astea
					document.getElementById('paraFrm_asteaWorkgroup').value = '';
					document.getElementById('paraFrm_asteaFieldRole').value = '';
					document.getElementById('paraFrm_asteaFieldDefaultWarehouse').value = '';
					document.getElementById('paraFrm_asteaFinanceRole').value = '';
					document.getElementById('paraFrm_asteaLogisticsRole').value = '';
					
				}
				if( section == 'UXACC' ) {
					document.getElementById(section).style.display = '';
					//for unix 
					document.getElementById('paraFrm_unixHost1').value = '';
					document.getElementById('paraFrm_unixHost2').value = '';
					document.getElementById('paraFrm_unixHost3').value = '';
					document.getElementById('paraFrm_unixHost4').value = '';
					document.getElementById('paraFrm_unixHostAccess').value = '';
					document.getElementById('paraFrm_unixGroup').value = '';
					
				}
				if( section == 'NTACC' ) {
					document.getElementById(section).style.display = '';
					
					// NTAccount
					document.getElementById('paraFrm_ntHost1').value = '';
					document.getElementById('paraFrm_ntHost2').value = '';
					document.getElementById('paraFrm_ntHost3').value = '';
					document.getElementById('paraFrm_ntHost4').value = '';
					document.getElementById('paraFrm_ntHostAccess').value = '';
					document.getElementById('paraFrm_ntGroup').value = '';
					
					
				}
				if( section == 'DBA') {
					document.getElementById(section).style.display = '';
										
					//DEVONBUILDING ACCESS
				
					document.getElementById('paraFrm_frontDoorAccess').checked  = false;
					document.getElementById('paraFrm_dataRoomAccess').checked  = false;
					document.getElementById('paraFrm_pictureIdAccess').checked = false;
					document.getElementById('paraFrm_agency').value = '';
					
					
				}
				
				if(section == 'NFCMT') {
					document.getElementById('showMandatory').style.display = '';
				}
			}

			if(hdnApplications == '') {
				hdnApplications = applnId;
			} else {
				hdnApplications += ',' + applnId;
			}
			
			if(hdnSections == '') {
				hdnSections = section;
			} else {
				hdnSections += ',' + section;
			}
		} else {
			if(!(section == 'null' || section == '')) {
				if(section == 'AST' || section == 'UXACC' || section == 'NTACC' || section == 'DBA') {
					document.getElementById(section).style.display = 'none';
					
					
					
				}
				
				if(section == 'NFCMT') {
					document.getElementById('showMandatory').style.display = 'none';
				}
			}
			
			var arrHdnApplns = hdnApplications.split(',');
			var arrApplns = new Array();
			var applns = '';
			var cntApplns = 0;
			
			for(var i = 0; i < arrHdnApplns.length; i++) {
				if(arrHdnApplns[i] != applnId) {
					arrApplns[cntApplns] = arrHdnApplns[i];
					cntApplns++;
				}
			}
			
			for(var i = 0; i < arrApplns.length; i++) {
				if(i == (arrApplns.length - 1)) {
					applns += arrApplns[i];
				} else {
					applns += arrApplns[i] + ',';
				}
			}
			
			hdnApplications = applns;
			
			var arrHdnSections = hdnSections.split(',');
			var arrSections = new Array();
			var sects = '';
			var cntSections = 0;
			
			for(var i = 0; i < arrHdnSections.length; i++) {
				if(arrHdnSections[i] != section) {
					arrSections[cntSections] = arrHdnSections[i];
					cntSections++;
				}
			}
			
			for(var i = 0; i < arrSections.length; i++) {
				if(i == (arrSections.length - 1)) {
					sects += arrSections[i];
				} else {
					sects += arrSections[i] + ',';
				}
			}
			
			hdnSections = sects;
		}

		document.getElementById('hdnApplications').value = hdnApplications;
		document.getElementById('hdnSections').value = hdnSections;
	}
	
	function showSections() {
		document.getElementById('AST').style.display = 'none';
		document.getElementById('UXACC').style.display = 'none';
		document.getElementById('NTACC').style.display = 'none';
		document.getElementById('DBA').style.display = 'none';
		document.getElementById('showMandatory').style.display = 'none';
		
		var hdnSections = document.getElementById('hdnSections').value;

		if(hdnSections != '') {
			var arrSections = hdnSections.split(',');
			
			for(var i = 0; i < arrSections.length; i++) {
				if(arrSections[i] == 'AST' || arrSections[i] == 'UXACC' || arrSections[i] == 'NTACC' || arrSections[i] == 'DBA') {
					document.getElementById(arrSections[i]).style.display = '';
					
					
				}
				
				if(arrSections[i] == 'NFCMT') {
					document.getElementById('showMandatory').style.display = '';
				}
			}
		}
	}
	
	function enableEmpType() {
		var hdnEmpType = document.getElementById('hdnEmpType').value;
		
		if(hdnEmpType != '') {
			document.getElementById('paraFrm_empType' + hdnEmpType).checked = true;
		}
	}
	
	function display()
	{
	
	if(document.getElementById('paraFrm_empTypeT').checked) {
			
				
				document.getElementById('displayfield').style.display = 'none';
		} 
			else
			{
			
			document.getElementById('displayfield').style.display = '';
				
			
			}
		}

	
	function enableExpiration() {
		if(document.getElementById('paraFrm_empTypeT').checked) {
			document.getElementById('paraFrm_empExpDate').readOnly = false;
			document.getElementById('paraFrm_empExpDate').style.background = 'white';
			document.getElementById('paraFrm_empExpDate').focus();
			document.getElementById('imgDate').style.display = '';
			
			document.getElementById('paraFrm_agency').readOnly = false;
			document.getElementById('paraFrm_agency').style.background = 'white';
		} else {
			document.getElementById('paraFrm_empExpDate').readOnly = true;
			document.getElementById('paraFrm_empExpDate').style.background = '#F2F2F2';
			document.getElementById('paraFrm_empExpDate').value = '';
			document.getElementById('imgDate').style.display = 'none';
			document.getElementById('displayfield').style.display = '';
			document.getElementById('paraFrm_agency').readOnly = true;
			document.getElementById('paraFrm_agency').style.background = '#F2F2F2';
			document.getElementById('paraFrm_agency').value = '';
		}
	}
	
	function checkExpiration() {
		var empExpDate = document.getElementById('paraFrm_empExpDate').value;
	
		if(validateDate('paraFrm_empExpDate', 'employee.exp.date')) {
			var arrEmpExpDate = empExpDate.split('-');
			var expDate = new Date();
			expDate.setDate(arrEmpExpDate[0]);
			expDate.setMonth(eval(arrEmpExpDate[1]) - 1);
			expDate.setYear(arrEmpExpDate[2]);
			
			var propExpDate = new Date();
			var month = propExpDate.getMonth();
			propExpDate.setMonth(month + 6);
			
			var currDate = new Date();
			
			
		} else {
			return false;
		}
		
		return true;
	}
	
	function imposeMaxLength(Event, Object, MaxLen) {
		return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
	}
--></script>


