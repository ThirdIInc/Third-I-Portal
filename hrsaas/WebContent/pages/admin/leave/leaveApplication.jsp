<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/pages/common/js/leaveAjax.js"></script>
<style>
.myText {
	border: 0px;
}
</style>

<div align="center" id="overlay"
	style="z-index: 3; visibility: hidden; position: absolute; width: 776px; height: 450px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid :                       DXImageTransform .                       Microsoft .                       alpha(opacity =                       15); -moz-opacity: .1; opacity: .1;">
</div>

<s:form action="LeaveApplication" method="post" name="LeaveForm"
	id="paraFrm" theme="simple">
	<table width="98%" border="0" align="left" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave
					<!-- This is for mypage --> Application Form </strong><s:hidden
						name="flagHrs" /></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<s:if test="saveDetailFlag">
				<td width="100%" colspan="3"><s:if test="appRejSendBackFlag">
					<input type="button" class="token" value="    Approve"
						onclick="return checkAppStatus(this,'A');" theme="simple" />
					<input type="button" class="token" value="    Reject"
						onclick="return checkAppStatus(this,'R');" theme="simple" />
					<input type="button" class="token" value="    Send Back"
						onclick="return checkAppStatus(this,'B')" theme="simple" />
				</s:if> <s:if test="approveAppCanFlag">

					<input type="button" class="token"
						value="    Approve Cancellation "
						onclick="return checkApproveCancellationStatus(this,'X');"
						theme="simple" />
					<input type="button" class="token" value="    Reject Cancellation "
						onclick="return checkApproveCancellationStatus(this,'Z');"
						theme="simple" />
				</s:if> <s:if test="leaveApplication.isAdminApprovalClick">
					<input type="button" class="token" value="    Back To List"
						onclick="return callBackAdmin();" theme="simple" />
				</s:if> <s:else>
					<input type="button" class="token" value="    Back To List"
						onclick="return callBack();" theme="simple" />
				</s:else></td>
			</s:if>
		</tr>
		<!-- 	<s:if test="isButtonVisible">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>

						<td width="85%"><s:if test="draftFlag">
							<s:submit cssClass="token" action="LeaveApplication_save"
								value="    Draft" onclick="return callSave('D');" />
						</s:if> <s:if test="searchResetFlag">
							<s:submit cssClass="reset" action="LeaveApplication_reset"
								value="    Reset" />
							<input type="button" class="search" value="    Search"
								onclick="javascript:callsF9(500,325,'LeaveApplication_f9actionLeaveCode.action');" />

						</s:if> <s:if test="deleteBtnFlag">
							<s:submit cssClass="delete" action="LeaveApplication_delete"
								value="   Delete"
								onclick="return callDelete('paraFrm_leaveApplication_leaveCode');" />
						</s:if> <s:if test="sendAppBtnFlag">
							<s:submit cssClass="token" action="LeaveApplication_save"
								value="    Send For Approval" onclick="return callSave('P');" />
						</s:if> <s:if test="cancelBtnFlag">
							<s:submit cssClass="delete" action="LeaveApplication_cancelForm"
								value="   Cancel" onclick="return cancel();" />

						</s:if> <s:if test="reportBtnFlag">
							<input type="button" class="token" value="   Report"
								onclick="callReport('LeaveApplication_report.action')" />
						</s:if> <s:if test="commonBtnFlag">

							<s:submit cssClass="token" action="LeaveApplication_back"
								value="    Back" />

						</s:if></td>
						<td width="15%">
						<div align="right"><span class="style2"><font
							color="red">*</font></span> Indicates Required</div>
						</td>
					</tr>
				</table>
				<label></label></td>
			</tr>
		</s:if> -->

		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<!-- TABLE FOR APPROVER COMMENTS START-->
		<s:if test="prevAppCommentFlag">
			<tr>
				<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="30%"><label name="approverComm" id="approverComm"
							ondblclick="callShowDiv(this);"><%=label.get("approverComm")%></label>:</td>
						<td width="70%"><s:if test="saveDetailFlag">
							<s:textarea theme="simple" cols="70" rows="3"
								name="approverComments" />
						</s:if> <s:else>
							<s:property value="approverComments" />
						</s:else></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- TABLE FOR APPROVER COMMENTS ENDS-->
		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS START-->
		<s:if test="prevAppCommentListFlag">
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
								Approver Details :</strong></td>
							</tr>
							<tr>
								<td class="formth" width="10%" height="22" valign="top">Sr.No.</td>
								<td class="formth" width="15%" height="22" valign="top">Approver
								ID</td>
								<td class="formth" width="25%" height="22" valign="top">
								Approver Name</td>
								<td class="formth" width="10%" height="22" valign="top">
								Date</td>
								<td class="formth" width="10%" height="22" valign="top">Status
								</td>
								<td class="formth" width="30%" height="22" valign="top">Comments
								</td>
							</tr>

							<%int i = 0;%>
							<%
							int k = 1;
							%>
							<s:iterator value="approverCommentList" status="stat">
								<tr>
									<td width="10%" class="sortableTD"><%=k%><s:hidden
										name="appSrNo" value="%{<%=k%>}" /></td>
									<td width="15%" class="sortableTD"><s:property
										value="prevApproverID" /><s:hidden name="prevApproverID" /></td>
									<td width="25%" class="sortableTD"><s:property
										value="prevApproverName" /><s:hidden name="prevApproverName" /></td>
									<td width="10%" class="sortableTD" align="center"><s:property
										value="prevApproverDate" /><s:hidden name="prevApproverDate" /></td>
									<td width="10%" class="sortableTD">&nbsp;<s:property
										value="prevApproverStatus" /><s:hidden
										name="prevApproverStatus" /></td>
									<td width="30%" class="sortableTD">&nbsp;<s:property
										value="prevApproverComment" /><s:hidden
										name="prevApproverComment" /></td>
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


		<!-- TABLE FOR PREVIOUS APPROVER COMMENTS END-->
		<!-- EMPLOYEE DETAILS TABLE  STARTS -->
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<s:hidden theme="simple" name="leaveApplication.leaveCode" />

				<tr>
					<td width="20%"><label name="appname" id="appname"
						ondblclick="callShowDiv(this);"><%=label.get("appname")%></label><span
						class="formtext"> :</span><span class="style2"><font
						color="red">*</font></span></td>
					<td width="20%"><s:hidden theme="simple"
						name="leaveApplication.empCode"
						value="%{leaveApplication.empCode}" /> <s:textfield
						theme="simple" readonly="true" size="20"
						name="leaveApplication.tokenNo"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td colspan="2"><s:textfield label="%{getText('empName')}"
						theme="simple" size="70" readonly="true"
						name="leaveApplication.empName"
						cssStyle="background-color: #F2F2F2;" /></td>
					<s:if test="isLeaveApp">
						<td width="10%" colspan="3"><img
							src="../pages/common/css/default/images/search2.gif"
							class="iconImage" width="16" height="15"
							onclick="javascript:getEmployee();" /></td>
					</s:if>
					<s:hidden name="leaveApplication.isOfficer" />
				</tr>

				<tr>
					<td width="20%"><label name="branch" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :<span
						class="style2"><font color="red">*</font></span></td>
					<td><s:textfield label="%{getText('centerNo')}" theme="simple"
						size="20" readonly="true" name="leaveApplication.center"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td width="24%"><label name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="30%"><s:textfield label="%{getText('department')}"
						theme="simple" size="20" readonly="true"
						name="leaveApplication.department"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td></td>
				</tr>
				
				<tr>
					<td><label name="appdate" id="appdate"
						ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label>
					:
					<td><s:textfield label="%{getText('applicationdate')}"
						theme="simple" readonly="true"
						name="leaveApplication.applicationDate" size="20"
						cssStyle="background-color: #F2F2F2;" /></td>
					<td><label name="stat" id="stat"
						ondblclick="callShowDiv(this);"><%=label.get("stat")%></label> :
					<td><s:hidden name="hiddenStatus" /> <s:select theme="simple"
						name="status" disabled="true" cssStyle="width:130"
						list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected','F':'Forwarded','N':'Cancelled','C':'Applied For Cancellation','X':'Cancellation Approved','Z':'Cancellation Rejected'}" /><s:hidden
						name="level" /></td>
					<td></td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- EMPLOYEE DETAILS TABLE  ENDS -->
		<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="50%" nowrap="nowrap"><strong>The
					Approver(s) for this application :</strong></td>
					<td colspan="2" nowrap="nowrap"></td>

					<td width="11%" nowrap="nowrap"><strong>Keep Informed
					To : </strong></td>
					<td width="13%"><s:if test="isLeaveTypeApp">
						<s:hidden name="employeeId" />
						<s:hidden name="employeeToken" />
						<s:textfield name="employeeName" readonly="true" />
					</s:if></td>
					<td width="5%" colspan="1"><s:if test="isLeaveTypeApp">
						<img src="../pages/common/css/default/images/search2.gif"
							class="iconImage" width="16" height="15"
							onclick="javascript:getKeepInformedEmp();" />
					</s:if></td>
					<td width="15%"><s:if test="isLeaveTypeApp">
						<s:submit name="" value=" Add" cssClass=" add"
							action="LeaveApplication_addKeepInformedEmpList"
							onclick="return callKeepInformed();" />
					</s:if></td>
				</tr>
				<!-- APPROVER LIST  TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<%
							int y = 1;
							%>
							<%!int z = 0;%>
							<s:iterator value="approverList">
								<tr>
									<td><s:hidden name="approverName" /><STRONG><s:property
										value="srNoIterator" /></STRONG> <s:property value="approverName" /></td>

								</tr>
								<%
								y++;
								%>
							</s:iterator>
							<%
							z = y;
							%>
						</tr>
					</table>
					</td>
				</tr>
				<!-- APPROVER LIST  TABLE  ENDS -->
				<!-- KEEP INFORMED LIST TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<%
							int counter11 = 0;
							int counter2 = 0;
						%>
						<s:iterator value="keepInformedList" status="stat">
							<tr>
								<td width="80%"><%=++counter11%><s:hidden name="serialNo" /><s:hidden
									name="keepInformedEmpName" /><s:property
									value="keepInformedEmpName" /><s:hidden
									name="keepInformedEmpId" /></td>
								<td width="20%"><s:if
									test="%{leaveApplication.approvalFlag}">
									<a href="#" onclick="callForRemove(<%=counter11%>);">Remove</a>
								</s:if></td>
							</tr>
							<%
							counter2 = counter11;
							%>
						</s:iterator>
						<%
						counter2 = 0;
						%>
					</table>
					</td>
					<td></td>
				</tr>

			</table>
			</td>
		</tr>
		<!-- KEEP INFORMED LIST TABLE  ENDS -->
		<!-- SELECT LEAVE TYPE TABLE STARTS -->
		<s:if test="isLeaveTypeApp">
			<tr id="leave">
				<td colspan="5">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="15" class="formhead"><strong
							class="forminnerhead">Apply For Leave</strong></td>
					</tr>
					<tr>
						<td width="16%" colspan="1"><label name="levtype"
							id="levtype1" ondblclick="callShowDiv(this);"><%=label.get("levtype")%></label>
						<span class="formtext">:</span><span class="style2"><font
							color="red">*</font></span></td>
						<td colspan="2"><s:hidden name="leaveApplication.levCode"
							value="%{leaveApplication.levCode}" theme="simple" /><s:hidden
							name="checkEdit" /> <s:hidden name="leaveApplication.leaveId"
							value="%{leaveApplication.leaveId}" theme="simple" /> <s:hidden
							name="leaveApplication.hdlevType"
							value="%{leaveApplication.hdlevType}" theme="simple" /><s:textfield
							name="leaveApplication.levType" readonly="true" theme="simple"
							cssStyle="background-color: #F2F2F2;" /></td>

						<s:if test="%{leaveApplication.isEditFlag}">
							<td width="15%">&nbsp;</td>
						</s:if>
						<s:else>
							<td width="10%"><img
								src="../pages/common/css/default/images/search2.gif"
								class="iconImage" width="16" height="15"
								onclick="javascript:getLeaveType();" /></td>
						</s:else>
						<!--<s:if test="%{isDeliveryDateShow}">
            <td colspan="2" nowrap="nowrap"></td>
            <td width="3%"></td>
            <td width="18%"></td>
            <td colspan="1"></td>
            <td width="1%"></td>
            </s:if>-->

						<td colspan="2" nowrap="nowrap"><label name="availbal"
							id="availbal1" ondblclick="callShowDiv(this);"><%=label.get("availbal")%>:</label></td>
						<td width="3%"></td>
						<td width="18%"><s:textfield name="levOpeningBalance"
							theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /></td>
						<td colspan="1"></td>
						<td width="1%"></td>

					</tr>					
					<tr>
						<td width="16%" colspan="1"><label name="fromdate"
							id="fromdate" ondblclick="callShowDiv(this);"><%=label.get("fromdate")%></label>
						<span class="formtext">:</span><span class="style2"><font
							color="red">*</font></span></td>
						<td colspan="2"><s:textfield name="leaveFromDtl"
							maxlength="10" onkeypress="return numbersWithHiphen();"
							theme="simple" onblur="getBlank();" /></td>
						<td><s:a
							href="javascript:NewCal('paraFrm_leaveFromDtl','DDMMYYYY');">
							<s:if test="approvalFlag">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:if>
						</s:a></td>
						<td colspan="2"><label name="halfday" id="halfday"
							ondblclick="callShowDiv(this);"><%=label.get("halfday")%></label>
						:</td>
						<td width="4%"><s:checkbox name="checkMe" id="checkMeId" /></td>
						<td><s:select theme="simple" name="halfDayFlag" id="firsthf"
							list="#{'FH':'First Half','SH':'Second Half'}" /></td>
						<td width="18%"></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="1"><label name="todate" id="todate"
							ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>
						:<span class="style2"><font color="red">*</font></span></td>
						<td colspan="2"><s:textfield name="leaveToDtl"
							onkeypress="return numbersWithHiphen();" theme="simple"
							maxlength="10" /></td>
						<td><s:a
							href="javascript:NewCal('paraFrm_leaveToDtl','DDMMYYYY');">
							<s:if test="approvalFlag">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" onclick="callDate()" />
							</s:if>

						</s:a></td>
						<td colspan="2"><label name="halfday" id="halfday1"
							ondblclick="callShowDiv(this);"><%=label.get("halfday")%></label>
						:</td>
						<td><s:checkbox name="checkMeForhalfTodate"
							id="checkMeForhalfTodateId" /></td>
						<td><s:select theme="simple" name="halfDayFlagTodate"
							id="firsthftodate" list="#{'FH':'First Half','SH':'Second Half'}" /></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<s:if test="%{isDeliveryDateShow}">
							<!-- isDeliveryDateShow -->
							<td colspan="1" nowrap="nowrap"><label
								name="expectedDeliverydate" id="expectedDeliverydate"
								ondblclick="callShowDiv(this);"><%=label.get("expectedDeliverydate")%></label>:<font
								color="red">*</font></td>
							<td colspan="2"><s:textfield
								onkeypress="return numbersWithHiphen();" name="deliveryDate"
								maxlength="10" /></td>
							<td colspan="1"><s:a
								href="javascript:NewCal('paraFrm_deliveryDate','DDMMYYYY');">
								<img class="iconImage" id="ctrlHide"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
							<td width="10%"></td>
							<td width="8%"></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</s:if>
					</tr>
					<tr>
						<s:if test="uploadProofFlag">
							<!-- uploadProofFlag -->
							<td colspan="1">Upload Proof:</td>
							<td colspan="2"><s:textfield name="userUploadFileName"
								maxlength="40" /></td>
							<td colspan="1"><s:textfield name="uploadFileName"
								readonly="true" /></td>
							<td width="10%"><input name="Upload" type="button"
								class="token" value="Browse"
								onclick="uploadFile('uploadFileName');" /></td>
							<td width="8%"><s:submit name="" value=" Add Proof"
								cssClass=" add" action="LeaveApplication_addMultipleProof"
								onclick="return callUpload();" /></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</s:if>
					</tr>
					<tr>
						<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<%
									int cnt = 0;
									int cnt1 = 0;
								%>
							<s:iterator value="proofList" status="stat">
								<tr>
									<td width="80%"><%=++cnt%><s:hidden name="proofSrNo" /><s:hidden
										name="proofName" /><s:hidden name="proofFileName" /> <a
										href="#"
										onclick="showRecord('<s:property value="proofName" />');"><s:property
										value="proofFileName" /></a></td>
									<td width="20%"><a href="#"
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
						<td colspan="1"></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>

					<tr>
						<s:if test="approvalFlag">
							<td colspan="9">
							<div align="center"><input type="button" class="add"
								theme="simple" value="    Add" id="addButton"
								onclick="getBlank();return callAdd();" /> <s:submit
								cssClass="reset" theme="simple" value="    Clear"
								onclick="return callClear1();" /></div>
							</td>
							<td width="1%"></td>
						</s:if>
					</tr>
				</table>
				</td>
			</tr>
			<!--  START HRS -->
			<tr id="hrs">
				<td colspan="5">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="15" class="formhead"><strong
							class="forminnerhead">Apply For Leave</strong> <s:hidden
							name="shiftTime" /> <s:hidden name="diffTime" /> <s:hidden
							name="editFlagHrs" /></td>
					</tr>
					<tr>
						<td width="16%" colspan="1"><label name="levtype"
							id="levtype2" ondblclick="callShowDiv(this);"><%=label.get("levtype")%></label>
						<span class="formtext">:</span><span class="style2"><font
							color="red">*</font></span></td>
						<td colspan="2"><s:textfield
							name="leaveApplication.leaveTypeNameHrs" readonly="true"
							theme="simple" cssStyle="background-color: #F2F2F2;" /> <s:hidden
							name="leaveApplication.leaveTypeCodeHrs" /></td>

						<s:if test="%{leaveApplication.isEditFlag}">
							<td width="15%">&nbsp;</td>
						</s:if>
						<s:else>
							<td width="10%"><s:if test="%{editFlagHrs=='true'}">
								<img id="ctrlHide" src="../pages/images/search2.gif" height="16"
									align="absmiddle" class="iconImage" width="16" theme="simple"
									onclick="javascript:getLeaveTypeHrs();"">
							</s:if></td>
						</s:else>
						<td colspan="2" nowrap="nowrap"><label name="availbal"
							id="availbal1" ondblclick="callShowDiv(this);"><%=label.get("availbal")%>:</label><span
							class="style2"><font color="red">*</font></span></td>

						<td width="18%" colspan="3"><s:textfield
							name="availableBalanceDay" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" size="5" />Days <s:textfield
							name="availableBalanceHrs" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" size="5" />Hrs</td>
					</tr>
					<tr>
						<td width="16%" colspan="1"><label name="date" id="date"
							ondblclick="callShowDiv(this);"><%=label.get("date")%></label> <span
							class="formtext">:</span><span class="style2"><font
							color="red">*</font></span></td>
						<td colspan="2"><s:textfield name="dateHrs" maxlength="10"
							onkeypress="return numbersWithHiphen();" theme="simple" /></td>
						<td><s:a
							href="javascript:NewCal('paraFrm_dateHrs','DDMMYYYY');">
							<s:if test="approvalFlag">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:if>
						</s:a></td>
						<td colspan="2"></td>
						<td width="4%"></td>
						<td></td>
						<td width="18%"></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="1"><label name="fromTime1" id="fromTIme1"
							ondblclick="callShowDiv(this);"><%=label.get("fromTime")%></label>:<span
							class="style2"><font color="red">*</font></span></td>
						<td colspan="2"><s:textfield name="fromTime" maxlength="5" />
						</td>
						<td>(HH24:MI)</td>
						<td colspan="2"><label name="toTime" id="toTime"
							ondblclick="callShowDiv(this);"><%=label.get("toTime")%></label>
						:<span class="style2"><font color="red">*</font></span></td>

						<td><s:textfield name="toTime" maxlength="5" /></td>
						<td>(HH24:MI)</td>
						<td></td>
					</tr>
				</table>
				</td>
			</tr>
			<!-- END HRS -->
		</s:if>
		<!-- LEAVE TYPE TABLE ENDS -->
		<!-- LEAVE LIST TABLE STARTS -->
		<tr id="leave1">
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="22" width="100%" class="formhead"><strong
								class="forminnerhead"><label name="leavelist"
								id="leavelist" ondblclick="callShowDiv(this);"> <%=label.get("leavelist")%></label></strong></td>
						</tr>
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td valign="top" class="formth" width="5%"><label
										name="srnum" id="srnum" ondblclick="callShowDiv(this);"><%=label.get("srnum")%></label></td>
									<td width="30%" valign="top" class="formth"><label
										name="levtype" id="levtype" ondblclick="callShowDiv(this);"><%=label.get("levtype")%></label></td>
									<td width="10%" valign="top" class="formth"><label
										name="fromdate" id="fromdate1" ondblclick="callShowDiv(this);"><%=label.get("fromdate")%></label></td>
									<td width="10%" valign="top" class="formth"><label
										name="todate" id="todate1" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label></td>
									<td width="10%" valign="top" class="formth"><label
										name="levdays" id="levdays" ondblclick="callShowDiv(this);"><%=label.get("levdays")%></label></td>

									<s:if test="avaibal">
										<td width="10%" valign="top" class="formth"><label
											name="availbal" id="availbal" ondblclick="callShowDiv(this);"><%=label.get("availbal")%></label></td>
									</s:if>
									<s:if test="%{leaveApplication.isPenaltyFlag}">
										<td width="10%" valign="top" class="formth" nowrap="nowrap">
										Penalty</td>
									</s:if>
									<s:if test="leaveApplication.uploadProofViewFlag">
										<td width="10%" valign="top" class="formth" nowrap="nowrap">
										Upload Proof</td>
									</s:if>
									<s:if test="%{leaveApplication.approvalFlag}">
										<td width="25%" valign="top" class="formth" nowrap="nowrap">Edit
										| Remove</td>

									</s:if>
								</tr>
								<%
								int count = 0, p = 0;
								%>
								<s:iterator value="att" status="stat">
									<tr>
										<td><%=++count%><s:hidden name="srNo"
											value="%{<%=count%>}" /></td>
										<td><s:property value="slevType" /><s:hidden
											name="slevType" /> <s:hidden name="slevCode" /></td>
										<td align="center"><s:property value="sleaveFromDtl" /><s:hidden
											name="sleaveFromDtl" /></td>
										<td align="center"><s:property value="sleaveToDtl" /><s:hidden
											name="sleaveToDtl" /></td>
										<td>
										<center><s:property value="slevClosingBalance" /><s:hidden
											name="slevClosingBalance" /></center>
										</td>
										<s:hidden name="halfDayType" />
										<s:hidden name="halfDayTypeToDate" />
										<s:if test="%{leaveApplication.avaibal}">
											<td align="center"><s:property value="availBalance" /></td>
										</s:if>

										<s:if test="%{leaveApplication.isPenaltyFlag}">
											<td width="10%" align="center" nowrap="nowrap"><s:property
												value="iteratorPenaltyDays" /></td>
										</s:if>

										<s:if test="leaveApplication.uploadProofViewFlag">
											<td><s:iterator value="ittUploadList">
												<a href="#"
													onclick="showRecord('<s:property value="uploadDoc" />');"><s:property
													value="uploadDocPath" /> </a>
											</s:iterator></td>
										</s:if>
										<s:hidden name="availBalance" />
										<s:hidden name="slevLeaveDays" />
										<s:hidden name="closeBalance" />
										<s:hidden name="onholdhidden" />
										<s:hidden name="uploadDoc" />
										<s:hidden name="uploadDocPath" />
										<s:hidden name="iteratorPenaltyDays" />
										<s:hidden name="iteratorAdjustPenaltyDays" />
										<s:hidden name="iteratorUnAdjustPenaltyDays" />
										<s:hidden name="expectedDeliveryDate" />
										<s:hidden name="isHalfDayLeaveItt" />

										<s:if test="%{leaveApplication.approvalFlag}">
											<td><input type="button" class="edit"
												onclick="callForEdit('<%=count %>','<s:property value="slevCode"/>','<s:property value="slevType"/>','<s:property value="slevClosingBalance"/>','<s:property value="sleaveFromDtl" />','<s:property value="sleaveToDtl"/>',<%=count %>,'<s:property value="%{availBalance}"/>','<s:property value="closeBalance"/>','<s:property value="onholdhidden"/>','<s:property value="halfDayType" />','<s:property value="halfDayTypeToDate" />','<s:property value="iteratorPenaltyDays" />','<s:property value="iteratorAdjustPenaltyDays" />','<s:property value="iteratorUnAdjustPenaltyDays" />','<s:property value="expectedDeliveryDate" />','<s:property value="isHalfDayLeaveItt" /> ')"
												value="  Edit" /> <s:if test="removeFlag">
												<input type="button" class="delete"
													onclick="callForDelete(<%=count %>)" value="  Remove" />
											</s:if> <s:else></s:else></td>
										</s:if>
									</tr>
									<%
									p = count;
									%>
								</s:iterator>
								<%
								p = 0;
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- LEAVE LIST TABLE ENDS -->

		<!-- LEAVE COMMENTS TABLE  STARTS -->
		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="7%" colspan="1" nowrap="nowrap"><label
						name="selectReason" id="selectReason"
						ondblclick="callShowDiv(this);"><%=label.get("selectReason")%></label>:
					</td>
					<td width="35%" colspan="3"><s:textfield
						name="leaveReasonName" size="105" readonly="true" /><s:hidden
						name="leaveReasonCode" /> <s:if test="isLeaveTypeApp">
						<img src="../pages/common/css/default/images/search2.gif"
							class="iconImage" width="16" height="15"
							onclick="javascript:getReason();" />
					</s:if></td>
				</tr>
				
				<tr>
					<td width="7%" colspan="1" nowrap="nowrap"><label
						name="reasons" id="reasons" ondblclick="callShowDiv(this);"><%=label.get("reasons")%></label>:
					</td>
					<td width="35%" colspan="1"><s:if
						test="%{leaveApplication.approvalFlag}">
						<s:textarea label="%{getText('medicalCert')}" theme="simple"
							cols="40" onkeypress="getValueBlank();" rows="4"
							name="leaveApplication.medicalCert" />

					</s:if> <s:else>
						<s:textarea label="%{getText('medicalCert')}" theme="simple"
							cols="40" readonly="true" rows="4"
							name="leaveApplication.medicalCert" />
					</s:else></td>
					<td width="17%" colspan="1"><label name="appcomments"
						id="appcomments" ondblclick="callShowDiv(this);"><%=label.get("appcomments")%></label>:
					</td>
					<td width="41%" colspan="1"><s:if
						test="%{leaveApplication.approvalFlag}">
						<s:textarea label="%{getText('comments')}" theme="simple"
							cols="40" rows="4" name="leaveApplication.comments" />
					</s:if> <s:else>
						<s:textarea label="%{getText('comments')}" theme="simple"
							cols="40" rows="4" readonly="true"
							name="leaveApplication.comments" />

					</s:else></td>
				</tr>



			</table>
			</td>
		</tr>

		<!-- LEAVE COMMENTS TABLE  ENDS -->

		<!-- LEAVE APPROVAL FORM BUTTON  STARTS -->
		<tr>
			<s:if test="saveDetailFlag">
				<td width="100%" colspan="3"><s:if test="appRejSendBackFlag">
					<input type="button" class="token" id="approve" value="    Approve"
						onclick="return checkAppStatus(this,'A');" theme="simple" />
					<input type="button" class="token" id="reject" value="    Reject"
						onclick="return checkAppStatus(this,'R');" theme="simple" />
					<input type="button" class="token" id="sendback"
						value="    Send Back" onclick="return checkAppStatus(this,'B')"
						theme="simple" />
				</s:if> <s:if test="approveAppCanFlag">

					<input type="button" class="token" id="approvecancel"
						value="    Approve Cancellation "
						onclick="return checkApproveCancellationStatus(this,'X');"
						theme="simple" />
					<input type="button" class="token" id="rejectcancel"
						value="    Reject Cancellation "
						onclick="return checkApproveCancellationStatus(this,'Z');"
						theme="simple" />
				</s:if> <s:if test="leaveApplication.isAdminApprovalClick">
					<input type="button" class="token" value="    Back To List"
						onclick="return callBackAdmin();" theme="simple" />
				</s:if> <s:else>
					<input type="button" class="token" value="    Back To List"
						onclick="return callBack();" theme="simple" />
				</s:else></td>
			</s:if>
		</tr>
		<!-- LEAVE APPROVAL FORM BUTTON  ENDS -->

		<!-- LEAVE FORM BUTTON  STARTS -->

		<!--<s:if test="isButtonVisible">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>

						<td width="100%"><s:if test="draftFlag">
							<s:submit cssClass="token" action="LeaveApplication_save"
								value="    Draft" onclick="return callSave('D');" />
						</s:if> <s:if test="searchResetFlag">
							<s:submit cssClass="reset" action="LeaveApplication_reset"
								value="    Reset" />
							<input type="button" class="search" value="    Search"
								onclick="javascript:callsF9(500,325,'LeaveApplication_f9actionLeaveCode.action');" />

						</s:if> <s:if test="deleteBtnFlag">
							<s:submit cssClass="delete" action="LeaveApplication_delete"
								value="   Delete"
								onclick="return callDelete('paraFrm_leaveApplication_leaveCode');" />
						</s:if> <s:if test="sendAppBtnFlag">
							<s:submit cssClass="token" action="LeaveApplication_save"
								value="    Send For Approval" onclick="return callSave('P');" />
						</s:if> <s:if test="cancelBtnFlag">
							<s:submit cssClass="delete" action="LeaveApplication_cancelForm"
								value="   Cancel" onclick="return cancel();" />

						</s:if> <s:if test="reportBtnFlag">
							<input type="button" class="token" value="   Report"
								onclick="callReport('LeaveApplication_report.action')" />
						</s:if> <s:if test="commonBtnFlag">

							<s:submit cssClass="token" action="LeaveApplication_back"
								value="    Back" />

						</s:if></td>

					</tr>
				</table>
				<label></label></td>

			</tr>
		</s:if>-->


		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

		<!-- LEAVE FORM BUTTON  ENDS -->

		<s:hidden name="leaveApplication.hiddenDate" />
		<s:hidden name="leaveApplication.zeroBalance" />
		<s:hidden name="leaveApplication.advanceAppl" />
		<s:hidden name="leaveApplication.paracode" />
		<s:hidden name="halfDayCheck" />
		<s:hidden name="isAddFlag" />
		<s:hidden name="leaveApplication.isEditFlag" />
		<s:hidden name="brnHDayFlag" />
		<s:hidden name="leaveApplication.onhold" />
		<s:hidden name="leaveApplication.oldLeaveDays" />
		<s:hidden name="leaveApplication.oldPenaltyAdjDays" />
		<s:hidden name="chkEdit" />
		<s:hidden name="approvalFlag" />
		<s:hidden name="leaveApplication.isApprovalClick" />
		<s:hidden name="leaveApplication.isAdminApprovalClick" />
		<s:hidden name="saveDetailFlag" />
		<s:hidden name="policyCode" />
		<s:hidden name="compOffDays" />
		<s:hidden name="empGender" />
		<s:hidden name="oldFromDate" />
		<s:hidden name="oldToDate" />
		<s:hidden name="negativeAllowBal" />
		<s:hidden name="checkRemove" />
		<s:hidden name="isProofRequired" />
		<s:hidden name="uploadProofFlag" />
		<s:hidden name="levClosingBalance" />
		<s:hidden name="leaveApplication.leaveTotalDays" />
		<s:hidden name="dataPath" />
		<s:hidden name="checkRemoveUpload" />
		<s:hidden name="checkApproveRejectStatus" />
		<s:hidden name="hiddenPenaltyDays" />
		<s:hidden name="hiddenAdjustPenaltyDays" />
		<s:hidden name="hiddenUnAdjustPenaltyDays" />
		<s:hidden name="checkApproveCancelStatus" />
		<s:hidden name="maternityLeaveCode" />
		<s:hidden name="isDeliveryDateShow" />
		<s:hidden name="isPoolDefine" />
		<s:hidden name="isHalfDayLeave" />
		<s:hidden name="source" id="source" />



	</table>

</s:form>



<script>
onLoad();

function onLoad(){
try{
var flagHrs=document.getElementById('paraFrm_flagHrs').value;
//alert(flagHrs);
if(flagHrs=='flag'){
document.getElementById('leave').style.display='none';
document.getElementById('leave1').style.display='none';
}
else{
document.getElementById('hrs').style.display='none';
}
}catch(e){}
}



function getValueBlank()
{


document.getElementById('paraFrm_leaveReasonName').value ='';
document.getElementById('paraFrm_leaveReasonCode').value ='';
}

function showRecord(fileName){
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "LeaveApplication_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
}

function draftFun(){ 
 		var leaveDtlRow = document.getElementsByName('sleaveFromDtl');	 		 		 		 		
 		var empCode = document.getElementById('paraFrm_leaveApplication_empCode').value;
		 if(empCode==""){
			alert (" Please fill the leave application");
			return false;
		 }
		 if(leaveDtlRow.length > 1){	 
		 	for(var i=0; i< leaveDtlRow.length;i++){		 
		 		if (i < leaveDtlRow.length-1){		 		 	
		 			var leaveDtlFrm= leaveDtlRow[i].value;			
		 			var leaveDtlFrmNext = leaveDtlRow[i+1].value;		 			
		 			strDate1 = leaveDtlFrm.split("-");
		 			strDate2 = leaveDtlFrmNext.split("-"); 
					starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 					 
					endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 	
					/*if(endtime <= starttime) { 		
						alert("From Date Should Not be Less than Previous From Date");
						return false;
					}*/		
		  		  }		  		
		 		}			 		 		
		 	}				
			var flagHrs=document.getElementById('paraFrm_flagHrs').value;	
			if(flagHrs=='flag'){
				if(document.getElementById('paraFrm_leaveApplication_leaveTypeCodeHrs').value==''){
					alert('Please select '+document.getElementById('levtype').innerHTML.toLowerCase());
					document.getElementById('paraFrm_leaveApplication_leaveTypeNameHrs').focus();
					return false;
				}
				if(document.getElementById('paraFrm_dateHrs').value==''){
					alert('Please select '+document.getElementById('date').innerHTML.toLowerCase());
					document.getElementById('paraFrm_dateHrs').focus();
					return false;
				}			
				if(document.getElementById('paraFrm_fromTime').value==''){
					alert('Please enter from time');
					document.getElementById('paraFrm_fromTime').focus();
					return false;
				}
				if(IsValidTime(document.getElementById('paraFrm_fromTime').value)){
				}
				else{
					document.getElementById('paraFrm_fromTime').focus();
					return false;
				}
				if(document.getElementById('paraFrm_toTime').value==''){
					alert('Please enter to time');
					document.getElementById('paraFrm_toTime').focus();
					return false;
				}if(IsValidTime(document.getElementById('paraFrm_toTime').value)){	
				}
				else{
				document.getElementById('paraFrm_toTime').focus();
				return false;
				}		
			}	
			//document.getElementById("overlay").style.visibility = "visible";
			//document.getElementById("overlay").style.display = "block";		
			document.getElementById('draft').disabled=true;		 
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action ='LeaveApplication_save.action?checkStatus=D'; 
			if(flagHrs=='flag'){
				document.getElementById('paraFrm').action ='LeaveApplication_saveHrs.action?checkStatus=D';
			}		
			document.getElementById('paraFrm').submit();	
	}

 function IsValidTime(id) {						
			var timeStr=id;//document.getElementById('fromTime'+id).value;				
			var timePat = /^(\d{1,2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;		
			var matchArray = timeStr.match(timePat);		
			if (matchArray == null) {
			alert("Please enter time in a valid format(HH24:MI)");
			return false;
			}
			hour = matchArray[1];
			minute = matchArray[2];
			second = matchArray[4];
			ampm = matchArray[6];			
			if (second=="") { second = null; }
			if (ampm=="") { ampm = null }			
			if (hour < 0  || hour > 23) {
				alert("Hour must be between 0 and 23");
				return false;
			}			
			if (minute<0 || minute > 59) {
				alert ("Minute must be between 0 and 59.");
				return false;
			}
			if (second != null && (second < 0 || second > 59)) {
				alert ("Second must be between 0 and 59.");
				return false;
			}
			return true;
}


function backFun(){	 
		document.getElementById('paraFrm').target = "_self";
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages'){
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices'){
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		else if(document.getElementById('source').value=='mytimecard'){
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_mytimeCard.action';
		}
		else if(document.getElementById('source').value=='myLeaves'){
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_myLeaves.action';
		}
		else{
		document.getElementById('paraFrm').action = 'LeaveApplication_back.action';
		}		
		document.getElementById('paraFrm').submit();
}

function callBack(){
		document.getElementById('paraFrm').target = "_self";
		//this is for mypage back button
		if(document.getElementById('source').value=='mymessages'){
			document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else{
			document.getElementById('paraFrm').action = 'LeaveApproval_callstatus.action';
		}
		document.getElementById('paraFrm').submit();

}

function callBackAdmin(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'LeaveAdminApproval_callstatus.action';
		document.getElementById('paraFrm').submit();

}

function sendforapprovalFun(){ 
	var empCode = document.getElementById('paraFrm_leaveApplication_empCode').value;
			if(empCode==""){
			alert (" Please fill the leave application");
			return false;
			}	
			//document.getElementById("overlay").style.visibility = "visible";
		//document.getElementById("overlay").style.display = "block";
		
		 var conf=confirm("Do you really want to send for approval ?");
		 if(conf){
			 	document.getElementById('sendforapproval').disabled=true;
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action ='LeaveApplication_save.action?checkStatus=P'; 		
				var flagHrs=document.getElementById('paraFrm_flagHrs').value;	
				if(flagHrs=='flag'){
					document.getElementById('paraFrm').action ='LeaveApplication_saveHrs.action?checkStatus=P'; 
				}
				document.getElementById('paraFrm').submit();			 		
		  }
		  else{
			    return false;
		  }		
}


function cancelapplicationFun(){ 
		var st=document.getElementById('paraFrm_status').value;
		//alert("st "+st);
		if(st=='F'){
				alert("You can not cancel forwarded application");
				return false;
		}
	 	var conf=confirm("Do you really want to cancel this application ?");
		if(conf){
			 	document.getElementById('cancelapplication').disabled=true;
			 	document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action ='LeaveApplication_cancelForm.action'; 
				document.getElementById('paraFrm').submit();

		}else{
	  			 return false;
	  	}		
}

function resetFun(){		 
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'LeaveApplication_reset.action';
		document.getElementById('paraFrm').submit();
}


function reportFun(){		 
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'LeaveApplication_report.action';
		document.getElementById('paraFrm').submit();

}
 
 function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'LeaveApplication_report.action';
		document.getElementById("paraFrm").submit();
}

function deleteFun(){
 	var conf=confirm("Do you really want to delete this record ?");
	if(conf){
			document.getElementById('delete').disabled=true;
			var flagHrs=document.getElementById('paraFrm_flagHrs').value;	
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action ='LeaveApplication_delete.action'; 
			if(flagHrs=='flag'){
				document.getElementById('paraFrm').action ='LeaveApplication_deleteHrs.action'; 
			}
			document.getElementById('paraFrm').submit();
	}
	else{
			 return false; 
	}
}

function uploadFile(fieldName) {
		var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}

function callUpload(){
	var uploadFile = document.getElementById('paraFrm_userUploadFileName').value;
	var uploadFilePath = document.getElementById('paraFrm_uploadFileName').value;	
	if(uploadFilePath==""){
		alert("Please Upload proof");
		return false;
	}	
	if(uploadFile==""){
		if(uploadFilePath==""){
			alert("Please Upload proof");
			return false;
		}	
		else{
			alert("Proof uploaded successfully.Please give the name for uploaded file");
			return false;
		}		
	}
	return true;
}
 
function callDelete(code){
	   if(document.getElementById(code).value==""){
		   alert("Please select a record to delete");
		   return false;
	   } 	   
	   if(!(document.getElementById('paraFrm_status').value=="D")){		
			var msg="You can't delete the";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='N'){
				msg+=" Cancelled";
			}			
			if(st=='P'){
				msg+=" Submited";
			}
			if(st=='R'){
				msg+=" Rejected";
			}
			if(st=='A'){
				msg+=" Approved ";
			}
			if(st=='C'){
				msg+=" Applied For Cancellation";
			}			
			if(st=='F'){
				msg+=" Forwarded ";
			}
			alert(  msg+" application !");
			return false;
			}	   
      		var conf=confirm("Do you really want to delete this record ?");
  			if(conf) {
  				return true;
  			}
	  		else{
	  			 return false;
	  		}
	  	return true;
 }

	

function getEmployee(){
		var empid =document.getElementById('paraFrm_leaveApplication_leaveCode').value;
		if(empid < 1){
			callsF9(500,325,'LeaveApplication_f9actionEmployeeCode.action');
		}else{
			alert("You can't change or select employee for this Application ! ")
		}		
	}
	
	
function getReason(){ 	
 	callsF9(500,325,'LeaveApplication_f9getReason.action');
 	document.getElementById('paraFrm_leaveApplication_medicalCert').value = '';		 
}
	
	
function getKeepInformedEmp(){
	try{	 
		var empcode=document.getElementById('paraFrm_leaveApplication_empCode').value;
	 	//	var emp =document.getElementById('paraFrm_employeeId').value;
		if(empcode==""){
				alert("Please select "+document.getElementById('appname').innerHTML.toLowerCase());
				return false;
		}
		else{
			callsF9(500,325,'LeaveApplication_f9KeepInformedEmployee.action');
		}
	}
	catch(e){
		alert(e);
	} 	
}
	
	function callKeepInformed(){		
		if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){
			var msg="You can't add keep informed to for the";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='N'){
				msg+=" Cancelled";
			}
			if(st=='P'){
				msg+=" Submited";
			}
			if(st=='D'){
				msg+=" Draft";
			}
			if(st=='R'){
				msg+=" Rejected";
			}
			if(st=='A'){
				msg+=" Approved ";
			}
			if(st=='F'){
				msg+=" Forwarded ";
			}
			alert(  msg+" application !");
			return false;
			}		
		 	var empcode=document.getElementById('paraFrm_leaveApplication_empCode').value;
		 	var emp =document.getElementById('paraFrm_employeeId').value;
		 	if(empcode==""){
				 alert("Please select "+document.getElementById('appname').innerHTML.toLowerCase());
		 		 return false;
			 }
			if(emp==""){
				alert("Please select Keep Informed To ");
				return false;
			}	
		return true;
}
	
	
function getLeaveType(){
		var leaveId =document.getElementById('paraFrm_leaveApplication_levCode').value;
		var empcode=document.getElementById('paraFrm_leaveApplication_empCode').value;			 
		if(empcode==""){
				alert("Please select "+document.getElementById('appname').innerHTML.toLowerCase());
				return false;
		}  		
		if(leaveId ==""){
			callsF9(500,325,'LeaveApplication_f9ltypeaction.action');
		}else{
				document.getElementById('paraFrm_levClosingBalance').value ="";
				document.getElementById('paraFrm_leaveFromDtl').value ="";
				document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value ="";
				document.getElementById('paraFrm_leaveToDtl').value ="";
				callsF9(500,325,'LeaveApplication_f9ltypeaction.action');
		}		
	}
	
function getLeaveTypeHrs(){
		var leaveId =document.getElementById('paraFrm_leaveApplication_leaveTypeCodeHrs').value;
		var empcode=document.getElementById('paraFrm_leaveApplication_empCode').value;	 
		if(empcode==""){
				alert("Please select "+document.getElementById('appname').innerHTML.toLowerCase());
				return false;
		}				
		//document.getElementById('paraFrm_levClosingBalance').value ="";
		//document.getElementById('paraFrm_leaveFromDtl').value ="";
		//document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value ="";
		//document.getElementById('paraFrm_leaveToDtl').value ="";
		callsF9(500,325,'LeaveApplication_f9ltypeactionHrs.action');
}
		
function callDate(){
			document.getElementById('paraFrm_leaveToDtl').focus();
}

function callAjax(link, formName) {	
		document.getElementById("paraFrm").target="main";
		parent.frames[2].name="main";
		retrieveURLLeave(link,formName);
		parent.frames[2].name="main";	
}

function callClear(){
		document.getElementById('paraFrm_leaveApplication_paracode').value ="";
		document.getElementById('paraFrm_leaveApplication_hiddenDate').value ="";
		document.getElementById('paraFrm_leaveApplication_leaveId').value ="";
		document.getElementById('paraFrm_leaveApplication_levType').value ="";
		document.getElementById('paraFrm_levClosingBalance').value ="";
		document.getElementById('paraFrm_leaveFromDtl').value ="";
		document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value ="";
		document.getElementById('paraFrm_leaveToDtl').value ="";
		document.getElementById('paraFrm_levOpeningBalance').value ="";
		document.getElementById('paraFrm_leaveApplication_levCode').value="";
		document.getElementById('paraFrm_leaveApplication_isEditFlag').value="false";
		document.getElementById('checkMeId').checked=false;
		return false;
}

function callForEdit(srno,id,levType,leaveTotalDays,leaveFromDtl,leaveToDtl,edit,balance,closebalance,onhold,halfdaytype,halfdayTodate,penaltyDays,adjustPenaltyDays,unadjustPenaltyDays,deliveryDate,halfDayLeave){
	try{			 
		if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){		
			var msg="You can't edit the";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='N'){
				msg+=" Cancelled";
			}
			if(st=='P'){
				msg+=" Submited";
			}
				if(st=='R'){
				msg+=" Rejected";
			}
				if(st=='A'){
				msg+=" Approved ";
			}
				if(st=='F'){
				msg+=" Forwarded ";
			}
			alert(  msg+" application !");
			return false;
			}
			var halfDay =  document.getElementById('paraFrm_halfDayCheck').value;			
			var closing = document.getElementById('paraFrm_levClosingBalance').value;
			document.getElementById('paraFrm_leaveApplication_isEditFlag').value=true;
		   //alert('aaa'+document.getElementById('paraFrm_leaveApplication_isEditFlag').value);		 
			document.getElementById("paraFrm_srNo").value=srno;	
			//alert(document.getElementById("paraFrm_srNo").value);		
			document.getElementById("paraFrm_leaveApplication_levCode").value=id;
			document.getElementById("paraFrm_leaveApplication_levType").value=levType;
			document.getElementById("paraFrm_leaveApplication_leaveTotalDays").value=leaveTotalDays;
			document.getElementById("paraFrm_leaveFromDtl").value=leaveFromDtl;
			document.getElementById("paraFrm_leaveToDtl").value=leaveToDtl;
			document.getElementById("paraFrm_checkEdit").value=edit;			  	
			//document.getElementById('paraFrm_isDeliveryDateShow').value=true;
			//alert('val--------- '+document.getElementById('paraFrm_isDeliveryDateShow').value);
		    //alert('deliveryDate--------- '+deliveryDate);
			//document.getElementById("paraFrm_deliveryDate").value=deliveryDate;
			if(unadjustPenaltyDays>0){	 	
				if(document.getElementById('paraFrm_isDeliveryDateShow').value=='false')
			  		document.getElementById("paraFrm_levOpeningBalance").value=eval(closebalance)+eval(adjustPenaltyDays)+eval(leaveTotalDays);
			  	}
			  	else{ 	
			  		if(document.getElementById('paraFrm_isDeliveryDateShow').value=='false')
			  		document.getElementById("paraFrm_levOpeningBalance").value=eval(closebalance)+eval(leaveTotalDays)+eval(penaltyDays);			  	
			  	}			  
			 	document.getElementById("paraFrm_levClosingBalance").value=closebalance;
			 	document.getElementById("paraFrm_leaveApplication_onhold").value=onhold;
			 	document.getElementById("paraFrm_leaveApplication_oldLeaveDays").value=leaveTotalDays;
			 	document.getElementById("paraFrm_leaveApplication_oldPenaltyAdjDays").value=adjustPenaltyDays;			 	
			 	document.getElementById("paraFrm_isHalfDayLeave").value=trim(halfDayLeave);			 	
			 	document.getElementById('paraFrm_chkEdit').value=srno;			 		
			 	//alert('aa'+document.getElementById("paraFrm_leaveApplication_leaveTotalDays").value);
			 	//alert('halfDay'+halfDay);
			 	//alert('closing--------'+document.getElementById('paraFrm_levClosingBalance').value);
			 	if(halfdaytype=="FH" || halfdaytype=="SH"  ){
			 			document.getElementById('checkMeId').checked =true;
			 			document.getElementById('firsthf').value=halfdaytype;			 			 
			 	}else{
			 		document.getElementById('checkMeId').checked =false;
			 	}			 		
			 	if(halfdayTodate=="FH" || halfdayTodate=="SH"  ){			 			
			 		document.getElementById('checkMeForhalfTodateId').checked =true;
			 		document.getElementById('firsthftodate').value=halfdayTodate;			 			 
			 	}
			 	else{
			 		document.getElementById('checkMeForhalfTodateId').checked =false;
			 	}
			 	document.getElementById('paraFrm').target="_self";
			 	document.getElementById("paraFrm").action="LeaveApplication_editLeaveType.action";
			 			document.getElementById("paraFrm").submit();
			 	}catch(e){alert(e);}				 	
	    }
	    
function callForRemoveUpload(id){
	    	var conf=confirm("Are you sure !\n You want to Remove this record ?");
  			if(conf){
				document.getElementById('paraFrm_checkRemoveUpload').value=id;
				document.getElementById('paraFrm').target="_self";
				document.getElementById("paraFrm").action="LeaveApplication_removeUploadFile.action";
		  	    document.getElementById("paraFrm").submit();
		  	}	
}
	    
function callForRemove(id){
  if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){		
	 var msg="You can't remove the";
	 var st=document.getElementById('paraFrm_status').value;
	 if(st=='N'){
				msg+=" Cancelled";
	 }if(st=='P'){
				msg+=" Submited";
	 }if(st=='R'){
				msg+=" Rejected";
	 }if(st=='A'){
				msg+=" Approved ";
	 }
	 if(st=='F'){
				msg+=" Forwarded ";
	 }
	 alert(  msg+" application !");
	 return false;
  }else{
	    var conf=confirm("Are you sure !\n You want to Remove this record ?");
  		if(conf){
			document.getElementById('paraFrm_checkRemove').value=id;
			document.getElementById('paraFrm').target="_self";
			document.getElementById("paraFrm").action="LeaveApplication_removeKeepInformed.action";
		  	document.getElementById("paraFrm").submit();
		}else{
		  	return false;
		}
	}
	return true;			
}
	    
function callForDelete(id){ 
		if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){		
			var msg="You can't remove the";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='N'){
				msg+=" Cancelled";
			}
			if(st=='P'){
				msg+=" Submited";
			}
				if(st=='R'){
				msg+=" Rejected";
			}
				if(st=='A'){
				msg+=" Approved ";
			}
			if(st=='F'){
				msg+=" Forwarded ";
			}
			alert(  msg+" application !");
			return false;
			}
			else{
				var conf=confirm("Are you sure !\n You want to Remove this record ?");
  				if(conf){
					  	document.getElementById('paraFrm_checkEdit').value=id;
					  	document.getElementById('paraFrm').target="_self";
					  	document.getElementById("paraFrm").action="LeaveApplication_deleteData.action";
		  				document.getElementById("paraFrm").submit();
  				}			
  			}
} 	
   
function callSave(id){
		var empCode = document.getElementById('paraFrm_leaveApplication_empCode').value;
		if(empCode==""){
			alert (" Please fill the leave application");
			return false;
		}
		if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){		
			var msg="You can't modify the";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='P'){
				msg+=" Submited";
			}
			if(st=='N'){
				msg+=" Cancelled";
			}
				if(st=='R'){
				msg+=" Rejected";
			}
				if(st=='A'){
				msg+=" Approved ";
			}
			if(st=='F'){
				msg+=" Forwarded ";
			}
			alert(  msg+" application !");
			return false;
			}
			document.getElementById("paraFrm_checkStatus").value=id; 
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			return true;
}  	
	
function cancel(){	
	if(document.getElementById('paraFrm_leaveApplication_leaveCode').value == ""){
  				alert("Please select application");
  				return false;
  	}
	if(!(document.getElementById('paraFrm_status').value=="A" || document.getElementById('paraFrm_status').value=="P")){
			var msg="You can't cancel the";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='D'){
				msg+=" Draft";
	}
	if(st=='B'){
				msg+=" Sent Back";
	}
	if(st=='N'){
				msg+=" Cancelled";
	}
	if(st=='R'){
				msg+=" Rejected";
	}
	if(st=='A'){
				msg+=" Approved ";
	}
	if(st=='F'){
				msg+=" Forwarded ";
	}
	alert(  msg+" application !");
	return false;
   }
   var conf=confirm("Do you really want to cancel this record ?");
   if(conf){
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		return true;
	}else{
	  	 return false;
	}	
	return true;
}
	
function callAdd(){
  try{
  		var current = new Date();
		var year =current.getYear();
		var month =current.getMonth();
		var days =current.getDate();
		var empcode = document.getElementById('paraFrm_leaveApplication_empCode').value;
		var leav = document.getElementById('paraFrm_leaveApplication_levCode').value;
		var fromDt =document.getElementById('paraFrm_leaveFromDtl').value ;
		var toDate = document.getElementById('paraFrm_leaveToDtl').value ;		 
		var close = document.getElementById('paraFrm_levClosingBalance').value ;
		var zeroFlag = document.getElementById('paraFrm_leaveApplication_zeroBalance').value ;
		if(document.getElementById('paraFrm_isDeliveryDateShow').value=='false')
		var baln = document.getElementById('paraFrm_levOpeningBalance').value ;
		var total = document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value ;
	    if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){
			var msg="You can't add leave for the";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='N'){
				msg+=" Cancelled";
			}
			if(st=='P'){
				msg+=" Submited";
			}
			if(st=='D'){
				msg+=" Draft";
			}
			if(st=='R'){
				msg+=" Rejected";
			}
			if(st=='A'){
				msg+=" Approved ";
			}
			if(st=='F'){
				msg+=" Forwarded ";
			}
			alert(  msg+" application !");
			return false;
	  	}
	   	if(empcode==""){
				alert("Please select "+document.getElementById('appname').innerHTML.toLowerCase());
				return false;
		}
	    if(leav==""){
			if(document.getElementById('paraFrm_saveDetailFlag').value=='true'){
					alert ("Please select Leave to edit");
					return false;
			 }else{
					alert("Please select "+document.getElementById('levtype').innerHTML.toLowerCase());
					return false;
			}
			return false;
		}		 
		if(fromDt ==""){
			alert("Please enter "+document.getElementById('fromdate').innerHTML.toLowerCase());
       		return false;
		}
		if(toDate ==""){
			alert("Please enter "+document.getElementById('todate').innerHTML.toLowerCase());
       			 return false;
		}		 
		var datdiff = dateDifferenceEqual(fromDt,toDate,'paraFrm_leaveFromDtl','fromdate','todate');
  	  	if(!datdiff){
  			return false;
  		}
		var check= validateDate('paraFrm_leaveFromDtl', 'fromdate');
		if(!check){
			return false;
		}
		var check1= validateDate('paraFrm_leaveToDtl', 'todate');
			if(!check1){
			return false;
		}
		//alert(document.getElementById('paraFrm_isDeliveryDateShow').value);
		if(document.getElementById('paraFrm_isDeliveryDateShow').value=='true'){
		 var deliveryDate = document.getElementById('paraFrm_deliveryDate').value ;
		 if(deliveryDate ==""){
		 	alert("Please enter "+document.getElementById('expectedDeliverydate').innerHTML.toLowerCase());
       			 return false;
		 }			
		 var check1= validateDate('paraFrm_deliveryDate', 'expectedDeliverydate');
		 if(!check1){
		 	return false;
		 }		 
		 var datdiff1 = dateDifference(fromDt,deliveryDate,'paraFrm_leaveFromDtl','fromdate','expectedDeliverydate');
  	  	 if(!datdiff1){
  		 	return false;
  		}
  		var datdiff2 = dateDifference(deliveryDate,toDate,'paraFrm_deliveryDate','expectedDeliverydate','todate');
  	  	if(!datdiff2){
  			return false;
  		}
	   }
	   //if(baln==0 && zeroFlag =='N'){
			//alert ("You don't have sufficient leave balance  !");
			//return false;
		//	}
		
		if(eval(fromDt)==eval(toDate)){
	 		if(document.getElementById('checkMeId').checked && document.getElementById('checkMeForhalfTodateId').checked){
	 				  alert("you can't apply 2 half days in single day ");
	 				  return false;
	 		}
	 	}		
		// alert('total---'+total);
		 if(eval(total)==0.0 ){
		 		alert("You can't apply leave on holiday ");
		 		return false;
		 }
		 document.getElementById('paraFrm').target="_self";
		 document.getElementById("paraFrm").action="LeaveApplication_addLeaveType.action";
		 document.getElementById("paraFrm").submit();
	}catch(e){
		  	alert(e);
	}						
  }
   
function getData(){
   
     	 var empcode = document.getElementById('paraFrm_leaveApplication_empCode').value;
		 var leav = document.getElementById('paraFrm_leaveApplication_levCode').value;
		 var fromDt =document.getElementById('paraFrm_leaveFromDtl').value ;
		 var toDate = document.getElementById('paraFrm_leaveToDtl').value ;		   
		 if(!(document.getElementById('paraFrm_status').value=="P")){
			var msg="You can't add leave for the";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='N'){
				msg+=" Cancelled";
			}
				if(st=='R'){
				msg+=" Rejected";
			}
				if(st=='A'){
				msg+=" Approved ";
			}
			if(st=='F'){
				msg+=" Forwarded ";
			}
			alert(  msg+" application !");
			return false;
			}		  
		    if(empcode==""){
				alert("Please select "+document.getElementById('appname').innerHTML.toLowerCase());
				return false;
			}						 
			if(leav==""){
				if(document.getElementById('paraFrm_saveDetailFlag').value=='true'){
						alert ("Please select Leave to edit");
						return false;
				}else{
						alert("Please select "+document.getElementById('levtype').innerHTML.toLowerCase());
						return false;
				}
						
			}
			if(fromDt ==""){
				alert("Please enter "+document.getElementById('fromdate').innerHTML.toLowerCase());
       			return false;
			}
			if(toDate ==""){
				alert("Please enter "+document.getElementById('todate').innerHTML.toLowerCase());
       			 return false;
			}				
			var check= validateDate('paraFrm_leaveFromDtl', 'fromdate');
			if(!check){
				return false;
			}
			var check1= validateDate('paraFrm_leaveToDtl', 'todate');
			if(!check1){
				return false;
			}		 
			var datdiff = dateDifferenceEqual(fromDt,toDate,'paraFrm_leaveFromDtl','fromdate','todate');
  	  		if(!datdiff){
  				return false;
  			}	
}
   
   function callClear1(){   
   		if(!(document.getElementById('paraFrm_status').value=="D" || document.getElementById('paraFrm_status').value=="B")){
			var msg="You can't clear";
			var st=document.getElementById('paraFrm_status').value;
			if(st=='N'){
				msg+=" Cancelled";
			}
			if(st=='P'){
				msg+=" Submited";
			}
			if(st=='D'){
				msg+=" Draft";
			}
				if(st=='R'){
				msg+=" Rejected";
			}
				if(st=='A'){
				msg+=" Approved ";
			}
			if(st=='F'){
				msg+=" Forwarded ";
			}
			alert(  msg+" application !");
			return false;
		}
		document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action="LeaveApplication_clear.action";
		document.getElementById("paraFrm").submit();
}
  
  
 function checkApproveCancellationStatus(obj,id){
  	document.getElementById("paraFrm_checkApproveCancelStatus").value=id; 
 	if(document.getElementById("paraFrm_checkApproveCancelStatus").value=="X")
       conf=confirm("Do you really want to approve this application ?");
    	if(document.getElementById("paraFrm_checkApproveCancelStatus").value=="Z")
       		conf=confirm("Do you really want to reject this application ?");
			if(conf){
					 if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="X"){
				 	 	document.getElementById('approvecancel').disabled=true;
				 	 }
				 	  if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="Z"){
				 	 	document.getElementById('rejectcancel').disabled=true;
				 	 }				 	 
					 document.getElementById('paraFrm').target="main";
					 document.getElementById("paraFrm").action="LeaveApplication_approveRejCancellationLeaveApp.action";
		  			 document.getElementById("paraFrm").submit();
		  			 window.close();
			}
			else{
				 return false; 
  			}  				
  		return true;
}
 
  
function checkAppStatus(obj,id){   
  	try{
  		var approverComments = document.getElementById('paraFrm_approverComments').value;
   		var conf;
  		document.getElementById("paraFrm_checkApproveRejectStatus").value=id; 
  		if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="A")
       		conf=confirm("Do you really want to approve this application ?");
      	if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="R")
       		conf=confirm("Do you really want to reject this application ?");     
       	if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="B"){
		   var fieldName=["paraFrm_approverComments"];
		   var lableName=["approverComm"];
		   var flag = ["enter"];
		   if(!(validateBlank(fieldName,lableName,flag))){
					return false;
		   }		    
       }
       if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="B")
       conf=confirm("Do you really want to send back this application ?");
	   if(conf){
			if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="A"){
				 	 document.getElementById('approve').disabled=true;
			}if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="R"){
				 	 document.getElementById('reject').disabled=true;
			}if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="B"){
				 	 document.getElementById('sendback').disabled=true;
			}
			document.getElementById("paraFrm").target="main";
			document.getElementById("paraFrm").action="LeaveApplication_approveRejSendBackLeaveApp.action";
		  	document.getElementById("paraFrm").submit();
		  	window.close();
		}else{
				 return false; 
  		}  				
  	}catch(e){alert(e);}	
  	return true; 		
  }
  
  
function checkDate(){
		var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
		var fromDt =document.getElementById('paraFrm_leaveFromDtl').value ;
		var toDate = document.getElementById('paraFrm_leaveToDtl').value ;		
		if (!(toDate.match(RegExPattern))) {
	       		//alert('Please enter valid todate ');
	       		document.getElementById('paraFrm_leaveToDtl').value="" ;
	       		return false;	 
   		} 
		if (!(fromDt.match(RegExPattern))) {
    		//alert('Please enter valid todate ');
    		document.getElementById('paraFrm_leaveFromDtl').value="";
    		return false;	 
		 } 		 
		var fromDate1= fromDt.substring(6,fromDt.length);
		var toDate1= toDate.substring(6,fromDt.length);
		if(toDate1.length !=4 ){
   			document.getElementById('paraFrm_leaveFromDtl').value="" ;
	       	return false;
   		}
   		if(fromDate1.length !=4 ){
   			document.getElementById('paraFrm_leaveToDtl').value="" ;
	       	return false;
   		}
   		return true;		
	}
   
  function callForHalf(){	
		var halfDay =  document.getElementById('paraFrm_halfDayCheck').value;
		var total = document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value;
		var closing = document.getElementById('paraFrm_levClosingBalance').value;
		  	var zeroFlag = document.getElementById('paraFrm_leaveApplication_zeroBalance').value ;
		if(document.getElementById('checkMeId').checked == true){
			if(halfDay == 'Y' && total == "1.0" || total == "1"){
				if(zeroFlag=='Y'){
						document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value =0.5;
						var totAmt = eval(closing);
						document.getElementById('paraFrm_levClosingBalance').value =Math.round(totAmt*100)/100;
						return true;
					
				 }
				 document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value =0.5;
				 var totAmt = eval(closing)+.5;
				 document.getElementById('paraFrm_levClosingBalance').value =Math.round(totAmt*100)/100;
				 return true;
			}				
		}
		else{
			if(halfDay == 'Y' && total == "0.5"){				
				document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value ="1.0";
				var tot =eval(closing)-.5;
				//alert(tot);
				document.getElementById('paraFrm_levClosingBalance').value =Math.round(tot*100)/100;				
				if(zeroFlag=='Y'){
							document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value ="1.0";
							var tot =eval(closing);
							document.getElementById('paraFrm_levClosingBalance').value =Math.round(tot*100)/100;
				}				
			}	
		}
}
	
function callReport(name) {
  			if(document.getElementById('paraFrm_leaveApplication_leaveCode').value == ""){
  				alert("Please select application");
  			}else{
				document.getElementById('paraFrm').target="_blank";
				document.getElementById('paraFrm').action=name;	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";
			}						
}	
	
function getBlank(){
	 document.getElementById('paraFrm_levClosingBalance').value ='';
	document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value ='';	
}
	
function callClose(){ 
			window.close();
}
	
function autoDate() {
	var tDay = new Date();
	var tMonth = tDay.getMonth()+1;
	var tDate = tDay.getDate();
	if ( tMonth < 10) tMonth = "0"+tMonth;
	if ( tDate < 10) tDate = "0"+tDate;			 
	if(document.getElementById('paraFrm_leaveApplication_applicationDate').value=="")
			document.getElementById("paraFrm_leaveApplication_applicationDate").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
				//alert("date"+document.getElementById("paraFrm_vchDate").value);
}
  
function pullLeaves(pullFromId){
  //alert("pullFromId   "+pullFromId);
 	var totleaveAdjust=document.getElementById('adjustedPoolLevAmt'+pullFromId).value;
 	var poolLevBalance=document.getElementById('poolLevBalance'+pullFromId).value;  	
 	if(totleaveAdjust==""){
 		alert("Please enter amount of leaves to be pulled");
 		return false;
 	} 
 	if(poolLevBalance==0){
 		alert("You dont have sufficient balance ");
 		return false;
 	}
 	if(eval(totleaveAdjust)>eval(poolLevBalance)){
 		alert("Leaves to be pulled can not be greater than  balance leave");
 		return false;
 	}
 	 conf=confirm("Do you really want to pull leave balance?");
 	 if(conf){
  		pullLeaveFrom('<%=request.getContextPath()%>/leaves/LeaveApplication_pullLeaves.action?','LeaveForm',pullFromId);
  	}
  	else{
  			return false;
  	}
  }
//autoDate();
</script>
