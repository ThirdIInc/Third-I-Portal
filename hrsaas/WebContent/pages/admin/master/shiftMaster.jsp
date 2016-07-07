<!-- @author: Reeba Joseph @date: 06 OCT 2009 -->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="ShiftMaster" method="post" id="paraFrm" validate="true" target="main" theme="simple">
	<s:hidden name="leaveCodeHid" /><s:hidden name="leaveAbbrHid" /><s:hidden name="shiftID" /><s:hidden name="myPage" />
	<s:hidden name="shiftDtlsShown" />
	
	<table width="100%" class="formbg" align="right">
		<tr>
			<td width="100%">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td valign="bottom" class="txt">
							<strong class="text_head"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Shift Management </strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%">
					<tr>
						<td colspan="4"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%">
							<div align="right"><span class="style2"><font color="red">*</font></span> Indicates Required</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" align="center" id="table1">
				<tr>
					<td>
						<div id="tabnav" style="vertical-align: bottom">
							<ul>
								<li>
									<a href="#" id="menuid1" onclick="showCurrent('menuid1', 'first')"><span>Shift Details</span></a>
								</li>
								<li>
									<a href="#" id="menuid2" onclick="showCurrent('menuid2', 'second')"><span>Shift Exception Details</span></a>
								</li>
								<li>
									<a href="#" id="menuid3" onclick="showCurrent('menuid3', 'third')"><span>Late Coming Monitoring</span></a>
								</li>
								<li>
									<a href="#" id="menuid4" onclick="showCurrent('menuid4', 'forth')"><span>Half Day Monitoring</span></a>
								</li>
								<li>
									<a href="#" id="menuid5" onclick="showCurrent('menuid5', 'fifth')"><span>Weekly Off Monitoring </span></a>
								</li>
								<li>
									<a href="#" id="menuid6" onclick="showCurrent('menuid6', 'sixth')"><span>OT Configuration </span></a>
								</li>
							</ul>
						</div>
					</td>
				</tr>

				<!-- DIV 1 Shift Details Begin -->
				<tr>
					<td width="100%">
						<div id="first">
							<table width="100%" class="formbg">
								<tr>
									<td width="50%"><b>Shift Details</b></td>
								</tr>
								<tr>
									<td width="50%" valign="top">
										<table width="100%" class="formbg">
											<tr>
												<td width="63%">
													<label class="set" name="shiftname" id="shiftname1" ondblclick="callShowDiv(this);"><%=label.get("shiftname")%></label> :<font color="red">*</font>
												</td>
												<td colspan="2">
													<s:textfield theme="simple" name="shiftName" maxlength="20" onkeypress="return allCharacters();" />
												</td>
											</tr>
											
											<tr>
												<td width="63%">
													<label class="set" name="flexiTimeAllowed" id="flexiTimeAllowed" ondblclick="callShowDiv(this);"><%=label.get("flexiTimeAllowed")%></label>
												</td>
												<td colspan="3">
													<s:checkbox name="flexiTimeAllowed" onclick="flexiTimeAllowedFunction();"/>
												</td>
											</tr>
											
											<tr>
												<td width="63%">
													<label class="set" name="workhours" id="workhours" ondblclick="callShowDiv(this);"><%=label.get("workhours")%></label> :<font color="red">*</font>
												</td>
												<td width="7%">
													<s:textfield id="startsHrs" maxlength="5" size="5" name="shiftWorkHrs" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											
											<tr>
												<td width="63%">
													<label class="set" name="markFlexiLateAfterThisTimeLable" id="markFlexiLateAfterThisTimeLable" ondblclick="callShowDiv(this);"><%=label.get("markFlexiLateAfterThisTimeLable")%></label> :<font color="red">*</font>
												</td>
												<td width="7%">
													<s:textfield id="markFlexiLateAfterThisTime" maxlength="5" size="5" name="markFlexiLateAfterThisTime" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											
											
											<tr>
												<td width="63%">
													<label class="set" name="markFlexiHalfDayAfterThisTimeLable" id="markFlexiHalfDayAfterThisTimeLable" ondblclick="callShowDiv(this);"><%=label.get("markFlexiHalfDayAfterThisTimeLable")%></label> :<font color="red">*</font>
												</td>
												<td width="7%">
													<s:textfield id="markFlexiHalfDayAfterThisTime" maxlength="5" size="5" name="markFlexiHalfDayAfterThisTime" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="markFlexiAbsentBeforeThisTimeLable" id="markFlexiAbsentBeforeThisTimeLable" ondblclick="callShowDiv(this);"><%=label.get("markFlexiAbsentBeforeThisTimeLable")%></label> :<font color="red">*</font>
												</td>
												<td width="7%">
													<s:textfield id="markFlexiAbsentBeforeThisTime" maxlength="5" size="5" name="markFlexiAbsentBeforeThisTime" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											
											<tr>
												<td width="63%">
													<label class="set" name="shiftstarttime" id="shiftstarttime1" ondblclick="callShowDiv(this);"><%=label.get("shiftstarttime")%></label> :<font color="red">*</font>
												</td>
												<td width="7%">
													<s:textfield id="startTime" theme="simple" name="shiftStrTime" maxlength="5" size="5" 
													onkeypress="return numbersWithColon();" /> 
													<!-- onfocus="clearText('startTime','hh:mm')" onblur="setText('startTime','hh:mm')" -->
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="latereportingafter" id="latereportingafter" ondblclick="callShowDiv(this);"><%=label.get("latereportingafter")%></label> :
												</td>
												<td width="7%">
													<s:textfield id="reportTimeLate" maxlength="5" size="5" name="reportTimeLate"
													onkeypress="return numbersWithColon();" />
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="halfdayreportingafter" id="halfdayreportingafter" ondblclick="callShowDiv(this);"><%=label.get("halfdayreportingafter")%></label> :
												</td>
												<td width="7%">
													<s:textfield id="reportTimeHalf" maxlength="5" size="5" name="reportTimeHalf" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="breakstart" id="breakstart" ondblclick="callShowDiv(this);"><%=label.get("breakstart")%></label> :
												</td>
												<td width="7%">
													<s:textfield id="lbreakStart" maxlength="5" size="5" name="lunchBreakStart" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="breakend" id="breakend" ondblclick="callShowDiv(this);"><%=label.get("breakend")%></label>
												</td>
												<td width="7%">
													<s:textfield id="lbreakEnd" maxlength="5" size="5" name="lunchBreakEnd" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="halfdayleave" id="halfdayleave" ondblclick="callShowDiv(this);"><%=label.get("halfdayleave")%></label> :
												</td>
												<td width="7%">
													<s:textfield id="offLeftBefore" maxlength="5" size="5" name="offLeftHalfDay" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="earlyleave" id="earlyleave" ondblclick="callShowDiv(this);"><%=label.get("earlyleave")%></label> :
												</td>
												<td width="7%">
													<s:textfield id="offearlyBefore" maxlength="5" size="5" name="offLeftEarly" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="shiftendtime" id="shiftendtime1" ondblclick="callShowDiv(this);"><%=label.get("shiftendtime")%></label> :<font color="red">*</font>
												</td>
												<td width="7%">
													<s:textfield id="endTime" theme="simple" name="shiftEndTime" maxlength="5" size="5" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											
											<tr>
												<td width="63%">
													<label class="set" name="extraworkhours" id="extraworkhours" ondblclick="callShowDiv(this);"><%=label.get("extraworkhours")%></label> :
												</td>
												<td width="7%">
													<s:textfield id="startsfrom" maxlength="5" size="5" name="extraWorkHrs" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											
											<tr>
												<td width="63%">
														<label class="set" name="markAbsentAfterThisTimeLabel" id="markAbsentAfterThisTimeLabel" ondblclick="callShowDiv(this);"><%=label.get("markAbsentAfterThisTimeLabel")%></label> :
												</td>
												<td width="7%">
													<s:textfield maxlength="5" size="5" name="markAbsentAfterThisTime" id="markAbsentAfterThisTime"
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											
											<tr>
												<td width="63%">
													<label class="set" name="nightflag" id="nightflag" ondblclick="callShowDiv(this);"><%=label.get("nightflag")%></label> :
												</td>
												<td colspan="3">
													<s:select theme="simple" name="shiftNtFlag" headerKey="1" 
													list="#{'' : '--Select--', 'Y' : 'Yes', 'N' : 'No'}" />
												</td>
											</tr>
											
										</table>
									</td>
									<td width="50%" valign="top">
										<table width="100%" class="formbg" id="table101">
											<tr>
												<td colspan="3">
													<strong>
														<label class="set" name="allowpersonal" id="allowpersonal" ondblclick="callShowDiv(this);"><%=label.get("allowpersonal")%></label>
													</strong>
												</td>
												<td width="15%"></td>
												<td><s:checkbox name="personalTime" id="perTime1" onclick="personalCheck();" /></td>		
											</tr>
											<tr id="freePerTime">
												<td nowrap="nowrap">Free Personal Time of</td>
												<td>
													<s:textfield name="freePersonalTimeOf" id="freeTime" maxlength="5" size="5" 
													onkeypress="return numbersWithColon();" />
												</td>
												<td nowrap="nowrap">(HH24:MI)</td>
												<td nowrap="nowrap">hrs per</td>
												<td nowrap="nowrap">
													<s:select theme="simple" name="freePersonalTimePer" id="select1" headerKey="1"
													list="#{'' : '--Select--', 'D' : 'Day', 'W' : 'Week', 'M' : 'Month'}" />
												</td>
											</tr>
											<tr id="adjPerTime">
												<td colspan="3">Adjust late marks/half days against personal time</td>
												<td></td>
												<td><s:checkbox name="adjPersonalTime" id="perTime2" /></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td width="50%"><font color="red">Note : -- Time Format should be hh:mm (24 hours)</font></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<!-- DIV 1 Shift Details End -->
				
				
				<!-- DIV 2 Shift Exception Details Begin -->
				<tr>
					<td width="100%">
						<div id="second">
							<table width="100%" class="formbg">
								<tr>
									<td width="100%"><b>Shift Exception Details</b></td>
								</tr>
								<tr id="trWeekDay">
									<td width="100%" valign="top">
										<table width="100%">
											<tr>
												<td width="15%" nowrap="nowrap">
													<label name="lbWeekDay" id="lbWeekDay" ondblclick="callShowDiv(this);"><%=label.get("lbWeekDay")%></label> :
												</td>
												<td width="15%" id="ctrlShow">
													<s:select name="selWeekDay" headerKey="0" headerValue="--Select--" 
													list="#{'1' : 'Sunday', '2' : 'Monday', '3' : 'Tuesday', '4' : 'Wednesday', '5' : 'Thursday', 
													'6' : 'Friday', '7' : 'Saturday'}" />
												</td>
												<td>
													<input type="button" id="ctrlShow" value="  Go  " class="token" onclick="getShiftDetails();" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="trShiftDtls">
									<td width="90%" valign="top">
										<table width="100%">
											<tr>
												<td width="63%">
													<label name="lbWeekDay1" id="lbWeekDay1" ondblclick="callShowDiv(this);"><%=label.get("lbWeekDay1")%></label> :
												</td>
												<td>
													<s:hidden name="weekDay" /><s:property value="dayOfWeek"/>
												</td>
											</tr>
											<tr>
												<td width="63%">
													<label name="shiftstarttime" id="shiftstarttime1" ondblclick="callShowDiv(this);"><%=label.get("shiftstarttime")%></label> :<font color="red">*</font>
												</td>
												<td width="7%">
													<s:textfield id="dtlStartTime" theme="simple" name="dtlShiftStartTime" maxlength="5" size="5" 
													onkeypress="return numbersWithColon();" />
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label name="latereportingafter" id="latereportingafter" ondblclick="callShowDiv(this);"><%=label.get("latereportingafter")%></label> :
												</td>
												<td width="7%">
													<s:textfield id="dtlReportTimeLate" maxlength="5" size="5" name="dtlReportTimeLate" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="halfdayreportingafter" id="halfdayreportingafter" ondblclick="callShowDiv(this);"><%=label.get("halfdayreportingafter")%></label> :
												</td>
												<td width="7%">
													<s:textfield id="dtlReportTimeHalf" maxlength="5" size="5" name="dtlReportTimeHalf" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="breakstart" id="breakstart" ondblclick="callShowDiv(this);"><%=label.get("breakstart")%></label> :
												</td>
												<td width="7%">
													<s:textfield id="dtlLbreakStart" maxlength="5" size="5" name="dtlLunchBreakStart" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="breakend" id="breakend" ondblclick="callShowDiv(this);"><%=label.get("breakend")%></label>
												</td>
												<td width="7%">
													<s:textfield id="dtlLbreakEnd" maxlength="5" size="5" name="dtlLunchBreakEnd" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="halfdayleave" id="halfdayleave" ondblclick="callShowDiv(this);"><%=label.get("halfdayleave")%></label> :
												</td>
												<td width="7%">
													<s:textfield id="dtlOffLeftBefore" maxlength="5" size="5" name="dtlOffLeftHalfDay" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="earlyleave" id="earlyleave" ondblclick="callShowDiv(this);"><%=label.get("earlyleave")%></label> :
												</td>
												<td width="7%">
													<s:textfield id="dtlOffearlyBefore" maxlength="5" size="5" name="dtlOffLeftEarly" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="shiftendtime" id="shiftendtime1" ondblclick="callShowDiv(this);"><%=label.get("shiftendtime")%></label> :<font color="red">*</font>
												</td>
												<td width="7%">
													<s:textfield id="dtlEndTime" theme="simple" name="dtlShiftEndTime" maxlength="5" size="5" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%">
													<label class="set" name="workhours" id="workhours" ondblclick="callShowDiv(this);"><%=label.get("workhours")%></label> :<font color="red">*</font>
												</td>
												<td width="7%">
													<s:textfield id="dtlStartsHrs" maxlength="5" size="5" name="dtlShiftWorkHrs" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td width="63%"">
													<label class="set" name="extraworkhours" id="extraworkhours" ondblclick="callShowDiv(this);"><%=label.get("extraworkhours")%></label> :
												</td>
												<td width="7%">
													<s:textfield id="dtlStartsfrom" maxlength="5" size="5" name="dtlExtraWorkHrs" 
													onkeypress="return numbersWithColon();"/>
												</td>
												<td>(HH24:MI)</td>
											</tr>
											<tr>
												<td>
													<label class="set" name="nightflag" id="nightflag" ondblclick="callShowDiv(this);"><%=label.get("nightflag")%></label> :
												</td>
												<td colspan="3">
													<s:select theme="simple" id="dtlShiftNtFlag" name="dtlShiftNtFlag" headerKey="1" 
													list="#{'' : '--Select--', 'Y' : 'Yes', 'N' : 'No'}" />
												</td>
											</tr>
										</table>
									</td>
									<s:if test="shiftDtlsExists">
										<td valign="top">
											<input type="button" id="ctrlShow" value="Delete" class="delete" onclick="deleteShiftExceptionDetails();" />
										</td>
									</s:if>
									<td valign="top">
										<input type="button" id="ctrlShow" value="Back" class="back" onclick="goBackToWeekDays();" />
									</td>
								</tr>
								<tr id="trNote">
									<td width="100%"><font color="red">Note : -- Time Format should be hh:mm (24 hours)</font></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<!-- DIV 2 Shift Details End -->
				
				

				<!-- DIV 3 Late Coming Monitoring Begin -->
				<tr>
					<td width="100%">
						<div id="third">
							<table width="100%" class="formbg">
								<tr>
									<td colspan="10"><strong>Late Coming Monitoring</strong></td>
								</tr>
								<tr>
									<td>
										<table width="502">
											<tr>
												<td width="412" nowrap="nowrap">Enable late coming Monitoring</td>
												<td width="72"><s:checkbox name="enableMonitor" id="enableMonitor" onclick="enableCheck();" /></td>
											</tr>
										</table>
									</td>
								</tr>		
								<tr id="enableData">
									<td>
										<table>
											<tr>
												<td width="419"><b>Based on number of late marks</b></td>			
												<td width="73">
													<s:checkbox name="bLateMark" id="bLateMark" onclick="LMarkCheck();" />
												</td>
											</tr>
										</table>
									</td>		
								</tr>		
								<tr id="LMarkData">
									<td>
										<table>
											<tr>
												<td></td>							
												<td>Waive-off late marks : </td>
												<td colspan="5">
													<s:textfield id="waiveOffLateMarks" name="waiveOffLateMarks" size="1" maxLength="2" onkeypress="numbersOnly();" />
												</td>
											</tr>
											
											
											<tr>
												<td style="padding-left: 20px">
													<s:textfield size="1" onkeypress="return numbersWithColonandDot();" id="adjustLMCountId"
													cssStyle="text-align: right" maxlength="2" name="adjustLMCount" />
												</td>
												<td nowrap="nowrap">late marks adjusted against</td>
												<td width="12">
													<s:textfield size="1" id="adjustLMLevDaysId" cssStyle="text-align: right" maxlength="10" 
													name="adjustLMLevDays" onkeypress="return numbersWithColonandDot();" />
												</td>
												<td nowrap="nowrap">days in</td>
												<td width="19">
													<s:textfield id="lateCombineLeaveId" name="lateCombineLeave" size="17" readonly="true" 
													cssStyle="background-color: #F2F2F2;" />
													
													<s:hidden name="lateCombineLeaveCode" />
												</td>
												<td width="71">
													<input type="button" class="token" value=" >> " id="lateCombineLeaveBtn" 
													onclick="callLeaveCombination('lateCombineLeaveCode','lateCombineLeaveId');" />
												</td>
												<td width="9" colspan="2"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="basedData">
									<td>
										<table width="503">
											<tr>
												<td width="422"><b>Based on hours of late reporting</b></td>
												<td width="71"><s:checkbox name="lmHrsIsEnabled" id="BLReport" onclick="LReportCheck();" /></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="LReportData">
									<td>
										<table width="505">
											<tr>
												<td colspan="4" style="padding-left: 20px" nowrap="nowrap">Late hour regularization required</td>
												<td width="72" nowrap="nowrap">
													<s:checkbox name="lmReglIsEnabled" id="LateHours" onclick="LateHoursCheck();" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="LateHoursData">
									<td>
										<table width="505">
											<tr>
												<td colspan="4" style="padding-left: 20px" nowrap="nowrap">Deduct regularized late marks from</td>
												<td width="97">
													<s:textfield name="lateMarksLeave" size="25" id="lateMarksLeaveId" readonly="true" 
													cssStyle="background-color: #F2F2F2;" />
													
													<s:hidden name="lateMarksLeaveCode" />
												</td>
												<td width="75">
													<input type="button" class="token" value=" >> " id="lateMarksLeaveBtn" 
													onclick="callLeaveCombination('lateMarksLeaveCode','lateMarksLeaveId');" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="LateHoursData1">
									<td>
										<table width="505">
											<tr>
												<td colspan="4" style="padding-left: 20px" nowrap="nowrap">Deduct non-regularized late marks from</td>
												<td width="98">
													<s:textfield name="nonRegLateMarksLeave" size="25" id="nonRegLateMarksLeaveId" readonly="true" 
													cssStyle="background-color: #F2F2F2;" />
													
													<s:hidden name="nonRegLateMarksLeaveCode" />
												</td>
												<td width="77">
													<input type="button" class="token" value=" >> " id="nonRegLateMarksLeaveBtn"
													onclick="callLeaveCombination('nonRegLateMarksLeaveCode','nonRegLateMarksLeaveId');" />
												</td>
											</tr>
											<tr id="LReportSlabData">
												<td colspan="4" style="padding-left: 20px" nowrap="nowrap">Deduct late hours in slabs of</td>
												<td>
													<s:textfield size="2" id="dedLmInSlabsOfId"  maxlength="2" cssStyle="text-align: right"
													name="dedLmInSlabsOf" onkeypress="return numbersWithColonandDot();" />
												</td>
												<td>minutes</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<!-- DIV 3 Late Coming Monitoring End -->


				<!-- DIV 4 Half Day Monitoring Begin -->
				<tr>
					<td width="100%">
						<div id="forth">
							<table width="100%" class="formbg">
								<tr>
									<td colspan="5"><strong>Half Day Monitoring</strong></td>
								</tr>
								<tr>
									<td>
										<table width="508">
											<tr>
												<td width="426" nowrap="nowrap">Enable Half-day Monitoring</td>
												<td width="72"><s:checkbox name="enableHalfMonitor" id="halfId" onclick="halfCheck();" /></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="halfDayData">
									<td>
										<table width="510">
											<tr>
												<td width="255" style="padding-left: 20px" nowrap="nowrap">Deduct unauthorized half-day from</td>
												<td width="146">
													<s:textfield name="regHalfDayLeave" size="25" id="regHalfDayLeaveId" readonly="true" 
													cssStyle="background-color: #F2F2F2;" />
													
													<s:hidden name="regHalfDayLeaveCode" />
												</td>
												<td width="85">
													<input type="button" class="token" value=" >> " id="regHalfDayLeaveBtn" 
													onclick="callLeaveCombination('regHalfDayLeaveCode','regHalfDayLeaveId');" />
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<!-- DIV 4 Half Day Monitoring End -->

				<!-- DIV 5 Weekly Off Monitoring Begin -->
				<tr>
					<td width="100%">
						<div id="fifth">
							<table class="formbg" width="100%">
								<tr>
									<td width="100%">
										<table width="100%">
											<tr>
												<td width="100%"><strong>Weekly Offs Monitoring</strong></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td width="100%">
										<table width="100%" id="table36">
											<tr>
												<td width="100%">
													<table width="100%" id="table36">
														<tr>
															<td width="33%"><b>Fixed Weekly Offs :</b></td>
															<td width="67%">
																<s:checkbox name="fixedWeekOff" id="weekId" onclick="weekCheck();" />
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr id="weekOffId">
												<td width="100%">
													<table width="100%" class="formbg">
														<tr valign="middle">
															<td width="8%" align="left" class="formth">Week</td>
															<td width="8%" valign="middle" class="formth">
																Sunday
																<span class="border2">
																	<br>
																	<input id="week7" value="1" type="checkbox" name="week72" 
																	onClick="callCheck(this, 'sun');">
																</span>
															</td>
															<td width="8%" align="middle" class="formth">
																Monday
																<span class="border2">
																	<br>
																	<input id="week72" value="1" type="checkbox" name="week73" 
																	onClick="callCheck(this, 'mon');">
																</span>
															</td>
															<td width="8%" align="middle" class="formth">
																Tuesday
																<span class="border2">
																	<br>
																	<input id="week73" value="1" type="checkbox" name="week74" 
																	onClick="callCheck(this, 'tue');">
																</span>
															</td>
															<td width="8%" align="middle" nowrap="nowrap" class="formth">
																Wednesday
																<span class="border2">
																	<br>
																	<input id="week74" value="1" type="checkbox" name="week75" 
																	onClick="callCheck(this, 'wed');">
																</span>
															</td>
															<td width="8%" align="middle" class="formth">
																Thursday
																<span class="border2">
																	<br>
																	<input id="week75" value="1" type="checkbox" name="week76" 
																	onClick="callCheck(this, 'thu');">
																</span>
															</td>
															<td width="8%" align="middle" class="formth">
																Friday
																<span class="border2">
																	<br>
																	<input id="week76" value="1" type="checkbox" name="week77" 
																	onClick="callCheck(this, 'fri');">
																</span>
															</td>
															<td width="8%" align="middle" class="formth">
																Saturday
																<span class="border2">
																	<br>
																	<input id="week77" value="1" type="checkbox" name="week78" 
																	onClick="callCheck(this, 'sat');">
																</span>
															</td>
														</tr>
														<%! int rows = 0; %>
														<% 	String[][] holidays = (String[][])request.getAttribute("weekDays");
															for(int count = 1; count < 7; count++) {
																rows = count;
														%>
														<tr>
															<td width="8%" class="sortableTD">Week <%=" " + count%></td>				
															<td width="8%" align="center" class="sortableTD">
																<input type="checkbox" name='week<%=count%>' id="sun<%=count%>" value="1" />
															</td>
															<td width="8%" align="center" class="sortableTD">
																<input type="checkbox" name="week<%=count%>" id="mon<%=count%>" value="2" />
															</td>
															<td width="8%" align="center" class="sortableTD">
																<input type="checkbox" name="week<%=count%>" id="tue<%=count%>" value="3" />
															</td>
															<td width="8%" align="center" class="sortableTD">
																<input type="checkbox" name="week<%=count%>" id="wed<%=count%>" value="4" />
															</td>
															<td width="8%" align="center" class="sortableTD">
																<input type="checkbox" name="week<%=count%>" id="thu<%=count%>" value="5" />
															</td>
															<td width="8%" align="center" class="sortableTD">
																<input type="checkbox" name="week<%=count%>" id="fri<%=count%>" value="6" />
															</td>
															<td width="8%" align="center" class="sortableTD">
																<input type="checkbox" name="week<%=count%>" id="sat<%=count%>" value="7" />
															</td>
															<%	if(holidays != null) {
																	if(holidays[count - 1][0].equals("Y")) {
															%>
															<script>
																var idRows = 'sun' + '<%=rows%>';
																document.getElementById(idRows).checked = true;
															</script>
															<%	}
																if(holidays[count - 1][1].equals("Y")) {
															%>
															<script>
																var idRows='mon' + '<%=rows%>';
																document.getElementById(idRows).checked = true;
															</script>
															<%	}
																if(holidays[count - 1][2].equals("Y")) {
															%>
															<script>
																var idRows='tue' + '<%=rows%>';
																document.getElementById(idRows).checked = true;
															</script>
															<%	}
																if(holidays[count - 1][3].equals("Y")) {
															%>
															<script>
																var idRows = 'wed' + '<%=rows%>';
																document.getElementById(idRows).checked = true;
															</script>
															<%	}
																if(holidays[count - 1][4].equals("Y")) {
															%>
															<script>
																var idRows = 'thu' + '<%=rows%>';
																document.getElementById(idRows).checked = true;
															</script>
															<%	}
																if(holidays[count - 1][5].equals("Y")) {
															%>
															<script>
																var idRows = 'fri' + '<%=rows%>';
																document.getElementById(idRows).checked = true;
															</script>
															<%	}
																if(holidays[count - 1][6].equals("Y")) {
															%>
															<script>
																var idRows = 'sat'+'<%=rows%>';
																document.getElementById(idRows).checked = true;
															</script>
															<%	}} %>
														</tr>
														<%	} %>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<!-- DIV 5 Weekly Off Monitoring End -->
				
				<!-- OT Configuration  Begins -->
				<tr>
					<td width="100%">
						<div id="sixth">
							<table width="100%" class="formbg">
								<tr>
									<td colspan="5">
										<strong>
											<label name="otConfigDetails" id="otConfigDetails" ondblclick="callShowDiv(this);"><%=label.get("otConfigDetails")%></label>
										</strong>
									</td>
								</tr>
								
								<tr>
									<td colspan="5">
										<table width="100%">
											<tr>
												<td width="20%" nowrap="nowrap">
													<label name="enableOtCalculationWorkflow" id="enableOtCalculationWorkflow" ondblclick="callShowDiv(this);"><%=label.get("enableOtCalculationWorkflow")%></label> 
												</td>
												<td width="70%">
													<s:checkbox name="enableOTConfigWorkflow" id="enableOTConfigWorkflow" onclick="callFormulaSection();"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								
								<tr>
									<td colspan="5">
										<table width="100%">
											<tr>
												<td nowrap="nowrap">
													<label name="overtimeDurationCriteria" id="overtimeDurationCriteria" ondblclick="callShowDiv(this);"><%=label.get("overtimeDurationCriteria")%></label> 
												</td>
											</tr>
											
											<tr>
												<td nowrap="nowrap">
													<s:checkbox name="actualHoursWorkedOT" onclick="selectActualHoursWorkedCheckBox();"/>
													<label name="actualHoursWorked" id="actualHoursWorked" ondblclick="callShowDiv(this);"><%=label.get("actualHoursWorked")%></label> 
												</td>
											</tr>
											
											<tr>
												<td nowrap="nowrap">
													<s:checkbox name="actualOutTimeOT" onclick="selectActualOutTimeCheckBox();"/>
													<label name="actualOutTime" id="actualOutTime" ondblclick="callShowDiv(this);"><%=label.get("actualOutTime")%></label>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								 
								<tr id="formulaForOTConfiguration">
									<td>
										<table width="100%">
											<tr>
												<td nowrap="nowrap" width="20%">
													<label name="regularOtHourlyRateFormula" id="regularOtHourlyRateFormula" ondblclick="callShowDiv(this);"><%=label.get("regularOtHourlyRateFormula")%></label> :
												</td>
												<td width="20%">
													<s:textfield name="regularOtHourlyRateFormulaOT" readonly="true" cssStyle="background-color: #F2F2F2;"/>
												</td>
												<td width="60%">
													<input type="button" class="token" value="Formula" 
													onclick="callFormulaBuilder('paraFrm_regularOtHourlyRateFormulaOT');"/>	
												</td>
											</tr>
											
											<tr>
												<td nowrap="nowrap" width="20%">
													<label name="weeklyOffHolidayotHourlyRateFormula" id="weeklyOffHolidayotHourlyRateFormula" ondblclick="callShowDiv(this);"><%=label.get("weeklyOffHolidayotHourlyRateFormula")%></label> :
												</td>
												<td width="20%">
													<s:textfield name="weeklyOffHolidayOtHourlyFormulaOT" readonly="true" cssStyle="background-color: #F2F2F2;"/>
												</td>
												<td width="60%">
													<input type="button" class="token" value="Formula" onclick="callFormulaBuilder('paraFrm_weeklyOffHolidayOtHourlyFormulaOT');"/>	
												</td>
											</tr>
											
											<tr>
												<td nowrap="nowrap" width="20%">
													<label name="doubleOtHourlyRateFormula" id="doubleOtHourlyRateFormula" ondblclick="callShowDiv(this);"><%=label.get("doubleOtHourlyRateFormula")%></label> :
												</td>
												<td width="20%">
													<s:textfield name="doubleOtHourlyFormulaOT" readonly="true" cssStyle="background-color: #F2F2F2;"/>
												</td>
												<td width="60%">
													<input type="button" class="token" value="Formula" onclick="callFormulaBuilder('paraFrm_doubleOtHourlyFormulaOT');"/>	
												</td>
											</tr>
										</table>
									</td>
								</tr>
								 
							</table>
						</div>
					</td>
				</tr>
				<!-- OT Configuration  Ends -->
				
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%">
					<tr>
						<td colspan="4"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="22%"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	onloadCall();
	function onloadCall() {
		var shiftDtlsShown = document.getElementById('paraFrm_shiftDtlsShown').value;		
		if(shiftDtlsShown == 'true') {
			document.getElementById('first').style.display = 'none';
			document.getElementById('second').style.display = 'block';
			document.getElementById('menuid2').className = 'on';
			
			document.getElementById('trWeekDay').style.display = 'none';
			document.getElementById('trShiftDtls').style.display = 'block';
			document.getElementById('trNote').style.display = 'block';
		} else {
			document.getElementById('first').style.display = 'block';
			document.getElementById('menuid1').className = 'on';
			document.getElementById('second').style.display = 'none';
			
			document.getElementById('trWeekDay').style.display = 'block';
			document.getElementById('trShiftDtls').style.display = 'none';
			document.getElementById('trNote').style.display = 'none';
		}
		document.getElementById('third').style.display = 'none';
		document.getElementById('forth').style.display = 'none';
		document.getElementById('fifth').style.display = 'none'; 
		document.getElementById('sixth').style.display = 'none'; 
		document.getElementById('menuid3').className = 'li';
		document.getElementById('menuid4').className = 'li';
		document.getElementById('menuid5').className = 'li';
		document.getElementById('menuid6').className = 'li';
		
		try {
			if(!document.getElementById('perTime1').checked) {
				document.getElementById('freeTime').disabled = true;
				document.getElementById('freeTime').style.backgroundColor = '#F2F2F2';
				document.getElementById('select1').disabled = true;
				document.getElementById('perTime2').disabled = true;
			}			
			if(!document.getElementById('enableMonitor').checked) {
				document.getElementById('bLateMark').disabled = true;
				document.getElementById('BLReport').disabled = true;				
				if(!document.getElementById('bLateMark').checked) {
					document.getElementById('adjustLMCountId').disabled = true;
					document.getElementById('adjustLMCountId').style.backgroundColor = '#F2F2F2';
					document.getElementById('adjustLMLevDaysId').disabled = true;
					document.getElementById('adjustLMLevDaysId').style.backgroundColor = '#F2F2F2';
					document.getElementById('lateCombineLeaveId').disabled = true;
					document.getElementById('lateCombineLeaveBtn').disabled = true;
				}				
				if(!document.getElementById('BLReport').checked) {
					document.getElementById('LateHours').disabled = true;
					document.getElementById('dedLmInSlabsOfId').disabled = true;
					document.getElementById('dedLmInSlabsOfId').style.backgroundColor = '#F2F2F2';
					document.getElementById('lateMarksLeaveId').disabled = true;
					document.getElementById('lateMarksLeaveBtn').disabled = true;
					document.getElementById('nonRegLateMarksLeaveId').disabled = true;
					document.getElementById('nonRegLateMarksLeaveBtn').disabled = true;
				}
			}			
			if(!document.getElementById('halfId').checked) {
				document.getElementById('regHalfDayLeaveId').disabled = true;
				document.getElementById('regHalfDayLeaveBtn').disabled = true;				
			}
		} catch(e) {
			alert(e);
		}		
		try {
			if(!document.getElementById('weekId').checked) {
				document.getElementById('week7').disabled = true;
				document.getElementById('week72').disabled = true;
				document.getElementById('week73').disabled = true;
				document.getElementById('week74').disabled = true;
				document.getElementById('week75').disabled = true;
				document.getElementById('week76').disabled = true;
				document.getElementById('week77').disabled = true;
				
				for(var i = 1; i < 7; i++) {
					document.getElementById('sun' + i).disabled = true;
					document.getElementById('mon' + i).disabled = true;
					document.getElementById('tue' + i).disabled = true;
					document.getElementById('wed' + i).disabled = true;
					document.getElementById('thu' + i).disabled = true;
					document.getElementById('fri' + i).disabled = true;
					document.getElementById('sat' + i).disabled = true;
				}
			}
		} catch(e) {
			alert(e);
		}		
		flexiTimeAllowedFunction();
		callFormulaSection();
	}
 
	
	function flexiTimeAllowedFunction(){
		try{
			if(document.getElementById('paraFrm_flexiTimeAllowed').checked){
			document.getElementById('startTime').readOnly='true';	
			document.getElementById('startTime').style.background='#F2F2F2';
			document.getElementById('startTime').value = '';
			
			document.getElementById('reportTimeLate').readOnly='true';	
			document.getElementById('reportTimeLate').style.background='#F2F2F2';
			document.getElementById('reportTimeLate').value = '';
			
			document.getElementById('reportTimeHalf').readOnly='true';	
			document.getElementById('reportTimeHalf').style.background='#F2F2F2';
			document.getElementById('reportTimeHalf').value = '';
			
			document.getElementById('lbreakStart').readOnly='true';	
			document.getElementById('lbreakStart').style.background='#F2F2F2';
			document.getElementById('lbreakStart').value = '';
			
			document.getElementById('lbreakEnd').readOnly='true';	
			document.getElementById('lbreakEnd').style.background='#F2F2F2';
			document.getElementById('lbreakEnd').value = '';
			
			document.getElementById('offLeftBefore').readOnly='true';	
			document.getElementById('offLeftBefore').style.background='#F2F2F2';
			document.getElementById('offLeftBefore').value = '';
			
			document.getElementById('offearlyBefore').readOnly='true';	
			document.getElementById('offearlyBefore').style.background='#F2F2F2';
			document.getElementById('offearlyBefore').value = '';
			
			document.getElementById('endTime').readOnly='true';	
			document.getElementById('endTime').style.background='#F2F2F2';
			document.getElementById('endTime').value = '';
			
			document.getElementById('startsfrom').readOnly='true';	
			document.getElementById('startsfrom').style.background='#F2F2F2';
			document.getElementById('startsfrom').value = '';
			
			document.getElementById('startsfrom').readOnly='true';	
			document.getElementById('startsfrom').style.background='#F2F2F2';
			document.getElementById('startsfrom').value = '';
			
			document.getElementById('markAbsentAfterThisTime').readOnly='true';	
			document.getElementById('markAbsentAfterThisTime').style.background='#F2F2F2';
			document.getElementById('markAbsentAfterThisTime').value='';
			
			document.getElementById('paraFrm_shiftNtFlag').disabled = true;
			document.getElementById('paraFrm_shiftNtFlag').value = '';	
						
			document.getElementById('markFlexiLateAfterThisTime').readOnly='';	
			document.getElementById('markFlexiLateAfterThisTime').style.background='white';
			
			document.getElementById('markFlexiHalfDayAfterThisTime').readOnly='';
			document.getElementById('markFlexiHalfDayAfterThisTime').style.background='white';
			
			document.getElementById('markFlexiAbsentBeforeThisTime').readOnly='';			
			document.getElementById('markFlexiAbsentBeforeThisTime').style.backgroud='white';		
			
		}else{
			document.getElementById('startTime').readOnly='';	
			document.getElementById('startTime').style.background='white';
			
			document.getElementById('reportTimeLate').readOnly='';	
			document.getElementById('reportTimeLate').style.background='white';
			
			document.getElementById('reportTimeHalf').readOnly='';	
			document.getElementById('reportTimeHalf').style.background='white';
			
			document.getElementById('lbreakStart').readOnly='';	
			document.getElementById('lbreakStart').style.background='white';
			
			document.getElementById('lbreakEnd').readOnly='';	
			document.getElementById('lbreakEnd').style.background='white';
			
			document.getElementById('offLeftBefore').readOnly='';	
			document.getElementById('offLeftBefore').style.background='white';
			
			document.getElementById('offearlyBefore').readOnly='';	
			document.getElementById('offearlyBefore').style.background='white';
			
			document.getElementById('endTime').readOnly='';	
			document.getElementById('endTime').style.background='white';
			
			document.getElementById('startsfrom').readOnly='';	
			document.getElementById('startsfrom').style.background='white';
			
			document.getElementById('startsfrom').readOnly='';	
			document.getElementById('startsfrom').style.background='white';
			
			document.getElementById('markAbsentAfterThisTime').readOnly='';	
			document.getElementById('markAbsentAfterThisTime').style.background='white';
			
			document.getElementById('paraFrm_shiftNtFlag').disabled = false;	
			//document.getElementById('paraFrm_shiftNtFlag').value = '';	
			
			document.getElementById('markFlexiLateAfterThisTime').readOnly='true';	
			document.getElementById('markFlexiLateAfterThisTime').style.background='#F2F2F2';
			document.getElementById('markFlexiLateAfterThisTime').value = '';
			document.getElementById('markFlexiHalfDayAfterThisTime').readOnly='true';
			document.getElementById('markFlexiHalfDayAfterThisTime').style.background='#F2F2F2';
			document.getElementById('markFlexiHalfDayAfterThisTime').value = '';

			document.getElementById('markFlexiAbsentBeforeThisTime').readOnly='true';
			document.getElementById('markFlexiAbsentBeforeThisTime').style.background='#F2F2F2';
			document.getElementById('markFlexiAbsentBeforeThisTime').value = '';
				
		}
		}catch(e){
			alert("exception : "+e);
		}
	}
	
	function showCurrent(menuId, id) {
		try {
			document.getElementById('first').style.display = 'none';
			document.getElementById('second').style.display = 'none';
			document.getElementById('third').style.display = 'none';
			document.getElementById('forth').style.display = 'none';
			document.getElementById('fifth').style.display = 'none';
			document.getElementById('sixth').style.display = 'none';
			document.getElementById('menuid1').className = 'li';
			document.getElementById('menuid2').className = 'li';
			document.getElementById('menuid3').className = 'li';
			document.getElementById('menuid4').className = 'li';
			document.getElementById('menuid5').className = 'li';
			document.getElementById('menuid6').className = 'li';			
			document.getElementById(menuId).className = 'on';
			document.getElementById(id).style.display = 'block';
		} catch(e) {
			alert(e);
		}
	}
		
	function personalCheck() {
		if(document.getElementById('perTime1').checked) {
			document.getElementById('freeTime').disabled = false;
			document.getElementById('freeTime').style.backgroundColor = 'white';
			document.getElementById('select1').disabled = false;
			document.getElementById('perTime2').disabled = false;
		} else {
			document.getElementById('freeTime').disabled = true;
			document.getElementById('freeTime').style.backgroundColor = '#F2F2F2';
			document.getElementById('select1').disabled = true;
			document.getElementById('perTime2').disabled = true;			
			document.getElementById('freeTime').value = "";
			document.getElementById('select1').value = "";
			document.getElementById('perTime2').checked = false;
		}
	}
		
	function enableCheck() {
		if(document.getElementById('enableMonitor').checked) {
			document.getElementById('bLateMark').disabled = false;
			document.getElementById('BLReport').disabled = false;
		} else {
			document.getElementById('bLateMark').disabled = true;
			document.getElementById('BLReport').disabled = true;
			document.getElementById('LateHours').disabled = true;			
			document.getElementById('waiveOffLateMarks').disabled = true;
			document.getElementById('waiveOffLateMarks').style.backgroundColor = '#F2F2F2';			
			document.getElementById('adjustLMCountId').disabled = true;
			document.getElementById('adjustLMCountId').style.backgroundColor = '#F2F2F2';
			document.getElementById('adjustLMLevDaysId').disabled = true;
			document.getElementById('adjustLMLevDaysId').style.backgroundColor = '#F2F2F2';
			document.getElementById('lateCombineLeaveId').disabled = true;
			document.getElementById('dedLmInSlabsOfId').disabled = true;
			document.getElementById('dedLmInSlabsOfId').style.backgroundColor = '#F2F2F2';
			document.getElementById('nonRegLateMarksLeaveId').disabled = true;
			document.getElementById('lateMarksLeaveId').disabled = true;
			document.getElementById('lateCombineLeaveBtn').disabled = true;
			document.getElementById('nonRegLateMarksLeaveBtn').disabled = true;
			document.getElementById('lateMarksLeaveBtn').disabled = true;
			document.getElementById('bLateMark').checked = false;
			document.getElementById('BLReport').checked = false;
			document.getElementById('LateHours').checked = false;
			document.getElementById('adjustLMCountId').value = "";
			document.getElementById('adjustLMLevDaysId').value = "";
			document.getElementById('lateCombineLeaveId').value = "";
			document.getElementById('paraFrm_lateCombineLeaveCode').value = "";
			document.getElementById('dedLmInSlabsOfId').value = "";
			document.getElementById('nonRegLateMarksLeaveId').value = "";
			document.getElementById('paraFrm_nonRegLateMarksLeaveCode').value = "";
			document.getElementById('lateMarksLeaveId').value = "";
			document.getElementById('paraFrm_lateMarksLeaveCode').value = "";
		}
	}
		
	function LMarkCheck() {
		if(document.getElementById('bLateMark').checked) {
			document.getElementById('waiveOffLateMarks').disabled = false;
			document.getElementById('waiveOffLateMarks').style.backgroundColor = 'white';		
			document.getElementById('adjustLMCountId').disabled = false;
			document.getElementById('adjustLMCountId').style.backgroundColor = 'white';
			document.getElementById('adjustLMLevDaysId').disabled = false;
			document.getElementById('adjustLMLevDaysId').style.backgroundColor = 'white';
			document.getElementById('lateCombineLeaveId').disabled = false;
			document.getElementById('lateCombineLeaveBtn').disabled = false;
			document.getElementById('BLReport').checked = false;
			document.getElementById('BLReport').disabled = true;
			document.getElementById('LateHours').checked = false;
			document.getElementById('LateHours').disabled = true;
			document.getElementById('dedLmInSlabsOfId').disabled = true;
			document.getElementById('dedLmInSlabsOfId').style.backgroundColor = '#F2F2F2';
			document.getElementById('nonRegLateMarksLeaveId').disabled = true;
			document.getElementById('nonRegLateMarksLeaveBtn').disabled = true;
			document.getElementById('dedLmInSlabsOfId').value = "";
			document.getElementById('nonRegLateMarksLeaveId').value = "";
			document.getElementById('paraFrm_nonRegLateMarksLeaveCode').value = "";
			document.getElementById('lateMarksLeaveId').value = "";
			document.getElementById('paraFrm_lateMarksLeaveCode').value = "";
		} else {
			document.getElementById('waiveOffLateMarks').disabled = true;
			document.getElementById('waiveOffLateMarks').style.backgroundColor = '#F2F2F2';			
			document.getElementById('adjustLMCountId').disabled = true;
			document.getElementById('adjustLMCountId').style.backgroundColor = '#F2F2F2';
			document.getElementById('adjustLMLevDaysId').disabled = true;
			document.getElementById('adjustLMLevDaysId').style.backgroundColor = '#F2F2F2';
			document.getElementById('lateCombineLeaveId').disabled = true;
			document.getElementById('lateCombineLeaveBtn').disabled = true;
			document.getElementById('BLReport').disabled = false;
			document.getElementById('adjustLMCountId').value = "";
			document.getElementById('adjustLMLevDaysId').value = "";
			document.getElementById('lateCombineLeaveId').value = "";
			document.getElementById('paraFrm_lateCombineLeaveCode').value = "";
		}
	}
		
	function LReportCheck() {
		if(document.getElementById('BLReport').checked) {
			document.getElementById('LateHours').disabled = false;
			document.getElementById('dedLmInSlabsOfId').disabled = false;
			document.getElementById('dedLmInSlabsOfId').style.backgroundColor = 'white';
			document.getElementById('nonRegLateMarksLeaveId').disabled = false;
			document.getElementById('nonRegLateMarksLeaveBtn').disabled = false;
			document.getElementById('adjustLMCountId').disabled = true;
			document.getElementById('adjustLMCountId').style.backgroundColor = '#F2F2F2';
			document.getElementById('adjustLMLevDaysId').disabled = true;
			document.getElementById('adjustLMLevDaysId').style.backgroundColor = '#F2F2F2';
			document.getElementById('lateCombineLeaveId').disabled = true;
			document.getElementById('lateCombineLeaveBtn').disabled = true;
			document.getElementById('bLateMark').disabled = true;
			document.getElementById('bLateMark').checked = false;
			document.getElementById('adjustLMCountId').value = "";
			document.getElementById('adjustLMLevDaysId').value = "";
			document.getElementById('lateCombineLeaveId').value = "";
			document.getElementById('paraFrm_lateCombineLeaveCode').value = "";
		} else {
			document.getElementById('bLateMark').disabled = false;
			document.getElementById('LateHours').disabled = true;
			document.getElementById('dedLmInSlabsOfId').disabled = true;
			document.getElementById('dedLmInSlabsOfId').style.backgroundColor = '#F2F2F2';
			document.getElementById('nonRegLateMarksLeaveId').disabled = true;
			document.getElementById('nonRegLateMarksLeaveBtn').disabled = true;
			document.getElementById('LateHours').checked = false;
			document.getElementById('dedLmInSlabsOfId').value = "";
			document.getElementById('nonRegLateMarksLeaveId').value = "";
			document.getElementById('paraFrm_nonRegLateMarksLeaveCode').value = "";
			document.getElementById('lateMarksLeaveId').disabled = true;
			document.getElementById('lateMarksLeaveBtn').disabled = true;
			document.getElementById('lateMarksLeaveId').value = "";
			document.getElementById('paraFrm_lateMarksLeaveCode').value = "";
		}
	}
		
	function LateHoursCheck() {
		if(document.getElementById('LateHours').checked) {
			document.getElementById('lateMarksLeaveId').disabled = false;
			document.getElementById('lateMarksLeaveBtn').disabled = false;
		} else {
			document.getElementById('lateMarksLeaveId').disabled = true;
			document.getElementById('lateMarksLeaveBtn').disabled = true;
			document.getElementById('lateMarksLeaveId').value = "";
			document.getElementById('paraFrm_lateMarksLeaveCode').value = "";
		}
	}
		
	function adjLateMarkCheck() {
		if(document.getElementById('adjLateMark2').checked) {
			document.getElementById('extraTime').disabled = false;
			document.getElementById('select3').disabled = false;
		} else {
			document.getElementById('extraTime').disabled = true;
			document.getElementById('select3').disabled = true;
			document.getElementById('extraTime').value = "";
			document.getElementById('select3').value = "";
		}
	}
		
	function halfCheck() {
		if(document.getElementById('halfId').checked) {
			document.getElementById('regHalfDayLeaveId').disabled = false;
			document.getElementById('regHalfDayLeaveBtn').disabled = false;
		} else {
			document.getElementById('regHalfDayLeaveId').disabled = true;
			document.getElementById('regHalfDayLeaveBtn').disabled = true;
			document.getElementById('regHalfDayLeaveId').value = "";
			document.getElementById('paraFrm_regHalfDayLeaveCode').value = "";			
		}
	}
		
	function weekCheck() {
		if(document.getElementById('weekId').checked) {
			document.getElementById('week7').disabled = false;
			document.getElementById('week72').disabled = false;
			document.getElementById('week73').disabled = false;
			document.getElementById('week74').disabled = false;
			document.getElementById('week75').disabled = false;
			document.getElementById('week76').disabled = false;
			document.getElementById('week77').disabled = false;
			
			for(var i = 1; i < 7; i++) {
				document.getElementById('sun' + i).disabled = false;
				document.getElementById('mon' + i).disabled = false;
				document.getElementById('tue' + i).disabled = false;
				document.getElementById('wed' + i).disabled = false;
				document.getElementById('thu' + i).disabled = false;
				document.getElementById('fri' + i).disabled = false;
				document.getElementById('sat' + i).disabled = false;
			}
		} else {
			document.getElementById('week7').disabled = true;
			document.getElementById('week72').disabled = true;
			document.getElementById('week73').disabled = true;
			document.getElementById('week74').disabled = true;
			document.getElementById('week75').disabled = true;
			document.getElementById('week76').disabled = true;
			document.getElementById('week77').disabled = true;
			document.getElementById('week7').checked = false;
			document.getElementById('week72').checked = false;
			document.getElementById('week73').checked = false;
			document.getElementById('week74').checked = false;
			document.getElementById('week75').checked = false;
			document.getElementById('week76').checked = false;
			document.getElementById('week77').checked = false;
							
			for(var i = 1; i < 7; i++) {
				document.getElementById('sun' + i).disabled = true;
				document.getElementById('mon' + i).disabled = true;
				document.getElementById('tue' + i).disabled = true;
				document.getElementById('wed' + i).disabled = true;
				document.getElementById('thu' + i).disabled = true;
				document.getElementById('fri' + i).disabled = true;
				document.getElementById('sat' + i).disabled = true;
				document.getElementById('sun' + i).checked = false;
				document.getElementById('mon' + i).checked = false;
				document.getElementById('tue' + i).checked = false;
				document.getElementById('wed' + i).checked = false;
				document.getElementById('thu' + i).checked = false;
				document.getElementById('fri' + i).checked = false;
				document.getElementById('sat' + i).checked = false;
			}
		}
	}
		
	function setText(fieldName, format) {
		if(document.getElementById(fieldName).value == '') {
			document.getElementById(fieldName).value = format; 
			document.getElementsByName(fieldName).value = format;
			document.getElementById(fieldName).style.color = '#ACACAC';
		}
	} 
	
	function clearText(fieldName, format) {
		if(document.getElementById(fieldName).value == format) {
			document.getElementById(fieldName).value = '';
			document.getElementById(fieldName).style.color = 'black';
		}
	}
	
	function callCheck(obj, day) {
		if(obj.checked == true) {
			for(var i = 1; i <= 6; i++) {
				document.getElementById(day + i).checked = true;
			}
		} else {
			for(var i = 1; i <= 6; i++) {
				document.getElementById(day + i).checked = false;
			}
		}
	}
 
  	function callReport(name) {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = name;	
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";							
	}
	
	function resetData() {
  		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	}
	
  	function callDelete(id) {
		if(document.getElementById(id).value == "") {
			alert("Please select a record to delete");
			return false;
		}		
      	var conf = confirm("Do you really want to delete this record ?");
		if(conf) {
			document.getElementById('paraFrm').target = "_self";
			return true;
  		}
	  	return false;
	}
  	
	function callSearch() {
		myWin = window.open('','myWin','top=260, left=250, width=700, height=400, scrollbars=no, toolbars=no, status=yes, resizable=yes');
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'ShiftMaster_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function call(id, id1, leaveCode) {
		try {
  	   	 	var hCode = document.getElementById(leaveCode).value;
			window.open('','new','top=100, left=300, width=400, height=400, scrollbars=yes, status=no, resizable=no');
 			document.getElementById("paraFrm").target = "new";
			document.getElementById("paraFrm").action = 'ShiftMaster_leaveConsecutive.action?id=' + id + '&hCode=' + hCode + '&id1=' + id1; 
  			document.getElementById("paraFrm").submit();
  			document.getElementById("paraFrm").target = "main";
  		} catch(e) {
  			alert(e);
  		}
  	 }
  	 
	function callLeaveCombination(setLeaveCode, setLeaveAbbr) {
		document.getElementById('paraFrm_leaveCodeHid').value = setLeaveCode;
		document.getElementById('paraFrm_leaveAbbrHid').value = setLeaveAbbr;		
		window.open('','new','top=300, left=300, width=350, height=300, scrollbars=yes, status=no, resizable=no');
	 	document.getElementById("paraFrm").target = "new";
		document.getElementById("paraFrm").action = 'ShiftMaster_leaveCombination.action'; 
	  	document.getElementById("paraFrm").submit();
	  	document.getElementById("paraFrm").target = "main";
	}

  	function saveFun() {
  		try{
  			if(trim(document.getElementById('paraFrm_shiftName').value) == "") {
				alert("Please enter " + document.getElementById("shiftname1").innerHTML.toLowerCase());
				//document.getElementById('paraFrm_shiftName').focus(); 
				return false;
			}  			
  			if(trim(document.getElementById('startsHrs').value) == "") {
				alert("Please enter " + document.getElementById("workhours").innerHTML.toLowerCase());
				document.getElementById('startsHrs').focus();
				return false;
			}		
			if(trim(document.getElementById('startsHrs').value) == "00:00") {
				alert(document.getElementById("workhours").innerHTML + " has to be greater than 00:00");
				document.getElementById('startsHrs').focus();
				return false;
			}
			if(!validateTime("startsHrs", 'workhours')) { return false; }
			  			
  			if(document.getElementById('paraFrm_flexiTimeAllowed').checked){  		
  				if(trim(document.getElementById('markFlexiLateAfterThisTime').value) == "") {
					alert("Please enter " + document.getElementById("markFlexiLateAfterThisTimeLable").innerHTML.toLowerCase());
					document.getElementById('markFlexiLateAfterThisTime').focus();
					return false;
				}
				if(trim(document.getElementById('markFlexiLateAfterThisTime').value) == "00:00") {
					alert(document.getElementById("markFlexiLateAfterThisTimeLable").innerHTML + " has to be greater than 00:00");
					document.getElementById('markFlexiLateAfterThisTime').focus();
					return false;
				}
			
				if(trim(document.getElementById('markFlexiHalfDayAfterThisTime').value) == "") {
					alert("Please enter " + document.getElementById("markFlexiHalfDayAfterThisTimeLable").innerHTML.toLowerCase());
					document.getElementById('markFlexiHalfDayAfterThisTime').focus();
					return false;
				}
				if(trim(document.getElementById('markFlexiHalfDayAfterThisTime').value) == "00:00") {
					alert(document.getElementById("markFlexiHalfDayAfterThisTimeLable").innerHTML + " has to be greater than 00:00");
					document.getElementById('markFlexiHalfDayAfterThisTime').focus();
					return false;
				}
				if(trim(document.getElementById('markFlexiAbsentBeforeThisTime').value)==""){
					alert("Please enter " + document.getElementById("markFlexiAbsentBeforeThisTimeLable").innerHTML.toLowerCase());
					return false;
				}
				if(trim(document.getElementById('markFlexiAbsentBeforeThisTime').value)=="00:00"){
					alert(document.getElementById("markFlexiAbsentBeforeThisTimeLable").innerHTML + " has to be greater than 00:00");
					document.getElementById('markFlexiAbsentBeforeThisTime').focus();
					return false;
				}			
				var lateVar = trim(document.getElementById('markFlexiLateAfterThisTime').value);			
				var halfDayVar = trim(document.getElementById('markFlexiHalfDayAfterThisTime').value);
				var flexiAbsent= trim(document.getElementById('markFlexiAbsentBeforeThisTime').value);			
		    
		    	if(!validateTime("markFlexiHalfDayAfterThisTime", 'markFlexiHalfDayAfterThisTimeLable')) { return false; }
		    	if(!validateTime("markFlexiLateAfterThisTime", 'markFlexiLateAfterThisTimeLable')) {return false; }		    
		    	if(!validateTime("markFlexiAbsentBeforeThisTime", 'markFlexiAbsentBeforeThisTimeLable')) { return false; }
		
				if(!timeDifferenceEqual(halfDayVar, lateVar,"markFlexiHalfDayAfterThisTime","markFlexiHalfDayAfterThisTimeLable","markFlexiLateAfterThisTimeLable")){
					return false;
				}	
				if(!timeDifferenceEqual(flexiAbsent,halfDayVar,"markFlexiAbsentBeforeThisTime","markFlexiAbsentBeforeThisTimeLable","markFlexiHalfDayAfterThisTimeLable")){
					return false;			
				}				
  			}					
			if(!document.getElementById('paraFrm_flexiTimeAllowed').checked){
				if(!formValidate()) {
					document.getElementById('paraFrm_enableAll').value = 'Y';
    				return false;
    			}
			}			
			var markAbsentAfterThisTimeVar = document.getElementById('markAbsentAfterThisTime').value;
			if(!markAbsentAfterThisTimeVar==""){
				if(markAbsentAfterThisTimeVar=="00:00"){
					alert("Entered time should me greater than 00:00");
					document.getElementById('markAbsentAfterThisTime').focus();
					return false;
				}else if(!validateTime("markAbsentAfterThisTime", "markAbsentAfterThisTimeLabel")) { 
					return false; 
				}
			}
		}catch(e){
  			alert("save function : "+e);
  		}			
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ShiftMaster_save.action';
		document.getElementById('paraFrm').submit();		
		return true;
  	}
  	
  	function formValidate() {
		try {
			//DIV 1
			if(trim(document.getElementById('startTime').value) == "") {
				alert("Please enter " + document.getElementById("shiftstarttime1").innerHTML.toLowerCase());
				document.getElementById('startTime').focus();
				return false;
			}		
			if(trim(document.getElementById('startTime').value) == "00:00") {
				alert(document.getElementById("shiftstarttime1").innerHTML + " has to be greater than 00:00");
				document.getElementById('startTime').focus();
				return false;
			}		
			if(trim(document.getElementById('endTime').value) == "") {
				alert("Please enter " + document.getElementById("shiftendtime1").innerHTML.toLowerCase());
				document.getElementById('endTime').focus();
				return false;
			}		
			if(trim(document.getElementById('endTime').value) == "00:00") {
				alert(document.getElementById("shiftendtime1").innerHTML + " has to be greater than 00:00");
				document.getElementById('endTime').focus();
				return false;
			}			
		/*	
			if(trim(document.getElementById('startsHrs').value) == "") {
				alert("Please enter " + document.getElementById("workhours").innerHTML.toLowerCase());
				document.getElementById('startsHrs').focus();
				return false;
			}		
			if(trim(document.getElementById('startsHrs').value) == "00:00") {
				alert(document.getElementById("workhours").innerHTML + " has to be greater than 00:00");
				document.getElementById('startsHrs').focus();
				return false;
			}
		*/			
			var shiftStartTime = document.getElementById('startTime').value;
  			var shiftEndTime = document.getElementById('endTime').value;
  			var shiftWorkHrs = document.getElementById('startsHrs').value;		    
		    
			if(!validateTime("startTime", 'shiftstarttime1')) { return false; }			
			if(!validateTime("endTime", 'shiftendtime1')) { return false; }			
			if(!validateTime("startsHrs", 'workhours')) { return false; }
						
			if(document.getElementById('reportTimeLate').value != "") {
				if(!validateTime("reportTimeLate", 'latereportingafter')) { return false; }
			}
		
			if(document.getElementById('reportTimeHalf').value != "") {
				if(!validateTime("reportTimeHalf", 'halfdayreportingafter')) { return false; }
			}
			
			if(document.getElementById('lbreakStart').value != "") {
				if(!validateTime("lbreakStart", 'breakstart')) { return false; }
			}
			
			if(document.getElementById('lbreakEnd').value != "") {
				if(!validateTime("lbreakEnd", 'breakend')) { return false; }
			}
			
			if(document.getElementById('offLeftBefore').value != "") {
				if(!validateTime("offLeftBefore", 'halfdayleave')) { return false; }
			}
			
			if(document.getElementById('offearlyBefore').value != "") {
				if(!validateTime("offearlyBefore", 'earlyleave')) { return false; }
			}
			
			if(document.getElementById('startsHrs').value != "") {
				if(!validateTime("startsHrs", 'workhours')) { return false; }
			}
			
			if(document.getElementById('startsfrom').value != "") {
				if(!validateTime("startsfrom", 'extraworkhours')) { return false; }
			}
			
			if(document.getElementById('freeTime').value != "") {
				if(!validateTimeMethod("freeTime", 'Free Personal Time of')) { return false; }
			}
		
			var shiftNtFlag = document.getElementById('paraFrm_shiftNtFlag').value;
			if(shiftNtFlag == 'Y') {}
			else {
				if(!timeDifference(shiftStartTime, shiftEndTime, "endTime", "shiftstarttime1", "shiftendtime1")) { return false; }
			}
			
			if(document.getElementById('perTime1').checked) {
				if(trim(document.getElementById('freeTime').value) == "") {
					alert("Please enter free personal time hours.");
					return false;
				}				
				if(document.getElementById('select1').value == "") {
					alert("Please select hours per option.");
					return false;
				}
			}
						
			//DIV 2
			var shiftDtlsShown = document.getElementById('paraFrm_shiftDtlsShown').value;
			if(shiftDtlsShown == 'true') {
				if(trim(document.getElementById('dtlStartTime').value) == "") {
					alert("Please enter " + document.getElementById("shiftstarttime1").innerHTML.toLowerCase());
					return false;
				}			
				if(trim(document.getElementById('dtlStartTime').value) == "00:00") {
					alert(document.getElementById("shiftstarttime1").innerHTML + " has to be greater than 00:00");
					return false;
				}			
				if(trim(document.getElementById('dtlEndTime').value) == "") {
					alert("Please enter " + document.getElementById("shiftendtime1").innerHTML.toLowerCase());
					return false;
				}			
				if(trim(document.getElementById('dtlEndTime').value) == "00:00") {
					alert(document.getElementById("shiftendtime1").innerHTML + " has to be greater than 00:00");
					return false;
				}				
				if(trim(document.getElementById('dtlStartsHrs').value) == "") {
					alert("Please enter " + document.getElementById("workhours").innerHTML.toLowerCase());
					return false;
				}			
				if(trim(document.getElementById('dtlStartsHrs').value) == "00:00") {
					alert(document.getElementById("workhours").innerHTML + " has to be greater than 00:00");
					return false;
				}	
				if(!validateTime("dtlStartTime", 'shiftstarttime1')) { return false; }	
				if(!validateTime("dtlEndTime", 'shiftendtime1')) { return false; }				
				if(!validateTime("dtlStartsHrs", 'workhours')) { return false; }			
				
				if(document.getElementById('dtlReportTimeLate').value != "") {
					if(!validateTime("dtlReportTimeLate", 'latereportingafter')) { return false; }
				}
			
				if(document.getElementById('dtlReportTimeHalf').value != "") {
					if(!validateTime("dtlReportTimeHalf", 'halfdayreportingafter')) { return false; }
				}
				
				if(document.getElementById('dtlLbreakStart').value != "") {
					if(!validateTime("dtlLbreakStart", 'breakstart')) { return false; }
				}
				
				if(document.getElementById('dtlLbreakEnd').value != "") {
					if(!validateTime("dtlLbreakEnd", 'breakend')) { return false; }
				}
				
				if(document.getElementById('dtlOffLeftBefore').value != "") {
					if(!validateTime("dtlOffLeftBefore", 'halfdayleave')) { return false; }
				}
				
				if(document.getElementById('dtlOffearlyBefore').value != "") {
					if(!validateTime("dtlOffearlyBefore", 'earlyleave')) { return false; }
				}
				
				if(document.getElementById('dtlStartsHrs').value != "") {
					if(!validateTime("dtlStartsHrs", 'workhours')) { return false; }
				}
				
				if(document.getElementById('dtlStartsfrom').value != "") {
					if(!validateTime("dtlStartsfrom", 'extraworkhours')) { return false; }
				}
				
				var dtlShiftNtFlagVal = document.getElementById('dtlShiftNtFlag').value;
				var dtlStartTimeVal = document.getElementById('dtlStartTime').value;
	  			var dtlEndTimeVal = document.getElementById('dtlEndTime').value;
				if(dtlShiftNtFlagVal == 'Y') {}
				else {
					if(!timeDifference(dtlStartTimeVal, dtlEndTimeVal, "dtlEndTime", "shiftstarttime1", "shiftendtime1")) { return false; }
				}
			}
		
			//DIV 3
			if(document.getElementById('enableMonitor').checked) {
				if(!document.getElementById('bLateMark').checked && !document.getElementById('BLReport').checked) {
					alert("Please check Based on number of late marks or Based on hours of late reporting.");
					return false;
				}
				
				if(document.getElementById('bLateMark').checked) {
					if(trim(document.getElementById('adjustLMCountId').value) == "") {
						alert("Please enter no. of late marks to be adjusted.");
						return false;
					}
					
					if(trim(document.getElementById('adjustLMLevDaysId').value) == "") {
						alert("Please enter no. of days to be adjusted.");
						return false;
					}
					
					if(trim(document.getElementById('lateCombineLeaveId').value) == "") {
						alert("Please select leave type to be adjusted against.");
						return false;
					}
				}				
				if(document.getElementById('BLReport').checked) {
					if(!document.getElementById('LateHours').checked) {
						alert("Please check late hour regularization.");
						return false;
					}					
					if(document.getElementById('LateHours').checked) {
						if(trim(document.getElementById('lateMarksLeaveId').value) == "") {
							alert("Please select leave type to deduct regularized late marks from.");
							return false;
						}
					}					
					if(trim(document.getElementById('nonRegLateMarksLeaveId').value) == "") {
						alert("Please select leave type to deduct non regularized late marks from.");
						return false;
					}					
					if(trim(document.getElementById('dedLmInSlabsOfId').value) == "") {
						alert("Please enter late hours to be deducted in minutes.");
						return false;
					}
				}
			}
		
			//DIV 4
			if(document.getElementById('halfId').checked) {
				if(trim(document.getElementById('regHalfDayLeaveId').value) == "") {
					alert("Please select leave type to deduct half day from.");
					return false;
				}
			}			
			document.getElementById('paraFrm').target = "_self";
			return true;
		 } catch(e) {
			//alert(e);
		 }
  	}
  	
  	function validateTimeMethod(name, labName) {
		var time = document.getElementById(name).value;
		if(time == "") { return true; }		
		var timeExp = /^[0-9]{2}[:]?[0-9]{2}$/;
		var timeArray = time.split(":");
		var hour = timeArray[0];
		var min = timeArray[1];
		
		if(!(time.match(timeExp)) || time.length < 5) {
			alert(labName + " should be in 24Hours HH:MI format");
			document.getElementById(name).focus();
			return false;
		}	
		if(hour > 23) {
			alert("Hour " + hour + " is not valid");
			document.getElementById(name).focus();
			return false;
		}	
		if(min > 59) {
			alert("Minuite " + min + " is not valid");
			document.getElementById(name).focus();
			return false;
		}
		return true;
	}
  	
  	function resetFun() {
  		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ShiftMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
  	
  	function backFun() {
  		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ShiftMaster_input.action';
		document.getElementById('paraFrm').submit();
  	}
  	
  	function editFun() {
  		try {
  			personalCheck(); enableCheck(); LMarkCheck(); LReportCheck(); LateHoursCheck(); halfCheck(); halfDayCheck(); weekCheck();
		} catch(e) {
			//alert(e);
		}
		return true;
	}
	
	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");		
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ShiftMaster_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function getShiftDetails() {		
		if(trim(document.getElementById('paraFrm_shiftName').value) == "") {
				alert("Please enter " + document.getElementById("shiftname1").innerHTML.toLowerCase());
				//document.getElementById('paraFrm_shiftName').focus(); 
				return false;
		}		
		var weekDay = document.getElementById('paraFrm_selWeekDay').value;		
		if(weekDay == '0') {
			alert('Please select ' + document.getElementById("weekDay").innerHTML);
		} else {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ShiftMaster_getShiftExceptionDetails.action';
			document.getElementById('paraFrm').submit();
		}	
	}
	
	function goBackToWeekDays() {
		//	document.getElementById('trWeekday').style.display = 'block';
		//	document.getElementById('trShiftDtls').style.display = 'none';
		//	document.getElementById('trNote').style.display = 'none';				
		document.getElementById('paraFrm_shiftDtlsShown').value = 'false';
		document.getElementById('paraFrm').target = "_self";		 
		document.getElementById('paraFrm').action = 'ShiftMaster_back.action';
		document.getElementById('paraFrm').submit();
	}
	
	var reqs;
	function deleteShiftExceptionDetails() {
		var isShiftExcpDel = confirm('Do you want to delete the Shift Exception details?');
		if(isShiftExcpDel) {
			try {
				var url = 'ShiftMaster_deleteShiftExceptionDetails.action?' + getFieldValuesForDeleteExcp() + '&' + Math.random();
			} catch(e) {
				alert(e);
			}
			if (window.XMLHttpRequest) { // Non-IE browsers
				reqs = new XMLHttpRequest(); // XMLHttpRequest object is created
			    reqs.onreadystatechange = processStateChangeForDeleteExcp; // XMLHttpRequest object is configured with a callback function
			    try {
			    	/**
			    	 * open("HTTP method", "URL", syn/asyn), for asyn-true
			    	 * if false, send operations are synchronous, browser doesn't accept any input/output
			    	**/
			    	reqs.open("GET", url, true);
			    } catch (e) {
					alert("Problem Communicating with Server\n"+e);
				}
				reqs.send(null);
			} else if (window.ActiveXObject) { // IE 
				reqs = new ActiveXObject("Microsoft.XMLHTTP");
			    if (reqs) {
			    	reqs.onreadystatechange = processStateChangeForDeleteExcp;
			      	reqs.open("GET", url, true);
			       	reqs.send();
			    }
			}
		}
		//document.getElementById('paraFrm').target = "_self";		  
		//document.getElementById('paraFrm').action = 'ShiftMaster_getShiftExceptionDetails.action';
		//document.getElementById('paraFrm').submit();		
	}
	
	function getFieldValuesForDeleteExcp() {
		var returnString = '';
		try {
			var formElements = document.forms['paraFrm'].elements;
			for (var i = formElements.length - 1; i >= 0; i--) {
	 			if(formElements[i].name == 'shiftID' || formElements[i].name == 'weekDay') {
	 				returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
	 			}
	 		}
		} catch(e) {
			alert(e);
		}
		return returnString;
	}
	
	function processStateChangeForDeleteExcp() {
		// 0 = uninitialized, 1 = loading, 2 = loaded, 3 = interactive (some data has been returned)!
		if(reqs.readyState == 4) { // Complete
			if (reqs.status == 200) { // OK response
		    	//responseXML: XML document of data returned from the server
		    	var res = reqs.responseText; // String version of data returned from the server
		    	alert(res);
		    	goBackToWeekDays();
			}
			parent.frames[2].name = 'main';
		}
	}
	
    function selectActualHoursWorkedCheckBox() {
	 	var actualHoursWorkedOTVar = document.getElementById('paraFrm_actualHoursWorkedOT').checked;
		if(actualHoursWorkedOTVar) {
			document.getElementById('paraFrm_actualOutTimeOT').checked = false;
			document.getElementById('paraFrm_actualHoursWorkedOT').checked = true;
		} else {
			document.getElementById('paraFrm_actualOutTimeOT').checked = true;
			document.getElementById('paraFrm_actualHoursWorkedOT').checked = false;
		}
	}

	function selectActualOutTimeCheckBox() {
		var actualOutTimeOTVar = document.getElementById('paraFrm_actualOutTimeOT').checked;
		if(actualOutTimeOTVar) {
			document.getElementById('paraFrm_actualOutTimeOT').checked = true;
			document.getElementById('paraFrm_actualHoursWorkedOT').checked = false;
		} else {
			document.getElementById('paraFrm_actualOutTimeOT').checked = false;
			document.getElementById('paraFrm_actualHoursWorkedOT').checked = true;
		}
	}

	function callFormulaSection() {
		var enableOTConfigWorkflow = document.getElementById('enableOTConfigWorkflow').checked;
		if (enableOTConfigWorkflow) {
			document.getElementById('formulaForOTConfiguration').style.display = '';
		} else {
			document.getElementById('formulaForOTConfiguration').style.display = 'none';
		}
	}
</script>