<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/admin/leave/Ajax.js"></script>
<script language="javascript"
	src="../pages/admin/leave/mootools.v1.11.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script language="javascript"
	src="../pages/admin/leave/nogray_time_picker_min.js"></script>
<s:form action="Regularization" method="post" name="LeavePolicy"
	id="paraFrm" theme="simple" target="main">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<input type="hidden" name="time2" id="time2" onchange="aaa()" />
		<s:hidden name="source" id="source" />
		<s:hidden name="month_old" />
		<s:hidden name="year" />
		<s:hidden name="status" />
		<s:hidden name="shiftCode" />
		<s:hidden name="empGender" />
		<s:hidden name="policyCode" />
		<s:hidden name="applyFor" />
		<s:hidden name="condTrueFlag" />
		<s:hidden name="countValue" />
		<s:hidden name="redressalFlag" />
		<s:hidden name="lateFlag" />
		<s:hidden name="regularizationApplCode" />
		<s:hidden name="applicationCode" />
		<s:hidden name="actionName" />
		<s:hidden name="sendBackActionName" />
		<s:hidden name="actAddQuesPageSwipe" />
		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					<s:if test="swipeFlag">Regularization Application Form</s:if> <s:if
						test="lateFlag"> Late Regularization </s:if> <s:if
						test="redressalFlag">Redressal Application  </s:if> <s:if
						test="personalTimeFlag">Personal Time Application  </s:if> </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:if test="viewApplFlag">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></s:if>
					<s:else>
						<input type="button" class="token" id="addButton"
							value="  Send For Approval"
							onclick="return sendForApprovalMethod();" />
						<input type="button" value="Back" onclick="backFun();" />
						<!--<s:submit cssClass="back"
							action="Regularization_%{sendBackActionName}" value="   Back " />-->
					</s:else></td>
					<td width="22%" valign="middle">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Employee Details </strong></td>
				</tr>
				<tr>
					<td width="22%" height="22" class="formtext"><label
						name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:<font color="red">*</font></td>
					<td height="22" colspan="4">
					<table width="100%" cellpadding="0" cellspacing="0">
						<tr>
							<td width="14%" colspan="1"><s:textfield name="empToken"
								size="10" readonly="true" /></td>
							<td width="86%" colspan="3"><s:hidden name="empCode" /> <s:textfield
								name="empName" size="85" readonly="true" /></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="22" class="formtext"><label name="branch"
						id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
					<td width="28%" height="22"><s:textfield name="empBranch"
						size="30" readonly="true" /></td>

					<td class="formtext" colspan="2" align="center"><label
						name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
					<td width="38%"><s:textfield name="empDesg" size="30"
						readonly="true" /></td>
				</tr>
				<tr>
					<td height="22" class="formtext" nowrap="nowrap"><label
						class="set" name="applied.date" id="applied.date"
						ondblclick="callShowDiv(this);"><%=label.get("applied.date")%></label>
					:</td>
					<td width="28%" height="22"><s:textfield
						name="applicationDate" size="30" readonly="true" /></td>
					<td class="formtext" colspan="3">&nbsp;</td>
				</tr>
				<!--<s:if test="swipeFlag">
					<tr>
						<td height="22" class="formtext" nowrap="nowrap"><label
							class="set" name="application.purpose" id="application.purpose"
							ondblclick="callShowDiv(this);"><%=label.get("application.purpose")%></label>
						:<font color="red"> *</font>
						</div>
						</td>
						<td class="formtext" colspan="4"><s:select disabled="false" onchange="setDropDownValue();"
							name="applyForDecode" value="%{applyFor}" id="applyForDecode" 
							list="#{'-1':'--Select--','1':'Forgot to bring access card','2':'Forgot to login/logout ','3':'Access Card Not Issued','4':'Special Sanction',
				'5':'Swipe System Not Working ','6':'Outdoor visit','7':'Late Regularization'}" 
							>
						</s:select></td>
					</tr>
				</s:if>-->
			</table>
			</td>
		</tr>
		<%!int keep = 0;%>
		<tr>
			<td colspan="5">
			<table width="100%" cellpadding="1" border="0" cellspacing="1"
				id="keepInformedTable">
				<tr>
					<td colspan="2"><strong>Approver List </strong></td>
					<td width="18%"><strong>Keep Inform To : </strong></td>
					<td width="21%" align="left"><s:hidden name="informCode" /> <s:hidden
						name="informToken" /> <s:if test="viewApplFlag">
					</s:if> <s:else> &nbsp;
 						<s:textfield name="informName" size="30" readonly="true" />
					</s:else></td>
					<td width="10%" nowrap="nowrap"><s:if test="viewApplFlag">
					</s:if> <s:else>
						<img src="../pages/images/recruitment/search2.gif" width="16"
							height="15" class="iconImage"
							onclick="javascript:callsF9(500,325,'Regularization_f9informTo.action');" />
						<!--<s:submit value="  Add" cssClass="add"
							action="Regularization_addInformListForRedressal"
							onclick="return callAddInfo();"></s:submit>-->
						<input type="button" value="Add" Class="add"
							onclick="return callAddKeepInfo();">
					</s:else></td>
				</tr>
				<tr>
					<td colspan="2" valign="top">
					<table width="100%" cellpadding="1" cellspacing="1" border="0"
						bordercolor="red">
						<%
						int ap = 0;
						%>
						<s:iterator value="approverList">
							<tr>
								<td width="20%" nowrap="nowrap"><s:property
									value="apprSrNo" /><s:hidden name="approverCode" /></td>
								<td><s:property value="approverName" /><s:hidden
									name="approverName" /></td>
							</tr>
							<%
							ap++;
							%>
						</s:iterator>
						<%
						keep = ap;
						%>
					</table>
					</td>
					<td width="18%" align="right" valign="top"></td>
					<td width="16%" colspan="2" valign="top"><s:hidden
						name="keepHidden" />
					<table width="100%" cellpadding="0" cellspacing="0" border="0">
						<%
						int kep = 0;
						%>
						<s:iterator value="informList">
							<tr>
								<td>&nbsp;</td>
								<s:hidden name="keepInformCode" />
								<td nowrap="nowrap"><s:property value="keepInform" /><s:hidden
									name="keepInform" /></td>
								<s:if test="regularization.viewApplFlag">
								</s:if>
								<s:else>
									<td align="right"><b> <a href="#"
										onclick="callRemoveKeep('<%=kep%>')">Remove</a> </b></td>
								</s:else>
							</tr>
							<%
							kep++;
							%>
						</s:iterator>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!--    CODING FOR PERSONAL TIME -->
		<tr id="shoeAddRowId" style="display: none;">
			<td><input type="button" id="accomAdd" value="   Add   "
				Class="token" theme="simple" onclick="addRow();" /></td>
		</tr>
		<%!int t = 0;%>
		<s:if test="lateFlag">
			<tr>
				<td colspan="5">
				<table width="100%" cellpadding="1" class="formbg" border="0">
					<tr>
						<td width="15%" colspan="1" align="center" class="formth"><label
							class="set" name="date" id="date111"
							ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
						<td width="13%" colspan="1" align="center" class="formth"
							nowrap="nowrap"><label class="set" name="shift.time"
							id="shift.name11" ondblclick="callShowDiv(this);"><%=label.get("shift.time")%></label></td>
						<td width="12%" colspan="1" align="center" class="formth"
							nowrap="nowrap"><label class="set" name="in.time"
							id="in.time" ondblclick="callShowDiv(this);"><%=label.get("in.time")%></label>
						</td>
						<td width="11%" align="center" class="formth" nowrap="nowrap"><label
							class="set" name="late.hrs" id="late.hrs"
							ondblclick="callShowDiv(this);"><%=label.get("late.hrs")%></label>
						</td>
						<td width="13%" colspan="1" align="center" class="formth"
							nowrap="nowrap"><label class="set"
							name="late.hrs.to.be.deducted" id="late.hrs.to.be.deducted"
							ondblclick="callShowDiv(this);"><%=label.get("late.hrs.to.be.deducted")%></label>
						</td>
						<td width="13%" colspan="1" align="center" class="formth"
							nowrap="nowrap"><label class="set"
							name="late.hrs.to.be.deducted.from"
							id="late.hrs.to.be.deducted.from" ondblclick="callShowDiv(this);"><%=label.get("late.hrs.to.be.deducted.from")%></label>
						</td>
						<td width="13%" colspan="1" align="center" class="formth"><s:if
							test="viewApplFlag"> &nbsp;</s:if> <s:else>
							<s:submit name="submit" cssClass="delete"
								action="Regularization_deleteReg" value="  Remove"
								onclick="return callDeleteLate();"></s:submit>
							<input type="checkbox" id="applyAll" name="checkbox2"
								onclick="callCheckAll()" />
						</s:else></td>
					</tr>
					<%
					int i = 0;
					%>
					<s:iterator value="regList">

						<tr id="<%=i%>">
							<td align="center" class="sortableTD"><s:property
								value="date" /><s:hidden name="date" /></td>
							<td align="center" class="sortableTD"><s:property
								value="shiftTime" /><s:hidden name="shiftTime" /></td>
							<td align="center" class="sortableTD"><s:property
								value="inTime" /><s:hidden name="inTime" /></td>
							<td align="center" class="sortableTD"><s:property
								value="lateHrs" /><s:hidden name="lateHrs" /></td>
							<td align="center" class="sortableTD"><s:property
								value="lateHrsDeduct" /><s:hidden name="lateHrsDeduct" /></td>
							<td align="center" class="sortableTD"><s:property
								value="lateHrsDeductFrom" /><s:hidden name="lateHrsDeductFrom" /><s:hidden
								name="lateHrsDeductFromCode" id="<%="lateHrsDeductFromCode"+i%>" /></td>
							<td align="center" class="sortableTD"><input type="checkbox"
								id="<%="leaveCheck"+i%>" value='<s:property value="date"/>'
								name="sCheck" /></td>
						</tr>
						<%
						i++;
						%>
					</s:iterator>
					<%
					t = i;
					%>
					<tr>
						<td width="15%" colspan="1" align="center"></td>
						<td width="13%" colspan="1" align="center"></td>
						<td width="12%" colspan="1" align="center"></td>
						<td width="11%" align="center"><strong>Total: </strong></td>
						<td width="13%" colspan="1" align="center"><strong><s:property
							value="totalLateHrs" /></strong></td>
						<td width="13%" colspan="1" align="center">&nbsp;</td>
						<td width="13%" colspan="1" align="center"><s:hidden
							name="totalLateHrsInMin" /></td>
					</tr>
				</table>
				</td>
			</tr>
			<%
			int kk = 0;
			%>
			<s:if test="viewApplFlag"> &nbsp;</s:if>
			<s:else>
				<s:if test="sendBackFlag"> &nbsp;</s:if>
				<s:else>
					<tr>
						<td colspan="5">
						<table width="100%" cellpadding="1" cellspacing="1" class="formbg">
							<tr>
								<td width="41%" colspan="3" align="center" class="formth"><label
									class="set" name="leave.type" id="leave.type"
									ondblclick="callShowDiv(this);"><%=label.get("leave.type")%></label></td>
								<td width="30%" colspan="1" align="center" class="formth"><label
									class="set" name="balance" id="balance"
									ondblclick="callShowDiv(this);"><%=label.get("balance")%></label></td>
								<td width="24%" colspan="2" align="center" class="formth"><label
									class="set" name="available.balance" id="available.balance"
									ondblclick="callShowDiv(this);"><%=label.get("available.balance")%></label></td>
							</tr>
							<s:iterator value="bList">
								<tr>
									<td width="41%" colspan="3" align="left" class="sortableTD"><s:hidden
										name="sLeaveCode" /><s:property value="sLeaveType" /></td>
									<td width="12%" colspan="1" align="center" class="sortableTD">
									<s:property value="sBalance" /></td>
									<td width="24%" align="center" colspan="2" class="sortableTD"><s:property
										value="remainingBalance" /><s:hidden name="remainingBalance" />
									</td>
								</tr>
							</s:iterator>
							<tr>
								<td width="41%" colspan="3" align="center"><strong>Total</strong></td>
								<td width="30%" colspan="1" align="center"><strong>
								<s:property value="totalBalance" /></strong></td>
								<td width="24%" align="center" colspan="2"><strong><s:property
									value="sClosingbalance" /></strong></td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="5" align="right"><s:hidden
							name="totalBalanceInMin" /><s:hidden name="difference" /></td>
					</tr>
				</s:else>
			</s:else>
		</s:if>
		<%!int tt = 0;%>
		<s:if test="swipeFlag">
			<tr>
				<td colspan="5">
				<table width="100%" cellpadding="1" class="formbg"
					id="regularizationTableId" border="0" bordercolor="red">
					<tr>
						<td width="15%" colspan="2" align="center" class="formth"><label
							class="set" name="date" id="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
						<td width="15%" colspan="1" align="center" class="formth"
							nowrap="nowrap"><label class="set" name="recorded.in.time"
							id="recorded.in.time" ondblclick="callShowDiv(this);"><%=label.get("recorded.in.time")%></label></td>
						<td width="15%" colspan="1" align="center" class="formth"
							nowrap="nowrap"><label class="set" name="recorded.out.time"
							id="recorded.out.time" ondblclick="callShowDiv(this);"><%=label.get("recorded.out.time")%></label></td>
						<td width="15%" align="center" class="formth" nowrap="nowrap"><label
							class="set" name="actual.in.time" id="actual.in.time"
							ondblclick="callShowDiv(this);"><%=label.get("actual.in.time")%></label>
						</td>
						<td width="15%" colspan="1" align="center" class="formth"
							nowrap="nowrap"><label class="set" name="actual.out.time"
							id="actual.out.time" ondblclick="callShowDiv(this);"><%=label.get("actual.out.time")%></label></td>

						<td width="15%" colspan="1" align="center" class="formth">Reason</td>
						<td width="10%" colspan="1" align="center" class="formth"><s:if
							test="viewApplFlag"> &nbsp;</s:if> <s:else>
							<!--<s:submit name="submit" cssClass="delete"
								action="Regularization_deleteReg" value="  Remove"
								onclick="return callDeleteSwipe();"></s:submit>
							<input type="checkbox" id="swipeCheck" name="checkbox2"
								onclick="callCheckAllSwipe()" />-->
						</s:else></td>
					</tr>
					<%
					int s = 0;
					%>
					<s:iterator value="swipeList">
						<tr>
							<td align="center" class="sortableTD" colspan="2"><s:property
								value="date" /><s:hidden name="date" /></td>
							<td align="center" class="sortableTD"><s:property
								value="shiftTime" /><s:hidden name="shiftTime" /></td>
							<td align="center" class="sortableTD"><s:property
								value="inTime" /><s:hidden name="inTime" /></td>
							<td align="center" nowrap="nowrap" class="sortableTD"><s:textfield
								name="fromTime" id="<%="fromTime"+s%>" size="05" maxlength="5"
								onkeypress="return numbersonlyWithColun(this);" />(HH24:MI)</td>
							<td align="center" nowrap="nowrap" class="sortableTD"><s:textfield
								name="toTime" id="<%="toTime"+s%>" size="05" maxlength="5"
								onkeypress="return numbersonlyWithColun(this);" />(HH24:MI)</td>

							<td align="center" nowrap="nowrap" class="sortableTD"><s:select
								name="reasonItt" value="%{reasonItt}" id="<%="reasonItt"+s%>"
								list="#{'-1':'---Select---','1':'Forgot to bring access card','2':'Forgot to login/logout ','3':'Access Card Not Issued','4':'Special Sanction',
										'5':'Swipe System Not Working ','6':'Outdoor visit','7':'Late Regularization','8':'Work From Home'}" />
							</td>

							<td align="center" class="sortableTD">&nbsp; <!--<input type="checkbox"
								id="<%="swipe"+s%>" value='<s:property value="date"/>'
								name="sCheck" />--></td>
						</tr>
						<%
						s++;
						%>
					</s:iterator>
					<%
					tt = s;
					%>
				</table>
				</td>
			</tr>

		</s:if>
		<%!int redCount = 0;%>
		<s:if test="redressalFlag">
			<tr>
				<td colspan="6">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td width="9%" class="formth"><label class="set" name=""
							id="" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
						<td width="15%" class="formth"><label class="set"
							name="leave.type" id="leave.type345"
							ondblclick="callShowDiv(this);"><%=label.get("leave.type")%></label></td>
						<td width="15%" class="formth"><label class="set"
							name="from.date" id="from.date35" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label></td>
						<td width="15%" class="formth"><label class="set"
							name="to.date" id="to.date22" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label></td>
						<td width="15%" class="formth"><label class="set"
							name="penalty.days" id="penalty.days"
							ondblclick="callShowDiv(this);"><%=label.get("penalty.days")%></label>
						</td>
						<s:if test="viewApplFlag">
							<td width="15%" class="formth"><label class="set"
								name="redressal.days" id="redressal.days"
								ondblclick="callShowDiv(this);"><%=label.get("redressal.days")%></label>
							</td>
						</s:if>
						<td width="16%" class="formth"><s:if test="viewApplFlag"> &nbsp;</s:if>
						<s:else>
							<s:submit name="submit" cssClass="delete"
								action="Regularization_deleteRedressal" value="  Remove"
								onclick="return callDeleteRed();"></s:submit>

							<input type="checkbox" id="redressal" name="checkbox2"
								onclick="callCheckAllRed()" />
						</s:else></td>
					</tr>
					<%
					int red = 0;
					%>

					<s:iterator value="list">
						<tr>
							<td width="9%" align="center" class="sortableTD"><%=red + 1%>
							</td>
							<td width="15%" align="center" class="sortableTD"><s:property
								value="leaveType" /><s:hidden name="rLeaveCode" /></td>
							<td width="15%" align="center" class="sortableTD"><s:property
								value="rFromDate" /><s:hidden name="rFromDate" /></td>
							<td width="15%" align="center" class="sortableTD"><s:property
								value="rToDate" /><s:hidden name="rToDate" /></td>
							<td width="15%" align="center" class="sortableTD"><s:property
								value="rPenaltyDays" /><s:hidden name="rPenaltyDays"
								id="<%="penDays"+red%>" /></td>
							<s:hidden name="redressalAdjDays" />
							<s:if test="regularization.viewApplFlag">
								<td width="15%" align="center" class="sortableTD"><input
									type="text" name="rrdressalDays" size="05"
									id="<%="redreDays"+red%>"
									value="<s:property value="rrdressalDays"/>" readonly="readonly" />
								</td>
							</s:if>
							<s:else>
								<input type="hidden" name="rrdressalDays"
									id="<%="redreDays"+red%>"
									value="<s:property value="rrdressalDays"/>" readonly="readonly" />
							</s:else>
							<td width="16%" align="center" class="sortableTD"><input
								type="checkbox" name="lateCheckBox" id="<%="redre"+red%>"
								value='<s:property value="rFromDate"/>' /></td>
						</tr>
						<%
						red++;
						%>
					</s:iterator>
					<%
					redCount = red;
					%>
					<%
					if (redCount == 0) {
					%>
					<tr>
						<td colspan="6" align="center"><font color="red">
						There in no data to display</font></td>
					</tr>
					<%
					}
					%>
				</table>
				</td>
			</tr>
		</s:if>
		<tr>
			<s:if test="redressalFlag">
				<td nowrap="nowrap"><label name="reason.for.redressal"
					id="reason.for.redressal" ondblclick="callShowDiv(this);"><%=label.get("reason.for.redressal")%></label>:</td>
			</s:if>
			<s:else>
				<td nowrap="nowrap" id="reasonLabel" style="display: none;">
				Comments :<font color="red">*</font></td>
			</s:else>
			<td colspan="4" align="left"><s:textarea name="reason" cols="90"
				rows="4"></s:textarea></td>
		</tr>
		<s:if test="commentsFlag">
			<tr>
				<td colspan="5"><b>Approver Comments:</b></td>
			</tr>
			<tr>
				<td colspan="5">
				<%
				int app = 0;
				%>
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td colspan="1" class="formth"><label class="set" name="sno"
							id="sno33333" ondblclick="callShowDiv(this);"><%=label.get("sno")%></td>
						<td colspan="2" class="formth"><label class="set"
							name="approver.name" id="approver.name4555"
							ondblclick="callShowDiv(this);"><%=label.get("approver.name")%></label>
						</td>
						<td colspan="2" class="formth"><label class="set"
							name="approver.comments" id="approver.comments234"
							ondblclick="callShowDiv(this);"><%=label.get("approver.comments")%></label>
						</td>
					</tr>
					<s:iterator value="viewApproverList">
						<tr>
							<td colspan="1" align="center" class="sortableTD"><%=++app%>
							</td>
							<td colspan="2" class="sortableTD"><s:property
								value="approverNameView" /></td>
							<td colspan="2" class="sortableTD"><s:property
								value="apprverComments" /></td>
						</tr>
					</s:iterator>
				</table>
				</td>
			</tr>
		</s:if>

		<tr>
			<td colspan="5">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:if test="viewApplFlag"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></s:if> <s:else>

						<input type="button" class="token" id="addButton"
							value="  Send For Approval"
							onclick="return sendForApprovalMethod();" />


						<input type="button" value="Back" onclick="backFun();" />
						<!--  	<input type="button" class="token" id="addButton1"
							value="  Send For Approval" onclick="return callApplySwipe()" />
						<s:submit cssClass="back"
							action="Regularization_showRegularizationList" value="   Back 11 " />-->
					</s:else></td>
					<td width="22%" valign="middle"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
function sendForApprovalMethod(){
  	var tbl = document.getElementById('regularizationTableId');
  	var lastRow = tbl.rows.length;		  
  	if(lastRow>1){		  
  	}
  	else{
	  	alert("You can not send this application,please add one or more record ");
		return false;
  	}
  	if(document.getElementById('source').value=='attendanceRegularization' || document.getElementById('source').value=='myservices'){
		return callForAttendanceRegularization();
  	}
  	else{ 
 		return callForTimecard();
  	}
}

function setDropDownValue(){
  document.getElementById('paraFrm_applyFor').value= document.getElementById('applyForDecode').value;
}

function addRow(){	
		addRowToRegularization();	
}
	
function addRowToRegularization(){		
		  var tbl = document.getElementById('regularizationTableId');
		  var lastRow = tbl.rows.length;
		  // if there's no header row in the table, then iteration = lastRow + 1
		  var iteration = lastRow-1;
		  var row = tbl.insertRow(lastRow);
		  // left cell	  
   		  var cell0 = row.insertCell(0);
      	  var column0 = document.createElement('input');
      	  cell0.className='sortableTD';
		  column0.type = 'text';
		  column0.name = 'date';
		  column0.id = 'paraFrm_date'+iteration;
		  column0.size='6';
		  column0.maxLength='20';
		  cell0.align='left';
		  cell0.appendChild(column0);
		 
		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('img');
		 
		  column1.type='image';
		  column1.src="../pages/images/recruitment/Date.gif";
		  column1.align='center';
		  column1.id='img'+ iteration;
		  column1.theme='simple';
		  column1.onclick=function(){
		  try {
				NewCal('paraFrm_date'+iteration,'DDMMYYYY');
		  }catch(e){}
		  };
		  cell1.appendChild(column1);
		  
		  var cell2 = row.insertCell(2);
      	  var column2= document.createElement('input');
      	  cell2.className='sortableTD';
		  column2.type = 'text';
		  column2.readOnly = 'true';
		  column2.name = 'shiftTime';
		  column2.id = 'paraFrm_shiftTime'+iteration;
		  column2.value = '00:00';
		  column2.size='6';
		  column2.maxLength='20';
		  cell2.align='right';
		  cell2.appendChild(column2);
		  
		  var cell3 = row.insertCell(3);
      	  var column3 = document.createElement('input');
      	  cell3.className='sortableTD';
		  column3.type = 'text';
		  column3.readOnly = 'true';
		  column3.name = 'inTime';
		  column3.id = 'paraFrm_inTime'+iteration;
		  column3.value = '00:00';
		  column3.size='6';
		  column3.maxLength='20';
		  cell3.align='center';
		  cell3.appendChild(column3);
		  
		  var cell4 = row.insertCell(4);
      	  var column4 = document.createElement('input');
      	  cell4.className='sortableTD';
		  column4.type = 'text';
		  column4.name = 'fromTime';
		  column4.id = 'paraFrm_fromTime'+iteration;
		  column4.size='6';
		  column4.maxLength='5';
		  cell4.align='center';
		  cell4.appendChild(column4);
		  		 
		  var cell5 = row.insertCell(5);
      	  var column5 = document.createElement('input');
      	  cell5.className='sortableTD';
		  column5.type = 'text';
		  column5.name = 'toTime';
		  column5.id = 'paraFrm_toTime'+iteration;
		  column5.size='6';
		  column5.maxLength='5';
		  cell5.align='center';
		  cell5.appendChild(column5);
		  		  
		  var cell6 = row.insertCell(6);
		  var column6 = document.createElement('SELECT');
		  column6.name = 'reasonItt';
	  	  column6.id = 'paraFrm_reasonItt'+iteration;
		  cell6.appendChild(column6);
			  
		  var option = document.createElement('option');
		  option.value = '-1';
		  option.appendChild(document.createTextNode('--Select--'));
   		  column6.appendChild(option);
   			  
		  var option = document.createElement('option');
		  option.value = '1';
		  option.appendChild(document.createTextNode('Forgot to bring access card'));
   		  column6.appendChild(option);
   			  
   		  option = document.createElement('option');
		  option.value = '2';
		  option.appendChild(document.createTextNode('Forgot to login/logout'));
   		  column6.appendChild(option);
   			  
   		  option = document.createElement('option');
		  option.value = '3';
		  option.appendChild(document.createTextNode('Access Card Not Issued'));
   		  column6.appendChild(option);
		
		  option = document.createElement('option');
		  option.value = '4';
		  option.appendChild(document.createTextNode('Special Sanction'));
   		  column6.appendChild(option);
   			   			  
   		  option = document.createElement('option');
		  option.value = '5';
		  option.appendChild(document.createTextNode('Swipe System Not Working'));
   		  column6.appendChild(option);
   			    			  
   		  option = document.createElement('option');
		  option.value = '6';
		  option.appendChild(document.createTextNode('Outdoor visit'));
   		  column6.appendChild(option);
   			 
   		  option = document.createElement('option');
		  option.value = '7';
		  option.appendChild(document.createTextNode('Late Regularization'));
   		  column6.appendChild(option);
   		  
   		  option = document.createElement('option');
		  option.value = '8';
		  option.appendChild(document.createTextNode('Work From Home'));
   		  column6.appendChild(option);

		  var cell7= row.insertCell(7);
		  var column7 = document.createElement('img');
		  column7.type='image';
		  column7.src="../pages/common/css/icons/delete.gif";
		  column7.align='absmiddle';
		  column7.id='img'+ iteration;
		  column7.theme='simple';
		  cell7.align='center';
		  column7.onclick=function(){
		  try {
		    deleteCurrentRow(this);
		  } catch(e){}
		  };
		 cell7.appendChild(column7);
	}
	
function timeDifference(fromTime,toTime,fieldName,fromLabName,toLabName){   
	try{
		var fromTimeSplit  = fromTime.split(":");
		var toTimeSplit    = toTime.split(":");
		var fromHour       = parseInt(eval(fromTimeSplit[0]));
		var fromMin        = parseInt(eval(fromTimeSplit[1]));
		var toHour         = parseInt(eval(toTimeSplit[0]));
		var toMin 		   = parseInt(eval(toTimeSplit[1]));
		var hourDifference = toHour-fromHour;
		var minDifference  = toMin-fromMin;
		if(hourDifference<0){
			alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater than "+document.getElementById(fromLabName).innerHTML.toLowerCase());
			document.getElementById(fieldName).focus();
			return false;
		}if(hourDifference==0 && minDifference<=0){
			alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater than "+document.getElementById(fromLabName).innerHTML.toLowerCase());
			document.getElementById(fieldName).focus();
			return false;
		}
		return true;
	  }catch(e){return false;}
}
	
function callForAttendanceRegularization(){
		try{			
				var tot='<%=keep%> ';			
				if(eval(tot)=='0'){
					alert("Reporting structure not define");
					return false;
				}				
				var regularizationTableRows=document.getElementById("regularizationTableId").rows.length-1;
				for( var i=0; i<regularizationTableRows;i++){
 			
 				var frmTime= document.getElementById('paraFrm_fromTime'+i).value;
 				var toTm = document.getElementById('paraFrm_toTime'+i).value; 				
 				if(document.getElementById('paraFrm_date'+i).value == ""){
 					alert("Please select date");
 					return false;
 				}
 				if(!validateDate('paraFrm_date'+i,'Date')){
 					return false;
 				} 				
 				 if(document.getElementById('paraFrm_fromTime'+i).value == ""){
 					alert("Please enter Actual In Time");
 					return false;
 				} 				
 				if(!validateTime('paraFrm_fromTime'+i, 'actual.in.time')){
 					return false;
 				} 				
 				 if(document.getElementById('paraFrm_toTime'+i).value == ""){
 					alert("Please enter Actual Out Time");
 					return false;
 				} 				
 				if(!validateTime('paraFrm_toTime'+i, 'actual.out.time')){
 					return false;
 				} 				
 				 if(document.getElementById('paraFrm_reasonItt'+i).value == "-1"){
 					alert("Please select reason");
 					return false;
 				}
 				/*var resultTime =timeDifference(frmTime,toTm, 'toTime'+i, 'actual.in.time', 'actual.out.time')
 				if(!resultTime){ 								
 						return false;		
 				}*/	 				
 			}//end of for
 			 			
 			var reason = document.getElementById('paraFrm_reason').value;			
			if(reason==""){						 
		 			alert('Please enter comments');
		 			return false;		 
		 	}
 			var actName=document.getElementById('paraFrm_actionName').value;
			actName=actName+".action";
			var con=confirm('Do you want to send this application for approval');
			if(con){					
				document.getElementById('paraFrm').action="Regularization_"+actName;	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";				
				document.getElementById('addButton').disabled=true;
				//document.getElementById('addButton1').disabled=true;				
				//return true;				
			}
			else{
				return false;
			}	
 		}catch(e){
 		}					
	}
	
function callAddKeepInfo(){
	try{
		  var keepInformCode = document.getElementById("paraFrm_informCode").value;
		  var keepInformedName = document.getElementById("paraFrm_informName").value;
		  if(keepInformedName==""){
		  	alert("Please select keep informed to");
		  	return false;
		  }
		  var tbl = document.getElementById('keepInformedTable');
		  var lastRow = tbl.rows.length;
		  var iteration = lastRow;
		  var row = tbl.insertRow(lastRow);		  
		  var cell0 = row.insertCell(0);
		  var column0 = document.createTextNode(iteration);
		//  cell0.appendChild(column0);	  
   		  var cell1 = row.insertCell(1);
		  var column1 = document.createElement('input');
  		  column1.type = 'text';
  		  column1.style.border = 'none';
		  column1.name = 'keepInform';
		  column1.value = keepInformedName; /*value to be set in the added cell*/
		  column1.id = 'keepInform'+iteration;
		  column1.size='20';
		  column1.maxLength='50';
		  column1.readOnly ="true";
		  cell1.align='left';
		  cell0.appendChild(column1);
		  
		  var cell2= row.insertCell(2);
		  var column2 = document.createElement('img');
		  column2.type='image';
		  column2.src="../pages/common/css/icons/delete.gif";
		  column2.align='absmiddle';
		  column2.id='img'+ iteration;
		  column2.theme='simple';
		  cell2.align='left';

		  column2.onclick=function(){
		  try {
		   	deleteCurrentRow(this);
		  	
		  }catch(e){alert(e);}
		  };
		  cell1.appendChild(column2);
		  
		  var column3 = document.createElement('input');
		  column3.type = 'hidden';
  		  column3.name = 'keepInformCode';
  		  column3.value = keepInformCode; /*value to be set in the added cell*/
		  column3.id = 'keepInformCode'+iteration;
		  column3.maxLength ='2';
		  cell2.appendChild(column3);
	}catch(e){}
		document.getElementById("paraFrm_informName").value="";	
 }

function deleteRows(rowObjArray){
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
 } 	
 
function deleteCurrentRow(obj){
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);		
	}

function callLabelChangeFun(){
	var appType = document.getElementById('applyForDecode').value;
	if(appType ==6){
	document.getElementById('odLabel').style.display='';
	document.getElementById('reasonLabel').style.display='none';
	}
	else{
	document.getElementById('odLabel').style.display='none';
	document.getElementById('reasonLabel').style.display='';
	}
}

function backFun(){
	try{
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
		else if(document.getElementById('source').value=='attendanceRegularization'){
		 document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_attendanceRegularization.action';
		}
		else{
		document.getElementById('paraFrm').action = 'Regularization_onLoad.action';
		}
		document.getElementById('paraFrm').submit();
	  }
	  catch(e){
		alert("Error  "+e);
	}
}

function callRemoveKeep(id){
	var r=confirm("Are you sure to remove this record?");
		if(r==false){
			return false;
		}else{
		 	document.getElementById('paraFrm_keepHidden').value=id;
	   	document.getElementById("paraFrm").action="Regularization_removeKeep.action";	  
	    document.getElementById("paraFrm").submit();
	    }
   }

function callForEditPT(id){	
	   	document.getElementById('paraFrm_ptDate').value=document.getElementById('ptDateItt'+id).value;
	   	document.getElementById('paraFrm_ptFromTime').value=document.getElementById('ptFromTimeItt'+id).value;
	   	document.getElementById('paraFrm_ptToTime').value=document.getElementById('ptToTimeItt'+id).value;	   	
	   	document.getElementById('paraFrm_hiddenEdit').value=id;	   
   }
   
function callDeletePT(id){
	var r=confirm("Are you sure to remove this record?");
		if(r==false){
			return false;
		}else{
	   	document.getElementById("paraFrm").action="Regularization_removePersonalTime.action";
	   	document.getElementById('paraFrm_hiddenEdit').value=id;
	    document.getElementById("paraFrm").submit();
	    }
   }
onLoad();
			
function onLoad(){
			if(document.getElementById('source').value=='attendanceRegularization' || document.getElementById('source').value=='myservices'){
			document.getElementById('shoeAddRowId').style.display='';
			}
			document.getElementById('reasonLabel').style.display='';
			var tot='<%=t%>';	
			var count='0';			
			if(tot>0){
			//if(document.getElementById('paraFrm_condTrueFlag').value=='true'){
					for(var i=0; i<tot;i++){
					if(document.getElementById('lateHrsDeductFromCode'+i).value==''){						
					document.getElementById(i).style.background='#FFC488';
					count++;
					}			
				}
				//}
				document.getElementById('paraFrm_countValue').value=count;	
				}
				var aa=document.getElementById('paraFrm_countValue').value;
				if(aa>0){
				document.getElementById('paraFrm_condTrueFlag').value='true';
				}
			}
			
function checkValidation(id){						
			var penDay=document.getElementById('penDays'+id).value;
			var redreDay=document.getElementById('redreDays'+id).value;						
			if(redreDay>penDay){
				alert('Redressal days must be less than penalty days');
				document.getElementById('redreDays'+id).value=penDay;
				return false;
			}						
	}
			
function ReplaceAll(Source,stringToFind,stringToReplace){
			  var temp = Source;
			  var index = temp.indexOf(stringToFind);
			   while(index != -1){
			   temp = temp.replace(stringToFind,stringToReplace);
			   index = temp.indexOf(stringToFind);        }
			   return temp;
	}
	
function callLeave(id){				
		// retrieveURL('Regularization_selCategory.action?','paraFrm');				
		javascript:callsF9(500,325,'Regularization_f9LeaveAction.action?srNo='+id);				
	}

function callForTimecard(){	
	try{
			var chk='<%=t%>';	
			var swipe='<%=tt%>';	
			var chkRR='<%=redCount%>';			
			var tot='<%=keep%> ';					
			if(eval(tot)=='0'){
			alert("Reporting structure not define");
				return false;
			}				
			if(document.getElementById('paraFrm_applyFor').value=='RR'){			
			    if(chkRR=='0'){
			         alert("You can not send this application,please add one or more record ");
				     return false;
			    }			
			}					
			else if(document.getElementById('paraFrm_applyFor').value=='LR'){					
				if(chk=='0'){
					alert("You can not send this application,please add one or more record ");
					return false;
				}			
			}
			else if(document.getElementById('paraFrm_applyFor').value=='PT'){
			}
			else{			
				for(var i=0; i<swipe;i++){
						var frmTime= document.getElementById('fromTime'+i).value;
 						var toTm = document.getElementById('toTime'+i).value; 						
						
						if(document.getElementById('fromTime'+i).value == ""){
 							alert("Please enter Actual In Time");
 							return false;
 						} 				
 						if(!validateTime('fromTime'+i,'actual.in.time')){
								return false;			
						} 				
 				 	    if(document.getElementById('toTime'+i).value == ""){
 							alert("Please enter Actual Out Time");
 							return false;
 						} 				
 						if(!validateTime('toTime'+i, 'actual.out.time')){
 							return false;
 						} 								
						/*else{
							document.getElementById('fromTime'+i).focus();
							return false;
						}*/	
						if(!validateTime('toTime'+i,'actual.out.time')){
							return false;
						}
						/*if(parseInt(frmTime)>=parseInt(toTime)){
							alert('Actual Out Time must be greater than Actual In Time');
							document.getElementById('toTime'+i).focus();
							return false
						}*/							
						/*var resultTime =timeDifference(frmTime,toTm, 'toTime'+i, 'actual.in.time', 'actual.out.time')
 						if(!resultTime){ 								
 							return false;		
 						}*/	 	 	
						if(document.getElementById('reasonItt'+i).value == "-1"){
							alert('Please select  reason');
						 	return false;
						}		 			
					}			
			}
			var reason = document.getElementById('paraFrm_reason').value;
			if(reason==""){ 	 
					alert('Please enter  comments');
					return false;		
			}		 		
			if(document.getElementById('paraFrm_condTrueFlag').value=='true'){
			var cc=document.getElementById('paraFrm_countValue').value;			
				if(cc >0){
				alert("You don't have sufficient balance ,please remove one or more record  ");
				return false;
				}		
			}			
			var actName=document.getElementById('paraFrm_actionName').value;
			actName=actName+".action";
			var con=confirm('Do you want to send this application for approval');
			if(con){					
				document.getElementById('paraFrm').action="Regularization_"+actName;	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";				
				document.getElementById('addButton').disabled=true;
				//document.getElementById('addButton1').disabled=true;				
				//return true;				
			}
			else{
				return false;
			}					
		}catch(e) { alert("Error----Timecard---"+e); }		
	}

function callApplySwipe(){				
		try{				
			var chk='<%=t%>';	
			var swipe='<%=tt%>';	
			var chkRR='<%=redCount%>';
			
			var tot='<%=keep%> ';					
			if(eval(tot)=='0'){
			alert("Reporting structure not define");
				return false;
			}	
			if(document.getElementById('paraFrm_applyFor').value=='RR'){			
			 if(chkRR=='0'){
			    alert("You can not send this application,please add one or more record ");
				return false;
			  }			
			}					
			else if(document.getElementById('paraFrm_applyFor').value=='LR'){
						
			if(chk=='0'){
			alert("You can not send this application,please add one or more record ");
				return false;
			}			
			}
			else if(document.getElementById('paraFrm_applyFor').value=='PT'){
			}
			else{
			
			/*if(swipe=='0'){
				alert("You can not send this application,please add one or more record ");
				return false;
			}
			*/
				for(var i=0; i<swipe;i++){
					if(!validateTime('fromTime'+i,'actual.in.time')){
						return false;			
					}
					/*else{
						document.getElementById('fromTime'+i).focus();
						return false;
					}*/	
					if(!validateTime('toTime'+i,'actual.out.time')){		
						return false;	
					}
					var frmTime=ReplaceAll(document.getElementById('fromTime'+i).value,':','');
					var toTime=ReplaceAll(document.getElementById('toTime'+i).value,':','');	
					if(parseInt(frmTime)>=parseInt(toTime)){
						alert('Actual Out Time must be greater than Actual In Time');
						document.getElementById('toTime'+i).focus();
						return false
					}	
				}			
			}			
			var reason = document.getElementById('paraFrm_reason').value;			
			if(reason==""){
		 			alert('Please enter reason');
		 			return false;		 					 
		 	}												
			if(document.getElementById('paraFrm_condTrueFlag').value=='true'){
				var cc=document.getElementById('paraFrm_countValue').value;			
				if(cc >0){
					alert("You don't have sufficient balance ,please remove one or more record  ");
					return false;
				}			
			}			
			var actName=document.getElementById('paraFrm_actionName').value;
			actName=actName+".action";
			var con=confirm('Do you want to send this application for approval');
			if(con){					
				document.getElementById('paraFrm').action="Regularization_"+actName;	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";				
				document.getElementById('addButton').disabled=true;
				document.getElementById('addButton1').disabled=true;				
				//return true;				
			}
			else{
				return false;
			}					
		}catch(e) { alert("Error--callApplySwipe-----"+e); }		
	}

function callAddInfo(){
			var info=document.getElementById('paraFrm_informName').value;
			if(info==''){
					alert('Please select keep inform to');
					return false;
			}				
	}

function callDelete(){
		var con=confirm('Do you want to remove !');
		if(con){
				return true;
		}
		else{
				return false;
		}
	}

function numbersonlyWithColun(myfield){
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789:").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}

function callCheckAll(){	
		var tot='<%=t%>';		
		if(document.getElementById('applyAll').checked){		
			for(var i=0; i<tot;i++){		
					document.getElementById('leaveCheck'+i).checked=true;
			}
		}	
		else{
			for(var i=0; i<tot;i++){		
				document.getElementById('leaveCheck'+i).checked=false;
			}
		}	
	}
		
function callCheckAllSwipe(){	
		var tot='<%=tt%>';	
			if(document.getElementById('swipeCheck').checked){		
				for(var i=0; i<tot;i++){		
					document.getElementById('swipe'+i).checked=true;
				}
			}	
			else{
				for(var i=0; i<tot;i++){		
					document.getElementById('swipe'+i).checked=false;
				}
			}	
	}
		
function callCheckAllRed(){	
		var tot='<%=redCount%>';			
			if(document.getElementById('redressal').checked){		
				for(var i=0; i<tot;i++){		
					document.getElementById('redre'+i).checked=true;
				}
			}	
			else{
				for(var i=0; i<tot;i++){		
					document.getElementById('redre'+i).checked=false;
				}
			}	
	}
		
function callDeleteRed(){
		var tot='<%=redCount%>';		
		var count='0';
		for(var i=0; i<tot;i++){
			if(document.getElementById('redre'+i).checked){
					count=count+1;
			}
		}
		if(count=='0'){
			alert('Please select at least one check box');
			return false;
		}		
		var con=confirm('<%=label.get("confirm.remove")%>');
			if(con){
				return true;
			}
			else{
				return false;
			}
	}
		
function callDeleteSwipe(){
		var tot='<%=tt%>';		
		var count='0';
		for(var i=0; i<tot;i++){
			if(document.getElementById('swipe'+i).checked){
				 count=count+1;
			}
		}
		if(count=='0'){
				alert('Please select at least one check box');
				return false;
		}		
		var con=confirm('Do you want to remove !');
		if(con){
			return true;
		}
		else{
			return false;
		}
	}
		
		
function callDeleteLate(){
		var tot='<%=t%>';		
		var count='0';
		for(var i=0; i<tot;i++){
			if(document.getElementById('leaveCheck'+i).checked){
				count=count+1;
			}
		}
		if(count=='0'){
			alert('Please select at least one check box');
			return false;
		}		
		var con=confirm('Do you want to remove !');
		if(con){
			return true;
		}
		else{
			return false;
		}
	}
		//showRegularizationList
</script>