
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/pages/common/js/leaveAjax.js"></script>

<div style="float: left; width: 100%">
<table width="98%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td><s:form action="LeaveApplication" method="post"
			name="LeaveForm" id="paraFrm" theme="simple">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="middle">
					<fieldset><legend class="legend">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><img src="../pages/mypage/images/icons/mtleave.png" width="16"
								height="16" /></td>
							<td>&nbsp;&nbsp;Leave Application</td>
						</tr>
					</table>
					</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						>
						<s:hidden theme="simple" name="leaveApplication.leaveCode" />
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td></td>
								</tr>
								<tr>
									<td height="0">
									<table width="350" border="0" align="right" cellpadding="2"
										cellspacing="2">
										<s:if test="newChangesFlag">
										<tr>
											<td width="18%"> 
											<div align="right"><span class="pointer" onclick="return reportFun();"><img src="../pages/mypage/images/icons/approve.png"
												width="10" height="10" onclick="return reportFun();"/>&nbsp;&nbsp;Report</span>&nbsp;&nbsp;
											| &nbsp;&nbsp;<span class="pointer" onclick="return callBack();"> <img src="../pages/mypage/images/icons/back.png" width="10"
												height="10" onclick="return callBack();" />&nbsp;&nbsp;Back</span></div>
											</td>
										</tr>
										
										</s:if>
										
										<s:if test="ncFlagForDraft">
										<tr>
											<td width="18%"> 
											<div align="right"><span class="pointer" onclick="return checkAppStatus(this,'A');">
											<img src="../pages/mypage/images/icons/approve.png"
												width="10" height="10" />&nbsp;&nbsp;Approve</span>&nbsp;&nbsp;
											|&nbsp;&nbsp; <span class="pointer" onclick="return checkAppStatus(this,'R');"><img src="../pages/mypage/images/icons/reject.png" width="10"
												height="10"/>&nbsp;&nbsp;Reject</span>&nbsp;&nbsp; |&nbsp;&nbsp;
											&nbsp;<span class="pointer" onclick="return checkAppStatus(this,'B');"><img src="../pages/mypage/images/icons/sendback.png"
												width="10" height="10" />&nbsp;&nbsp;Send Back </span>&nbsp;&nbsp;
											&nbsp;|&nbsp;&nbsp;<span class="pointer" onclick="return callBack();"> <img src="../pages/mypage/images/icons/back.png" width="10"
												height="10" />&nbsp;&nbsp;Back</span></div>
											</td>
										</tr>
										</s:if>
										
										<s:if test="ncFlagForApprovePending">
										<tr>
											<td width="18%"> 
											<div align="right"><span class="pointer" onclick="cancelapplicationFun();">
											<img src="../pages/mypage/images/icons/approve.png"
												width="10" height="10" />&nbsp;&nbsp;Cancel Application</span>&nbsp;&nbsp;
											|&nbsp;&nbsp;<span class="pointer" onclick="return reportFun();"><img src="../pages/mypage/images/icons/approve.png"
												width="10" height="10" />&nbsp;&nbsp;Report</span>&nbsp;&nbsp;
											| &nbsp;&nbsp;<span class="pointer" onclick="return callBack();"><img src="../pages/mypage/images/icons/back.png" width="10"
												height="10" onclick="return callBack();" />&nbsp;&nbsp;Back</span></div>
											</td>
										</tr>
										</s:if>
										
										<!-- For cancel application -->
											<s:if test="ncFlagForCancelApp">
										<tr>
											<td width="18%"> 
											<div align="right"><span class="pointer" onclick="return checkApproveCancellationStatus(this,'X');">
											<img src="../pages/mypage/images/icons/approve.png"
												width="10" height="10" />&nbsp;&nbsp;Approve Cancellation</span>&nbsp;&nbsp;
											|&nbsp;&nbsp; <span class="pointer" onclick="return checkApproveCancellationStatus(this,'Z');"><img src="../pages/mypage/images/icons/reject.png" width="10"
												height="10"/>&nbsp;&nbsp;Reject Cancellation</span>&nbsp;&nbsp; |&nbsp;&nbsp;<span class="pointer" onclick="return callBack();"> <img src="../pages/mypage/images/icons/back.png" width="10"
												height="10" />&nbsp;&nbsp;Back</span></div>
											</td>
										</tr>
										</s:if>
										
									</table>
									</td>
								</tr>
								<tr>
									<td height="1" bgcolor="#cccccc" class="style1"></td>
								</tr>

								<tr>
									<td>
									<fieldset><legend class="legend1">
									Application Information</legend>
									<table width="99%" border="0" align="center" cellpadding="2"
										cellspacing="1">
										<tr>
											<td><label name="appname" id="appname"
						ondblclick="callShowDiv(this);"><%=label.get("appname")%></label></td>
											<td width="1%" class="star">&nbsp;</td>
											<td width="1%">:</td>
											<td><label class="text1"><s:hidden theme="simple"
												name="leaveApplication.empCode"
												value="%{leaveApplication.empCode}" /><s:property
												value="leaveApplication.empName"></s:property> </label></td>
											<td>&nbsp;</td>
											<td><label name="employee.code" id="employee.code"
						ondblclick="callShowDiv(this);"><%=label.get("employee.code")%></label></td>
											<td class="star">&nbsp;</td>
											<td>:</td>
											<td class="text1"><s:property
												value="leaveApplication.tokenNo"></s:property></td>
										</tr>
										<tr>
											<td><label name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
											<td class="star">&nbsp;</td>
											<td width="1%">:</td>
											<td width="25%" class="text1"><s:property
												value="leaveApplication.department"></s:property></td>
											<td>&nbsp;</td>
											<td width="15%"><label name="appdate" id="appdate"
						ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></td>
											<td width="1%" class="star">&nbsp;</td>
											<td width="1%">:</td>
											<td width="30%" class="text1"><s:property
												value="leaveApplication.applicationDate"></s:property></td>
										</tr>
										<tr>
											<td width="15%"><label name="branch" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
											<td width="1%">&nbsp;</td>
											<td>:</td>
											<td class="text1"><s:property
												value="leaveApplication.center"></s:property></td>
											<td width="3%">&nbsp;</td>
											<td><label name="stat" id="stat"
						ondblclick="callShowDiv(this);"><%=label.get("stat")%></label></td>
											<td class="star">&nbsp;</td>
											<td>:</td>
											<s:if test='%{status =="D"}'>
												<td width="30%" class="text1">Draft</td>
											</s:if>
											<s:if test='%{status =="P"}'>
												<td width="30%" class="text1">Pending</td>
											</s:if>
											<s:if test='%{status =="B"}'>
												<td width="30%" class="text1">Sent Back</td>
											</s:if>
											<s:if test='%{status =="A"}'>
												<td width="30%" class="text1">Approved</td>
											</s:if>
											<s:if test='%{status =="R"}'>
												<td width="30%" class="text1">Rejected</td>
											</s:if>
											<s:if test='%{status =="F"}'>
												<td width="30%" class="text1">Forwarded</td>
											</s:if>
											<s:if test='%{status =="N"}'>
												<td width="30%" class="text1">Cancelled</td>
											</s:if>
											<s:if test='%{status =="C"}'>
												<td width="30%" class="text1">Applied For Cancellation</td>
											</s:if>

											<s:if test='%{status =="X"}'>
												<td width="30%" class="text1">Cancellation Approved</td>
											</s:if>
											<s:if test='%{status =="Z"}'>
												<td width="30%" class="text1">Cancellation Rejected</td>
											</s:if>
											<s:hidden
												name="hiddenStatus" />
												<s:hidden name="status" />
											<s:hidden name="level" />
										</tr>

										<tr>
											<td><label name="approver" id="approver"
						ondblclick="callShowDiv(this);"><%=label.get("approver")%></label></td>
											<td>&nbsp;</td>
											<td>:</td>
											<%
											int y = 1;
											%>
											<%!int z = 0;%>
											<s:iterator value="approverList">
												<td class="text1"><s:hidden name="approverName" /><s:property
													value="approverName" /></td>
												<%
												y++;
												%>
											</s:iterator>
											<%
											z = y;
											%>
											<td>&nbsp;</td>
											<td><label name="keep" id="keep"
						ondblclick="callShowDiv(this);"><%=label.get("keep")%></label></td>
											<td>&nbsp;</td>
											<td>:</td>
											<%
													int counter11 = 0;
													int counter2 = 0;
												%>

											<s:iterator value="keepInformedList" status="stat">
												<td><span class="text1"><s:hidden
													name="keepInformedEmpName" /><s:property
													value="keepInformedEmpName" /><s:hidden
													name="keepInformedEmpId" /></span></td>
												<%
													counter2 = counter11;
													%>
											</s:iterator>
											<%
												counter2 = 0;
												%>
										</tr>
									</table>
									</fieldset>
									</td>
								</tr>

								<tr></tr>
								<tr>
									<td>
									<fieldset><legend class="legend1">Leave
									Details</legend>

									<table width="99%" border="0" align="center" cellpadding="2"
										cellspacing="1">
										<tr>
											<td colspan="5">
											<table width="100%" border="0" align="center" cellpadding="2"
												cellspacing="2" class="border">
												<tr>
													<td width="3%" bgcolor="#EEF4FB"><label
										name="srnum" id="srnum" ondblclick="callShowDiv(this);"><%=label.get("srnum")%></label></td>
													<td width="25%" bgcolor="#EEF4FB"><label name="levtype"
								id="levtype2" ondblclick="callShowDiv(this);"><%=label.get("levtype")%></label></td>
													<td width="13%" bgcolor="#EEF4FB"><label
										name="fromdate" id="fromdate1" ondblclick="callShowDiv(this);"><%=label.get("fromdate")%></label></td>
													<td width="12%" bgcolor="#EEF4FB"><label
										name="todate" id="todate1" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label></td>
													<td width="18%" bgcolor="#EEF4FB"><label
										name="half" id="half" ondblclick="callShowDiv(this);"><%=label.get("half")%></label></td>
													<td width="12%" bgcolor="#EEF4FB"><label
										name="levdays" id="levdays" ondblclick="callShowDiv(this);"><%=label.get("levdays")%></label></td>
													<s:if test="avaibal">
														<td width="8%" valign="top" bgcolor="#EEF4FB"><label
															name="availbal" id="availbal"
															ondblclick="callShowDiv(this);"><label
											name="availbal" id="availbal" ondblclick="callShowDiv(this);"><%=label.get("availbal")%></label></td>
													</s:if>
													<s:if test="%{leaveApplication.isPenaltyFlag}">
														<td width="5%" valign="top" class="formth" nowrap="nowrap">
														Penalty</td>
													</s:if>
													<td width="10%" bgcolor="#EEF4FB"><label
											name="proof1" id="proof1" ondblclick="callShowDiv(this);"><%=label.get("proof1")%></label></td>
												</tr>

												<%
												int count = 0, p = 0;
												%>
												<s:iterator value="att" status="stat">
													<tr>
														<td class="text1"><%=++count%><s:hidden name="srNo"
															value="%{<%=count%>}" /></td>
														<td class="text1"><s:property value="slevType" /><s:hidden
															name="slevType" /> <s:hidden name="slevCode" /></td>
														<td class="text1"><s:property value="sleaveFromDtl" /><s:hidden
															name="sleaveFromDtl" /></td>
														<td class="text1"><s:property value="sleaveToDtl" /><s:hidden
															name="sleaveToDtl" /></td>


														<s:if test='%{halfDayType =="FH"}'>
															<td class="text1">Yes,First Half</td>
														</s:if>

														<s:if test='%{halfDayType =="SH"}'>
															<td class="text1">Yes,Second Half</td>
														</s:if>

														<s:if test='%{halfDayType ==""}'>
															<td class="text1">No</td>
														</s:if>
														<td class="text1"><s:property
															value="slevClosingBalance" /><s:hidden
															name="slevClosingBalance" /></td>
														<s:hidden name="halfDayType" />
														<s:hidden name="halfDayTypeToDate" />

														<s:if test="%{leaveApplication.avaibal}">
															<td align="center" class="text1"><s:property
																value="availBalance" /></td>
														</s:if>

														<s:if test="%{leaveApplication.isPenaltyFlag}">
															<td width="5%" align="center" nowrap="nowrap"
																class="text1"><s:property
																value="iteratorPenaltyDays" /></td>
														</s:if>

														<s:if test="leaveApplication.uploadProofViewFlag">
															<td class="text1">Yes</td>
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

													</tr>
													<%
													p = count;
													%>
												</s:iterator>
												<%
												p = 0;%><!--
												
						
                                    
                                  <tr>
                                    <td class="text1">1</td>
                                    <td class="text1">Personal Reason </td>
                                    <td class="text1">12/03/2012</td>
                                    <td class="text1">14/03/2012</td>
                                    <td class="text1">No</td>
                                    <td class="text1">14</td>
                                    <td class="text1">Yes</td>
                                    </tr>
                                -->
											</table>
											</td>
										</tr>
										<tr>
											<td width="16%"><label
											name="proof" id="proof" ondblclick="callShowDiv(this);"><%=label.get("proof")%></label></td>
											<td width="1%">&nbsp;</td>
											<td width="1%">&nbsp;</td>
											<%
												int count1 = 0, p1 = 0;
												%>
											<s:iterator value="att" status="stat">
												<s:if test="leaveApplication.uploadProofViewFlag">
													<s:iterator value="ittUploadList">
														<td class="text1"><%=++count1%>- <a href="#"
															onclick="showRecord('<s:property value="uploadDoc" />');"><s:property
															value="uploadDocPath" /> </a></td>
													</s:iterator>
												</s:if>
												<%
													p1 = count1;
													%>
											</s:iterator>
											<%
												p1 = 0;%>

										</tr>

									</table>
									</fieldset>
									</td>
								</tr>

								<tr>
									<td>
									<fieldset><legend class="legend1"> Leave
									Reason </legend>
									<table width="99%" border="0" align="center" cellpadding="2"
										cellspacing="1">
										<tr>
											<td width="15%"> <label
											name="leave.reason" id="leave.reason" ondblclick="callShowDiv(this);"><%=label.get("leave.reason")%></label></td>
											<td width="1%" class="star">&nbsp;</td>
											<td width="1%">:</td>
											<td width="25%"><label class="text1"><s:property
												value="leaveReasonName" /></label></td>
											<td width="3%">&nbsp;</td>
											<td width="15%"><label
											name="if.leave.reason" id="if.leave.reason" ondblclick="callShowDiv(this);"><%=label.get("if.leave.reason")%></label></td>
											<td width="1%" class="star">&nbsp;</td>
											<td width="1%">:</td>
											<td width="30%" class="text1"><s:property
												value="leaveApplication.medicalCert" /></td>
										</tr>
									</table>
									</fieldset>
									</td>
								</tr>
								<tr>
									<td>
									<fieldset><legend class="legend1">Communication
									Thread </legend>
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td>
											<table width="99%" border="0" align="center" cellpadding="2"
												cellspacing="1">
												<s:if test="ncFlagForDraft">
												<tr>
													<td width="14%"><label name="approverComm" id="approverComm"
						ondblclick="callShowDiv(this);"><%=label.get("approverComm")%></label></td>
													<td width="1%" class="star"></td>
													<td width="1%">:</td>
													
													<td width="70%"><label class="text1"> <s:textfield
														name="approverComments"  
														
														value="" size="70" /> </label></td>
													
												</tr>
</s:if>


				<s:if test="ncFlagForCancelApp">
												<tr>
													<td width="14%"><label name="approverComm" id="approverComm"
						ondblclick="callShowDiv(this);"><%=label.get("approverComm")%></label></td>
													<td width="1%" class="star"></td>
													<td width="1%">:</td>
													
													<td width="70%"><label class="text1"> <s:textfield
														name="approverComments"  
														
														value="" size="70" /> </label></td>
													
												</tr>
</s:if>
<tr>
													<td width="14%"><label
											name="emp.comm" id="emp.comm" ondblclick="callShowDiv(this);"><%=label.get("emp.comm")%></label></td>
													<td width="1%" class="star"></td>
													<td width="1%">:</td>
													
													<td width="70%"><label class="text1"> <s:property
														value="leaveApplication.comments"  
														
														 /> </label></td>
													
												</tr>

											</table>
											</td>
										</tr>
										<s:if test="prevAppCommentListFlag">
										<tr>
											<td>
											<table width="99%" border="0" align="center" cellpadding="2"
												cellspacing="1" class="border">

												<tr>

													<td width="5%" bgcolor="#EEF4FB">Sr.No</td>
													<td width="15%" bgcolor="#EEF4FB" nowrap="nowrap">Approver ID</td>
													<td width="25%" bgcolor="#EEF4FB">Name</td>
													<td width="10%" bgcolor="#EEF4FB">Date</td>
													<td width="15%" bgcolor="#EEF4FB">Status</td>
													<td width="30%" bgcolor="#EEF4FB">Comments</td>
												</tr>
												<%int i = 0;%>
												<%
							int k = 1;
							%>
												<s:iterator value="approverCommentList" status="stat">
													<tr>
														<td><%=k%><s:hidden name="appSrNo" value="%{<%=k%>}" /></td>
														<td><s:property value="prevApproverID" /><s:hidden
															name="prevApproverID" /></td>
														<td><s:property value="prevApproverName" /><s:hidden
															name="prevApproverName" /></td>
														<td class="star"><span class="text1"><s:property
															value="prevApproverDate" /><s:hidden
															name="prevApproverDate" /></span></td>
														<td class="text1">&nbsp;<s:property
															value="prevApproverStatus" /><s:hidden
															name="prevApproverStatus" /></td>
														<td><label class="text1">&nbsp;<s:property
															value="prevApproverComment" /><s:hidden
															name="prevApproverComment" /> </label></td>
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
										</s:if>
									</table>
									</fieldset>
									 </td>
								</tr>
								<tr>
									<td></td>
								</tr>
								<tr>
									<td height="1px" bgcolor="#cccccc"></td>
								</tr>
								<tr>
									<td>
									<table width="350" border="0" align="right" cellpadding="2"
										cellspacing="2">
							<s:if test="newChangesFlag">
											<tr>
											<td width="18%"> 
											<div align="right"><span class="pointer" onclick="return reportFun();"><img src="../pages/mypage/images/icons/approve.png"
												width="10" height="10" onclick="return reportFun();"/>&nbsp;&nbsp;Report</span>&nbsp;&nbsp;
											| &nbsp;&nbsp;<span class="pointer" onclick="return callBack();"> <img src="../pages/mypage/images/icons/back.png" width="10"
												height="10" onclick="return callBack();" />&nbsp;&nbsp;Back</span></div>
											</td>
										</tr>
										
										</s:if>
										
										<s:if test="ncFlagForDraft">
										<tr>
											<td width="18%"> 
											<div align="right"><span class="pointer" onclick="return checkAppStatus(this,'A');">
											<img src="../pages/mypage/images/icons/approve.png"
												width="10" height="10" />&nbsp;&nbsp;Approve</span>&nbsp;&nbsp;
											|&nbsp;&nbsp; <span class="pointer" onclick="return checkAppStatus(this,'R');"><img src="../pages/mypage/images/icons/reject.png" width="10"
												height="10"/>&nbsp;&nbsp;Reject</span>&nbsp;&nbsp; |&nbsp;&nbsp;
											&nbsp;<span class="pointer" onclick="return checkAppStatus(this,'B');"><img src="../pages/mypage/images/icons/sendback.png"
												width="10" height="10" />&nbsp;&nbsp;Send Back </span>&nbsp;&nbsp;
											&nbsp;|&nbsp;&nbsp;<span class="pointer" onclick="return callBack();"> <img src="../pages/mypage/images/icons/back.png" width="10"
												height="10" />&nbsp;&nbsp;Back</span></div>
											</td>
										</tr>
										</s:if>
										
										<s:if test="ncFlagForApprovePending">
										<tr>
											<td width="18%"> 
											<div align="right"><span class="pointer" onclick="cancelapplicationFun();">
											<img src="../pages/mypage/images/icons/approve.png"
												width="10" height="10" />&nbsp;&nbsp;Cancel Application</span>&nbsp;&nbsp;
											|<span class="pointer" onclick="return reportFun();"><img src="../pages/mypage/images/icons/approve.png"
												width="10" height="10" />&nbsp;&nbsp;Report</span>&nbsp;&nbsp;
											| <span class="pointer" onclick="return callBack();"><img src="../pages/mypage/images/icons/back.png" width="10"
												height="10" onclick="return callBack();" />&nbsp;&nbsp;Back</span></div>
											</td>
										</tr>
										</s:if>
										
										<!-- For cancel application -->
											<s:if test="ncFlagForCancelApp">
										<tr>
											<td width="18%"> 
											<div align="right"><span class="pointer" onclick="return checkApproveCancellationStatus(this,'X');">
											<img src="../pages/mypage/images/icons/approve.png"
												width="10" height="10" />&nbsp;&nbsp;Approve Cancellation</span>&nbsp;&nbsp;
											|&nbsp;&nbsp; <span class="pointer" onclick="return checkApproveCancellationStatus(this,'Z');"><img src="../pages/mypage/images/icons/reject.png" width="10"
												height="10"/>&nbsp;&nbsp;Reject Cancellation</span>&nbsp;&nbsp; |&nbsp;&nbsp;<span class="pointer" onclick="return callBack();"> <img src="../pages/mypage/images/icons/back.png" width="10"
												height="10" />&nbsp;&nbsp;Back</span></div>
											</td>
										</tr>
										</s:if>
									</table>
									</td>
								</tr>

							</table>
							</td>
						</tr>
					</table>
					</fieldset>
					 </td>
				</tr>
			</table>
			
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
			
		
		</s:form></td>
	</tr>
</table>
</div>
<div
	style="float: left; width: 100%; height: 1px; background-color: #FF6600"></div>
	
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

function showRecord(fileName)
	{
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "LeaveApplication_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}

function draftFun()
{
 
 		 var empCode = document.getElementById('paraFrm_leaveApplication_empCode').value;
			if(empCode==""){
			alert (" Please fill the leave application");
			return false;
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
		
		for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'draft') {
			//alert(document.all[i]);
			document.all[i].disabled=true;
		}
		}
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


function backFun()
{
	 
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
		else if(document.getElementById('source').value=='mytimecard')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_mytimeCard.action';
		}
		else if(document.getElementById('source').value=='myLeaves')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_myLeaves.action';
		}
		else{
		document.getElementById('paraFrm').action = 'LeaveApplication_back.action';
		}
		
		document.getElementById('paraFrm').submit();

}

function callBack()
{

 
		document.getElementById('paraFrm').target = "_self";
		//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else{
		document.getElementById('paraFrm').action = 'LeaveApproval_callstatus.action';
		}
		document.getElementById('paraFrm').submit();

}

function callBackAdmin()
{
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'LeaveAdminApproval_callstatus.action';
		document.getElementById('paraFrm').submit();

}

function sendforapprovalFun()
{ 
	var empCode = document.getElementById('paraFrm_leaveApplication_empCode').value;
			if(empCode==""){
			alert (" Please fill the leave application");
			return false;
			}	
			//document.getElementById("overlay").style.visibility = "visible";
		//document.getElementById("overlay").style.display = "block";
		
		 var conf=confirm("Do you really want to send for approval ?");
			 		if(conf)
			 		{
			 				for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'sendforapproval') {
			//alert(document.all[i]);
			//document.all[i].value="Saving...";
			document.all[i].disabled=true;
		}
		}
		
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


function cancelapplicationFun()
{ 
		var st=document.getElementById('paraFrm_status').value;
		//alert("st "+st);
			if(st=='F'){
				alert("You can not cancel forwarded application");
				return false;
			}
	 var conf=confirm("Do you really want to cancel this application ?");
			 		if(conf)
			 		{
			 			for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'cancelapplication') {
			document.all[i].disabled=true;
		}
		}
			 		document.getElementById('paraFrm').target = "_self";
					document.getElementById('paraFrm').action ='LeaveApplication_cancelForm.action'; 
						document.getElementById('paraFrm').submit();

			 		}
			 else
	  		{
	  			 return false;
	  		}
		
}

function resetFun()
{
		 
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'LeaveApplication_reset.action';
		document.getElementById('paraFrm').submit();

}


function reportFun()
{
		 
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

function deleteFun()
{
 	var conf=confirm("Do you really want to delete this record ?");
		 	if(conf)
			{
			
			for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'delete') {
			document.all[i].disabled=true;
		}
		}
			var flagHrs=document.getElementById('paraFrm_flagHrs').value;	
		
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action ='LeaveApplication_delete.action'; 
			if(flagHrs=='flag'){
			document.getElementById('paraFrm').action ='LeaveApplication_deleteHrs.action'; 
			}
			document.getElementById('paraFrm').submit();

			 }
			 else
			 {
			 return false; 
			 
			 }
}

function uploadFile(fieldName) {
		var path = document.getElementById("paraFrm_dataPath").value;
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}

function callUpload()
{
	var uploadFile = document.getElementById('paraFrm_userUploadFileName').value;
	var uploadFilePath = document.getElementById('paraFrm_uploadFileName').value;
	
	if(uploadFilePath=="")
	{
		alert("Please Upload proof");
		return false;
	}
	
	if(uploadFile=="")
	{
		if(uploadFilePath=="")
		{
		alert("Please Upload proof");
		return false;
		}	
		else
		{
				alert("Proof uploaded successfully.Please give the name for uploaded file");
		return false;
		}	
	
	}
	return true;
}
 
function callDelete(code)
   {
	   if(document.getElementById(code).value=="")
	   {
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
  			if(conf) 
  			{
  				return true;
  			}
	  		else
	  		{
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
	
	
	
	function getKeepInformedEmp()
	{
	try
	{
	 
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
	catch(e)
	{
		alert(e);
		} 
	
	}
	
	function callKeepInformed()
	{
		
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
			if(emp=="")
			{
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
		//	alert('aaa'+document.getElementById('paraFrm_leaveApplication_isEditFlag').value);
		 
				document.getElementById("paraFrm_srNo").value=srno;	
			//	alert(document.getElementById("paraFrm_srNo").value);		
			  	document.getElementById("paraFrm_leaveApplication_levCode").value=id;
			  	document.getElementById("paraFrm_leaveApplication_levType").value=levType;
			  	document.getElementById("paraFrm_leaveApplication_leaveTotalDays").value=leaveTotalDays;
			  	document.getElementById("paraFrm_leaveFromDtl").value=leaveFromDtl;
			  	document.getElementById("paraFrm_leaveToDtl").value=leaveToDtl;
			  	
			  	document.getElementById("paraFrm_checkEdit").value=edit;
			  	
			  //	document.getElementById('paraFrm_isDeliveryDateShow').value=true;
			  //	alert('val--------- '+document.getElementById('paraFrm_isDeliveryDateShow').value);
			  //	alert('deliveryDate--------- '+deliveryDate);
			  	//document.getElementById("paraFrm_deliveryDate").value=deliveryDate;
			  	if(unadjustPenaltyDays>0)
			  	{	 	if(document.getElementById('paraFrm_isDeliveryDateShow').value=='false')
			  		document.getElementById("paraFrm_levOpeningBalance").value=eval(closebalance)+eval(adjustPenaltyDays)+eval(leaveTotalDays);
			  	}
			  	else
			  	{ 	if(document.getElementById('paraFrm_isDeliveryDateShow').value=='false')
			  		document.getElementById("paraFrm_levOpeningBalance").value=eval(closebalance)+eval(leaveTotalDays)+eval(penaltyDays);
			  	
			  	}
			  	
			  
			 	document.getElementById("paraFrm_levClosingBalance").value=closebalance;
			 	document.getElementById("paraFrm_leaveApplication_onhold").value=onhold;
			 	document.getElementById("paraFrm_leaveApplication_oldLeaveDays").value=leaveTotalDays;
			 	document.getElementById("paraFrm_leaveApplication_oldPenaltyAdjDays").value=adjustPenaltyDays;
			 	
			 	document.getElementById("paraFrm_isHalfDayLeave").value=trim(halfDayLeave);
			 	
			 	
			 		document.getElementById('paraFrm_chkEdit').value=srno;
			 		
			 	//	alert('aa'+document.getElementById("paraFrm_leaveApplication_leaveTotalDays").value);
			 	//	alert('halfDay'+halfDay);
			 //	alert('closing--------'+document.getElementById('paraFrm_levClosingBalance').value);
			 		if(halfdaytype=="FH" || halfdaytype=="SH"  )
			 		{
			 			
			 			document.getElementById('checkMeId').checked =true;
			 			document.getElementById('firsthf').value=halfdaytype;
			 			 
			 		}
			 		else
			 		{
			 		document.getElementById('checkMeId').checked =false;
			 		}
			 		
			 		if(halfdayTodate=="FH" || halfdayTodate=="SH"  )
			 		{
			 			
			 			document.getElementById('checkMeForhalfTodateId').checked =true;
			 			document.getElementById('firsthftodate').value=halfdayTodate;
			 			 
			 		}
			 		else
			 		{
			 		document.getElementById('checkMeForhalfTodateId').checked =false;
			 		}
			 		document.getElementById('paraFrm').target="_self";
			 		document.getElementById("paraFrm").action="LeaveApplication_editLeaveType.action";
			 			document.getElementById("paraFrm").submit();
			 	}catch(e){alert(e);}	
			 	
	    }
	    
	    function callForRemoveUpload(id)
	    {
	    var conf=confirm("Are you sure !\n You want to Remove this record ?");
  				if(conf){
					  		document.getElementById('paraFrm_checkRemoveUpload').value=id;
					  		document.getElementById('paraFrm').target="_self";
					  		 document.getElementById("paraFrm").action="LeaveApplication_removeUploadFile.action";
		  					document.getElementById("paraFrm").submit();
		  				}	
	    }
	    
	    function callForRemove(id)
	    {
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
	    	
	    	else
	    	{
	    		var conf=confirm("Are you sure !\n You want to Remove this record ?");
  				if(conf){
					  		document.getElementById('paraFrm_checkRemove').value=id;
					  		document.getElementById('paraFrm').target="_self";
					  		 document.getElementById("paraFrm").action="LeaveApplication_removeKeepInformed.action";
		  					document.getElementById("paraFrm").submit();
		  				}	
		  				else
		  				{
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
			else
			{
			var conf=confirm("Are you sure !\n You want to Remove this record ?");
  				if(conf){
					  		document.getElementById('paraFrm_checkEdit').value=id;
					  		document.getElementById('paraFrm').target="_self";
					  		 document.getElementById("paraFrm").action="LeaveApplication_deleteData.action";
		  					document.getElementById("paraFrm").submit();
		  					
  			}			}
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
			 		if(conf)
			 		{
			 		document.getElementById("overlay").style.visibility = "visible";
					document.getElementById("overlay").style.display = "block";
					return true;
			 		}
			 else
	  		{
	  			 return false;
	  		}
			
			
	return true;
	}
	
	
	 function callAdd()
   	{
  			try
  			{
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
							  if(document.getElementById('paraFrm_saveDetailFlag').value=='true')
					 			{
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
		if(document.getElementById('paraFrm_isDeliveryDateShow').value=='true')
		{
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
		
		
		 
		  if(eval(fromDt)==eval(toDate))
	 		{
	 			if(document.getElementById('checkMeId').checked && document.getElementById('checkMeForhalfTodateId').checked)
	 			{
	 				  alert("you can't apply 2 half days in single day ");
	 				  return false;
	 			}
	 		}
		
		// alert('total---'+total);
		 if(eval(total)==0.0 )
		 {
		 		alert("You can't apply leave on holiday ");
		 		return false;
		 }
		 	 
	  				document.getElementById('paraFrm').target="_self";
					  		 document.getElementById("paraFrm").action="LeaveApplication_addLeaveType.action";
		  					document.getElementById("paraFrm").submit();
		  	}
		  	catch(e)
		  	{
		  	alert(e);
		  	}				
			
   }
   
   function getData()
   {
   
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
						 
							 if(leav=="")
							 {
							
							  if(document.getElementById('paraFrm_saveDetailFlag').value=='true')
					 			{
								 alert ("Please select Leave to edit");
								 return false;
								}
								else{
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
   
   function callClear1()
   {
   
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
  
  
 function checkApproveCancellationStatus(obj,id)
 {
  	document.getElementById("paraFrm_checkApproveCancelStatus").value=id; 
 	if(document.getElementById("paraFrm_checkApproveCancelStatus").value=="X")
       conf=confirm("Do you really want to approve this application ?");
      if(document.getElementById("paraFrm_checkApproveCancelStatus").value=="Z")
       conf=confirm("Do you really want to reject this application ?");
       
        if(conf)
				{
					obj.disabled=true;
					document.getElementById('paraFrm').target="main";
					document.getElementById("paraFrm").action="LeaveApplication_approveRejCancellationLeaveApp.action";
		  			document.getElementById("paraFrm").submit();
		  			 window.close();
				 }
				 else
				 {
				 return false; 
  				}
  				
  		return true;
 }
 
  
  
  function checkAppStatus(obj,id)
  {
   
  	try{
  	var approverComments = document.getElementById('paraFrm_approverComments').value;
  	  
   	var conf;
  	document.getElementById("paraFrm_checkApproveRejectStatus").value=id; 
  	if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="A")
       conf=confirm("Do you really want to approve this application ?");
      if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="R")
       conf=confirm("Do you really want to reject this application ?");
     
       if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="B")
       {
		     var fieldName=["paraFrm_approverComments"];
		    var lableName=["approverComm"];
		    var flag = ["enter"];
		    	if(!(validateBlank(fieldName,lableName,flag))){
					return false;
		        }
		    
       }
     
       if(document.getElementById("paraFrm_checkApproveRejectStatus").value=="B")
       conf=confirm("Do you really want to send back this application ?");
       
        
 		 if(conf)
				{
				 	obj.disabled=true;
			 		document.getElementById("paraFrm").target="main";
					document.getElementById("paraFrm").action="LeaveApplication_approveRejSendBackLeaveApp.action";
		  			document.getElementById("paraFrm").submit();
		  			window.close();
				 }
				 else
				 {
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
   
  function callForHalf()
  {
	
		var halfDay =  document.getElementById('paraFrm_halfDayCheck').value;
		var total = document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value;
		var closing = document.getElementById('paraFrm_levClosingBalance').value;
		  	var zeroFlag = document.getElementById('paraFrm_leaveApplication_zeroBalance').value ;
		if(document.getElementById('checkMeId').checked == true)
		{
			if(halfDay == 'Y' && total == "1.0" || total == "1")
			{
			
					
					if(zeroFlag=='Y')
					{
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
		else
		{
				if(halfDay == 'Y' && total == "0.5")
				{
				
					document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value ="1.0";
					var tot =eval(closing)-.5;
				//	alert(tot);
					document.getElementById('paraFrm_levClosingBalance').value =Math.round(tot*100)/100;
				
						if(zeroFlag=='Y')
						{
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
	
	function getBlank()
	{
	 	document.getElementById('paraFrm_levClosingBalance').value ='';
		 document.getElementById('paraFrm_leaveApplication_leaveTotalDays').value ='';
		   		
	
	}
	
	function callClose()
	{ 
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
  	
 	if(totleaveAdjust=="")
 	{
 		alert("Please enter amount of leaves to be pulled");
 		return false;
 	}
 
 	if(poolLevBalance==0)
 	{
 		alert("You dont have sufficient balance ");
 		return false;
 	}
 	if(eval(totleaveAdjust)>eval(poolLevBalance))
 	{
 		alert("Leaves to be pulled can not be greater than  balance leave");
 		return false;
 	}
 	 conf=confirm("Do you really want to pull leave balance?");
 	 if(conf)
 	 {
  pullLeaveFrom('<%=request.getContextPath()%>/leaves/LeaveApplication_pullLeaves.action?','LeaveForm',pullFromId);
  	}
  	else
  	{
  			return false;
  	}
  }
//autoDate();
	
</script>
	
	
	
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>