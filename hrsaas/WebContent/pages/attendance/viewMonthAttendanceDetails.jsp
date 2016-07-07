<!--Bhushan Dasare--><!--Jul 3, 2009-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp" %>

<body onunload="callClose();">
	<s:form action="ViewMonthAttendance" name="ViewMonthAttendanceDetails" id="paraFrm" validate="true" target="main" theme="simple">
		<s:hidden name="empId" /><s:hidden name="empBranchId" /><s:hidden name="empDivisionId" /><s:hidden name="empDepartmentId" />
		<s:hidden name="empDesignationId" /><s:hidden name="empTypeId" /><s:hidden name="empJoinDate" />
		<s:hidden name="autoPresentLateMark" /><s:hidden name="autoPresentHalfDay" /><s:hidden name="autoPresentAbsent" />
		<s:hidden name="lmEnabled" /><s:hidden name="lmInNoEnabled" /><s:hidden name="lmCount" /><s:hidden name="lmAdjustLeaveDays" />
		<s:hidden name="lmAdjustLeaveTypes" /><s:hidden name="lmInHrsEnabled" /><s:hidden name="lmDeductNonRegLeaveTypes" />
		<s:hidden name="hdEnabled" /><s:hidden name="hdDeductLeaveTypes" /><s:hidden name="woFixedEnabled" />
		<s:hidden name="woDaysForWeek1" /><s:hidden name="woDaysForWeek2" /><s:hidden name="woDaysForWeek3" />
		<s:hidden name="woDaysForWeek4" /><s:hidden name="woDaysForWeek5" /><s:hidden name="woDaysForWeek6" />
		<s:hidden name="workingHours" /><s:hidden name="attendanceCode" /><s:hidden name="month" /><s:hidden name="year" />
		<s:hidden name="recordNo" /><s:hidden id="oldValue" value="0" /><s:hidden id="oldHrsValue" value="00:00" />
		<s:hidden name="lockAttendance" /><s:hidden name="leaveUnplanFlag" /><s:hidden name="leaveUnauthFlag" />
		<s:hidden name="recoveryFlowFlag" />
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
							<td width="92%" class="txt"><strong class="text_head">View Monthly Attendance Details</strong></td>
							<td width="4%" valign="middle" align="right" class="txt">
								<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
							</td>
						</tr>
					</table>
					<table width="100%">
						<tr>
							<td>
								<input type="button" class="save" value=" Save" title="Save the attendance" 
								onclick="saveAttendance();"/>
								<input type="button" class="cancel" value=" Cancel" title="Close the window" onclick="callCancel();" />
								<s:submit cssClass="token" value="Recalculate This Record" title="Recalculate the attendance"
								action="ViewMonthAttendance_recalculateAttendance" onclick=" return recalculateAttendance();" />
							</td>
							<td align="right"><font color="red">*</font>Indicates Required</td>
						</tr>
					</table>
					<table width="100%" class="formbg">
						<tr>
							<td>
								<label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label>:
								<B><s:property value="monthName" /></B>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label>:
								<B><s:property value="year" /></B>
							</td>
							<td align="center">
								<label id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:
								<B><s:property value="empToken" /></B><s:hidden name="empToken" /> - 
								<B><s:property value="empName" /></B><s:hidden name="empName" />
							</td>
							<td align="right">
								<label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
								<B><s:property value="empBranch" /></B><s:hidden name="empBranch" />
							</td>
						</tr>
					</table>
					<table width="100%" class="formbg">
						<tr>
							<td>
								<strong class="forminnerhead">
									<label id="attendanceDetails" name="attendanceDetails" ondblclick="callShowDiv(this);"><%=label.get("attendanceDetails")%></label>
								</strong>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%">
									<s:if test="lmInHrsEnabled">
										<tr>
											<td colspan="2" align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="attendanceDays" name="attendanceDays" ondblclick="callShowDiv(this);"><%=label.get("attendanceDays")%></label></b>
											</td>
											<td align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="weeklyOffs" name="weeklyOffs" ondblclick="callShowDiv(this);"><%=label.get("weeklyOffs")%></label></b>
											</td>
											<td align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="holidays" name="holidays" ondblclick="callShowDiv(this);"><%=label.get("holidays")%></label></b>
											</td>
											<td colspan="2" align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="lateMarks" name="lateMarks" ondblclick="callShowDiv(this);"><%=label.get("lateMarks")%></label></b>
											</td>
											<td align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="halfDays" name="halfDays" ondblclick="callShowDiv(this);"><%=label.get("halfDays")%></label></b>
											</td>
											<td colspan="2" align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="paidLeaves" name="paidLeaves" ondblclick="callShowDiv(this);"><%=label.get("paidLeaves")%></label></b>
											</td>
											<td colspan="2" align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="unPaidLeaves" name="unPaidLeaves" ondblclick="callShowDiv(this);"><%=label.get("unPaidLeaves")%></label></b>
											</td>
											<s:if test="leaveUnplanFlag">
												<td align="center" width="10%" class="formth" nowrap="nowrap">
													<b><label id="penaltiesAdjusted" name="penaltiesAdjusted" ondblclick="callShowDiv(this);"><%=label.get("penaltiesAdjusted")%></label></b>
												</td>
												<td align="center" width="10%" class="formth" nowrap="nowrap">
													<b><label id="penaltiesUnAdjusted" name="penaltiesUnAdjusted" ondblclick="callShowDiv(this);"><%=label.get("penaltiesUnAdjusted")%></label></b>
												</td>
											</s:if>
											<s:if test="leaveUnauthFlag">
												<td align="center" width="10%" class="formth" nowrap="nowrap">
													<b><label id="unauthAdj" name="unauthAdj" ondblclick="callShowDiv(this);"><%=label.get("unauthAdj")%></label></b>
												</td>
												<td align="center" width="10%" class="formth" nowrap="nowrap">
													<b><label id="unauthUnAdj" name="unauthUnAdj" ondblclick="callShowDiv(this);"><%=label.get("unauthUnAdj")%></label></b>
												</td>
											</s:if>
											<td colspan="2" align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="salaryDays" name="salaryDays" ondblclick="callShowDiv(this);"><%=label.get("salaryDays")%></label></b>
											</td>
											<s:if test="recoveryFlowFlag">
											<td colspan="2" align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="recoveryDays" name="recoveryDays" ondblclick="callShowDiv(this);"><%=label.get("recoveryDays")%></label></b>
											</td>
											</s:if>
										</tr>
										<tr>
											<td align="center" width="5%" class="formth" nowrap="nowrap">
												<b><label id="days" name="days" ondblclick="callShowDiv(this);"><%=label.get("days")%></label></b>
											</td>
											<td align="center" width="5%" class="formth" nowrap="nowrap">
												<b><label id="hours" name="hours" ondblclick="callShowDiv(this);"><%=label.get("hours")%></label></b>
											</td>
											<td class="formth">&nbsp;</td>
											<td class="formth">&nbsp;</td>
											<td align="center" width="5%" class="formth" nowrap="nowrap">
												<b><label id="days" name="days" ondblclick="callShowDiv(this);"><%=label.get("days")%></label></b>
											</td>
											<td align="center" width="5%" class="formth" nowrap="nowrap">
												<b><label id="hours" name="hours" ondblclick="callShowDiv(this);"><%=label.get("hours")%></label></b>
											</td>
											<td class="formth">&nbsp;</td>
											<td align="center" width="5%" class="formth" nowrap="nowrap">
												<b><label id="days" name="days" ondblclick="callShowDiv(this);"><%=label.get("days")%></label></b>
											</td>
											<td align="center" width="5%" class="formth" nowrap="nowrap">
												<b><label id="hours" name="hours" ondblclick="callShowDiv(this);"><%=label.get("hours")%></label></b>
											</td>
											<td align="center" width="5%" class="formth" nowrap="nowrap">
												<b><label id="days" name="days" ondblclick="callShowDiv(this);"><%=label.get("days")%></label></b>
											</td>
											<td align="center" width="5%" class="formth" nowrap="nowrap">
												<b><label id="hours" name="hours" ondblclick="callShowDiv(this);"><%=label.get("hours")%></label></b>
											</td>
											<s:if test="leaveUnplanFlag">
												<td class="formth">&nbsp;</td>
												<td class="formth">&nbsp;</td>
											</s:if>	
											<s:if test="leaveUnauthFlag">
												<td class="formth">&nbsp;</td>
												<td class="formth">&nbsp;</td>
											</s:if>
											<td align="center" width="5%" class="formth" nowrap="nowrap">
												<b><label id="days" name="days" ondblclick="callShowDiv(this);"><%=label.get("days")%></label></b>
											</td>
											<td align="center" width="5%" class="formth" nowrap="nowrap">
												<b><label id="hours" name="hours" ondblclick="callShowDiv(this);"><%=label.get("hours")%></label></b>
											</td>
											<s:if test="recoveryFlowFlag">
											<td class="formth"></td>
											</s:if>
										</tr>
										<tr>
											<td align="center">
												<s:textfield name="attendanceDays" size="5" cssStyle="text-align:right;" 
												cssStyle="text-align:right; background-color:#F2F2F2;" readonly="true" />
											</td>
											<td align="center">
												<s:textfield name="attendanceHrs" size="5" cssStyle="text-align:right;" 
												cssStyle="text-align:right; background-color:#F2F2F2;" readonly="true" />
											</td>
											<td align="center">
												<s:textfield name="weeklyOffs" size="5" cssStyle="text-align:right;" maxLength="2" 
												onfocus="setOldValuesOnDaysBasis('paraFrm_weeklyOffs');" 
												onkeypress="return numbersOnly();" 
												onblur="return calculateAttendanceOnHoursBasis('paraFrm_weeklyOffs', 'NA', 'weeklyOffs');" />
											</td>
											<td align="center">
												<s:textfield name="holidays" size="5" cssStyle="text-align:right;" maxLength="2" 
												onfocus="setOldValuesOnDaysBasis('paraFrm_holidays');" 
												onkeypress="return numbersOnly();" 
												onblur="return calculateAttendanceOnHoursBasis('paraFrm_holidays', 'NA', 'holidays');" />
											</td>
											<s:if test="autoPresentLateMark || autoPresentAbsent">
												<td align="center">
													<s:textfield name="lateMarks" size="5" readonly="true" value="0.0" 
													cssStyle="text-align:right; background-color:#F2F2F2;" />
													
													<input type="hidden" name="originalLateMarks" value='0.0' />
												</td>
												<td align="center">
													<s:textfield name="lateMarksHrs" size="5" readonly="true" value="00:00" 
													cssStyle="text-align:right; background-color:#F2F2F2;" />
													
													<input type="hidden" name="originalLateMarksHrs" value='00:00' />
												</td>
											</s:if>
											<s:else>
												<td align="center">
													<s:textfield name="lateMarks" size="5" cssStyle="text-align:right;" maxLength="2"
													onfocus="setOldValuesOnHoursBasis('paraFrm_lateMarks', 'paraFrm_lateMarksHrs');" 
													onkeypress="return numbersOnly();" 
													onblur="setValueInBlank('paraFrm_lateMarks', 'NA');" />
													
													<input type="hidden" name="originalLateMarks" value='<s:property value="lateMarks" />' />
												</td>
												<td align="center">
													<s:textfield name="lateMarksHrs" size="5" cssStyle="text-align:right;" 
													maxLength="5" onfocus="setOldValuesOnHoursBasis('paraFrm_lateMarks', 'paraFrm_lateMarksHrs');" 
													onblur="javascript: if(validateTime('paraFrm_lateMarksHrs', 'lateMarks')) 
													{ setValueInBlank('paraFrm_lateMarksHrs', 'A'); };" />
													
													<input type="hidden" name="originalLateMarksHrs" value='<s:property value="lateMarksHrs" />' />
												</td>
											</s:else>
											<s:if test="autoPresentHalfDay || autoPresentAbsent">
												<td align="center">
													<s:textfield name="halfDays" size="5" readonly="true" value="0.0" 
													cssStyle="text-align:right; background-color:#F2F2F2;" />
													
													<input type="hidden" name="originalHalfDays" value='0.0' />
												</td>
											</s:if>
											<s:else>
												<td align="center">
													<s:textfield name="halfDays" size="5" cssStyle="text-align:right;" maxLength="2" 
													onfocus="setOldValuesOnDaysBasis('paraFrm_halfDays');" 
													onkeypress="return numbersOnly();" 
													onblur="setValueInBlank('paraFrm_halfDays', 'NA');" />
													
													<input type="hidden" name="originalHalfDays" value='<s:property value="halfDays" />' />
												</td>
											</s:else>
											<s:if test="autoPresentAbsent">
												<td align="center">
													<s:textfield name="paidLeaves" size="5" readonly="true" value="0.0"
													cssStyle="text-align:right; background-color:#F2F2F2;" />
												</td>
												<td align="center">
													<s:textfield name="paidLeavesHrs" size="5" readonly="true" value="00:00"
													cssStyle="text-align:right; background-color:#F2F2F2;" />
												</td>
											</s:if>
											<s:else>
												<td align="center">
													<s:textfield name="paidLeaves" size="5" readonly="true" 
													cssStyle="text-align:right; background-color:#F2F2F2;" />
												</td>
												<td align="center">
													<s:textfield name="paidLeavesHrs" size="5" readonly="true" 
													cssStyle="text-align:right; background-color:#F2F2F2;" />
												</td>
											</s:else>
											<s:if test="autoPresentAbsent">
												<td align="center">
													<s:textfield name="unPaidLeaves" size="5" readonly="true" value="0.0" 
													cssStyle="text-align:right; background-color:#F2F2F2;" />
													
													<s:hidden name="systemUnPaidLeaves" value="0.0" />
												</td>
												<td align="center">
													<s:textfield name="unPaidLeavesHrs" size="5" readonly="true" value="00:00" 
													cssStyle="text-align:right; background-color:#F2F2F2;" />
												</td>
											</s:if>
											<s:else>											
												<td align="center">
													<s:textfield name="unPaidLeaves" size="5" cssStyle="text-align:right;" maxLength="5" 
													onfocus="setOldValuesOnHoursBasis('paraFrm_unPaidLeaves', 'paraFrm_unPaidLeavesHrs');" 
													onkeypress="return numbersOnly();" 
													onblur="return calculateAttendanceOnHoursBasis('paraFrm_unPaidLeaves', 'paraFrm_unPaidLeavesHrs', 'unPaidLeaves');" />
													
													<s:hidden name="systemUnPaidLeaves" />
												</td>
												<td align="center">
													<s:textfield name="unPaidLeavesHrs" size="5" cssStyle="text-align:right;" maxLength="5" 
													onfocus="setOldValuesOnHoursBasis('paraFrm_unPaidLeaves', 'paraFrm_unPaidLeavesHrs');" 
													onblur="return calculateAttendanceOnHoursBasis('paraFrm_unPaidLeaves', 'paraFrm_unPaidLeavesHrs', 'unPaidLeaves');" />
												</td>
											</s:else>
											<s:if test="leaveUnplanFlag">
												<s:if test="autoPresentAbsent">
													<td align="center">
														<s:textfield name="penaltiesAdjusted" size="5" readonly="true" value="0.0" 
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
												</s:if>
												<s:else>
													<td align="center">
														<s:textfield name="penaltiesAdjusted" size="5" readonly="true" 
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
												</s:else>
												<s:if test="autoPresentAbsent">
													<td align="center">
														<s:textfield name="penaltiesUnAdjusted" size="5" readonly="true" value="0.0" 
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
												</s:if>
												<s:else>
													<td align="center">
														<s:textfield name="penaltiesUnAdjusted" size="5" readonly="true"
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
												</s:else>
											</s:if>
											<s:if test="leaveUnauthFlag">
												<s:if test="autoPresentAbsent">
													<td align="center">
														<s:textfield name="unauthAdjusted" size="5" readonly="true" value="0.0" 
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
												</s:if>
												<s:else>
													<td align="center">
														<s:textfield name="unauthAdjusted" size="5" readonly="true" 
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
												</s:else>
												<s:if test="autoPresentAbsent">
													<td align="center">
														<s:textfield name="unauthUnAdjusted" size="5" readonly="true" value="0.0" 
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
												</s:if>
												<s:else>
													<td align="center">
														<s:textfield name="unauthUnAdjusted" size="5" cssStyle="text-align:right;" maxLength="5" 
														onkeypress="return checkNumbers('paraFrm_unauthUnAdjusted');" 
														onblur="return calculateAttendanceOnHoursBasis('paraFrm_unauthUnAdjusted', 'NA', 'unauthUnAdj');" />
													</td>
												</s:else>
											</s:if>
											<td align="center">
												<s:textfield name="salaryDays" size="5" 
												cssStyle="text-align:right; background-color:#F2F2F2;" readonly="true" />
												
												<s:hidden name="totalAttendanceDays" />
											</td>
											<td align="center">
												<s:textfield name="salaryHrs" size="5" 
												cssStyle="text-align:right; background-color:#F2F2F2;" readonly="true" />
											</td>
											<s:if test="recoveryFlowFlag">
											<td align="center">
												<s:textfield name="recoveryDays" size="5" 
												cssStyle="text-align:right;" readonly="false" onkeypress="return numbersWithDot();" />
											</td>
											</s:if>
										</tr>
									</s:if>
									<s:else>
										<tr>
											<td align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="attendanceDays" name="attendanceDays" ondblclick="callShowDiv(this);"><%=label.get("attendanceDays")%></label></b>
											</td>
											<td align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="weeklyOffs" name="weeklyOffs" ondblclick="callShowDiv(this);"><%=label.get("weeklyOffs")%></label></b>
											</td>
											<td align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="holidays" name="holidays" ondblclick="callShowDiv(this);"><%=label.get("holidays")%></label></b>
											</td>
											<td align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="lateMarks" name="lateMarks" ondblclick="callShowDiv(this);"><%=label.get("lateMarks")%></label></b>
											</td>
											<td align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="halfDays" name="halfDays" ondblclick="callShowDiv(this);"><%=label.get("halfDays")%></label></b>
											</td>
											<td align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="paidLeaves" name="paidLeaves" ondblclick="callShowDiv(this);"><%=label.get("paidLeaves")%></label></b>
											</td>
											<td align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="unPaidLeaves" name="unPaidLeaves" ondblclick="callShowDiv(this);"><%=label.get("unPaidLeaves")%></label></b>
											</td>
											<s:if test="leaveUnplanFlag">
												<td align="center" width="10%" class="formth" nowrap="nowrap">
													<b><label id="penaltiesAdjusted" name="penaltiesAdjusted" ondblclick="callShowDiv(this);"><%=label.get("penaltiesAdjusted")%></label></b>
												</td>
												<td align="center" width="10%" class="formth" nowrap="nowrap">
													<b><label id="penaltiesUnAdjusted" name="penaltiesUnAdjusted" ondblclick="callShowDiv(this);"><%=label.get("penaltiesUnAdjusted")%></label></b>
												</td>
											</s:if>
											<s:if test="leaveUnauthFlag">
												<td align="center" width="10%" class="formth" nowrap="nowrap">
													<b><label id="unauthAdj" name="unauthAdj" ondblclick="callShowDiv(this);"><%=label.get("unauthAdj")%></label></b>
												</td>
												<td align="center" width="10%" class="formth" nowrap="nowrap">
													<b><label id="unauthUnAdj" name="unauthUnAdj" ondblclick="callShowDiv(this);"><%=label.get("unauthUnAdj")%></label></b>
												</td>
											</s:if>
											<td align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="salaryDays" name="salaryDays" ondblclick="callShowDiv(this);"><%=label.get("salaryDays")%></label></b>
											</td>
											<s:if test="recoveryFlowFlag">
											<td colspan="2" align="center" width="10%" class="formth" nowrap="nowrap">
												<b><label id="recoveryDays" name="recoveryDays" ondblclick="callShowDiv(this);"><%=label.get("recoveryDays")%></label></b>
											</td>
											</s:if>
										</tr>
										<tr>
											<td align="center">
												<s:textfield name="attendanceDays" size="5" cssStyle="text-align:right;" 
												cssStyle="text-align:right; background-color:#F2F2F2;" readonly="true" />
											</td>
											<td align="center">
												<s:textfield name="weeklyOffs" size="5" cssStyle="text-align:right;" maxLength="2" 
												onfocus="setOldValuesOnDaysBasis('paraFrm_weeklyOffs');" 
												onkeypress="return numbersOnly();" 
												onblur="return calculateAttendanceOnDaysBasis('paraFrm_weeklyOffs');" />
											</td>
											<td align="center">
												<s:textfield name="holidays" size="5" cssStyle="text-align:right;" maxLength="2" 
												onfocus="setOldValuesOnDaysBasis('paraFrm_holidays');" 
												onkeypress="return numbersOnly();" 
												onblur="return calculateAttendanceOnDaysBasis('paraFrm_holidays');" />
											</td>
											<s:if test="autoPresentLateMark || autoPresentAbsent">
												<td align="center">
													<s:textfield name="lateMarks" size="5" readonly="true" value="0.0"
													cssStyle="text-align:right; background-color:#F2F2F2;" readonly="true" />
													
													<input type="hidden" name="originalLateMarks" value='0.0' />
												</td>
											</s:if>
											<s:else>
												<td align="center">
													<s:textfield name="lateMarks" size="5" cssStyle="text-align:right;" maxLength="2"
													onfocus="setOldValuesOnDaysBasis('paraFrm_lateMarks');" 
													onkeypress="return numbersOnly();" 
													onblur="setValueInBlank('paraFrm_lateMarks', 'NA');" />
													
													<input type="hidden" name="originalLateMarks" value='<s:property value="lateMarks" />' />
												</td>
											</s:else>
											<s:if test="autoPresentHalfDay || autoPresentAbsent">
												<td align="center">
													<s:textfield name="halfDays" size="5" readonly="true" value="0.0" 
													cssStyle="text-align:right; background-color:#F2F2F2;" />
													
													<input type="hidden" name="originalHalfDays" value='0.0' />
												</td>
											</s:if>
											<s:else>
												<td align="center">
													<s:textfield name="halfDays" size="5" cssStyle="text-align:right;" maxLength="2" 
													onfocus="setOldValuesOnDaysBasis('paraFrm_halfDays');" 
													onkeypress="return numbersOnly();" 
													onblur="setValueInBlank('paraFrm_halfDays', 'NA');" />
													
													<input type="hidden" name="originalHalfDays" value='<s:property value="halfDays" />' />
												</td>
											</s:else>
											<s:if test="autoPresentAbsent">
												<td align="center">
													<s:textfield name="paidLeaves" size="5" readonly="true" value="0.0"
													cssStyle="text-align:right; background-color:#F2F2F2;" />
												</td>
												<td align="center">
													<s:textfield name="unPaidLeaves" size="5" readonly="true" value="0.0"
													cssStyle="text-align:right; background-color:#F2F2F2;" />
													
													<s:hidden name="systemUnPaidLeaves" value="0.0" />
												</td>
											</s:if>
											<s:else>
												<td align="center">
													<s:textfield name="paidLeaves" size="5" readonly="true"
													cssStyle="text-align:right; background-color:#F2F2F2;" />
												</td>
												<td align="center">
													<s:textfield name="unPaidLeaves" size="5" cssStyle="text-align:right;" maxLength="5" 
													onfocus="setOldValuesOnDaysBasis('paraFrm_unPaidLeaves');" 
													onkeypress="return checkNumbers('paraFrm_unPaidLeaves');" 
													onblur="return calculateAttendanceOnDaysBasis('paraFrm_unPaidLeaves');" />
													
													<s:hidden name="systemUnPaidLeaves" />
												</td>
											</s:else>
											<s:if test="leaveUnplanFlag">
												<s:if test="autoPresentAbsent">
													<td align="center">
														<s:textfield name="penaltiesAdjusted" size="5" readonly="true" value="0.0" 
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
													<td align="center">
														<s:textfield name="penaltiesUnAdjusted" size="5" readonly="true" value="0.0" 
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
												</s:if>
												<s:else>
													<td align="center">
														<s:textfield name="penaltiesAdjusted" size="5" readonly="true" 
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
													<td align="center">
														<s:textfield name="penaltiesUnAdjusted" size="5" readonly="true"
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
												</s:else>
											</s:if>
											<s:if test="leaveUnauthFlag">
												<s:if test="autoPresentAbsent">
													<td align="center">
														<s:textfield name="unauthAdjusted" size="5" readonly="true" value="0.0" 
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
													<td align="center">
														<s:textfield name="unauthUnAdjusted" size="5" readonly="true" value="0.0" 
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
												</s:if>
												<s:else>
													<td align="center">
														<s:textfield name="unauthAdjusted" size="5" readonly="true" 
														cssStyle="text-align:right; background-color:#F2F2F2;" />
													</td>
													<td align="center">
														<s:textfield name="unauthUnAdjusted" size="5" cssStyle="text-align:right;" 
														onkeypress="return checkNumbers('paraFrm_unauthUnAdjusted');" 
														onblur="return calculateAttendanceOnDaysBasis('paraFrm_unauthUnAdjusted');" />
													</td>
												</s:else>
											</s:if>
											<td align="center">
												<s:textfield name="salaryDays" size="5" 
												cssStyle="text-align:right; background-color:#F2F2F2;" readonly="true" />
												
												<s:hidden name="totalAttendanceDays" />
											</td>
											<s:if test="recoveryFlowFlag">
											<td align="center">
												<s:textfield name="recoveryDays" size="5" 
												cssStyle="text-align:right; " readonly="false" />
											</td>
											</s:if>
										</tr>
									</s:else>
								</table>
							</td>
						</tr>
					</table>
					<%! double systemPaidLeaves = 0.0;%>
					<% Object[][] viewAttendanceDetails = (Object[][])request.getAttribute("viewAttendanceDetails"); %>
					<table width="100%" class="formbg">
						<tr>
							<td>
								<table width="100%">
									<tr>
										<td width="40%">
											<strong class="forminnerhead">
												<label id="leaveDetails" name="leaveDetails" ondblclick="callShowDiv(this);"><%=label.get("leaveDetails")%></label>
											</strong>
										</td>
										<% if(viewAttendanceDetails != null && viewAttendanceDetails.length > 0) { %>
											<td>
												<s:submit cssClass="token" value="Recalculate Leave Details" title="Recalculate leave details"
												action="ViewMonthAttendance_recalculateAttendanceDetails" onclick="return recalculateAttendance();" />
											</td>
										<% } %>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%">
									<s:if test="lmInHrsEnabled">
										<tr>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="leaveType" name="leaveType" ondblclick="callShowDiv(this);"><%=label.get("leaveType")%></label></b>
											</td>
											<td colspan="2" align="center" class="formth" nowrap="nowrap">
												<b><label id="openingBalance" name="openingBalance" ondblclick="callShowDiv(this);"><%=label.get("openingBalance")%></label></b>
											</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="paidleavesAdjusted" name="paidleavesAdjusted" ondblclick="callShowDiv(this);"><%=label.get("paidleavesAdjusted")%></label></b>
											</td>
											<td colspan="2" align="center" class="formth" nowrap="nowrap">
												<b><label id="lateMarksAdjusted" name="lateMarksAdjusted" ondblclick="callShowDiv(this);"><%=label.get("lateMarksAdjusted")%></label></b>
											</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="halfDaysAdjusted" name="halfDaysAdjusted" ondblclick="callShowDiv(this);"><%=label.get("halfDaysAdjusted")%></label></b>
											</td>
											<s:if test="leaveUnauthFlag">
												<td align="center" class="formth" nowrap="nowrap">
													<b><label id="unauthAdj" name="unauthAdj" ondblclick="callShowDiv(this);"><%=label.get("unauthAdj")%></label></b>
												</td>
											</s:if>
											<td colspan="2" align="center" class="formth" nowrap="nowrap">
												<b><label id="manuallyAdjusted" name="manuallyAdjusted" ondblclick="callShowDiv(this);"><%=label.get("manuallyAdjusted")%></label></b>
											</td>
											<td colspan="2" align="center" class="formth" nowrap="nowrap">
												<b><label id="currentBalance" name="currentBalance" ondblclick="callShowDiv(this);"><%=label.get("currentBalance")%></label></b>
											</td>
										</tr>
										<tr>
											<td></td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="days" name="days" ondblclick="callShowDiv(this);"><%=label.get("days")%></label></b>
											</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="hours" name="hours" ondblclick="callShowDiv(this);"><%=label.get("hours")%></label></b>
											</td>
											<td class="formth">&nbsp;</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="days" name="days" ondblclick="callShowDiv(this);"><%=label.get("days")%></label></b>
											</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="hours" name="hours" ondblclick="callShowDiv(this);"><%=label.get("hours")%></label></b>
											</td>
											<td class="formth">&nbsp;</td>
											<s:if test="leaveUnauthFlag">
												<td class="formth">&nbsp;</td>
											</s:if>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="days" name="days" ondblclick="callShowDiv(this);"><%=label.get("days")%></label></b>
											</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="hours" name="hours" ondblclick="callShowDiv(this);"><%=label.get("hours")%></label></b>
											</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="days" name="days" ondblclick="callShowDiv(this);"><%=label.get("days")%></label></b>
											</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="hours" name="hours" ondblclick="callShowDiv(this);"><%=label.get("hours")%></label></b>
											</td>
										</tr>
										<% 	if(viewAttendanceDetails != null && viewAttendanceDetails.length > 0) {
												systemPaidLeaves = 0.0;
												for(int i = 0; i < viewAttendanceDetails.length; i++) {
										%>		<tr>
													<td>
														<s:hidden name="leaveId" id='<%="leaveId" + i%>' 
														value="<%=String.valueOf(viewAttendanceDetails[i][0])%>" />
														
														<s:hidden name="leaveType" id='<%="leaveType" + i%>' 
														value="<%=String.valueOf(viewAttendanceDetails[i][1])%>" />
														
														<%=String.valueOf(viewAttendanceDetails[i][1])%>
													</td>
													<td align="center">
														<input type="text" name="openingBalance" id='openingBalance<%=i%>' size="5" 
														readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
														value="<%=String.valueOf(viewAttendanceDetails[i][2])%>" />
													</td>
													<td align="center">
														<input type="text" name="openingHrsBalance" id='openingHrsBalance<%=i%>' 
														size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
														value="<%=String.valueOf(viewAttendanceDetails[i][3])%>" />
													</td>
													<s:if test="autoPresentAbsent">
														<td align="center">
															<input type="text" name="paidLeavesAdjusted" id='paidLeavesAdjusted<%=i%>' 
															size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
															value="0.0" />
														</td>
													</s:if>
													<s:else>
														<td align="center">
															<input type="text" name="paidLeavesAdjusted" id='paidLeavesAdjusted<%=i%>' 
															size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
															value="<%=String.valueOf(viewAttendanceDetails[i][4])%>" />
															
															<%systemPaidLeaves += Double.parseDouble(String.valueOf(viewAttendanceDetails[i][4]));%>
														</td>
													</s:else>
													<s:if test="autoPresentLateMark || autoPresentAbsent">
														<td align="center">
															<input type="text" name="lateMarksAdjusted" id='lateMarksAdjusted<%=i%>' 
															size="5" readonly="readonly" value="0.0" 
															style="text-align:right; background-color:#F2F2F2;" />
														</td>
														<td align="center">
															<input type="text" name="lateMarksHrsAdjusted" id='lateMarksHrsAdjusted<%=i%>' 
															size="5" readonly="readonly" value="00:00" 
															style="text-align:right; background-color:#F2F2F2;" />
														</td>
													</s:if>
													<s:else>
														<td align="center">
															<input type="text" name="lateMarksAdjusted" id='lateMarksAdjusted<%=i%>' 
															size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
															value="<%=String.valueOf(viewAttendanceDetails[i][6])%>" />
														</td>
														<td align="center">
															<input type="text" name="lateMarksHrsAdjusted" id='lateMarksHrsAdjusted<%=i%>' 
															size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
															value="<%=String.valueOf(viewAttendanceDetails[i][7])%>" />
														</td>
													</s:else>
													<s:if test="autoPresentHalfDay || autoPresentAbsent">
														<td align="center">
															<input type="text" name="halfDaysAdjusted" id='halfDaysAdjusted<%=i%>' 
															size="5" readonly="readonly" value="0.0" 
															style="text-align:right; background-color:#F2F2F2;" />
														</td>
													</s:if>
													<s:else>
														<td align="center">
															<input type="text" name="halfDaysAdjusted" id='halfDaysAdjusted<%=i%>' 
															size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
															value="<%=String.valueOf(viewAttendanceDetails[i][8])%>" />
														</td>
													</s:else>
													<s:if test="leaveUnauthFlag">
														<s:if test="autoPresentAbsent">
															<td align="center">
																<s:textfield name="unauthorisedAdj" id='unauthorisedAdj<%=i%>' size="5" 
																readonly="readonly" value="0.0" 
																cssStyle="text-align:right; background-color:#F2F2F2;" />
															</td>
														</s:if>
														<s:else>
															<td align="center">
																<s:textfield name="unauthorisedAdj" id='unauthorisedAdj<%=i%>' size="5" 
																readonly="readonly" cssStyle="text-align:right; background-color:#F2F2F2;" 
																value="<%=String.valueOf(viewAttendanceDetails[i][5])%>" />
															</td>
														</s:else>
													</s:if>
													<s:if test="autoPresentAbsent">
														<td align="center">
															<input type="text" name="manuallyAdjusted" id='manuallyAdjusted<%=i%>' 
															size="5" readonly="true" value="0.0" 
															style="text-align:right; background-color:#F2F2F2;"/>
														</td>
														<td align="center">
															<input type="text" name="manuallyHrsAdjusted" id='manuallyHrsAdjusted<%=i%>' 
															size="5" readonly="true" value="0.0" 
															style="text-align:right; background-color:#F2F2F2;"/>
														</td>
													</s:if>
													<s:else>
														<td align="center">
															<input type="text" name="manuallyAdjusted" id='manuallyAdjusted<%=i%>' 
															size="5" style="text-align:right;" maxlength="5" 
															value="<%=String.valueOf(viewAttendanceDetails[i][9])%>" 
															onfocus="setOldValuesOnHoursBasis('manuallyAdjusted<%=i%>', 'manuallyHrsAdjusted<%=i%>');"
															onkeypress="return numbersOnly();"  
															onblur="doManuallyAdjustOnHoursBasis('<%=i%>');" />
														</td>
														<td align="center">
															<input type="text" name="manuallyHrsAdjusted" id='manuallyHrsAdjusted<%=i%>' 
															size="5" style="text-align:right;" maxlength="5" 
															value="<%=String.valueOf(viewAttendanceDetails[i][10])%>" 
															onfocus="setOldValuesOnHoursBasis('manuallyAdjusted<%=i%>', 'manuallyHrsAdjusted<%=i%>');"
															onblur="doManuallyAdjustOnHoursBasis('<%=i%>');" />
														</td>
													</s:else>
													<td align="center">
														<s:hidden id='<%="hiddenCurrentBalance" + i%>' 
														value="<%=String.valueOf(viewAttendanceDetails[i][11])%>" />														
														
														<input type="text" name="currentBalance" id='currentBalance<%=i%>' 
														size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
														value="<%=String.valueOf(viewAttendanceDetails[i][11])%>" />
													</td>
													<td align="center">
														<s:hidden id='<%="hiddenCurrentHrsBalance" + i%>' 
														value="<%=String.valueOf(viewAttendanceDetails[i][12])%>" />
													
														<input type="text" name="currentHrsBalance" id='currentHrsBalance<%=i%>' 
														size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
														value="<%=String.valueOf(viewAttendanceDetails[i][12])%>" />
													</td>
												</tr>
										<% 		}
											}
										%>
									</s:if>
									<s:else>
										<tr>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="leaveType" name="leaveType" ondblclick="callShowDiv(this);"><%=label.get("leaveType")%></label></b>
											</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="openingBalance" name="openingBalance" ondblclick="callShowDiv(this);"><%=label.get("openingBalance")%></label></b>
											</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="paidleavesAdjusted" name="paidleavesAdjusted" ondblclick="callShowDiv(this);"><%=label.get("paidleavesAdjusted")%></label></b>
											</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="lateMarksAdjusted" name="lateMarksAdjusted" ondblclick="callShowDiv(this);"><%=label.get("lateMarksAdjusted")%></label></b>
											</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="halfDaysAdjusted" name="halfDaysAdjusted" ondblclick="callShowDiv(this);"><%=label.get("halfDaysAdjusted")%></label></b>
											</td>
											<s:if test="leaveUnauthFlag">
												<td align="center" class="formth" nowrap="nowrap">
													<b><label id="unauthAdj" name="unauthAdj" ondblclick="callShowDiv(this);"><%=label.get("unauthAdj")%></label></b>
												</td>
											</s:if>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="manuallyAdjusted" name="manuallyAdjusted" ondblclick="callShowDiv(this);"><%=label.get("manuallyAdjusted")%></label></b>
											</td>
											<td align="center" class="formth" nowrap="nowrap">
												<b><label id="currentBalance" name="currentBalance" ondblclick="callShowDiv(this);"><%=label.get("currentBalance")%></label></b>
											</td>
										</tr>
										<% 	if(viewAttendanceDetails != null && viewAttendanceDetails.length > 0) {
												systemPaidLeaves = 0.0;
												for(int i = 0; i < viewAttendanceDetails.length; i++) {
										%>		<tr>
													<td>
														<s:hidden name="leaveId" id='<%="leaveId" + i%>' 
														value="<%=String.valueOf(viewAttendanceDetails[i][0])%>" />
														
														<s:hidden name="leaveType" id='<%="leaveType" + i%>' 
														value="<%=String.valueOf(viewAttendanceDetails[i][1])%>" />
														
														<%=String.valueOf(viewAttendanceDetails[i][1])%>
													</td>
													<td align="center">
														<input type="text" name="openingBalance" id='openingBalance<%=i%>' size="5" 
														readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
														value="<%=String.valueOf(viewAttendanceDetails[i][2])%>" />
													</td>
													<s:if test="autoPresentAbsent">
														<td align="center">
															<input type="text" name="paidLeavesAdjusted" id='paidLeavesAdjusted<%=i%>' 
															size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
															value="0.0" />
														</td>
													</s:if>
													<s:else>
														<td align="center">
															<input type="text" name="paidLeavesAdjusted" id='paidLeavesAdjusted<%=i%>' 
															size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
															value="<%=String.valueOf(viewAttendanceDetails[i][3])%>" />
															
															<%systemPaidLeaves += Double.parseDouble(String.valueOf(viewAttendanceDetails[i][3]));%>
														</td>
													</s:else>
													<s:if test="autoPresentLateMark || autoPresentAbsent">
														<td align="center">
															<input type="text" name="lateMarksAdjusted" id='lateMarksAdjusted<%=i%>' 
															size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
															value="0.0" />
														</td>
													</s:if>
													<s:else>
														<td align="center">
															<input type="text" name="lateMarksAdjusted" id='lateMarksAdjusted<%=i%>' 
															size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
															value="<%=String.valueOf(viewAttendanceDetails[i][5])%>" />
														</td>
													</s:else>
													<s:if test="autoPresentHalfDay || autoPresentAbsent">
														<td align="center">
															<input type="text" name="halfDaysAdjusted" id='halfDaysAdjusted<%=i%>' 
															size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
															value="0.0" />
														</td>
													</s:if>
													<s:else>
														<td align="center">
															<input type="text" name="halfDaysAdjusted" id='halfDaysAdjusted<%=i%>' 
															size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
															value="<%=String.valueOf(viewAttendanceDetails[i][6])%>" />
														</td>
													</s:else>
													<s:if test="leaveUnauthFlag">
														<s:if test="autoPresentAbsent">
															<td align="center">
																<input type="text" name="unauthorisedAdj" id='unauthorisedAdj<%=i%>' size="5" 
																readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
																value="0.0" />
															</td>
														</s:if>
														<s:else>
															<td align="center">
																<input type="text" name="unauthorisedAdj" id='unauthorisedAdj<%=i%>' size="5" 
																readonly="readonly" style="text-align:right; background-color:#F2F2F2;"
																value="<%=String.valueOf(viewAttendanceDetails[i][4])%>" />
															</td>
														</s:else>
													</s:if>
													<s:if test="autoPresentAbsent">
														<td align="center">
															<input type="text" name="manuallyAdjusted" id='manuallyAdjusted<%=i%>' 
															size="5" readonly="true" value="0.0" 
															style="text-align:right; background-color:#F2F2F2;"/>
														</td>
													</s:if>
													<s:else>
														<td align="center">
															<input type="text" name="manuallyAdjusted" id='manuallyAdjusted<%=i%>' 
															size="5" style="text-align:right;" maxlength="5" 
															value="<%=String.valueOf(viewAttendanceDetails[i][7])%>" 
															onblur="doManuallyAdjustOnDaysBasis('<%=i%>');" 
															onfocus="setOldValuesOnDaysBasis('manuallyAdjusted<%=i%>');" 
															onkeypress="return checkNumbers('manuallyAdjusted<%=i%>');" />
														</td>
													</s:else>
													<td align="center">
														<s:hidden id='<%="hiddenCurrentBalance" + i%>' 
														value="<%=String.valueOf(viewAttendanceDetails[i][8])%>" />
														
														<input type="text" name="currentBalance" id='currentBalance<%=i%>' 
														size="5" readonly="readonly" style="text-align:right; background-color:#F2F2F2;" 
														value="<%=String.valueOf(viewAttendanceDetails[i][8])%>" />
													</td>
												</tr>
										<% 		}
											}
										%>
									</s:else>
								</table>
								<% 	if(viewAttendanceDetails == null || viewAttendanceDetails.length < 1) { %>
									<table width="100%">
										<tr>
											<td align="center"><font color="red">No Data To Display</font></td>
										</tr>
									</table>
								<% 	} %>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<table width="100%">
						<tr>
							<td>
								<input type="button" class="save" value=" Save" title="Save the attendance" 
								onclick="saveAttendance();"/>
								<input type="button" class="cancel" value=" Cancel" title="Close the window" onclick="callCancel();" />
								<s:submit cssClass="token" value="Recalculate This Record" title="Recalculate the attendance"
								action="ViewMonthAttendance_recalculateAttendance" onclick=" return recalculateAttendance();" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</s:form>
</body>

<script type="text/javascript">
	callSave();
	
	function callSave() {
		var saveMessage = '<%=request.getAttribute("saveMessage")%>';
		if(saveMessage != 'null') {
			alert(saveMessage);
			window.close();
		}
	}

	function setValueInBlank(fieldName, isApplicable) {
		var currentValue = document.getElementById(fieldName).value;
		if(currentValue == '' && isApplicable == 'A') {
			document.getElementById(fieldName).value = document.getElementById('oldHrsValue').value;
			document.getElementById(fieldName).focus();
		} else if(currentValue == '') {
			document.getElementById(fieldName).value = document.getElementById('oldValue').value;
			document.getElementById(fieldName).focus();
		}
	}

	function getMinutesFromDays(days) {
		var minutes;
		try {
			var workingHours = eval(document.getElementById('paraFrm_workingHours').value);
			minutes = eval(days * workingHours * 60);
		} catch(e) {}
		return minutes;
	}
	
	function getMinutesFromHours(hours) {
		var minutes;
		try {
			hours = hours.split(':');
			var minutes = eval(hours[0]) * 60;
			minutes += eval(hours[1]);
		} catch(e) {}		
		return minutes;
	}
	
	function getDaysAndRemainingMins(minutes) {
		var daysAndRemainingMins;
		try {
			var workingHours = document.getElementById('paraFrm_workingHours').value;
		
			var days = Math.floor((minutes / 60) / workingHours);
			var daysInMins = days * workingHours * 60;
			var remainingMins = minutes - daysInMins;
				
			var daysAndRemainingMins = days + "," + remainingMins;
		} catch(e) {}		
		return daysAndRemainingMins;
	}
	
	function isTimeValid(field, label) {
		return validateTime(field, label)
	}
	
	function getHours(minutes) {
		var hours;
		try {
			var hrs = Math.floor(minutes / 60);
			var mins = Math.floor(minutes % 60);
			
			if(hrs < 10) {
				hrs = '0' + hrs;
			}
			if(mins < 10) {
				mins = '0' + mins;
			}
			hours =  hrs + ':' + mins;
		} catch(e) {}
		return hours;
	}
	
	function doManuallyAdjustOnHoursBasis(id) {
		try {
			var manuallyAdjusted = document.getElementById('manuallyAdjusted' + id).value;
			var manuallyHrsAdjusted = document.getElementById('manuallyHrsAdjusted' + id).value;
			
			var oldValue = document.getElementById('oldValue').value;
			var oldHrsValue = document.getElementById('oldHrsValue').value;
			
			if(isTimeValid('manuallyHrsAdjusted' + id, 'manuallyAdjusted')) {
				if(manuallyAdjusted == '') {
					document.getElementById('manuallyAdjusted' + id).value = oldValue;
					document.getElementById('manuallyAdjusted' + id).focus();
				} else if(manuallyHrsAdjusted == '') {
					document.getElementById('manuallyHrsAdjusted' + id).value = oldHrsValue;
					document.getElementById('manuallyHrsAdjusted' + id).focus();
				} else {
					var manuallyAdjustedMins = getMinutesFromDays(manuallyAdjusted);
					manuallyAdjustedMins += getMinutesFromHours(manuallyHrsAdjusted);
					
					var oldValueMins = getMinutesFromDays(oldValue);
					oldValueMins += getMinutesFromHours(oldHrsValue);
					
					manuallyAdjustedMins = manuallyAdjustedMins - oldValueMins;
							
					if(manuallyAdjustedMins != 0) {
						// current balance
						var currentBalance = eval(document.getElementById('currentBalance' + id).value);
						var currentHrsBalance = document.getElementById('currentHrsBalance' + id).value;
									
						var currentBalanceMins = getMinutesFromDays(currentBalance);
						currentBalanceMins += getMinutesFromHours(currentHrsBalance);
										
						currentBalanceMins -= manuallyAdjustedMins;
						
						// paid leaves
						var paidLeaves = eval(document.getElementById('paraFrm_paidLeaves').value);
						var paidLeavesHrs = document.getElementById('paraFrm_paidLeavesHrs').value;
						
						var paidLeavesMins = getMinutesFromDays(paidLeaves);
						paidLeavesMins +=  getMinutesFromHours(paidLeavesHrs);
						
						paidLeavesMins += manuallyAdjustedMins;
								
						// attendance days 
						var attendanceDays = eval(document.getElementById('paraFrm_attendanceDays').value);
						var attendanceHrs = document.getElementById('paraFrm_attendanceHrs').value;
						
						var attendanceMins = getMinutesFromDays(attendanceDays);
						attendanceMins += getMinutesFromHours(attendanceHrs);
						
						attendanceMins -= manuallyAdjustedMins;
						
						if(currentBalanceMins < 0) {
							alert(document.getElementById('manuallyAdjusted').innerHTML + ' should not exceed the ' + 
							document.getElementById('currentBalance').innerHTML);
							
							document.getElementById('manuallyAdjusted' + id).value = oldValue;
							document.getElementById('manuallyHrsAdjusted' + id).value = oldHrsValue;
							document.getElementById('manuallyAdjusted' + id).focus();
						} else {
							// current balance
							var curBalDaysAndRemMins = getDaysAndRemainingMins(currentBalanceMins).split(',');
							document.getElementById('currentBalance' + id).value = curBalDaysAndRemMins[0];
							document.getElementById('currentHrsBalance' + id).value = getHours(curBalDaysAndRemMins[1]);
							
							// paid leaves
							var paidLevDaysAndRemMins = getDaysAndRemainingMins(paidLeavesMins).split(',');
							document.getElementById('paraFrm_paidLeaves').value = paidLevDaysAndRemMins[0];
							document.getElementById('paraFrm_paidLeavesHrs').value = getHours(paidLevDaysAndRemMins[1]);
							
							// attendance days
							var atdnDaysAndRemMins = getDaysAndRemainingMins(attendanceMins).split(',');
							document.getElementById('paraFrm_attendanceDays').value = atdnDaysAndRemMins[0];
							document.getElementById('paraFrm_attendanceHrs').value = getHours(atdnDaysAndRemMins[1]);
						}
					}
				}
			} else {
				document.getElementById('manuallyHrsAdjusted' + id).value = oldHrsValue;
				document.getElementById('manuallyHrsAdjusted' + id).focus();
			}
		} catch(e) {}
	}
	
	function doManuallyAdjustOnDaysBasis(id) {
		var manuallyAdjusted = document.getElementById('manuallyAdjusted' + id).value;
		var oldValue = document.getElementById('oldValue').value;

		if(manuallyAdjusted == '') {
			document.getElementById('manuallyAdjusted' + id).value = oldValue;
			document.getElementById('manuallyAdjusted' + id).focus();
		} else {
			manuallyAdjusted = getRoundedNumber(manuallyAdjusted);
			manuallyAdjusted = Math.abs(manuallyAdjusted) - Math.abs(oldValue);
					
			if(manuallyAdjusted != 0) {
				var currentBalance = eval(document.getElementById('currentBalance' + id).value);
				currentBalance = eval(getRoundedNumber(currentBalance));
				currentBalance = currentBalance - manuallyAdjusted;
				
				var paidLeaves = eval(document.getElementById('paraFrm_paidLeaves').value);
				paidLeaves = eval(getRoundedNumber(paidLeaves));
				paidLeaves = paidLeaves + manuallyAdjusted;
						
				var attendanceDays = eval(document.getElementById('paraFrm_attendanceDays').value);
				attendanceDays = eval(getRoundedNumber(attendanceDays));
				attendanceDays = attendanceDays - manuallyAdjusted;
						
				if(currentBalance < 0) {
					alert(document.getElementById('manuallyAdjusted').innerHTML + ' should not exceed the ' + 
					document.getElementById('currentBalance').innerHTML);
					
					document.getElementById('manuallyAdjusted' + id).value = oldValue;
					document.getElementById('manuallyAdjusted' + id).focus();						
				} else {
					document.getElementById('currentBalance' + id).value = getRoundedNumber(currentBalance);
					document.getElementById('paraFrm_paidLeaves').value = paidLeaves;
					document.getElementById('paraFrm_attendanceDays').value = attendanceDays;
				}
			}
		}
	}

	function recalculateAttendance() {
		var lockAttendance = document.getElementById('paraFrm_lockAttendance').value;
		if(lockAttendance == 'true') {
			alert('Attendance is already locked!');
			return false;
		} else {
			document.getElementById('paraFrm').target = '_self';
		}
		return true;
	}

	function saveAttendance() {
		var lockAttendance = document.getElementById('paraFrm_lockAttendance').value;
		if(lockAttendance == 'true') {
			alert('Attendance is already locked!');
			window.close();
		} else {
			var isSaveAttendnace = confirm('Are you sure!\n You want to save the attendance?');
			if(isSaveAttendnace) {
				try {
					var recordNo = document.getElementById('paraFrm_recordNo').value;
					
					var lmInHrsEnabled = document.getElementById('paraFrm_lmInHrsEnabled').value;
					var leaveUnplanFlag = document.getElementById('paraFrm_leaveUnplanFlag').value;
					var leaveUnauthFlag = document.getElementById('paraFrm_leaveUnauthFlag').value;
					
					// attendance days
					var attendanceDays = document.getElementById('paraFrm_attendanceDays').value;
					if(lmInHrsEnabled == 'true') {
						var attendanceHrs = document.getElementById('paraFrm_attendanceHrs').value.split(':');
						attendanceDays += ' d ' + attendanceHrs[0] + ' h ' + attendanceHrs[1] + ' m';
					}
					window.opener.document.getElementById('attendanceDays' + recordNo).value = attendanceDays;
					window.opener.document.getElementById('lblAttendanceDays' + recordNo).innerHTML = attendanceDays;
					
					// weekly offs
					var weeklyOffs = document.getElementById('paraFrm_weeklyOffs').value;
					window.opener.document.getElementById('weeklyOffs' + recordNo).value = weeklyOffs;
					window.opener.document.getElementById('lblWeeklyOffs' + recordNo).innerHTML = weeklyOffs;
					
					// holidays
					var holidays = document.getElementById('paraFrm_holidays').value;
					window.opener.document.getElementById('holidays' + recordNo).value = holidays;
					window.opener.document.getElementById('lblHolidays' + recordNo).innerHTML = holidays;
					
					// late marks
					var lateMarks = document.getElementById('paraFrm_lateMarks').value;
					if(lmInHrsEnabled == 'true') {
						var lateMarksHrs = document.getElementById('paraFrm_lateMarksHrs').value.split(':');
						lateMarks += ' d ' + lateMarksHrs[0] + ' h ' + lateMarksHrs[1] + ' m';
					}
					window.opener.document.getElementById('lateMarks' + recordNo).value = lateMarks;
					window.opener.document.getElementById('lblLateMarks' + recordNo).innerHTML = lateMarks;
					
					// half days
					var halfDays = document.getElementById('paraFrm_halfDays').value;
					window.opener.document.getElementById('halfDays' + recordNo).value = halfDays;
					window.opener.document.getElementById('lblHalfDays' + recordNo).innerHTML = halfDays;
					
					// paid leaves
					var totalPaid = eval(document.getElementById('paraFrm_paidLeaves').value);
					if(leaveUnplanFlag == 'true') {
						totalPaid += eval(document.getElementById('paraFrm_penaltiesAdjusted').value);
					}
					if(leaveUnauthFlag == 'true') {
						totalPaid += eval(document.getElementById('paraFrm_unauthAdjusted').value);
					}
					if(lmInHrsEnabled == 'true') {
						var paidLeavesHrs = document.getElementById('paraFrm_paidLeavesHrs').value.split(':');
						totalPaid += ' d ' + paidLeavesHrs[0] + ' h ' + paidLeavesHrs[1] + ' m';
					}
					window.opener.document.getElementById('paidLeaves' + recordNo).value = totalPaid;
					window.opener.document.getElementById('lblPaidLeaves' + recordNo).innerHTML = totalPaid;
					
					// unpaid leaves
					var totalUnpaid = eval(document.getElementById('paraFrm_unPaidLeaves').value);
					if(leaveUnplanFlag == 'true') {
						totalUnpaid += eval(document.getElementById('paraFrm_penaltiesUnAdjusted').value);
					}
					if(leaveUnauthFlag == 'true') {
						totalUnpaid += eval(document.getElementById('paraFrm_unauthUnAdjusted').value);
					}
					if(lmInHrsEnabled == 'true') {
						var unPaidLeavesHrs = document.getElementById('paraFrm_unPaidLeavesHrs').value.split(':');
						totalUnpaid += ' d ' + unPaidLeavesHrs[0] + ' h ' + unPaidLeavesHrs[1] + ' m';
					}
					window.opener.document.getElementById('unPaidLeaves' + recordNo).value = totalUnpaid;
					window.opener.document.getElementById('lblUnPaidLeaves' + recordNo).innerHTML = totalUnpaid;
					
					// salary days
					var salaryDays = document.getElementById('paraFrm_salaryDays').value;
					if(lmInHrsEnabled == 'true') {
						var salaryHrs = document.getElementById('paraFrm_salaryHrs').value.split(':');
						salaryDays += ' d ' + salaryHrs[0] + ' h ' + salaryHrs[1] + ' m';
					}
					window.opener.document.getElementById('salaryDays' + recordNo).value = salaryDays;
					window.opener.document.getElementById('lblSalaryDays' + recordNo).innerHTML = salaryDays;
					
					
					if(document.getElementById('paraFrm_recoveryFlowFlag').value=="true"){
					var recoveryDays = document.getElementById('paraFrm_recoveryDays').value;
					window.opener.document.getElementById('recoveryDays' + recordNo).innerHTML = recoveryDays;
					}
					
				
					document.getElementById('paraFrm').action = 'ViewMonthAttendance_saveAttendance.action';
					document.getElementById('paraFrm').target = '';
					document.getElementById('paraFrm').submit();
				} catch(e) {}
			}
		}
	}
	
	function getRoundedNumber(number) {
		var roundedNumber;
		try {
			number = Math.abs(number);
			
			roundedNumber = eval(Math.round(number * Math.pow(10, 2)) / Math.pow(10, 2));
		} catch(e) {
			roundedNumber = number;
		}
		return roundedNumber;
	}
	
	function setOldValuesOnDaysBasis(fieldName) {
		var currentValue = document.getElementById(fieldName).value;
		document.getElementById('oldValue').value = currentValue;
	}
	
	function setOldValuesOnHoursBasis(fieldForDays, fieldForHrs) {
		try {
			var oldValue = document.getElementById(fieldForDays).value;
			var oldHrsValue = document.getElementById(fieldForHrs).value;
			
			document.getElementById('oldValue').value = oldValue;
			document.getElementById('oldHrsValue').value = oldHrsValue;
		} catch(e) {}
	}
	
	function checkNumbers(fieldName) {
		var count = 0;
		var txtNo = document.getElementById(fieldName).value;
		
		for(var i = 0; i < txtNo.length; i++) {
			if(txtNo.charAt(i) == '.') {
				count = count + 1;
			}
		}
		
		if(count > 0) {
			if(!numbersOnly()) {
				return false;
			}
		} else if(!numbersWithDot()) {
			return false;
		}
		return true;
	}
	
	function calculateAttendanceOnDaysBasis(fieldName) {
		try {
			var newValue = document.getElementById(fieldName).value;
			
			if(newValue == '') {
				document.getElementById(fieldName).value = document.getElementById('oldValue').value;
			} else {
				// calculate attendance days
				var weeklyOffs = eval(document.getElementById('paraFrm_weeklyOffs').value);
				var holidays = eval(document.getElementById('paraFrm_holidays').value);
				var paidLeaves = eval(document.getElementById('paraFrm_paidLeaves').value);
				var unPaidLeaves = eval(document.getElementById('paraFrm_unPaidLeaves').value);
				//var systemPaidLeaves = <%=systemPaidLeaves%>;
				//var systemUnPaidLeaves = eval(document.getElementById('paraFrm_systemUnPaidLeaves').value);
				var totalAttendanceDays = eval(document.getElementById('paraFrm_totalAttendanceDays').value);
				
				var attendanceDays = eval(totalAttendanceDays - (weeklyOffs + holidays + paidLeaves + unPaidLeaves));
				
				// calculate salary days
				var leaveUnplanFlag = document.getElementById('paraFrm_leaveUnplanFlag').value;				
				var penaltiesUnAdjusted = 0;
				if(leaveUnplanFlag == 'true') {
					penaltiesUnAdjusted = eval(document.getElementById('paraFrm_penaltiesUnAdjusted').value);
				}
				
				var leaveUnauthFlag = document.getElementById('paraFrm_leaveUnauthFlag').value;
				var unauthUnAdjusted = 0;
				if(leaveUnauthFlag == 'true') {
					 unauthUnAdjusted = eval(document.getElementById('paraFrm_unauthUnAdjusted').value);
				}
				
				var unPaidLeaves = eval(document.getElementById('paraFrm_unPaidLeaves').value);
				var salaryDays = totalAttendanceDays - (unPaidLeaves + penaltiesUnAdjusted + unauthUnAdjusted);
								
				if(attendanceDays >= 0 && salaryDays >= 0) {
					document.getElementById('paraFrm_attendanceDays').value = attendanceDays;
					document.getElementById('paraFrm_salaryDays').value = salaryDays;
				} else if(attendanceDays < 0) {
					alert(document.getElementById('attendanceDays').innerHTML + ' cannot be less than 0!');
					
					document.getElementById(fieldName).value = document.getElementById('oldValue').value;
					document.getElementById(fieldName).focus();
					return false;
				} else if(salaryDays < 0) {
					alert(document.getElementById('salaryDays').innerHTML + ' cannot be less than 0!');

					document.getElementById(fieldName).value = document.getElementById('oldValue').value;
					document.getElementById(fieldName).focus();
					return false;
				}
			}
		} catch(e) {}
		return true;
	}
	
	function calculateAttendanceOnHoursBasis(fieldForDays, fieldForHrs, fieldLabel) {
		try {
			var newValue = document.getElementById(fieldForDays).value;
			var newHrsValue = '';
			var isTimeValid = 'true'
			if(fieldForHrs != 'NA') {
				if(validateTime(fieldForHrs, fieldLabel)) {
					newHrsValue = document.getElementById(fieldForHrs).value;
				} else {
					isTimeValid = 'false';
				}
			}
			
			if(isTimeValid == 'true') {
				if(newValue == '') {
					document.getElementById(fieldForDays).value = document.getElementById('oldValue').value;
				} else if(newHrsValue == '' && fieldForHrs != 'NA') {
					document.getElementById(fieldForHrs).value = document.getElementById('oldHrsValue').value;
				} else {
					/*
					 * Calculate attendance days
					 */
					// calculate weekly offs in minutes
					var weeklyOffs = eval(document.getElementById('paraFrm_weeklyOffs').value);
					var weeklyOffMins = getMinutesFromDays(weeklyOffs);
					
					// calculate holidays in minutes
					var holidays = eval(document.getElementById('paraFrm_holidays').value);
					var holidayMins = getMinutesFromDays(holidays);
					
					/*var systemPaidLeaves = <%=systemPaidLeaves%>;
					var systemPaidLeaveMins = getMinutesFromDays(systemPaidLeaves);
					
					var systemUnPaidLeaves = eval(document.getElementById('paraFrm_systemUnPaidLeaves').value);
					var systemUnPaidLeaveMins = getMinutesFromDays(systemUnPaidLeaves);
					
					var totalMins = weeklyOffMins + holidayMins + systemPaidLeaveMins + systemUnPaidLeaveMins;*/
					
					// calculate paid leaves in minutes
					var paidLeaves = eval(document.getElementById('paraFrm_paidLeaves').value);
					var paidLeavesHrs = document.getElementById('paraFrm_paidLeavesHrs').value;
					var paidLeavesMins = getMinutesFromDays(paidLeaves);
					paidLeavesMins += getMinutesFromHours(paidLeavesHrs);
					
					// calculate unpaid leaves in minutes
					var unPaidLeaves = eval(document.getElementById('paraFrm_unPaidLeaves').value);
					var unPaidLeavesHrs = document.getElementById('paraFrm_unPaidLeavesHrs').value;
					var unPaidLeavesMins = getMinutesFromDays(unPaidLeaves);
					unPaidLeavesMins += getMinutesFromHours(unPaidLeavesHrs);
					
					// calculate total attendance days in minutes
					var totalAttendanceMins = eval(document.getElementById('paraFrm_totalAttendanceDays').value);
					
					// calculate attendance days in minutes
					var attendanceMins = eval(totalAttendanceMins - (weeklyOffMins + holidayMins + paidLeavesMins + unPaidLeavesMins));
					
					/*
					 * Calculate salary days
					 */
					// calculate unplan penalties in minutes
					var leaveUnplanFlag = document.getElementById('paraFrm_leaveUnplanFlag').value;
					var penaltiesUnAdjustedMins = 0;
					if(leaveUnplanFlag == 'true') {
						var penaltiesUnAdjusted = eval(document.getElementById('paraFrm_penaltiesUnAdjusted').value);
						penaltiesUnAdjustedMins = getMinutesFromDays(penaltiesUnAdjusted);
					}
					
					// calculate unauthorised penalties in minutes
					var leaveUnauthFlag = document.getElementById('paraFrm_leaveUnauthFlag').value;
					var unauthUnAdjustedMins = 0;
					if(leaveUnauthFlag == 'true') {
						var unauthUnAdjusted = eval(document.getElementById('paraFrm_unauthUnAdjusted').value);
						unauthUnAdjustedMins = getMinutesFromDays(unauthUnAdjusted);
					}
					
					// calculate salary dats in minutes
					var salaryMins = eval(totalAttendanceMins - (unPaidLeavesMins + penaltiesUnAdjustedMins + unauthUnAdjustedMins));
					
					if(attendanceMins >= 0 && salaryMins >= 0) {
						// set attendance days and hours
						var atdnDaysAndRemMins = getDaysAndRemainingMins(attendanceMins).split(',');
						document.getElementById('paraFrm_attendanceDays').value = atdnDaysAndRemMins[0];
						document.getElementById('paraFrm_attendanceHrs').value = getHours(eval(atdnDaysAndRemMins[1]));
						
						// set salary days amd hours
						var salDaysAndRemMins = getDaysAndRemainingMins(salaryMins).split(',');
						document.getElementById('paraFrm_salaryDays').value = salDaysAndRemMins[0];
						document.getElementById('paraFrm_salaryHrs').value = getHours(eval(salDaysAndRemMins[1]));
					} else if(attendanceMins < 0) {
						alert(document.getElementById('attendanceDays').innerHTML + ' cannot be less than 0!');
						
						document.getElementById(fieldForDays).value = document.getElementById('oldValue').value;
						document.getElementById(fieldForDays).focus();
						return false;
					} else if(salaryMins < 0) {
						alert(document.getElementById('salaryDays').innerHTML + ' cannot be less than 0!');
						
						document.getElementById(fieldForDays).value = document.getElementById('oldValue').value;
						document.getElementById(fieldForDays).focus();
						return false;
					}
				}
			}			
		} catch(e) {}
		return true;
	}
	
	function callCancel() {
		try {
			window.close();
		} catch(e) {}
	}
	
	function callClose() {
		try {
			var recordNo = document.getElementById('paraFrm_recordNo').value;
			window.opener.document.getElementById('trRecord' + recordNo).style.background = 'white';
		} catch(e) {}
	}
</script>