<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp"%>

	<jsp:include page="/pages/CommonCssJS.jsp" ></jsp:include>
<script type="text/javascript"	src="../pages/common/include/javascript/sorttable.js"></script>

<STYLE type=text/css>
a:hover {
	COLOR: #FF0000;
	text-decoration: underline;
}
</STYLE>
<s:form action="AppraisalCalendar" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong
							class="formhead"> <img
								src="../pages/images/recruitment/review_shared.gif" width="25"
								height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Appraisal
								Calendar</strong></td>
						<td width="3%" valign="top" class="txt"><div align="right">
								<img src="../pages/images/recruitment/help.gif" width="16"
									height="16" />
							</div></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<s:hidden name="appraisalCode" />
			<s:hidden name="appraisalId" />
			<s:hidden name="startDate" />
			<s:hidden name="validTill" />
			<s:hidden name="repeatFreq" />
			<s:hidden name="endDate" />
			<s:hidden name="hideAutoStart"></s:hidden>
			<s:hidden name="hideJoinDateCheck"></s:hidden>
			<s:hidden name="hideEmpTypeCheck"></s:hidden>
			<s:hidden name="hideEmpDivCheck"></s:hidden>
			<s:hidden name="hideEmpGradeCheck"></s:hidden>
			<s:hidden name="hideEmpDeptCheck"></s:hidden>
			<s:hidden name="hideImportContentConfig"></s:hidden>
			<s:hidden name="appraisalIdPhase"></s:hidden>
			<s:hidden name="appraisalIdRating"></s:hidden>
			<s:hidden name="appraisalIdParameters"></s:hidden>
			<s:hidden name="appraisalIdAppraisers"></s:hidden>
			<s:hidden name="appraisalIdTemplate"></s:hidden>
			<s:hidden name="appraisalIdMapping"></s:hidden>
			<s:hidden name="paraId" />
			<s:hidden name="edit" />
			<s:hidden name="addNew" />
			<s:hidden name="onload" />
			<s:hidden name="importStartDate"></s:hidden>
			<s:hidden name="importEndDate"></s:hidden>
			<s:hidden name="hideImportConfig"></s:hidden>
			<s:hidden name="joinToDate" />
			<s:hidden name="joinFromDate" />
			<s:hidden name="show" value="%{show}" />
			<s:hidden name="myPage" id="myPage" />
			<s:hidden name="isStarted" />
			<s:hidden name="calUpdateflag" />
			<s:hidden name="importAppraisalID"></s:hidden>
			<script>
        function setJoinDateCheck(){
			if(document.getElementById("paraFrm_hideJoinDateCheck").value=="Y"){
				document.getElementById("joinDateCheck").checked=true;
				document.getElementById("joinDateDiv").style.display ='';
			
	}else{
			document.getElementById("joinDateDiv").style.display ='none';
		}
	}
		function setEmpTypeCheck(){
			if(document.getElementById("paraFrm_hideEmpTypeCheck").value=="Y"){
				document.getElementById("empTypeCheck").checked=true;
			}else{
				document.getElementById("empTypeCheck").checked="";
			}
		}
		function setEmpDivCheck(){
    if(document.getElementById("paraFrm_hideEmpDivCheck").value=="Y"){
				document.getElementById("empDivCheck").checked=true;
			}else{
				document.getElementById("empDivCheck").checked="";
			}
			
	}
		 function setEmpGradeCheck(){
			if(document.getElementById("paraFrm_hideEmpGradeCheck").value=="Y"){
				document.getElementById("empGradeCheck").checked=true;
			}
			else{
				document.getElementById("empGradeCheck").checked="";
			}
		}
		function setEmpDeptCheck(){
			if(document.getElementById("paraFrm_hideEmpDeptCheck").value=="Y"){
				document.getElementById("empDeptCheck").checked=true;
			}
			else{
				document.getElementById("empDeptCheck").checked="";
			}
		}
		function setAutoStartCheck(){
			if(document.getElementById("paraFrm_hideAutoStart").value=="Y"){
				document.getElementById("autoStart").checked=true;
			}
			else{
				document.getElementById("autoStart").checked="";
			}
		}
   </script>
			<s:if test="onloadMode">
				<tr>
					<td width="78%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td>
						<%
							int totalPage = 0;
									int pageNo = 0;
						%> <s:if test="noData"></s:if>
						<s:else>
							<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
								<%
									totalPage = (Integer) request.getAttribute("totalPage");
												pageNo = (Integer) request.getAttribute("pageNo");
								%> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPage%>', 'AppraisalCalendar_callPage.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" />
							</a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPage%>', 'AppraisalCalendar_callPage.action');">
									<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" />
							</a> <input type="text" name="pageNoField" id="pageNoField" size="3"
								value="<%=pageNo%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage%>', 'AppraisalCalendar_callPage.action');return numbersOnly();" />
								of <%=totalPage%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPage%>', 'AppraisalCalendar_callPage.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" />
							</a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'AppraisalCalendar_callPage.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" />
							</a></td>
						</s:else>
					</td>
				</tr>
				<tr>
					<td align="left" class="formtext" colspan="3">Note: <img
						src="../pages/mypage/images/icons/appraisal_phase_config.png"
						width="16" align="absmiddle" />&nbsp;Appraisal Phase
						Configuration &nbsp; <img
						src="../pages/mypage/images/icons/appraisal_schedule.png"
						width="16" align="absmiddle" />&nbsp;Appraisal Schedule&nbsp; <img
						src="../pages/mypage/images/icons/rating_scale.png" width="16"
						align="absmiddle" />&nbsp;Rating Scale Definition <img
						src="../pages/mypage/images/icons/appraiser_configuration.png"
						width="16" align="absmiddle" />&nbsp;Appraiser Configuration
						&nbsp; <img src="../pages/mypage/images/icons/form_designer.png"
						width="16" align="absmiddle" />&nbsp;Appraisal Form Designer&nbsp;

					</td>
				</tr>
				<tr>
					<td colspan="3">
						<table width="100%" border="0" cellpadding="2" cellspacing="2"
							class="formbg">
							<tr>
								<td class="formtext">
									<table width="100%" border="0" cellpadding="1" cellspacing="1"
										class="sortable">

										<tr class="td_bottom_border">
											<td width="5%" class="formth">Sr.No.</td>
											<td class="formth" align="left" width="20%"><label
												class="set" name="appraisal.code" id="appraisal.code12"
												ondblclick="callShowDiv(this);"><%=label.get("appraisal.code")%></label></td>

											<td class="formth" width="15%"><label class="set"
												name="start.date" id="start.date12"
												ondblclick="callShowDiv(this);"><%=label.get("start.date")%></label></td>
											<td class="formth" width="15%"><label class="set"
												name="end.date" id="end.date1"
												ondblclick="callShowDiv(this);"><%=label.get("end.date")%></label></td>
											<td class="formth" width="15%">Configure</td>
											<td class="formth" align="center" width="5%">Edit</td>
											<td class="formth" width="15%" no><input type="button"
												value=" Delete" class="delete"
												onclick="return deleteMultiple();" /> <br>
											<input type="checkbox" name="allChk" id="allChk"
												onclick="callAllCheck()" align="center" /></td>
										</tr>
										<%
											int count = 0;
										%>
										<%
											int k = 0;
													int c = 0;
													int cnt = pageNo * 10 - 10;
										%>
										<s:if test="noData">
											<tr>
												<td width="100%" colspan="8" align="center"
													class="sortableTD"><font color="red">No Data To
														Display</font></td>
											</tr>
										</s:if>
										<s:else>
											<s:iterator value="calendarList">

												<tr <%if (count % 2 == 0) {%> class="tableCell1"
													<%} else {%> class="tableCell2" <%}
							count++;%> ondblclick=""
													onmouseover="javascript:newRowColor(this);" title=""
													onmouseout="javascript:oldRowColor(this,<%=count % 2%>);">

													<td class="sortableTD" width="5%" align="center"><%=++cnt%>
														<%
															++k;
														%></td>
													<td class="sortableTD" width="20%" nowrap="nowrap"><s:property
															value="appraisalCodeList" />&nbsp;<s:hidden
															name="appraisalCodeList" /><input type="hidden"
														name="appraisalIdList"
														value="<s:property value='appraisalIdList'/>"
														id="appraisalIdList<%=k%>" /></td>

													<td class="sortableTD" align="center" nowrap="nowrap"
														width="15%"><s:property value="startDateList" />&nbsp;<s:hidden
															name="startDateList" /></td>
													<td class="sortableTD" align="center" nowrap="nowrap"
														width="15%"><s:property value="endDateList" />&nbsp;<s:hidden
															name="endDateList" /></td>
													<input type="hidden" name="hdeleteCode"
														id="hdeleteCode<%=k%>" />

													<!--  <td align="left">
												<a href="#?" class="link"  onclick="javascript:callForPhaseConfig('<s:property value="appraisalIdList"/>');"> <img
						src="../pages/mypage/images/icons/appraisal_phase_config.png"
						width="10" height="10" align="absmiddle"/>Appraisal Phase Configuration </a><br>
												<a href="#?" class="link"  onclick="javascript:callForAppraisalSchedule('<s:property value="appraisalIdList"/>');"><img
						src="../pages/mypage/images/icons/appraisal_schedule.png"
						width="10" height="10" align="absmiddle"/>Appraisal Schedule </a><br>
												<a href="#?" class="link"  onclick="javascript:callForRatingScaleDef('<s:property value="appraisalIdList"/>');"><img
						src="../pages/mypage/images/icons/rating_scale.png"
						width="10" height="10" align="absmiddle"/>Rating Scale Definition </a><br>
												<a href="#?" class="link"  onclick="javascript:callForAppraiserConfig('<s:property value="appraisalIdList"/>');"><img
						src="../pages/mypage/images/icons/appraiser_configuration.png"
						width="10" height="10" align="absmiddle"/>Appraiser Configuration </a><br>
												<a href="#?" class="link"  onclick="javascript:callForAppraisalFormDesigner('<s:property value="appraisalIdList"/>');"><img
						src="../pages/mypage/images/icons/form_designer.png"
						width="10" height="10" align="absmiddle"/>Appraisal Form Designer </a><br>
												<!--<a href="#?" class="link"  onclick="javascript:callForAppraisalRatingDef('<s:property value="appraisalIdList"/>');">Appraisal Rating Definition </a> 
											</td>-->

													<td align="middle" nowrap="nowrap"><a href="#?"
														class="link"
														onclick="javascript:callForPhaseConfig('<s:property value="appraisalIdList"/>');">
															<img
															src="../pages/mypage/images/icons/appraisal_phase_config.png"
															width="16" height="16" align="absmiddle"
															title="Appraisal Phase Configuration " class="iconImage" />
													</a>&nbsp; <a href="#?" class="link"
														onclick="javascript:callForAppraisalSchedule('<s:property value="appraisalIdList"/>');"><img
															src="../pages/mypage/images/icons/appraisal_schedule.png"
															width="16" height="16" align="absmiddle"
															title="Appraisal Schedule " class="iconImage" /></a>&nbsp; <a
														href="#?" class="link"
														onclick="javascript:callForRatingScaleDef('<s:property value="appraisalIdList"/>');"><img
															src="../pages/mypage/images/icons/rating_scale.png"
															width="16" height="16" align="absmiddle"
															title="Rating Scale Definition " class="iconImage" /></a>&nbsp;
														<a href="#?" class="link"
														onclick="javascript:callForAppraiserConfig('<s:property value="appraisalIdList"/>');"><img
															src="../pages/mypage/images/icons/appraiser_configuration.png"
															width="16" height="16" align="absmiddle"
															title="Appraiser Configuration" class="iconImage" /> </a>&nbsp;
														<a href="#?" class="link"
														onclick="javascript:callForAppraisalFormDesigner('<s:property value="appraisalIdList"/>');"><img
															src="../pages/mypage/images/icons/form_designer.png"
															width="16" height="16" align="absmiddle"
															title="Appraisal Form Designer " class="iconImage" /></a>&nbsp;
													
													<td class="sortableTD" width="5%" id="ctrlShow"
														align="center"><i class="fa fa-pencil" aria-hidden="true" title="Click for edit"
														onclick="callForEdit('<s:property value="appraisalIdList"/>')" ></i>

													</td>
													<td class="sortableTD" nowrap="nowrap" align="center"
														width="10%"><input type="checkbox" al
														name="deleteChk" id="deleteChk<%=k%>"
														onclick="callCheckBox(<%=k%>)" /></td>

												</tr>

												<%
													c++;
												%>
											</s:iterator>
										</s:else>
										<td>&nbsp;<input type="hidden" name="count" id="count"
											value="<%=c%>" />
									</table>
						</table>
				</tr>
				<tr>
					<td width="78%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td width="78%"><input type="button" class="back"
						value=" Back " onclick="return callBack();" /></td>
					<td width="22%">
						<div align="right">
							<font color="red">*</font> Indicates Required
						</div>
					</td>
				</tr>

				<tr>
					<td colspan="3">
						<div style="" bgcolor="#FFFFFF" align="center">

							<table width="100%" border="0" cellpadding="2" cellspacing="2"
								class="formbg">
								<tr>
									<td>
										<table width="100%" border="0" align="center" cellpadding="2"
											cellspacing="2">

											<tr>
												<td colspan="3" width="100%"><STRONG><label
														name="set.eligibility.criteria" class="set"
														id="set.eligibility.criteria"
														ondblclick="callShowDiv(this);"><%=label.get("set.eligibility.criteria")%></label>
														<font color="red">*</font></STRONG></td>
											</tr>
											<tr>
												<td align="center"><input type="button" class="token"
													value="View Eligible Employees" id="ctrlShow"
													onclick="return viewEligibleEmp();" /></td>
											</tr>

											<tr>
												<td colspan="3" width="100%"><input type="checkbox"
													name="empDivCheck" class="checkbox" id="empDivCheck"
													disabled="disabled" value="N"></input> <script>setEmpDivCheck();</script><label
													name="based.on.division" class="set" id="based.on.division"
													ondblclick="callShowDiv(this);"><%=label.get("based.on.division")%></label>
												</td>
											</tr>

											<tr>
												<td>
													<div id="divisionDiv">
														<table border="0" width="100%">
															<tr>
																<td width="25%" class="formtext" colspan="1"
																	nowrap="nowrap"><label name="select.employee.div"
																	class="set" id="select.employee.div"
																	ondblclick="callShowDiv(this);"><%=label.get("select.employee.div")%></label>
																	:</td>
																<td width="25%" colspan="1" nowrap="nowrap"><s:optiontransferselect
																		doubleDisabled="true" disabled="true" size="10"
																		doubleSize="10" doubleId="selDivId" id="availDivId"
																		label="Employee Division"
																		rightTitle="Selected Division"
																		leftTitle="Available Division"
																		addToLeftLabel="<< Remove" addToRightLabel="Add >>"
																		addAllToLeftLabel="Remove All"
																		addAllToRightLabel="Add All"
																		selectAllLabel="Select All" cssStyle="width:100px"
																		doubleCssStyle="width:100px" name="availDiv"
																		multiple="true" buttonCssClass="token"
																		list="%{hashmapDiv}" doubleName="selDiv"
																		doubleList="%{hashmapDivSel}" /></td>
																<td width="25%" class="formtext" colspan="1"></td>
																<td width="25%" colspan="1" nowrap="nowrap"></td>
															</tr>
														</table>
													</div>
												</td>
											</tr>
											<tr>
												<td colspan="3" width="100%"><input type="checkbox"
													name="joinDateCheck" class="checkbox" id="joinDateCheck"
													disabled="disabled" value="N"
													onclick="return callJoinDateCheck();"></input> <label
													name="based.on.join.date" class="set"
													id="based.on.join.date" ondblclick="callShowDiv(this);"><%=label.get("based.on.join.date")%></label>
												</td>
											</tr>
											<tr>
												<td>
													<div id="joinDateDiv">
														<table border="0" width="100%">
															<tr>
																<td width="25%" class="formtext" colspan="1"
																	nowrap="nowrap"><label name="employee.join.date"
																	class="set" id="employee.join.date"
																	ondblclick="callShowDiv(this);"><%=label.get("employee.join.date")%></label>
																	:</td>
																<td width="25%" colspan="1" nowrap="nowrap"><s:select
																		name="joinDateCondition" headerKey="1"
																		headerValue="On" cssStyle="width:150" disabled="true"
																		list="#{'2':'Between','3':'Before','4':'After','5':'On or Before','6':'On or After' }"
																		onchange="return callJoinDateCond();" /></td>
																<td width="25%" class="formtext" colspan="1"></td>
																<td width="25%" colspan="1" nowrap="nowrap"></td>
															</tr>
															<tr>
																<td width="25%" class="formtext" colspan="4">
																	<div id="joinDateFromDiv">
																		<table width="100%" border="0">
																			<tr>
																				<td width="25%" class="formtext" colspan="1"
																					align="right"><label name="join.date"
																					class="set" id="join.date"
																					ondblclick="callShowDiv(this);"><%=label.get("join.date")%></label>
																					<font color="red">*</font>:</td>
																				<td width="75%" colspan="3" nowrap="nowrap"><s:property
																						value="joinDate" /></td>
																			</tr>
																		</table>
																	</div>
																</td>
															</tr>
															<tr>
																<td width="25%" class="formtext" colspan="4">
																	<div id="joinDateToDiv">
																		<table width="100%" border="0">
																			<tr>
																				<td width="25%" class="formtext" colspan="1"
																					align="right"><label name="from.date"
																					class="set" id="from.date"
																					ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label>
																					<font color="red">*</font>:</td>
																				<td width="25%" colspan="1" nowrap="nowrap"><s:property
																						value="joinFromDate" /></td>
																				<td width="25%" class="formtext" colspan="1"
																					align="right"><label name="to.date"
																					class="set" id="to.date"
																					ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label>
																					<font color="red">*</font>:</td>
																				<td width="25%" colspan="1" nowrap="nowrap"><s:property
																						value="joinToDate" /></td>
																			</tr>
																		</table>
																	</div>
																</td>
															</tr>
															<script>setJoinDateCheck();</script>
														</table>
												</td>
											</tr>
											<tr>
												<td colspan="3" width="100%"><input type="checkbox"
													name="empTypeCheck" class="checkbox" id="empTypeCheck"
													disabled="disabled" value="N"
													onclick="return callEmpTypeCheck();"></input> <script>setEmpTypeCheck();</script><label
													name="based.on.emp.type" class="set" id="based.on.emp.type"
													ondblclick="callShowDiv(this);"><%=label.get("based.on.emp.type")%></label>
												</td>
											</tr>
											<tr>
												<td>
													<div id="empTypeDiv">
														<table border="0" width="100%">
															<tr>
																<td width="25%" class="formtext" colspan="1"
																	nowrap="nowrap"><label name="select.employee.type"
																	class="set" id="select.employee.type"
																	ondblclick="callShowDiv(this);"><%=label.get("select.employee.type")%></label>
																	:</td>
																<td width="25%" colspan="1" nowrap="nowrap"><s:optiontransferselect
																		doubleDisabled="true" disabled="true" size="10"
																		doubleSize="10" doubleId="selTypeId" id="availTypeId"
																		label="Employee Type" rightTitle="Selected Type"
																		leftTitle="Available Type" addToLeftLabel="<< Remove"
																		addToRightLabel="Add >>"
																		addAllToLeftLabel="Remove All"
																		addAllToRightLabel="Add All"
																		selectAllLabel="Select All" cssStyle="width:100px"
																		doubleCssStyle="width:100px" name="availType"
																		multiple="true" buttonCssClass="token"
																		list="%{hashmapType}" doubleName="selType"
																		doubleList="%{hashmapTypeSel}" /></td>
																<td width="25%" class="formtext" colspan="1"></td>
																<td width="25%" colspan="1" nowrap="nowrap"></td>
															</tr>
														</table>
													</div>
												</td>
											</tr>
											<tr>
												<td colspan="3" width="100%"><input type="checkbox"
													disabled="disabled" name="empGradeCheck" class="checkbox"
													id="empGradeCheck" value="N"
													onclick="return callEmpGradeCheck();"></input> <script>setEmpGradeCheck();</script><label
													name="based.on.emp.grade" class="set"
													id="based.on.emp.grade" ondblclick="callShowDiv(this);"><%=label.get("based.on.emp.grade")%></label>
												</td>
											</tr>
											<tr>
												<td>
													<div id="empGradeDiv">
														<table border="0" width="100%">
															<tr>
																<td width="25%" class="formtext" colspan="1"
																	nowrap="nowrap"><label
																	name="select.employee.grade" class="set"
																	id="select.employee.grade"
																	ondblclick="callShowDiv(this);"><%=label.get("select.employee.grade")%></label>
																	:</td>
																<td width="25%" colspan="1" nowrap="nowrap"><s:optiontransferselect
																		doubleDisabled="true" disabled="true" size="10"
																		doubleSize="10" doubleId="selGradeId"
																		id="availGradeId" label="Employee Grade"
																		rightTitle="Selected Grade"
																		leftTitle="Available Grade" addToLeftLabel="<< Remove"
																		addToRightLabel="Add >>"
																		addAllToLeftLabel="Remove All"
																		addAllToRightLabel="Add All"
																		selectAllLabel="Select All" cssStyle="width:100px"
																		doubleCssStyle="width:100px" doubleName="selGrade"
																		multiple="true" buttonCssClass="token"
																		doubleList="%{hashmapGradeSel}" name="availGrade"
																		list="%{hashmapGrade}" /></td>
																<td width="25%" class="formtext" colspan="1"></td>
																<td width="25%" colspan="1" nowrap="nowrap"></td>
															</tr>
														</table>
													</div>
												</td>
											</tr>
											<tr>
												<td colspan="3" width="100%"><input type="checkbox"
													disabled="disabled" name="empDeptCheck" class="checkbox"
													id="empDeptCheck" value="N"
													onclick="return callEmpDeptCheck();"></input> <script>setEmpDeptCheck();</script><label
													name="based.on.emp.dept" class="set" id="based.on.emp.dept"
													ondblclick="callShowDiv(this);"><%=label.get("based.on.emp.dept")%></label>
												</td>
											</tr>
											<tr>
												<td>
													<div id="empDeptDiv">
														<table border="0" width="100%">
															<tr>
																<td width="25%" class="formtext" colspan="1"
																	nowrap="nowrap"><label name="select.employee.dept"
																	class="set" id="select.employee.dept"
																	ondblclick="callShowDiv(this);"><%=label.get("select.employee.dept")%></label>
																	:</td>
																<td width="25%" colspan="1" nowrap="nowrap"><s:optiontransferselect
																		doubleDisabled="true" disabled="true" size="10"
																		doubleSize="10" doubleId="selDeptId" id="availDeptId"
																		label="Employee Department"
																		rightTitle="Selected Department"
																		leftTitle="Available Department"
																		addToLeftLabel="<< Remove" addToRightLabel="Add >>"
																		addAllToLeftLabel="Remove All"
																		addAllToRightLabel="Add All"
																		selectAllLabel="Select All" cssStyle="width:100px"
																		doubleCssStyle="width:100px" doubleName="selDept"
																		multiple="true" buttonCssClass="token"
																		doubleList="%{hashmapDeptSel}" name="availDept"
																		list="%{hashmapDept}" /></td>
																<td width="25%" class="formtext" colspan="1"></td>
																<td width="25%" colspan="1" nowrap="nowrap"></td>
															</tr>
														</table>
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td width="78%"><input type="button" class="back"
						value=" Back " onclick="return callBack();" /></td>
					<td width="22%">
						<div align="right">
							<font color="red">*</font> Indicates Required
						</div>
					</td>
				</tr>
			</s:else>
		</tr>
	</table>
	<s:hidden name="joinDate" />
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>

<script type="text/javascript">
//autoDate();
	function callBack(){
		var apprId = document.getElementById('paraFrm_appraisalId').value;
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").action="AppraisalCalendar_callForEdit.action?apprId="+apprId;
		document.getElementById('paraFrm').submit();
	}
	function callChkScore()
	{
		if(document.getElementById('isScoreShow').checked)
		{
			document.getElementById('paraFrm_hiddenisScoreShow').value="Y";
		}else
		{
			document.getElementById('paraFrm_hiddenisScoreShow').value="N";
		}
	}
	onload();

/* ======================================================================
	Function	: callChk
	Explanation : change the status of check box as mouse clicked on check box
	========================================================================== */

	function selectList()
	{
		var selId = document.getElementById('selTypeId');
		var availId = document.getElementById('availTypeId');
		selectAllOptionsList(selId,availId);
		
		 selId = document.getElementById('selGradeId');
		 availId = document.getElementById('availGradeId');
		selectAllOptionsList(selId,availId);
		
		 selId = document.getElementById('selDeptId');
		 availId = document.getElementById('availDeptId');
		selectAllOptionsList(selId,availId);
		
		selId = document.getElementById('selDivId');
		 availId = document.getElementById('availDivId');
		selectAllOptionsList(selId,availId);
	}

	function deSelectList()
	{
		
		var selId = document.getElementById('selTypeId');
		var availId = document.getElementById('availTypeId');
		deSelectAllOptions(selId,availId);
		
		 selId = document.getElementById('selGradeId');
		 availId = document.getElementById('availGradeId');
		deSelectAllOptions(selId,availId);
		
		 selId = document.getElementById('selDeptId');
		 availId = document.getElementById('availDeptId');
		 
		deSelectAllOptions(selId,availId);
		
		selId = document.getElementById('selDivId');
		 availId = document.getElementById('availDivId');
		deSelectAllOptions(selId,availId);
	}
	function deSelectAllOptions(selId,availId){
			
			try{
			for(i=0;i<selId.length;i++)
			selId.options[i].selected=false;
			for(i=0;i<availId.length;i++)
			availId.options[i].selected=false;
		
		}catch(e)
		{
			alert(e);
		}
	}
	function selectAllOptionsList(selId,availId){
			
			try{
			for(i=0;i<selId.length;i++)
			selId.options[i].selected=true;
			for(i=0;i<availId.length;i++)
			availId.options[i].selected=true;
		
		}catch(e)
		{
			alert(e);
		}
	}
	function cancelFun(){
			document.getElementById("paraFrm").action="AppraisalCalendar_cancel.action";
			document.getElementById("paraFrm").submit();
	}
	
	function addnewFun(){
			document.getElementById("paraFrm").action="AppraisalCalendar_addNew.action";
			document.getElementById("paraFrm").submit();
	}
	function editFun(){
	
	
			if(document.getElementById("paraFrm_isStarted").value=="Y"){
				alert("Appraisal process has been started.\You can't edit the calendar.");
				return false;
			}
			document.getElementById("paraFrm").action="AppraisalCalendar_edit.action";
			document.getElementById("paraFrm").submit();
	}
	function searchFun(){
			javascript:callsF9(500,325,'AppraisalCalendar_f9Search.action');	
	}

	function viewEligibleEmp(){
		
		document.getElementById("paraFrm").target="main";
		document.getElementById("paraFrm").action="AppraisalCalendar_viewEligibleEmp.action";
		document.getElementById("paraFrm").submit();	
		document.getElementById("paraFrm").target="main";
	}

	  function autoDate () {
		var tDay = new Date();
		var tMonth = tDay.getMonth()+1;
		var tDate = tDay.getDate();
			document.getElementById("importDiv").style.display ='none';
			document.getElementById("importContentDiv").style.display ='none';
			document.getElementById("joinDateDiv").style.display ='none';
			document.getElementById("empTypeDiv").style.display ='none';
			document.getElementById("empGradeDiv").style.display ='none';
			document.getElementById("empDeptDiv").style.display ='none';
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10) tDate = "0"+tDate;
			if(document.getElementById('paraFrm_code').value=="")
		document.getElementById("paraFrm_assignDate1").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
					//alert("date"+document.getElementById("paraFrm_vchDate").value);
	}

	function deleteFun(){
			var conf=confirm("Do you really want to delete this calendar ?");
  			if(conf) {
  				document.getElementById("paraFrm").action="AppraisalCalendar_delete.action";
				document.getElementById("paraFrm").submit();
  			}else{
  				return false;
  			}
		}
	function onload(){
	//pgshow();
		if(document.getElementById('paraFrm_hiddenisScoreShow').value=='N')	
		   {
		   		
		   		document.getElementById('isScoreShow').checked=false;
		   		
		   }else if(document.getElementById('paraFrm_hiddenisScoreShow').value=='Y')
		   {
		   		
		   		document.getElementById('isScoreShow').checked=true;
		   }
		if(document.getElementById("paraFrm_hideJoinDateCheck").value=="Y"){
		document.getElementById("joinDateDiv").style.display ='';
		}
		var condn = document.getElementById("paraFrm_joinDateCondition").value;
			if(condn=="2"){
				document.getElementById("joinDateToDiv").style.display ='';
				document.getElementById("joinDateFromDiv").style.display ='none';
			}else{
				document.getElementById("joinDateToDiv").style.display ='none';
				document.getElementById("joinDateFromDiv").style.display ='';
		}
		if(document.getElementById("paraFrm_hideEmpTypeCheck").value=="Y"){
				document.getElementById("empTypeDiv").style.display ='';
			}else{
			document.getElementById("empTypeDiv").style.display ='none';
			}
			if(document.getElementById("paraFrm_hideEmpDivCheck").value=="Y"){
				document.getElementById("divisionDiv").style.display ='';
			}else{
			document.getElementById("divisionDiv").style.display ='none';
			}
		if(document.getElementById("paraFrm_hideEmpGradeCheck").value=="Y"){
				document.getElementById("empGradeDiv").style.display ='';
			}else{
			document.getElementById("empGradeDiv").style.display ='none';
			}
		if(document.getElementById("paraFrm_hideEmpDeptCheck").value=="Y"){
				document.getElementById("empDeptDiv").style.display ='';
			}else{
			document.getElementById("empDeptDiv").style.display ='none';
			}
			deSelectList();
		document.getElementById("importDiv").style.display ='none';
		document.getElementById("importContentDiv").style.display ='none';
	}

	function callCheckBox(i)
	   {
	   
	   if(document.getElementById('deleteChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=document.getElementById('appraisalIdList'+i).value;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
   }
   function deleteMultiple()
	{
	var checkFlag="false";
	var flag=document.getElementById("count").value;
	for(var x=1;x<=flag;x++){
		if(document.getElementById('deleteChk'+x).checked){
			checkFlag="true";
			break;
		}
		
	}
	if(checkFlag =="false"){
		alert("Please check atleast one record to delete.");
		return false;
	}else{
	
	 
	 var con=confirm('Do you really want to delete these records ?');
	 if(con){
	   document.getElementById('paraFrm').action="AppraisalCalendar_deleteMultipleCalendar.action";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    {     
	    document.getElementById('allChk').checked=false;
	    for(var a=1;a<=flag;a++){	
	    document.getElementById('deleteChk'+a).checked=false;
	    document.getElementById('hdeleteCode'+a).value="";
	    
	 	}
	     return false;
	     }
	 }
	 
	}
   
function callForEdit(apprId){
	document.getElementById("paraFrm_paraId").value=apprId;	
	document.getElementById("paraFrm").action="AppraisalCalendar_callForEdit.action?apprId="+apprId;
	////document.getElementById("paraFrm").action="AppraisalCalendar_callForEdit.action";
	document.getElementById("paraFrm").submit();
}
	function newRowColor(cell)
   	 {
   	 
			cell.className='onOverCell';

	}
	function oldRowColor(cell,val) {
	
		if(val=='1'){
			 cell.className='tableCell2';
			}
		else 
			cell.className='tableCell1';
		
	}
	
	/*function callPage(id){
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').action="AppraisalCalendar_paging.action";
		document.getElementById('paraFrm').submit();
   }*/
   
   function callAllCheck(){
   		var flag=document.getElementById("count").value;
   		var checkedFlag = document.getElementById("allChk").checked;
   		
   			for(var x=1;x<=flag;x++){
   			
   			document.getElementById('deleteChk'+x).checked = checkedFlag;
   			if(checkedFlag){
   			var apprId = document.getElementById('appraisalIdList'+x).value;
			document.getElementById('hdeleteCode'+x).value=apprId;
			}else{
				document.getElementById('hdeleteCode'+x).value="";
			}
		}
	}
	
   /*function next()
   {
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1")
   	{	document.getElementById('myPage').value=2;
	 document.getElementById('paraFrm_show').value=2;
	 }
	 else{
	 document.getElementById('myPage').value=eval(pageno)+1;
	 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	 }
	   document.getElementById('paraFrm').action="AppraisalCalendar_paging.action";
	   document.getElementById('paraFrm').submit();
   }
	//-----function for previous
  	 function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="AppraisalCalendar_paging.action";
	   document.getElementById('paraFrm').submit();
	   
   }
  	function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="AppraisalCalendar_paging.action";
	   
	   document.getElementById('paraFrm').submit();
   }
  	
  	function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	}*/
	
	function callForPhaseConfig(appraisalIdList){
    	
    	
  	   	document.getElementById("paraFrm").action="AppraisalCalendar_callForPhaseConfig.action?appraisalIdList="+appraisalIdList;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
    }
    
    function callForAppraisalSchedule(appraisalIdList){ 
    		
  	   	document.getElementById("paraFrm").action="AppraisalSchedule_calforedit.action?appraisalIdList="+appraisalIdList;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
    }
    
    function callForRatingScaleDef(appraisalIdList){ 
    	   	
  	   	document.getElementById("paraFrm").action="RatingScaleDefinition_edit.action?appraisalIdList="+appraisalIdList;
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
    }
    
    function callForAppraiserConfig(appraisalIdList){ 
    	   	
  	   	document.getElementById("paraFrm").action="AppraiserConfig_setApprGroupList.action?appraisalIdList="+appraisalIdList+"&menuCode=748";
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
    }
     function callForAppraisalFormDesigner(appraisalIdList){ 
    	   	
  	   	document.getElementById("paraFrm").action="TemplateDefination_input.action?appraisalIdList="+appraisalIdList+"&menuCode=748";
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
    }
</script>
