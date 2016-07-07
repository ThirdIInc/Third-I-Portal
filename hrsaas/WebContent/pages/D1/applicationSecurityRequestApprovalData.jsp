<!-- Bhushan Dasare -->
<!-- Feb 28, 2011 -->

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="ApplicationSecurityRequestApproval"
	name="ApplicationSecurityRequestApproval" id="paraFrm" validate="true"
	target="main" theme="simple">
	<s:hidden name="applicationId" />
	<s:hidden name="dataPath" />
	<s:hidden name="addedFile" />
	<s:hidden name="applnStatus" />
	<s:hidden name="listType" />
	
	<s:hidden name="pageForPending" id="pageForPending" />
	<s:hidden name="pageForPendingCancel" id="pageForPendingCancel" />
	<s:hidden name="pageForApprove" id="pageForApprove" />
	<s:hidden name="pageForApproveCancel" id="pageForApproveCancel" />
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
					Security Request Approval </strong></td>
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
					<td align="right"><s:if
						test='applnStatus == "P" || applnStatus == "C"'>
						<font color="red">*</font>Indicates Required
							</s:if></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="20%" colspan="2"><strong> <label
						id="approver.comments" name="approver.comments"
						ondblclick="callShowDiv(this);"><%=label.get("approver.comments")%></label>
					</strong> <s:if test='applnStatus == "P" || applnStatus == "C" || status=="Pending"|| status=="Applied For Cancellation" '>
						<font color="red">*</font>
					</s:if></td>
					<td><s:if test='applnStatus == "P" || applnStatus == "C" || status=="Pending"|| status=="Applied For Cancellation"'>
						<s:textarea theme="simple" cols="70" rows="3"
							name="approverComments" id="approverComments" />
					</s:if></td>
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
					<td colspan="3"><s:property value="requestDate" /></td>
				</tr>
				<tr>
					<td width="20%"><label id="requested.type"
						name="requested.type" ondblclick="callShowDiv(this);"><%=label.get("requested.type")%></label>
					:</td>
					<td width="30%"><s:property value="requestType" /></td>
					<td width="20%"><label id="requested.status"
						name="requested.status" ondblclick="callShowDiv(this);"><%=label.get("requested.status")%></label>
					:</td>
					<td><s:property value="status" /><s:hidden name="status" /></td>
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
					:</td>
					<td nowrap="nowrap" colspan="3"><s:hidden name="mgrId" /><s:property
						value="mgrToken" /><s:property value="mgrName" /></td>
				</tr>
				<tr>
					<td width="20%"><label id="manager.division"
						name="manager.division" ondblclick="callShowDiv(this);"><%=label.get("manager.division")%></label>
					:</td>
					<td width="30%"><s:property value="mgrDivision" /></td>
					<td width="20%"><label id="manager.designation"
						name="manager.designation" ondblclick="callShowDiv(this);"><%=label.get("manager.designation")%></label>
					:</td>
					<td><s:property value="mgrDesignation" /></td>
				</tr>
				<tr>
					<td width="20%"><label id="manager.department"
						name="manager.department" ondblclick="callShowDiv(this);"><%=label.get("manager.department")%></label>
					:</td>
					<td width="30%"><s:property value="mgrDepartment" /></td>
					<td width="20%"><label id="manager.city" name="manager.city"
						ondblclick="callShowDiv(this);"><%=label.get("manager.city")%></label>
					:</td>
					<td><s:property value="mgrCity" /></td>
				</tr>
				<tr>
					<td width="20%"><label id="manager.state" name="manager.state"
						ondblclick="callShowDiv(this);"><%=label.get("manager.state")%></label>
					:</td>
					<td width="30%"><s:property value="mgrState" /></td>
					<td width="20%"><label id="manager.pincode"
						name="manager.pincode" ondblclick="callShowDiv(this);"><%=label.get("manager.pincode")%></label>
					:</td>
					<td><s:property value="mgrPincode" /></td>
				</tr>
				<tr>
					<td width="20%"><label id="manager.workphone"
						name="manager.workphone" ondblclick="callShowDiv(this);"><%=label.get("manager.workphone")%></label>
					:</td>
					<td width="30%"><s:property value="mgrWorkPhone" /></td>
					<td width="20%"><label id="manager.ext" name="manager.ext"
						ondblclick="callShowDiv(this);"><%=label.get("manager.ext")%></label>
					:</td>
					<td><s:property value="mgrExt" /></td>
				</tr>
				<tr>
					<td width="20%"><label id="manager.fax" name="manager.fax"
						ondblclick="callShowDiv(this);"><%=label.get("manager.fax")%></label>
					:</td>
					<td width="30%"><s:property value="mgrFax" /></td>
					<td width="20%"><label id="manager.email" name="manager.email"
						ondblclick="callShowDiv(this);"><%=label.get("manager.email")%></label>
					:</td>
					<td><s:property value="mgrEmail" /></td>
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
							<s:property value="empFileRequest" />
						</td>
					</tr>
					-->
				<s:hidden name="empFileRequest" />
				<tr id="fillEmpInfo">
					<td width="100%" colspan="4">
					<table width="100%" class="formbg">
						<tr>
									<td width="20%">
										<label id="employee.name" name="employee.name" ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label> :
									</td>
									<td nowrap="nowrap" style="vertical-align: middle;">
										<s:property value="empToken" />
									</td>
									
										<td colspan="1">
							<label id="employee.name"
								name="employee.name" ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label>:<font id="ctrlHide" color="red"></font>
							</td>
						<td colspan="1">
						<s:property
								value="empName" /><div id="displayfield">
						</td>
									
								</tr>
						<tr>
							<td width="20%"><label id="employee.type"
								name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
							:</td>
							<td width="30%"><s:property value="hdnEmpType" /></td>
							<td width="20%"><label id="employee.exp.date"
								name="employee.exp.date" ondblclick="callShowDiv(this);"><%=label.get("employee.exp.date")%></label>
							:</td>
							<td><s:property value="empExpDate" /></td>
						</tr>
						<tr>
							<td width="20%"><label id="employee.designation"
								name="employee.designation" ondblclick="callShowDiv(this);"><%=label.get("employee.designation")%></label>
							:</td>
							<td width="80%" colspan="3"><s:property
								value="empDesignation" /></td>
						</tr>
						<tr>
							<td width="20%"><label id="copy.manager.division"
								name="copy.manager.division" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.division")%></label>
							:</td>
							<td width="30%" nowrap="nowrap"><s:property
								value="copyMgrDiv" /></td>
							<td width="20%"><label id="copy.manager.department"
								name="copy.manager.department" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.department")%></label>
							:</td>
							<td nowrap="nowrap"><s:property value="copyMgrDept" /></td>
						</tr>
						<tr>
							<td width="20%"><label id="copy.manager.city"
								name="copy.manager.city" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.city")%></label>
							:</td>
							<td width="30%" nowrap="nowrap"><s:property
								value="copyMgrCity" /></td>
							<td width="20%"><label id="copy.manager.state"
								name="copy.manager.state" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.state")%></label>
							:</td>
							<td><s:property value="copyMgrState" /></td>
						</tr>
						<tr>
							<td width="20%"><label id="copy.manager.pincode"
								name="copy.manager.pincode" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.pincode")%></label>
							:</td>
							<td width="30%"><s:property value="copyMgrPincode" /></td>
							<td width="20%"><label id="copy.manager.email"
								name="copy.manager.email" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.email")%></label>
							:</td>
							<td><s:property value="copyMgrEmail" /></td>
						</tr>
						<tr>
							<td width="20%"><label id="copy.manager.workphone"
								name="copy.manager.workphone" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.workphone")%></label>
							:</td>
							<td width="30%"><s:property value="copyMgrWorkPhone" /></td>
							<td width="20%"><label id="copy.manager.ext"
								name="copy.manager.ext" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.ext")%></label>
							:</td>
							<td><s:property value="copyMgrExt" /></td>
						</tr>
						<tr>
							<td width="20%"><label id="copy.manager.fax"
								name="copy.manager.fax" ondblclick="callShowDiv(this);"><%=label.get("copy.manager.fax")%></label>
							:</td>
							<td width="80%" colspan="3"><s:property value="copyMgrFax" />
							</td>
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
					:</td>
					<td width="80%" colspan="3"><s:hidden name="hdnApplications"
						id="hdnApplications" /><s:hidden name="hdnSections"
						id="hdnSections" />
					<table width="100%">
						<%
							try {
								if (request.getAttribute("applicationList") != null) {
									Object[][] applicationList = (Object[][]) request.getAttribute("applicationList");
									int cnt = 1;
									for (int i = 0; i < applicationList.length; i++) {
										if (String.valueOf(applicationList[i][3]).equals("Y")) {
											if (cnt == 1) {
						%>						<tr>
						<%					}
						%>					<td width="20%">
												<input type="checkbox" name="<%=String.valueOf(applicationList[i][0])%>"
												checked="checked" disabled="disabled" onclick="addApplications(this, 
												'<%=String.valueOf(applicationList[i][0])%>', 
												'<%=String.valueOf(applicationList[i][2]) %>');" />
												<%=String.valueOf(applicationList[i][1])%>
											</td>
						<%					if (cnt == 3) {
												cnt = 0;
						%>						</tr>
						<%					}
											
											cnt++;
										}
									}
									
									if (cnt != 1) {
						%>				</tr>
						<%			}
								} else {
						%>			<tr><td>Please configure the application for security.</td></tr>
						<%		}
							} catch (Exception e) {}
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
							:</td>
							<td colspan="3"><s:hidden name="asteaWorkgroup" /><s:property
								value="asteaWorkgroup" /></td>
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
									</td>
									<td width="30%"><s:hidden name="asteaFieldRole" /><s:property
										value="asteaFieldRole" /></td>
									<td width="20%"><label id="astea.field.default.warehouse"
										name="astea.field.default.warehouse"
										ondblclick="callShowDiv(this);"><%=label.get("astea.field.default.warehouse")%></label>
									</td>
									<td><s:hidden name="asteaFieldDefaultWarehouse" /><s:property
										value="asteaFieldDefaultWarehouse" /></td>
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
									:</td>
									<td colspan="3"><s:hidden name="asteaFinanceRole" /><s:property
										value="asteaFinanceRole" /></td>
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
									:</td>
									<td colspan="3"><s:hidden name="asteaLogisticsRole" /><s:property
										value="asteaLogisticsRole" /></td>
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
							:</td>
							<td colspan="3" nowrap="nowrap">
							<table width="32%">
								<tr>
									<td width="8%"><s:property value="unixHost1" /></td>
									<td width="8%"><s:property value="unixHost2" /></td>
									<td width="8%"><s:property value="unixHost3" /></td>
									<td width="8%"><s:property value="unixHost4" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="20%"><label id="unix.host.access"
								name="unix.host.access" ondblclick="callShowDiv(this);"><%=label.get("unix.host.access")%></label>
							:</td>
							<td width="30%"><s:property value="unixHostAccess" /></td>
							<td width="20%"><label id="unix.group" name="unix.group"
								ondblclick="callShowDiv(this);"><%=label.get("unix.group")%></label>
							:</td>
							<td><s:property value="unixGroup" /></td>
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
							:</td>
							<td colspan="3" nowrap="nowrap">
							<table width="32%">
								<tr>
									<td width="8%"><s:property value="ntHost1" /></td>
									<td width="8%"><s:property value="ntHost2" /></td>
									<td width="8%"><s:property value="ntHost3" /></td>
									<td width="8%"><s:property value="ntHost4" /></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="20%"><label id="nt.host.access"
								name="nt.host.access" ondblclick="callShowDiv(this);"><%=label.get("nt.host.access")%></label>
							:</td>
							<td width="30%"><s:property value="ntHostAccess" /></td>
							<td width="20%"><label id="nt.group" name="nt.group"
								ondblclick="callShowDiv(this);"><%=label.get("nt.group")%></label>
							:</td>
							<td><s:property value="ntGroup" /></td>
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
							:</td>
							<td width="80%" colspan="3"><s:checkbox disabled="true"
								name="frontDoorAccess" />Front Door 7/24
							&nbsp;&nbsp;&nbsp;&nbsp; <s:checkbox disabled="true"
								name="dataRoomAccess" />Data Room 7/24 &nbsp;&nbsp;&nbsp;&nbsp;
							<s:checkbox disabled="true" name="pictureIdAccess" />Picture Id
							</td>
						</tr>
						<tr>
							<td width="20%"><label id="devon.building.agency"
								name="devon.building.agency" ondblclick="callShowDiv(this);"><%=label.get("devon.building.agency")%></label>
							:</td>
							<td width="30%"><s:property value="agency" /></td>
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
					<td width="100%" colspan="4"><strong class="text_head">
					<label id="security.clearance" name="security.clearance"
						ondblclick="callShowDiv(this);"><%=label.get("security.clearance")%></label>
					</strong></td>
				</tr>
				<tr>
					<td width="20%"><label id="security.clearance.comments"
						name="security.clearance.comments" ondblclick="callShowDiv(this);"><%=label.get("security.clearance.comments")%></label>
					:</td>
					<td width="30%"><s:property value="comments" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="20%"><strong> <label id="completed.by"
						name="completed.by" ondblclick="callShowDiv(this);"><%=label.get("completed.by")%></label>
					: </strong></td>
					<td width="30%"><s:hidden name="initiatorId" /><s:property
						value="initiatorName" /></td>
					<td width="20%"><strong> <label id="completed.on"
						name="completed.on" ondblclick="callShowDiv(this);"><%=label.get("completed.on")%></label>
					: </strong></td>
					<td><s:property value="completedOn" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	callOnLoadFunctions();

	function callOnLoadFunctions() {
		//callEmpInfo();
		setAddedFile();
		displayWorkgroup();
		showSections();
	}

	function backtolistFun() {
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequestApproval_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	function printFun() {	
	window.print();
	}
	
	function callEmpInfo() {
		var empFileRequest = document.getElementById('paraFrm_empFileRequest').value;
		
		if(empFileRequest == 'Yes') {
			document.getElementById('addAttachment').style.display = '';
			document.getElementById('fillEmpInfo').style.display = 'none';
		} else {
			document.getElementById('addAttachment').style.display = 'none';
			document.getElementById('fillEmpInfo').style.display = '';
		}
	}
	
	function showSections() {
		document.getElementById('AST').style.display = 'none';
		document.getElementById('UXACC').style.display = 'none';
		document.getElementById('NTACC').style.display = 'none';
		document.getElementById('DBA').style.display = 'none';
		
		var hdnSections = document.getElementById('hdnSections').value;

		if(hdnSections != '') {
			var arrSections = hdnSections.split(',');
			
			for(var i = 0; i < arrSections.length; i++) {
				if(arrSections[i] == 'AST' || arrSections[i] == 'UXACC' || arrSections[i] == 'NTACC' || arrSections[i] == 'DBA') {
					document.getElementById(arrSections[i]).style.display = '';
				}
			}
		}
	}
	
	function setAddedFile() {
		document.getElementById('attachedFile').innerHTML = document.getElementById('paraFrm_addedFile').value;
	}
	
	function openAttachedFile() {
		document.getElementById('paraFrm').action = 'ApplicationSecurityRequest_openAttachedFile.action';
		document.getElementById('paraFrm').submit();
	}
	
	function approveFun() {
		var conf = confirm('Do you really want to approve the application?');			
		if(conf) {
			document.getElementById('paraFrm').action = 'ApplicationSecurityRequestApproval_approve.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function rejectFun() {
		var conf = confirm('Do you really want to reject the application?');			
		if(conf) {
			document.getElementById('paraFrm').action = 'ApplicationSecurityRequestApproval_reject.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function sendbackFun() {
		var conf = confirm('Do you really want to send back the application?');			
		if(conf) {
			document.getElementById('paraFrm').action = 'ApplicationSecurityRequestApproval_sendBack.action';
			document.getElementById('paraFrm').submit();
		}
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
	function closeFun() {
					window.close();
			}
</script>