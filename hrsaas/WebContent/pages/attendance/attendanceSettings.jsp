<%--Bhushan Dasare--%><%--June 21, 2008--%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />

<s:form action="AttendanceSettings" validate="true" id="paraFrm" target="main" theme="simple">
	<s:hidden name="timeFlagType" /><s:hidden name="oneTimeFlag" /><s:hidden name="inOutFlag" /><s:hidden name="divID" /><s:hidden name="aID" />
	
	<table width="100%" class="formbg" style="vertical-align: top;" align="right">
		<tr valign="top">
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td>
							<strong class="text_head"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Attendance Settings</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr valign="top" height="470">
			<td>
				<table width="100%">
					<tr>
						<td>
							<div id="tabnav">
								<ul>
									<li>
										<a id="attSett" href="javascript:callDivLoad('attSett', 'attSettings');" >
											<div align="center"><span>Attendance Settings</span></div>
										</a>
									</li>
									<li>
										<a id="dailySett" href="javascript:callDivLoad('dailySett', 'dailySettings');">
											<div align="center"><span>Daily Attendance Integration Settings</span></div>
										</a>
									</li>
								</ul>
							</div>
						</td>
					</tr>
					<tr valign="top">
						<td width="100%">
							<div id="attSettings" style="vertical-align: top;">
								<table width="100%" align="right">
									<tr>
										<td width="100%">
											<table width="100%">
												<tr>
													<td>
														<s:submit cssClass="save" action="AttendanceSettings_saveAttendanceSetting" theme="simple" 
														value="Save" onclick="return saveAttendanceSettings();" />
														
														<s:submit cssClass="reset" action="AttendanceSettings_resetAttendanceSettings" theme="simple" 
														value="Reset" />
													</td>
													<td align="right"><font color="red">*</font> Indicates Required</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td width="100%">
											<table class="formbg" width="100%">
												<tr>
													<td>
														<strong class="forminnerhead">
															<label id="attnSettings" name="attnSettings" ondblclick="callShowDiv(this);"><%=label.get("attnSettings")%></label>
														</strong>
													</td>
												</tr>
												<tr>
													<td width="40%">
														<label id="levApplWorkflow" name="levApplWorkflow" ondblclick="callShowDiv(this);"><%=label.get("levApplWorkflow")%></label> :
													</td>
													<td><s:checkbox name="leaveApplicationFlag" /></td>
												</tr>		
												<tr>
													<td width="40%">
														<label id="brnHolidayWorkflow" name="brnHolidayWorkflow" ondblclick="callShowDiv(this);"><%=label.get("brnHolidayWorkflow")%></label> :
													</td>
													<td><s:checkbox name="branchHolidayFlag" /></td>
												</tr>		
												<tr>
													<td width="40%">
														<label id="dailyAttnWorkflow" name="dailyAttnWorkflow" ondblclick="callShowDiv(this);"><%=label.get("dailyAttnWorkflow")%></label> :
													</td>
													<td>
														<s:checkbox name="attendanceFlag" /><!-- onclick="startDateDiv();"  -->
														<s:hidden name="attSettingsCode" />
													</td>
												</tr>
												<!-- Enable OT Config start -->
												<!--<tr>
													<td width="40%">
														<label id="otWorkflow" name="otWorkflow" ondblclick="callShowDiv(this);"><%=label.get("otWorkflow")%></label> :
													</td>
													<td><s:checkbox name="otFlag" /></td>
												</tr>
												--><!-- Enable OT Config end -->
												<tr><!-- id="salStartDate" -->
													<td width="40%" style="padding-left: 20">
														<label id="salstartdt" name="salstartdt" ondblclick="callShowDiv(this);"><%=label.get("salstartdt")%></label> :<font color="red" size="2">*</font>
													</td>
													<td>
														<s:textfield name="startDate" size="8" maxlength="2" cssStyle="text-align: right;" 
														onkeypress="return numbersOnly();" />
													</td>
												</tr>		
												<tr id="salaryMonth">
													<td width="40%" style="padding-left: 20">
														<label id="salMonth" name="salMonth" ondblclick="callShowDiv(this);"><%=label.get("salMonth")%></label> :
													</td>
													<td>
														<s:select name="salaryFlag" list="#{'C' : 'Current', 'P' : 'Previous'}" />
													</td>
												</tr>
												<tr>
													<td width="40%">
														<label id="monthlyAttnWorkflow" name="monthlyAttnWorkflow" ondblclick="callShowDiv(this);"><%=label.get("monthlyAttnWorkflow")%></label> :
													</td>
													<td>
														<s:checkbox name="monthlyAttendanceFlag" onclick="showMonthlyDivReset();" />
													</td>
												</tr>
												<tr id="monthDiv">
													<td width="40%" style="padding-left: 20">
														<label id="monthlyPaid" name="monthlyPaid" ondblclick="callShowDiv(this);"><%=label.get("monthlyPaid")%></label> :
													</td>
													<td>
														<s:textfield name="monthlyPaidleave" size="25" readonly="true" 
														cssStyle="background-color: #F2F2F2;" />
														
														<s:hidden name="monthlyPaidleaveCode" /><s:hidden name="leaveCodeHid" />
														<s:hidden name="leaveAbbrHid" />
														
														<input type="button" class="token" value=" >> " 
														onclick="callLeaveCombination('monthlyPaidleaveCode','monthlyPaidleave');" />
													</td>
												</tr>
												<tr id="defaultDays">
												<td width="40%" style="padding-left: 20">
														<label id="defaultAttendanceDays" name="defaultAttendanceDays" ondblclick="callShowDiv(this);"><%=label.get("defaultAttendanceDays")%></label> :
													</td>
													<td>
														<input type="radio" name="defaultDay" id="paraFrm_actualDays" value="A"/>
														Actual Days
														<input type="radio" name="defaultDay" id="paraFrm_zeroDays" value="Z"/>
														Zero Days<s:hidden name="defaultDayHidden"/>
													</td>
												</tr>
												<tr>
													<td width="40%">
														<label id="loginAttendanceFlow" name="loginAttendanceFlow" ondblclick="callShowDiv(this);"><%=label.get("loginAttendanceFlow")%></label> :
													</td>
													<td><s:checkbox name="loginAttendanceFlag" /></td>
												</tr>
												<tr>
													<td width="40%">
														<label id="recPerPage" name="recPerPage" ondblclick="callShowDiv(this);"><%=label.get("recPerPage")%></label> :<font color="red" size="2">*</font>
													</td>
													<td>
														<s:textfield name="recordPerPage" size="8" cssStyle="text-align: right;" 
														onkeypress="return numbersOnly();" />
													</td>
												</tr>		
												<tr>
													<td width="40%">
														<label id="levMgmtYrMth" name="levMgmtYrMth" ondblclick="callShowDiv(this);"><%=label.get("levMgmtYrMth")%></label> :
													</td>
													<td>
														<s:select name="leaveMngtStartMonth" headerKey="-1" headerValue="--Select--" 
														list="#{'1' : 'January', '2' : 'Febuary', '3' : 'March', '4' : 'April', '5' : 'May', 
														'6' : 'June', '7' : 'July', '8' : 'August', '9' : 'September', '10' : 'October', 
														'11' : 'November', '12' : 'December'}" />
													</td>
												</tr> 
												<tr>
													<td width="100%" colspan="2" nowrap="nowrap">
														Include employees joining on or before 
														<s:textfield name="empJoinBefore" size="2" maxLength="2" cssStyle="text-align: right;" 
														onkeypress="return numbersOnly();" onblur="callValidateMonthDay();" /> 
														of the month in the attendance process
													</td> 
												</tr>	
												<tr>
													<td width="100%" colspan="2" nowrap="nowrap">
														Alerts should be shown for last&nbsp;
														<s:textfield name="alertLeave" size="2" maxLength="2" cssStyle="text-align: right;" 
														onkeypress="return numbersOnly();" />&nbsp; 
														days
													</td> 
												</tr>	
												
												<tr>
													<td width="100%" colspan="2" nowrap="nowrap">
														<label id="outdoor.block" name="outdoor.block" ondblclick="callShowDiv(this);"><%=label.get("outdoor.block")%></label> :
														<s:textfield name="outdoorBlockAfterDays" size="2" maxLength="2" cssStyle="text-align: right;" 
														onkeypress="return numbersOnly();" />&nbsp; 
														days
													</td> 
												</tr>	
												
																							
											</table>
										</td>
									</tr>
									<tr>
										<td width="100%">
											<!--table for comp off start-->								
											<table class="formbg" width="100%">
												<tr>
													<td>
														<strong class="forminnerhead">
															<label class="set" id="compConfig" name="compConfig" ondblclick="callShowDiv(this);"><%=label.get("compConfig")%></label>
														</strong>
													</td>
												</tr>
											 
												 <tr>
													<td width="40%">
														<label class="set" id="compOffLevType" name="compOffLevType" ondblclick="callShowDiv(this);"><%=label.get("compOffLevType")%></label>:
													</td>
													<td>
														<s:hidden name="compOffCode" /><s:textfield name="compOffName" size="25" readonly="true" />
														<img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="16"
														onclick="javascript:callsF9(500,325,'AttendanceSettings_f9CompOffAction.action');">
													</td>
												</tr> 
												<tr>
													<td width="40%">
														<label class="set" id="tobeavail" name="tobeavail" ondblclick="callShowDiv(this);"><%=label.get("tobeavail")%></label> :
													</td>
													<td>
														<s:textfield name="compoffdays" size="8" maxlength="2" cssStyle="text-align: right;" 
														onkeypress="return numbersOnly();" />(Days)
													</td>
												</tr>
											</table>
								 			<!--table for comp off end-->
										</td>
									</tr>
									
									
									<!-- Regularization Configuration Section : Added by manish sakpal Begins -->
									<tr>
										<td width="100%">
											<table class="formbg" width="100%">
												<tr>
													<td colspan="2">
														<strong class="forminnerhead">
															<label class="set" id="regularizationConfigLabel" name="regularizationConfigLabel" ondblclick="callShowDiv(this);"><%=label.get("regularizationConfigLabel")%></label> 
														</strong>
													</td>
												</tr>
											 
												 <tr>
													<td width="20%">														 
														<label class="set" id="lateRegularizationLabel" name="lateRegularizationLabel" ondblclick="callShowDiv(this);"><%=label.get("lateRegularizationLabel")%></label> :
													</td>
													<td>
														<s:checkbox name="lateRegularizationCheckBox"></s:checkbox>
													</td>
												</tr>
												
												<tr>
													<td width="20%">
														<label class="set" id="halfDayRegularizationLabel" name="halfDayRegularizationLabel" ondblclick="callShowDiv(this);"><%=label.get("halfDayRegularizationLabel")%></label> :
													</td>
													<td>
														<s:checkbox name="halfDayRegularizationCheckBox"></s:checkbox>
													</td>
												</tr>
												
												<tr>
													<td width="20%">
														<label class="set" id="absentRegularizationLabel" name="absentRegularizationLabel" ondblclick="callShowDiv(this);"><%=label.get("absentRegularizationLabel")%></label> :
													</td>
													<td>
														<s:checkbox name="absentRegularizationCheckBox"></s:checkbox>
													</td>
												</tr>
												
												<tr>
													<td width="20%">
														<label class="set" id="regularTimeRegularizationLabel" name="regularTimeRegularizationLabel" ondblclick="callShowDiv(this);"><%=label.get("regularTimeRegularizationLabel")%></label> :
													</td>
													<td>
														<s:checkbox name="regularTimeRegularizationCheckBox"></s:checkbox>
													</td>
												</tr> 
												
												<tr>
													<td width="20%">
														<label class="set" id="weekyOffHolidayLabel" name="weekyOffHolidayLabel" ondblclick="callShowDiv(this);"><%=label.get("weekyOffHolidayLabel")%></label> :
													</td>
													<td>
														<s:checkbox name="weekyOffHolidayCheckBox"></s:checkbox>
													</td>
												</tr> 
												
												<tr>
													<td width="20%">
														<label class="set" id="showExtraWorkBenifitLabel" name="showExtraWorkBenifitLabel" ondblclick="callShowDiv(this);"><%=label.get("showExtraWorkBenifitLabel")%></label> :
													</td>
													<td>
														<s:checkbox name="showExtraWorkBenifitCheckBox"></s:checkbox>
													</td>
												</tr> 
												
											</table>
										</td>
									</tr>
									<!-- Regularization Configuration Section : Added by manish sakpal Ends -->
									<tr>
										<td width="100%">
											<s:submit cssClass="save" action="AttendanceSettings_saveAttendanceSetting" theme="simple" value="Save" 
											onclick="return saveAttendanceSettings();" />
											
											<s:submit cssClass="reset" action="AttendanceSettings_resetAttendanceSettings" theme="simple" 
											value="Reset" />
										</td>
									</tr>
								</table>
							</div>
							<div id="dailySettings" style="display: none; vertical-align: top;">
								<table width="100%" align="right">
									<tr>
										<td width="100%">
											<table width="100%">
												<tr>
													<td>
														<s:submit cssClass="save" action="AttendanceSettings_saveDailyAttendanceDetails" 
														theme="simple" value="Save" onclick="return saveDailyAttendanceDetails();" />
														
														<s:submit cssClass="reset" action="AttendanceSettings_reset" theme="simple" value="Reset" />
													</td>
													<td align="right"><font color="red">*</font> Indicates Required</td>
												</tr>
											</table>											
										</td>
									</tr>
									<tr>
										<td width="100%">
											<table class="formbg" width="100%">
												<tr>
													<td width="25%" colspan="5">
														<strong class="forminnerhead">
															<label id="intSetting" name="intSetting" ondblclick="callShowDiv(this);"><%=label.get("intSetting")%></label>
														</strong>
													</td>
													<td width="5%"></td><td width="5%"></td><td></td>
												</tr>	
												<tr>
													<td width="25%">
														<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red" size="2">*</font>
													</td>
													<td width="5%" colspan="2">
														<s:hidden name="divCode" />
														
														<s:textfield name="divName" size="40" readonly="true" cssStyle="background-color: #F2F2F2;" />
													</td>
													<td width="5%">	
														<img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="16"
														onclick="javascript:callsF9(500,325,'AttendanceSettings_f9DivisionAction.action');">
													</td>
													<td></td>
												</tr>		
												<tr>
													<td width="25%">
														<label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :<font color="red" size="2">*</font>
													</td>
													<td width="5%" colspan="2">
														<s:hidden name="branchCode" />
														
														<s:textfield name="branchName" size="40" readonly="true" cssStyle="background-color: #F2F2F2;" />
													</td>
													<td width="5%">
														<img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="16"
														onclick="javascript:callsF9(500,325,'AttendanceSettings_f9BranchAction.action');">
													</td>
													<td></td>
												</tr>		
												<tr>
													<td width="25%">
														<label id="reportTypelabel" name="reportTypelabel" ondblclick="callShowDiv(this);"><%=label.get("reportTypelabel")%></label> :<font color="red" size="2">*</font>
													</td>
													<td width="5%">
														<s:select name="reportType" headerKey="" headerValue="--Select--" 
														onchange="onReportTypeChange();" list="#{'t':'TXT', 'x':'XLS', 'c':'CSV'}" />
													</td>
													<td align="right" width="5%">
														<s:submit cssClass="token" action="AttendanceSettings_showRecords" theme="simple" 
														value="Show Record" onclick="return showRecord();" />
													</td>
													<td></td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td width="100%">
											<table class="formbg" width="100%">
												<tr id="sheet">
													<td width="22%">
														<label id="sheetNo" name="sheetNo" ondblclick="callShowDiv(this);"><%=label.get("sheetNo")%></label> :<font color="red" size="2">*</font>
													</td>
													<td>
														<s:textfield name="sheetNum" size="9" cssStyle="text-align: right;" maxlength="5" 
														onblur="return isValueGreatorThanZero(this);" />
													</td>
												</tr>
												<tr>
													<td width="22%">
														<label id="forDate" name="forDate" ondblclick="callShowDiv(this);"><%=label.get("forDate")%></label> :<font color="red" size="2">*</font>
													</td>
													<td nowrap="nowrap">
														<s:select name="ddFormat" headerKey="" headerValue="--Select--"
														list="#{'dd':'DD', 'MM':'MM', 'yyyy':'YYYY', 'yy':'YY', 'MMM':'MMM'}" />
														
														<s:select name="ddSeparator" headerKey="" headerValue="--Select--"
														list="#{'-':'-', '/':'/', ':':':', ' ':'Space'}" />
														
														<s:select name="mmFormat" headerKey="" headerValue="--Select--"
														list="#{'dd':'DD', 'MM':'MM', 'yyyy':'YYYY', 'yy':'YY', 'MMM':'MMM'}" />
														
														<s:select name="mmSeparator" headerKey="" headerValue="--Select--"
														list="#{'-':'-', '/':'/', ':':':', ' ':'Space'}" />
														
														<s:select name="yyFormat" headerKey="" headerValue="--Select--"
														list="#{'dd':'DD', 'MM':'MM', 'yyyy':'YYYY', 'yy':'YY', 'MMM':'MMM'}" />
													</td>
												</tr>
												<tr>
													<td width="22%">
														<label id="forTime" name="forTime" ondblclick="callShowDiv(this);"><%=label.get("forTime")%></label> :<font color="red" size="2">*</font>
													</td>
													<td>
														<s:select name="timeFormat" headerKey="" headerValue="--Select--"
														list="#{'HH:MM:SS':'HH:MM:SS', 'HH:MM':'HH:MM'}" />
													</td>
												</tr>
												<tr>
													<td width="22%">
														<label id="inoutflag" name="inoutflag" ondblclick="callShowDiv(this);"><%=label.get("inoutflag")%></label> :
													</td>
													<td>
														<s:if test="%{inOutFlag}">
															<input type="radio" name="chkQl" value="inOutFlag" checked="checked" 
															onclick="chkRadioButton(this);">
														</s:if>
														<s:elseif test="%{oneTimeFlag}">
															<input type="radio" name="chkQl" value="inOutFlag" onclick="chkRadioButton(this);">
														</s:elseif>
														<s:else>
															<input type="radio" name="chkQl" value="inOutFlag" checked="checked" 
															onclick="chkRadioButton(this);">
														</s:else>
													</td>
												</tr>
												<tr>
													<td width="22%">
														<label id="otFlag" name="otFlag" ondblclick="callShowDiv(this);"><%=label.get("otFlag")%></label> :
													</td>
													<td>
														<s:if test="%{oneTimeFlag}">
															<input type="radio" name="chkQl" value="oneTimeFlag" checked="checked" 
															onclick="chkRadioButton(this);">
														</s:if>
														<s:else>
															<input type="radio" name="chkQl" value="oneTimeFlag" onclick="chkRadioButton(this);">
														</s:else>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td width="100%">
											<table width="100%" class="formbg">
												<tr>
													<td width="20%" align="center" class="formth">
														<label id="colType" name="colType" ondblclick="callShowDiv(this);"><%=label.get("colType")%></label>
													</td>
													<td width="10%" align="center" class="formth">
														<label id="isAvail" name="isAvail" ondblclick="callShowDiv(this);"><%=label.get("isAvail")%></label>
													</td>
													<td width="23%" align="center" class="formth">
														<div id="txt">
															<table width="100%">
																<tr>
																	<td width="20%" class="formth">
																		<label id="frmChar" name="frmChar" ondblclick="callShowDiv(this);"><%=label.get("frmChar")%></label>
																	</td>
																	<td width="20%" class="formth">
																		<label id="toChar" name="toChar" ondblclick="callShowDiv(this);"><%=label.get("toChar")%></label>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="xls">
															<table>
																<tr>
																	<td width="15%" class="formth">
																		<label id="colNo" name="colNo" ondblclick="callShowDiv(this);"><%=label.get("colNo")%></label>
																	</td>
																</tr>
															</table>
														</div>
													</td>
												</tr>		
												<tr>
													<td width="20%" class="sortableTD">
														<label id="empNo" name="empNo" ondblclick="callShowDiv(this);"><%=label.get("empNo")%></label>
													</td>
													<td width="10%" class="sortableTD" align="center">
														<s:checkbox name="empFlag" id="empFlag" 
														onclick="disableFields(this, 'empCharFrom', 'empCharTo', 'empColumn', 'empColumnNoCsv')" />
													</td>
													<td width="23%" class="sortableTD">
														<div id="empTxt">
															<table width="100%">
																<tr>
																	<td width="20%" align="center">
																		<s:if test="%{empFlag}">
																			<s:textfield name="empCharFrom" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="empCharFrom" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																	<td width="20%" align="center">
																		<s:if test="%{empFlag}">
																			<s:textfield name="empCharTo" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="empCharTo" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="empXls">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{empFlag}">
																			<s:textfield name="empColumn" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="empColumn" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="empCsv">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{empFlag}">
																			<s:textfield name="empColumnNoCsv" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="empColumnNoCsv" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>
													</td>
												</tr>		
												<tr>
													<td width="20%" class="sortableTD">
														<label id="date" name="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label>
													</td>
													<td width="10%" class="sortableTD" align="center">
														<s:checkbox name="dateFlag" id="dateFlag" 
														onclick="disableFields(this, 'dateCharFrom', 'dateCharTo', 'dateColumn', 'dateColumnNoCsv')" />
													</td>
													<td width="23%" class="sortableTD">
														<div id="dateTxt">
															<table width="100%">
																<tr>
																	<td width="20%" align="center">
																		<s:if test="%{dateFlag}">
																			<s:textfield name="dateCharFrom" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="dateCharFrom" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																	<td width="20%" align="center">
																		<s:if test="%{dateFlag}">
																			<s:textfield name="dateCharTo" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="dateCharTo" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="dateXls">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{dateFlag}">
																			<s:textfield name="dateColumn" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="dateColumn" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="dateCsv">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{dateFlag}">
																			<s:textfield name="dateColumnNoCsv" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="dateColumnNoCsv" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>
													</td>
												</tr>		
												<tr>
													<td width="20%" class="sortableTD">
														<label id="wHours" name="wHours" ondblclick="callShowDiv(this);"><%=label.get("wHours")%></label>
													</td>
													<td width="10%" class="sortableTD" align="center">
														<s:checkbox name="workHrsFlag" id="workHrsFlag" 
														onclick="disableFields(this, 'workHrsCharFrom', 'workHrsCharTo', 'workHrsColumn', 'workHrsColumnNoCsv')" />
													</td>
													<td width="23%" class="sortableTD">
														<div id="workHrsTxt">
															<table width="100%">
																<tr>
																	<td width="20%" align="center">
																		<s:if test="%{workHrsFlag}">
																			<s:textfield name="workHrsCharFrom" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="workHrsCharFrom" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																	<td width="20%" align="center">
																		<s:if test="%{workHrsFlag}">
																			<s:textfield name="workHrsCharTo" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="workHrsCharTo" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="workHrsXls">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{workHrsFlag}">
																			<s:textfield name="workHrsColumn" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="workHrsColumn" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>			
														<div id="workHrsCsv">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{workHrsFlag}">
																			<s:textfield name="workHrsColumnNoCsv" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="workHrsColumnNoCsv" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>
													</td>
												</tr>		
												<tr>
													<td width="20%" class="sortableTD">
														<label id="shift" name="shift" ondblclick="callShowDiv(this);"><%=label.get("shift")%></label>
													</td>
													<td width="10%" class="sortableTD" align="center">
														<s:checkbox name="shiftFlag" id="shiftFlag" 
														onclick="disableFields(this, 'shiftCharFrom', 'shiftCharTo', 'shiftColumn', 'shiftColumnNoCsv')" />
													</td>
													<td width="23%" class="sortableTD">
														<div id="shiftTxt">
															<table width="100%">
																<tr>
																	<td width="20%" align="center">
																		<s:if test="%{shiftFlag}">
																			<s:textfield name="shiftCharFrom" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="shiftCharFrom" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																	<td width="20%" align="center">
																		<s:if test="%{shiftFlag}">
																			<s:textfield name="shiftCharTo" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="shiftCharTo" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="shiftXls">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{shiftFlag}">
																			<s:textfield name="shiftColumn" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="shiftColumn" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="shiftCsv">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{shiftFlag}">
																			<s:textfield name="shiftColumnNoCsv" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="shiftColumnNoCsv" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>
													</td>
												</tr>		
												<tr id="inTime">
													<td width="20%" class="sortableTD">
														<label id="timeIn" name="timeIn" ondblclick="callShowDiv(this);"><%=label.get("timeIn")%></label>
													</td>
													<td width="10%" class="sortableTD" align="center">
														<s:checkbox name="inTimeFlag" id="inTimeFlag" 
														onclick="disableFields(this, 'inTimeCharFrom', 'inTimeCharTo', 'inTimeColumn', 'inTimeColumnNoCsv')" />
													</td>
													<td width="23%" class="sortableTD">		
														<div id="inTimeTxt">
															<table width="100%">
																<tr>
																	<td width="20%" align="center">
																		<s:if test="%{inTimeFlag}">
																			<s:textfield name="inTimeCharFrom" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="inTimeCharFrom" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																	<td width="20%" align="center">
																		<s:if test="%{inTimeFlag}">
																			<s:textfield name="inTimeCharTo" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="inTimeCharTo" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>			
														<div id="inTimeXls">
															<table>
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{inTimeFlag}">
																			<s:textfield name="inTimeColumn" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="inTimeColumn" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>			
														<div id="inTimeCsv">
															<table>
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{inTimeFlag}">
																			<s:textfield name="inTimeColumnNoCsv" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="inTimeColumnNoCsv" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>
													</td>
												</tr>		
												<tr id="outTime">
													<td width="20%" class="sortableTD">
														<label id="outTime" name="outTime" ondblclick="callShowDiv(this);"><%=label.get("outTime")%></label>
													</td>
													<td width="10%" class="sortableTD" align="center">
														<s:checkbox name="outTimeFlag" id="outTimeFlag" 
														onclick="disableFields(this, 'outTimeCharFrom', 'outTimeCharTo', 'outTimeColumn', 'outTimeColumnNoCsv')" />
													</td>
													<td width="23%" class="sortableTD">		
														<div id="outTimeTxt">
															<table width="100%">
																<tr>
																	<td width="20%" align="center">
																		<s:if test="%{outTimeFlag}">
																			<s:textfield name="outTimeCharFrom" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="outTimeCharFrom" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																	<td width="20%" align="center">
																		<s:if test="%{outTimeFlag}">
																			<s:textfield name="outTimeCharTo" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="outTimeCharTo" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>
														<div id="outTimeXls">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{outTimeFlag}">
																			<s:textfield name="outTimeColumn" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="outTimeColumn" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="outTimeCsv">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{outTimeFlag}">
																			<s:textfield name="outTimeColumnNoCsv" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="outTimeColumnNoCsv" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>
													</td>
												</tr>
												<tr id="oneTime">
													<td width="20%" class="sortableTD">
														<label id="time" name="time" ondblclick="callShowDiv(this);"><%=label.get("time")%></label>
													</td>
													<td width="10%" class="sortableTD" align="center">
														<s:checkbox name="timeFlag" id="timeFlag" 
														onclick="disableFields(this, 'timeCharFrom', 'timeCharTo', 'timeColumn', 'timeColumnNoCsv')" />
													</td>
													<td width="23%" class="sortableTD">		
														<div id="timeTxt">
															<table width="100%">
																<tr>
																	<td width="20%" align="center">
																		<s:if test="%{timeFlag}">
																			<s:textfield name="timeCharFrom" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="timeCharFrom" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																	<td width="20%" align="center">
																		<s:if test="%{timeFlag}">
																			<s:textfield name="timeCharTo" size="10" 
																			cssStyle="text-align: right;" maxlength="10" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="timeCharTo" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="timeXls">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{timeFlag}">
																			<s:textfield name="timeColumn" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="timeColumn" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="timeCsv">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{timeFlag}">
																			<s:textfield name="timeColumnNoCsv" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="timeColumnNoCsv" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>
													</td>
												</tr>		
												<tr id="ioFlag">
													<td width="20%" class="sortableTD">
														<label id="flage" name="flage" ondblclick="callShowDiv(this);"><%=label.get("flage")%></label>
													</td>
													<td width="10%" class="sortableTD" align="center">
														<s:checkbox name="checkFlag" id="checkFlag" 
														onclick="disableFields(this, 'checkCharFrom', 'checkCharTo', 'checkColumn', 'checkColumnNoCsv')" />
													</td>
													<td width="23%" class="sortableTD">		
														<div id="checkTxt">
															<table width="100%">
																<tr>
																	<td width="20%" align="center">
																		<s:if test="%{checkFlag}">
																			<s:textfield name="checkCharFrom" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="checkCharFrom" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																	<td width="20%" align="center">
																		<s:if test="%{checkFlag}">
																			<s:textfield name="checkCharTo" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="checkCharTo" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 't');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="checkXls">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{checkFlag}">
																			<s:textfield name="checkColumn" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="checkColumn" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'x');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>		
														<div id="checkCsv">
															<table width="100%">
																<tr>
																	<td width="15%" align="center">
																		<s:if test="%{checkFlag}">
																			<s:textfield name="checkColumnNoCsv" size="10" 
																			maxlength="10" cssStyle="text-align: right;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:if>
																		<s:else>
																			<s:textfield name="checkColumnNoCsv" size="10" 
																			maxlength="10" readonly="true" 
																			cssStyle="text-align: right; background-color: #F2F2F2;" 
																			onblur="return checkDuplicateValues(this, 'c');" />
																		</s:else>
																	</td>
																</tr>
															</table>
														</div>
													</td>
												</tr>
											</table>
										</td>
									</tr>
									<tr>
										<td width="100%">
											<s:submit cssClass="save" action="AttendanceSettings_saveDailyAttendanceDetails" 
											theme="simple" value="Save" onclick="return saveDailyAttendanceDetails();" />
											<s:submit cssClass="reset" action="AttendanceSettings_reset" theme="simple" value="Reset" />
										</td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	callOnLoad();
	
	function callOnLoad() {
		var divID = document.getElementById('paraFrm_divID').value;
		var aID = document.getElementById('paraFrm_aID').value;
		
		if(divID == '') {
			divID = 'attSettings';
		}
		
		if(aID == '') {
			aID = 'attSett';
		}
		
		document.getElementById('attSettings').style.display = 'none';
		document.getElementById('dailySettings').style.display = 'none';		
		document.getElementById(divID).style.display = '';
		
		document.getElementById('attSett').className = '';
		document.getElementById('dailySett').className = '';
		document.getElementById(aID).className = 'on';
		if(document.getElementById('paraFrm_defaultDayHidden').value=='Z'){
			document.getElementById('paraFrm_zeroDays').checked=true;
		}else{
			document.getElementById('paraFrm_actualDays').checked=true;
		}
	}
	
	function isValueGreatorThanZero(obj) {
		var currentValue = obj.value;
		if(currentValue != '' && currentValue <= 0) {
			alert('Please enter the value greator than zero');
			obj.value = '';
			obj.focus();
			return false;
		}
		return true;
	}
	
	function checkDuplicateValues(obj, reportType) {
		var currentValue = obj.value;
		
		if(currentValue != '') {
			if(isValueGreatorThanZero(obj)) {
				currentValue = parseInt(obj.value);
				if(reportType == 't') {
					var empCharFrom = parseInt(document.getElementById('paraFrm_empCharFrom').value);
					var empCharTo = parseInt(document.getElementById('paraFrm_empCharTo').value);
					var dateCharFrom = parseInt(document.getElementById('paraFrm_dateCharFrom').value);
					var dateCharTo = parseInt(document.getElementById('paraFrm_dateCharTo').value);
					var workHrsCharFrom = parseInt(document.getElementById('paraFrm_workHrsCharFrom').value);
					var workHrsCharTo = parseInt(document.getElementById('paraFrm_workHrsCharTo').value);
					var shiftCharFrom = parseInt(document.getElementById('paraFrm_shiftCharFrom').value);
					var shiftCharTo = parseInt(document.getElementById('paraFrm_shiftCharTo').value);
					var inTimeCharFrom = parseInt(document.getElementById('paraFrm_inTimeCharFrom').value);
					var inTimeCharTo = parseInt(document.getElementById('paraFrm_inTimeCharTo').value);
					var outTimeCharFrom = parseInt(document.getElementById('paraFrm_outTimeCharFrom').value);
					var outTimeCharTo = parseInt(document.getElementById('paraFrm_outTimeCharTo').value);
					var timeCharFrom = parseInt(document.getElementById('paraFrm_timeCharFrom').value);
					var timeCharTo = parseInt(document.getElementById('paraFrm_timeCharTo').value);
					var checkCharFrom = parseInt(document.getElementById('paraFrm_checkCharFrom').value);
					var checkCharTo = parseInt(document.getElementById('paraFrm_checkCharTo').value);
					
					if((obj.id != 'paraFrm_empCharFrom' && obj.id != 'paraFrm_empCharTo') && 
					currentValue >= empCharFrom && currentValue <= empCharTo) {
						alert('This character range is already used for ' + document.getElementById('empNo').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(empCharFrom != '' && empCharTo != '' && empCharFrom >= empCharTo) {
						alert(document.getElementById('frmChar').innerHTML + ' cannot be greater than or equal to ' + 
						document.getElementById('toChar').innerHTML + ' for ' + document.getElementById('empNo').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if((obj.id != 'paraFrm_dateCharFrom' && obj.id != 'paraFrm_dateCharTo') && 
						currentValue >= dateCharFrom && currentValue <= dateCharTo) {
						alert('This character range is already used for ' + document.getElementById('date').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(dateCharFrom != '' && dateCharTo != '' && dateCharFrom >= dateCharTo) {
						alert(document.getElementById('frmChar').innerHTML + ' cannot be greater than or equal to ' + 
						document.getElementById('toChar').innerHTML + ' for ' + document.getElementById('date').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if((obj.id != 'paraFrm_workHrsCharFrom' && obj.id != 'paraFrm_workHrsCharTo') && 
						currentValue >= workHrsCharFrom && currentValue <= workHrsCharTo) {
						alert('This character range is already used for ' + document.getElementById('wHours').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(workHrsCharFrom != '' && workHrsCharTo != '' && workHrsCharFrom >= workHrsCharTo) {
						alert(document.getElementById('frmChar').innerHTML + ' cannot be greater than or equal to ' + 
						document.getElementById('toChar').innerHTML + ' for ' + document.getElementById('wHours').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if((obj.id != 'paraFrm_shiftCharFrom' && obj.id != 'paraFrm_shiftCharTo') && 
						currentValue >= shiftCharFrom && currentValue <= shiftCharTo) {
						alert('This character range is already used for ' + document.getElementById('shift').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(shiftCharFrom != '' && shiftCharTo != '' && shiftCharFrom > shiftCharTo) {
						alert(document.getElementById('frmChar').innerHTML + ' cannot be greater than ' + 
						document.getElementById('toChar').innerHTML + ' for ' + document.getElementById('shift').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if((obj.id != 'paraFrm_inTimeCharFrom' && obj.id != 'paraFrm_inTimeCharTo') && 
						currentValue >= inTimeCharFrom && currentValue <= inTimeCharTo) {
						alert('This character range is already used for ' + document.getElementById('timeIn').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(inTimeCharFrom != '' && inTimeCharTo != '' && inTimeCharFrom >= inTimeCharTo) {
						alert(document.getElementById('frmChar').innerHTML + ' cannot be greater than or equal to ' + 
						document.getElementById('toChar').innerHTML + ' for ' + document.getElementById('timeIn').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if((obj.id != 'paraFrm_outTimeCharFrom' && obj.id != 'paraFrm_outTimeCharTo') && 
						currentValue >= outTimeCharFrom && currentValue <= outTimeCharTo) {
						alert('This character range is already used for ' + document.getElementById('outTime').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(outTimeCharFrom != '' && outTimeCharTo != '' && outTimeCharFrom >= outTimeCharTo) {
						alert(document.getElementById('frmChar').innerHTML + ' cannot be greater than or equal to ' + 
						document.getElementById('toChar').innerHTML + ' for ' + document.getElementById('outTime').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if((obj.id != 'paraFrm_timeCharFrom' && obj.id != 'paraFrm_timeCharTo') && 
						currentValue >= timeCharFrom && currentValue <= timeCharTo) {
						alert('This character range is already used for ' + document.getElementById('time').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(timeCharFrom != '' && timeCharTo != '' && timeCharFrom >= timeCharTo) {
						alert(document.getElementById('frmChar').innerHTML + ' cannot be greater than or equal to ' + 
						document.getElementById('toChar').innerHTML + ' for ' + document.getElementById('time').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if((obj.id != 'paraFrm_checkCharFrom' && obj.id != 'paraFrm_checkCharTo') && 
						currentValue >= checkCharFrom && currentValue <= checkCharTo) {
						alert('This character range is already used for ' + document.getElementById('flage').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(checkCharFrom != '' && checkCharTo != '' && checkCharFrom > checkCharTo) {
						alert(document.getElementById('frmChar').innerHTML + ' cannot be greater than ' + 
						document.getElementById('toChar').innerHTML + ' for ' + document.getElementById('flage').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					}
				} else if(reportType == 'x') {
					var empColumn = parseInt(document.getElementById('paraFrm_empColumn').value);
					var dateColumn = parseInt(document.getElementById('paraFrm_dateColumn').value);
					var workHrsColumn = parseInt(document.getElementById('paraFrm_workHrsColumn').value);
					var shiftColumn = parseInt(document.getElementById('paraFrm_shiftColumn').value);
					var inTimeColumn = parseInt(document.getElementById('paraFrm_inTimeColumn').value);
					var outTimeColumn = parseInt(document.getElementById('paraFrm_outTimeColumn').value);
					var timeColumn = parseInt(document.getElementById('paraFrm_timeColumn').value);
					var checkColumn = parseInt(document.getElementById('paraFrm_checkColumn').value);
					
					if(obj.id != 'paraFrm_empColumn' && currentValue == empColumn) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('empNo').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_dateColumn' && currentValue == dateColumn) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('date').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_workHrsColumn' && currentValue == workHrsColumn) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('wHours').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_shiftColumn' && currentValue == shiftColumn) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('shift').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_inTimeColumn' && currentValue == inTimeColumn) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('timeIn').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_outTimeColumn' && currentValue == outTimeColumn) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('outTime').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_timeColumn' && currentValue == timeColumn) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('time').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_checkColumn' && currentValue == checkColumn) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('flage').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					}
				} else if(reportType == 'c') {
					var empColumnNoCsv = document.getElementById('paraFrm_empColumnNoCsv').value;
					var dateColumnNoCsv = document.getElementById('paraFrm_dateColumnNoCsv').value;
					var workHrsColumnNoCsv = document.getElementById('paraFrm_workHrsColumnNoCsv').value;
					var shiftColumnNoCsv = document.getElementById('paraFrm_shiftColumnNoCsv').value;
					var inTimeColumnNoCsv = document.getElementById('paraFrm_inTimeColumnNoCsv').value;
					var outTimeColumnNoCsv = document.getElementById('paraFrm_outTimeColumnNoCsv').value;
					var timeColumnNoCsv = document.getElementById('paraFrm_timeColumnNoCsv').value;
					var checkColumnNoCsv = document.getElementById('paraFrm_checkColumnNoCsv').value;
					
					if(obj.id != 'paraFrm_empColumnNoCsv' && currentValue == empColumnNoCsv) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('empNo').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_dateColumnNoCsv' && currentValue == dateColumnNoCsv) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('date').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_workHrsColumnNoCsv' && currentValue == workHrsColumnNoCsv) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('wHours').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_shiftColumnNoCsv' && currentValue == shiftColumnNoCsv) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('shift').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_inTimeColumnNoCsv' && currentValue == inTimeColumnNoCsv) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('timeIn').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_outTimeColumnNoCsv' && currentValue == outTimeColumnNoCsv) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('outTime').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_timeColumnNoCsv' && currentValue == timeColumnNoCsv) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('time').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					} else if(obj.id != 'paraFrm_checkColumnNoCsv' && currentValue == checkColumnNoCsv) {
						alert('This ' + document.getElementById('colNo').innerHTML + ' is already used for ' + 
						document.getElementById('flage').innerHTML);
						obj.value = '';
						obj.focus();
						return false;
					}
				}
			}
		}
		return true;
	}

	function callDivLoad(aID, divID) {
		document.getElementById('attSettings').style.display = 'none';
		document.getElementById('dailySettings').style.display = 'none';
		document.getElementById('attSett').className = '';
		document.getElementById('dailySett').className = '';
		document.getElementById(aID).className = 'on';
		document.getElementById(divID).style.display = '';
		document.getElementById('paraFrm_divID').value =  divID;
		document.getElementById('paraFrm_aID').value =  aID;
	}

	function onReportTypeChange() {
		showDiv();
		sheetNumberDiv();
		resetFields();
		resetTimeFields();
	}

	function showDiv(){
		var reportType = document.getElementById('paraFrm_reportType').value;
		var timeFlag = document.getElementById('paraFrm_timeFlagType').value;
		
		if(reportType == '') {
			document.getElementById('txt').style.display = '';
			document.getElementById('xls').style.display = 'none';
			
			document.getElementById('empTxt').style.display = '';			
			document.getElementById('dateTxt').style.display = '';
			document.getElementById('workHrsTxt').style.display = '';
			document.getElementById('shiftTxt').style.display = '';
			
			document.getElementById('empXls').style.display = 'none';
			document.getElementById('empCsv').style.display = 'none';
			
			document.getElementById('dateXls').style.display = 'none';
			document.getElementById('dateCsv').style.display = 'none';
			
			document.getElementById('workHrsXls').style.display = 'none';
			document.getElementById('workHrsCsv').style.display = 'none';
			
			document.getElementById('shiftXls').style.display = 'none';
			document.getElementById('shiftCsv').style.display = 'none';
			
			document.getElementById('timeXls').style.display = 'none';
			document.getElementById('timeCsv').style.display = 'none';
			
			if(timeFlag == '' || timeFlag == 'inOutFlag') {
				document.getElementById('inTime').style.display = '';
				document.getElementById('outTime').style.display = '';
				
				document.getElementById('inTimeTxt').style.display = '';
				document.getElementById('outTimeTxt').style.display = '';
			
				document.getElementById('oneTime').style.display = 'none';
				document.getElementById('ioFlag').style.display = 'none';
				
				document.getElementById('inTimeXls').style.display = 'none';
				document.getElementById('inTimeCsv').style.display = 'none';
				
				document.getElementById('outTimeXls').style.display = 'none';
				document.getElementById('outTimeCsv').style.display = 'none';
			}
			if(timeFlag == 'oneTimeFlag') {
				document.getElementById('oneTime').style.display = '';
				document.getElementById('ioFlag').style.display = '';
				
				document.getElementById('timeTxt').style.display = '';
				document.getElementById('checkTxt').style.display = '';
				
				document.getElementById('inTime').style.display = 'none';
				document.getElementById('outTime').style.display = 'none';
				
				document.getElementById('timeXls').style.display = 'none';
				document.getElementById('timeCsv').style.display = 'none';
				
				document.getElementById('checkXls').style.display = 'none';
				document.getElementById('checkCsv').style.display = 'none';
			}
		}
		
		if(reportType == 't') {
			document.getElementById('xls').style.display = 'none';
			document.getElementById('txt').style.display = '';	
		
			document.getElementById('empTxt').style.display = '';			
			document.getElementById('empXls').style.display = 'none';
			document.getElementById('empCsv').style.display = 'none';
			
			document.getElementById('dateTxt').style.display = '';			
			document.getElementById('dateXls').style.display = 'none';
			document.getElementById('dateCsv').style.display = 'none';
			
			document.getElementById('workHrsTxt').style.display = '';			
			document.getElementById('workHrsXls').style.display = 'none';
			document.getElementById('workHrsCsv').style.display = 'none';
			
			document.getElementById('shiftTxt').style.display = '';			
			document.getElementById('shiftXls').style.display = 'none';
			document.getElementById('shiftCsv').style.display = 'none';
			
			if(timeFlag == '' || timeFlag == 'inOutFlag') {
				document.getElementById('inTimeTxt').style.display = '';				
				document.getElementById('inTimeXls').style.display = 'none';
				document.getElementById('inTimeCsv').style.display = 'none';
				
				document.getElementById('outTimeTxt').style.display = '';
				document.getElementById('outTimeXls').style.display = 'none';
				document.getElementById('outTimeCsv').style.display = 'none';
			}
			if(timeFlag == 'oneTimeFlag') {
				document.getElementById('timeTxt').style.display = '';
				document.getElementById('timeXls').style.display = 'none';
				document.getElementById('timeCsv').style.display = 'none';
				
				document.getElementById('checkTxt').style.display = '';
				document.getElementById('checkXls').style.display = 'none';
				document.getElementById('checkCsv').style.display = 'none';
			}
		}
		if(reportType == 'x') {
			document.getElementById('txt').style.display = 'none';
			document.getElementById('xls').style.display = '';	
		
			document.getElementById('empTxt').style.display = 'none';
			document.getElementById('empXls').style.display = '';
			document.getElementById('empCsv').style.display = 'none';
			
			document.getElementById('dateTxt').style.display = 'none';
			document.getElementById('dateXls').style.display = '';
			document.getElementById('dateCsv').style.display = 'none';
			
			document.getElementById('workHrsTxt').style.display = 'none';
			document.getElementById('workHrsXls').style.display = '';
			document.getElementById('workHrsCsv').style.display = 'none';
			
			document.getElementById('shiftTxt').style.display = 'none';
			document.getElementById('shiftXls').style.display = '';
			document.getElementById('shiftCsv').style.display = 'none';
			
			if(timeFlag == '' || timeFlag == 'inOutFlag') {
				document.getElementById('inTimeTxt').style.display = 'none';
				document.getElementById('inTimeXls').style.display = '';
				document.getElementById('inTimeCsv').style.display = 'none';
				
				document.getElementById('outTimeTxt').style.display = 'none';
				document.getElementById('outTimeXls').style.display = '';
				document.getElementById('outTimeCsv').style.display = 'none';
			}
			if(timeFlag == 'oneTimeFlag') {
				document.getElementById('timeTxt').style.display = 'none';
				document.getElementById('timeXls').style.display = '';
				document.getElementById('timeCsv').style.display = 'none';
				
				document.getElementById('checkTxt').style.display = 'none';
				document.getElementById('checkXls').style.display = '';
				document.getElementById('checkCsv').style.display = 'none';
			}
		}
		if(reportType == 'c') {
			document.getElementById('txt').style.display = 'none';
			document.getElementById('xls').style.display = '';	
		
			document.getElementById('empTxt').style.display = 'none';
			document.getElementById('empXls').style.display = 'none';
			document.getElementById('empCsv').style.display = '';
			
			document.getElementById('dateTxt').style.display = 'none';
			document.getElementById('dateXls').style.display = 'none';			
			document.getElementById('dateCsv').style.display = '';
			
			document.getElementById('workHrsTxt').style.display = 'none';
			document.getElementById('workHrsXls').style.display = 'none';			
			document.getElementById('workHrsCsv').style.display = '';
			
			document.getElementById('shiftTxt').style.display = 'none';
			document.getElementById('shiftXls').style.display = 'none';			
			document.getElementById('shiftCsv').style.display = '';
			
			if(timeFlag == '' || timeFlag == 'inOutFlag') {
				document.getElementById('inTimeTxt').style.display = 'none';
				document.getElementById('inTimeXls').style.display = 'none';				
				document.getElementById('inTimeCsv').style.display = '';
			
				document.getElementById('outTimeTxt').style.display = 'none';
				document.getElementById('outTimeXls').style.display = 'none';				
				document.getElementById('outTimeCsv').style.display = '';
			}
			if(timeFlag == 'oneTimeFlag') {
				document.getElementById('timeTxt').style.display = 'none';
				document.getElementById('timeXls').style.display = 'none';				
				document.getElementById('timeCsv').style.display = '';
				
				document.getElementById('checkTxt').style.display = 'none';
				document.getElementById('checkXls').style.display = 'none';				
				document.getElementById('checkCsv').style.display = '';
			}
		}
	}
	
	function resetTimeFields() {
		document.getElementById('paraFrm_inTimeCharFrom').value = "";
		document.getElementById('paraFrm_inTimeCharFrom').readOnly = 'true';
		document.getElementById('paraFrm_inTimeCharFrom').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_inTimeCharTo').value   = "";
		document.getElementById('paraFrm_inTimeCharTo').readOnly = 'true';
		document.getElementById('paraFrm_inTimeCharTo').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_inTimeColumn').value = "";
		document.getElementById('paraFrm_inTimeColumn').readOnly = 'true';
		document.getElementById('paraFrm_inTimeColumn').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_inTimeColumnNoCsv').value = "";
		document.getElementById('paraFrm_inTimeColumnNoCsv').readOnly = 'true';
		document.getElementById('paraFrm_inTimeColumnNoCsv').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_outTimeCharFrom').value = "";
		document.getElementById('paraFrm_outTimeCharFrom').readOnly = 'true';
		document.getElementById('paraFrm_outTimeCharFrom').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_outTimeCharTo').value   = "";
		document.getElementById('paraFrm_outTimeCharTo').readOnly = 'true';
		document.getElementById('paraFrm_outTimeCharTo').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_outTimeColumn').value = "";
		document.getElementById('paraFrm_outTimeColumn').readOnly = 'true';
		document.getElementById('paraFrm_outTimeColumn').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_outTimeColumnNoCsv').value = "";
		document.getElementById('paraFrm_outTimeColumnNoCsv').readOnly = 'true';
		document.getElementById('paraFrm_outTimeColumnNoCsv').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_timeCharFrom').value = "";
		document.getElementById('paraFrm_timeCharFrom').readOnly = 'true';
		document.getElementById('paraFrm_timeCharFrom').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_timeCharTo').value   = "";
		document.getElementById('paraFrm_timeCharTo').readOnly = 'true';
		document.getElementById('paraFrm_timeCharTo').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_timeColumn').value = "";
		document.getElementById('paraFrm_timeColumn').readOnly = 'true';
		document.getElementById('paraFrm_timeColumn').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_timeColumnNoCsv').value = "";
		document.getElementById('paraFrm_timeColumnNoCsv').readOnly = 'true';
		document.getElementById('paraFrm_timeColumnNoCsv').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_checkCharFrom').value = "";
		document.getElementById('paraFrm_checkCharFrom').readOnly = 'true';
		document.getElementById('paraFrm_checkCharFrom').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_checkCharTo').value   = "";
		document.getElementById('paraFrm_checkCharTo').readOnly = 'true';
		document.getElementById('paraFrm_checkCharTo').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_checkColumn').value = "";
		document.getElementById('paraFrm_checkColumn').readOnly = 'true';
		document.getElementById('paraFrm_checkColumn').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_checkColumnNoCsv').value = "";
		document.getElementById('paraFrm_checkColumnNoCsv').readOnly = 'true';
		document.getElementById('paraFrm_checkColumnNoCsv').style.backgroundColor = '#F2F2F2';
	}
	
	function chkRadioButton(obj) {
		document.getElementById('paraFrm_timeFlagType').value = obj.value;
		showDiv();
		resetTimeFields();
		if(obj.value == 'inOutFlag') {
			document.getElementById('inTime').style.display = '';
			document.getElementById('outTime').style.display = '';
			
			document.getElementById('oneTime').style.display = 'none';
			document.getElementById('ioFlag').style.display = 'none';
			
			document.getElementById('timeFlag').checked = false;
			document.getElementById('checkFlag').checked = false;
			document.getElementById('inOutDiv').style.display = '';
			document.getElementById('oneTimeDiv').style.display = 'none';
		}
		if(obj.value == 'oneTimeFlag') {
			document.getElementById('inTime').style.display = 'none';
			document.getElementById('outTime').style.display = 'none';
			
			document.getElementById('oneTime').style.display = '';
			document.getElementById('ioFlag').style.display = '';
			
			document.getElementById('inTimeFlag').checked = false;
			document.getElementById('outTimeFlag').checked = false;
			document.getElementById('inOutDiv').style.display = 'none';
			document.getElementById('oneTimeDiv').style.display = '';
		}
	}
	
	function resetFields() {
		document.getElementById('empFlag').checked = false;
		document.getElementById('dateFlag').checked = false;
		document.getElementById('inTimeFlag').checked = false;
		document.getElementById('outTimeFlag').checked = false;
		document.getElementById('timeFlag').checked = false;
		document.getElementById('checkFlag').checked = false;
		document.getElementById('workHrsFlag').checked = false;
		document.getElementById('shiftFlag').checked = false;
		
		document.getElementById('paraFrm_ddFormat').value = ""; 
		document.getElementById('paraFrm_ddSeparator').value = ''; 
	  	document.getElementById('paraFrm_mmFormat').value = '';
		document.getElementById('paraFrm_mmSeparator').value = ''; 
	  	document.getElementById('paraFrm_yyFormat').value = '';	  	
	  	document.getElementById('paraFrm_timeFormat').value = '';
				
		document.getElementById('paraFrm_empCharFrom').value = "";
		document.getElementById('paraFrm_empCharFrom').readOnly = 'true';
		document.getElementById('paraFrm_empCharFrom').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_empCharTo').value   = "";
		document.getElementById('paraFrm_empCharTo').readOnly = 'true';
		document.getElementById('paraFrm_empCharTo').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_empColumn').value = "";
		document.getElementById('paraFrm_empColumn').readOnly = 'true';
		document.getElementById('paraFrm_empColumn').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_empColumnNoCsv').value = "";
		document.getElementById('paraFrm_empColumnNoCsv').readOnly = 'true';
		document.getElementById('paraFrm_empColumnNoCsv').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_dateCharFrom').value = "";
		document.getElementById('paraFrm_dateCharFrom').readOnly = 'true';
		document.getElementById('paraFrm_dateCharFrom').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_dateCharTo').value   = "";
		document.getElementById('paraFrm_dateCharTo').readOnly = 'true';
		document.getElementById('paraFrm_dateCharTo').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_dateColumn').value = "";
		document.getElementById('paraFrm_dateColumn').readOnly = 'true';
		document.getElementById('paraFrm_dateColumn').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_dateColumnNoCsv').value = "";
		document.getElementById('paraFrm_dateColumnNoCsv').readOnly = 'true';
		document.getElementById('paraFrm_dateColumnNoCsv').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_workHrsCharFrom').value = "";
		document.getElementById('paraFrm_workHrsCharFrom').readOnly = 'true';
		document.getElementById('paraFrm_workHrsCharFrom').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_workHrsCharTo').value   = "";
		document.getElementById('paraFrm_workHrsCharTo').readOnly = 'true';
		document.getElementById('paraFrm_workHrsCharTo').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_workHrsColumn').value = "";
		document.getElementById('paraFrm_workHrsColumn').readOnly = 'true';
		document.getElementById('paraFrm_workHrsColumn').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_workHrsColumnNoCsv').value = "";
		document.getElementById('paraFrm_workHrsColumnNoCsv').readOnly = 'true';
		document.getElementById('paraFrm_workHrsColumnNoCsv').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_shiftCharFrom').value = "";
		document.getElementById('paraFrm_shiftCharFrom').readOnly = 'true';
		document.getElementById('paraFrm_shiftCharFrom').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_shiftCharTo').value   = "";
		document.getElementById('paraFrm_shiftCharTo').readOnly = 'true';
		document.getElementById('paraFrm_shiftCharTo').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_shiftColumn').value = "";
		document.getElementById('paraFrm_shiftColumn').readOnly = 'true';
		document.getElementById('paraFrm_shiftColumn').style.backgroundColor = '#F2F2F2';
		
		document.getElementById('paraFrm_shiftColumnNoCsv').value = "";
		document.getElementById('paraFrm_shiftColumnNoCsv').readOnly = 'true';
		document.getElementById('paraFrm_shiftColumnNoCsv').style.backgroundColor = '#F2F2F2';
	}
	
	function disableFields(obj, charFrom, charTo, columnNo, columnNoCsv) {
		var reportType = document.getElementById('paraFrm_reportType').value;
		if(reportType == '') {
			alert('Please select ' + document.getElementById('reportTypelabel').innerHTML);
			document.getElementById('paraFrm_reportType').focus();
			obj.checked = false;
		} else {
			if(obj.checked) {			
				if(reportType == 't') {
					document.getElementById('paraFrm_' + charFrom).readOnly = '';
					document.getElementById('paraFrm_' + charTo).readOnly = '';
					
					document.getElementById('paraFrm_' + charFrom).style.backgroundColor = 'white';
					document.getElementById('paraFrm_' + charTo).style.backgroundColor = 'white';
									
					document.getElementById('paraFrm_' + charFrom).focus();
				} else if(reportType == 'x') {
					document.getElementById('paraFrm_' + columnNo).style.backgroundColor = 'white';
					document.getElementById('paraFrm_' + columnNo).readOnly = '';
					document.getElementById('paraFrm_' + columnNo).focus();
				} else if(reportType == 'c') {
					document.getElementById('paraFrm_' + columnNoCsv).style.backgroundColor = 'white';
					document.getElementById('paraFrm_' + columnNoCsv).readOnly = '';
					document.getElementById('paraFrm_' + columnNoCsv).focus();
				}
			} else {
				document.getElementById('paraFrm_' + charFrom).value = '';
				document.getElementById('paraFrm_' + charTo).value = '';
				document.getElementById('paraFrm_' + columnNo).value = '';
				document.getElementById('paraFrm_' + columnNoCsv).value = '';
				
				document.getElementById('paraFrm_' + charFrom).readOnly = 'true';
				document.getElementById('paraFrm_' + charTo).readOnly = 'true';
				document.getElementById('paraFrm_' + columnNo).readOnly = 'true';
				document.getElementById('paraFrm_' + columnNoCsv).readOnly = 'true';
				
				document.getElementById('paraFrm_' + charFrom).style.backgroundColor = '#F2F2F2';
				document.getElementById('paraFrm_' + charTo).style.backgroundColor = '#F2F2F2';
				document.getElementById('paraFrm_' + columnNo).style.backgroundColor = '#F2F2F2';
				document.getElementById('paraFrm_' + columnNoCsv).style.backgroundColor = '#F2F2F2';
			}
		}		
	}
	
	function showRecord() {
		var fieldNames = ["paraFrm_divName", "paraFrm_branchName", "paraFrm_reportType"];
		var labelNames = ["division", "branch", "reportTypelabel"];
		var flag = ["select", "select", "select"];
		
		if(!validateBlank(fieldNames, labelNames, flag)) {
			return false;
		}
	}
	
	function saveAttendanceSettings() {
	
		var date = document.getElementById('paraFrm_startDate').value;
		var recordsPerPage = document.getElementById('paraFrm_recordPerPage').value;
		 
		if(document.getElementById('paraFrm_attendanceFlag').checked) {
			if(date == "") {
				alert("Please enter " + document.getElementById('salstartdt').innerHTML.toLowerCase());
				document.getElementById('paraFrm_startDate').focus();
				return false;
			} else if(eval(date) < 1 || eval(date) > 28) {
				alert("Please enter " + document.getElementById('salstartdt').innerHTML.toLowerCase() + " between 1 to 28");
		 		document.getElementById('paraFrm_startDate').focus();
				return false;
			}
	    }
	    
	    if(recordsPerPage == "") {
	    	alert("Please enter "+document.getElementById('recPerPage').innerHTML.toLowerCase());
	    	document.getElementById('paraFrm_recordPerPage').focus();
	    	return false;
	    }
	    
	    if(eval(recordsPerPage) < 1) {
	    	alert("The value of " + document.getElementById('recPerPage').innerHTML.toLowerCase()+" should be greater than 0");
	    	document.getElementById('paraFrm_recordPerPage').focus();
	    	return false;
	    }
	    
	    if(document.getElementById('paraFrm_monthlyAttendanceFlag').checked) { 
			if(document.getElementById('paraFrm_monthlyPaidleave').value == "") {
			 	alert("Please select " + document.getElementById('monthlyPaid').innerHTML.toLowerCase());
			  	return false;
			}
		}
		 
       var empJoinBefore = document.getElementById('paraFrm_empJoinBefore').value ;
	   if(empJoinBefore>28 || empJoinBefore=="0")
	   {
	    alert("Please enter the employee joining date between 1 and 28.");
	    document.getElementById('paraFrm_empJoinBefore').focus();
	    return false;
	   }
		  
	}
	
	function saveDailyAttendanceDetails() {
		var reportType = document.getElementById('paraFrm_reportType').value;	 
	  	var fieldNames = ["paraFrm_divName", "paraFrm_branchName", "paraFrm_reportType"];
	  	var lableNames = ["division", "branch", "reportTypelabel"];
	  	var flag = ["select", "select", "select"];
	  
	  	if(!validateBlank(fieldNames, lableNames, flag)) {
	  		return false;
	  	}
	  
	  	if(reportType == '') {
			alert("Please select " + document.getElementById('reportTypelabel').innerHTML.toLowerCase());
	  		document.getElementById('paraFrm_reportType').focus();
	  		return false;
	  	}
	  
	  	if(reportType == 'x' && document.getElementById('paraFrm_sheetNum').value == "") {
	  		alert('Please enter sheet number');
	  		document.getElementById('paraFrm_sheetNum').focus();
	  		return false;
	  	}
	  
	  	if(document.getElementById('paraFrm_ddFormat').value == "" && document.getElementById('paraFrm_ddSeparator').value == '' 
	  	&& document.getElementById('paraFrm_mmFormat').value == '' && document.getElementById('paraFrm_mmSeparator').value == '' 
	  	&& document.getElementById('paraFrm_yyFormat').value == '') {
	  		alert("Please select " + document.getElementById('forDate').innerHTML.toLowerCase());
	  		return false;
	  	}
	  
	  	if(document.getElementById('paraFrm_ddFormat').value == "" || document.getElementById('paraFrm_ddSeparator').value == '' 
	  	|| document.getElementById('paraFrm_mmFormat').value == '' || document.getElementById('paraFrm_mmSeparator').value == '' 
	  	|| document.getElementById('paraFrm_yyFormat').value == '') {
	  		alert('Please select date format properly');
	  		return false;
	  	}
	  
		if(document.getElementById('paraFrm_timeFormat').value == "") {
	  		alert("Please select " + document.getElementById('forTime').innerHTML.toLowerCase());
	  		document.getElementById('paraFrm_timeFormat').focus();
	  		return false;
		}
		
		var reportType = document.getElementById('paraFrm_reportType').value;
		
		if(reportType == 't') {
			return isFieldsForTextValid();
		} else if(reportType == 'x') {
			return isFieldsForXlsValid();
		} else if(reportType == 'c') {
			return isFieldsForCsvValid();
		} 
	}
	
	function isFieldsForTextValid() {
		/* Employee No. */
		if(document.getElementById('empFlag').checked && document.getElementById('paraFrm_empCharFrom').value == '') {
			alert('Please enter ' + document.getElementById('frmChar').innerHTML + ' for ' + 
			document.getElementById('empNo').innerHTML);
			document.getElementById('paraFrm_empCharFrom').focus();
			
			return false;
		}
		if(document.getElementById('empFlag').checked && document.getElementById('paraFrm_empCharTo').value == '') {
			alert('Please enter ' + document.getElementById('toChar').innerHTML + ' for ' + 
			document.getElementById('empNo').innerHTML);
			document.getElementById('paraFrm_empCharTo').focus();
			
			return false;
		}
		
		/* Date */
		if(document.getElementById('dateFlag').checked && document.getElementById('paraFrm_dateCharFrom').value == '') {
			alert('Please enter ' + document.getElementById('frmChar').innerHTML + ' for ' + 
			document.getElementById('date').innerHTML);
			document.getElementById('paraFrm_dateCharFrom').focus();
			
			return false;
		}
		if(document.getElementById('dateFlag').checked && document.getElementById('paraFrm_dateCharTo').value == '') {
			alert('Please enter ' + document.getElementById('toChar').innerHTML + ' for ' + 
			document.getElementById('date').innerHTML);
			document.getElementById('paraFrm_dateCharTo').focus();
			
			return false;
		}
		
		/* Work Hours */
		if(document.getElementById('workHrsFlag').checked && document.getElementById('paraFrm_workHrsCharFrom').value == '') {
			alert('Please enter ' + document.getElementById('frmChar').innerHTML + ' for ' + 
			document.getElementById('wHours').innerHTML);
			document.getElementById('paraFrm_workHrsCharFrom').focus();
			
			return false;
		}
		if(document.getElementById('workHrsFlag').checked && document.getElementById('paraFrm_workHrsCharTo').value == '') {
			alert('Please enter ' + document.getElementById('toChar').innerHTML + ' for ' + 
			document.getElementById('wHours').innerHTML);
			document.getElementById('paraFrm_workHrsCharTo').focus();
			
			return false;
		}
		
		/* Shift */
		if(document.getElementById('shiftFlag').checked && document.getElementById('paraFrm_shiftCharFrom').value == '') {
			alert('Please enter ' + document.getElementById('frmChar').innerHTML + ' for ' + 
			document.getElementById('shift').innerHTML);
			document.getElementById('paraFrm_shiftCharFrom').focus();
			
			return false;
		}
		if(document.getElementById('shiftFlag').checked && document.getElementById('paraFrm_shiftCharTo').value == '') {
			alert('Please enter ' + document.getElementById('toChar').innerHTML + ' for ' + 
			document.getElementById('shift').innerHTML);
			document.getElementById('paraFrm_shiftCharTo').focus();
			
			return false;
		}
		
		/* In Time */
		if(document.getElementById('inTimeFlag').checked && document.getElementById('paraFrm_inTimeCharFrom').value == '') {
			alert('Please enter ' + document.getElementById('frmChar').innerHTML + ' for ' + 
			document.getElementById('timeIn').innerHTML);
			document.getElementById('paraFrm_inTimeCharFrom').focus();
			
			return false;
		}
		if(document.getElementById('inTimeFlag').checked && document.getElementById('paraFrm_inTimeCharTo').value == '') {
			alert('Please enter ' + document.getElementById('toChar').innerHTML + ' for ' + 
			document.getElementById('timeIn').innerHTML);
			document.getElementById('paraFrm_inTimeCharTo').focus();
			
			return false;
		}
		
		/* Out Time */
		if(document.getElementById('outTimeFlag').checked && document.getElementById('paraFrm_outTimeCharFrom').value == '') {
			alert('Please enter ' + document.getElementById('frmChar').innerHTML + ' for ' + 
			document.getElementById('outTime').innerHTML);
			document.getElementById('paraFrm_outTimeCharFrom').focus();
			
			return false;
		}
		if(document.getElementById('outTimeFlag').checked && document.getElementById('paraFrm_outTimeCharTo').value == '') {
			alert('Please enter ' + document.getElementById('toChar').innerHTML + ' for ' + 
			document.getElementById('outTime').innerHTML);
			document.getElementById('paraFrm_outTimeCharTo').focus();
			
			return false;
		}
		
		/* One Time */
		if(document.getElementById('timeFlag').checked && document.getElementById('paraFrm_timeCharFrom').value == '') {
			alert('Please enter ' + document.getElementById('frmChar').innerHTML + ' for ' + 
			document.getElementById('time').innerHTML);
			document.getElementById('paraFrm_timeCharFrom').focus();
			
			return false;
		}
		if(document.getElementById('timeFlag').checked && document.getElementById('paraFrm_timeCharTo').value == '') {
			alert('Please enter ' + document.getElementById('toChar').innerHTML + ' for ' + 
			document.getElementById('time').innerHTML);
			document.getElementById('paraFrm_timeCharTo').focus();
			
			return false;
		}
		
		/* IO Flag */
		if(document.getElementById('checkFlag').checked && document.getElementById('paraFrm_checkCharFrom').value == '') {
			alert('Please enter ' + document.getElementById('frmChar').innerHTML + ' for ' + 
			document.getElementById('flage').innerHTML);
			document.getElementById('paraFrm_checkCharFrom').focus();
			
			return false;
		}
		if(document.getElementById('checkFlag').checked && document.getElementById('paraFrm_checkCharTo').value == '') {
			alert('Please enter ' + document.getElementById('toChar').innerHTML + ' for ' + 
			document.getElementById('flage').innerHTML);
			document.getElementById('paraFrm_checkCharTo').focus();
			
			return false;
		}
		
		return true;
	}
	
	function isFieldsForXlsValid() {
		/* Employee No. */
		if(document.getElementById('empFlag').checked == true && document.getElementById('paraFrm_empColumn').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('empNo').innerHTML);
			document.getElementById('paraFrm_empColumn').focus();
			
			return false;
		}
		
		/* Date */
		if(document.getElementById('dateFlag').checked && document.getElementById('paraFrm_dateColumn').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('date').innerHTML);
			document.getElementById('paraFrm_dateColumn').focus();
			
			return false;
		}
		
		/* Work Hours */
		if(document.getElementById('workHrsFlag').checked && document.getElementById('paraFrm_workHrsColumn').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('wHours').innerHTML);
			document.getElementById('paraFrm_workHrsColumn').focus();
			
			return false;
		}
		
		/* Shift */
		if(document.getElementById('shiftFlag').checked && document.getElementById('paraFrm_shiftColumn').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('shift').innerHTML);
			document.getElementById('paraFrm_shiftColumn').focus();
			
			return false;
		}
		
		/* In Time */
		if(document.getElementById('inTimeFlag').checked && document.getElementById('paraFrm_inTimeColumn').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('timeIn').innerHTML);
			document.getElementById('paraFrm_inTimeColumn').focus();
			
			return false;
		}
		
		/* Out Time */
		if(document.getElementById('outTimeFlag').checked && document.getElementById('paraFrm_outTimeColumn').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('outTime').innerHTML);
			document.getElementById('paraFrm_outTimeColumn').focus();
			
			return false;
		}
		
		/* One Time */
		if(document.getElementById('timeFlag').checked && document.getElementById('paraFrm_timeColumn').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('time').innerHTML);
			document.getElementById('paraFrm_timeColumn').focus();
			
			return false;
		}
		
		/* IO Flag */
		if(document.getElementById('checkFlag').checked && document.getElementById('paraFrm_checkColumn').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('flage').innerHTML);
			document.getElementById('paraFrm_checkColumn').focus();
			
			return false;
		}
		
		return true;
	}
	
	function isFieldsForCsvValid() {
		/* Employee No. */
		if(document.getElementById('empFlag').checked && document.getElementById('paraFrm_empColumnNoCsv').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('empNo').innerHTML);
			document.getElementById('paraFrm_empColumnNoCsv').focus();
			
			return false;
		}
		
		/* Date */
		if(document.getElementById('dateFlag').checked && document.getElementById('paraFrm_dateColumnNoCsv').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('date').innerHTML);
			document.getElementById('paraFrm_dateColumnNoCsv').focus();
			
			return false;
		}
		
		/* Work Hours */
		if(document.getElementById('workHrsFlag').checked && document.getElementById('paraFrm_workHrsColumnNoCsv').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('wHours').innerHTML);
			document.getElementById('paraFrm_workHrsColumnNoCsv').focus();
			
			return false;
		}
		
		/* Shift */
		if(document.getElementById('shiftFlag').checked && document.getElementById('paraFrm_shiftColumnNoCsv').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('shift').innerHTML);
			document.getElementById('paraFrm_shiftColumnNoCsv').focus();
			
			return false;
		}
		
		/* In Time */
		if(document.getElementById('inTimeFlag').checked && document.getElementById('paraFrm_inTimeColumnNoCsv').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('timeIn').innerHTML);
			document.getElementById('paraFrm_inTimeColumnNoCsv').focus();
			
			return false;
		}
		
		/* Out Time */
		if(document.getElementById('outTimeFlag').checked && document.getElementById('paraFrm_outTimeColumnNoCsv').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('outTime').innerHTML);
			document.getElementById('paraFrm_outTimeColumnNoCsv').focus();
			
			return false;
		}
		
		/* One Time */
		if(document.getElementById('timeFlag').checked && document.getElementById('paraFrm_timeColumnNoCsv').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('time').innerHTML);
			document.getElementById('paraFrm_timeColumnNoCsv').focus();
			
			return false;
		}
		
		/* IO Flag */
		if(document.getElementById('checkFlag').checked && document.getElementById('paraFrm_checkColumnNoCsv').value == '') {
			alert('Please enter ' + document.getElementById('colNo').innerHTML + ' for ' + 
			document.getElementById('flage').innerHTML);
			document.getElementById('paraFrm_checkColumnNoCsv').focus();
			
			return false;
		}
	
		return true;
	}
	
	function showTimeDetails() {
		document.getElementById('inTime').style.display = '';
		document.getElementById('outTime').style.display = '';
		
		document.getElementById('oneTime').style.display = 'none';
		document.getElementById('ioFlag').style.display = 'none';
		
		var inOutFlag = document.getElementById('paraFrm_inOutFlag').value;
		var oneTimeFlag = document.getElementById('paraFrm_oneTimeFlag').value;
		
		if(inOutFlag == 'true') {
			document.getElementById('paraFrm_timeFlagType').value = 'inOutFlag';
			
			document.getElementById('inTime').style.display = '';
			document.getElementById('outTime').style.display = '';
			
			document.getElementById('oneTime').style.display = 'none';
			document.getElementById('ioFlag').style.display = 'none';
		}
		if(oneTimeFlag == 'true') {
			document.getElementById('paraFrm_timeFlagType').value = 'oneTimeFlag';
			
			document.getElementById('inTime').style.display = 'none';
			document.getElementById('outTime').style.display = 'none';
			
			document.getElementById('oneTime').style.display = '';
			document.getElementById('ioFlag').style.display = '';
		}
	}
	
	function startDateDiv() {
		if(document.getElementById('paraFrm_attendanceFlag').checked) {
			document.getElementById('salStartDate').style.display = '';
			document.getElementById('salaryMonth').style.display = '';
		} else {
			document.getElementById('salStartDate').style.display = 'none';
			document.getElementById('salaryMonth').style.display = 'none';
		}
	}
	
	function sheetNumberDiv() {
		if(document.getElementById('paraFrm_reportType').value == 'x') {
			document.getElementById('sheet').style.display = '';
		} else {
			document.getElementById('paraFrm_sheetNum').value = '';
			document.getElementById('sheet').style.display = 'none';
		}
	}
	
	function callLeaveCombination(setLeaveCode, setLeaveAbbr) {
		document.getElementById('paraFrm_leaveCodeHid').value = setLeaveCode;
		document.getElementById('paraFrm_leaveAbbrHid').value = setLeaveAbbr;
		
		window.open('','new','top=300,left=300,width=350,height=300,scrollbars=yes,status=no,resizable=no');
	 	document.getElementById("paraFrm").target = "new";
		document.getElementById("paraFrm").action = 'AttendanceSettings_leaveCombination.action'; 
	  	document.getElementById("paraFrm").submit();
	  	document.getElementById("paraFrm").target = "main";
	}
	
   function showMonthlyDiv() {
		if(document.getElementById('paraFrm_monthlyAttendanceFlag').checked) {
			document.getElementById('monthDiv').style.display = '';
			document.getElementById('monthDiv').style.display = '';
		} else {
			document.getElementById('monthDiv').style.display = 'none';
			document.getElementById('monthDiv').style.display = 'none';
		}
		
	}
	
	function showMonthlyDivReset() {
	   	document.getElementById('paraFrm_monthlyPaidleave').value = "";
	   	document.getElementById('paraFrm_monthlyPaidleaveCode').value = "";
		
		if(document.getElementById('paraFrm_monthlyAttendanceFlag').checked) { 
			document.getElementById('monthDiv').style.display = '';
			document.getElementById('monthDiv').style.display = '';
		} else {
			document.getElementById('monthDiv').style.display = 'none';
			document.getElementById('monthDiv').style.display = 'none';
		}
		if(document.getElementById('paraFrm_monthlyAttendanceFlag').checked) {
			document.getElementById('defaultDays').style.display = '';
		} else {
			document.getElementById('defaultDays').style.display = 'none';
		}
	}
	
	function callValidateMonthDay() {
		var empJoinBefore = document.getElementById('paraFrm_empJoinBefore').value;
		if(empJoinBefore > 31 || empJoinBefore == "0") {
			alert("Please enter the employee joining date between 1 and 31.");
			document.getElementById('paraFrm_empJoinBefore').focus();
			return false;
		}
	}
	
	startDateDiv();
	showTimeDetails();
	showDiv();
	sheetNumberDiv();
	showMonthlyDiv();
</script>